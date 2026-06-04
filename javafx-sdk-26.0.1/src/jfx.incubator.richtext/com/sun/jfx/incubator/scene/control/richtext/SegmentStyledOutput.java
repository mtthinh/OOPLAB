/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jfx.incubator.scene.control.richtext;

import java.io.IOException;
import java.util.ArrayList;
import jfx.incubator.scene.control.richtext.model.StyledOutput;
import jfx.incubator.scene.control.richtext.model.StyledSegment;

/**
 * This StyledOutput simply collects StyledSegments in a list.
 */
public class SegmentStyledOutput implements StyledOutput {
    private ArrayList<StyledSegment> segments;

    public SegmentStyledOutput(int initialCapacity) {
        segments = new ArrayList<>(initialCapacity);
    }

    @Override
    public void consume(StyledSegment s) throws IOException {
        segments.add(s);
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }

    public StyledSegment[] getSegments() {
        return segments.toArray(new StyledSegment[segments.size()]);
    }
}
