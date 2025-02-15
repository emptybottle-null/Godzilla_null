/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum FontScheme {
    NONE(1),
    MAJOR(2),
    MINOR(3);

    private int value;
    private static FontScheme[] _table;

    private FontScheme(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }

    public static FontScheme valueOf(int value) {
        return _table[value];
    }

    static {
        _table = new FontScheme[4];
        FontScheme[] fontSchemeArray = FontScheme.values();
        int n = fontSchemeArray.length;
        for (int i = 0; i < n; ++i) {
            FontScheme c;
            FontScheme._table[c.getValue()] = c = fontSchemeArray[i];
        }
    }
}

