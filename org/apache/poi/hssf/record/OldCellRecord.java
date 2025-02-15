/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.HexDump;

public abstract class OldCellRecord {
    private final short sid;
    private final boolean isBiff2;
    private final int field_1_row;
    private final short field_2_column;
    private int field_3_cell_attrs;
    private short field_3_xf_index;

    protected OldCellRecord(RecordInputStream in, boolean isBiff2) {
        this.sid = in.getSid();
        this.isBiff2 = isBiff2;
        this.field_1_row = in.readUShort();
        this.field_2_column = in.readShort();
        if (isBiff2) {
            this.field_3_cell_attrs = in.readUShort() << 8;
            this.field_3_cell_attrs += in.readUByte();
        } else {
            this.field_3_xf_index = in.readShort();
        }
    }

    public final int getRow() {
        return this.field_1_row;
    }

    public final short getColumn() {
        return this.field_2_column;
    }

    public final short getXFIndex() {
        return this.field_3_xf_index;
    }

    public int getCellAttrs() {
        return this.field_3_cell_attrs;
    }

    public boolean isBiff2() {
        return this.isBiff2;
    }

    public short getSid() {
        return this.sid;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        String recordName = this.getRecordName();
        sb.append("[").append(recordName).append("]\n");
        sb.append("    .row    = ").append(HexDump.shortToHex(this.getRow())).append("\n");
        sb.append("    .col    = ").append(HexDump.shortToHex(this.getColumn())).append("\n");
        if (this.isBiff2()) {
            sb.append("    .cellattrs = ").append(HexDump.shortToHex(this.getCellAttrs())).append("\n");
        } else {
            sb.append("    .xfindex   = ").append(HexDump.shortToHex(this.getXFIndex())).append("\n");
        }
        this.appendValueText(sb);
        sb.append("\n");
        sb.append("[/").append(recordName).append("]\n");
        return sb.toString();
    }

    protected abstract void appendValueText(StringBuilder var1);

    protected abstract String getRecordName();
}

