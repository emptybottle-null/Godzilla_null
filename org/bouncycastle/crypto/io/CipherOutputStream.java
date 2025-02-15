/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.io.CipherIOException;
import org.bouncycastle.crypto.io.InvalidCipherTextIOException;
import org.bouncycastle.crypto.modes.AEADBlockCipher;

public class CipherOutputStream
extends FilterOutputStream {
    private BufferedBlockCipher bufferedBlockCipher;
    private StreamCipher streamCipher;
    private AEADBlockCipher aeadBlockCipher;
    private final byte[] oneByte = new byte[1];
    private byte[] buf;

    public CipherOutputStream(OutputStream outputStream, BufferedBlockCipher bufferedBlockCipher) {
        super(outputStream);
        this.bufferedBlockCipher = bufferedBlockCipher;
    }

    public CipherOutputStream(OutputStream outputStream, StreamCipher streamCipher) {
        super(outputStream);
        this.streamCipher = streamCipher;
    }

    public CipherOutputStream(OutputStream outputStream, AEADBlockCipher aEADBlockCipher) {
        super(outputStream);
        this.aeadBlockCipher = aEADBlockCipher;
    }

    public void write(int n) throws IOException {
        this.oneByte[0] = (byte)n;
        if (this.streamCipher != null) {
            this.out.write(this.streamCipher.returnByte((byte)n));
        } else {
            this.write(this.oneByte, 0, 1);
        }
    }

    public void write(byte[] byArray) throws IOException {
        this.write(byArray, 0, byArray.length);
    }

    public void write(byte[] byArray, int n, int n2) throws IOException {
        this.ensureCapacity(n2, false);
        if (this.bufferedBlockCipher != null) {
            int n3 = this.bufferedBlockCipher.processBytes(byArray, n, n2, this.buf, 0);
            if (n3 != 0) {
                this.out.write(this.buf, 0, n3);
            }
        } else if (this.aeadBlockCipher != null) {
            int n4 = this.aeadBlockCipher.processBytes(byArray, n, n2, this.buf, 0);
            if (n4 != 0) {
                this.out.write(this.buf, 0, n4);
            }
        } else {
            this.streamCipher.processBytes(byArray, n, n2, this.buf, 0);
            this.out.write(this.buf, 0, n2);
        }
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

    public void flush() throws IOException {
        this.out.flush();
    }

    public void close() throws IOException {
        IOException iOException;
        block13: {
            this.ensureCapacity(0, true);
            iOException = null;
            try {
                int n;
                if (this.bufferedBlockCipher != null) {
                    n = this.bufferedBlockCipher.doFinal(this.buf, 0);
                    if (n != 0) {
                        this.out.write(this.buf, 0, n);
                    }
                } else if (this.aeadBlockCipher != null) {
                    n = this.aeadBlockCipher.doFinal(this.buf, 0);
                    if (n != 0) {
                        this.out.write(this.buf, 0, n);
                    }
                } else if (this.streamCipher != null) {
                    this.streamCipher.reset();
                }
            } catch (InvalidCipherTextException invalidCipherTextException) {
                iOException = new InvalidCipherTextIOException("Error finalising cipher data", invalidCipherTextException);
            } catch (Exception exception) {
                iOException = new CipherIOException("Error closing stream: ", exception);
            }
            try {
                this.flush();
                this.out.close();
            } catch (IOException iOException2) {
                if (iOException != null) break block13;
                iOException = iOException2;
            }
        }
        if (iOException != null) {
            throw iOException;
        }
    }
}

