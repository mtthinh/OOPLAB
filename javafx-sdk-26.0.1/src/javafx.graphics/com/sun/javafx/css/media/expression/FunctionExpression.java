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
import java.util.function.Function;

/**
 * Evaluates to {@code true} if the return value of the specified function is equal to {@code value}.
 */
public final class FunctionExpression<T> implements MediaQuery {

    private final String featureName;
    private final String featureValue;
    private final Function<MediaQueryContext, T> function;
    private final int contextAwareness;
    private final T value;

    private FunctionExpression(String featureName,
                               String featureValue,
                               Function<MediaQueryContext, T> function,
                               T value,
                               ContextAwareness... contextAwareness) {
        this.featureName = Objects.requireNonNull(featureName, "featureName cannot be null");
        this.featureValue = featureValue;
        this.function = Objects.requireNonNull(function, "function cannot be null");
        this.contextAwareness = ContextAwareness.combine(contextAwareness);
        this.value = value;
    }

    /**
     * Returns an interned {@code FunctionExpression}.
     *
     * @param featureName the feature name
     * @param featureValue the feature value, or {@code null} to indicate a boolean context
     * @param function the evaluation function
     * @param value the expected return value of the function
     * @param contextAwareness the context awareness of the function, see {@link MediaQuery#getContextAwareness()}
     */
    public static <T> FunctionExpression<T> of(String featureName,
                                               String featureValue,
                                               Function<MediaQueryContext, T> function,
                                               T value,
                                               ContextAwareness... contextAwareness) {
        return MediaQueryCache.getCachedMediaQuery(
            new FunctionExpression<>(featureName, featureValue, function, value, contextAwareness));
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    @Override
    public int getContextAwareness() {
        return contextAwareness;
    }

    @Override
    public boolean evaluate(MediaQueryContext context) {
        return Objects.equals(function.apply(context), value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FunctionExpression<?> expr
            && expr.featureName.equals(featureName)
            && Objects.equals(expr.featureValue, featureValue)
            && Objects.equals(expr.value, value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(featureName, featureValue, value);
    }

    @Override
    public String toString() {
        return "(" + (featureValue != null ? featureName + ": " + featureValue : featureName) + ")";
    }
}
