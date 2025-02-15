/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Memoable;

public class MD2Digest
implements ExtendedDigest,
Memoable {
    private static final int DIGEST_LENGTH = 16;
    private byte[] X = new byte[48];
    private int xOff;
    private byte[] M = new byte[16];
    private int mOff;
    private byte[] C = new byte[16];
    private int COff;
    private static final byte[] S = new byte[]{41, 46, 67, -55, -94, -40, 124, 1, 61, 54, 84, -95, -20, -16, 6, 19, 98, -89, 5, -13, -64, -57, 115, -116, -104, -109, 43, -39, -68, 76, -126, -54, 30, -101, 87, 60, -3, -44, -32, 22, 103, 66, 111, 24, -118, 23, -27, 18, -66, 78, -60, -42, -38, -98, -34, 73, -96, -5, -11, -114, -69, 47, -18, 122, -87, 104, 121, -111, 21, -78, 7, 63, -108, -62, 16, -119, 11, 34, 95, 33, -128, 127, 93, -102, 90, -112, 50, 39, 53, 62, -52, -25, -65, -9, -105, 3, -1, 25, 48, -77, 72, -91, -75, -47, -41, 94, -110, 42, -84, 86, -86, -58, 79, -72, 56, -46, -106, -92, 125, -74, 118, -4, 107, -30, -100, 116, 4, -15, 69, -99, 112, 89, 100, 113, -121, 32, -122, 91, -49, 101, -26, 45, -88, 2, 27, 96, 37, -83, -82, -80, -71, -10, 28, 70, 97, 105, 52, 64, 126, 15, 85, 71, -93, 35, -35, 81, -81, 58, -61, 92, -7, -50, -70, -59, -22, 38, 44, 83, 13, 110, -123, 40, -124, 9, -45, -33, -51, -12, 65, -127, 77, 82, 106, -36, 55, -56, 108, -63, -85, -6, 36, -31, 123, 8, 12, -67, -79, 74, 120, -120, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, 29, 57, -14, -17, -73, 14, 102, 88, -48, -28, -90, 119, 114, -8, -21, 117, 75, 10, 49, 68, 80, -76, -113, -19, 31, 26, -37, -103, -115, 51, -97, 17, -125, 20};

    public MD2Digest() {
        this.reset();
    }

    public MD2Digest(MD2Digest mD2Digest) {
        this.copyIn(mD2Digest);
    }

    private void copyIn(MD2Digest mD2Digest) {
        System.arraycopy(mD2Digest.X, 0, this.X, 0, mD2Digest.X.length);
        this.xOff = mD2Digest.xOff;
        System.arraycopy(mD2Digest.M, 0, this.M, 0, mD2Digest.M.length);
        this.mOff = mD2Digest.mOff;
        System.arraycopy(mD2Digest.C, 0, this.C, 0, mD2Digest.C.length);
        this.COff = mD2Digest.COff;
    }

    public String getAlgorithmName() {
        return "MD2";
    }

    public int getDigestSize() {
        return 16;
    }

    public int doFinal(byte[] byArray, int n) {
        byte by = (byte)(this.M.length - this.mOff);
        for (int i = this.mOff; i < this.M.length; ++i) {
            this.M[i] = by;
        }
        this.processCheckSum(this.M);
        this.processBlock(this.M);
        this.processBlock(this.C);
        System.arraycopy(this.X, this.xOff, byArray, n, 16);
        this.reset();
        return 16;
    }

    public void reset() {
        int n;
        this.xOff = 0;
        for (n = 0; n != this.X.length; ++n) {
            this.X[n] = 0;
        }
        this.mOff = 0;
        for (n = 0; n != this.M.length; ++n) {
            this.M[n] = 0;
        }
        this.COff = 0;
        for (n = 0; n != this.C.length; ++n) {
            this.C[n] = 0;
        }
    }

    public void update(byte by) {
        this.M[this.mOff++] = by;
        if (this.mOff == 16) {
            this.processCheckSum(this.M);
            this.processBlock(this.M);
            this.mOff = 0;
        }
    }

    public void update(byte[] byArray, int n, int n2) {
        while (this.mOff != 0 && n2 > 0) {
            this.update(byArray[n]);
            ++n;
            --n2;
        }
        while (n2 > 16) {
            System.arraycopy(byArray, n, this.M, 0, 16);
            this.processCheckSum(this.M);
            this.processBlock(this.M);
            n2 -= 16;
            n += 16;
        }
        while (n2 > 0) {
            this.update(byArray[n]);
            ++n;
            --n2;
        }
    }

    protected void processCheckSum(byte[] byArray) {
        byte by = this.C[15];
        for (int i = 0; i < 16; ++i) {
            int n = i;
            this.C[n] = (byte)(this.C[n] ^ S[(byArray[i] ^ by) & 0xFF]);
            by = this.C[i];
        }
    }

    protected void processBlock(byte[] byArray) {
        int n;
        for (n = 0; n < 16; ++n) {
            this.X[n + 16] = byArray[n];
            this.X[n + 32] = (byte)(byArray[n] ^ this.X[n]);
        }
        n = 0;
        for (int i = 0; i < 18; ++i) {
            int n2 = 0;
            while (n2 < 48) {
                int n3 = n2++;
                byte by = (byte)(this.X[n3] ^ S[n]);
                this.X[n3] = by;
                n = by;
                n &= 0xFF;
            }
            n = (n + i) % 256;
        }
    }

    public int getByteLength() {
        return 16;
    }

    public Memoable copy() {
        return new MD2Digest(this);
    }

    public void reset(Memoable memoable) {
        MD2Digest mD2Digest = (MD2Digest)memoable;
        this.copyIn(mD2Digest);
    }
}

