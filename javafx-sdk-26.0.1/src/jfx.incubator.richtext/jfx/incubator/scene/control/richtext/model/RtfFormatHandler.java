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
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import javafx.scene.input.DataFormat;
import com.sun.jfx.incubator.scene.control.richtext.RtfStyledOutput;
import com.sun.jfx.incubator.scene.control.richtext.rtf.RTFReader;
import jfx.incubator.scene.control.richtext.StyleResolver;
import jfx.incubator.scene.control.richtext.TextPos;

/**
 * This {@link DataFormatHandler} provides export/import support for RTF format.
 *
 * @since 24
 */
// TODO import is not yet working...
public class RtfFormatHandler extends DataFormatHandler {
    private static final RtfFormatHandler instance = new RtfFormatHandler();

    /** The constructor */
    private RtfFormatHandler() {
        super(DataFormat.RTF);
    }

    /**
     * Returns the singleton instance of {@code RtfFormatHandler}.
     * @return the singleton instance of {@code RtfFormatHandler}
     */
    public static final RtfFormatHandler getInstance() {
        return instance;
    }

    @Override
    public StyledInput createStyledInput(String text, StyleAttributeMap attr) throws IOException {
        try (RTFReader rd = new RTFReader(text)) {
            return rd.generateStyledInput();
        }
    }

    @Override
    public Object copy(StyledTextModel model, StyleResolver resolver, TextPos start, TextPos end) throws IOException {
        StringWriter wr = new StringWriter(65536);
        export(model, resolver, start, end, wr);
        return wr.toString();
    }

    @Override
    public void save(StyledTextModel model, StyleResolver resolver, TextPos start, TextPos end, OutputStream out)
        throws IOException {
        Charset ascii = Charset.forName("ASCII");
        OutputStreamWriter wr = new OutputStreamWriter(out, ascii);
        export(model, resolver, start, end, wr);
    }

    private void export(StyledTextModel model, StyleResolver resolver, TextPos start, TextPos end, Writer wr)
        throws IOException {
        RtfStyledOutput out = new RtfStyledOutput(resolver, wr);
        // collect styles
        model.export(start, end, out.firstPassBuilder());

        out.writePrologue();
        model.export(start, end, out);
        out.writeEpilogue();
        out.flush();
    }
}
