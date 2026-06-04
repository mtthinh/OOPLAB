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

import java.util.concurrent.atomic.AtomicBoolean;
import com.sun.javafx.geom.Point2D;
import com.sun.javafx.geom.RectBounds;


public interface GlyphList {

    /**
     * Returns the number of glyphs in GlyphList.
     */
    public int getGlyphCount();

    /**
     * Returns the glyph code for the given glyphIndex.
     */
    public int getGlyphCode(int glyphIndex);

    /**
     * The x position for the given glyphIndex relative the GlyphList.
     */
    public float getPosX(int glyphIndex);

    /**
     * The y position for the given glyphIndex relative the GlyphList.
     */
    public float getPosY(int glyphIndex);

    /**
     * Returns the width of the GlyphList
     */
    public float getWidth();

    /**
     * Returns the height of the GlyphList
     */
    public float getHeight();

    /**
     * See TextLine#getBounds()
     * (used outside text layout in rendering and span bounds)
     */
    public RectBounds getLineBounds();

    /**
     * The top-left location of the GlyphList relative to
     * the origin of the Text Layout.
     */
    public Point2D getLocation();

    /**
     * Maps the given glyph index to the char offset.
     * (used during rendering (selection))
     */
    public int getCharOffset(int glyphIndex);


    /**
     * Means that this GlyphList was shaped using complex processing (ICU),
     * either because it is complex script or because font features were
     * requested.
     * (used outside text layout in rendering)
     */
    public boolean isComplex();

    /**
     * Used during layout children (for rich text)
     * can be null (for non-rich text) but never null for rich text.
     */
    public TextSpan getTextSpan();

    /**
     * Returns true if this GlyphList is a line break.
     * @return whether this GlyphList is a line break
     */
    public boolean isLinebreak();

    /**
     * Returns the start offset.
     * @return the start offset
     */
    public int getStart();

    /**
     * Gets the glyph offset at the specified x coordinate.
     * @param x the x coordinate
     * @param trailing the reference to hold the trailing flag
     * @return the glyph offset
     */
    public int getOffsetAtX(float x, AtomicBoolean trailing);
}

