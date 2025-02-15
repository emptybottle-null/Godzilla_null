/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

public class KCTRBlockCipher
extends StreamBlockCipher {
    private byte[] iv;
    private byte[] ofbV;
    private byte[] ofbOutV;
    private int byteCount;
    private boolean initialised;
    private BlockCipher engine;

    public KCTRBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.engine = blockCipher;
        this.iv = new byte[blockCipher.getBlockSize()];
        this.ofbV = new byte[blockCipher.getBlockSize()];
        this.ofbOutV = new byte[blockCipher.getBlockSize()];
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.initialised = true;
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameter passed");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
        byte[] byArray = parametersWithIV.getIV();
        int n = this.iv.length - byArray.length;
        Arrays.fill(this.iv, (byte)0);
        System.arraycopy(byArray, 0, this.iv, n, byArray.length);
        cipherParameters = parametersWithIV.getParameters();
        if (cipherParameters != null) {
            this.engine.init(true, cipherParameters);
        }
        this.reset();
    }

    public String getAlgorithmName() {
        return this.engine.getAlgorithmName() + "/KCTR";
    }

    public int getBlockSize() {
        return this.engine.getBlockSize();
    }

    protected byte calculateByte(byte by) {
        if (this.byteCount == 0) {
            this.incrementCounterAt(0);
            this.checkCounter();
            this.engine.processBlock(this.ofbV, 0, this.ofbOutV, 0);
            return (byte)(this.ofbOutV[this.byteCount++] ^ by);
        }
        byte by2 = (byte)(this.ofbOutV[this.byteCount++] ^ by);
        if (this.byteCount == this.ofbV.length) {
            this.byteCount = 0;
        }
        return by2;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (byArray.length - n < this.getBlockSize()) {
            throw new DataLengthException("input buffer too short");
        }
        if (byArray2.length - n2 < this.getBlockSize()) {
            throw new OutputLengthException("output buffer too short");
        }
        this.processBytes(byArray, n, this.getBlockSize(), byArray2, n2);
        return this.getBlockSize();
    }

    public void reset() {
        if (this.initialised) {
            this.engine.processBlock(this.iv, 0, this.ofbV, 0);
        }
        this.engine.reset();
        this.byteCount = 0;
    }

    private void incrementCounterAt(int n) {
        int n2 = n;
        while (n2 < this.ofbV.length) {
            int n3 = n2++;
            this.ofbV[n3] = (byte)(this.ofbV[n3] + 1);
            if (this.ofbV[n3] == 0) continue;
            break;
        }
    }

    private void checkCounter() {
    }
}

