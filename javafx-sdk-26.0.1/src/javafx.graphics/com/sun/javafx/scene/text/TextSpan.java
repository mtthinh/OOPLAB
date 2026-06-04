/*
 * Copyright (c) 2012, 2025, Oracle and/or its affiliates. All rights reserved.
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

import javafx.scene.layout.Region;
import com.sun.javafx.geom.RectBounds;

/**
 * Represents a sequence of characters all using the same font, or
 * an embedded object if no font is supplied.
 * <p>
 * A text span can contain line breaks if the text should span multiple
 * lines.
 */
public interface TextSpan {
    /**
     * The text for the span, can be empty but not null.
     */
    public String getText();

    /**
     * The font for the span, if null the span is handled as embedded object.
     */
    public Object getFont();

    /**
     * The bounds for embedded object, only used when the font returns null.
     * The text for a embedded object should be a single char ("\uFFFC" is
     * recommended).
     */
    public RectBounds getBounds();

    /**
     * Returns the {@code Region} which contains the layout for this TextSpan.
     *
     * @return the layout root, or null
     */
    public Region getLayoutRootRegion();
}
