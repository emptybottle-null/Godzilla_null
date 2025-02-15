/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum TextAlignment {
    TOP(1),
    CENTER(2),
    BASELINE(3),
    BOTTOM(4),
    AUTO(5);

    private static Map<Integer, TextAlignment> imap;
    private final int value;

    private TextAlignment(int val) {
        this.value = val;
    }

    public static TextAlignment valueOf(int type) {
        TextAlignment align = imap.get(type);
        if (align == null) {
            throw new IllegalArgumentException("Unknown text alignment: " + type);
        }
        return align;
    }

    public int getValue() {
        return this.value;
    }

    static {
        imap = new HashMap<Integer, TextAlignment>();
        for (TextAlignment p : TextAlignment.values()) {
            imap.put(p.getValue(), p);
        }
    }
}

