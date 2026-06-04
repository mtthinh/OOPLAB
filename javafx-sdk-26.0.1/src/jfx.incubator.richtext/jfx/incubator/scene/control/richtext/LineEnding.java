/*
 * Copyright (c) 2025, Oracle and/or its affiliates. All rights reserved.
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

package jfx.incubator.scene.control.richtext;

/**
 * Specifies line separator (line ending) characters.
 *
 * @since 26
 */
public enum LineEnding {
    /** Legacy Mac OS line ending, ASCII CR (0x0d). */
    CR,
    /** Windows line ending, sequence of CR/LF (0x0d 0x0a). */
    CRLF,
    /** macOS/Unix line ending, ASCII LF (0x0a). */
    LF;

    private static final LineEnding system = init();

    /**
     * Returns the line ending as a {@code String}.
     * @return the line ending string
     */
    public String getText() {
        return switch(this) {
            case CR -> "\r";
            case CRLF -> "\r\n";
            case LF -> "\n";
        };
    }

    /**
     * Returns the {@code LineEnding} based on the value of system line separator string
     * {@link System#lineSeparator()}.
     * @return the system default line ending
     */
    public static LineEnding system() {
        return system;
    }

    private static LineEnding init() {
        String s = System.lineSeparator();
        if (s != null) {
            return switch (s) {
                case "\r" -> CR;
                case "\r\n" -> CRLF;
                case "\n" -> LF;
                default -> LF;
            };
        }
        return LineEnding.LF;
    }
}
