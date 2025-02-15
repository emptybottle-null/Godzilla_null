/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;

public class BufferedBlockCipher {
    protected byte[] buf;
    protected int bufOff;
    protected boolean forEncryption;
    protected BlockCipher cipher;
    protected boolean partialBlockOkay;
    protected boolean pgpCFB;

    protected BufferedBlockCipher() {
    }

    public BufferedBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        this.buf = new byte[blockCipher.getBlockSize()];
        this.bufOff = 0;
        String string = blockCipher.getAlgorithmName();
        int n = string.indexOf(47) + 1;
        boolean bl = this.pgpCFB = n > 0 && string.startsWith("PGP", n);
        this.partialBlockOkay = this.pgpCFB || blockCipher instanceof StreamCipher ? true : n > 0 && string.startsWith("OpenPGP", n);
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = bl;
        this.reset();
        this.cipher.init(bl, cipherParameters);
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int getUpdateOutputSize(int n) {
        int n2 = n + this.bufOff;
        int n3 = this.pgpCFB ? (this.forEncryption ? n2 % this.buf.length - (this.cipher.getBlockSize() + 2) : n2 % this.buf.length) : n2 % this.buf.length;
        return n2 - n3;
    }

    public int getOutputSize(int n) {
        return n + this.bufOff;
    }

    public int processByte(byte by, byte[] byArray, int n) throws DataLengthException, IllegalStateException {
        int n2 = 0;
        this.buf[this.bufOff++] = by;
        if (this.bufOff == this.buf.length) {
            n2 = this.cipher.processBlock(this.buf, 0, byArray, n);
            this.bufOff = 0;
        }
        return n2;
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws DataLengthException, IllegalStateException {
        if (n2 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int n4 = this.getBlockSize();
        int n5 = this.getUpdateOutputSize(n2);
        if (n5 > 0 && n3 + n5 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int n6 = 0;
        int n7 = this.buf.length - this.bufOff;
        if (n2 > n7) {
            System.arraycopy(byArray, n, this.buf, this.bufOff, n7);
            n6 += this.cipher.processBlock(this.buf, 0, byArray2, n3);
            this.bufOff = 0;
            n2 -= n7;
            n += n7;
            while (n2 > this.buf.length) {
                n6 += this.cipher.processBlock(byArray, n, byArray2, n3 + n6);
                n2 -= n4;
                n += n4;
            }
        }
        System.arraycopy(byArray, n, this.buf, this.bufOff, n2);
        this.bufOff += n2;
        if (this.bufOff == this.buf.length) {
            n6 += this.cipher.processBlock(this.buf, 0, byArray2, n3 + n6);
            this.bufOff = 0;
        }
        return n6;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int doFinal(byte[] byArray, int n) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
        try {
            int n2 = 0;
            if (n + this.bufOff > byArray.length) {
                throw new OutputLengthException("output buffer too short for doFinal()");
            }
            if (this.bufOff != 0) {
                if (!this.partialBlockOkay) {
                    throw new DataLengthException("data not block size aligned");
                }
                this.cipher.processBlock(this.buf, 0, this.buf, 0);
                n2 = this.bufOff;
                this.bufOff = 0;
                System.arraycopy(this.buf, 0, byArray, n, n2);
            }
            int n3 = n2;
            return n3;
        } finally {
            this.reset();
        }
    }

    public void reset() {
        for (int i = 0; i < this.buf.length; ++i) {
            this.buf[i] = 0;
        }
        this.bufOff = 0;
        this.cipher.reset();
    }
}

