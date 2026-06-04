/*
 * Copyright (c) 2011, 2021, Oracle and/or its affiliates. All rights reserved.
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

import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

/**
 * Converter to convert a {@code String} to a {@code Boolean}.
 * @since 9
 */
public final class BooleanConverter extends StyleConverter<String, Boolean> {

    // lazy, thread-safe instatiation
    private static class Holder {
        static final BooleanConverter INSTANCE = new BooleanConverter();
    }

    /**
     * Get the {@code BooleanConverter} instance.
     * @return the {@code BooleanConverter} instance
     */
    public static StyleConverter<String, Boolean> getInstance() {
        return Holder.INSTANCE;
    }

    private BooleanConverter() {
        super();
    }

    @Override
    public Boolean convert(ParsedValue<String, Boolean> value, Font not_used) {
        String str = value.getValue();
        return Boolean.valueOf(str);
    }

    @Override
    public String toString() {
        return "BooleanConverter";
    }
}
