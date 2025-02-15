/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.arj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.arj.ArjArchiveEntry;
import org.apache.commons.compress.archivers.arj.LocalFileHeader;
import org.apache.commons.compress.archivers.arj.MainHeader;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.CRC32VerifyingInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class ArjArchiveInputStream
extends ArchiveInputStream {
    private static final int ARJ_MAGIC_1 = 96;
    private static final int ARJ_MAGIC_2 = 234;
    private final DataInputStream in;
    private final String charsetName;
    private final MainHeader mainHeader;
    private LocalFileHeader currentLocalFileHeader = null;
    private InputStream currentInputStream = null;

    public ArjArchiveInputStream(InputStream inputStream, String charsetName) throws ArchiveException {
        this.in = new DataInputStream(inputStream);
        this.charsetName = charsetName;
        try {
            this.mainHeader = this.readMainHeader();
            if ((this.mainHeader.arjFlags & 1) != 0) {
                throw new ArchiveException("Encrypted ARJ files are unsupported");
            }
            if ((this.mainHeader.arjFlags & 4) != 0) {
                throw new ArchiveException("Multi-volume ARJ files are unsupported");
            }
        } catch (IOException ioException) {
            throw new ArchiveException(ioException.getMessage(), ioException);
        }
    }

    public ArjArchiveInputStream(InputStream inputStream) throws ArchiveException {
        this(inputStream, "CP437");
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

    private int read8(DataInputStream dataIn) throws IOException {
        int value = dataIn.readUnsignedByte();
        this.count(1);
        return value;
    }

    private int read16(DataInputStream dataIn) throws IOException {
        int value = dataIn.readUnsignedShort();
        this.count(2);
        return Integer.reverseBytes(value) >>> 16;
    }

    private int read32(DataInputStream dataIn) throws IOException {
        int value = dataIn.readInt();
        this.count(4);
        return Integer.reverseBytes(value);
    }

    private String readString(DataInputStream dataIn) throws IOException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();){
            int nextByte;
            while ((nextByte = dataIn.readUnsignedByte()) != 0) {
                buffer.write(nextByte);
            }
            if (this.charsetName != null) {
                String string = new String(buffer.toByteArray(), this.charsetName);
                return string;
            }
            String string = new String(buffer.toByteArray());
            return string;
        }
    }

    private void readFully(DataInputStream dataIn, byte[] b) throws IOException {
        dataIn.readFully(b);
        this.count(b.length);
    }

    private byte[] readHeader() throws IOException {
        boolean found = false;
        byte[] basicHeaderBytes = null;
        do {
            int first = 0;
            int second = this.read8(this.in);
            do {
                first = second;
                second = this.read8(this.in);
            } while (first != 96 && second != 234);
            int basicHeaderSize = this.read16(this.in);
            if (basicHeaderSize == 0) {
                return null;
            }
            if (basicHeaderSize > 2600) continue;
            basicHeaderBytes = new byte[basicHeaderSize];
            this.readFully(this.in, basicHeaderBytes);
            long basicHeaderCrc32 = (long)this.read32(this.in) & 0xFFFFFFFFL;
            CRC32 crc32 = new CRC32();
            crc32.update(basicHeaderBytes);
            if (basicHeaderCrc32 != crc32.getValue()) continue;
            found = true;
        } while (!found);
        return basicHeaderBytes;
    }

    private MainHeader readMainHeader() throws IOException {
        byte[] basicHeaderBytes = this.readHeader();
        if (basicHeaderBytes == null) {
            throw new IOException("Archive ends without any headers");
        }
        DataInputStream basicHeader = new DataInputStream(new ByteArrayInputStream(basicHeaderBytes));
        int firstHeaderSize = basicHeader.readUnsignedByte();
        byte[] firstHeaderBytes = new byte[firstHeaderSize - 1];
        basicHeader.readFully(firstHeaderBytes);
        DataInputStream firstHeader = new DataInputStream(new ByteArrayInputStream(firstHeaderBytes));
        MainHeader hdr = new MainHeader();
        hdr.archiverVersionNumber = firstHeader.readUnsignedByte();
        hdr.minVersionToExtract = firstHeader.readUnsignedByte();
        hdr.hostOS = firstHeader.readUnsignedByte();
        hdr.arjFlags = firstHeader.readUnsignedByte();
        hdr.securityVersion = firstHeader.readUnsignedByte();
        hdr.fileType = firstHeader.readUnsignedByte();
        hdr.reserved = firstHeader.readUnsignedByte();
        hdr.dateTimeCreated = this.read32(firstHeader);
        hdr.dateTimeModified = this.read32(firstHeader);
        hdr.archiveSize = 0xFFFFFFFFL & (long)this.read32(firstHeader);
        hdr.securityEnvelopeFilePosition = this.read32(firstHeader);
        hdr.fileSpecPosition = this.read16(firstHeader);
        hdr.securityEnvelopeLength = this.read16(firstHeader);
        this.pushedBackBytes(20L);
        hdr.encryptionVersion = firstHeader.readUnsignedByte();
        hdr.lastChapter = firstHeader.readUnsignedByte();
        if (firstHeaderSize >= 33) {
            hdr.arjProtectionFactor = firstHeader.readUnsignedByte();
            hdr.arjFlags2 = firstHeader.readUnsignedByte();
            firstHeader.readUnsignedByte();
            firstHeader.readUnsignedByte();
        }
        hdr.name = this.readString(basicHeader);
        hdr.comment = this.readString(basicHeader);
        int extendedHeaderSize = this.read16(this.in);
        if (extendedHeaderSize > 0) {
            hdr.extendedHeaderBytes = new byte[extendedHeaderSize];
            this.readFully(this.in, hdr.extendedHeaderBytes);
            long extendedHeaderCrc32 = 0xFFFFFFFFL & (long)this.read32(this.in);
            CRC32 crc32 = new CRC32();
            crc32.update(hdr.extendedHeaderBytes);
            if (extendedHeaderCrc32 != crc32.getValue()) {
                throw new IOException("Extended header CRC32 verification failure");
            }
        }
        return hdr;
    }

    /*
     * Exception decompiling
     */
    private LocalFileHeader readLocalFileHeader() throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    private void readExtraData(int firstHeaderSize, DataInputStream firstHeader, LocalFileHeader localFileHeader) throws IOException {
        if (firstHeaderSize >= 33) {
            localFileHeader.extendedFilePosition = this.read32(firstHeader);
            if (firstHeaderSize >= 45) {
                localFileHeader.dateTimeAccessed = this.read32(firstHeader);
                localFileHeader.dateTimeCreated = this.read32(firstHeader);
                localFileHeader.originalSizeEvenForVolumes = this.read32(firstHeader);
                this.pushedBackBytes(12L);
            }
            this.pushedBackBytes(4L);
        }
    }

    public static boolean matches(byte[] signature, int length) {
        return length >= 2 && (0xFF & signature[0]) == 96 && (0xFF & signature[1]) == 234;
    }

    public String getArchiveName() {
        return this.mainHeader.name;
    }

    public String getArchiveComment() {
        return this.mainHeader.comment;
    }

    @Override
    public ArjArchiveEntry getNextEntry() throws IOException {
        if (this.currentInputStream != null) {
            IOUtils.skip(this.currentInputStream, Long.MAX_VALUE);
            this.currentInputStream.close();
            this.currentLocalFileHeader = null;
            this.currentInputStream = null;
        }
        this.currentLocalFileHeader = this.readLocalFileHeader();
        if (this.currentLocalFileHeader != null) {
            this.currentInputStream = new BoundedInputStream(this.in, this.currentLocalFileHeader.compressedSize);
            if (this.currentLocalFileHeader.method == 0) {
                this.currentInputStream = new CRC32VerifyingInputStream(this.currentInputStream, this.currentLocalFileHeader.originalSize, this.currentLocalFileHeader.originalCrc32);
            }
            return new ArjArchiveEntry(this.currentLocalFileHeader);
        }
        this.currentInputStream = null;
        return null;
    }

    @Override
    public boolean canReadEntryData(ArchiveEntry ae) {
        return ae instanceof ArjArchiveEntry && ((ArjArchiveEntry)ae).getMethod() == 0;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.currentLocalFileHeader == null) {
            throw new IllegalStateException("No current arj entry");
        }
        if (this.currentLocalFileHeader.method != 0) {
            throw new IOException("Unsupported compression method " + this.currentLocalFileHeader.method);
        }
        return this.currentInputStream.read(b, off, len);
    }
}

