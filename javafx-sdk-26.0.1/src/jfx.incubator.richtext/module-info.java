/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Incubates the RichTextArea control and related classes.
 * <p>
 * <b><a href="https://openjdk.org/jeps/11">Incubating Feature.</a>
 * Will be removed in a future release.</b>
 *
 * @moduleGraph
 * @since 24
 */
module jfx.incubator.richtext {
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive jfx.incubator.input;
    requires java.desktop;

    exports jfx.incubator.scene.control.richtext;
    exports jfx.incubator.scene.control.richtext.skin;
    exports jfx.incubator.scene.control.richtext.model;
}
