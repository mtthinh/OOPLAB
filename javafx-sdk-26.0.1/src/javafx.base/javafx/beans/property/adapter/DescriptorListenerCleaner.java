/*
 * Copyright (c) 2011, 2022, Oracle and/or its affiliates. All rights reserved.
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
package javafx.beans.property.adapter;

import java.lang.ref.WeakReference;

import com.sun.javafx.property.adapter.ReadOnlyPropertyDescriptor;

class DescriptorListenerCleaner<T> implements Runnable {

    private final ReadOnlyPropertyDescriptor<T> pd;
    private final WeakReference<ReadOnlyPropertyDescriptor<T>.ReadOnlyListener> lRef;

    DescriptorListenerCleaner(ReadOnlyPropertyDescriptor<T> pd, ReadOnlyPropertyDescriptor<T>.ReadOnlyListener l) {
        this.pd = pd;
        this.lRef = new WeakReference<>(l);
    }

    @Override
    public void run() {
        ReadOnlyPropertyDescriptor<T>.ReadOnlyListener l = lRef.get();
        if (l != null) {
            pd.removeListener(l);
        }
    }
}
