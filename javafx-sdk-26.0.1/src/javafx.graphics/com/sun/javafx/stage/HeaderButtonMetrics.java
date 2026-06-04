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

package com.sun.javafx.stage;

import javafx.geometry.Dimension2D;
import javafx.scene.layout.HeaderBar;
import javafx.stage.StageStyle;
import java.util.Objects;

/**
 * Provides metrics about the header buttons of {@link StageStyle#EXTENDED} windows.
 *
 * @param leftInset the size of the left inset
 * @param rightInset the size of the right inset
 * @param minHeight the minimum height of the window buttons
 * @see HeaderBar
 */
public record HeaderButtonMetrics(Dimension2D leftInset, Dimension2D rightInset, double minHeight) {

    public HeaderButtonMetrics {
        Objects.requireNonNull(leftInset);
        Objects.requireNonNull(rightInset);

        if (minHeight < 0) {
            throw new IllegalArgumentException("minHeight cannot be negative");
        }
    }
}
