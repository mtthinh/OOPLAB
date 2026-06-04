/*
 * Copyright (c) 2011, 2022, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.scenario.effect.impl.prism;

import com.sun.prism.Image;
import com.sun.scenario.effect.Filterable;

/**
 * This is a special class that is only used for the purposes of converting
 * a Prism image (from Image.platformImage) into a Filterable (see
 * PrismToolkit.toFilterable()) that can then be passed to
 * PrRenderer.createImageData().  All of this is only used by the Identity
 * effect; eventually we should figure out a more straightforward solution.
 */
public class PrImage implements Filterable {

    private final Image image;

    private PrImage(Image image) {
        this.image = image;
    }

    public static PrImage create(Image image) {
        return new PrImage(image);
    }

    public Image getImage() {
        return image;
    }

    @Override
    public Object getData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getContentWidth() {
        return image.getWidth();
    }

    @Override
    public int getContentHeight() {
        return image.getHeight();
    }

    @Override
    public int getPhysicalWidth() {
        return image.getWidth();
    }

    @Override
    public int getPhysicalHeight() {
        return image.getHeight();
    }

    @Override
    public float getPixelScale() {
        return image.getPixelScale();
    }

    @Override
    public int getMaxContentWidth() {
        return image.getWidth();
    }

    @Override
    public int getMaxContentHeight() {
        return image.getHeight();
    }

    @Override
    public void setContentWidth(int contentW) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setContentHeight(int contentH) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void lock() {
    }

    @Override
    public void unlock() {
    }

    @Override
    public boolean isLost() {
        return false;
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
