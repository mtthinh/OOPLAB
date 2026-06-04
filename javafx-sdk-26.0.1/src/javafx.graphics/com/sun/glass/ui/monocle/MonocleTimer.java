/*
 * Copyright (c) 2011, 2020, Oracle and/or its affiliates. All rights reserved.
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

import com.sun.glass.ui.Timer;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Monocle implementation class for Timer.
 */
final class MonocleTimer extends Timer {
    private static final String THREAD_NAME = "Monocle Timer";

    private static ScheduledThreadPoolExecutor scheduler;
    private ScheduledFuture<?> task;

    MonocleTimer(final Runnable runnable) {
        super(runnable);
    }

    static int getMinPeriod_impl() {
        return 0;
    }

    static int getMaxPeriod_impl() {
        return 1000000;
    }

    @Override protected long _start(final Runnable runnable, int period) {
        if (scheduler == null) {
            scheduler = new ScheduledThreadPoolExecutor(1, target -> {
                Thread thread = new Thread(target, THREAD_NAME);
                thread.setDaemon(true);
                return thread;
            });
        }

        task = scheduler.scheduleAtFixedRate(runnable, 0, period, TimeUnit.MILLISECONDS);
        return 1; // need something non-zero to denote success.
    }

    @Override protected long _start(Runnable runnable) {
        throw new RuntimeException("vsync timer not supported");
    }

    @Override protected void _stop(long timer) {
        if (task != null) {
            task.cancel(false);
            task = null;
        }
    }

    @Override protected void _pause(long timer) {}
    @Override protected void _resume(long timer) {}
}

