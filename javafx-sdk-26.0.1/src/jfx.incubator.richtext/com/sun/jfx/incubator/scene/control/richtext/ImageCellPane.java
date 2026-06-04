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

package com.sun.jfx.incubator.scene.control.richtext;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Content pane for RichParagraph that shows a single image.
 * The image gets resized if it cannot fit into available width.
 */
public class ImageCellPane extends Pane {
    private final Image image;
    private final ImageView imageView;
    private static final Insets PADDING = new Insets(1, 1, 1, 1);

    /**
     * The constructor.
     * @param image the image
     */
    public ImageCellPane(Image image) {
        this.image = image;

        imageView = new ImageView(image);
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);
        getChildren().add(imageView);

        setPadding(PADDING);
        getStyleClass().add("image-cell-pane");
    }

    @Override
    protected void layoutChildren() {
        double width = getWidth();
        double sc;
        if (width < image.getWidth()) {
            sc = width / image.getWidth();
        } else {
            sc = 1.0;
        }
        imageView.setScaleX(sc);
        imageView.setScaleY(sc);

        double x0 = snappedLeftInset();
        double y0 = snappedTopInset();
        layoutInArea(
            imageView,
            x0,
            y0,
            image.getWidth() * sc,
            image.getHeight() * sc,
            0,
            PADDING,
            true,
            false,
            HPos.CENTER,
            VPos.CENTER
        );
    }

    @Override
    protected double computePrefHeight(double w) {
        double pad = snappedTopInset() + snappedBottomInset();
        if (w != -1) {
            if (w < image.getWidth()) {
                return pad + (image.getHeight() * w / image.getWidth());
            }
        }
        return pad + (image.getHeight());
    }
}
