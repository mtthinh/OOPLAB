/*
 * Copyright (c) 2022, 2023, Oracle and/or its affiliates. All rights reserved.
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

package javafx.scene.control;

/**
 * Base class for a constrained column resize policy.
 * Setting any policy that extends this class on a Tree/TableView results in
 * disabling of its horizontal scroll bar.
 *
 * @see TableView#columnResizePolicyProperty
 * @see TreeTableView#columnResizePolicyProperty
 *
 * @since 20
 */
public abstract class ConstrainedColumnResizeBase {
    /**
     * Constructor for subclasses to call.
     */
    public ConstrainedColumnResizeBase() {
    }

    @Override
    public String toString() {
        // name of a pseudo-style set on a Tree/TableView when a constrained resize policy is in effect
        return "constrained-resize";
    }
}
