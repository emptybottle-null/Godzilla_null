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

public final class NoteStructureSubRecord
extends SubRecord {
    public static final short sid = 13;
    private static final int ENCODED_SIZE = 22;
    private final byte[] reserved;

    public NoteStructureSubRecord() {
        this.reserved = new byte[22];
    }

    public NoteStructureSubRecord(NoteStructureSubRecord other) {
        super(other);
        this.reserved = (byte[])other.reserved.clone();
    }

    public NoteStructureSubRecord(LittleEndianInput in, int size) {
        if (size != 22) {
            throw new RecordFormatException("Unexpected size (" + size + ")");
        }
        byte[] buf = IOUtils.safelyAllocate(size, 22);
        in.readFully(buf);
        this.reserved = buf;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[ftNts ]").append("\n");
        buffer.append("  size     = ").append(this.getDataSize()).append("\n");
        buffer.append("  reserved = ").append(HexDump.toHex(this.reserved)).append("\n");
        buffer.append("[/ftNts ]").append("\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(13);
        out.writeShort(this.reserved.length);
        out.write(this.reserved);
    }

    @Override
    protected int getDataSize() {
        return this.reserved.length;
    }

    public short getSid() {
        return 13;
    }

    @Override
    @Deprecated
    @Removal(version="5.0.0")
    public NoteStructureSubRecord clone() {
        return this.copy();
    }

    @Override
    public NoteStructureSubRecord copy() {
        return new NoteStructureSubRecord(this);
    }
}

