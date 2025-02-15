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
import org.apache.poi.util.RecordFormatException;

public class EscherSplitMenuColorsRecord
extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.SPLIT_MENU_COLORS.typeID;
    private int field_1_color1;
    private int field_2_color2;
    private int field_3_color3;
    private int field_4_color4;

    public EscherSplitMenuColorsRecord() {
    }

    public EscherSplitMenuColorsRecord(EscherSplitMenuColorsRecord other) {
        super(other);
        this.field_1_color1 = other.field_1_color1;
        this.field_2_color2 = other.field_2_color2;
        this.field_3_color3 = other.field_3_color3;
        this.field_4_color4 = other.field_4_color4;
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = this.readHeader(data, offset);
        int pos = offset + 8;
        int size = 0;
        this.field_1_color1 = LittleEndian.getInt(data, pos + size);
        this.field_2_color2 = LittleEndian.getInt(data, pos + (size += 4));
        this.field_3_color3 = LittleEndian.getInt(data, pos + (size += 4));
        this.field_4_color4 = LittleEndian.getInt(data, pos + (size += 4));
        if ((bytesRemaining -= (size += 4)) != 0) {
            throw new RecordFormatException("Expecting no remaining data but got " + bytesRemaining + " byte(s).");
        }
        return 8 + size + bytesRemaining;
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        int pos = offset;
        LittleEndian.putShort(data, pos, this.getOptions());
        LittleEndian.putShort(data, pos += 2, this.getRecordId());
        int remainingBytes = this.getRecordSize() - 8;
        LittleEndian.putInt(data, pos += 2, remainingBytes);
        LittleEndian.putInt(data, pos += 4, this.field_1_color1);
        LittleEndian.putInt(data, pos += 4, this.field_2_color2);
        LittleEndian.putInt(data, pos += 4, this.field_3_color3);
        LittleEndian.putInt(data, pos += 4, this.field_4_color4);
        listener.afterRecordSerialize(pos += 4, this.getRecordId(), pos - offset, this);
        return this.getRecordSize();
    }

    @Override
    public int getRecordSize() {
        return 24;
    }

    @Override
    public short getRecordId() {
        return RECORD_ID;
    }

    @Override
    public String getRecordName() {
        return EscherRecordTypes.SPLIT_MENU_COLORS.recordName;
    }

    public int getColor1() {
        return this.field_1_color1;
    }

    public void setColor1(int field_1_color1) {
        this.field_1_color1 = field_1_color1;
    }

    public int getColor2() {
        return this.field_2_color2;
    }

    public void setColor2(int field_2_color2) {
        this.field_2_color2 = field_2_color2;
    }

    public int getColor3() {
        return this.field_3_color3;
    }

    public void setColor3(int field_3_color3) {
        this.field_3_color3 = field_3_color3;
    }

    public int getColor4() {
        return this.field_4_color4;
    }

    public void setColor4(int field_4_color4) {
        this.field_4_color4 = field_4_color4;
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "color1", this::getColor1, "color2", this::getColor2, "color3", this::getColor3, "color4", this::getColor4);
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.SPLIT_MENU_COLORS;
    }

    @Override
    public EscherSplitMenuColorsRecord copy() {
        return new EscherSplitMenuColorsRecord(this);
    }
}

