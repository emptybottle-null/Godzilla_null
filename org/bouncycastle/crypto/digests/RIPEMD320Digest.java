/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.digests.GeneralDigest;
import org.bouncycastle.util.Memoable;

public class RIPEMD320Digest
extends GeneralDigest {
    private static final int DIGEST_LENGTH = 40;
    private int H0;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int H5;
    private int H6;
    private int H7;
    private int H8;
    private int H9;
    private int[] X = new int[16];
    private int xOff;

    public RIPEMD320Digest() {
        this.reset();
    }

    public RIPEMD320Digest(RIPEMD320Digest rIPEMD320Digest) {
        super(rIPEMD320Digest);
        this.doCopy(rIPEMD320Digest);
    }

    private void doCopy(RIPEMD320Digest rIPEMD320Digest) {
        super.copyIn(rIPEMD320Digest);
        this.H0 = rIPEMD320Digest.H0;
        this.H1 = rIPEMD320Digest.H1;
        this.H2 = rIPEMD320Digest.H2;
        this.H3 = rIPEMD320Digest.H3;
        this.H4 = rIPEMD320Digest.H4;
        this.H5 = rIPEMD320Digest.H5;
        this.H6 = rIPEMD320Digest.H6;
        this.H7 = rIPEMD320Digest.H7;
        this.H8 = rIPEMD320Digest.H8;
        this.H9 = rIPEMD320Digest.H9;
        System.arraycopy(rIPEMD320Digest.X, 0, this.X, 0, rIPEMD320Digest.X.length);
        this.xOff = rIPEMD320Digest.xOff;
    }

    public String getAlgorithmName() {
        return "RIPEMD320";
    }

    public int getDigestSize() {
        return 40;
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
        this.unpackWord(this.H8, byArray, n + 32);
        this.unpackWord(this.H9, byArray, n + 36);
        this.reset();
        return 40;
    }

    public void reset() {
        super.reset();
        this.H0 = 1732584193;
        this.H1 = -271733879;
        this.H2 = -1732584194;
        this.H3 = 271733878;
        this.H4 = -1009589776;
        this.H5 = 1985229328;
        this.H6 = -19088744;
        this.H7 = -1985229329;
        this.H8 = 19088743;
        this.H9 = 1009589775;
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

    private int f5(int n, int n2, int n3) {
        return n ^ (n2 | ~n3);
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
        int n9 = this.H8;
        int n10 = this.H9;
        n = this.RL(n + this.f1(n2, n3, n4) + this.X[0], 11) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f1(n, n2, n3) + this.X[1], 14) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f1(n5, n, n2) + this.X[2], 15) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f1(n4, n5, n) + this.X[3], 12) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f1(n3, n4, n5) + this.X[4], 5) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f1(n2, n3, n4) + this.X[5], 8) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f1(n, n2, n3) + this.X[6], 7) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f1(n5, n, n2) + this.X[7], 9) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f1(n4, n5, n) + this.X[8], 11) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f1(n3, n4, n5) + this.X[9], 13) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f1(n2, n3, n4) + this.X[10], 14) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f1(n, n2, n3) + this.X[11], 15) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f1(n5, n, n2) + this.X[12], 6) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f1(n4, n5, n) + this.X[13], 7) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f1(n3, n4, n5) + this.X[14], 9) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f1(n2, n3, n4) + this.X[15], 8) + n5;
        n3 = this.RL(n3, 10);
        n6 = this.RL(n6 + this.f5(n7, n8, n9) + this.X[5] + 1352829926, 8) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f5(n6, n7, n8) + this.X[14] + 1352829926, 9) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f5(n10, n6, n7) + this.X[7] + 1352829926, 9) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f5(n9, n10, n6) + this.X[0] + 1352829926, 11) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f5(n8, n9, n10) + this.X[9] + 1352829926, 13) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f5(n7, n8, n9) + this.X[2] + 1352829926, 15) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f5(n6, n7, n8) + this.X[11] + 1352829926, 15) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f5(n10, n6, n7) + this.X[4] + 1352829926, 5) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f5(n9, n10, n6) + this.X[13] + 1352829926, 7) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f5(n8, n9, n10) + this.X[6] + 1352829926, 7) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f5(n7, n8, n9) + this.X[15] + 1352829926, 8) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f5(n6, n7, n8) + this.X[8] + 1352829926, 11) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f5(n10, n6, n7) + this.X[1] + 1352829926, 14) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f5(n9, n10, n6) + this.X[10] + 1352829926, 14) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f5(n8, n9, n10) + this.X[3] + 1352829926, 12) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f5(n7, n8, n9) + this.X[12] + 1352829926, 6) + n10;
        n8 = this.RL(n8, 10);
        int n11 = n;
        n = n6;
        n6 = n11;
        n5 = this.RL(n5 + this.f2(n, n2, n3) + this.X[7] + 1518500249, 7) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f2(n5, n, n2) + this.X[4] + 1518500249, 6) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f2(n4, n5, n) + this.X[13] + 1518500249, 8) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f2(n3, n4, n5) + this.X[1] + 1518500249, 13) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f2(n2, n3, n4) + this.X[10] + 1518500249, 11) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f2(n, n2, n3) + this.X[6] + 1518500249, 9) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f2(n5, n, n2) + this.X[15] + 1518500249, 7) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f2(n4, n5, n) + this.X[3] + 1518500249, 15) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f2(n3, n4, n5) + this.X[12] + 1518500249, 7) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f2(n2, n3, n4) + this.X[0] + 1518500249, 12) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f2(n, n2, n3) + this.X[9] + 1518500249, 15) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f2(n5, n, n2) + this.X[5] + 1518500249, 9) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f2(n4, n5, n) + this.X[2] + 1518500249, 11) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f2(n3, n4, n5) + this.X[14] + 1518500249, 7) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f2(n2, n3, n4) + this.X[11] + 1518500249, 13) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f2(n, n2, n3) + this.X[8] + 1518500249, 12) + n4;
        n2 = this.RL(n2, 10);
        n10 = this.RL(n10 + this.f4(n6, n7, n8) + this.X[6] + 1548603684, 9) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f4(n10, n6, n7) + this.X[11] + 1548603684, 13) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f4(n9, n10, n6) + this.X[3] + 1548603684, 15) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f4(n8, n9, n10) + this.X[7] + 1548603684, 7) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f4(n7, n8, n9) + this.X[0] + 1548603684, 12) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f4(n6, n7, n8) + this.X[13] + 1548603684, 8) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f4(n10, n6, n7) + this.X[5] + 1548603684, 9) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f4(n9, n10, n6) + this.X[10] + 1548603684, 11) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f4(n8, n9, n10) + this.X[14] + 1548603684, 7) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f4(n7, n8, n9) + this.X[15] + 1548603684, 7) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f4(n6, n7, n8) + this.X[8] + 1548603684, 12) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f4(n10, n6, n7) + this.X[12] + 1548603684, 7) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f4(n9, n10, n6) + this.X[4] + 1548603684, 6) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f4(n8, n9, n10) + this.X[9] + 1548603684, 15) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f4(n7, n8, n9) + this.X[1] + 1548603684, 13) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f4(n6, n7, n8) + this.X[2] + 1548603684, 11) + n9;
        n7 = this.RL(n7, 10);
        n11 = n2;
        n2 = n7;
        n7 = n11;
        n4 = this.RL(n4 + this.f3(n5, n, n2) + this.X[3] + 1859775393, 11) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f3(n4, n5, n) + this.X[10] + 1859775393, 13) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f3(n3, n4, n5) + this.X[14] + 1859775393, 6) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f3(n2, n3, n4) + this.X[4] + 1859775393, 7) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f3(n, n2, n3) + this.X[9] + 1859775393, 14) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f3(n5, n, n2) + this.X[15] + 1859775393, 9) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f3(n4, n5, n) + this.X[8] + 1859775393, 13) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f3(n3, n4, n5) + this.X[1] + 1859775393, 15) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f3(n2, n3, n4) + this.X[2] + 1859775393, 14) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f3(n, n2, n3) + this.X[7] + 1859775393, 8) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f3(n5, n, n2) + this.X[0] + 1859775393, 13) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f3(n4, n5, n) + this.X[6] + 1859775393, 6) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f3(n3, n4, n5) + this.X[13] + 1859775393, 5) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f3(n2, n3, n4) + this.X[11] + 1859775393, 12) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f3(n, n2, n3) + this.X[5] + 1859775393, 7) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f3(n5, n, n2) + this.X[12] + 1859775393, 5) + n3;
        n = this.RL(n, 10);
        n9 = this.RL(n9 + this.f3(n10, n6, n7) + this.X[15] + 1836072691, 9) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f3(n9, n10, n6) + this.X[5] + 1836072691, 7) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f3(n8, n9, n10) + this.X[1] + 1836072691, 15) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f3(n7, n8, n9) + this.X[3] + 1836072691, 11) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f3(n6, n7, n8) + this.X[7] + 1836072691, 8) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f3(n10, n6, n7) + this.X[14] + 1836072691, 6) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f3(n9, n10, n6) + this.X[6] + 1836072691, 6) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f3(n8, n9, n10) + this.X[9] + 1836072691, 14) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f3(n7, n8, n9) + this.X[11] + 1836072691, 12) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f3(n6, n7, n8) + this.X[8] + 1836072691, 13) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f3(n10, n6, n7) + this.X[12] + 1836072691, 5) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f3(n9, n10, n6) + this.X[2] + 1836072691, 14) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f3(n8, n9, n10) + this.X[10] + 1836072691, 13) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f3(n7, n8, n9) + this.X[0] + 1836072691, 13) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f3(n6, n7, n8) + this.X[4] + 1836072691, 7) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f3(n10, n6, n7) + this.X[13] + 1836072691, 5) + n8;
        n6 = this.RL(n6, 10);
        n11 = n3;
        n3 = n8;
        n8 = n11;
        n3 = this.RL(n3 + this.f4(n4, n5, n) + this.X[1] + -1894007588, 11) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f4(n3, n4, n5) + this.X[9] + -1894007588, 12) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f4(n2, n3, n4) + this.X[11] + -1894007588, 14) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f4(n, n2, n3) + this.X[10] + -1894007588, 15) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f4(n5, n, n2) + this.X[0] + -1894007588, 14) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f4(n4, n5, n) + this.X[8] + -1894007588, 15) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f4(n3, n4, n5) + this.X[12] + -1894007588, 9) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f4(n2, n3, n4) + this.X[4] + -1894007588, 8) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f4(n, n2, n3) + this.X[13] + -1894007588, 9) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f4(n5, n, n2) + this.X[3] + -1894007588, 14) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f4(n4, n5, n) + this.X[7] + -1894007588, 5) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f4(n3, n4, n5) + this.X[15] + -1894007588, 6) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f4(n2, n3, n4) + this.X[14] + -1894007588, 8) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f4(n, n2, n3) + this.X[5] + -1894007588, 6) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f4(n5, n, n2) + this.X[6] + -1894007588, 5) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f4(n4, n5, n) + this.X[2] + -1894007588, 12) + n2;
        n5 = this.RL(n5, 10);
        n8 = this.RL(n8 + this.f2(n9, n10, n6) + this.X[8] + 2053994217, 15) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f2(n8, n9, n10) + this.X[6] + 2053994217, 5) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f2(n7, n8, n9) + this.X[4] + 2053994217, 8) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f2(n6, n7, n8) + this.X[1] + 2053994217, 11) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f2(n10, n6, n7) + this.X[3] + 2053994217, 14) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f2(n9, n10, n6) + this.X[11] + 2053994217, 14) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f2(n8, n9, n10) + this.X[15] + 2053994217, 6) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f2(n7, n8, n9) + this.X[0] + 2053994217, 14) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f2(n6, n7, n8) + this.X[5] + 2053994217, 6) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f2(n10, n6, n7) + this.X[12] + 2053994217, 9) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f2(n9, n10, n6) + this.X[2] + 2053994217, 12) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f2(n8, n9, n10) + this.X[13] + 2053994217, 9) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f2(n7, n8, n9) + this.X[9] + 2053994217, 12) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f2(n6, n7, n8) + this.X[7] + 2053994217, 5) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f2(n10, n6, n7) + this.X[10] + 2053994217, 15) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f2(n9, n10, n6) + this.X[14] + 2053994217, 8) + n7;
        n10 = this.RL(n10, 10);
        n11 = n4;
        n4 = n9;
        n9 = n11;
        n2 = this.RL(n2 + this.f5(n3, n4, n5) + this.X[4] + -1454113458, 9) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f5(n2, n3, n4) + this.X[0] + -1454113458, 15) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f5(n, n2, n3) + this.X[5] + -1454113458, 5) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f5(n5, n, n2) + this.X[9] + -1454113458, 11) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f5(n4, n5, n) + this.X[7] + -1454113458, 6) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f5(n3, n4, n5) + this.X[12] + -1454113458, 8) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f5(n2, n3, n4) + this.X[2] + -1454113458, 13) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f5(n, n2, n3) + this.X[10] + -1454113458, 12) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f5(n5, n, n2) + this.X[14] + -1454113458, 5) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f5(n4, n5, n) + this.X[1] + -1454113458, 12) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f5(n3, n4, n5) + this.X[3] + -1454113458, 13) + n;
        n4 = this.RL(n4, 10);
        n = this.RL(n + this.f5(n2, n3, n4) + this.X[8] + -1454113458, 14) + n5;
        n3 = this.RL(n3, 10);
        n5 = this.RL(n5 + this.f5(n, n2, n3) + this.X[11] + -1454113458, 11) + n4;
        n2 = this.RL(n2, 10);
        n4 = this.RL(n4 + this.f5(n5, n, n2) + this.X[6] + -1454113458, 8) + n3;
        n = this.RL(n, 10);
        n3 = this.RL(n3 + this.f5(n4, n5, n) + this.X[15] + -1454113458, 5) + n2;
        n5 = this.RL(n5, 10);
        n2 = this.RL(n2 + this.f5(n3, n4, n5) + this.X[13] + -1454113458, 6) + n;
        n4 = this.RL(n4, 10);
        n7 = this.RL(n7 + this.f1(n8, n9, n10) + this.X[12], 8) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f1(n7, n8, n9) + this.X[15], 5) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f1(n6, n7, n8) + this.X[10], 12) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f1(n10, n6, n7) + this.X[4], 9) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f1(n9, n10, n6) + this.X[1], 12) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f1(n8, n9, n10) + this.X[5], 5) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f1(n7, n8, n9) + this.X[8], 14) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f1(n6, n7, n8) + this.X[7], 6) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f1(n10, n6, n7) + this.X[6], 8) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f1(n9, n10, n6) + this.X[2], 13) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f1(n8, n9, n10) + this.X[13], 6) + n6;
        n9 = this.RL(n9, 10);
        n6 = this.RL(n6 + this.f1(n7, n8, n9) + this.X[14], 5) + n10;
        n8 = this.RL(n8, 10);
        n10 = this.RL(n10 + this.f1(n6, n7, n8) + this.X[0], 15) + n9;
        n7 = this.RL(n7, 10);
        n9 = this.RL(n9 + this.f1(n10, n6, n7) + this.X[3], 13) + n8;
        n6 = this.RL(n6, 10);
        n8 = this.RL(n8 + this.f1(n9, n10, n6) + this.X[9], 11) + n7;
        n10 = this.RL(n10, 10);
        n7 = this.RL(n7 + this.f1(n8, n9, n10) + this.X[11], 11) + n6;
        n9 = this.RL(n9, 10);
        this.H0 += n;
        this.H1 += n2;
        this.H2 += n3;
        this.H3 += n4;
        this.H4 += n10;
        this.H5 += n6;
        this.H6 += n7;
        this.H7 += n8;
        this.H8 += n9;
        this.H9 += n5;
        this.xOff = 0;
        for (int i = 0; i != this.X.length; ++i) {
            this.X[i] = 0;
        }
    }

    public Memoable copy() {
        return new RIPEMD320Digest(this);
    }

    public void reset(Memoable memoable) {
        RIPEMD320Digest rIPEMD320Digest = (RIPEMD320Digest)memoable;
        this.doCopy(rIPEMD320Digest);
    }
}

