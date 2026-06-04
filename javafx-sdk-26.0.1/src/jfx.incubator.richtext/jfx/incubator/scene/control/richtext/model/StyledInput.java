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

package jfx.incubator.scene.control.richtext.model;

import java.io.Closeable;
import java.io.IOException;
import com.sun.jfx.incubator.scene.control.richtext.StringStyledInput;

/**
 * This interface represents a source of styled text segments for the purposes of
 * pasting, importing, or loading from an input stream.
 *
 * @since 24
 */
public interface StyledInput extends Closeable {
    /**
     * Returns the next segment, or null if no more segments.
     * @return the next segment, or null if no more segments
     */
    public abstract StyledSegment nextSegment();

    /** An empty StyledInput. */
    public static final StyledInput EMPTY = new StyledInput() {
        @Override
        public StyledSegment nextSegment() {
            return null;
        }

        @Override
        public void close() throws IOException {
        }
    };

    /**
     * Creates a plain text styled input with the specified style.
     *
     * @param text the source text
     * @param attrs the source style attributes
     * @return the StyledInput instance
     */
    public static StyledInput of(String text, StyleAttributeMap attrs) {
        return new StringStyledInput(text, attrs);
    }

    /**
     * Creates a plain text styled input with {@link StyleAttributeMap#EMPTY}.
     *
     * @param text the source text
     * @return the StyledInput instance
     */
    public static StyledInput of(String text) {
        return new StringStyledInput(text, StyleAttributeMap.EMPTY);
    }
}
