/*
 * Copyright (c) 2008, 2025, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.tk.quantum;

import java.nio.ByteOrder;
import com.sun.glass.ui.Application;
import com.sun.glass.ui.Cursor;
import com.sun.glass.ui.Pixels;
import com.sun.glass.ui.View;
import com.sun.glass.ui.Window;
import com.sun.javafx.cursor.CursorFrame;
import com.sun.javafx.sg.prism.NGNode;
import com.sun.javafx.tk.Toolkit;
import com.sun.prism.GraphicsPipeline;
import javafx.scene.Parent;
import javafx.scene.Scene;

class ViewScene extends GlassScene {

    private static final String UNSUPPORTED_FORMAT =
        "Transparent windows only supported for BYTE_BGRA_PRE format on LITTLE_ENDIAN machines";

    private Scene scene;
    private View platformView;
    private ViewPainter painter;
    private PaintRenderJob paintRenderJob;
    private ViewSceneOverlay viewSceneOverlay;
    private Parent overlayRoot;

    public ViewScene(Scene scene, boolean depthBuffer, boolean msaa) {
        super(depthBuffer, msaa);

        this.scene = scene;
        this.platformView = Application.GetApplication().createView();
        this.platformView.setEventHandler(new GlassViewEventHandler(this));
    }

    @Override protected boolean isSynchronous() {
        return painter != null && painter instanceof PresentingPainter;
    }

    @Override
    protected View getPlatformView() {
        return this.platformView;
    }

    ViewPainter getPainter() {
        return painter;
    }

    @Override
    public void setStage(GlassStage stage) {
        super.setStage(stage);
        if (stage != null) {
            WindowStage wstage  = (WindowStage)stage;
            if (wstage.needsUpdateWindow() || GraphicsPipeline.getPipeline().isUploading()) {
                if (Pixels.getNativeFormat() != Pixels.Format.BYTE_BGRA_PRE ||
                    ByteOrder.nativeOrder() != ByteOrder.LITTLE_ENDIAN) {
                    throw new UnsupportedOperationException(UNSUPPORTED_FORMAT);
                }
                painter = new UploadingPainter(this);
            } else {
                painter = new PresentingPainter(this);
            }

            if (scene != null) {
                viewSceneOverlay = new ViewSceneOverlay(scene, painter);
                viewSceneOverlay.setRoot(overlayRoot);
            } else {
                viewSceneOverlay = null;
            }

            painter.setRoot(getRoot());
            paintRenderJob = new PaintRenderJob(this, PaintCollector.getInstance().getRendered(), painter);
        }
    }

    WindowStage getWindowStage() {
        return (WindowStage)getStage();
    }

    /* com.sun.javafx.tk.TKScene */

    @Override
    public void dispose() {
        if (platformView != null) {
            QuantumToolkit.runWithRenderLock(() -> {
                platformView.close();
                platformView = null;
                updateSceneState();
                painter = null;
                paintRenderJob = null;
                scene = null;

                if (viewSceneOverlay != null) {
                    viewSceneOverlay.dispose();
                    viewSceneOverlay = null;
                }

                return null;
            });
        }
        super.dispose();
    }

    @Override public void setRoot(NGNode root) {
        super.setRoot(root);
        if (painter != null) {
            painter.setRoot(root);
        }
    }

    @Override
    public void setCursor(final Object cursor) {
        super.setCursor(cursor);
        Application.invokeLater(() -> {
            final CursorFrame cursorFrame = (CursorFrame) cursor;
            final Cursor platformCursor =
                    CursorUtils.getPlatformCursor(cursorFrame);

            if (platformView != null) {
                Window window = platformView.getWindow();
                if (window != null) {
                    window.setCursor(platformCursor);
                }
            }
        });
    }

    @Override void repaint() {
        if (platformView == null) {
            return;
        }

        if (!setPainting(true)) {
            Toolkit tk = Toolkit.getToolkit();
            tk.addRenderJob(paintRenderJob);
        }
    }

    @Override
    public void enableInputMethodEvents(boolean enable) {
        platformView.enableInputMethodEvents(enable);
    }

    @Override
    public void finishInputMethodComposition() {
        platformView.finishInputMethodComposition();
    }

    @Override
    public void reapplyOverlayCSS() {
        if (viewSceneOverlay != null) {
            viewSceneOverlay.reapplyCSS();
        }
    }

    @Override
    public void processOverlayCSS() {
        if (viewSceneOverlay != null) {
            viewSceneOverlay.processCSS();
        }
    }

    @Override
    public void layoutOverlay() {
        if (viewSceneOverlay != null) {
            viewSceneOverlay.layout();
        }
    }

    @Override
    public void synchronizeOverlay() {
        if (viewSceneOverlay != null) {
            viewSceneOverlay.synchronize();
        }
    }

    public void setViewSize(float width, float height) {
        sceneListener.changedSize(width, height);

        if (viewSceneOverlay != null) {
            viewSceneOverlay.resize(width, height);
        }
    }

    public void setOverlay(Parent root) {
        overlayRoot = root;

        if (viewSceneOverlay != null) {
            viewSceneOverlay.setRoot(root);
        }
    }

    @Override public String toString() {
        View view = getPlatformView();
        return (" scene: " + hashCode() + " @ (" + view.getWidth() + "," + view.getHeight() + ")");
    }
}

