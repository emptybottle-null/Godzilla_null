/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SkippingCipher;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.io.CipherIOException;
import org.bouncycastle.crypto.io.InvalidCipherTextIOException;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.util.Arrays;

public class CipherInputStream
extends FilterInputStream {
    private static final int INPUT_BUF_SIZE = 2048;
    private SkippingCipher skippingCipher;
    private byte[] inBuf;
    private BufferedBlockCipher bufferedBlockCipher;
    private StreamCipher streamCipher;
    private AEADBlockCipher aeadBlockCipher;
    private byte[] buf;
    private byte[] markBuf;
    private int bufOff;
    private int maxBuf;
    private boolean finalized;
    private long markPosition;
    private int markBufOff;

    public CipherInputStream(InputStream inputStream, BufferedBlockCipher bufferedBlockCipher) {
        this(inputStream, bufferedBlockCipher, 2048);
    }

    public CipherInputStream(InputStream inputStream, StreamCipher streamCipher) {
        this(inputStream, streamCipher, 2048);
    }

    public CipherInputStream(InputStream inputStream, AEADBlockCipher aEADBlockCipher) {
        this(inputStream, aEADBlockCipher, 2048);
    }

    public CipherInputStream(InputStream inputStream, BufferedBlockCipher bufferedBlockCipher, int n) {
        super(inputStream);
        this.bufferedBlockCipher = bufferedBlockCipher;
        this.inBuf = new byte[n];
        this.skippingCipher = bufferedBlockCipher instanceof SkippingCipher ? (SkippingCipher)((Object)bufferedBlockCipher) : null;
    }

    public CipherInputStream(InputStream inputStream, StreamCipher streamCipher, int n) {
        super(inputStream);
        this.streamCipher = streamCipher;
        this.inBuf = new byte[n];
        this.skippingCipher = streamCipher instanceof SkippingCipher ? (SkippingCipher)((Object)streamCipher) : null;
    }

    public CipherInputStream(InputStream inputStream, AEADBlockCipher aEADBlockCipher, int n) {
        super(inputStream);
        this.aeadBlockCipher = aEADBlockCipher;
        this.inBuf = new byte[n];
        this.skippingCipher = aEADBlockCipher instanceof SkippingCipher ? (SkippingCipher)((Object)aEADBlockCipher) : null;
    }

    private int nextChunk() throws IOException {
        if (this.finalized) {
            return -1;
        }
        this.bufOff = 0;
        this.maxBuf = 0;
        while (this.maxBuf == 0) {
            int n = this.in.read(this.inBuf);
            if (n == -1) {
                this.finaliseCipher();
                if (this.maxBuf == 0) {
                    return -1;
                }
                return this.maxBuf;
            }
            try {
                this.ensureCapacity(n, false);
                if (this.bufferedBlockCipher != null) {
                    this.maxBuf = this.bufferedBlockCipher.processBytes(this.inBuf, 0, n, this.buf, 0);
                    continue;
                }
                if (this.aeadBlockCipher != null) {
                    this.maxBuf = this.aeadBlockCipher.processBytes(this.inBuf, 0, n, this.buf, 0);
                    continue;
                }
                this.streamCipher.processBytes(this.inBuf, 0, n, this.buf, 0);
                this.maxBuf = n;
            } catch (Exception exception) {
                throw new CipherIOException("Error processing stream ", exception);
            }
        }
        return this.maxBuf;
    }

    private void finaliseCipher() throws IOException {
        try {
            this.finalized = true;
            this.ensureCapacity(0, true);
            this.maxBuf = this.bufferedBlockCipher != null ? this.bufferedBlockCipher.doFinal(this.buf, 0) : (this.aeadBlockCipher != null ? this.aeadBlockCipher.doFinal(this.buf, 0) : 0);
        } catch (InvalidCipherTextException invalidCipherTextException) {
            throw new InvalidCipherTextIOException("Error finalising cipher", invalidCipherTextException);
        } catch (Exception exception) {
            throw new IOException("Error finalising cipher " + exception);
        }
    }

    public int read() throws IOException {
        if (this.bufOff >= this.maxBuf && this.nextChunk() < 0) {
            return -1;
        }
        return this.buf[this.bufOff++] & 0xFF;
    }

    public int read(byte[] byArray) throws IOException {
        return this.read(byArray, 0, byArray.length);
    }

    public int read(byte[] byArray, int n, int n2) throws IOException {
        if (this.bufOff >= this.maxBuf && this.nextChunk() < 0) {
            return -1;
        }
        int n3 = Math.min(n2, this.available());
        System.arraycopy(this.buf, this.bufOff, byArray, n, n3);
        this.bufOff += n3;
        return n3;
    }

    public long skip(long l) throws IOException {
        if (l <= 0L) {
            return 0L;
        }
        if (this.skippingCipher != null) {
            long l2;
            int n = this.available();
            if (l <= (long)n) {
                this.bufOff = (int)((long)this.bufOff + l);
                return l;
            }
            this.bufOff = this.maxBuf;
            long l3 = this.in.skip(l - (long)n);
            if (l3 != (l2 = this.skippingCipher.skip(l3))) {
                throw new IOException("Unable to skip cipher " + l3 + " bytes.");
            }
            return l3 + (long)n;
        }
        int n = (int)Math.min(l, (long)this.available());
        this.bufOff += n;
        return n;
    }

    public int available() throws IOException {
        return this.maxBuf - this.bufOff;
    }

    private void ensureCapacity(int n, boolean bl) {
        int n2 = n;
        if (bl) {
            if (this.bufferedBlockCipher != null) {
                n2 = this.bufferedBlockCipher.getOutputSize(n);
            } else if (this.aeadBlockCipher != null) {
                n2 = this.aeadBlockCipher.getOutputSize(n);
            }
        } else if (this.bufferedBlockCipher != null) {
            n2 = this.bufferedBlockCipher.getUpdateOutputSize(n);
        } else if (this.aeadBlockCipher != null) {
            n2 = this.aeadBlockCipher.getUpdateOutputSize(n);
        }
        if (this.buf == null || this.buf.length < n2) {
            this.buf = new byte[n2];
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void close() throws IOException {
        try {
            this.in.close();
        } finally {
            if (!this.finalized) {
                this.finaliseCipher();
            }
        }
        this.bufOff = 0;
        this.maxBuf = 0;
        this.markBufOff = 0;
        this.markPosition = 0L;
        if (this.markBuf != null) {
            Arrays.fill(this.markBuf, (byte)0);
            this.markBuf = null;
        }
        if (this.buf != null) {
            Arrays.fill(this.buf, (byte)0);
            this.buf = null;
        }
        Arrays.fill(this.inBuf, (byte)0);
    }

    public void mark(int n) {
        this.in.mark(n);
        if (this.skippingCipher != null) {
            this.markPosition = this.skippingCipher.getPosition();
        }
        if (this.buf != null) {
            this.markBuf = new byte[this.buf.length];
            System.arraycopy(this.buf, 0, this.markBuf, 0, this.buf.length);
        }
        this.markBufOff = this.bufOff;
    }

    public void reset() throws IOException {
        if (this.skippingCipher == null) {
            throw new IOException("cipher must implement SkippingCipher to be used with reset()");
        }
        this.in.reset();
        this.skippingCipher.seekTo(this.markPosition);
        if (this.markBuf != null) {
            this.buf = this.markBuf;
        }
        this.bufOff = this.markBufOff;
    }

    public boolean markSupported() {
        if (this.skippingCipher != null) {
            return this.in.markSupported();
        }
        return false;
    }
}

