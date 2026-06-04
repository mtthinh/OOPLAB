/*
 * Copyright (c) 2023, 2025, Oracle and/or its affiliates. All rights reserved.
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

import com.sun.prism.impl.BaseMesh;
import com.sun.prism.impl.Disposer;

class MTLMesh extends BaseMesh {

    static int count = 0;

    private final MTLContext context;
    private final long nativeHandle;

    private MTLMesh(MTLContext context, long nativeHandle, Disposer.Record disposerRecord) {
        super(disposerRecord);
        this.context = context;
        this.nativeHandle = nativeHandle;
        count++;
    }

    static MTLMesh create(MTLContext context) {
        long nativeHandle = context.createMTLMesh();
        return new MTLMesh(context, nativeHandle, new MTLMeshDisposerRecord(context, nativeHandle));
    }

    long getNativeHandle() {
        return nativeHandle;
    }

    @Override
    public boolean isValid() {
        return !context.isDisposed();
    }

    @Override
    public void dispose() {
        disposerRecord.dispose();
        count--;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean buildNativeGeometry(float[] vertexBuffer, int vertexBufferLength,
            int[] indexBufferInt, int indexBufferLength) {
        return context.buildNativeGeometry(nativeHandle, vertexBuffer,
                vertexBufferLength, indexBufferInt, indexBufferLength);
    }

    @Override
    public boolean buildNativeGeometry(float[] vertexBuffer, int vertexBufferLength,
            short[] indexBufferShort, int indexBufferLength) {
        return context.buildNativeGeometry(nativeHandle, vertexBuffer,
                vertexBufferLength, indexBufferShort, indexBufferLength);
    }

    private static class MTLMeshDisposerRecord implements Disposer.Record {

        private final MTLContext context;
        private long nativeHandle;

        MTLMeshDisposerRecord(MTLContext context, long nativeHandle) {
            this.context = context;
            this.nativeHandle = nativeHandle;
        }

        void traceDispose() {}

        @Override
        public void dispose() {
            if (nativeHandle != 0L && !context.isDisposed()) {
                traceDispose();
                context.releaseMTLMesh(nativeHandle);
                nativeHandle = 0L;
            }
        }
    }
}
