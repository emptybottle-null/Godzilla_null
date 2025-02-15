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
import org.apache.poi.util.RecordFormatException;

public final class EscherTextboxRecord
extends EscherRecord {
    private static final int MAX_RECORD_LENGTH = 100000;
    public static final short RECORD_ID = EscherRecordTypes.CLIENT_TEXTBOX.typeID;
    private static final byte[] NO_BYTES = new byte[0];
    private byte[] thedata = NO_BYTES;

    public EscherTextboxRecord() {
    }

    public EscherTextboxRecord(EscherTextboxRecord other) {
        super(other);
        this.thedata = other.thedata == null ? NO_BYTES : (byte[])other.thedata.clone();
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = this.readHeader(data, offset);
        this.thedata = IOUtils.safelyAllocate(bytesRemaining, 100000);
        System.arraycopy(data, offset + 8, this.thedata, 0, bytesRemaining);
        return bytesRemaining + 8;
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        LittleEndian.putShort(data, offset, this.getOptions());
        LittleEndian.putShort(data, offset + 2, this.getRecordId());
        int remainingBytes = this.thedata.length;
        LittleEndian.putInt(data, offset + 4, remainingBytes);
        System.arraycopy(this.thedata, 0, data, offset + 8, this.thedata.length);
        int pos = offset + 8 + this.thedata.length;
        listener.afterRecordSerialize(pos, this.getRecordId(), pos - offset, this);
        int size = pos - offset;
        if (size != this.getRecordSize()) {
            throw new RecordFormatException(size + " bytes written but getRecordSize() reports " + this.getRecordSize());
        }
        return size;
    }

    public byte[] getData() {
        return this.thedata;
    }

    public void setData(byte[] b, int start, int length) {
        this.thedata = IOUtils.safelyAllocate(length, 100000);
        System.arraycopy(b, start, this.thedata, 0, length);
    }

    public void setData(byte[] b) {
        this.setData(b, 0, b.length);
    }

    @Override
    public int getRecordSize() {
        return 8 + this.thedata.length;
    }

    @Override
    public String getRecordName() {
        return EscherRecordTypes.CLIENT_TEXTBOX.recordName;
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.CLIENT_TEXTBOX;
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "isContainer", this::isContainerRecord, "extraData", this::getData);
    }

    @Override
    public EscherTextboxRecord copy() {
        return new EscherTextboxRecord(this);
    }
}

