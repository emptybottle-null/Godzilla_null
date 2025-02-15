/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class CalcCountRecord
extends StandardRecord {
    public static final short sid = 12;
    private short field_1_iterations;

    public CalcCountRecord() {
    }

    public CalcCountRecord(CalcCountRecord other) {
        super(other);
        this.field_1_iterations = other.field_1_iterations;
    }

    public CalcCountRecord(RecordInputStream in) {
        this.field_1_iterations = in.readShort();
    }

    public void setIterations(short iterations) {
        this.field_1_iterations = iterations;
    }

    public short getIterations() {
        return this.field_1_iterations;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[CALCCOUNT]\n");
        buffer.append("    .iterations     = ").append(Integer.toHexString(this.getIterations())).append("\n");
        buffer.append("[/CALCCOUNT]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.getIterations());
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 12;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public CalcCountRecord clone() {
        return this.copy();
    }

    @Override
    public CalcCountRecord copy() {
        return new CalcCountRecord(this);
    }
}

