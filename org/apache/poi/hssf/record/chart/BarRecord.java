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

public final class BarRecord
extends StandardRecord {
    public static final short sid = 4119;
    private static final BitField horizontal = BitFieldFactory.getInstance(1);
    private static final BitField stacked = BitFieldFactory.getInstance(2);
    private static final BitField displayAsPercentage = BitFieldFactory.getInstance(4);
    private static final BitField shadow = BitFieldFactory.getInstance(8);
    private short field_1_barSpace;
    private short field_2_categorySpace;
    private short field_3_formatFlags;

    public BarRecord() {
    }

    public BarRecord(BarRecord other) {
        super(other);
        this.field_1_barSpace = other.field_1_barSpace;
        this.field_2_categorySpace = other.field_2_categorySpace;
        this.field_3_formatFlags = other.field_3_formatFlags;
    }

    public BarRecord(RecordInputStream in) {
        this.field_1_barSpace = in.readShort();
        this.field_2_categorySpace = in.readShort();
        this.field_3_formatFlags = in.readShort();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[BAR]\n");
        buffer.append("    .barSpace             = ").append("0x").append(HexDump.toHex(this.getBarSpace())).append(" (").append(this.getBarSpace()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .categorySpace        = ").append("0x").append(HexDump.toHex(this.getCategorySpace())).append(" (").append(this.getCategorySpace()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .formatFlags          = ").append("0x").append(HexDump.toHex(this.getFormatFlags())).append(" (").append(this.getFormatFlags()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("         .horizontal               = ").append(this.isHorizontal()).append('\n');
        buffer.append("         .stacked                  = ").append(this.isStacked()).append('\n');
        buffer.append("         .displayAsPercentage      = ").append(this.isDisplayAsPercentage()).append('\n');
        buffer.append("         .shadow                   = ").append(this.isShadow()).append('\n');
        buffer.append("[/BAR]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_barSpace);
        out.writeShort(this.field_2_categorySpace);
        out.writeShort(this.field_3_formatFlags);
    }

    @Override
    protected int getDataSize() {
        return 6;
    }

    @Override
    public short getSid() {
        return 4119;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public BarRecord clone() {
        return this.copy();
    }

    public short getBarSpace() {
        return this.field_1_barSpace;
    }

    public void setBarSpace(short field_1_barSpace) {
        this.field_1_barSpace = field_1_barSpace;
    }

    public short getCategorySpace() {
        return this.field_2_categorySpace;
    }

    public void setCategorySpace(short field_2_categorySpace) {
        this.field_2_categorySpace = field_2_categorySpace;
    }

    public short getFormatFlags() {
        return this.field_3_formatFlags;
    }

    public void setFormatFlags(short field_3_formatFlags) {
        this.field_3_formatFlags = field_3_formatFlags;
    }

    public void setHorizontal(boolean value) {
        this.field_3_formatFlags = horizontal.setShortBoolean(this.field_3_formatFlags, value);
    }

    public boolean isHorizontal() {
        return horizontal.isSet(this.field_3_formatFlags);
    }

    public void setStacked(boolean value) {
        this.field_3_formatFlags = stacked.setShortBoolean(this.field_3_formatFlags, value);
    }

    public boolean isStacked() {
        return stacked.isSet(this.field_3_formatFlags);
    }

    public void setDisplayAsPercentage(boolean value) {
        this.field_3_formatFlags = displayAsPercentage.setShortBoolean(this.field_3_formatFlags, value);
    }

    public boolean isDisplayAsPercentage() {
        return displayAsPercentage.isSet(this.field_3_formatFlags);
    }

    public void setShadow(boolean value) {
        this.field_3_formatFlags = shadow.setShortBoolean(this.field_3_formatFlags, value);
    }

    public boolean isShadow() {
        return shadow.isSet(this.field_3_formatFlags);
    }

    @Override
    public BarRecord copy() {
        return new BarRecord(this);
    }
}

