/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;

class XSLFLineBreak
extends XSLFTextRun {
    protected XSLFLineBreak(CTTextLineBreak r, XSLFTextParagraph p) {
        super(r, p);
    }

    @Override
    public void setText(String text) {
        throw new IllegalStateException("You cannot change text of a line break, it is always '\\n'");
    }
}

