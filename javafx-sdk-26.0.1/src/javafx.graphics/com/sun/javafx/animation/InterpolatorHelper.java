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

package com.sun.javafx.animation;

import com.sun.javafx.util.Utils;
import javafx.animation.Interpolator;

public final class InterpolatorHelper {

    static {
        Utils.forceInit(Interpolator.class);
    }

    private static Accessor accessor;

    public static void setAccessor(Accessor accessor) {
        InterpolatorHelper.accessor = accessor;
    }

    public static double curve(Interpolator interpolator, double t) {
        return accessor.curve(interpolator, t);
    }

    public interface Accessor {
        double curve(Interpolator interpolator, double t);
    }
}
