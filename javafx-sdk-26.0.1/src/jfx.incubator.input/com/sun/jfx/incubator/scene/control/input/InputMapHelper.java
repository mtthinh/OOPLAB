/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.jfx.incubator.scene.control.input;

import com.sun.javafx.util.Utils;
import jfx.incubator.scene.control.input.FunctionTag;
import jfx.incubator.scene.control.input.InputMap;

/**
 * Hides execute() methods in InputMap from the public.
 */
public class InputMapHelper {
    public interface Accessor {
        public void execute(Object source, InputMap inputMap, FunctionTag tag);
        public void executeDefault(Object source, InputMap inputMap, FunctionTag tag);
        public void setSkinInputMap(InputMap inputMap, SkinInputMap sm);
    }

    static {
        Utils.forceInit(InputMap.class);
    }

    private static Accessor accessor;

    public static void setAccessor(Accessor a) {
        if (accessor != null) {
            throw new IllegalStateException();
        }
        accessor = a;
    }

    public static void execute(Object source, InputMap inputMap, FunctionTag tag) {
        accessor.execute(source, inputMap, tag);
    }

    public static void executeDefault(Object source, InputMap inputMap, FunctionTag tag) {
        accessor.executeDefault(source, inputMap, tag);
    }

    public static void setSkinInputMap(InputMap inputMap, SkinInputMap sm) {
        accessor.setSkinInputMap(inputMap, sm);
    }
}
