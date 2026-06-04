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

import com.sun.glass.ui.MenuItem.Callback;
import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.delegate.MenuItemDelegate;

class GtkMenuItemDelegate implements MenuItemDelegate {

    public GtkMenuItemDelegate() {
    }

    @Override
    public boolean createMenuItem(String title, Callback callback, int shortcutKey, int shortcutModifiers, Pixels pixels, boolean enabled, boolean checked) {
        return true;
    }

    @Override
    public boolean setTitle(String title) {
        return true;
    }

    @Override
    public boolean setCallback(Callback callback) {
        return true;
    }

    @Override
    public boolean setShortcut(int shortcutKey, int shortcutModifiers) {
        return true;
    }

    @Override
    public boolean setPixels(Pixels pixels) {
        return true;
    }

    @Override
    public boolean setEnabled(boolean enabled) {
        return true;
    }

    @Override
    public boolean setChecked(boolean checked) {
        return true;
    }

}
