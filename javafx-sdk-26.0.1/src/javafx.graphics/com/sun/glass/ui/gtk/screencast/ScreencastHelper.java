/*
 * Copyright (c) 2023, 2025, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.sun.glass.ui.gtk.screencast;

import com.sun.glass.ui.Screen;

import com.sun.javafx.geom.Rectangle;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Helper class for grabbing pixels from the screen using the
 * <a href="https://flatpak.github.io/xdg-desktop-portal/#gdbus-org.freedesktop.portal.ScreenCast">
 * org.freedesktop.portal.ScreenCast API</a>
 */

public final class ScreencastHelper {

    static final boolean SCREENCAST_DEBUG;
    private static final boolean IS_NATIVE_LOADED;

    private static final int ERROR = -1;
    private static final int DENIED = -11;
    private static final int OUT_OF_BOUNDS = -12;
    private static final int NO_STREAMS = -13;

    private static final int XDG_METHOD_SCREENCAST = 0;
    private static final int XDG_METHOD_REMOTE_DESKTOP = 1;

    private static final int DELAY_BEFORE_SESSION_CLOSE = 2000;

    private static volatile TimerTask timerTask = null;
    private static final Timer timerCloseSession
            = new Timer("auto-close screencast session", true);


    private ScreencastHelper() {}

    static {
        SCREENCAST_DEBUG = Boolean.getBoolean("javafx.robot.screenshotDebug");

        boolean loadFailed = false;

        boolean shouldLoadNative = XdgDesktopPortal.isRemoteDesktop()
                || XdgDesktopPortal.isScreencast();

        int methodId = XdgDesktopPortal.isScreencast()
                ? XDG_METHOD_SCREENCAST
                : XDG_METHOD_REMOTE_DESKTOP;

        if (!(shouldLoadNative && loadPipewire(methodId, SCREENCAST_DEBUG))) {

            System.err.println(
                    "Could not load native libraries for ScreencastHelper"
            );

            loadFailed = true;
        }

        IS_NATIVE_LOADED = !loadFailed;
    }

    public static boolean isAvailable() {
        return IS_NATIVE_LOADED;
    }

    private static native boolean loadPipewire(int method, boolean isDebug);

    private static native int getRGBPixelsImpl(
            int x, int y, int width, int height,
            int[] pixelArray,
            int[] affectedScreensBoundsArray,
            String token
    );

    public static int clipRound(final double coordinate) {
        final double newv = coordinate - 0.5;
        if (newv < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (newv > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) Math.ceil(newv);
    }

    private static List<Rectangle> getSystemScreensBounds() {
        return Screen
                .getScreens()
                .stream()
                .map(screen -> new Rectangle(
                        clipRound(screen.getPlatformX() * screen.getPlatformScaleX()),
                        clipRound(screen.getPlatformY() * screen.getPlatformScaleY()),
                        clipRound(screen.getPlatformWidth() * screen.getPlatformScaleX()),
                        clipRound(screen.getPlatformHeight() * screen.getPlatformScaleY())
                ))
                .toList();
    }

    private static synchronized native void closeSession();

    private static void timerCloseSessionRestart() {
        if (timerTask != null) {
            timerTask.cancel();
        }

        timerTask = new TimerTask() {
            @Override
            public void run() {
                closeSession();
            }
        };

        timerCloseSession.schedule(timerTask, DELAY_BEFORE_SESSION_CLOSE);
    }

    public static synchronized void getRGBPixels(
            int x, int y, int width, int height, int[] pixelArray
    ) {
        if (!IS_NATIVE_LOADED) return;

        timerCloseSessionRestart();

        Rectangle captureArea = new Rectangle(x, y, width, height);

        List<Rectangle> affectedScreenBounds = getSystemScreensBounds()
                .stream()
                .filter(r -> !captureArea.intersection(r).isEmpty())
                .toList();

        if (SCREENCAST_DEBUG) {
            System.out.printf("// getRGBPixels in %s, affectedScreenBounds %s\n",
                    captureArea, affectedScreenBounds);
        }

        if (affectedScreenBounds.isEmpty()) {
            if (SCREENCAST_DEBUG) {
                System.out.println("// getRGBPixels - requested area "
                        + "outside of any screen");
            }
            return;
        }

        int retVal;
        Set<TokenItem> tokensForRectangle =
                TokenStorage.getTokens(affectedScreenBounds);

        int[] affectedScreenBoundsArray = affectedScreenBounds
                .stream()
                .filter(r -> !captureArea.intersection(r).isEmpty())
                .flatMapToInt(bounds -> IntStream.of(
                        bounds.x, bounds.y,
                        bounds.width, bounds.height
                ))
                .toArray();

        for (TokenItem tokenItem : tokensForRectangle) {
            retVal = getRGBPixelsImpl(
                    x, y, width, height,
                    pixelArray,
                    affectedScreenBoundsArray,
                    tokenItem.token
            );

            debugReturnValue(retVal);

            if (retVal >= 0  // we have received a screen data
                || retVal == ERROR
                || retVal == DENIED) {
                return;
            } // else, try other tokens
        }

        // we do not have a saved token or it did not work,
        // try without the token to show the system's permission request window
        retVal = getRGBPixelsImpl(
                x, y, width, height,
                pixelArray,
                affectedScreenBoundsArray,
                null
        );

        debugReturnValue(retVal);
    }

    private static boolean debugReturnValue(int retVal) {
        if (retVal == DENIED) {
            if (SCREENCAST_DEBUG) {
                System.err.println("robot action: access denied by user.");
            }
        } else if (retVal == ERROR) {
            if (SCREENCAST_DEBUG) {
                System.err.println("robot action: failed.");
            }
        } else if (retVal == OUT_OF_BOUNDS) {
            if (SCREENCAST_DEBUG) {
                System.err.println(
                        "Token does not provide access to requested area.");
            }
        } else if (retVal == NO_STREAMS) {
            if (SCREENCAST_DEBUG) {
                System.err.println("robot action: no streams available");
            }
        }
        return retVal != ERROR;
    }

    private static void performWithToken(Function<String, Integer> func) {
        if (!XdgDesktopPortal.isRemoteDesktop() || !IS_NATIVE_LOADED) return;

        timerCloseSessionRestart();

        for (TokenItem tokenItem : TokenStorage.getTokens(getSystemScreensBounds())) {
            int retVal = func.apply(tokenItem.token);

            if (retVal >= 0 || !debugReturnValue(retVal)) {
                return;
            }
        }

        debugReturnValue(func.apply(null));
    }

    public static synchronized void remoteDesktopMouseMove(int x, int y) {
        performWithToken((token) -> remoteDesktopMouseMoveImpl(x, y, token));
    }

    public static synchronized void remoteDesktopMouseButton(boolean isPress, int buttons) {
        performWithToken((token) -> remoteDesktopMouseButtonImpl(isPress, buttons, token));
    }

    public static synchronized void remoteDesktopMouseWheel(int wheel) {
        performWithToken((token) -> remoteDesktopMouseWheelImpl(wheel, token));
    }

    public static synchronized void remoteDesktopKey(boolean isPress, int key) {
        performWithToken((token) -> remoteDesktopKeyImpl(isPress, key, token));
    }

    private static synchronized native int remoteDesktopMouseMoveImpl(int x, int y, String token);
    private static synchronized native int remoteDesktopMouseButtonImpl(boolean isPress, int buttons, String token);
    private static synchronized native int remoteDesktopMouseWheelImpl(int wheelAmt, String token);
    private static synchronized native int remoteDesktopKeyImpl(boolean isPress, int key, String token);
}
