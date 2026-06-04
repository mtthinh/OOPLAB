/*
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates. All rights reserved.
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

package javafx.css.converter;

import javafx.css.Size;
import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Converter to Convert a {@code Size} to {@code Duration}.
 *
 * @since 9
 */
public final class DurationConverter extends StyleConverter<ParsedValue<?, Size>, Duration> {

    // lazy, thread-safe instatiation
    private static class Holder {
        static final DurationConverter INSTANCE = new DurationConverter();
        static final SequenceConverter SEQUENCE_INSTANCE = new SequenceConverter();
    }

    /**
     * Gets the {@code DurationConverter} instance.
     * @return the {@code DurationConverter} instance
     */
    public static StyleConverter<ParsedValue<?, Size>, Duration> getInstance() {
        return Holder.INSTANCE;
    }

    private DurationConverter() {
        super();
    }

    @Override
    public Duration convert(ParsedValue<ParsedValue<?, Size>, Duration> value, Font font) {
        ParsedValue<?, Size> parsedValue = value.getValue();
        Size size = parsedValue.convert(font);
        double time = size.getValue();
        Duration duration = null;
        if (time < Double.POSITIVE_INFINITY) {
            switch (size.getUnits()) {
                case S:  duration = Duration.seconds(time); break;
                case MS: duration = Duration.millis(time);  break;
                default: duration = Duration.UNKNOWN;
            }
        } else {
            duration = Duration.INDEFINITE;
        }
        return duration;
    }

    @Override
    public String toString() {
        return "DurationConverter";
    }

    /**
     * Converts a sequence of parsed values to an array of {@link Duration} instances.
     *
     * @since 23
     */
    public static final class SequenceConverter extends StyleConverter<ParsedValue<ParsedValue<?, Size>, Duration>[], Duration[]> {
        /**
         * Gets the {@code SequenceConverter} instance.
         * @return the {@code SequenceConverter} instance
         */
        public static SequenceConverter getInstance() {
            return Holder.SEQUENCE_INSTANCE;
        }

        private SequenceConverter() {}

        @Override
        public Duration[] convert(
                ParsedValue<ParsedValue<ParsedValue<?, Size>, Duration>[], Duration[]> value, Font font) {
            ParsedValue<?, Duration>[] values = value.getValue();
            Duration[] durations = new Duration[values.length];
            for (int p = 0; p < values.length; p++) {
                durations[p] = values[p].convert(font);
            }

            return durations;
        }

        @Override
        public String toString() {
            return "Duration.SequenceConverter";
        }
    }

}
