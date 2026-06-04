/*
 * Copyright (c) 2010, 2022, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.glass.ui.gtk;

import com.sun.glass.ui.delegate.MenuBarDelegate;
import com.sun.glass.ui.delegate.MenuDelegate;

class GtkMenuBarDelegate implements MenuBarDelegate {

    public GtkMenuBarDelegate() {
    }

    @Override
    public boolean createMenuBar() {
        return true;
    }

    @Override
    public boolean insert(MenuDelegate menu, int pos) {
        return true;
    }

    @Override
    public boolean remove(MenuDelegate menu, int pos) {
        return true;
    }

    @Override
    public long getNativeMenu() {
        return 0;
    }

}
