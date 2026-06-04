/*
 * Copyright (c) 2026, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.css.media;

public enum ContextAwareness {

    /**
     * Indicates no context awareness.
     */
    NONE(0),

    /**
     * Indicates that the media query probes the viewport size (width or height).
     */
    VIEWPORT_SIZE(1),

    /**
     * Indicates that the media query probes the full-screen state.
     */
    FULLSCREEN(2);

    ContextAwareness(int value) {
        this.value = value;
    }

    private final int value;

    public int value() {
        return value;
    }

    public boolean isSet(int flags) {
        return (flags & value) != 0;
    }

    public static int combine(ContextAwareness... contextAwareness) {
        int result = 0;

        for (ContextAwareness value : contextAwareness) {
            result |= value.value;
        }

        return result;
    }
}
