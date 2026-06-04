/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Specifies the traversal method.
 */
public enum TraversalMethod {
    /**
     * Traversal was initiated programmatically or by clicking.
     */
    DEFAULT,

    /**
     * Traversal was initiated by pressing a key on the keyboard.
     */
    KEY
}
