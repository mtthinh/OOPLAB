/*
 * Copyright (c) 2013, 2021, Oracle and/or its affiliates. All rights reserved.
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
 * <p>Defines APIs for the JavaFX / Swing interop support included with the
 *    JavaFX UI toolkit, including {@link javafx.embed.swing.SwingNode} (for
 *    embedding Swing inside a JavaFX application) and
 *    {@link javafx.embed.swing.JFXPanel} (for embedding JavaFX inside a Swing
 *    application). </p>
 * <p>The {@link javafx.embed.swing.JFXPanel JFXPanel} class defines a
 *    lightweight Swing component, which accepts and renders an instance of
 *    {@link javafx.scene.Scene Scene} and forwards all input events from Swing
 *    to the attached JavaFX scene.
 *    The {@link javafx.embed.swing.SwingNode} class is used to embed
 *    a Swing content into a JavaFX application.
 *    The content to be displayed is specified with the {@code SwingNode.setContent} method
 *    that accepts an instance of a Swing {@code JComponent}. The hierarchy of components
 *    contained in the {@code JComponent} instance should not contain any heavyweight
 *    components, otherwise {@code SwingNode} may fail to paint it. The content gets
 *    repainted automatically. All the input and focus events are forwarded to the
 *    {@code JComponent} instance.</p>
 */
package javafx.embed.swing;
