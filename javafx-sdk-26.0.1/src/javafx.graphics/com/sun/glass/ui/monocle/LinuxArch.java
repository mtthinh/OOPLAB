/*
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates. All rights reserved.
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

import java.util.function.Supplier;

public class LinuxArch {

    private static final int bits = ((Supplier<Integer>) () -> {
        LinuxSystem system = LinuxSystem.getLinuxSystem();
        return (int) system.sysconf(LinuxSystem._SC_LONG_BIT);
    }).get();

    static boolean is64Bit() {
        return bits == 64;
    }

    static int getBits() {
        return bits;
    }

}
