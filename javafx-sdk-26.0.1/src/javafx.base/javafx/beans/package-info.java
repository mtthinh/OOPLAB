/*
 * Copyright (c) 2013, 2017, Oracle and/or its affiliates. All rights reserved.
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
 * <p>The package {@code javafx.beans} contains the interfaces that
 *     define the most generic form of observability. All other classes in
 *     the JavaFX library, that are observable, extend the {@link javafx.beans.Observable}
 *     interface.</p>
 * <p>An implementation of {@code Observable} allows to attach an
 *     {@link javafx.beans.InvalidationListener}. The contentBinding gets notified every time
 *     the {@code Observable} may have changed. Typical implementations of
 *     {@code Observable} are all properties, all bindings, {@link
 *     javafx.collections.ObservableList}, and {@link
 *     javafx.collections.ObservableMap}.</p>
 * <p>An {@code InvalidationListener} will get no further information,
 *     e.g. it will not get the old and the new value of a property. If you
 *     need more information consider using a {@link
 *     javafx.beans.value.ChangeListener} for properties and bindings, {@link
 *     javafx.collections.ListChangeListener} for {@code ObservableLists} or
 *     {@link javafx.collections.MapChangeListener} for {@code ObservableMap}
 *     instead.</p>
 */
package javafx.beans;
