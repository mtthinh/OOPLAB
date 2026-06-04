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
import java.util.function.Function;

import javafx.beans.value.ObservableValue;
import javafx.util.Subscription;

public class MappedBinding<S, T> extends LazyObjectBinding<T> {

    private final ObservableValue<S> source;
    private final Function<? super S, ? extends T> mapper;

    public MappedBinding(ObservableValue<S> source, Function<? super S, ? extends T> mapper) {
        this.source = Objects.requireNonNull(source, "source cannot be null");
        this.mapper = Objects.requireNonNull(mapper, "mapper cannot be null");
    }

    @Override
    protected T computeValue() {
        S value = source.getValue();

        return value == null ? null : mapper.apply(value);
    }

    @Override
    protected Subscription observeSources() {
        return source.subscribe(this::invalidate); // start observing source
    }
}
