/*
 * Copyright (c) 2009, 2023, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.iio.common;

import com.sun.javafx.iio.ImageFormatDescription;
import java.util.List;

public class ImageDescriptor implements ImageFormatDescription {
    private final String formatName;
    private final List<String> extensions;
    private final List<Signature> signatures;
    private final List<String> mimeSubtypes;

    public ImageDescriptor(String formatName, String[] extensions, Signature[] signatures, String[] mimeSubtypes) {
        this.formatName = formatName;
        this.extensions = List.of(extensions);
        this.signatures = List.of(signatures);
        this.mimeSubtypes = List.of(mimeSubtypes);
    }

    @Override
    public String getFormatName() {
        return formatName;
    }

    @Override
    public List<String> getExtensions() {
        return extensions;
    }

    @Override
    public List<Signature> getSignatures() {
        return signatures;
    }

    @Override
    public List<String> getMIMESubtypes() {
        return mimeSubtypes;
    }
}
