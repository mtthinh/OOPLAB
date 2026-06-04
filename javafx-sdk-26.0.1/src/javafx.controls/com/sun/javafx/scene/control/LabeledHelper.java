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
package com.sun.javafx.scene.control;

import javafx.scene.control.Labeled;
import com.sun.javafx.util.Utils;

/**
 * Labeled Helper.
 */
public class LabeledHelper {
    /** Accessor */
    public interface Accessor {
        /**
         * Sets the text truncated flag.
         * @param c the Labeled control
         * @param on the value of the text truncated flag
         */
        public void setTextTruncated(Labeled c, boolean on);
    }

    private static Accessor accessor;

    static {
        Utils.forceInit(Labeled.class);
    }

    private LabeledHelper() {
    }

    public static void setAccessor(Accessor a) {
        accessor = a;
    }

    public static void setTextTruncated(Labeled c, boolean on) {
        accessor.setTextTruncated(c, on);
    }
}
