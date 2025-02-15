/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickMark;

public enum AxisTickMark {
    CROSS(STTickMark.CROSS),
    IN(STTickMark.IN),
    NONE(STTickMark.NONE),
    OUT(STTickMark.OUT);

    final STTickMark.Enum underlying;
    private static final HashMap<STTickMark.Enum, AxisTickMark> reverse;

    private AxisTickMark(STTickMark.Enum tickMark) {
        this.underlying = tickMark;
    }

    static AxisTickMark valueOf(STTickMark.Enum tickMark) {
        return reverse.get(tickMark);
    }

    static {
        reverse = new HashMap();
        for (AxisTickMark value : AxisTickMark.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

