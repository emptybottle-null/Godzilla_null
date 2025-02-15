/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum PrintCellComments {
    NONE(1),
    AS_DISPLAYED(2),
    AT_END(3);

    private int comments;
    private static PrintCellComments[] _table;

    private PrintCellComments(int comments) {
        this.comments = comments;
    }

    public int getValue() {
        return this.comments;
    }

    public static PrintCellComments valueOf(int value) {
        return _table[value];
    }

    static {
        _table = new PrintCellComments[4];
        PrintCellComments[] printCellCommentsArray = PrintCellComments.values();
        int n = printCellCommentsArray.length;
        for (int i = 0; i < n; ++i) {
            PrintCellComments c;
            PrintCellComments._table[c.getValue()] = c = printCellCommentsArray[i];
        }
    }
}

