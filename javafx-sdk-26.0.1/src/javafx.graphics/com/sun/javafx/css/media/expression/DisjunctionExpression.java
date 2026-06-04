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
import java.util.List;
import java.util.Objects;

/**
 * Logical disjunction of the specified expressions.
 */
public final class DisjunctionExpression implements MediaQuery {

    private final MediaQuery left;
    private final MediaQuery right;
    private final int contextAwareness;

    private DisjunctionExpression(MediaQuery left, MediaQuery right) {
        this.left = Objects.requireNonNull(left, "left cannot be null");
        this.right = Objects.requireNonNull(right, "right cannot be null");
        this.contextAwareness = left.getContextAwareness() | right.getContextAwareness();
    }

    /**
     * Returns the disjunction of the specified expressions.
     */
    public static DisjunctionExpression of(MediaQuery left, MediaQuery right) {
        return MediaQueryCache.getCachedMediaQuery(new DisjunctionExpression(left, right));
    }

    /**
     * Returns the disjunction of all specified expressions.
     */
    public static DisjunctionExpression of(List<MediaQuery> expressions) {
        if (expressions.size() < 2) {
            throw new IllegalArgumentException();
        }

        var result = of(expressions.get(0), expressions.get(1));

        for (int i = 2; i < expressions.size(); i++) {
            result = of(result, expressions.get(i));
        }

        return result;
    }

    public MediaQuery getLeft() {
        return left;
    }

    public MediaQuery getRight() {
        return right;
    }

    @Override
    public int getContextAwareness() {
        return contextAwareness;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DisjunctionExpression other
            && left.equals(other.left)
            && right.equals(other.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DisjunctionExpression.class, left, right);
    }

    @Override
    public boolean evaluate(MediaQueryContext context) {
        return left.evaluate(context) || right.evaluate(context);
    }

    @Override
    public String toString() {
        return "(" + left + " or " + right + ")";
    }
}
