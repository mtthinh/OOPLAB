/*
 * Copyright (c) 2024, 2025, Oracle and/or its affiliates. All rights reserved.
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

import com.sun.javafx.scene.SceneHelper;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.WeakChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.InputMethodRequests;

/**
 * Used to manage a collection of scenes which must coordinate enabling input
 * method events and retrieving InputMethodRequests. PopupWindows do not have
 * the OS focus and rely on events being posted first to a root
 * (non-popup) scene and then routed through the PopupWindow stack. If any
 * PopupWindow requires input method events they must be enabled on the root
 * scene and input method requests from that scene must be routed to the
 * PopupWindow.
 */
public class InputMethodStateManager {
    /**
     * The root non-popup scene which the OS sends input method
     * events to.
     */
    private final WeakReference<Scene> rootScene;

    /**
     * The scene for which we enabled input method events.
     */
    private Scene currentEventScene;

    /**
     * The scene stack including the root.
     */
    private final LinkedList<Scene> scenes = new LinkedList<Scene>();

    /**
     * We listen for changes to the input method requests configuration
     * on every scene in the stack.
     */
    private final ChangeListener<InputMethodRequests> inputMethodRequestsChangedListener =
        (obs, old, current) -> updateInputMethodEventEnableState();
    private final ChangeListener<EventHandler<? super InputMethodEvent>> onInputMethodTextChangedListener =
        (obs, old, current) -> updateInputMethodEventEnableState();

    private final WeakChangeListener<InputMethodRequests> weakInputMethodRequestsChangedListener =
        new WeakChangeListener(inputMethodRequestsChangedListener);
    private final WeakChangeListener<EventHandler<? super InputMethodEvent>> weakOnInputMethodTextChangedListener =
        new WeakChangeListener(onInputMethodTextChangedListener);

    /**
     * Constructs a new instance. Only root (non-popup) scenes should do this.
     *
     * @param scene the root {@link Scene} for which input methods should be enabled and disabled
     */
    public InputMethodStateManager(Scene scene) {
        this.rootScene = new WeakReference<>(scene);
        this.scenes.add(scene);
        this.currentEventScene = scene;
    }

    /**
     * Add a new Scene to the stack.
     */
    public void addScene(Scene scene) {
        scenes.addFirst(scene);
        updateInputMethodEventEnableState();
    }

    /**
     * Remove a Scene from the stack.
     */
    public void removeScene(Scene scene) {
        /**
         * If this scene is going away we should cleanup any composition
         * state. Hiding a window doesn't ensure proper cleanup.
         */
        SceneHelper.finishInputMethodComposition(rootScene.get());

        Node focusOwner = scene.getFocusOwner();
        if (focusOwner != null) {
            focusOwner.inputMethodRequestsProperty().removeListener(weakInputMethodRequestsChangedListener);
            focusOwner.onInputMethodTextChangedProperty().removeListener(weakOnInputMethodTextChangedListener);
        }

        scenes.remove(scene);
        updateInputMethodEventEnableState();
    }

    /**
     * Every Scene must call this before the focusOwner changes.
     */
    public void focusOwnerWillChangeForScene(Scene scene) {
        /**
         * Calling finishInputMethodComposition is only necessary if there's a
         * node that accepts IM events. But there's a system test that
         * expects finishInputMethodComposition to be called whenever the
         * focusOwner changes. To satisfy this test we call
         * finishInputMethodComposition even if no IM is enabled
         * (so currentEventScene will be null). This will be no-op as far as
         * the OS is concerned.
         */
        if (scene == currentEventScene || currentEventScene == null) {
            Scene root = rootScene.get();
            if (root != null) {
                SceneHelper.finishInputMethodComposition(root);
            }
        }
    }

    /**
     * Every Scene must call this when the focusOwner changes.
     */
    public void focusOwnerChanged(Node oldFocusOwner, Node newFocusOwner) {
        if (oldFocusOwner != null) {
            oldFocusOwner.inputMethodRequestsProperty().removeListener(weakInputMethodRequestsChangedListener);
            oldFocusOwner.onInputMethodTextChangedProperty().removeListener(weakOnInputMethodTextChangedListener);
        }
        if (newFocusOwner != null) {
            newFocusOwner.inputMethodRequestsProperty().addListener(weakInputMethodRequestsChangedListener);
            newFocusOwner.onInputMethodTextChangedProperty().addListener(weakOnInputMethodTextChangedListener);
        }
        updateInputMethodEventEnableState();
    }

    public Scene getRootScene() {
        return rootScene.get();
    }

    private void updateInputMethodEventEnableState() {
        currentEventScene = null;
        // Visit Scenes in order from top to bottom.
        for (Scene scene : scenes) {
            Node focusOwner = scene.getFocusOwner();
            if ((focusOwner != null) &&
                (focusOwner.getInputMethodRequests() != null) &&
                (focusOwner.getOnInputMethodTextChanged() != null)) {
                currentEventScene = scene;
                break;
            }
        }
        Scene root = rootScene.get();
        if (root != null) {
            SceneHelper.enableInputMethodEvents(root, currentEventScene != null);
        }
    }

    public InputMethodRequests getInputMethodRequests() {
        if (currentEventScene != null) {
            Node focusOwner = currentEventScene.getFocusOwner();
            if (focusOwner != null) {
                return focusOwner.getInputMethodRequests();
            }
        }
        return null;
    }
}
