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

import javafx.css.CssMetaData;
import javafx.css.Styleable;
import java.util.Map;

/**
 * Defines the {@code convert} and {@code convertBack} operations that enable object
 * decomposition and reconstruction. Note that the following invariant must always be
 * satisfied: {@code convert(convertBack(value)).equals(value)}
 *
 * @param <T> the target type
 */
public interface SubPropertyConverter<T> {

    /**
     * Converts a map of CSS values to the target type.
     *
     * @param values the constituent values
     * @throws NullPointerException if {@code values} is {@code null}
     * @return the converted object
     */
    T convert(Map<CssMetaData<? extends Styleable, ?>, Object> values);

    /**
     * Converts an object back to a map of its constituent values (deconstruction).
     * The returned map can be passed into {@link #convert(Map)} to reconstruct the object.
     *
     * @param value the object
     * @throws NullPointerException if {@code value} is {@code null}
     * @return a {@code Map} of the constituent values
     */
    Map<CssMetaData<? extends Styleable, ?>, Object> convertBack(T value);
}
