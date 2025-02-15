/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class ChartStartBlockRecord
extends StandardRecord {
    public static final short sid = 2130;
    private short rt;
    private short grbitFrt;
    private short iObjectKind;
    private short iObjectContext;
    private short iObjectInstance1;
    private short iObjectInstance2;

    public ChartStartBlockRecord() {
    }

    public ChartStartBlockRecord(ChartStartBlockRecord other) {
        super(other);
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        this.iObjectKind = other.iObjectKind;
        this.iObjectContext = other.iObjectContext;
        this.iObjectInstance1 = other.iObjectInstance1;
        this.iObjectInstance2 = other.iObjectInstance2;
    }

    public ChartStartBlockRecord(RecordInputStream in) {
        this.rt = in.readShort();
        this.grbitFrt = in.readShort();
        this.iObjectKind = in.readShort();
        this.iObjectContext = in.readShort();
        this.iObjectInstance1 = in.readShort();
        this.iObjectInstance2 = in.readShort();
    }

    @Override
    protected int getDataSize() {
        return 12;
    }

    @Override
    public short getSid() {
        return 2130;
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.rt);
        out.writeShort(this.grbitFrt);
        out.writeShort(this.iObjectKind);
        out.writeShort(this.iObjectContext);
        out.writeShort(this.iObjectInstance1);
        out.writeShort(this.iObjectInstance2);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[STARTBLOCK]\n");
        buffer.append("    .rt              =").append(HexDump.shortToHex(this.rt)).append('\n');
        buffer.append("    .grbitFrt        =").append(HexDump.shortToHex(this.grbitFrt)).append('\n');
        buffer.append("    .iObjectKind     =").append(HexDump.shortToHex(this.iObjectKind)).append('\n');
        buffer.append("    .iObjectContext  =").append(HexDump.shortToHex(this.iObjectContext)).append('\n');
        buffer.append("    .iObjectInstance1=").append(HexDump.shortToHex(this.iObjectInstance1)).append('\n');
        buffer.append("    .iObjectInstance2=").append(HexDump.shortToHex(this.iObjectInstance2)).append('\n');
        buffer.append("[/STARTBLOCK]\n");
        return buffer.toString();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public ChartStartBlockRecord clone() {
        return this.copy();
    }

    @Override
    public ChartStartBlockRecord copy() {
        return new ChartStartBlockRecord(this);
    }
}

