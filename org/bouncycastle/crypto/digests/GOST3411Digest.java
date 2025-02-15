/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.engines.GOST28147Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class GOST3411Digest
implements ExtendedDigest,
Memoable {
    private static final int DIGEST_LENGTH = 32;
    private byte[] H = new byte[32];
    private byte[] L = new byte[32];
    private byte[] M = new byte[32];
    private byte[] Sum = new byte[32];
    private byte[][] C = new byte[4][32];
    private byte[] xBuf = new byte[32];
    private int xBufOff;
    private long byteCount;
    private BlockCipher cipher = new GOST28147Engine();
    private byte[] sBox;
    private byte[] K = new byte[32];
    byte[] a = new byte[8];
    short[] wS = new short[16];
    short[] w_S = new short[16];
    byte[] S = new byte[32];
    byte[] U = new byte[32];
    byte[] V = new byte[32];
    byte[] W = new byte[32];
    private static final byte[] C2 = new byte[]{0, -1, 0, -1, 0, -1, 0, -1, -1, 0, -1, 0, -1, 0, -1, 0, 0, -1, -1, 0, -1, 0, 0, -1, -1, 0, 0, 0, -1, -1, 0, -1};

    public GOST3411Digest() {
        this.sBox = GOST28147Engine.getSBox("D-A");
        this.cipher.init(true, new ParametersWithSBox(null, this.sBox));
        this.reset();
    }

    public GOST3411Digest(byte[] byArray) {
        this.sBox = Arrays.clone(byArray);
        this.cipher.init(true, new ParametersWithSBox(null, this.sBox));
        this.reset();
    }

    public GOST3411Digest(GOST3411Digest gOST3411Digest) {
        this.reset(gOST3411Digest);
    }

    public String getAlgorithmName() {
        return "GOST3411";
    }

    public int getDigestSize() {
        return 32;
    }

    public void update(byte by) {
        this.xBuf[this.xBufOff++] = by;
        if (this.xBufOff == this.xBuf.length) {
            this.sumByteArray(this.xBuf);
            this.processBlock(this.xBuf, 0);
            this.xBufOff = 0;
        }
        ++this.byteCount;
    }

    public void update(byte[] byArray, int n, int n2) {
        while (this.xBufOff != 0 && n2 > 0) {
            this.update(byArray[n]);
            ++n;
            --n2;
        }
        while (n2 > this.xBuf.length) {
            System.arraycopy(byArray, n, this.xBuf, 0, this.xBuf.length);
            this.sumByteArray(this.xBuf);
            this.processBlock(this.xBuf, 0);
            n += this.xBuf.length;
            n2 -= this.xBuf.length;
            this.byteCount += (long)this.xBuf.length;
        }
        while (n2 > 0) {
            this.update(byArray[n]);
            ++n;
            --n2;
        }
    }

    private byte[] P(byte[] byArray) {
        for (int i = 0; i < 8; ++i) {
            this.K[4 * i] = byArray[i];
            this.K[1 + 4 * i] = byArray[8 + i];
            this.K[2 + 4 * i] = byArray[16 + i];
            this.K[3 + 4 * i] = byArray[24 + i];
        }
        return this.K;
    }

    private byte[] A(byte[] byArray) {
        for (int i = 0; i < 8; ++i) {
            this.a[i] = (byte)(byArray[i] ^ byArray[i + 8]);
        }
        System.arraycopy(byArray, 8, byArray, 0, 24);
        System.arraycopy(this.a, 0, byArray, 24, 8);
        return byArray;
    }

    private void E(byte[] byArray, byte[] byArray2, int n, byte[] byArray3, int n2) {
        this.cipher.init(true, new KeyParameter(byArray));
        this.cipher.processBlock(byArray3, n2, byArray2, n);
    }

    private void fw(byte[] byArray) {
        this.cpyBytesToShort(byArray, this.wS);
        this.w_S[15] = (short)(this.wS[0] ^ this.wS[1] ^ this.wS[2] ^ this.wS[3] ^ this.wS[12] ^ this.wS[15]);
        System.arraycopy(this.wS, 1, this.w_S, 0, 15);
        this.cpyShortToBytes(this.w_S, byArray);
    }

    protected void processBlock(byte[] byArray, int n) {
        int n2;
        System.arraycopy(byArray, n, this.M, 0, 32);
        System.arraycopy(this.H, 0, this.U, 0, 32);
        System.arraycopy(this.M, 0, this.V, 0, 32);
        for (n2 = 0; n2 < 32; ++n2) {
            this.W[n2] = (byte)(this.U[n2] ^ this.V[n2]);
        }
        this.E(this.P(this.W), this.S, 0, this.H, 0);
        for (n2 = 1; n2 < 4; ++n2) {
            int n3;
            byte[] byArray2 = this.A(this.U);
            for (n3 = 0; n3 < 32; ++n3) {
                this.U[n3] = (byte)(byArray2[n3] ^ this.C[n2][n3]);
            }
            this.V = this.A(this.A(this.V));
            for (n3 = 0; n3 < 32; ++n3) {
                this.W[n3] = (byte)(this.U[n3] ^ this.V[n3]);
            }
            this.E(this.P(this.W), this.S, n2 * 8, this.H, n2 * 8);
        }
        for (n2 = 0; n2 < 12; ++n2) {
            this.fw(this.S);
        }
        for (n2 = 0; n2 < 32; ++n2) {
            this.S[n2] = (byte)(this.S[n2] ^ this.M[n2]);
        }
        this.fw(this.S);
        for (n2 = 0; n2 < 32; ++n2) {
            this.S[n2] = (byte)(this.H[n2] ^ this.S[n2]);
        }
        for (n2 = 0; n2 < 61; ++n2) {
            this.fw(this.S);
        }
        System.arraycopy(this.S, 0, this.H, 0, this.H.length);
    }

    private void finish() {
        Pack.longToLittleEndian(this.byteCount * 8L, this.L, 0);
        while (this.xBufOff != 0) {
            this.update((byte)0);
        }
        this.processBlock(this.L, 0);
        this.processBlock(this.Sum, 0);
    }

    public int doFinal(byte[] byArray, int n) {
        this.finish();
        System.arraycopy(this.H, 0, byArray, n, this.H.length);
        this.reset();
        return 32;
    }

    public void reset() {
        int n;
        this.byteCount = 0L;
        this.xBufOff = 0;
        for (n = 0; n < this.H.length; ++n) {
            this.H[n] = 0;
        }
        for (n = 0; n < this.L.length; ++n) {
            this.L[n] = 0;
        }
        for (n = 0; n < this.M.length; ++n) {
            this.M[n] = 0;
        }
        for (n = 0; n < this.C[1].length; ++n) {
            this.C[1][n] = 0;
        }
        for (n = 0; n < this.C[3].length; ++n) {
            this.C[3][n] = 0;
        }
        for (n = 0; n < this.Sum.length; ++n) {
            this.Sum[n] = 0;
        }
        for (n = 0; n < this.xBuf.length; ++n) {
            this.xBuf[n] = 0;
        }
        System.arraycopy(C2, 0, this.C[2], 0, C2.length);
    }

    private void sumByteArray(byte[] byArray) {
        int n = 0;
        for (int i = 0; i != this.Sum.length; ++i) {
            int n2 = (this.Sum[i] & 0xFF) + (byArray[i] & 0xFF) + n;
            this.Sum[i] = (byte)n2;
            n = n2 >>> 8;
        }
    }

    private void cpyBytesToShort(byte[] byArray, short[] sArray) {
        for (int i = 0; i < byArray.length / 2; ++i) {
            sArray[i] = (short)(byArray[i * 2 + 1] << 8 & 0xFF00 | byArray[i * 2] & 0xFF);
        }
    }

    private void cpyShortToBytes(short[] sArray, byte[] byArray) {
        for (int i = 0; i < byArray.length / 2; ++i) {
            byArray[i * 2 + 1] = (byte)(sArray[i] >> 8);
            byArray[i * 2] = (byte)sArray[i];
        }
    }

    public int getByteLength() {
        return 32;
    }

    public Memoable copy() {
        return new GOST3411Digest(this);
    }

    public void reset(Memoable memoable) {
        GOST3411Digest gOST3411Digest = (GOST3411Digest)memoable;
        this.sBox = gOST3411Digest.sBox;
        this.cipher.init(true, new ParametersWithSBox(null, this.sBox));
        this.reset();
        System.arraycopy(gOST3411Digest.H, 0, this.H, 0, gOST3411Digest.H.length);
        System.arraycopy(gOST3411Digest.L, 0, this.L, 0, gOST3411Digest.L.length);
        System.arraycopy(gOST3411Digest.M, 0, this.M, 0, gOST3411Digest.M.length);
        System.arraycopy(gOST3411Digest.Sum, 0, this.Sum, 0, gOST3411Digest.Sum.length);
        System.arraycopy(gOST3411Digest.C[1], 0, this.C[1], 0, gOST3411Digest.C[1].length);
        System.arraycopy(gOST3411Digest.C[2], 0, this.C[2], 0, gOST3411Digest.C[2].length);
        System.arraycopy(gOST3411Digest.C[3], 0, this.C[3], 0, gOST3411Digest.C[3].length);
        System.arraycopy(gOST3411Digest.xBuf, 0, this.xBuf, 0, gOST3411Digest.xBuf.length);
        this.xBufOff = gOST3411Digest.xBufOff;
        this.byteCount = gOST3411Digest.byteCount;
    }
}

