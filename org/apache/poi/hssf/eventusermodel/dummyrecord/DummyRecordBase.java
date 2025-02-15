/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.eventusermodel.dummyrecord;

import org.apache.poi.hssf.record.Record;
import org.apache.poi.util.RecordFormatException;

abstract class DummyRecordBase
extends Record {
    protected DummyRecordBase() {
    }

    @Override
    public final short getSid() {
        return -1;
    }

    @Override
    public int serialize(int offset, byte[] data) {
        throw new RecordFormatException("Cannot serialize a dummy record");
    }

    @Override
    public final int getRecordSize() {
        throw new RecordFormatException("Cannot serialize a dummy record");
    }
}

