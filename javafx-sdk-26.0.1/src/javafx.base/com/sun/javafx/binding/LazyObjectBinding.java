/*
 * Copyright (c) 2022, 2025, Oracle and/or its affiliates. All rights reserved.
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

import javafx.beans.InvalidationListener;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ChangeListener;
import javafx.util.Subscription;

/**
 * Extends {@link ObjectBinding} with the ability to lazily register and eagerly unregister listeners on its
 * dependencies.
 *
 * @param <T> the type of the wrapped {@code Object}
 */
abstract class LazyObjectBinding<T> extends ObjectBinding<T> {

    private Subscription subscription;
    private boolean wasObserved;

    @Override
    public void addListener(ChangeListener<? super T> listener) {
        updateSubscriptionBeforeAdd();

        super.addListener(listener);
    }

    @Override
    public void removeListener(ChangeListener<? super T> listener) {
        super.removeListener(listener);

        updateSubscriptionAfterRemove();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        updateSubscriptionBeforeAdd();

        super.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        super.removeListener(listener);

        updateSubscriptionAfterRemove();
    }

    @Override
    protected boolean allowValidation() {
        return isObserved();
    }

    /**
     * Called before a listener was added to start observing inputs if they're not observed already.
     * This is done before the addition of a listener to be able to start observing its source
     * before the first listener queries the current value. By doing this, this first query will
     * also be immediately cached, as observed bindings are allowed to do so. This potentially avoids
     * many redundant compute value calls, especially if the source was also not yet observed (and
     * its source, and so on).
     */
    private void updateSubscriptionBeforeAdd() {
        if (!wasObserved) { // was first observer registered?
            subscription = observeSources(); // start observing source
            wasObserved = true;
        }
    }

    /**
     * Called after a listener was removed to stop observing inputs if this was the last listener
     * observing this binding.
     */
    private void updateSubscriptionAfterRemove() {
        if (wasObserved && !isObserved()) { // was last observer unregistered?
            subscription.unsubscribe();
            subscription = null;
            invalidate(); // make binding invalid as source is no longer tracked
            wasObserved = false;
        }
    }

    /**
     * Called when this binding was previously not observed and a new observer was added. Implementors must return a
     * {@link Subscription} which will be cancelled when this binding no longer has any observers.
     *
     * @return a {@link Subscription} which will be cancelled when this binding no longer has any observers, never null
     */
    protected abstract Subscription observeSources();
}
