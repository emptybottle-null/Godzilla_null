/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class AxisParentRecord
extends StandardRecord {
    public static final short sid = 4161;
    public static final short AXIS_TYPE_MAIN = 0;
    public static final short AXIS_TYPE_SECONDARY = 1;
    private short field_1_axisType;
    private int field_2_x;
    private int field_3_y;
    private int field_4_width;
    private int field_5_height;

    public AxisParentRecord() {
    }

    public AxisParentRecord(AxisParentRecord other) {
        super(other);
        this.field_1_axisType = other.field_1_axisType;
        this.field_2_x = other.field_2_x;
        this.field_3_y = other.field_3_y;
        this.field_4_width = other.field_4_width;
        this.field_5_height = other.field_5_height;
    }

    public AxisParentRecord(RecordInputStream in) {
        this.field_1_axisType = in.readShort();
        this.field_2_x = in.readInt();
        this.field_3_y = in.readInt();
        this.field_4_width = in.readInt();
        this.field_5_height = in.readInt();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[AXISPARENT]\n");
        buffer.append("    .axisType             = ").append("0x").append(HexDump.toHex(this.getAxisType())).append(" (").append(this.getAxisType()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .x                    = ").append("0x").append(HexDump.toHex(this.getX())).append(" (").append(this.getX()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .y                    = ").append("0x").append(HexDump.toHex(this.getY())).append(" (").append(this.getY()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .width                = ").append("0x").append(HexDump.toHex(this.getWidth())).append(" (").append(this.getWidth()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .height               = ").append("0x").append(HexDump.toHex(this.getHeight())).append(" (").append(this.getHeight()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("[/AXISPARENT]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_axisType);
        out.writeInt(this.field_2_x);
        out.writeInt(this.field_3_y);
        out.writeInt(this.field_4_width);
        out.writeInt(this.field_5_height);
    }

    @Override
    protected int getDataSize() {
        return 18;
    }

    @Override
    public short getSid() {
        return 4161;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public AxisParentRecord clone() {
        return this.copy();
    }

    public short getAxisType() {
        return this.field_1_axisType;
    }

    public void setAxisType(short field_1_axisType) {
        this.field_1_axisType = field_1_axisType;
    }

    public int getX() {
        return this.field_2_x;
    }

    public void setX(int field_2_x) {
        this.field_2_x = field_2_x;
    }

    public int getY() {
        return this.field_3_y;
    }

    public void setY(int field_3_y) {
        this.field_3_y = field_3_y;
    }

    public int getWidth() {
        return this.field_4_width;
    }

    public void setWidth(int field_4_width) {
        this.field_4_width = field_4_width;
    }

    public int getHeight() {
        return this.field_5_height;
    }

    public void setHeight(int field_5_height) {
        this.field_5_height = field_5_height;
    }

    @Override
    public AxisParentRecord copy() {
        return new AxisParentRecord(this);
    }
}

