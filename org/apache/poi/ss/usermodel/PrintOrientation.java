/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum PrintOrientation {
    DEFAULT(1),
    PORTRAIT(2),
    LANDSCAPE(3);

    private int orientation;
    private static PrintOrientation[] _table;

    private PrintOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getValue() {
        return this.orientation;
    }

    public static PrintOrientation valueOf(int value) {
        return _table[value];
    }

    static {
        _table = new PrintOrientation[4];
        PrintOrientation[] printOrientationArray = PrintOrientation.values();
        int n = printOrientationArray.length;
        for (int i = 0; i < n; ++i) {
            PrintOrientation c;
            PrintOrientation._table[c.getValue()] = c = printOrientationArray[i];
        }
    }
}

