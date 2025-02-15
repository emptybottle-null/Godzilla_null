/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import java.util.HashMap;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;

public enum AnchorType {
    BOTTOM(STTextAnchoringType.B),
    CENTER(STTextAnchoringType.CTR),
    DISTRIBUTED(STTextAnchoringType.DIST),
    JUSTIFIED(STTextAnchoringType.JUST),
    TOP(STTextAnchoringType.T);

    final STTextAnchoringType.Enum underlying;
    private static final HashMap<STTextAnchoringType.Enum, AnchorType> reverse;

    private AnchorType(STTextAnchoringType.Enum caps) {
        this.underlying = caps;
    }

    static AnchorType valueOf(STTextAnchoringType.Enum caps) {
        return reverse.get(caps);
    }

    static {
        reverse = new HashMap();
        for (AnchorType value : AnchorType.values()) {
            reverse.put(value.underlying, value);
        }
    }
}

