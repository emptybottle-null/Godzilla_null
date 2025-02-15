/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.aggregates;

import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;

public abstract class RecordAggregate
extends RecordBase {
    public abstract void visitContainedRecords(RecordVisitor var1);

    @Override
    public final int serialize(int offset, byte[] data) {
        SerializingRecordVisitor srv = new SerializingRecordVisitor(data, offset);
        this.visitContainedRecords(srv);
        return srv.countBytesWritten();
    }

    @Override
    public int getRecordSize() {
        RecordSizingVisitor rsv = new RecordSizingVisitor();
        this.visitContainedRecords(rsv);
        return rsv.getTotalSize();
    }

    public static final class PositionTrackingVisitor
    implements RecordVisitor {
        private final RecordVisitor _rv;
        private int _position;

        public PositionTrackingVisitor(RecordVisitor rv, int initialPosition) {
            this._rv = rv;
            this._position = initialPosition;
        }

        @Override
        public void visitRecord(Record r) {
            this._position += r.getRecordSize();
            this._rv.visitRecord(r);
        }

        public void setPosition(int position) {
            this._position = position;
        }

        public int getPosition() {
            return this._position;
        }
    }

    private static final class RecordSizingVisitor
    implements RecordVisitor {
        private int _totalSize = 0;

        public int getTotalSize() {
            return this._totalSize;
        }

        @Override
        public void visitRecord(Record r) {
            this._totalSize += r.getRecordSize();
        }
    }

    private static final class SerializingRecordVisitor
    implements RecordVisitor {
        private final byte[] _data;
        private final int _startOffset;
        private int _countBytesWritten;

        public SerializingRecordVisitor(byte[] data, int startOffset) {
            this._data = data;
            this._startOffset = startOffset;
            this._countBytesWritten = 0;
        }

        public int countBytesWritten() {
            return this._countBytesWritten;
        }

        @Override
        public void visitRecord(Record r) {
            int currentOffset = this._startOffset + this._countBytesWritten;
            this._countBytesWritten += r.serialize(currentOffset, this._data);
        }
    }

    public static interface RecordVisitor {
        public void visitRecord(Record var1);
    }
}

