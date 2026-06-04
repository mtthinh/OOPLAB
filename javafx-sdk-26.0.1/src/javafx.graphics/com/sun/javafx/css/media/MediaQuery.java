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

package com.sun.javafx.css.media;

import com.sun.javafx.css.media.expression.ConjunctionExpression;
import com.sun.javafx.css.media.expression.ConstantExpression;
import com.sun.javafx.css.media.expression.FunctionExpression;
import com.sun.javafx.css.media.expression.NegationExpression;
import com.sun.javafx.css.media.expression.DisjunctionExpression;
import com.sun.javafx.css.media.expression.RangeExpression;

/**
 * {@code MediaQuery} is the runtime representation of a CSS media query expression.
 * <p>
 * It is evaluated against a context that provides the values that are referenced in the expression,
 * and evaluates to either {@code true} or {@code false}.
 */
public sealed interface MediaQuery
        permits ConstantExpression,
                ConjunctionExpression,
                DisjunctionExpression,
                FunctionExpression,
                NegationExpression,
                RangeExpression {

    /**
     * Gets the context awareness flags of this media query, indicating which aspects of the
     * media query context are probed by the query.
     *
     * @return the context awareness flags
     */
    int getContextAwareness();

    /**
     * Evaluates this media query against the provided context.
     *
     * @param context the evaluation context
     * @return {@code true} if the media query matches, {@code false} otherwise
     */
    boolean evaluate(MediaQueryContext context);
}
