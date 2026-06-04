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

package com.sun.javafx.text;

import com.sun.javafx.geom.RectBounds;
import com.sun.javafx.scene.text.TextLine;

public class PrismTextLine implements TextLine {
    private final TextRun[] runs;
    private final RectBounds bounds;
    private final int start;
    private final int length;
    private final float leading;
    private float lsb;
    private float rsb;

    public PrismTextLine(int start, int length, TextRun[] runs,
                         float width, float ascent, float descent, float leading) {
        this.start = start;
        this.length = length;
        this.bounds = new RectBounds(0, ascent, width, descent + leading);
        this.leading = leading;
        this.runs = runs;
    }

    @Override
    public RectBounds getBounds() {
        return bounds;
    }

    public float getLeading() {
        return leading;
    }

    @Override
    public TextRun[] getRuns() {
        return runs;
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int getLength() {
        return length;
    }

    public void setSideBearings(float lsb, float rsb) {
        this.lsb = lsb;
        this.rsb = rsb;
    }

    @Override
    public float getLeftSideBearing() {
        return lsb;
    }

    @Override
    public float getRightSideBearing() {
        return rsb;
    }

    public void setAlignment(float x) {
        bounds.setMinX(x);
        bounds.setMaxX(x + bounds.getMaxX());
    }

    public void setWidth(float width) {
        bounds.setMaxX(bounds.getMinX() + width);
    }
}
