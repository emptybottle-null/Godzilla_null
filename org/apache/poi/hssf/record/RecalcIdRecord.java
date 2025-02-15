/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;

public final class RecalcIdRecord
extends StandardRecord {
    public static final short sid = 449;
    private final int _reserved0;
    private int _engineId;

    public RecalcIdRecord() {
        this._reserved0 = 0;
        this._engineId = 0;
    }

    public RecalcIdRecord(RecalcIdRecord other) {
        this._reserved0 = other._reserved0;
        this._engineId = other._engineId;
    }

    public RecalcIdRecord(RecordInputStream in) {
        in.readUShort();
        this._reserved0 = in.readUShort();
        this._engineId = in.readInt();
    }

    public boolean isNeeded() {
        return true;
    }

    public void setEngineId(int val) {
        this._engineId = val;
    }

    public int getEngineId() {
        return this._engineId;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[RECALCID]\n");
        buffer.append("    .reserved = ").append(HexDump.shortToHex(this._reserved0)).append("\n");
        buffer.append("    .engineId = ").append(HexDump.intToHex(this._engineId)).append("\n");
        buffer.append("[/RECALCID]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(449);
        out.writeShort(this._reserved0);
        out.writeInt(this._engineId);
    }

    @Override
    protected int getDataSize() {
        return 8;
    }

    @Override
    public short getSid() {
        return 449;
    }

    @Override
    public RecalcIdRecord copy() {
        return new RecalcIdRecord(this);
    }
}

