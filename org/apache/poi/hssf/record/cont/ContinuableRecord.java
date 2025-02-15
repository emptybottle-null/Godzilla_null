/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.cont;

import java.io.IOException;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;

public abstract class ContinuableRecord
extends Record {
    protected ContinuableRecord() {
    }

    protected ContinuableRecord(ContinuableRecord other) {
        super(other);
    }

    protected abstract void serialize(ContinuableRecordOutput var1);

    @Override
    public final int getRecordSize() {
        ContinuableRecordOutput out = ContinuableRecordOutput.createForCountingOnly();
        this.serialize(out);
        out.terminate();
        return out.getTotalSize();
    }

    @Override
    public final int serialize(int offset, byte[] data) {
        int totalSize = 0;
        try (LittleEndianByteArrayOutputStream leo = new LittleEndianByteArrayOutputStream(data, offset);){
            ContinuableRecordOutput out = new ContinuableRecordOutput(leo, this.getSid());
            this.serialize(out);
            out.terminate();
            totalSize = out.getTotalSize();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
        return totalSize;
    }
}

