/*
 * Copyright (c) 2014, 2022, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Provide Android implementation of AcceleratedScreen
 *
 */
class AndroidAcceleratedScreen extends AcceleratedScreen {


    AndroidAcceleratedScreen(int[] attributes) throws GLException {
        super(attributes);
    }

    @Override
    boolean initPlatformLibraries() {
        return super.initPlatformLibraries();
    }

    @Override
    protected long platformGetNativeDisplay() {
        return 0;
    }

    @Override
    protected long platformGetNativeWindow() {
        long answer = NativePlatformFactory.getNativePlatform()
                .getScreen().getNativeHandle();
        return answer;
    }

}
