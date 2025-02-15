/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class BeginRecord
extends StandardRecord {
    public static final short sid = 4147;

    public BeginRecord() {
    }

    public BeginRecord(BeginRecord other) {
        super(other);
    }

    public BeginRecord(RecordInputStream in) {
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[BEGIN]\n");
        buffer.append("[/BEGIN]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
    }

    @Override
    protected int getDataSize() {
        return 0;
    }

    @Override
    public short getSid() {
        return 4147;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public BeginRecord clone() {
        return this.copy();
    }

    @Override
    public BeginRecord copy() {
        return new BeginRecord(this);
    }
}

