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

package com.sun.prism.es2;

import com.sun.prism.impl.DisposerManagedResource;

class ES2TextureResource<T extends ES2TextureData>
    extends DisposerManagedResource<T>
{
    ES2TextureResource(T resource) {
        super(resource, ES2VramPool.instance, resource);
    }

    @Override
    public void free() {
        if (resource != null) {
            resource.dispose();
            // resource.dispose() will free the native-side
            // resource = null is not set here, ManagedResource will handle that when appropriate
        }
    }
}
