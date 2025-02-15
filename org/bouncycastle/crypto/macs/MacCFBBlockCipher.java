/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;

class MacCFBBlockCipher {
    private byte[] IV;
    private byte[] cfbV;
    private byte[] cfbOutV;
    private int blockSize;
    private BlockCipher cipher = null;

    public MacCFBBlockCipher(BlockCipher blockCipher, int n) {
        this.cipher = blockCipher;
        this.blockSize = n / 8;
        this.IV = new byte[blockCipher.getBlockSize()];
        this.cfbV = new byte[blockCipher.getBlockSize()];
        this.cfbOutV = new byte[blockCipher.getBlockSize()];
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
            byte[] byArray = parametersWithIV.getIV();
            if (byArray.length < this.IV.length) {
                System.arraycopy(byArray, 0, this.IV, this.IV.length - byArray.length, byArray.length);
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

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CFB" + this.blockSize * 8;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (n + this.blockSize > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + this.blockSize > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.cipher.processBlock(this.cfbV, 0, this.cfbOutV, 0);
        for (int i = 0; i < this.blockSize; ++i) {
            byArray2[n2 + i] = (byte)(this.cfbOutV[i] ^ byArray[n + i]);
        }
        System.arraycopy(this.cfbV, this.blockSize, this.cfbV, 0, this.cfbV.length - this.blockSize);
        System.arraycopy(byArray2, n2, this.cfbV, this.cfbV.length - this.blockSize, this.blockSize);
        return this.blockSize;
    }

    public void reset() {
        System.arraycopy(this.IV, 0, this.cfbV, 0, this.IV.length);
        this.cipher.reset();
    }

    void getMacBlock(byte[] byArray) {
        this.cipher.processBlock(this.cfbV, 0, byArray, 0);
    }
}

