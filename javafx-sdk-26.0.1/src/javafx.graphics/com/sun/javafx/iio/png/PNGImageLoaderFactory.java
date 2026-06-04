/*
 * Copyright (c) 2009, 2022, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.iio.png;

import com.sun.javafx.iio.ImageFormatDescription;
import com.sun.javafx.iio.ImageLoader;
import com.sun.javafx.iio.ImageLoaderFactory;
import java.io.IOException;
import java.io.InputStream;

public class PNGImageLoaderFactory implements ImageLoaderFactory {
    private static final PNGImageLoaderFactory theInstance =
            new PNGImageLoaderFactory();

    private PNGImageLoaderFactory() {}

    public static final ImageLoaderFactory getInstance() {
        return theInstance;
    }

    @Override
    public ImageFormatDescription getFormatDescription() {
        return PNGDescriptor.getInstance();
    }

    @Override
    public ImageLoader createImageLoader(InputStream input) throws IOException {
        return new PNGImageLoader2(input);
    }

}
