/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.Removal;

public final class FtCblsSubRecord
extends SubRecord {
    public static final short sid = 12;
    private static final int ENCODED_SIZE = 20;
    private final byte[] reserved;

    public FtCblsSubRecord() {
        this.reserved = new byte[20];
    }

    public FtCblsSubRecord(FtCblsSubRecord other) {
        super(other);
        this.reserved = (byte[])other.reserved.clone();
    }

    public FtCblsSubRecord(LittleEndianInput in, int size) {
        if (size != 20) {
            throw new RecordFormatException("Unexpected size (" + size + ")");
        }
        byte[] buf = IOUtils.safelyAllocate(size, 20);
        in.readFully(buf);
        this.reserved = buf;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[FtCbls ]").append("\n");
        buffer.append("  size     = ").append(this.getDataSize()).append("\n");
        buffer.append("  reserved = ").append(HexDump.toHex(this.reserved)).append("\n");
        buffer.append("[/FtCbls ]").append("\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(12);
        out.writeShort(this.reserved.length);
        out.write(this.reserved);
    }

    @Override
    protected int getDataSize() {
        return this.reserved.length;
    }

    public short getSid() {
        return 12;
    }

    @Override
    @Deprecated
    @Removal(version="5.0.0")
    public FtCblsSubRecord clone() {
        return this.copy();
    }

    @Override
    public FtCblsSubRecord copy() {
        return new FtCblsSubRecord(this);
    }
}

