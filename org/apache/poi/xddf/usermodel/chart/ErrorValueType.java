/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.STErrValType
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrValType;

public enum ErrorValueType {
    CUSTOM(STErrValType.CUST),
    FIXED_VALUE(STErrValType.FIXED_VAL),
    PERCENTAGE(STErrValType.PERCENTAGE),
    STANDARD_DEVIATION(STErrValType.STD_DEV),
    STANDARD_ERROR(STErrValType.STD_ERR);

    final STErrValType.Enum underlying;
    private static final HashMap<STErrValType.Enum, ErrorValueType> reverse;

    private ErrorValueType(STErrValType.Enum valueType) {
        this.underlying = valueType;
    }

    static ErrorValueType valueOf(STErrValType.Enum valueType) {
        return reverse.get(valueType);
    }

    static {
        reverse = new HashMap();
        for (ErrorValueType value : ErrorValueType.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

