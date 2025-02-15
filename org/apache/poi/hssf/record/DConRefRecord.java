/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

public class DConRefRecord
extends StandardRecord {
    private static final int MAX_RECORD_LENGTH = 100000;
    public static final short sid = 81;
    private int firstRow;
    private int lastRow;
    private int firstCol;
    private int lastCol;
    private int charCount;
    private int charType;
    private byte[] path;
    private byte[] _unused;

    public DConRefRecord(DConRefRecord other) {
        super(other);
        this.firstCol = other.firstCol;
        this.firstRow = other.firstRow;
        this.lastCol = other.lastCol;
        this.lastRow = other.lastRow;
        this.charCount = other.charCount;
        this.charType = other.charType;
        this.path = other.path == null ? null : (byte[])other.path.clone();
        this._unused = other._unused == null ? null : (byte[])other._unused.clone();
    }

    public DConRefRecord(byte[] data) {
        this(DConRefRecord.bytesToRIStream(data));
    }

    public DConRefRecord(RecordInputStream inStream) {
        if (inStream.getSid() != 81) {
            throw new RecordFormatException("Wrong sid: " + inStream.getSid());
        }
        this.firstRow = inStream.readUShort();
        this.lastRow = inStream.readUShort();
        this.firstCol = inStream.readUByte();
        this.lastCol = inStream.readUByte();
        this.charCount = inStream.readUShort();
        this.charType = inStream.readUByte() & 1;
        int byteLength = this.charCount * (this.charType + 1);
        this.path = IOUtils.safelyAllocate(byteLength, 100000);
        inStream.readFully(this.path);
        if (this.path[0] == 2) {
            this._unused = inStream.readRemainder();
        }
    }

    @Override
    protected int getDataSize() {
        int sz = 9 + this.path.length;
        if (this.path[0] == 2) {
            sz += this._unused.length;
        }
        return sz;
    }

    @Override
    protected void serialize(LittleEndianOutput out) {
        out.writeShort(this.firstRow);
        out.writeShort(this.lastRow);
        out.writeByte(this.firstCol);
        out.writeByte(this.lastCol);
        out.writeShort(this.charCount);
        out.writeByte(this.charType);
        out.write(this.path);
        if (this.path[0] == 2) {
            out.write(this._unused);
        }
    }

    @Override
    public short getSid() {
        return 81;
    }

    public int getFirstColumn() {
        return this.firstCol;
    }

    public int getFirstRow() {
        return this.firstRow;
    }

    public int getLastColumn() {
        return this.lastCol;
    }

    public int getLastRow() {
        return this.lastRow;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("[DCONREF]\n");
        b.append("    .ref\n");
        b.append("        .firstrow   = ").append(this.firstRow).append("\n");
        b.append("        .lastrow    = ").append(this.lastRow).append("\n");
        b.append("        .firstcol   = ").append(this.firstCol).append("\n");
        b.append("        .lastcol    = ").append(this.lastCol).append("\n");
        b.append("    .cch            = ").append(this.charCount).append("\n");
        b.append("    .stFile\n");
        b.append("        .h          = ").append(this.charType).append("\n");
        b.append("        .rgb        = ").append(this.getReadablePath()).append("\n");
        b.append("[/DCONREF]\n");
        return b.toString();
    }

    public byte[] getPath() {
        return Arrays.copyOf(this.path, this.path.length);
    }

    public String getReadablePath() {
        if (this.path != null) {
            int offset;
            for (offset = 1; offset < this.path.length && this.path[offset] < 32; ++offset) {
            }
            String out = new String(Arrays.copyOfRange(this.path, offset, this.path.length), StringUtil.UTF8);
            out = out.replaceAll("\u0003", "/");
            return out;
        }
        return null;
    }

    public boolean isExternalRef() {
        return this.path[0] == 1;
    }

    @Override
    public DConRefRecord copy() {
        return new DConRefRecord(this);
    }

    private static RecordInputStream bytesToRIStream(byte[] data) {
        RecordInputStream ric = new RecordInputStream(new ByteArrayInputStream(data));
        ric.nextRecord();
        return ric;
    }
}

