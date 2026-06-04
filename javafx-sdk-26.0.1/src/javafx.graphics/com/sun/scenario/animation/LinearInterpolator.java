/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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
import javafx.geometry.Point2D;
import java.util.Arrays;
import java.util.Objects;

/**
 * Implementation of a piecewise linear interpolator as described by
 * <a href="https://www.w3.org/TR/css-easing-2/#the-linear-easing-function">CSS Easing Functions Level 2</a>
 */
public final class LinearInterpolator extends Interpolator {

    // Control points stored as [x0, y0, x1, y1, ...]
    private final double[] controlPoints;

    public LinearInterpolator(Point2D[] controlPoints) {
        Objects.requireNonNull(controlPoints, "controlPoints cannot be null");

        if (controlPoints.length < 2) {
            throw new IllegalArgumentException("controlPoints must have at least two items");
        }

        int n = controlPoints.length;
        this.controlPoints = new double[n * 2];

        for (int i = 0; i < n; i++) {
            Point2D p = controlPoints[i];
            this.controlPoints[2 * i] = p.getX();
            this.controlPoints[2 * i + 1] = p.getY();
        }

        canonicalize(this.controlPoints, n);
    }

    private static void canonicalize(double[] controlPoints, int n) {
        // If the first control point has no input progress value, set it to 0.
        if (Double.isNaN(controlPoints[0])) {
            controlPoints[0] = 0.0;
        }

        // If the last control point has no input progress value, set it to 1.
        int lastIdx = 2 * (n - 1);
        if (Double.isNaN(controlPoints[lastIdx])) {
            controlPoints[lastIdx] = 1.0;
        }

        // Ensure that the input progress value of each control point is greater than or equal to the
        // input progress values of all preceding control points (monotonically non-decreasing).
        double largestX = controlPoints[0];
        for (int i = 1; i < n; ++i) {
            double curX = controlPoints[2 * i];
            if (curX < largestX) {
                controlPoints[2 * i] = largestX;
            } else if (!Double.isNaN(curX)) {
                largestX = curX;
            }
        }

        // For all control points without input progress value: determine an appropriate input progress value
        // by equally spacing the control points between neighboring control points.
        for (int i = 1; i < n; ++i) {
            if (!Double.isNaN(controlPoints[2 * i])) {
                continue;
            }

            int j = i;
            while (j < n && Double.isNaN(controlPoints[2 * j])) {
                ++j;
            }

            double x0 = controlPoints[2 * (i - 1)];
            double x1 = controlPoints[2 * j];
            double xStep = (x1 - x0) / (j - i + 1);
            double x = x0;
            for (int k = i; k < j; ++k) {
                x += xStep;
                controlPoints[2 * k] = x;
            }

            i = j;
        }
    }

    /*
     * Algorithm implemented based on the following specification:
     * https://www.w3.org/TR/css-easing-2/#linear-easing-function-output
     */
    @Override
    public double curve(double t) {
        int n = controlPoints.length / 2;
        int pointAIndex = 0;

        for (int i = 0; i < n; ++i) {
            if (controlPoints[2 * i] <= t) {
                pointAIndex = i;
            } else {
                break;
            }
        }

        if (pointAIndex == n - 1) {
            --pointAIndex;
        }

        int idx = pointAIndex * 2;
        double pointAInput = controlPoints[idx];
        double pointAOutput = controlPoints[idx + 1];
        double pointBInput = controlPoints[idx + 2];
        double pointBOutput = controlPoints[idx + 3];

        if (pointAInput == pointBInput) {
            return pointBOutput;
        }

        double progressFromPointA = t - pointAInput;
        double pointInputRange = pointBInput - pointAInput;
        double progressBetweenPoints = progressFromPointA / pointInputRange;
        double pointOutputRange = pointBOutput - pointAOutput;
        double outputFromLastPoint = progressBetweenPoints * pointOutputRange;
        return pointAOutput + outputFromLastPoint;
    }

    @Override
    public String toString() {
        return "LinearInterpolator " + Arrays.toString(controlPoints);
    }
}
