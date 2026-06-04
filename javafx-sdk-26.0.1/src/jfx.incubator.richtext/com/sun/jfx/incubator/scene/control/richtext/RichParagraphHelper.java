/*
 * Copyright (c) 2023, 2025, Oracle and/or its affiliates. All rights reserved.
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

import java.util.List;
import java.util.function.Consumer;
import com.sun.javafx.util.Utils;
import jfx.incubator.scene.control.richtext.model.RichParagraph;

/**
 * Provides access to internal methods in RichParagraph.
 */
public class RichParagraphHelper {
    public interface Accessor {
        public List<Consumer<TextCell>> getHighlights(RichParagraph p);
    }

    static {
        Utils.forceInit(RichParagraph.class);
    }

    private static Accessor accessor;

    public static void setAccessor(Accessor a) {
        if (accessor != null) {
            throw new IllegalStateException();
        }
        accessor = a;
    }

    public static List<Consumer<TextCell>> getHighlights(RichParagraph p) {
        return accessor.getHighlights(p);
    }
}
