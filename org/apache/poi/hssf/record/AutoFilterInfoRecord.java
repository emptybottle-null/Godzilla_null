/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class AutoFilterInfoRecord
extends StandardRecord {
    public static final short sid = 157;
    private short _cEntries;

    public AutoFilterInfoRecord() {
    }

    public AutoFilterInfoRecord(AutoFilterInfoRecord other) {
        super(other);
        this._cEntries = other._cEntries;
    }

    public AutoFilterInfoRecord(RecordInputStream in) {
        this._cEntries = in.readShort();
    }

    public void setNumEntries(short num) {
        this._cEntries = num;
    }

    public short getNumEntries() {
        return this._cEntries;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[AUTOFILTERINFO]\n");
        buffer.append("    .numEntries          = ").append(this._cEntries).append("\n");
        buffer.append("[/AUTOFILTERINFO]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._cEntries);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 157;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public AutoFilterInfoRecord clone() {
        return this.copy();
    }

    @Override
    public AutoFilterInfoRecord copy() {
        return new AutoFilterInfoRecord(this);
    }
}

