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
import org.apache.poi.util.LittleEndian;

public class EscherSpRecord
extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.SP.typeID;
    public static final int FLAG_GROUP = 1;
    public static final int FLAG_CHILD = 2;
    public static final int FLAG_PATRIARCH = 4;
    public static final int FLAG_DELETED = 8;
    public static final int FLAG_OLESHAPE = 16;
    public static final int FLAG_HAVEMASTER = 32;
    public static final int FLAG_FLIPHORIZ = 64;
    public static final int FLAG_FLIPVERT = 128;
    public static final int FLAG_CONNECTOR = 256;
    public static final int FLAG_HAVEANCHOR = 512;
    public static final int FLAG_BACKGROUND = 1024;
    public static final int FLAG_HASSHAPETYPE = 2048;
    private static final int[] FLAGS_MASKS = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
    private static final String[] FLAGS_NAMES = new String[]{"GROUP", "CHILD", "PATRIARCH", "DELETED", "OLESHAPE", "HAVEMASTER", "FLIPHORIZ", "FLIPVERT", "CONNECTOR", "HAVEANCHOR", "BACKGROUND", "HASSHAPETYPE"};
    private int field_1_shapeId;
    private int field_2_flags;

    public EscherSpRecord() {
    }

    public EscherSpRecord(EscherSpRecord other) {
        super(other);
        this.field_1_shapeId = other.field_1_shapeId;
        this.field_2_flags = other.field_2_flags;
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        this.readHeader(data, offset);
        int pos = offset + 8;
        int size = 0;
        this.field_1_shapeId = LittleEndian.getInt(data, pos + size);
        this.field_2_flags = LittleEndian.getInt(data, pos + (size += 4));
        size += 4;
        return this.getRecordSize();
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        LittleEndian.putShort(data, offset, this.getOptions());
        LittleEndian.putShort(data, offset + 2, this.getRecordId());
        int remainingBytes = 8;
        LittleEndian.putInt(data, offset + 4, remainingBytes);
        LittleEndian.putInt(data, offset + 8, this.field_1_shapeId);
        LittleEndian.putInt(data, offset + 12, this.field_2_flags);
        listener.afterRecordSerialize(offset + this.getRecordSize(), this.getRecordId(), this.getRecordSize(), this);
        return 16;
    }

    @Override
    public int getRecordSize() {
        return 16;
    }

    @Override
    public short getRecordId() {
        return RECORD_ID;
    }

    @Override
    public String getRecordName() {
        return EscherRecordTypes.SP.recordName;
    }

    private String decodeFlags(int flags) {
        StringBuilder result = new StringBuilder();
        result.append((flags & 1) != 0 ? "|GROUP" : "");
        result.append((flags & 2) != 0 ? "|CHILD" : "");
        result.append((flags & 4) != 0 ? "|PATRIARCH" : "");
        result.append((flags & 8) != 0 ? "|DELETED" : "");
        result.append((flags & 0x10) != 0 ? "|OLESHAPE" : "");
        result.append((flags & 0x20) != 0 ? "|HAVEMASTER" : "");
        result.append((flags & 0x40) != 0 ? "|FLIPHORIZ" : "");
        result.append((flags & 0x80) != 0 ? "|FLIPVERT" : "");
        result.append((flags & 0x100) != 0 ? "|CONNECTOR" : "");
        result.append((flags & 0x200) != 0 ? "|HAVEANCHOR" : "");
        result.append((flags & 0x400) != 0 ? "|BACKGROUND" : "");
        result.append((flags & 0x800) != 0 ? "|HASSHAPETYPE" : "");
        if (result.length() > 0) {
            result.deleteCharAt(0);
        }
        return result.toString();
    }

    public int getShapeId() {
        return this.field_1_shapeId;
    }

    public void setShapeId(int field_1_shapeId) {
        this.field_1_shapeId = field_1_shapeId;
    }

    public int getFlags() {
        return this.field_2_flags;
    }

    public void setFlags(int field_2_flags) {
        this.field_2_flags = field_2_flags;
    }

    public short getShapeType() {
        return this.getInstance();
    }

    public void setShapeType(short value) {
        this.setInstance(value);
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "shapeType", this::getShapeType, "shapeId", this::getShapeId, "flags", GenericRecordUtil.getBitsAsString(this::getFlags, FLAGS_MASKS, FLAGS_NAMES));
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.SP;
    }

    @Override
    public EscherSpRecord copy() {
        return new EscherSpRecord(this);
    }
}

