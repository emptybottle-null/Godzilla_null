/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class SCLRecord
extends StandardRecord {
    public static final short sid = 160;
    private short field_1_numerator;
    private short field_2_denominator;

    public SCLRecord() {
    }

    public SCLRecord(SCLRecord other) {
        super(other);
        this.field_1_numerator = other.field_1_numerator;
        this.field_2_denominator = other.field_2_denominator;
    }

    public SCLRecord(RecordInputStream in) {
        this.field_1_numerator = in.readShort();
        this.field_2_denominator = in.readShort();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[SCL]\n");
        buffer.append("    .numerator            = ").append("0x").append(HexDump.toHex(this.getNumerator())).append(" (").append(this.getNumerator()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .denominator          = ").append("0x").append(HexDump.toHex(this.getDenominator())).append(" (").append(this.getDenominator()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("[/SCL]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_numerator);
        out.writeShort(this.field_2_denominator);
    }

    @Override
    protected int getDataSize() {
        return 4;
    }

    @Override
    public short getSid() {
        return 160;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public SCLRecord clone() {
        return this.copy();
    }

    @Override
    public SCLRecord copy() {
        return new SCLRecord(this);
    }

    public short getNumerator() {
        return this.field_1_numerator;
    }

    public void setNumerator(short field_1_numerator) {
        this.field_1_numerator = field_1_numerator;
    }

    public short getDenominator() {
        return this.field_2_denominator;
    }

    public void setDenominator(short field_2_denominator) {
        this.field_2_denominator = field_2_denominator;
    }
}

