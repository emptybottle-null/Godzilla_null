/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum FontFamily {
    NOT_APPLICABLE(0),
    ROMAN(1),
    SWISS(2),
    MODERN(3),
    SCRIPT(4),
    DECORATIVE(5);

    private int family;
    private static FontFamily[] _table;

    private FontFamily(int value) {
        this.family = value;
    }

    public int getValue() {
        return this.family;
    }

    public static FontFamily valueOf(int family) {
        return _table[family];
    }

    static {
        _table = new FontFamily[6];
        FontFamily[] fontFamilyArray = FontFamily.values();
        int n = fontFamilyArray.length;
        for (int i = 0; i < n; ++i) {
            FontFamily c;
            FontFamily._table[c.getValue()] = c = fontFamilyArray[i];
        }
    }
}

