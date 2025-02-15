/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;

public enum LineEndLength {
    LARGE(STLineEndLength.LG),
    MEDIUM(STLineEndLength.MED),
    SMALL(STLineEndLength.SM);

    final STLineEndLength.Enum underlying;
    private static final HashMap<STLineEndLength.Enum, LineEndLength> reverse;

    private LineEndLength(STLineEndLength.Enum lineEnd) {
        this.underlying = lineEnd;
    }

    static LineEndLength valueOf(STLineEndLength.Enum LineEndWidth2) {
        return reverse.get(LineEndWidth2);
    }

    static {
        reverse = new HashMap();
        for (LineEndLength value : LineEndLength.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

