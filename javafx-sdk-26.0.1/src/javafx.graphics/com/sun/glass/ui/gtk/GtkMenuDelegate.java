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

import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.delegate.MenuDelegate;
import com.sun.glass.ui.delegate.MenuItemDelegate;

class GtkMenuDelegate implements MenuDelegate {

    public GtkMenuDelegate() {
    }

    @Override
    public boolean createMenu(String title, boolean enabled) {
        return true;
    }

    @Override
    public boolean setTitle(String title) {
        return true;
    }

    @Override
    public boolean setEnabled(boolean enabled) {
        return true;
    }

    @Override
    public boolean setPixels(Pixels pixels) {
        return false;
    }

    @Override
    public boolean insert(MenuDelegate menu, int pos) {
        return true;
    }

    @Override
    public boolean insert(MenuItemDelegate item, int pos) {
        return true;
    }

    @Override
    public boolean remove(MenuDelegate menu, int pos) {
        return true;
    }

    @Override
    public boolean remove(MenuItemDelegate item, int pos) {
        return true;
    }

}
