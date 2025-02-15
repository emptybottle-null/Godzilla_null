/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class OFBBlockCipher
extends StreamBlockCipher {
    private int byteCount;
    private byte[] IV;
    private byte[] ofbV;
    private byte[] ofbOutV;
    private final int blockSize;
    private final BlockCipher cipher;

    public OFBBlockCipher(BlockCipher blockCipher, int n) {
        super(blockCipher);
        this.cipher = blockCipher;
        this.blockSize = n / 8;
        this.IV = new byte[blockCipher.getBlockSize()];
        this.ofbV = new byte[blockCipher.getBlockSize()];
        this.ofbOutV = new byte[blockCipher.getBlockSize()];
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
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
            if (parametersWithIV.getParameters() != null) {
                this.cipher.init(true, parametersWithIV.getParameters());
            }
        } else {
            this.reset();
            if (cipherParameters != null) {
                this.cipher.init(true, cipherParameters);
            }
        }
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/OFB" + this.blockSize * 8;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        this.processBytes(byArray, n, this.blockSize, byArray2, n2);
        return this.blockSize;
    }

    public void reset() {
        System.arraycopy(this.IV, 0, this.ofbV, 0, this.IV.length);
        this.byteCount = 0;
        this.cipher.reset();
    }

    protected byte calculateByte(byte by) throws DataLengthException, IllegalStateException {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.ofbV, 0, this.ofbOutV, 0);
        }
        byte by2 = (byte)(this.ofbOutV[this.byteCount++] ^ by);
        if (this.byteCount == this.blockSize) {
            this.byteCount = 0;
            System.arraycopy(this.ofbV, this.blockSize, this.ofbV, 0, this.ofbV.length - this.blockSize);
            System.arraycopy(this.ofbOutV, 0, this.ofbV, this.ofbV.length - this.blockSize, this.blockSize);
        }
        return by2;
    }
}

