/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class ChartEndBlockRecord
extends StandardRecord {
    public static final short sid = 2131;
    private short rt;
    private short grbitFrt;
    private short iObjectKind;
    private byte[] unused;

    public ChartEndBlockRecord() {
    }

    public ChartEndBlockRecord(ChartEndBlockRecord other) {
        super(other);
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        this.iObjectKind = other.iObjectKind;
        this.unused = other.unused == null ? null : (byte[])other.unused.clone();
    }

    public ChartEndBlockRecord(RecordInputStream in) {
        this.rt = in.readShort();
        this.grbitFrt = in.readShort();
        this.iObjectKind = in.readShort();
        if (in.available() == 0) {
            this.unused = new byte[0];
        } else {
            this.unused = new byte[6];
            in.readFully(this.unused);
        }
    }

    @Override
    protected int getDataSize() {
        return 6 + this.unused.length;
    }

    @Override
    public short getSid() {
        return 2131;
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.rt);
        out.writeShort(this.grbitFrt);
        out.writeShort(this.iObjectKind);
        out.write(this.unused);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[ENDBLOCK]\n");
        buffer.append("    .rt         =").append(HexDump.shortToHex(this.rt)).append('\n');
        buffer.append("    .grbitFrt   =").append(HexDump.shortToHex(this.grbitFrt)).append('\n');
        buffer.append("    .iObjectKind=").append(HexDump.shortToHex(this.iObjectKind)).append('\n');
        buffer.append("    .unused     =").append(HexDump.toHex(this.unused)).append('\n');
        buffer.append("[/ENDBLOCK]\n");
        return buffer.toString();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public ChartEndBlockRecord clone() {
        return this.copy();
    }

    @Override
    public ChartEndBlockRecord copy() {
        return new ChartEndBlockRecord(this);
    }
}

