/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.digests.GeneralDigest;
import org.bouncycastle.util.Memoable;

public class MD4Digest
extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int[] X = new int[16];
    private int xOff;
    private static final int S11 = 3;
    private static final int S12 = 7;
    private static final int S13 = 11;
    private static final int S14 = 19;
    private static final int S21 = 3;
    private static final int S22 = 5;
    private static final int S23 = 9;
    private static final int S24 = 13;
    private static final int S31 = 3;
    private static final int S32 = 9;
    private static final int S33 = 11;
    private static final int S34 = 15;

    public MD4Digest() {
        this.reset();
    }

    public MD4Digest(MD4Digest mD4Digest) {
        super(mD4Digest);
        this.copyIn(mD4Digest);
    }

    private void copyIn(MD4Digest mD4Digest) {
        super.copyIn(mD4Digest);
        this.H1 = mD4Digest.H1;
        this.H2 = mD4Digest.H2;
        this.H3 = mD4Digest.H3;
        this.H4 = mD4Digest.H4;
        System.arraycopy(mD4Digest.X, 0, this.X, 0, mD4Digest.X.length);
        this.xOff = mD4Digest.xOff;
    }

    public String getAlgorithmName() {
        return "MD4";
    }

    public int getDigestSize() {
        return 16;
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
        this.unpackWord(this.H1, byArray, n);
        this.unpackWord(this.H2, byArray, n + 4);
        this.unpackWord(this.H3, byArray, n + 8);
        this.unpackWord(this.H4, byArray, n + 12);
        this.reset();
        return 16;
    }

    public void reset() {
        super.reset();
        this.H1 = 1732584193;
        this.H2 = -271733879;
        this.H3 = -1732584194;
        this.H4 = 271733878;
        this.xOff = 0;
        for (int i = 0; i != this.X.length; ++i) {
            this.X[i] = 0;
        }
    }

    private int rotateLeft(int n, int n2) {
        return n << n2 | n >>> 32 - n2;
    }

    private int F(int n, int n2, int n3) {
        return n & n2 | ~n & n3;
    }

    private int G(int n, int n2, int n3) {
        return n & n2 | n & n3 | n2 & n3;
    }

    private int H(int n, int n2, int n3) {
        return n ^ n2 ^ n3;
    }

    protected void processBlock() {
        int n = this.H1;
        int n2 = this.H2;
        int n3 = this.H3;
        int n4 = this.H4;
        n = this.rotateLeft(n + this.F(n2, n3, n4) + this.X[0], 3);
        n4 = this.rotateLeft(n4 + this.F(n, n2, n3) + this.X[1], 7);
        n3 = this.rotateLeft(n3 + this.F(n4, n, n2) + this.X[2], 11);
        n2 = this.rotateLeft(n2 + this.F(n3, n4, n) + this.X[3], 19);
        n = this.rotateLeft(n + this.F(n2, n3, n4) + this.X[4], 3);
        n4 = this.rotateLeft(n4 + this.F(n, n2, n3) + this.X[5], 7);
        n3 = this.rotateLeft(n3 + this.F(n4, n, n2) + this.X[6], 11);
        n2 = this.rotateLeft(n2 + this.F(n3, n4, n) + this.X[7], 19);
        n = this.rotateLeft(n + this.F(n2, n3, n4) + this.X[8], 3);
        n4 = this.rotateLeft(n4 + this.F(n, n2, n3) + this.X[9], 7);
        n3 = this.rotateLeft(n3 + this.F(n4, n, n2) + this.X[10], 11);
        n2 = this.rotateLeft(n2 + this.F(n3, n4, n) + this.X[11], 19);
        n = this.rotateLeft(n + this.F(n2, n3, n4) + this.X[12], 3);
        n4 = this.rotateLeft(n4 + this.F(n, n2, n3) + this.X[13], 7);
        n3 = this.rotateLeft(n3 + this.F(n4, n, n2) + this.X[14], 11);
        n2 = this.rotateLeft(n2 + this.F(n3, n4, n) + this.X[15], 19);
        n = this.rotateLeft(n + this.G(n2, n3, n4) + this.X[0] + 1518500249, 3);
        n4 = this.rotateLeft(n4 + this.G(n, n2, n3) + this.X[4] + 1518500249, 5);
        n3 = this.rotateLeft(n3 + this.G(n4, n, n2) + this.X[8] + 1518500249, 9);
        n2 = this.rotateLeft(n2 + this.G(n3, n4, n) + this.X[12] + 1518500249, 13);
        n = this.rotateLeft(n + this.G(n2, n3, n4) + this.X[1] + 1518500249, 3);
        n4 = this.rotateLeft(n4 + this.G(n, n2, n3) + this.X[5] + 1518500249, 5);
        n3 = this.rotateLeft(n3 + this.G(n4, n, n2) + this.X[9] + 1518500249, 9);
        n2 = this.rotateLeft(n2 + this.G(n3, n4, n) + this.X[13] + 1518500249, 13);
        n = this.rotateLeft(n + this.G(n2, n3, n4) + this.X[2] + 1518500249, 3);
        n4 = this.rotateLeft(n4 + this.G(n, n2, n3) + this.X[6] + 1518500249, 5);
        n3 = this.rotateLeft(n3 + this.G(n4, n, n2) + this.X[10] + 1518500249, 9);
        n2 = this.rotateLeft(n2 + this.G(n3, n4, n) + this.X[14] + 1518500249, 13);
        n = this.rotateLeft(n + this.G(n2, n3, n4) + this.X[3] + 1518500249, 3);
        n4 = this.rotateLeft(n4 + this.G(n, n2, n3) + this.X[7] + 1518500249, 5);
        n3 = this.rotateLeft(n3 + this.G(n4, n, n2) + this.X[11] + 1518500249, 9);
        n2 = this.rotateLeft(n2 + this.G(n3, n4, n) + this.X[15] + 1518500249, 13);
        n = this.rotateLeft(n + this.H(n2, n3, n4) + this.X[0] + 1859775393, 3);
        n4 = this.rotateLeft(n4 + this.H(n, n2, n3) + this.X[8] + 1859775393, 9);
        n3 = this.rotateLeft(n3 + this.H(n4, n, n2) + this.X[4] + 1859775393, 11);
        n2 = this.rotateLeft(n2 + this.H(n3, n4, n) + this.X[12] + 1859775393, 15);
        n = this.rotateLeft(n + this.H(n2, n3, n4) + this.X[2] + 1859775393, 3);
        n4 = this.rotateLeft(n4 + this.H(n, n2, n3) + this.X[10] + 1859775393, 9);
        n3 = this.rotateLeft(n3 + this.H(n4, n, n2) + this.X[6] + 1859775393, 11);
        n2 = this.rotateLeft(n2 + this.H(n3, n4, n) + this.X[14] + 1859775393, 15);
        n = this.rotateLeft(n + this.H(n2, n3, n4) + this.X[1] + 1859775393, 3);
        n4 = this.rotateLeft(n4 + this.H(n, n2, n3) + this.X[9] + 1859775393, 9);
        n3 = this.rotateLeft(n3 + this.H(n4, n, n2) + this.X[5] + 1859775393, 11);
        n2 = this.rotateLeft(n2 + this.H(n3, n4, n) + this.X[13] + 1859775393, 15);
        n = this.rotateLeft(n + this.H(n2, n3, n4) + this.X[3] + 1859775393, 3);
        n4 = this.rotateLeft(n4 + this.H(n, n2, n3) + this.X[11] + 1859775393, 9);
        n3 = this.rotateLeft(n3 + this.H(n4, n, n2) + this.X[7] + 1859775393, 11);
        n2 = this.rotateLeft(n2 + this.H(n3, n4, n) + this.X[15] + 1859775393, 15);
        this.H1 += n;
        this.H2 += n2;
        this.H3 += n3;
        this.H4 += n4;
        this.xOff = 0;
        for (int i = 0; i != this.X.length; ++i) {
            this.X[i] = 0;
        }
    }

    public Memoable copy() {
        return new MD4Digest(this);
    }

    public void reset(Memoable memoable) {
        MD4Digest mD4Digest = (MD4Digest)memoable;
        this.copyIn(mD4Digest);
    }
}

