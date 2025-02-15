/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;

public class XWPFDefaultParagraphStyle {
    private CTPPr ppr;

    public XWPFDefaultParagraphStyle(CTPPr ppr) {
        this.ppr = ppr;
    }

    protected CTPPr getPPr() {
        return this.ppr;
    }

    public int getSpacingAfter() {
        if (this.ppr.isSetSpacing()) {
            return this.ppr.getSpacing().getAfter().intValue();
        }
        return -1;
    }
}

