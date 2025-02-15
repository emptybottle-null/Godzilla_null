/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class TickRecord
extends StandardRecord {
    public static final short sid = 4126;
    private static final BitField autoTextColor = BitFieldFactory.getInstance(1);
    private static final BitField autoTextBackground = BitFieldFactory.getInstance(2);
    private static final BitField rotation = BitFieldFactory.getInstance(28);
    private static final BitField autorotate = BitFieldFactory.getInstance(32);
    private byte field_1_majorTickType;
    private byte field_2_minorTickType;
    private byte field_3_labelPosition;
    private byte field_4_background;
    private int field_5_labelColorRgb;
    private int field_6_zero1;
    private int field_7_zero2;
    private int field_8_zero3;
    private int field_9_zero4;
    private short field_10_options;
    private short field_11_tickColor;
    private short field_12_zero5;

    public TickRecord() {
    }

    public TickRecord(TickRecord other) {
        super(other);
        this.field_1_majorTickType = other.field_1_majorTickType;
        this.field_2_minorTickType = other.field_2_minorTickType;
        this.field_3_labelPosition = other.field_3_labelPosition;
        this.field_4_background = other.field_4_background;
        this.field_5_labelColorRgb = other.field_5_labelColorRgb;
        this.field_6_zero1 = other.field_6_zero1;
        this.field_7_zero2 = other.field_7_zero2;
        this.field_8_zero3 = other.field_8_zero3;
        this.field_9_zero4 = other.field_9_zero4;
        this.field_10_options = other.field_10_options;
        this.field_11_tickColor = other.field_11_tickColor;
        this.field_12_zero5 = other.field_12_zero5;
    }

    public TickRecord(RecordInputStream in) {
        this.field_1_majorTickType = in.readByte();
        this.field_2_minorTickType = in.readByte();
        this.field_3_labelPosition = in.readByte();
        this.field_4_background = in.readByte();
        this.field_5_labelColorRgb = in.readInt();
        this.field_6_zero1 = in.readInt();
        this.field_7_zero2 = in.readInt();
        this.field_8_zero3 = in.readInt();
        this.field_9_zero4 = in.readInt();
        this.field_10_options = in.readShort();
        this.field_11_tickColor = in.readShort();
        this.field_12_zero5 = in.readShort();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[TICK]\n");
        buffer.append("    .majorTickType        = ").append("0x").append(HexDump.toHex(this.getMajorTickType())).append(" (").append(this.getMajorTickType()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .minorTickType        = ").append("0x").append(HexDump.toHex(this.getMinorTickType())).append(" (").append(this.getMinorTickType()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .labelPosition        = ").append("0x").append(HexDump.toHex(this.getLabelPosition())).append(" (").append(this.getLabelPosition()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .background           = ").append("0x").append(HexDump.toHex(this.getBackground())).append(" (").append(this.getBackground()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .labelColorRgb        = ").append("0x").append(HexDump.toHex(this.getLabelColorRgb())).append(" (").append(this.getLabelColorRgb()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .zero1                = ").append("0x").append(HexDump.toHex(this.getZero1())).append(" (").append(this.getZero1()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .zero2                = ").append("0x").append(HexDump.toHex(this.getZero2())).append(" (").append(this.getZero2()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .options              = ").append("0x").append(HexDump.toHex(this.getOptions())).append(" (").append(this.getOptions()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("         .autoTextColor            = ").append(this.isAutoTextColor()).append('\n');
        buffer.append("         .autoTextBackground       = ").append(this.isAutoTextBackground()).append('\n');
        buffer.append("         .rotation                 = ").append(this.getRotation()).append('\n');
        buffer.append("         .autorotate               = ").append(this.isAutorotate()).append('\n');
        buffer.append("    .tickColor            = ").append("0x").append(HexDump.toHex(this.getTickColor())).append(" (").append(this.getTickColor()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .zero3                = ").append("0x").append(HexDump.toHex(this.getZero3())).append(" (").append(this.getZero3()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("[/TICK]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeByte(this.field_1_majorTickType);
        out.writeByte(this.field_2_minorTickType);
        out.writeByte(this.field_3_labelPosition);
        out.writeByte(this.field_4_background);
        out.writeInt(this.field_5_labelColorRgb);
        out.writeInt(this.field_6_zero1);
        out.writeInt(this.field_7_zero2);
        out.writeInt(this.field_8_zero3);
        out.writeInt(this.field_9_zero4);
        out.writeShort(this.field_10_options);
        out.writeShort(this.field_11_tickColor);
        out.writeShort(this.field_12_zero5);
    }

    @Override
    protected int getDataSize() {
        return 30;
    }

    @Override
    public short getSid() {
        return 4126;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public TickRecord clone() {
        return this.copy();
    }

    @Override
    public TickRecord copy() {
        return new TickRecord(this);
    }

    public byte getMajorTickType() {
        return this.field_1_majorTickType;
    }

    public void setMajorTickType(byte field_1_majorTickType) {
        this.field_1_majorTickType = field_1_majorTickType;
    }

    public byte getMinorTickType() {
        return this.field_2_minorTickType;
    }

    public void setMinorTickType(byte field_2_minorTickType) {
        this.field_2_minorTickType = field_2_minorTickType;
    }

    public byte getLabelPosition() {
        return this.field_3_labelPosition;
    }

    public void setLabelPosition(byte field_3_labelPosition) {
        this.field_3_labelPosition = field_3_labelPosition;
    }

    public byte getBackground() {
        return this.field_4_background;
    }

    public void setBackground(byte field_4_background) {
        this.field_4_background = field_4_background;
    }

    public int getLabelColorRgb() {
        return this.field_5_labelColorRgb;
    }

    public void setLabelColorRgb(int field_5_labelColorRgb) {
        this.field_5_labelColorRgb = field_5_labelColorRgb;
    }

    public int getZero1() {
        return this.field_6_zero1;
    }

    public void setZero1(int field_6_zero1) {
        this.field_6_zero1 = field_6_zero1;
    }

    public int getZero2() {
        return this.field_7_zero2;
    }

    public void setZero2(int field_7_zero2) {
        this.field_7_zero2 = field_7_zero2;
    }

    public short getOptions() {
        return this.field_10_options;
    }

    public void setOptions(short field_10_options) {
        this.field_10_options = field_10_options;
    }

    public short getTickColor() {
        return this.field_11_tickColor;
    }

    public void setTickColor(short field_11_tickColor) {
        this.field_11_tickColor = field_11_tickColor;
    }

    public short getZero3() {
        return this.field_12_zero5;
    }

    public void setZero3(short field_12_zero3) {
        this.field_12_zero5 = field_12_zero3;
    }

    public void setAutoTextColor(boolean value) {
        this.field_10_options = autoTextColor.setShortBoolean(this.field_10_options, value);
    }

    public boolean isAutoTextColor() {
        return autoTextColor.isSet(this.field_10_options);
    }

    public void setAutoTextBackground(boolean value) {
        this.field_10_options = autoTextBackground.setShortBoolean(this.field_10_options, value);
    }

    public boolean isAutoTextBackground() {
        return autoTextBackground.isSet(this.field_10_options);
    }

    public void setRotation(short value) {
        this.field_10_options = rotation.setShortValue(this.field_10_options, value);
    }

    public short getRotation() {
        return rotation.getShortValue(this.field_10_options);
    }

    public void setAutorotate(boolean value) {
        this.field_10_options = autorotate.setShortBoolean(this.field_10_options, value);
    }

    public boolean isAutorotate() {
        return autorotate.isSet(this.field_10_options);
    }
}

