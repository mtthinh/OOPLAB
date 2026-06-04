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

import com.sun.prism.RenderTarget;
import com.sun.prism.impl.ps.BaseShaderGraphics;
import com.sun.prism.paint.Color;

class MTLGraphics extends BaseShaderGraphics {

    private final MTLContext context;

    private MTLGraphics(MTLContext context, RenderTarget target) {
        super(context, target);
        this.context = context;
    }

    static MTLGraphics create(MTLContext context, RenderTarget target) {
        return target == null ? null : new MTLGraphics(context, target);
    }

    @Override
    public void clear(Color color) {
        float r = color.getRedPremult();
        float g = color.getGreenPremult();
        float b = color.getBluePremult();
        float a = color.getAlpha();

        context.validateClearOp(this);
        getRenderTarget().setOpaque(color.isOpaque());
        nClear(context.getContextHandle(), r, g, b, a, isDepthBuffer());
    }

    @Override
    public void sync() {
        context.flushVertexBuffer();
        context.commitCurrentCommandBuffer();
    }

    // Native methods
    private static native void nClear(long pContext, float red, float green, float blue, float alpha, boolean clearDepth);
}
