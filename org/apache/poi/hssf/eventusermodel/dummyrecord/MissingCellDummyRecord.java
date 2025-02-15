/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.eventusermodel.dummyrecord;

import org.apache.poi.hssf.eventusermodel.dummyrecord.DummyRecordBase;

public final class MissingCellDummyRecord
extends DummyRecordBase {
    private final int row;
    private final int column;

    public MissingCellDummyRecord(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public MissingCellDummyRecord copy() {
        return this;
    }
}

