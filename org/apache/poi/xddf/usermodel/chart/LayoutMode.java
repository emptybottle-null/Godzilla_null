/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutMode;

public enum LayoutMode {
    EDGE(STLayoutMode.EDGE),
    FACTOR(STLayoutMode.FACTOR);

    final STLayoutMode.Enum underlying;
    private static final HashMap<STLayoutMode.Enum, LayoutMode> reverse;

    private LayoutMode(STLayoutMode.Enum layoutMode) {
        this.underlying = layoutMode;
    }

    static LayoutMode valueOf(STLayoutMode.Enum layoutMode) {
        return reverse.get(layoutMode);
    }

    static {
        reverse = new HashMap();
        for (LayoutMode value : LayoutMode.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

