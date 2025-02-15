/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.sevenz.Archive;
import org.apache.commons.compress.archivers.sevenz.BindPair;
import org.apache.commons.compress.archivers.sevenz.BoundedSeekableByteChannelInputStream;
import org.apache.commons.compress.archivers.sevenz.Coder;
import org.apache.commons.compress.archivers.sevenz.Coders;
import org.apache.commons.compress.archivers.sevenz.Folder;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFileOptions;
import org.apache.commons.compress.archivers.sevenz.SevenZMethod;
import org.apache.commons.compress.archivers.sevenz.SevenZMethodConfiguration;
import org.apache.commons.compress.archivers.sevenz.StartHeader;
import org.apache.commons.compress.archivers.sevenz.StreamMap;
import org.apache.commons.compress.archivers.sevenz.SubStreamsInfo;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.CRC32VerifyingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class SevenZFile
implements Closeable {
    static final int SIGNATURE_HEADER_SIZE = 32;
    private static final String DEFAULT_FILE_NAME = "unknown archive";
    private final String fileName;
    private SeekableByteChannel channel;
    private final Archive archive;
    private int currentEntryIndex = -1;
    private int currentFolderIndex = -1;
    private InputStream currentFolderInputStream = null;
    private byte[] password;
    private final SevenZFileOptions options;
    private long compressedBytesReadFromCurrentEntry;
    private long uncompressedBytesReadFromCurrentEntry;
    private final ArrayList<InputStream> deferredBlockStreams = new ArrayList();
    static final byte[] sevenZSignature = new byte[]{55, 122, -68, -81, 39, 28};
    private static final CharsetEncoder PASSWORD_ENCODER = StandardCharsets.UTF_16LE.newEncoder();

    public SevenZFile(File fileName, char[] password) throws IOException {
        this(fileName, password, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(File fileName, char[] password, SevenZFileOptions options) throws IOException {
        this(Files.newByteChannel(fileName.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), fileName.getAbsolutePath(), SevenZFile.utf16Decode(password), true, options);
    }

    public SevenZFile(File fileName, byte[] password) throws IOException {
        this(Files.newByteChannel(fileName.toPath(), EnumSet.of(StandardOpenOption.READ), new FileAttribute[0]), fileName.getAbsolutePath(), password, true, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel channel) throws IOException {
        this(channel, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel channel, SevenZFileOptions options) throws IOException {
        this(channel, DEFAULT_FILE_NAME, null, options);
    }

    public SevenZFile(SeekableByteChannel channel, char[] password) throws IOException {
        this(channel, password, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel channel, char[] password, SevenZFileOptions options) throws IOException {
        this(channel, DEFAULT_FILE_NAME, password, options);
    }

    public SevenZFile(SeekableByteChannel channel, String fileName, char[] password) throws IOException {
        this(channel, fileName, password, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel channel, String fileName, char[] password, SevenZFileOptions options) throws IOException {
        this(channel, fileName, SevenZFile.utf16Decode(password), false, options);
    }

    public SevenZFile(SeekableByteChannel channel, String fileName) throws IOException {
        this(channel, fileName, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel channel, String fileName, SevenZFileOptions options) throws IOException {
        this(channel, fileName, null, false, options);
    }

    public SevenZFile(SeekableByteChannel channel, byte[] password) throws IOException {
        this(channel, DEFAULT_FILE_NAME, password);
    }

    public SevenZFile(SeekableByteChannel channel, String fileName, byte[] password) throws IOException {
        this(channel, fileName, password, false, SevenZFileOptions.DEFAULT);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private SevenZFile(SeekableByteChannel channel, String filename, byte[] password, boolean closeOnError, SevenZFileOptions options) throws IOException {
        boolean succeeded = false;
        this.channel = channel;
        this.fileName = filename;
        this.options = options;
        try {
            this.archive = this.readHeaders(password);
            this.password = (byte[])(password != null ? Arrays.copyOf(password, password.length) : null);
            succeeded = true;
        } finally {
            if (!succeeded && closeOnError) {
                this.channel.close();
            }
        }
    }

    public SevenZFile(File fileName) throws IOException {
        this(fileName, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(File fileName, SevenZFileOptions options) throws IOException {
        this(fileName, (char[])null, options);
    }

    @Override
    public void close() throws IOException {
        if (this.channel != null) {
            try {
                this.channel.close();
            } finally {
                this.channel = null;
                if (this.password != null) {
                    Arrays.fill(this.password, (byte)0);
                }
                this.password = null;
            }
        }
    }

    public SevenZArchiveEntry getNextEntry() throws IOException {
        if (this.currentEntryIndex >= this.archive.files.length - 1) {
            return null;
        }
        ++this.currentEntryIndex;
        SevenZArchiveEntry entry = this.archive.files[this.currentEntryIndex];
        if (entry.getName() == null && this.options.getUseDefaultNameForUnnamedEntries()) {
            entry.setName(this.getDefaultName());
        }
        this.buildDecodingStream();
        this.compressedBytesReadFromCurrentEntry = 0L;
        this.uncompressedBytesReadFromCurrentEntry = 0L;
        return entry;
    }

    public Iterable<SevenZArchiveEntry> getEntries() {
        return Arrays.asList(this.archive.files);
    }

    private Archive readHeaders(byte[] password) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
        this.readFully(buf);
        byte[] signature = new byte[6];
        buf.get(signature);
        if (!Arrays.equals(signature, sevenZSignature)) {
            throw new IOException("Bad 7z signature");
        }
        byte archiveVersionMajor = buf.get();
        byte archiveVersionMinor = buf.get();
        if (archiveVersionMajor != 0) {
            throw new IOException(String.format("Unsupported 7z version (%d,%d)", archiveVersionMajor, archiveVersionMinor));
        }
        long startHeaderCrc = 0xFFFFFFFFL & (long)buf.getInt();
        StartHeader startHeader = this.readStartHeader(startHeaderCrc);
        SevenZFile.assertFitsIntoInt("nextHeaderSize", startHeader.nextHeaderSize);
        int nextHeaderSizeInt = (int)startHeader.nextHeaderSize;
        this.channel.position(32L + startHeader.nextHeaderOffset);
        buf = ByteBuffer.allocate(nextHeaderSizeInt).order(ByteOrder.LITTLE_ENDIAN);
        this.readFully(buf);
        CRC32 crc = new CRC32();
        crc.update(buf.array());
        if (startHeader.nextHeaderCrc != crc.getValue()) {
            throw new IOException("NextHeader CRC mismatch");
        }
        Archive archive = new Archive();
        int nid = SevenZFile.getUnsignedByte(buf);
        if (nid == 23) {
            buf = this.readEncodedHeader(buf, archive, password);
            archive = new Archive();
            nid = SevenZFile.getUnsignedByte(buf);
        }
        if (nid != 1) {
            throw new IOException("Broken or unsupported archive: no Header");
        }
        this.readHeader(buf, archive);
        return archive;
    }

    private StartHeader readStartHeader(long startHeaderCrc) throws IOException {
        StartHeader startHeader = new StartHeader();
        try (DataInputStream dataInputStream = new DataInputStream(new CRC32VerifyingInputStream((InputStream)new BoundedSeekableByteChannelInputStream(this.channel, 20L), 20L, startHeaderCrc));){
            startHeader.nextHeaderOffset = Long.reverseBytes(dataInputStream.readLong());
            startHeader.nextHeaderSize = Long.reverseBytes(dataInputStream.readLong());
            startHeader.nextHeaderCrc = 0xFFFFFFFFL & (long)Integer.reverseBytes(dataInputStream.readInt());
            StartHeader startHeader2 = startHeader;
            return startHeader2;
        }
    }

    private void readHeader(ByteBuffer header, Archive archive) throws IOException {
        int nid = SevenZFile.getUnsignedByte(header);
        if (nid == 2) {
            this.readArchiveProperties(header);
            nid = SevenZFile.getUnsignedByte(header);
        }
        if (nid == 3) {
            throw new IOException("Additional streams unsupported");
        }
        if (nid == 4) {
            this.readStreamsInfo(header, archive);
            nid = SevenZFile.getUnsignedByte(header);
        }
        if (nid == 5) {
            this.readFilesInfo(header, archive);
            nid = SevenZFile.getUnsignedByte(header);
        }
        if (nid != 0) {
            throw new IOException("Badly terminated header, found " + nid);
        }
    }

    private void readArchiveProperties(ByteBuffer input) throws IOException {
        int nid = SevenZFile.getUnsignedByte(input);
        while (nid != 0) {
            long propertySize = SevenZFile.readUint64(input);
            SevenZFile.assertFitsIntoInt("propertySize", propertySize);
            byte[] property = new byte[(int)propertySize];
            input.get(property);
            nid = SevenZFile.getUnsignedByte(input);
        }
    }

    private ByteBuffer readEncodedHeader(ByteBuffer header, Archive archive, byte[] password) throws IOException {
        this.readStreamsInfo(header, archive);
        Folder folder = archive.folders[0];
        boolean firstPackStreamIndex = false;
        long folderOffset = 32L + archive.packPos + 0L;
        this.channel.position(folderOffset);
        InputStream inputStreamStack = new BoundedSeekableByteChannelInputStream(this.channel, archive.packSizes[0]);
        for (Coder coder : folder.getOrderedCoders()) {
            if (coder.numInStreams != 1L || coder.numOutStreams != 1L) {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
            inputStreamStack = Coders.addDecoder(this.fileName, inputStreamStack, folder.getUnpackSizeForCoder(coder), coder, password, this.options.getMaxMemoryLimitInKb());
        }
        if (folder.hasCrc) {
            inputStreamStack = new CRC32VerifyingInputStream(inputStreamStack, folder.getUnpackSize(), folder.crc);
        }
        SevenZFile.assertFitsIntoInt("unpackSize", folder.getUnpackSize());
        byte[] nextHeader = new byte[(int)folder.getUnpackSize()];
        try (DataInputStream nextHeaderInputStream = new DataInputStream(inputStreamStack);){
            nextHeaderInputStream.readFully(nextHeader);
        }
        return ByteBuffer.wrap(nextHeader).order(ByteOrder.LITTLE_ENDIAN);
    }

    private void readStreamsInfo(ByteBuffer header, Archive archive) throws IOException {
        int nid = SevenZFile.getUnsignedByte(header);
        if (nid == 6) {
            this.readPackInfo(header, archive);
            nid = SevenZFile.getUnsignedByte(header);
        }
        if (nid == 7) {
            this.readUnpackInfo(header, archive);
            nid = SevenZFile.getUnsignedByte(header);
        } else {
            archive.folders = new Folder[0];
        }
        if (nid == 8) {
            this.readSubStreamsInfo(header, archive);
            nid = SevenZFile.getUnsignedByte(header);
        }
        if (nid != 0) {
            throw new IOException("Badly terminated StreamsInfo");
        }
    }

    private void readPackInfo(ByteBuffer header, Archive archive) throws IOException {
        int i;
        archive.packPos = SevenZFile.readUint64(header);
        long numPackStreams = SevenZFile.readUint64(header);
        SevenZFile.assertFitsIntoInt("numPackStreams", numPackStreams);
        int numPackStreamsInt = (int)numPackStreams;
        int nid = SevenZFile.getUnsignedByte(header);
        if (nid == 9) {
            archive.packSizes = new long[numPackStreamsInt];
            for (i = 0; i < archive.packSizes.length; ++i) {
                archive.packSizes[i] = SevenZFile.readUint64(header);
            }
            nid = SevenZFile.getUnsignedByte(header);
        }
        if (nid == 10) {
            archive.packCrcsDefined = this.readAllOrBits(header, numPackStreamsInt);
            archive.packCrcs = new long[numPackStreamsInt];
            for (i = 0; i < numPackStreamsInt; ++i) {
                if (!archive.packCrcsDefined.get(i)) continue;
                archive.packCrcs[i] = 0xFFFFFFFFL & (long)header.getInt();
            }
            nid = SevenZFile.getUnsignedByte(header);
        }
        if (nid != 0) {
            throw new IOException("Badly terminated PackInfo (" + nid + ")");
        }
    }

    private void readUnpackInfo(ByteBuffer header, Archive archive) throws IOException {
        int nid = SevenZFile.getUnsignedByte(header);
        if (nid != 11) {
            throw new IOException("Expected kFolder, got " + nid);
        }
        long numFolders = SevenZFile.readUint64(header);
        SevenZFile.assertFitsIntoInt("numFolders", numFolders);
        int numFoldersInt = (int)numFolders;
        Folder[] folders = new Folder[numFoldersInt];
        archive.folders = folders;
        int external = SevenZFile.getUnsignedByte(header);
        if (external != 0) {
            throw new IOException("External unsupported");
        }
        for (int i = 0; i < numFoldersInt; ++i) {
            folders[i] = this.readFolder(header);
        }
        nid = SevenZFile.getUnsignedByte(header);
        if (nid != 12) {
            throw new IOException("Expected kCodersUnpackSize, got " + nid);
        }
        for (Folder folder : folders) {
            SevenZFile.assertFitsIntoInt("totalOutputStreams", folder.totalOutputStreams);
            folder.unpackSizes = new long[(int)folder.totalOutputStreams];
            int i = 0;
            while ((long)i < folder.totalOutputStreams) {
                folder.unpackSizes[i] = SevenZFile.readUint64(header);
                ++i;
            }
        }
        nid = SevenZFile.getUnsignedByte(header);
        if (nid == 10) {
            BitSet crcsDefined = this.readAllOrBits(header, numFoldersInt);
            for (int i = 0; i < numFoldersInt; ++i) {
                if (crcsDefined.get(i)) {
                    folders[i].hasCrc = true;
                    folders[i].crc = 0xFFFFFFFFL & (long)header.getInt();
                    continue;
                }
                folders[i].hasCrc = false;
            }
            nid = SevenZFile.getUnsignedByte(header);
        }
        if (nid != 0) {
            throw new IOException("Badly terminated UnpackInfo");
        }
    }

    private void readSubStreamsInfo(ByteBuffer header, Archive archive) throws IOException {
        for (Folder folder : archive.folders) {
            folder.numUnpackSubStreams = 1;
        }
        int totalUnpackStreams = archive.folders.length;
        int nid = SevenZFile.getUnsignedByte(header);
        if (nid == 13) {
            totalUnpackStreams = 0;
            for (Folder folder : archive.folders) {
                long numStreams = SevenZFile.readUint64(header);
                SevenZFile.assertFitsIntoInt("numStreams", numStreams);
                folder.numUnpackSubStreams = (int)numStreams;
                totalUnpackStreams = (int)((long)totalUnpackStreams + numStreams);
            }
            nid = SevenZFile.getUnsignedByte(header);
        }
        SubStreamsInfo subStreamsInfo = new SubStreamsInfo();
        subStreamsInfo.unpackSizes = new long[totalUnpackStreams];
        subStreamsInfo.hasCrc = new BitSet(totalUnpackStreams);
        subStreamsInfo.crcs = new long[totalUnpackStreams];
        int nextUnpackStream = 0;
        for (Folder folder : archive.folders) {
            if (folder.numUnpackSubStreams == 0) continue;
            long sum = 0L;
            if (nid == 9) {
                for (int i = 0; i < folder.numUnpackSubStreams - 1; ++i) {
                    long size = SevenZFile.readUint64(header);
                    subStreamsInfo.unpackSizes[nextUnpackStream++] = size;
                    sum += size;
                }
            }
            subStreamsInfo.unpackSizes[nextUnpackStream++] = folder.getUnpackSize() - sum;
        }
        if (nid == 9) {
            nid = SevenZFile.getUnsignedByte(header);
        }
        int numDigests = 0;
        for (Folder folder : archive.folders) {
            if (folder.numUnpackSubStreams == 1 && folder.hasCrc) continue;
            numDigests += folder.numUnpackSubStreams;
        }
        if (nid == 10) {
            BitSet hasMissingCrc = this.readAllOrBits(header, numDigests);
            long[] missingCrcs = new long[numDigests];
            for (int i = 0; i < numDigests; ++i) {
                if (!hasMissingCrc.get(i)) continue;
                missingCrcs[i] = 0xFFFFFFFFL & (long)header.getInt();
            }
            int nextCrc = 0;
            int nextMissingCrc = 0;
            for (Folder folder : archive.folders) {
                if (folder.numUnpackSubStreams == 1 && folder.hasCrc) {
                    subStreamsInfo.hasCrc.set(nextCrc, true);
                    subStreamsInfo.crcs[nextCrc] = folder.crc;
                    ++nextCrc;
                    continue;
                }
                for (int i = 0; i < folder.numUnpackSubStreams; ++i) {
                    subStreamsInfo.hasCrc.set(nextCrc, hasMissingCrc.get(nextMissingCrc));
                    subStreamsInfo.crcs[nextCrc] = missingCrcs[nextMissingCrc];
                    ++nextCrc;
                    ++nextMissingCrc;
                }
            }
            nid = SevenZFile.getUnsignedByte(header);
        }
        if (nid != 0) {
            throw new IOException("Badly terminated SubStreamsInfo");
        }
        archive.subStreamsInfo = subStreamsInfo;
    }

    private Folder readFolder(ByteBuffer header) throws IOException {
        Folder folder = new Folder();
        long numCoders = SevenZFile.readUint64(header);
        SevenZFile.assertFitsIntoInt("numCoders", numCoders);
        Coder[] coders = new Coder[(int)numCoders];
        long totalInStreams = 0L;
        long totalOutStreams = 0L;
        for (int i = 0; i < coders.length; ++i) {
            coders[i] = new Coder();
            int bits = SevenZFile.getUnsignedByte(header);
            int idSize = bits & 0xF;
            boolean isSimple = (bits & 0x10) == 0;
            boolean hasAttributes = (bits & 0x20) != 0;
            boolean moreAlternativeMethods = (bits & 0x80) != 0;
            coders[i].decompressionMethodId = new byte[idSize];
            header.get(coders[i].decompressionMethodId);
            if (isSimple) {
                coders[i].numInStreams = 1L;
                coders[i].numOutStreams = 1L;
            } else {
                coders[i].numInStreams = SevenZFile.readUint64(header);
                coders[i].numOutStreams = SevenZFile.readUint64(header);
            }
            totalInStreams += coders[i].numInStreams;
            totalOutStreams += coders[i].numOutStreams;
            if (hasAttributes) {
                long propertiesSize = SevenZFile.readUint64(header);
                SevenZFile.assertFitsIntoInt("propertiesSize", propertiesSize);
                coders[i].properties = new byte[(int)propertiesSize];
                header.get(coders[i].properties);
            }
            if (!moreAlternativeMethods) continue;
            throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
        }
        folder.coders = coders;
        SevenZFile.assertFitsIntoInt("totalInStreams", totalInStreams);
        folder.totalInputStreams = totalInStreams;
        SevenZFile.assertFitsIntoInt("totalOutStreams", totalOutStreams);
        folder.totalOutputStreams = totalOutStreams;
        if (totalOutStreams == 0L) {
            throw new IOException("Total output streams can't be 0");
        }
        long numBindPairs = totalOutStreams - 1L;
        SevenZFile.assertFitsIntoInt("numBindPairs", numBindPairs);
        BindPair[] bindPairs = new BindPair[(int)numBindPairs];
        for (int i = 0; i < bindPairs.length; ++i) {
            bindPairs[i] = new BindPair();
            bindPairs[i].inIndex = SevenZFile.readUint64(header);
            bindPairs[i].outIndex = SevenZFile.readUint64(header);
        }
        folder.bindPairs = bindPairs;
        if (totalInStreams < numBindPairs) {
            throw new IOException("Total input streams can't be less than the number of bind pairs");
        }
        long numPackedStreams = totalInStreams - numBindPairs;
        SevenZFile.assertFitsIntoInt("numPackedStreams", numPackedStreams);
        long[] packedStreams = new long[(int)numPackedStreams];
        if (numPackedStreams == 1L) {
            int i;
            for (i = 0; i < (int)totalInStreams && folder.findBindPairForInStream(i) >= 0; ++i) {
            }
            if (i == (int)totalInStreams) {
                throw new IOException("Couldn't find stream's bind pair index");
            }
            packedStreams[0] = i;
        } else {
            for (int i = 0; i < (int)numPackedStreams; ++i) {
                packedStreams[i] = SevenZFile.readUint64(header);
            }
        }
        folder.packedStreams = packedStreams;
        return folder;
    }

    private BitSet readAllOrBits(ByteBuffer header, int size) throws IOException {
        BitSet bits;
        int areAllDefined = SevenZFile.getUnsignedByte(header);
        if (areAllDefined != 0) {
            bits = new BitSet(size);
            for (int i = 0; i < size; ++i) {
                bits.set(i, true);
            }
        } else {
            bits = this.readBits(header, size);
        }
        return bits;
    }

    private BitSet readBits(ByteBuffer header, int size) throws IOException {
        BitSet bits = new BitSet(size);
        int mask = 0;
        int cache = 0;
        for (int i = 0; i < size; ++i) {
            if (mask == 0) {
                mask = 128;
                cache = SevenZFile.getUnsignedByte(header);
            }
            bits.set(i, (cache & mask) != 0);
            mask >>>= 1;
        }
        return bits;
    }

    private void readFilesInfo(ByteBuffer header, Archive archive) throws IOException {
        int propertyType;
        long numFiles = SevenZFile.readUint64(header);
        SevenZFile.assertFitsIntoInt("numFiles", numFiles);
        SevenZArchiveEntry[] files = new SevenZArchiveEntry[(int)numFiles];
        for (int i = 0; i < files.length; ++i) {
            files[i] = new SevenZArchiveEntry();
        }
        BitSet isEmptyStream = null;
        BitSet isEmptyFile = null;
        BitSet isAnti = null;
        block13: while ((propertyType = SevenZFile.getUnsignedByte(header)) != 0) {
            long size = SevenZFile.readUint64(header);
            switch (propertyType) {
                case 14: {
                    isEmptyStream = this.readBits(header, files.length);
                    break;
                }
                case 15: {
                    if (isEmptyStream == null) {
                        throw new IOException("Header format error: kEmptyStream must appear before kEmptyFile");
                    }
                    isEmptyFile = this.readBits(header, isEmptyStream.cardinality());
                    break;
                }
                case 16: {
                    if (isEmptyStream == null) {
                        throw new IOException("Header format error: kEmptyStream must appear before kAnti");
                    }
                    isAnti = this.readBits(header, isEmptyStream.cardinality());
                    break;
                }
                case 17: {
                    int external = SevenZFile.getUnsignedByte(header);
                    if (external != 0) {
                        throw new IOException("Not implemented");
                    }
                    if ((size - 1L & 1L) != 0L) {
                        throw new IOException("File names length invalid");
                    }
                    SevenZFile.assertFitsIntoInt("file names length", size - 1L);
                    byte[] names = new byte[(int)(size - 1L)];
                    header.get(names);
                    int nextFile = 0;
                    int nextName = 0;
                    for (int i = 0; i < names.length; i += 2) {
                        if (names[i] != 0 || names[i + 1] != 0) continue;
                        files[nextFile++].setName(new String(names, nextName, i - nextName, "UTF-16LE"));
                        nextName = i + 2;
                    }
                    if (nextName == names.length && nextFile == files.length) continue block13;
                    throw new IOException("Error parsing file names");
                }
                case 18: {
                    int i;
                    BitSet timesDefined = this.readAllOrBits(header, files.length);
                    int external = SevenZFile.getUnsignedByte(header);
                    if (external != 0) {
                        throw new IOException("Unimplemented");
                    }
                    for (i = 0; i < files.length; ++i) {
                        files[i].setHasCreationDate(timesDefined.get(i));
                        if (!files[i].getHasCreationDate()) continue;
                        files[i].setCreationDate(header.getLong());
                    }
                    continue block13;
                }
                case 19: {
                    int i;
                    BitSet timesDefined = this.readAllOrBits(header, files.length);
                    int external = SevenZFile.getUnsignedByte(header);
                    if (external != 0) {
                        throw new IOException("Unimplemented");
                    }
                    for (i = 0; i < files.length; ++i) {
                        files[i].setHasAccessDate(timesDefined.get(i));
                        if (!files[i].getHasAccessDate()) continue;
                        files[i].setAccessDate(header.getLong());
                    }
                    continue block13;
                }
                case 20: {
                    int i;
                    BitSet timesDefined = this.readAllOrBits(header, files.length);
                    int external = SevenZFile.getUnsignedByte(header);
                    if (external != 0) {
                        throw new IOException("Unimplemented");
                    }
                    for (i = 0; i < files.length; ++i) {
                        files[i].setHasLastModifiedDate(timesDefined.get(i));
                        if (!files[i].getHasLastModifiedDate()) continue;
                        files[i].setLastModifiedDate(header.getLong());
                    }
                    continue block13;
                }
                case 21: {
                    int i;
                    BitSet attributesDefined = this.readAllOrBits(header, files.length);
                    int external = SevenZFile.getUnsignedByte(header);
                    if (external != 0) {
                        throw new IOException("Unimplemented");
                    }
                    for (i = 0; i < files.length; ++i) {
                        files[i].setHasWindowsAttributes(attributesDefined.get(i));
                        if (!files[i].getHasWindowsAttributes()) continue;
                        files[i].setWindowsAttributes(header.getInt());
                    }
                    continue block13;
                }
                case 24: {
                    throw new IOException("kStartPos is unsupported, please report");
                }
                case 25: {
                    if (SevenZFile.skipBytesFully(header, size) >= size) continue block13;
                    throw new IOException("Incomplete kDummy property");
                }
                default: {
                    if (SevenZFile.skipBytesFully(header, size) >= size) continue block13;
                    throw new IOException("Incomplete property of type " + propertyType);
                }
            }
        }
        int nonEmptyFileCounter = 0;
        int emptyFileCounter = 0;
        for (int i = 0; i < files.length; ++i) {
            files[i].setHasStream(isEmptyStream == null || !isEmptyStream.get(i));
            if (files[i].hasStream()) {
                files[i].setDirectory(false);
                files[i].setAntiItem(false);
                files[i].setHasCrc(archive.subStreamsInfo.hasCrc.get(nonEmptyFileCounter));
                files[i].setCrcValue(archive.subStreamsInfo.crcs[nonEmptyFileCounter]);
                files[i].setSize(archive.subStreamsInfo.unpackSizes[nonEmptyFileCounter]);
                ++nonEmptyFileCounter;
                continue;
            }
            files[i].setDirectory(isEmptyFile == null || !isEmptyFile.get(emptyFileCounter));
            files[i].setAntiItem(isAnti != null && isAnti.get(emptyFileCounter));
            files[i].setHasCrc(false);
            files[i].setSize(0L);
            ++emptyFileCounter;
        }
        archive.files = files;
        this.calculateStreamMap(archive);
    }

    private void calculateStreamMap(Archive archive) throws IOException {
        StreamMap streamMap = new StreamMap();
        int nextFolderPackStreamIndex = 0;
        int numFolders = archive.folders != null ? archive.folders.length : 0;
        streamMap.folderFirstPackStreamIndex = new int[numFolders];
        for (int i = 0; i < numFolders; ++i) {
            streamMap.folderFirstPackStreamIndex[i] = nextFolderPackStreamIndex;
            nextFolderPackStreamIndex += archive.folders[i].packedStreams.length;
        }
        long nextPackStreamOffset = 0L;
        int numPackSizes = archive.packSizes != null ? archive.packSizes.length : 0;
        streamMap.packStreamOffsets = new long[numPackSizes];
        for (int i = 0; i < numPackSizes; ++i) {
            streamMap.packStreamOffsets[i] = nextPackStreamOffset;
            nextPackStreamOffset += archive.packSizes[i];
        }
        streamMap.folderFirstFileIndex = new int[numFolders];
        streamMap.fileFolderIndex = new int[archive.files.length];
        int nextFolderIndex = 0;
        int nextFolderUnpackStreamIndex = 0;
        for (int i = 0; i < archive.files.length; ++i) {
            if (!archive.files[i].hasStream() && nextFolderUnpackStreamIndex == 0) {
                streamMap.fileFolderIndex[i] = -1;
                continue;
            }
            if (nextFolderUnpackStreamIndex == 0) {
                while (nextFolderIndex < archive.folders.length) {
                    streamMap.folderFirstFileIndex[nextFolderIndex] = i;
                    if (archive.folders[nextFolderIndex].numUnpackSubStreams > 0) break;
                    ++nextFolderIndex;
                }
                if (nextFolderIndex >= archive.folders.length) {
                    throw new IOException("Too few folders in archive");
                }
            }
            streamMap.fileFolderIndex[i] = nextFolderIndex;
            if (!archive.files[i].hasStream() || ++nextFolderUnpackStreamIndex < archive.folders[nextFolderIndex].numUnpackSubStreams) continue;
            ++nextFolderIndex;
            nextFolderUnpackStreamIndex = 0;
        }
        archive.streamMap = streamMap;
    }

    private void buildDecodingStream() throws IOException {
        int folderIndex = this.archive.streamMap.fileFolderIndex[this.currentEntryIndex];
        if (folderIndex < 0) {
            this.deferredBlockStreams.clear();
            return;
        }
        SevenZArchiveEntry file = this.archive.files[this.currentEntryIndex];
        if (this.currentFolderIndex == folderIndex) {
            file.setContentMethods(this.archive.files[this.currentEntryIndex - 1].getContentMethods());
        } else {
            this.currentFolderIndex = folderIndex;
            this.deferredBlockStreams.clear();
            if (this.currentFolderInputStream != null) {
                this.currentFolderInputStream.close();
                this.currentFolderInputStream = null;
            }
            Folder folder = this.archive.folders[folderIndex];
            int firstPackStreamIndex = this.archive.streamMap.folderFirstPackStreamIndex[folderIndex];
            long folderOffset = 32L + this.archive.packPos + this.archive.streamMap.packStreamOffsets[firstPackStreamIndex];
            this.currentFolderInputStream = this.buildDecoderStack(folder, folderOffset, firstPackStreamIndex, file);
        }
        InputStream fileStream = new BoundedInputStream(this.currentFolderInputStream, file.getSize());
        if (file.getHasCrc()) {
            fileStream = new CRC32VerifyingInputStream(fileStream, file.getSize(), file.getCrcValue());
        }
        this.deferredBlockStreams.add(fileStream);
    }

    private InputStream buildDecoderStack(Folder folder, long folderOffset, int firstPackStreamIndex, SevenZArchiveEntry entry) throws IOException {
        this.channel.position(folderOffset);
        InputStream inputStreamStack = new FilterInputStream(new BufferedInputStream(new BoundedSeekableByteChannelInputStream(this.channel, this.archive.packSizes[firstPackStreamIndex]))){

            @Override
            public int read() throws IOException {
                int r = this.in.read();
                if (r >= 0) {
                    this.count(1);
                }
                return r;
            }

            @Override
            public int read(byte[] b) throws IOException {
                return this.read(b, 0, b.length);
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                int r = this.in.read(b, off, len);
                if (r >= 0) {
                    this.count(r);
                }
                return r;
            }

            private void count(int c) {
                SevenZFile.this.compressedBytesReadFromCurrentEntry = SevenZFile.this.compressedBytesReadFromCurrentEntry + (long)c;
            }
        };
        LinkedList<SevenZMethodConfiguration> methods = new LinkedList<SevenZMethodConfiguration>();
        for (Coder coder : folder.getOrderedCoders()) {
            if (coder.numInStreams != 1L || coder.numOutStreams != 1L) {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
            SevenZMethod method = SevenZMethod.byId(coder.decompressionMethodId);
            inputStreamStack = Coders.addDecoder(this.fileName, inputStreamStack, folder.getUnpackSizeForCoder(coder), coder, this.password, this.options.getMaxMemoryLimitInKb());
            methods.addFirst(new SevenZMethodConfiguration(method, Coders.findByMethod(method).getOptionsFromCoder(coder, inputStreamStack)));
        }
        entry.setContentMethods(methods);
        if (folder.hasCrc) {
            return new CRC32VerifyingInputStream(inputStreamStack, folder.getUnpackSize(), folder.crc);
        }
        return inputStreamStack;
    }

    public int read() throws IOException {
        int b = this.getCurrentStream().read();
        if (b >= 0) {
            ++this.uncompressedBytesReadFromCurrentEntry;
        }
        return b;
    }

    private InputStream getCurrentStream() throws IOException {
        if (this.archive.files[this.currentEntryIndex].getSize() == 0L) {
            return new ByteArrayInputStream(new byte[0]);
        }
        if (this.deferredBlockStreams.isEmpty()) {
            throw new IllegalStateException("No current 7z entry (call getNextEntry() first).");
        }
        while (this.deferredBlockStreams.size() > 1) {
            try (InputStream stream = this.deferredBlockStreams.remove(0);){
                IOUtils.skip(stream, Long.MAX_VALUE);
            }
            this.compressedBytesReadFromCurrentEntry = 0L;
        }
        return this.deferredBlockStreams.get(0);
    }

    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int cnt = this.getCurrentStream().read(b, off, len);
        if (cnt > 0) {
            this.uncompressedBytesReadFromCurrentEntry += (long)cnt;
        }
        return cnt;
    }

    public InputStreamStatistics getStatisticsForCurrentEntry() {
        return new InputStreamStatistics(){

            @Override
            public long getCompressedCount() {
                return SevenZFile.this.compressedBytesReadFromCurrentEntry;
            }

            @Override
            public long getUncompressedCount() {
                return SevenZFile.this.uncompressedBytesReadFromCurrentEntry;
            }
        };
    }

    private static long readUint64(ByteBuffer in) throws IOException {
        long firstByte = SevenZFile.getUnsignedByte(in);
        int mask = 128;
        long value = 0L;
        for (int i = 0; i < 8; ++i) {
            if ((firstByte & (long)mask) == 0L) {
                return value | (firstByte & (long)(mask - 1)) << 8 * i;
            }
            long nextByte = SevenZFile.getUnsignedByte(in);
            value |= nextByte << 8 * i;
            mask >>>= 1;
        }
        return value;
    }

    private static int getUnsignedByte(ByteBuffer buf) {
        return buf.get() & 0xFF;
    }

    public static boolean matches(byte[] signature, int length) {
        if (length < sevenZSignature.length) {
            return false;
        }
        for (int i = 0; i < sevenZSignature.length; ++i) {
            if (signature[i] == sevenZSignature[i]) continue;
            return false;
        }
        return true;
    }

    private static long skipBytesFully(ByteBuffer input, long bytesToSkip) throws IOException {
        if (bytesToSkip < 1L) {
            return 0L;
        }
        int current = input.position();
        int maxSkip = input.remaining();
        if ((long)maxSkip < bytesToSkip) {
            bytesToSkip = maxSkip;
        }
        input.position(current + (int)bytesToSkip);
        return bytesToSkip;
    }

    private void readFully(ByteBuffer buf) throws IOException {
        buf.rewind();
        IOUtils.readFully(this.channel, buf);
        buf.flip();
    }

    public String toString() {
        return this.archive.toString();
    }

    public String getDefaultName() {
        if (DEFAULT_FILE_NAME.equals(this.fileName) || this.fileName == null) {
            return null;
        }
        String lastSegment = new File(this.fileName).getName();
        int dotPos = lastSegment.lastIndexOf(".");
        if (dotPos > 0) {
            return lastSegment.substring(0, dotPos);
        }
        return lastSegment + "~";
    }

    private static byte[] utf16Decode(char[] chars) throws IOException {
        if (chars == null) {
            return null;
        }
        ByteBuffer encoded = PASSWORD_ENCODER.encode(CharBuffer.wrap(chars));
        if (encoded.hasArray()) {
            return encoded.array();
        }
        byte[] e = new byte[encoded.remaining()];
        encoded.get(e);
        return e;
    }

    private static void assertFitsIntoInt(String what, long value) throws IOException {
        if (value > Integer.MAX_VALUE) {
            throw new IOException("Cannot handle " + what + value);
        }
    }
}

