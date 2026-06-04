/*
 * Copyright (c) 2010, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.scene.control;

import com.sun.javafx.util.Utils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.skin.VirtualFlow;

/**
 * This custom ScrollBar is used to map the increment & decrement features
 * to pixel based scrolling rather than thumb/track based scrolling, if the
 * "virtual" attribute is true.
 */
public class VirtualScrollBar extends ScrollBar {

    /**************************************************************************
     *
     * Private fields
     *
     **************************************************************************/

    private final VirtualFlow flow;

    private boolean adjusting;



    /**************************************************************************
     *
     * Constructors
     *
     **************************************************************************/

    /**
     * Creates a new VirtualScrollBar, for use by the VirtualFlow control.
     */
    public VirtualScrollBar(final VirtualFlow flow) {
        this.flow = flow;

        super.valueProperty().addListener(valueModel -> {
            if (isVirtual()/* && oldValue != newValue*/) {
                if (adjusting) {
                    // no-op
                } else {
                    flow.setPosition(getValue());
                }
            }
        });
    }



    /**************************************************************************
     *
     * Properties
     *
     **************************************************************************/

    // --- virtual
    private BooleanProperty virtual = new SimpleBooleanProperty(this, "virtual");
    public final void setVirtual(boolean value) {
        virtual.set(value);
    }

    public final boolean isVirtual() {
        return virtual.get();
    }

    public final BooleanProperty virtualProperty() {
        return virtual;
    }


    /**************************************************************************
     *
     * Public API
     *
     **************************************************************************/

    /** {@inheritDoc} */
    @Override public void decrement() {
        if (isVirtual()) {
            flow.scrollPixels(-10);
        } else {
            super.decrement();
        }
    }

    /** {@inheritDoc} */
    @Override public void increment() {
        if (isVirtual()) {
            flow.scrollPixels(10);
        } else {
            super.increment();
        }
    }

    // this method is called when the user clicks in the scrollbar track, so
    // we special-case it to allow for page-up and page-down clicking to work
    // as expected.
    /** {@inheritDoc} */
    @Override public void adjustValue(double pos) {
        if (isVirtual()) {
            adjusting = true;
            if (pos < getValue()) {
                flow.scrollPixels(-flow.getViewportLength());
            } else {
                flow.scrollPixels(flow.getViewportLength());
            }
            adjusting = false;
        } else {
            super.adjustValue(pos);
        }
    }
}
