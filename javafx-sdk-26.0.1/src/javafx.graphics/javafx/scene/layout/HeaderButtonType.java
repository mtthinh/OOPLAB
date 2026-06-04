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

package javafx.scene.layout;

import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Identifies the semantic type of a button in a custom {@link HeaderBar}, which enables integrations
 * with the platform window manager. For example, hovering over a {@link #MAXIMIZE} button on Windows
 * will summon snap layouts.
 *
 * @since 25
 * @deprecated This is a preview feature which may be changed or removed in a future release.
 * @see HeaderBar#setButtonType(Node, HeaderButtonType)
 */
@Deprecated(since = "25")
public enum HeaderButtonType {

    /**
     * Identifies the iconify button.
     *
     * @see Stage#isIconified()
     * @see Stage#setIconified(boolean)
     */
    ICONIFY,

    /**
     * Identifies the maximize button.
     * <p>
     * This button toggles the {@link Stage#isMaximized()} or {@link Stage#isFullScreen()} property,
     * depending on platform-specific invocation semantics. For example, on macOS the button will
     * put the window into full-screen mode by default, but maximize it to cover the desktop when
     * the option key is pressed.
     * <p>
     * If the window is maximized, the button will have the {@code maximized} pseudo-class.
     *
     * @see Stage#isMaximized()
     * @see Stage#setMaximized(boolean)
     * @see Stage#isFullScreen()
     * @see Stage#setFullScreen(boolean)
     */
    MAXIMIZE,

    /**
     * Identifies the close button.
     *
     * @see Stage#close()
     */
    CLOSE
}
