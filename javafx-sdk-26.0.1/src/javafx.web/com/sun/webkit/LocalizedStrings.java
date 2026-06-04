/*
 * Copyright (c) 2011, 2018, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.webkit;

import com.sun.javafx.logging.PlatformLogger;
import java.util.Locale;
import java.util.ResourceBundle;

final class LocalizedStrings {
    private final static PlatformLogger log =
            PlatformLogger.getLogger(LocalizedStrings.class.getName());

    private final static ResourceBundle BUNDLE =
        ResourceBundle.getBundle("com.sun.webkit.LocalizedStrings",
            Locale.getDefault());

    /** Private ctor to avoid unexpected instantiation */
    private LocalizedStrings() {}

    private static String getLocalizedProperty(String propName) {
        log.fine("Get property: " + propName);
        String propValue = BUNDLE.getString(propName);
        if ((propValue != null) && (propValue.trim().length() > 0)) {
            log.fine("Property value: " + propValue);
            return propValue.trim();
        }
        log.fine("Unknown property value");
        return null;
    }
}
