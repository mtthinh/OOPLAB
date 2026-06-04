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
package com.sun.javafx.text;

import java.util.Objects;
import javafx.geometry.Rectangle2D;
import javafx.scene.text.CaretInfo;

/**
 * CaretInfo as reported by the PrismTextLayout.
 */
public final class PrismCaretInfo extends CaretInfo {

    private final Rectangle2D[] parts;

    public PrismCaretInfo(Rectangle2D[] parts) {
        this.parts = parts;
    }

    @Override
    public int getSegmentCount() {
        return parts.length;
    }

    @Override
    public Rectangle2D getSegmentAt(int index) {
        Objects.checkIndex(index, parts.length);
        return parts[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < getSegmentCount(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(getSegmentAt(i));
        }
        sb.append("]");
        return sb.toString();
    }
}
