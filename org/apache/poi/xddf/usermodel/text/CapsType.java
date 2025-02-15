/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextCapsType;

public enum CapsType {
    ALL(STTextCapsType.ALL),
    NONE(STTextCapsType.NONE),
    SMALL(STTextCapsType.SMALL);

    final STTextCapsType.Enum underlying;
    private static final HashMap<STTextCapsType.Enum, CapsType> reverse;

    private CapsType(STTextCapsType.Enum caps) {
        this.underlying = caps;
    }

    static CapsType valueOf(STTextCapsType.Enum caps) {
        return reverse.get(caps);
    }

    static {
        reverse = new HashMap();
        for (CapsType value : CapsType.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

