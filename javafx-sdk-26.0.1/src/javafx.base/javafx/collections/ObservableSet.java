/*
 * Copyright (c) 2010, 2020, Oracle and/or its affiliates. All rights reserved.
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

package javafx.collections;

import java.util.Set;

import javafx.beans.Observable;

/**
 * A set that allows observers to track changes when they occur. Implementations can be created using methods in {@link FXCollections}
 * such as {@link FXCollections#observableSet(Object...) observableSet}, or with a
 * {@link javafx.beans.property.SimpleSetProperty SimpleSetProperty}.
 *
 * @see SetChangeListener
 * @see SetChangeListener.Change
 * @param <E> the set element type
 * @since JavaFX 2.1
 */
public interface ObservableSet<E> extends Set<E>, Observable {
    /**
     * Add a listener to this observable set.
     * @param listener the listener for listening to the set changes
     */
    public void addListener(SetChangeListener<? super E> listener);
    /**
     * Tries to removed a listener from this observable set. If the listener is not
     * attached to this list, nothing happens.
     * @param listener a listener to remove
     */
    public void removeListener(SetChangeListener<? super E> listener);
}
