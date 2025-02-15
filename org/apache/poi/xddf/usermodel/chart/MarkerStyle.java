/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle;

public enum MarkerStyle {
    CIRCLE(STMarkerStyle.CIRCLE),
    DASH(STMarkerStyle.DASH),
    DIAMOND(STMarkerStyle.DIAMOND),
    DOT(STMarkerStyle.DOT),
    NONE(STMarkerStyle.NONE),
    PICTURE(STMarkerStyle.PICTURE),
    PLUS(STMarkerStyle.PLUS),
    SQUARE(STMarkerStyle.SQUARE),
    STAR(STMarkerStyle.STAR),
    TRIANGLE(STMarkerStyle.TRIANGLE),
    X(STMarkerStyle.X);

    final STMarkerStyle.Enum underlying;
    private static final HashMap<STMarkerStyle.Enum, MarkerStyle> reverse;

    private MarkerStyle(STMarkerStyle.Enum style) {
        this.underlying = style;
    }

    static MarkerStyle valueOf(STMarkerStyle.Enum style) {
        return reverse.get(style);
    }

    static {
        reverse = new HashMap();
        for (MarkerStyle value : MarkerStyle.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

