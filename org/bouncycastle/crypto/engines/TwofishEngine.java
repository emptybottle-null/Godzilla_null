/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public final class TwofishEngine
implements BlockCipher {
    private static final byte[][] P = new byte[][]{{-87, 103, -77, -24, 4, -3, -93, 118, -102, -110, -128, 120, -28, -35, -47, 56, 13, -58, 53, -104, 24, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, 72, -14, -48, -117, 48, -124, 84, -33, 35, 25, 91, 61, 89, -13, -82, -94, -126, 99, 1, -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, 22, 12, -29, 97, -64, -116, 58, -11, 115, 44, 37, 11, -69, 78, -119, 107, 83, 106, -76, -15, -31, -26, -67, 69, -30, -12, -74, 102, -52, -107, 3, 86, -44, 28, 30, -41, -5, -61, -114, -75, -23, -49, -65, -70, -22, 119, 57, -81, 51, -55, 98, 113, -127, 121, 9, -83, 36, -51, -7, -40, -27, -59, -71, 77, 68, 8, -122, -25, -95, 29, -86, -19, 6, 112, -78, -46, 65, 123, -96, 17, 49, -62, 39, -112, 32, -10, 96, -1, -106, 92, -79, -85, -98, -100, 82, 27, 95, -109, 10, -17, -111, -123, 73, -18, 45, 79, -113, 59, 71, -121, 109, 70, -42, 62, 105, 100, 42, -50, -53, 47, -4, -105, 5, 122, -84, 127, -43, 26, 75, 14, -89, 90, 40, 20, 63, 41, -120, 60, 76, 2, -72, -38, -80, 23, 85, 31, -118, 125, 87, -57, -115, 116, -73, -60, -97, 114, 126, 21, 34, 18, 88, 7, -103, 52, 110, 80, -34, 104, 101, -68, -37, -8, -56, -88, 43, 64, -36, -2, 50, -92, -54, 16, 33, -16, -45, 93, 15, 0, 111, -99, 54, 66, 74, 94, -63, -32}, {117, -13, -58, -12, -37, 123, -5, -56, 74, -45, -26, 107, 69, 125, -24, 75, -42, 50, -40, -3, 55, 113, -15, -31, 48, 15, -8, 27, -121, -6, 6, 63, 94, -70, -82, 91, -118, 0, -68, -99, 109, -63, -79, 14, -128, 93, -46, -43, -96, -124, 7, 20, -75, -112, 44, -93, -78, 115, 76, 84, -110, 116, 54, 81, 56, -80, -67, 90, -4, 96, 98, -106, 108, 66, -9, 16, 124, 40, 39, -116, 19, -107, -100, -57, 36, 70, 59, 112, -54, -29, -123, -53, 17, -48, -109, -72, -90, -125, 32, -1, -97, 119, -61, -52, 3, 111, 8, -65, 64, -25, 43, -30, 121, 12, -86, -126, 65, 58, -22, -71, -28, -102, -92, -105, 126, -38, 122, 23, 102, -108, -95, 29, 61, -16, -34, -77, 11, 114, -89, 28, -17, -47, 83, 62, -113, 51, 38, 95, -20, 118, 42, 73, -127, -120, -18, 33, -60, 26, -21, -39, -59, 57, -103, -51, -83, 49, -117, 1, 24, 35, -35, 31, 78, 45, -7, 72, 79, -14, 101, -114, 120, 92, 88, 25, -115, -27, -104, 87, 103, 127, 5, 100, -81, 99, -74, -2, -11, -73, 60, -91, -50, -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, 21, 89, -88, 10, -98, 110, 71, -33, 52, 53, 106, -49, -36, 34, -55, -64, -101, -119, -44, -19, -85, 18, -94, 13, 82, -69, 2, 47, -87, -41, 97, 30, -76, 80, 4, -10, -62, 22, 37, -122, 86, 85, 9, -66, -111}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int RS_GF_FDBK = 333;
    private static final int ROUNDS = 16;
    private static final int MAX_ROUNDS = 16;
    private static final int BLOCK_SIZE = 16;
    private static final int MAX_KEY_BITS = 256;
    private static final int INPUT_WHITEN = 0;
    private static final int OUTPUT_WHITEN = 4;
    private static final int ROUND_SUBKEYS = 8;
    private static final int TOTAL_SUBKEYS = 40;
    private static final int SK_STEP = 0x2020202;
    private static final int SK_BUMP = 0x1010101;
    private static final int SK_ROTL = 9;
    private boolean encrypting = false;
    private int[] gMDS0 = new int[256];
    private int[] gMDS1 = new int[256];
    private int[] gMDS2 = new int[256];
    private int[] gMDS3 = new int[256];
    private int[] gSubKeys;
    private int[] gSBox;
    private int k64Cnt = 0;
    private byte[] workingKey = null;

    public TwofishEngine() {
        int[] nArray = new int[2];
        int[] nArray2 = new int[2];
        int[] nArray3 = new int[2];
        for (int i = 0; i < 256; ++i) {
            int n;
            nArray[0] = n = P[0][i] & 0xFF;
            nArray2[0] = this.Mx_X(n) & 0xFF;
            nArray3[0] = this.Mx_Y(n) & 0xFF;
            nArray[1] = n = P[1][i] & 0xFF;
            nArray2[1] = this.Mx_X(n) & 0xFF;
            nArray3[1] = this.Mx_Y(n) & 0xFF;
            this.gMDS0[i] = nArray[1] | nArray2[1] << 8 | nArray3[1] << 16 | nArray3[1] << 24;
            this.gMDS1[i] = nArray3[0] | nArray3[0] << 8 | nArray2[0] << 16 | nArray[0] << 24;
            this.gMDS2[i] = nArray2[1] | nArray3[1] << 8 | nArray[1] << 16 | nArray3[1] << 24;
            this.gMDS3[i] = nArray2[0] | nArray[0] << 8 | nArray3[0] << 16 | nArray2[0] << 24;
        }
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.encrypting = bl;
            this.workingKey = ((KeyParameter)cipherParameters).getKey();
            this.k64Cnt = this.workingKey.length / 8;
            this.setKey(this.workingKey);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + cipherParameters.getClass().getName());
    }

    public String getAlgorithmName() {
        return "Twofish";
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Twofish not initialised");
        }
        if (n + 16 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 16 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            this.encryptBlock(byArray, n, byArray2, n2);
        } else {
            this.decryptBlock(byArray, n, byArray2, n2);
        }
        return 16;
    }

    public void reset() {
        if (this.workingKey != null) {
            this.setKey(this.workingKey);
        }
    }

    public int getBlockSize() {
        return 16;
    }

    private void setKey(byte[] byArray) {
        int n;
        int n2;
        int n3;
        int[] nArray = new int[4];
        int[] nArray2 = new int[4];
        int[] nArray3 = new int[4];
        this.gSubKeys = new int[40];
        if (this.k64Cnt < 1) {
            throw new IllegalArgumentException("Key size less than 64 bits");
        }
        if (this.k64Cnt > 4) {
            throw new IllegalArgumentException("Key size larger than 256 bits");
        }
        for (n3 = 0; n3 < this.k64Cnt; ++n3) {
            n2 = n3 * 8;
            nArray[n3] = this.BytesTo32Bits(byArray, n2);
            nArray2[n3] = this.BytesTo32Bits(byArray, n2 + 4);
            nArray3[this.k64Cnt - 1 - n3] = this.RS_MDS_Encode(nArray[n3], nArray2[n3]);
        }
        for (n = 0; n < 20; ++n) {
            n3 = n * 0x2020202;
            n2 = this.F32(n3, nArray);
            int n4 = this.F32(n3 + 0x1010101, nArray2);
            n4 = n4 << 8 | n4 >>> 24;
            this.gSubKeys[n * 2] = n2 += n4;
            this.gSubKeys[n * 2 + 1] = (n2 += n4) << 9 | n2 >>> 23;
        }
        n = nArray3[0];
        int n5 = nArray3[1];
        int n6 = nArray3[2];
        int n7 = nArray3[3];
        this.gSBox = new int[1024];
        block8: for (int i = 0; i < 256; ++i) {
            int n8;
            int n9 = n8 = i;
            int n10 = n8;
            int n11 = n8;
            switch (this.k64Cnt & 3) {
                case 1: {
                    this.gSBox[i * 2] = this.gMDS0[P[0][n11] & 0xFF ^ this.b0(n)];
                    this.gSBox[i * 2 + 1] = this.gMDS1[P[0][n10] & 0xFF ^ this.b1(n)];
                    this.gSBox[i * 2 + 512] = this.gMDS2[P[1][n9] & 0xFF ^ this.b2(n)];
                    this.gSBox[i * 2 + 513] = this.gMDS3[P[1][n8] & 0xFF ^ this.b3(n)];
                    continue block8;
                }
                case 0: {
                    n11 = P[1][n11] & 0xFF ^ this.b0(n7);
                    n10 = P[0][n10] & 0xFF ^ this.b1(n7);
                    n9 = P[0][n9] & 0xFF ^ this.b2(n7);
                    n8 = P[1][n8] & 0xFF ^ this.b3(n7);
                }
                case 3: {
                    n11 = P[1][n11] & 0xFF ^ this.b0(n6);
                    n10 = P[1][n10] & 0xFF ^ this.b1(n6);
                    n9 = P[0][n9] & 0xFF ^ this.b2(n6);
                    n8 = P[0][n8] & 0xFF ^ this.b3(n6);
                }
                case 2: {
                    this.gSBox[i * 2] = this.gMDS0[P[0][P[0][n11] & 0xFF ^ this.b0(n5)] & 0xFF ^ this.b0(n)];
                    this.gSBox[i * 2 + 1] = this.gMDS1[P[0][P[1][n10] & 0xFF ^ this.b1(n5)] & 0xFF ^ this.b1(n)];
                    this.gSBox[i * 2 + 512] = this.gMDS2[P[1][P[0][n9] & 0xFF ^ this.b2(n5)] & 0xFF ^ this.b2(n)];
                    this.gSBox[i * 2 + 513] = this.gMDS3[P[1][P[1][n8] & 0xFF ^ this.b3(n5)] & 0xFF ^ this.b3(n)];
                }
            }
        }
    }

    private void encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = this.BytesTo32Bits(byArray, n) ^ this.gSubKeys[0];
        int n4 = this.BytesTo32Bits(byArray, n + 4) ^ this.gSubKeys[1];
        int n5 = this.BytesTo32Bits(byArray, n + 8) ^ this.gSubKeys[2];
        int n6 = this.BytesTo32Bits(byArray, n + 12) ^ this.gSubKeys[3];
        int n7 = 8;
        for (int i = 0; i < 16; i += 2) {
            int n8 = this.Fe32_0(n3);
            int n9 = this.Fe32_3(n4);
            n5 ^= n8 + n9 + this.gSubKeys[n7++];
            n5 = n5 >>> 1 | n5 << 31;
            n6 = (n6 << 1 | n6 >>> 31) ^ n8 + 2 * n9 + this.gSubKeys[n7++];
            n8 = this.Fe32_0(n5);
            n9 = this.Fe32_3(n6);
            n3 ^= n8 + n9 + this.gSubKeys[n7++];
            n3 = n3 >>> 1 | n3 << 31;
            n4 = (n4 << 1 | n4 >>> 31) ^ n8 + 2 * n9 + this.gSubKeys[n7++];
        }
        this.Bits32ToBytes(n5 ^ this.gSubKeys[4], byArray2, n2);
        this.Bits32ToBytes(n6 ^ this.gSubKeys[5], byArray2, n2 + 4);
        this.Bits32ToBytes(n3 ^ this.gSubKeys[6], byArray2, n2 + 8);
        this.Bits32ToBytes(n4 ^ this.gSubKeys[7], byArray2, n2 + 12);
    }

    private void decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = this.BytesTo32Bits(byArray, n) ^ this.gSubKeys[4];
        int n4 = this.BytesTo32Bits(byArray, n + 4) ^ this.gSubKeys[5];
        int n5 = this.BytesTo32Bits(byArray, n + 8) ^ this.gSubKeys[6];
        int n6 = this.BytesTo32Bits(byArray, n + 12) ^ this.gSubKeys[7];
        int n7 = 39;
        for (int i = 0; i < 16; i += 2) {
            int n8 = this.Fe32_0(n3);
            int n9 = this.Fe32_3(n4);
            n6 ^= n8 + 2 * n9 + this.gSubKeys[n7--];
            n5 = (n5 << 1 | n5 >>> 31) ^ n8 + n9 + this.gSubKeys[n7--];
            n6 = n6 >>> 1 | n6 << 31;
            n8 = this.Fe32_0(n5);
            n9 = this.Fe32_3(n6);
            n4 ^= n8 + 2 * n9 + this.gSubKeys[n7--];
            n3 = (n3 << 1 | n3 >>> 31) ^ n8 + n9 + this.gSubKeys[n7--];
            n4 = n4 >>> 1 | n4 << 31;
        }
        this.Bits32ToBytes(n5 ^ this.gSubKeys[0], byArray2, n2);
        this.Bits32ToBytes(n6 ^ this.gSubKeys[1], byArray2, n2 + 4);
        this.Bits32ToBytes(n3 ^ this.gSubKeys[2], byArray2, n2 + 8);
        this.Bits32ToBytes(n4 ^ this.gSubKeys[3], byArray2, n2 + 12);
    }

    private int F32(int n, int[] nArray) {
        int n2 = this.b0(n);
        int n3 = this.b1(n);
        int n4 = this.b2(n);
        int n5 = this.b3(n);
        int n6 = nArray[0];
        int n7 = nArray[1];
        int n8 = nArray[2];
        int n9 = nArray[3];
        int n10 = 0;
        switch (this.k64Cnt & 3) {
            case 1: {
                n10 = this.gMDS0[P[0][n2] & 0xFF ^ this.b0(n6)] ^ this.gMDS1[P[0][n3] & 0xFF ^ this.b1(n6)] ^ this.gMDS2[P[1][n4] & 0xFF ^ this.b2(n6)] ^ this.gMDS3[P[1][n5] & 0xFF ^ this.b3(n6)];
                break;
            }
            case 0: {
                n2 = P[1][n2] & 0xFF ^ this.b0(n9);
                n3 = P[0][n3] & 0xFF ^ this.b1(n9);
                n4 = P[0][n4] & 0xFF ^ this.b2(n9);
                n5 = P[1][n5] & 0xFF ^ this.b3(n9);
            }
            case 3: {
                n2 = P[1][n2] & 0xFF ^ this.b0(n8);
                n3 = P[1][n3] & 0xFF ^ this.b1(n8);
                n4 = P[0][n4] & 0xFF ^ this.b2(n8);
                n5 = P[0][n5] & 0xFF ^ this.b3(n8);
            }
            case 2: {
                n10 = this.gMDS0[P[0][P[0][n2] & 0xFF ^ this.b0(n7)] & 0xFF ^ this.b0(n6)] ^ this.gMDS1[P[0][P[1][n3] & 0xFF ^ this.b1(n7)] & 0xFF ^ this.b1(n6)] ^ this.gMDS2[P[1][P[0][n4] & 0xFF ^ this.b2(n7)] & 0xFF ^ this.b2(n6)] ^ this.gMDS3[P[1][P[1][n5] & 0xFF ^ this.b3(n7)] & 0xFF ^ this.b3(n6)];
            }
        }
        return n10;
    }

    private int RS_MDS_Encode(int n, int n2) {
        int n3;
        int n4 = n2;
        for (n3 = 0; n3 < 4; ++n3) {
            n4 = this.RS_rem(n4);
        }
        n4 ^= n;
        for (n3 = 0; n3 < 4; ++n3) {
            n4 = this.RS_rem(n4);
        }
        return n4;
    }

    private int RS_rem(int n) {
        int n2;
        int n3 = (n2 << 1 ^ (((n2 = n >>> 24 & 0xFF) & 0x80) != 0 ? 333 : 0)) & 0xFF;
        int n4 = n2 >>> 1 ^ ((n2 & 1) != 0 ? 166 : 0) ^ n3;
        return n << 8 ^ n4 << 24 ^ n3 << 16 ^ n4 << 8 ^ n2;
    }

    private int LFSR1(int n) {
        return n >> 1 ^ ((n & 1) != 0 ? 180 : 0);
    }

    private int LFSR2(int n) {
        return n >> 2 ^ ((n & 2) != 0 ? 180 : 0) ^ ((n & 1) != 0 ? 90 : 0);
    }

    private int Mx_X(int n) {
        return n ^ this.LFSR2(n);
    }

    private int Mx_Y(int n) {
        return n ^ this.LFSR1(n) ^ this.LFSR2(n);
    }

    private int b0(int n) {
        return n & 0xFF;
    }

    private int b1(int n) {
        return n >>> 8 & 0xFF;
    }

    private int b2(int n) {
        return n >>> 16 & 0xFF;
    }

    private int b3(int n) {
        return n >>> 24 & 0xFF;
    }

    private int Fe32_0(int n) {
        return this.gSBox[0 + 2 * (n & 0xFF)] ^ this.gSBox[1 + 2 * (n >>> 8 & 0xFF)] ^ this.gSBox[512 + 2 * (n >>> 16 & 0xFF)] ^ this.gSBox[513 + 2 * (n >>> 24 & 0xFF)];
    }

    private int Fe32_3(int n) {
        return this.gSBox[0 + 2 * (n >>> 24 & 0xFF)] ^ this.gSBox[1 + 2 * (n & 0xFF)] ^ this.gSBox[512 + 2 * (n >>> 8 & 0xFF)] ^ this.gSBox[513 + 2 * (n >>> 16 & 0xFF)];
    }

    private int BytesTo32Bits(byte[] byArray, int n) {
        return byArray[n] & 0xFF | (byArray[n + 1] & 0xFF) << 8 | (byArray[n + 2] & 0xFF) << 16 | (byArray[n + 3] & 0xFF) << 24;
    }

    private void Bits32ToBytes(int n, byte[] byArray, int n2) {
        byArray[n2] = (byte)n;
        byArray[n2 + 1] = (byte)(n >> 8);
        byArray[n2 + 2] = (byte)(n >> 16);
        byArray[n2 + 3] = (byte)(n >> 24);
    }
}

