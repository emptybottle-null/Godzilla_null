/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;

public enum AxisTickLabelPosition {
    HIGH(STTickLblPos.HIGH),
    LOW(STTickLblPos.LOW),
    NEXT_TO(STTickLblPos.NEXT_TO),
    NONE(STTickLblPos.NONE);

    final STTickLblPos.Enum underlying;
    private static final HashMap<STTickLblPos.Enum, AxisTickLabelPosition> reverse;

    private AxisTickLabelPosition(STTickLblPos.Enum position) {
        this.underlying = position;
    }

    static AxisTickLabelPosition valueOf(STTickLblPos.Enum position) {
        return reverse.get(position);
    }

    static {
        reverse = new HashMap();
        for (AxisTickLabelPosition value : AxisTickLabelPosition.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

