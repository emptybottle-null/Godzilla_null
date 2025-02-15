/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.GeneralPurposeBit;
import org.apache.commons.compress.archivers.zip.ResourceAlignmentExtraField;
import org.apache.commons.compress.archivers.zip.StreamCompressor;
import org.apache.commons.compress.archivers.zip.UnicodeCommentExtraField;
import org.apache.commons.compress.archivers.zip.UnicodePathExtraField;
import org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.Zip64RequiredException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipEightByteInteger;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.archivers.zip.ZipLong;
import org.apache.commons.compress.archivers.zip.ZipMethod;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.commons.compress.utils.IOUtils;

public class ZipArchiveOutputStream
extends ArchiveOutputStream {
    static final int BUFFER_SIZE = 512;
    private static final int LFH_SIG_OFFSET = 0;
    private static final int LFH_VERSION_NEEDED_OFFSET = 4;
    private static final int LFH_GPB_OFFSET = 6;
    private static final int LFH_METHOD_OFFSET = 8;
    private static final int LFH_TIME_OFFSET = 10;
    private static final int LFH_CRC_OFFSET = 14;
    private static final int LFH_COMPRESSED_SIZE_OFFSET = 18;
    private static final int LFH_ORIGINAL_SIZE_OFFSET = 22;
    private static final int LFH_FILENAME_LENGTH_OFFSET = 26;
    private static final int LFH_EXTRA_LENGTH_OFFSET = 28;
    private static final int LFH_FILENAME_OFFSET = 30;
    private static final int CFH_SIG_OFFSET = 0;
    private static final int CFH_VERSION_MADE_BY_OFFSET = 4;
    private static final int CFH_VERSION_NEEDED_OFFSET = 6;
    private static final int CFH_GPB_OFFSET = 8;
    private static final int CFH_METHOD_OFFSET = 10;
    private static final int CFH_TIME_OFFSET = 12;
    private static final int CFH_CRC_OFFSET = 16;
    private static final int CFH_COMPRESSED_SIZE_OFFSET = 20;
    private static final int CFH_ORIGINAL_SIZE_OFFSET = 24;
    private static final int CFH_FILENAME_LENGTH_OFFSET = 28;
    private static final int CFH_EXTRA_LENGTH_OFFSET = 30;
    private static final int CFH_COMMENT_LENGTH_OFFSET = 32;
    private static final int CFH_DISK_NUMBER_OFFSET = 34;
    private static final int CFH_INTERNAL_ATTRIBUTES_OFFSET = 36;
    private static final int CFH_EXTERNAL_ATTRIBUTES_OFFSET = 38;
    private static final int CFH_LFH_OFFSET = 42;
    private static final int CFH_FILENAME_OFFSET = 46;
    protected boolean finished = false;
    public static final int DEFLATED = 8;
    public static final int DEFAULT_COMPRESSION = -1;
    public static final int STORED = 0;
    static final String DEFAULT_ENCODING = "UTF8";
    @Deprecated
    public static final int EFS_FLAG = 2048;
    private static final byte[] EMPTY = new byte[0];
    private CurrentEntry entry;
    private String comment = "";
    private int level = -1;
    private boolean hasCompressionLevelChanged = false;
    private int method = 8;
    private final List<ZipArchiveEntry> entries = new LinkedList<ZipArchiveEntry>();
    private final StreamCompressor streamCompressor;
    private long cdOffset = 0L;
    private long cdLength = 0L;
    private static final byte[] ZERO = new byte[]{0, 0};
    private static final byte[] LZERO = new byte[]{0, 0, 0, 0};
    private static final byte[] ONE = ZipLong.getBytes(1L);
    private final Map<ZipArchiveEntry, EntryMetaData> metaData = new HashMap<ZipArchiveEntry, EntryMetaData>();
    private String encoding = "UTF8";
    private ZipEncoding zipEncoding = ZipEncodingHelper.getZipEncoding("UTF8");
    protected final Deflater def;
    private final SeekableByteChannel channel;
    private final OutputStream out;
    private boolean useUTF8Flag = true;
    private boolean fallbackToUTF8 = false;
    private UnicodeExtraFieldPolicy createUnicodeExtraFields = UnicodeExtraFieldPolicy.NEVER;
    private boolean hasUsedZip64 = false;
    private Zip64Mode zip64Mode = Zip64Mode.AsNeeded;
    private final byte[] copyBuffer = new byte[32768];
    private final Calendar calendarInstance = Calendar.getInstance();
    static final byte[] LFH_SIG = ZipLong.LFH_SIG.getBytes();
    static final byte[] DD_SIG = ZipLong.DD_SIG.getBytes();
    static final byte[] CFH_SIG = ZipLong.CFH_SIG.getBytes();
    static final byte[] EOCD_SIG = ZipLong.getBytes(101010256L);
    static final byte[] ZIP64_EOCD_SIG = ZipLong.getBytes(101075792L);
    static final byte[] ZIP64_EOCD_LOC_SIG = ZipLong.getBytes(117853008L);

    public ZipArchiveOutputStream(OutputStream out) {
        this.out = out;
        this.channel = null;
        this.def = new Deflater(this.level, true);
        this.streamCompressor = StreamCompressor.create(out, this.def);
    }

    public ZipArchiveOutputStream(File file) throws IOException {
        this.def = new Deflater(this.level, true);
        FileOutputStream o = null;
        SeekableByteChannel _channel = null;
        StreamCompressor _streamCompressor = null;
        try {
            _channel = Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.TRUNCATE_EXISTING), new FileAttribute[0]);
            _streamCompressor = StreamCompressor.create(_channel, this.def);
        } catch (IOException e) {
            IOUtils.closeQuietly(_channel);
            _channel = null;
            o = new FileOutputStream(file);
            _streamCompressor = StreamCompressor.create(o, this.def);
        }
        this.out = o;
        this.channel = _channel;
        this.streamCompressor = _streamCompressor;
    }

    public ZipArchiveOutputStream(SeekableByteChannel channel) throws IOException {
        this.channel = channel;
        this.def = new Deflater(this.level, true);
        this.streamCompressor = StreamCompressor.create(channel, this.def);
        this.out = null;
    }

    public boolean isSeekable() {
        return this.channel != null;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        if (this.useUTF8Flag && !ZipEncodingHelper.isUTF8(encoding)) {
            this.useUTF8Flag = false;
        }
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setUseLanguageEncodingFlag(boolean b) {
        this.useUTF8Flag = b && ZipEncodingHelper.isUTF8(this.encoding);
    }

    public void setCreateUnicodeExtraFields(UnicodeExtraFieldPolicy b) {
        this.createUnicodeExtraFields = b;
    }

    public void setFallbackToUTF8(boolean b) {
        this.fallbackToUTF8 = b;
    }

    public void setUseZip64(Zip64Mode mode) {
        this.zip64Mode = mode;
    }

    @Override
    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        if (this.entry != null) {
            throw new IOException("This archive contains unclosed entries.");
        }
        this.cdOffset = this.streamCompressor.getTotalBytesWritten();
        this.writeCentralDirectoryInChunks();
        this.cdLength = this.streamCompressor.getTotalBytesWritten() - this.cdOffset;
        this.writeZip64CentralDirectory();
        this.writeCentralDirectoryEnd();
        this.metaData.clear();
        this.entries.clear();
        this.streamCompressor.close();
        this.finished = true;
    }

    private void writeCentralDirectoryInChunks() throws IOException {
        int NUM_PER_WRITE = 1000;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(70000);
        int count = 0;
        for (ZipArchiveEntry ze : this.entries) {
            byteArrayOutputStream.write(this.createCentralFileHeader(ze));
            if (++count <= 1000) continue;
            this.writeCounted(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.reset();
            count = 0;
        }
        this.writeCounted(byteArrayOutputStream.toByteArray());
    }

    @Override
    public void closeArchiveEntry() throws IOException {
        this.preClose();
        this.flushDeflater();
        long bytesWritten = this.streamCompressor.getTotalBytesWritten() - this.entry.dataStart;
        long realCrc = this.streamCompressor.getCrc32();
        this.entry.bytesRead = this.streamCompressor.getBytesRead();
        Zip64Mode effectiveMode = this.getEffectiveZip64Mode(this.entry.entry);
        boolean actuallyNeedsZip64 = this.handleSizesAndCrc(bytesWritten, realCrc, effectiveMode);
        this.closeEntry(actuallyNeedsZip64, false);
        this.streamCompressor.reset();
    }

    private void closeCopiedEntry(boolean phased) throws IOException {
        this.preClose();
        this.entry.bytesRead = this.entry.entry.getSize();
        Zip64Mode effectiveMode = this.getEffectiveZip64Mode(this.entry.entry);
        boolean actuallyNeedsZip64 = this.checkIfNeedsZip64(effectiveMode);
        this.closeEntry(actuallyNeedsZip64, phased);
    }

    private void closeEntry(boolean actuallyNeedsZip64, boolean phased) throws IOException {
        if (!phased && this.channel != null) {
            this.rewriteSizesAndCrc(actuallyNeedsZip64);
        }
        if (!phased) {
            this.writeDataDescriptor(this.entry.entry);
        }
        this.entry = null;
    }

    private void preClose() throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        if (this.entry == null) {
            throw new IOException("No current entry to close");
        }
        if (!this.entry.hasWritten) {
            this.write(EMPTY, 0, 0);
        }
    }

    public void addRawArchiveEntry(ZipArchiveEntry entry, InputStream rawStream) throws IOException {
        ZipArchiveEntry ae = new ZipArchiveEntry(entry);
        if (this.hasZip64Extra(ae)) {
            ae.removeExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        }
        boolean is2PhaseSource = ae.getCrc() != -1L && ae.getSize() != -1L && ae.getCompressedSize() != -1L;
        this.putArchiveEntry(ae, is2PhaseSource);
        this.copyFromZipInputStream(rawStream);
        this.closeCopiedEntry(is2PhaseSource);
    }

    private void flushDeflater() throws IOException {
        if (this.entry.entry.getMethod() == 8) {
            this.streamCompressor.flushDeflater();
        }
    }

    private boolean handleSizesAndCrc(long bytesWritten, long crc, Zip64Mode effectiveMode) throws ZipException {
        if (this.entry.entry.getMethod() == 8) {
            this.entry.entry.setSize(this.entry.bytesRead);
            this.entry.entry.setCompressedSize(bytesWritten);
            this.entry.entry.setCrc(crc);
        } else if (this.channel == null) {
            if (this.entry.entry.getCrc() != crc) {
                throw new ZipException("Bad CRC checksum for entry " + this.entry.entry.getName() + ": " + Long.toHexString(this.entry.entry.getCrc()) + " instead of " + Long.toHexString(crc));
            }
            if (this.entry.entry.getSize() != bytesWritten) {
                throw new ZipException("Bad size for entry " + this.entry.entry.getName() + ": " + this.entry.entry.getSize() + " instead of " + bytesWritten);
            }
        } else {
            this.entry.entry.setSize(bytesWritten);
            this.entry.entry.setCompressedSize(bytesWritten);
            this.entry.entry.setCrc(crc);
        }
        return this.checkIfNeedsZip64(effectiveMode);
    }

    private boolean checkIfNeedsZip64(Zip64Mode effectiveMode) throws ZipException {
        boolean actuallyNeedsZip64 = this.isZip64Required(this.entry.entry, effectiveMode);
        if (actuallyNeedsZip64 && effectiveMode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.getEntryTooBigMessage(this.entry.entry));
        }
        return actuallyNeedsZip64;
    }

    private boolean isZip64Required(ZipArchiveEntry entry1, Zip64Mode requestedMode) {
        return requestedMode == Zip64Mode.Always || this.isTooLageForZip32(entry1);
    }

    private boolean isTooLageForZip32(ZipArchiveEntry zipArchiveEntry) {
        return zipArchiveEntry.getSize() >= 0xFFFFFFFFL || zipArchiveEntry.getCompressedSize() >= 0xFFFFFFFFL;
    }

    private void rewriteSizesAndCrc(boolean actuallyNeedsZip64) throws IOException {
        long save = this.channel.position();
        this.channel.position(this.entry.localDataStart);
        this.writeOut(ZipLong.getBytes(this.entry.entry.getCrc()));
        if (!this.hasZip64Extra(this.entry.entry) || !actuallyNeedsZip64) {
            this.writeOut(ZipLong.getBytes(this.entry.entry.getCompressedSize()));
            this.writeOut(ZipLong.getBytes(this.entry.entry.getSize()));
        } else {
            this.writeOut(ZipLong.ZIP64_MAGIC.getBytes());
            this.writeOut(ZipLong.ZIP64_MAGIC.getBytes());
        }
        if (this.hasZip64Extra(this.entry.entry)) {
            ByteBuffer name = this.getName(this.entry.entry);
            int nameLen = name.limit() - name.position();
            this.channel.position(this.entry.localDataStart + 12L + 4L + (long)nameLen + 4L);
            this.writeOut(ZipEightByteInteger.getBytes(this.entry.entry.getSize()));
            this.writeOut(ZipEightByteInteger.getBytes(this.entry.entry.getCompressedSize()));
            if (!actuallyNeedsZip64) {
                this.channel.position(this.entry.localDataStart - 10L);
                this.writeOut(ZipShort.getBytes(this.versionNeededToExtract(this.entry.entry.getMethod(), false, false)));
                this.entry.entry.removeExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
                this.entry.entry.setExtra();
                if (this.entry.causedUseOfZip64) {
                    this.hasUsedZip64 = false;
                }
            }
        }
        this.channel.position(save);
    }

    @Override
    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        this.putArchiveEntry(archiveEntry, false);
    }

    private void putArchiveEntry(ArchiveEntry archiveEntry, boolean phased) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        if (this.entry != null) {
            this.closeArchiveEntry();
        }
        this.entry = new CurrentEntry((ZipArchiveEntry)archiveEntry);
        this.entries.add(this.entry.entry);
        this.setDefaults(this.entry.entry);
        Zip64Mode effectiveMode = this.getEffectiveZip64Mode(this.entry.entry);
        this.validateSizeInformation(effectiveMode);
        if (this.shouldAddZip64Extra(this.entry.entry, effectiveMode)) {
            ZipEightByteInteger compressedSize;
            ZipEightByteInteger size;
            Zip64ExtendedInformationExtraField z64 = this.getZip64Extra(this.entry.entry);
            if (phased) {
                size = new ZipEightByteInteger(this.entry.entry.getSize());
                compressedSize = new ZipEightByteInteger(this.entry.entry.getCompressedSize());
            } else {
                compressedSize = this.entry.entry.getMethod() == 0 && this.entry.entry.getSize() != -1L ? (size = new ZipEightByteInteger(this.entry.entry.getSize())) : (size = ZipEightByteInteger.ZERO);
            }
            z64.setSize(size);
            z64.setCompressedSize(compressedSize);
            this.entry.entry.setExtra();
        }
        if (this.entry.entry.getMethod() == 8 && this.hasCompressionLevelChanged) {
            this.def.setLevel(this.level);
            this.hasCompressionLevelChanged = false;
        }
        this.writeLocalFileHeader((ZipArchiveEntry)archiveEntry, phased);
    }

    private void setDefaults(ZipArchiveEntry entry) {
        if (entry.getMethod() == -1) {
            entry.setMethod(this.method);
        }
        if (entry.getTime() == -1L) {
            entry.setTime(System.currentTimeMillis());
        }
    }

    private void validateSizeInformation(Zip64Mode effectiveMode) throws ZipException {
        if (this.entry.entry.getMethod() == 0 && this.channel == null) {
            if (this.entry.entry.getSize() == -1L) {
                throw new ZipException("Uncompressed size is required for STORED method when not writing to a file");
            }
            if (this.entry.entry.getCrc() == -1L) {
                throw new ZipException("CRC checksum is required for STORED method when not writing to a file");
            }
            this.entry.entry.setCompressedSize(this.entry.entry.getSize());
        }
        if ((this.entry.entry.getSize() >= 0xFFFFFFFFL || this.entry.entry.getCompressedSize() >= 0xFFFFFFFFL) && effectiveMode == Zip64Mode.Never) {
            throw new Zip64RequiredException(Zip64RequiredException.getEntryTooBigMessage(this.entry.entry));
        }
    }

    private boolean shouldAddZip64Extra(ZipArchiveEntry entry, Zip64Mode mode) {
        return mode == Zip64Mode.Always || entry.getSize() >= 0xFFFFFFFFL || entry.getCompressedSize() >= 0xFFFFFFFFL || entry.getSize() == -1L && this.channel != null && mode != Zip64Mode.Never;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setLevel(int level) {
        if (level < -1 || level > 9) {
            throw new IllegalArgumentException("Invalid compression level: " + level);
        }
        if (this.level == level) {
            return;
        }
        this.hasCompressionLevelChanged = true;
        this.level = level;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    @Override
    public boolean canWriteEntryData(ArchiveEntry ae) {
        if (ae instanceof ZipArchiveEntry) {
            ZipArchiveEntry zae = (ZipArchiveEntry)ae;
            return zae.getMethod() != ZipMethod.IMPLODING.getCode() && zae.getMethod() != ZipMethod.UNSHRINKING.getCode() && ZipUtil.canHandleEntryData(zae);
        }
        return false;
    }

    @Override
    public void write(byte[] b, int offset, int length) throws IOException {
        if (this.entry == null) {
            throw new IllegalStateException("No current entry");
        }
        ZipUtil.checkRequestedFeatures(this.entry.entry);
        long writtenThisTime = this.streamCompressor.write(b, offset, length, this.entry.entry.getMethod());
        this.count(writtenThisTime);
    }

    private void writeCounted(byte[] data) throws IOException {
        this.streamCompressor.writeCounted(data);
    }

    private void copyFromZipInputStream(InputStream src) throws IOException {
        int length;
        if (this.entry == null) {
            throw new IllegalStateException("No current entry");
        }
        ZipUtil.checkRequestedFeatures(this.entry.entry);
        this.entry.hasWritten = true;
        while ((length = src.read(this.copyBuffer)) >= 0) {
            this.streamCompressor.writeCounted(this.copyBuffer, 0, length);
            this.count(length);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (!this.finished) {
                this.finish();
            }
        } finally {
            this.destroy();
        }
    }

    @Override
    public void flush() throws IOException {
        if (this.out != null) {
            this.out.flush();
        }
    }

    protected final void deflate() throws IOException {
        this.streamCompressor.deflate();
    }

    protected void writeLocalFileHeader(ZipArchiveEntry ze) throws IOException {
        this.writeLocalFileHeader(ze, false);
    }

    private void writeLocalFileHeader(ZipArchiveEntry ze, boolean phased) throws IOException {
        boolean encodable = this.zipEncoding.canEncode(ze.getName());
        ByteBuffer name = this.getName(ze);
        if (this.createUnicodeExtraFields != UnicodeExtraFieldPolicy.NEVER) {
            this.addUnicodeExtraFields(ze, encodable, name);
        }
        long localHeaderStart = this.streamCompressor.getTotalBytesWritten();
        byte[] localHeader = this.createLocalFileHeader(ze, name, encodable, phased, localHeaderStart);
        this.metaData.put(ze, new EntryMetaData(localHeaderStart, this.usesDataDescriptor(ze.getMethod(), phased)));
        this.entry.localDataStart = localHeaderStart + 14L;
        this.writeCounted(localHeader);
        this.entry.dataStart = this.streamCompressor.getTotalBytesWritten();
    }

    private byte[] createLocalFileHeader(ZipArchiveEntry ze, ByteBuffer name, boolean encodable, boolean phased, long archiveOffset) {
        int alignment;
        ResourceAlignmentExtraField oldAlignmentEx = (ResourceAlignmentExtraField)ze.getExtraField(ResourceAlignmentExtraField.ID);
        if (oldAlignmentEx != null) {
            ze.removeExtraField(ResourceAlignmentExtraField.ID);
        }
        if ((alignment = ze.getAlignment()) <= 0 && oldAlignmentEx != null) {
            alignment = oldAlignmentEx.getAlignment();
        }
        if (alignment > 1 || oldAlignmentEx != null && !oldAlignmentEx.allowMethodChange()) {
            int oldLength = 30 + name.limit() - name.position() + ze.getLocalFileDataExtra().length;
            int padding = (int)(-archiveOffset - (long)oldLength - 4L - 2L & (long)(alignment - 1));
            ze.addExtraField(new ResourceAlignmentExtraField(alignment, oldAlignmentEx != null && oldAlignmentEx.allowMethodChange(), padding));
        }
        byte[] extra = ze.getLocalFileDataExtra();
        int nameLen = name.limit() - name.position();
        int len = 30 + nameLen + extra.length;
        byte[] buf = new byte[len];
        System.arraycopy(LFH_SIG, 0, buf, 0, 4);
        int zipMethod = ze.getMethod();
        boolean dataDescriptor = this.usesDataDescriptor(zipMethod, phased);
        ZipShort.putShort(this.versionNeededToExtract(zipMethod, this.hasZip64Extra(ze), dataDescriptor), buf, 4);
        GeneralPurposeBit generalPurposeBit = this.getGeneralPurposeBits(!encodable && this.fallbackToUTF8, dataDescriptor);
        generalPurposeBit.encode(buf, 6);
        ZipShort.putShort(zipMethod, buf, 8);
        ZipUtil.toDosTime(this.calendarInstance, ze.getTime(), buf, 10);
        if (phased) {
            ZipLong.putLong(ze.getCrc(), buf, 14);
        } else if (zipMethod == 8 || this.channel != null) {
            System.arraycopy(LZERO, 0, buf, 14, 4);
        } else {
            ZipLong.putLong(ze.getCrc(), buf, 14);
        }
        if (this.hasZip64Extra(this.entry.entry)) {
            ZipLong.ZIP64_MAGIC.putLong(buf, 18);
            ZipLong.ZIP64_MAGIC.putLong(buf, 22);
        } else if (phased) {
            ZipLong.putLong(ze.getCompressedSize(), buf, 18);
            ZipLong.putLong(ze.getSize(), buf, 22);
        } else if (zipMethod == 8 || this.channel != null) {
            System.arraycopy(LZERO, 0, buf, 18, 4);
            System.arraycopy(LZERO, 0, buf, 22, 4);
        } else {
            ZipLong.putLong(ze.getSize(), buf, 18);
            ZipLong.putLong(ze.getSize(), buf, 22);
        }
        ZipShort.putShort(nameLen, buf, 26);
        ZipShort.putShort(extra.length, buf, 28);
        System.arraycopy(name.array(), name.arrayOffset(), buf, 30, nameLen);
        System.arraycopy(extra, 0, buf, 30 + nameLen, extra.length);
        return buf;
    }

    private void addUnicodeExtraFields(ZipArchiveEntry ze, boolean encodable, ByteBuffer name) throws IOException {
        String comm;
        if (this.createUnicodeExtraFields == UnicodeExtraFieldPolicy.ALWAYS || !encodable) {
            ze.addExtraField(new UnicodePathExtraField(ze.getName(), name.array(), name.arrayOffset(), name.limit() - name.position()));
        }
        if ((comm = ze.getComment()) != null && !"".equals(comm)) {
            boolean commentEncodable = this.zipEncoding.canEncode(comm);
            if (this.createUnicodeExtraFields == UnicodeExtraFieldPolicy.ALWAYS || !commentEncodable) {
                ByteBuffer commentB = this.getEntryEncoding(ze).encode(comm);
                ze.addExtraField(new UnicodeCommentExtraField(comm, commentB.array(), commentB.arrayOffset(), commentB.limit() - commentB.position()));
            }
        }
    }

    protected void writeDataDescriptor(ZipArchiveEntry ze) throws IOException {
        if (!this.usesDataDescriptor(ze.getMethod(), false)) {
            return;
        }
        this.writeCounted(DD_SIG);
        this.writeCounted(ZipLong.getBytes(ze.getCrc()));
        if (!this.hasZip64Extra(ze)) {
            this.writeCounted(ZipLong.getBytes(ze.getCompressedSize()));
            this.writeCounted(ZipLong.getBytes(ze.getSize()));
        } else {
            this.writeCounted(ZipEightByteInteger.getBytes(ze.getCompressedSize()));
            this.writeCounted(ZipEightByteInteger.getBytes(ze.getSize()));
        }
    }

    protected void writeCentralFileHeader(ZipArchiveEntry ze) throws IOException {
        byte[] centralFileHeader = this.createCentralFileHeader(ze);
        this.writeCounted(centralFileHeader);
    }

    private byte[] createCentralFileHeader(ZipArchiveEntry ze) throws IOException {
        boolean needsZip64Extra;
        EntryMetaData entryMetaData = this.metaData.get(ze);
        boolean bl = needsZip64Extra = this.hasZip64Extra(ze) || ze.getCompressedSize() >= 0xFFFFFFFFL || ze.getSize() >= 0xFFFFFFFFL || entryMetaData.offset >= 0xFFFFFFFFL || this.zip64Mode == Zip64Mode.Always;
        if (needsZip64Extra && this.zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException("Archive's size exceeds the limit of 4GByte.");
        }
        this.handleZip64Extra(ze, entryMetaData.offset, needsZip64Extra);
        return this.createCentralFileHeader(ze, this.getName(ze), entryMetaData, needsZip64Extra);
    }

    private byte[] createCentralFileHeader(ZipArchiveEntry ze, ByteBuffer name, EntryMetaData entryMetaData, boolean needsZip64Extra) throws IOException {
        byte[] extra = ze.getCentralDirectoryExtra();
        String comm = ze.getComment();
        if (comm == null) {
            comm = "";
        }
        ByteBuffer commentB = this.getEntryEncoding(ze).encode(comm);
        int nameLen = name.limit() - name.position();
        int commentLen = commentB.limit() - commentB.position();
        int len = 46 + nameLen + extra.length + commentLen;
        byte[] buf = new byte[len];
        System.arraycopy(CFH_SIG, 0, buf, 0, 4);
        ZipShort.putShort(ze.getPlatform() << 8 | (!this.hasUsedZip64 ? 20 : 45), buf, 4);
        int zipMethod = ze.getMethod();
        boolean encodable = this.zipEncoding.canEncode(ze.getName());
        ZipShort.putShort(this.versionNeededToExtract(zipMethod, needsZip64Extra, entryMetaData.usesDataDescriptor), buf, 6);
        this.getGeneralPurposeBits(!encodable && this.fallbackToUTF8, entryMetaData.usesDataDescriptor).encode(buf, 8);
        ZipShort.putShort(zipMethod, buf, 10);
        ZipUtil.toDosTime(this.calendarInstance, ze.getTime(), buf, 12);
        ZipLong.putLong(ze.getCrc(), buf, 16);
        if (ze.getCompressedSize() >= 0xFFFFFFFFL || ze.getSize() >= 0xFFFFFFFFL || this.zip64Mode == Zip64Mode.Always) {
            ZipLong.ZIP64_MAGIC.putLong(buf, 20);
            ZipLong.ZIP64_MAGIC.putLong(buf, 24);
        } else {
            ZipLong.putLong(ze.getCompressedSize(), buf, 20);
            ZipLong.putLong(ze.getSize(), buf, 24);
        }
        ZipShort.putShort(nameLen, buf, 28);
        ZipShort.putShort(extra.length, buf, 30);
        ZipShort.putShort(commentLen, buf, 32);
        System.arraycopy(ZERO, 0, buf, 34, 2);
        ZipShort.putShort(ze.getInternalAttributes(), buf, 36);
        ZipLong.putLong(ze.getExternalAttributes(), buf, 38);
        if (entryMetaData.offset >= 0xFFFFFFFFL || this.zip64Mode == Zip64Mode.Always) {
            ZipLong.putLong(0xFFFFFFFFL, buf, 42);
        } else {
            ZipLong.putLong(Math.min(entryMetaData.offset, 0xFFFFFFFFL), buf, 42);
        }
        System.arraycopy(name.array(), name.arrayOffset(), buf, 46, nameLen);
        int extraStart = 46 + nameLen;
        System.arraycopy(extra, 0, buf, extraStart, extra.length);
        int commentStart = extraStart + extra.length;
        System.arraycopy(commentB.array(), commentB.arrayOffset(), buf, commentStart, commentLen);
        return buf;
    }

    private void handleZip64Extra(ZipArchiveEntry ze, long lfhOffset, boolean needsZip64Extra) {
        if (needsZip64Extra) {
            Zip64ExtendedInformationExtraField z64 = this.getZip64Extra(ze);
            if (ze.getCompressedSize() >= 0xFFFFFFFFL || ze.getSize() >= 0xFFFFFFFFL || this.zip64Mode == Zip64Mode.Always) {
                z64.setCompressedSize(new ZipEightByteInteger(ze.getCompressedSize()));
                z64.setSize(new ZipEightByteInteger(ze.getSize()));
            } else {
                z64.setCompressedSize(null);
                z64.setSize(null);
            }
            if (lfhOffset >= 0xFFFFFFFFL || this.zip64Mode == Zip64Mode.Always) {
                z64.setRelativeHeaderOffset(new ZipEightByteInteger(lfhOffset));
            }
            ze.setExtra();
        }
    }

    protected void writeCentralDirectoryEnd() throws IOException {
        this.writeCounted(EOCD_SIG);
        this.writeCounted(ZERO);
        this.writeCounted(ZERO);
        int numberOfEntries = this.entries.size();
        if (numberOfEntries > 65535 && this.zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException("Archive contains more than 65535 entries.");
        }
        if (this.cdOffset > 0xFFFFFFFFL && this.zip64Mode == Zip64Mode.Never) {
            throw new Zip64RequiredException("Archive's size exceeds the limit of 4GByte.");
        }
        byte[] num = ZipShort.getBytes(Math.min(numberOfEntries, 65535));
        this.writeCounted(num);
        this.writeCounted(num);
        this.writeCounted(ZipLong.getBytes(Math.min(this.cdLength, 0xFFFFFFFFL)));
        this.writeCounted(ZipLong.getBytes(Math.min(this.cdOffset, 0xFFFFFFFFL)));
        ByteBuffer data = this.zipEncoding.encode(this.comment);
        int dataLen = data.limit() - data.position();
        this.writeCounted(ZipShort.getBytes(dataLen));
        this.streamCompressor.writeCounted(data.array(), data.arrayOffset(), dataLen);
    }

    protected void writeZip64CentralDirectory() throws IOException {
        if (this.zip64Mode == Zip64Mode.Never) {
            return;
        }
        if (!(this.hasUsedZip64 || this.cdOffset < 0xFFFFFFFFL && this.cdLength < 0xFFFFFFFFL && this.entries.size() < 65535)) {
            this.hasUsedZip64 = true;
        }
        if (!this.hasUsedZip64) {
            return;
        }
        long offset = this.streamCompressor.getTotalBytesWritten();
        this.writeOut(ZIP64_EOCD_SIG);
        this.writeOut(ZipEightByteInteger.getBytes(44L));
        this.writeOut(ZipShort.getBytes(45));
        this.writeOut(ZipShort.getBytes(45));
        this.writeOut(LZERO);
        this.writeOut(LZERO);
        byte[] num = ZipEightByteInteger.getBytes(this.entries.size());
        this.writeOut(num);
        this.writeOut(num);
        this.writeOut(ZipEightByteInteger.getBytes(this.cdLength));
        this.writeOut(ZipEightByteInteger.getBytes(this.cdOffset));
        this.writeOut(ZIP64_EOCD_LOC_SIG);
        this.writeOut(LZERO);
        this.writeOut(ZipEightByteInteger.getBytes(offset));
        this.writeOut(ONE);
    }

    protected final void writeOut(byte[] data) throws IOException {
        this.streamCompressor.writeOut(data, 0, data.length);
    }

    protected final void writeOut(byte[] data, int offset, int length) throws IOException {
        this.streamCompressor.writeOut(data, offset, length);
    }

    private GeneralPurposeBit getGeneralPurposeBits(boolean utfFallback, boolean usesDataDescriptor) {
        GeneralPurposeBit b = new GeneralPurposeBit();
        b.useUTF8ForNames(this.useUTF8Flag || utfFallback);
        if (usesDataDescriptor) {
            b.useDataDescriptor(true);
        }
        return b;
    }

    private int versionNeededToExtract(int zipMethod, boolean zip64, boolean usedDataDescriptor) {
        if (zip64) {
            return 45;
        }
        if (usedDataDescriptor) {
            return 20;
        }
        return this.versionNeededToExtractMethod(zipMethod);
    }

    private boolean usesDataDescriptor(int zipMethod, boolean phased) {
        return !phased && zipMethod == 8 && this.channel == null;
    }

    private int versionNeededToExtractMethod(int zipMethod) {
        return zipMethod == 8 ? 20 : 10;
    }

    @Override
    public ArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new ZipArchiveEntry(inputFile, entryName);
    }

    private Zip64ExtendedInformationExtraField getZip64Extra(ZipArchiveEntry ze) {
        if (this.entry != null) {
            this.entry.causedUseOfZip64 = !this.hasUsedZip64;
        }
        this.hasUsedZip64 = true;
        Zip64ExtendedInformationExtraField z64 = (Zip64ExtendedInformationExtraField)ze.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (z64 == null) {
            z64 = new Zip64ExtendedInformationExtraField();
        }
        ze.addAsFirstExtraField(z64);
        return z64;
    }

    private boolean hasZip64Extra(ZipArchiveEntry ze) {
        return ze.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID) != null;
    }

    private Zip64Mode getEffectiveZip64Mode(ZipArchiveEntry ze) {
        if (this.zip64Mode != Zip64Mode.AsNeeded || this.channel != null || ze.getMethod() != 8 || ze.getSize() != -1L) {
            return this.zip64Mode;
        }
        return Zip64Mode.Never;
    }

    private ZipEncoding getEntryEncoding(ZipArchiveEntry ze) {
        boolean encodable = this.zipEncoding.canEncode(ze.getName());
        return !encodable && this.fallbackToUTF8 ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
    }

    private ByteBuffer getName(ZipArchiveEntry ze) throws IOException {
        return this.getEntryEncoding(ze).encode(ze.getName());
    }

    void destroy() throws IOException {
        try {
            if (this.channel != null) {
                this.channel.close();
            }
        } finally {
            if (this.out != null) {
                this.out.close();
            }
        }
    }

    private static final class EntryMetaData {
        private final long offset;
        private final boolean usesDataDescriptor;

        private EntryMetaData(long offset, boolean usesDataDescriptor) {
            this.offset = offset;
            this.usesDataDescriptor = usesDataDescriptor;
        }
    }

    private static final class CurrentEntry {
        private final ZipArchiveEntry entry;
        private long localDataStart = 0L;
        private long dataStart = 0L;
        private long bytesRead = 0L;
        private boolean causedUseOfZip64 = false;
        private boolean hasWritten;

        private CurrentEntry(ZipArchiveEntry entry) {
            this.entry = entry;
        }
    }

    public static final class UnicodeExtraFieldPolicy {
        public static final UnicodeExtraFieldPolicy ALWAYS = new UnicodeExtraFieldPolicy("always");
        public static final UnicodeExtraFieldPolicy NEVER = new UnicodeExtraFieldPolicy("never");
        public static final UnicodeExtraFieldPolicy NOT_ENCODEABLE = new UnicodeExtraFieldPolicy("not encodeable");
        private final String name;

        private UnicodeExtraFieldPolicy(String n) {
            this.name = n;
        }

        public String toString() {
            return this.name;
        }
    }
}

