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

package com.sun.javafx.css;

import com.sun.javafx.util.Utils;
import com.sun.javafx.css.media.MediaRule;
import javafx.css.Rule;

public final class RuleHelper {

    private RuleHelper() {}

    static {
        Utils.forceInit(Rule.class);
    }

    private static Accessor accessor;

    public static void setAccessor(Accessor accessor) {
        RuleHelper.accessor = accessor;
    }

    public static MediaRule getMediaRule(Rule rule) {
        return accessor.getMediaRule(rule);
    }

    public interface Accessor {
        MediaRule getMediaRule(Rule rule);
    }
}
