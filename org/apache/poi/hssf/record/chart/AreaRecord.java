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

public final class AreaRecord
extends StandardRecord {
    public static final short sid = 4122;
    private static final BitField stacked = BitFieldFactory.getInstance(1);
    private static final BitField displayAsPercentage = BitFieldFactory.getInstance(2);
    private static final BitField shadow = BitFieldFactory.getInstance(4);
    private short field_1_formatFlags;

    public AreaRecord() {
    }

    public AreaRecord(AreaRecord other) {
        super(other);
        this.field_1_formatFlags = other.field_1_formatFlags;
    }

    public AreaRecord(RecordInputStream in) {
        this.field_1_formatFlags = in.readShort();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[AREA]\n");
        buffer.append("    .formatFlags          = ").append("0x").append(HexDump.toHex(this.getFormatFlags())).append(" (").append(this.getFormatFlags()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("         .stacked                  = ").append(this.isStacked()).append('\n');
        buffer.append("         .displayAsPercentage      = ").append(this.isDisplayAsPercentage()).append('\n');
        buffer.append("         .shadow                   = ").append(this.isShadow()).append('\n');
        buffer.append("[/AREA]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_formatFlags);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 4122;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public AreaRecord clone() {
        return this.copy();
    }

    public short getFormatFlags() {
        return this.field_1_formatFlags;
    }

    public void setFormatFlags(short field_1_formatFlags) {
        this.field_1_formatFlags = field_1_formatFlags;
    }

    public void setStacked(boolean value) {
        this.field_1_formatFlags = stacked.setShortBoolean(this.field_1_formatFlags, value);
    }

    public boolean isStacked() {
        return stacked.isSet(this.field_1_formatFlags);
    }

    public void setDisplayAsPercentage(boolean value) {
        this.field_1_formatFlags = displayAsPercentage.setShortBoolean(this.field_1_formatFlags, value);
    }

    public boolean isDisplayAsPercentage() {
        return displayAsPercentage.isSet(this.field_1_formatFlags);
    }

    public void setShadow(boolean value) {
        this.field_1_formatFlags = shadow.setShortBoolean(this.field_1_formatFlags, value);
    }

    public boolean isShadow() {
        return shadow.isSet(this.field_1_formatFlags);
    }

    @Override
    public AreaRecord copy() {
        return new AreaRecord(this);
    }
}

