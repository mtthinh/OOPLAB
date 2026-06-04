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

import javafx.scene.Node;
import jfx.incubator.scene.control.richtext.model.StyleAttributeMap;
import jfx.incubator.scene.control.richtext.skin.CellContext;

/**
 * Assist in creating virtualized text cells.
 */
class VFlowCellContext implements CellContext {
    private Node node;
    private StyleAttributeMap attrs;
    private final StringBuilder style = new StringBuilder();

    public VFlowCellContext() {
    }

    @Override
    public void addStyle(String fxStyle) {
        style.append(fxStyle);
    }

    @Override
    public StyleAttributeMap getAttributes() {
        return attrs;
    }

    @Override
    public Node getNode() {
        return node;
    }

    void reset(Node n, StyleAttributeMap a) {
        this.node = n;
        this.attrs = a;
        style.setLength(0);
    }

    void apply() {
        if (style.length() > 0) {
            String s = style.toString();
            node.setStyle(s);
        }
    }
}
