/*
 * Copyright (c) 2010, 2023, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.collections;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A helper and marker interface used for {@code ObservableList}s that implement sorting algorithms that report
 * the sort as one change.
 *
 * @param <E> the type of elements in this list
 * @see FXCollections#sort(ObservableList, Comparator)
 */
public interface SortableList<E> extends ObservableList<E> {

    @SuppressWarnings("unchecked")
    @Override
    public default void sort(Comparator<? super E> comparator) {
        if (size() == 0 || size() == 1) {
            return;
        }
        // The cast will succeed, but a ClassCastException will be thrown as specified when compare is called
        comparator = comparator != null ? comparator : (Comparator<? super E>) Comparator.naturalOrder();
        doSort(comparator);
    }

    /**
     * Sorts the list and reports it as one change event.
     *
     * @param comparator the comparator for the sorting; never {@code null}
     */
    void doSort(Comparator<? super E> comparator);
}
