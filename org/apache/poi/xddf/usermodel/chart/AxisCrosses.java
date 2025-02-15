/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrosses;

public enum AxisCrosses {
    AUTO_ZERO(STCrosses.AUTO_ZERO),
    MAX(STCrosses.MAX),
    MIN(STCrosses.MIN);

    final STCrosses.Enum underlying;
    private static final HashMap<STCrosses.Enum, AxisCrosses> reverse;

    private AxisCrosses(STCrosses.Enum crosses) {
        this.underlying = crosses;
    }

    static AxisCrosses valueOf(STCrosses.Enum crosses) {
        return reverse.get(crosses);
    }

    static {
        reverse = new HashMap();
        for (AxisCrosses value : AxisCrosses.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

