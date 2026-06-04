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

/**
 * Provides a way to add side decorations to each paragraph
 * in a {@link RichTextArea}.
 * <p>
 * The side decorations Nodes are added to either left or right side of each paragraph.  Each side node will be
 * resized to the height of the corresponding paragraph.  The width of all the side nodes, in order to avoid complicated
 * layout process, would be determined by the following process:
 * <ul>
 * <li>if {@link #getPrefWidth} method returns a value greater than 0, that will be the width of all the side nodes.
 * <li>otherwise, the {@link #getMeasurementNode(int)} method is called.
 * The preferred width of the {@code Node} returned will be used to size all other nodes for that side.
 * </ul>
 *
 * @since 24
 */
public interface SideDecorator {
    /**
     * Returns the width to size the pane which hosts the side decoration {@code Node}s.
     * <p>
     * When return value is 0 or negative, an alternative method to size the side pane hosting the decoration
     * will be used: a special measurement {@code Node} will be obtained via
     * {@link #getMeasurementNode(int)},
     * whose preferred width will be used instead.
     *
     * @param viewWidth width of the view
     * @return the preferred width
     */
    public double getPrefWidth(double viewWidth);

    /**
     * Returns the special measurement node to use for sizing the pane that holds the side decorations.
     * This method will only be called if {@link #getPrefWidth(double)} returns 0 or negative value.
     * The measurement node will not be displayed and will be discarded.
     *
     * @param index the paragraph index at the top of the viewable area
     * @return the measurement {@code Node}
     */
    public Node getMeasurementNode(int index);

    /**
     * Creates a Node to be added to the layout to the right or to the left of the given paragraph.
     * This method may return {@code null}.
     *
     * @param index the paragraph index
     * @return new instance {@code Node}
     */
    public Node getNode(int index);
}
