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
package javafx.css;

/**
 * A class that contains {@code StyleClass} information.
 * @since 9
 */
public final class StyleClass {

    /**
     * Constructs a {@code StyleClass} object.
     * @param styleClassName name of the style class
     * @param index style class index
     */
    public StyleClass(String styleClassName, int index) {
        this.styleClassName = styleClassName;
        this.index = index;
    }

    /**
     * Returns the name of {@code StyleClass}.
     * @return the name of {@code StyleClass}
     */
    public String getStyleClassName() {
        return styleClassName;
    }

    /**
     * Returns the name of {@code StyleClass}.
     * @return the name of {@code StyleClass}
     */
    @Override public String toString() {
        return styleClassName;
    }

    /**
     * Returns the index of this {@code StyleClass} in the styleClasses list.
     * @return index
     */
    public int getIndex() {
       return index;
    }

    private final String styleClassName;

    // index of this StyleClass in styleClasses list.
    private final int index;

}
