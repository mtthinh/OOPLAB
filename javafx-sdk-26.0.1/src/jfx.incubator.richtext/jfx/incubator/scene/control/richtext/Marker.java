/*
 * Copyright (c) 2022, 2024, Oracle and/or its affiliates. All rights reserved.
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
// This code borrows heavily from the following project, with permission from the author:
// https://github.com/andy-goryachev/FxEditor

package jfx.incubator.scene.control.richtext;

import java.util.Objects;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import com.sun.jfx.incubator.scene.control.richtext.MarkerHelper;

/**
 * Tracks the text position in a document in the presence of edits.
 *
 * @since 24
 */
public final class Marker implements Comparable<Marker> {
    static {
        MarkerHelper.setAccessor(new MarkerHelper.Accessor() {
            @Override
            public void setMarkerPos(Marker m, TextPos p) {
                m.setTextPos(p);
            }

            @Override
            public Marker createMarker(TextPos p) {
                return new Marker(p);
            }
        });
    }

    private final ReadOnlyObjectWrapper<TextPos> pos;

    private Marker(TextPos pos) {
        Objects.nonNull(pos);
        this.pos = new ReadOnlyObjectWrapper<>(pos);
    }

    @Override
    public String toString() {
        return "Marker{index=" + getIndex() + ", offset=" + getOffset() + "}";
    }

    /**
     * This property tracks the marker's position within the model (value is never null).
     * @return the text position property
     */
    public final ReadOnlyObjectProperty<TextPos> textPosProperty() {
        return pos.getReadOnlyProperty();
    }

    public final TextPos getTextPos() {
        return pos.get();
    }

    private final void setTextPos(TextPos p) {
        pos.set(p);
    }

    @Override
    public final int compareTo(Marker m) {
        return getTextPos().compareTo(m.getTextPos());
    }

    @Override
    public int hashCode() {
        int h = Marker.class.hashCode();
        h = h * 31 + getTextPos().hashCode();
        return h;
    }

    @Override
    public boolean equals(Object x) {
        if (x == this) {
            return true;
        } else if (x instanceof Marker m) {
            return getTextPos().equals(m.getTextPos());
        }
        return false;
    }

    /**
     * Returns the paragraph index.
     * @return the paragraph index
     */
    public final int getIndex() {
        return getTextPos().index();
    }

    /**
     * Returns the text offset within the paragraph.
     * @return the offset value
     */
    public final int getOffset() {
        return getTextPos().offset();
    }
}
