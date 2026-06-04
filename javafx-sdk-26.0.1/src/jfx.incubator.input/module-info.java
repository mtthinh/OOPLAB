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

/**
 * Incubates the mechanism for customization of JavaFX controls using the
 * {@link jfx.incubator.scene.control.input.InputMap InputMap}.
 * <p>
 * <b><a href="https://openjdk.org/jeps/11">Incubating Feature.</a>
 * Will be removed in a future release.</b>
 *
 * @moduleGraph
 * @since 24
 */
module jfx.incubator.input {
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;

    exports jfx.incubator.scene.control.input;

    // becomes unnecessary once InputMap is moved to Control JDK-8314968
    exports com.sun.jfx.incubator.scene.control.input to jfx.incubator.richtext;
}
