/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipLong;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.apache.commons.compress.archivers.zip.ZipUtil;

public class X5455_ExtendedTimestamp
implements ZipExtraField,
Cloneable,
Serializable {
    private static final ZipShort HEADER_ID = new ZipShort(21589);
    private static final long serialVersionUID = 1L;
    public static final byte MODIFY_TIME_BIT = 1;
    public static final byte ACCESS_TIME_BIT = 2;
    public static final byte CREATE_TIME_BIT = 4;
    private byte flags;
    private boolean bit0_modifyTimePresent;
    private boolean bit1_accessTimePresent;
    private boolean bit2_createTimePresent;
    private ZipLong modifyTime;
    private ZipLong accessTime;
    private ZipLong createTime;

    @Override
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(1 + (this.bit0_modifyTimePresent ? 4 : 0) + (this.bit1_accessTimePresent && this.accessTime != null ? 4 : 0) + (this.bit2_createTimePresent && this.createTime != null ? 4 : 0));
    }

    @Override
    public ZipShort getCentralDirectoryLength() {
        return new ZipShort(1 + (this.bit0_modifyTimePresent ? 4 : 0));
    }

    @Override
    public byte[] getLocalFileDataData() {
        byte[] data = new byte[this.getLocalFileDataLength().getValue()];
        int pos = 0;
        data[pos++] = 0;
        if (this.bit0_modifyTimePresent) {
            data[0] = (byte)(data[0] | 1);
            System.arraycopy(this.modifyTime.getBytes(), 0, data, pos, 4);
            pos += 4;
        }
        if (this.bit1_accessTimePresent && this.accessTime != null) {
            data[0] = (byte)(data[0] | 2);
            System.arraycopy(this.accessTime.getBytes(), 0, data, pos, 4);
            pos += 4;
        }
        if (this.bit2_createTimePresent && this.createTime != null) {
            data[0] = (byte)(data[0] | 4);
            System.arraycopy(this.createTime.getBytes(), 0, data, pos, 4);
            pos += 4;
        }
        return data;
    }

    @Override
    public byte[] getCentralDirectoryData() {
        return Arrays.copyOf(this.getLocalFileDataData(), this.getCentralDirectoryLength().getValue());
    }

    @Override
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        this.reset();
        if (length < 1) {
            throw new ZipException("X5455_ExtendedTimestamp too short, only " + length + " bytes");
        }
        int len = offset + length;
        this.setFlags(data[offset++]);
        if (this.bit0_modifyTimePresent && offset + 4 <= len) {
            this.modifyTime = new ZipLong(data, offset);
            offset += 4;
        }
        if (this.bit1_accessTimePresent && offset + 4 <= len) {
            this.accessTime = new ZipLong(data, offset);
            offset += 4;
        }
        if (this.bit2_createTimePresent && offset + 4 <= len) {
            this.createTime = new ZipLong(data, offset);
            offset += 4;
        }
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
        this.reset();
        this.parseFromLocalFileData(buffer, offset, length);
    }

    private void reset() {
        this.setFlags((byte)0);
        this.modifyTime = null;
        this.accessTime = null;
        this.createTime = null;
    }

    public void setFlags(byte flags) {
        this.flags = flags;
        this.bit0_modifyTimePresent = (flags & 1) == 1;
        this.bit1_accessTimePresent = (flags & 2) == 2;
        this.bit2_createTimePresent = (flags & 4) == 4;
    }

    public byte getFlags() {
        return this.flags;
    }

    public boolean isBit0_modifyTimePresent() {
        return this.bit0_modifyTimePresent;
    }

    public boolean isBit1_accessTimePresent() {
        return this.bit1_accessTimePresent;
    }

    public boolean isBit2_createTimePresent() {
        return this.bit2_createTimePresent;
    }

    public ZipLong getModifyTime() {
        return this.modifyTime;
    }

    public ZipLong getAccessTime() {
        return this.accessTime;
    }

    public ZipLong getCreateTime() {
        return this.createTime;
    }

    public Date getModifyJavaTime() {
        return X5455_ExtendedTimestamp.zipLongToDate(this.modifyTime);
    }

    public Date getAccessJavaTime() {
        return X5455_ExtendedTimestamp.zipLongToDate(this.accessTime);
    }

    public Date getCreateJavaTime() {
        return X5455_ExtendedTimestamp.zipLongToDate(this.createTime);
    }

    public void setModifyTime(ZipLong l) {
        this.bit0_modifyTimePresent = l != null;
        this.flags = (byte)(l != null ? this.flags | 1 : this.flags & 0xFFFFFFFE);
        this.modifyTime = l;
    }

    public void setAccessTime(ZipLong l) {
        this.bit1_accessTimePresent = l != null;
        this.flags = (byte)(l != null ? this.flags | 2 : this.flags & 0xFFFFFFFD);
        this.accessTime = l;
    }

    public void setCreateTime(ZipLong l) {
        this.bit2_createTimePresent = l != null;
        this.flags = (byte)(l != null ? this.flags | 4 : this.flags & 0xFFFFFFFB);
        this.createTime = l;
    }

    public void setModifyJavaTime(Date d) {
        this.setModifyTime(X5455_ExtendedTimestamp.dateToZipLong(d));
    }

    public void setAccessJavaTime(Date d) {
        this.setAccessTime(X5455_ExtendedTimestamp.dateToZipLong(d));
    }

    public void setCreateJavaTime(Date d) {
        this.setCreateTime(X5455_ExtendedTimestamp.dateToZipLong(d));
    }

    private static ZipLong dateToZipLong(Date d) {
        if (d == null) {
            return null;
        }
        return X5455_ExtendedTimestamp.unixTimeToZipLong(d.getTime() / 1000L);
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("0x5455 Zip Extra Field: Flags=");
        buf.append(Integer.toBinaryString(ZipUtil.unsignedIntToSignedByte(this.flags))).append(" ");
        if (this.bit0_modifyTimePresent && this.modifyTime != null) {
            Date m = this.getModifyJavaTime();
            buf.append(" Modify:[").append(m).append("] ");
        }
        if (this.bit1_accessTimePresent && this.accessTime != null) {
            Date a = this.getAccessJavaTime();
            buf.append(" Access:[").append(a).append("] ");
        }
        if (this.bit2_createTimePresent && this.createTime != null) {
            Date c = this.getCreateJavaTime();
            buf.append(" Create:[").append(c).append("] ");
        }
        return buf.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object o) {
        if (o instanceof X5455_ExtendedTimestamp) {
            X5455_ExtendedTimestamp xf = (X5455_ExtendedTimestamp)o;
            return (this.flags & 7) == (xf.flags & 7) && (this.modifyTime == xf.modifyTime || this.modifyTime != null && this.modifyTime.equals(xf.modifyTime)) && (this.accessTime == xf.accessTime || this.accessTime != null && this.accessTime.equals(xf.accessTime)) && (this.createTime == xf.createTime || this.createTime != null && this.createTime.equals(xf.createTime));
        }
        return false;
    }

    public int hashCode() {
        int hc = -123 * (this.flags & 7);
        if (this.modifyTime != null) {
            hc ^= this.modifyTime.hashCode();
        }
        if (this.accessTime != null) {
            hc ^= Integer.rotateLeft(this.accessTime.hashCode(), 11);
        }
        if (this.createTime != null) {
            hc ^= Integer.rotateLeft(this.createTime.hashCode(), 22);
        }
        return hc;
    }

    private static Date zipLongToDate(ZipLong unixTime) {
        return unixTime != null ? new Date((long)unixTime.getIntValue() * 1000L) : null;
    }

    private static ZipLong unixTimeToZipLong(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("X5455 timestamps must fit in a signed 32 bit integer: " + l);
        }
        return new ZipLong(l);
    }
}

