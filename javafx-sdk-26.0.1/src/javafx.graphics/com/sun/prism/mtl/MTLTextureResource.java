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

import com.sun.prism.impl.DisposerManagedResource;

class MTLTextureResource<T extends MTLTextureData> extends DisposerManagedResource<T> {

    private final boolean canDispose;

    MTLTextureResource(T resource, boolean canDispose) {
        super(resource, MTLVramPool.getInstance(), resource);
        this.canDispose = canDispose;
    }

    @Override
    public void free() {
        if (resource != null && canDispose) {
            resource.dispose();
            // resource.dispose() will free the native-side
            // resource = null is not set here, ManagedResource will handle that when appropriate
        }
    }
}
