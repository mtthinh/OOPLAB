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

import java.util.Set;

/**
 * Codifies priority of event handler invocation.
 */
public enum EventHandlerPriority {
    USER_HIGH(6000),
    USER_KB(5000),
    SKIN_KB(4000),
    SKIN_HIGH(3000),
    SKIN_LOW(2000), // not used, reserved for SkinInputMap.addHandlerLast
    USER_LOW(1000); // not used, reserved for InputMap.addHandlerLast

    /** set of priorities associated with a {@code Skin} */
    public static final Set<EventHandlerPriority> ALL_SKIN = Set.of(
        SKIN_KB,
        SKIN_HIGH,
        SKIN_LOW
    );

    final int priority;

    private EventHandlerPriority(int priority) {
        this.priority = priority;
    }
}
