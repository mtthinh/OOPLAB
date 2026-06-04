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

import com.sun.javafx.util.Utils;
import jfx.incubator.scene.control.richtext.Marker;
import jfx.incubator.scene.control.richtext.TextPos;

/**
 * Manages Marker Accessor.
 */
public class MarkerHelper {
    public interface Accessor {
        public Marker createMarker(TextPos p);
        public void setMarkerPos(Marker m, TextPos p);
    }

    static {
        Utils.forceInit(Marker.class);
    }

    private static MarkerHelper.Accessor accessor;

    public static void setAccessor(MarkerHelper.Accessor a) {
        if (accessor != null) {
            throw new IllegalStateException();
        }
        accessor = a;
    }

    public static void setMarkerPos(Marker m, TextPos p) {
        accessor.setMarkerPos(m, p);
    }

    public static Marker createMarker(TextPos p) {
        return accessor.createMarker(p);
    }
}
