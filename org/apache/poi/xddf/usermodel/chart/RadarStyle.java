/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STRadarStyle;

public enum RadarStyle {
    FILLED(STRadarStyle.FILLED),
    MARKER(STRadarStyle.MARKER),
    STANDARD(STRadarStyle.STANDARD);

    final STRadarStyle.Enum underlying;
    private static final HashMap<STRadarStyle.Enum, RadarStyle> reverse;

    private RadarStyle(STRadarStyle.Enum style) {
        this.underlying = style;
    }

    static RadarStyle valueOf(STRadarStyle.Enum style) {
        return reverse.get(style);
    }

    static {
        reverse = new HashMap();
        for (RadarStyle value : RadarStyle.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

