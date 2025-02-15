/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class EOFRecord
extends StandardRecord {
    public static final short sid = 10;
    public static final int ENCODED_SIZE = 4;
    public static final EOFRecord instance = new EOFRecord();

    private EOFRecord() {
    }

    public EOFRecord(RecordInputStream in) {
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[EOF]\n");
        buffer.append("[/EOF]\n");
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
        return 10;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public EOFRecord clone() {
        return this.copy();
    }

    @Override
    public EOFRecord copy() {
        return instance;
    }
}

