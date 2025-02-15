/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class EndRecord
extends StandardRecord {
    public static final short sid = 4148;

    public EndRecord() {
    }

    public EndRecord(RecordInputStream in) {
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[END]\n");
        buffer.append("[/END]\n");
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
        return 4148;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public EndRecord clone() {
        return this.copy();
    }

    @Override
    public EndRecord copy() {
        return new EndRecord();
    }
}

