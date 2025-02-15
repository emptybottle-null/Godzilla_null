/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.model;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

public final class WMLHelper {
    public static boolean convertSTOnOffToBoolean(STOnOff.Enum value) {
        return value == STOnOff.TRUE || value == STOnOff.ON || value == STOnOff.X_1;
    }

    public static STOnOff.Enum convertBooleanToSTOnOff(boolean value) {
        return value ? STOnOff.TRUE : STOnOff.FALSE;
    }
}

