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

public final class DatRecord
extends StandardRecord {
    public static final short sid = 4195;
    private static final BitField horizontalBorder = BitFieldFactory.getInstance(1);
    private static final BitField verticalBorder = BitFieldFactory.getInstance(2);
    private static final BitField border = BitFieldFactory.getInstance(4);
    private static final BitField showSeriesKey = BitFieldFactory.getInstance(8);
    private short field_1_options;

    public DatRecord() {
    }

    public DatRecord(DatRecord other) {
        super(other);
        this.field_1_options = other.field_1_options;
    }

    public DatRecord(RecordInputStream in) {
        this.field_1_options = in.readShort();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[DAT]\n");
        buffer.append("    .options              = ").append("0x").append(HexDump.toHex(this.getOptions())).append(" (").append(this.getOptions()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("         .horizontalBorder         = ").append(this.isHorizontalBorder()).append('\n');
        buffer.append("         .verticalBorder           = ").append(this.isVerticalBorder()).append('\n');
        buffer.append("         .border                   = ").append(this.isBorder()).append('\n');
        buffer.append("         .showSeriesKey            = ").append(this.isShowSeriesKey()).append('\n');
        buffer.append("[/DAT]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_options);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 4195;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public DatRecord clone() {
        return this.copy();
    }

    @Override
    public DatRecord copy() {
        return new DatRecord(this);
    }

    public short getOptions() {
        return this.field_1_options;
    }

    public void setOptions(short field_1_options) {
        this.field_1_options = field_1_options;
    }

    public void setHorizontalBorder(boolean value) {
        this.field_1_options = horizontalBorder.setShortBoolean(this.field_1_options, value);
    }

    public boolean isHorizontalBorder() {
        return horizontalBorder.isSet(this.field_1_options);
    }

    public void setVerticalBorder(boolean value) {
        this.field_1_options = verticalBorder.setShortBoolean(this.field_1_options, value);
    }

    public boolean isVerticalBorder() {
        return verticalBorder.isSet(this.field_1_options);
    }

    public void setBorder(boolean value) {
        this.field_1_options = border.setShortBoolean(this.field_1_options, value);
    }

    public boolean isBorder() {
        return border.isSet(this.field_1_options);
    }

    public void setShowSeriesKey(boolean value) {
        this.field_1_options = showSeriesKey.setShortBoolean(this.field_1_options, value);
    }

    public boolean isShowSeriesKey() {
        return showSeriesKey.isSet(this.field_1_options);
    }
}

