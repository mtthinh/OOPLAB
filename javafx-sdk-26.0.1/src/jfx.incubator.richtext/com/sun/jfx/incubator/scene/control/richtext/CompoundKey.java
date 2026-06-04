/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jfx.incubator.scene.control.richtext;

import java.util.Arrays;

/**
 * Compound Key
 */
public class CompoundKey {
    private final Object[] keys;

    public CompoundKey(Object... keys) {
        this.keys = keys;
    }

    @Override
    public int hashCode() {
        int h = CompoundKey.class.hashCode();
        return 31 * h + Arrays.hashCode(keys);
    }

    @Override
    public boolean equals(Object x) {
        if (x == this) {
            return true;
        } else if (x instanceof CompoundKey c) {
            return Arrays.equals(keys, c.keys);
        } else {
            return false;
        }
    }
}
