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

package com.sun.javafx.css.media;

import com.sun.javafx.css.media.expression.EqualExpression;
import com.sun.javafx.css.media.expression.GreaterExpression;
import com.sun.javafx.css.media.expression.GreaterOrEqualExpression;
import com.sun.javafx.css.media.expression.LessExpression;
import com.sun.javafx.css.media.expression.LessOrEqualExpression;
import com.sun.javafx.css.media.expression.RangeExpression;
import javafx.css.Size;

enum ComparisonOp {

    LESS(new RangeExpression.Supplier() {
        @Override
        public RangeExpression getSizeExpression(SizeQueryType featureType, Size sizeValue) {
            return LessExpression.ofSize(featureType, sizeValue);
        }

        @Override
        public RangeExpression getNumberExpression(SizeQueryType featureType, double numberValue) {
            return LessExpression.ofNumber(featureType, numberValue);
        }
    }),

    LESS_OR_EQUAL(new RangeExpression.Supplier() {
        @Override
        public RangeExpression getSizeExpression(SizeQueryType featureType, Size sizeValue) {
            return LessOrEqualExpression.ofSize(featureType, sizeValue);
        }

        @Override
        public RangeExpression getNumberExpression(SizeQueryType featureType, double numberValue) {
            return LessOrEqualExpression.ofNumber(featureType, numberValue);
        }
    }),

    GREATER(new RangeExpression.Supplier() {
        @Override
        public RangeExpression getSizeExpression(SizeQueryType featureType, Size sizeValue) {
            return GreaterExpression.ofSize(featureType, sizeValue);
        }

        @Override
        public RangeExpression getNumberExpression(SizeQueryType featureType, double numberValue) {
            return GreaterExpression.ofNumber(featureType, numberValue);
        }
    }),

    GREATER_OR_EQUAL(new RangeExpression.Supplier() {
        @Override
        public RangeExpression getSizeExpression(SizeQueryType featureType, Size sizeValue) {
            return GreaterOrEqualExpression.ofSize(featureType, sizeValue);
        }

        @Override
        public RangeExpression getNumberExpression(SizeQueryType featureType, double numberValue) {
            return GreaterOrEqualExpression.ofNumber(featureType, numberValue);
        }
    }),

    EQUAL(new RangeExpression.Supplier() {
        @Override
        public RangeExpression getSizeExpression(SizeQueryType featureType, Size sizeValue) {
            return EqualExpression.ofSize(featureType, sizeValue);
        }

        @Override
        public RangeExpression getNumberExpression(SizeQueryType featureType, double numberValue) {
            return EqualExpression.ofNumber(featureType, numberValue);
        }
    });

    ComparisonOp(RangeExpression.Supplier supplier) {
        this.supplier = supplier;
    }

    private final RangeExpression.Supplier supplier;

    public RangeExpression.Supplier getExpressionSupplier() {
        return supplier;
    }

    public ComparisonOp flipped() {
        return switch (this) {
            case LESS -> GREATER;
            case LESS_OR_EQUAL -> GREATER_OR_EQUAL;
            case GREATER -> LESS;
            case GREATER_OR_EQUAL -> LESS_OR_EQUAL;
            case EQUAL -> EQUAL;
        };
    }

    public boolean isSameDirection(ComparisonOp other) {
        return switch (this) {
            case LESS, LESS_OR_EQUAL -> other == LESS || other == LESS_OR_EQUAL;
            case GREATER, GREATER_OR_EQUAL -> other == GREATER || other == GREATER_OR_EQUAL;
            default -> false;
        };
    }
}
