/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum FontUnderline {
    SINGLE(1),
    DOUBLE(2),
    SINGLE_ACCOUNTING(3),
    DOUBLE_ACCOUNTING(4),
    NONE(5);

    private int value;
    private static FontUnderline[] _table;

    private FontUnderline(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }

    public byte getByteValue() {
        switch (this) {
            case DOUBLE: {
                return 2;
            }
            case DOUBLE_ACCOUNTING: {
                return 34;
            }
            case SINGLE_ACCOUNTING: {
                return 33;
            }
            case NONE: {
                return 0;
            }
            case SINGLE: {
                return 1;
            }
        }
        return 1;
    }

    public static FontUnderline valueOf(int value) {
        return _table[value];
    }

    public static FontUnderline valueOf(byte value) {
        FontUnderline val;
        switch (value) {
            case 2: {
                val = DOUBLE;
                break;
            }
            case 34: {
                val = DOUBLE_ACCOUNTING;
                break;
            }
            case 33: {
                val = SINGLE_ACCOUNTING;
                break;
            }
            case 1: {
                val = SINGLE;
                break;
            }
            default: {
                val = NONE;
            }
        }
        return val;
    }

    static {
        _table = new FontUnderline[6];
        FontUnderline[] fontUnderlineArray = FontUnderline.values();
        int n = fontUnderlineArray.length;
        for (int i = 0; i < n; ++i) {
            FontUnderline c;
            FontUnderline._table[c.getValue()] = c = fontUnderlineArray[i];
        }
    }
}

