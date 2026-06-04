/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

import java.io.BufferedReader;
import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class XdgDesktopPortal {
    private static final String METHOD_GTK = "gtk";
    private static final String METHOD_SCREENCAST = "dbusScreencast";
    private static final String METHOD_REMOTE_DESKTOP = "dbusRemoteDesktop";

    private static final String method;
    private static final boolean isRemoteDesktop;
    private static final boolean isScreencast;

    private XdgDesktopPortal() {}

    static {
        String waylandDisplay = System.getenv("WAYLAND_DISPLAY");
        boolean isOnWayland = waylandDisplay != null && !waylandDisplay.isBlank();

        String defaultMethod = METHOD_GTK;
        if (isOnWayland) {
            Integer gnomeShellVersion = null;

            if ("gnome".equals(getDesktop())) {
                gnomeShellVersion = getGnomeShellMajorVersion();
            }

            defaultMethod = (gnomeShellVersion != null && gnomeShellVersion >= 47)
                    ? METHOD_REMOTE_DESKTOP
                    : METHOD_SCREENCAST;
        }

        String m = System.getProperty("javafx.robot.screenshotMethod", defaultMethod);

        if (!METHOD_REMOTE_DESKTOP.equals(m)
                && !METHOD_SCREENCAST.equals(m)
                && !METHOD_GTK.equals(m)) {
            m = defaultMethod;
        }

        isRemoteDesktop = METHOD_REMOTE_DESKTOP.equals(m);
        isScreencast = METHOD_SCREENCAST.equals(m);
        method = m;
    }

    public static String getMethod() {
        return method;
    }

    public static boolean isRemoteDesktop() {
        return isRemoteDesktop;
    }

    public static boolean isScreencast() {
        return isScreencast;
    }

    private static String getDesktop() {
        String gnome = "gnome";
        String gsi = System.getenv("GNOME_DESKTOP_SESSION_ID");
        if (gsi != null) {
            return gnome;
        }

        String desktop = System.getenv("XDG_CURRENT_DESKTOP");
        return (desktop != null && desktop.toLowerCase().contains(gnome))
                ? gnome : null;
    }

    private static Integer getGnomeShellMajorVersion() {
        try {
            Process process =
                    new ProcessBuilder("/usr/bin/gnome-shell", "--version")
                            .start();
            try (BufferedReader reader = process.inputReader()) {
                if (process.waitFor(2, SECONDS) &&  process.exitValue() == 0) {
                    String line = reader.readLine();
                    if (line != null) {
                        String[] versionComponents = line
                                .replaceAll("[^\\d.]", "")
                                .split("\\.");

                        if (versionComponents.length >= 1) {
                            return Integer.parseInt(versionComponents[0]);
                        }
                    }
                }
            }
        } catch (IOException
                 | InterruptedException
                 | IllegalThreadStateException
                 | NumberFormatException ignored) {
        }

        return null;
    }
}
