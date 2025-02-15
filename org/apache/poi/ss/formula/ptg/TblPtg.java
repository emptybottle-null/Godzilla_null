/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class TblPtg
extends ControlPtg {
    private static final int SIZE = 5;
    public static final short sid = 2;
    private final int field_1_first_row;
    private final int field_2_first_col;

    public TblPtg(LittleEndianInput in) {
        this.field_1_first_row = in.readUShort();
        this.field_2_first_col = in.readUShort();
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(2 + this.getPtgClass());
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
        throw new RuntimeException("Table and Arrays are not yet supported");
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder("[Data Table - Parent cell is an interior cell in a data table]\n");
        buffer.append("top left row = ").append(this.getRow()).append("\n");
        buffer.append("top left col = ").append(this.getColumn()).append("\n");
        return buffer.toString();
    }

    @Override
    public TblPtg copy() {
        return this;
    }
}

