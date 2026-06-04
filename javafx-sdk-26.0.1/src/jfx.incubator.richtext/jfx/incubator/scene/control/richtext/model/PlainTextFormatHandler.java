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

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import javafx.scene.input.DataFormat;
import com.sun.jfx.incubator.scene.control.richtext.StringBuilderStyledOutput;
import jfx.incubator.scene.control.richtext.StyleResolver;
import jfx.incubator.scene.control.richtext.TextPos;

/**
 * {@link DataFormatHandler} which operates with plain text.
 *
 * @since 24
 */
public class PlainTextFormatHandler extends DataFormatHandler {
    private static final PlainTextFormatHandler instance = new PlainTextFormatHandler();

    /** The constructor. */
    private PlainTextFormatHandler() {
        super(DataFormat.PLAIN_TEXT);
    }

    /**
     * Returns the singleton instance of {@code PlainTextFormatHandler}.
     * @return the singleton instance of {@code PlainTextFormatHandler}
     */
    public static final PlainTextFormatHandler getInstance() {
        return instance;
    }

    @Override
    public StyledInput createStyledInput(String text, StyleAttributeMap attr) {
        return StyledInput.of(text, attr);
    }

    @Override
    public Object copy(StyledTextModel m, StyleResolver resolver, TextPos start, TextPos end) throws IOException {
        StringBuilderStyledOutput out = new StringBuilderStyledOutput(m.getLineEnding());
        m.export(start, end, out);
        return out.toString();
    }

    @Override
    public void save(StyledTextModel m, StyleResolver resolver, TextPos start, TextPos end, OutputStream out) throws IOException {
        byte[] newline = m.getLineEnding().getText().getBytes(StandardCharsets.UTF_8);

        StyledOutput so = new StyledOutput() {
            @Override
            public void consume(StyledSegment seg) throws IOException {
                switch (seg.getType()) {
                case LINE_BREAK:
                    out.write(newline);
                    break;
                case TEXT:
                    String text = seg.getText();
                    byte[] b = text.getBytes(StandardCharsets.UTF_8);
                    out.write(b);
                    break;
                }
            }

            @Override
            public void flush() throws IOException {
                out.flush();
            }

            @Override
            public void close() throws IOException {
                out.close();
            }
        };
        m.export(start, end, so);
        out.flush();
    }
}
