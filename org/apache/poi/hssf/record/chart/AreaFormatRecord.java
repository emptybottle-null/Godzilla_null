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

public final class AreaFormatRecord
extends StandardRecord {
    public static final short sid = 4106;
    private static final BitField automatic = BitFieldFactory.getInstance(1);
    private static final BitField invert = BitFieldFactory.getInstance(2);
    private int field_1_foregroundColor;
    private int field_2_backgroundColor;
    private short field_3_pattern;
    private short field_4_formatFlags;
    private short field_5_forecolorIndex;
    private short field_6_backcolorIndex;

    public AreaFormatRecord() {
    }

    public AreaFormatRecord(RecordInputStream in) {
        this.field_1_foregroundColor = in.readInt();
        this.field_2_backgroundColor = in.readInt();
        this.field_3_pattern = in.readShort();
        this.field_4_formatFlags = in.readShort();
        this.field_5_forecolorIndex = in.readShort();
        this.field_6_backcolorIndex = in.readShort();
    }

    public AreaFormatRecord(AreaFormatRecord other) {
        super(other);
        this.field_1_foregroundColor = other.field_1_foregroundColor;
        this.field_2_backgroundColor = other.field_2_backgroundColor;
        this.field_3_pattern = other.field_3_pattern;
        this.field_4_formatFlags = other.field_4_formatFlags;
        this.field_5_forecolorIndex = other.field_5_forecolorIndex;
        this.field_6_backcolorIndex = other.field_6_backcolorIndex;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[AREAFORMAT]\n");
        buffer.append("    .foregroundColor      = ").append("0x").append(HexDump.toHex(this.getForegroundColor())).append(" (").append(this.getForegroundColor()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .backgroundColor      = ").append("0x").append(HexDump.toHex(this.getBackgroundColor())).append(" (").append(this.getBackgroundColor()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .pattern              = ").append("0x").append(HexDump.toHex(this.getPattern())).append(" (").append(this.getPattern()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .formatFlags          = ").append("0x").append(HexDump.toHex(this.getFormatFlags())).append(" (").append(this.getFormatFlags()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("         .automatic                = ").append(this.isAutomatic()).append('\n');
        buffer.append("         .invert                   = ").append(this.isInvert()).append('\n');
        buffer.append("    .forecolorIndex       = ").append("0x").append(HexDump.toHex(this.getForecolorIndex())).append(" (").append(this.getForecolorIndex()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .backcolorIndex       = ").append("0x").append(HexDump.toHex(this.getBackcolorIndex())).append(" (").append(this.getBackcolorIndex()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("[/AREAFORMAT]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeInt(this.field_1_foregroundColor);
        out.writeInt(this.field_2_backgroundColor);
        out.writeShort(this.field_3_pattern);
        out.writeShort(this.field_4_formatFlags);
        out.writeShort(this.field_5_forecolorIndex);
        out.writeShort(this.field_6_backcolorIndex);
    }

    @Override
    protected int getDataSize() {
        return 16;
    }

    @Override
    public short getSid() {
        return 4106;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public AreaFormatRecord clone() {
        return this.copy();
    }

    public int getForegroundColor() {
        return this.field_1_foregroundColor;
    }

    public void setForegroundColor(int field_1_foregroundColor) {
        this.field_1_foregroundColor = field_1_foregroundColor;
    }

    public int getBackgroundColor() {
        return this.field_2_backgroundColor;
    }

    public void setBackgroundColor(int field_2_backgroundColor) {
        this.field_2_backgroundColor = field_2_backgroundColor;
    }

    public short getPattern() {
        return this.field_3_pattern;
    }

    public void setPattern(short field_3_pattern) {
        this.field_3_pattern = field_3_pattern;
    }

    public short getFormatFlags() {
        return this.field_4_formatFlags;
    }

    public void setFormatFlags(short field_4_formatFlags) {
        this.field_4_formatFlags = field_4_formatFlags;
    }

    public short getForecolorIndex() {
        return this.field_5_forecolorIndex;
    }

    public void setForecolorIndex(short field_5_forecolorIndex) {
        this.field_5_forecolorIndex = field_5_forecolorIndex;
    }

    public short getBackcolorIndex() {
        return this.field_6_backcolorIndex;
    }

    public void setBackcolorIndex(short field_6_backcolorIndex) {
        this.field_6_backcolorIndex = field_6_backcolorIndex;
    }

    public void setAutomatic(boolean value) {
        this.field_4_formatFlags = automatic.setShortBoolean(this.field_4_formatFlags, value);
    }

    public boolean isAutomatic() {
        return automatic.isSet(this.field_4_formatFlags);
    }

    public void setInvert(boolean value) {
        this.field_4_formatFlags = invert.setShortBoolean(this.field_4_formatFlags, value);
    }

    public boolean isInvert() {
        return invert.isSet(this.field_4_formatFlags);
    }

    @Override
    public AreaFormatRecord copy() {
        return new AreaFormatRecord(this);
    }
}

