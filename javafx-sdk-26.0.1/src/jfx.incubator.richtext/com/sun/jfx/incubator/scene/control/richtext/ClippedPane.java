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

package com.sun.jfx.incubator.scene.control.richtext;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Pane that allows for container/controller to lay out its children,
 * clipping its content to its bounds.
 */
public class ClippedPane extends Pane {
    private final Rectangle clip;

    public ClippedPane(String cssName) {
        getStyleClass().add(cssName);

        clip = new Rectangle();
        clip.setSmooth(false);
        clip.widthProperty().bind(widthProperty());
        clip.heightProperty().bind(heightProperty());

        setClip(clip);
    }
}
