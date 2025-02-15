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

public class EscherBlipRecord
extends EscherRecord {
    private static final int MAX_RECORD_LENGTH = 0x6400000;
    public static final short RECORD_ID_START = EscherRecordTypes.BLIP_START.typeID;
    public static final short RECORD_ID_END = EscherRecordTypes.BLIP_END.typeID;
    private static final int HEADER_SIZE = 8;
    private byte[] field_pictureData;

    public EscherBlipRecord() {
    }

    public EscherBlipRecord(EscherBlipRecord other) {
        super(other);
        this.field_pictureData = other.field_pictureData == null ? null : (byte[])other.field_pictureData.clone();
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesAfterHeader = this.readHeader(data, offset);
        int pos = offset + 8;
        this.field_pictureData = IOUtils.safelyAllocate(bytesAfterHeader, 0x6400000);
        System.arraycopy(data, pos, this.field_pictureData, 0, bytesAfterHeader);
        return bytesAfterHeader + 8;
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        LittleEndian.putShort(data, offset, this.getOptions());
        LittleEndian.putShort(data, offset + 2, this.getRecordId());
        System.arraycopy(this.field_pictureData, 0, data, offset + 4, this.field_pictureData.length);
        listener.afterRecordSerialize(offset + 4 + this.field_pictureData.length, this.getRecordId(), this.field_pictureData.length + 4, this);
        return this.field_pictureData.length + 4;
    }

    @Override
    public int getRecordSize() {
        return this.field_pictureData.length + 8;
    }

    @Override
    public String getRecordName() {
        EscherRecordTypes t;
        return ((t = EscherRecordTypes.forTypeID((int)this.getRecordId())) != EscherRecordTypes.UNKNOWN ? t : EscherRecordTypes.BLIP_START).recordName;
    }

    public byte[] getPicturedata() {
        return this.field_pictureData;
    }

    public void setPictureData(byte[] pictureData) {
        this.setPictureData(pictureData, 0, pictureData == null ? 0 : pictureData.length);
    }

    public void setPictureData(byte[] pictureData, int offset, int length) {
        if (pictureData == null || offset < 0 || length < 0 || pictureData.length < offset + length) {
            throw new IllegalArgumentException("picture data can't be null");
        }
        this.field_pictureData = IOUtils.safelyAllocate(length, 0x6400000);
        System.arraycopy(pictureData, offset, this.field_pictureData, 0, length);
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "pictureData", this::getPicturedata);
    }

    @Override
    public Enum getGenericRecordType() {
        EscherRecordTypes t = EscherRecordTypes.forTypeID(this.getRecordId());
        return t != EscherRecordTypes.UNKNOWN ? t : EscherRecordTypes.BLIP_START;
    }

    @Override
    public EscherBlipRecord copy() {
        return new EscherBlipRecord(this);
    }
}

