/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class PGPCFBBlockCipher
implements BlockCipher {
    private byte[] IV;
    private byte[] FR;
    private byte[] FRE;
    private byte[] tmp;
    private BlockCipher cipher;
    private int count;
    private int blockSize;
    private boolean forEncryption;
    private boolean inlineIv;

    public PGPCFBBlockCipher(BlockCipher blockCipher, boolean bl) {
        this.cipher = blockCipher;
        this.inlineIv = bl;
        this.blockSize = blockCipher.getBlockSize();
        this.IV = new byte[this.blockSize];
        this.FR = new byte[this.blockSize];
        this.FRE = new byte[this.blockSize];
        this.tmp = new byte[this.blockSize];
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public String getAlgorithmName() {
        if (this.inlineIv) {
            return this.cipher.getAlgorithmName() + "/PGPCFBwithIV";
        }
        return this.cipher.getAlgorithmName() + "/PGPCFB";
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (this.inlineIv) {
            return this.forEncryption ? this.encryptBlockWithIV(byArray, n, byArray2, n2) : this.decryptBlockWithIV(byArray, n, byArray2, n2);
        }
        return this.forEncryption ? this.encryptBlock(byArray, n, byArray2, n2) : this.decryptBlock(byArray, n, byArray2, n2);
    }

    public void reset() {
        this.count = 0;
        for (int i = 0; i != this.FR.length; ++i) {
            this.FR[i] = this.inlineIv ? (byte)0 : this.IV[i];
        }
        this.cipher.reset();
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = bl;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
            byte[] byArray = parametersWithIV.getIV();
            if (byArray.length < this.IV.length) {
                System.arraycopy(byArray, 0, this.IV, this.IV.length - byArray.length, byArray.length);
                for (int i = 0; i < this.IV.length - byArray.length; ++i) {
                    this.IV[i] = 0;
                }
            } else {
                System.arraycopy(byArray, 0, this.IV, 0, this.IV.length);
            }
            this.reset();
            this.cipher.init(true, parametersWithIV.getParameters());
        } else {
            this.reset();
            this.cipher.init(true, cipherParameters);
        }
    }

    private byte encryptByte(byte by, int n) {
        return (byte)(this.FRE[n] ^ by);
    }

    private int encryptBlockWithIV(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (n + this.blockSize > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (this.count == 0) {
            int n3;
            if (n2 + 2 * this.blockSize + 2 > byArray2.length) {
                throw new OutputLengthException("output buffer too short");
            }
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (n3 = 0; n3 < this.blockSize; ++n3) {
                byArray2[n2 + n3] = this.encryptByte(this.IV[n3], n3);
            }
            System.arraycopy(byArray2, n2, this.FR, 0, this.blockSize);
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            byArray2[n2 + this.blockSize] = this.encryptByte(this.IV[this.blockSize - 2], 0);
            byArray2[n2 + this.blockSize + 1] = this.encryptByte(this.IV[this.blockSize - 1], 1);
            System.arraycopy(byArray2, n2 + 2, this.FR, 0, this.blockSize);
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (n3 = 0; n3 < this.blockSize; ++n3) {
                byArray2[n2 + this.blockSize + 2 + n3] = this.encryptByte(byArray[n + n3], n3);
            }
            System.arraycopy(byArray2, n2 + this.blockSize + 2, this.FR, 0, this.blockSize);
            this.count += 2 * this.blockSize + 2;
            return 2 * this.blockSize + 2;
        }
        if (this.count >= this.blockSize + 2) {
            if (n2 + this.blockSize > byArray2.length) {
                throw new OutputLengthException("output buffer too short");
            }
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (int i = 0; i < this.blockSize; ++i) {
                byArray2[n2 + i] = this.encryptByte(byArray[n + i], i);
            }
            System.arraycopy(byArray2, n2, this.FR, 0, this.blockSize);
        }
        return this.blockSize;
    }

    private int decryptBlockWithIV(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (n + this.blockSize > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + this.blockSize > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.count == 0) {
            for (int i = 0; i < this.blockSize; ++i) {
                this.FR[i] = byArray[n + i];
            }
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            this.count += this.blockSize;
            return 0;
        }
        if (this.count == this.blockSize) {
            System.arraycopy(byArray, n, this.tmp, 0, this.blockSize);
            System.arraycopy(this.FR, 2, this.FR, 0, this.blockSize - 2);
            this.FR[this.blockSize - 2] = this.tmp[0];
            this.FR[this.blockSize - 1] = this.tmp[1];
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (int i = 0; i < this.blockSize - 2; ++i) {
                byArray2[n2 + i] = this.encryptByte(this.tmp[i + 2], i);
            }
            System.arraycopy(this.tmp, 2, this.FR, 0, this.blockSize - 2);
            this.count += 2;
            return this.blockSize - 2;
        }
        if (this.count >= this.blockSize + 2) {
            System.arraycopy(byArray, n, this.tmp, 0, this.blockSize);
            byArray2[n2 + 0] = this.encryptByte(this.tmp[0], this.blockSize - 2);
            byArray2[n2 + 1] = this.encryptByte(this.tmp[1], this.blockSize - 1);
            System.arraycopy(this.tmp, 0, this.FR, this.blockSize - 2, 2);
            this.cipher.processBlock(this.FR, 0, this.FRE, 0);
            for (int i = 0; i < this.blockSize - 2; ++i) {
                byArray2[n2 + i + 2] = this.encryptByte(this.tmp[i + 2], i);
            }
            System.arraycopy(this.tmp, 2, this.FR, 0, this.blockSize - 2);
        }
        return this.blockSize;
    }

    private int encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        int n3;
        if (n + this.blockSize > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + this.blockSize > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.cipher.processBlock(this.FR, 0, this.FRE, 0);
        for (n3 = 0; n3 < this.blockSize; ++n3) {
            byArray2[n2 + n3] = this.encryptByte(byArray[n + n3], n3);
        }
        for (n3 = 0; n3 < this.blockSize; ++n3) {
            this.FR[n3] = byArray2[n2 + n3];
        }
        return this.blockSize;
    }

    private int decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        int n3;
        if (n + this.blockSize > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + this.blockSize > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.cipher.processBlock(this.FR, 0, this.FRE, 0);
        for (n3 = 0; n3 < this.blockSize; ++n3) {
            byArray2[n2 + n3] = this.encryptByte(byArray[n + n3], n3);
        }
        for (n3 = 0; n3 < this.blockSize; ++n3) {
            this.FR[n3] = byArray[n + n3];
        }
        return this.blockSize;
    }
}

