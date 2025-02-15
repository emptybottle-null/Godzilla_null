/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.cpio;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.cpio.CpioConstants;
import org.apache.commons.compress.archivers.cpio.CpioUtil;

public class CpioArchiveEntry
implements CpioConstants,
ArchiveEntry {
    private final short fileFormat;
    private final int headerSize;
    private final int alignmentBoundary;
    private long chksum = 0L;
    private long filesize = 0L;
    private long gid = 0L;
    private long inode = 0L;
    private long maj = 0L;
    private long min = 0L;
    private long mode = 0L;
    private long mtime = 0L;
    private String name;
    private long nlink = 0L;
    private long rmaj = 0L;
    private long rmin = 0L;
    private long uid = 0L;

    public CpioArchiveEntry(short format) {
        switch (format) {
            case 1: {
                this.headerSize = 110;
                this.alignmentBoundary = 4;
                break;
            }
            case 2: {
                this.headerSize = 110;
                this.alignmentBoundary = 4;
                break;
            }
            case 4: {
                this.headerSize = 76;
                this.alignmentBoundary = 0;
                break;
            }
            case 8: {
                this.headerSize = 26;
                this.alignmentBoundary = 2;
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown header type");
            }
        }
        this.fileFormat = format;
    }

    public CpioArchiveEntry(String name) {
        this(1, name);
    }

    public CpioArchiveEntry(short format, String name) {
        this(format);
        this.name = name;
    }

    public CpioArchiveEntry(String name, long size) {
        this(name);
        this.setSize(size);
    }

    public CpioArchiveEntry(short format, String name, long size) {
        this(format, name);
        this.setSize(size);
    }

    public CpioArchiveEntry(File inputFile, String entryName) {
        this(1, inputFile, entryName);
    }

    public CpioArchiveEntry(short format, File inputFile, String entryName) {
        this(format, entryName, inputFile.isFile() ? inputFile.length() : 0L);
        if (inputFile.isDirectory()) {
            this.setMode(16384L);
        } else if (inputFile.isFile()) {
            this.setMode(32768L);
        } else {
            throw new IllegalArgumentException("Cannot determine type of file " + inputFile.getName());
        }
        this.setTime(inputFile.lastModified() / 1000L);
    }

    private void checkNewFormat() {
        if ((this.fileFormat & 3) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    private void checkOldFormat() {
        if ((this.fileFormat & 0xC) == 0) {
            throw new UnsupportedOperationException();
        }
    }

    public long getChksum() {
        this.checkNewFormat();
        return this.chksum & 0xFFFFFFFFL;
    }

    public long getDevice() {
        this.checkOldFormat();
        return this.min;
    }

    public long getDeviceMaj() {
        this.checkNewFormat();
        return this.maj;
    }

    public long getDeviceMin() {
        this.checkNewFormat();
        return this.min;
    }

    @Override
    public long getSize() {
        return this.filesize;
    }

    public short getFormat() {
        return this.fileFormat;
    }

    public long getGID() {
        return this.gid;
    }

    public int getHeaderSize() {
        return this.headerSize;
    }

    public int getAlignmentBoundary() {
        return this.alignmentBoundary;
    }

    @Deprecated
    public int getHeaderPadCount() {
        return this.getHeaderPadCount(null);
    }

    public int getHeaderPadCount(Charset charset) {
        if (this.name == null) {
            return 0;
        }
        if (charset == null) {
            return this.getHeaderPadCount(this.name.length());
        }
        return this.getHeaderPadCount(this.name.getBytes(charset).length);
    }

    public int getHeaderPadCount(long namesize) {
        int remain;
        if (this.alignmentBoundary == 0) {
            return 0;
        }
        int size = this.headerSize + 1;
        if (this.name != null) {
            size = (int)((long)size + namesize);
        }
        if ((remain = size % this.alignmentBoundary) > 0) {
            return this.alignmentBoundary - remain;
        }
        return 0;
    }

    public int getDataPadCount() {
        if (this.alignmentBoundary == 0) {
            return 0;
        }
        long size = this.filesize;
        int remain = (int)(size % (long)this.alignmentBoundary);
        if (remain > 0) {
            return this.alignmentBoundary - remain;
        }
        return 0;
    }

    public long getInode() {
        return this.inode;
    }

    public long getMode() {
        return this.mode == 0L && !"TRAILER!!!".equals(this.name) ? 32768L : this.mode;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public long getNumberOfLinks() {
        return this.nlink == 0L ? (this.isDirectory() ? 2L : 1L) : this.nlink;
    }

    public long getRemoteDevice() {
        this.checkOldFormat();
        return this.rmin;
    }

    public long getRemoteDeviceMaj() {
        this.checkNewFormat();
        return this.rmaj;
    }

    public long getRemoteDeviceMin() {
        this.checkNewFormat();
        return this.rmin;
    }

    public long getTime() {
        return this.mtime;
    }

    @Override
    public Date getLastModifiedDate() {
        return new Date(1000L * this.getTime());
    }

    public long getUID() {
        return this.uid;
    }

    public boolean isBlockDevice() {
        return CpioUtil.fileType(this.mode) == 24576L;
    }

    public boolean isCharacterDevice() {
        return CpioUtil.fileType(this.mode) == 8192L;
    }

    @Override
    public boolean isDirectory() {
        return CpioUtil.fileType(this.mode) == 16384L;
    }

    public boolean isNetwork() {
        return CpioUtil.fileType(this.mode) == 36864L;
    }

    public boolean isPipe() {
        return CpioUtil.fileType(this.mode) == 4096L;
    }

    public boolean isRegularFile() {
        return CpioUtil.fileType(this.mode) == 32768L;
    }

    public boolean isSocket() {
        return CpioUtil.fileType(this.mode) == 49152L;
    }

    public boolean isSymbolicLink() {
        return CpioUtil.fileType(this.mode) == 40960L;
    }

    public void setChksum(long chksum) {
        this.checkNewFormat();
        this.chksum = chksum & 0xFFFFFFFFL;
    }

    public void setDevice(long device) {
        this.checkOldFormat();
        this.min = device;
    }

    public void setDeviceMaj(long maj) {
        this.checkNewFormat();
        this.maj = maj;
    }

    public void setDeviceMin(long min) {
        this.checkNewFormat();
        this.min = min;
    }

    public void setSize(long size) {
        if (size < 0L || size > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("Invalid entry size <" + size + ">");
        }
        this.filesize = size;
    }

    public void setGID(long gid) {
        this.gid = gid;
    }

    public void setInode(long inode) {
        this.inode = inode;
    }

    public void setMode(long mode) {
        long maskedMode = mode & 0xF000L;
        switch ((int)maskedMode) {
            case 4096: 
            case 8192: 
            case 16384: 
            case 24576: 
            case 32768: 
            case 36864: 
            case 40960: 
            case 49152: {
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown mode. Full: " + Long.toHexString(mode) + " Masked: " + Long.toHexString(maskedMode));
            }
        }
        this.mode = mode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfLinks(long nlink) {
        this.nlink = nlink;
    }

    public void setRemoteDevice(long device) {
        this.checkOldFormat();
        this.rmin = device;
    }

    public void setRemoteDeviceMaj(long rmaj) {
        this.checkNewFormat();
        this.rmaj = rmaj;
    }

    public void setRemoteDeviceMin(long rmin) {
        this.checkNewFormat();
        this.rmin = rmin;
    }

    public void setTime(long time) {
        this.mtime = time;
    }

    public void setUID(long uid) {
        this.uid = uid;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        CpioArchiveEntry other = (CpioArchiveEntry)obj;
        if (this.name == null) {
            return other.name == null;
        }
        return this.name.equals(other.name);
    }
}

