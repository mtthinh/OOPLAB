/*
 * Copyright (c) 2012, 2022, Oracle and/or its affiliates. All rights reserved.
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

import com.sun.glass.ui.Clipboard;
import com.sun.glass.ui.SystemClipboard;

import java.util.HashMap;

/** There is no system clipboard on embedded Linux systems using a
 * framebuffer. For X11 and Android a different implementation will
 * be needed. */
final class MonocleSystemClipboard extends SystemClipboard {

    MonocleSystemClipboard() {
        super(Clipboard.SYSTEM);
    }

    @Override
    protected boolean isOwner() {
        return true;
    }

    @Override
    protected void pushToSystem(HashMap<String, Object> cacheData,
                                int supportedActions) {
    }

    @Override
    protected void pushTargetActionToSystem(int actionDone) {
    }
    @Override
    protected Object popFromSystem(String mimeType) {
        return null;
    }

    @Override
    protected int supportedSourceActionsFromSystem() {
        return Clipboard.ACTION_NONE;
    }

    @Override
    protected String[] mimesFromSystem() {
        return new String[0];
    }

}
