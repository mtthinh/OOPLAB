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

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A spacer node used to emulate the first line indent.
 *
 * FIX problems:
 * - selection: TextFlow thinks there is a separate node (click on left side, move to right side of this node)
 */
public class FirstLineIndentSpacer extends Rectangle {
    public FirstLineIndentSpacer(double width) {
        super(width, 1);
        setFill(Color.rgb(0, 0, 0, 0.0));
    }
}
