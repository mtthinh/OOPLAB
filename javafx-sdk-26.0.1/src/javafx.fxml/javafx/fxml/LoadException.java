/*
 * Copyright (c) 2010, 2021, Oracle and/or its affiliates. All rights reserved.
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

package javafx.fxml;

import java.io.IOException;

/**
 * Thrown when an error is encountered during a load operation.
 * @since JavaFX 2.0
 */
public class LoadException extends IOException {
    private static final long serialVersionUID = 0;

    /**
     * Creates a default {@code LoadException}.
     */
    public LoadException() {
        super();
    }

    /**
     * Creates a {@code LoadException} with the given message.
     * @param message the message
     */
    public LoadException(String message) {
        super(message);
    }

    /**
     * Creates a {@code LoadException} with the given {@code Throwable} cause.
     * @param cause the {@code Throwable} cause
     */
    public LoadException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a {@code LoadException} with the given message and {@code Throwable} cause.
     * @param message the message
     * @param cause the {@code Throwable} cause
     */
    public LoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
