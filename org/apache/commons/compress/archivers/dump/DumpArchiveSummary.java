/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.dump;

import java.io.IOException;
import java.util.Date;
import org.apache.commons.compress.archivers.dump.DumpArchiveUtil;
import org.apache.commons.compress.archivers.zip.ZipEncoding;

public class DumpArchiveSummary {
    private long dumpDate;
    private long previousDumpDate;
    private int volume;
    private String label;
    private int level;
    private String filesys;
    private String devname;
    private String hostname;
    private int flags;
    private int firstrec;
    private int ntrec;

    DumpArchiveSummary(byte[] buffer, ZipEncoding encoding) throws IOException {
        this.dumpDate = 1000L * (long)DumpArchiveUtil.convert32(buffer, 4);
        this.previousDumpDate = 1000L * (long)DumpArchiveUtil.convert32(buffer, 8);
        this.volume = DumpArchiveUtil.convert32(buffer, 12);
        this.label = DumpArchiveUtil.decode(encoding, buffer, 676, 16).trim();
        this.level = DumpArchiveUtil.convert32(buffer, 692);
        this.filesys = DumpArchiveUtil.decode(encoding, buffer, 696, 64).trim();
        this.devname = DumpArchiveUtil.decode(encoding, buffer, 760, 64).trim();
        this.hostname = DumpArchiveUtil.decode(encoding, buffer, 824, 64).trim();
        this.flags = DumpArchiveUtil.convert32(buffer, 888);
        this.firstrec = DumpArchiveUtil.convert32(buffer, 892);
        this.ntrec = DumpArchiveUtil.convert32(buffer, 896);
    }

    public Date getDumpDate() {
        return new Date(this.dumpDate);
    }

    public void setDumpDate(Date dumpDate) {
        this.dumpDate = dumpDate.getTime();
    }

    public Date getPreviousDumpDate() {
        return new Date(this.previousDumpDate);
    }

    public void setPreviousDumpDate(Date previousDumpDate) {
        this.previousDumpDate = previousDumpDate.getTime();
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFilesystem() {
        return this.filesys;
    }

    public void setFilesystem(String fileSystem) {
        this.filesys = fileSystem;
    }

    public String getDevname() {
        return this.devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getFirstRecord() {
        return this.firstrec;
    }

    public void setFirstRecord(int firstrec) {
        this.firstrec = firstrec;
    }

    public int getNTRec() {
        return this.ntrec;
    }

    public void setNTRec(int ntrec) {
        this.ntrec = ntrec;
    }

    public boolean isNewHeader() {
        return (this.flags & 1) == 1;
    }

    public boolean isNewInode() {
        return (this.flags & 2) == 2;
    }

    public boolean isCompressed() {
        return (this.flags & 0x80) == 128;
    }

    public boolean isMetaDataOnly() {
        return (this.flags & 0x100) == 256;
    }

    public boolean isExtendedAttributes() {
        return (this.flags & 0x8000) == 32768;
    }

    public int hashCode() {
        int hash = 17;
        if (this.label != null) {
            hash = this.label.hashCode();
        }
        hash = (int)((long)hash + 31L * this.dumpDate);
        if (this.hostname != null) {
            hash = 31 * this.hostname.hashCode() + 17;
        }
        if (this.devname != null) {
            hash = 31 * this.devname.hashCode() + 17;
        }
        return hash;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !o.getClass().equals(this.getClass())) {
            return false;
        }
        DumpArchiveSummary rhs = (DumpArchiveSummary)o;
        if (this.dumpDate != rhs.dumpDate) {
            return false;
        }
        if (this.getHostname() == null || !this.getHostname().equals(rhs.getHostname())) {
            return false;
        }
        return this.getDevname() != null && this.getDevname().equals(rhs.getDevname());
    }
}

