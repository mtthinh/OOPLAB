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
package javafx.scene;

/**
 * Specifies the direction of focus traversal.
 *
 * @since 24
 * @see Node#requestFocusTraversal(TraversalDirection)
 */
public enum TraversalDirection {
    /** Indicates a focus change to the node below the currently focused node. */
    DOWN,
    /** Indicates a focus change to the node to the left of the currently focused node. */
    LEFT,
    /** Indicates a focus change to the next focusable node. */
    NEXT,
    /** Indicates a focus change to the previous focusable node. */
    PREVIOUS,
    /** Indicates a focus change to the node to the right of the currently focused node. */
    RIGHT,
    /** Indicates a focus change to the node above the currently focused node. */
    UP;
}
