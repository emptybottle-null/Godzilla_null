/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;

public enum TextAlignment {
    CENTER(STTextAlignType.CTR),
    DISTRIBUTED(STTextAlignType.DIST),
    JUSTIFIED(STTextAlignType.JUST),
    JUSTIFIED_LOW(STTextAlignType.JUST_LOW),
    LEFT(STTextAlignType.L),
    RIGHT(STTextAlignType.R),
    THAI_DISTRIBUTED(STTextAlignType.THAI_DIST);

    final STTextAlignType.Enum underlying;
    private static final HashMap<STTextAlignType.Enum, TextAlignment> reverse;

    private TextAlignment(STTextAlignType.Enum align) {
        this.underlying = align;
    }

    static TextAlignment valueOf(STTextAlignType.Enum align) {
        return reverse.get(align);
    }

    static {
        reverse = new HashMap();
        for (TextAlignment value : TextAlignment.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

