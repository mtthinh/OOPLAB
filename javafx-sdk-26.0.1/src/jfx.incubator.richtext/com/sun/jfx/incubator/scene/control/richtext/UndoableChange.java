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

package com.sun.jfx.incubator.scene.control.richtext;

import java.io.IOException;
import jfx.incubator.scene.control.richtext.StyleResolver;
import jfx.incubator.scene.control.richtext.TextPos;
import jfx.incubator.scene.control.richtext.model.StyledSegment;
import jfx.incubator.scene.control.richtext.model.StyledTextModel;

/**
 * Represents an undo-able and redo-able change.
 */
public class UndoableChange {
    private final StyledTextModel model;
    private final TextPos start;
    private final StyledSegment[] undo;
    private final boolean isEdit;
    private StyledSegment[] redo;
    private final TextPos endBefore;
    private TextPos endAfter;
    private UndoableChange prev;
    private UndoableChange next;

    private UndoableChange(StyledTextModel model, TextPos start, TextPos end, StyledSegment[] undo, boolean isEdit) {
        this.model = model;
        this.start = start;
        this.endBefore = end;
        this.undo = undo;
        this.isEdit = isEdit;
    }

    /**
     * Creates an UndoableChange object.
     * This method might return null if an error happened during creation, for example, if the model
     * could not export the affected area as a sequence of StyledSegments.
     * <p>
     * TODO perhaps it should throw an exception which will be handled by the control, in order to provide
     * user feedback.
     * @param model source model
     * @param start start text position
     * @param end end text position
     * @param isEdit determines whether it's a content change (true) or a style change (false)
     * @throws IOException if the save point cannot be created
     */
    public static UndoableChange create(StyledTextModel model, TextPos start, TextPos end, boolean isEdit) {
        try {
            SegmentStyledOutput out = new SegmentStyledOutput(128);
            model.export(start, end, out);
            StyledSegment[] ss = out.getSegments();
            return new UndoableChange(model, start, end, ss, isEdit);
        } catch (IOException e) {
            // TODO log
            return null;
        }
    }

    public static UndoableChange createHead() {
        return new UndoableChange(null, null, null, null, false);
    }

    @Override
    public String toString() {
        return
            "UndoableChange{" +
            "start=" + start +
            ", endBefore=" + endBefore +
            ", endAfter=" + endAfter;
    }

    public void setEndAfter(TextPos p) {
        endAfter = p;
    }

    public void undo(StyleResolver resolver) throws IOException {
        if (redo == null) {
            // create redo
            SegmentStyledOutput out = new SegmentStyledOutput(128);
            model.export(start, endAfter, out);
            redo = out.getSegments();
        }

        // undo
        SegmentStyledInput in = new SegmentStyledInput(undo);
        StyledTextModelHelper.replace(model, resolver, start, endAfter, in, false, isEdit);
    }

    public void redo(StyleResolver resolver) throws IOException {
        SegmentStyledInput in = new SegmentStyledInput(redo);
        StyledTextModelHelper.replace(model, resolver, start, endBefore, in, false, isEdit);
    }

    public UndoableChange getPrev() {
        return prev;
    }

    public void setPrev(UndoableChange ch) {
        prev = ch;
    }

    public UndoableChange getNext() {
        return next;
    }

    public void setNext(UndoableChange ch) {
        next = ch;
    }

    public TextPos[] getSelectionBefore() {
        return new TextPos[] {
            start,
            endBefore
        };
    }

    public TextPos[] getSelectionAfter() {
        return new TextPos[] {
            start,
            endAfter
        };
    }
}
