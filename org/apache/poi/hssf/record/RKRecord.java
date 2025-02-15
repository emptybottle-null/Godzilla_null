/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.CellRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.util.RKUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class RKRecord
extends CellRecord {
    public static final short sid = 638;
    public static final short RK_IEEE_NUMBER = 0;
    public static final short RK_IEEE_NUMBER_TIMES_100 = 1;
    public static final short RK_INTEGER = 2;
    public static final short RK_INTEGER_TIMES_100 = 3;
    private int field_4_rk_number;

    private RKRecord() {
    }

    public RKRecord(RKRecord other) {
        super(other);
        this.field_4_rk_number = other.field_4_rk_number;
    }

    public RKRecord(RecordInputStream in) {
        super(in);
        this.field_4_rk_number = in.readInt();
    }

    public double getRKNumber() {
        return RKUtil.decodeNumber(this.field_4_rk_number);
    }

    @Override
    protected String getRecordName() {
        return "RK";
    }

    @Override
    protected void appendValueText(StringBuilder sb) {
        sb.append("  .value= ").append(this.getRKNumber());
    }

    @Override
    protected void serializeValue(LittleEndianOutput out) {
        out.writeInt(this.field_4_rk_number);
    }

    @Override
    protected int getValueDataSize() {
        return 4;
    }

    @Override
    public short getSid() {
        return 638;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public RKRecord clone() {
        return this.copy();
    }

    @Override
    public RKRecord copy() {
        return new RKRecord(this);
    }
}

