/*
 * Copyright (c) 2010, 2026, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.glass.ui.win;

import com.sun.glass.events.MouseEvent;
import com.sun.glass.ui.HeaderButtonOverlay;
import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.View;
import com.sun.javafx.tk.HeaderAreaType;

import java.util.Map;

/**
 * MS Windows platform implementation class for View.
 */
final class WinView extends View {

    private native static void _initIDs();
    static {
        _initIDs();
        multiClickTime = _getMultiClickTime_impl();
        multiClickMaxX = _getMultiClickMaxX_impl();
        multiClickMaxY = _getMultiClickMaxY_impl();
    }

    // Constants
    private static final long multiClickTime;
    private static final int multiClickMaxX, multiClickMaxY;

    protected WinView() {
        super();
    }

    native private static long _getMultiClickTime_impl();
    native private static int _getMultiClickMaxX_impl();
    native private static int _getMultiClickMaxY_impl();

    static long getMultiClickTime_impl() {
        return multiClickTime;
    }

    static int getMultiClickMaxX_impl() {
        return multiClickMaxX;
    }

    static int getMultiClickMaxY_impl() {
        return multiClickMaxY;
    }

    @Override
    protected long _getNativeFrameBuffer(long ptr) {
        return 0;
    }

    @Override native protected void _enableInputMethodEvents(long ptr, boolean enable);
    @Override native protected void _finishInputMethodComposition(long ptr);

    @Override native protected long _create(Map caps);
    @Override native protected long _getNativeView(long ptr);
    @Override native protected int _getX(long ptr);
    @Override native protected int _getY(long ptr);
    @Override native protected void _setParent(long ptr, long parentPtr);
    @Override native protected boolean _close(long ptr);
    @Override native protected void _scheduleRepaint(long ptr);
    @Override native protected void _begin(long ptr);
    @Override native protected void _end(long ptr);
    @Override native protected void _uploadPixels(long ptr, Pixels pixels);
    @Override native protected boolean _enterFullscreen(long ptr, boolean animate, boolean keepRatio, boolean hideCursor);
    @Override native protected void _exitFullscreen(long ptr, boolean animate);

    @Override
    protected void notifyResize(int width, int height) {
        super.notifyResize(width, height);

        // After resizing, do a move notification to force the view relocation.
        // When moving to a screen with different DPI settings, its location needs
        // to be recalculated.
        updateLocation();
    }

    @Override
    protected void notifyMenu(int x, int y, int xAbs, int yAbs, boolean isKeyboardTrigger) {
        // If all of the following conditions are satisfied, we open a system menu at the specified coordinates:
        // 1. The application didn't consume the menu event.
        // 2. The window is an EXTENDED window and is not in full-screen mode.
        // 3. The menu event occurred on a draggable area.
        if (!handleMenuEvent(x, y, xAbs, yAbs, isKeyboardTrigger)) {
            var window = (WinWindow)getWindow();
            if (!window.isExtendedWindow() || isInFullscreen()) {
                return;
            }

            double wx = x / window.getPlatformScaleX();
            double wy = y / window.getPlatformScaleY();

            EventHandler eventHandler = getEventHandler();
            if (eventHandler != null && eventHandler.pickHeaderArea(wx, wy) == HeaderAreaType.DRAGBAR) {
                window.showSystemMenu(x, y);
            }
        }
    }

    @Override
    protected boolean handleNonClientMouseEvent(long time, int type, int button, int x, int y, int xAbs, int yAbs,
                                                int modifiers, int clickCount) {
        if (!shouldHandleEvent()) {
            return false;
        }

        if (getWindow() instanceof WinWindow window) {
            double wx = x / window.getPlatformScaleX();
            double wy = y / window.getPlatformScaleY();

            // Give the header button overlay the first chance to handle the event.
            if (window.headerButtonOverlayProperty().get() instanceof HeaderButtonOverlay overlay
                    && overlay.handleMouseEvent(type, button, wx, wy)) {
                return true;
            }

            if (window.isResizable()
                    && clickCount == 2
                    && type == MouseEvent.DOWN
                    && getEventHandler() instanceof View.EventHandler eventHandler
                    && eventHandler.pickHeaderArea(wx, wy) == HeaderAreaType.DRAGBAR) {
                window.maximize(!window.isMaximized());
            }
        }

        // If the overlay didn't handle the event, we pass it down to the application.
        handleMouseEvent(time, type, button, x, y, xAbs, yAbs, modifiers, false, false);
        return true;
    }
}
