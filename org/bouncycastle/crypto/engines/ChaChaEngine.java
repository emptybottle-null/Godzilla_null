/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.engines.Salsa20Engine;
import org.bouncycastle.util.Pack;

public class ChaChaEngine
extends Salsa20Engine {
    public ChaChaEngine() {
    }

    public ChaChaEngine(int n) {
        super(n);
    }

    public String getAlgorithmName() {
        return "ChaCha" + this.rounds;
    }

    protected void advanceCounter(long l) {
        int n = (int)(l >>> 32);
        int n2 = (int)l;
        if (n > 0) {
            this.engineState[13] = this.engineState[13] + n;
        }
        int n3 = this.engineState[12];
        this.engineState[12] = this.engineState[12] + n2;
        if (n3 != 0 && this.engineState[12] < n3) {
            this.engineState[13] = this.engineState[13] + 1;
        }
    }

    protected void advanceCounter() {
        this.engineState[12] = this.engineState[12] + 1;
        if (this.engineState[12] == 0) {
            this.engineState[13] = this.engineState[13] + 1;
        }
    }

    protected void retreatCounter(long l) {
        int n = (int)(l >>> 32);
        int n2 = (int)l;
        if (n != 0) {
            if (((long)this.engineState[13] & 0xFFFFFFFFL) >= ((long)n & 0xFFFFFFFFL)) {
                this.engineState[13] = this.engineState[13] - n;
            } else {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
        }
        if (((long)this.engineState[12] & 0xFFFFFFFFL) >= ((long)n2 & 0xFFFFFFFFL)) {
            this.engineState[12] = this.engineState[12] - n2;
        } else if (this.engineState[13] != 0) {
            this.engineState[13] = this.engineState[13] - 1;
            this.engineState[12] = this.engineState[12] - n2;
        } else {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
    }

    protected void retreatCounter() {
        if (this.engineState[12] == 0 && this.engineState[13] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        this.engineState[12] = this.engineState[12] - 1;
        if (this.engineState[12] == -1) {
            this.engineState[13] = this.engineState[13] - 1;
        }
    }

    protected long getCounter() {
        return (long)this.engineState[13] << 32 | (long)this.engineState[12] & 0xFFFFFFFFL;
    }

    protected void resetCounter() {
        this.engineState[13] = 0;
        this.engineState[12] = 0;
    }

    protected void setKey(byte[] byArray, byte[] byArray2) {
        if (byArray != null) {
            if (byArray.length != 16 && byArray.length != 32) {
                throw new IllegalArgumentException(this.getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            this.packTauOrSigma(byArray.length, this.engineState, 0);
            Pack.littleEndianToInt(byArray, 0, this.engineState, 4, 4);
            Pack.littleEndianToInt(byArray, byArray.length - 16, this.engineState, 8, 4);
        }
        Pack.littleEndianToInt(byArray2, 0, this.engineState, 14, 2);
    }

    protected void generateKeyStream(byte[] byArray) {
        ChaChaEngine.chachaCore(this.rounds, this.engineState, this.x);
        Pack.intToLittleEndian(this.x, byArray, 0);
    }

    public static void chachaCore(int n, int[] nArray, int[] nArray2) {
        if (nArray.length != 16) {
            throw new IllegalArgumentException();
        }
        if (nArray2.length != 16) {
            throw new IllegalArgumentException();
        }
        if (n % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        int n2 = nArray[0];
        int n3 = nArray[1];
        int n4 = nArray[2];
        int n5 = nArray[3];
        int n6 = nArray[4];
        int n7 = nArray[5];
        int n8 = nArray[6];
        int n9 = nArray[7];
        int n10 = nArray[8];
        int n11 = nArray[9];
        int n12 = nArray[10];
        int n13 = nArray[11];
        int n14 = nArray[12];
        int n15 = nArray[13];
        int n16 = nArray[14];
        int n17 = nArray[15];
        for (int i = n; i > 0; i -= 2) {
            n14 = ChaChaEngine.rotl(n14 ^ (n2 += n6), 16);
            n6 = ChaChaEngine.rotl(n6 ^ (n10 += n14), 12);
            n14 = ChaChaEngine.rotl(n14 ^ (n2 += n6), 8);
            n6 = ChaChaEngine.rotl(n6 ^ (n10 += n14), 7);
            n15 = ChaChaEngine.rotl(n15 ^ (n3 += n7), 16);
            n7 = ChaChaEngine.rotl(n7 ^ (n11 += n15), 12);
            n15 = ChaChaEngine.rotl(n15 ^ (n3 += n7), 8);
            n7 = ChaChaEngine.rotl(n7 ^ (n11 += n15), 7);
            n16 = ChaChaEngine.rotl(n16 ^ (n4 += n8), 16);
            n8 = ChaChaEngine.rotl(n8 ^ (n12 += n16), 12);
            n16 = ChaChaEngine.rotl(n16 ^ (n4 += n8), 8);
            n8 = ChaChaEngine.rotl(n8 ^ (n12 += n16), 7);
            n17 = ChaChaEngine.rotl(n17 ^ (n5 += n9), 16);
            n9 = ChaChaEngine.rotl(n9 ^ (n13 += n17), 12);
            n17 = ChaChaEngine.rotl(n17 ^ (n5 += n9), 8);
            n9 = ChaChaEngine.rotl(n9 ^ (n13 += n17), 7);
            n17 = ChaChaEngine.rotl(n17 ^ (n2 += n7), 16);
            n7 = ChaChaEngine.rotl(n7 ^ (n12 += n17), 12);
            n17 = ChaChaEngine.rotl(n17 ^ (n2 += n7), 8);
            n7 = ChaChaEngine.rotl(n7 ^ (n12 += n17), 7);
            n14 = ChaChaEngine.rotl(n14 ^ (n3 += n8), 16);
            n8 = ChaChaEngine.rotl(n8 ^ (n13 += n14), 12);
            n14 = ChaChaEngine.rotl(n14 ^ (n3 += n8), 8);
            n8 = ChaChaEngine.rotl(n8 ^ (n13 += n14), 7);
            n15 = ChaChaEngine.rotl(n15 ^ (n4 += n9), 16);
            n9 = ChaChaEngine.rotl(n9 ^ (n10 += n15), 12);
            n15 = ChaChaEngine.rotl(n15 ^ (n4 += n9), 8);
            n9 = ChaChaEngine.rotl(n9 ^ (n10 += n15), 7);
            n16 = ChaChaEngine.rotl(n16 ^ (n5 += n6), 16);
            n6 = ChaChaEngine.rotl(n6 ^ (n11 += n16), 12);
            n16 = ChaChaEngine.rotl(n16 ^ (n5 += n6), 8);
            n6 = ChaChaEngine.rotl(n6 ^ (n11 += n16), 7);
        }
        nArray2[0] = n2 + nArray[0];
        nArray2[1] = n3 + nArray[1];
        nArray2[2] = n4 + nArray[2];
        nArray2[3] = n5 + nArray[3];
        nArray2[4] = n6 + nArray[4];
        nArray2[5] = n7 + nArray[5];
        nArray2[6] = n8 + nArray[6];
        nArray2[7] = n9 + nArray[7];
        nArray2[8] = n10 + nArray[8];
        nArray2[9] = n11 + nArray[9];
        nArray2[10] = n12 + nArray[10];
        nArray2[11] = n13 + nArray[11];
        nArray2[12] = n14 + nArray[12];
        nArray2[13] = n15 + nArray[13];
        nArray2[14] = n16 + nArray[14];
        nArray2[15] = n17 + nArray[15];
    }
}

