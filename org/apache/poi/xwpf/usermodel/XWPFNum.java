/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;

public class XWPFNum {
    protected XWPFNumbering numbering;
    private CTNum ctNum;

    public XWPFNum() {
        this.ctNum = null;
        this.numbering = null;
    }

    public XWPFNum(CTNum ctNum) {
        this.ctNum = ctNum;
        this.numbering = null;
    }

    public XWPFNum(XWPFNumbering numbering) {
        this.ctNum = null;
        this.numbering = numbering;
    }

    public XWPFNum(CTNum ctNum, XWPFNumbering numbering) {
        this.ctNum = ctNum;
        this.numbering = numbering;
    }

    public XWPFNumbering getNumbering() {
        return this.numbering;
    }

    public void setNumbering(XWPFNumbering numbering) {
        this.numbering = numbering;
    }

    public CTNum getCTNum() {
        return this.ctNum;
    }

    public void setCTNum(CTNum ctNum) {
        this.ctNum = ctNum;
    }
}

