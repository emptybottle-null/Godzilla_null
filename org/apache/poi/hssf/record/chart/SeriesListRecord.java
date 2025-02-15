/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import java.util.Arrays;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class SeriesListRecord
extends StandardRecord {
    public static final short sid = 4118;
    private short[] field_1_seriesNumbers;

    public SeriesListRecord(SeriesListRecord other) {
        super(other);
        this.field_1_seriesNumbers = other.field_1_seriesNumbers == null ? null : (short[])other.field_1_seriesNumbers.clone();
    }

    public SeriesListRecord(short[] seriesNumbers) {
        this.field_1_seriesNumbers = seriesNumbers == null ? null : (short[])seriesNumbers.clone();
    }

    public SeriesListRecord(RecordInputStream in) {
        int nItems = in.readUShort();
        short[] ss = new short[nItems];
        for (int i = 0; i < nItems; ++i) {
            ss[i] = in.readShort();
        }
        this.field_1_seriesNumbers = ss;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[SERIESLIST]\n");
        buffer.append("    .seriesNumbers= ").append(" (").append(Arrays.toString(this.getSeriesNumbers())).append(" )");
        buffer.append("\n");
        buffer.append("[/SERIESLIST]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        int nItems = this.field_1_seriesNumbers.length;
        out.writeShort(nItems);
        for (int i = 0; i < nItems; ++i) {
            out.writeShort(this.field_1_seriesNumbers[i]);
        }
    }

    @Override
    protected int getDataSize() {
        return this.field_1_seriesNumbers.length * 2 + 2;
    }

    @Override
    public short getSid() {
        return 4118;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public SeriesListRecord clone() {
        return this.copy();
    }

    @Override
    public SeriesListRecord copy() {
        return new SeriesListRecord(this);
    }

    public short[] getSeriesNumbers() {
        return this.field_1_seriesNumbers;
    }
}

