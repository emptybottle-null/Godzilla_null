/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Locale;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class HeaderFooterRecord
extends StandardRecord {
    public static final short sid = 2204;
    private static final byte[] BLANK_GUID = new byte[16];
    private byte[] _rawData;

    public HeaderFooterRecord(byte[] data) {
        this._rawData = data;
    }

    public HeaderFooterRecord(HeaderFooterRecord other) {
        super(other);
        this._rawData = other._rawData == null ? null : (byte[])other._rawData.clone();
    }

    public HeaderFooterRecord(RecordInputStream in) {
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
        return 2204;
    }

    public byte[] getGuid() {
        byte[] guid = new byte[16];
        System.arraycopy(this._rawData, 12, guid, 0, guid.length);
        return guid;
    }

    public boolean isCurrentSheet() {
        return Arrays.equals(this.getGuid(), BLANK_GUID);
    }

    @Override
    public String toString() {
        return "[HEADERFOOTER] (0x" + Integer.toHexString(2204).toUpperCase(Locale.ROOT) + ")\n  rawData=" + HexDump.toHex(this._rawData) + "\n[/HEADERFOOTER]\n";
    }

    @Deprecated
    @Removal(version="5.0.0")
    public HeaderFooterRecord clone() {
        return this.copy();
    }

    @Override
    public HeaderFooterRecord copy() {
        return new HeaderFooterRecord(this);
    }
}

