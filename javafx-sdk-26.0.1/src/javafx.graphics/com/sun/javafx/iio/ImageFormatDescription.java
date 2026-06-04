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

package com.sun.javafx.iio;

import java.util.Arrays;
import java.util.List;

/**
 * A description of image format attributes.
 */
public interface ImageFormatDescription {
    /**
     * Get the name of the format, for example "JPEG," "PNG," and so on,
     * preferably in upper case.
     *
     * @return the format name.
     */
    String getFormatName();

    /**
     * Get the extension(s) used for a file stored in this format, preferably in
     * lower case.
     *
     * @return the file extension(s) for this format.
     */
    List<String> getExtensions();

    /**
     * Get the possible signatures which may appear at the beginning of
     * the stream of an image stored in this format.
     *
     * @return the signatures of an image stream in this format.
     */
    List<Signature> getSignatures();

    /**
     * Get the MIME subtype(s) of the "image" type corresponding to this format,
     * for example, "jpeg" "png," etc.
     *
     * @return the MIME type(s) of this format.
     */
    List<String> getMIMESubtypes();

    /**
     * Represents a sequences of bytes which can appear at the beginning of
     * the stream of an image stored in this format.
     */
    public final class Signature {
        private final byte[] bytes;

        public Signature(final byte... bytes) {
            this.bytes = bytes;
        }

        public int getLength() {
            return bytes.length;
        }

        public boolean matches(final byte[] streamBytes) {
            if (streamBytes.length < bytes.length) {
                return false;
            }

            for (int i = 0; i < bytes.length; i++) {
                if (streamBytes[i] != bytes[i]) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(bytes);
        }

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }

            if (!(other instanceof Signature)) {
                return false;
            }

            return Arrays.equals(bytes, ((Signature) other).bytes);
        }
    }
}
