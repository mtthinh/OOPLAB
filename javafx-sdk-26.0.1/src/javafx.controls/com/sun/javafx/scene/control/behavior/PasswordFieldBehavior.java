/*
 * Copyright (c) 2011, 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.scene.control.behavior;

import javafx.scene.control.PasswordField;
import javafx.scene.text.HitInfo;

/**
 * Password field behavior.
 */
public class PasswordFieldBehavior extends TextFieldBehavior {

    public PasswordFieldBehavior(PasswordField passwordField) {
        super(passwordField);
    }

    // JDK-8127160 & JDK-8127849: Stub out word based navigation and editing
    // for security reasons.
    @Override
    protected void deletePreviousWord() { }
    @Override
    protected void deleteNextWord() { }
    @Override
    protected void selectPreviousWord() { }
    @Override
    public void selectNextWord() { }
    @Override
    protected void previousWord() { }
    @Override
    protected void nextWord() { }
    @Override
    protected void selectWord() {
        selectAll();
    }
    @Override
    protected void mouseDoubleClick(HitInfo hit) {
        getNode().selectAll();
    }

}
