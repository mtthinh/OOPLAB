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

package com.sun.javafx.css;

import javafx.css.PseudoClass;

/**
 * Implementation details of {@link javafx.css.PseudoClass}
 */
final class PseudoClassImpl extends PseudoClass {

    /**
     * Constructs a {@code PseudoClassImpl} object.
     * @param pseudoClassName name of the pseudo-class
     * @param index index of this PseudoClass in pseudoClasses list
     * @return a {@code PseudoClassImpl} object
     */
    PseudoClassImpl(String pseudoClassName, int index) {
        this.pseudoClassName = pseudoClassName;
        this.index = index;
    }

    /**
     * Gets the pseudo class name.
     * @return the pseudo class name
     */
    @Override
    public String getPseudoClassName() {
        return pseudoClassName;
    }

    /**
     * Gets the pseudo class name.
     * @return the pseudo class name
     */
    @Override public String toString() {
        return pseudoClassName;
    }

    /**
     * Returns the index of this {@code PseudoClass} in the styleClasses list.
     * @return index
     */
    public int getIndex() {
       return index;
    }

    private final String pseudoClassName;

    // index of this PseudoClass in pseudoClasses list.
    private final int index;

}
