/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;

public class XWPFFieldRun
extends XWPFRun {
    private CTSimpleField field;

    public XWPFFieldRun(CTSimpleField field, CTR run, IRunBody p) {
        super(run, p);
        this.field = field;
    }

    @Internal
    public CTSimpleField getCTField() {
        return this.field;
    }

    public String getFieldInstruction() {
        return this.field.getInstr();
    }

    public void setFieldInstruction(String instruction) {
        this.field.setInstr(instruction);
    }
}

