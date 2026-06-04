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
package com.sun.jfx.incubator.scene.control.input;

import javafx.event.EventType;
import javafx.scene.input.KeyEvent;
import jfx.incubator.scene.control.input.KeyBinding;

/**
 * Contains logic for mapping KeyBinding to a specific KeyEvent.
 */
public class KeyEventMapper {
    private static final int PRESSED = 0x01;
    private static final int RELEASED = 0x02;
    private static final int TYPED = 0x04;

    private int types;

    public EventType<KeyEvent> addType(KeyBinding k) {
        if (k.isKeyPressed()) {
            types |= PRESSED;
            return KeyEvent.KEY_PRESSED;
        } else if (k.isKeyReleased()) {
            types |= RELEASED;
            return KeyEvent.KEY_RELEASED;
        } else {
            types |= TYPED;
            return KeyEvent.KEY_TYPED;
        }
    }

    public boolean hasKeyPressed() {
        return (types & PRESSED) != 0;
    }

    public boolean hasKeyReleased() {
        return (types & RELEASED) != 0;
    }

    public boolean hasKeyTyped() {
        return (types & TYPED) != 0;
    }
}
