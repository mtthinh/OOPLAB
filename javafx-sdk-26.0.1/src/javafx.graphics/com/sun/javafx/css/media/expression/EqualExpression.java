/*
 * Copyright (c) 2026, Oracle and/or its affiliates. All rights reserved.
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

import com.sun.javafx.css.media.MediaQueryCache;
import com.sun.javafx.css.media.SizeQueryType;
import com.sun.javafx.css.media.MediaQueryContext;
import javafx.css.Size;

/**
 * Evaluates whether a media feature is equal to a specified value.
 */
public final class EqualExpression extends RangeExpression {

    private EqualExpression(SizeQueryType featureType, Size sizeValue) {
        super(featureType, sizeValue);
    }

    private EqualExpression(SizeQueryType featureType, double numberValue) {
        super(featureType, numberValue);
    }

    public static EqualExpression ofSize(SizeQueryType featureType, Size sizeValue) {
        return MediaQueryCache.getCachedMediaQuery(new EqualExpression(featureType, sizeValue));
    }

    public static EqualExpression ofNumber(SizeQueryType featureType, double numberValue) {
        return MediaQueryCache.getCachedMediaQuery(new EqualExpression(featureType, numberValue));
    }

    @Override
    public boolean evaluate(MediaQueryContext context) {
        return getFeatureType().evaluate(context) == getValue();
    }

    @Override
    public String toString() {
        return "(" + getFeatureName() + " = " + getFormattedValue() + ")";
    }
}
