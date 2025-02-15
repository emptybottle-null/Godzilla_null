/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;

public final class WriteProtectRecord
extends StandardRecord {
    public static final short sid = 134;

    public WriteProtectRecord() {
    }

    public WriteProtectRecord(RecordInputStream in) {
        if (in.remaining() == 2) {
            in.readShort();
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[WRITEPROTECT]\n");
        buffer.append("[/WRITEPROTECT]\n");
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
        return 134;
    }

    @Override
    public WriteProtectRecord copy() {
        return new WriteProtectRecord();
    }
}

