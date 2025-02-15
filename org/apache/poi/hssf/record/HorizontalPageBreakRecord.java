/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.PageBreakRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.Removal;

public final class HorizontalPageBreakRecord
extends PageBreakRecord {
    public static final short sid = 27;

    public HorizontalPageBreakRecord() {
    }

    public HorizontalPageBreakRecord(HorizontalPageBreakRecord other) {
        super(other);
    }

    public HorizontalPageBreakRecord(RecordInputStream in) {
        super(in);
    }

    @Override
    public short getSid() {
        return 27;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public PageBreakRecord clone() {
        return this.copy();
    }

    @Override
    public HorizontalPageBreakRecord copy() {
        return new HorizontalPageBreakRecord(this);
    }
}

