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

package com.sun.jfx.incubator.scene.control.richtext;

import com.sun.javafx.util.Utils;
import jfx.incubator.scene.control.richtext.StyleResolver;
import jfx.incubator.scene.control.richtext.TextPos;
import jfx.incubator.scene.control.richtext.model.StyledInput;
import jfx.incubator.scene.control.richtext.model.StyledTextModel;

/**
 * Provides access to internal methods in StyledTextModel.
 */
public class StyledTextModelHelper {
    public interface Accessor {
        public TextPos replace(StyledTextModel m, StyleResolver r, TextPos start, TextPos end, StyledInput in, boolean allowUndo, boolean isEdit);
    }

    static {
        Utils.forceInit(StyledTextModel.class);
    }

    private static Accessor accessor;

    public static void setAccessor(Accessor a) {
        if (accessor != null) {
            throw new IllegalStateException();
        }
        accessor = a;
    }

    public static TextPos replace(StyledTextModel m, StyleResolver r, TextPos start, TextPos end, StyledInput in, boolean allowUndo, boolean isEdit) {
        return accessor.replace(m, r, start, end, in, allowUndo, isEdit);
    }
}
