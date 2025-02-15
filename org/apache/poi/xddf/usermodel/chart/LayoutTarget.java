/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutTarget;

public enum LayoutTarget {
    INNER(STLayoutTarget.INNER),
    OUTER(STLayoutTarget.OUTER);

    final STLayoutTarget.Enum underlying;
    private static final HashMap<STLayoutTarget.Enum, LayoutTarget> reverse;

    private LayoutTarget(STLayoutTarget.Enum layoutTarget) {
        this.underlying = layoutTarget;
    }

    static LayoutTarget valueOf(STLayoutTarget.Enum layoutTarget) {
        return reverse.get(layoutTarget);
    }

    static {
        reverse = new HashMap();
        for (LayoutTarget value : LayoutTarget.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

