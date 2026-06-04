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

package jfx.incubator.scene.control.richtext;

import jfx.incubator.scene.control.richtext.model.CodeTextModel;
import jfx.incubator.scene.control.richtext.model.RichParagraph;

/**
 * Decorates plain text by producing a {@link RichParagraph}.
 *
 * @since 24
 */
public interface SyntaxDecorator {
    /**
     * Creates a {@link RichParagraph} with syntax decoration for the given paragraph index.
     * <p>
     * For simple cases, when decorations can be generated using the paragraph text,
     * it can be obtained by calling {@link CodeTextModel#getPlainText(int)}.
     * This string is guaranteed to contain no control symbols except for TAB.
     * <p>
     * Alternatively, the syntax decorator may cache the syntax information,
     * or create {@link Marker}s at critical points in the model, or even perform processing in the background
     * (provided the model supports concurrent access), culminating in sending a refresh even in the FX
     * application thread by calling
     * {@link CodeTextModel#fireChangeEvent(TextPos, TextPos, int, int, int)} method.
     *
     * @param model the model
     * @param index the paragraph index
     * @return the decorated {@link RichParagraph} instance
     */
    public RichParagraph createRichParagraph(CodeTextModel model, int index);

    /**
     * Receives the updates from the model, before any of the model's
     * {@link jfx.incubator.scene.control.richtext.model.StyledTextModel.Listener StyledTextModel.Listener}s
     * are notified.
     * <p>
     * The implementation might do nothing if the syntax can be determined based on the text of a single
     * paragraph.  Other implementations, which handle more complex syntax might want to re-build the syntax model
     * any time the plain text document changes, should use this method to trigger the refresh.
     *
     * @param m the model
     * @param start start of the affected range
     * @param end end of the affected range
     * @param charsTop number of characters added before any added paragraphs
     * @param linesAdded number of paragraphs inserted
     * @param charsBottom number of characters added after any inserted paragraphs
     */
    public void handleChange(CodeTextModel m, TextPos start, TextPos end, int charsTop, int linesAdded, int charsBottom);
}
