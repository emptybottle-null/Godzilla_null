/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

public class OpenPGPCFBBlockCipher
implements BlockCipher {
    private byte[] IV;
    private byte[] FR;
    private byte[] FRE;
    private BlockCipher cipher;
    private int count;
    private int blockSize;
    private boolean forEncryption;

    public OpenPGPCFBBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        this.blockSize = blockCipher.getBlockSize();
        this.IV = new byte[this.blockSize];
        this.FR = new byte[this.blockSize];
        this.FRE = new byte[this.blockSize];
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/OpenPGPCFB";
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        return this.forEncryption ? this.encryptBlock(byArray, n, byArray2, n2) : this.decryptBlock(byArray, n, byArray2, n2);
    }

    public void reset() {
        this.count = 0;
        System.arraycopy(this.IV, 0, this.FR, 0, this.FR.length);
        this.cipher.reset();
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = bl;
        this.reset();
        this.cipher.init(true, cipherParameters);
    }

    private byte encryptByte(byte by, int n) {
        return (byte)(this.FRE[n] ^ by);
    }

    private int encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (n + this.blockSize > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + this.blockSize > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.count > this.blockSize) {
            this.FR[this.blockSize - 2] = byArray2[n2] = this.encryptByte(byArray[n], this.blockSize - 2);
            byte by = this.encryptByte(byArray[n + 1], this.blockSize - 1);
            byArray2[n2 + 1] = by;
            this.FR[this.blockSize - 1] = by;
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (int i = 2; i < this.blockSize; ++i) {
                byte by2 = this.encryptByte(byArray[n + i], i - 2);
                byArray2[n2 + i] = by2;
                this.FR[i - 2] = by2;
            }
        } else if (this.count == 0) {
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (int i = 0; i < this.blockSize; ++i) {
                byte by = this.encryptByte(byArray[n + i], i);
                byArray2[n2 + i] = by;
                this.FR[i] = by;
            }
            this.count += this.blockSize;
        } else if (this.count == this.blockSize) {
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            byArray2[n2] = this.encryptByte(byArray[n], 0);
            byArray2[n2 + 1] = this.encryptByte(byArray[n + 1], 1);
            System.arraycopy(this.FR, 2, this.FR, 0, this.blockSize - 2);
            System.arraycopy(byArray2, n2, this.FR, this.blockSize - 2, 2);
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (int i = 2; i < this.blockSize; ++i) {
                byte by = this.encryptByte(byArray[n + i], i - 2);
                byArray2[n2 + i] = by;
                this.FR[i - 2] = by;
            }
            this.count += this.blockSize;
        }
        return this.blockSize;
    }

    private int decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (n + this.blockSize > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + this.blockSize > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.count > this.blockSize) {
            byte by;
            this.FR[this.blockSize - 2] = by = byArray[n];
            byArray2[n2] = this.encryptByte(by, this.blockSize - 2);
            this.FR[this.blockSize - 1] = by = byArray[n + 1];
            byArray2[n2 + 1] = this.encryptByte(by, this.blockSize - 1);
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (int i = 2; i < this.blockSize; ++i) {
                this.FR[i - 2] = by = byArray[n + i];
                byArray2[n2 + i] = this.encryptByte(by, i - 2);
            }
        } else if (this.count == 0) {
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (int i = 0; i < this.blockSize; ++i) {
                this.FR[i] = byArray[n + i];
                byArray2[i] = this.encryptByte(byArray[n + i], i);
            }
            this.count += this.blockSize;
        } else if (this.count == this.blockSize) {
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            byte by = byArray[n];
            byte by2 = byArray[n + 1];
            byArray2[n2] = this.encryptByte(by, 0);
            byArray2[n2 + 1] = this.encryptByte(by2, 1);
            System.arraycopy(this.FR, 2, this.FR, 0, this.blockSize - 2);
            this.FR[this.blockSize - 2] = by;
            this.FR[this.blockSize - 1] = by2;
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (int i = 2; i < this.blockSize; ++i) {
                byte by3;
                this.FR[i - 2] = by3 = byArray[n + i];
                byArray2[n2 + i] = this.encryptByte(by3, i - 2);
            }
            this.count += this.blockSize;
        }
        return this.blockSize;
    }
}

