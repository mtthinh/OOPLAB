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

import jfx.incubator.scene.control.richtext.model.StyleAttribute;

/**
 * Attribute represents CSS styles: a combination of a direct style (-fx-...)
 * and a number of style names.
 */
public final record CssStyles(String style, String[] names) {
    /** This special attribute contains CSS direct style and style names for text segments only */
    public static final StyleAttribute<CssStyles> CSS = new StyleAttribute<>("CSS", CssStyles.class, false);
}
