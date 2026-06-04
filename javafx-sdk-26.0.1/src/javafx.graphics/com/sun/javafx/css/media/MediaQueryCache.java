/*
 * Copyright (c) 2026, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.css.media;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * A cache for {@link MediaQuery} instances that is used to deduplicate media queries. More specifically,
 * this cache ensures that only a single instance of any distinct media query exists at any point in time.
 * This cache holds weak references, ensuring that media queries that are no longer in use will be eligible
 * for garbage collection.
 */
public final class MediaQueryCache {

    private MediaQueryCache() {}

    private static final Map<MediaQuery, WeakReference<MediaQuery>> CACHE = new WeakHashMap<>();

    @SuppressWarnings("unchecked")
    public static synchronized <T extends MediaQuery> T getCachedMediaQuery(T query) {
        if (CACHE.get(query) instanceof WeakReference<MediaQuery> wref
                && wref.get() instanceof MediaQuery cachedQuery) {
            return (T)cachedQuery;
        }

        CACHE.put(query, new WeakReference<>(query));
        return query;
    }
}
