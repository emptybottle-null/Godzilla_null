/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

public class SM4Engine
implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final byte[] Sbox = new byte[]{-42, -112, -23, -2, -52, -31, 61, -73, 22, -74, 20, -62, 40, -5, 44, 5, 43, 103, -102, 118, 42, -66, 4, -61, -86, 68, 19, 38, 73, -122, 6, -103, -100, 66, 80, -12, -111, -17, -104, 122, 51, 84, 11, 67, -19, -49, -84, 98, -28, -77, 28, -87, -55, 8, -24, -107, -128, -33, -108, -6, 117, -113, 63, -90, 71, 7, -89, -4, -13, 115, 23, -70, -125, 89, 60, 25, -26, -123, 79, -88, 104, 107, -127, -78, 113, 100, -38, -117, -8, -21, 15, 75, 112, 86, -99, 53, 30, 36, 14, 94, 99, 88, -47, -94, 37, 34, 124, 59, 1, 33, 120, -121, -44, 0, 70, 87, -97, -45, 39, 82, 76, 54, 2, -25, -96, -60, -56, -98, -22, -65, -118, -46, 64, -57, 56, -75, -93, -9, -14, -50, -7, 97, 21, -95, -32, -82, 93, -92, -101, 52, 26, 85, -83, -109, 50, 48, -11, -116, -79, -29, 29, -10, -30, 46, -126, 102, -54, 96, -64, 41, 35, -85, 13, 83, 78, 111, -43, -37, 55, 69, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, 91, 81, -115, 27, -81, -110, -69, -35, -68, 127, 17, -39, 92, 65, 31, 16, 90, -40, 10, -63, 49, -120, -91, -51, 123, -67, 45, 116, -48, 18, -72, -27, -76, -80, -119, 105, -105, 74, 12, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, -124, 24, -16, 125, -20, 58, -36, 77, 32, 121, -18, 95, 62, -41, -53, 57, 72};
    private static final int[] CK = new int[]{462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};
    private static final int[] FK = new int[]{-1548633402, 1453994832, 1736282519, -1301273892};
    private final int[] X = new int[4];
    private int[] rk;

    private int rotateLeft(int n, int n2) {
        return n << n2 | n >>> -n2;
    }

    private int tau(int n) {
        int n2 = Sbox[n >> 24 & 0xFF] & 0xFF;
        int n3 = Sbox[n >> 16 & 0xFF] & 0xFF;
        int n4 = Sbox[n >> 8 & 0xFF] & 0xFF;
        int n5 = Sbox[n & 0xFF] & 0xFF;
        return n2 << 24 | n3 << 16 | n4 << 8 | n5;
    }

    private int L_ap(int n) {
        return n ^ this.rotateLeft(n, 13) ^ this.rotateLeft(n, 23);
    }

    private int T_ap(int n) {
        return this.L_ap(this.tau(n));
    }

    private int[] expandKey(boolean bl, byte[] byArray) {
        int[] nArray = new int[32];
        int[] nArray2 = new int[]{Pack.bigEndianToInt(byArray, 0), Pack.bigEndianToInt(byArray, 4), Pack.bigEndianToInt(byArray, 8), Pack.bigEndianToInt(byArray, 12)};
        int[] nArray3 = new int[]{nArray2[0] ^ FK[0], nArray2[1] ^ FK[1], nArray2[2] ^ FK[2], nArray2[3] ^ FK[3]};
        if (bl) {
            nArray[0] = nArray3[0] ^ this.T_ap(nArray3[1] ^ nArray3[2] ^ nArray3[3] ^ CK[0]);
            nArray[1] = nArray3[1] ^ this.T_ap(nArray3[2] ^ nArray3[3] ^ nArray[0] ^ CK[1]);
            nArray[2] = nArray3[2] ^ this.T_ap(nArray3[3] ^ nArray[0] ^ nArray[1] ^ CK[2]);
            nArray[3] = nArray3[3] ^ this.T_ap(nArray[0] ^ nArray[1] ^ nArray[2] ^ CK[3]);
            for (int i = 4; i < 32; ++i) {
                nArray[i] = nArray[i - 4] ^ this.T_ap(nArray[i - 3] ^ nArray[i - 2] ^ nArray[i - 1] ^ CK[i]);
            }
        } else {
            nArray[31] = nArray3[0] ^ this.T_ap(nArray3[1] ^ nArray3[2] ^ nArray3[3] ^ CK[0]);
            nArray[30] = nArray3[1] ^ this.T_ap(nArray3[2] ^ nArray3[3] ^ nArray[31] ^ CK[1]);
            nArray[29] = nArray3[2] ^ this.T_ap(nArray3[3] ^ nArray[31] ^ nArray[30] ^ CK[2]);
            nArray[28] = nArray3[3] ^ this.T_ap(nArray[31] ^ nArray[30] ^ nArray[29] ^ CK[3]);
            for (int i = 27; i >= 0; --i) {
                nArray[i] = nArray[i + 4] ^ this.T_ap(nArray[i + 3] ^ nArray[i + 2] ^ nArray[i + 1] ^ CK[31 - i]);
            }
        }
        return nArray;
    }

    private int L(int n) {
        int n2 = n ^ this.rotateLeft(n, 2) ^ this.rotateLeft(n, 10) ^ this.rotateLeft(n, 18) ^ this.rotateLeft(n, 24);
        return n2;
    }

    private int T(int n) {
        return this.L(this.tau(n));
    }

    private void R(int[] nArray, int n) {
        int n2 = n;
        int n3 = n + 1;
        int n4 = n + 2;
        int n5 = n + 3;
        nArray[n2] = nArray[n2] ^ nArray[n5];
        nArray[n5] = nArray[n2] ^ nArray[n5];
        nArray[n2] = nArray[n2] ^ nArray[n5];
        nArray[n3] = nArray[n3] ^ nArray[n4];
        nArray[n4] = nArray[n3] ^ nArray[n4];
        nArray[n3] = nArray[n3] ^ nArray[n4];
    }

    private int F0(int[] nArray, int n) {
        return nArray[0] ^ this.T(nArray[1] ^ nArray[2] ^ nArray[3] ^ n);
    }

    private int F1(int[] nArray, int n) {
        return nArray[1] ^ this.T(nArray[2] ^ nArray[3] ^ nArray[0] ^ n);
    }

    private int F2(int[] nArray, int n) {
        return nArray[2] ^ this.T(nArray[3] ^ nArray[0] ^ nArray[1] ^ n);
    }

    private int F3(int[] nArray, int n) {
        return nArray[3] ^ this.T(nArray[0] ^ nArray[1] ^ nArray[2] ^ n);
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] byArray;
        if (cipherParameters instanceof KeyParameter) {
            byArray = ((KeyParameter)cipherParameters).getKey();
            if (byArray.length != 16) {
                throw new IllegalArgumentException("SM4 requires a 128 bit key");
            }
        } else {
            throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + cipherParameters.getClass().getName());
        }
        this.rk = this.expandKey(bl, byArray);
    }

    public String getAlgorithmName() {
        return "SM4";
    }

    public int getBlockSize() {
        return 16;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (this.rk == null) {
            throw new IllegalStateException("SM4 not initialised");
        }
        if (n + 16 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 16 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.X[0] = Pack.bigEndianToInt(byArray, n);
        this.X[1] = Pack.bigEndianToInt(byArray, n + 4);
        this.X[2] = Pack.bigEndianToInt(byArray, n + 8);
        this.X[3] = Pack.bigEndianToInt(byArray, n + 12);
        for (int i = 0; i < 32; i += 4) {
            this.X[0] = this.F0(this.X, this.rk[i]);
            this.X[1] = this.F1(this.X, this.rk[i + 1]);
            this.X[2] = this.F2(this.X, this.rk[i + 2]);
            this.X[3] = this.F3(this.X, this.rk[i + 3]);
        }
        this.R(this.X, 0);
        Pack.intToBigEndian(this.X[0], byArray2, n2);
        Pack.intToBigEndian(this.X[1], byArray2, n2 + 4);
        Pack.intToBigEndian(this.X[2], byArray2, n2 + 8);
        Pack.intToBigEndian(this.X[3], byArray2, n2 + 12);
        return 16;
    }

    public void reset() {
    }
}

