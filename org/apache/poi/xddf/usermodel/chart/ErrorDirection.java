/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.STErrDir
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrDir;

public enum ErrorDirection {
    X(STErrDir.X),
    Y(STErrDir.Y);

    final STErrDir.Enum underlying;
    private static final HashMap<STErrDir.Enum, ErrorDirection> reverse;

    private ErrorDirection(STErrDir.Enum direction) {
        this.underlying = direction;
    }

    static ErrorDirection valueOf(STErrDir.Enum direction) {
        return reverse.get(direction);
    }

    static {
        reverse = new HashMap();
        for (ErrorDirection value : ErrorDirection.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

