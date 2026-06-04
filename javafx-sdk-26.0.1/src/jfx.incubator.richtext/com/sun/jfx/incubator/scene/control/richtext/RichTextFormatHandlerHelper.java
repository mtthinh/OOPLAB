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

package com.sun.jfx.incubator.scene.control.richtext;

import java.io.Writer;
import com.sun.javafx.util.Utils;
import jfx.incubator.scene.control.richtext.StyleResolver;
import jfx.incubator.scene.control.richtext.model.RichTextFormatHandler;
import jfx.incubator.scene.control.richtext.model.StyledOutput;

public class RichTextFormatHandlerHelper {
    public interface Accessor {
        public StyledOutput createStyledOutput(RichTextFormatHandler h, StyleResolver r, Writer wr);
    }

    static {
        Utils.forceInit(RichTextFormatHandler.class);
    }

    private static Accessor accessor;

    public static void setAccessor(Accessor a) {
        if (accessor != null) {
            throw new IllegalStateException();
        }
        accessor = a;
    }

    public static StyledOutput createStyledOutput(RichTextFormatHandler h, StyleResolver r, Writer wr) {
        return accessor.createStyledOutput(h, r, wr);
    }
}
