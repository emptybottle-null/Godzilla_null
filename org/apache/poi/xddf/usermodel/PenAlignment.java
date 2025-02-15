/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;

public enum PenAlignment {
    CENTER(STPenAlignment.CTR),
    IN(STPenAlignment.IN);

    final STPenAlignment.Enum underlying;
    private static final HashMap<STPenAlignment.Enum, PenAlignment> reverse;

    private PenAlignment(STPenAlignment.Enum alignment) {
        this.underlying = alignment;
    }

    static PenAlignment valueOf(STPenAlignment.Enum LineEndWidth2) {
        return reverse.get(LineEndWidth2);
    }

    static {
        reverse = new HashMap();
        for (PenAlignment value : PenAlignment.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

