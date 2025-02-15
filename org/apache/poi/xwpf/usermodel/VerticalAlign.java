/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum VerticalAlign {
    BASELINE(1),
    SUPERSCRIPT(2),
    SUBSCRIPT(3);

    private static Map<Integer, VerticalAlign> imap;
    private final int value;

    private VerticalAlign(int val) {
        this.value = val;
    }

    public static VerticalAlign valueOf(int type) {
        VerticalAlign align = imap.get(type);
        if (align == null) {
            throw new IllegalArgumentException("Unknown vertical alignment: " + type);
        }
        return align;
    }

    public int getValue() {
        return this.value;
    }

    static {
        imap = new HashMap<Integer, VerticalAlign>();
        for (VerticalAlign p : VerticalAlign.values()) {
            imap.put(p.getValue(), p);
        }
    }
}

