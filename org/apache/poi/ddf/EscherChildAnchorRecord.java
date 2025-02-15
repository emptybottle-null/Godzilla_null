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

public class EscherChildAnchorRecord
extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.CHILD_ANCHOR.typeID;
    private int field_1_dx1;
    private int field_2_dy1;
    private int field_3_dx2;
    private int field_4_dy2;

    public EscherChildAnchorRecord() {
    }

    public EscherChildAnchorRecord(EscherChildAnchorRecord other) {
        super(other);
        this.field_1_dx1 = other.field_1_dx1;
        this.field_2_dy1 = other.field_2_dy1;
        this.field_3_dx2 = other.field_3_dx2;
        this.field_4_dy2 = other.field_4_dy2;
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = this.readHeader(data, offset);
        int pos = offset + 8;
        int size = 0;
        switch (bytesRemaining) {
            case 16: {
                this.field_1_dx1 = LittleEndian.getInt(data, pos + size);
                this.field_2_dy1 = LittleEndian.getInt(data, pos + (size += 4));
                this.field_3_dx2 = LittleEndian.getInt(data, pos + (size += 4));
                this.field_4_dy2 = LittleEndian.getInt(data, pos + (size += 4));
                size += 4;
                break;
            }
            case 8: {
                this.field_1_dx1 = LittleEndian.getShort(data, pos + size);
                this.field_2_dy1 = LittleEndian.getShort(data, pos + (size += 2));
                this.field_3_dx2 = LittleEndian.getShort(data, pos + (size += 2));
                this.field_4_dy2 = LittleEndian.getShort(data, pos + (size += 2));
                size += 2;
                break;
            }
            default: {
                throw new RuntimeException("Invalid EscherChildAnchorRecord - neither 8 nor 16 bytes.");
            }
        }
        return 8 + size;
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        int pos = offset;
        LittleEndian.putShort(data, pos, this.getOptions());
        LittleEndian.putShort(data, pos += 2, this.getRecordId());
        LittleEndian.putInt(data, pos += 2, this.getRecordSize() - 8);
        LittleEndian.putInt(data, pos += 4, this.field_1_dx1);
        LittleEndian.putInt(data, pos += 4, this.field_2_dy1);
        LittleEndian.putInt(data, pos += 4, this.field_3_dx2);
        LittleEndian.putInt(data, pos += 4, this.field_4_dy2);
        listener.afterRecordSerialize(pos += 4, this.getRecordId(), pos - offset, this);
        return pos - offset;
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
        return EscherRecordTypes.CHILD_ANCHOR.recordName;
    }

    public int getDx1() {
        return this.field_1_dx1;
    }

    public void setDx1(int field_1_dx1) {
        this.field_1_dx1 = field_1_dx1;
    }

    public int getDy1() {
        return this.field_2_dy1;
    }

    public void setDy1(int field_2_dy1) {
        this.field_2_dy1 = field_2_dy1;
    }

    public int getDx2() {
        return this.field_3_dx2;
    }

    public void setDx2(int field_3_dx2) {
        this.field_3_dx2 = field_3_dx2;
    }

    public int getDy2() {
        return this.field_4_dy2;
    }

    public void setDy2(int field_4_dy2) {
        this.field_4_dy2 = field_4_dy2;
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "x1", this::getDx1, "y1", this::getDy1, "x2", this::getDx2, "y2", this::getDy2);
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.CHILD_ANCHOR;
    }

    @Override
    public EscherChildAnchorRecord copy() {
        return new EscherChildAnchorRecord(this);
    }
}

