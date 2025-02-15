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

public final class WindowProtectRecord
extends StandardRecord {
    public static final short sid = 25;
    private static final BitField settingsProtectedFlag = BitFieldFactory.getInstance(1);
    private int _options;

    public WindowProtectRecord(int options) {
        this._options = options;
    }

    public WindowProtectRecord(WindowProtectRecord other) {
        super(other);
        this._options = other._options;
    }

    public WindowProtectRecord(RecordInputStream in) {
        this(in.readUShort());
    }

    public WindowProtectRecord(boolean protect) {
        this(0);
        this.setProtect(protect);
    }

    public void setProtect(boolean protect) {
        this._options = settingsProtectedFlag.setBoolean(this._options, protect);
    }

    public boolean getProtect() {
        return settingsProtectedFlag.isSet(this._options);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[WINDOWPROTECT]\n");
        buffer.append("    .options = ").append(HexDump.shortToHex(this._options)).append("\n");
        buffer.append("[/WINDOWPROTECT]\n");
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
        return 25;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public WindowProtectRecord clone() {
        return this.copy();
    }

    @Override
    public WindowProtectRecord copy() {
        return new WindowProtectRecord(this);
    }
}

