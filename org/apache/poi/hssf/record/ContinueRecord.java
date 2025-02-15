/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class ContinueRecord
extends StandardRecord {
    public static final short sid = 60;
    private byte[] _data;

    public ContinueRecord(byte[] data) {
        this._data = (byte[])data.clone();
    }

    public ContinueRecord(ContinueRecord other) {
        super(other);
        this._data = other._data == null ? null : (byte[])other._data.clone();
    }

    @Override
    protected int getDataSize() {
        return this._data.length;
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.write(this._data);
    }

    public byte[] getData() {
        return this._data;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[CONTINUE RECORD]\n");
        buffer.append("    .data = ").append(HexDump.toHex(this._data)).append("\n");
        buffer.append("[/CONTINUE RECORD]\n");
        return buffer.toString();
    }

    @Override
    public short getSid() {
        return 60;
    }

    public ContinueRecord(RecordInputStream in) {
        this._data = in.readRemainder();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public ContinueRecord clone() {
        return this.copy();
    }

    @Override
    public ContinueRecord copy() {
        return new ContinueRecord(this);
    }
}

