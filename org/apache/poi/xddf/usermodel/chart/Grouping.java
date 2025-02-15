/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGrouping;

public enum Grouping {
    STANDARD(STGrouping.STANDARD),
    STACKED(STGrouping.STACKED),
    PERCENT_STACKED(STGrouping.PERCENT_STACKED);

    final STGrouping.Enum underlying;
    private static final HashMap<STGrouping.Enum, Grouping> reverse;

    private Grouping(STGrouping.Enum grouping) {
        this.underlying = grouping;
    }

    static Grouping valueOf(STGrouping.Enum grouping) {
        return reverse.get(grouping);
    }

    static {
        reverse = new HashMap();
        for (Grouping value : Grouping.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

