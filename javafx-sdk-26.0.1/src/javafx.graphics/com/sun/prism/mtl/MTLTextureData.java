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

import com.sun.prism.impl.Disposer;
import java.util.Objects;

class MTLTextureData implements Disposer.Record {
    protected final MTLContext mtlContext;
    protected long pTexture;
    private long size;

    MTLTextureData(MTLContext context, long texPtr, long textureSize) {
        Objects.requireNonNull(context, "MTLContext must not be null");
        if (texPtr == 0L) {
            throw new IllegalArgumentException("Texture cannot be null");
        }
        mtlContext = context;
        pTexture = texPtr;
        size = textureSize;
    }

    public long getResource() {
        return pTexture;
    }

    public long getSize() {
        return size;
    }

    @Override
    public void dispose() {
        if (pTexture != 0L && !mtlContext.isDisposed()) {
            MTLResourceFactory.releaseTexture(pTexture);
            pTexture = 0L;
        }
    }
}
