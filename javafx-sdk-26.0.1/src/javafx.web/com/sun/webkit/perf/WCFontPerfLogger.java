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

package com.sun.webkit.perf;

import com.sun.javafx.logging.PlatformLogger;
import com.sun.webkit.graphics.WCFont;
import com.sun.webkit.graphics.WCTextRun;

public final class WCFontPerfLogger extends WCFont {
    private static final PlatformLogger log =
            PlatformLogger.getLogger(WCFontPerfLogger.class.getName());

    private static final PerfLogger logger = PerfLogger.getLogger(log);

    private final WCFont fnt;

    public WCFontPerfLogger(WCFont fnt) {
        this.fnt = fnt;
    }

    public synchronized static boolean isEnabled() {
        return logger.isEnabled();
    }

    public static void log() {
        logger.log();
    }

    public static void reset() {
        logger.reset();
    }

    @Override
    public Object getPlatformFont() {
        return fnt.getPlatformFont();
    }

    @Override
    public WCFont deriveFont(float size) {
        logger.resumeCount("DERIVEFONT");
        WCFont res = fnt.deriveFont(size);
        logger.suspendCount("DERIVEFONT");
        return res;
    }

    @Override
    public WCTextRun[] getTextRuns(String str) {
        logger.resumeCount("GETTEXTRUNS");
        final WCTextRun runs[] = fnt.getTextRuns(str);
        logger.suspendCount("GETTEXTRUNS");
        return runs;
    }

    @Override
    public int[] getGlyphCodes(char[] chars) {
        logger.resumeCount("GETGLYPHCODES");
        int[] res = fnt.getGlyphCodes(chars);
        logger.suspendCount("GETGLYPHCODES");
        return res;
    }

    @Override
    public float getXHeight() {
        logger.resumeCount("GETXHEIGHT");
        float res = fnt.getXHeight();
        logger.suspendCount("GETXHEIGHT");
        return res;
    }

    @Override
    public double getGlyphWidth(int glyph) {
        logger.resumeCount("GETGLYPHWIDTH");
        double res = fnt.getGlyphWidth(glyph);
        logger.suspendCount("GETGLYPHWIDTH");
        return res;
    }

    @Override
    public float[] getGlyphBoundingBox(int glyph) {
        logger.resumeCount("GETGLYPHBOUNDINGBOX");
        float[] res = fnt.getGlyphBoundingBox(glyph);
        logger.suspendCount("GETGLYPHBOUNDINGBOX");
        return res;
    }

    @Override
    public int hashCode() {
        logger.resumeCount("HASH");
        int res = fnt.hashCode();
        logger.suspendCount("HASH");
        return res;
    }

    @Override
    public boolean equals(Object object) {
        logger.resumeCount("COMPARE");
        boolean res = fnt.equals(object);
        logger.suspendCount("COMPARE");
        return res;
    }

    @Override
    public float getAscent() {
        logger.resumeCount("GETASCENT");
        float res = fnt.getAscent();
        logger.suspendCount("GETASCENT");
        return res;
    }

    @Override
    public float getDescent() {
        logger.resumeCount("GETDESCENT");
        float res = fnt.getDescent();
        logger.suspendCount("GETDESCENT");
        return res;
    }

    @Override
    public float getLineSpacing() {
        logger.resumeCount("GETLINESPACING");
        float res = fnt.getLineSpacing();
        logger.suspendCount("GETLINESPACING");
        return res;
    }

    @Override
    public float getLineGap() {
        logger.resumeCount("GETLINEGAP");
        float res = fnt.getLineGap();
        logger.suspendCount("GETLINEGAP");
        return res;
    }

    @Override
    public boolean hasUniformLineMetrics() {
        logger.resumeCount("HASUNIFORMLINEMETRICS");
        boolean res = fnt.hasUniformLineMetrics();
        logger.suspendCount("HASUNIFORMLINEMETRICS");
        return res;
    }

    @Override
    public float getCapHeight() {
        logger.resumeCount("GETCAPHEIGHT");
        float res = fnt.getCapHeight();
        logger.suspendCount("GETCAPHEIGHT");
        return res;
    }
}
