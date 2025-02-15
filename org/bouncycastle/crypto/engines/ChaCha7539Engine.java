/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.engines.ChaChaEngine;
import org.bouncycastle.crypto.engines.Salsa20Engine;
import org.bouncycastle.util.Pack;

public class ChaCha7539Engine
extends Salsa20Engine {
    public String getAlgorithmName() {
        return "ChaCha7539-" + this.rounds;
    }

    protected int getNonceSize() {
        return 12;
    }

    protected void advanceCounter(long l) {
        int n = (int)(l >>> 32);
        int n2 = (int)l;
        if (n > 0) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
        int n3 = this.engineState[12];
        this.engineState[12] = this.engineState[12] + n2;
        if (n3 != 0 && this.engineState[12] < n3) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
    }

    protected void advanceCounter() {
        this.engineState[12] = this.engineState[12] + 1;
        if (this.engineState[12] == 0) {
            throw new IllegalStateException("attempt to increase counter past 2^32.");
        }
    }

    protected void retreatCounter(long l) {
        int n = (int)(l >>> 32);
        int n2 = (int)l;
        if (n != 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        if (((long)this.engineState[12] & 0xFFFFFFFFL) < ((long)n2 & 0xFFFFFFFFL)) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        this.engineState[12] = this.engineState[12] - n2;
    }

    protected void retreatCounter() {
        if (this.engineState[12] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        this.engineState[12] = this.engineState[12] - 1;
    }

    protected long getCounter() {
        return (long)this.engineState[12] & 0xFFFFFFFFL;
    }

    protected void resetCounter() {
        this.engineState[12] = 0;
    }

    protected void setKey(byte[] byArray, byte[] byArray2) {
        if (byArray != null) {
            if (byArray.length != 32) {
                throw new IllegalArgumentException(this.getAlgorithmName() + " requires 256 bit key");
            }
            this.packTauOrSigma(byArray.length, this.engineState, 0);
            Pack.littleEndianToInt(byArray, 0, this.engineState, 4, 8);
        }
        Pack.littleEndianToInt(byArray2, 0, this.engineState, 13, 3);
    }

    protected void generateKeyStream(byte[] byArray) {
        ChaChaEngine.chachaCore(this.rounds, this.engineState, this.x);
        Pack.intToLittleEndian(this.x, byArray, 0);
    }
}

