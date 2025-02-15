/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.dump;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.dump.Dirent;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.apache.commons.compress.archivers.dump.DumpArchiveEntry;
import org.apache.commons.compress.archivers.dump.DumpArchiveSummary;
import org.apache.commons.compress.archivers.dump.DumpArchiveUtil;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.compress.archivers.dump.TapeInputStream;
import org.apache.commons.compress.archivers.dump.UnrecognizedFormatException;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;

public class DumpArchiveInputStream
extends ArchiveInputStream {
    private DumpArchiveSummary summary;
    private DumpArchiveEntry active;
    private boolean isClosed;
    private boolean hasHitEOF;
    private long entrySize;
    private long entryOffset;
    private int readIdx;
    private final byte[] readBuf = new byte[1024];
    private byte[] blockBuffer;
    private int recordOffset;
    private long filepos;
    protected TapeInputStream raw;
    private final Map<Integer, Dirent> names = new HashMap<Integer, Dirent>();
    private final Map<Integer, DumpArchiveEntry> pending = new HashMap<Integer, DumpArchiveEntry>();
    private Queue<DumpArchiveEntry> queue;
    private final ZipEncoding zipEncoding;
    final String encoding;

    public DumpArchiveInputStream(InputStream is) throws ArchiveException {
        this(is, null);
    }

    public DumpArchiveInputStream(InputStream is, String encoding) throws ArchiveException {
        this.raw = new TapeInputStream(is);
        this.hasHitEOF = false;
        this.encoding = encoding;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        try {
            byte[] headerBytes = this.raw.readRecord();
            if (!DumpArchiveUtil.verify(headerBytes)) {
                throw new UnrecognizedFormatException();
            }
            this.summary = new DumpArchiveSummary(headerBytes, this.zipEncoding);
            this.raw.resetBlockSize(this.summary.getNTRec(), this.summary.isCompressed());
            this.blockBuffer = new byte[4096];
            this.readCLRI();
            this.readBITS();
        } catch (IOException ex) {
            throw new ArchiveException(ex.getMessage(), ex);
        }
        Dirent root = new Dirent(2, 2, 4, ".");
        this.names.put(2, root);
        this.queue = new PriorityQueue<DumpArchiveEntry>(10, new Comparator<DumpArchiveEntry>(){

            @Override
            public int compare(DumpArchiveEntry p, DumpArchiveEntry q) {
                if (p.getOriginalName() == null || q.getOriginalName() == null) {
                    return Integer.MAX_VALUE;
                }
                return p.getOriginalName().compareTo(q.getOriginalName());
            }
        });
    }

    @Override
    @Deprecated
    public int getCount() {
        return (int)this.getBytesRead();
    }

    @Override
    public long getBytesRead() {
        return this.raw.getBytesRead();
    }

    public DumpArchiveSummary getSummary() {
        return this.summary;
    }

    private void readCLRI() throws IOException {
        byte[] buffer = this.raw.readRecord();
        if (!DumpArchiveUtil.verify(buffer)) {
            throw new InvalidFormatException();
        }
        this.active = DumpArchiveEntry.parse(buffer);
        if (DumpArchiveConstants.SEGMENT_TYPE.CLRI != this.active.getHeaderType()) {
            throw new InvalidFormatException();
        }
        if (this.raw.skip(1024L * (long)this.active.getHeaderCount()) == -1L) {
            throw new EOFException();
        }
        this.readIdx = this.active.getHeaderCount();
    }

    private void readBITS() throws IOException {
        byte[] buffer = this.raw.readRecord();
        if (!DumpArchiveUtil.verify(buffer)) {
            throw new InvalidFormatException();
        }
        this.active = DumpArchiveEntry.parse(buffer);
        if (DumpArchiveConstants.SEGMENT_TYPE.BITS != this.active.getHeaderType()) {
            throw new InvalidFormatException();
        }
        if (this.raw.skip(1024L * (long)this.active.getHeaderCount()) == -1L) {
            throw new EOFException();
        }
        this.readIdx = this.active.getHeaderCount();
    }

    public DumpArchiveEntry getNextDumpEntry() throws IOException {
        return this.getNextEntry();
    }

    @Override
    public DumpArchiveEntry getNextEntry() throws IOException {
        DumpArchiveEntry entry = null;
        String path = null;
        if (!this.queue.isEmpty()) {
            return this.queue.remove();
        }
        while (entry == null) {
            if (this.hasHitEOF) {
                return null;
            }
            while (this.readIdx < this.active.getHeaderCount()) {
                if (this.active.isSparseRecord(this.readIdx++) || this.raw.skip(1024L) != -1L) continue;
                throw new EOFException();
            }
            this.readIdx = 0;
            this.filepos = this.raw.getBytesRead();
            byte[] headerBytes = this.raw.readRecord();
            if (!DumpArchiveUtil.verify(headerBytes)) {
                throw new InvalidFormatException();
            }
            this.active = DumpArchiveEntry.parse(headerBytes);
            while (DumpArchiveConstants.SEGMENT_TYPE.ADDR == this.active.getHeaderType()) {
                if (this.raw.skip(1024L * (long)(this.active.getHeaderCount() - this.active.getHeaderHoles())) == -1L) {
                    throw new EOFException();
                }
                this.filepos = this.raw.getBytesRead();
                headerBytes = this.raw.readRecord();
                if (!DumpArchiveUtil.verify(headerBytes)) {
                    throw new InvalidFormatException();
                }
                this.active = DumpArchiveEntry.parse(headerBytes);
            }
            if (DumpArchiveConstants.SEGMENT_TYPE.END == this.active.getHeaderType()) {
                this.hasHitEOF = true;
                return null;
            }
            entry = this.active;
            if (entry.isDirectory()) {
                this.readDirectoryEntry(this.active);
                this.entryOffset = 0L;
                this.entrySize = 0L;
                this.readIdx = this.active.getHeaderCount();
            } else {
                this.entryOffset = 0L;
                this.entrySize = this.active.getEntrySize();
                this.readIdx = 0;
            }
            this.recordOffset = this.readBuf.length;
            path = this.getPath(entry);
            if (path != null) continue;
            entry = null;
        }
        entry.setName(path);
        entry.setSimpleName(this.names.get(entry.getIno()).getName());
        entry.setOffset(this.filepos);
        return entry;
    }

    private void readDirectoryEntry(DumpArchiveEntry entry) throws IOException {
        long size = entry.getEntrySize();
        boolean first = true;
        while (first || DumpArchiveConstants.SEGMENT_TYPE.ADDR == entry.getHeaderType()) {
            int datalen;
            if (!first) {
                this.raw.readRecord();
            }
            if (!this.names.containsKey(entry.getIno()) && DumpArchiveConstants.SEGMENT_TYPE.INODE == entry.getHeaderType()) {
                this.pending.put(entry.getIno(), entry);
            }
            if (this.blockBuffer.length < (datalen = 1024 * entry.getHeaderCount())) {
                this.blockBuffer = new byte[datalen];
            }
            if (this.raw.read(this.blockBuffer, 0, datalen) != datalen) {
                throw new EOFException();
            }
            int reclen = 0;
            for (int i = 0; i < datalen - 8 && (long)i < size - 8L; i += reclen) {
                int ino = DumpArchiveUtil.convert32(this.blockBuffer, i);
                reclen = DumpArchiveUtil.convert16(this.blockBuffer, i + 4);
                byte type = this.blockBuffer[i + 6];
                String name = DumpArchiveUtil.decode(this.zipEncoding, this.blockBuffer, i + 8, this.blockBuffer[i + 7]);
                if (".".equals(name) || "..".equals(name)) continue;
                Dirent d = new Dirent(ino, entry.getIno(), type, name);
                this.names.put(ino, d);
                for (Map.Entry<Integer, DumpArchiveEntry> entry2 : this.pending.entrySet()) {
                    String path = this.getPath(entry2.getValue());
                    if (path == null) continue;
                    entry2.getValue().setName(path);
                    entry2.getValue().setSimpleName(this.names.get(entry2.getKey()).getName());
                    this.queue.add(entry2.getValue());
                }
                for (DumpArchiveEntry dumpArchiveEntry : this.queue) {
                    this.pending.remove(dumpArchiveEntry.getIno());
                }
            }
            byte[] peekBytes = this.raw.peek();
            if (!DumpArchiveUtil.verify(peekBytes)) {
                throw new InvalidFormatException();
            }
            entry = DumpArchiveEntry.parse(peekBytes);
            first = false;
            size -= 1024L;
        }
    }

    private String getPath(DumpArchiveEntry entry) {
        Stack<String> elements = new Stack<String>();
        Dirent dirent = null;
        int i = entry.getIno();
        while (true) {
            if (!this.names.containsKey(i)) {
                elements.clear();
                break;
            }
            dirent = this.names.get(i);
            elements.push(dirent.getName());
            if (dirent.getIno() == dirent.getParentIno()) break;
            i = dirent.getParentIno();
        }
        if (elements.isEmpty()) {
            this.pending.put(entry.getIno(), entry);
            return null;
        }
        StringBuilder sb = new StringBuilder((String)elements.pop());
        while (!elements.isEmpty()) {
            sb.append('/');
            sb.append((String)elements.pop());
        }
        return sb.toString();
    }

    @Override
    public int read(byte[] buf, int off, int len) throws IOException {
        int totalRead = 0;
        if (this.hasHitEOF || this.isClosed || this.entryOffset >= this.entrySize) {
            return -1;
        }
        if (this.active == null) {
            throw new IllegalStateException("No current dump entry");
        }
        if ((long)len + this.entryOffset > this.entrySize) {
            len = (int)(this.entrySize - this.entryOffset);
        }
        while (len > 0) {
            int sz;
            int n = sz = len > this.readBuf.length - this.recordOffset ? this.readBuf.length - this.recordOffset : len;
            if (this.recordOffset + sz <= this.readBuf.length) {
                System.arraycopy(this.readBuf, this.recordOffset, buf, off, sz);
                totalRead += sz;
                this.recordOffset += sz;
                len -= sz;
                off += sz;
            }
            if (len <= 0) continue;
            if (this.readIdx >= 512) {
                byte[] headerBytes = this.raw.readRecord();
                if (!DumpArchiveUtil.verify(headerBytes)) {
                    throw new InvalidFormatException();
                }
                this.active = DumpArchiveEntry.parse(headerBytes);
                this.readIdx = 0;
            }
            if (!this.active.isSparseRecord(this.readIdx++)) {
                int r = this.raw.read(this.readBuf, 0, this.readBuf.length);
                if (r != this.readBuf.length) {
                    throw new EOFException();
                }
            } else {
                Arrays.fill(this.readBuf, (byte)0);
            }
            this.recordOffset = 0;
        }
        this.entryOffset += (long)totalRead;
        return totalRead;
    }

    @Override
    public void close() throws IOException {
        if (!this.isClosed) {
            this.isClosed = true;
            this.raw.close();
        }
    }

    public static boolean matches(byte[] buffer, int length) {
        if (length < 32) {
            return false;
        }
        if (length >= 1024) {
            return DumpArchiveUtil.verify(buffer);
        }
        return 60012 == DumpArchiveUtil.convert32(buffer, 24);
    }
}

