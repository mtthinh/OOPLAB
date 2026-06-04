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

import com.sun.javafx.css.media.MediaQuery;
import com.sun.javafx.css.media.MediaQueryCache;
import com.sun.javafx.css.media.MediaQueryContext;
import java.util.Objects;

/**
 * Logical negation of the specified expression.
 */
public final class NegationExpression implements MediaQuery {

    private final MediaQuery expression;
    private final int contextAwareness;

    private NegationExpression(MediaQuery expression) {
        this.expression = Objects.requireNonNull(expression, "expression cannot be null");
        this.contextAwareness = expression.getContextAwareness();
    }

    public static NegationExpression of(MediaQuery expression) {
        return MediaQueryCache.getCachedMediaQuery(new NegationExpression(expression));
    }

    public MediaQuery getExpression() {
        return expression;
    }

    @Override
    public int getContextAwareness() {
        return contextAwareness;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NegationExpression other && expression.equals(other.expression) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(NegationExpression.class, expression);
    }

    @Override
    public boolean evaluate(MediaQueryContext context) {
        return !expression.evaluate(context);
    }

    @Override
    public String toString() {
        return "not " + expression;
    }
}
