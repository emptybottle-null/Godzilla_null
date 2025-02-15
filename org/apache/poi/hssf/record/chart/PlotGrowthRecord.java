/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class PlotGrowthRecord
extends StandardRecord {
    public static final short sid = 4196;
    private int field_1_horizontalScale;
    private int field_2_verticalScale;

    public PlotGrowthRecord() {
    }

    public PlotGrowthRecord(PlotGrowthRecord other) {
        this.field_1_horizontalScale = other.field_1_horizontalScale;
        this.field_2_verticalScale = other.field_2_verticalScale;
    }

    public PlotGrowthRecord(RecordInputStream in) {
        this.field_1_horizontalScale = in.readInt();
        this.field_2_verticalScale = in.readInt();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[PLOTGROWTH]\n");
        buffer.append("    .horizontalScale      = ").append("0x").append(HexDump.toHex(this.getHorizontalScale())).append(" (").append(this.getHorizontalScale()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("    .verticalScale        = ").append("0x").append(HexDump.toHex(this.getVerticalScale())).append(" (").append(this.getVerticalScale()).append(" )");
        buffer.append(System.getProperty("line.separator"));
        buffer.append("[/PLOTGROWTH]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeInt(this.field_1_horizontalScale);
        out.writeInt(this.field_2_verticalScale);
    }

    @Override
    protected int getDataSize() {
        return 8;
    }

    @Override
    public short getSid() {
        return 4196;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public PlotGrowthRecord clone() {
        return this.copy();
    }

    @Override
    public PlotGrowthRecord copy() {
        return new PlotGrowthRecord(this);
    }

    public int getHorizontalScale() {
        return this.field_1_horizontalScale;
    }

    public void setHorizontalScale(int field_1_horizontalScale) {
        this.field_1_horizontalScale = field_1_horizontalScale;
    }

    public int getVerticalScale() {
        return this.field_2_verticalScale;
    }

    public void setVerticalScale(int field_2_verticalScale) {
        this.field_2_verticalScale = field_2_verticalScale;
    }
}

