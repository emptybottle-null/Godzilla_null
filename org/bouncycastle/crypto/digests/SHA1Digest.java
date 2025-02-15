/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.digests.EncodableDigest;
import org.bouncycastle.crypto.digests.GeneralDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class SHA1Digest
extends GeneralDigest
implements EncodableDigest {
    private static final int DIGEST_LENGTH = 20;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int H5;
    private int[] X = new int[80];
    private int xOff;
    private static final int Y1 = 1518500249;
    private static final int Y2 = 1859775393;
    private static final int Y3 = -1894007588;
    private static final int Y4 = -899497514;

    public SHA1Digest() {
        this.reset();
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super(sHA1Digest);
        this.copyIn(sHA1Digest);
    }

    public SHA1Digest(byte[] byArray) {
        super(byArray);
        this.H1 = Pack.bigEndianToInt(byArray, 16);
        this.H2 = Pack.bigEndianToInt(byArray, 20);
        this.H3 = Pack.bigEndianToInt(byArray, 24);
        this.H4 = Pack.bigEndianToInt(byArray, 28);
        this.H5 = Pack.bigEndianToInt(byArray, 32);
        this.xOff = Pack.bigEndianToInt(byArray, 36);
        for (int i = 0; i != this.xOff; ++i) {
            this.X[i] = Pack.bigEndianToInt(byArray, 40 + i * 4);
        }
    }

    private void copyIn(SHA1Digest sHA1Digest) {
        this.H1 = sHA1Digest.H1;
        this.H2 = sHA1Digest.H2;
        this.H3 = sHA1Digest.H3;
        this.H4 = sHA1Digest.H4;
        this.H5 = sHA1Digest.H5;
        System.arraycopy(sHA1Digest.X, 0, this.X, 0, sHA1Digest.X.length);
        this.xOff = sHA1Digest.xOff;
    }

    public String getAlgorithmName() {
        return "SHA-1";
    }

    public int getDigestSize() {
        return 20;
    }

    protected void processWord(byte[] byArray, int n) {
        int n2 = byArray[n] << 24;
        n2 |= (byArray[++n] & 0xFF) << 16;
        n2 |= (byArray[++n] & 0xFF) << 8;
        this.X[this.xOff] = n2 |= byArray[++n] & 0xFF;
        if (++this.xOff == 16) {
            this.processBlock();
        }
    }

    protected void processLength(long l) {
        if (this.xOff > 14) {
            this.processBlock();
        }
        this.X[14] = (int)(l >>> 32);
        this.X[15] = (int)(l & 0xFFFFFFFFFFFFFFFFL);
    }

    public int doFinal(byte[] byArray, int n) {
        this.finish();
        Pack.intToBigEndian(this.H1, byArray, n);
        Pack.intToBigEndian(this.H2, byArray, n + 4);
        Pack.intToBigEndian(this.H3, byArray, n + 8);
        Pack.intToBigEndian(this.H4, byArray, n + 12);
        Pack.intToBigEndian(this.H5, byArray, n + 16);
        this.reset();
        return 20;
    }

    public void reset() {
        super.reset();
        this.H1 = 1732584193;
        this.H2 = -271733879;
        this.H3 = -1732584194;
        this.H4 = 271733878;
        this.H5 = -1009589776;
        this.xOff = 0;
        for (int i = 0; i != this.X.length; ++i) {
            this.X[i] = 0;
        }
    }

    private int f(int n, int n2, int n3) {
        return n & n2 | ~n & n3;
    }

    private int h(int n, int n2, int n3) {
        return n ^ n2 ^ n3;
    }

    private int g(int n, int n2, int n3) {
        return n & n2 | n & n3 | n2 & n3;
    }

    protected void processBlock() {
        int n;
        int n2;
        int n3;
        for (n3 = 16; n3 < 80; ++n3) {
            n2 = this.X[n3 - 3] ^ this.X[n3 - 8] ^ this.X[n3 - 14] ^ this.X[n3 - 16];
            this.X[n3] = n2 << 1 | n2 >>> 31;
        }
        n3 = this.H1;
        n2 = this.H2;
        int n4 = this.H3;
        int n5 = this.H4;
        int n6 = this.H5;
        int n7 = 0;
        for (n = 0; n < 4; ++n) {
            n6 += (n3 << 5 | n3 >>> 27) + this.f(n2, n4, n5) + this.X[n7++] + 1518500249;
            n2 = n2 << 30 | n2 >>> 2;
            n5 += (n6 << 5 | n6 >>> 27) + this.f(n3, n2, n4) + this.X[n7++] + 1518500249;
            n3 = n3 << 30 | n3 >>> 2;
            n4 += (n5 << 5 | n5 >>> 27) + this.f(n6, n3, n2) + this.X[n7++] + 1518500249;
            n6 = n6 << 30 | n6 >>> 2;
            n2 += (n4 << 5 | n4 >>> 27) + this.f(n5, n6, n3) + this.X[n7++] + 1518500249;
            n5 = n5 << 30 | n5 >>> 2;
            n3 += (n2 << 5 | n2 >>> 27) + this.f(n4, n5, n6) + this.X[n7++] + 1518500249;
            n4 = n4 << 30 | n4 >>> 2;
        }
        for (n = 0; n < 4; ++n) {
            n6 += (n3 << 5 | n3 >>> 27) + this.h(n2, n4, n5) + this.X[n7++] + 1859775393;
            n2 = n2 << 30 | n2 >>> 2;
            n5 += (n6 << 5 | n6 >>> 27) + this.h(n3, n2, n4) + this.X[n7++] + 1859775393;
            n3 = n3 << 30 | n3 >>> 2;
            n4 += (n5 << 5 | n5 >>> 27) + this.h(n6, n3, n2) + this.X[n7++] + 1859775393;
            n6 = n6 << 30 | n6 >>> 2;
            n2 += (n4 << 5 | n4 >>> 27) + this.h(n5, n6, n3) + this.X[n7++] + 1859775393;
            n5 = n5 << 30 | n5 >>> 2;
            n3 += (n2 << 5 | n2 >>> 27) + this.h(n4, n5, n6) + this.X[n7++] + 1859775393;
            n4 = n4 << 30 | n4 >>> 2;
        }
        for (n = 0; n < 4; ++n) {
            n6 += (n3 << 5 | n3 >>> 27) + this.g(n2, n4, n5) + this.X[n7++] + -1894007588;
            n2 = n2 << 30 | n2 >>> 2;
            n5 += (n6 << 5 | n6 >>> 27) + this.g(n3, n2, n4) + this.X[n7++] + -1894007588;
            n3 = n3 << 30 | n3 >>> 2;
            n4 += (n5 << 5 | n5 >>> 27) + this.g(n6, n3, n2) + this.X[n7++] + -1894007588;
            n6 = n6 << 30 | n6 >>> 2;
            n2 += (n4 << 5 | n4 >>> 27) + this.g(n5, n6, n3) + this.X[n7++] + -1894007588;
            n5 = n5 << 30 | n5 >>> 2;
            n3 += (n2 << 5 | n2 >>> 27) + this.g(n4, n5, n6) + this.X[n7++] + -1894007588;
            n4 = n4 << 30 | n4 >>> 2;
        }
        for (n = 0; n <= 3; ++n) {
            n6 += (n3 << 5 | n3 >>> 27) + this.h(n2, n4, n5) + this.X[n7++] + -899497514;
            n2 = n2 << 30 | n2 >>> 2;
            n5 += (n6 << 5 | n6 >>> 27) + this.h(n3, n2, n4) + this.X[n7++] + -899497514;
            n3 = n3 << 30 | n3 >>> 2;
            n4 += (n5 << 5 | n5 >>> 27) + this.h(n6, n3, n2) + this.X[n7++] + -899497514;
            n6 = n6 << 30 | n6 >>> 2;
            n2 += (n4 << 5 | n4 >>> 27) + this.h(n5, n6, n3) + this.X[n7++] + -899497514;
            n5 = n5 << 30 | n5 >>> 2;
            n3 += (n2 << 5 | n2 >>> 27) + this.h(n4, n5, n6) + this.X[n7++] + -899497514;
            n4 = n4 << 30 | n4 >>> 2;
        }
        this.H1 += n3;
        this.H2 += n2;
        this.H3 += n4;
        this.H4 += n5;
        this.H5 += n6;
        this.xOff = 0;
        for (n = 0; n < 16; ++n) {
            this.X[n] = 0;
        }
    }

    public Memoable copy() {
        return new SHA1Digest(this);
    }

    public void reset(Memoable memoable) {
        SHA1Digest sHA1Digest = (SHA1Digest)memoable;
        super.copyIn(sHA1Digest);
        this.copyIn(sHA1Digest);
    }

    public byte[] getEncodedState() {
        byte[] byArray = new byte[40 + this.xOff * 4];
        super.populateState(byArray);
        Pack.intToBigEndian(this.H1, byArray, 16);
        Pack.intToBigEndian(this.H2, byArray, 20);
        Pack.intToBigEndian(this.H3, byArray, 24);
        Pack.intToBigEndian(this.H4, byArray, 28);
        Pack.intToBigEndian(this.H5, byArray, 32);
        Pack.intToBigEndian(this.xOff, byArray, 36);
        for (int i = 0; i != this.xOff; ++i) {
            Pack.intToBigEndian(this.X[i], byArray, 40 + i * 4);
        }
        return byArray;
    }
}

