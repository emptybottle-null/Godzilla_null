/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum BreakType {
    PAGE(1),
    COLUMN(2),
    TEXT_WRAPPING(3);

    private static Map<Integer, BreakType> imap;
    private final int value;

    private BreakType(int val) {
        this.value = val;
    }

    public static BreakType valueOf(int type) {
        BreakType bType = imap.get(type);
        if (bType == null) {
            throw new IllegalArgumentException("Unknown break type: " + type);
        }
        return bType;
    }

    public int getValue() {
        return this.value;
    }

    static {
        imap = new HashMap<Integer, BreakType>();
        for (BreakType p : BreakType.values()) {
            imap.put(p.getValue(), p);
        }
    }
}

