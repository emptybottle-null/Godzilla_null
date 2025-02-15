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

public final class ProtectRecord
extends StandardRecord {
    public static final short sid = 18;
    private static final BitField protectFlag = BitFieldFactory.getInstance(1);
    private int _options;

    private ProtectRecord(int options) {
        this._options = options;
    }

    private ProtectRecord(ProtectRecord other) {
        super(other);
        this._options = other._options;
    }

    public ProtectRecord(boolean isProtected) {
        this(0);
        this.setProtect(isProtected);
    }

    public ProtectRecord(RecordInputStream in) {
        this(in.readShort());
    }

    public void setProtect(boolean protect) {
        this._options = protectFlag.setBoolean(this._options, protect);
    }

    public boolean getProtect() {
        return protectFlag.isSet(this._options);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[PROTECT]\n");
        buffer.append("    .options = ").append(HexDump.shortToHex(this._options)).append("\n");
        buffer.append("[/PROTECT]\n");
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
        return 18;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public ProtectRecord clone() {
        return this.copy();
    }

    @Override
    public ProtectRecord copy() {
        return new ProtectRecord(this);
    }
}

