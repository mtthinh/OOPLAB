/*
 * Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.
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
 * Defines APIs for the WebView functionality contained within the the
 * JavaFX UI toolkit.
 *
 * @moduleGraph
 * @since 9
 */
module javafx.web {
    requires java.desktop;
    requires java.net.http;
    requires javafx.media;
    requires jdk.jsobject;
    requires jdk.xml.dom;

    requires transitive java.xml;
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;

    exports javafx.scene.web;

    exports com.sun.javafx.fxml.builder.web to
        javafx.fxml;
}
