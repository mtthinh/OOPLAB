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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.util.StringConverter;

/**
 * A {@link StringConverter} implementation for {@link Number} values that represent currency. Instances of this class are immutable.
 *
 * @see PercentageStringConverter
 * @see NumberStringConverter
 * @see StringConverter
 * @since JavaFX 2.1
 */
public class CurrencyStringConverter extends NumberStringConverter {

    /**
     * Constructs a {@code CurrencyStringConverter} with the default locale and format.
     */
    public CurrencyStringConverter() {
        this(Locale.getDefault());
    }

    /**
     * Constructs a {@code CurrencyStringConverter} with the given locale and the default format.
     *
     * @param locale the locale used in determining the number format used to format the string
     */
    public CurrencyStringConverter(Locale locale) {
        this(locale, null);
    }

    /**
     * Constructs a {@code CurrencyStringConverter} with the default locale and the given decimal format pattern.
     *
     * @param pattern the string pattern used in determining the number format used to format the string
     *
     * @see java.text.DecimalFormat
     */
    public CurrencyStringConverter(String pattern) {
        this(Locale.getDefault(), pattern);
    }

    /**
     * Constructs a {@code CurrencyStringConverter} with the given locale and decimal format pattern.
     *
     * @param locale the locale used in determining the number format used to format the string
     * @param pattern the string pattern used in determining the number format used to format the string
     *
     * @see java.text.DecimalFormat
     */
    public CurrencyStringConverter(Locale locale, String pattern) {
        super(locale, pattern, null);
    }

    /**
     * Constructs a {@code CurrencyStringConverter} with the given number format.
     *
     * @param numberFormat the number format used to format the string
     */
    public CurrencyStringConverter(NumberFormat numberFormat) {
        super(null, null, numberFormat);
    }

    /**
    * @deprecated This method was exposed erroneously and will be removed in a future version.
    */
    @Deprecated(forRemoval = true, since = "22")
    @SuppressWarnings("removal")
    @Override
    protected NumberFormat getNumberFormat() {
        Locale _locale = locale == null ? Locale.getDefault() : locale;

        if (numberFormat != null) {
            return numberFormat;
        } else if (pattern != null) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(_locale);
            return new DecimalFormat(pattern, symbols);
        } else {
            return NumberFormat.getCurrencyInstance(_locale);
        }
    }
}
