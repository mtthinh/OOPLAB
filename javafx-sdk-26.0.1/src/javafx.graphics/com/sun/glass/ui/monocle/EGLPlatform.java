/*
 * Copyright (c) 2020, 2021, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.glass.ui.monocle;

import java.util.ArrayList;
import java.util.List;

public class EGLPlatform extends LinuxPlatform {

    private List<NativeScreen> screens;

    /**
     * Create an <code>EGLPlatform</code>. If a library with specific native code is needed for this platform,
     * it will be downloaded now. The system property <code>monocle.egl.lib</code> can be used to define the
     * name of the library that should be loaded.
     */
    public EGLPlatform() {
        String lib = System.getProperty("monocle.egl.lib");
        if (lib != null) {
            long handle = LinuxSystem.getLinuxSystem().dlopen(lib, LinuxSystem.RTLD_LAZY | LinuxSystem.RTLD_GLOBAL);
            if (handle == 0) {
                throw new UnsatisfiedLinkError("EGLPlatform failed to load the requested library " + lib);
            }
        }
    }

    @Override
    protected NativeCursor createCursor() {
        // By default, hardware cursor will be used
        // Fallback to software cursor will be used in case monocle.egl.swcursor is set to true
        boolean swcursor = Boolean.getBoolean("monocle.egl.swcursor");
        final NativeCursor c = useCursor ? (swcursor ? new SoftwareCursor() : new EGLCursor()) : new NullCursor();
        return logSelectedCursor(c);
    }


    @Override
    protected NativeScreen createScreen() {
        return new EGLScreen(0);
    }

    @Override
    protected synchronized List<NativeScreen> createScreens() {
        if (screens == null) {
            int numScreens = nGetNumberOfScreens();
            screens = new ArrayList<>(numScreens);
            for (int i = 0; i < numScreens; i++) {
                screens.add(new EGLScreen(i));
            }
        }
        return screens;
    }

    @Override
    public synchronized AcceleratedScreen getAcceleratedScreen(int[] attributes) throws GLException {
        if (accScreen == null) {
            accScreen = new EGLAcceleratedScreen(attributes);
        }
        return accScreen;

    }

    private native int nGetNumberOfScreens();

}
