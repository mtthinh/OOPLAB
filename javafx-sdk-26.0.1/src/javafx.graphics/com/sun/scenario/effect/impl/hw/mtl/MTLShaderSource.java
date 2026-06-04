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

package com.sun.scenario.effect.impl.hw.mtl;

import com.sun.scenario.effect.Effect.AccelType;
import com.sun.scenario.effect.impl.hw.ShaderSource;
import java.io.InputStream;

public class MTLShaderSource implements ShaderSource {

    @Override
    public InputStream loadSource(String name) {
        // MSL shaders are compiled and linked into a MTLLibrary at build time.
        // At runtime, shaders get loaded from that library as needed.
        // throw new UnsupportedOperationException("Metal shader source is not available at runtime.");
        return null;
    }

    @Override
    public AccelType getAccelType() {
        return AccelType.METAL;
    }
}
