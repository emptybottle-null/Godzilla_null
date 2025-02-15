/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;

public enum FontAlignment {
    AUTOMATIC(STTextFontAlignType.AUTO),
    BOTTOM(STTextFontAlignType.B),
    BASELINE(STTextFontAlignType.BASE),
    CENTER(STTextFontAlignType.CTR),
    TOP(STTextFontAlignType.T);

    final STTextFontAlignType.Enum underlying;
    private static final HashMap<STTextFontAlignType.Enum, FontAlignment> reverse;

    private FontAlignment(STTextFontAlignType.Enum align) {
        this.underlying = align;
    }

    static FontAlignment valueOf(STTextFontAlignType.Enum align) {
        return reverse.get(align);
    }

    static {
        reverse = new HashMap();
        for (FontAlignment value : FontAlignment.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

