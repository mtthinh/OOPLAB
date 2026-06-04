/*
 * Copyright (c) 2014, 2022, Oracle and/or its affiliates. All rights reserved.
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
package com.sun.javafx.logging.jfr;

import com.sun.javafx.logging.Logger;
import com.sun.javafx.logging.PulseLogger;

import jdk.jfr.FlightRecorder;

public final class JFRPulseLogger extends Logger {
    private final ThreadLocal<JFRPulsePhaseEvent> currentPulsePhaseEvent;
    private final ThreadLocal<JFRInputEvent> currentInputEvent;

    private int pulseNumber;
    private int fxPulseNumber;
    private int renderPulseNumber;
    private Thread fxThread;

    public static Logger createInstance() {
        if (FlightRecorder.isInitialized() || PulseLogger.isPulseLoggingRequested()) {
            return new JFRPulseLogger();
        }
        return null;
    }

    private JFRPulseLogger() {
        FlightRecorder.register(JFRInputEvent.class);
        FlightRecorder.register(JFRPulsePhaseEvent.class);
        currentPulsePhaseEvent = new ThreadLocal<>() {
            @Override
            public JFRPulsePhaseEvent initialValue() {
                return new JFRPulsePhaseEvent();
            }
        };
        currentInputEvent = new ThreadLocal<>() {
            @Override
            public JFRInputEvent initialValue() {
                return new JFRInputEvent();
            }
        };
    }

    @Override
    public void pulseStart() {
        ++pulseNumber;
        fxPulseNumber = pulseNumber;
        if (fxThread == null) {
            fxThread = Thread.currentThread();
        }
        newPhase("Pulse start");
    }

    @Override
    public void pulseEnd() {
        newPhase(null);
        fxPulseNumber = 0;
    }

    @Override
    public void renderStart() {
        renderPulseNumber = fxPulseNumber;
    }

    @Override
    public void renderEnd() {
        newPhase(null);
        renderPulseNumber = 0;
    }

    /**
     * Finishes the current phase and starts a new one if phaseName is not null.
     *
     * @param phaseName The name for the new phase.
     */
    @Override
    public void newPhase(String phaseName) {
        JFRPulsePhaseEvent event = currentPulsePhaseEvent.get();

        /* Cleanup if no longer enabled */
        if (!event.isEnabled()) {
            event.setPhaseName(null);
            return;
        }

        /* If there is an ongoing event, commit it */
        if (event.getPhaseName() != null) {
            event.commit();
        }

        /* Done if the new phase name is null */
        if (phaseName == null) {
            event.setPhaseName(null);
            return;
        }

        event = new JFRPulsePhaseEvent();
        event.begin();
        event.setPhaseName(phaseName);
        event.setPulseId(Thread.currentThread() == fxThread ? fxPulseNumber : renderPulseNumber);
        currentPulsePhaseEvent.set(event);
    }

    @Override
    public void newInput(String input) {
        JFRInputEvent event = currentInputEvent.get();

        /* Cleanup if no longer enabled */
        if (!event.isEnabled()) {
            event.setInput(null);
            return;
        }

        /* If there is an ongoing event, commit it */
        if (event.getInput() != null) {
            event.commit();
        }

        /* Done if the new input is null */
        if (input == null) {
            event.setInput(null);
            return;
        }

        event = new JFRInputEvent();
        event.begin();
        event.setInput(input);
        currentInputEvent.set(event);
    }
}
