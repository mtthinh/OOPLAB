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

import jfx.incubator.scene.control.richtext.RichTextArea;
import jfx.incubator.scene.control.richtext.TextPos;

/**
 * A utility class to help deal with anchor and caret positions.
 */
public class SelInfo {
    private final RichTextArea control;
    private final TextPos caret;
    private final TextPos anchor;
    private final boolean caretAtMin;

    public SelInfo(RichTextArea control, TextPos caret, TextPos anchor, boolean caretAtMin) {
        this.control = control;
        this.caret = caret;
        this.anchor = anchor;
        this.caretAtMin = caretAtMin;
    }

    public static SelInfo get(RichTextArea control) {
        if (control == null) {
            return null;
        }
        TextPos ca = control.getCaretPosition();
        if (ca == null) {
            return null;
        }
        TextPos an = control.getAnchorPosition();
        if (an == null) {
            an = ca;
        }
        boolean atMin = (ca.compareTo(an) <= 0);
        return new SelInfo(control, ca, an, atMin);
    }

    public TextPos getMin() {
        return caretAtMin ? caret : anchor;
    }

    public TextPos getMax() {
        return caretAtMin ? anchor : caret;
    }

    public TextPos getCaret() {
        return caret;
    }

    public TextPos getAnchor() {
        return anchor;
    }

    public boolean hasSelection() {
        return caret.compareTo(anchor) != 0;
    }
}
