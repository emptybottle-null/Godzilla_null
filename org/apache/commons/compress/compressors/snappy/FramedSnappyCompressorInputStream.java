/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.snappy;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.snappy.FramedSnappyDialect;
import org.apache.commons.compress.compressors.snappy.PureJavaCrc32C;
import org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class FramedSnappyCompressorInputStream
extends CompressorInputStream
implements InputStreamStatistics {
    static final long MASK_OFFSET = 2726488792L;
    private static final int STREAM_IDENTIFIER_TYPE = 255;
    static final int COMPRESSED_CHUNK_TYPE = 0;
    private static final int UNCOMPRESSED_CHUNK_TYPE = 1;
    private static final int PADDING_CHUNK_TYPE = 254;
    private static final int MIN_UNSKIPPABLE_TYPE = 2;
    private static final int MAX_UNSKIPPABLE_TYPE = 127;
    private static final int MAX_SKIPPABLE_TYPE = 253;
    static final byte[] SZ_SIGNATURE = new byte[]{-1, 6, 0, 0, 115, 78, 97, 80, 112, 89};
    private long unreadBytes;
    private final CountingInputStream countingStream;
    private final PushbackInputStream in;
    private final FramedSnappyDialect dialect;
    private SnappyCompressorInputStream currentCompressedChunk;
    private final byte[] oneByte = new byte[1];
    private boolean endReached;
    private boolean inUncompressedChunk;
    private int uncompressedBytesRemaining;
    private long expectedChecksum = -1L;
    private final int blockSize;
    private final PureJavaCrc32C checksum = new PureJavaCrc32C();
    private final ByteUtils.ByteSupplier supplier = new ByteUtils.ByteSupplier(){

        @Override
        public int getAsByte() throws IOException {
            return FramedSnappyCompressorInputStream.this.readOneByte();
        }
    };

    public FramedSnappyCompressorInputStream(InputStream in) throws IOException {
        this(in, FramedSnappyDialect.STANDARD);
    }

    public FramedSnappyCompressorInputStream(InputStream in, FramedSnappyDialect dialect) throws IOException {
        this(in, 32768, dialect);
    }

    public FramedSnappyCompressorInputStream(InputStream in, int blockSize, FramedSnappyDialect dialect) throws IOException {
        if (blockSize <= 0) {
            throw new IllegalArgumentException("blockSize must be bigger than 0");
        }
        this.countingStream = new CountingInputStream(in);
        this.in = new PushbackInputStream(this.countingStream, 1);
        this.blockSize = blockSize;
        this.dialect = dialect;
        if (dialect.hasStreamIdentifier()) {
            this.readStreamIdentifier();
        }
    }

    @Override
    public int read() throws IOException {
        return this.read(this.oneByte, 0, 1) == -1 ? -1 : this.oneByte[0] & 0xFF;
    }

    @Override
    public void close() throws IOException {
        try {
            if (this.currentCompressedChunk != null) {
                this.currentCompressedChunk.close();
                this.currentCompressedChunk = null;
            }
        } finally {
            this.in.close();
        }
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int read = this.readOnce(b, off, len);
        if (read == -1) {
            this.readNextBlock();
            if (this.endReached) {
                return -1;
            }
            read = this.readOnce(b, off, len);
        }
        return read;
    }

    @Override
    public int available() throws IOException {
        if (this.inUncompressedChunk) {
            return Math.min(this.uncompressedBytesRemaining, this.in.available());
        }
        if (this.currentCompressedChunk != null) {
            return this.currentCompressedChunk.available();
        }
        return 0;
    }

    @Override
    public long getCompressedCount() {
        return this.countingStream.getBytesRead() - this.unreadBytes;
    }

    private int readOnce(byte[] b, int off, int len) throws IOException {
        int read = -1;
        if (this.inUncompressedChunk) {
            int amount = Math.min(this.uncompressedBytesRemaining, len);
            if (amount == 0) {
                return -1;
            }
            read = this.in.read(b, off, amount);
            if (read != -1) {
                this.uncompressedBytesRemaining -= read;
                this.count(read);
            }
        } else if (this.currentCompressedChunk != null) {
            long before = this.currentCompressedChunk.getBytesRead();
            read = this.currentCompressedChunk.read(b, off, len);
            if (read == -1) {
                this.currentCompressedChunk.close();
                this.currentCompressedChunk = null;
            } else {
                this.count(this.currentCompressedChunk.getBytesRead() - before);
            }
        }
        if (read > 0) {
            this.checksum.update(b, off, read);
        }
        return read;
    }

    private void readNextBlock() throws IOException {
        this.verifyLastChecksumAndReset();
        this.inUncompressedChunk = false;
        int type = this.readOneByte();
        if (type == -1) {
            this.endReached = true;
        } else if (type == 255) {
            this.in.unread(type);
            ++this.unreadBytes;
            this.pushedBackBytes(1L);
            this.readStreamIdentifier();
            this.readNextBlock();
        } else if (type == 254 || type > 127 && type <= 253) {
            this.skipBlock();
            this.readNextBlock();
        } else {
            if (type >= 2 && type <= 127) {
                throw new IOException("Unskippable chunk with type " + type + " (hex " + Integer.toHexString(type) + ") detected.");
            }
            if (type == 1) {
                this.inUncompressedChunk = true;
                this.uncompressedBytesRemaining = this.readSize() - 4;
                if (this.uncompressedBytesRemaining < 0) {
                    throw new IOException("Found illegal chunk with negative size");
                }
                this.expectedChecksum = FramedSnappyCompressorInputStream.unmask(this.readCrc());
            } else if (type == 0) {
                boolean expectChecksum = this.dialect.usesChecksumWithCompressedChunks();
                long size = (long)this.readSize() - (expectChecksum ? 4L : 0L);
                if (size < 0L) {
                    throw new IOException("Found illegal chunk with negative size");
                }
                this.expectedChecksum = expectChecksum ? FramedSnappyCompressorInputStream.unmask(this.readCrc()) : -1L;
                this.currentCompressedChunk = new SnappyCompressorInputStream(new BoundedInputStream(this.in, size), this.blockSize);
                this.count(this.currentCompressedChunk.getBytesRead());
            } else {
                throw new IOException("Unknown chunk type " + type + " detected.");
            }
        }
    }

    private long readCrc() throws IOException {
        byte[] b = new byte[4];
        int read = IOUtils.readFully(this.in, b);
        this.count(read);
        if (read != 4) {
            throw new IOException("Premature end of stream");
        }
        return ByteUtils.fromLittleEndian(b);
    }

    static long unmask(long x) {
        x -= 2726488792L;
        return ((x &= 0xFFFFFFFFL) >> 17 | x << 15) & 0xFFFFFFFFL;
    }

    private int readSize() throws IOException {
        return (int)ByteUtils.fromLittleEndian(this.supplier, 3);
    }

    private void skipBlock() throws IOException {
        int size = this.readSize();
        if (size < 0) {
            throw new IOException("Found illegal chunk with negative size");
        }
        long read = IOUtils.skip(this.in, size);
        this.count(read);
        if (read != (long)size) {
            throw new IOException("Premature end of stream");
        }
    }

    private void readStreamIdentifier() throws IOException {
        byte[] b = new byte[10];
        int read = IOUtils.readFully(this.in, b);
        this.count(read);
        if (10 != read || !FramedSnappyCompressorInputStream.matches(b, 10)) {
            throw new IOException("Not a framed Snappy stream");
        }
    }

    private int readOneByte() throws IOException {
        int b = this.in.read();
        if (b != -1) {
            this.count(1);
            return b & 0xFF;
        }
        return -1;
    }

    private void verifyLastChecksumAndReset() throws IOException {
        if (this.expectedChecksum >= 0L && this.expectedChecksum != this.checksum.getValue()) {
            throw new IOException("Checksum verification failed");
        }
        this.expectedChecksum = -1L;
        this.checksum.reset();
    }

    public static boolean matches(byte[] signature, int length) {
        if (length < SZ_SIGNATURE.length) {
            return false;
        }
        byte[] shortenedSig = signature;
        if (signature.length > SZ_SIGNATURE.length) {
            shortenedSig = new byte[SZ_SIGNATURE.length];
            System.arraycopy(signature, 0, shortenedSig, 0, SZ_SIGNATURE.length);
        }
        return Arrays.equals(shortenedSig, SZ_SIGNATURE);
    }
}

