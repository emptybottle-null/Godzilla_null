/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class ARIAEngine
implements BlockCipher {
    private static final byte[][] C = new byte[][]{Hex.decode("517cc1b727220a94fe13abe8fa9a6ee0"), Hex.decode("6db14acc9e21c820ff28b1d5ef5de2b0"), Hex.decode("db92371d2126e9700324977504e8c90e")};
    private static final byte[] SB1_sbox = new byte[]{99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    private static final byte[] SB2_sbox = new byte[]{-30, 78, 84, -4, -108, -62, 74, -52, 98, 13, 106, 70, 60, 77, -117, -47, 94, -6, 100, -53, -76, -105, -66, 43, -68, 119, 46, 3, -45, 25, 89, -63, 29, 6, 65, 107, 85, -16, -103, 105, -22, -100, 24, -82, 99, -33, -25, -69, 0, 115, 102, -5, -106, 76, -123, -28, 58, 9, 69, -86, 15, -18, 16, -21, 45, 127, -12, 41, -84, -49, -83, -111, -115, 120, -56, -107, -7, 47, -50, -51, 8, 122, -120, 56, 92, -125, 42, 40, 71, -37, -72, -57, -109, -92, 18, 83, -1, -121, 14, 49, 54, 33, 88, 72, 1, -114, 55, 116, 50, -54, -23, -79, -73, -85, 12, -41, -60, 86, 66, 38, 7, -104, 96, -39, -74, -71, 17, 64, -20, 32, -116, -67, -96, -55, -124, 4, 73, 35, -15, 79, 80, 31, 19, -36, -40, -64, -98, 87, -29, -61, 123, 101, 59, 2, -113, 62, -24, 37, -110, -27, 21, -35, -3, 23, -87, -65, -44, -102, 126, -59, 57, 103, -2, 118, -99, 67, -89, -31, -48, -11, 104, -14, 27, 52, 112, 5, -93, -118, -43, 121, -122, -88, 48, -58, 81, 75, 30, -90, 39, -10, 53, -46, 110, 36, 22, -126, 95, -38, -26, 117, -94, -17, 44, -78, 28, -97, 93, 111, -128, 10, 114, 68, -101, 108, -112, 11, 91, 51, 125, 90, 82, -13, 97, -95, -9, -80, -42, 63, 124, 109, -19, 20, -32, -91, 61, 34, -77, -8, -119, -34, 113, 26, -81, -70, -75, -127};
    private static final byte[] SB3_sbox = new byte[]{82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, -128, -20, 95, 96, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125};
    private static final byte[] SB4_sbox = new byte[]{48, 104, -103, 27, -121, -71, 33, 120, 80, 57, -37, -31, 114, 9, 98, 60, 62, 126, 94, -114, -15, -96, -52, -93, 42, 29, -5, -74, -42, 32, -60, -115, -127, 101, -11, -119, -53, -99, 119, -58, 87, 67, 86, 23, -44, 64, 26, 77, -64, 99, 108, -29, -73, -56, 100, 106, 83, -86, 56, -104, 12, -12, -101, -19, 127, 34, 118, -81, -35, 58, 11, 88, 103, -120, 6, -61, 53, 13, 1, -117, -116, -62, -26, 95, 2, 36, 117, -109, 102, 30, -27, -30, 84, -40, 16, -50, 122, -24, 8, 44, 18, -105, 50, -85, -76, 39, 10, 35, -33, -17, -54, -39, -72, -6, -36, 49, 107, -47, -83, 25, 73, -67, 81, -106, -18, -28, -88, 65, -38, -1, -51, 85, -122, 54, -66, 97, 82, -8, -69, 14, -126, 72, 105, -102, -32, 71, -98, 92, 4, 75, 52, 21, 121, 38, -89, -34, 41, -82, -110, -41, -124, -23, -46, -70, 93, -13, -59, -80, -65, -92, 59, 113, 68, 70, 43, -4, -21, 111, -43, -10, 20, -2, 124, 112, 90, 125, -3, 47, 24, -125, 22, -91, -111, 31, 5, -107, 116, -87, -63, 91, 74, -123, 109, 19, 7, 79, 78, 69, -78, 15, -55, 28, -90, -68, -20, 115, -112, 123, -49, 89, -113, -95, -7, 45, -14, -79, 0, -108, 55, -97, -48, 46, -100, 110, 40, 63, -128, -16, 61, -45, 37, -118, -75, -25, 66, -77, -57, -22, -9, 76, 17, 51, 3, -94, -84, 96};
    protected static final int BLOCK_SIZE = 16;
    private byte[][] roundKeys;

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to ARIA init - " + cipherParameters.getClass().getName());
        }
        this.roundKeys = ARIAEngine.keySchedule(bl, ((KeyParameter)cipherParameters).getKey());
    }

    public String getAlgorithmName() {
        return "ARIA";
    }

    public int getBlockSize() {
        return 16;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (this.roundKeys == null) {
            throw new IllegalStateException("ARIA engine not initialised");
        }
        if (n > byArray.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 > byArray2.length - 16) {
            throw new OutputLengthException("output buffer too short");
        }
        byte[] byArray3 = new byte[16];
        System.arraycopy(byArray, n, byArray3, 0, 16);
        int n3 = 0;
        int n4 = this.roundKeys.length - 3;
        while (n3 < n4) {
            ARIAEngine.FO(byArray3, this.roundKeys[n3++]);
            ARIAEngine.FE(byArray3, this.roundKeys[n3++]);
        }
        ARIAEngine.FO(byArray3, this.roundKeys[n3++]);
        ARIAEngine.xor(byArray3, this.roundKeys[n3++]);
        ARIAEngine.SL2(byArray3);
        ARIAEngine.xor(byArray3, this.roundKeys[n3]);
        System.arraycopy(byArray3, 0, byArray2, n2, 16);
        return 16;
    }

    public void reset() {
    }

    protected static void A(byte[] byArray) {
        byte by = byArray[0];
        byte by2 = byArray[1];
        byte by3 = byArray[2];
        byte by4 = byArray[3];
        byte by5 = byArray[4];
        byte by6 = byArray[5];
        byte by7 = byArray[6];
        byte by8 = byArray[7];
        byte by9 = byArray[8];
        byte by10 = byArray[9];
        byte by11 = byArray[10];
        byte by12 = byArray[11];
        byte by13 = byArray[12];
        byte by14 = byArray[13];
        byte by15 = byArray[14];
        byte by16 = byArray[15];
        byArray[0] = (byte)(by4 ^ by5 ^ by7 ^ by9 ^ by10 ^ by14 ^ by15);
        byArray[1] = (byte)(by3 ^ by6 ^ by8 ^ by9 ^ by10 ^ by13 ^ by16);
        byArray[2] = (byte)(by2 ^ by5 ^ by7 ^ by11 ^ by12 ^ by13 ^ by16);
        byArray[3] = (byte)(by ^ by6 ^ by8 ^ by11 ^ by12 ^ by14 ^ by15);
        byArray[4] = (byte)(by ^ by3 ^ by6 ^ by9 ^ by12 ^ by15 ^ by16);
        byArray[5] = (byte)(by2 ^ by4 ^ by5 ^ by10 ^ by11 ^ by15 ^ by16);
        byArray[6] = (byte)(by ^ by3 ^ by8 ^ by10 ^ by11 ^ by13 ^ by14);
        byArray[7] = (byte)(by2 ^ by4 ^ by7 ^ by9 ^ by12 ^ by13 ^ by14);
        byArray[8] = (byte)(by ^ by2 ^ by5 ^ by8 ^ by11 ^ by14 ^ by16);
        byArray[9] = (byte)(by ^ by2 ^ by6 ^ by7 ^ by12 ^ by13 ^ by15);
        byArray[10] = (byte)(by3 ^ by4 ^ by6 ^ by7 ^ by9 ^ by14 ^ by16);
        byArray[11] = (byte)(by3 ^ by4 ^ by5 ^ by8 ^ by10 ^ by13 ^ by15);
        byArray[12] = (byte)(by2 ^ by3 ^ by7 ^ by8 ^ by10 ^ by12 ^ by13);
        byArray[13] = (byte)(by ^ by4 ^ by7 ^ by8 ^ by9 ^ by11 ^ by14);
        byArray[14] = (byte)(by ^ by4 ^ by5 ^ by6 ^ by10 ^ by12 ^ by15);
        byArray[15] = (byte)(by2 ^ by3 ^ by5 ^ by6 ^ by9 ^ by11 ^ by16);
    }

    protected static void FE(byte[] byArray, byte[] byArray2) {
        ARIAEngine.xor(byArray, byArray2);
        ARIAEngine.SL2(byArray);
        ARIAEngine.A(byArray);
    }

    protected static void FO(byte[] byArray, byte[] byArray2) {
        ARIAEngine.xor(byArray, byArray2);
        ARIAEngine.SL1(byArray);
        ARIAEngine.A(byArray);
    }

    protected static byte[][] keySchedule(boolean bl, byte[] byArray) {
        int n = byArray.length;
        if (n < 16 || n > 32 || (n & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int n2 = (n >>> 3) - 2;
        byte[] byArray2 = C[n2];
        byte[] byArray3 = C[(n2 + 1) % 3];
        byte[] byArray4 = C[(n2 + 2) % 3];
        byte[] byArray5 = new byte[16];
        byte[] byArray6 = new byte[16];
        System.arraycopy(byArray, 0, byArray5, 0, 16);
        System.arraycopy(byArray, 16, byArray6, 0, n - 16);
        byte[] byArray7 = new byte[16];
        byte[] byArray8 = new byte[16];
        byte[] byArray9 = new byte[16];
        byte[] byArray10 = new byte[16];
        System.arraycopy(byArray5, 0, byArray7, 0, 16);
        System.arraycopy(byArray7, 0, byArray8, 0, 16);
        ARIAEngine.FO(byArray8, byArray2);
        ARIAEngine.xor(byArray8, byArray6);
        System.arraycopy(byArray8, 0, byArray9, 0, 16);
        ARIAEngine.FE(byArray9, byArray3);
        ARIAEngine.xor(byArray9, byArray7);
        System.arraycopy(byArray9, 0, byArray10, 0, 16);
        ARIAEngine.FO(byArray10, byArray4);
        ARIAEngine.xor(byArray10, byArray8);
        int n3 = 12 + n2 * 2;
        byte[][] byArray11 = new byte[n3 + 1][16];
        ARIAEngine.keyScheduleRound(byArray11[0], byArray7, byArray8, 19);
        ARIAEngine.keyScheduleRound(byArray11[1], byArray8, byArray9, 19);
        ARIAEngine.keyScheduleRound(byArray11[2], byArray9, byArray10, 19);
        ARIAEngine.keyScheduleRound(byArray11[3], byArray10, byArray7, 19);
        ARIAEngine.keyScheduleRound(byArray11[4], byArray7, byArray8, 31);
        ARIAEngine.keyScheduleRound(byArray11[5], byArray8, byArray9, 31);
        ARIAEngine.keyScheduleRound(byArray11[6], byArray9, byArray10, 31);
        ARIAEngine.keyScheduleRound(byArray11[7], byArray10, byArray7, 31);
        ARIAEngine.keyScheduleRound(byArray11[8], byArray7, byArray8, 67);
        ARIAEngine.keyScheduleRound(byArray11[9], byArray8, byArray9, 67);
        ARIAEngine.keyScheduleRound(byArray11[10], byArray9, byArray10, 67);
        ARIAEngine.keyScheduleRound(byArray11[11], byArray10, byArray7, 67);
        ARIAEngine.keyScheduleRound(byArray11[12], byArray7, byArray8, 97);
        if (n3 > 12) {
            ARIAEngine.keyScheduleRound(byArray11[13], byArray8, byArray9, 97);
            ARIAEngine.keyScheduleRound(byArray11[14], byArray9, byArray10, 97);
            if (n3 > 14) {
                ARIAEngine.keyScheduleRound(byArray11[15], byArray10, byArray7, 97);
                ARIAEngine.keyScheduleRound(byArray11[16], byArray7, byArray8, 109);
            }
        }
        if (!bl) {
            ARIAEngine.reverseKeys(byArray11);
            for (int i = 1; i < n3; ++i) {
                ARIAEngine.A(byArray11[i]);
            }
        }
        return byArray11;
    }

    protected static void keyScheduleRound(byte[] byArray, byte[] byArray2, byte[] byArray3, int n) {
        int n2 = n >>> 3;
        int n3 = n & 7;
        int n4 = 8 - n3;
        int n5 = byArray3[15 - n2] & 0xFF;
        for (int i = 0; i < 16; ++i) {
            int n6 = byArray3[i - n2 & 0xF] & 0xFF;
            int n7 = n5 << n4 | n6 >>> n3;
            byArray[i] = (byte)(n7 ^= byArray2[i] & 0xFF);
            n5 = n6;
        }
    }

    protected static void reverseKeys(byte[][] byArray) {
        int n = byArray.length;
        int n2 = n / 2;
        int n3 = n - 1;
        for (int i = 0; i < n2; ++i) {
            byte[] byArray2 = byArray[i];
            byArray[i] = byArray[n3 - i];
            byArray[n3 - i] = byArray2;
        }
    }

    protected static byte SB1(byte by) {
        return SB1_sbox[by & 0xFF];
    }

    protected static byte SB2(byte by) {
        return SB2_sbox[by & 0xFF];
    }

    protected static byte SB3(byte by) {
        return SB3_sbox[by & 0xFF];
    }

    protected static byte SB4(byte by) {
        return SB4_sbox[by & 0xFF];
    }

    protected static void SL1(byte[] byArray) {
        byArray[0] = ARIAEngine.SB1(byArray[0]);
        byArray[1] = ARIAEngine.SB2(byArray[1]);
        byArray[2] = ARIAEngine.SB3(byArray[2]);
        byArray[3] = ARIAEngine.SB4(byArray[3]);
        byArray[4] = ARIAEngine.SB1(byArray[4]);
        byArray[5] = ARIAEngine.SB2(byArray[5]);
        byArray[6] = ARIAEngine.SB3(byArray[6]);
        byArray[7] = ARIAEngine.SB4(byArray[7]);
        byArray[8] = ARIAEngine.SB1(byArray[8]);
        byArray[9] = ARIAEngine.SB2(byArray[9]);
        byArray[10] = ARIAEngine.SB3(byArray[10]);
        byArray[11] = ARIAEngine.SB4(byArray[11]);
        byArray[12] = ARIAEngine.SB1(byArray[12]);
        byArray[13] = ARIAEngine.SB2(byArray[13]);
        byArray[14] = ARIAEngine.SB3(byArray[14]);
        byArray[15] = ARIAEngine.SB4(byArray[15]);
    }

    protected static void SL2(byte[] byArray) {
        byArray[0] = ARIAEngine.SB3(byArray[0]);
        byArray[1] = ARIAEngine.SB4(byArray[1]);
        byArray[2] = ARIAEngine.SB1(byArray[2]);
        byArray[3] = ARIAEngine.SB2(byArray[3]);
        byArray[4] = ARIAEngine.SB3(byArray[4]);
        byArray[5] = ARIAEngine.SB4(byArray[5]);
        byArray[6] = ARIAEngine.SB1(byArray[6]);
        byArray[7] = ARIAEngine.SB2(byArray[7]);
        byArray[8] = ARIAEngine.SB3(byArray[8]);
        byArray[9] = ARIAEngine.SB4(byArray[9]);
        byArray[10] = ARIAEngine.SB1(byArray[10]);
        byArray[11] = ARIAEngine.SB2(byArray[11]);
        byArray[12] = ARIAEngine.SB3(byArray[12]);
        byArray[13] = ARIAEngine.SB4(byArray[13]);
        byArray[14] = ARIAEngine.SB1(byArray[14]);
        byArray[15] = ARIAEngine.SB2(byArray[15]);
    }

    protected static void xor(byte[] byArray, byte[] byArray2) {
        for (int i = 0; i < 16; ++i) {
            int n = i;
            byArray[n] = (byte)(byArray[n] ^ byArray2[i]);
        }
    }
}

