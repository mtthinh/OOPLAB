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
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.MapChangeListener;

final class PlatformThemeObserver {

    private static final String THEME_NAME_KEY = "GTK.theme_name";

    private final ReadOnlyStringWrapper stylesheet = new ReadOnlyStringWrapper(this, "stylesheet");

    private PlatformThemeObserver() {
        PlatformImpl.getPlatformPreferences().addListener((MapChangeListener<String, Object>) change -> {
            if (THEME_NAME_KEY.equals(change.getKey())) {
                updateThemeStylesheets();
            }
        });

        updateThemeStylesheets();
    }

    public static PlatformThemeObserver getInstance() {
        class Holder {
            static final PlatformThemeObserver instance = new PlatformThemeObserver();
        }

        return Holder.instance;
    }

    public ReadOnlyStringProperty stylesheetProperty() {
        return stylesheet.getReadOnlyProperty();
    }

    private void updateThemeStylesheets() {
        stylesheet.set(WindowDecorationTheme.findBestTheme().getStylesheet());
    }
}
