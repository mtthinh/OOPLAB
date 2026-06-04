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
import com.sun.jfx.incubator.scene.control.richtext.util.ListenerHelper;
import jfx.incubator.scene.control.richtext.RichTextArea;
import jfx.incubator.scene.control.richtext.TextPos;
import jfx.incubator.scene.control.richtext.skin.RichTextAreaSkin;

/**
 * Manages RichTextArea Accessor.
 */
public class RichTextAreaHelper {

    public interface Accessor {
        public boolean getText(RichTextArea t, TextPos start, TextPos end, StringBuilder sb, int limit);
    }

    static {
        Utils.forceInit(RichTextArea.class);
    }

    private static Accessor accessor;

    public static void setAccessor(Accessor a) {
        if (accessor != null) {
            // this code might break when RTA is created outside of the fx application thread
            // I am not sure what this check does really
            throw new IllegalStateException();
        }
        accessor = a;
    }

    public static boolean getText(RichTextArea t, TextPos start, TextPos end, StringBuilder sb, int limit) {
        return accessor.getText(t, start, end, sb, limit);
    }
}
