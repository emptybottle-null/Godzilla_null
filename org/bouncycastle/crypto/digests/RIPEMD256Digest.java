/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.digests.GeneralDigest;
import org.bouncycastle.util.Memoable;

public class RIPEMD256Digest
extends GeneralDigest {
    private static final int DIGEST_LENGTH = 32;
    private int H0;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int H5;
    private int H6;
    private int H7;
    private int[] X = new int[16];
    private int xOff;

    public RIPEMD256Digest() {
        this.reset();
    }

    public RIPEMD256Digest(RIPEMD256Digest rIPEMD256Digest) {
        super(rIPEMD256Digest);
        this.copyIn(rIPEMD256Digest);
    }

    private void copyIn(RIPEMD256Digest rIPEMD256Digest) {
        super.copyIn(rIPEMD256Digest);
        this.H0 = rIPEMD256Digest.H0;
        this.H1 = rIPEMD256Digest.H1;
        this.H2 = rIPEMD256Digest.H2;
        this.H3 = rIPEMD256Digest.H3;
        this.H4 = rIPEMD256Digest.H4;
        this.H5 = rIPEMD256Digest.H5;
        this.H6 = rIPEMD256Digest.H6;
        this.H7 = rIPEMD256Digest.H7;
        System.arraycopy(rIPEMD256Digest.X, 0, this.X, 0, rIPEMD256Digest.X.length);
        this.xOff = rIPEMD256Digest.xOff;
    }

    public String getAlgorithmName() {
        return "RIPEMD256";
    }

    public int getDigestSize() {
        return 32;
    }

    protected void processWord(byte[] byArray, int n) {
        this.X[this.xOff++] = byArray[n] & 0xFF | (byArray[n + 1] & 0xFF) << 8 | (byArray[n + 2] & 0xFF) << 16 | (byArray[n + 3] & 0xFF) << 24;
        if (this.xOff == 16) {
            this.processBlock();
        }
    }

    protected void processLength(long l) {
        if (this.xOff > 14) {
            this.processBlock();
        }
        this.X[14] = (int)(l & 0xFFFFFFFFFFFFFFFFL);
        this.X[15] = (int)(l >>> 32);
    }

    private void unpackWord(int n, byte[] byArray, int n2) {
        byArray[n2] = (byte)n;
        byArray[n2 + 1] = (byte)(n >>> 8);
        byArray[n2 + 2] = (byte)(n >>> 16);
        byArray[n2 + 3] = (byte)(n >>> 24);
    }

    public int doFinal(byte[] byArray, int n) {
        this.finish();
        this.unpackWord(this.H0, byArray, n);
        this.unpackWord(this.H1, byArray, n + 4);
        this.unpackWord(this.H2, byArray, n + 8);
        this.unpackWord(this.H3, byArray, n + 12);
        this.unpackWord(this.H4, byArray, n + 16);
        this.unpackWord(this.H5, byArray, n + 20);
        this.unpackWord(this.H6, byArray, n + 24);
        this.unpackWord(this.H7, byArray, n + 28);
        this.reset();
        return 32;
    }

    public void reset() {
        super.reset();
        this.H0 = 1732584193;
        this.H1 = -271733879;
        this.H2 = -1732584194;
        this.H3 = 271733878;
        this.H4 = 1985229328;
        this.H5 = -19088744;
        this.H6 = -1985229329;
        this.H7 = 19088743;
        this.xOff = 0;
        for (int i = 0; i != this.X.length; ++i) {
            this.X[i] = 0;
        }
    }

    private int RL(int n, int n2) {
        return n << n2 | n >>> 32 - n2;
    }

    private int f1(int n, int n2, int n3) {
        return n ^ n2 ^ n3;
    }

    private int f2(int n, int n2, int n3) {
        return n & n2 | ~n & n3;
    }

    private int f3(int n, int n2, int n3) {
        return (n | ~n2) ^ n3;
    }

    private int f4(int n, int n2, int n3) {
        return n & n3 | n2 & ~n3;
    }

    private int F1(int n, int n2, int n3, int n4, int n5, int n6) {
        return this.RL(n + this.f1(n2, n3, n4) + n5, n6);
    }

    private int F2(int n, int n2, int n3, int n4, int n5, int n6) {
        return this.RL(n + this.f2(n2, n3, n4) + n5 + 1518500249, n6);
    }

    private int F3(int n, int n2, int n3, int n4, int n5, int n6) {
        return this.RL(n + this.f3(n2, n3, n4) + n5 + 1859775393, n6);
    }

    private int F4(int n, int n2, int n3, int n4, int n5, int n6) {
        return this.RL(n + this.f4(n2, n3, n4) + n5 + -1894007588, n6);
    }

    private int FF1(int n, int n2, int n3, int n4, int n5, int n6) {
        return this.RL(n + this.f1(n2, n3, n4) + n5, n6);
    }

    private int FF2(int n, int n2, int n3, int n4, int n5, int n6) {
        return this.RL(n + this.f2(n2, n3, n4) + n5 + 1836072691, n6);
    }

    private int FF3(int n, int n2, int n3, int n4, int n5, int n6) {
        return this.RL(n + this.f3(n2, n3, n4) + n5 + 1548603684, n6);
    }

    private int FF4(int n, int n2, int n3, int n4, int n5, int n6) {
        return this.RL(n + this.f4(n2, n3, n4) + n5 + 1352829926, n6);
    }

    protected void processBlock() {
        int n = this.H0;
        int n2 = this.H1;
        int n3 = this.H2;
        int n4 = this.H3;
        int n5 = this.H4;
        int n6 = this.H5;
        int n7 = this.H6;
        int n8 = this.H7;
        n = this.F1(n, n2, n3, n4, this.X[0], 11);
        n4 = this.F1(n4, n, n2, n3, this.X[1], 14);
        n3 = this.F1(n3, n4, n, n2, this.X[2], 15);
        n2 = this.F1(n2, n3, n4, n, this.X[3], 12);
        n = this.F1(n, n2, n3, n4, this.X[4], 5);
        n4 = this.F1(n4, n, n2, n3, this.X[5], 8);
        n3 = this.F1(n3, n4, n, n2, this.X[6], 7);
        n2 = this.F1(n2, n3, n4, n, this.X[7], 9);
        n = this.F1(n, n2, n3, n4, this.X[8], 11);
        n4 = this.F1(n4, n, n2, n3, this.X[9], 13);
        n3 = this.F1(n3, n4, n, n2, this.X[10], 14);
        n2 = this.F1(n2, n3, n4, n, this.X[11], 15);
        n = this.F1(n, n2, n3, n4, this.X[12], 6);
        n4 = this.F1(n4, n, n2, n3, this.X[13], 7);
        n3 = this.F1(n3, n4, n, n2, this.X[14], 9);
        n2 = this.F1(n2, n3, n4, n, this.X[15], 8);
        n5 = this.FF4(n5, n6, n7, n8, this.X[5], 8);
        n8 = this.FF4(n8, n5, n6, n7, this.X[14], 9);
        n7 = this.FF4(n7, n8, n5, n6, this.X[7], 9);
        n6 = this.FF4(n6, n7, n8, n5, this.X[0], 11);
        n5 = this.FF4(n5, n6, n7, n8, this.X[9], 13);
        n8 = this.FF4(n8, n5, n6, n7, this.X[2], 15);
        n7 = this.FF4(n7, n8, n5, n6, this.X[11], 15);
        n6 = this.FF4(n6, n7, n8, n5, this.X[4], 5);
        n5 = this.FF4(n5, n6, n7, n8, this.X[13], 7);
        n8 = this.FF4(n8, n5, n6, n7, this.X[6], 7);
        n7 = this.FF4(n7, n8, n5, n6, this.X[15], 8);
        n6 = this.FF4(n6, n7, n8, n5, this.X[8], 11);
        n5 = this.FF4(n5, n6, n7, n8, this.X[1], 14);
        n8 = this.FF4(n8, n5, n6, n7, this.X[10], 14);
        n7 = this.FF4(n7, n8, n5, n6, this.X[3], 12);
        n6 = this.FF4(n6, n7, n8, n5, this.X[12], 6);
        int n9 = n;
        n = n5;
        n5 = n9;
        n = this.F2(n, n2, n3, n4, this.X[7], 7);
        n4 = this.F2(n4, n, n2, n3, this.X[4], 6);
        n3 = this.F2(n3, n4, n, n2, this.X[13], 8);
        n2 = this.F2(n2, n3, n4, n, this.X[1], 13);
        n = this.F2(n, n2, n3, n4, this.X[10], 11);
        n4 = this.F2(n4, n, n2, n3, this.X[6], 9);
        n3 = this.F2(n3, n4, n, n2, this.X[15], 7);
        n2 = this.F2(n2, n3, n4, n, this.X[3], 15);
        n = this.F2(n, n2, n3, n4, this.X[12], 7);
        n4 = this.F2(n4, n, n2, n3, this.X[0], 12);
        n3 = this.F2(n3, n4, n, n2, this.X[9], 15);
        n2 = this.F2(n2, n3, n4, n, this.X[5], 9);
        n = this.F2(n, n2, n3, n4, this.X[2], 11);
        n4 = this.F2(n4, n, n2, n3, this.X[14], 7);
        n3 = this.F2(n3, n4, n, n2, this.X[11], 13);
        n2 = this.F2(n2, n3, n4, n, this.X[8], 12);
        n5 = this.FF3(n5, n6, n7, n8, this.X[6], 9);
        n8 = this.FF3(n8, n5, n6, n7, this.X[11], 13);
        n7 = this.FF3(n7, n8, n5, n6, this.X[3], 15);
        n6 = this.FF3(n6, n7, n8, n5, this.X[7], 7);
        n5 = this.FF3(n5, n6, n7, n8, this.X[0], 12);
        n8 = this.FF3(n8, n5, n6, n7, this.X[13], 8);
        n7 = this.FF3(n7, n8, n5, n6, this.X[5], 9);
        n6 = this.FF3(n6, n7, n8, n5, this.X[10], 11);
        n5 = this.FF3(n5, n6, n7, n8, this.X[14], 7);
        n8 = this.FF3(n8, n5, n6, n7, this.X[15], 7);
        n7 = this.FF3(n7, n8, n5, n6, this.X[8], 12);
        n6 = this.FF3(n6, n7, n8, n5, this.X[12], 7);
        n5 = this.FF3(n5, n6, n7, n8, this.X[4], 6);
        n8 = this.FF3(n8, n5, n6, n7, this.X[9], 15);
        n7 = this.FF3(n7, n8, n5, n6, this.X[1], 13);
        n6 = this.FF3(n6, n7, n8, n5, this.X[2], 11);
        n9 = n2;
        n2 = n6;
        n6 = n9;
        n = this.F3(n, n2, n3, n4, this.X[3], 11);
        n4 = this.F3(n4, n, n2, n3, this.X[10], 13);
        n3 = this.F3(n3, n4, n, n2, this.X[14], 6);
        n2 = this.F3(n2, n3, n4, n, this.X[4], 7);
        n = this.F3(n, n2, n3, n4, this.X[9], 14);
        n4 = this.F3(n4, n, n2, n3, this.X[15], 9);
        n3 = this.F3(n3, n4, n, n2, this.X[8], 13);
        n2 = this.F3(n2, n3, n4, n, this.X[1], 15);
        n = this.F3(n, n2, n3, n4, this.X[2], 14);
        n4 = this.F3(n4, n, n2, n3, this.X[7], 8);
        n3 = this.F3(n3, n4, n, n2, this.X[0], 13);
        n2 = this.F3(n2, n3, n4, n, this.X[6], 6);
        n = this.F3(n, n2, n3, n4, this.X[13], 5);
        n4 = this.F3(n4, n, n2, n3, this.X[11], 12);
        n3 = this.F3(n3, n4, n, n2, this.X[5], 7);
        n2 = this.F3(n2, n3, n4, n, this.X[12], 5);
        n5 = this.FF2(n5, n6, n7, n8, this.X[15], 9);
        n8 = this.FF2(n8, n5, n6, n7, this.X[5], 7);
        n7 = this.FF2(n7, n8, n5, n6, this.X[1], 15);
        n6 = this.FF2(n6, n7, n8, n5, this.X[3], 11);
        n5 = this.FF2(n5, n6, n7, n8, this.X[7], 8);
        n8 = this.FF2(n8, n5, n6, n7, this.X[14], 6);
        n7 = this.FF2(n7, n8, n5, n6, this.X[6], 6);
        n6 = this.FF2(n6, n7, n8, n5, this.X[9], 14);
        n5 = this.FF2(n5, n6, n7, n8, this.X[11], 12);
        n8 = this.FF2(n8, n5, n6, n7, this.X[8], 13);
        n7 = this.FF2(n7, n8, n5, n6, this.X[12], 5);
        n6 = this.FF2(n6, n7, n8, n5, this.X[2], 14);
        n5 = this.FF2(n5, n6, n7, n8, this.X[10], 13);
        n8 = this.FF2(n8, n5, n6, n7, this.X[0], 13);
        n7 = this.FF2(n7, n8, n5, n6, this.X[4], 7);
        n6 = this.FF2(n6, n7, n8, n5, this.X[13], 5);
        n9 = n3;
        n3 = n7;
        n7 = n9;
        n = this.F4(n, n2, n3, n4, this.X[1], 11);
        n4 = this.F4(n4, n, n2, n3, this.X[9], 12);
        n3 = this.F4(n3, n4, n, n2, this.X[11], 14);
        n2 = this.F4(n2, n3, n4, n, this.X[10], 15);
        n = this.F4(n, n2, n3, n4, this.X[0], 14);
        n4 = this.F4(n4, n, n2, n3, this.X[8], 15);
        n3 = this.F4(n3, n4, n, n2, this.X[12], 9);
        n2 = this.F4(n2, n3, n4, n, this.X[4], 8);
        n = this.F4(n, n2, n3, n4, this.X[13], 9);
        n4 = this.F4(n4, n, n2, n3, this.X[3], 14);
        n3 = this.F4(n3, n4, n, n2, this.X[7], 5);
        n2 = this.F4(n2, n3, n4, n, this.X[15], 6);
        n = this.F4(n, n2, n3, n4, this.X[14], 8);
        n4 = this.F4(n4, n, n2, n3, this.X[5], 6);
        n3 = this.F4(n3, n4, n, n2, this.X[6], 5);
        n2 = this.F4(n2, n3, n4, n, this.X[2], 12);
        n5 = this.FF1(n5, n6, n7, n8, this.X[8], 15);
        n8 = this.FF1(n8, n5, n6, n7, this.X[6], 5);
        n7 = this.FF1(n7, n8, n5, n6, this.X[4], 8);
        n6 = this.FF1(n6, n7, n8, n5, this.X[1], 11);
        n5 = this.FF1(n5, n6, n7, n8, this.X[3], 14);
        n8 = this.FF1(n8, n5, n6, n7, this.X[11], 14);
        n7 = this.FF1(n7, n8, n5, n6, this.X[15], 6);
        n6 = this.FF1(n6, n7, n8, n5, this.X[0], 14);
        n5 = this.FF1(n5, n6, n7, n8, this.X[5], 6);
        n8 = this.FF1(n8, n5, n6, n7, this.X[12], 9);
        n7 = this.FF1(n7, n8, n5, n6, this.X[2], 12);
        n6 = this.FF1(n6, n7, n8, n5, this.X[13], 9);
        n5 = this.FF1(n5, n6, n7, n8, this.X[9], 12);
        n8 = this.FF1(n8, n5, n6, n7, this.X[7], 5);
        n7 = this.FF1(n7, n8, n5, n6, this.X[10], 15);
        n6 = this.FF1(n6, n7, n8, n5, this.X[14], 8);
        n9 = n4;
        n4 = n8;
        n8 = n9;
        this.H0 += n;
        this.H1 += n2;
        this.H2 += n3;
        this.H3 += n4;
        this.H4 += n5;
        this.H5 += n6;
        this.H6 += n7;
        this.H7 += n8;
        this.xOff = 0;
        for (int i = 0; i != this.X.length; ++i) {
            this.X[i] = 0;
        }
    }

    public Memoable copy() {
        return new RIPEMD256Digest(this);
    }

    public void reset(Memoable memoable) {
        RIPEMD256Digest rIPEMD256Digest = (RIPEMD256Digest)memoable;
        this.copyIn(rIPEMD256Digest);
    }
}

