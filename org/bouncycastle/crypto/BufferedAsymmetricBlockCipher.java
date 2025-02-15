/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

public class BufferedAsymmetricBlockCipher {
    protected byte[] buf;
    protected int bufOff;
    private final AsymmetricBlockCipher cipher;

    public BufferedAsymmetricBlockCipher(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.cipher = asymmetricBlockCipher;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public int getBufferPosition() {
        return this.bufOff;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.reset();
        this.cipher.init(bl, cipherParameters);
        this.buf = new byte[this.cipher.getInputBlockSize() + (bl ? 1 : 0)];
        this.bufOff = 0;
    }

    public int getInputBlockSize() {
        return this.cipher.getInputBlockSize();
    }

    public int getOutputBlockSize() {
        return this.cipher.getOutputBlockSize();
    }

    public void processByte(byte by) {
        if (this.bufOff >= this.buf.length) {
            throw new DataLengthException("attempt to process message too long for cipher");
        }
        this.buf[this.bufOff++] = by;
    }

    public void processBytes(byte[] byArray, int n, int n2) {
        if (n2 == 0) {
            return;
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        if (this.bufOff + n2 > this.buf.length) {
            throw new DataLengthException("attempt to process message too long for cipher");
        }
        System.arraycopy(byArray, n, this.buf, this.bufOff, n2);
        this.bufOff += n2;
    }

    public byte[] doFinal() throws InvalidCipherTextException {
        byte[] byArray = this.cipher.processBlock(this.buf, 0, this.bufOff);
        this.reset();
        return byArray;
    }

    public void reset() {
        if (this.buf != null) {
            for (int i = 0; i < this.buf.length; ++i) {
                this.buf[i] = 0;
            }
        }
        this.bufOff = 0;
    }
}

