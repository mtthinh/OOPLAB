/*
 * Copyright (c) 2011, 2022, Oracle and/or its affiliates. All rights reserved.
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

import javafx.scene.Parent;
import javafx.scene.SubScene;

/**
 * Traversal engine for subscene
 */
public final class SubSceneTraversalEngine extends TopMostTraversalEngine{

    private final SubScene subScene;

    public SubSceneTraversalEngine(SubScene scene) {
        this.subScene = scene;
    }

    @Override
    protected Parent getRoot() {
        return subScene.getRoot();
    }
}
