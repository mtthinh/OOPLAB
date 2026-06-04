/*
 * Copyright (c) 2013, 2019, Oracle and/or its affiliates. All rights reserved.
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
 * <p>The package {@code javafx.beans.property} defines read-only
 *     properties and writable properties, plus a number of implementations.
 * </p>
 * <h2>Read-only Properties</h2>
 * <p>Read-only properties have two getters, {@code get()} returns the
 *     primitive value, {@code getValue()} returns the boxed value.</p>
 * <p>It is possible to observe read-only properties for changes. They
 *     define methods to add and remove {@link
 *     javafx.beans.InvalidationListener InvalidationListeners} and {@link
 *     javafx.beans.value.ChangeListener ChangeListeners}.</p>
 * <p>To get the context of a read-only property, two methods {@code
 *     getBean()} and {@code getName()} are defined. They return the
 *     containing bean and the name of a property.</p>
 *
 * <h2>Writable Properties</h2>
 * <p>In addition to the functionality defined for read-only
 *     properties, writable properties contain the following methods.</p>
 * <p>A writable property defines two setters in addition to the
 *     getters defined for read-only properties. The setter {@code set()}
 *     takes a primitive value, the second setter {@code setValue()} takes
 *     the boxed value.</p>
 * <p>All properties can be bound to {@link
 *     javafx.beans.value.ObservableValue ObservableValues} of the same type,
 *     which means that the property will always contain the same value as
 *     the bound {@code ObservableValue}. It is also possible to define a
 *     bidirectional binding between two properties, so that both properties
 *     always contain the same value. If one of the properties changes, the
 *     other one will be updated.</p>
 */
package javafx.beans.property;
