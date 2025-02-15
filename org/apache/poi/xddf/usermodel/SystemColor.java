/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STSystemColorVal;

public enum SystemColor {
    ACTIVE_BORDER(STSystemColorVal.ACTIVE_BORDER),
    ACTIVE_CAPTION(STSystemColorVal.ACTIVE_CAPTION),
    APPLICATION_WORKSPACE(STSystemColorVal.APP_WORKSPACE),
    BACKGROUND(STSystemColorVal.BACKGROUND),
    BUTTON_FACE(STSystemColorVal.BTN_FACE),
    BUTTON_HIGHLIGHT(STSystemColorVal.BTN_HIGHLIGHT),
    BUTTON_SHADOW(STSystemColorVal.BTN_SHADOW),
    BUTTON_TEXT(STSystemColorVal.BTN_TEXT),
    CAPTION_TEXT(STSystemColorVal.CAPTION_TEXT),
    GRADIENT_ACTIVE_CAPTION(STSystemColorVal.GRADIENT_ACTIVE_CAPTION),
    GRADIENT_INACTIVE_CAPTION(STSystemColorVal.GRADIENT_INACTIVE_CAPTION),
    GRAY_TEXT(STSystemColorVal.GRAY_TEXT),
    HIGHLIGHT(STSystemColorVal.HIGHLIGHT),
    HIGHLIGHT_TEXT(STSystemColorVal.HIGHLIGHT_TEXT),
    HOT_LIGHT(STSystemColorVal.HOT_LIGHT),
    INACTIVE_BORDER(STSystemColorVal.INACTIVE_BORDER),
    INACTIVE_CAPTION(STSystemColorVal.INACTIVE_CAPTION),
    INACTIVE_CAPTION_TEXT(STSystemColorVal.INACTIVE_CAPTION_TEXT),
    INFO_BACKGROUND(STSystemColorVal.INFO_BK),
    INFO_TEXT(STSystemColorVal.INFO_TEXT),
    MENU(STSystemColorVal.MENU),
    MENU_BAR(STSystemColorVal.MENU_BAR),
    MENU_HIGHLIGHT(STSystemColorVal.MENU_HIGHLIGHT),
    MENU_TEXT(STSystemColorVal.MENU_TEXT),
    SCROLL_BAR(STSystemColorVal.SCROLL_BAR),
    WINDOW(STSystemColorVal.WINDOW),
    WINDOW_FRAME(STSystemColorVal.WINDOW_FRAME),
    WINDOW_TEXT(STSystemColorVal.WINDOW_TEXT),
    X_3D_DARK_SHADOW(STSystemColorVal.X_3_D_DK_SHADOW),
    X_3D_LIGHT(STSystemColorVal.X_3_D_LIGHT);

    final STSystemColorVal.Enum underlying;
    private static final HashMap<STSystemColorVal.Enum, SystemColor> reverse;

    private SystemColor(STSystemColorVal.Enum color) {
        this.underlying = color;
    }

    static SystemColor valueOf(STSystemColorVal.Enum color) {
        return reverse.get(color);
    }

    static {
        reverse = new HashMap();
        for (SystemColor value : SystemColor.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

