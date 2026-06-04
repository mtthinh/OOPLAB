/*
 * Copyright (c) 2024, 2025, Oracle and/or its affiliates. All rights reserved.
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
package javafx.scene.text;

import javafx.geometry.Rectangle2D;
import com.sun.javafx.text.PrismCaretInfo;

/**
 * Provides the information associated with the caret.
 * <p>
 * Typically, the caret is represented by a single vertical line which visually indicates the
 * position within the text.  In some cases, where the caret is positioned between left-to-right and
 * right-to-left text, two line segments will be shown, indicating the insertion position for both left-to-right
 * and right-to-left character.
 *
 * @since 25
 */
public sealed abstract class CaretInfo permits PrismCaretInfo {
    /**
     * Constructor for subclasses to call.
     */
    protected CaretInfo() {
    }

    /**
     * Returns the number of segments representing the caret.
     *
     * @return the number of segments representing the caret
     */
    public abstract int getSegmentCount();

    /**
     * Returns the geometry of the segment at the specified index.
     *
     * @param index the line index
     * @return the bounds of the caret segment
     * @throws IndexOutOfBoundsException if the index is out of range
     *     {@code (index < 0 || index >= getSegmentCount())}
     */
    public abstract Rectangle2D getSegmentAt(int index);
}
