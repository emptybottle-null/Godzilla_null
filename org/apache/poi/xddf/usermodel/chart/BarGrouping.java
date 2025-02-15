/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarGrouping;

public enum BarGrouping {
    STANDARD(STBarGrouping.STANDARD),
    CLUSTERED(STBarGrouping.CLUSTERED),
    STACKED(STBarGrouping.STACKED),
    PERCENT_STACKED(STBarGrouping.PERCENT_STACKED);

    final STBarGrouping.Enum underlying;
    private static final HashMap<STBarGrouping.Enum, BarGrouping> reverse;

    private BarGrouping(STBarGrouping.Enum grouping) {
        this.underlying = grouping;
    }

    static BarGrouping valueOf(STBarGrouping.Enum grouping) {
        return reverse.get(grouping);
    }

    static {
        reverse = new HashMap();
        for (BarGrouping value : BarGrouping.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

