/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.Removal;

public final class FtPioGrbitSubRecord
extends SubRecord {
    public static final short sid = 8;
    public static final short length = 2;
    public static final int AUTO_PICT_BIT = 1;
    public static final int DDE_BIT = 2;
    public static final int PRINT_CALC_BIT = 4;
    public static final int ICON_BIT = 8;
    public static final int CTL_BIT = 16;
    public static final int PRSTM_BIT = 32;
    public static final int CAMERA_BIT = 128;
    public static final int DEFAULT_SIZE_BIT = 256;
    public static final int AUTO_LOAD_BIT = 512;
    private short flags;

    public FtPioGrbitSubRecord() {
    }

    public FtPioGrbitSubRecord(FtPioGrbitSubRecord other) {
        super(other);
        this.flags = other.flags;
    }

    public FtPioGrbitSubRecord(LittleEndianInput in, int size) {
        if (size != 2) {
            throw new RecordFormatException("Unexpected size (" + size + ")");
        }
        this.flags = in.readShort();
    }

    public void setFlagByBit(int bitmask, boolean enabled) {
        this.flags = enabled ? (short)(this.flags | bitmask) : (short)(this.flags & (0xFFFF ^ bitmask));
    }

    public boolean getFlagByBit(int bitmask) {
        return (this.flags & bitmask) != 0;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[FtPioGrbit ]\n");
        buffer.append("  size     = ").append(2).append("\n");
        buffer.append("  flags    = ").append(HexDump.toHex(this.flags)).append("\n");
        buffer.append("[/FtPioGrbit ]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(8);
        out.writeShort(2);
        out.writeShort(this.flags);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 8;
    }

    @Override
    @Deprecated
    @Removal(version="5.0.0")
    public FtPioGrbitSubRecord clone() {
        return this.copy();
    }

    @Override
    public FtPioGrbitSubRecord copy() {
        return new FtPioGrbitSubRecord(this);
    }

    public short getFlags() {
        return this.flags;
    }

    public void setFlags(short flags) {
        this.flags = flags;
    }
}

