/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordFactory;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

public class EscherClientDataRecord
extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.CLIENT_DATA.typeID;
    private static final int MAX_RECORD_LENGTH = 100000;
    private static final byte[] EMPTY = new byte[0];
    private byte[] remainingData;

    public EscherClientDataRecord() {
    }

    public EscherClientDataRecord(EscherClientDataRecord other) {
        super(other);
        this.remainingData = other.remainingData == null ? null : (byte[])other.remainingData.clone();
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = this.readHeader(data, offset);
        int pos = offset + 8;
        this.remainingData = bytesRemaining == 0 ? EMPTY : IOUtils.safelyAllocate(bytesRemaining, 100000);
        System.arraycopy(data, pos, this.remainingData, 0, bytesRemaining);
        return 8 + bytesRemaining;
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        if (this.remainingData == null) {
            this.remainingData = EMPTY;
        }
        LittleEndian.putShort(data, offset, this.getOptions());
        LittleEndian.putShort(data, offset + 2, this.getRecordId());
        LittleEndian.putInt(data, offset + 4, this.remainingData.length);
        System.arraycopy(this.remainingData, 0, data, offset + 8, this.remainingData.length);
        int pos = offset + 8 + this.remainingData.length;
        listener.afterRecordSerialize(pos, this.getRecordId(), pos - offset, this);
        return pos - offset;
    }

    @Override
    public int getRecordSize() {
        return 8 + (this.remainingData == null ? 0 : this.remainingData.length);
    }

    @Override
    public short getRecordId() {
        return RECORD_ID;
    }

    @Override
    public String getRecordName() {
        return EscherRecordTypes.CLIENT_DATA.recordName;
    }

    public byte[] getRemainingData() {
        return this.remainingData;
    }

    public void setRemainingData(byte[] remainingData) {
        this.remainingData = remainingData == null ? new byte[]{} : (byte[])remainingData.clone();
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "remainingData", this::getRemainingData);
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.CLIENT_DATA;
    }

    @Override
    public EscherClientDataRecord copy() {
        return new EscherClientDataRecord(this);
    }
}

