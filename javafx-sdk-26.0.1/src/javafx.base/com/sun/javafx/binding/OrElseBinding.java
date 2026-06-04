/*
 * Copyright (c) 2022, 2023, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.binding;

import java.util.Objects;

import javafx.beans.value.ObservableValue;
import javafx.util.Subscription;

public class OrElseBinding<T> extends LazyObjectBinding<T> {

    private final ObservableValue<T> source;
    private final T constant;

    public OrElseBinding(ObservableValue<T> source, T constant) {
        this.source = Objects.requireNonNull(source, "source cannot be null");
        this.constant = constant;
    }

    @Override
    protected T computeValue() {
        T value = source.getValue();

        return value == null ? constant : value;
    }

    @Override
    protected Subscription observeSources() {
        return source.subscribe(this::invalidate); // start observing source
    }
}
