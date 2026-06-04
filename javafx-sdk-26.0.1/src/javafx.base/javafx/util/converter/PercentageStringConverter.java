/*
 * Copyright (c) 2010, 2024, Oracle and/or its affiliates. All rights reserved.
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

package javafx.util.converter;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.util.StringConverter;

/**
 * A {@link StringConverter} implementation for {@link Number} values that represent percentages. Instances of this class are
 * immutable.
 *
 * @see CurrencyStringConverter
 * @see NumberStringConverter
 * @see StringConverter
 * @since JavaFX 2.1
 */
public class PercentageStringConverter extends NumberStringConverter {

    /**
     * Constructs a {@code PercentageStringConverter} with the default locale and format.
     */
    public PercentageStringConverter() {
        this(Locale.getDefault());
    }

    /**
     * Constructs a {@code PercentageStringConverter} with the given locale and the default format.
     *
     * @param locale the locale used in determining the number format used to format the string
     */
    public PercentageStringConverter(Locale locale) {
        super(locale, null, null);
    }

    /**
     * Constructs a {@code PercentageStringConverter} with the given number format.
     *
     * @param numberFormat the number format used to format the string
     */
    public PercentageStringConverter(NumberFormat numberFormat) {
        super(null, null, numberFormat);
    }

    /**
     * @deprecated This method was exposed erroneously and will be removed in a future version.
     */
    @Deprecated(forRemoval = true, since = "22")
    @SuppressWarnings("removal")
    @Override
    public NumberFormat getNumberFormat() {
        Locale _locale = locale == null ? Locale.getDefault() : locale;

        if (numberFormat != null) {
            return numberFormat;
        } else {
            return NumberFormat.getPercentInstance(_locale);
        }
    }
}
