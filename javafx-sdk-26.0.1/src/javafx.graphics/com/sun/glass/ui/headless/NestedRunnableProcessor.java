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

import com.sun.glass.ui.Application;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class NestedRunnableProcessor implements Runnable {

    private final LinkedList<RunLoopEntry> activeRunLoops = new LinkedList<>();

    private final BlockingQueue<Runnable> runnableQueue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        newRunLoop();
    }

    void invokeLater(final Runnable r) {
        runnableQueue.add(r);
    }

    void invokeAndWait(final Runnable r) {
        final CountDownLatch latch = new CountDownLatch(1);
        runnableQueue.add(() -> {
            try {
                r.run();
            } finally {
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            Application.reportException(e);
        }
    }

    void stopProcessing() {
        for (RunLoopEntry entry : activeRunLoops) {
            runnableQueue.add(() -> entry.active = false);
        }
    }

    public Object newRunLoop() {
        RunLoopEntry entry = new RunLoopEntry();

        activeRunLoops.push(entry);

        entry.active = true;
        while (entry.active) {
            try {
                runnableQueue.take().run();
            } catch (Throwable e) {
                Application.reportException(e);
            }
        }
        return entry.returnValue;
    }

    public void leaveCurrentLoop(Object returnValue) {
        RunLoopEntry entry = activeRunLoops.pop();
        entry.active = false;
        entry.returnValue = returnValue;
    }

    private static class RunLoopEntry {

        // This is only accessed on the event thread
        boolean active;

        Object returnValue;
    }

}
