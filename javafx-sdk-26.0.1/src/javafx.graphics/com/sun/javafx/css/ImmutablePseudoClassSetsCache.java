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

package com.sun.javafx.css;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javafx.css.PseudoClass;

/**
 * A cache for immutable sets of {@link PseudoClass}es.
 */
public class ImmutablePseudoClassSetsCache {
    private static final Map<Set<PseudoClass>, Set<PseudoClass>> CACHE = new HashMap<>();

    /**
     * Returns an immutable set of {@link PseudoClass}es.
     * <p>
     * Note: this method may or may not return the same instance for the same set of
     * {@link PseudoClass}es.
     *
     * @param pseudoClasses a set of {@link PseudoClass} to make immutable, cannot be {@code null}
     * @return an immutable set of {@link PseudoClass}es, never {@code null}
     * @throws NullPointerException when {@code pseudoClasses} is {@code null} or contains {@code null}s
     */
    public static Set<PseudoClass> of(Set<PseudoClass> pseudoClasses) {
        Set<PseudoClass> cachedSet = CACHE.get(Objects.requireNonNull(pseudoClasses, "pseudoClasses cannot be null"));

        if (cachedSet != null) {
            return cachedSet;
        }

        Set<PseudoClass> copy = Set.copyOf(pseudoClasses);

        CACHE.put(copy, copy);

        return copy;
    }
}
