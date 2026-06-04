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
package com.sun.javafx.text;

import java.util.concurrent.atomic.AtomicBoolean;
import com.sun.javafx.font.PrismFontFactory;

/* This class creates a singleton GlyphLayout which is checked out
 * for use. Callers who find its checked out create one that after use
 * is discarded. This means that in a MT-rendering environment,
 * there's no need to synchronise except for that one instance.
 * Fewer threads will then need to synchronise, perhaps helping
 * throughput on a MP system. If for some reason the reusable
 * GlyphLayout is checked out for a long time (or never returned?) then
 * we would end up always creating new ones. That situation should not
 * occur and if if did, it would just lead to some extra garbage being
 * created.
 */
public class GlyphLayoutManager {
    private static final GlyphLayout REUSABLE_INSTANCE = newInstance();
    private static final AtomicBoolean IN_USE = new AtomicBoolean(false);

    private static GlyphLayout newInstance() {
        PrismFontFactory factory = PrismFontFactory.getFontFactory();
        return factory.createGlyphLayout();
    }

    public static GlyphLayout getInstance() {
        if (IN_USE.compareAndSet(false, true)) {
            return REUSABLE_INSTANCE;
        } else {
            return newInstance();
        }
    }

    public static void dispose(GlyphLayout la) {
        if (la == REUSABLE_INSTANCE) {
            IN_USE.set(false);
        }
    }
}
