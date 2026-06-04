/*
 * Copyright (c) 2020, 2022, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.scenario.animation.shared;

import javafx.animation.Animation;

/**
 * Clip envelope for multi-cycle animations. In this case, autoReverse and cyclePosition (which can be different from ticks)
 * are important.
 */
abstract class MultiLoopClipEnvelope extends ClipEnvelope {

    protected boolean autoReverse;

    /**
     * The current position of the play head in its current cycle.
     * cyclePos = ticks % cycleTicks, so 0 <= cyclePos <= cycleTicks.
     */
    protected long cyclePos;

    protected MultiLoopClipEnvelope(Animation animation) {
        super(animation);
    }

    protected boolean isAutoReverse() {
        return autoReverse;
    }

    @Override
    public void setAutoReverse(boolean autoReverse) {
        this.autoReverse = autoReverse;
    }

    @Override
    protected long ticksRateChange(double newRate) {
        return Math.round((ticks - deltaTicks) * Math.abs(newRate / rate));
     }

    protected boolean isDirectionChanged(double newRate) {
        return newRate * rate < 0;
    }

    protected boolean isDuringEvenCycle() {
        return ticks % (2 * cycleTicks) < cycleTicks;
    }
}
