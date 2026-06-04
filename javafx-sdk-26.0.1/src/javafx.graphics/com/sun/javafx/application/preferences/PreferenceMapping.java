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

package com.sun.javafx.application.preferences;

import com.sun.javafx.util.Logging;
import java.util.Objects;
import java.util.function.Function;

/**
 * A mapping from platform-specific keys to platform-independent keys defined by JavaFX, including a
 * function that maps the platform-specific value to the platform-independent value.
 */
public record PreferenceMapping<T, U>(String keyName, Class<T> valueType, Function<T, U> valueMapper) {

    public PreferenceMapping {
        Objects.requireNonNull(keyName, "keyName cannot be null");
        Objects.requireNonNull(valueType, "valueType cannot be null");
        Objects.requireNonNull(valueMapper, "valueMapper cannot be null");
    }

    @SuppressWarnings("unchecked")
    public PreferenceMapping(String keyName, Class<T> valueType) {
        this(keyName, valueType, value -> (U)value);
    }

    @SuppressWarnings("unchecked")
    public U map(Object value) {
        if (valueType.isInstance(value)) {
            return valueMapper.apply((T)value);
        }

        if (value != null) {
            Logging.getJavaFXLogger().warning(
                "Unexpected value of " + keyName + " platform preference, " +
                "using default value instead (expected = " + valueType.getName() +
                ", actual = " + value.getClass().getName() + ")");
        }

        return null;
    }
}
