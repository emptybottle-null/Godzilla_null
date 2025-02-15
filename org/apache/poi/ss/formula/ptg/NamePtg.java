/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.WorkbookDependentFormula;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class NamePtg
extends OperandPtg
implements WorkbookDependentFormula {
    public static final short sid = 35;
    private static final int SIZE = 5;
    private int field_1_label_index;
    private short field_2_zero;

    public NamePtg(int nameIndex) {
        this.field_1_label_index = 1 + nameIndex;
    }

    public NamePtg(NamePtg other) {
        super(other);
        this.field_1_label_index = other.field_1_label_index;
        this.field_2_zero = other.field_2_zero;
    }

    public NamePtg(LittleEndianInput in) {
        this.field_1_label_index = in.readUShort();
        this.field_2_zero = in.readShort();
    }

    public int getIndex() {
        return this.field_1_label_index - 1;
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(35 + this.getPtgClass());
        out.writeShort(this.field_1_label_index);
        out.writeShort(this.field_2_zero);
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public String toFormulaString(FormulaRenderingWorkbook book) {
        return book.getNameText(this);
    }

    @Override
    public String toFormulaString() {
        throw new RuntimeException("3D references need a workbook to determine formula text");
    }

    @Override
    public byte getDefaultOperandClass() {
        return 0;
    }

    @Override
    public NamePtg copy() {
        return new NamePtg(this);
    }
}

