/*
 * Copyright (c) 2023, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.javafx.css;

import java.util.List;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.scene.Node;

/**
 * Utility methods for dealing with CSS.
 */
public final class CssUtil {

    private CssUtil() {
    }

    /**
     * Utility method which combines {@code CssMetaData} items in one immutable list.
     * <p>
     * The intended usage is to combine the parent and the child {@code CssMetaData} for
     * the purposes of {@code getClassCssMetaData()} method, see for example {@link Node#getClassCssMetaData()}.
     * <p>
     * Example:
     * <pre>{@code
     * private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES = CssMetaData.combine(
     *      <Parent>.getClassCssMetaData(),
     *      STYLEABLE1,
     *      STYLEABLE2
     *  );
     * }</pre>
     * This method returns an instance of a {@code List} that implements
     * {@link java.util.RandomAccess} interface.
     *
     * @param inheritedFromParent the {@code CssMetaData} items inherited from parent, must not be null
     * @param items the additional items
     * @return the immutable list containing all of the items
     */
    // NOTE: this should be a public utility, see https://bugs.openjdk.org/browse/JDK-8320796
    public static List<CssMetaData<? extends Styleable, ?>> combine(
        List<CssMetaData<? extends Styleable, ?>> inheritedFromParent,
        CssMetaData<? extends Styleable, ?>... items)
    {
        CssMetaData[] combined = new CssMetaData[inheritedFromParent.size() + items.length];
        inheritedFromParent.toArray(combined);
        System.arraycopy(items, 0, combined, inheritedFromParent.size(), items.length);
        return List.of(combined);
    }
}
