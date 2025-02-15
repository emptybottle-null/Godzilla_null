/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class HCenterRecord
extends StandardRecord {
    public static final short sid = 131;
    private short field_1_hcenter;

    public HCenterRecord() {
    }

    public HCenterRecord(HCenterRecord other) {
        super(other);
        this.field_1_hcenter = other.field_1_hcenter;
    }

    public HCenterRecord(RecordInputStream in) {
        this.field_1_hcenter = in.readShort();
    }

    public void setHCenter(boolean hc) {
        this.field_1_hcenter = (short)(hc ? 1 : 0);
    }

    public boolean getHCenter() {
        return this.field_1_hcenter == 1;
    }

    @Override
    public String toString() {
        return "[HCENTER]\n    .hcenter        = " + this.getHCenter() + "\n[/HCENTER]\n";
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_hcenter);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 131;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public HCenterRecord clone() {
        return this.copy();
    }

    @Override
    public HCenterRecord copy() {
        return new HCenterRecord(this);
    }
}

