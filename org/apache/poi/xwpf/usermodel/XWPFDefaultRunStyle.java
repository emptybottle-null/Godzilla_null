/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;

public class XWPFDefaultRunStyle {
    private CTRPr rpr;

    public XWPFDefaultRunStyle(CTRPr rpr) {
        this.rpr = rpr;
    }

    protected CTRPr getRPr() {
        return this.rpr;
    }

    public int getFontSize() {
        if (this.rpr.isSetSz()) {
            return this.rpr.getSz().getVal().intValue() / 2;
        }
        return -1;
    }
}

