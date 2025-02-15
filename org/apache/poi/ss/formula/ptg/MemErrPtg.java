/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class MemErrPtg
extends OperandPtg {
    public static final short sid = 39;
    private static final int SIZE = 7;
    private int field_1_reserved;
    private short field_2_subex_len;

    public MemErrPtg(MemErrPtg other) {
        super(other);
        this.field_1_reserved = other.field_1_reserved;
        this.field_2_subex_len = other.field_2_subex_len;
    }

    public MemErrPtg(LittleEndianInput in) {
        this.field_1_reserved = in.readInt();
        this.field_2_subex_len = in.readShort();
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(39 + this.getPtgClass());
        out.writeInt(this.field_1_reserved);
        out.writeShort(this.field_2_subex_len);
    }

    @Override
    public int getSize() {
        return 7;
    }

    @Override
    public String toFormulaString() {
        return "ERR#";
    }

    @Override
    public byte getDefaultOperandClass() {
        return 32;
    }

    @Override
    public MemErrPtg copy() {
        return new MemErrPtg(this);
    }
}

