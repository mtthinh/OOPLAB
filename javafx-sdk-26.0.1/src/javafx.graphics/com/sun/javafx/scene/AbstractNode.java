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

package com.sun.javafx.scene;

import javafx.scene.Node;

/**
 * This class only exists so that JavaFX code can extend the {@code Node} class across module
 * boundaries, as classes in other modules cannot be permitted subclasses.
 */
public abstract non-sealed class AbstractNode extends Node {}
