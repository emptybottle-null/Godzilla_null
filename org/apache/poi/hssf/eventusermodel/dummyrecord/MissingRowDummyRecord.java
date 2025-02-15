/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.eventusermodel.dummyrecord;

import org.apache.poi.hssf.eventusermodel.dummyrecord.DummyRecordBase;

public final class MissingRowDummyRecord
extends DummyRecordBase {
    private final int rowNumber;

    public MissingRowDummyRecord(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    @Override
    public MissingRowDummyRecord copy() {
        return this;
    }
}

