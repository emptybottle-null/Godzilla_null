/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLblAlgn;

public enum AxisLabelAlignment {
    CENTER(STLblAlgn.CTR),
    LEFT(STLblAlgn.L),
    RIGHT(STLblAlgn.R);

    final STLblAlgn.Enum underlying;
    private static final HashMap<STLblAlgn.Enum, AxisLabelAlignment> reverse;

    private AxisLabelAlignment(STLblAlgn.Enum alignment) {
        this.underlying = alignment;
    }

    static AxisLabelAlignment valueOf(STLblAlgn.Enum alignment) {
        return reverse.get(alignment);
    }

    static {
        reverse = new HashMap();
        for (AxisLabelAlignment value : AxisLabelAlignment.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

