/*
 * Copyright (c) 2012, 2025, Oracle and/or its affiliates. All rights reserved.
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
import com.sun.javafx.scene.text.TextLayout;
import com.sun.javafx.scene.text.TextLayoutFactory;

public class PrismTextLayoutFactory implements TextLayoutFactory {
    private static final PrismTextLayoutFactory FACTORY = new PrismTextLayoutFactory();
    /* Same strategy as GlyphLayout */
    private static final TextLayout REUSABLE_INSTANCE = FACTORY.createLayout();
    private static final AtomicBoolean IN_USE = new AtomicBoolean(false);

    private PrismTextLayoutFactory() {
    }

    @Override
    public TextLayout createLayout() {
        return new PrismTextLayout(PrismFontFactory.cacheLayoutSize);
    }

    @Override
    public TextLayout getLayout() {
        if (IN_USE.compareAndSet(false, true)) {
            REUSABLE_INSTANCE.setAlignment(0);
            REUSABLE_INSTANCE.setWrapWidth(0);
            REUSABLE_INSTANCE.setDirection(0);
            REUSABLE_INSTANCE.setContent(null);
            return REUSABLE_INSTANCE;
        } else {
            return createLayout();
        }
    }

    @Override
    public void disposeLayout(TextLayout layout) {
        if (layout == REUSABLE_INSTANCE) {
            IN_USE.set(false);
        }
    }

    public static PrismTextLayoutFactory getFactory() {
        return FACTORY;
    }
}
