/*
 * Copyright (c) 2013, 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.prism.d3d;

import com.sun.prism.impl.DisposerManagedResource;

class D3DTextureResource
    extends DisposerManagedResource<D3DTextureData>
{
    public D3DTextureResource(D3DTextureData resource) {
        super(resource, D3DVramPool.instance, resource);
    }

    @Override
    public void free() {
        if (resource != null) {
            resource.dispose();
            // resource.dispose() will free the native-side
            // resource = null is not set here, ManagedResource will handle that when appropriate
        }
    }

    @Override
    public boolean isValid() {
        return resource != null && resource.getResource() != 0L;
    }
}
