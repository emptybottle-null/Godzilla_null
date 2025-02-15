/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STShape;

public enum Shape {
    BOX(STShape.BOX),
    CONE(STShape.CONE),
    CONE_TO_MAX(STShape.CONE_TO_MAX),
    CYLINDER(STShape.CYLINDER),
    PYRAMID(STShape.PYRAMID),
    PYRAMID_TO_MAX(STShape.PYRAMID_TO_MAX);

    final STShape.Enum underlying;
    private static final HashMap<STShape.Enum, Shape> reverse;

    private Shape(STShape.Enum grouping) {
        this.underlying = grouping;
    }

    static Shape valueOf(STShape.Enum grouping) {
        return reverse.get(grouping);
    }

    static {
        reverse = new HashMap();
        for (Shape value : Shape.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

