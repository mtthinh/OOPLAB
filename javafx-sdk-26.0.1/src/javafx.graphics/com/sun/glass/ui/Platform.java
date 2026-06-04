/*
 * Copyright (c) 2010, 2025, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.glass.ui;

import com.sun.javafx.PlatformUtil;

final class Platform {

    public static final String MAC = "Mac";
    public static final String WINDOWS = "Win";
    public static final String GTK = "Gtk";
    public static final String IOS = "Ios";
    public static final String HEADLESS = "Headless";
    public static final String UNKNOWN = "unknown";

    static private String type = null;

    static public synchronized String determinePlatform() {
        if (type == null) {

            // Provide for a runtime override, allowing EGL for example
            String userPlatform = System.getProperty("glass.platform");

            if (userPlatform != null) {
                if (userPlatform.equals("macosx"))
                   type = MAC;
                else if (userPlatform.equals("windows"))
                   type = WINDOWS;
                else if (userPlatform.equals("linux"))
                   type = GTK;
                else if (userPlatform.equals("gtk"))
                   type = GTK;
                else if (userPlatform.equals("ios"))
                   type = IOS;
                else if (userPlatform.equals("headless"))
                   type = HEADLESS;
                else
                   type = userPlatform;
                return type;
            }

            if (PlatformUtil.isMac()) {
                type = MAC;
            } else if (PlatformUtil.isWindows()) {
                type = WINDOWS;
            } else if (PlatformUtil.isLinux()) {
                type = GTK;
            } else if (PlatformUtil.isIOS()) {
                type = IOS;
            }
        }

        return type;
    }
}
