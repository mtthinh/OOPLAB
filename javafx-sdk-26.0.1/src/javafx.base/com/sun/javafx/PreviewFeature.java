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

package com.sun.javafx;

import com.sun.javafx.runtime.VersionInfo;
import java.util.HashSet;
import java.util.Set;

/**
 * Using a preview feature requires an opt-in from application developers by specifying the
 * {@code javafx.enablePreview=true} system property. This class verifies that the application
 * has opted into preview features.
 */
public enum PreviewFeature {

    // Add preview feature constants here:
    // TEST_FEATURE("Test Feature")
    STAGE_STYLE_EXTENDED("StageStyle.EXTENDED"),
    HEADER_BAR("HeaderBar");

    PreviewFeature(String featureName) {
        this.featureName = featureName;
    }

    private final String featureName;

    private static final String ENABLE_PREVIEW_PROPERTY = "javafx.enablePreview";
    private static final String SUPPRESS_WARNING_PROPERTY = "javafx.suppressPreviewWarning";

    private static final boolean enabled = Boolean.getBoolean(ENABLE_PREVIEW_PROPERTY);
    private static final boolean suppressWarning = Boolean.getBoolean(SUPPRESS_WARNING_PROPERTY);
    private static final Set<PreviewFeature> enabledFeatures = new HashSet<>();

    /**
     * Verifies that preview features are enabled, and throws an exception otherwise.
     * <p>
     * Unless suppressed with the {@code javafx.suppressPreviewWarning=true} system property, this method
     * prints a one-time warning to the error output stream for every feature for which it is called.
     *
     * @throws RuntimeException if preview features are not enabled
     */
    public void checkEnabled() {
        if (!enabled) {
            throw new RuntimeException("""
                %s is a preview feature of JavaFX %s.
                Preview features may be removed in a future release, or upgraded to permanent features of JavaFX.
                Programs can only use preview features when the following system property is set: -D%s=true
                """.formatted(featureName, VersionInfo.getVersion(), ENABLE_PREVIEW_PROPERTY));
        } else if (!suppressWarning && enabledFeatures.add(this)) {
            System.err.printf("""
                Note: This program uses the following preview feature of JavaFX %s: %s
                      Preview features may be removed in a future release, or upgraded to permanent features of JavaFX.
                      This warning can be disabled with the following system property: -D%s=true
                """, VersionInfo.getVersion(), featureName, SUPPRESS_WARNING_PROPERTY);
        }
    }
}
