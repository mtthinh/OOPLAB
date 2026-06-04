/*
 * Copyright (c) 2023, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.application;

import com.sun.javafx.PlatformUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Enumeration of possible high contrast scheme values.
 * <p>
 * For each scheme, a theme key is defined. These keys can be
 * used in a resource bundle that defines the theme name values
 * for supported locales.
 * <p>
 * The high contrast feature may not be available on all platforms.
 */
enum WindowsHighContrastScheme {

    NONE(null),
    HIGH_CONTRAST_BLACK("high.contrast.black.theme"),
    HIGH_CONTRAST_WHITE("high.contrast.white.theme"),
    HIGH_CONTRAST_1("high.contrast.1.theme"),
    HIGH_CONTRAST_2("high.contrast.2.theme");

    private static List<ResourceBundle> resourceBundles;

    static {
        if (PlatformUtil.isWindows()) {
            resourceBundles = Arrays.stream(Locale.getAvailableLocales())
                .map(locale -> ResourceBundle.getBundle("com/sun/glass/ui/win/themes", locale))
                .distinct()
                .toList();
        }
    }

    private final String themeKey;

    WindowsHighContrastScheme(String themeKey) {
        this.themeKey = themeKey;
    }

    /**
     * Given a theme name string, this method finds the possible enum constant
     * for which the result of a function, applying its theme key, matches the theme name.
     *
     * @param themeName a string with the localized theme name (for the locale of the OS, not the JVM)
     * @return the enum constant or {@code NONE} if not found
     */
    static WindowsHighContrastScheme fromThemeName(String themeName) {
        if (themeName == null || resourceBundles == null) {
            return NONE;
        }

        // Iterate over all resource bundles and try to find a value that matches the theme name
        // we got from the OS. We can't just look in the properties file for the current locale,
        // since we might be running on a JVM with a locale that is different from the OS.
        for (WindowsHighContrastScheme item : values()) {
            for (ResourceBundle resourceBundle : resourceBundles) {
                if (item != NONE && themeName.equalsIgnoreCase(resourceBundle.getString(item.themeKey))) {
                    return item;
                }
            }
        }

        return NONE;
    }
}
