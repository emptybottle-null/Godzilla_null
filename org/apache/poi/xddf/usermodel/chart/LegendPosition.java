/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;

public enum LegendPosition {
    BOTTOM(STLegendPos.B),
    LEFT(STLegendPos.L),
    RIGHT(STLegendPos.R),
    TOP(STLegendPos.T),
    TOP_RIGHT(STLegendPos.TR);

    final STLegendPos.Enum underlying;
    private static final HashMap<STLegendPos.Enum, LegendPosition> reverse;

    private LegendPosition(STLegendPos.Enum position) {
        this.underlying = position;
    }

    static LegendPosition valueOf(STLegendPos.Enum position) {
        return reverse.get(position);
    }

    static {
        reverse = new HashMap();
        for (LegendPosition value : LegendPosition.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

