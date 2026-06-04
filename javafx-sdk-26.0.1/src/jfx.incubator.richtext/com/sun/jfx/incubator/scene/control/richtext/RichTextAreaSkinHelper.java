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

import javafx.scene.control.Skin;
import com.sun.javafx.util.Utils;
import com.sun.jfx.incubator.scene.control.richtext.util.ListenerHelper;
import jfx.incubator.scene.control.richtext.RichTextArea;
import jfx.incubator.scene.control.richtext.skin.RichTextAreaSkin;

/**
 * Manages RichTextAreaSkin Accessor.
 */
public class RichTextAreaSkinHelper {
    public interface Accessor {
        public VFlow getVFlow(Skin<?> skin);
        public ListenerHelper getListenerHelper(Skin<?> skin);
    }

    static {
        Utils.forceInit(RichTextAreaSkin.class);
    }

    private static Accessor accessor;

    public static void setAccessor(Accessor a) {
        if (accessor != null) {
            throw new IllegalStateException();
        }
        accessor = a;
    }

    public static VFlow getVFlow(RichTextArea t) {
        var skin = t.getSkin();
        return accessor.getVFlow(skin);
    }

    public static ListenerHelper getListenerHelper(RichTextAreaSkin skin) {
        return accessor.getListenerHelper(skin);
    }
}
