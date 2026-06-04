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

class MTLRTTextureData extends MTLTextureData {

    MTLRTTextureData(MTLContext context, long texPtr, long size) {
        super(context, texPtr, size);
    }

    @Override
    public void dispose() {
        if (pTexture != 0L && !mtlContext.isDisposed()) {
            if (mtlContext.isCurrentRTT(pTexture)) {
                mtlContext.flushVertexBuffer();
            }
        }
        super.dispose();
    }
}
