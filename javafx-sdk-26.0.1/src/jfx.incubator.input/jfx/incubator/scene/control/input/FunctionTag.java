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

package jfx.incubator.scene.control.input;

import javafx.scene.control.Control;
import com.sun.javafx.ModuleUtil;

/**
 * A function tag is a public identifier of a method that can be mapped to a key binding by the
 * control's {@link InputMap}.
 * <h2>Example</h2>
 * Example:
 * <pre>
 * public class RichTextArea extends Control {
 *     public static class Tags {
 *         // Deletes the symbol before the caret.
 *         public static final FunctionTag BACKSPACE = new FunctionTag();
 *         // Copies selected text to the clipboard.
 *         public static final FunctionTag COPY = new FunctionTag();
 *         // Cuts selected text and places it to the clipboard.
 *         public static final FunctionTag CUT = new FunctionTag();
 *         ...
 * </pre>
 *
 * @since 24
 */
public final class FunctionTag {
    /** Constructs the function tag. */
    public FunctionTag() {
    }

    static { ModuleUtil.incubatorWarning(); }
}
