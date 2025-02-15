/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ddf.EscherBlipRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordFactory;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.ddf.NullEscherSerializationListener;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

public final class EscherBSERecord
extends EscherRecord {
    private static final int MAX_RECORD_LENGTH = 100000;
    public static final short RECORD_ID = EscherRecordTypes.BSE.typeID;
    private byte field_1_blipTypeWin32;
    private byte field_2_blipTypeMacOS;
    private final byte[] field_3_uid = new byte[16];
    private short field_4_tag;
    private int field_5_size;
    private int field_6_ref;
    private int field_7_offset;
    private byte field_8_usage;
    private byte field_9_name;
    private byte field_10_unused2;
    private byte field_11_unused3;
    private EscherBlipRecord field_12_blipRecord;
    private byte[] _remainingData = new byte[0];

    public EscherBSERecord() {
        this.setRecordId(RECORD_ID);
    }

    public EscherBSERecord(EscherBSERecord other) {
        super(other);
        this.field_1_blipTypeWin32 = other.field_1_blipTypeWin32;
        this.field_2_blipTypeMacOS = other.field_2_blipTypeMacOS;
        System.arraycopy(other.field_3_uid, 0, this.field_3_uid, 0, this.field_3_uid.length);
        this.field_4_tag = other.field_4_tag;
        this.field_5_size = other.field_5_size;
        this.field_6_ref = other.field_6_ref;
        this.field_7_offset = other.field_7_offset;
        this.field_8_usage = other.field_8_usage;
        this.field_9_name = other.field_9_name;
        this.field_10_unused2 = other.field_10_unused2;
        this.field_11_unused3 = other.field_11_unused3;
        this.field_12_blipRecord = other.field_12_blipRecord.copy();
        this._remainingData = (byte[])other._remainingData.clone();
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = this.readHeader(data, offset);
        int pos = offset + 8;
        this.field_1_blipTypeWin32 = data[pos];
        this.field_2_blipTypeMacOS = data[pos + 1];
        System.arraycopy(data, pos + 2, this.field_3_uid, 0, 16);
        this.field_4_tag = LittleEndian.getShort(data, pos + 18);
        this.field_5_size = LittleEndian.getInt(data, pos + 20);
        this.field_6_ref = LittleEndian.getInt(data, pos + 24);
        this.field_7_offset = LittleEndian.getInt(data, pos + 28);
        this.field_8_usage = data[pos + 32];
        this.field_9_name = data[pos + 33];
        this.field_10_unused2 = data[pos + 34];
        this.field_11_unused3 = data[pos + 35];
        int bytesRead = 0;
        if ((bytesRemaining -= 36) > 0) {
            this.field_12_blipRecord = (EscherBlipRecord)recordFactory.createRecord(data, pos + 36);
            bytesRead = this.field_12_blipRecord.fillFields(data, pos + 36, recordFactory);
        }
        this._remainingData = IOUtils.safelyAllocate(bytesRemaining -= bytesRead, 100000);
        System.arraycopy(data, pos += 36 + bytesRead, this._remainingData, 0, bytesRemaining);
        return bytesRemaining + 8 + 36 + (this.field_12_blipRecord == null ? 0 : this.field_12_blipRecord.getRecordSize());
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        if (this._remainingData == null) {
            this._remainingData = new byte[0];
        }
        LittleEndian.putShort(data, offset, this.getOptions());
        LittleEndian.putShort(data, offset + 2, this.getRecordId());
        int blipSize = this.field_12_blipRecord == null ? 0 : this.field_12_blipRecord.getRecordSize();
        int remainingBytes = this._remainingData.length + 36 + blipSize;
        LittleEndian.putInt(data, offset + 4, remainingBytes);
        data[offset + 8] = this.field_1_blipTypeWin32;
        data[offset + 9] = this.field_2_blipTypeMacOS;
        System.arraycopy(this.field_3_uid, 0, data, offset + 10, 16);
        LittleEndian.putShort(data, offset + 26, this.field_4_tag);
        LittleEndian.putInt(data, offset + 28, this.field_5_size);
        LittleEndian.putInt(data, offset + 32, this.field_6_ref);
        LittleEndian.putInt(data, offset + 36, this.field_7_offset);
        data[offset + 40] = this.field_8_usage;
        data[offset + 41] = this.field_9_name;
        data[offset + 42] = this.field_10_unused2;
        data[offset + 43] = this.field_11_unused3;
        int bytesWritten = 0;
        if (this.field_12_blipRecord != null) {
            bytesWritten = this.field_12_blipRecord.serialize(offset + 44, data, new NullEscherSerializationListener());
        }
        System.arraycopy(this._remainingData, 0, data, offset + 44 + bytesWritten, this._remainingData.length);
        int pos = offset + 8 + 36 + this._remainingData.length + bytesWritten;
        listener.afterRecordSerialize(pos, this.getRecordId(), pos - offset, this);
        return pos - offset;
    }

    @Override
    public int getRecordSize() {
        int field_12_size = 0;
        if (this.field_12_blipRecord != null) {
            field_12_size = this.field_12_blipRecord.getRecordSize();
        }
        int remaining_size = 0;
        if (this._remainingData != null) {
            remaining_size = this._remainingData.length;
        }
        return 44 + field_12_size + remaining_size;
    }

    @Override
    public String getRecordName() {
        return EscherRecordTypes.BSE.recordName;
    }

    public byte getBlipTypeWin32() {
        return this.field_1_blipTypeWin32;
    }

    public PictureData.PictureType getPictureTypeWin32() {
        return PictureData.PictureType.forNativeID(this.field_1_blipTypeWin32);
    }

    public void setBlipTypeWin32(byte blipTypeWin32) {
        this.field_1_blipTypeWin32 = blipTypeWin32;
    }

    public byte getBlipTypeMacOS() {
        return this.field_2_blipTypeMacOS;
    }

    public PictureData.PictureType getPictureTypeMacOS() {
        return PictureData.PictureType.forNativeID(this.field_2_blipTypeMacOS);
    }

    public void setBlipTypeMacOS(byte blipTypeMacOS) {
        this.field_2_blipTypeMacOS = blipTypeMacOS;
    }

    public byte[] getUid() {
        return this.field_3_uid;
    }

    public void setUid(byte[] uid) {
        if (uid == null || uid.length != 16) {
            throw new IllegalArgumentException("uid must be byte[16]");
        }
        System.arraycopy(uid, 0, this.field_3_uid, 0, this.field_3_uid.length);
    }

    public short getTag() {
        return this.field_4_tag;
    }

    public void setTag(short tag) {
        this.field_4_tag = tag;
    }

    public int getSize() {
        return this.field_5_size;
    }

    public void setSize(int size) {
        this.field_5_size = size;
    }

    public int getRef() {
        return this.field_6_ref;
    }

    public void setRef(int ref) {
        this.field_6_ref = ref;
    }

    public int getOffset() {
        return this.field_7_offset;
    }

    public void setOffset(int offset) {
        this.field_7_offset = offset;
    }

    public byte getUsage() {
        return this.field_8_usage;
    }

    public void setUsage(byte usage) {
        this.field_8_usage = usage;
    }

    public byte getName() {
        return this.field_9_name;
    }

    public void setName(byte name) {
        this.field_9_name = name;
    }

    public byte getUnused2() {
        return this.field_10_unused2;
    }

    public void setUnused2(byte unused2) {
        this.field_10_unused2 = unused2;
    }

    public byte getUnused3() {
        return this.field_11_unused3;
    }

    public void setUnused3(byte unused3) {
        this.field_11_unused3 = unused3;
    }

    public EscherBlipRecord getBlipRecord() {
        return this.field_12_blipRecord;
    }

    public void setBlipRecord(EscherBlipRecord blipRecord) {
        this.field_12_blipRecord = blipRecord;
    }

    public byte[] getRemainingData() {
        return this._remainingData;
    }

    public void setRemainingData(byte[] remainingData) {
        this._remainingData = remainingData == null ? new byte[]{} : (byte[])remainingData.clone();
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap m = new LinkedHashMap(super.getGenericProperties());
        m.put("blipTypeWin32", this::getBlipTypeWin32);
        m.put("pictureTypeWin32", this::getPictureTypeWin32);
        m.put("blipTypeMacOS", this::getBlipTypeMacOS);
        m.put("pictureTypeMacOS", this::getPictureTypeMacOS);
        m.put("suid", this::getUid);
        m.put("tag", this::getTag);
        m.put("size", this::getSize);
        m.put("ref", this::getRef);
        m.put("offset", this::getOffset);
        m.put("usage", this::getUsage);
        m.put("name", this::getName);
        m.put("unused2", this::getUnused2);
        m.put("unused3", this::getUnused3);
        m.put("blipRecord", this::getBlipRecord);
        m.put("remainingData", this::getRemainingData);
        return Collections.unmodifiableMap(m);
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.BSE;
    }

    @Override
    public EscherBSERecord copy() {
        return new EscherBSERecord(this);
    }
}

