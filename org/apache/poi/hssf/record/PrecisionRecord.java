/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;

public final class PrecisionRecord
extends StandardRecord {
    public static final short sid = 14;
    public short field_1_precision;

    public PrecisionRecord() {
    }

    public PrecisionRecord(PrecisionRecord other) {
        super(other);
        this.field_1_precision = other.field_1_precision;
    }

    public PrecisionRecord(RecordInputStream in) {
        this.field_1_precision = in.readShort();
    }

    public void setFullPrecision(boolean fullprecision) {
        this.field_1_precision = (short)(fullprecision ? 1 : 0);
    }

    public boolean getFullPrecision() {
        return this.field_1_precision == 1;
    }

    @Override
    public String toString() {
        return "[PRECISION]\n    .precision       = " + this.getFullPrecision() + "\n[/PRECISION]\n";
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_precision);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 14;
    }

    @Override
    public PrecisionRecord copy() {
        return new PrecisionRecord(this);
    }
}

