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

public final class ProtectionRev4Record
extends StandardRecord {
    public static final short sid = 431;
    private static final BitField protectedFlag = BitFieldFactory.getInstance(1);
    private int _options;

    private ProtectionRev4Record(int options) {
        this._options = options;
    }

    private ProtectionRev4Record(ProtectionRev4Record other) {
        super(other);
        this._options = other._options;
    }

    public ProtectionRev4Record(boolean protect) {
        this(0);
        this.setProtect(protect);
    }

    public ProtectionRev4Record(RecordInputStream in) {
        this(in.readUShort());
    }

    public void setProtect(boolean protect) {
        this._options = protectedFlag.setBoolean(this._options, protect);
    }

    public boolean getProtect() {
        return protectedFlag.isSet(this._options);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[PROT4REV]\n");
        buffer.append("    .options = ").append(HexDump.shortToHex(this._options)).append("\n");
        buffer.append("[/PROT4REV]\n");
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
        return 431;
    }

    @Override
    public ProtectionRev4Record copy() {
        return new ProtectionRev4Record(this);
    }
}

