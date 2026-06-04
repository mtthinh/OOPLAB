/*
 * Copyright (c) 2024, 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.css;

import javafx.css.StyleableProperty;

/**
 * {@code TransitionMediator} encapsulates the interactions between a {@link TransitionTimer} and its target.
 */
public abstract class TransitionMediator {

    private TransitionTimer.CancellationToken cancellationToken;

    /**
     * Starts the transition timer with the specified transition definition.
     *
     * @param definition the transition definition
     * @param targetPropertyName the name of the targeted CSS property
     * @param nanoNow the current time in nanoseconds
     */
    public final void run(TransitionDefinition definition, String targetPropertyName, long nanoNow) {
        // Might return 'null' if the transition duration is zero or the target node is not showing.
        cancellationToken = TransitionTimer.run(this, definition, targetPropertyName, nanoNow);

        // If no timer was started, we complete the transition immediately.
        if (cancellationToken == null) {
            onUpdate(1);
            onStop();
        }
    }

    /**
     * Cancels the transition timer if it is currently running.
     */
    public final void cancel() {
        if (cancellationToken != null) {
            cancellationToken.cancel();
        }
    }

    /**
     * Returns the styleable property targeted by the transition.
     *
     * @return the styleable property
     */
    public abstract StyleableProperty<?> getStyleableProperty();

    /**
     * Derived classes should implement this method to compute a new intermediate value
     * based on the current progress, and update the {@link StyleableProperty} accordingly.
     *
     * @param progress the progress of the transition along the output progress axis,
     *                 can be less than 0 or larger than 1
     */
    public abstract void onUpdate(double progress);

    /**
     * Occurs when the timer has stopped and the mediator should be discarded.
     * Derived classes should implement this method to clear any references to this mediator.
     */
    public abstract void onStop();

    /**
     * Derived classes must implement the following protocol:
     * <ol>
     *     <li>If the reversing-adjusted start value of the existing transition is equal
     *         to the end value of this transition:
     *         Set the reversing-adjusted start value of this transition to the end value
     *         of the existing transition and return {@code true}.
     *     <li>Otherwise, return {@code false}.
     * </ol>
     * Refer to <a href="https://www.w3.org/TR/css-transitions-1/#starting">Starting of transitions</a>
     * for more information about the reversing-adjusted start value.
     *
     * @param existingMediator the mediator of the existing transition
     * @return {@code true} if the reversing-adjusted start value was updated, {@code false} otherwise
     */
    public abstract boolean updateReversingAdjustedStartValue(TransitionMediator existingMediator);
}
