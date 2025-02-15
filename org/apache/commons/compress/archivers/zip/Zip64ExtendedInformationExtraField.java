/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipEightByteInteger;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipLong;
import org.apache.commons.compress.archivers.zip.ZipShort;

public class Zip64ExtendedInformationExtraField
implements ZipExtraField {
    static final ZipShort HEADER_ID = new ZipShort(1);
    private static final String LFH_MUST_HAVE_BOTH_SIZES_MSG = "Zip64 extended information must contain both size values in the local file header.";
    private static final byte[] EMPTY = new byte[0];
    private ZipEightByteInteger size;
    private ZipEightByteInteger compressedSize;
    private ZipEightByteInteger relativeHeaderOffset;
    private ZipLong diskStart;
    private byte[] rawCentralDirectoryData;

    public Zip64ExtendedInformationExtraField() {
    }

    public Zip64ExtendedInformationExtraField(ZipEightByteInteger size, ZipEightByteInteger compressedSize) {
        this(size, compressedSize, null, null);
    }

    public Zip64ExtendedInformationExtraField(ZipEightByteInteger size, ZipEightByteInteger compressedSize, ZipEightByteInteger relativeHeaderOffset, ZipLong diskStart) {
        this.size = size;
        this.compressedSize = compressedSize;
        this.relativeHeaderOffset = relativeHeaderOffset;
        this.diskStart = diskStart;
    }

    @Override
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.size != null ? 16 : 0);
    }

    @Override
    public ZipShort getCentralDirectoryLength() {
        return new ZipShort((this.size != null ? 8 : 0) + (this.compressedSize != null ? 8 : 0) + (this.relativeHeaderOffset != null ? 8 : 0) + (this.diskStart != null ? 4 : 0));
    }

    @Override
    public byte[] getLocalFileDataData() {
        if (this.size != null || this.compressedSize != null) {
            if (this.size == null || this.compressedSize == null) {
                throw new IllegalArgumentException(LFH_MUST_HAVE_BOTH_SIZES_MSG);
            }
            byte[] data = new byte[16];
            this.addSizes(data);
            return data;
        }
        return EMPTY;
    }

    @Override
    public byte[] getCentralDirectoryData() {
        byte[] data = new byte[this.getCentralDirectoryLength().getValue()];
        int off = this.addSizes(data);
        if (this.relativeHeaderOffset != null) {
            System.arraycopy(this.relativeHeaderOffset.getBytes(), 0, data, off, 8);
            off += 8;
        }
        if (this.diskStart != null) {
            System.arraycopy(this.diskStart.getBytes(), 0, data, off, 4);
            off += 4;
        }
        return data;
    }

    @Override
    public void parseFromLocalFileData(byte[] buffer, int offset, int length) throws ZipException {
        if (length == 0) {
            return;
        }
        if (length < 16) {
            throw new ZipException(LFH_MUST_HAVE_BOTH_SIZES_MSG);
        }
        this.size = new ZipEightByteInteger(buffer, offset);
        this.compressedSize = new ZipEightByteInteger(buffer, offset += 8);
        offset += 8;
        int remaining = length - 16;
        if (remaining >= 8) {
            this.relativeHeaderOffset = new ZipEightByteInteger(buffer, offset);
            offset += 8;
            remaining -= 8;
        }
        if (remaining >= 4) {
            this.diskStart = new ZipLong(buffer, offset);
            offset += 4;
            remaining -= 4;
        }
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
        this.rawCentralDirectoryData = new byte[length];
        System.arraycopy(buffer, offset, this.rawCentralDirectoryData, 0, length);
        if (length >= 28) {
            this.parseFromLocalFileData(buffer, offset, length);
        } else if (length == 24) {
            this.size = new ZipEightByteInteger(buffer, offset);
            this.compressedSize = new ZipEightByteInteger(buffer, offset += 8);
            this.relativeHeaderOffset = new ZipEightByteInteger(buffer, offset += 8);
        } else if (length % 8 == 4) {
            this.diskStart = new ZipLong(buffer, offset + length - 4);
        }
    }

    public void reparseCentralDirectoryData(boolean hasUncompressedSize, boolean hasCompressedSize, boolean hasRelativeHeaderOffset, boolean hasDiskStart) throws ZipException {
        if (this.rawCentralDirectoryData != null) {
            int expectedLength = (hasUncompressedSize ? 8 : 0) + (hasCompressedSize ? 8 : 0) + (hasRelativeHeaderOffset ? 8 : 0) + (hasDiskStart ? 4 : 0);
            if (this.rawCentralDirectoryData.length < expectedLength) {
                throw new ZipException("Central directory zip64 extended information extra field's length doesn't match central directory data.  Expected length " + expectedLength + " but is " + this.rawCentralDirectoryData.length);
            }
            int offset = 0;
            if (hasUncompressedSize) {
                this.size = new ZipEightByteInteger(this.rawCentralDirectoryData, offset);
                offset += 8;
            }
            if (hasCompressedSize) {
                this.compressedSize = new ZipEightByteInteger(this.rawCentralDirectoryData, offset);
                offset += 8;
            }
            if (hasRelativeHeaderOffset) {
                this.relativeHeaderOffset = new ZipEightByteInteger(this.rawCentralDirectoryData, offset);
                offset += 8;
            }
            if (hasDiskStart) {
                this.diskStart = new ZipLong(this.rawCentralDirectoryData, offset);
                offset += 4;
            }
        }
    }

    public ZipEightByteInteger getSize() {
        return this.size;
    }

    public void setSize(ZipEightByteInteger size) {
        this.size = size;
    }

    public ZipEightByteInteger getCompressedSize() {
        return this.compressedSize;
    }

    public void setCompressedSize(ZipEightByteInteger compressedSize) {
        this.compressedSize = compressedSize;
    }

    public ZipEightByteInteger getRelativeHeaderOffset() {
        return this.relativeHeaderOffset;
    }

    public void setRelativeHeaderOffset(ZipEightByteInteger rho) {
        this.relativeHeaderOffset = rho;
    }

    public ZipLong getDiskStartNumber() {
        return this.diskStart;
    }

    public void setDiskStartNumber(ZipLong ds) {
        this.diskStart = ds;
    }

    private int addSizes(byte[] data) {
        int off = 0;
        if (this.size != null) {
            System.arraycopy(this.size.getBytes(), 0, data, 0, 8);
            off += 8;
        }
        if (this.compressedSize != null) {
            System.arraycopy(this.compressedSize.getBytes(), 0, data, off, 8);
            off += 8;
        }
        return off;
    }
}

