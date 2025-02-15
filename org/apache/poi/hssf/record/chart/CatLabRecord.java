/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.chart;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;

public final class CatLabRecord
extends StandardRecord {
    public static final short sid = 2134;
    private short rt;
    private short grbitFrt;
    private short wOffset;
    private short at;
    private short grbit;
    private Short unused;

    public CatLabRecord(CatLabRecord other) {
        super(other);
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        this.wOffset = other.wOffset;
        this.at = other.at;
        this.grbit = other.grbit;
        this.unused = other.unused;
    }

    public CatLabRecord(RecordInputStream in) {
        this.rt = in.readShort();
        this.grbitFrt = in.readShort();
        this.wOffset = in.readShort();
        this.at = in.readShort();
        this.grbit = in.readShort();
        this.unused = in.available() == 0 ? null : Short.valueOf(in.readShort());
    }

    @Override
    protected int getDataSize() {
        return 10 + (this.unused == null ? 0 : 2);
    }

    @Override
    public short getSid() {
        return 2134;
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.rt);
        out.writeShort(this.grbitFrt);
        out.writeShort(this.wOffset);
        out.writeShort(this.at);
        out.writeShort(this.grbit);
        if (this.unused != null) {
            out.writeShort(this.unused.shortValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[CATLAB]\n");
        buffer.append("    .rt      =").append(HexDump.shortToHex(this.rt)).append('\n');
        buffer.append("    .grbitFrt=").append(HexDump.shortToHex(this.grbitFrt)).append('\n');
        buffer.append("    .wOffset =").append(HexDump.shortToHex(this.wOffset)).append('\n');
        buffer.append("    .at      =").append(HexDump.shortToHex(this.at)).append('\n');
        buffer.append("    .grbit   =").append(HexDump.shortToHex(this.grbit)).append('\n');
        if (this.unused != null) {
            buffer.append("    .unused  =").append(HexDump.shortToHex(this.unused.shortValue())).append('\n');
        }
        buffer.append("[/CATLAB]\n");
        return buffer.toString();
    }

    @Override
    public CatLabRecord copy() {
        return new CatLabRecord(this);
    }
}

