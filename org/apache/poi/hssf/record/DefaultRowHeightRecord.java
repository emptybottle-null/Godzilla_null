/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class DefaultRowHeightRecord
extends StandardRecord {
    public static final short sid = 549;
    public static final short DEFAULT_ROW_HEIGHT = 255;
    private short field_1_option_flags;
    private short field_2_row_height;

    public DefaultRowHeightRecord() {
        this.field_1_option_flags = 0;
        this.field_2_row_height = (short)255;
    }

    public DefaultRowHeightRecord(DefaultRowHeightRecord other) {
        super(other);
        this.field_1_option_flags = other.field_1_option_flags;
        this.field_2_row_height = other.field_2_row_height;
    }

    public DefaultRowHeightRecord(RecordInputStream in) {
        this.field_1_option_flags = in.readShort();
        this.field_2_row_height = in.readShort();
    }

    public void setOptionFlags(short flags) {
        this.field_1_option_flags = flags;
    }

    public void setRowHeight(short height) {
        this.field_2_row_height = height;
    }

    public short getOptionFlags() {
        return this.field_1_option_flags;
    }

    public short getRowHeight() {
        return this.field_2_row_height;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[DEFAULTROWHEIGHT]\n");
        buffer.append("    .optionflags    = ").append(Integer.toHexString(this.getOptionFlags())).append("\n");
        buffer.append("    .rowheight      = ").append(Integer.toHexString(this.getRowHeight())).append("\n");
        buffer.append("[/DEFAULTROWHEIGHT]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.getOptionFlags());
        out.writeShort(this.getRowHeight());
    }

    @Override
    protected int getDataSize() {
        return 4;
    }

    @Override
    public short getSid() {
        return 549;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public DefaultRowHeightRecord clone() {
        return this.copy();
    }

    @Override
    public DefaultRowHeightRecord copy() {
        return new DefaultRowHeightRecord(this);
    }
}

