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
package javafx.scene.text;

/**
 * This class encapsulates an immutable single tab stop within the {@link TabStopPolicy}.
 *
 * @since 25
 */
public final class TabStop {
    private final double position;

    /**
     * Constructs a new tab stop with the specified position.
     *
     * @param position the position in pixels
     */
    public TabStop(double position) {
        this.position = position;
    }

    /**
     * Returns the position, in pixels, of the tab.
     * @return the position of the tab
     */
    public final double getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object x) {
        if (x == this) {
            return true;
        } else if (x instanceof TabStop p) {
            return position == p.position;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = TabStop.class.hashCode();
        h = 31 * h + Double.hashCode(position);
        return h;
    }
}
