/*
 * Copyright (c) 2025, 2026, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.css.media.expression;

import com.sun.javafx.css.media.ContextAwareness;
import com.sun.javafx.css.media.MediaQuery;
import com.sun.javafx.css.media.MediaQueryCache;
import com.sun.javafx.css.media.MediaQueryContext;
import java.util.Objects;

/**
 * Evaluates to a constant boolean value.
 */
public final class ConstantExpression implements MediaQuery {

    private final boolean value;

    private ConstantExpression(boolean value) {
        this.value = value;
    }

    public static ConstantExpression of(boolean value) {
        return MediaQueryCache.getCachedMediaQuery(new ConstantExpression(value));
    }

    public boolean value() {
        return value;
    }

    @Override
    public int getContextAwareness() {
        return ContextAwareness.NONE.value();
    }

    @Override
    public boolean evaluate(MediaQueryContext context) {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ConstantExpression other && value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ConstantExpression.class, value);
    }

    @Override
    public String toString() {
        return "(" + value + ")";
    }
}
