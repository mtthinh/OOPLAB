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

package com.sun.glass.ui;

import static javafx.scene.AccessibleAttribute.PARENT;
import static javafx.scene.AccessibleAttribute.ROLE;
import com.sun.javafx.scene.NodeHelper;
import com.sun.javafx.scene.SceneHelper;
import com.sun.javafx.tk.quantum.QuantumToolkit;
import javafx.scene.AccessibleAction;
import javafx.scene.AccessibleAttribute;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.util.function.Supplier;

public abstract class Accessible {

    private EventHandler eventHandler;
    private View view;

    public static abstract class EventHandler {
        public Object getAttribute(AccessibleAttribute attribute, Object... parameters) {
            return null;
        }

        public void executeAction(AccessibleAction action, Object... parameters) {
        }
    }

    public EventHandler getEventHandler() {
        return this.eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void dispose() {
        eventHandler = null;
        view = null;
    }

    public boolean isDisposed() {
        return getNativeAccessible() == 0L;
    }

    @Override
    public String toString() {
         return getClass().getSimpleName() + " (" + eventHandler + ")";
    }

    protected boolean isIgnored() {
        AccessibleRole role = (AccessibleRole)getAttribute(ROLE);
        if (role == null) return true;
        return role == AccessibleRole.NODE || role == AccessibleRole.PARENT;
    }

    protected abstract long getNativeAccessible();

    protected Accessible getAccessible(Scene scene) {
        if (scene == null) return null;
        return SceneHelper.getAccessible(scene);
    }

    protected Accessible getAccessible(Node node) {
        if (node == null) return null;
        return NodeHelper.getAccessible(node);
    }

    protected long getNativeAccessible(Node node) {
        if (node == null) return 0L;
        Accessible acc = getAccessible(node);
        if (acc == null) return 0L;
        return acc.getNativeAccessible();
    }

    protected Accessible getContainerAccessible(AccessibleRole targetRole) {
        Node node = (Node)getAttribute(PARENT);
        while (node != null) {
            Accessible acc = getAccessible(node);
            AccessibleRole role = (AccessibleRole)acc.getAttribute(ROLE);
            if (role == targetRole) return acc;
            node = (Node)acc.getAttribute(PARENT);
        }
        return null;
    }

    private class GetAttribute implements Supplier<Object> {
        AccessibleAttribute attribute;
        Object[] parameters;
        @Override public Object get() {
            Object result = eventHandler.getAttribute(attribute, parameters);
            if (result != null) {
                Class<?> clazz = attribute.getReturnType();
                if (clazz != null) {
                    try {
                        clazz.cast(result);
                    } catch (Exception e) {
                        String msg = "The expected return type for the " + attribute +
                                     " attribute is " + clazz.getSimpleName() +
                                     " but found " + result.getClass().getSimpleName();
                        System.err.println(msg);
                        return null; //Fail no exception
                    }
                }
            }
            return result;
        }
    }

    private GetAttribute getAttribute = new GetAttribute();

    public Object getAttribute(AccessibleAttribute attribute, Object... parameters) {
        getAttribute.attribute = attribute;
        getAttribute.parameters = parameters;
        return QuantumToolkit.runWithoutRenderLock(getAttribute);
    }

    private class ExecuteAction implements Supplier<Void> {
        AccessibleAction action;
        Object[] parameters;
        @Override public Void get() {
            eventHandler.executeAction(action, parameters);
            return null;
        }
    }

    private ExecuteAction executeAction = new ExecuteAction();

    public void executeAction(AccessibleAction action, Object... parameters) {
        executeAction.action = action;
        executeAction.parameters = parameters;
        QuantumToolkit.runWithoutRenderLock(executeAction);
    }

    public abstract void sendNotification(AccessibleAttribute notification);

}
