/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.digests.LongDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.MemoableResetException;
import org.bouncycastle.util.Pack;

public class SHA512tDigest
extends LongDigest {
    private int digestLength;
    private long H1t;
    private long H2t;
    private long H3t;
    private long H4t;
    private long H5t;
    private long H6t;
    private long H7t;
    private long H8t;

    public SHA512tDigest(int n) {
        if (n >= 512) {
            throw new IllegalArgumentException("bitLength cannot be >= 512");
        }
        if (n % 8 != 0) {
            throw new IllegalArgumentException("bitLength needs to be a multiple of 8");
        }
        if (n == 384) {
            throw new IllegalArgumentException("bitLength cannot be 384 use SHA384 instead");
        }
        this.digestLength = n / 8;
        this.tIvGenerate(this.digestLength * 8);
        this.reset();
    }

    public SHA512tDigest(SHA512tDigest sHA512tDigest) {
        super(sHA512tDigest);
        this.digestLength = sHA512tDigest.digestLength;
        this.reset(sHA512tDigest);
    }

    public SHA512tDigest(byte[] byArray) {
        this(SHA512tDigest.readDigestLength(byArray));
        this.restoreState(byArray);
    }

    private static int readDigestLength(byte[] byArray) {
        return Pack.bigEndianToInt(byArray, byArray.length - 4);
    }

    public String getAlgorithmName() {
        return "SHA-512/" + Integer.toString(this.digestLength * 8);
    }

    public int getDigestSize() {
        return this.digestLength;
    }

    public int doFinal(byte[] byArray, int n) {
        this.finish();
        SHA512tDigest.longToBigEndian(this.H1, byArray, n, this.digestLength);
        SHA512tDigest.longToBigEndian(this.H2, byArray, n + 8, this.digestLength - 8);
        SHA512tDigest.longToBigEndian(this.H3, byArray, n + 16, this.digestLength - 16);
        SHA512tDigest.longToBigEndian(this.H4, byArray, n + 24, this.digestLength - 24);
        SHA512tDigest.longToBigEndian(this.H5, byArray, n + 32, this.digestLength - 32);
        SHA512tDigest.longToBigEndian(this.H6, byArray, n + 40, this.digestLength - 40);
        SHA512tDigest.longToBigEndian(this.H7, byArray, n + 48, this.digestLength - 48);
        SHA512tDigest.longToBigEndian(this.H8, byArray, n + 56, this.digestLength - 56);
        this.reset();
        return this.digestLength;
    }

    public void reset() {
        super.reset();
        this.H1 = this.H1t;
        this.H2 = this.H2t;
        this.H3 = this.H3t;
        this.H4 = this.H4t;
        this.H5 = this.H5t;
        this.H6 = this.H6t;
        this.H7 = this.H7t;
        this.H8 = this.H8t;
    }

    private void tIvGenerate(int n) {
        this.H1 = -3482333909917012819L;
        this.H2 = 2216346199247487646L;
        this.H3 = -7364697282686394994L;
        this.H4 = 65953792586715988L;
        this.H5 = -816286391624063116L;
        this.H6 = 4512832404995164602L;
        this.H7 = -5033199132376557362L;
        this.H8 = -124578254951840548L;
        this.update((byte)83);
        this.update((byte)72);
        this.update((byte)65);
        this.update((byte)45);
        this.update((byte)53);
        this.update((byte)49);
        this.update((byte)50);
        this.update((byte)47);
        if (n > 100) {
            this.update((byte)(n / 100 + 48));
            this.update((byte)((n %= 100) / 10 + 48));
            this.update((byte)((n %= 10) + 48));
        } else if (n > 10) {
            this.update((byte)(n / 10 + 48));
            this.update((byte)((n %= 10) + 48));
        } else {
            this.update((byte)(n + 48));
        }
        this.finish();
        this.H1t = this.H1;
        this.H2t = this.H2;
        this.H3t = this.H3;
        this.H4t = this.H4;
        this.H5t = this.H5;
        this.H6t = this.H6;
        this.H7t = this.H7;
        this.H8t = this.H8;
    }

    private static void longToBigEndian(long l, byte[] byArray, int n, int n2) {
        if (n2 > 0) {
            SHA512tDigest.intToBigEndian((int)(l >>> 32), byArray, n, n2);
            if (n2 > 4) {
                SHA512tDigest.intToBigEndian((int)(l & 0xFFFFFFFFL), byArray, n + 4, n2 - 4);
            }
        }
    }

    private static void intToBigEndian(int n, byte[] byArray, int n2, int n3) {
        int n4 = Math.min(4, n3);
        while (--n4 >= 0) {
            int n5 = 8 * (3 - n4);
            byArray[n2 + n4] = (byte)(n >>> n5);
        }
    }

    public Memoable copy() {
        return new SHA512tDigest(this);
    }

    public void reset(Memoable memoable) {
        SHA512tDigest sHA512tDigest = (SHA512tDigest)memoable;
        if (this.digestLength != sHA512tDigest.digestLength) {
            throw new MemoableResetException("digestLength inappropriate in other");
        }
        super.copyIn(sHA512tDigest);
        this.H1t = sHA512tDigest.H1t;
        this.H2t = sHA512tDigest.H2t;
        this.H3t = sHA512tDigest.H3t;
        this.H4t = sHA512tDigest.H4t;
        this.H5t = sHA512tDigest.H5t;
        this.H6t = sHA512tDigest.H6t;
        this.H7t = sHA512tDigest.H7t;
        this.H8t = sHA512tDigest.H8t;
    }

    public byte[] getEncodedState() {
        int n = this.getEncodedStateSize();
        byte[] byArray = new byte[n + 4];
        this.populateState(byArray);
        Pack.intToBigEndian(this.digestLength * 8, byArray, n);
        return byArray;
    }
}

