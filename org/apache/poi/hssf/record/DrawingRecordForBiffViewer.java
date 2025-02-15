/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.io.ByteArrayInputStream;
import org.apache.poi.hssf.record.AbstractEscherHolderRecord;
import org.apache.poi.hssf.record.DrawingRecord;
import org.apache.poi.hssf.record.RecordInputStream;

public final class DrawingRecordForBiffViewer
extends AbstractEscherHolderRecord {
    public static final short sid = 236;

    public DrawingRecordForBiffViewer() {
    }

    public DrawingRecordForBiffViewer(DrawingRecordForBiffViewer other) {
        super(other);
    }

    public DrawingRecordForBiffViewer(RecordInputStream in) {
        super(in);
    }

    public DrawingRecordForBiffViewer(DrawingRecord r) {
        super(DrawingRecordForBiffViewer.convertToInputStream(r));
        this.convertRawBytesToEscherRecords();
    }

    private static RecordInputStream convertToInputStream(DrawingRecord r) {
        byte[] data = r.serialize();
        RecordInputStream rinp = new RecordInputStream(new ByteArrayInputStream(data));
        rinp.nextRecord();
        return rinp;
    }

    @Override
    protected String getRecordName() {
        return "MSODRAWING";
    }

    @Override
    public short getSid() {
        return 236;
    }

    @Override
    public DrawingRecordForBiffViewer copy() {
        return new DrawingRecordForBiffViewer(this);
    }
}

