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

import com.sun.javafx.application.PlatformImpl;
import javafx.stage.StageStyle;
import java.util.Locale;
import java.util.Map;

/**
 * The client-side window decoration theme used for {@link StageStyle#EXTENDED} windows.
 */
enum WindowDecorationTheme {

    GNOME("WindowDecorationGnome.css"),
    KDE("WindowDecorationKDE.css");

    WindowDecorationTheme(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    private static final String THEME_NAME_KEY = "GTK.theme_name";

    /**
     * A mapping of platform theme names to the most similar window decoration theme.
     */
    private static final Map<String, WindowDecorationTheme> SIMILAR_THEMES = Map.of(
        "adwaita", WindowDecorationTheme.GNOME,
        "yaru", WindowDecorationTheme.GNOME,
        "breeze", WindowDecorationTheme.KDE
    );

    private final String stylesheet;

    /**
     * Determines the best window decoration theme for the current platform theme and desktop environment.
     * <p>
     * Since we can't ship decorations for all possible platform themes, we need to choose the theme most
     * similar to the platform theme. If we can't choose a theme by name, we fall back to choosing a theme
     * by determining the current desktop environment.
     */
    public static WindowDecorationTheme findBestTheme() {
        return PlatformImpl.getPlatformPreferences()
            .getString(THEME_NAME_KEY)
            .map(name -> {
                for (Map.Entry<String, WindowDecorationTheme> entry : SIMILAR_THEMES.entrySet()) {
                    if (name.toLowerCase(Locale.ROOT).startsWith(entry.getKey())) {
                        return entry.getValue();
                    }
                }

                return null;
            })
            .orElse(switch (DesktopEnvironment.current()) {
                case GNOME -> WindowDecorationTheme.GNOME;
                case KDE -> WindowDecorationTheme.KDE;
                default -> WindowDecorationTheme.GNOME;
            });
    }

    public String getStylesheet() {
        var url = getClass().getResource(stylesheet);
        if (url == null) {
            throw new RuntimeException("Resource not found: " + stylesheet);
        }

        return url.toExternalForm();
    }
}
