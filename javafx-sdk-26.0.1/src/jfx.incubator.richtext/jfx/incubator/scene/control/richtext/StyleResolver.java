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

import javafx.scene.Node;
import javafx.scene.image.WritableImage;
import jfx.incubator.scene.control.richtext.model.StyleAttributeMap;

/**
 * Enables conversion of CSS styles to {@code StyleAttribute}s.
 * <p>
 * Whenever the {@code StyledTextModel} contains logical class names instead of actual attributes,
 * a separate CSS style resolution step is required.  The resulting attributes might depend on the view that
 * originated an operation such as exporting or coping.
 * <p>
 * This interface is a part of API layer between the model and the view, and only comes to play when the
 * model refers to CSS styles.
 * Applications should not normally use this interface.
 *
 * @since 24
 */
public interface StyleResolver {
    /**
     * Resolves CSS styles (when present) to the individual attributes declared in {@link StyleAttributeMap}.
     *
     * @param attrs the style attributes
     * @return the resolved style attributes
     */
    public StyleAttributeMap resolveStyles(StyleAttributeMap attrs);

    /**
     * Creates a snapshot of the specified Node to be exported or copied as an image.
     *
     * @param node the {@link Node} to make a snapshot of
     * @return snapshot the generated {@link WritableImage}
     */
    public WritableImage snapshot(Node node);
}
