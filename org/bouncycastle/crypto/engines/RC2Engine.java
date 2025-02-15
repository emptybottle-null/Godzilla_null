/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RC2Parameters;

public class RC2Engine
implements BlockCipher {
    private static byte[] piTable = new byte[]{-39, 120, -7, -60, 25, -35, -75, -19, 40, -23, -3, 121, 74, -96, -40, -99, -58, 126, 55, -125, 43, 118, 83, -114, 98, 76, 100, -120, 68, -117, -5, -94, 23, -102, 89, -11, -121, -77, 79, 19, 97, 69, 109, -115, 9, -127, 125, 50, -67, -113, 64, -21, -122, -73, 123, 11, -16, -107, 33, 34, 92, 107, 78, -126, 84, -42, 101, -109, -50, 96, -78, 28, 115, 86, -64, 20, -89, -116, -15, -36, 18, 117, -54, 31, 59, -66, -28, -47, 66, 61, -44, 48, -93, 60, -74, 38, 111, -65, 14, -38, 70, 105, 7, 87, 39, -14, 29, -101, -68, -108, 67, 3, -8, 17, -57, -10, -112, -17, 62, -25, 6, -61, -43, 47, -56, 102, 30, -41, 8, -24, -22, -34, -128, 82, -18, -9, -124, -86, 114, -84, 53, 77, 106, 42, -106, 26, -46, 113, 90, 21, 73, 116, 75, -97, -48, 94, 4, 24, -92, -20, -62, -32, 65, 110, 15, 81, -53, -52, 36, -111, -81, 80, -95, -12, 112, 57, -103, 124, 58, -123, 35, -72, -76, 122, -4, 2, 54, 91, 37, 85, -105, 49, 45, 93, -6, -104, -29, -118, -110, -82, 5, -33, 41, 16, 103, 108, -70, -55, -45, 0, -26, -49, -31, -98, -88, 44, 99, 22, 1, 63, 88, -30, -119, -87, 13, 56, 52, 27, -85, 51, -1, -80, -69, 72, 12, 95, -71, -79, -51, 46, -59, -13, -37, 71, -27, -91, -100, 119, 10, -90, 32, 104, -2, 127, -63, -83};
    private static final int BLOCK_SIZE = 8;
    private int[] workingKey;
    private boolean encrypting;

    private int[] generateWorkingKey(byte[] byArray, int n) {
        int n2;
        int n3;
        int n4;
        int[] nArray = new int[128];
        for (n4 = 0; n4 != byArray.length; ++n4) {
            nArray[n4] = byArray[n4] & 0xFF;
        }
        n4 = byArray.length;
        if (n4 < 128) {
            n3 = 0;
            n2 = nArray[n4 - 1];
            do {
                n2 = piTable[n2 + nArray[n3++] & 0xFF] & 0xFF;
                nArray[n4++] = n2;
            } while (n4 < 128);
        }
        n4 = n + 7 >> 3;
        nArray[128 - n4] = n2 = piTable[nArray[128 - n4] & 255 >> (7 & -n)] & 0xFF;
        for (n3 = 128 - n4 - 1; n3 >= 0; --n3) {
            nArray[n3] = n2 = piTable[n2 ^ nArray[n3 + n4]] & 0xFF;
        }
        int[] nArray2 = new int[64];
        for (int i = 0; i != nArray2.length; ++i) {
            nArray2[i] = nArray[2 * i] + (nArray[2 * i + 1] << 8);
        }
        return nArray2;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.encrypting = bl;
        if (cipherParameters instanceof RC2Parameters) {
            RC2Parameters rC2Parameters = (RC2Parameters)cipherParameters;
            this.workingKey = this.generateWorkingKey(rC2Parameters.getKey(), rC2Parameters.getEffectiveKeyBits());
        } else if (cipherParameters instanceof KeyParameter) {
            byte[] byArray = ((KeyParameter)cipherParameters).getKey();
            this.workingKey = this.generateWorkingKey(byArray, byArray.length * 8);
        } else {
            throw new IllegalArgumentException("invalid parameter passed to RC2 init - " + cipherParameters.getClass().getName());
        }
    }

    public void reset() {
    }

    public String getAlgorithmName() {
        return "RC2";
    }

    public int getBlockSize() {
        return 8;
    }

    public final int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("RC2 engine not initialised");
        }
        if (n + 8 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 8 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            this.encryptBlock(byArray, n, byArray2, n2);
        } else {
            this.decryptBlock(byArray, n, byArray2, n2);
        }
        return 8;
    }

    private int rotateWordLeft(int n, int n2) {
        return (n &= 0xFFFF) << n2 | n >> 16 - n2;
    }

    private void encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3;
        int n4 = ((byArray[n + 7] & 0xFF) << 8) + (byArray[n + 6] & 0xFF);
        int n5 = ((byArray[n + 5] & 0xFF) << 8) + (byArray[n + 4] & 0xFF);
        int n6 = ((byArray[n + 3] & 0xFF) << 8) + (byArray[n + 2] & 0xFF);
        int n7 = ((byArray[n + 1] & 0xFF) << 8) + (byArray[n + 0] & 0xFF);
        for (n3 = 0; n3 <= 16; n3 += 4) {
            n7 = this.rotateWordLeft(n7 + (n6 & ~n4) + (n5 & n4) + this.workingKey[n3], 1);
            n6 = this.rotateWordLeft(n6 + (n5 & ~n7) + (n4 & n7) + this.workingKey[n3 + 1], 2);
            n5 = this.rotateWordLeft(n5 + (n4 & ~n6) + (n7 & n6) + this.workingKey[n3 + 2], 3);
            n4 = this.rotateWordLeft(n4 + (n7 & ~n5) + (n6 & n5) + this.workingKey[n3 + 3], 5);
        }
        n5 += this.workingKey[(n6 += this.workingKey[(n7 += this.workingKey[n4 & 0x3F]) & 0x3F]) & 0x3F];
        n4 += this.workingKey[n5 & 0x3F];
        for (n3 = 20; n3 <= 40; n3 += 4) {
            n7 = this.rotateWordLeft(n7 + (n6 & ~n4) + (n5 & n4) + this.workingKey[n3], 1);
            n6 = this.rotateWordLeft(n6 + (n5 & ~n7) + (n4 & n7) + this.workingKey[n3 + 1], 2);
            n5 = this.rotateWordLeft(n5 + (n4 & ~n6) + (n7 & n6) + this.workingKey[n3 + 2], 3);
            n4 = this.rotateWordLeft(n4 + (n7 & ~n5) + (n6 & n5) + this.workingKey[n3 + 3], 5);
        }
        n5 += this.workingKey[(n6 += this.workingKey[(n7 += this.workingKey[n4 & 0x3F]) & 0x3F]) & 0x3F];
        n4 += this.workingKey[n5 & 0x3F];
        for (n3 = 44; n3 < 64; n3 += 4) {
            n7 = this.rotateWordLeft(n7 + (n6 & ~n4) + (n5 & n4) + this.workingKey[n3], 1);
            n6 = this.rotateWordLeft(n6 + (n5 & ~n7) + (n4 & n7) + this.workingKey[n3 + 1], 2);
            n5 = this.rotateWordLeft(n5 + (n4 & ~n6) + (n7 & n6) + this.workingKey[n3 + 2], 3);
            n4 = this.rotateWordLeft(n4 + (n7 & ~n5) + (n6 & n5) + this.workingKey[n3 + 3], 5);
        }
        byArray2[n2 + 0] = (byte)n7;
        byArray2[n2 + 1] = (byte)(n7 >> 8);
        byArray2[n2 + 2] = (byte)n6;
        byArray2[n2 + 3] = (byte)(n6 >> 8);
        byArray2[n2 + 4] = (byte)n5;
        byArray2[n2 + 5] = (byte)(n5 >> 8);
        byArray2[n2 + 6] = (byte)n4;
        byArray2[n2 + 7] = (byte)(n4 >> 8);
    }

    private void decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3;
        int n4 = ((byArray[n + 7] & 0xFF) << 8) + (byArray[n + 6] & 0xFF);
        int n5 = ((byArray[n + 5] & 0xFF) << 8) + (byArray[n + 4] & 0xFF);
        int n6 = ((byArray[n + 3] & 0xFF) << 8) + (byArray[n + 2] & 0xFF);
        int n7 = ((byArray[n + 1] & 0xFF) << 8) + (byArray[n + 0] & 0xFF);
        for (n3 = 60; n3 >= 44; n3 -= 4) {
            n4 = this.rotateWordLeft(n4, 11) - ((n7 & ~n5) + (n6 & n5) + this.workingKey[n3 + 3]);
            n5 = this.rotateWordLeft(n5, 13) - ((n4 & ~n6) + (n7 & n6) + this.workingKey[n3 + 2]);
            n6 = this.rotateWordLeft(n6, 14) - ((n5 & ~n7) + (n4 & n7) + this.workingKey[n3 + 1]);
            n7 = this.rotateWordLeft(n7, 15) - ((n6 & ~n4) + (n5 & n4) + this.workingKey[n3]);
        }
        n4 -= this.workingKey[n5 & 0x3F];
        n5 -= this.workingKey[n6 & 0x3F];
        n6 -= this.workingKey[n7 & 0x3F];
        n7 -= this.workingKey[n4 & 0x3F];
        for (n3 = 40; n3 >= 20; n3 -= 4) {
            n4 = this.rotateWordLeft(n4, 11) - ((n7 & ~n5) + (n6 & n5) + this.workingKey[n3 + 3]);
            n5 = this.rotateWordLeft(n5, 13) - ((n4 & ~n6) + (n7 & n6) + this.workingKey[n3 + 2]);
            n6 = this.rotateWordLeft(n6, 14) - ((n5 & ~n7) + (n4 & n7) + this.workingKey[n3 + 1]);
            n7 = this.rotateWordLeft(n7, 15) - ((n6 & ~n4) + (n5 & n4) + this.workingKey[n3]);
        }
        n4 -= this.workingKey[n5 & 0x3F];
        n5 -= this.workingKey[n6 & 0x3F];
        n6 -= this.workingKey[n7 & 0x3F];
        n7 -= this.workingKey[n4 & 0x3F];
        for (n3 = 16; n3 >= 0; n3 -= 4) {
            n4 = this.rotateWordLeft(n4, 11) - ((n7 & ~n5) + (n6 & n5) + this.workingKey[n3 + 3]);
            n5 = this.rotateWordLeft(n5, 13) - ((n4 & ~n6) + (n7 & n6) + this.workingKey[n3 + 2]);
            n6 = this.rotateWordLeft(n6, 14) - ((n5 & ~n7) + (n4 & n7) + this.workingKey[n3 + 1]);
            n7 = this.rotateWordLeft(n7, 15) - ((n6 & ~n4) + (n5 & n4) + this.workingKey[n3]);
        }
        byArray2[n2 + 0] = (byte)n7;
        byArray2[n2 + 1] = (byte)(n7 >> 8);
        byArray2[n2 + 2] = (byte)n6;
        byArray2[n2 + 3] = (byte)(n6 >> 8);
        byArray2[n2 + 4] = (byte)n5;
        byArray2[n2 + 5] = (byte)(n5 >> 8);
        byArray2[n2 + 6] = (byte)n4;
        byArray2[n2 + 7] = (byte)(n4 >> 8);
    }
}

