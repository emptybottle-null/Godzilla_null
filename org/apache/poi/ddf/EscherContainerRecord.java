/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordFactory;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public final class EscherContainerRecord
extends EscherRecord
implements Iterable<EscherRecord> {
    public static final short DGG_CONTAINER = EscherRecordTypes.DGG_CONTAINER.typeID;
    public static final short BSTORE_CONTAINER = EscherRecordTypes.BSTORE_CONTAINER.typeID;
    public static final short DG_CONTAINER = EscherRecordTypes.DG_CONTAINER.typeID;
    public static final short SPGR_CONTAINER = EscherRecordTypes.SPGR_CONTAINER.typeID;
    public static final short SP_CONTAINER = EscherRecordTypes.SP_CONTAINER.typeID;
    public static final short SOLVER_CONTAINER = EscherRecordTypes.SOLVER_CONTAINER.typeID;
    private static final POILogger log = POILogFactory.getLogger(EscherContainerRecord.class);
    private int _remainingLength;
    private final List<EscherRecord> _childRecords = new ArrayList<EscherRecord>();

    public EscherContainerRecord() {
    }

    public EscherContainerRecord(EscherContainerRecord other) {
        super(other);
        this._remainingLength = other._remainingLength;
        other._childRecords.stream().map(EscherRecord::copy).forEach(this._childRecords::add);
    }

    @Override
    public int fillFields(byte[] data, int pOffset, EscherRecordFactory recordFactory) {
        int bytesRemaining = this.readHeader(data, pOffset);
        int bytesWritten = 8;
        int offset = pOffset + 8;
        while (bytesRemaining > 0 && offset < data.length) {
            EscherRecord child = recordFactory.createRecord(data, offset);
            int childBytesWritten = child.fillFields(data, offset, recordFactory);
            bytesWritten += childBytesWritten;
            this.addChildRecord(child);
            if ((offset += childBytesWritten) < data.length || (bytesRemaining -= childBytesWritten) <= 0) continue;
            this._remainingLength = bytesRemaining;
            if (!log.check(5)) continue;
            log.log(5, "Not enough Escher data: " + bytesRemaining + " bytes remaining but no space left");
        }
        return bytesWritten;
    }

    @Override
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, this.getRecordId(), this);
        LittleEndian.putShort(data, offset, this.getOptions());
        LittleEndian.putShort(data, offset + 2, this.getRecordId());
        int remainingBytes = 0;
        for (EscherRecord r : this) {
            remainingBytes += r.getRecordSize();
        }
        LittleEndian.putInt(data, offset + 4, remainingBytes += this._remainingLength);
        int pos = offset + 8;
        for (EscherRecord r : this) {
            pos += r.serialize(pos, data, listener);
        }
        listener.afterRecordSerialize(pos, this.getRecordId(), pos - offset, this);
        return pos - offset;
    }

    @Override
    public int getRecordSize() {
        int childRecordsSize = 0;
        for (EscherRecord r : this) {
            childRecordsSize += r.getRecordSize();
        }
        return 8 + childRecordsSize;
    }

    public boolean hasChildOfType(short recordId) {
        return this._childRecords.stream().anyMatch(r -> r.getRecordId() == recordId);
    }

    @Override
    public EscherRecord getChild(int index) {
        return this._childRecords.get(index);
    }

    @Override
    public List<EscherRecord> getChildRecords() {
        return new ArrayList<EscherRecord>(this._childRecords);
    }

    @Override
    public Iterator<EscherRecord> iterator() {
        return Collections.unmodifiableList(this._childRecords).iterator();
    }

    @Override
    public void setChildRecords(List<EscherRecord> childRecords) {
        if (childRecords == this._childRecords) {
            throw new IllegalStateException("Child records private data member has escaped");
        }
        this._childRecords.clear();
        this._childRecords.addAll(childRecords);
    }

    public boolean removeChildRecord(EscherRecord toBeRemoved) {
        return this._childRecords.remove(toBeRemoved);
    }

    public List<EscherContainerRecord> getChildContainers() {
        ArrayList<EscherContainerRecord> containers = new ArrayList<EscherContainerRecord>();
        for (EscherRecord r : this) {
            if (!(r instanceof EscherContainerRecord)) continue;
            containers.add((EscherContainerRecord)r);
        }
        return containers;
    }

    @Override
    public String getRecordName() {
        short id = this.getRecordId();
        EscherRecordTypes t = EscherRecordTypes.forTypeID(id);
        return t != EscherRecordTypes.UNKNOWN ? t.recordName : "Container 0x" + HexDump.toHex(id);
    }

    @Override
    public void display(PrintWriter w, int indent) {
        super.display(w, indent);
        for (EscherRecord escherRecord : this) {
            escherRecord.display(w, indent + 1);
        }
    }

    public void addChildRecord(EscherRecord record) {
        this._childRecords.add(record);
    }

    public void addChildBefore(EscherRecord record, int insertBeforeRecordId) {
        int idx = 0;
        for (EscherRecord rec : this) {
            if (rec.getRecordId() == (short)insertBeforeRecordId) break;
            ++idx;
        }
        this._childRecords.add(idx, record);
    }

    public <T extends EscherRecord> T getChildById(short recordId) {
        for (EscherRecord childRecord : this) {
            if (childRecord.getRecordId() != recordId) continue;
            EscherRecord result = childRecord;
            return (T)result;
        }
        return null;
    }

    public void getRecordsById(short recordId, List<EscherRecord> out) {
        for (EscherRecord r : this) {
            if (r instanceof EscherContainerRecord) {
                EscherContainerRecord c = (EscherContainerRecord)r;
                c.getRecordsById(recordId, out);
                continue;
            }
            if (r.getRecordId() != recordId) continue;
            out.add(r);
        }
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "isContainer", this::isContainerRecord);
    }

    @Override
    public Enum getGenericRecordType() {
        return EscherRecordTypes.forTypeID(this.getRecordId());
    }

    @Override
    public EscherContainerRecord copy() {
        return new EscherContainerRecord(this);
    }
}

