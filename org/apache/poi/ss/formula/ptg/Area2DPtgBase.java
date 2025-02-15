/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.AreaPtgBase;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public abstract class Area2DPtgBase
extends AreaPtgBase {
    private static final int SIZE = 9;

    protected Area2DPtgBase(int firstRow, int lastRow, int firstColumn, int lastColumn, boolean firstRowRelative, boolean lastRowRelative, boolean firstColRelative, boolean lastColRelative) {
        super(firstRow, lastRow, firstColumn, lastColumn, firstRowRelative, lastRowRelative, firstColRelative, lastColRelative);
    }

    protected Area2DPtgBase(Area2DPtgBase other) {
        super(other);
    }

    protected Area2DPtgBase(AreaReference ar) {
        super(ar);
    }

    protected Area2DPtgBase(LittleEndianInput in) {
        this.readCoordinates(in);
    }

    protected abstract byte getSid();

    @Override
    public final void write(LittleEndianOutput out) {
        out.writeByte(this.getSid() + this.getPtgClass());
        this.writeCoordinates(out);
    }

    @Override
    public final int getSize() {
        return 9;
    }

    @Override
    public final String toFormulaString() {
        return this.formatReferenceAsString();
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

