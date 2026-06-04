/*
 * Copyright (c) 1997, 2024, Oracle and/or its affiliates. All rights reserved.
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
// adapted from package javax.swing.text.rtf;
package com.sun.jfx.incubator.scene.control.richtext.rtf;

import jfx.incubator.scene.control.richtext.model.StyleAttribute;

/**
 * This abstract class defines a 1-1 mapping between
 * an RTF keyword and a StyleAttribute attribute.
 */
abstract class  RTFAttribute {
    public static final int D_CHARACTER = 0;
    public static final int D_PARAGRAPH = 1;
    public static final int D_SECTION = 2;
    public static final int D_DOCUMENT = 3;
    public static final int D_META = 4;

    public abstract boolean set(AttrSet target);

    public abstract boolean set(AttrSet target, int parameter);

    public abstract boolean setDefault(AttrSet target);

    protected final int domain;
    protected final StyleAttribute attribute;
    protected final String rtfName;

    protected RTFAttribute(int domain, StyleAttribute attribute, String rtfName) {
        this.domain = domain;
        this.attribute = attribute;
        this.rtfName = rtfName;
    }

    public int domain() {
        return domain;
    }

    public StyleAttribute getStyleAttribute() {
        return attribute;
    }

    public String rtfName() {
        return rtfName;
    }
}
