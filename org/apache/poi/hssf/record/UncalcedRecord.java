/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;

public final class UncalcedRecord
extends StandardRecord {
    public static final short sid = 94;
    private short _reserved;

    public UncalcedRecord() {
        this._reserved = 0;
    }

    public UncalcedRecord(UncalcedRecord other) {
        super(other);
        this._reserved = other._reserved;
    }

    @Override
    public short getSid() {
        return 94;
    }

    public UncalcedRecord(RecordInputStream in) {
        this._reserved = in.readShort();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[UNCALCED]\n");
        buffer.append("    _reserved: ").append(this._reserved).append('\n');
        buffer.append("[/UNCALCED]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._reserved);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    public static int getStaticRecordSize() {
        return 6;
    }

    @Override
    public UncalcedRecord copy() {
        return new UncalcedRecord(this);
    }
}

