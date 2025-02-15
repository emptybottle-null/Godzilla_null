/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ExplodingInputStream;
import org.apache.commons.compress.archivers.zip.GeneralPurposeBit;
import org.apache.commons.compress.archivers.zip.InflaterInputStreamWithStatistics;
import org.apache.commons.compress.archivers.zip.UnshrinkingInputStream;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntryPredicate;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipEightByteInteger;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.archivers.zip.ZipLong;
import org.apache.commons.compress.archivers.zip.ZipMethod;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class ZipFile
implements Closeable {
    private static final int HASH_SIZE = 509;
    static final int NIBLET_MASK = 15;
    static final int BYTE_SHIFT = 8;
    private static final int POS_0 = 0;
    private static final int POS_1 = 1;
    private static final int POS_2 = 2;
    private static final int POS_3 = 3;
    private static final byte[] ONE_ZERO_BYTE = new byte[1];
    private final List<ZipArchiveEntry> entries = new LinkedList<ZipArchiveEntry>();
    private final Map<String, LinkedList<ZipArchiveEntry>> nameMap = new HashMap<String, LinkedList<ZipArchiveEntry>>(509);
    private final String encoding;
    private final ZipEncoding zipEncoding;
    private final String archiveName;
    private final SeekableByteChannel archive;
    private final boolean useUnicodeExtraFields;
    private volatile boolean closed = true;
    private final byte[] dwordBuf = new byte[8];
    private final byte[] wordBuf = new byte[4];
    private final byte[] cfhBuf = new byte[42];
    private final byte[] shortBuf = new byte[2];
    private final ByteBuffer dwordBbuf = ByteBuffer.wrap(this.dwordBuf);
    private final ByteBuffer wordBbuf = ByteBuffer.wrap(this.wordBuf);
    private final ByteBuffer cfhBbuf = ByteBuffer.wrap(this.cfhBuf);
    private static final int CFH_LEN = 42;
    private static final long CFH_SIG = ZipLong.getValue(ZipArchiveOutputStream.CFH_SIG);
    static final int MIN_EOCD_SIZE = 22;
    private static final int MAX_EOCD_SIZE = 65557;
    private static final int CFD_LOCATOR_OFFSET = 16;
    private static final int ZIP64_EOCDL_LENGTH = 20;
    private static final int ZIP64_EOCDL_LOCATOR_OFFSET = 8;
    private static final int ZIP64_EOCD_CFD_LOCATOR_OFFSET = 48;
    private static final long LFH_OFFSET_FOR_FILENAME_LENGTH = 26L;
    private final Comparator<ZipArchiveEntry> offsetComparator = new Comparator<ZipArchiveEntry>(){

        @Override
        public int compare(ZipArchiveEntry e1, ZipArchiveEntry e2) {
            Entry ent2;
            if (e1 == e2) {
                return 0;
            }
            Entry ent1 = e1 instanceof Entry ? (Entry)e1 : null;
            Entry entry = ent2 = e2 instanceof Entry ? (Entry)e2 : null;
            if (ent1 == null) {
                return 1;
            }
            if (ent2 == null) {
                return -1;
            }
            long val = ent1.getLocalHeaderOffset() - ent2.getLocalHeaderOffset();
            return val == 0L ? 0 : (val < 0L ? -1 : 1);
        }
    };

    public ZipFile(File f) throws IOException {
        this(f, "UTF8");
    }

    public ZipFile(String name) throws IOException {
        this(new File(name), "UTF8");
    }

    public ZipFile(String name, String encoding) throws IOException {
        this(new File(name), encoding, true);
    }

    public ZipFile(File f, String encoding) throws IOException {
        this(f, encoding, true);
    }

    public ZipFile(File f, String encoding, boolean useUnicodeExtraFields) throws IOException {
        this(f, encoding, useUnicodeExtraFields, false);
    }

    public ZipFile(File f, String encoding, boolean useUnicodeExtraFields, boolean ignoreLocalFileHeader) throws IOException {
        this(Files.newByteChannel(f.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), f.getAbsolutePath(), encoding, useUnicodeExtraFields, true, ignoreLocalFileHeader);
    }

    public ZipFile(SeekableByteChannel channel) throws IOException {
        this(channel, "unknown archive", "UTF8", true);
    }

    public ZipFile(SeekableByteChannel channel, String encoding) throws IOException {
        this(channel, "unknown archive", encoding, true);
    }

    public ZipFile(SeekableByteChannel channel, String archiveName, String encoding, boolean useUnicodeExtraFields) throws IOException {
        this(channel, archiveName, encoding, useUnicodeExtraFields, false, false);
    }

    public ZipFile(SeekableByteChannel channel, String archiveName, String encoding, boolean useUnicodeExtraFields, boolean ignoreLocalFileHeader) throws IOException {
        this(channel, archiveName, encoding, useUnicodeExtraFields, false, ignoreLocalFileHeader);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private ZipFile(SeekableByteChannel channel, String archiveName, String encoding, boolean useUnicodeExtraFields, boolean closeOnError, boolean ignoreLocalFileHeader) throws IOException {
        this.archiveName = archiveName;
        this.encoding = encoding;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        this.useUnicodeExtraFields = useUnicodeExtraFields;
        this.archive = channel;
        boolean success = false;
        try {
            Map<ZipArchiveEntry, NameAndComment> entriesWithoutUTF8Flag = this.populateFromCentralDirectory();
            if (!ignoreLocalFileHeader) {
                this.resolveLocalFileHeaderData(entriesWithoutUTF8Flag);
            }
            this.fillNameMap();
            success = true;
            boolean bl = this.closed = !success;
        } catch (Throwable throwable) {
            boolean bl = this.closed = !success;
            if (!success && closeOnError) {
                IOUtils.closeQuietly(this.archive);
            }
            throw throwable;
        }
        if (!success && closeOnError) {
            IOUtils.closeQuietly(this.archive);
        }
    }

    public String getEncoding() {
        return this.encoding;
    }

    @Override
    public void close() throws IOException {
        this.closed = true;
        this.archive.close();
    }

    public static void closeQuietly(ZipFile zipfile) {
        IOUtils.closeQuietly(zipfile);
    }

    public Enumeration<ZipArchiveEntry> getEntries() {
        return Collections.enumeration(this.entries);
    }

    public Enumeration<ZipArchiveEntry> getEntriesInPhysicalOrder() {
        ZipArchiveEntry[] allEntries = this.entries.toArray(new ZipArchiveEntry[this.entries.size()]);
        Arrays.sort(allEntries, this.offsetComparator);
        return Collections.enumeration(Arrays.asList(allEntries));
    }

    public ZipArchiveEntry getEntry(String name) {
        LinkedList<ZipArchiveEntry> entriesOfThatName = this.nameMap.get(name);
        return entriesOfThatName != null ? entriesOfThatName.getFirst() : null;
    }

    public Iterable<ZipArchiveEntry> getEntries(String name) {
        List<ZipArchiveEntry> entriesOfThatName = (List<ZipArchiveEntry>)this.nameMap.get(name);
        return entriesOfThatName != null ? entriesOfThatName : Collections.emptyList();
    }

    public Iterable<ZipArchiveEntry> getEntriesInPhysicalOrder(String name) {
        ZipArchiveEntry[] entriesOfThatName = new ZipArchiveEntry[]{};
        if (this.nameMap.containsKey(name)) {
            entriesOfThatName = this.nameMap.get(name).toArray(entriesOfThatName);
            Arrays.sort(entriesOfThatName, this.offsetComparator);
        }
        return Arrays.asList(entriesOfThatName);
    }

    public boolean canReadEntryData(ZipArchiveEntry ze) {
        return ZipUtil.canHandleEntryData(ze);
    }

    public InputStream getRawInputStream(ZipArchiveEntry ze) {
        if (!(ze instanceof Entry)) {
            return null;
        }
        long start = ze.getDataOffset();
        if (start == -1L) {
            return null;
        }
        return this.createBoundedInputStream(start, ze.getCompressedSize());
    }

    public void copyRawEntries(ZipArchiveOutputStream target, ZipArchiveEntryPredicate predicate) throws IOException {
        Enumeration<ZipArchiveEntry> src = this.getEntriesInPhysicalOrder();
        while (src.hasMoreElements()) {
            ZipArchiveEntry entry = src.nextElement();
            if (!predicate.test(entry)) continue;
            target.addRawArchiveEntry(entry, this.getRawInputStream(entry));
        }
    }

    public InputStream getInputStream(ZipArchiveEntry ze) throws IOException {
        if (!(ze instanceof Entry)) {
            return null;
        }
        ZipUtil.checkRequestedFeatures(ze);
        long start = this.getDataOffset(ze);
        BufferedInputStream is = new BufferedInputStream(this.createBoundedInputStream(start, ze.getCompressedSize()));
        switch (ZipMethod.getMethodByCode(ze.getMethod())) {
            case STORED: {
                return new StoredStatisticsStream(is);
            }
            case UNSHRINKING: {
                return new UnshrinkingInputStream(is);
            }
            case IMPLODING: {
                return new ExplodingInputStream(ze.getGeneralPurposeBit().getSlidingDictionarySize(), ze.getGeneralPurposeBit().getNumberOfShannonFanoTrees(), is);
            }
            case DEFLATED: {
                final Inflater inflater = new Inflater(true);
                return new InflaterInputStreamWithStatistics(new SequenceInputStream(is, new ByteArrayInputStream(ONE_ZERO_BYTE)), inflater){

                    @Override
                    public void close() throws IOException {
                        try {
                            super.close();
                        } finally {
                            inflater.end();
                        }
                    }
                };
            }
            case BZIP2: {
                return new BZip2CompressorInputStream(is);
            }
            case ENHANCED_DEFLATED: {
                return new Deflate64CompressorInputStream(is);
            }
        }
        throw new UnsupportedZipFeatureException(ZipMethod.getMethodByCode(ze.getMethod()), ze);
    }

    public String getUnixSymlink(ZipArchiveEntry entry) throws IOException {
        if (entry != null && entry.isUnixSymlink()) {
            try (InputStream in = this.getInputStream(entry);){
                String string = this.zipEncoding.decode(IOUtils.toByteArray(in));
                return string;
            }
        }
        return null;
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.closed) {
                System.err.println("Cleaning up unclosed ZipFile for archive " + this.archiveName);
                this.close();
            }
        } finally {
            super.finalize();
        }
    }

    private Map<ZipArchiveEntry, NameAndComment> populateFromCentralDirectory() throws IOException {
        HashMap<ZipArchiveEntry, NameAndComment> noUTF8Flag = new HashMap<ZipArchiveEntry, NameAndComment>();
        this.positionAtCentralDirectory();
        this.wordBbuf.rewind();
        IOUtils.readFully(this.archive, this.wordBbuf);
        long sig = ZipLong.getValue(this.wordBuf);
        if (sig != CFH_SIG && this.startsWithLocalFileHeader()) {
            throw new IOException("Central directory is empty, can't expand corrupt archive.");
        }
        while (sig == CFH_SIG) {
            this.readCentralDirectoryEntry(noUTF8Flag);
            this.wordBbuf.rewind();
            IOUtils.readFully(this.archive, this.wordBbuf);
            sig = ZipLong.getValue(this.wordBuf);
        }
        return noUTF8Flag;
    }

    private void readCentralDirectoryEntry(Map<ZipArchiveEntry, NameAndComment> noUTF8Flag) throws IOException {
        ZipEncoding entryEncoding;
        this.cfhBbuf.rewind();
        IOUtils.readFully(this.archive, this.cfhBbuf);
        int off = 0;
        Entry ze = new Entry();
        int versionMadeBy = ZipShort.getValue(this.cfhBuf, off);
        ze.setVersionMadeBy(versionMadeBy);
        ze.setPlatform(versionMadeBy >> 8 & 0xF);
        ze.setVersionRequired(ZipShort.getValue(this.cfhBuf, off += 2));
        GeneralPurposeBit gpFlag = GeneralPurposeBit.parse(this.cfhBuf, off += 2);
        boolean hasUTF8Flag = gpFlag.usesUTF8ForNames();
        ZipEncoding zipEncoding = entryEncoding = hasUTF8Flag ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
        if (hasUTF8Flag) {
            ze.setNameSource(ZipArchiveEntry.NameSource.NAME_WITH_EFS_FLAG);
        }
        ze.setGeneralPurposeBit(gpFlag);
        ze.setRawFlag(ZipShort.getValue(this.cfhBuf, off));
        ze.setMethod(ZipShort.getValue(this.cfhBuf, off += 2));
        long time = ZipUtil.dosToJavaTime(ZipLong.getValue(this.cfhBuf, off += 2));
        ze.setTime(time);
        ze.setCrc(ZipLong.getValue(this.cfhBuf, off += 4));
        ze.setCompressedSize(ZipLong.getValue(this.cfhBuf, off += 4));
        ze.setSize(ZipLong.getValue(this.cfhBuf, off += 4));
        int fileNameLen = ZipShort.getValue(this.cfhBuf, off += 4);
        int extraLen = ZipShort.getValue(this.cfhBuf, off += 2);
        int commentLen = ZipShort.getValue(this.cfhBuf, off += 2);
        int diskStart = ZipShort.getValue(this.cfhBuf, off += 2);
        ze.setInternalAttributes(ZipShort.getValue(this.cfhBuf, off += 2));
        ze.setExternalAttributes(ZipLong.getValue(this.cfhBuf, off += 2));
        byte[] fileName = new byte[fileNameLen];
        IOUtils.readFully(this.archive, ByteBuffer.wrap(fileName));
        ze.setName(entryEncoding.decode(fileName), fileName);
        ze.setLocalHeaderOffset(ZipLong.getValue(this.cfhBuf, off += 4));
        this.entries.add(ze);
        byte[] cdExtraData = new byte[extraLen];
        IOUtils.readFully(this.archive, ByteBuffer.wrap(cdExtraData));
        ze.setCentralDirectoryExtra(cdExtraData);
        this.setSizesAndOffsetFromZip64Extra(ze, diskStart);
        byte[] comment = new byte[commentLen];
        IOUtils.readFully(this.archive, ByteBuffer.wrap(comment));
        ze.setComment(entryEncoding.decode(comment));
        if (!hasUTF8Flag && this.useUnicodeExtraFields) {
            noUTF8Flag.put(ze, new NameAndComment(fileName, comment));
        }
        ze.setStreamContiguous(true);
    }

    private void setSizesAndOffsetFromZip64Extra(ZipArchiveEntry ze, int diskStart) throws IOException {
        Zip64ExtendedInformationExtraField z64 = (Zip64ExtendedInformationExtraField)ze.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (z64 != null) {
            boolean hasUncompressedSize = ze.getSize() == 0xFFFFFFFFL;
            boolean hasCompressedSize = ze.getCompressedSize() == 0xFFFFFFFFL;
            boolean hasRelativeHeaderOffset = ze.getLocalHeaderOffset() == 0xFFFFFFFFL;
            z64.reparseCentralDirectoryData(hasUncompressedSize, hasCompressedSize, hasRelativeHeaderOffset, diskStart == 65535);
            if (hasUncompressedSize) {
                ze.setSize(z64.getSize().getLongValue());
            } else if (hasCompressedSize) {
                z64.setSize(new ZipEightByteInteger(ze.getSize()));
            }
            if (hasCompressedSize) {
                ze.setCompressedSize(z64.getCompressedSize().getLongValue());
            } else if (hasUncompressedSize) {
                z64.setCompressedSize(new ZipEightByteInteger(ze.getCompressedSize()));
            }
            if (hasRelativeHeaderOffset) {
                ze.setLocalHeaderOffset(z64.getRelativeHeaderOffset().getLongValue());
            }
        }
    }

    private void positionAtCentralDirectory() throws IOException {
        boolean searchedForZip64EOCD;
        this.positionAtEndOfCentralDirectoryRecord();
        boolean found = false;
        boolean bl = searchedForZip64EOCD = this.archive.position() > 20L;
        if (searchedForZip64EOCD) {
            this.archive.position(this.archive.position() - 20L);
            this.wordBbuf.rewind();
            IOUtils.readFully(this.archive, this.wordBbuf);
            found = Arrays.equals(ZipArchiveOutputStream.ZIP64_EOCD_LOC_SIG, this.wordBuf);
        }
        if (!found) {
            if (searchedForZip64EOCD) {
                this.skipBytes(16);
            }
            this.positionAtCentralDirectory32();
        } else {
            this.positionAtCentralDirectory64();
        }
    }

    private void positionAtCentralDirectory64() throws IOException {
        this.skipBytes(4);
        this.dwordBbuf.rewind();
        IOUtils.readFully(this.archive, this.dwordBbuf);
        this.archive.position(ZipEightByteInteger.getLongValue(this.dwordBuf));
        this.wordBbuf.rewind();
        IOUtils.readFully(this.archive, this.wordBbuf);
        if (!Arrays.equals(this.wordBuf, ZipArchiveOutputStream.ZIP64_EOCD_SIG)) {
            throw new ZipException("Archive's ZIP64 end of central directory locator is corrupt.");
        }
        this.skipBytes(44);
        this.dwordBbuf.rewind();
        IOUtils.readFully(this.archive, this.dwordBbuf);
        this.archive.position(ZipEightByteInteger.getLongValue(this.dwordBuf));
    }

    private void positionAtCentralDirectory32() throws IOException {
        this.skipBytes(16);
        this.wordBbuf.rewind();
        IOUtils.readFully(this.archive, this.wordBbuf);
        this.archive.position(ZipLong.getValue(this.wordBuf));
    }

    private void positionAtEndOfCentralDirectoryRecord() throws IOException {
        boolean found = this.tryToLocateSignature(22L, 65557L, ZipArchiveOutputStream.EOCD_SIG);
        if (!found) {
            throw new ZipException("Archive is not a ZIP archive");
        }
    }

    private boolean tryToLocateSignature(long minDistanceFromEnd, long maxDistanceFromEnd, byte[] sig) throws IOException {
        long off;
        boolean found = false;
        long stopSearching = Math.max(0L, this.archive.size() - maxDistanceFromEnd);
        if (off >= 0L) {
            for (off = this.archive.size() - minDistanceFromEnd; off >= stopSearching; --off) {
                this.archive.position(off);
                try {
                    this.wordBbuf.rewind();
                    IOUtils.readFully(this.archive, this.wordBbuf);
                    this.wordBbuf.flip();
                } catch (EOFException ex) {
                    break;
                }
                byte curr = this.wordBbuf.get();
                if (curr != sig[0] || (curr = this.wordBbuf.get()) != sig[1] || (curr = this.wordBbuf.get()) != sig[2] || (curr = this.wordBbuf.get()) != sig[3]) continue;
                found = true;
                break;
            }
        }
        if (found) {
            this.archive.position(off);
        }
        return found;
    }

    private void skipBytes(int count) throws IOException {
        long currentPosition = this.archive.position();
        long newPosition = currentPosition + (long)count;
        if (newPosition > this.archive.size()) {
            throw new EOFException();
        }
        this.archive.position(newPosition);
    }

    private void resolveLocalFileHeaderData(Map<ZipArchiveEntry, NameAndComment> entriesWithoutUTF8Flag) throws IOException {
        for (ZipArchiveEntry zipArchiveEntry : this.entries) {
            Entry ze = (Entry)zipArchiveEntry;
            int[] lens = this.setDataOffset(ze);
            int fileNameLen = lens[0];
            int extraFieldLen = lens[1];
            this.skipBytes(fileNameLen);
            byte[] localExtraData = new byte[extraFieldLen];
            IOUtils.readFully(this.archive, ByteBuffer.wrap(localExtraData));
            ze.setExtra(localExtraData);
            if (!entriesWithoutUTF8Flag.containsKey(ze)) continue;
            NameAndComment nc = entriesWithoutUTF8Flag.get(ze);
            ZipUtil.setNameAndCommentFromExtraFields(ze, nc.name, nc.comment);
        }
    }

    private void fillNameMap() {
        for (ZipArchiveEntry ze : this.entries) {
            String name = ze.getName();
            LinkedList<ZipArchiveEntry> entriesOfThatName = this.nameMap.get(name);
            if (entriesOfThatName == null) {
                entriesOfThatName = new LinkedList();
                this.nameMap.put(name, entriesOfThatName);
            }
            entriesOfThatName.addLast(ze);
        }
    }

    private int[] setDataOffset(ZipArchiveEntry ze) throws IOException {
        long offset = ze.getLocalHeaderOffset();
        this.archive.position(offset + 26L);
        this.wordBbuf.rewind();
        IOUtils.readFully(this.archive, this.wordBbuf);
        this.wordBbuf.flip();
        this.wordBbuf.get(this.shortBuf);
        int fileNameLen = ZipShort.getValue(this.shortBuf);
        this.wordBbuf.get(this.shortBuf);
        int extraFieldLen = ZipShort.getValue(this.shortBuf);
        ze.setDataOffset(offset + 26L + 2L + 2L + (long)fileNameLen + (long)extraFieldLen);
        return new int[]{fileNameLen, extraFieldLen};
    }

    private long getDataOffset(ZipArchiveEntry ze) throws IOException {
        long s = ze.getDataOffset();
        if (s == -1L) {
            this.setDataOffset(ze);
            return ze.getDataOffset();
        }
        return s;
    }

    private boolean startsWithLocalFileHeader() throws IOException {
        this.archive.position(0L);
        this.wordBbuf.rewind();
        IOUtils.readFully(this.archive, this.wordBbuf);
        return Arrays.equals(this.wordBuf, ZipArchiveOutputStream.LFH_SIG);
    }

    private BoundedInputStream createBoundedInputStream(long start, long remaining) {
        return this.archive instanceof FileChannel ? new BoundedFileChannelInputStream(start, remaining) : new BoundedInputStream(start, remaining);
    }

    private static class StoredStatisticsStream
    extends CountingInputStream
    implements InputStreamStatistics {
        StoredStatisticsStream(InputStream in) {
            super(in);
        }

        @Override
        public long getCompressedCount() {
            return super.getBytesRead();
        }

        @Override
        public long getUncompressedCount() {
            return this.getCompressedCount();
        }
    }

    private static class Entry
    extends ZipArchiveEntry {
        Entry() {
        }

        @Override
        public int hashCode() {
            return 3 * super.hashCode() + (int)this.getLocalHeaderOffset() + (int)(this.getLocalHeaderOffset() >> 32);
        }

        @Override
        public boolean equals(Object other) {
            if (super.equals(other)) {
                Entry otherEntry = (Entry)other;
                return this.getLocalHeaderOffset() == otherEntry.getLocalHeaderOffset() && super.getDataOffset() == otherEntry.getDataOffset();
            }
            return false;
        }
    }

    private static final class NameAndComment {
        private final byte[] name;
        private final byte[] comment;

        private NameAndComment(byte[] name, byte[] comment) {
            this.name = name;
            this.comment = comment;
        }
    }

    private class BoundedFileChannelInputStream
    extends BoundedInputStream {
        private final FileChannel archive;

        BoundedFileChannelInputStream(long start, long remaining) {
            super(start, remaining);
            this.archive = (FileChannel)ZipFile.this.archive;
        }

        @Override
        protected int read(long pos, ByteBuffer buf) throws IOException {
            int read = this.archive.read(buf, pos);
            buf.flip();
            return read;
        }
    }

    private class BoundedInputStream
    extends InputStream {
        private ByteBuffer singleByteBuffer;
        private final long end;
        private long loc;

        BoundedInputStream(long start, long remaining) {
            this.end = start + remaining;
            if (this.end < start) {
                throw new IllegalArgumentException("Invalid length of stream at offset=" + start + ", length=" + remaining);
            }
            this.loc = start;
        }

        @Override
        public synchronized int read() throws IOException {
            if (this.loc >= this.end) {
                return -1;
            }
            if (this.singleByteBuffer == null) {
                this.singleByteBuffer = ByteBuffer.allocate(1);
            } else {
                this.singleByteBuffer.rewind();
            }
            int read = this.read(this.loc, this.singleByteBuffer);
            if (read < 0) {
                return read;
            }
            ++this.loc;
            return this.singleByteBuffer.get() & 0xFF;
        }

        @Override
        public synchronized int read(byte[] b, int off, int len) throws IOException {
            ByteBuffer buf;
            int ret;
            if (len <= 0) {
                return 0;
            }
            if ((long)len > this.end - this.loc) {
                if (this.loc >= this.end) {
                    return -1;
                }
                len = (int)(this.end - this.loc);
            }
            if ((ret = this.read(this.loc, buf = ByteBuffer.wrap(b, off, len))) > 0) {
                this.loc += (long)ret;
                return ret;
            }
            return ret;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        protected int read(long pos, ByteBuffer buf) throws IOException {
            int read;
            SeekableByteChannel seekableByteChannel = ZipFile.this.archive;
            synchronized (seekableByteChannel) {
                ZipFile.this.archive.position(pos);
                read = ZipFile.this.archive.read(buf);
            }
            buf.flip();
            return read;
        }
    }
}

