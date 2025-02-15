/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class RefreshAllRecord
extends StandardRecord {
    public static final short sid = 439;
    private static final BitField refreshFlag = BitFieldFactory.getInstance(1);
    private int _options;

    private RefreshAllRecord(int options) {
        this._options = options;
    }

    private RefreshAllRecord(RefreshAllRecord other) {
        super(other);
        this._options = other._options;
    }

    public RefreshAllRecord(RecordInputStream in) {
        this(in.readUShort());
    }

    public RefreshAllRecord(boolean refreshAll) {
        this(0);
        this.setRefreshAll(refreshAll);
    }

    public void setRefreshAll(boolean refreshAll) {
        this._options = refreshFlag.setBoolean(this._options, refreshAll);
    }

    public boolean getRefreshAll() {
        return refreshFlag.isSet(this._options);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[REFRESHALL]\n");
        buffer.append("    .options      = ").append(HexDump.shortToHex(this._options)).append("\n");
        buffer.append("[/REFRESHALL]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._options);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 439;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public RefreshAllRecord clone() {
        return this.copy();
    }

    @Override
    public RefreshAllRecord copy() {
        return new RefreshAllRecord(this);
    }
}

