/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.CellRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class LabelSSTRecord
extends CellRecord {
    public static final short sid = 253;
    private int field_4_sst_index;

    public LabelSSTRecord() {
    }

    public LabelSSTRecord(LabelSSTRecord other) {
        super(other);
        this.field_4_sst_index = other.field_4_sst_index;
    }

    public LabelSSTRecord(RecordInputStream in) {
        super(in);
        this.field_4_sst_index = in.readInt();
    }

    public void setSSTIndex(int index) {
        this.field_4_sst_index = index;
    }

    public int getSSTIndex() {
        return this.field_4_sst_index;
    }

    @Override
    protected String getRecordName() {
        return "LABELSST";
    }

    @Override
    protected void appendValueText(StringBuilder sb) {
        sb.append("  .sstIndex = ");
        sb.append(HexDump.shortToHex(this.getSSTIndex()));
    }

    @Override
    protected void serializeValue(LittleEndianOutput out) {
        out.writeInt(this.getSSTIndex());
    }

    @Override
    protected int getValueDataSize() {
        return 4;
    }

    @Override
    public short getSid() {
        return 253;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public LabelSSTRecord clone() {
        return this.copy();
    }

    @Override
    public LabelSSTRecord copy() {
        return new LabelSSTRecord(this);
    }
}

