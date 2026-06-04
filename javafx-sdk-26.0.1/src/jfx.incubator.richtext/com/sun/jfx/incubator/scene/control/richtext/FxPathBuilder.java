/*
 * Copyright (c) 2022, 2024, Oracle and/or its affiliates. All rights reserved.
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
// This code borrows heavily from the following project, with permission from the author:
// https://github.com/andy-goryachev/FxEditor

package com.sun.jfx.incubator.scene.control.richtext;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.PathElement;

/**
 * Conventient utility for building javafx {@link Path}
 */
public class FxPathBuilder {
    private final ArrayList<PathElement> elements = new ArrayList<>();

    public FxPathBuilder() {
    }

    public void add(PathElement em) {
        elements.add(em);
    }

    public void addAll(PathElement... es) {
        for (PathElement em : es) {
            elements.add(em);
        }
    }

    public void moveto(double x, double y) {
        add(new MoveTo(x, y));
    }

    public void lineto(double x, double y) {
        add(new LineTo(x, y));
    }

    public List<PathElement> getPathElements() {
        return elements;
    }
}
