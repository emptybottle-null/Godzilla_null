/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.pivottable;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class DataItemRecord
extends StandardRecord {
    public static final short sid = 197;
    private int isxvdData;
    private int iiftab;
    private int df;
    private int isxvd;
    private int isxvi;
    private int ifmt;
    private String name;

    public DataItemRecord(DataItemRecord other) {
        super(other);
        this.isxvdData = other.isxvdData;
        this.iiftab = other.iiftab;
        this.df = other.df;
        this.isxvd = other.isxvd;
        this.isxvi = other.isxvi;
        this.ifmt = other.ifmt;
        this.name = other.name;
    }

    public DataItemRecord(RecordInputStream in) {
        this.isxvdData = in.readUShort();
        this.iiftab = in.readUShort();
        this.df = in.readUShort();
        this.isxvd = in.readUShort();
        this.isxvi = in.readUShort();
        this.ifmt = in.readUShort();
        this.name = in.readString();
    }

    @Override
    protected void serialize(LittleEndianOutput out) {
        out.writeShort(this.isxvdData);
        out.writeShort(this.iiftab);
        out.writeShort(this.df);
        out.writeShort(this.isxvd);
        out.writeShort(this.isxvi);
        out.writeShort(this.ifmt);
        StringUtil.writeUnicodeString(out, this.name);
    }

    @Override
    protected int getDataSize() {
        return 12 + StringUtil.getEncodedSize(this.name);
    }

    @Override
    public short getSid() {
        return 197;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[SXDI]\n");
        buffer.append("  .isxvdData = ").append(HexDump.shortToHex(this.isxvdData)).append("\n");
        buffer.append("  .iiftab = ").append(HexDump.shortToHex(this.iiftab)).append("\n");
        buffer.append("  .df = ").append(HexDump.shortToHex(this.df)).append("\n");
        buffer.append("  .isxvd = ").append(HexDump.shortToHex(this.isxvd)).append("\n");
        buffer.append("  .isxvi = ").append(HexDump.shortToHex(this.isxvi)).append("\n");
        buffer.append("  .ifmt = ").append(HexDump.shortToHex(this.ifmt)).append("\n");
        buffer.append("[/SXDI]\n");
        return buffer.toString();
    }

    @Override
    public DataItemRecord copy() {
        return new DataItemRecord(this);
    }
}

