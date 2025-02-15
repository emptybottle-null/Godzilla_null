/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.pivottable;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;

public final class StreamIDRecord
extends StandardRecord {
    public static final short sid = 213;
    private int idstm;

    public StreamIDRecord(StreamIDRecord other) {
        super(other);
        this.idstm = other.idstm;
    }

    public StreamIDRecord(RecordInputStream in) {
        this.idstm = in.readShort();
    }

    @Override
    protected void serialize(LittleEndianOutput out) {
        out.writeShort(this.idstm);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 213;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[SXIDSTM]\n");
        buffer.append("    .idstm      =").append(HexDump.shortToHex(this.idstm)).append('\n');
        buffer.append("[/SXIDSTM]\n");
        return buffer.toString();
    }

    @Override
    public StreamIDRecord copy() {
        return new StreamIDRecord(this);
    }
}

