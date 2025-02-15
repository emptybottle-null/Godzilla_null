/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;

public enum LineSpacingRule {
    AUTO(1),
    EXACT(2),
    AT_LEAST(3);

    private static Map<Integer, LineSpacingRule> imap;
    private final int value;

    private LineSpacingRule(int val) {
        this.value = val;
    }

    public static LineSpacingRule valueOf(int type) {
        LineSpacingRule lineType = imap.get(type);
        if (lineType == null) {
            throw new IllegalArgumentException("Unknown line type: " + type);
        }
        return lineType;
    }

    public int getValue() {
        return this.value;
    }

    static {
        imap = new HashMap<Integer, LineSpacingRule>();
        for (LineSpacingRule p : LineSpacingRule.values()) {
            imap.put(p.getValue(), p);
        }
    }
}

