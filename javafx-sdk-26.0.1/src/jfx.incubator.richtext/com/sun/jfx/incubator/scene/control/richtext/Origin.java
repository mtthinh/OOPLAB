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

/**
 * View origin: model index of the top paragraph index + offset in pixels from the upper edge of the top cell to
 * the upper edge of the view area.
 *
 * @param index the model index of a paragraph at the top of visible area
 * @param offset the distance in pixels from the top of the visible area to the top of the topmost paragraph
 */
public record Origin(int index, double offset) {
    /** beginning of the document */
    public static final Origin ZERO = new Origin(0, 0.0);

    @Override
    public String toString() {
        return "Origin{index=" + index + ", offset=" + offset + "}";
    }
}
