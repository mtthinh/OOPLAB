/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
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
// Original code is re-licensed to Oracle by the author.
// https://github.com/andy-goryachev/FxTextEditor/blob/master/src/goryachev/common/util/Disconnectable.java
// Copyright © 2021-2022 Andy Goryachev <andy@goryachev.com>
package com.sun.javafx.scene.control;

/**
 * A functional interface that provides a {@link #disconnect()} method.
 */
@FunctionalInterface
public interface IDisconnectable {
    /**
     * Disconnects what has been connected. May be called multiple times, only the
     * first invocation actually disconnects.
     */
    public void disconnect();
}
