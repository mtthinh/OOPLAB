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

package javafx.animation;

import com.sun.javafx.tk.Toolkit;
import com.sun.javafx.util.Utils;
import com.sun.scenario.animation.AbstractPrimaryTimer;
import com.sun.scenario.animation.shared.TimerReceiver;

/**
 * The class {@code AnimationTimer} allows to create a timer, that is called in
 * each frame while it is active.
 * <p>
 * An extending class has to override the method {@link #handle(long)} which
 * will be called in every frame.
 * <p>
 * The methods {@link AnimationTimer#start()} and {@link #stop()} allow to start
 * and stop the timer.
 * <p>
 * The animation timer runs on the JavaFX Application Thread.
 *
 * @since JavaFX 2.0
 */
public abstract class AnimationTimer {

    private class AnimationTimerReceiver implements TimerReceiver {
        @Override public void handle(final long now) {
            AnimationTimer.this.handle(now);
        }
    }

    private final AbstractPrimaryTimer timer;
    private final AnimationTimerReceiver timerReceiver = new AnimationTimerReceiver();
    private boolean active;

    /**
     * Creates a new timer.
     */
    public AnimationTimer() {
        timer = Toolkit.getToolkit().getPrimaryTimer();
    }

    // For testing only
    AnimationTimer(AbstractPrimaryTimer timer) {
        this.timer = timer;
    }

    /**
     * This method needs to be overridden by extending classes. It is going to
     * be called in every frame while the {@code AnimationTimer} is active.
     *
     * @param now
     *            The timestamp of the current frame given in nanoseconds. This
     *            value will be the same for all {@code AnimationTimers} called
     *            during one frame.
     */
    public abstract void handle(long now);

    /**
     * Starts the {@code AnimationTimer}. Once it is started, the
     * {@link #handle(long)} method of this {@code AnimationTimer} will be
     * called in every frame.
     * <p>
     * The {@code AnimationTimer} can be stopped by calling {@link #stop()}.
     * <p>
     * Note: if this method is not called on the JavaFX Application Thread, it is delegated to it automatically.
     * In this case, the call is asynchronous and may not happen immediately.
     */
    public void start() {
        Utils.runOnFxThread(this::startImpl);
    }

    /**
     * This method must be run on the JavaFX Application Thread.
     *
     * @see #start()
     */
    private void startImpl() {
        if (!active) {
            timer.addAnimationTimer(timerReceiver);
            active = true;
        }
    }

    /**
     * Stops the {@code AnimationTimer}. It can be activated again by calling
     * {@link #start()}.
     * <p>
     * Note: if this method is not called on the JavaFX Application Thread, it is delegated to it automatically.
     * In this case, the call is asynchronous and may not happen immediately.
     */
    public void stop() {
        Utils.runOnFxThread(this::stopImpl);
    }

    /**
     * This method must be run on the JavaFX Application Thread.
     *
     * @see #stop()
     */
    private void stopImpl() {
        if (active) {
            timer.removeAnimationTimer(timerReceiver);
            active = false;
        }
    }
}
