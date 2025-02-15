/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum BorderStyle {
    NONE(0),
    THIN(1),
    MEDIUM(2),
    DASHED(3),
    DOTTED(4),
    THICK(5),
    DOUBLE(6),
    HAIR(7),
    MEDIUM_DASHED(8),
    DASH_DOT(9),
    MEDIUM_DASH_DOT(10),
    DASH_DOT_DOT(11),
    MEDIUM_DASH_DOT_DOT(12),
    SLANTED_DASH_DOT(13);

    private final short code;
    private static final BorderStyle[] _table;

    private BorderStyle(int code) {
        this.code = (short)code;
    }

    public short getCode() {
        return this.code;
    }

    public static BorderStyle valueOf(short code) {
        return _table[code];
    }

    static {
        _table = new BorderStyle[14];
        BorderStyle[] borderStyleArray = BorderStyle.values();
        int n = borderStyleArray.length;
        for (int i = 0; i < n; ++i) {
            BorderStyle c;
            BorderStyle._table[c.getCode()] = c = borderStyleArray[i];
        }
    }
}

