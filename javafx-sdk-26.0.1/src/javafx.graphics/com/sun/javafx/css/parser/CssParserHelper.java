/*
 * Copyright (c) 2026, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.css.parser;

import com.sun.javafx.util.Utils;
import javafx.css.CssParser;
import javafx.css.Size;

public final class CssParserHelper {

    private static Accessor accessor;

    static {
        Utils.forceInit(CssParser.class);
    }

    private CssParserHelper() {}

    public static void setAccessor(Accessor accessor) {
        CssParserHelper.accessor = accessor;
    }

    public static Size parseSize(Token token) {
        return accessor.parseSize(token);
    }

    public interface Accessor {
        Size parseSize(Token token);
    }
}
