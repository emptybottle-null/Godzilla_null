/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STScatterStyle;

public enum ScatterStyle {
    LINE(STScatterStyle.LINE),
    LINE_MARKER(STScatterStyle.LINE_MARKER),
    MARKER(STScatterStyle.MARKER),
    NONE(STScatterStyle.NONE),
    SMOOTH(STScatterStyle.SMOOTH),
    SMOOTH_MARKER(STScatterStyle.SMOOTH_MARKER);

    final STScatterStyle.Enum underlying;
    private static final HashMap<STScatterStyle.Enum, ScatterStyle> reverse;

    private ScatterStyle(STScatterStyle.Enum style) {
        this.underlying = style;
    }

    static ScatterStyle valueOf(STScatterStyle.Enum style) {
        return reverse.get(style);
    }

    static {
        reverse = new HashMap();
        for (ScatterStyle value : ScatterStyle.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

