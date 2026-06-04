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

package com.sun.javafx.image.impl;

import com.sun.javafx.image.AlphaType;
import com.sun.javafx.image.IntPixelAccessor;
import com.sun.javafx.image.IntPixelGetter;
import com.sun.javafx.image.IntPixelSetter;
import com.sun.javafx.image.IntToIntPixelConverter;
import com.sun.javafx.image.PixelUtils;
import java.nio.IntBuffer;

public class IntRgb {
    private IntRgb() {}

    public static final IntPixelGetter     getter = Accessor.instance;
    public static final IntPixelSetter     setter = Accessor.instance;
    public static final IntPixelAccessor accessor = Accessor.instance;

    public static IntToIntPixelConverter ToIntArgbPreConverter() {
        return IntRgb.ToIntArgbPreConv.instance;
    }

    static class Accessor implements IntPixelAccessor {
        static final IntPixelAccessor instance = new Accessor();
        private Accessor() {}

        @Override
        public AlphaType getAlphaType() {
            return AlphaType.OPAQUE;
        }

        @Override
        public int getNumElements() {
            return 1;
        }

        @Override
        public int getArgb(int[] arr, int offset) {
            return arr[offset] | (0xff << 24);
        }

        @Override
        public int getArgbPre(int[] arr, int offset) {
            return PixelUtils.NonPretoPre(arr[offset] | (0xff << 24));
        }

        @Override
        public int getArgb(IntBuffer buffer, int offset) {
            return buffer.get(offset) | (0xff << 24);
        }

        @Override
        public int getArgbPre(IntBuffer buffer, int offset) {
            return PixelUtils.NonPretoPre(buffer.get(offset) | (0xff << 24));
        }

        @Override
        public void setArgb(int[] arr, int offset, int argb) {
            arr[offset] = argb | (0xff << 24);
        }

        @Override
        public void setArgbPre(int[] arr, int offset, int argbpre) {
            arr[offset] = PixelUtils.PretoNonPre(argbpre) | (0xff << 24);
        }

        @Override
        public void setArgb(IntBuffer buffer, int offset, int argb) {
            buffer.put(offset, argb | (0xff << 24));
        }

        @Override
        public void setArgbPre(IntBuffer buffer, int offset, int argbpre) {
            buffer.put(offset, PixelUtils.PretoNonPre(argbpre) | (0xff << 24));
        }
    }

    public static class ToIntArgbPreConv extends BaseIntToIntConverter {
        public static final IntToIntPixelConverter instance =
            new ToIntArgbPreConv();

        private ToIntArgbPreConv() {
            super(IntRgb.getter, IntArgbPre.setter);
        }

        @Override
        void doConvert(int[] srcarr, int srcoff, int srcscanints,
                       int[] dstarr, int dstoff, int dstscanints,
                       int w, int h)
        {
            srcscanints -= w;
            dstscanints -= w;
            while (--h >= 0) {
                for (int x = 0; x < w; x++) {
                    int pixel = srcarr[srcoff++];
                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >>  8) & 0xff;
                    int b = (pixel      ) & 0xff;
                    dstarr[dstoff++] = (255 << 24) | (r << 16) | (g << 8) | b;
                }
                srcoff += srcscanints;
                dstoff += dstscanints;
            }
        }

        @Override
        void doConvert(IntBuffer srcbuf, int srcoff, int srcscanints,
                       IntBuffer dstbuf, int dstoff, int dstscanints,
                       int w, int h)
        {
            while (--h >= 0) {
                for (int x = 0; x < w; x++) {
                    int pixel = srcbuf.get(srcoff + x);
                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >>  8) & 0xff;
                    int b = (pixel      ) & 0xff;
                    dstbuf.put(dstoff + x, (255 << 24) | (r << 16) | (g << 8) | b);
                }
                srcoff += srcscanints;
                dstoff += dstscanints;
            }
        }
    }

}
