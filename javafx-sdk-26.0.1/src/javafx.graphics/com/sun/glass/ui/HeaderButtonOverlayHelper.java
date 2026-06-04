/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.glass.ui;

import com.sun.javafx.scene.layout.RegionHelper;
import com.sun.javafx.util.Utils;
import javafx.scene.Node;

final class HeaderButtonOverlayHelper extends RegionHelper {

    private static final HeaderButtonOverlayHelper theInstance = new HeaderButtonOverlayHelper();
    private static Accessor theAccessor;

    private HeaderButtonOverlayHelper() {}

    static {
        Utils.forceInit(HeaderButtonOverlay.class);
    }

    public static void setAccessor(Accessor accessor) {
        theAccessor = accessor;
    }

    public static void initHelper(HeaderButtonOverlay overlay) {
        setHelper(overlay, theInstance);
    }

    @Override
    protected void processCSSImpl(Node node) {
        super.processCSSImpl(node);
        theAccessor.afterProcessCSS((HeaderButtonOverlay)node);
    }

    public interface Accessor {
        void afterProcessCSS(HeaderButtonOverlay overlay);
    }
}
