/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

abstract class Ref2DPtgBase
extends RefPtgBase {
    private static final int SIZE = 5;

    protected Ref2DPtgBase(int row, int column, boolean isRowRelative, boolean isColumnRelative) {
        this.setRow(row);
        this.setColumn(column);
        this.setRowRelative(isRowRelative);
        this.setColRelative(isColumnRelative);
    }

    protected Ref2DPtgBase(Ref2DPtgBase other) {
        super(other);
    }

    protected Ref2DPtgBase(LittleEndianInput in) {
        this.readCoordinates(in);
    }

    protected Ref2DPtgBase(CellReference cr) {
        super(cr);
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(this.getSid() + this.getPtgClass());
        this.writeCoordinates(out);
    }

    @Override
    public final String toFormulaString() {
        return this.formatReferenceAsString();
    }

    protected abstract byte getSid();

    @Override
    public final int getSize() {
        return 5;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append(" [");
        sb.append(this.formatReferenceAsString());
        sb.append("]");
        return sb.toString();
    }
}

