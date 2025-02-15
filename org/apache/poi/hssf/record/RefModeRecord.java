/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class RefModeRecord
extends StandardRecord {
    public static final short sid = 15;
    public static final short USE_A1_MODE = 1;
    public static final short USE_R1C1_MODE = 0;
    private short field_1_mode;

    public RefModeRecord() {
    }

    public RefModeRecord(RefModeRecord other) {
        this.field_1_mode = other.field_1_mode;
    }

    public RefModeRecord(RecordInputStream in) {
        this.field_1_mode = in.readShort();
    }

    public void setMode(short mode) {
        this.field_1_mode = mode;
    }

    public short getMode() {
        return this.field_1_mode;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[REFMODE]\n");
        buffer.append("    .mode           = ").append(Integer.toHexString(this.getMode())).append("\n");
        buffer.append("[/REFMODE]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.getMode());
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 15;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public RefModeRecord clone() {
        return this.copy();
    }

    @Override
    public RefModeRecord copy() {
        return new RefModeRecord();
    }
}

