/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;

public enum CompoundLine {
    DOUBLE(STCompoundLine.DBL),
    SINGLE(STCompoundLine.SNG),
    THICK_THIN(STCompoundLine.THICK_THIN),
    THIN_THICK(STCompoundLine.THIN_THICK),
    TRIPLE(STCompoundLine.TRI);

    final STCompoundLine.Enum underlying;
    private static final HashMap<STCompoundLine.Enum, CompoundLine> reverse;

    private CompoundLine(STCompoundLine.Enum line) {
        this.underlying = line;
    }

    static CompoundLine valueOf(STCompoundLine.Enum LineEndWidth2) {
        return reverse.get(LineEndWidth2);
    }

    static {
        reverse = new HashMap();
        for (CompoundLine value : CompoundLine.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

