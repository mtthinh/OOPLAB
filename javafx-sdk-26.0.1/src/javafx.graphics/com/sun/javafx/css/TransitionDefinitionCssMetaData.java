/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.css;

import com.sun.javafx.scene.NodeHelper;
import javafx.animation.Interpolator;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.css.converter.DurationConverter;
import javafx.css.converter.StringConverter;
import javafx.scene.Node;
import javafx.util.Duration;
import java.util.List;

/**
 * An implementation of {@link CssMetaData} for the {@code transition} property that includes the
 * four sub-properties {@code transition-property}, {@code transition-duration}, {@code transition-delay}
 * and {@code transition-timing-function}.
 */
public class TransitionDefinitionCssMetaData extends CssMetaData<Node, TransitionDefinition[]> {

    private static class Holder {
        static final TransitionDefinitionCssMetaData INSTANCE = new TransitionDefinitionCssMetaData();
    }

    public static TransitionDefinitionCssMetaData getInstance() {
        return Holder.INSTANCE;
    }

    public TransitionDefinitionCssMetaData() {
        super("transition", TransitionDefinitionConverter.SequenceConverter.getInstance(),
              new TransitionDefinition[0], false, createSubProperties());
    }

    private static final String[] PROPERTY_ALL = new String[] { TransitionDefinitionConverter.PROPERTY_ALL };

    private static final Duration[] DURATION_ZERO = new Duration[] { Duration.ZERO };

    private static final Interpolator[] INTERPOLATOR_EASE = new Interpolator[] { InterpolatorConverter.CSS_EASE };

    @Override
    public boolean isSettable(Node node) {
        return true;
    }

    @Override
    public StyleableProperty<TransitionDefinition[]> getStyleableProperty(Node node) {
        return NodeHelper.getTransitionProperty(node);
    }

    private static <S extends Styleable> List<CssMetaData<? extends Styleable, ?>> createSubProperties() {
        return List.of(
            new CssMetaData<S, String[]>("transition-property",
                    StringConverter.SequenceConverter.getInstance(), PROPERTY_ALL, false) {
                @Override
                public boolean isSettable(S styleable) {
                    return false;
                }

                @Override
                public StyleableProperty<String[]> getStyleableProperty(S styleable) {
                    return null;
                }
            },
            new CssMetaData<S, Duration[]>("transition-duration",
                    DurationConverter.SequenceConverter.getInstance(), DURATION_ZERO, false) {
                @Override
                public boolean isSettable(S styleable) {
                    return false;
                }

                @Override
                public StyleableProperty<Duration[]> getStyleableProperty(S styleable) {
                    return null;
                }
            },
            new CssMetaData<S, Duration[]>("transition-delay",
                    DurationConverter.SequenceConverter.getInstance(), DURATION_ZERO, false) {
                @Override
                public boolean isSettable(S styleable) {
                    return false;
                }

                @Override
                public StyleableProperty<Duration[]> getStyleableProperty(S styleable) {
                    return null;
                }
            },
            new CssMetaData<S, Interpolator[]>("transition-timing-function",
                    InterpolatorConverter.SequenceConverter.getInstance(), INTERPOLATOR_EASE, false) {
                @Override
                public boolean isSettable(S styleable) {
                    return false;
                }

                @Override
                public StyleableProperty<Interpolator[]> getStyleableProperty(S styleable) {
                    return null;
                }
            }
        );
    }

}
