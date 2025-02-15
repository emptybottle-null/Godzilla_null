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

public final class UserSViewEnd
extends StandardRecord {
    public static final short sid = 427;
    private byte[] _rawData;

    public UserSViewEnd(UserSViewEnd other) {
        super(other);
        this._rawData = other._rawData == null ? null : (byte[])other._rawData.clone();
    }

    public UserSViewEnd(byte[] data) {
        this._rawData = data;
    }

    public UserSViewEnd(RecordInputStream in) {
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
        return 427;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[').append("USERSVIEWEND").append("] (0x");
        sb.append(Integer.toHexString(427).toUpperCase(Locale.ROOT)).append(")\n");
        sb.append("  rawData=").append(HexDump.toHex(this._rawData)).append("\n");
        sb.append("[/").append("USERSVIEWEND").append("]\n");
        return sb.toString();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public UserSViewEnd clone() {
        return this.copy();
    }

    @Override
    public UserSViewEnd copy() {
        return new UserSViewEnd(this);
    }
}

