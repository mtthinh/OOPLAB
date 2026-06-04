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

public class ConditionalBinding<T> extends LazyObjectBinding<T> {

    private final ObservableValue<T> source;
    private final ObservableValue<Boolean> nonNullCondition;

    private Subscription subscription;

    public ConditionalBinding(ObservableValue<T> source, ObservableValue<Boolean> condition) {
        this.source = Objects.requireNonNull(source, "source cannot be null");
        this.nonNullCondition = Objects.requireNonNull(condition, "condition cannot be null").orElse(false);

        // condition is always observed and never unsubscribed
        nonNullCondition.subscribe(this::conditionChanged);
    }

    private void conditionChanged(boolean active) {
        if (!active && !isValid()) {
            getValue();  // makes binding valid, which it should always be when inactive
        }
        else if (isValid() && source.getValue() != getValue()) {
            invalidate();
        }

        updateSubscription();
    }

    /**
     * This binding is valid whenever it is observed, or it is currently inactive.
     * When inactive, the binding has the value of its source at the time it became
     * inactive.
     */
    @Override
    protected boolean allowValidation() {
        return super.allowValidation() || !isActive();
    }

    @Override
    protected T computeValue() {
        updateSubscription();

        return source.getValue();
    }

    private void updateSubscription() {
        if (isObserved() && isActive()) {
            if (subscription == null) {
                subscription = source.subscribe(this::invalidate);
            }
        }
        else {
            unsubscribe();
        }
    }

    @Override
    protected Subscription observeSources() {
        return this::unsubscribe;
    }

    private boolean isActive() {
        return nonNullCondition.getValue();
    }

    private void unsubscribe() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }
}
