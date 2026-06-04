/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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

import javafx.animation.Interpolator;
import javafx.util.Duration;
import java.util.Objects;

/**
 * {@code TransitionDefinition} describes how a {@link javafx.css.StyleableProperty} changes from one
 * value to another when its value is changed implicitly by the CSS subsystem. The transition can be
 * smooth, for example using linear or Bézier interpolation, or discrete using stepwise interpolation.
 *
 * @param propertyName the CSS property name, or "all" to target any property
 * @param duration duration of the transition
 * @param delay delay after which the transition is started; if negative, the transition starts
 *              immediately, but will appear to have begun at an earlier point in time
 * @param interpolator interpolator for the transition
 */
public record TransitionDefinition(String propertyName, Duration duration,
                                   Duration delay, Interpolator interpolator) {

    /**
     * Creates a new {@code TransitionDefinition} instance.
     *
     * @throws NullPointerException if any of the arguments is {@code null}
     * @throws IllegalArgumentException if the duration is negative
     */
    public TransitionDefinition {
        Objects.requireNonNull(propertyName, "propertyName cannot be null");
        Objects.requireNonNull(duration, "duration cannot be null");
        Objects.requireNonNull(delay, "delay cannot be null");
        Objects.requireNonNull(interpolator, "interpolator cannot be null");

        propertyName = TransitionDefinitionConverter.PROPERTY_ALL.equalsIgnoreCase(propertyName) ?
            TransitionDefinitionConverter.PROPERTY_ALL : propertyName;

        if (duration.lessThan(Duration.ZERO)) {
            throw new IllegalArgumentException("duration cannot be negative");
        }
    }

}
