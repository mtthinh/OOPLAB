/*
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.embed.swing;

import java.awt.Component;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.InvalidDnDOperationException;
import javafx.embed.swing.SwingNode;
import com.sun.javafx.embed.swing.newimpl.FXDnDInteropN;

/**
 * A utility class to connect DnD mechanism of Swing and FX.
 * It allows Swing content to use the FX machinery for performing DnD.
 */
final public class FXDnD {
    public static boolean fxAppThreadIsDispatchThread = "true".equals(System.getProperty("javafx.embed.singleThread"));
    private FXDnDInteropN fxdndiop;

    public FXDnD(SwingNode node) {
        fxdndiop = new FXDnDInteropN();
        fxdndiop.setNode(node);
    }

    public Object createDragSourceContext(DragGestureEvent dge)
            throws InvalidDnDOperationException {
        return fxdndiop.createDragSourceContext(dge);
    }

    public <T extends DragGestureRecognizer> T createDragGestureRecognizer(
            Class<T> abstractRecognizerClass,
            DragSource ds, Component c, int srcActions,
            DragGestureListener dgl)
    {
        return fxdndiop.createDragGestureRecognizer(ds, c, srcActions, dgl);
    }

    public void addDropTarget(DropTarget dt) {
        SwingNode node = fxdndiop.getNode();
        if (node != null) {
            fxdndiop.addDropTarget(dt, node);
        }
    }

    public void removeDropTarget(DropTarget dt) {
        SwingNode node = fxdndiop.getNode();
        if (node != null) {
            fxdndiop.removeDropTarget(dt, node);
        }
    }
}
