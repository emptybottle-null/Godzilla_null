/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class DESEngine
implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private int[] workingKey = null;
    private static final short[] bytebit = new short[]{128, 64, 32, 16, 8, 4, 2, 1};
    private static final int[] bigbyte = new int[]{0x800000, 0x400000, 0x200000, 0x100000, 524288, 262144, 131072, 65536, 32768, 16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
    private static final byte[] pc1 = new byte[]{56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60, 52, 44, 36, 28, 20, 12, 4, 27, 19, 11, 3};
    private static final byte[] totrot = new byte[]{1, 2, 4, 6, 8, 10, 12, 14, 15, 17, 19, 21, 23, 25, 27, 28};
    private static final byte[] pc2 = new byte[]{13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 15, 6, 26, 19, 12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31};
    private static final int[] SP1 = new int[]{0x1010400, 0, 65536, 0x1010404, 0x1010004, 66564, 4, 65536, 1024, 0x1010400, 0x1010404, 1024, 0x1000404, 0x1010004, 0x1000000, 4, 1028, 0x1000400, 0x1000400, 66560, 66560, 0x1010000, 0x1010000, 0x1000404, 65540, 0x1000004, 0x1000004, 65540, 0, 1028, 66564, 0x1000000, 65536, 0x1010404, 4, 0x1010000, 0x1010400, 0x1000000, 0x1000000, 1024, 0x1010004, 65536, 66560, 0x1000004, 1024, 4, 0x1000404, 66564, 0x1010404, 65540, 0x1010000, 0x1000404, 0x1000004, 1028, 66564, 0x1010400, 1028, 0x1000400, 0x1000400, 0, 65540, 66560, 0, 0x1010004};
    private static final int[] SP2 = new int[]{-2146402272, -2147450880, 32768, 1081376, 0x100000, 32, -2146435040, -2147450848, -2147483616, -2146402272, -2146402304, Integer.MIN_VALUE, -2147450880, 0x100000, 32, -2146435040, 0x108000, 0x100020, -2147450848, 0, Integer.MIN_VALUE, 32768, 1081376, -2146435072, 0x100020, -2147483616, 0, 0x108000, 32800, -2146402304, -2146435072, 32800, 0, 1081376, -2146435040, 0x100000, -2147450848, -2146435072, -2146402304, 32768, -2146435072, -2147450880, 32, -2146402272, 1081376, 32, 32768, Integer.MIN_VALUE, 32800, -2146402304, 0x100000, -2147483616, 0x100020, -2147450848, -2147483616, 0x100020, 0x108000, 0, -2147450880, 32800, Integer.MIN_VALUE, -2146435040, -2146402272, 0x108000};
    private static final int[] SP3 = new int[]{520, 0x8020200, 0, 0x8020008, 0x8000200, 0, 131592, 0x8000200, 131080, 0x8000008, 0x8000008, 131072, 0x8020208, 131080, 0x8020000, 520, 0x8000000, 8, 0x8020200, 512, 131584, 0x8020000, 0x8020008, 131592, 0x8000208, 131584, 131072, 0x8000208, 8, 0x8020208, 512, 0x8000000, 0x8020200, 0x8000000, 131080, 520, 131072, 0x8020200, 0x8000200, 0, 512, 131080, 0x8020208, 0x8000200, 0x8000008, 512, 0, 0x8020008, 0x8000208, 131072, 0x8000000, 0x8020208, 8, 131592, 131584, 0x8000008, 0x8020000, 0x8000208, 520, 0x8020000, 131592, 8, 0x8020008, 131584};
    private static final int[] SP4 = new int[]{8396801, 8321, 8321, 128, 0x802080, 0x800081, 0x800001, 8193, 0, 0x802000, 0x802000, 8396929, 129, 0, 0x800080, 0x800001, 1, 8192, 0x800000, 8396801, 128, 0x800000, 8193, 8320, 0x800081, 1, 8320, 0x800080, 8192, 0x802080, 8396929, 129, 0x800080, 0x800001, 0x802000, 8396929, 129, 0, 0, 0x802000, 8320, 0x800080, 0x800081, 1, 8396801, 8321, 8321, 128, 8396929, 129, 1, 8192, 0x800001, 8193, 0x802080, 0x800081, 8193, 8320, 0x800000, 8396801, 128, 0x800000, 8192, 0x802080};
    private static final int[] SP5 = new int[]{256, 34078976, 0x2080000, 1107296512, 524288, 256, 0x40000000, 0x2080000, 1074266368, 524288, 0x2000100, 1074266368, 1107296512, 1107820544, 524544, 0x40000000, 0x2000000, 0x40080000, 0x40080000, 0, 0x40000100, 1107820800, 1107820800, 0x2000100, 1107820544, 0x40000100, 0, 0x42000000, 34078976, 0x2000000, 0x42000000, 524544, 524288, 1107296512, 256, 0x2000000, 0x40000000, 0x2080000, 1107296512, 1074266368, 0x2000100, 0x40000000, 1107820544, 34078976, 1074266368, 256, 0x2000000, 1107820544, 1107820800, 524544, 0x42000000, 1107820800, 0x2080000, 0, 0x40080000, 0x42000000, 524544, 0x2000100, 0x40000100, 524288, 0, 0x40080000, 34078976, 0x40000100};
    private static final int[] SP6 = new int[]{0x20000010, 0x20400000, 16384, 541081616, 0x20400000, 16, 541081616, 0x400000, 0x20004000, 0x404010, 0x400000, 0x20000010, 0x400010, 0x20004000, 0x20000000, 16400, 0, 0x400010, 536887312, 16384, 0x404000, 536887312, 16, 541065232, 541065232, 0, 0x404010, 0x20404000, 16400, 0x404000, 0x20404000, 0x20000000, 0x20004000, 16, 541065232, 0x404000, 541081616, 0x400000, 16400, 0x20000010, 0x400000, 0x20004000, 0x20000000, 16400, 0x20000010, 541081616, 0x404000, 0x20400000, 0x404010, 0x20404000, 0, 541065232, 16, 16384, 0x20400000, 0x404010, 16384, 0x400010, 536887312, 0, 0x20404000, 0x20000000, 0x400010, 536887312};
    private static final int[] SP7 = new int[]{0x200000, 0x4200002, 67110914, 0, 2048, 67110914, 0x200802, 69208064, 69208066, 0x200000, 0, 0x4000002, 2, 0x4000000, 0x4200002, 2050, 0x4000800, 0x200802, 0x200002, 0x4000800, 0x4000002, 0x4200000, 69208064, 0x200002, 0x4200000, 2048, 2050, 69208066, 0x200800, 2, 0x4000000, 0x200800, 0x4000000, 0x200800, 0x200000, 67110914, 67110914, 0x4200002, 0x4200002, 2, 0x200002, 0x4000000, 0x4000800, 0x200000, 69208064, 2050, 0x200802, 69208064, 2050, 0x4000002, 69208066, 0x4200000, 0x200800, 0, 2, 69208066, 0, 0x200802, 0x4200000, 2048, 0x4000002, 0x4000800, 2048, 0x200002};
    private static final int[] SP8 = new int[]{0x10001040, 4096, 262144, 0x10041040, 0x10000000, 0x10001040, 64, 0x10000000, 262208, 0x10040000, 0x10041040, 266240, 0x10041000, 266304, 4096, 64, 0x10040000, 0x10000040, 0x10001000, 4160, 266240, 262208, 0x10040040, 0x10041000, 4160, 0, 0, 0x10040040, 0x10000040, 0x10001000, 266304, 262144, 266304, 262144, 0x10041000, 4096, 64, 0x10040040, 4096, 266304, 0x10001000, 64, 0x10000040, 0x10040000, 0x10040040, 0x10000000, 262144, 0x10001040, 0, 0x10041040, 262208, 0x10000040, 0x10040000, 0x10001000, 0x10001040, 0, 0x10041040, 266240, 266240, 4160, 4160, 262208, 0x10000000, 0x10041000};

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            if (((KeyParameter)cipherParameters).getKey().length > 8) {
                throw new IllegalArgumentException("DES key too long - should be 8 bytes");
            }
            this.workingKey = this.generateWorkingKey(bl, ((KeyParameter)cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to DES init - " + cipherParameters.getClass().getName());
    }

    public String getAlgorithmName() {
        return "DES";
    }

    public int getBlockSize() {
        return 8;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        if (this.workingKey == null) {
            throw new IllegalStateException("DES engine not initialised");
        }
        if (n + 8 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 8 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        this.desFunc(this.workingKey, byArray, n, byArray2, n2);
        return 8;
    }

    public void reset() {
    }

    protected int[] generateWorkingKey(boolean bl, byte[] byArray) {
        int n;
        int n2;
        int n3;
        int[] nArray = new int[32];
        boolean[] blArray = new boolean[56];
        boolean[] blArray2 = new boolean[56];
        for (n3 = 0; n3 < 56; ++n3) {
            n2 = pc1[n3];
            blArray[n3] = (byArray[n2 >>> 3] & bytebit[n2 & 7]) != 0;
        }
        for (n3 = 0; n3 < 16; ++n3) {
            int n4;
            n = bl ? n3 << 1 : 15 - n3 << 1;
            int n5 = n + 1;
            nArray[n5] = 0;
            nArray[n] = 0;
            for (n4 = 0; n4 < 28; ++n4) {
                n2 = n4 + totrot[n3];
                blArray2[n4] = n2 < 28 ? blArray[n2] : blArray[n2 - 28];
            }
            for (n4 = 28; n4 < 56; ++n4) {
                n2 = n4 + totrot[n3];
                blArray2[n4] = n2 < 56 ? blArray[n2] : blArray[n2 - 28];
            }
            for (n4 = 0; n4 < 24; ++n4) {
                if (blArray2[pc2[n4]]) {
                    int n6 = n;
                    nArray[n6] = nArray[n6] | bigbyte[n4];
                }
                if (!blArray2[pc2[n4 + 24]]) continue;
                int n7 = n5;
                nArray[n7] = nArray[n7] | bigbyte[n4];
            }
        }
        for (n3 = 0; n3 != 32; n3 += 2) {
            n2 = nArray[n3];
            n = nArray[n3 + 1];
            nArray[n3] = (n2 & 0xFC0000) << 6 | (n2 & 0xFC0) << 10 | (n & 0xFC0000) >>> 10 | (n & 0xFC0) >>> 6;
            nArray[n3 + 1] = (n2 & 0x3F000) << 12 | (n2 & 0x3F) << 16 | (n & 0x3F000) >>> 4 | n & 0x3F;
        }
        return nArray;
    }

    protected void desFunc(int[] nArray, byte[] byArray, int n, byte[] byArray2, int n2) {
        int n3 = (byArray[n + 0] & 0xFF) << 24;
        n3 |= (byArray[n + 1] & 0xFF) << 16;
        n3 |= (byArray[n + 2] & 0xFF) << 8;
        int n4 = (byArray[n + 4] & 0xFF) << 24;
        n4 |= (byArray[n + 5] & 0xFF) << 16;
        n4 |= (byArray[n + 6] & 0xFF) << 8;
        int n5 = ((n3 |= byArray[n + 3] & 0xFF) >>> 4 ^ (n4 |= byArray[n + 7] & 0xFF)) & 0xF0F0F0F;
        n4 ^= n5;
        n3 ^= n5 << 4;
        n5 = (n3 >>> 16 ^ n4) & 0xFFFF;
        n4 ^= n5;
        n3 ^= n5 << 16;
        n5 = (n4 >>> 2 ^ n3) & 0x33333333;
        n3 ^= n5;
        n4 ^= n5 << 2;
        n5 = (n4 >>> 8 ^ n3) & 0xFF00FF;
        n3 ^= n5;
        n4 ^= n5 << 8;
        n4 = (n4 << 1 | n4 >>> 31 & 1) & 0xFFFFFFFF;
        n5 = (n3 ^ n4) & 0xAAAAAAAA;
        n3 ^= n5;
        n4 ^= n5;
        n3 = (n3 << 1 | n3 >>> 31 & 1) & 0xFFFFFFFF;
        for (int i = 0; i < 8; ++i) {
            n5 = n4 << 28 | n4 >>> 4;
            int n6 = SP7[(n5 ^= nArray[i * 4 + 0]) & 0x3F];
            n6 |= SP5[n5 >>> 8 & 0x3F];
            n6 |= SP3[n5 >>> 16 & 0x3F];
            n6 |= SP1[n5 >>> 24 & 0x3F];
            n5 = n4 ^ nArray[i * 4 + 1];
            n6 |= SP8[n5 & 0x3F];
            n6 |= SP6[n5 >>> 8 & 0x3F];
            n6 |= SP4[n5 >>> 16 & 0x3F];
            n3 ^= (n6 |= SP2[n5 >>> 24 & 0x3F]);
            n5 = n3 << 28 | n3 >>> 4;
            n6 = SP7[(n5 ^= nArray[i * 4 + 2]) & 0x3F];
            n6 |= SP5[n5 >>> 8 & 0x3F];
            n6 |= SP3[n5 >>> 16 & 0x3F];
            n6 |= SP1[n5 >>> 24 & 0x3F];
            n5 = n3 ^ nArray[i * 4 + 3];
            n6 |= SP8[n5 & 0x3F];
            n6 |= SP6[n5 >>> 8 & 0x3F];
            n6 |= SP4[n5 >>> 16 & 0x3F];
            n4 ^= (n6 |= SP2[n5 >>> 24 & 0x3F]);
        }
        n4 = n4 << 31 | n4 >>> 1;
        n5 = (n3 ^ n4) & 0xAAAAAAAA;
        n3 ^= n5;
        n4 ^= n5;
        n3 = n3 << 31 | n3 >>> 1;
        n5 = (n3 >>> 8 ^ n4) & 0xFF00FF;
        n4 ^= n5;
        n3 ^= n5 << 8;
        n5 = (n3 >>> 2 ^ n4) & 0x33333333;
        n4 ^= n5;
        n3 ^= n5 << 2;
        n5 = (n4 >>> 16 ^ n3) & 0xFFFF;
        n3 ^= n5;
        n4 ^= n5 << 16;
        n5 = (n4 >>> 4 ^ n3) & 0xF0F0F0F;
        byArray2[n2 + 0] = (byte)((n4 ^= n5 << 4) >>> 24 & 0xFF);
        byArray2[n2 + 1] = (byte)(n4 >>> 16 & 0xFF);
        byArray2[n2 + 2] = (byte)(n4 >>> 8 & 0xFF);
        byArray2[n2 + 3] = (byte)(n4 & 0xFF);
        byArray2[n2 + 4] = (byte)((n3 ^= n5) >>> 24 & 0xFF);
        byArray2[n2 + 5] = (byte)(n3 >>> 16 & 0xFF);
        byArray2[n2 + 6] = (byte)(n3 >>> 8 & 0xFF);
        byArray2[n2 + 7] = (byte)(n3 & 0xFF);
    }
}

