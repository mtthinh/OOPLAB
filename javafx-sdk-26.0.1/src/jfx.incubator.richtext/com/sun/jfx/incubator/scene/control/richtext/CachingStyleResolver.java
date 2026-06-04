/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jfx.incubator.scene.control.richtext;

import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.image.WritableImage;
import jfx.incubator.scene.control.richtext.StyleResolver;
import jfx.incubator.scene.control.richtext.model.StyleAttributeMap;

/**
 * Caching StyleResolver caches CSSStyle to StyleAttribute conversion results
 * to avoid re-querying for the same information.
 */
public class CachingStyleResolver implements StyleResolver {
    private final StyleResolver resolver;
    private final HashMap<CssStyles, StyleAttributeMap> cache = new HashMap<>();

    public CachingStyleResolver(StyleResolver r) {
        this.resolver = r;
    }

    @Override
    public StyleAttributeMap resolveStyles(StyleAttributeMap attrs) {
        CssStyles css = attrs.get(CssStyles.CSS);
        if (css == null) {
            // no conversion is needed
            return attrs;
        }

        StyleAttributeMap a = cache.get(css);
        if (a == null) {
            a = resolver.resolveStyles(attrs);
            cache.put(css, a);
        }
        return a;
    }

    @Override
    public WritableImage snapshot(Node node) {
        return resolver.snapshot(node);
    }
}
