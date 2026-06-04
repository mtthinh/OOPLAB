/*
 * Copyright (c) 2021, 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.prism.mtl;

import com.sun.glass.ui.Screen;
import com.sun.glass.utils.NativeLibLoader;
import com.sun.prism.GraphicsPipeline;
import com.sun.prism.ResourceFactory;
import com.sun.prism.impl.PrismSettings;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class MTLPipeline extends GraphicsPipeline {

    private static MTLPipeline theInstance;
    private static MTLResourceFactory mtlResourceFactory;

    private static native boolean nSupportsMTL();

    static {
        String libName = "prism_mtl";

        if (PrismSettings.verbose) {
            System.err.println("Loading native metal library, named: " + libName);
        }
        NativeLibLoader.loadLibrary(libName);
        if (PrismSettings.verbose) {
            System.err.println("Succeeded: Loading native metal library.");
        }
        theInstance = new MTLPipeline();
    }

    private MTLPipeline() {}

    public static MTLPipeline getInstance() {
        return theInstance;
    }

    @Override
    public boolean init() {
        if (!MTLPipeline.nSupportsMTL()) {
            return false;
        }
        Map<String, Long> devDetails = new HashMap<>();
        devDetails.put("isVsyncEnabled", PrismSettings.isVsyncEnabled ? 1L : 0L);
        setDeviceDetails(devDetails);
        return true;
    }

    @Override
    public int getAdapterOrdinal(Screen screen) {
        return 0;
    }

    @Override
    public ResourceFactory getDefaultResourceFactory(List<Screen> screens) {
        // This creates only one resource factory, all the Metal resources like
        // MTLBuffer, MTLTexture and created and handled in native Metal classes.
        return getResourceFactory(Screen.getMainScreen());
    }

    @Override
    public ResourceFactory getResourceFactory(Screen screen) {
        // All the Metal resources like MTLBuffer, MTLTexture are created
        // and handled on native side of Metal impl.
        // So, a common ResourceFactory instance across screens is sufficient.
        if (mtlResourceFactory == null) {
            mtlResourceFactory = new MTLResourceFactory(screen);

            // This enables sharing of MTLCommandQueue between PRISM and GLASS
            Map<String, Long> devDetails = MTLPipeline.getInstance().getDeviceDetails();
            devDetails.put("mtlCommandQueue",
                                mtlResourceFactory.getContext().getMetalCommandQueue());
        }
        return mtlResourceFactory;
    }

    @Override
    public void dispose() {
        if (mtlResourceFactory != null) {
            mtlResourceFactory.dispose();
            mtlResourceFactory = null;
        }
        super.dispose();
    }

    @Override
    public boolean is3DSupported() {
        return true;
    }

    @Override
    public final boolean isMSAASupported() {
        return true;
    }

    @Override
    public boolean isVsyncSupported() {
        return true;
    }

    @Override
    public boolean supportsShaderType(ShaderType type) {
        return switch (type) {
            case MSL -> true;
            default -> false;
        };
    }

    @Override
    public boolean supportsShaderModel(ShaderModel model) {
        return switch (model) {
            case SM3 -> true;
            default -> false;
        };
    }
}
