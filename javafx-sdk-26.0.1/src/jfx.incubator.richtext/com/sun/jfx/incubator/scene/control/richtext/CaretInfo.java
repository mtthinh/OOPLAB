/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jfx.incubator.scene.control.richtext;

import java.util.Objects;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.PathElement;

/**
 * Captures the caret position and bounds in the {@code VFlow.content} coordinates.
 */
public final class CaretInfo {
    private final double xmin;
    private final double xmax;
    private final double ymin;
    private final double ymax;
    private final double lineSpacing;
    private final PathElement[] path;

    private CaretInfo(double xmin, double xmax, double ymin, double ymax, double lineSpacing, PathElement[] path) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.lineSpacing = lineSpacing;
        this.path = path;
    }

    /**
     * Creates an instance of CaretInfo given the path and translation offsets required to
     * convert path coordinates (which come in the frame of reference of its {@code TextFlow}) to the view port
     * coordinates.
     *
     * @param lineSpacing the line spacing
     * @param path the caret path
     * @return the CaretInfo instance
     */
    public static CaretInfo create(double lineSpacing, PathElement[] path) {
        Objects.requireNonNull(path);
        if (path.length == 0) {
            throw new IllegalArgumentException("non-empty path is required");
        }

        double xmin = Double.POSITIVE_INFINITY;
        double xmax = Double.NEGATIVE_INFINITY;
        double ymin = Double.POSITIVE_INFINITY;
        double ymax = Double.NEGATIVE_INFINITY;

        int sz = path.length;
        for (int i = 0; i < sz; i++) {
            PathElement em = path[i];
            if (em instanceof LineTo lineto) {
                double x = lineto.getX();
                double y = lineto.getY();

                x = halfPixel(x);
                if (x < xmin) {
                    xmin = x;
                } else if (x > xmax) {
                    xmax = x;
                }

                y = halfPixel(y);
                if (y < ymin) {
                    ymin = y;
                } else if (y > ymax) {
                    ymax = y;
                }
            } else if (em instanceof MoveTo moveto) {
                double x = moveto.getX();
                double y = moveto.getY();

                x = halfPixel(x);
                if (x < xmin) {
                    xmin = x;
                } else if (x > xmax) {
                    xmax = x;
                }

                y = halfPixel(y);
                if (y < ymin) {
                    ymin = y;
                } else if (y > ymax) {
                    ymax = y;
                }
            } else {
                throw new IllegalArgumentException("Unexpected PathElement: " + em);
            }
        }

        return new CaretInfo(xmin, xmax, ymin, ymax, lineSpacing, path);
    }

    /**
     * Returns the smallest x coordinate of the caret shape bounding box.
     * @return minimum x coordinate
     */
    public final double getMinX() {
        return xmin;
    }

    /**
     * Returns the largest x coordinate of the caret shape bounding box.
     * @return maximum x coordinate
     */
    public final double getMaxX() {
        return xmax;
    }

    /**
     * Returns the smallest y coordinate of the caret shape bounding box.
     * @return minimum y coordinate
     */
    public final double getMinY() {
        return ymin;
    }

    /**
     * Returns the largest y coordinate of the caret shape bounding box.
     * @return maximum y coordinate
     */
    public final double getMaxY() {
        return ymax;
    }

    /**
     * Returns the line spacing at the caret position.
     * @return the line spacing
     */
    public final double getLineSpacing() {
        return lineSpacing;
    }

    /**
     * Returns the caret path.
     * @return the non-null array of path elements
     */
    public final PathElement[] path() {
        return path;
    }

    /**
     * Returns true if the specified y coordinate is between the smallest and largest y coordinate of the
     * caret bounding box.
     *
     * @param y the Y coordinate
     * @return true if the coordinate is within the caret bounding box
     */
    public final boolean containsY(double y) {
        return (y >= ymin) && (y < ymax);
    }

    private static double halfPixel(double coord) {
        return Math.round(coord + 0.5) - 0.5;
    }

    @Override
    public String toString() {
        return "CaretInfo{xmin=" + xmin + ", xmax=" + xmax + ", ymin=" + ymin + ", ymax=" + ymax + "}";
    }
}
