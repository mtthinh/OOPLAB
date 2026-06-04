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

/**
 * Provides the information about a text line in a text layout.
 *
 * @param start the start offset for the line
 * @param end the end offset for the line (index of the last character + 1)
 * @param bounds the bounds of the text line, in local coordinates:
 * <ul>
 *   <li>
 *     {@code minX} - the x origin of the line (relative to the layout).
 *     The x origin is defined by TextAlignment of the text layout, always zero
 *     for left-aligned text.
 *   <li>
 *     {@code minY} - the ascent of the line (negative).
 *     The ascent of the line is the max ascent of all fonts in the line.
 *   <li>
 *     {@code width} - the width of the line.
 *     The width of the line is sum of all the run widths in the line, it is not
 *     affect by the wrapping width but it will include any changes caused by
 *     justification.
 *   <li>
 *     {@code height} - the height of the line.
 *     The height of the line is sum of the max ascent, max descent, and
 *     max line gap of all the fonts in the line.
 * </ul>
 *
 * @since 25
 */
public record TextLineInfo(int start, int end, Rectangle2D bounds) {
}
