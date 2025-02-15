/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.PageBreakRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.Removal;

public final class VerticalPageBreakRecord
extends PageBreakRecord {
    public static final short sid = 26;

    public VerticalPageBreakRecord() {
    }

    public VerticalPageBreakRecord(VerticalPageBreakRecord other) {
        super(other);
    }

    public VerticalPageBreakRecord(RecordInputStream in) {
        super(in);
    }

    @Override
    public short getSid() {
        return 26;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public VerticalPageBreakRecord clone() {
        return this.copy();
    }

    @Override
    public VerticalPageBreakRecord copy() {
        return new VerticalPageBreakRecord(this);
    }
}

