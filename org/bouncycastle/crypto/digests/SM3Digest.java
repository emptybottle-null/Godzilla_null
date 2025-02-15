/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.digests.GeneralDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class SM3Digest
extends GeneralDigest {
    private static final int DIGEST_LENGTH = 32;
    private static final int BLOCK_SIZE = 16;
    private int[] V = new int[8];
    private int[] inwords = new int[16];
    private int xOff;
    private int[] W = new int[68];
    private int[] W1 = new int[64];
    private static final int[] T;

    public SM3Digest() {
        this.reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super(sM3Digest);
        this.copyIn(sM3Digest);
    }

    private void copyIn(SM3Digest sM3Digest) {
        System.arraycopy(sM3Digest.V, 0, this.V, 0, this.V.length);
        System.arraycopy(sM3Digest.inwords, 0, this.inwords, 0, this.inwords.length);
        this.xOff = sM3Digest.xOff;
    }

    public String getAlgorithmName() {
        return "SM3";
    }

    public int getDigestSize() {
        return 32;
    }

    public Memoable copy() {
        return new SM3Digest(this);
    }

    public void reset(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest)memoable;
        super.copyIn(sM3Digest);
        this.copyIn(sM3Digest);
    }

    public void reset() {
        super.reset();
        this.V[0] = 1937774191;
        this.V[1] = 1226093241;
        this.V[2] = 388252375;
        this.V[3] = -628488704;
        this.V[4] = -1452330820;
        this.V[5] = 372324522;
        this.V[6] = -477237683;
        this.V[7] = -1325724082;
        this.xOff = 0;
    }

    public int doFinal(byte[] byArray, int n) {
        this.finish();
        Pack.intToBigEndian(this.V[0], byArray, n + 0);
        Pack.intToBigEndian(this.V[1], byArray, n + 4);
        Pack.intToBigEndian(this.V[2], byArray, n + 8);
        Pack.intToBigEndian(this.V[3], byArray, n + 12);
        Pack.intToBigEndian(this.V[4], byArray, n + 16);
        Pack.intToBigEndian(this.V[5], byArray, n + 20);
        Pack.intToBigEndian(this.V[6], byArray, n + 24);
        Pack.intToBigEndian(this.V[7], byArray, n + 28);
        this.reset();
        return 32;
    }

    protected void processWord(byte[] byArray, int n) {
        int n2;
        this.inwords[this.xOff] = n2 = (byArray[n] & 0xFF) << 24 | (byArray[++n] & 0xFF) << 16 | (byArray[++n] & 0xFF) << 8 | byArray[++n] & 0xFF;
        ++this.xOff;
        if (this.xOff >= 16) {
            this.processBlock();
        }
    }

    protected void processLength(long l) {
        if (this.xOff > 14) {
            this.inwords[this.xOff] = 0;
            ++this.xOff;
            this.processBlock();
        }
        while (this.xOff < 14) {
            this.inwords[this.xOff] = 0;
            ++this.xOff;
        }
        this.inwords[this.xOff++] = (int)(l >>> 32);
        this.inwords[this.xOff++] = (int)l;
    }

    private int P0(int n) {
        int n2 = n << 9 | n >>> 23;
        int n3 = n << 17 | n >>> 15;
        return n ^ n2 ^ n3;
    }

    private int P1(int n) {
        int n2 = n << 15 | n >>> 17;
        int n3 = n << 23 | n >>> 9;
        return n ^ n2 ^ n3;
    }

    private int FF0(int n, int n2, int n3) {
        return n ^ n2 ^ n3;
    }

    private int FF1(int n, int n2, int n3) {
        return n & n2 | n & n3 | n2 & n3;
    }

    private int GG0(int n, int n2, int n3) {
        return n ^ n2 ^ n3;
    }

    private int GG1(int n, int n2, int n3) {
        return n & n2 | ~n & n3;
    }

    protected void processBlock() {
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10;
        int n11;
        int n12;
        for (n12 = 0; n12 < 16; ++n12) {
            this.W[n12] = this.inwords[n12];
        }
        for (n12 = 16; n12 < 68; ++n12) {
            n11 = this.W[n12 - 3];
            n10 = n11 << 15 | n11 >>> 17;
            n9 = this.W[n12 - 13];
            n8 = n9 << 7 | n9 >>> 25;
            this.W[n12] = this.P1(this.W[n12 - 16] ^ this.W[n12 - 9] ^ n10) ^ n8 ^ this.W[n12 - 6];
        }
        for (n12 = 0; n12 < 64; ++n12) {
            this.W1[n12] = this.W[n12] ^ this.W[n12 + 4];
        }
        n12 = this.V[0];
        n11 = this.V[1];
        n10 = this.V[2];
        n9 = this.V[3];
        n8 = this.V[4];
        int n13 = this.V[5];
        int n14 = this.V[6];
        int n15 = this.V[7];
        for (n7 = 0; n7 < 16; ++n7) {
            n6 = n12 << 12 | n12 >>> 20;
            n5 = n6 + n8 + T[n7];
            n4 = n5 << 7 | n5 >>> 25;
            n3 = n4 ^ n6;
            n2 = this.FF0(n12, n11, n10) + n9 + n3 + this.W1[n7];
            n = this.GG0(n8, n13, n14) + n15 + n4 + this.W[n7];
            n9 = n10;
            n10 = n11 << 9 | n11 >>> 23;
            n11 = n12;
            n12 = n2;
            n15 = n14;
            n14 = n13 << 19 | n13 >>> 13;
            n13 = n8;
            n8 = this.P0(n);
        }
        for (n7 = 16; n7 < 64; ++n7) {
            n6 = n12 << 12 | n12 >>> 20;
            n5 = n6 + n8 + T[n7];
            n4 = n5 << 7 | n5 >>> 25;
            n3 = n4 ^ n6;
            n2 = this.FF1(n12, n11, n10) + n9 + n3 + this.W1[n7];
            n = this.GG1(n8, n13, n14) + n15 + n4 + this.W[n7];
            n9 = n10;
            n10 = n11 << 9 | n11 >>> 23;
            n11 = n12;
            n12 = n2;
            n15 = n14;
            n14 = n13 << 19 | n13 >>> 13;
            n13 = n8;
            n8 = this.P0(n);
        }
        this.V[0] = this.V[0] ^ n12;
        this.V[1] = this.V[1] ^ n11;
        this.V[2] = this.V[2] ^ n10;
        this.V[3] = this.V[3] ^ n9;
        this.V[4] = this.V[4] ^ n8;
        this.V[5] = this.V[5] ^ n13;
        this.V[6] = this.V[6] ^ n14;
        this.V[7] = this.V[7] ^ n15;
        this.xOff = 0;
    }

    static {
        int n;
        int n2;
        T = new int[64];
        for (n2 = 0; n2 < 16; ++n2) {
            n = 2043430169;
            SM3Digest.T[n2] = n << n2 | n >>> 32 - n2;
        }
        for (n2 = 16; n2 < 64; ++n2) {
            n = n2 % 32;
            int n3 = 2055708042;
            SM3Digest.T[n2] = n3 << n | n3 >>> 32 - n;
        }
    }
}

