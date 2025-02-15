/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class MemFuncPtg
extends OperandPtg {
    public static final byte sid = 41;
    private final int field_1_len_ref_subexpression;

    public MemFuncPtg(LittleEndianInput in) {
        this(in.readUShort());
    }

    public MemFuncPtg(int subExprLen) {
        this.field_1_len_ref_subexpression = subExprLen;
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(41 + this.getPtgClass());
        out.writeShort(this.field_1_len_ref_subexpression);
    }

    @Override
    public String toFormulaString() {
        return "";
    }

    @Override
    public byte getDefaultOperandClass() {
        return 0;
    }

    public int getNumberOfOperands() {
        return this.field_1_len_ref_subexpression;
    }

    public int getLenRefSubexpression() {
        return this.field_1_len_ref_subexpression;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(this.getClass().getName()).append(" [len=");
        sb.append(this.field_1_len_ref_subexpression);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public MemFuncPtg copy() {
        return this;
    }
}

