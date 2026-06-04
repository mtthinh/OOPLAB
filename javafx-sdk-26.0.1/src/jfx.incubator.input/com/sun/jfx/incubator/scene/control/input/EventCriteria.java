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

import javafx.event.Event;
import javafx.event.EventType;

/**
 * This interface enables wider control in specifying conditional matching logic when adding skin/behavior handlers.
 *
 * @param <T> the type of the event
 */
public interface EventCriteria<T extends Event> {
    /**
     * Returns the event type for which this criteria are valid.
     * @return the event type
     */
    public EventType<T> getEventType();

    /**
     * Returns true if the specified event matches this criteria.
     * @param ev the event
     * @return true if match occurs
     */
    public boolean isEventAcceptable(T ev);
}
