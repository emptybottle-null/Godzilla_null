/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;

public enum LineEndWidth {
    LARGE(STLineEndWidth.LG),
    MEDIUM(STLineEndWidth.MED),
    SMALL(STLineEndWidth.SM);

    final STLineEndWidth.Enum underlying;
    private static final HashMap<STLineEndWidth.Enum, LineEndWidth> reverse;

    private LineEndWidth(STLineEndWidth.Enum lineEnd) {
        this.underlying = lineEnd;
    }

    static LineEndWidth valueOf(STLineEndWidth.Enum LineEndWidth2) {
        return reverse.get(LineEndWidth2);
    }

    static {
        reverse = new HashMap();
        for (LineEndWidth value : LineEndWidth.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

