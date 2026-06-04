/*
 * Copyright (c) 2008, 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.runtime.async;

/**
 * AsyncFunctionListener
*
*/
public interface AsyncOperationListener<V> {
    public void onProgress(long progressValue, long progressMax);
    public void onCompletion(V value);
    public void onCancel();
    public void onException(Exception e);
}
