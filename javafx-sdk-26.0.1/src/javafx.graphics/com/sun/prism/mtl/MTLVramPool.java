/*
 * Copyright (c) 2021, 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.prism.mtl;

import com.sun.prism.PixelFormat;
import com.sun.prism.impl.BaseResourcePool;
import com.sun.prism.impl.PrismSettings;
import com.sun.prism.impl.TextureResourcePool;

class MTLVramPool extends BaseResourcePool<MTLTextureData>
               implements TextureResourcePool<MTLTextureData> {

    private static final MTLVramPool theInstance = new MTLVramPool();

    public static MTLVramPool getInstance() {
        return theInstance;
    }

    private MTLVramPool() {
        super(PrismSettings.targetVram, PrismSettings.maxVram);
    }

    @Override
    public long estimateTextureSize(int width, int height, PixelFormat format) {
        return (long) width * height * format.getBytesPerPixelUnit();
    }

    @Override
    public long estimateRTTextureSize(int width, int height, boolean hasDepth) {
        // REMIND: need to deal with size of depth buffer, etc.
        return ((long) width) * ((long) height) * 4L;
    }

    @Override
    public long size(MTLTextureData resource) {
        return resource.getSize();
    }

    @Override
    public String toString() {
        return "MTL Vram Pool";
    }
}
