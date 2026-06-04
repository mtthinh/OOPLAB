/*
 * Copyright (c) 2023, Oracle and/or its affiliates. All rights reserved.
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Contains information about a changed value.
 *
 * @param oldValue the old mappings
 * @param newValue the new mappings
 */
public record ChangedValue(Object oldValue, Object newValue) {

    /**
     * Returns a map that contains the new or changed mappings of {@code current} compared to {@code old}.
     * A value has changed if {@link Objects#equals(Object, Object)} or {@link Arrays#equals(Object[], Object[])}
     * returns {@code false} when invoked with the old and new value.
     *
     * @param old the old mappings
     * @param current the current mappings
     * @return a mapping of keys to changed values
     */
    public static Map<String, ChangedValue> getEffectiveChanges(Map<String, Object> old, Map<String, Object> current) {
        Map<String, ChangedValue> changed = null;

        for (Map.Entry<String, Object> entry : current.entrySet()) {
            Object newValue = entry.getValue();
            Object oldValue = old.get(entry.getKey());

            if (!Objects.deepEquals(oldValue, newValue)) {
                if (changed == null) {
                    changed = new HashMap<>();
                }

                changed.put(entry.getKey(), new ChangedValue(oldValue, newValue));
            }
        }

        return changed != null ? changed : Map.of();
    }
}
