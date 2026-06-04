/*
 * Copyright (c) 2024, 2025, Oracle and/or its affiliates. All rights reserved.
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

import com.sun.javafx.util.Utils;
import jfx.incubator.scene.control.richtext.model.StyleAttributeMap;

/**
 * Provides access to private methods in StyleAttributeMap.
 */
public class StyleAttributeMapHelper {
    public interface Accessor {
        public StyleAttributeMap filterAttributes(StyleAttributeMap ss, boolean isParagraph);
    }

    static {
        Utils.forceInit(StyleAttributeMap.class);
    }

    private static Accessor accessor;

    public static void setAccessor(Accessor a) {
        if (accessor != null) {
            throw new IllegalStateException();
        }
        accessor = a;
    }

    /**
     * Returns a new StyleAttributeMap instance which contains only the character attributes,
     * or null if no character attributes found.
     *
     * @param ss the style attribute map
     * @return the instance of StyleAttributeMap, or null
     */
    public static StyleAttributeMap getCharacterAttrs(StyleAttributeMap ss) {
        return accessor.filterAttributes(ss, false);
    }

    /**
     * Returns a new StyleAttributeMap instance which contains only the paragraph attributes,
     * or null if no paragraph attributes found.
     *
     * @param ss the style attribute map
     * @return the instance of StyleAttributeMap, or null
     */
    public static StyleAttributeMap getParagraphAttrs(StyleAttributeMap ss) {
        return accessor.filterAttributes(ss, true);
    }

    /**
     * Returns a new StyleAttributeMap instance which contains only paragraph attributes
     * when {@code forParagraph=true}, or character attributes when {@code forParagraph=false},
     * or null when no attributes of the specified type are found.
     *
     * @param ss the style attribute map
     * @param forParagraph determines which attributes to retain
     * @return the instance of StyleAttributeMap, or null
     */
    public static StyleAttributeMap filter(StyleAttributeMap ss, boolean forParagraph) {
        if (forParagraph) {
            return StyleAttributeMapHelper.getParagraphAttrs(ss);
        } else {
            return StyleAttributeMapHelper.getCharacterAttrs(ss);
        }
    }
}
