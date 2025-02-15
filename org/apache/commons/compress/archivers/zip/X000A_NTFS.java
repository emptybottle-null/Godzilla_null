/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.Date;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipEightByteInteger;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;

public class X000A_NTFS
implements ZipExtraField {
    private static final ZipShort HEADER_ID = new ZipShort(10);
    private static final ZipShort TIME_ATTR_TAG = new ZipShort(1);
    private static final ZipShort TIME_ATTR_SIZE = new ZipShort(24);
    private ZipEightByteInteger modifyTime = ZipEightByteInteger.ZERO;
    private ZipEightByteInteger accessTime = ZipEightByteInteger.ZERO;
    private ZipEightByteInteger createTime = ZipEightByteInteger.ZERO;
    private static final long EPOCH_OFFSET = -116444736000000000L;

    @Override
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(32);
    }

    @Override
    public ZipShort getCentralDirectoryLength() {
        return this.getLocalFileDataLength();
    }

    @Override
    public byte[] getLocalFileDataData() {
        byte[] data = new byte[this.getLocalFileDataLength().getValue()];
        int pos = 4;
        System.arraycopy(TIME_ATTR_TAG.getBytes(), 0, data, pos, 2);
        System.arraycopy(TIME_ATTR_SIZE.getBytes(), 0, data, pos += 2, 2);
        System.arraycopy(this.modifyTime.getBytes(), 0, data, pos += 2, 8);
        System.arraycopy(this.accessTime.getBytes(), 0, data, pos += 8, 8);
        System.arraycopy(this.createTime.getBytes(), 0, data, pos += 8, 8);
        return data;
    }

    @Override
    public byte[] getCentralDirectoryData() {
        return this.getLocalFileDataData();
    }

    @Override
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        int len = offset + length;
        offset += 4;
        while (offset + 4 <= len) {
            ZipShort tag = new ZipShort(data, offset);
            offset += 2;
            if (tag.equals(TIME_ATTR_TAG)) {
                this.readTimeAttr(data, offset, len - offset);
                break;
            }
            ZipShort size = new ZipShort(data, offset);
            offset += 2 + size.getValue();
        }
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
        this.reset();
        this.parseFromLocalFileData(buffer, offset, length);
    }

    public ZipEightByteInteger getModifyTime() {
        return this.modifyTime;
    }

    public ZipEightByteInteger getAccessTime() {
        return this.accessTime;
    }

    public ZipEightByteInteger getCreateTime() {
        return this.createTime;
    }

    public Date getModifyJavaTime() {
        return X000A_NTFS.zipToDate(this.modifyTime);
    }

    public Date getAccessJavaTime() {
        return X000A_NTFS.zipToDate(this.accessTime);
    }

    public Date getCreateJavaTime() {
        return X000A_NTFS.zipToDate(this.createTime);
    }

    public void setModifyTime(ZipEightByteInteger t) {
        this.modifyTime = t == null ? ZipEightByteInteger.ZERO : t;
    }

    public void setAccessTime(ZipEightByteInteger t) {
        this.accessTime = t == null ? ZipEightByteInteger.ZERO : t;
    }

    public void setCreateTime(ZipEightByteInteger t) {
        this.createTime = t == null ? ZipEightByteInteger.ZERO : t;
    }

    public void setModifyJavaTime(Date d) {
        this.setModifyTime(X000A_NTFS.dateToZip(d));
    }

    public void setAccessJavaTime(Date d) {
        this.setAccessTime(X000A_NTFS.dateToZip(d));
    }

    public void setCreateJavaTime(Date d) {
        this.setCreateTime(X000A_NTFS.dateToZip(d));
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("0x000A Zip Extra Field:").append(" Modify:[").append(this.getModifyJavaTime()).append("] ").append(" Access:[").append(this.getAccessJavaTime()).append("] ").append(" Create:[").append(this.getCreateJavaTime()).append("] ");
        return buf.toString();
    }

    public boolean equals(Object o) {
        if (o instanceof X000A_NTFS) {
            X000A_NTFS xf = (X000A_NTFS)o;
            return (this.modifyTime == xf.modifyTime || this.modifyTime != null && this.modifyTime.equals(xf.modifyTime)) && (this.accessTime == xf.accessTime || this.accessTime != null && this.accessTime.equals(xf.accessTime)) && (this.createTime == xf.createTime || this.createTime != null && this.createTime.equals(xf.createTime));
        }
        return false;
    }

    public int hashCode() {
        int hc = -123;
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

    private void reset() {
        this.modifyTime = ZipEightByteInteger.ZERO;
        this.accessTime = ZipEightByteInteger.ZERO;
        this.createTime = ZipEightByteInteger.ZERO;
    }

    private void readTimeAttr(byte[] data, int offset, int length) {
        ZipShort tagValueLength;
        if (length >= 26 && TIME_ATTR_SIZE.equals(tagValueLength = new ZipShort(data, offset))) {
            this.modifyTime = new ZipEightByteInteger(data, offset += 2);
            this.accessTime = new ZipEightByteInteger(data, offset += 8);
            this.createTime = new ZipEightByteInteger(data, offset += 8);
        }
    }

    private static ZipEightByteInteger dateToZip(Date d) {
        if (d == null) {
            return null;
        }
        return new ZipEightByteInteger(d.getTime() * 10000L - -116444736000000000L);
    }

    private static Date zipToDate(ZipEightByteInteger z) {
        if (z == null || ZipEightByteInteger.ZERO.equals(z)) {
            return null;
        }
        long l = (z.getLongValue() + -116444736000000000L) / 10000L;
        return new Date(l);
    }
}

