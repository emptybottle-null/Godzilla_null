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

public class AESLightEngine
implements BlockCipher {
    private static final byte[] S = new byte[]{99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    private static final byte[] Si = new byte[]{82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, -128, -20, 95, 96, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125};
    private static final int[] rcon = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, 197, 145};
    private static final int m1 = -2139062144;
    private static final int m2 = 0x7F7F7F7F;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 0x3F3F3F3F;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private int C0;
    private int C1;
    private int C2;
    private int C3;
    private boolean forEncryption;
    private static final int BLOCK_SIZE = 16;

    private static int shift(int n, int n2) {
        return n >>> n2 | n << -n2;
    }

    private static int FFmulX(int n) {
        return (n & 0x7F7F7F7F) << 1 ^ ((n & 0x80808080) >>> 7) * 27;
    }

    private static int FFmulX2(int n) {
        int n2 = (n & 0x3F3F3F3F) << 2;
        int n3 = n & 0xC0C0C0C0;
        n3 ^= n3 >>> 1;
        return n2 ^ n3 >>> 2 ^ n3 >>> 5;
    }

    private static int mcol(int n) {
        int n2 = AESLightEngine.shift(n, 8);
        int n3 = n ^ n2;
        return AESLightEngine.shift(n3, 16) ^ n2 ^ AESLightEngine.FFmulX(n3);
    }

    private static int inv_mcol(int n) {
        int n2 = n;
        int n3 = n2 ^ AESLightEngine.shift(n2, 8);
        n2 ^= AESLightEngine.FFmulX(n3);
        n3 ^= AESLightEngine.FFmulX2(n2);
        return n2 ^= n3 ^ AESLightEngine.shift(n3, 16);
    }

    private static int subWord(int n) {
        return S[n & 0xFF] & 0xFF | (S[n >> 8 & 0xFF] & 0xFF) << 8 | (S[n >> 16 & 0xFF] & 0xFF) << 16 | S[n >> 24 & 0xFF] << 24;
    }

    private int[][] generateWorkingKey(byte[] byArray, boolean bl) {
        int n;
        int n2;
        int n3 = byArray.length;
        if (n3 < 16 || n3 > 32 || (n3 & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int n4 = n3 >> 2;
        this.ROUNDS = n4 + 6;
        int[][] nArray = new int[this.ROUNDS + 1][4];
        switch (n4) {
            case 4: {
                int n5;
                int n6;
                int n7;
                int n8;
                nArray[0][0] = n2 = Pack.littleEndianToInt(byArray, 0);
                nArray[0][1] = n = Pack.littleEndianToInt(byArray, 4);
                nArray[0][2] = n8 = Pack.littleEndianToInt(byArray, 8);
                nArray[0][3] = n7 = Pack.littleEndianToInt(byArray, 12);
                for (n6 = 1; n6 <= 10; ++n6) {
                    n5 = AESLightEngine.subWord(AESLightEngine.shift(n7, 8)) ^ rcon[n6 - 1];
                    nArray[n6][0] = n2 ^= n5;
                    nArray[n6][1] = n ^= n2;
                    nArray[n6][2] = n8 ^= n;
                    nArray[n6][3] = n7 ^= n8;
                }
                break;
            }
            case 6: {
                int n9;
                int n5;
                int n6;
                int n7;
                int n8;
                nArray[0][0] = n2 = Pack.littleEndianToInt(byArray, 0);
                nArray[0][1] = n = Pack.littleEndianToInt(byArray, 4);
                nArray[0][2] = n8 = Pack.littleEndianToInt(byArray, 8);
                nArray[0][3] = n7 = Pack.littleEndianToInt(byArray, 12);
                nArray[1][0] = n6 = Pack.littleEndianToInt(byArray, 16);
                nArray[1][1] = n5 = Pack.littleEndianToInt(byArray, 20);
                int n10 = 1;
                int n11 = AESLightEngine.subWord(AESLightEngine.shift(n5, 8)) ^ n10;
                n10 <<= 1;
                nArray[1][2] = n2 ^= n11;
                nArray[1][3] = n ^= n2;
                nArray[2][0] = n8 ^= n;
                nArray[2][1] = n7 ^= n8;
                nArray[2][2] = n6 ^= n7;
                nArray[2][3] = n5 ^= n6;
                for (n9 = 3; n9 < 12; n9 += 3) {
                    n11 = AESLightEngine.subWord(AESLightEngine.shift(n5, 8)) ^ n10;
                    n10 <<= 1;
                    nArray[n9][0] = n2 ^= n11;
                    nArray[n9][1] = n ^= n2;
                    nArray[n9][2] = n8 ^= n;
                    nArray[n9][3] = n7 ^= n8;
                    nArray[n9 + 1][0] = n6 ^= n7;
                    nArray[n9 + 1][1] = n5 ^= n6;
                    n11 = AESLightEngine.subWord(AESLightEngine.shift(n5, 8)) ^ n10;
                    n10 <<= 1;
                    nArray[n9 + 1][2] = n2 ^= n11;
                    nArray[n9 + 1][3] = n ^= n2;
                    nArray[n9 + 2][0] = n8 ^= n;
                    nArray[n9 + 2][1] = n7 ^= n8;
                    nArray[n9 + 2][2] = n6 ^= n7;
                    nArray[n9 + 2][3] = n5 ^= n6;
                }
                n11 = AESLightEngine.subWord(AESLightEngine.shift(n5, 8)) ^ n10;
                nArray[12][0] = n2 ^= n11;
                nArray[12][1] = n ^= n2;
                nArray[12][2] = n8 ^= n;
                nArray[12][3] = n7 ^= n8;
                break;
            }
            case 8: {
                int n9;
                int n11;
                int n10;
                int n5;
                int n6;
                int n7;
                int n8;
                nArray[0][0] = n2 = Pack.littleEndianToInt(byArray, 0);
                nArray[0][1] = n = Pack.littleEndianToInt(byArray, 4);
                nArray[0][2] = n8 = Pack.littleEndianToInt(byArray, 8);
                nArray[0][3] = n7 = Pack.littleEndianToInt(byArray, 12);
                nArray[1][0] = n6 = Pack.littleEndianToInt(byArray, 16);
                nArray[1][1] = n5 = Pack.littleEndianToInt(byArray, 20);
                nArray[1][2] = n10 = Pack.littleEndianToInt(byArray, 24);
                nArray[1][3] = n11 = Pack.littleEndianToInt(byArray, 28);
                int n12 = 1;
                for (int i = 2; i < 14; i += 2) {
                    n9 = AESLightEngine.subWord(AESLightEngine.shift(n11, 8)) ^ n12;
                    n12 <<= 1;
                    nArray[i][0] = n2 ^= n9;
                    nArray[i][1] = n ^= n2;
                    nArray[i][2] = n8 ^= n;
                    nArray[i][3] = n7 ^= n8;
                    n9 = AESLightEngine.subWord(n7);
                    nArray[i + 1][0] = n6 ^= n9;
                    nArray[i + 1][1] = n5 ^= n6;
                    nArray[i + 1][2] = n10 ^= n5;
                    nArray[i + 1][3] = n11 ^= n10;
                }
                n9 = AESLightEngine.subWord(AESLightEngine.shift(n11, 8)) ^ n12;
                nArray[14][0] = n2 ^= n9;
                nArray[14][1] = n ^= n2;
                nArray[14][2] = n8 ^= n;
                nArray[14][3] = n7 ^= n8;
                break;
            }
            default: {
                throw new IllegalStateException("Should never get here");
            }
        }
        if (!bl) {
            for (n2 = 1; n2 < this.ROUNDS; ++n2) {
                for (n = 0; n < 4; ++n) {
                    nArray[n2][n] = AESLightEngine.inv_mcol(nArray[n2][n]);
                }
            }
        }
        return nArray;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = this.generateWorkingKey(((KeyParameter)cipherParameters).getKey(), bl);
            this.forEncryption = bl;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
    }

    public String getAlgorithmName() {
        return "AES";
    }

    public int getBlockSize() {
        return 16;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        if (this.WorkingKey == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (n + 16 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 16 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            this.unpackBlock(byArray, n);
            this.encryptBlock(this.WorkingKey);
            this.packBlock(byArray2, n2);
        } else {
            this.unpackBlock(byArray, n);
            this.decryptBlock(this.WorkingKey);
            this.packBlock(byArray2, n2);
        }
        return 16;
    }

    public void reset() {
    }

    private void unpackBlock(byte[] byArray, int n) {
        int n2 = n;
        this.C0 = byArray[n2++] & 0xFF;
        this.C0 |= (byArray[n2++] & 0xFF) << 8;
        this.C0 |= (byArray[n2++] & 0xFF) << 16;
        this.C0 |= byArray[n2++] << 24;
        this.C1 = byArray[n2++] & 0xFF;
        this.C1 |= (byArray[n2++] & 0xFF) << 8;
        this.C1 |= (byArray[n2++] & 0xFF) << 16;
        this.C1 |= byArray[n2++] << 24;
        this.C2 = byArray[n2++] & 0xFF;
        this.C2 |= (byArray[n2++] & 0xFF) << 8;
        this.C2 |= (byArray[n2++] & 0xFF) << 16;
        this.C2 |= byArray[n2++] << 24;
        this.C3 = byArray[n2++] & 0xFF;
        this.C3 |= (byArray[n2++] & 0xFF) << 8;
        this.C3 |= (byArray[n2++] & 0xFF) << 16;
        this.C3 |= byArray[n2++] << 24;
    }

    private void packBlock(byte[] byArray, int n) {
        int n2 = n;
        byArray[n2++] = (byte)this.C0;
        byArray[n2++] = (byte)(this.C0 >> 8);
        byArray[n2++] = (byte)(this.C0 >> 16);
        byArray[n2++] = (byte)(this.C0 >> 24);
        byArray[n2++] = (byte)this.C1;
        byArray[n2++] = (byte)(this.C1 >> 8);
        byArray[n2++] = (byte)(this.C1 >> 16);
        byArray[n2++] = (byte)(this.C1 >> 24);
        byArray[n2++] = (byte)this.C2;
        byArray[n2++] = (byte)(this.C2 >> 8);
        byArray[n2++] = (byte)(this.C2 >> 16);
        byArray[n2++] = (byte)(this.C2 >> 24);
        byArray[n2++] = (byte)this.C3;
        byArray[n2++] = (byte)(this.C3 >> 8);
        byArray[n2++] = (byte)(this.C3 >> 16);
        byArray[n2++] = (byte)(this.C3 >> 24);
    }

    private void encryptBlock(int[][] nArray) {
        int n;
        int n2;
        int n3;
        int n4 = this.C0 ^ nArray[0][0];
        int n5 = this.C1 ^ nArray[0][1];
        int n6 = this.C2 ^ nArray[0][2];
        int n7 = 1;
        int n8 = this.C3 ^ nArray[0][3];
        while (n7 < this.ROUNDS - 1) {
            n3 = AESLightEngine.mcol(S[n4 & 0xFF] & 0xFF ^ (S[n5 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n6 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n8 >> 24 & 0xFF] << 24) ^ nArray[n7][0];
            n2 = AESLightEngine.mcol(S[n5 & 0xFF] & 0xFF ^ (S[n6 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n8 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n4 >> 24 & 0xFF] << 24) ^ nArray[n7][1];
            n = AESLightEngine.mcol(S[n6 & 0xFF] & 0xFF ^ (S[n8 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n4 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n5 >> 24 & 0xFF] << 24) ^ nArray[n7][2];
            n8 = AESLightEngine.mcol(S[n8 & 0xFF] & 0xFF ^ (S[n4 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n5 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n6 >> 24 & 0xFF] << 24) ^ nArray[n7++][3];
            n4 = AESLightEngine.mcol(S[n3 & 0xFF] & 0xFF ^ (S[n2 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n >> 16 & 0xFF] & 0xFF) << 16 ^ S[n8 >> 24 & 0xFF] << 24) ^ nArray[n7][0];
            n5 = AESLightEngine.mcol(S[n2 & 0xFF] & 0xFF ^ (S[n >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n8 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n3 >> 24 & 0xFF] << 24) ^ nArray[n7][1];
            n6 = AESLightEngine.mcol(S[n & 0xFF] & 0xFF ^ (S[n8 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n3 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n2 >> 24 & 0xFF] << 24) ^ nArray[n7][2];
            n8 = AESLightEngine.mcol(S[n8 & 0xFF] & 0xFF ^ (S[n3 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n2 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n >> 24 & 0xFF] << 24) ^ nArray[n7++][3];
        }
        n3 = AESLightEngine.mcol(S[n4 & 0xFF] & 0xFF ^ (S[n5 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n6 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n8 >> 24 & 0xFF] << 24) ^ nArray[n7][0];
        n2 = AESLightEngine.mcol(S[n5 & 0xFF] & 0xFF ^ (S[n6 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n8 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n4 >> 24 & 0xFF] << 24) ^ nArray[n7][1];
        n = AESLightEngine.mcol(S[n6 & 0xFF] & 0xFF ^ (S[n8 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n4 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n5 >> 24 & 0xFF] << 24) ^ nArray[n7][2];
        n8 = AESLightEngine.mcol(S[n8 & 0xFF] & 0xFF ^ (S[n4 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n5 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n6 >> 24 & 0xFF] << 24) ^ nArray[n7++][3];
        this.C0 = S[n3 & 0xFF] & 0xFF ^ (S[n2 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n >> 16 & 0xFF] & 0xFF) << 16 ^ S[n8 >> 24 & 0xFF] << 24 ^ nArray[n7][0];
        this.C1 = S[n2 & 0xFF] & 0xFF ^ (S[n >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n8 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n3 >> 24 & 0xFF] << 24 ^ nArray[n7][1];
        this.C2 = S[n & 0xFF] & 0xFF ^ (S[n8 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n3 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n2 >> 24 & 0xFF] << 24 ^ nArray[n7][2];
        this.C3 = S[n8 & 0xFF] & 0xFF ^ (S[n3 >> 8 & 0xFF] & 0xFF) << 8 ^ (S[n2 >> 16 & 0xFF] & 0xFF) << 16 ^ S[n >> 24 & 0xFF] << 24 ^ nArray[n7][3];
    }

    private void decryptBlock(int[][] nArray) {
        int n;
        int n2;
        int n3;
        int n4 = this.C0 ^ nArray[this.ROUNDS][0];
        int n5 = this.C1 ^ nArray[this.ROUNDS][1];
        int n6 = this.C2 ^ nArray[this.ROUNDS][2];
        int n7 = this.ROUNDS - 1;
        int n8 = this.C3 ^ nArray[this.ROUNDS][3];
        while (n7 > 1) {
            n3 = AESLightEngine.inv_mcol(Si[n4 & 0xFF] & 0xFF ^ (Si[n8 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n6 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n5 >> 24 & 0xFF] << 24) ^ nArray[n7][0];
            n2 = AESLightEngine.inv_mcol(Si[n5 & 0xFF] & 0xFF ^ (Si[n4 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n8 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n6 >> 24 & 0xFF] << 24) ^ nArray[n7][1];
            n = AESLightEngine.inv_mcol(Si[n6 & 0xFF] & 0xFF ^ (Si[n5 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n4 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n8 >> 24 & 0xFF] << 24) ^ nArray[n7][2];
            n8 = AESLightEngine.inv_mcol(Si[n8 & 0xFF] & 0xFF ^ (Si[n6 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n5 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n4 >> 24 & 0xFF] << 24) ^ nArray[n7--][3];
            n4 = AESLightEngine.inv_mcol(Si[n3 & 0xFF] & 0xFF ^ (Si[n8 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n2 >> 24 & 0xFF] << 24) ^ nArray[n7][0];
            n5 = AESLightEngine.inv_mcol(Si[n2 & 0xFF] & 0xFF ^ (Si[n3 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n8 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n >> 24 & 0xFF] << 24) ^ nArray[n7][1];
            n6 = AESLightEngine.inv_mcol(Si[n & 0xFF] & 0xFF ^ (Si[n2 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n3 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n8 >> 24 & 0xFF] << 24) ^ nArray[n7][2];
            n8 = AESLightEngine.inv_mcol(Si[n8 & 0xFF] & 0xFF ^ (Si[n >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n2 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n3 >> 24 & 0xFF] << 24) ^ nArray[n7--][3];
        }
        n3 = AESLightEngine.inv_mcol(Si[n4 & 0xFF] & 0xFF ^ (Si[n8 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n6 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n5 >> 24 & 0xFF] << 24) ^ nArray[n7][0];
        n2 = AESLightEngine.inv_mcol(Si[n5 & 0xFF] & 0xFF ^ (Si[n4 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n8 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n6 >> 24 & 0xFF] << 24) ^ nArray[n7][1];
        n = AESLightEngine.inv_mcol(Si[n6 & 0xFF] & 0xFF ^ (Si[n5 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n4 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n8 >> 24 & 0xFF] << 24) ^ nArray[n7][2];
        n8 = AESLightEngine.inv_mcol(Si[n8 & 0xFF] & 0xFF ^ (Si[n6 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n5 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n4 >> 24 & 0xFF] << 24) ^ nArray[n7][3];
        this.C0 = Si[n3 & 0xFF] & 0xFF ^ (Si[n8 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n2 >> 24 & 0xFF] << 24 ^ nArray[0][0];
        this.C1 = Si[n2 & 0xFF] & 0xFF ^ (Si[n3 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n8 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n >> 24 & 0xFF] << 24 ^ nArray[0][1];
        this.C2 = Si[n & 0xFF] & 0xFF ^ (Si[n2 >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n3 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n8 >> 24 & 0xFF] << 24 ^ nArray[0][2];
        this.C3 = Si[n8 & 0xFF] & 0xFF ^ (Si[n >> 8 & 0xFF] & 0xFF) << 8 ^ (Si[n2 >> 16 & 0xFF] & 0xFF) << 16 ^ Si[n3 >> 24 & 0xFF] << 24 ^ nArray[0][3];
    }
}

