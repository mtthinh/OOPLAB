/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
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

/**
 * A native platform for a Linux system with an electrophoretic display, also
 * called an e-paper display.
 */
class EPDPlatform extends LinuxPlatform {

    /**
     * Creates a new Monocle EPD Platform.
     */
    EPDPlatform() {
        EPDSystem.getEPDSystem().loadLibrary();
    }

    @Override
    protected InputDeviceRegistry createInputDeviceRegistry() {
        return new EPDInputDeviceRegistry(false);
    }

    @Override
    protected NativeScreen createScreen() {
        try {
            return new EPDScreen();
        } catch (RuntimeException e) {
            return new HeadlessScreen();
        }
    }
}
