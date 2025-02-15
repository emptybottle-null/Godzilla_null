/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.Margin;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class LeftMarginRecord
extends StandardRecord
implements Margin {
    public static final short sid = 38;
    private double field_1_margin;

    public LeftMarginRecord() {
    }

    public LeftMarginRecord(LeftMarginRecord other) {
        super(other);
        this.field_1_margin = other.field_1_margin;
    }

    public LeftMarginRecord(RecordInputStream in) {
        this.field_1_margin = in.readDouble();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[LeftMargin]\n");
        buffer.append("    .margin               = ").append(" (").append(this.getMargin()).append(" )\n");
        buffer.append("[/LeftMargin]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeDouble(this.field_1_margin);
    }

    @Override
    protected int getDataSize() {
        return 8;
    }

    @Override
    public short getSid() {
        return 38;
    }

    @Override
    public double getMargin() {
        return this.field_1_margin;
    }

    @Override
    public void setMargin(double field_1_margin) {
        this.field_1_margin = field_1_margin;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public LeftMarginRecord clone() {
        return this.copy();
    }

    @Override
    public LeftMarginRecord copy() {
        return new LeftMarginRecord(this);
    }
}

