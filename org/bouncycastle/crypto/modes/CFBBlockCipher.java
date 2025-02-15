/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

public class CFBBlockCipher
extends StreamBlockCipher {
    private byte[] IV;
    private byte[] cfbV;
    private byte[] cfbOutV;
    private byte[] inBuf;
    private int blockSize;
    private BlockCipher cipher = null;
    private boolean encrypting;
    private int byteCount;

    public CFBBlockCipher(BlockCipher blockCipher, int n) {
        super(blockCipher);
        this.cipher = blockCipher;
        this.blockSize = n / 8;
        this.IV = new byte[blockCipher.getBlockSize()];
        this.cfbV = new byte[blockCipher.getBlockSize()];
        this.cfbOutV = new byte[blockCipher.getBlockSize()];
        this.inBuf = new byte[this.blockSize];
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.encrypting = bl;
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
        return this.cipher.getAlgorithmName() + "/CFB" + this.blockSize * 8;
    }

    protected byte calculateByte(byte by) throws DataLengthException, IllegalStateException {
        return this.encrypting ? this.encryptByte(by) : this.decryptByte(by);
    }

    private byte encryptByte(byte by) {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.cfbV, 0, this.cfbOutV, 0);
        }
        byte by2 = (byte)(this.cfbOutV[this.byteCount] ^ by);
        this.inBuf[this.byteCount++] = by2;
        if (this.byteCount == this.blockSize) {
            this.byteCount = 0;
            System.arraycopy(this.cfbV, this.blockSize, this.cfbV, 0, this.cfbV.length - this.blockSize);
            System.arraycopy(this.inBuf, 0, this.cfbV, this.cfbV.length - this.blockSize, this.blockSize);
        }
        return by2;
    }

    private byte decryptByte(byte by) {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.cfbV, 0, this.cfbOutV, 0);
        }
        this.inBuf[this.byteCount] = by;
        byte by2 = (byte)(this.cfbOutV[this.byteCount++] ^ by);
        if (this.byteCount == this.blockSize) {
            this.byteCount = 0;
            System.arraycopy(this.cfbV, this.blockSize, this.cfbV, 0, this.cfbV.length - this.blockSize);
            System.arraycopy(this.inBuf, 0, this.cfbV, this.cfbV.length - this.blockSize, this.blockSize);
        }
        return by2;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        this.processBytes(byArray, n, this.blockSize, byArray2, n2);
        return this.blockSize;
    }

    public int encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        this.processBytes(byArray, n, this.blockSize, byArray2, n2);
        return this.blockSize;
    }

    public int decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        this.processBytes(byArray, n, this.blockSize, byArray2, n2);
        return this.blockSize;
    }

    public byte[] getCurrentIV() {
        return Arrays.clone(this.cfbV);
    }

    public void reset() {
        System.arraycopy(this.IV, 0, this.cfbV, 0, this.IV.length);
        Arrays.fill(this.inBuf, (byte)0);
        this.byteCount = 0;
        this.cipher.reset();
    }
}

