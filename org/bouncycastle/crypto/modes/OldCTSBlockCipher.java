/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.modes.OFBBlockCipher;

public class OldCTSBlockCipher
extends BufferedBlockCipher {
    private int blockSize;

    public OldCTSBlockCipher(BlockCipher blockCipher) {
        if (blockCipher instanceof OFBBlockCipher || blockCipher instanceof CFBBlockCipher) {
            throw new IllegalArgumentException("CTSBlockCipher can only accept ECB, or CBC ciphers");
        }
        this.cipher = blockCipher;
        this.blockSize = blockCipher.getBlockSize();
        this.buf = new byte[this.blockSize * 2];
        this.bufOff = 0;
    }

    public int getUpdateOutputSize(int n) {
        int n2 = n + this.bufOff;
        int n3 = n2 % this.buf.length;
        if (n3 == 0) {
            return n2 - this.buf.length;
        }
        return n2 - n3;
    }

    public int getOutputSize(int n) {
        return n + this.bufOff;
    }

    public int processByte(byte by, byte[] byArray, int n) throws DataLengthException, IllegalStateException {
        int n2 = 0;
        if (this.bufOff == this.buf.length) {
            n2 = this.cipher.processBlock(this.buf, 0, byArray, n);
            System.arraycopy(this.buf, this.blockSize, this.buf, 0, this.blockSize);
            this.bufOff = this.blockSize;
        }
        this.buf[this.bufOff++] = by;
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
            System.arraycopy(this.buf, n4, this.buf, 0, n4);
            this.bufOff = n4;
            n2 -= n7;
            n += n7;
            while (n2 > n4) {
                System.arraycopy(byArray, n, this.buf, this.bufOff, n4);
                n6 += this.cipher.processBlock(this.buf, 0, byArray2, n3 + n6);
                System.arraycopy(this.buf, n4, this.buf, 0, n4);
                n2 -= n4;
                n += n4;
            }
        }
        System.arraycopy(byArray, n, this.buf, this.bufOff, n2);
        this.bufOff += n2;
        return n6;
    }

    public int doFinal(byte[] byArray, int n) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
        if (this.bufOff + n > byArray.length) {
            throw new OutputLengthException("output buffer to small in doFinal");
        }
        int n2 = this.cipher.getBlockSize();
        int n3 = this.bufOff - n2;
        byte[] byArray2 = new byte[n2];
        if (this.forEncryption) {
            int n4;
            this.cipher.processBlock(this.buf, 0, byArray2, 0);
            if (this.bufOff < n2) {
                throw new DataLengthException("need at least one block of input for CTS");
            }
            for (n4 = this.bufOff; n4 != this.buf.length; ++n4) {
                this.buf[n4] = byArray2[n4 - n2];
            }
            for (n4 = n2; n4 != this.bufOff; ++n4) {
                int n5 = n4;
                this.buf[n5] = (byte)(this.buf[n5] ^ byArray2[n4 - n2]);
            }
            if (this.cipher instanceof CBCBlockCipher) {
                BlockCipher blockCipher = ((CBCBlockCipher)this.cipher).getUnderlyingCipher();
                blockCipher.processBlock(this.buf, n2, byArray, n);
            } else {
                this.cipher.processBlock(this.buf, n2, byArray, n);
            }
            System.arraycopy(byArray2, 0, byArray, n + n2, n3);
        } else {
            byte[] byArray3 = new byte[n2];
            if (this.cipher instanceof CBCBlockCipher) {
                BlockCipher blockCipher = ((CBCBlockCipher)this.cipher).getUnderlyingCipher();
                blockCipher.processBlock(this.buf, 0, byArray2, 0);
            } else {
                this.cipher.processBlock(this.buf, 0, byArray2, 0);
            }
            for (int i = n2; i != this.bufOff; ++i) {
                byArray3[i - n2] = (byte)(byArray2[i - n2] ^ this.buf[i]);
            }
            System.arraycopy(this.buf, n2, byArray2, 0, n3);
            this.cipher.processBlock(byArray2, 0, byArray, n);
            System.arraycopy(byArray3, 0, byArray, n + n2, n3);
        }
        int n6 = this.bufOff;
        this.reset();
        return n6;
    }
}

