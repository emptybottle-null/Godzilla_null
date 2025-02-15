/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;

public enum LineCap {
    FLAT(STLineCap.FLAT),
    ROUND(STLineCap.RND),
    SQUARE(STLineCap.SQ);

    final STLineCap.Enum underlying;
    private static final HashMap<STLineCap.Enum, LineCap> reverse;

    private LineCap(STLineCap.Enum line) {
        this.underlying = line;
    }

    static LineCap valueOf(STLineCap.Enum LineEndWidth2) {
        return reverse.get(LineEndWidth2);
    }

    static {
        reverse = new HashMap();
        for (LineCap value : LineCap.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

