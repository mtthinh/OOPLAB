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

package javafx.application;

/**
 * Defines the color scheme of the user interface, which specifies whether applications
 * should prefer light text on dark backgrounds, or dark text on light backgrounds.
 *
 * @see javafx.application.Platform.Preferences#colorSchemeProperty()
 * @since 22
 */
public enum ColorScheme {
    /**
     * A light color scheme uses bright backgrounds and dark text.
     */
    LIGHT,

    /**
     * A dark color scheme uses dark backgrounds and bright text.
     */
    DARK
}
