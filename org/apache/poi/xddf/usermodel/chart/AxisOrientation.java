/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;

public enum AxisOrientation {
    MIN_MAX(STOrientation.MIN_MAX),
    MAX_MIN(STOrientation.MAX_MIN);

    final STOrientation.Enum underlying;
    private static final HashMap<STOrientation.Enum, AxisOrientation> reverse;

    private AxisOrientation(STOrientation.Enum orientation) {
        this.underlying = orientation;
    }

    static AxisOrientation valueOf(STOrientation.Enum orientation) {
        return reverse.get(orientation);
    }

    static {
        reverse = new HashMap();
        for (AxisOrientation value : AxisOrientation.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

