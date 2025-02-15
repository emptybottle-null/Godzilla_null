/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class KeccakDigest
implements ExtendedDigest {
    private static long[] KeccakRoundConstants = KeccakDigest.keccakInitializeRoundConstants();
    private static int[] KeccakRhoOffsets = KeccakDigest.keccakInitializeRhoOffsets();
    protected long[] state = new long[25];
    protected byte[] dataQueue = new byte[192];
    protected int rate;
    protected int bitsInQueue;
    protected int fixedOutputLength;
    protected boolean squeezing;

    private static long[] keccakInitializeRoundConstants() {
        long[] lArray = new long[24];
        byte[] byArray = new byte[]{1};
        for (int i = 0; i < 24; ++i) {
            lArray[i] = 0L;
            for (int j = 0; j < 7; ++j) {
                int n = (1 << j) - 1;
                if (!KeccakDigest.LFSR86540(byArray)) continue;
                int n2 = i;
                lArray[n2] = lArray[n2] ^ 1L << n;
            }
        }
        return lArray;
    }

    private static boolean LFSR86540(byte[] byArray) {
        boolean bl = (byArray[0] & 1) != 0;
        byArray[0] = (byArray[0] & 0x80) != 0 ? (byte)(byArray[0] << 1 ^ 0x71) : (byte)(byArray[0] << 1);
        return bl;
    }

    private static int[] keccakInitializeRhoOffsets() {
        int[] nArray = new int[25];
        nArray[0] = 0;
        int n = 1;
        int n2 = 0;
        for (int i = 0; i < 24; ++i) {
            nArray[n % 5 + 5 * (n2 % 5)] = (i + 1) * (i + 2) / 2 % 64;
            int n3 = (0 * n + 1 * n2) % 5;
            int n4 = (2 * n + 3 * n2) % 5;
            n = n3;
            n2 = n4;
        }
        return nArray;
    }

    public KeccakDigest() {
        this(288);
    }

    public KeccakDigest(int n) {
        this.init(n);
    }

    public KeccakDigest(KeccakDigest keccakDigest) {
        System.arraycopy(keccakDigest.state, 0, this.state, 0, keccakDigest.state.length);
        System.arraycopy(keccakDigest.dataQueue, 0, this.dataQueue, 0, keccakDigest.dataQueue.length);
        this.rate = keccakDigest.rate;
        this.bitsInQueue = keccakDigest.bitsInQueue;
        this.fixedOutputLength = keccakDigest.fixedOutputLength;
        this.squeezing = keccakDigest.squeezing;
    }

    public String getAlgorithmName() {
        return "Keccak-" + this.fixedOutputLength;
    }

    public int getDigestSize() {
        return this.fixedOutputLength / 8;
    }

    public void update(byte by) {
        this.absorb(new byte[]{by}, 0, 1);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.absorb(byArray, n, n2);
    }

    public int doFinal(byte[] byArray, int n) {
        this.squeeze(byArray, n, this.fixedOutputLength);
        this.reset();
        return this.getDigestSize();
    }

    protected int doFinal(byte[] byArray, int n, byte by, int n2) {
        if (n2 > 0) {
            this.absorbBits(by, n2);
        }
        this.squeeze(byArray, n, this.fixedOutputLength);
        this.reset();
        return this.getDigestSize();
    }

    public void reset() {
        this.init(this.fixedOutputLength);
    }

    public int getByteLength() {
        return this.rate / 8;
    }

    private void init(int n) {
        switch (n) {
            case 128: 
            case 224: 
            case 256: 
            case 288: 
            case 384: 
            case 512: {
                this.initSponge(1600 - (n << 1));
                break;
            }
            default: {
                throw new IllegalArgumentException("bitLength must be one of 128, 224, 256, 288, 384, or 512.");
            }
        }
    }

    private void initSponge(int n) {
        if (n <= 0 || n >= 1600 || n % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        }
        this.rate = n;
        for (int i = 0; i < this.state.length; ++i) {
            this.state[i] = 0L;
        }
        Arrays.fill(this.dataQueue, (byte)0);
        this.bitsInQueue = 0;
        this.squeezing = false;
        this.fixedOutputLength = (1600 - n) / 2;
    }

    protected void absorb(byte[] byArray, int n, int n2) {
        if (this.bitsInQueue % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.squeezing) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        int n3 = this.bitsInQueue >> 3;
        int n4 = this.rate >> 3;
        int n5 = 0;
        while (n5 < n2) {
            if (n3 == 0 && n5 <= n2 - n4) {
                do {
                    this.KeccakAbsorb(byArray, n + n5);
                } while ((n5 += n4) <= n2 - n4);
                continue;
            }
            int n6 = Math.min(n4 - n3, n2 - n5);
            System.arraycopy(byArray, n + n5, this.dataQueue, n3, n6);
            n5 += n6;
            if ((n3 += n6) != n4) continue;
            this.KeccakAbsorb(this.dataQueue, 0);
            n3 = 0;
        }
        this.bitsInQueue = n3 << 3;
    }

    protected void absorbBits(int n, int n2) {
        if (n2 < 1 || n2 > 7) {
            throw new IllegalArgumentException("'bits' must be in the range 1 to 7");
        }
        if (this.bitsInQueue % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.squeezing) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        int n3 = (1 << n2) - 1;
        this.dataQueue[this.bitsInQueue >> 3] = (byte)(n & n3);
        this.bitsInQueue += n2;
    }

    private void padAndSwitchToSqueezingPhase() {
        int n = this.bitsInQueue >> 3;
        this.dataQueue[n] = (byte)(this.dataQueue[n] | (byte)(1L << (this.bitsInQueue & 7)));
        if (++this.bitsInQueue == this.rate) {
            this.KeccakAbsorb(this.dataQueue, 0);
            this.bitsInQueue = 0;
        }
        int n2 = this.bitsInQueue >> 6;
        int n3 = this.bitsInQueue & 0x3F;
        int n4 = 0;
        int n5 = 0;
        while (n5 < n2) {
            int n6 = n5++;
            this.state[n6] = this.state[n6] ^ Pack.littleEndianToLong(this.dataQueue, n4);
            n4 += 8;
        }
        if (n3 > 0) {
            long l = (1L << n3) - 1L;
            int n7 = n2;
            this.state[n7] = this.state[n7] ^ Pack.littleEndianToLong(this.dataQueue, n4) & l;
        }
        int n8 = this.rate - 1 >> 6;
        this.state[n8] = this.state[n8] ^ Long.MIN_VALUE;
        this.KeccakPermutation();
        this.KeccakExtract();
        this.bitsInQueue = this.rate;
        this.squeezing = true;
    }

    protected void squeeze(byte[] byArray, int n, long l) {
        int n2;
        if (!this.squeezing) {
            this.padAndSwitchToSqueezingPhase();
        }
        if (l % 8L != 0L) {
            throw new IllegalStateException("outputLength not a multiple of 8");
        }
        for (long i = 0L; i < l; i += (long)n2) {
            if (this.bitsInQueue == 0) {
                this.KeccakPermutation();
                this.KeccakExtract();
                this.bitsInQueue = this.rate;
            }
            n2 = (int)Math.min((long)this.bitsInQueue, l - i);
            System.arraycopy(this.dataQueue, (this.rate - this.bitsInQueue) / 8, byArray, n + (int)(i / 8L), n2 / 8);
            this.bitsInQueue -= n2;
        }
    }

    private void KeccakAbsorb(byte[] byArray, int n) {
        int n2 = this.rate >> 6;
        int n3 = 0;
        while (n3 < n2) {
            int n4 = n3++;
            this.state[n4] = this.state[n4] ^ Pack.littleEndianToLong(byArray, n);
            n += 8;
        }
        this.KeccakPermutation();
    }

    private void KeccakExtract() {
        Pack.longToLittleEndian(this.state, 0, this.rate >> 6, this.dataQueue, 0);
    }

    private void KeccakPermutation() {
        for (int i = 0; i < 24; ++i) {
            KeccakDigest.theta(this.state);
            KeccakDigest.rho(this.state);
            KeccakDigest.pi(this.state);
            KeccakDigest.chi(this.state);
            KeccakDigest.iota(this.state, i);
        }
    }

    private static long leftRotate(long l, int n) {
        return l << n | l >>> -n;
    }

    private static void theta(long[] lArray) {
        long l = lArray[0] ^ lArray[5] ^ lArray[10] ^ lArray[15] ^ lArray[20];
        long l2 = lArray[1] ^ lArray[6] ^ lArray[11] ^ lArray[16] ^ lArray[21];
        long l3 = lArray[2] ^ lArray[7] ^ lArray[12] ^ lArray[17] ^ lArray[22];
        long l4 = lArray[3] ^ lArray[8] ^ lArray[13] ^ lArray[18] ^ lArray[23];
        long l5 = lArray[4] ^ lArray[9] ^ lArray[14] ^ lArray[19] ^ lArray[24];
        long l6 = KeccakDigest.leftRotate(l2, 1) ^ l5;
        lArray[0] = lArray[0] ^ l6;
        lArray[5] = lArray[5] ^ l6;
        lArray[10] = lArray[10] ^ l6;
        lArray[15] = lArray[15] ^ l6;
        lArray[20] = lArray[20] ^ l6;
        l6 = KeccakDigest.leftRotate(l3, 1) ^ l;
        lArray[1] = lArray[1] ^ l6;
        lArray[6] = lArray[6] ^ l6;
        lArray[11] = lArray[11] ^ l6;
        lArray[16] = lArray[16] ^ l6;
        lArray[21] = lArray[21] ^ l6;
        l6 = KeccakDigest.leftRotate(l4, 1) ^ l2;
        lArray[2] = lArray[2] ^ l6;
        lArray[7] = lArray[7] ^ l6;
        lArray[12] = lArray[12] ^ l6;
        lArray[17] = lArray[17] ^ l6;
        lArray[22] = lArray[22] ^ l6;
        l6 = KeccakDigest.leftRotate(l5, 1) ^ l3;
        lArray[3] = lArray[3] ^ l6;
        lArray[8] = lArray[8] ^ l6;
        lArray[13] = lArray[13] ^ l6;
        lArray[18] = lArray[18] ^ l6;
        lArray[23] = lArray[23] ^ l6;
        l6 = KeccakDigest.leftRotate(l, 1) ^ l4;
        lArray[4] = lArray[4] ^ l6;
        lArray[9] = lArray[9] ^ l6;
        lArray[14] = lArray[14] ^ l6;
        lArray[19] = lArray[19] ^ l6;
        lArray[24] = lArray[24] ^ l6;
    }

    private static void rho(long[] lArray) {
        for (int i = 1; i < 25; ++i) {
            lArray[i] = KeccakDigest.leftRotate(lArray[i], KeccakRhoOffsets[i]);
        }
    }

    private static void pi(long[] lArray) {
        long l = lArray[1];
        lArray[1] = lArray[6];
        lArray[6] = lArray[9];
        lArray[9] = lArray[22];
        lArray[22] = lArray[14];
        lArray[14] = lArray[20];
        lArray[20] = lArray[2];
        lArray[2] = lArray[12];
        lArray[12] = lArray[13];
        lArray[13] = lArray[19];
        lArray[19] = lArray[23];
        lArray[23] = lArray[15];
        lArray[15] = lArray[4];
        lArray[4] = lArray[24];
        lArray[24] = lArray[21];
        lArray[21] = lArray[8];
        lArray[8] = lArray[16];
        lArray[16] = lArray[5];
        lArray[5] = lArray[3];
        lArray[3] = lArray[18];
        lArray[18] = lArray[17];
        lArray[17] = lArray[11];
        lArray[11] = lArray[7];
        lArray[7] = lArray[10];
        lArray[10] = l;
    }

    private static void chi(long[] lArray) {
        for (int i = 0; i < 25; i += 5) {
            long l = lArray[0 + i] ^ (lArray[1 + i] ^ 0xFFFFFFFFFFFFFFFFL) & lArray[2 + i];
            long l2 = lArray[1 + i] ^ (lArray[2 + i] ^ 0xFFFFFFFFFFFFFFFFL) & lArray[3 + i];
            long l3 = lArray[2 + i] ^ (lArray[3 + i] ^ 0xFFFFFFFFFFFFFFFFL) & lArray[4 + i];
            long l4 = lArray[3 + i] ^ (lArray[4 + i] ^ 0xFFFFFFFFFFFFFFFFL) & lArray[0 + i];
            long l5 = lArray[4 + i] ^ (lArray[0 + i] ^ 0xFFFFFFFFFFFFFFFFL) & lArray[1 + i];
            lArray[0 + i] = l;
            lArray[1 + i] = l2;
            lArray[2 + i] = l3;
            lArray[3 + i] = l4;
            lArray[4 + i] = l5;
        }
    }

    private static void iota(long[] lArray, int n) {
        lArray[0] = lArray[0] ^ KeccakRoundConstants[n];
    }
}

