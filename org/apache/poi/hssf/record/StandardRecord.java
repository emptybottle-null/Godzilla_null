/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.io.IOException;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianOutput;

public abstract class StandardRecord
extends Record {
    protected abstract int getDataSize();

    protected StandardRecord() {
    }

    protected StandardRecord(StandardRecord other) {
    }

    @Override
    public final int getRecordSize() {
        return 4 + this.getDataSize();
    }

    @Override
    public final int serialize(int offset, byte[] data) {
        int dataSize = this.getDataSize();
        int recSize = 4 + dataSize;
        try (LittleEndianByteArrayOutputStream out = new LittleEndianByteArrayOutputStream(data, offset, recSize);){
            out.writeShort(this.getSid());
            out.writeShort(dataSize);
            this.serialize(out);
            if (out.getWriteIndex() - offset != recSize) {
                throw new IllegalStateException("Error in serialization of (" + this.getClass().getName() + "): Incorrect number of bytes written - expected " + recSize + " but got " + (out.getWriteIndex() - offset));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
        return recSize;
    }

    protected abstract void serialize(LittleEndianOutput var1);

    @Override
    public abstract StandardRecord copy();
}

