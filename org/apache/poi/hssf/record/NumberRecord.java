/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.CellRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class NumberRecord
extends CellRecord {
    public static final short sid = 515;
    private double field_4_value;

    public NumberRecord() {
    }

    public NumberRecord(NumberRecord other) {
        super(other);
        this.field_4_value = other.field_4_value;
    }

    public NumberRecord(RecordInputStream in) {
        super(in);
        this.field_4_value = in.readDouble();
    }

    public void setValue(double value) {
        this.field_4_value = value;
    }

    public double getValue() {
        return this.field_4_value;
    }

    @Override
    protected String getRecordName() {
        return "NUMBER";
    }

    @Override
    protected void appendValueText(StringBuilder sb) {
        sb.append("  .value= ").append(NumberToTextConverter.toText(this.field_4_value));
    }

    @Override
    protected void serializeValue(LittleEndianOutput out) {
        out.writeDouble(this.getValue());
    }

    @Override
    protected int getValueDataSize() {
        return 8;
    }

    @Override
    public short getSid() {
        return 515;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public NumberRecord clone() {
        return this.copy();
    }

    @Override
    public NumberRecord copy() {
        return new NumberRecord(this);
    }
}

