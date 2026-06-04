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

import com.sun.javafx.css.media.ContextAwareness;
import com.sun.javafx.css.media.MediaQuery;
import com.sun.javafx.css.media.SizeQueryType;
import javafx.css.Size;
import javafx.css.SizeUnits;
import java.util.Objects;

/**
 * Base class for expressions that are evaluated in a range context.
 */
public sealed abstract class RangeExpression implements MediaQuery
        permits EqualExpression,
                GreaterExpression,
                GreaterOrEqualExpression,
                LessExpression,
                LessOrEqualExpression {

    private final SizeQueryType featureType;
    private final Size sizeValue;
    private final double numberValue;

    RangeExpression(SizeQueryType featureType, Size sizeValue) {
        this.featureType = Objects.requireNonNull(featureType, "featureType cannot be null");
        this.sizeValue = Objects.requireNonNull(sizeValue, "sizeValue cannot be null");
        this.numberValue = sizeValue.pixels();
    }

    RangeExpression(SizeQueryType featureType, double numberValue) {
        this.featureType = Objects.requireNonNull(featureType, "featureType cannot be null");
        this.numberValue = numberValue;
        this.sizeValue = null;
    }

    public final SizeQueryType getFeatureType() {
        return featureType;
    }

    public final String getFeatureName() {
        return featureType.getFeatureName();
    }

    /**
     * Gets the feature value as specified in the stylesheet; for example, "100em" will return the value 100.
     */
    public final double getFeatureValue() {
        return sizeValue != null ? sizeValue.getValue() : numberValue;
    }

    /**
     * Gets the converted feature value; for example, "100em" will not return the value 100, but the
     * result of calling {@code SizeUnits.EM.pixels(100, 1, null)}.
     */
    public final double getValue() {
        return numberValue;
    }

    /**
     * Gets the unit as specified in the stylesheet, or {@code null} if no unit was specified.
     */
    public final SizeUnits getUnit() {
        return sizeValue != null ? sizeValue.getUnits() : null;
    }

    @Override
    public final int getContextAwareness() {
        return ContextAwareness.VIEWPORT_SIZE.value();
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        RangeExpression other = (RangeExpression)obj;
        return other.featureType == featureType
            && other.numberValue == numberValue
            && Objects.equals(other.sizeValue, sizeValue);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getClass(), featureType, sizeValue, numberValue);
    }

    final String getFormattedValue() {
        return sizeValue != null ? sizeValue.toString() : Double.toString(numberValue);
    }

    public interface Supplier {
        RangeExpression getSizeExpression(SizeQueryType featureType, Size sizeValue);
        RangeExpression getNumberExpression(SizeQueryType featureType, double numberValue);
    }
}
