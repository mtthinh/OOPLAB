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

import java.io.IOException;
import java.io.OutputStream;
import javafx.scene.input.DataFormat;
import jfx.incubator.scene.control.richtext.StyleResolver;
import jfx.incubator.scene.control.richtext.TextPos;

/**
 * Facilitates import/export of styled text into/from a StyledTextModel.
 *
 * @since 24
 */
public abstract class DataFormatHandler {
    /**
     * Creates a StyledInput for the given input string.  When pasting, the caller may pass
     * the style attributes {@code attr} at the insertion point.  This argument may be used by
     * the implementation if the format contains no styles on its own (for example, in the plain text format case).
     *
     * @param input the input string
     * @param attr the style attributes (can be null)
     * @return the StyledInput
     * @throws IOException when operation is not supported or an I/O error occurs
     * @throws UnsupportedOperationException if the copy operation is not supported
     */
    public abstract StyledInput createStyledInput(String input, StyleAttributeMap attr) throws IOException;

    /**
     * Creates an object to be put into the Clipboard for the given text range.
     * The caller must guarantee that the {@code start} precedes the {@code end} position.
     * <p>
     * Typically, the implementation creates an instance of {@link StyledOutput} and calls
     * {@link StyledTextModel#export(TextPos, TextPos, StyledOutput)} method.
     *
     * @param model source model
     * @param resolver view-specific style resolver
     * @param start start text position
     * @param end end text position
     * @return an object to be placed to the Clipboard
     * @throws IOException when an I/O error occurs
     * @throws UnsupportedOperationException if the copy operation is not supported
     */
    public abstract Object copy(
        StyledTextModel model,
        StyleResolver resolver,
        TextPos start,
        TextPos end
    ) throws IOException;

    /**
     * Save the text range in the handler's format to the output stream (e.g. save to file).
     * The caller must guarantee that the {@code start} precedes the {@code end} position.
     * It is the responsibility of the caller to close the {@code OutputStream}.
     * <p>
     * Typically, the implementation creates an instance of {@link StyledOutput} and calls
     * {@link StyledTextModel#export(TextPos, TextPos, StyledOutput)} method.
     *
     * @param model source model
     * @param resolver view-specific style resolver
     * @param start start text position
     * @param end end text position
     * @param out target {@code OutputStream}
     * @throws IOException when an I/O error occurs
     * @throws UnsupportedOperationException if the copy operation is not supported
     */
    public abstract void save(
        StyledTextModel model,
        StyleResolver resolver,
        TextPos start,
        TextPos end,
        OutputStream out
    ) throws IOException;

    private final DataFormat format;

    /**
     * Creates a DataHandler instance for the specified format.
     * @param f data format
     */
    public DataFormatHandler(DataFormat f) {
        this.format = f;
    }

    /**
     * Returns the {@link DataFormat} associated with this handler.
     * @return the data format
     */
    public final DataFormat getDataFormat() {
        return format;
    }
}
