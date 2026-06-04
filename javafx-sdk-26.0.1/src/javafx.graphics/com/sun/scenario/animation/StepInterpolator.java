/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.scenario.animation;

import javafx.animation.Interpolator;
import java.util.Objects;

/**
 * Implementation of a step interpolator as described by
 * <a href="https://www.w3.org/TR/css-easing-1/#step-easing-algo">CSS Easing Functions Level 1</a>
 */
public final class StepInterpolator extends Interpolator {

    private final int intervalCount;
    private final StepPosition position;

    public StepInterpolator(int intervalCount, StepPosition position) {
        if (position == StepPosition.NONE && intervalCount <= 1) {
            throw new IllegalArgumentException("intervalCount must be greater than 1");
        }

        if (intervalCount <= 0) {
            throw new IllegalArgumentException("intervalCount must be greater than 0");
        }

        this.position = Objects.requireNonNull(position, "position cannot be null");
        this.intervalCount = intervalCount;
    }

    @Override
    protected double curve(double t) {
        // JavaFX interpolators are not usually valid outside the interval [0..1], but
        // this implementation ensures that the output value is correct even for points
        // on the curve that are outside of this interval.
        boolean before = t < 0;

        if (before) {
            t = 0;
        } else if (t > 1) {
            t = 1;
        }

        int step = (int)(t * intervalCount);

        if (position == StepPosition.START || position == StepPosition.BOTH) {
            ++step;
        }

        if (before && (t * intervalCount % 1 == 0)) {
            --step;
        }

        if (t >= 0 && step < 0) {
            step = 0;
        }

        int jumps = switch (position) {
            case START, END -> intervalCount;
            case NONE -> intervalCount - 1;
            case BOTH -> intervalCount + 1;
        };

        if (t <= 1 && step > jumps) {
            step = jumps;
        }

        return (double)step / jumps;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(intervalCount) + 31 * position.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StepInterpolator other
            && intervalCount == other.intervalCount
            && position == other.position;
    }

    @Override
    public String toString() {
        return "StepInterpolator [intervalCount=" + intervalCount + ", position=" + position + "]";
    }

}
