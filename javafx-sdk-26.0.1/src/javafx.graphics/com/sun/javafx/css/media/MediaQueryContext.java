/*
 * Copyright (c) 2025, 2026, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.css.media;

import com.sun.javafx.scene.SceneContext;
import javafx.application.ColorScheme;

/**
 * A media query is evaluated against a {@code MediaQueryContext}, which provides the media feature
 * values that can be referenced in a media query expression.
 */
public sealed interface MediaQueryContext permits SceneContext {

    /**
     * Provides the value for the {@code prefers-color-scheme} media feature.
     *
     * @return the color scheme
     */
    ColorScheme getColorScheme();

    /**
     * Provides the value for the {@code prefers-reduced-motion} media feature.
     *
     * @return {@code true} if the application should reduce motion
     */
    boolean isReducedMotion();

    /**
     * Provides the value for the {@code prefers-reduced-transparency} media feature.
     *
     * @return {@code true} if the application should reduce transparency
     */
    boolean isReducedTransparency();

    /**
     * Provides the value for the {@code prefers-reduced-data} media feature.
     *
     * @return {@code true} if the application should reduce internet traffic
     */
    boolean isReducedData();

    /**
     * Provides the value for the {@code -fx-prefers-persistent-scrollbars} media feature.
     *
     * @return {@code true} if the application should use persistent scroll bars
     */
    boolean isPersistentScrollBars();

    /**
     * Provides the value for the {@code display-mode} media feature.
     *
     * @return {@code true} if the application is in full-screen mode
     */
    boolean isFullScreen();

    /**
     * Provides the value for the {@code width} media feature.
     *
     * @return the width
     */
    double getWidth();

    /**
     * Provides the value for the {@code height} media feature.
     *
     * @return the height
     */
    double getHeight();

    /**
     * Notifies the media query context that a query has been evaluated and provides its current value.
     * <p>
     * The context can then re-evaluate the query at its own discretion and compare the evaluated value with
     * the stored value to determine if it is necessary to re-apply CSS. This is an optimization to minimize
     * CSS invalidation. For example, range-based queries like "width" and "height" can change very often when
     * the scene is resized (but the evaluated value doesn't change often in a query like "width < 500").
     * If not for this optimization, we would have to speculatively re-apply CSS on every resize just to catch
     * the case when a range-based query changes its value.
     *
     * @param query the query
     * @param currentValue the query value
     */
    void notifyQueryEvaluated(MediaQuery query, boolean currentValue);
}
