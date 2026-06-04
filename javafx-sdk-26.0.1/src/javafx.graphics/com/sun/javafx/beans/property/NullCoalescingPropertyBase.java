/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.beans.property;

import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import java.util.Objects;

/**
 * Base class for null-coalescing properties that evaluate to their local value if non-{@code null},
 * or to their base value if the local value is {@code null}.
 *
 * @param <T> the value type
 */
public abstract class NullCoalescingPropertyBase<T> extends ObjectPropertyBase<T> {

    private final ChangeListener<T> listener = (_, _, _) -> {
        invalidated();
        fireValueChangedEvent();
    };

    private final WeakChangeListener<T> weakListener = new WeakChangeListener<>(listener);
    private final ObservableValue<T> baseObservable;
    private boolean currentValueChanged;
    private T currentValue;

    /**
     * Initializes a new {@code NullCoalescingPropertyBase} with the specified base value.
     *
     * @param baseObservable the base observable
     * @throws NullPointerException if {@code baseObservable} is {@code null}
     */
    protected NullCoalescingPropertyBase(ObservableValue<T> baseObservable) {
        this.baseObservable = Objects.requireNonNull(baseObservable, "baseObservable");
        this.currentValue = baseObservable.getValue();
    }

    /**
     * Connects this property to the base observable and starts observing.
     */
    public final void connect() {
        baseObservable.addListener(weakListener);
        invalidated();
        fireValueChangedEvent();
    }

    /**
     * Disconnects this property from the base observable and stops observing.
     */
    public final void disconnect() {
        baseObservable.removeListener(weakListener);
    }

    @Override
    public final T get() {
        return currentValue;
    }

    @Override
    protected final void fireValueChangedEvent() {
        if (currentValueChanged) {
            currentValueChanged = false;
            super.fireValueChangedEvent();
        }
    }

    @Override
    protected final void invalidated() {
        T localValue = super.get();
        T newValue = localValue != null ? localValue : baseObservable.getValue();

        if (currentValue != newValue) {
            currentValue = newValue;
            currentValueChanged = true;
            onInvalidated();
        }
    }

    /**
     * Called when the current value has changed.
     */
    protected void onInvalidated() {}
}
