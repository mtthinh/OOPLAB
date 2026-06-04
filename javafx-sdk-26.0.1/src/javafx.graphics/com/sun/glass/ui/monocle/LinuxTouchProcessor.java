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

abstract class LinuxTouchProcessor implements LinuxInputProcessor {

    final TouchState state = new TouchState();
    final TouchPipeline pipeline;
    final LinuxTouchTransform transform;

    LinuxTouchProcessor(LinuxInputDevice device) {
        transform = new LinuxTouchTransform(device);
        String getFilterProperty =
                System.getProperty(
                        "monocle.input." + device.getProduct()
                                + ".touchFilters",
                        "");
        pipeline = new TouchPipeline();
        pipeline.addNamedFilters(getFilterProperty);
        pipeline.add(TouchInput.getInstance().getBasePipeline());
    }

}
