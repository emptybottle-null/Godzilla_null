/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class SeriesRecord
extends StandardRecord {
    public static final short sid = 4099;
    public static final short CATEGORY_DATA_TYPE_DATES = 0;
    public static final short CATEGORY_DATA_TYPE_NUMERIC = 1;
    public static final short CATEGORY_DATA_TYPE_SEQUENCE = 2;
    public static final short CATEGORY_DATA_TYPE_TEXT = 3;
    public static final short VALUES_DATA_TYPE_DATES = 0;
    public static final short VALUES_DATA_TYPE_NUMERIC = 1;
    public static final short VALUES_DATA_TYPE_SEQUENCE = 2;
    public static final short VALUES_DATA_TYPE_TEXT = 3;
    public static final short BUBBLE_SERIES_TYPE_DATES = 0;
    public static final short BUBBLE_SERIES_TYPE_NUMERIC = 1;
    public static final short BUBBLE_SERIES_TYPE_SEQUENCE = 2;
    public static final short BUBBLE_SERIES_TYPE_TEXT = 3;
    private short field_1_categoryDataType;
    private short field_2_valuesDataType;
    private short field_3_numCategories;
    private short field_4_numValues;
    private short field_5_bubbleSeriesType;
    private short field_6_numBubbleValues;

    public SeriesRecord() {
    }

    public SeriesRecord(SeriesRecord other) {
        super(other);
        this.field_1_categoryDataType = other.field_1_categoryDataType;
        this.field_2_valuesDataType = other.field_2_valuesDataType;
        this.field_3_numCategories = other.field_3_numCategories;
        this.field_4_numValues = other.field_4_numValues;
        this.field_5_bubbleSeriesType = other.field_5_bubbleSeriesType;
        this.field_6_numBubbleValues = other.field_6_numBubbleValues;
    }

    public SeriesRecord(RecordInputStream in) {
        this.field_1_categoryDataType = in.readShort();
        this.field_2_valuesDataType = in.readShort();
        this.field_3_numCategories = in.readShort();
        this.field_4_numValues = in.readShort();
        this.field_5_bubbleSeriesType = in.readShort();
        this.field_6_numBubbleValues = in.readShort();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[SERIES]\n");
        buffer.append("    .categoryDataType     = ").append("0x").append(HexDump.toHex(this.getCategoryDataType())).append(" (").append(this.getCategoryDataType()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .valuesDataType       = ").append("0x").append(HexDump.toHex(this.getValuesDataType())).append(" (").append(this.getValuesDataType()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .numCategories        = ").append("0x").append(HexDump.toHex(this.getNumCategories())).append(" (").append(this.getNumCategories()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .numValues            = ").append("0x").append(HexDump.toHex(this.getNumValues())).append(" (").append(this.getNumValues()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .bubbleSeriesType     = ").append("0x").append(HexDump.toHex(this.getBubbleSeriesType())).append(" (").append(this.getBubbleSeriesType()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .numBubbleValues      = ").append("0x").append(HexDump.toHex(this.getNumBubbleValues())).append(" (").append(this.getNumBubbleValues()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("[/SERIES]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_categoryDataType);
        out.writeShort(this.field_2_valuesDataType);
        out.writeShort(this.field_3_numCategories);
        out.writeShort(this.field_4_numValues);
        out.writeShort(this.field_5_bubbleSeriesType);
        out.writeShort(this.field_6_numBubbleValues);
    }

    @Override
    protected int getDataSize() {
        return 12;
    }

    @Override
    public short getSid() {
        return 4099;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public SeriesRecord clone() {
        return this.copy();
    }

    @Override
    public SeriesRecord copy() {
        return new SeriesRecord(this);
    }

    public short getCategoryDataType() {
        return this.field_1_categoryDataType;
    }

    public void setCategoryDataType(short field_1_categoryDataType) {
        this.field_1_categoryDataType = field_1_categoryDataType;
    }

    public short getValuesDataType() {
        return this.field_2_valuesDataType;
    }

    public void setValuesDataType(short field_2_valuesDataType) {
        this.field_2_valuesDataType = field_2_valuesDataType;
    }

    public short getNumCategories() {
        return this.field_3_numCategories;
    }

    public void setNumCategories(short field_3_numCategories) {
        this.field_3_numCategories = field_3_numCategories;
    }

    public short getNumValues() {
        return this.field_4_numValues;
    }

    public void setNumValues(short field_4_numValues) {
        this.field_4_numValues = field_4_numValues;
    }

    public short getBubbleSeriesType() {
        return this.field_5_bubbleSeriesType;
    }

    public void setBubbleSeriesType(short field_5_bubbleSeriesType) {
        this.field_5_bubbleSeriesType = field_5_bubbleSeriesType;
    }

    public short getNumBubbleValues() {
        return this.field_6_numBubbleValues;
    }

    public void setNumBubbleValues(short field_6_numBubbleValues) {
        this.field_6_numBubbleValues = field_6_numBubbleValues;
    }
}

