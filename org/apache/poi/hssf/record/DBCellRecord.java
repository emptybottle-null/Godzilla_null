/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class DBCellRecord
extends StandardRecord {
    public static final short sid = 215;
    public static final int BLOCK_SIZE = 32;
    private final int field_1_row_offset;
    private final short[] field_2_cell_offsets;

    public DBCellRecord(int rowOffset, short[] cellOffsets) {
        this.field_1_row_offset = rowOffset;
        this.field_2_cell_offsets = cellOffsets;
    }

    public DBCellRecord(RecordInputStream in) {
        this.field_1_row_offset = in.readUShort();
        int size = in.remaining();
        this.field_2_cell_offsets = new short[size / 2];
        for (int i = 0; i < this.field_2_cell_offsets.length; ++i) {
            this.field_2_cell_offsets[i] = in.readShort();
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[DBCELL]\n");
        buffer.append("    .rowoffset = ").append(HexDump.intToHex(this.field_1_row_offset)).append("\n");
        for (int k = 0; k < this.field_2_cell_offsets.length; ++k) {
            buffer.append("    .cell_").append(k).append(" = ").append(HexDump.shortToHex(this.field_2_cell_offsets[k])).append("\n");
        }
        buffer.append("[/DBCELL]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeInt(this.field_1_row_offset);
        for (short field_2_cell_offset : this.field_2_cell_offsets) {
            out.writeShort(field_2_cell_offset);
        }
    }

    @Override
    protected int getDataSize() {
        return 4 + this.field_2_cell_offsets.length * 2;
    }

    @Override
    public short getSid() {
        return 215;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public DBCellRecord clone() {
        return this.copy();
    }

    @Override
    public DBCellRecord copy() {
        return this;
    }
}

