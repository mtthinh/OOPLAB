/*
 * Copyright (c) 2023, 2024, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Provides common models for
 * {@link jfx.incubator.scene.control.richtext.RichTextArea RichTextArea} and
 * {@link jfx.incubator.scene.control.richtext.CodeArea CodeArea} controls.
 * <p>
 * The {@link jfx.incubator.scene.control.richtext.RichTextArea RichTextArea}
 * control separates data model from the view by providing the
 * {@link jfx.incubator.scene.control.richtext.RichTextArea#modelProperty() model} property.
 * The base class for any data model is
 * {@link jfx.incubator.scene.control.richtext.model.StyledTextModel StyledTextModel}.
 * This abstract class provides no data storage, focusing instead on providing common functionality
 * to the actual models, such as dealing with styled segments, keeping track of markers, sending events, etc.
 * <p>
 * This package provides a number of standard models are provided, each designed for a specific use case.
 * <ul>
 * <li>The {@link jfx.incubator.scene.control.richtext.model.RichTextModel RichTextModel} stores the data in memory,
 *     in the form of text segments styled with attributes defined in
 *     {@link jfx.incubator.scene.control.richtext.model.StyleAttributeMap StyleAttributeMap} class.
 *     This is a default model for RichTextArea.
 * <li>The {@link jfx.incubator.scene.control.richtext.model.BasicTextModel BasicTextModel}
 *     could be used as a base class for in-memory or virtualized text models based on plain text.
 *     This class provides foundation for the
 *     {@link jfx.incubator.scene.control.richtext.model.CodeTextModel CodeTextModel},
 *     which supports styling using a pluggable
 *     {@link jfx.incubator.scene.control.richtext.SyntaxDecorator SyntaxDecorator}.
 * <li>The abstract
 *     {@link jfx.incubator.scene.control.richtext.model.StyledTextModelViewOnlyBase StyledTextModelViewOnlyBase}
 *     is a base class for immutable models.  This class is used by
 *     {@link jfx.incubator.scene.control.richtext.model.SimpleViewOnlyStyledModel SimpleViewOnlyStyledModel}
 *     which simplifies building of in-memory view-only styled documents.
 * </ul>
 * <b><a href="https://openjdk.org/jeps/11">Incubating Feature.</a>
 * Will be removed in a future release.</b>
 *
 * @since 24
 */
package jfx.incubator.scene.control.richtext.model;
