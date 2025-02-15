/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;

public enum LineEndType {
    ARROW(STLineEndType.ARROW),
    DIAMOND(STLineEndType.DIAMOND),
    NONE(STLineEndType.NONE),
    OVAL(STLineEndType.OVAL),
    STEALTH(STLineEndType.STEALTH),
    TRIANGLE(STLineEndType.TRIANGLE);

    final STLineEndType.Enum underlying;
    private static final HashMap<STLineEndType.Enum, LineEndType> reverse;

    private LineEndType(STLineEndType.Enum lineEnd) {
        this.underlying = lineEnd;
    }

    static LineEndType valueOf(STLineEndType.Enum LineEndWidth2) {
        return reverse.get(LineEndWidth2);
    }

    static {
        reverse = new HashMap();
        for (LineEndType value : LineEndType.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

