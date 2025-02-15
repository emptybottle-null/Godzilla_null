/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class MemAreaPtg
extends OperandPtg {
    public static final short sid = 38;
    private static final int SIZE = 7;
    private final int field_1_reserved;
    private final int field_2_subex_len;

    public MemAreaPtg(int subexLen) {
        this.field_1_reserved = 0;
        this.field_2_subex_len = subexLen;
    }

    public MemAreaPtg(LittleEndianInput in) {
        this.field_1_reserved = in.readInt();
        this.field_2_subex_len = in.readShort();
    }

    public int getLenRefSubexpression() {
        return this.field_2_subex_len;
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(38 + this.getPtgClass());
        out.writeInt(this.field_1_reserved);
        out.writeShort(this.field_2_subex_len);
    }

    @Override
    public int getSize() {
        return 7;
    }

    @Override
    public String toFormulaString() {
        return "";
    }

    @Override
    public byte getDefaultOperandClass() {
        return 32;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(this.getClass().getName()).append(" [len=");
        sb.append(this.field_2_subex_len);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public MemAreaPtg copy() {
        return this;
    }
}

