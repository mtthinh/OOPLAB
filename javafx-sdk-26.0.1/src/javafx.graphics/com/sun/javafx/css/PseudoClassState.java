/*
 * Copyright (c) 2011, 2023, Oracle and/or its affiliates. All rights reserved.
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * States represents a set of State. A {@code Node} may be in more than
 * one pseudo-class state. {@code States} is used to aggregate the active
 * pseudo-class state of a {@code Node}.
 */
public final class PseudoClassState extends BitSet<PseudoClass> {

    /** Create an empty set of PseudoClass */
    public PseudoClassState() {
        super();
    }

    PseudoClassState(List<String> pseudoClassNames) {
        super();

        int nMax = pseudoClassNames != null ? pseudoClassNames.size() : 0;
        for(int n=0; n<nMax; n++) {
            final PseudoClass sc = getPseudoClass(pseudoClassNames.get(n));
            add(sc);
        }
    }

    @Override
    public String toString() {
        List<String> strings = new ArrayList<>();
        Iterator<PseudoClass> iter = iterator();
        while (iter.hasNext()) {
            strings.add(iter.next().getPseudoClassName());
        }
        return strings.toString();
    }

    @Override
    protected Class<PseudoClass> getElementType() {
        return PseudoClass.class;
    }

    @Override
    protected PseudoClass getT(int index) {
        return getPseudoClass(index);
    }

    @Override
    protected int getIndex(PseudoClass t) {

        if (t instanceof PseudoClassImpl) {
            return ((PseudoClassImpl)t).getIndex();
        }

        final String pseudoClass = t.getPseudoClassName();
        Integer index = pseudoClassMap.get(pseudoClass);

        if (index == null) {
            index = Integer.valueOf(pseudoClasses.size());
            pseudoClasses.add(new PseudoClassImpl(pseudoClass, index.intValue()));
            pseudoClassMap.put(pseudoClass, index);
        }
        return index.intValue();

    }


    /**
     * @see javafx.css.PseudoClass#getPseudoClass(String)
     */
    public static PseudoClass getPseudoClass(String pseudoClass) {

        if (pseudoClass == null || pseudoClass.trim().isEmpty()) {
            throw new IllegalArgumentException("pseudoClass cannot be null or empty String");
        }

        PseudoClass instance = null;

        final Integer value = pseudoClassMap.get(pseudoClass);
        final int index = value != null ? value.intValue() : -1;

        final int size = pseudoClasses.size();
        assert index < size;

        if (index != -1 && index < size) {
            instance = pseudoClasses.get(index);
        }

        if (instance == null) {
            instance = new PseudoClassImpl(pseudoClass, size);
            pseudoClasses.add(instance);
            pseudoClassMap.put(pseudoClass, Integer.valueOf(size));
        }

        return instance;
    }

    static PseudoClass getPseudoClass(int index) {
       if (0 <= index && index < pseudoClasses.size()) {
           return pseudoClasses.get(index);
       }
       return null;
    }

    // package private for unit test purposes
    static final Map<String,Integer> pseudoClassMap =
            new HashMap<>(64);

    static final List<PseudoClass> pseudoClasses =
            new ArrayList<>();

}

