/*
 * Copyright (c) 2011, 2018, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.glass.ui.ios;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.GlassRobot;

/**
 * iOS platform implementation class of test automation Robot.
 */
final class IosRobot extends GlassRobot {

    private long ptr = 0;

    // init and create native robot object
    private native long _init();

    @Override public void create() {
        Application.checkEventThread();
        ptr = _init();
    }

    // release native robot object
    private native void _destroy(long ptr);
    @Override public void destroy() {
        Application.checkEventThread();
        _destroy(ptr);
        ptr = 0;
    }

    // synthesize key press
    private native void _keyPress(long ptr, int code);
    @Override public void keyPress(KeyCode code) {
        Application.checkEventThread();
        if (ptr == 0) {
            return;
        }
        _keyPress(ptr, code.getCode());
    }

    // synthesize key release
    private native void _keyRelease(long ptr, int code);
    @Override public void keyRelease(KeyCode code) {
        Application.checkEventThread();
        if (ptr == 0) {
            return;
        }
        _keyRelease(ptr, code.getCode());
    }

    // synthesize mouse motion
    private native void _mouseMove(long ptr, int x, int y);
    @Override public void mouseMove(double x, double y) {
        Application.checkEventThread();
        if (ptr == 0) {
            return;
        }
        _mouseMove(ptr, (int) x, (int) y);
    }

    // synthesize mouse press of buttons
    private native void _mousePress(long ptr, int buttons);
    @Override
    public void mousePress(MouseButton... buttons) {
        Application.checkEventThread();
        if (ptr == 0) {
            return;
        }
        _mousePress(ptr, GlassRobot.convertToRobotMouseButton(buttons));
    }

    // synthesize mouse release of buttons
    private native void _mouseRelease(long ptr, int buttons);
    @Override
    public void mouseRelease(MouseButton... buttons) {
        Application.checkEventThread();
        if (ptr == 0) {
            return;
        }
        _mouseRelease(ptr, GlassRobot.convertToRobotMouseButton(buttons));
    }

    private native void _mouseWheel(long ptr, int wheelAmt);
    @Override public void mouseWheel(int wheelAmt) {
        Application.checkEventThread();
        if (ptr == 0) {
            return;
        }
        _mouseWheel(ptr, wheelAmt);
    }

    // get x-coordinate of mouse location
    private native int _getMouseX(long ptr);
    @Override public double getMouseX() {
        Application.checkEventThread();
        if (ptr == 0) {
            return 0;
        }
        return _getMouseX(ptr);
    }

    // get x-coordinate of mouse location
    private native int _getMouseY(long ptr);
    @Override public double getMouseY() {
        Application.checkEventThread();
        if (ptr == 0) {
            return 0;
        }
        return _getMouseY(ptr);
    }

    private native int _getPixelColor(long ptr, int x, int y);
    @Override public Color getPixelColor(double x, double y) {
        Application.checkEventThread();
        if (ptr == 0) {
            return GlassRobot.convertFromIntArgb(0);
        }
        return GlassRobot.convertFromIntArgb(_getPixelColor(ptr, (int) x, (int) y));
    }

    private native void _getScreenCapture(long ptr, int x, int y, int width, int height, int[] data);
    @Override
    public void getScreenCapture(int x, int y, int width, int height, int[] data, boolean scaleToFit) {
        Application.checkEventThread();
        if (ptr == 0) {
            return;
        }
        _getScreenCapture(ptr, x, y, width, height, data);
    }

}

