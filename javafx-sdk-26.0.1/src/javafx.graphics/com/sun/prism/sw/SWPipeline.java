/*
 * Copyright (c) 2011, 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.prism.sw;

import com.sun.glass.ui.Screen;
import com.sun.glass.utils.NativeLibLoader;
import com.sun.prism.GraphicsPipeline;
import com.sun.prism.ResourceFactory;
import com.sun.prism.impl.PrismSettings;
import com.sun.javafx.PlatformUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SWPipeline extends GraphicsPipeline {

    static {
        NativeLibLoader.loadLibrary("prism_sw");
    }

    @Override public boolean init() {
        Map<String, Boolean> devDetails = new HashMap<>();
        setDeviceDetails(devDetails);
        return true;
    }

    private static SWPipeline theInstance;

    private SWPipeline() {
    }

    public static SWPipeline getInstance() {
        if (theInstance == null) {
            theInstance = new SWPipeline();
        }
        return theInstance;
    }

    private final HashMap<Integer, SWResourceFactory> factories =
            new HashMap<>(1);

    @Override
    public int getAdapterOrdinal(Screen screen) {
        return Screen.getScreens().indexOf(screen);
    }

    @Override public ResourceFactory getResourceFactory(Screen screen) {
        Integer index = Integer.valueOf(screen.getAdapterOrdinal());
        SWResourceFactory factory = factories.get(index);
        if (factory == null) {
            factory = new SWResourceFactory(screen);
            factories.put(index, factory);
            if (PlatformUtil.isMac()) {
                Map<String, Boolean> devDetails = getDeviceDetails();
                if (PrismSettings.defaultPipeline.equals("es2")) {
                    devDetails.put("useMTLInGlassForSW", false);
                } else {
                    devDetails.put("useMTLInGlassForSW", true);
                }
            }
        }
        return factory;
    }

    @Override public ResourceFactory getDefaultResourceFactory(List<Screen> screens) {
        return getResourceFactory(Screen.getMainScreen());
    }

    @Override public boolean is3DSupported() {
        return false;
    }

    @Override
    public boolean isVsyncSupported() {
        return false;
    }

    @Override
    public boolean supportsShaderType(ShaderType type) {
        return false;
    }

    @Override
    public boolean supportsShaderModel(ShaderModel model) {
        return false;
    }

    @Override public void dispose() {
        // TODO: implement (JDK-8092378)
        super.dispose();
    }

    @Override
    public boolean isUploading() {
        return true;
    }
}
