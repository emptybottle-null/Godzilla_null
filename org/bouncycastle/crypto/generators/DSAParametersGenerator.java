/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.DSAParameterGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.crypto.params.DSAValidationParameters;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.encoders.Hex;

public class DSAParametersGenerator {
    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private Digest digest;
    private int L;
    private int N;
    private int certainty;
    private int iterations;
    private SecureRandom random;
    private boolean use186_3;
    private int usageIndex;

    public DSAParametersGenerator() {
        this(DigestFactory.createSHA1());
    }

    public DSAParametersGenerator(Digest digest) {
        this.digest = digest;
    }

    public void init(int n, int n2, SecureRandom secureRandom) {
        this.L = n;
        this.N = DSAParametersGenerator.getDefaultN(n);
        this.certainty = n2;
        this.iterations = Math.max(DSAParametersGenerator.getMinimumIterations(this.L), (n2 + 1) / 2);
        this.random = secureRandom;
        this.use186_3 = false;
        this.usageIndex = -1;
    }

    public void init(DSAParameterGenerationParameters dSAParameterGenerationParameters) {
        int n = dSAParameterGenerationParameters.getL();
        int n2 = dSAParameterGenerationParameters.getN();
        if (n < 1024 || n > 3072 || n % 1024 != 0) {
            throw new IllegalArgumentException("L values must be between 1024 and 3072 and a multiple of 1024");
        }
        if (n == 1024 && n2 != 160) {
            throw new IllegalArgumentException("N must be 160 for L = 1024");
        }
        if (n == 2048 && n2 != 224 && n2 != 256) {
            throw new IllegalArgumentException("N must be 224 or 256 for L = 2048");
        }
        if (n == 3072 && n2 != 256) {
            throw new IllegalArgumentException("N must be 256 for L = 3072");
        }
        if (this.digest.getDigestSize() * 8 < n2) {
            throw new IllegalStateException("Digest output size too small for value of N");
        }
        this.L = n;
        this.N = n2;
        this.certainty = dSAParameterGenerationParameters.getCertainty();
        this.iterations = Math.max(DSAParametersGenerator.getMinimumIterations(n), (this.certainty + 1) / 2);
        this.random = dSAParameterGenerationParameters.getRandom();
        this.use186_3 = true;
        this.usageIndex = dSAParameterGenerationParameters.getUsageIndex();
    }

    public DSAParameters generateParameters() {
        return this.use186_3 ? this.generateParameters_FIPS186_3() : this.generateParameters_FIPS186_2();
    }

    /*
     * Unable to fully structure code
     */
    private DSAParameters generateParameters_FIPS186_2() {
        var1_1 = new byte[20];
        var2_2 = new byte[20];
        var3_3 = new byte[20];
        var4_4 = new byte[20];
        var5_5 = (this.L - 1) / 160;
        var6_6 = new byte[this.L / 8];
        if (!(this.digest instanceof SHA1Digest)) {
            throw new IllegalStateException("can only use SHA-1 for generating FIPS 186-2 parameters");
        }
        block0: while (true) {
            this.random.nextBytes(var1_1);
            DSAParametersGenerator.hash(this.digest, var1_1, var2_2, 0);
            System.arraycopy(var1_1, 0, var3_3, 0, var1_1.length);
            DSAParametersGenerator.inc(var3_3);
            DSAParametersGenerator.hash(this.digest, var3_3, var3_3, 0);
            for (var7_8 = 0; var7_8 != var4_4.length; ++var7_8) {
                var4_4[var7_8] = (byte)(var2_2[var7_8] ^ var3_3[var7_8]);
            }
            var4_4[0] = (byte)(var4_4[0] | -128);
            var4_4[19] = (byte)(var4_4[19] | 1);
            var7_7 = new BigInteger(1, var4_4);
            if (!this.isProbablePrime(var7_7)) continue;
            var8_9 = Arrays.clone(var1_1);
            DSAParametersGenerator.inc(var8_9);
            var9_10 = 0;
            while (true) {
                if (var9_10 < 4096) ** break;
                continue block0;
                for (var10_12 = 1; var10_12 <= var5_5; ++var10_12) {
                    DSAParametersGenerator.inc(var8_9);
                    DSAParametersGenerator.hash(this.digest, var8_9, var6_6, var6_6.length - var10_12 * var2_2.length);
                }
                var10_12 = var6_6.length - var5_5 * var2_2.length;
                DSAParametersGenerator.inc(var8_9);
                DSAParametersGenerator.hash(this.digest, var8_9, var2_2, 0);
                System.arraycopy(var2_2, var2_2.length - var10_12, var6_6, 0, var10_12);
                var6_6[0] = (byte)(var6_6[0] | -128);
                var10_11 = new BigInteger(1, var6_6);
                var11_13 = var10_11.mod(var7_7.shiftLeft(1));
                var12_14 = var10_11.subtract(var11_13.subtract(DSAParametersGenerator.ONE));
                if (var12_14.bitLength() == this.L && this.isProbablePrime(var12_14)) {
                    var13_15 = DSAParametersGenerator.calculateGenerator_FIPS186_2(var12_14, var7_7, this.random);
                    return new DSAParameters(var12_14, var7_7, var13_15, new DSAValidationParameters(var1_1, var9_10));
                }
                ++var9_10;
            }
            break;
        }
    }

    private static BigInteger calculateGenerator_FIPS186_2(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        BigInteger bigInteger5 = bigInteger.subtract(ONE).divide(bigInteger2);
        BigInteger bigInteger6 = bigInteger.subtract(TWO);
        while ((bigInteger4 = (bigInteger3 = BigIntegers.createRandomInRange(TWO, bigInteger6, secureRandom)).modPow(bigInteger5, bigInteger)).bitLength() <= 1) {
        }
        return bigInteger4;
    }

    /*
     * Unable to fully structure code
     */
    private DSAParameters generateParameters_FIPS186_3() {
        var1_1 = this.digest;
        var2_2 = var1_1.getDigestSize() * 8;
        var3_3 = this.N;
        var4_4 = new byte[var3_3 / 8];
        var5_5 = (this.L - 1) / var2_2;
        var6_6 = (this.L - 1) % var2_2;
        var7_7 = new byte[this.L / 8];
        var8_8 = new byte[var1_1.getDigestSize()];
        block0: while (true) {
            this.random.nextBytes(var4_4);
            DSAParametersGenerator.hash(var1_1, var4_4, var8_8, 0);
            var9_9 = new BigInteger(1, var8_8).mod(DSAParametersGenerator.ONE.shiftLeft(this.N - 1));
            var10_10 = var9_9.setBit(0).setBit(this.N - 1);
            if (!this.isProbablePrime(var10_10)) continue;
            var11_11 = Arrays.clone(var4_4);
            var12_12 = 4 * this.L;
            var13_13 = 0;
            while (true) {
                if (var13_13 < var12_12) ** break;
                continue block0;
                for (var14_15 = 1; var14_15 <= var5_5; ++var14_15) {
                    DSAParametersGenerator.inc(var11_11);
                    DSAParametersGenerator.hash(var1_1, var11_11, var7_7, var7_7.length - var14_15 * var8_8.length);
                }
                var14_15 = var7_7.length - var5_5 * var8_8.length;
                DSAParametersGenerator.inc(var11_11);
                DSAParametersGenerator.hash(var1_1, var11_11, var8_8, 0);
                System.arraycopy(var8_8, var8_8.length - var14_15, var7_7, 0, var14_15);
                var7_7[0] = (byte)(var7_7[0] | -128);
                var14_14 = new BigInteger(1, var7_7);
                var15_16 = var14_14.mod(var10_10.shiftLeft(1));
                var16_17 = var14_14.subtract(var15_16.subtract(DSAParametersGenerator.ONE));
                if (var16_17.bitLength() == this.L && this.isProbablePrime(var16_17)) {
                    if (this.usageIndex >= 0 && (var17_18 = DSAParametersGenerator.calculateGenerator_FIPS186_3_Verifiable(var1_1, var16_17, var10_10, var4_4, this.usageIndex)) != null) {
                        return new DSAParameters(var16_17, var10_10, var17_18, new DSAValidationParameters(var4_4, var13_13, this.usageIndex));
                    }
                    var17_18 = DSAParametersGenerator.calculateGenerator_FIPS186_3_Unverifiable(var16_17, var10_10, this.random);
                    return new DSAParameters(var16_17, var10_10, var17_18, new DSAValidationParameters(var4_4, var13_13));
                }
                ++var13_13;
            }
            break;
        }
    }

    private boolean isProbablePrime(BigInteger bigInteger) {
        return bigInteger.isProbablePrime(this.certainty);
    }

    private static BigInteger calculateGenerator_FIPS186_3_Unverifiable(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        return DSAParametersGenerator.calculateGenerator_FIPS186_2(bigInteger, bigInteger2, secureRandom);
    }

    private static BigInteger calculateGenerator_FIPS186_3_Verifiable(Digest digest, BigInteger bigInteger, BigInteger bigInteger2, byte[] byArray, int n) {
        BigInteger bigInteger3 = bigInteger.subtract(ONE).divide(bigInteger2);
        byte[] byArray2 = Hex.decode("6767656E");
        byte[] byArray3 = new byte[byArray.length + byArray2.length + 1 + 2];
        System.arraycopy(byArray, 0, byArray3, 0, byArray.length);
        System.arraycopy(byArray2, 0, byArray3, byArray.length, byArray2.length);
        byArray3[byArray3.length - 3] = (byte)n;
        byte[] byArray4 = new byte[digest.getDigestSize()];
        for (int i = 1; i < 65536; ++i) {
            DSAParametersGenerator.inc(byArray3);
            DSAParametersGenerator.hash(digest, byArray3, byArray4, 0);
            BigInteger bigInteger4 = new BigInteger(1, byArray4);
            BigInteger bigInteger5 = bigInteger4.modPow(bigInteger3, bigInteger);
            if (bigInteger5.compareTo(TWO) < 0) continue;
            return bigInteger5;
        }
        return null;
    }

    private static void hash(Digest digest, byte[] byArray, byte[] byArray2, int n) {
        digest.update(byArray, 0, byArray.length);
        digest.doFinal(byArray2, n);
    }

    private static int getDefaultN(int n) {
        return n > 1024 ? 256 : 160;
    }

    private static int getMinimumIterations(int n) {
        return n <= 1024 ? 40 : 48 + 8 * ((n - 1) / 1024);
    }

    private static void inc(byte[] byArray) {
        for (int i = byArray.length - 1; i >= 0; --i) {
            byte by;
            byArray[i] = by = (byte)(byArray[i] + 1 & 0xFF);
            if (by != 0) break;
        }
    }
}

