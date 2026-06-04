/*
 * Copyright (c) 2011, 2022, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.javafx.css;

import javafx.css.StyleOrigin;

public final class CalculatedValue {

    public static final CalculatedValue SKIP = new CalculatedValue(new int[0], null, false);


    public CalculatedValue(Object value, StyleOrigin origin, boolean relative) {

        this.value = value;
        this.origin = origin;
        this.relative = relative;
    }

    public Object getValue() {
        return value;
    }

    public StyleOrigin getOrigin() {
        return origin;
    }

    public boolean isRelative() {
        return relative;
    }

    @Override public String toString() {
        return
            (new StringBuilder()
                .append('{')
                .append(String.valueOf(value))
                .append(", ").append(origin)
                .append(", ").append(relative)
                .append('}')
            ).toString();
    }

    @Override public boolean equals(Object obj) {

        if(obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        CalculatedValue other = (CalculatedValue)obj;

        if (this.relative != other.relative)  {
            return false;
        }

        if (this.origin != other.origin) {
            return false;
        }

        if (this.value == null ? other.value != null : !this.value.equals(other.value)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int h = CalculatedValue.class.hashCode();
        h = 31 * h + Boolean.hashCode(relative);
        h = 31 * h + (origin == null ? 0 : origin.hashCode());
        h = 31 * h + (value == null ? 0 : value.hashCode());
        return h;
    }

    private final Object value;
    private final StyleOrigin origin;
    private final boolean relative;
}
