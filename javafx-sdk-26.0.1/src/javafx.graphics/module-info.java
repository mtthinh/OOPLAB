/*
 * Copyright (c) 2015, 2025, Oracle and/or its affiliates. All rights reserved.
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
 * Defines the core scenegraph APIs for the JavaFX UI toolkit
 * (such as layout containers, application lifecycle, shapes,
 * transformations, canvas, input, painting, image handling, and effects),
 * as well as APIs for animation, css, concurrency, geometry, printing, and
 * windowing.
 *
 * <p><b>Note:</b> The JavaFX classes must be loaded from a set of
 * named {@code javafx.*} modules on the <em>module path</em>.
 * Loading the JavaFX classes from the classpath is not supported.
 * See {@link javafx.application.Platform#startup(Runnable) Platform.startup}
 * for more information.
 *
 * @moduleGraph
 * @since 9
 */
module javafx.graphics {
    requires java.desktop;
    requires java.xml;

    requires transitive javafx.base;

    exports javafx.animation;
    exports javafx.application;
    exports javafx.concurrent;
    exports javafx.css;
    exports javafx.css.converter;
    exports javafx.geometry;
    exports javafx.print;
    exports javafx.scene;
    exports javafx.scene.canvas;
    exports javafx.scene.effect;
    exports javafx.scene.image;
    exports javafx.scene.input;
    exports javafx.scene.layout;
    exports javafx.scene.paint;
    exports javafx.scene.robot;
    exports javafx.scene.shape;
    exports javafx.scene.text;
    exports javafx.scene.transform;
    exports javafx.stage;

    exports com.sun.glass.ui to
        javafx.media,
        javafx.swing,
        javafx.web;
    exports com.sun.glass.utils to
        javafx.media,
        javafx.web;
    exports com.sun.javafx.application to
        java.base,
        javafx.controls,
        javafx.swing,
        javafx.web;
    exports com.sun.javafx.css to
        javafx.controls;
    exports com.sun.javafx.cursor to
        javafx.swing;
    exports com.sun.javafx.embed to
        javafx.swing;
    exports com.sun.javafx.font to
        javafx.web;
    exports com.sun.javafx.geom to
        javafx.controls,
        javafx.media,
        javafx.swing,
        javafx.web;
    exports com.sun.javafx.geom.transform to
        javafx.controls,
        javafx.media,
        javafx.swing,
        javafx.web;
    exports com.sun.javafx.iio to
        javafx.web;
    exports com.sun.javafx.menu to
        javafx.controls;
    exports com.sun.javafx.scene to
        javafx.controls,
        jfx.incubator.input,
        jfx.incubator.richtext,
        javafx.media,
        javafx.swing,
        javafx.web;
    exports com.sun.javafx.scene.input to
        javafx.controls,
        javafx.swing,
        javafx.web;
    exports com.sun.javafx.scene.layout to
        javafx.controls,
        javafx.web;
    exports com.sun.javafx.scene.text to
        javafx.controls,
        jfx.incubator.richtext,
        javafx.web;
    exports com.sun.javafx.scene.shape to
        javafx.controls;
    exports com.sun.javafx.scene.traversal to
        javafx.controls,
        jfx.incubator.input,
        jfx.incubator.richtext,
        javafx.web;
    exports com.sun.javafx.sg.prism to
        javafx.media,
        javafx.swing,
        javafx.web;
    exports com.sun.javafx.stage to
        javafx.controls,
        javafx.swing;
    exports com.sun.javafx.text to
        javafx.web;
    exports com.sun.javafx.tk to
        javafx.controls,
        javafx.media,
        javafx.swing,
        javafx.web;
    exports com.sun.javafx.util to
        javafx.controls,
        javafx.fxml,
        jfx.incubator.input,
        jfx.incubator.richtext,
        javafx.media,
        javafx.swing,
        javafx.web;
    exports com.sun.prism to
        javafx.media,
        javafx.web;
    exports com.sun.prism.image to
        javafx.web;
    exports com.sun.prism.paint to
        javafx.web;
    exports com.sun.scenario.effect to
        javafx.web;
    exports com.sun.scenario.effect.impl to
        javafx.web;
    exports com.sun.scenario.effect.impl.prism to
        javafx.web;
}
