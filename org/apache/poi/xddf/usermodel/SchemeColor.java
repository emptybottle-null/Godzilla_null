/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STSchemeColorVal;

public enum SchemeColor {
    ACCENT_1(STSchemeColorVal.ACCENT_1),
    ACCENT_2(STSchemeColorVal.ACCENT_2),
    ACCENT_3(STSchemeColorVal.ACCENT_3),
    ACCENT_4(STSchemeColorVal.ACCENT_4),
    ACCENT_5(STSchemeColorVal.ACCENT_5),
    ACCENT_6(STSchemeColorVal.ACCENT_6),
    BACKGROUND_1(STSchemeColorVal.BG_1),
    BACKGROUND_2(STSchemeColorVal.BG_2),
    DARK_1(STSchemeColorVal.DK_1),
    DARK_2(STSchemeColorVal.DK_2),
    FOLLOWED_LINK(STSchemeColorVal.FOL_HLINK),
    LINK(STSchemeColorVal.HLINK),
    LIGHT_1(STSchemeColorVal.LT_1),
    LIGHT_2(STSchemeColorVal.LT_2),
    PLACEHOLDER(STSchemeColorVal.PH_CLR),
    TEXT_1(STSchemeColorVal.TX_1),
    TEXT_2(STSchemeColorVal.TX_2);

    final STSchemeColorVal.Enum underlying;
    private static final HashMap<STSchemeColorVal.Enum, SchemeColor> reverse;

    private SchemeColor(STSchemeColorVal.Enum color) {
        this.underlying = color;
    }

    static SchemeColor valueOf(STSchemeColorVal.Enum color) {
        return reverse.get(color);
    }

    static {
        reverse = new HashMap();
        for (SchemeColor value : SchemeColor.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

