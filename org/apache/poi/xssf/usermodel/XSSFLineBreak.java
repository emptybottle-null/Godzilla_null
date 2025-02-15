/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.xssf.usermodel.XSSFTextParagraph;
import org.apache.poi.xssf.usermodel.XSSFTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;

class XSSFLineBreak
extends XSSFTextRun {
    private final CTTextCharacterProperties _brProps;

    XSSFLineBreak(CTRegularTextRun r, XSSFTextParagraph p, CTTextCharacterProperties brProps) {
        super(r, p);
        this._brProps = brProps;
    }

    @Override
    protected CTTextCharacterProperties getRPr() {
        return this._brProps;
    }

    @Override
    public void setText(String text) {
        throw new IllegalStateException("You cannot change text of a line break, it is always '\\n'");
    }
}

