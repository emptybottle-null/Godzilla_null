/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.util.Locale;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class UserSViewBegin
extends StandardRecord {
    public static final short sid = 426;
    private byte[] _rawData;

    public UserSViewBegin(UserSViewBegin other) {
        super(other);
        this._rawData = other._rawData == null ? null : (byte[])other._rawData.clone();
    }

    public UserSViewBegin(byte[] data) {
        this._rawData = data;
    }

    public UserSViewBegin(RecordInputStream in) {
        this._rawData = in.readRemainder();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.write(this._rawData);
    }

    @Override
    protected int getDataSize() {
        return this._rawData.length;
    }

    @Override
    public short getSid() {
        return 426;
    }

    public byte[] getGuid() {
        byte[] guid = new byte[16];
        System.arraycopy(this._rawData, 0, guid, 0, guid.length);
        return guid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append("USERSVIEWBEGIN").append("] (0x");
        sb.append(Integer.toHexString(426).toUpperCase(Locale.ROOT)).append(")\n");
        sb.append("  rawData=").append(HexDump.toHex(this._rawData)).append("\n");
        sb.append("[/").append("USERSVIEWBEGIN").append("]\n");
        return sb.toString();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public UserSViewBegin clone() {
        return this.copy();
    }

    @Override
    public UserSViewBegin copy() {
        return new UserSViewBegin(this);
    }
}

