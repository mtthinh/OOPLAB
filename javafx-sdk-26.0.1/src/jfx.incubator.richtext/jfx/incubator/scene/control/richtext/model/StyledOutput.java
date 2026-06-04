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

package jfx.incubator.scene.control.richtext.model;

import java.io.Closeable;
import java.io.IOException;
import com.sun.jfx.incubator.scene.control.richtext.StringBuilderStyledOutput;
import jfx.incubator.scene.control.richtext.LineEnding;

/**
 * Class represents a consumer of styled text segments for the purposes of
 * exporting, copying, or saving to an output stream.
 *
 * @since 24
 */
public interface StyledOutput extends Closeable {
    /**
     * Consumes the next styled segment.
     *
     * @param segment the segment to output
     * @throws IOException when an I/O error occurs
     */
    public void consume(StyledSegment segment) throws IOException;

    /**
     * Flushes this output stream.
     * @throws IOException when an I/O error occurs
     */
    public void flush() throws IOException;

    /**
     * Creates an instance of a plain text StyledOutput with the platform line ending.
     * @return the instance of a plain text StyledOutput
     */
    public static StyledOutput forPlainText() {
        return new StringBuilderStyledOutput(LineEnding.system());
    }

    /**
     * Creates an instance of a plain text StyledOutput with the specified line ending characters.
     * @param lineEnding the line ending characters
     * @return the instance of a plain text StyledOutput
     * @since 26
     */
    public static StyledOutput forPlainText(LineEnding lineEnding) {
        return new StringBuilderStyledOutput(lineEnding);
    }
}
