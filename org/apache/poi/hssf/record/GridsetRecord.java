/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class GridsetRecord
extends StandardRecord {
    public static final short sid = 130;
    public short field_1_gridset_flag;

    public GridsetRecord() {
    }

    public GridsetRecord(GridsetRecord other) {
        super(other);
        this.field_1_gridset_flag = other.field_1_gridset_flag;
    }

    public GridsetRecord(RecordInputStream in) {
        this.field_1_gridset_flag = in.readShort();
    }

    public void setGridset(boolean gridset) {
        this.field_1_gridset_flag = gridset ? (short)1 : 0;
    }

    public boolean getGridset() {
        return this.field_1_gridset_flag == 1;
    }

    @Override
    public String toString() {
        return "[GRIDSET]\n    .gridset        = " + this.getGridset() + "\n[/GRIDSET]\n";
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_gridset_flag);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 130;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public GridsetRecord clone() {
        return this.copy();
    }

    @Override
    public GridsetRecord copy() {
        return new GridsetRecord(this);
    }
}

