/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum VerticalAlignment {
    TOP,
    CENTER,
    BOTTOM,
    JUSTIFY,
    DISTRIBUTED;


    public short getCode() {
        return (short)this.ordinal();
    }

    public static VerticalAlignment forInt(int code) {
        if (code < 0 || code >= VerticalAlignment.values().length) {
            throw new IllegalArgumentException("Invalid VerticalAlignment code: " + code);
        }
        return VerticalAlignment.values()[code];
    }
}

