/*
 * Copyright (c) 2023, 2026, Oracle and/or its affiliates. All rights reserved.
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
import jfx.incubator.scene.control.richtext.LineEnding;
import jfx.incubator.scene.control.richtext.model.StyledOutput;
import jfx.incubator.scene.control.richtext.model.StyledSegment;

public class StringBuilderStyledOutput implements StyledOutput {
    private final int limit;
    private final StringBuilder sb;
    private final String newline;

    public StringBuilderStyledOutput(StringBuilder sb, LineEnding lineEnding, int limit) {
        // TODO throw IOException on running over limit
        this.limit = limit;
        this.sb = sb;
        newline = lineEnding.getText();
    }

    public StringBuilderStyledOutput(LineEnding lineEnding) {
        this(new StringBuilder(1024), lineEnding, Integer.MAX_VALUE);
    }

    @Override
    public void consume(StyledSegment seg) throws IOException {
        switch (seg.getType()) {
        case LINE_BREAK:
            append(newline, false);
            break;
        case TEXT:
            String text = seg.getText();
            // append as much text as possible
            // potentially risking breaking up code points and grapheme clusters
            append(text, true);
            break;
        }
    }

    private void append(String text, boolean allowPartial) throws IOException {
        if (limit < Integer.MAX_VALUE) {
            int available = limit - sb.length();
            if (text.length() > available) {
                if (allowPartial) {
                    sb.append(text, 0, available);
                }
                throw new IOException("limit exceeded");
            }
        }
        sb.append(text);
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }
}
