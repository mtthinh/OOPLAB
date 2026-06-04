/*
 * Copyright (c) 2019, 2024, Oracle and/or its affiliates. All rights reserved.
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

import com.sun.javafx.logging.PlatformLogger;
import com.sun.javafx.util.Logging;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;

import java.util.function.Supplier;

/**
 * A factory object for creating the native platform on a Linux system with an
 * electrophoretic display, also called an e-paper display, found on e-readers
 * such as the Amazon Kindle and Rakuten Kobo.
 */
class EPDPlatformFactory extends NativePlatformFactory {

    /**
     * The major version number of this platform factory.
     */
    private static final int MAJOR_VERSION = 1;

    /**
     * The minor version number of this platform factory.
     */
    private static final int MINOR_VERSION = 0;

    /**
     * The file that contains the name of the frame buffer device when CONFIG_FB
     * is defined during kernel compilation.
     */
    private static final String FB_FILE = "/proc/fb";

    /**
     * The name of the Mobile Extreme Convergence Electrophoretic Display
     * Controller Frame Buffer device.
     */
    private static final String FB_NAME = "mxc_epdc_fb";

    private final PlatformLogger logger = Logging.getJavaFXLogger();

    /**
     * Creates a new factory object for the Monocle EPD Platform.
     */
    EPDPlatformFactory() {
    }

    @Override
    protected boolean matches() {
        String fbinfo = ((Supplier<String>) () -> {
            String line = null;
            try (var reader = new BufferedReader(new FileReader(FB_FILE))) {
                line = reader.readLine();
            } catch (IOException e) {
                logger.severe("Failed reading " + FB_FILE, e);
            }
            return line;
        }).get();
        return fbinfo != null && fbinfo.contains(FB_NAME);
    }

    @Override
    protected NativePlatform createNativePlatform() {
        return new EPDPlatform();
    }

    @Override
    protected int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @Override
    protected int getMinorVersion() {
        return MINOR_VERSION;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}[majorVersion={1} minorVersion={2} matches=\"{3} in {4}\"]",
                getClass().getName(), getMajorVersion(), getMinorVersion(), FB_NAME, FB_FILE);
    }
}
