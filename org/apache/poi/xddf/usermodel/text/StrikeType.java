/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;

public enum StrikeType {
    DOUBLE_STRIKE(STTextStrikeType.DBL_STRIKE),
    NO_STRIKE(STTextStrikeType.NO_STRIKE),
    SINGLE_STRIKE(STTextStrikeType.SNG_STRIKE);

    final STTextStrikeType.Enum underlying;
    private static final HashMap<STTextStrikeType.Enum, StrikeType> reverse;

    private StrikeType(STTextStrikeType.Enum strike) {
        this.underlying = strike;
    }

    static StrikeType valueOf(STTextStrikeType.Enum strike) {
        return reverse.get(strike);
    }

    static {
        reverse = new HashMap();
        for (StrikeType value : StrikeType.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

