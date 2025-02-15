/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.hssf.record.common.FtrHeader;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class FeatHdrRecord
extends StandardRecord {
    public static final int SHAREDFEATURES_ISFPROTECTION = 2;
    public static final int SHAREDFEATURES_ISFFEC2 = 3;
    public static final int SHAREDFEATURES_ISFFACTOID = 4;
    public static final int SHAREDFEATURES_ISFLIST = 5;
    public static final short sid = 2151;
    private final FtrHeader futureHeader;
    private int isf_sharedFeatureType;
    private byte reserved;
    private long cbHdrData;
    private byte[] rgbHdrData;

    public FeatHdrRecord() {
        this.futureHeader = new FtrHeader();
        this.futureHeader.setRecordType((short)2151);
    }

    public FeatHdrRecord(FeatHdrRecord other) {
        super(other);
        this.futureHeader = other.futureHeader.copy();
        this.isf_sharedFeatureType = other.isf_sharedFeatureType;
        this.reserved = other.reserved;
        this.cbHdrData = other.cbHdrData;
        this.rgbHdrData = other.rgbHdrData == null ? null : (byte[])other.rgbHdrData.clone();
    }

    public FeatHdrRecord(RecordInputStream in) {
        this.futureHeader = new FtrHeader(in);
        this.isf_sharedFeatureType = in.readShort();
        this.reserved = in.readByte();
        this.cbHdrData = in.readInt();
        this.rgbHdrData = in.readRemainder();
    }

    @Override
    public short getSid() {
        return 2151;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[FEATURE HEADER]\n");
        buffer.append("[/FEATURE HEADER]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        this.futureHeader.serialize(out);
        out.writeShort(this.isf_sharedFeatureType);
        out.writeByte(this.reserved);
        out.writeInt((int)this.cbHdrData);
        out.write(this.rgbHdrData);
    }

    @Override
    protected int getDataSize() {
        return 19 + this.rgbHdrData.length;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public FeatHdrRecord clone() {
        return this.copy();
    }

    @Override
    public FeatHdrRecord copy() {
        return new FeatHdrRecord(this);
    }
}

