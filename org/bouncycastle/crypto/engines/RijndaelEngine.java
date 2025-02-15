/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class RijndaelEngine
implements BlockCipher {
    private static final int MAXROUNDS = 14;
    private static final int MAXKC = 64;
    private static final byte[] logtable = new byte[]{0, 0, 25, 1, 50, 2, 26, -58, 75, -57, 27, 104, 51, -18, -33, 3, 100, 4, -32, 14, 52, -115, -127, -17, 76, 113, 8, -56, -8, 105, 28, -63, 125, -62, 29, -75, -7, -71, 39, 106, 77, -28, -90, 114, -102, -55, 9, 120, 101, 47, -118, 5, 33, 15, -31, 36, 18, -16, -126, 69, 53, -109, -38, -114, -106, -113, -37, -67, 54, -48, -50, -108, 19, 92, -46, -15, 64, 70, -125, 56, 102, -35, -3, 48, -65, 6, -117, 98, -77, 37, -30, -104, 34, -120, -111, 16, 126, 110, 72, -61, -93, -74, 30, 66, 58, 107, 40, 84, -6, -123, 61, -70, 43, 121, 10, 21, -101, -97, 94, -54, 78, -44, -84, -27, -13, 115, -89, 87, -81, 88, -88, 80, -12, -22, -42, 116, 79, -82, -23, -43, -25, -26, -83, -24, 44, -41, 117, 122, -21, 22, 11, -11, 89, -53, 95, -80, -100, -87, 81, -96, 127, 12, -10, 111, 23, -60, 73, -20, -40, 67, 31, 45, -92, 118, 123, -73, -52, -69, 62, 90, -5, 96, -79, -122, 59, 82, -95, 108, -86, 85, 41, -99, -105, -78, -121, -112, 97, -66, -36, -4, -68, -107, -49, -51, 55, 63, 91, -47, 83, 57, -124, 60, 65, -94, 109, 71, 20, 42, -98, 93, 86, -14, -45, -85, 68, 17, -110, -39, 35, 32, 46, -119, -76, 124, -72, 38, 119, -103, -29, -91, 103, 74, -19, -34, -59, 49, -2, 24, 13, 99, -116, -128, -64, -9, 112, 7};
    private static final byte[] aLogtable = new byte[]{0, 3, 5, 15, 17, 51, 85, -1, 26, 46, 114, -106, -95, -8, 19, 53, 95, -31, 56, 72, -40, 115, -107, -92, -9, 2, 6, 10, 30, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, 112, -112, -85, -26, 49, 83, -11, 4, 12, 20, 60, 68, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, -32, 59, 77, -41, 98, -90, -15, 8, 24, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, 127, -127, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, 48, 80, -16, 11, 29, 39, 105, -69, -42, 97, -93, -2, 25, 43, 125, -121, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, -96, -5, 22, 58, 78, -46, 109, -73, -62, 93, -25, 50, 86, -6, 21, 63, 65, -61, 94, -30, 61, 71, -55, 64, -64, 91, -19, 44, 116, -100, -65, -38, 117, -97, -70, -43, 100, -84, -17, 42, 126, -126, -99, -68, -33, 122, -114, -119, -128, -101, -74, -63, 88, -24, 35, 101, -81, -22, 37, 111, -79, -56, 67, -59, 84, -4, 31, 33, 99, -91, -12, 7, 9, 27, 45, 119, -103, -80, -53, 70, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, 14, 18, 54, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, -89, -14, 13, 23, 57, 75, -35, 124, -124, -105, -94, -3, 28, 36, 108, -76, -57, 82, -10, 1, 3, 5, 15, 17, 51, 85, -1, 26, 46, 114, -106, -95, -8, 19, 53, 95, -31, 56, 72, -40, 115, -107, -92, -9, 2, 6, 10, 30, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, 112, -112, -85, -26, 49, 83, -11, 4, 12, 20, 60, 68, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, -32, 59, 77, -41, 98, -90, -15, 8, 24, 40, 120, -120, -125, -98, -71, -48, 107, -67, -36, 127, -127, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, 48, 80, -16, 11, 29, 39, 105, -69, -42, 97, -93, -2, 25, 43, 125, -121, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, -96, -5, 22, 58, 78, -46, 109, -73, -62, 93, -25, 50, 86, -6, 21, 63, 65, -61, 94, -30, 61, 71, -55, 64, -64, 91, -19, 44, 116, -100, -65, -38, 117, -97, -70, -43, 100, -84, -17, 42, 126, -126, -99, -68, -33, 122, -114, -119, -128, -101, -74, -63, 88, -24, 35, 101, -81, -22, 37, 111, -79, -56, 67, -59, 84, -4, 31, 33, 99, -91, -12, 7, 9, 27, 45, 119, -103, -80, -53, 70, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, 14, 18, 54, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, -89, -14, 13, 23, 57, 75, -35, 124, -124, -105, -94, -3, 28, 36, 108, -76, -57, 82, -10, 1};
    private static final byte[] S = new byte[]{99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    private static final byte[] Si = new byte[]{82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, -128, -20, 95, 96, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125};
    private static final int[] rcon = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, 197, 145};
    static byte[][] shifts0 = new byte[][]{{0, 8, 16, 24}, {0, 8, 16, 24}, {0, 8, 16, 24}, {0, 8, 16, 32}, {0, 8, 24, 32}};
    static byte[][] shifts1 = new byte[][]{{0, 24, 16, 8}, {0, 32, 24, 16}, {0, 40, 32, 24}, {0, 48, 40, 24}, {0, 56, 40, 32}};
    private int BC;
    private long BC_MASK;
    private int ROUNDS;
    private int blockBits;
    private long[][] workingKey;
    private long A0;
    private long A1;
    private long A2;
    private long A3;
    private boolean forEncryption;
    private byte[] shifts0SC;
    private byte[] shifts1SC;

    private byte mul0x2(int n) {
        if (n != 0) {
            return aLogtable[25 + (logtable[n] & 0xFF)];
        }
        return 0;
    }

    private byte mul0x3(int n) {
        if (n != 0) {
            return aLogtable[1 + (logtable[n] & 0xFF)];
        }
        return 0;
    }

    private byte mul0x9(int n) {
        if (n >= 0) {
            return aLogtable[199 + n];
        }
        return 0;
    }

    private byte mul0xb(int n) {
        if (n >= 0) {
            return aLogtable[104 + n];
        }
        return 0;
    }

    private byte mul0xd(int n) {
        if (n >= 0) {
            return aLogtable[238 + n];
        }
        return 0;
    }

    private byte mul0xe(int n) {
        if (n >= 0) {
            return aLogtable[223 + n];
        }
        return 0;
    }

    private void KeyAddition(long[] lArray) {
        this.A0 ^= lArray[0];
        this.A1 ^= lArray[1];
        this.A2 ^= lArray[2];
        this.A3 ^= lArray[3];
    }

    private long shift(long l, int n) {
        return (l >>> n | l << this.BC - n) & this.BC_MASK;
    }

    private void ShiftRow(byte[] byArray) {
        this.A1 = this.shift(this.A1, byArray[1]);
        this.A2 = this.shift(this.A2, byArray[2]);
        this.A3 = this.shift(this.A3, byArray[3]);
    }

    private long applyS(long l, byte[] byArray) {
        long l2 = 0L;
        for (int i = 0; i < this.BC; i += 8) {
            l2 |= (long)(byArray[(int)(l >> i & 0xFFL)] & 0xFF) << i;
        }
        return l2;
    }

    private void Substitution(byte[] byArray) {
        this.A0 = this.applyS(this.A0, byArray);
        this.A1 = this.applyS(this.A1, byArray);
        this.A2 = this.applyS(this.A2, byArray);
        this.A3 = this.applyS(this.A3, byArray);
    }

    private void MixColumn() {
        long l = 0L;
        long l2 = 0L;
        long l3 = 0L;
        long l4 = 0L;
        for (int i = 0; i < this.BC; i += 8) {
            int n = (int)(this.A0 >> i & 0xFFL);
            int n2 = (int)(this.A1 >> i & 0xFFL);
            int n3 = (int)(this.A2 >> i & 0xFFL);
            int n4 = (int)(this.A3 >> i & 0xFFL);
            l4 |= (long)((this.mul0x2(n) ^ this.mul0x3(n2) ^ n3 ^ n4) & 0xFF) << i;
            l3 |= (long)((this.mul0x2(n2) ^ this.mul0x3(n3) ^ n4 ^ n) & 0xFF) << i;
            l2 |= (long)((this.mul0x2(n3) ^ this.mul0x3(n4) ^ n ^ n2) & 0xFF) << i;
            l |= (long)((this.mul0x2(n4) ^ this.mul0x3(n) ^ n2 ^ n3) & 0xFF) << i;
        }
        this.A0 = l4;
        this.A1 = l3;
        this.A2 = l2;
        this.A3 = l;
    }

    private void InvMixColumn() {
        long l = 0L;
        long l2 = 0L;
        long l3 = 0L;
        long l4 = 0L;
        for (int i = 0; i < this.BC; i += 8) {
            int n = (int)(this.A0 >> i & 0xFFL);
            int n2 = (int)(this.A1 >> i & 0xFFL);
            int n3 = (int)(this.A2 >> i & 0xFFL);
            int n4 = (int)(this.A3 >> i & 0xFFL);
            n = n != 0 ? logtable[n & 0xFF] & 0xFF : -1;
            n2 = n2 != 0 ? logtable[n2 & 0xFF] & 0xFF : -1;
            n3 = n3 != 0 ? logtable[n3 & 0xFF] & 0xFF : -1;
            n4 = n4 != 0 ? logtable[n4 & 0xFF] & 0xFF : -1;
            l4 |= (long)((this.mul0xe(n) ^ this.mul0xb(n2) ^ this.mul0xd(n3) ^ this.mul0x9(n4)) & 0xFF) << i;
            l3 |= (long)((this.mul0xe(n2) ^ this.mul0xb(n3) ^ this.mul0xd(n4) ^ this.mul0x9(n)) & 0xFF) << i;
            l2 |= (long)((this.mul0xe(n3) ^ this.mul0xb(n4) ^ this.mul0xd(n) ^ this.mul0x9(n2)) & 0xFF) << i;
            l |= (long)((this.mul0xe(n4) ^ this.mul0xb(n) ^ this.mul0xd(n2) ^ this.mul0x9(n3)) & 0xFF) << i;
        }
        this.A0 = l4;
        this.A1 = l3;
        this.A2 = l2;
        this.A3 = l;
    }

    private long[][] generateWorkingKey(byte[] byArray) {
        int n;
        int n2;
        int n3;
        int n4 = 0;
        int n5 = byArray.length * 8;
        byte[][] byArray2 = new byte[4][64];
        long[][] lArray = new long[15][4];
        switch (n5) {
            case 128: {
                n3 = 4;
                break;
            }
            case 160: {
                n3 = 5;
                break;
            }
            case 192: {
                n3 = 6;
                break;
            }
            case 224: {
                n3 = 7;
                break;
            }
            case 256: {
                n3 = 8;
                break;
            }
            default: {
                throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
            }
        }
        this.ROUNDS = n5 >= this.blockBits ? n3 + 6 : this.BC / 8 + 6;
        int n6 = 0;
        for (n2 = 0; n2 < byArray.length; ++n2) {
            byArray2[n2 % 4][n2 / 4] = byArray[n6++];
        }
        int n7 = 0;
        for (n2 = 0; n2 < n3 && n7 < (this.ROUNDS + 1) * (this.BC / 8); ++n2, ++n7) {
            for (n = 0; n < 4; ++n) {
                long[] lArray2 = lArray[n7 / (this.BC / 8)];
                int n8 = n;
                lArray2[n8] = lArray2[n8] | (long)(byArray2[n][n2] & 0xFF) << n7 * 8 % this.BC;
            }
        }
        while (n7 < (this.ROUNDS + 1) * (this.BC / 8)) {
            for (n2 = 0; n2 < 4; ++n2) {
                byte[] byArray3 = byArray2[n2];
                byArray3[0] = (byte)(byArray3[0] ^ S[byArray2[(n2 + 1) % 4][n3 - 1] & 0xFF]);
            }
            byte[] byArray4 = byArray2[0];
            byArray4[0] = (byte)(byArray4[0] ^ rcon[n4++]);
            if (n3 <= 6) {
                for (n2 = 1; n2 < n3; ++n2) {
                    for (n = 0; n < 4; ++n) {
                        byte[] byArray5 = byArray2[n];
                        int n9 = n2;
                        byArray5[n9] = (byte)(byArray5[n9] ^ byArray2[n][n2 - 1]);
                    }
                }
            } else {
                for (n2 = 1; n2 < 4; ++n2) {
                    for (n = 0; n < 4; ++n) {
                        byte[] byArray6 = byArray2[n];
                        int n10 = n2;
                        byArray6[n10] = (byte)(byArray6[n10] ^ byArray2[n][n2 - 1]);
                    }
                }
                for (n2 = 0; n2 < 4; ++n2) {
                    byte[] byArray7 = byArray2[n2];
                    byArray7[4] = (byte)(byArray7[4] ^ S[byArray2[n2][3] & 0xFF]);
                }
                for (n2 = 5; n2 < n3; ++n2) {
                    for (n = 0; n < 4; ++n) {
                        byte[] byArray8 = byArray2[n];
                        int n11 = n2;
                        byArray8[n11] = (byte)(byArray8[n11] ^ byArray2[n][n2 - 1]);
                    }
                }
            }
            for (n2 = 0; n2 < n3 && n7 < (this.ROUNDS + 1) * (this.BC / 8); ++n2, ++n7) {
                for (n = 0; n < 4; ++n) {
                    long[] lArray3 = lArray[n7 / (this.BC / 8)];
                    int n12 = n;
                    lArray3[n12] = lArray3[n12] | (long)(byArray2[n][n2] & 0xFF) << n7 * 8 % this.BC;
                }
            }
        }
        return lArray;
    }

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int n) {
        switch (n) {
            case 128: {
                this.BC = 32;
                this.BC_MASK = 0xFFFFFFFFL;
                this.shifts0SC = shifts0[0];
                this.shifts1SC = shifts1[0];
                break;
            }
            case 160: {
                this.BC = 40;
                this.BC_MASK = 0xFFFFFFFFFFL;
                this.shifts0SC = shifts0[1];
                this.shifts1SC = shifts1[1];
                break;
            }
            case 192: {
                this.BC = 48;
                this.BC_MASK = 0xFFFFFFFFFFFFL;
                this.shifts0SC = shifts0[2];
                this.shifts1SC = shifts1[2];
                break;
            }
            case 224: {
                this.BC = 56;
                this.BC_MASK = 0xFFFFFFFFFFFFFFL;
                this.shifts0SC = shifts0[3];
                this.shifts1SC = shifts1[3];
                break;
            }
            case 256: {
                this.BC = 64;
                this.BC_MASK = -1L;
                this.shifts0SC = shifts0[4];
                this.shifts1SC = shifts1[4];
                break;
            }
            default: {
                throw new IllegalArgumentException("unknown blocksize to Rijndael");
            }
        }
        this.blockBits = n;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = this.generateWorkingKey(((KeyParameter)cipherParameters).getKey());
            this.forEncryption = bl;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
    }

    public String getAlgorithmName() {
        return "Rijndael";
    }

    public int getBlockSize() {
        return this.BC / 2;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Rijndael engine not initialised");
        }
        if (n + this.BC / 2 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + this.BC / 2 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            this.unpackBlock(byArray, n);
            this.encryptBlock(this.workingKey);
            this.packBlock(byArray2, n2);
        } else {
            this.unpackBlock(byArray, n);
            this.decryptBlock(this.workingKey);
            this.packBlock(byArray2, n2);
        }
        return this.BC / 2;
    }

    public void reset() {
    }

    private void unpackBlock(byte[] byArray, int n) {
        int n2 = n;
        this.A0 = byArray[n2++] & 0xFF;
        this.A1 = byArray[n2++] & 0xFF;
        this.A2 = byArray[n2++] & 0xFF;
        this.A3 = byArray[n2++] & 0xFF;
        for (int i = 8; i != this.BC; i += 8) {
            this.A0 |= (long)(byArray[n2++] & 0xFF) << i;
            this.A1 |= (long)(byArray[n2++] & 0xFF) << i;
            this.A2 |= (long)(byArray[n2++] & 0xFF) << i;
            this.A3 |= (long)(byArray[n2++] & 0xFF) << i;
        }
    }

    private void packBlock(byte[] byArray, int n) {
        int n2 = n;
        for (int i = 0; i != this.BC; i += 8) {
            byArray[n2++] = (byte)(this.A0 >> i);
            byArray[n2++] = (byte)(this.A1 >> i);
            byArray[n2++] = (byte)(this.A2 >> i);
            byArray[n2++] = (byte)(this.A3 >> i);
        }
    }

    private void encryptBlock(long[][] lArray) {
        this.KeyAddition(lArray[0]);
        for (int i = 1; i < this.ROUNDS; ++i) {
            this.Substitution(S);
            this.ShiftRow(this.shifts0SC);
            this.MixColumn();
            this.KeyAddition(lArray[i]);
        }
        this.Substitution(S);
        this.ShiftRow(this.shifts0SC);
        this.KeyAddition(lArray[this.ROUNDS]);
    }

    private void decryptBlock(long[][] lArray) {
        this.KeyAddition(lArray[this.ROUNDS]);
        this.Substitution(Si);
        this.ShiftRow(this.shifts1SC);
        for (int i = this.ROUNDS - 1; i > 0; --i) {
            this.KeyAddition(lArray[i]);
            this.InvMixColumn();
            this.Substitution(Si);
            this.ShiftRow(this.shifts1SC);
        }
        this.KeyAddition(lArray[0]);
    }
}

