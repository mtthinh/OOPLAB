/*
 * Copyright (c) 2007, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.marlin;

public interface DPathConsumer2D {
    /**
     * @see java.awt.geom.Path2D.Double#moveTo
     */
    public void moveTo(double x, double y);

    /**
     * @see java.awt.geom.Path2D.Double#lineTo
     */
    public void lineTo(double x, double y);

    /**
     * @see java.awt.geom.Path2D.Double#quadTo
     */
    public void quadTo(double x1, double y1,
                       double x2, double y2);

    /**
     * @see java.awt.geom.Path2D.Double#curveTo
     */
    public void curveTo(double x1, double y1,
                        double x2, double y2,
                        double x3, double y3);

    /**
     * @see java.awt.geom.Path2D.Double#closePath
     */
    public void closePath();

    /**
     * Called after the last segment of the last subpath when the
     * iteration of the path segments is completely done.  This
     * method serves to trigger the end of path processing in the
     * consumer that would normally be triggered when a
     * {@link java.awt.geom.PathIterator PathIterator}
     * returns {@code true} from its {@code done} method.
     */
    public void pathDone();
}
