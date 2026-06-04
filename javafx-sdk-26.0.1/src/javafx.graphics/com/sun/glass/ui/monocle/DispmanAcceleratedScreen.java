/*
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.glass.ui.monocle;

class DispmanAcceleratedScreen extends AcceleratedScreen {

    DispmanAcceleratedScreen(int[] attributes) throws GLException {
        super(attributes);
    }

    private native long _platformGetNativeWindow(int displayID, int layerID);

    @Override
    protected long platformGetNativeWindow() {
        int displayID = Integer.getInteger("dispman.display", 0 /* LCD */);
        int layerID = Integer.getInteger("dispman.layer", 1);
        return _platformGetNativeWindow(displayID, layerID);
    }
}
