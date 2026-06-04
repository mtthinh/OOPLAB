/*
 * Copyright (c) 2012, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.prism.es2;

import com.sun.prism.GraphicsResource;

abstract class GLDrawable implements GraphicsResource {

    final private long nativeWindow;
    final private GLPixelFormat pixelFormat;
    long nativeDrawableInfo;

    GLDrawable(long nativeWindow, GLPixelFormat pixelFormat) {
        this.nativeWindow = nativeWindow;
        this.pixelFormat = pixelFormat;
    }

    GLPixelFormat getPixelFormat() {
        return pixelFormat;
    }

    long getNativeWindow() {
        return nativeWindow;
    }

    void setNativeDrawableInfo(long nativeDrawableInfo) {
        this.nativeDrawableInfo = nativeDrawableInfo;
    }

    long getNativeDrawableInfo() {
        return nativeDrawableInfo;
    }
    abstract boolean swapBuffers(GLContext glCtx);
}
