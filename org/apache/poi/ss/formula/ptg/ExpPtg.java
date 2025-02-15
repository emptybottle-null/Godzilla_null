/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class ExpPtg
extends ControlPtg {
    private static final int SIZE = 5;
    public static final short sid = 1;
    private final int field_1_first_row;
    private final int field_2_first_col;

    public ExpPtg(LittleEndianInput in) {
        this.field_1_first_row = in.readShort();
        this.field_2_first_col = in.readShort();
    }

    public ExpPtg(int firstRow, int firstCol) {
        this.field_1_first_row = firstRow;
        this.field_2_first_col = firstCol;
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(1 + this.getPtgClass());
        out.writeShort(this.field_1_first_row);
        out.writeShort(this.field_2_first_col);
    }

    @Override
    public int getSize() {
        return 5;
    }

    public int getRow() {
        return this.field_1_first_row;
    }

    public int getColumn() {
        return this.field_2_first_col;
    }

    @Override
    public String toFormulaString() {
        throw new RuntimeException("Coding Error: Expected ExpPtg to be converted from Shared to Non-Shared Formula by ValueRecordsAggregate, but it wasn't");
    }

    @Override
    public String toString() {
        return "[Array Formula or Shared Formula]\nrow = " + this.getRow() + "\ncol = " + this.getColumn() + "\n";
    }

    @Override
    public ExpPtg copy() {
        return this;
    }
}

