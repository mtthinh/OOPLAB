/*
 * Copyright (c) 2011, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.font;


public interface CompositeFontResource extends FontResource {

    public FontResource getSlotResource(int slot);

    public int getNumSlots();

    default public int addSlotFont(FontResource font) {
        return -1;
    }

    /**
     * Returns the slot for the given font name.
     * Adds fontName as a new fallback font if needed.
     */
    public int getSlotForFont(String fontName);

    @Override
    default boolean isColorGlyph(int glyphCode) {
        int slot = (glyphCode >>> 24);
        int slotglyphCode = glyphCode & CompositeGlyphMapper.GLYPHMASK;
        FontResource slotResource = getSlotResource(slot);
        return slotResource.isColorGlyph(slotglyphCode);
    }
}
