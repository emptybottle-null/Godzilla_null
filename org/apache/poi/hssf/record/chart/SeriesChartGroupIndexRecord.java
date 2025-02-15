/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class SeriesChartGroupIndexRecord
extends StandardRecord {
    public static final short sid = 4165;
    private short field_1_chartGroupIndex;

    public SeriesChartGroupIndexRecord() {
    }

    public SeriesChartGroupIndexRecord(SeriesChartGroupIndexRecord other) {
        super(other);
        this.field_1_chartGroupIndex = other.field_1_chartGroupIndex;
    }

    public SeriesChartGroupIndexRecord(RecordInputStream in) {
        this.field_1_chartGroupIndex = in.readShort();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[SERTOCRT]\n");
        buffer.append("    .chartGroupIndex      = ").append("0x").append(HexDump.toHex(this.getChartGroupIndex())).append(" (").append(this.getChartGroupIndex()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("[/SERTOCRT]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_chartGroupIndex);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 4165;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public SeriesChartGroupIndexRecord clone() {
        return this.copy();
    }

    @Override
    public SeriesChartGroupIndexRecord copy() {
        return new SeriesChartGroupIndexRecord(this);
    }

    public short getChartGroupIndex() {
        return this.field_1_chartGroupIndex;
    }

    public void setChartGroupIndex(short field_1_chartGroupIndex) {
        this.field_1_chartGroupIndex = field_1_chartGroupIndex;
    }
}

