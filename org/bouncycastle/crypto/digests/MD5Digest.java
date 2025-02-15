/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.digests.EncodableDigest;
import org.bouncycastle.crypto.digests.GeneralDigest;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class MD5Digest
extends GeneralDigest
implements EncodableDigest {
    private static final int DIGEST_LENGTH = 16;
    private int H1;
    private int H2;
    private int H3;
    private int H4;
    private int[] X = new int[16];
    private int xOff;
    private static final int S11 = 7;
    private static final int S12 = 12;
    private static final int S13 = 17;
    private static final int S14 = 22;
    private static final int S21 = 5;
    private static final int S22 = 9;
    private static final int S23 = 14;
    private static final int S24 = 20;
    private static final int S31 = 4;
    private static final int S32 = 11;
    private static final int S33 = 16;
    private static final int S34 = 23;
    private static final int S41 = 6;
    private static final int S42 = 10;
    private static final int S43 = 15;
    private static final int S44 = 21;

    public MD5Digest() {
        this.reset();
    }

    public MD5Digest(byte[] byArray) {
        super(byArray);
        this.H1 = Pack.bigEndianToInt(byArray, 16);
        this.H2 = Pack.bigEndianToInt(byArray, 20);
        this.H3 = Pack.bigEndianToInt(byArray, 24);
        this.H4 = Pack.bigEndianToInt(byArray, 28);
        this.xOff = Pack.bigEndianToInt(byArray, 32);
        for (int i = 0; i != this.xOff; ++i) {
            this.X[i] = Pack.bigEndianToInt(byArray, 36 + i * 4);
        }
    }

    public MD5Digest(MD5Digest mD5Digest) {
        super(mD5Digest);
        this.copyIn(mD5Digest);
    }

    private void copyIn(MD5Digest mD5Digest) {
        super.copyIn(mD5Digest);
        this.H1 = mD5Digest.H1;
        this.H2 = mD5Digest.H2;
        this.H3 = mD5Digest.H3;
        this.H4 = mD5Digest.H4;
        System.arraycopy(mD5Digest.X, 0, this.X, 0, mD5Digest.X.length);
        this.xOff = mD5Digest.xOff;
    }

    public String getAlgorithmName() {
        return "MD5";
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
        return n & n3 | n2 & ~n3;
    }

    private int H(int n, int n2, int n3) {
        return n ^ n2 ^ n3;
    }

    private int K(int n, int n2, int n3) {
        return n2 ^ (n | ~n3);
    }

    protected void processBlock() {
        int n = this.H1;
        int n2 = this.H2;
        int n3 = this.H3;
        int n4 = this.H4;
        n = this.rotateLeft(n + this.F(n2, n3, n4) + this.X[0] + -680876936, 7) + n2;
        n4 = this.rotateLeft(n4 + this.F(n, n2, n3) + this.X[1] + -389564586, 12) + n;
        n3 = this.rotateLeft(n3 + this.F(n4, n, n2) + this.X[2] + 606105819, 17) + n4;
        n2 = this.rotateLeft(n2 + this.F(n3, n4, n) + this.X[3] + -1044525330, 22) + n3;
        n = this.rotateLeft(n + this.F(n2, n3, n4) + this.X[4] + -176418897, 7) + n2;
        n4 = this.rotateLeft(n4 + this.F(n, n2, n3) + this.X[5] + 1200080426, 12) + n;
        n3 = this.rotateLeft(n3 + this.F(n4, n, n2) + this.X[6] + -1473231341, 17) + n4;
        n2 = this.rotateLeft(n2 + this.F(n3, n4, n) + this.X[7] + -45705983, 22) + n3;
        n = this.rotateLeft(n + this.F(n2, n3, n4) + this.X[8] + 1770035416, 7) + n2;
        n4 = this.rotateLeft(n4 + this.F(n, n2, n3) + this.X[9] + -1958414417, 12) + n;
        n3 = this.rotateLeft(n3 + this.F(n4, n, n2) + this.X[10] + -42063, 17) + n4;
        n2 = this.rotateLeft(n2 + this.F(n3, n4, n) + this.X[11] + -1990404162, 22) + n3;
        n = this.rotateLeft(n + this.F(n2, n3, n4) + this.X[12] + 1804603682, 7) + n2;
        n4 = this.rotateLeft(n4 + this.F(n, n2, n3) + this.X[13] + -40341101, 12) + n;
        n3 = this.rotateLeft(n3 + this.F(n4, n, n2) + this.X[14] + -1502002290, 17) + n4;
        n2 = this.rotateLeft(n2 + this.F(n3, n4, n) + this.X[15] + 1236535329, 22) + n3;
        n = this.rotateLeft(n + this.G(n2, n3, n4) + this.X[1] + -165796510, 5) + n2;
        n4 = this.rotateLeft(n4 + this.G(n, n2, n3) + this.X[6] + -1069501632, 9) + n;
        n3 = this.rotateLeft(n3 + this.G(n4, n, n2) + this.X[11] + 643717713, 14) + n4;
        n2 = this.rotateLeft(n2 + this.G(n3, n4, n) + this.X[0] + -373897302, 20) + n3;
        n = this.rotateLeft(n + this.G(n2, n3, n4) + this.X[5] + -701558691, 5) + n2;
        n4 = this.rotateLeft(n4 + this.G(n, n2, n3) + this.X[10] + 38016083, 9) + n;
        n3 = this.rotateLeft(n3 + this.G(n4, n, n2) + this.X[15] + -660478335, 14) + n4;
        n2 = this.rotateLeft(n2 + this.G(n3, n4, n) + this.X[4] + -405537848, 20) + n3;
        n = this.rotateLeft(n + this.G(n2, n3, n4) + this.X[9] + 568446438, 5) + n2;
        n4 = this.rotateLeft(n4 + this.G(n, n2, n3) + this.X[14] + -1019803690, 9) + n;
        n3 = this.rotateLeft(n3 + this.G(n4, n, n2) + this.X[3] + -187363961, 14) + n4;
        n2 = this.rotateLeft(n2 + this.G(n3, n4, n) + this.X[8] + 1163531501, 20) + n3;
        n = this.rotateLeft(n + this.G(n2, n3, n4) + this.X[13] + -1444681467, 5) + n2;
        n4 = this.rotateLeft(n4 + this.G(n, n2, n3) + this.X[2] + -51403784, 9) + n;
        n3 = this.rotateLeft(n3 + this.G(n4, n, n2) + this.X[7] + 1735328473, 14) + n4;
        n2 = this.rotateLeft(n2 + this.G(n3, n4, n) + this.X[12] + -1926607734, 20) + n3;
        n = this.rotateLeft(n + this.H(n2, n3, n4) + this.X[5] + -378558, 4) + n2;
        n4 = this.rotateLeft(n4 + this.H(n, n2, n3) + this.X[8] + -2022574463, 11) + n;
        n3 = this.rotateLeft(n3 + this.H(n4, n, n2) + this.X[11] + 1839030562, 16) + n4;
        n2 = this.rotateLeft(n2 + this.H(n3, n4, n) + this.X[14] + -35309556, 23) + n3;
        n = this.rotateLeft(n + this.H(n2, n3, n4) + this.X[1] + -1530992060, 4) + n2;
        n4 = this.rotateLeft(n4 + this.H(n, n2, n3) + this.X[4] + 1272893353, 11) + n;
        n3 = this.rotateLeft(n3 + this.H(n4, n, n2) + this.X[7] + -155497632, 16) + n4;
        n2 = this.rotateLeft(n2 + this.H(n3, n4, n) + this.X[10] + -1094730640, 23) + n3;
        n = this.rotateLeft(n + this.H(n2, n3, n4) + this.X[13] + 681279174, 4) + n2;
        n4 = this.rotateLeft(n4 + this.H(n, n2, n3) + this.X[0] + -358537222, 11) + n;
        n3 = this.rotateLeft(n3 + this.H(n4, n, n2) + this.X[3] + -722521979, 16) + n4;
        n2 = this.rotateLeft(n2 + this.H(n3, n4, n) + this.X[6] + 76029189, 23) + n3;
        n = this.rotateLeft(n + this.H(n2, n3, n4) + this.X[9] + -640364487, 4) + n2;
        n4 = this.rotateLeft(n4 + this.H(n, n2, n3) + this.X[12] + -421815835, 11) + n;
        n3 = this.rotateLeft(n3 + this.H(n4, n, n2) + this.X[15] + 530742520, 16) + n4;
        n2 = this.rotateLeft(n2 + this.H(n3, n4, n) + this.X[2] + -995338651, 23) + n3;
        n = this.rotateLeft(n + this.K(n2, n3, n4) + this.X[0] + -198630844, 6) + n2;
        n4 = this.rotateLeft(n4 + this.K(n, n2, n3) + this.X[7] + 1126891415, 10) + n;
        n3 = this.rotateLeft(n3 + this.K(n4, n, n2) + this.X[14] + -1416354905, 15) + n4;
        n2 = this.rotateLeft(n2 + this.K(n3, n4, n) + this.X[5] + -57434055, 21) + n3;
        n = this.rotateLeft(n + this.K(n2, n3, n4) + this.X[12] + 1700485571, 6) + n2;
        n4 = this.rotateLeft(n4 + this.K(n, n2, n3) + this.X[3] + -1894986606, 10) + n;
        n3 = this.rotateLeft(n3 + this.K(n4, n, n2) + this.X[10] + -1051523, 15) + n4;
        n2 = this.rotateLeft(n2 + this.K(n3, n4, n) + this.X[1] + -2054922799, 21) + n3;
        n = this.rotateLeft(n + this.K(n2, n3, n4) + this.X[8] + 1873313359, 6) + n2;
        n4 = this.rotateLeft(n4 + this.K(n, n2, n3) + this.X[15] + -30611744, 10) + n;
        n3 = this.rotateLeft(n3 + this.K(n4, n, n2) + this.X[6] + -1560198380, 15) + n4;
        n2 = this.rotateLeft(n2 + this.K(n3, n4, n) + this.X[13] + 1309151649, 21) + n3;
        n = this.rotateLeft(n + this.K(n2, n3, n4) + this.X[4] + -145523070, 6) + n2;
        n4 = this.rotateLeft(n4 + this.K(n, n2, n3) + this.X[11] + -1120210379, 10) + n;
        n3 = this.rotateLeft(n3 + this.K(n4, n, n2) + this.X[2] + 718787259, 15) + n4;
        n2 = this.rotateLeft(n2 + this.K(n3, n4, n) + this.X[9] + -343485551, 21) + n3;
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
        return new MD5Digest(this);
    }

    public void reset(Memoable memoable) {
        MD5Digest mD5Digest = (MD5Digest)memoable;
        this.copyIn(mD5Digest);
    }

    public byte[] getEncodedState() {
        byte[] byArray = new byte[36 + this.xOff * 4];
        super.populateState(byArray);
        Pack.intToBigEndian(this.H1, byArray, 16);
        Pack.intToBigEndian(this.H2, byArray, 20);
        Pack.intToBigEndian(this.H3, byArray, 24);
        Pack.intToBigEndian(this.H4, byArray, 28);
        Pack.intToBigEndian(this.xOff, byArray, 32);
        for (int i = 0; i != this.xOff; ++i) {
            Pack.intToBigEndian(this.X[i], byArray, 36 + i * 4);
        }
        return byArray;
    }
}

