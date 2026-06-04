/*
 * Copyright (c) 2011, 2021, Oracle and/or its affiliates. All rights reserved.
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.scene.control;

import javafx.geometry.Orientation;

/**
 * <p>
 * A {@link MenuItem} that as the name suggests allows for a horizontal Separator to be embedded within it,
 * by assigning a {@link Separator} to the {@link #contentProperty() content} property of the {@link CustomMenuItem}
 * This is provided for convenience as groups of {@link MenuItem menuitems} can be separated
 * by a separator. Instead of a creating a {@link CustomMenuItem}  for this purpose, the user
 * can use this class as indicated below.
 *
<pre><code>MenuItem item1 = new MenuItem("Item1");
MenuItem item2 = new MenuItem("Item2");
SeparatorMenuItem separator = new SeparatorMenuItem();

Menu menu = new Menu("Items");
menu.getItems().addAll(item1, separator, item2);
MenuBar menuBar = new MenuBar(menu);</code></pre>
 *
 * <img src="doc-files/SeparatorMenuItem.png" alt="Image of the SeparatorMenuItem control">
 *
 * @see CustomMenuItem
 * @see MenuItem
 * @see Menu
 * @since JavaFX 2.0
 */
public class SeparatorMenuItem extends CustomMenuItem {

    /* *************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    /**
     * Creates a default SeparatorMenuItem instance.
     */
    public SeparatorMenuItem() {
        super(new Separator(Orientation.HORIZONTAL), false);
        getStyleClass().add(DEFAULT_STYLE_CLASS);
    }



    /* *************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    private static final String DEFAULT_STYLE_CLASS = "separator-menu-item";
}
