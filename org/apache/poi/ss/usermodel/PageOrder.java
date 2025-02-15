/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public enum PageOrder {
    DOWN_THEN_OVER(1),
    OVER_THEN_DOWN(2);

    private final int order;
    private static PageOrder[] _table;

    private PageOrder(int order) {
        this.order = order;
    }

    public int getValue() {
        return this.order;
    }

    public static PageOrder valueOf(int value) {
        return _table[value];
    }

    static {
        _table = new PageOrder[3];
        PageOrder[] pageOrderArray = PageOrder.values();
        int n = pageOrderArray.length;
        for (int i = 0; i < n; ++i) {
            PageOrder c;
            PageOrder._table[c.getValue()] = c = pageOrderArray[i];
        }
    }
}

