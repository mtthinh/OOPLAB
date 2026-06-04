/*
 * Copyright (c) 2011, 2020, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.pisces;

import java.nio.IntBuffer;

public final class JavaSurface extends AbstractSurface {

    private IntBuffer dataBuffer;

    private int[] dataInt;

    public JavaSurface(int[] dataInt, int dataType, int width, int height) {
        super(width, height);
        if (dataInt.length / width < height) {
            throw new IllegalArgumentException("width(=" + width + ") * height(="
                    + height + ") is greater than dataInt.length(=" + dataInt.length + ")");
        }
        this.dataInt = dataInt;
        this.dataBuffer = IntBuffer.wrap(this.dataInt);

        initialize(dataType, width, height);
        // The native method initialize() creates the native object of
        // struct JavaSurface and saves it's reference in the super class
        // member AbstractSurface.nativePtr. This reference is needed for
        // creating disposer record hence the below call to addDisposerRecord()
        // is needed here and cannot be made in super class constructor.
        addDisposerRecord();
    }

    public IntBuffer getDataIntBuffer() {
        return this.dataBuffer;
    }

    private native void initialize(int dataType, int width, int height);
}
