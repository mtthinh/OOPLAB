/*
 * Copyright (c) 2013, 2022, Oracle and/or its affiliates. All rights reserved.
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

package javafx.scene.control;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * Event related to {@link TableView} and {@link TreeTableView} sorting.
 * @param <C> the type of control
 * @since JavaFX 8.0
 */
public class SortEvent<C> extends Event {

    /**
     * Common supertype for all sort event types.
     */
    public static final EventType<SortEvent> ANY =
            new EventType<> (Event.ANY, "SORT");

    /**
     * Gets the default singleton {@code SortEvent}.
     * @param <C> the type of control
     * @return the default singleton {@code SortEvent}
     */
    @SuppressWarnings("unchecked")
    public static <C> EventType<SortEvent<C>> sortEvent() {
        return (EventType<SortEvent<C>>) SORT_EVENT;
    }

    private static final EventType<?> SORT_EVENT = new EventType<>(SortEvent.ANY, "SORT_EVENT");

    /**
     * Constructs a new {@code SortEvent} with the specified event source and target.
     * If the source or target is set to {@code null}, it is replaced by
     * the {@code NULL_SOURCE_TARGET} value.
     *
     * @param source the event source which sent the event
     * @param target the target of the event
     */
    public SortEvent(@NamedArg("source") C source, @NamedArg("target") EventTarget target) {
        super(source, target, sortEvent());
    }

    @SuppressWarnings("unchecked")
    @Override public C getSource() {
        return (C) super.getSource();
    }
}
