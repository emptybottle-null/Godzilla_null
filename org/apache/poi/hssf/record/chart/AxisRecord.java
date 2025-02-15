/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class AxisRecord
extends StandardRecord {
    public static final short sid = 4125;
    public static final short AXIS_TYPE_CATEGORY_OR_X_AXIS = 0;
    public static final short AXIS_TYPE_VALUE_AXIS = 1;
    public static final short AXIS_TYPE_SERIES_AXIS = 2;
    private short field_1_axisType;
    private int field_2_reserved1;
    private int field_3_reserved2;
    private int field_4_reserved3;
    private int field_5_reserved4;

    public AxisRecord() {
    }

    public AxisRecord(AxisRecord other) {
        super(other);
        this.field_1_axisType = other.field_1_axisType;
        this.field_2_reserved1 = other.field_2_reserved1;
        this.field_3_reserved2 = other.field_3_reserved2;
        this.field_4_reserved3 = other.field_4_reserved3;
        this.field_5_reserved4 = other.field_5_reserved4;
    }

    public AxisRecord(RecordInputStream in) {
        this.field_1_axisType = in.readShort();
        this.field_2_reserved1 = in.readInt();
        this.field_3_reserved2 = in.readInt();
        this.field_4_reserved3 = in.readInt();
        this.field_5_reserved4 = in.readInt();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[AXIS]\n");
        buffer.append("    .axisType             = ").append("0x").append(HexDump.toHex(this.getAxisType())).append(" (").append(this.getAxisType()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .reserved1            = ").append("0x").append(HexDump.toHex(this.getReserved1())).append(" (").append(this.getReserved1()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .reserved2            = ").append("0x").append(HexDump.toHex(this.getReserved2())).append(" (").append(this.getReserved2()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .reserved3            = ").append("0x").append(HexDump.toHex(this.getReserved3())).append(" (").append(this.getReserved3()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .reserved4            = ").append("0x").append(HexDump.toHex(this.getReserved4())).append(" (").append(this.getReserved4()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("[/AXIS]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_axisType);
        out.writeInt(this.field_2_reserved1);
        out.writeInt(this.field_3_reserved2);
        out.writeInt(this.field_4_reserved3);
        out.writeInt(this.field_5_reserved4);
    }

    @Override
    protected int getDataSize() {
        return 18;
    }

    @Override
    public short getSid() {
        return 4125;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public AxisRecord clone() {
        return this.copy();
    }

    public short getAxisType() {
        return this.field_1_axisType;
    }

    public void setAxisType(short field_1_axisType) {
        this.field_1_axisType = field_1_axisType;
    }

    public int getReserved1() {
        return this.field_2_reserved1;
    }

    public void setReserved1(int field_2_reserved1) {
        this.field_2_reserved1 = field_2_reserved1;
    }

    public int getReserved2() {
        return this.field_3_reserved2;
    }

    public void setReserved2(int field_3_reserved2) {
        this.field_3_reserved2 = field_3_reserved2;
    }

    public int getReserved3() {
        return this.field_4_reserved3;
    }

    public void setReserved3(int field_4_reserved3) {
        this.field_4_reserved3 = field_4_reserved3;
    }

    public int getReserved4() {
        return this.field_5_reserved4;
    }

    public void setReserved4(int field_5_reserved4) {
        this.field_5_reserved4 = field_5_reserved4;
    }

    @Override
    public AxisRecord copy() {
        return new AxisRecord(this);
    }
}

