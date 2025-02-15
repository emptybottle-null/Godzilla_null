/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.STErrBarType
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrBarType;

public enum ErrorBarType {
    BOTH(STErrBarType.BOTH),
    MINUS(STErrBarType.MINUS),
    PLUS(STErrBarType.PLUS);

    final STErrBarType.Enum underlying;
    private static final HashMap<STErrBarType.Enum, ErrorBarType> reverse;

    private ErrorBarType(STErrBarType.Enum barType) {
        this.underlying = barType;
    }

    static ErrorBarType valueOf(STErrBarType.Enum barType) {
        return reverse.get(barType);
    }

    static {
        reverse = new HashMap();
        for (ErrorBarType value : ErrorBarType.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

