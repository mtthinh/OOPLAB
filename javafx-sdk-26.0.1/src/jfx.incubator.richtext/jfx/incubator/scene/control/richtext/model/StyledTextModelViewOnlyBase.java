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

import java.util.function.Supplier;
import javafx.scene.layout.Region;
import jfx.incubator.scene.control.richtext.TextPos;

/**
 * The base class for view-only {@link StyledTextModel}s.
 * <p>
 * Models extending this class will not be user editable.
 *
 * @since 24
 */
public abstract class StyledTextModelViewOnlyBase extends StyledTextModel {
    /** The constructor. */
    public StyledTextModelViewOnlyBase() {
        registerDataFormatHandler(RichTextFormatHandler.getInstance(), true, false, 2000);
    }

    /**
     * @return always returns {@code false}
     */
    @Override
    public final boolean isWritable() {
        return false;
    }

    @Override
    protected void removeRange(TextPos start, TextPos end) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected int insertTextSegment(int index, int offset, String text, StyleAttributeMap attrs) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void insertLineBreak(int index, int offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void insertParagraph(int index, Supplier<Region> generator) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected final void applyParagraphStyle(int ix, StyleAttributeMap a) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected final void setParagraphStyle(int ix, StyleAttributeMap a) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected final void applyStyle(int ix, int start, int end, StyleAttributeMap a, boolean merge) {
        throw new UnsupportedOperationException();
    }
}
