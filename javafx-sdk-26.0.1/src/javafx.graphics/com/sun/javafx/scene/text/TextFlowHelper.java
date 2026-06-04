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

package com.sun.javafx.scene.text;

import javafx.scene.text.TextFlow;
import com.sun.javafx.util.Utils;

/**
 * Used to access internal methods of TextFlow.
 */
public class TextFlowHelper {
    public interface Accessor {
        public TextLayout getTextLayout(TextFlow f);
    }

    private static Accessor accessor;

    static {
        Utils.forceInit(TextFlow.class);
    }

    public static void setAccessor(Accessor a) {
        if (accessor != null) {
            throw new IllegalStateException();
        }

        accessor = a;
    }

    public static TextLayout getTextLayout(TextFlow f) {
        return accessor.getTextLayout(f);
    }
}
