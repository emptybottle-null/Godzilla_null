/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

public class NullEngine
implements BlockCipher {
    private boolean initialised;
    protected static final int DEFAULT_BLOCK_SIZE = 1;
    private final int blockSize;

    public NullEngine() {
        this(1);
    }

    public NullEngine(int n) {
        this.blockSize = n;
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.initialised = true;
    }

    public String getAlgorithmName() {
        return "Null";
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (!this.initialised) {
            throw new IllegalStateException("Null engine not initialised");
        }
        if (n + this.blockSize > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + this.blockSize > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i = 0; i < this.blockSize; ++i) {
            byArray2[n2 + i] = byArray[n + i];
        }
        return this.blockSize;
    }

    public void reset() {
    }
}

