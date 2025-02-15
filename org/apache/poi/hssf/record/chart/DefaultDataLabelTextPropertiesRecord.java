/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class DefaultDataLabelTextPropertiesRecord
extends StandardRecord {
    public static final short sid = 4132;
    public static final short CATEGORY_DATA_TYPE_SHOW_LABELS_CHARACTERISTIC = 0;
    public static final short CATEGORY_DATA_TYPE_VALUE_AND_PERCENTAGE_CHARACTERISTIC = 1;
    public static final short CATEGORY_DATA_TYPE_ALL_TEXT_CHARACTERISTIC = 2;
    private short field_1_categoryDataType;

    public DefaultDataLabelTextPropertiesRecord() {
    }

    public DefaultDataLabelTextPropertiesRecord(DefaultDataLabelTextPropertiesRecord other) {
        super(other);
        this.field_1_categoryDataType = other.field_1_categoryDataType;
    }

    public DefaultDataLabelTextPropertiesRecord(RecordInputStream in) {
        this.field_1_categoryDataType = in.readShort();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[DEFAULTTEXT]\n");
        buffer.append("    .categoryDataType     = ").append("0x").append(HexDump.toHex(this.getCategoryDataType())).append(" (").append(this.getCategoryDataType()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("[/DEFAULTTEXT]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_categoryDataType);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 4132;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public DefaultDataLabelTextPropertiesRecord clone() {
        return this.copy();
    }

    @Override
    public DefaultDataLabelTextPropertiesRecord copy() {
        return new DefaultDataLabelTextPropertiesRecord(this);
    }

    public short getCategoryDataType() {
        return this.field_1_categoryDataType;
    }

    public void setCategoryDataType(short field_1_categoryDataType) {
        this.field_1_categoryDataType = field_1_categoryDataType;
    }
}

