/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;

public enum AxisPosition {
    BOTTOM(STAxPos.B),
    LEFT(STAxPos.L),
    RIGHT(STAxPos.R),
    TOP(STAxPos.T);

    final STAxPos.Enum underlying;
    private static final HashMap<STAxPos.Enum, AxisPosition> reverse;

    private AxisPosition(STAxPos.Enum position) {
        this.underlying = position;
    }

    static AxisPosition valueOf(STAxPos.Enum position) {
        return reverse.get(position);
    }

    static {
        reverse = new HashMap();
        for (AxisPosition value : AxisPosition.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

