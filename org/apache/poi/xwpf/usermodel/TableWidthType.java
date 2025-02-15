/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

public enum TableWidthType {
    AUTO(STTblWidth.AUTO),
    DXA(STTblWidth.DXA),
    NIL(STTblWidth.NIL),
    PCT(STTblWidth.PCT);

    private STTblWidth.Enum type = STTblWidth.NIL;

    private TableWidthType(STTblWidth.Enum type) {
        this.type = type;
    }

    protected STTblWidth.Enum getSTWidthType() {
        return this.type;
    }

    @Internal
    public STTblWidth.Enum getStWidthType() {
        return this.type;
    }
}

