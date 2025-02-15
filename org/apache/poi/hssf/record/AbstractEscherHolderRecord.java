/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.NullEscherSerializationListener;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.util.LazilyConcatenatedByteArray;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

public abstract class AbstractEscherHolderRecord
extends Record {
    private static boolean DESERIALISE;
    private final List<EscherRecord> escherRecords = new ArrayList<EscherRecord>();
    private final LazilyConcatenatedByteArray rawDataContainer = new LazilyConcatenatedByteArray();

    public AbstractEscherHolderRecord() {
    }

    public AbstractEscherHolderRecord(AbstractEscherHolderRecord other) {
        other.escherRecords.stream().map(EscherRecord::copy).forEach(this.escherRecords::add);
        this.rawDataContainer.concatenate(other.rawDataContainer);
    }

    public AbstractEscherHolderRecord(RecordInputStream in) {
        if (!DESERIALISE) {
            this.rawDataContainer.concatenate(in.readRemainder());
        } else {
            byte[] data = in.readAllContinuedRemainder();
            this.convertToEscherRecords(0, data.length, data);
        }
    }

    protected void convertRawBytesToEscherRecords() {
        if (!DESERIALISE) {
            byte[] rawData = this.getRawData();
            this.convertToEscherRecords(0, rawData.length, rawData);
        }
    }

    private void convertToEscherRecords(int offset, int size, byte[] data) {
        int bytesRead;
        this.escherRecords.clear();
        DefaultEscherRecordFactory recordFactory = new DefaultEscherRecordFactory();
        for (int pos = offset; pos < offset + size; pos += bytesRead) {
            EscherRecord r = recordFactory.createRecord(data, pos);
            bytesRead = r.fillFields(data, pos, recordFactory);
            this.escherRecords.add(r);
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        String nl = System.getProperty("line.separator");
        buffer.append('[' + this.getRecordName() + ']' + nl);
        if (this.escherRecords.size() == 0) {
            buffer.append("No Escher Records Decoded" + nl);
        }
        for (EscherRecord r : this.escherRecords) {
            buffer.append(r);
        }
        buffer.append("[/" + this.getRecordName() + ']' + nl);
        return buffer.toString();
    }

    protected abstract String getRecordName();

    @Override
    public int serialize(int offset, byte[] data) {
        LittleEndian.putShort(data, 0 + offset, this.getSid());
        LittleEndian.putShort(data, 2 + offset, (short)(this.getRecordSize() - 4));
        byte[] rawData = this.getRawData();
        if (this.escherRecords.size() == 0 && rawData != null) {
            LittleEndian.putShort(data, 0 + offset, this.getSid());
            LittleEndian.putShort(data, 2 + offset, (short)(this.getRecordSize() - 4));
            System.arraycopy(rawData, 0, data, 4 + offset, rawData.length);
            return rawData.length + 4;
        }
        LittleEndian.putShort(data, 0 + offset, this.getSid());
        LittleEndian.putShort(data, 2 + offset, (short)(this.getRecordSize() - 4));
        int pos = offset + 4;
        for (EscherRecord r : this.escherRecords) {
            pos += r.serialize(pos, data, new NullEscherSerializationListener());
        }
        return this.getRecordSize();
    }

    @Override
    public int getRecordSize() {
        byte[] rawData = this.getRawData();
        if (this.escherRecords.size() == 0 && rawData != null) {
            return rawData.length;
        }
        int size = 0;
        for (EscherRecord r : this.escherRecords) {
            size += r.getRecordSize();
        }
        return size;
    }

    @Override
    public abstract short getSid();

    @Deprecated
    @Removal(version="5.0.0")
    public AbstractEscherHolderRecord clone() {
        return this.copy();
    }

    @Override
    public abstract AbstractEscherHolderRecord copy();

    public void addEscherRecord(int index, EscherRecord element) {
        this.escherRecords.add(index, element);
    }

    public boolean addEscherRecord(EscherRecord element) {
        return this.escherRecords.add(element);
    }

    public List<EscherRecord> getEscherRecords() {
        return this.escherRecords;
    }

    public void clearEscherRecords() {
        this.escherRecords.clear();
    }

    public EscherContainerRecord getEscherContainer() {
        for (EscherRecord er : this.escherRecords) {
            if (!(er instanceof EscherContainerRecord)) continue;
            return (EscherContainerRecord)er;
        }
        return null;
    }

    public EscherRecord findFirstWithId(short id) {
        return this.findFirstWithId(id, this.getEscherRecords());
    }

    private EscherRecord findFirstWithId(short id, List<EscherRecord> records) {
        for (EscherRecord r : records) {
            if (r.getRecordId() != id) continue;
            return r;
        }
        for (EscherRecord r : records) {
            EscherRecord found;
            if (!r.isContainerRecord() || (found = this.findFirstWithId(id, r.getChildRecords())) == null) continue;
            return found;
        }
        return null;
    }

    public EscherRecord getEscherRecord(int index) {
        return this.escherRecords.get(index);
    }

    public void join(AbstractEscherHolderRecord record) {
        this.rawDataContainer.concatenate(record.getRawData());
    }

    public void processContinueRecord(byte[] record) {
        this.rawDataContainer.concatenate(record);
    }

    public byte[] getRawData() {
        return this.rawDataContainer.toArray();
    }

    public void setRawData(byte[] rawData) {
        this.rawDataContainer.clear();
        this.rawDataContainer.concatenate(rawData);
    }

    public void decode() {
        if (null == this.escherRecords || 0 == this.escherRecords.size()) {
            byte[] rawData = this.getRawData();
            this.convertToEscherRecords(0, rawData.length, rawData);
        }
    }

    static {
        try {
            DESERIALISE = System.getProperty("poi.deserialize.escher") != null;
        } catch (SecurityException e) {
            DESERIALISE = false;
        }
    }
}

