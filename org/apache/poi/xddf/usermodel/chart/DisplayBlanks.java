/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STDispBlanksAs;

public enum DisplayBlanks {
    GAP(STDispBlanksAs.GAP),
    SPAN(STDispBlanksAs.SPAN),
    ZERO(STDispBlanksAs.ZERO);

    final STDispBlanksAs.Enum underlying;
    private static final HashMap<STDispBlanksAs.Enum, DisplayBlanks> reverse;

    private DisplayBlanks(STDispBlanksAs.Enum mode) {
        this.underlying = mode;
    }

    static DisplayBlanks valueOf(STDispBlanksAs.Enum mode) {
        return reverse.get(mode);
    }

    static {
        reverse = new HashMap();
        for (DisplayBlanks value : DisplayBlanks.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

