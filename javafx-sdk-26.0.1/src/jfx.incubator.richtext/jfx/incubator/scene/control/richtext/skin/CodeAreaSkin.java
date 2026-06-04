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

package jfx.incubator.scene.control.richtext.skin;

import java.util.Locale;
import javafx.scene.text.Font;
import com.sun.jfx.incubator.scene.control.richtext.RichTextAreaSkinHelper;
import com.sun.jfx.incubator.scene.control.richtext.util.ListenerHelper;
import com.sun.jfx.incubator.scene.control.richtext.util.RichUtils;
import jfx.incubator.scene.control.richtext.CodeArea;
import jfx.incubator.scene.control.richtext.model.StyleAttributeMap;

/**
 * The skin for {@link CodeArea}.
 *
 * @since 24
 */
public class CodeAreaSkin extends RichTextAreaSkin {
    /**
     * Constructs the CodeArea skin.
     * @param control the CodeArea instance
     */
    public CodeAreaSkin(CodeArea control) {
        super(control);

        ListenerHelper lh = RichTextAreaSkinHelper.getListenerHelper(this);
        lh.addInvalidationListener(
            this::refreshLayout,
            control.fontProperty(),
            control.lineSpacingProperty(),
            control.tabSizeProperty()
        );
    }

    @Override
    public void applyStyles(CellContext cx, StyleAttributeMap attrs, boolean forParagraph) {
        super.applyStyles(cx, attrs, forParagraph);

        if (forParagraph) {
            CodeArea control = (CodeArea)getSkinnable();
            // font
            Font f = control.getFont();
            if (f != null) {
                double size = f.getSize();
                String family = f.getFamily();
                String name = f.getName();
                if (RichUtils.isLogicalFont(family)) {
                    String lowerCaseName = name.toLowerCase(Locale.ENGLISH);
                    String style = RichUtils.guessFontStyle(lowerCaseName);
                    String weight = RichUtils.guessFontWeight(lowerCaseName);
                    cx.addStyle("-fx-font-family:'" + family + "';");
                    cx.addStyle("-fx-font-style:" + style + ";");
                    cx.addStyle("-fx-font-weight:" + weight + ";");
                } else {
                    cx.addStyle("-fx-font-family:'" + name + "';");
                }
                cx.addStyle("-fx-font-size:" + size + ";");
            }

            // line spacing
            double lineSpacing = control.getLineSpacing();
            cx.addStyle("-fx-line-spacing:" + lineSpacing + ";");

            // tab size
            double tabSize = control.getTabSize();
            cx.addStyle("-fx-tab-size:" + tabSize + ";");
        }
    }
}
