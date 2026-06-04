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

import java.text.DecimalFormat;
import java.util.Arrays;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Side decorator which shows paragraph (line) numbers.
 * The numbering starts at line 1.
 *
 * @since 24
 */
public class LineNumberDecorator implements SideDecorator {
    private final DecimalFormat format;

    /**
     * Creates an instance with the Western-style group separator (comma).
     */
    public LineNumberDecorator() {
        this(new DecimalFormat("#,##0"));
    }

    /**
     * Creates an instance using the specified {@link DecimalFormat}.
     *
     * @param format the {@code DecimalFormat} to use
     */
    public LineNumberDecorator(DecimalFormat format) {
        this.format = format;
    }

    @Override
    public double getPrefWidth(double viewWidth) {
        // no set width, must request a measurement Node
        return 0;
    }

    @Override
    public Node getMeasurementNode(int index) {
        // make sure the size is sufficient to display all the numbers in the view
        String s = format.format(index + 300);
        char[] cs = new char[s.length()];
        // what's wider, 0 or 8 ?
        Arrays.fill(cs, '8');
        return createNode(new String(cs));
    }

    @Override
    public Node getNode(int index) {
        String s = format.format(index + 1);
        return createNode(s);
    }

    private Node createNode(String text) {
        Label t = new Label(text);
        t.getStyleClass().add("line-number-decorator");
        // label needs to fill all available space
        t.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        // do not interfere with vflow layout
        t.setMinHeight(1);
        t.setPrefHeight(1);
        // numbers should be right aligned
        t.setAlignment(Pos.CENTER_RIGHT);
        t.setOpacity(1.0);
        return t;
    }
}
