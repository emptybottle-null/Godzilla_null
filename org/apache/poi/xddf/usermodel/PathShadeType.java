/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathShadeType;

public enum PathShadeType {
    CIRCLE(STPathShadeType.CIRCLE),
    RECTANGLE(STPathShadeType.RECT),
    SHAPE(STPathShadeType.SHAPE);

    final STPathShadeType.Enum underlying;
    private static final HashMap<STPathShadeType.Enum, PathShadeType> reverse;

    private PathShadeType(STPathShadeType.Enum pathShadeType) {
        this.underlying = pathShadeType;
    }

    static PathShadeType valueOf(STPathShadeType.Enum pathShadeType) {
        return reverse.get(pathShadeType);
    }

    static {
        reverse = new HashMap();
        for (PathShadeType value : PathShadeType.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

