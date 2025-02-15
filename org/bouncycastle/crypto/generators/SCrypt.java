/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.engines.Salsa20Engine;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class SCrypt {
    public static byte[] generate(byte[] byArray, byte[] byArray2, int n, int n2, int n3, int n4) {
        if (byArray == null) {
            throw new IllegalArgumentException("Passphrase P must be provided.");
        }
        if (byArray2 == null) {
            throw new IllegalArgumentException("Salt S must be provided.");
        }
        if (n <= 1) {
            throw new IllegalArgumentException("Cost parameter N must be > 1.");
        }
        if (n2 == 1 && n > 65536) {
            throw new IllegalArgumentException("Cost parameter N must be > 1 and < 65536.");
        }
        if (n2 < 1) {
            throw new IllegalArgumentException("Block size r must be >= 1.");
        }
        int n5 = Integer.MAX_VALUE / (128 * n2 * 8);
        if (n3 < 1 || n3 > n5) {
            throw new IllegalArgumentException("Parallelisation parameter p must be >= 1 and <= " + n5 + " (based on block size r of " + n2 + ")");
        }
        if (n4 < 1) {
            throw new IllegalArgumentException("Generated key length dkLen must be >= 1.");
        }
        return SCrypt.MFcrypt(byArray, byArray2, n, n2, n3, n4);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static byte[] MFcrypt(byte[] byArray, byte[] byArray2, int n, int n2, int n3, int n4) {
        byte[] byArray3;
        int n5 = n2 * 128;
        byte[] byArray4 = SCrypt.SingleIterationPBKDF2(byArray, byArray2, n3 * n5);
        int[] nArray = null;
        try {
            int n6 = byArray4.length >>> 2;
            nArray = new int[n6];
            Pack.littleEndianToInt(byArray4, 0, nArray);
            int n7 = n5 >>> 2;
            for (int i = 0; i < n6; i += n7) {
                SCrypt.SMix(nArray, i, n, n2);
            }
            Pack.intToLittleEndian(nArray, byArray4, 0);
            byArray3 = SCrypt.SingleIterationPBKDF2(byArray, byArray4, n4);
        } catch (Throwable throwable) {
            SCrypt.Clear(byArray4);
            SCrypt.Clear(nArray);
            throw throwable;
        }
        SCrypt.Clear(byArray4);
        SCrypt.Clear(nArray);
        return byArray3;
    }

    private static byte[] SingleIterationPBKDF2(byte[] byArray, byte[] byArray2, int n) {
        PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(new SHA256Digest());
        pKCS5S2ParametersGenerator.init(byArray, byArray2, 1);
        KeyParameter keyParameter = (KeyParameter)((PBEParametersGenerator)pKCS5S2ParametersGenerator).generateDerivedMacParameters(n * 8);
        return keyParameter.getKey();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void SMix(int[] nArray, int n, int n2, int n3) {
        int n4 = n3 * 32;
        int[] nArray2 = new int[16];
        int[] nArray3 = new int[16];
        int[] nArray4 = new int[n4];
        int[] nArray5 = new int[n4];
        int[][] nArrayArray = new int[n2][];
        try {
            int n5;
            System.arraycopy(nArray, n, nArray5, 0, n4);
            for (n5 = 0; n5 < n2; ++n5) {
                nArrayArray[n5] = Arrays.clone(nArray5);
                SCrypt.BlockMix(nArray5, nArray2, nArray3, nArray4, n3);
            }
            n5 = n2 - 1;
            for (int i = 0; i < n2; ++i) {
                int n6 = nArray5[n4 - 16] & n5;
                SCrypt.Xor(nArray5, nArrayArray[n6], 0, nArray5);
                SCrypt.BlockMix(nArray5, nArray2, nArray3, nArray4, n3);
            }
            System.arraycopy(nArray5, 0, nArray, n, n4);
        } catch (Throwable throwable) {
            SCrypt.ClearAll(nArrayArray);
            SCrypt.ClearAll(new int[][]{nArray5, nArray2, nArray3, nArray4});
            throw throwable;
        }
        SCrypt.ClearAll(nArrayArray);
        SCrypt.ClearAll(new int[][]{nArray5, nArray2, nArray3, nArray4});
    }

    private static void BlockMix(int[] nArray, int[] nArray2, int[] nArray3, int[] nArray4, int n) {
        System.arraycopy(nArray, nArray.length - 16, nArray2, 0, 16);
        int n2 = 0;
        int n3 = 0;
        int n4 = nArray.length >>> 1;
        for (int i = 2 * n; i > 0; --i) {
            SCrypt.Xor(nArray2, nArray, n2, nArray3);
            Salsa20Engine.salsaCore(8, nArray3, nArray2);
            System.arraycopy(nArray2, 0, nArray4, n3, 16);
            n3 = n4 + n2 - n3;
            n2 += 16;
        }
        System.arraycopy(nArray4, 0, nArray, 0, nArray4.length);
    }

    private static void Xor(int[] nArray, int[] nArray2, int n, int[] nArray3) {
        for (int i = nArray3.length - 1; i >= 0; --i) {
            nArray3[i] = nArray[i] ^ nArray2[n + i];
        }
    }

    private static void Clear(byte[] byArray) {
        if (byArray != null) {
            Arrays.fill(byArray, (byte)0);
        }
    }

    private static void Clear(int[] nArray) {
        if (nArray != null) {
            Arrays.fill(nArray, 0);
        }
    }

    private static void ClearAll(int[][] nArray) {
        for (int i = 0; i < nArray.length; ++i) {
            SCrypt.Clear(nArray[i]);
        }
    }
}

