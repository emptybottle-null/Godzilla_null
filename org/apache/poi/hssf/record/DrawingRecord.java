/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class DrawingRecord
extends StandardRecord {
    public static final short sid = 236;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private byte[] recordData;
    private byte[] contd;

    public DrawingRecord() {
        this.recordData = EMPTY_BYTE_ARRAY;
    }

    public DrawingRecord(DrawingRecord other) {
        super(other);
        this.recordData = other.recordData == null ? null : (byte[])other.recordData.clone();
        this.contd = other.contd == null ? null : (byte[])other.contd.clone();
    }

    public DrawingRecord(RecordInputStream in) {
        this.recordData = in.readRemainder();
    }

    public DrawingRecord(byte[] data) {
        this.recordData = (byte[])data.clone();
    }

    @Deprecated
    void processContinueRecord(byte[] record) {
        this.contd = record;
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.write(this.recordData);
    }

    @Override
    protected int getDataSize() {
        return this.recordData.length;
    }

    @Override
    public short getSid() {
        return 236;
    }

    public byte[] getRecordData() {
        return this.recordData;
    }

    public void setData(byte[] thedata) {
        if (thedata == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.recordData = thedata;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public DrawingRecord clone() {
        return this.copy();
    }

    @Override
    public DrawingRecord copy() {
        return new DrawingRecord(this);
    }

    @Override
    public String toString() {
        return "DrawingRecord[" + this.recordData.length + "]";
    }
}

