/*
 * Copyright (c) 2020, 2022, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.sg.prism;

import com.sun.javafx.geom.Vec3d;

import javafx.geometry.Point3D;

/**
 * The peer of the {@code DirectionalLight} class. Holds the default values of {@code DirectionalLight}'s
 * properties and updates the visuals via {@link NGNode#visualsChanged} when one of the current
 * values changes. The peer receives its changes by {@link javafx.scene.DirectionalLight#doUpdatePeer} calls.
 */
public class NGDirectionalLight extends NGLightBase {

    /** Direction default value */
    private static final Point3D DEFAULT_DIRECTION = new Point3D(0, 0, 1);

    public NGDirectionalLight() {
    }

    public static Point3D getDefaultDirection() {
        return DEFAULT_DIRECTION;
    }

    private Point3D direction = DEFAULT_DIRECTION;
    private final Vec3d effectiveDir = new Vec3d();

    public Point3D getDirection() {
        var dir = new Vec3d(direction.getX(), direction.getY(), direction.getZ());
        getWorldTransform().deltaTransform(dir, effectiveDir);
        return new Point3D(effectiveDir.x, effectiveDir.y, effectiveDir.z);
    }

    public void setDirection(Point3D direction) {
        if (!this.direction.equals(direction)) {
            this.direction = direction;
            visualsChanged();
        }
    }
}
