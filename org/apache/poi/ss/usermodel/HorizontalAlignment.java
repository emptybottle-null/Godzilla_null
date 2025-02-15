/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum HorizontalAlignment {
    GENERAL,
    LEFT,
    CENTER,
    RIGHT,
    FILL,
    JUSTIFY,
    CENTER_SELECTION,
    DISTRIBUTED;


    public short getCode() {
        return (short)this.ordinal();
    }

    public static HorizontalAlignment forInt(int code) {
        if (code < 0 || code >= HorizontalAlignment.values().length) {
            throw new IllegalArgumentException("Invalid HorizontalAlignment code: " + code);
        }
        return HorizontalAlignment.values()[code];
    }
}

