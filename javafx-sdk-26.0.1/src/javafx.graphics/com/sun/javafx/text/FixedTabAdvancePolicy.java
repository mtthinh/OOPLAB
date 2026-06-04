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

package com.sun.javafx.text;

import com.sun.javafx.scene.text.TabAdvancePolicy;

/**
 * TabAdvancePolicy based on a fixed tab size.
 */
public class FixedTabAdvancePolicy implements TabAdvancePolicy {

    private final float tabAdvance;

    /**
     * Creates a tab advance policy for the given tab size.
     *
     * @param tabSize the tab size
     * @param spaceAdvance the advance of the space character
     */
    public FixedTabAdvancePolicy(int tabSize, float spaceAdvance) {
        this.tabAdvance = Math.max(1, tabSize) * spaceAdvance;
    }

    @Override
    public float nextTabStop(float offset, float position) {
        if (tabAdvance == 0.0) {
            return -1.0f;
        }
        return nextPosition(position, tabAdvance);
    }

    static float nextPosition(float position, float tabAdvance) {
        // there is a weird case (tabAdvance=57.6 and position=172.79999)
        // when the original formula
        // float f = ((int)(position / tabAdvance) + 1) * tabAdvance;
        // returns the same pos=172.79999 next=172.79999
        float n = (position / tabAdvance);
        return ((int)(n + Math.ulp(n)) + 1) * tabAdvance;
    }

    @Override
    public boolean equals(Object x) {
        if (x == this) {
            return true;
        } else if (x instanceof FixedTabAdvancePolicy p) {
            return tabAdvance == p.tabAdvance;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = FixedTabAdvancePolicy.class.hashCode();
        return h * 31 + Float.floatToIntBits(tabAdvance);
    }

    @Override
    public String toString() {
        return "FixedTabAdvancePolicy{tabAdvance=" + tabAdvance + "}";
    }
}
