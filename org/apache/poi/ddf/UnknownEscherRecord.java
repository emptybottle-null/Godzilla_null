/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordFactory;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

public final class UnknownEscherRecord
extends EscherRecord {
    private static final int MAX_RECORD_LENGTH = 100000000;
    private static final byte[] NO_BYTES = new byte[0];
    private byte[] thedata = NO_BYTES;
    private final List<EscherRecord> _childRecords = new ArrayList<EscherRecord>();

    public UnknownEscherRecord() {
    }

    public UnknownEscherRecord(UnknownEscherRecord other) {
        super(other);
        other._childRecords.stream().map(EscherRecord::copy).forEach(this._childRecords::add);
    }

    @Override
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int available;
        int bytesRemaining = this.readHeader(data, offset);
        if (bytesRemaining > (available = data.length - (offset + 8))) {
            bytesRemaining = available;
        }
        if (this.isContainerRecord()) {
            int bytesWritten = 0;
            this.thedata = new byte[0];
            offset += 8;
            bytesWritten += 8;
            while (bytesRemaining > 0) {
                EscherRecord child = recordFactory.createRecord(data, offset);
                int childBytesWritten = child.fillFields(data, offset, recordFactory);
                bytesWritten += childBytesWritten;
                offset += childBytesWritten;
                bytesRemaining -= childBytesWritten;
                this.getChildRecords().add(child);
            }
            return bytesWritten;
        }
        if (bytesRemaining < 0) {
            bytesRemaining = 0;
        }
        this.thedata = IOUtils.safelyAllocate(bytesRemaining, 100000000);
        System.arraycopy(data, offset + 8, this.thedata, 0, bytesRemaining);
        return bytesRemaining + 8;
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        LittleEndian.putShort(data, offset, this.getOptions());
        LittleEndian.putShort(data, offset + 2, this.getRecordId());
        int remainingBytes = this.thedata.length;
        for (EscherRecord r : this._childRecords) {
            remainingBytes += r.getRecordSize();
        }
        LittleEndian.putInt(data, offset + 4, remainingBytes);
        System.arraycopy(this.thedata, 0, data, offset + 8, this.thedata.length);
        int pos = offset + 8 + this.thedata.length;
        for (EscherRecord r : this._childRecords) {
            pos += r.serialize(pos, data, listener);
        }
        listener.afterRecordSerialize(pos, this.getRecordId(), pos - offset, this);
        return pos - offset;
    }

    public byte[] getData() {
        return this.thedata;
    }

    @Override
    public int getRecordSize() {
        return 8 + this.thedata.length;
    }

    @Override
    public List<EscherRecord> getChildRecords() {
        return this._childRecords;
    }

    @Override
    public void setChildRecords(List<EscherRecord> childRecords) {
        if (childRecords == this._childRecords) {
            return;
        }
        this._childRecords.clear();
        this._childRecords.addAll(childRecords);
    }

    @Override
    public String getRecordName() {
        return "Unknown 0x" + HexDump.toHex(this.getRecordId());
    }

    public void addChildRecord(EscherRecord childRecord) {
        this.getChildRecords().add(childRecord);
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "data", this::getData);
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.UNKNOWN;
    }

    @Override
    public UnknownEscherRecord copy() {
        return new UnknownEscherRecord(this);
    }
}

