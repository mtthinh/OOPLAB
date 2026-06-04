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

package jfx.incubator.scene.control.richtext.skin;

import javafx.scene.Node;
import jfx.incubator.scene.control.richtext.model.StyleAttributeMap;

/**
 * This interface provides a mechanism for the Skin to support custom {@code StyleAttribute}s.
 * During the layout pass, the cell context is passed to the Skin so the latter can add inline style(s)
 * to either the paragraph Node (typically {@code TextFlow}) or the text segment Node ({@code Text}).
 *
 * @see RichTextAreaSkin#applyStyles(CellContext, StyleAttributeMap, boolean)
 * @since 24
 */
public interface CellContext {
    /**
     * Adds an inline style.
     * <p>
     * The inline style must be a valid CSS style string, for example {@code "-fx-font-size:15px;"}.
     * This string might contain multiple CSS properties.
     *
     * @param fxStyle the inline style string
     */
    public void addStyle(String fxStyle);

    /**
     * Returns the node being styled.
     * <p>
     * This might be a TextFlow (for the paragraph cell context) or Text (for the text segment cell context).
     * @return the node being styled.
     */
    public Node getNode();

    /**
     * Returns the current attributes.
     * @return the current attributes.
     */
    public StyleAttributeMap getAttributes();
}
