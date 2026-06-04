/*
 * Copyright (c) 2025, Gluon. All rights reserved.
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
package com.sun.glass.ui.headless;

import com.sun.glass.ui.Cursor;
import com.sun.glass.ui.Pixels;

public class HeadlessCursor extends Cursor {

     HeadlessCursor(int type) {
        super(type);
    }

    @Override
    protected long _createCursor(int x, int y, Pixels pixels) {
        return 1;
    }

}
