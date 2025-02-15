/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrossBetween;

public enum AxisCrossBetween {
    BETWEEN(STCrossBetween.BETWEEN),
    MIDPOINT_CATEGORY(STCrossBetween.MID_CAT);

    final STCrossBetween.Enum underlying;
    private static final HashMap<STCrossBetween.Enum, AxisCrossBetween> reverse;

    private AxisCrossBetween(STCrossBetween.Enum crossBetween) {
        this.underlying = crossBetween;
    }

    static AxisCrossBetween valueOf(STCrossBetween.Enum crossBetween) {
        return reverse.get(crossBetween);
    }

    static {
        reverse = new HashMap();
        for (AxisCrossBetween value : AxisCrossBetween.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

