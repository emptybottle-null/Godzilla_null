/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.common.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.util.Internal;

public enum HyperlinkType {
    NONE(-1),
    URL(1),
    DOCUMENT(2),
    EMAIL(3),
    FILE(4);

    @Internal(since="3.15 beta 3")
    @Deprecated
    private final int code;
    private static final Map<Integer, HyperlinkType> map;

    @Internal(since="3.15 beta 3")
    @Deprecated
    private HyperlinkType(int code) {
        this.code = code;
    }

    @Internal(since="3.15 beta 3")
    @Deprecated
    public int getCode() {
        return this.code;
    }

    @Internal(since="3.15 beta 3")
    @Deprecated
    public static HyperlinkType forInt(int code) {
        HyperlinkType type = map.get(code);
        if (type == null) {
            throw new IllegalArgumentException("Invalid type: " + code);
        }
        return type;
    }

    static {
        map = new HashMap<Integer, HyperlinkType>();
        for (HyperlinkType type : HyperlinkType.values()) {
            map.put(type.getCode(), type);
        }
    }
}

