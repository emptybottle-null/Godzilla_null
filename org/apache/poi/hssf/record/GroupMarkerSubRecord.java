/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class GroupMarkerSubRecord
extends SubRecord {
    public static final short sid = 6;
    private static final int MAX_RECORD_LENGTH = 100000;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private byte[] reserved;

    public GroupMarkerSubRecord() {
        this.reserved = EMPTY_BYTE_ARRAY;
    }

    public GroupMarkerSubRecord(GroupMarkerSubRecord other) {
        super(other);
        this.reserved = (byte[])other.reserved.clone();
    }

    public GroupMarkerSubRecord(LittleEndianInput in, int size) {
        byte[] buf = IOUtils.safelyAllocate(size, 100000);
        in.readFully(buf);
        this.reserved = buf;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        String nl = System.getProperty("line.separator");
        buffer.append("[ftGmo]" + nl);
        buffer.append("  reserved = ").append(HexDump.toHex(this.reserved)).append(nl);
        buffer.append("[/ftGmo]" + nl);
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(6);
        out.writeShort(this.reserved.length);
        out.write(this.reserved);
    }

    @Override
    protected int getDataSize() {
        return this.reserved.length;
    }

    public short getSid() {
        return 6;
    }

    @Override
    @Deprecated
    @Removal(version="5.0.0")
    public GroupMarkerSubRecord clone() {
        return this.copy();
    }

    @Override
    public GroupMarkerSubRecord copy() {
        return new GroupMarkerSubRecord(this);
    }
}

