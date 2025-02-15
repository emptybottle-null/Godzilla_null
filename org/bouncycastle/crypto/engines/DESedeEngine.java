/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.engines.DESEngine;
import org.bouncycastle.crypto.params.KeyParameter;

public class DESedeEngine
extends DESEngine {
    protected static final int BLOCK_SIZE = 8;
    private int[] workingKey1 = null;
    private int[] workingKey2 = null;
    private int[] workingKey3 = null;
    private boolean forEncryption;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to DESede init - " + cipherParameters.getClass().getName());
        }
        byte[] byArray = ((KeyParameter)cipherParameters).getKey();
        if (byArray.length != 24 && byArray.length != 16) {
            throw new IllegalArgumentException("key size must be 16 or 24 bytes.");
        }
        this.forEncryption = bl;
        byte[] byArray2 = new byte[8];
        System.arraycopy(byArray, 0, byArray2, 0, byArray2.length);
        this.workingKey1 = this.generateWorkingKey(bl, byArray2);
        byte[] byArray3 = new byte[8];
        System.arraycopy(byArray, 8, byArray3, 0, byArray3.length);
        this.workingKey2 = this.generateWorkingKey(!bl, byArray3);
        if (byArray.length == 24) {
            byte[] byArray4 = new byte[8];
            System.arraycopy(byArray, 16, byArray4, 0, byArray4.length);
            this.workingKey3 = this.generateWorkingKey(bl, byArray4);
        } else {
            this.workingKey3 = this.workingKey1;
        }
    }

    public String getAlgorithmName() {
        return "DESede";
    }

    public int getBlockSize() {
        return 8;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        if (this.workingKey1 == null) {
            throw new IllegalStateException("DESede engine not initialised");
        }
        if (n + 8 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 8 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] byArray3 = new byte[8];
        if (this.forEncryption) {
            this.desFunc(this.workingKey1, byArray, n, byArray3, 0);
            this.desFunc(this.workingKey2, byArray3, 0, byArray3, 0);
            this.desFunc(this.workingKey3, byArray3, 0, byArray2, n2);
        } else {
            this.desFunc(this.workingKey3, byArray, n, byArray3, 0);
            this.desFunc(this.workingKey2, byArray3, 0, byArray3, 0);
            this.desFunc(this.workingKey1, byArray3, 0, byArray2, n2);
        }
        return 8;
    }

    public void reset() {
    }
}

