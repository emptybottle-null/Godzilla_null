/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import java.util.stream.Stream;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class ChartFRTInfoRecord
extends StandardRecord {
    public static final short sid = 2128;
    private short rt;
    private short grbitFrt;
    private byte verOriginator;
    private byte verWriter;
    private CFRTID[] rgCFRTID;

    public ChartFRTInfoRecord(ChartFRTInfoRecord other) {
        super(other);
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        this.verOriginator = other.verOriginator;
        this.verWriter = other.verWriter;
        if (other.rgCFRTID != null) {
            this.rgCFRTID = (CFRTID[])Stream.of(other.rgCFRTID).map(CFRTID::new).toArray(CFRTID[]::new);
        }
    }

    public ChartFRTInfoRecord(RecordInputStream in) {
        this.rt = in.readShort();
        this.grbitFrt = in.readShort();
        this.verOriginator = in.readByte();
        this.verWriter = in.readByte();
        int cCFRTID = in.readShort();
        this.rgCFRTID = new CFRTID[cCFRTID];
        for (int i = 0; i < cCFRTID; ++i) {
            this.rgCFRTID[i] = new CFRTID(in);
        }
    }

    @Override
    protected int getDataSize() {
        return 8 + this.rgCFRTID.length * 4;
    }

    @Override
    public short getSid() {
        return 2128;
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.rt);
        out.writeShort(this.grbitFrt);
        out.writeByte(this.verOriginator);
        out.writeByte(this.verWriter);
        out.writeShort(this.rgCFRTID.length);
        for (CFRTID cfrtid : this.rgCFRTID) {
            cfrtid.serialize(out);
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[CHARTFRTINFO]\n");
        buffer.append("    .rt           =").append(HexDump.shortToHex(this.rt)).append('\n');
        buffer.append("    .grbitFrt     =").append(HexDump.shortToHex(this.grbitFrt)).append('\n');
        buffer.append("    .verOriginator=").append(HexDump.byteToHex(this.verOriginator)).append('\n');
        buffer.append("    .verWriter    =").append(HexDump.byteToHex(this.verOriginator)).append('\n');
        buffer.append("    .nCFRTIDs     =").append(HexDump.shortToHex(this.rgCFRTID.length)).append('\n');
        buffer.append("[/CHARTFRTINFO]\n");
        return buffer.toString();
    }

    @Override
    public ChartFRTInfoRecord copy() {
        return new ChartFRTInfoRecord(this);
    }

    private static final class CFRTID {
        public static final int ENCODED_SIZE = 4;
        private int rtFirst;
        private int rtLast;

        public CFRTID(CFRTID other) {
            this.rtFirst = other.rtFirst;
            this.rtLast = other.rtLast;
        }

        public CFRTID(LittleEndianInput in) {
            this.rtFirst = in.readShort();
            this.rtLast = in.readShort();
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this.rtFirst);
            out.writeShort(this.rtLast);
        }
    }
}

