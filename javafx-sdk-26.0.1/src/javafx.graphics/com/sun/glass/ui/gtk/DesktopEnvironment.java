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

package com.sun.glass.ui.gtk;

import java.util.Locale;

/**
 * Linux desktop environments.
 */
enum DesktopEnvironment {
    UNKNOWN,
    GNOME,
    KDE;

    /**
     * Returns the current desktop environment.
     */
    public static DesktopEnvironment current() {
        var result = parse(System.getenv("XDG_CURRENT_DESKTOP"));
        if (result != UNKNOWN) {
            return result;
        }

        result = parse(System.getenv("GDMSESSION"));
        if (result != UNKNOWN) {
            return result;
        }

        if (System.getenv("KDE_FULL_SESSION") != null) {
            return KDE;
        }

        return UNKNOWN;
    }

    private static DesktopEnvironment parse(String value) {
        if (value == null) {
            return UNKNOWN;
        }

        String v = value.toLowerCase(Locale.ROOT);

        if (v.contains("gnome")) {
            return GNOME;
        }

        if (v.contains("kde")) {
            return KDE;
        }

        return UNKNOWN;
    }
}
