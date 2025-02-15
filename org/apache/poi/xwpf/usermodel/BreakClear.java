/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum BreakClear {
    NONE(1),
    LEFT(2),
    RIGHT(3),
    ALL(4);

    private static Map<Integer, BreakClear> imap;
    private final int value;

    private BreakClear(int val) {
        this.value = val;
    }

    public static BreakClear valueOf(int type) {
        BreakClear bType = imap.get(type);
        if (bType == null) {
            throw new IllegalArgumentException("Unknown break clear type: " + type);
        }
        return bType;
    }

    public int getValue() {
        return this.value;
    }

    static {
        imap = new HashMap<Integer, BreakClear>();
        for (BreakClear p : BreakClear.values()) {
            imap.put(p.getValue(), p);
        }
    }
}

