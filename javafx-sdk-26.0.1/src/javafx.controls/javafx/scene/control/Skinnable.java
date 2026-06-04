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

package javafx.scene.control;

import javafx.beans.property.ObjectProperty;

/**
 * <p>
 * The Skinnable interface is implemented by the {@link Control} class,
 * and therefore is implemented by all Control implementations.
 *
 * @since JavaFX 2.0
 */
public interface Skinnable {
    /**
     * The {@code Skin} responsible for rendering this {@code Skinnable}. From the
     * perspective of the {@code Skinnable}, the {@code Skin} is a black box.
     * It listens and responds to changes in state of its {@code Skinnable}.
     * <p>
     * Some implementations of {@code Skinnable} define a one-to-one relationship between {@code Skinnable}
     * and its {@code Skin}. Every {@code Skin} maintains a back reference to the
     * {@code Skinnable}.  When required, this relationship is enforced when the {@code Skin} is set,
     * throwing an {@code IllegalArgumentException} if the return value of {@link Skin#getSkinnable()}
     * is not the same as this {@code Skinnable}.
     * <p>
     * A skin may be null.
     *
     * @return the skin property for this Skinnable
     */
    public ObjectProperty<Skin<?>> skinProperty();

    @SuppressWarnings("doclint:missing")
    public void setSkin(Skin<?> value);

    @SuppressWarnings("doclint:missing")
    public Skin<?> getSkin();
}
