/*
 * Copyright (c) 2012, 2023, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.binding;

import com.sun.javafx.logging.PlatformLogger;

public class Logging {

    private static boolean keepException = false;

    /**
     * This is only used for testing purposes.
     * @param keepException
     */
    public static void setKeepException(boolean keepException) {
        Logging.keepException = keepException;
    }

    /**
     * This is only used for testing purposes.
     * @return
     */
    public static boolean getKeepException() {
        return keepException;
    }

    public static ErrorLogger getLogger() {
        return ErrorLogger.INSTANCE;
    }

    /**
     * A PlatformLogger that keeps a record ({@code ErrorLogRecord}) of the last error ({@code Throwable}) logged.
     */
    public static class ErrorLogger extends PlatformLogger {

        ErrorLogger() {
            super(System.getLogger("javafx.beans"));
        }

        private static final ErrorLogger INSTANCE = new ErrorLogger();

        public static class ErrorLogRecord {
            private final Level level;
            private final Throwable thrown;

            public ErrorLogRecord(Level level, Throwable thrown) {
                this.level = level;
                if (Logging.keepException) {
                    this.thrown = thrown;
                } else {
                    this.thrown = null;
                }
            }

            public Throwable getThrown() {
                return thrown;
            }

            public Level getLevel() {
                return level;
            }
        }

        private ErrorLogRecord errorLogRecord;

        public ErrorLogRecord getErrorLogRecord() {
            return errorLogRecord;
        }

        public void setErrorLogRecord(ErrorLogRecord errorLogRecord) {
            this.errorLogRecord = errorLogRecord;
        }

        /* Some of the following logging methods are unused and thus commented-out,
           but are kept here anyway on purpose. See JDK-8195974 */

/*        @Override
        public void severe(String msg, Throwable t) {
            super.severe(msg, t);
            errorLogRecord = new ErrorLogRecord(Level.SEVERE, t);
        }*/

        @Override
        public void warning(String msg, Throwable t) {
            super.warning(msg, t);
            errorLogRecord = new ErrorLogRecord(Level.WARNING, t);
        }

/*        @Override
        public void info(String msg, Throwable t) {
            super.info(msg, t);
            errorLogRecord = new ErrorLogRecord(Level.INFO, t);
        }*/

        @Override
        public void fine(String msg, Throwable t) {
            super.fine(msg, t);
            errorLogRecord = new ErrorLogRecord(Level.FINE, t);
        }

/*        @Override
        public void finer(String msg, Throwable t) {
            super.finer(msg, t);
            errorLogRecord = new ErrorLogRecord(Level.FINER, t);
        }*/

/*        @Override
        public void finest(String msg, Throwable t) {
            super.finest(msg, t);
            errorLogRecord = new ErrorLogRecord(Level.FINEST, t);
        }*/
    }
}
