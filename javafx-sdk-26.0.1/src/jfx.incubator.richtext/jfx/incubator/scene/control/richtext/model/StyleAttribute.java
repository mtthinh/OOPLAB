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

import java.util.Objects;

/**
 * Style Attribute provides a way to specify style in the RichTextArea.
 *
 * @param <T> the attribute value type
 * @see StyleAttributeMap
 * @since 24
 */
public final class StyleAttribute<T> {
    private final String name;
    private final Class<T> type;
    private final boolean isParagraph;

    /**
     * Constructs the style attribute.
     *
     * @param name the attribute name (cannot be null)
     * @param type the attribute type
     * @param isParagraph specifies a paragraph attribute (true), or a character attribute (false)
     */
    public StyleAttribute(String name, Class<T> type, boolean isParagraph) {
        Objects.requireNonNull(name, "name cannot be null");
        this.name = name;
        this.type = type;
        this.isParagraph = isParagraph;
    }

    /**
     * Attribute name.
     *
     * @return attribute name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the class corresponding to the attribute value.
     *
     * @return attribute type
     */
    public final Class<T> getType() {
        return type;
    }

    /**
     * Returns true for a paragraph attribute, false for a character attribute.
     *
     * @return true for a paragraph attribute, false for a character attribute
     */
    public boolean isParagraphAttribute() {
        return isParagraph;
    }

    @Override
    public String toString() {
        return name;
    }

    // TODO maybe it should override equals() and hashCode()
}
