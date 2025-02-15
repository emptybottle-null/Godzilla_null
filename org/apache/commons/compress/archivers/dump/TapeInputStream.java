/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.dump;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.apache.commons.compress.archivers.dump.DumpArchiveException;
import org.apache.commons.compress.archivers.dump.DumpArchiveUtil;
import org.apache.commons.compress.archivers.dump.ShortFileException;
import org.apache.commons.compress.archivers.dump.UnsupportedCompressionAlgorithmException;
import org.apache.commons.compress.utils.IOUtils;

class TapeInputStream
extends FilterInputStream {
    private byte[] blockBuffer = new byte[1024];
    private int currBlkIdx = -1;
    private int blockSize = 1024;
    private static final int RECORD_SIZE = 1024;
    private int readOffset = 1024;
    private boolean isCompressed = false;
    private long bytesRead = 0L;

    public TapeInputStream(InputStream in) {
        super(in);
    }

    public void resetBlockSize(int recsPerBlock, boolean isCompressed) throws IOException {
        this.isCompressed = isCompressed;
        if (recsPerBlock < 1) {
            throw new IOException("Block with " + recsPerBlock + " records found, must be at least 1");
        }
        this.blockSize = 1024 * recsPerBlock;
        byte[] oldBuffer = this.blockBuffer;
        this.blockBuffer = new byte[this.blockSize];
        System.arraycopy(oldBuffer, 0, this.blockBuffer, 0, 1024);
        this.readFully(this.blockBuffer, 1024, this.blockSize - 1024);
        this.currBlkIdx = 0;
        this.readOffset = 1024;
    }

    @Override
    public int available() throws IOException {
        if (this.readOffset < this.blockSize) {
            return this.blockSize - this.readOffset;
        }
        return this.in.available();
    }

    @Override
    public int read() throws IOException {
        throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (len % 1024 != 0) {
            throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
        }
        int bytes = 0;
        while (bytes < len) {
            if (this.readOffset == this.blockSize) {
                try {
                    this.readBlock(true);
                } catch (ShortFileException sfe) {
                    return -1;
                }
            }
            int n = 0;
            n = this.readOffset + (len - bytes) <= this.blockSize ? len - bytes : this.blockSize - this.readOffset;
            System.arraycopy(this.blockBuffer, this.readOffset, b, off, n);
            this.readOffset += n;
            bytes += n;
            off += n;
        }
        return bytes;
    }

    @Override
    public long skip(long len) throws IOException {
        long bytes;
        long n;
        if (len % 1024L != 0L) {
            throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
        }
        for (bytes = 0L; bytes < len; bytes += n) {
            if (this.readOffset == this.blockSize) {
                try {
                    this.readBlock(len - bytes < (long)this.blockSize);
                } catch (ShortFileException sfe) {
                    return -1L;
                }
            }
            n = 0L;
            n = (long)this.readOffset + (len - bytes) <= (long)this.blockSize ? len - bytes : (long)this.blockSize - (long)this.readOffset;
            this.readOffset = (int)((long)this.readOffset + n);
        }
        return bytes;
    }

    @Override
    public void close() throws IOException {
        if (this.in != null && this.in != System.in) {
            this.in.close();
        }
    }

    public byte[] peek() throws IOException {
        if (this.readOffset == this.blockSize) {
            try {
                this.readBlock(true);
            } catch (ShortFileException sfe) {
                return null;
            }
        }
        byte[] b = new byte[1024];
        System.arraycopy(this.blockBuffer, this.readOffset, b, 0, b.length);
        return b;
    }

    public byte[] readRecord() throws IOException {
        byte[] result = new byte[1024];
        if (-1 == this.read(result, 0, result.length)) {
            throw new ShortFileException();
        }
        return result;
    }

    private void readBlock(boolean decompress) throws IOException {
        if (this.in == null) {
            throw new IOException("Input buffer is closed");
        }
        if (!this.isCompressed || this.currBlkIdx == -1) {
            this.readFully(this.blockBuffer, 0, this.blockSize);
            this.bytesRead += (long)this.blockSize;
        } else {
            boolean compressed;
            this.readFully(this.blockBuffer, 0, 4);
            this.bytesRead += 4L;
            int h = DumpArchiveUtil.convert32(this.blockBuffer, 0);
            boolean bl = compressed = (h & 1) == 1;
            if (!compressed) {
                this.readFully(this.blockBuffer, 0, this.blockSize);
                this.bytesRead += (long)this.blockSize;
            } else {
                int flags = h >> 1 & 7;
                int length = h >> 4 & 0xFFFFFFF;
                byte[] compBuffer = new byte[length];
                this.readFully(compBuffer, 0, length);
                this.bytesRead += (long)length;
                if (!decompress) {
                    Arrays.fill(this.blockBuffer, (byte)0);
                } else {
                    switch (DumpArchiveConstants.COMPRESSION_TYPE.find(flags & 3)) {
                        case ZLIB: {
                            Inflater inflator = new Inflater();
                            try {
                                inflator.setInput(compBuffer, 0, compBuffer.length);
                                length = inflator.inflate(this.blockBuffer);
                                if (length != this.blockSize) {
                                    throw new ShortFileException();
                                }
                                break;
                            } catch (DataFormatException e) {
                                throw new DumpArchiveException("Bad data", e);
                            } finally {
                                inflator.end();
                            }
                        }
                        case BZLIB: {
                            throw new UnsupportedCompressionAlgorithmException("BZLIB2");
                        }
                        case LZO: {
                            throw new UnsupportedCompressionAlgorithmException("LZO");
                        }
                        default: {
                            throw new UnsupportedCompressionAlgorithmException();
                        }
                    }
                }
            }
        }
        ++this.currBlkIdx;
        this.readOffset = 0;
    }

    private void readFully(byte[] b, int off, int len) throws IOException {
        int count = IOUtils.readFully(this.in, b, off, len);
        if (count < len) {
            throw new ShortFileException();
        }
    }

    public long getBytesRead() {
        return this.bytesRead;
    }
}

