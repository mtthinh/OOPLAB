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

import jfx.incubator.scene.control.richtext.skin.CellContext;

/**
 * This functional interface defines a style attribute handler.
 * <p>
 * This interface is needed when extending the RichTextArea with support for other style attributes.
 * Applications should not normally use this interface.
 * <p>
 * The purpose of this handler is to apply changes to the {@code CellContext} based on the value
 * of the corresponding attribute.
 *
 * @param <C> the actual type of RichTextArea control
 * @param <T> the attribute value type
 *
 * @since 24
 */
@FunctionalInterface
public interface StyleAttributeHandler<C extends RichTextArea, T> {
    /**
     * Executes the attribute handler for the given control, cell context,
     * and the attribute value.
     *
     * @param control the control
     * @param cx the cell context
     * @param value the attribute value
     */
    public void apply(C control, CellContext cx, T value);
}
