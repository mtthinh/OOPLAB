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
import java.util.List;
import jfx.incubator.scene.control.richtext.model.StyledInput;
import jfx.incubator.scene.control.richtext.model.StyledSegment;

public class SegmentStyledInput implements StyledInput {
    private final StyledSegment[] segments;
    private int index;

    public SegmentStyledInput(StyledSegment[] segments) {
        this.segments = segments;
    }

    @Override
    public StyledSegment nextSegment() {
        if (index < segments.length) {
            return segments[index++];
        }
        return null;
    }

    public static SegmentStyledInput of(List<StyledSegment> segments) {
        StyledSegment[] ss = segments.toArray(new StyledSegment[segments.size()]);
        return new SegmentStyledInput(ss);
    }

    @Override
    public void close() throws IOException {
    }
}
