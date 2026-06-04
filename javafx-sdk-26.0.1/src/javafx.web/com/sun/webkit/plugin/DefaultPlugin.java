/*
 * Copyright (c) 2011, 2022, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.webkit.plugin;

import java.io.IOError;
import java.net.URL;

import com.sun.prism.paint.Color;
import com.sun.webkit.graphics.WCGraphicsContext;


final class DefaultPlugin implements Plugin {

    //private JLabel nullComp;

    private void init(final String pluginDetails) {
        //nullComp = new JLabel(pluginDetails);
        //nullComp.setHorizontalAlignment(JLabel.CENTER);
        //nullComp.setVisible(true);
    }

    DefaultPlugin(URL url, String type, String[] pNames, String[] pValues) {
        init("Default Plugin for: " + (null==url ? "(null)" : url.toExternalForm()));
    }

    @Override
    public void paint(WCGraphicsContext g, int intX, int intY, int intWidth, int intHeight)
    {
        //if(g instanceof  WCGraphics2DContext){
            //nullComp.paint( ((WCGraphics2DContext)g).getImageGraphics() );
        //}
        g.fillRect(x, y, w, h, new Color(2 / 3.0f, 1.0f, 1.0f, 1 / 15.0f));
    }

    @Override
    public void activate(Object nativeContainer, PluginListener pl) {}

    @Override
    public void destroy() {}

    @Override
    public void setVisible(boolean isVisible) {}

    @Override
    public void setEnabled(boolean enabled) {}

    private int x = 0;
    private int y = 0;
    private int w = 0;
    private int h = 0;

    @Override
    public void setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
        //nullComp.setBounds(new Rectangle(x, y, width, height) ) ;
    }

    @Override
    public Object invoke(String subObjectId, String methodName, Object[] args) throws IOError {
        return null;
    }

    @Override
    public boolean handleMouseEvent(
            String type,
            int offsetX,
            int offsetY,
            int screenX,
            int screenY,
            int button,
            boolean buttonDown,
            boolean altKey,
            boolean metaKey,
            boolean ctrlKey,
            boolean shiftKey,
            long timeStamp)
    {
        return false;
    }

    @Override
    public void requestFocus() {}

    @Override
    public void setNativeContainerBounds(int x, int y, int width, int height) {}
}

