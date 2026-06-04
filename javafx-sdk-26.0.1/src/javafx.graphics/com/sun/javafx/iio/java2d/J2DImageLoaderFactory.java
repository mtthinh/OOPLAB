/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.iio.java2d;

import com.sun.javafx.iio.ImageFormatDescription;
import com.sun.javafx.iio.ImageLoader;
import com.sun.javafx.iio.ImageLoaderFactory;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class J2DImageLoaderFactory implements ImageLoaderFactory {

    private static J2DImageLoaderFactory theInstance;

    private J2DImageLoaderFactory() {}

    public static synchronized J2DImageLoaderFactory getInstance() {
        if (theInstance == null) {
            theInstance = new J2DImageLoaderFactory();
        }

        return theInstance;
    }

    @Override
    public ImageFormatDescription getFormatDescription() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImageLoader createImageLoader(InputStream input) throws IOException {
        boolean oldUseCache = ImageIO.getUseCache();
        ImageIO.setUseCache(false);

        try {
            ImageInputStream stream = ImageIO.createImageInputStream(input);
            if (stream != null) {
                Iterator<ImageReader> readers = ImageIO.getImageReaders(stream);
                ImageReader reader = readers.hasNext() ? readers.next() : null;
                if (reader == null) {
                    stream.close();
                    return null;
                }

                // J2DImageLoader is responsible for closing the ImageInputStream after
                // it has finished reading from it.
                return new J2DImageLoader(reader, stream);
            }

            return null;
        } finally {
            ImageIO.setUseCache(oldUseCache);
        }
    }

}
