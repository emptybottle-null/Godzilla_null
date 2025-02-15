/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.Removal;

public final class ObjRecord
extends Record {
    public static final short sid = 93;
    private static final int NORMAL_PAD_ALIGNMENT = 2;
    private static int MAX_PAD_ALIGNMENT = 4;
    private final List<SubRecord> subrecords = new ArrayList<SubRecord>();
    private final byte[] _uninterpretedData;
    private boolean _isPaddedToQuadByteMultiple;

    public ObjRecord() {
        this._uninterpretedData = null;
    }

    public ObjRecord(ObjRecord other) {
        other.subrecords.stream().map(SubRecord::copy).forEach(this.subrecords::add);
        this._uninterpretedData = other._uninterpretedData == null ? null : (byte[])other._uninterpretedData.clone();
        this._isPaddedToQuadByteMultiple = other._isPaddedToQuadByteMultiple;
    }

    public ObjRecord(RecordInputStream in) {
        SubRecord subRecord;
        byte[] subRecordData = in.readRemainder();
        if (LittleEndian.getUShort(subRecordData, 0) != 21) {
            this._uninterpretedData = subRecordData;
            return;
        }
        LittleEndianByteArrayInputStream subRecStream = new LittleEndianByteArrayInputStream(subRecordData);
        CommonObjectDataSubRecord cmo = (CommonObjectDataSubRecord)SubRecord.createSubRecord(subRecStream, 0);
        this.subrecords.add(cmo);
        do {
            subRecord = SubRecord.createSubRecord(subRecStream, cmo.getObjectType());
            this.subrecords.add(subRecord);
        } while (!subRecord.isTerminating());
        int nRemainingBytes = subRecordData.length - subRecStream.getReadIndex();
        if (nRemainingBytes > 0) {
            this._isPaddedToQuadByteMultiple = subRecordData.length % MAX_PAD_ALIGNMENT == 0;
            if (nRemainingBytes >= (this._isPaddedToQuadByteMultiple ? MAX_PAD_ALIGNMENT : 2)) {
                if (!ObjRecord.canPaddingBeDiscarded(subRecordData, nRemainingBytes)) {
                    String msg = "Leftover " + nRemainingBytes + " bytes in subrecord data " + HexDump.toHex(subRecordData);
                    throw new RecordFormatException(msg);
                }
                this._isPaddedToQuadByteMultiple = false;
            }
        } else {
            this._isPaddedToQuadByteMultiple = false;
        }
        this._uninterpretedData = null;
    }

    private static boolean canPaddingBeDiscarded(byte[] data, int nRemainingBytes) {
        for (int i = data.length - nRemainingBytes; i < data.length; ++i) {
            if (data[i] == 0) continue;
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[OBJ]\n");
        for (SubRecord record : this.subrecords) {
            sb.append("SUBRECORD: ").append(record);
        }
        sb.append("[/OBJ]\n");
        return sb.toString();
    }

    @Override
    public int getRecordSize() {
        if (this._uninterpretedData != null) {
            return this._uninterpretedData.length + 4;
        }
        int size = 0;
        for (SubRecord record : this.subrecords) {
            size += record.getDataSize() + 4;
        }
        if (this._isPaddedToQuadByteMultiple) {
            while (size % MAX_PAD_ALIGNMENT != 0) {
                ++size;
            }
        } else {
            while (size % 2 != 0) {
                ++size;
            }
        }
        return size + 4;
    }

    @Override
    public int serialize(int offset, byte[] data) {
        int recSize = this.getRecordSize();
        int dataSize = recSize - 4;
        try (LittleEndianByteArrayOutputStream out = new LittleEndianByteArrayOutputStream(data, offset, recSize);){
            out.writeShort(93);
            out.writeShort(dataSize);
            if (this._uninterpretedData == null) {
                for (int i = 0; i < this.subrecords.size(); ++i) {
                    SubRecord record = this.subrecords.get(i);
                    record.serialize(out);
                }
                int expectedEndIx = offset + dataSize;
                while (out.getWriteIndex() < expectedEndIx) {
                    out.writeByte(0);
                }
            } else {
                out.write(this._uninterpretedData);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
        return recSize;
    }

    @Override
    public short getSid() {
        return 93;
    }

    public List<SubRecord> getSubRecords() {
        return this.subrecords;
    }

    public void clearSubRecords() {
        this.subrecords.clear();
    }

    public void addSubRecord(int index, SubRecord element) {
        this.subrecords.add(index, element);
    }

    public boolean addSubRecord(SubRecord o) {
        return this.subrecords.add(o);
    }

    @Deprecated
    @Removal(version="5.0.0")
    public ObjRecord clone() {
        return this.copy();
    }

    @Override
    public ObjRecord copy() {
        return new ObjRecord(this);
    }
}

