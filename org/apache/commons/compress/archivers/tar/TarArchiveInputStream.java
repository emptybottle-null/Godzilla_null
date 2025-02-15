/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.tar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveSparseEntry;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;

public class TarArchiveInputStream
extends ArchiveInputStream {
    private static final int SMALL_BUFFER_SIZE = 256;
    private final byte[] smallBuf = new byte[256];
    private final int recordSize;
    private final int blockSize;
    private boolean hasHitEOF;
    private long entrySize;
    private long entryOffset;
    private final InputStream is;
    private TarArchiveEntry currEntry;
    private final ZipEncoding zipEncoding;
    final String encoding;
    private Map<String, String> globalPaxHeaders = new HashMap<String, String>();
    private final boolean lenient;

    public TarArchiveInputStream(InputStream is) {
        this(is, 10240, 512);
    }

    public TarArchiveInputStream(InputStream is, boolean lenient) {
        this(is, 10240, 512, null, lenient);
    }

    public TarArchiveInputStream(InputStream is, String encoding) {
        this(is, 10240, 512, encoding);
    }

    public TarArchiveInputStream(InputStream is, int blockSize) {
        this(is, blockSize, 512);
    }

    public TarArchiveInputStream(InputStream is, int blockSize, String encoding) {
        this(is, blockSize, 512, encoding);
    }

    public TarArchiveInputStream(InputStream is, int blockSize, int recordSize) {
        this(is, blockSize, recordSize, null);
    }

    public TarArchiveInputStream(InputStream is, int blockSize, int recordSize, String encoding) {
        this(is, blockSize, recordSize, encoding, false);
    }

    public TarArchiveInputStream(InputStream is, int blockSize, int recordSize, String encoding, boolean lenient) {
        this.is = is;
        this.hasHitEOF = false;
        this.encoding = encoding;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        this.recordSize = recordSize;
        this.blockSize = blockSize;
        this.lenient = lenient;
    }

    @Override
    public void close() throws IOException {
        this.is.close();
    }

    public int getRecordSize() {
        return this.recordSize;
    }

    @Override
    public int available() throws IOException {
        if (this.isDirectory()) {
            return 0;
        }
        if (this.entrySize - this.entryOffset > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)(this.entrySize - this.entryOffset);
    }

    @Override
    public long skip(long n) throws IOException {
        if (n <= 0L || this.isDirectory()) {
            return 0L;
        }
        long available = this.entrySize - this.entryOffset;
        long skipped = IOUtils.skip(this.is, Math.min(n, available));
        this.count(skipped);
        this.entryOffset += skipped;
        return skipped;
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public void mark(int markLimit) {
    }

    @Override
    public synchronized void reset() {
    }

    public TarArchiveEntry getNextTarEntry() throws IOException {
        byte[] headerBuf;
        if (this.isAtEOF()) {
            return null;
        }
        if (this.currEntry != null) {
            IOUtils.skip(this, Long.MAX_VALUE);
            this.skipRecordPadding();
        }
        if ((headerBuf = this.getRecord()) == null) {
            this.currEntry = null;
            return null;
        }
        try {
            this.currEntry = new TarArchiveEntry(headerBuf, this.zipEncoding, this.lenient);
        } catch (IllegalArgumentException e) {
            throw new IOException("Error detected parsing the header", e);
        }
        this.entryOffset = 0L;
        this.entrySize = this.currEntry.getSize();
        if (this.currEntry.isGNULongLinkEntry()) {
            byte[] longLinkData = this.getLongNameData();
            if (longLinkData == null) {
                return null;
            }
            this.currEntry.setLinkName(this.zipEncoding.decode(longLinkData));
        }
        if (this.currEntry.isGNULongNameEntry()) {
            byte[] longNameData = this.getLongNameData();
            if (longNameData == null) {
                return null;
            }
            this.currEntry.setName(this.zipEncoding.decode(longNameData));
        }
        if (this.currEntry.isGlobalPaxHeader()) {
            this.readGlobalPaxHeaders();
        }
        if (this.currEntry.isPaxHeader()) {
            this.paxHeaders();
        } else if (!this.globalPaxHeaders.isEmpty()) {
            this.applyPaxHeadersToCurrentEntry(this.globalPaxHeaders);
        }
        if (this.currEntry.isOldGNUSparse()) {
            this.readOldGNUSparse();
        }
        this.entrySize = this.currEntry.getSize();
        return this.currEntry;
    }

    private void skipRecordPadding() throws IOException {
        if (!this.isDirectory() && this.entrySize > 0L && this.entrySize % (long)this.recordSize != 0L) {
            long numRecords = this.entrySize / (long)this.recordSize + 1L;
            long padding = numRecords * (long)this.recordSize - this.entrySize;
            long skipped = IOUtils.skip(this.is, padding);
            this.count(skipped);
        }
    }

    protected byte[] getLongNameData() throws IOException {
        ByteArrayOutputStream longName = new ByteArrayOutputStream();
        int length = 0;
        while ((length = this.read(this.smallBuf)) >= 0) {
            longName.write(this.smallBuf, 0, length);
        }
        this.getNextEntry();
        if (this.currEntry == null) {
            return null;
        }
        byte[] longNameData = longName.toByteArray();
        for (length = longNameData.length; length > 0 && longNameData[length - 1] == 0; --length) {
        }
        if (length != longNameData.length) {
            byte[] l = new byte[length];
            System.arraycopy(longNameData, 0, l, 0, length);
            longNameData = l;
        }
        return longNameData;
    }

    private byte[] getRecord() throws IOException {
        byte[] headerBuf = this.readRecord();
        this.setAtEOF(this.isEOFRecord(headerBuf));
        if (this.isAtEOF() && headerBuf != null) {
            this.tryToConsumeSecondEOFRecord();
            this.consumeRemainderOfLastBlock();
            headerBuf = null;
        }
        return headerBuf;
    }

    protected boolean isEOFRecord(byte[] record) {
        return record == null || ArchiveUtils.isArrayZero(record, this.recordSize);
    }

    protected byte[] readRecord() throws IOException {
        byte[] record = new byte[this.recordSize];
        int readNow = IOUtils.readFully(this.is, record);
        this.count(readNow);
        if (readNow != this.recordSize) {
            return null;
        }
        return record;
    }

    private void readGlobalPaxHeaders() throws IOException {
        this.globalPaxHeaders = this.parsePaxHeaders(this);
        this.getNextEntry();
    }

    private void paxHeaders() throws IOException {
        Map<String, String> headers = this.parsePaxHeaders(this);
        this.getNextEntry();
        this.applyPaxHeadersToCurrentEntry(headers);
    }

    Map<String, String> parsePaxHeaders(InputStream i) throws IOException {
        int ch;
        HashMap<String, String> headers = new HashMap<String, String>(this.globalPaxHeaders);
        block0: do {
            int len = 0;
            int read = 0;
            while ((ch = i.read()) != -1) {
                ++read;
                if (ch == 10) continue block0;
                if (ch == 32) {
                    ByteArrayOutputStream coll = new ByteArrayOutputStream();
                    while ((ch = i.read()) != -1) {
                        ++read;
                        if (ch == 61) {
                            String keyword = coll.toString("UTF-8");
                            int restLen = len - read;
                            if (restLen == 1) {
                                headers.remove(keyword);
                                continue block0;
                            }
                            byte[] rest = new byte[restLen];
                            int got = IOUtils.readFully(i, rest);
                            if (got != restLen) {
                                throw new IOException("Failed to read Paxheader. Expected " + restLen + " bytes, read " + got);
                            }
                            String value = new String(rest, 0, restLen - 1, "UTF-8");
                            headers.put(keyword, value);
                            continue block0;
                        }
                        coll.write((byte)ch);
                    }
                    continue block0;
                }
                len *= 10;
                len += ch - 48;
            }
        } while (ch != -1);
        return headers;
    }

    private void applyPaxHeadersToCurrentEntry(Map<String, String> headers) {
        this.currEntry.updateEntryFromPaxHeaders(headers);
    }

    private void readOldGNUSparse() throws IOException {
        if (this.currEntry.isExtended()) {
            byte[] headerBuf;
            TarArchiveSparseEntry entry;
            do {
                if ((headerBuf = this.getRecord()) != null) continue;
                this.currEntry = null;
                break;
            } while ((entry = new TarArchiveSparseEntry(headerBuf)).isExtended());
        }
    }

    private boolean isDirectory() {
        return this.currEntry != null && this.currEntry.isDirectory();
    }

    @Override
    public ArchiveEntry getNextEntry() throws IOException {
        return this.getNextTarEntry();
    }

    private void tryToConsumeSecondEOFRecord() throws IOException {
        boolean shouldReset = true;
        boolean marked = this.is.markSupported();
        if (marked) {
            this.is.mark(this.recordSize);
        }
        try {
            shouldReset = !this.isEOFRecord(this.readRecord());
        } finally {
            if (shouldReset && marked) {
                this.pushedBackBytes(this.recordSize);
                this.is.reset();
            }
        }
    }

    @Override
    public int read(byte[] buf, int offset, int numToRead) throws IOException {
        int totalRead = 0;
        if (this.isAtEOF() || this.isDirectory() || this.entryOffset >= this.entrySize) {
            return -1;
        }
        if (this.currEntry == null) {
            throw new IllegalStateException("No current tar entry");
        }
        totalRead = this.is.read(buf, offset, numToRead = Math.min(numToRead, this.available()));
        if (totalRead == -1) {
            if (numToRead > 0) {
                throw new IOException("Truncated TAR archive");
            }
            this.setAtEOF(true);
        } else {
            this.count(totalRead);
            this.entryOffset += (long)totalRead;
        }
        return totalRead;
    }

    @Override
    public boolean canReadEntryData(ArchiveEntry ae) {
        if (ae instanceof TarArchiveEntry) {
            TarArchiveEntry te = (TarArchiveEntry)ae;
            return !te.isSparse();
        }
        return false;
    }

    public TarArchiveEntry getCurrentEntry() {
        return this.currEntry;
    }

    protected final void setCurrentEntry(TarArchiveEntry e) {
        this.currEntry = e;
    }

    protected final boolean isAtEOF() {
        return this.hasHitEOF;
    }

    protected final void setAtEOF(boolean b) {
        this.hasHitEOF = b;
    }

    private void consumeRemainderOfLastBlock() throws IOException {
        long bytesReadOfLastBlock = this.getBytesRead() % (long)this.blockSize;
        if (bytesReadOfLastBlock > 0L) {
            long skipped = IOUtils.skip(this.is, (long)this.blockSize - bytesReadOfLastBlock);
            this.count(skipped);
        }
    }

    public static boolean matches(byte[] signature, int length) {
        if (length < 265) {
            return false;
        }
        if (ArchiveUtils.matchAsciiBuffer("ustar\u0000", signature, 257, 6) && ArchiveUtils.matchAsciiBuffer("00", signature, 263, 2)) {
            return true;
        }
        if (ArchiveUtils.matchAsciiBuffer("ustar ", signature, 257, 6) && (ArchiveUtils.matchAsciiBuffer(" \u0000", signature, 263, 2) || ArchiveUtils.matchAsciiBuffer("0\u0000", signature, 263, 2))) {
            return true;
        }
        return ArchiveUtils.matchAsciiBuffer("ustar\u0000", signature, 257, 6) && ArchiveUtils.matchAsciiBuffer("\u0000\u0000", signature, 263, 2);
    }
}

