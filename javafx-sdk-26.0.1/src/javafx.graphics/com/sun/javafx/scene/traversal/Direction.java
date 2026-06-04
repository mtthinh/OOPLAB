/*
 * Copyright (c) 2010, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.scene.traversal;

import javafx.geometry.NodeOrientation;
import javafx.scene.TraversalDirection;

/**
 * Specifies the direction of traversal.
 */
public enum Direction {

    UP(false),
    DOWN(true),
    LEFT(false),
    RIGHT(true),
    NEXT(true),
    NEXT_IN_LINE(true), // Like NEXT, but does not traverse into the current parent
    PREVIOUS(false);
    private final boolean forward;

    Direction(boolean forward) {
        this.forward = forward;
    }

    public boolean isForward() {
        return forward;
    }

    /**
     * Returns the direction with respect to the node's orientation. It affect's only arrow keys however, so it's not
     * an error to ignore this call if handling only next/previous traversal.
     * @param orientation
     * @return
     */
    public Direction getDirectionForNodeOrientation(NodeOrientation orientation) {
        if (orientation == NodeOrientation.RIGHT_TO_LEFT) {
            switch (this) {
                case LEFT:
                    return RIGHT;
                case RIGHT:
                    return LEFT;
            }
        }
        return this;
    }

    public static Direction of(TraversalDirection d) {
        return switch (d) {
        case DOWN -> DOWN;
        case LEFT -> LEFT;
        case NEXT -> NEXT;
        case PREVIOUS -> PREVIOUS;
        case RIGHT -> RIGHT;
        case UP -> UP;
        };
    }
}
