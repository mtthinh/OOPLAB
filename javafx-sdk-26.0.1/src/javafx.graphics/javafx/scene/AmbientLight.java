/*
 * Copyright (c) 2013, 2024, Oracle and/or its affiliates. All rights reserved.
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

package javafx.scene;

import com.sun.javafx.scene.AmbientLightHelper;
import com.sun.javafx.sg.prism.NGAmbientLight;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.paint.Color;

/**
 * A light that illuminates an object from all directions equally regardless of its position and orientation. An
 * {@code AmbientLight} adds a constant term to the amount of light reflected by each point on the surface of an object,
 * thereby increasing the brightness of the object uniformly.
 * <p>
 * {@code AmbientLight}s are often used to represent the base amount of illumination in a scene. In the real world,
 * light gets reflected off of surfaces, causing areas that are not in direct line-of-sight of the light to be lit (more
 * dimly). Using a dark colored (weak) {@code AmbientLight} can achieve the effect of the lighting of those areas.
 *
 * @since JavaFX 8.0
 */
public non-sealed class AmbientLight extends LightBase {
    static {
        AmbientLightHelper.setAmbientLightAccessor(new AmbientLightHelper.AmbientLightAccessor() {
            @Override
            public NGNode doCreatePeer(Node node) {
                return ((AmbientLight) node).doCreatePeer();
            }
        });
    }

    {
        // To initialize the class helper at the beginning each constructor of this class
        AmbientLightHelper.initHelper(this);
    }

    /**
     * Creates a new instance of {@code AmbientLight} class with a default Color.WHITE light source.
     */
    public AmbientLight() {
        super();
    }

    /**
     * Creates a new instance of {@code AmbientLight} class using the specified color.
     *
     * @param color the color of the light source
     */
    public AmbientLight(Color color) {
        super(color);
    }

    /*
     * Note: This method MUST only be called via its accessor method.
     */
    private NGNode doCreatePeer() {
        return new NGAmbientLight();
    }
}
