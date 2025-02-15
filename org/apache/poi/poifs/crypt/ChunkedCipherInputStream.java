/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInputStream;

@Internal
public abstract class ChunkedCipherInputStream
extends LittleEndianInputStream {
    private static final int MAX_RECORD_LENGTH = 100000;
    private final int chunkSize;
    private final int chunkBits;
    private final long size;
    private final byte[] chunk;
    private final byte[] plain;
    private final Cipher cipher;
    private int lastIndex;
    private long pos;
    private boolean chunkIsValid;

    public ChunkedCipherInputStream(InputStream stream, long size, int chunkSize) throws GeneralSecurityException {
        this(stream, size, chunkSize, 0);
    }

    public ChunkedCipherInputStream(InputStream stream, long size, int chunkSize, int initialPos) throws GeneralSecurityException {
        super(stream);
        this.size = size;
        this.pos = initialPos;
        this.chunkSize = chunkSize;
        int cs = chunkSize == -1 ? 4096 : chunkSize;
        this.chunk = IOUtils.safelyAllocate(cs, 100000);
        this.plain = IOUtils.safelyAllocate(cs, 100000);
        this.chunkBits = Integer.bitCount(this.chunk.length - 1);
        this.lastIndex = (int)(this.pos >> this.chunkBits);
        this.cipher = this.initCipherForBlock(null, this.lastIndex);
    }

    public final Cipher initCipherForBlock(int block) throws IOException, GeneralSecurityException {
        if (this.chunkSize != -1) {
            throw new GeneralSecurityException("the cipher block can only be set for streaming encryption, e.g. CryptoAPI...");
        }
        this.chunkIsValid = false;
        return this.initCipherForBlock(this.cipher, block);
    }

    protected abstract Cipher initCipherForBlock(Cipher var1, int var2) throws GeneralSecurityException;

    @Override
    public int read() throws IOException {
        byte[] b = new byte[]{0};
        return this.read(b) == 1 ? b[0] & 0xFF : -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.read(b, off, len, false);
    }

    private int read(byte[] b, int off, int len, boolean readPlain) throws IOException {
        int total = 0;
        if (this.remainingBytes() <= 0) {
            return -1;
        }
        int chunkMask = this.getChunkMask();
        while (len > 0) {
            if (!this.chunkIsValid) {
                try {
                    this.nextChunk();
                    this.chunkIsValid = true;
                } catch (GeneralSecurityException e) {
                    throw new EncryptedDocumentException(e.getMessage(), e);
                }
            }
            int count = (int)((long)this.chunk.length - (this.pos & (long)chunkMask));
            int avail = this.remainingBytes();
            if (avail == 0) {
                return total;
            }
            count = Math.min(avail, Math.min(count, len));
            System.arraycopy(readPlain ? this.plain : this.chunk, (int)(this.pos & (long)chunkMask), b, off, count);
            off += count;
            len -= count;
            this.pos += (long)count;
            if ((this.pos & (long)chunkMask) == 0L) {
                this.chunkIsValid = false;
            }
            total += count;
        }
        return total;
    }

    @Override
    public long skip(long n) {
        long start = this.pos;
        long skip = Math.min((long)this.remainingBytes(), n);
        if (((this.pos + skip ^ start) & (long)(~this.getChunkMask())) != 0L) {
            this.chunkIsValid = false;
        }
        this.pos += skip;
        return skip;
    }

    @Override
    public int available() {
        return this.remainingBytes();
    }

    private int remainingBytes() {
        return (int)(this.size - this.pos);
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public synchronized void mark(int readlimit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized void reset() {
        throw new UnsupportedOperationException();
    }

    protected int getChunkMask() {
        return this.chunk.length - 1;
    }

    private void nextChunk() throws GeneralSecurityException, IOException {
        int readBytes;
        if (this.chunkSize != -1) {
            long skipN;
            int index = (int)(this.pos >> this.chunkBits);
            this.initCipherForBlock(this.cipher, index);
            if (this.lastIndex != index && super.skip(skipN = (long)(index - this.lastIndex << this.chunkBits)) < skipN) {
                throw new EOFException("buffer underrun");
            }
            this.lastIndex = index + 1;
        }
        int todo = (int)Math.min(this.size, (long)this.chunk.length);
        int totalBytes = 0;
        while ((readBytes = super.read(this.plain, totalBytes, todo - totalBytes)) != -1 && (totalBytes += Math.max(0, readBytes)) < todo) {
        }
        if (readBytes == -1 && this.pos + (long)totalBytes < this.size && this.size < Integer.MAX_VALUE) {
            throw new EOFException("buffer underrun");
        }
        System.arraycopy(this.plain, 0, this.chunk, 0, totalBytes);
        this.invokeCipher(totalBytes, totalBytes == this.chunkSize);
    }

    protected int invokeCipher(int totalBytes, boolean doFinal) throws GeneralSecurityException {
        if (doFinal) {
            return this.cipher.doFinal(this.chunk, 0, totalBytes, this.chunk);
        }
        return this.cipher.update(this.chunk, 0, totalBytes, this.chunk);
    }

    @Override
    public void readPlain(byte[] b, int off, int len) {
        if (len <= 0) {
            return;
        }
        try {
            int readBytes;
            int total = 0;
            while ((readBytes = this.read(b, off, len, true)) > -1 && (total += Math.max(0, readBytes)) < len) {
            }
            if (total < len) {
                throw new EOFException("buffer underrun");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setNextRecordSize(int recordSize) {
    }

    protected byte[] getChunk() {
        return this.chunk;
    }

    protected byte[] getPlain() {
        return this.plain;
    }

    public long getPos() {
        return this.pos;
    }
}

