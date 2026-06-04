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

package com.sun.javafx.scene.text;

/**
 * TabAdvancePolicy provides the next tab advance for any given position within the TextLayout.
 */
public interface TabAdvancePolicy {

    /**
     * Provides the next tab stop for the given position.
     * A value of 0 or less indicates that there are no more stops.
     *
     * @param offset the offset of the text layout relative to the owner {@code Node} edge
     * @param position the current position
     * @return the next tab stop
     */
    public float nextTabStop(float offset, float position);
}
