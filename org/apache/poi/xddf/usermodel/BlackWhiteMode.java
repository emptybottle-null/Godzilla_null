/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;

public enum BlackWhiteMode {
    AUTO(STBlackWhiteMode.AUTO),
    BLACK(STBlackWhiteMode.BLACK),
    BLACK_GRAY(STBlackWhiteMode.BLACK_GRAY),
    BLACK_WHITE(STBlackWhiteMode.BLACK_WHITE);

    final STBlackWhiteMode.Enum underlying;
    private static final HashMap<STBlackWhiteMode.Enum, BlackWhiteMode> reverse;

    private BlackWhiteMode(STBlackWhiteMode.Enum mode) {
        this.underlying = mode;
    }

    static BlackWhiteMode valueOf(STBlackWhiteMode.Enum mode) {
        return reverse.get(mode);
    }

    static {
        reverse = new HashMap();
        for (BlackWhiteMode value : BlackWhiteMode.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

