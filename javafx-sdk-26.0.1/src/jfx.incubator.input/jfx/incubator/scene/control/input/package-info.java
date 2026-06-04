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
 * <p>Incubates a customization mechanism for the JavaFX Controls utilizing the
 * {@link jfx.incubator.scene.control.input.InputMap InputMap}.
 * <p>
 * The {@code InputMap}
 * <ul>
 * <li>allows for customization of a control behavior by changing the existing or adding new key mappings
 * <li>supports dynamic modification of the key mappings
 * <li>allows for accessing the default functionality even when it was overwritten by the application
 * <li>allows for reverting customization to the default implementation
 * <li>guarantees priorities between the application and the skin event handlers and key mappings
 * <li>allows for gradual migration of the existing controls to use the InputMap
 * </ul>
 * <p>
 * <b><a href="https://openjdk.org/jeps/11">Incubating Feature.</a>
 * Will be removed in a future release.</b>
 *
 * @since 24
 */
package jfx.incubator.scene.control.input;
