/*
 * Copyright (c) 2016, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.embed.swing;

import java.awt.EventQueue;
import com.sun.javafx.embed.swing.newimpl.SwingFXUtilsImplInteropN;

public class SwingFXUtilsImpl {

    private static SwingFXUtilsImplInteropN swFXUtilIOP;

    static {
        swFXUtilIOP = new SwingFXUtilsImplInteropN();
    }

    private static EventQueue getEventQueue() {
        return java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue();
    }

    //Called with reflection from PlatformImpl to avoid dependency
    public static void installFwEventQueue() {
        swFXUtilIOP.setFwDispatcher(getEventQueue());
    }

    //Called with reflection from PlatformImpl to avoid dependency
    public static void removeFwEventQueue() {
        swFXUtilIOP.setFwDispatcher(getEventQueue());
    }
}
