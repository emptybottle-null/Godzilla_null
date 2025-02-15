/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.GOST3410Parameters;
import org.bouncycastle.crypto.params.GOST3410ValidationParameters;

public class GOST3410ParametersGenerator {
    private int size;
    private int typeproc;
    private SecureRandom init_random;
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);

    public void init(int n, int n2, SecureRandom secureRandom) {
        this.size = n;
        this.typeproc = n2;
        this.init_random = secureRandom;
    }

    private int procedure_A(int n, int n2, BigInteger[] bigIntegerArray, int n3) {
        while (n < 0 || n > 65536) {
            n = this.init_random.nextInt() / 32768;
        }
        while (n2 < 0 || n2 > 65536 || n2 / 2 == 0) {
            n2 = this.init_random.nextInt() / 32768 + 1;
        }
        BigInteger bigInteger = new BigInteger(Integer.toString(n2));
        BigInteger bigInteger2 = new BigInteger("19381");
        BigInteger[] bigIntegerArray2 = new BigInteger[]{new BigInteger(Integer.toString(n))};
        int[] nArray = new int[]{n3};
        int n4 = 0;
        int n5 = 0;
        while (nArray[n5] >= 17) {
            int[] nArray2 = new int[nArray.length + 1];
            System.arraycopy(nArray, 0, nArray2, 0, nArray.length);
            nArray = new int[nArray2.length];
            System.arraycopy(nArray2, 0, nArray, 0, nArray2.length);
            nArray[n5 + 1] = nArray[n5] / 2;
            n4 = n5 + 1;
            ++n5;
        }
        BigInteger[] bigIntegerArray3 = new BigInteger[n4 + 1];
        bigIntegerArray3[n4] = new BigInteger("8003", 16);
        int n6 = n4 - 1;
        for (int i = 0; i < n4; ++i) {
            int n7 = nArray[n6] / 16;
            block4: while (true) {
                BigInteger[] bigIntegerArray4 = new BigInteger[bigIntegerArray2.length];
                System.arraycopy(bigIntegerArray2, 0, bigIntegerArray4, 0, bigIntegerArray2.length);
                bigIntegerArray2 = new BigInteger[n7 + 1];
                System.arraycopy(bigIntegerArray4, 0, bigIntegerArray2, 0, bigIntegerArray4.length);
                for (int j = 0; j < n7; ++j) {
                    bigIntegerArray2[j + 1] = bigIntegerArray2[j].multiply(bigInteger2).add(bigInteger).mod(TWO.pow(16));
                }
                BigInteger bigInteger3 = new BigInteger("0");
                for (int j = 0; j < n7; ++j) {
                    bigInteger3 = bigInteger3.add(bigIntegerArray2[j].multiply(TWO.pow(16 * j)));
                }
                bigIntegerArray2[0] = bigIntegerArray2[n7];
                BigInteger bigInteger4 = TWO.pow(nArray[n6] - 1).divide(bigIntegerArray3[n6 + 1]).add(TWO.pow(nArray[n6] - 1).multiply(bigInteger3).divide(bigIntegerArray3[n6 + 1].multiply(TWO.pow(16 * n7))));
                if (bigInteger4.mod(TWO).compareTo(ONE) == 0) {
                    bigInteger4 = bigInteger4.add(ONE);
                }
                int n8 = 0;
                while (true) {
                    bigIntegerArray3[n6] = bigIntegerArray3[n6 + 1].multiply(bigInteger4.add(BigInteger.valueOf(n8))).add(ONE);
                    if (bigIntegerArray3[n6].compareTo(TWO.pow(nArray[n6])) == 1) continue block4;
                    if (TWO.modPow(bigIntegerArray3[n6 + 1].multiply(bigInteger4.add(BigInteger.valueOf(n8))), bigIntegerArray3[n6]).compareTo(ONE) == 0 && TWO.modPow(bigInteger4.add(BigInteger.valueOf(n8)), bigIntegerArray3[n6]).compareTo(ONE) != 0) break block4;
                    n8 += 2;
                }
                break;
            }
            if (--n6 >= 0) continue;
            bigIntegerArray[0] = bigIntegerArray3[0];
            bigIntegerArray[1] = bigIntegerArray3[1];
            return bigIntegerArray2[0].intValue();
        }
        return bigIntegerArray2[0].intValue();
    }

    private long procedure_Aa(long l, long l2, BigInteger[] bigIntegerArray, int n) {
        while (l < 0L || l > 0x100000000L) {
            l = this.init_random.nextInt() * 2;
        }
        while (l2 < 0L || l2 > 0x100000000L || l2 / 2L == 0L) {
            l2 = this.init_random.nextInt() * 2 + 1;
        }
        BigInteger bigInteger = new BigInteger(Long.toString(l2));
        BigInteger bigInteger2 = new BigInteger("97781173");
        BigInteger[] bigIntegerArray2 = new BigInteger[]{new BigInteger(Long.toString(l))};
        int[] nArray = new int[]{n};
        int n2 = 0;
        int n3 = 0;
        while (nArray[n3] >= 33) {
            int[] nArray2 = new int[nArray.length + 1];
            System.arraycopy(nArray, 0, nArray2, 0, nArray.length);
            nArray = new int[nArray2.length];
            System.arraycopy(nArray2, 0, nArray, 0, nArray2.length);
            nArray[n3 + 1] = nArray[n3] / 2;
            n2 = n3 + 1;
            ++n3;
        }
        BigInteger[] bigIntegerArray3 = new BigInteger[n2 + 1];
        bigIntegerArray3[n2] = new BigInteger("8000000B", 16);
        int n4 = n2 - 1;
        for (int i = 0; i < n2; ++i) {
            int n5 = nArray[n4] / 32;
            block4: while (true) {
                BigInteger[] bigIntegerArray4 = new BigInteger[bigIntegerArray2.length];
                System.arraycopy(bigIntegerArray2, 0, bigIntegerArray4, 0, bigIntegerArray2.length);
                bigIntegerArray2 = new BigInteger[n5 + 1];
                System.arraycopy(bigIntegerArray4, 0, bigIntegerArray2, 0, bigIntegerArray4.length);
                for (int j = 0; j < n5; ++j) {
                    bigIntegerArray2[j + 1] = bigIntegerArray2[j].multiply(bigInteger2).add(bigInteger).mod(TWO.pow(32));
                }
                BigInteger bigInteger3 = new BigInteger("0");
                for (int j = 0; j < n5; ++j) {
                    bigInteger3 = bigInteger3.add(bigIntegerArray2[j].multiply(TWO.pow(32 * j)));
                }
                bigIntegerArray2[0] = bigIntegerArray2[n5];
                BigInteger bigInteger4 = TWO.pow(nArray[n4] - 1).divide(bigIntegerArray3[n4 + 1]).add(TWO.pow(nArray[n4] - 1).multiply(bigInteger3).divide(bigIntegerArray3[n4 + 1].multiply(TWO.pow(32 * n5))));
                if (bigInteger4.mod(TWO).compareTo(ONE) == 0) {
                    bigInteger4 = bigInteger4.add(ONE);
                }
                int n6 = 0;
                while (true) {
                    bigIntegerArray3[n4] = bigIntegerArray3[n4 + 1].multiply(bigInteger4.add(BigInteger.valueOf(n6))).add(ONE);
                    if (bigIntegerArray3[n4].compareTo(TWO.pow(nArray[n4])) == 1) continue block4;
                    if (TWO.modPow(bigIntegerArray3[n4 + 1].multiply(bigInteger4.add(BigInteger.valueOf(n6))), bigIntegerArray3[n4]).compareTo(ONE) == 0 && TWO.modPow(bigInteger4.add(BigInteger.valueOf(n6)), bigIntegerArray3[n4]).compareTo(ONE) != 0) break block4;
                    n6 += 2;
                }
                break;
            }
            if (--n4 >= 0) continue;
            bigIntegerArray[0] = bigIntegerArray3[0];
            bigIntegerArray[1] = bigIntegerArray3[1];
            return bigIntegerArray2[0].longValue();
        }
        return bigIntegerArray2[0].longValue();
    }

    /*
     * Unable to fully structure code
     */
    private void procedure_B(int var1_1, int var2_2, BigInteger[] var3_3) {
        while (var1_1 < 0 || var1_1 > 65536) {
            var1_1 = this.init_random.nextInt() / 32768;
        }
        while (var2_2 < 0 || var2_2 > 65536 || var2_2 / 2 == 0) {
            var2_2 = this.init_random.nextInt() / 32768 + 1;
        }
        var4_4 = new BigInteger[2];
        var5_5 = null;
        var6_6 = null;
        var7_7 = null;
        var8_8 = new BigInteger(Integer.toString(var2_2));
        var9_9 = new BigInteger("19381");
        var1_1 = this.procedure_A(var1_1, var2_2, var4_4, 256);
        var5_5 = var4_4[0];
        var1_1 = this.procedure_A(var1_1, var2_2, var4_4, 512);
        var6_6 = var4_4[0];
        var10_10 = new BigInteger[65];
        var10_10[0] = new BigInteger(Integer.toString(var1_1));
        var11_11 = 1024;
        while (true) {
            for (var12_13 = 0; var12_13 < 64; ++var12_13) {
                var10_10[var12_13 + 1] = var10_10[var12_13].multiply(var9_9).add(var8_8).mod(GOST3410ParametersGenerator.TWO.pow(16));
            }
            var12_12 = new BigInteger("0");
            for (var13_15 = 0; var13_15 < 64; ++var13_15) {
                var12_12 = var12_12.add(var10_10[var13_15].multiply(GOST3410ParametersGenerator.TWO.pow(16 * var13_15)));
            }
            var10_10[0] = var10_10[64];
            var13_14 = GOST3410ParametersGenerator.TWO.pow(var11_11 - 1).divide(var5_5.multiply(var6_6)).add(GOST3410ParametersGenerator.TWO.pow(var11_11 - 1).multiply(var12_12).divide(var5_5.multiply(var6_6).multiply(GOST3410ParametersGenerator.TWO.pow(1024))));
            if (var13_14.mod(GOST3410ParametersGenerator.TWO).compareTo(GOST3410ParametersGenerator.ONE) == 0) {
                var13_14 = var13_14.add(GOST3410ParametersGenerator.ONE);
            }
            var14_16 = 0;
            while (true) {
                if ((var7_7 = var5_5.multiply(var6_6).multiply(var13_14.add(BigInteger.valueOf(var14_16))).add(GOST3410ParametersGenerator.ONE)).compareTo(GOST3410ParametersGenerator.TWO.pow(var11_11)) == 1) ** continue;
                if (GOST3410ParametersGenerator.TWO.modPow(var5_5.multiply(var6_6).multiply(var13_14.add(BigInteger.valueOf(var14_16))), var7_7).compareTo(GOST3410ParametersGenerator.ONE) == 0 && GOST3410ParametersGenerator.TWO.modPow(var5_5.multiply(var13_14.add(BigInteger.valueOf(var14_16))), var7_7).compareTo(GOST3410ParametersGenerator.ONE) != 0) {
                    var3_3[0] = var7_7;
                    var3_3[1] = var5_5;
                    return;
                }
                var14_16 += 2;
            }
            break;
        }
    }

    /*
     * Unable to fully structure code
     */
    private void procedure_Bb(long var1_1, long var3_2, BigInteger[] var5_3) {
        while (var1_1 < 0L || var1_1 > 0x100000000L) {
            var1_1 = this.init_random.nextInt() * 2;
        }
        while (var3_2 < 0L || var3_2 > 0x100000000L || var3_2 / 2L == 0L) {
            var3_2 = this.init_random.nextInt() * 2 + 1;
        }
        var6_4 = new BigInteger[2];
        var7_5 = null;
        var8_6 = null;
        var9_7 = null;
        var10_8 = new BigInteger(Long.toString(var3_2));
        var11_9 = new BigInteger("97781173");
        var1_1 = this.procedure_Aa(var1_1, var3_2, var6_4, 256);
        var7_5 = var6_4[0];
        var1_1 = this.procedure_Aa(var1_1, var3_2, var6_4, 512);
        var8_6 = var6_4[0];
        var12_10 = new BigInteger[33];
        var12_10[0] = new BigInteger(Long.toString(var1_1));
        var13_11 = 1024;
        while (true) {
            for (var14_13 = 0; var14_13 < 32; ++var14_13) {
                var12_10[var14_13 + 1] = var12_10[var14_13].multiply(var11_9).add(var10_8).mod(GOST3410ParametersGenerator.TWO.pow(32));
            }
            var14_12 = new BigInteger("0");
            for (var15_15 = 0; var15_15 < 32; ++var15_15) {
                var14_12 = var14_12.add(var12_10[var15_15].multiply(GOST3410ParametersGenerator.TWO.pow(32 * var15_15)));
            }
            var12_10[0] = var12_10[32];
            var15_14 = GOST3410ParametersGenerator.TWO.pow(var13_11 - 1).divide(var7_5.multiply(var8_6)).add(GOST3410ParametersGenerator.TWO.pow(var13_11 - 1).multiply(var14_12).divide(var7_5.multiply(var8_6).multiply(GOST3410ParametersGenerator.TWO.pow(1024))));
            if (var15_14.mod(GOST3410ParametersGenerator.TWO).compareTo(GOST3410ParametersGenerator.ONE) == 0) {
                var15_14 = var15_14.add(GOST3410ParametersGenerator.ONE);
            }
            var16_16 = 0;
            while (true) {
                if ((var9_7 = var7_5.multiply(var8_6).multiply(var15_14.add(BigInteger.valueOf(var16_16))).add(GOST3410ParametersGenerator.ONE)).compareTo(GOST3410ParametersGenerator.TWO.pow(var13_11)) == 1) ** continue;
                if (GOST3410ParametersGenerator.TWO.modPow(var7_5.multiply(var8_6).multiply(var15_14.add(BigInteger.valueOf(var16_16))), var9_7).compareTo(GOST3410ParametersGenerator.ONE) == 0 && GOST3410ParametersGenerator.TWO.modPow(var7_5.multiply(var15_14.add(BigInteger.valueOf(var16_16))), var9_7).compareTo(GOST3410ParametersGenerator.ONE) != 0) {
                    var5_3[0] = var9_7;
                    var5_3[1] = var7_5;
                    return;
                }
                var16_16 += 2;
            }
            break;
        }
    }

    private BigInteger procedure_C(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        BigInteger bigInteger5 = bigInteger.subtract(ONE);
        BigInteger bigInteger6 = bigInteger5.divide(bigInteger2);
        int n = bigInteger.bitLength();
        while ((bigInteger4 = new BigInteger(n, this.init_random)).compareTo(ONE) <= 0 || bigInteger4.compareTo(bigInteger5) >= 0 || (bigInteger3 = bigInteger4.modPow(bigInteger6, bigInteger)).compareTo(ONE) == 0) {
        }
        return bigInteger3;
    }

    public GOST3410Parameters generateParameters() {
        BigInteger[] bigIntegerArray = new BigInteger[2];
        BigInteger bigInteger = null;
        BigInteger bigInteger2 = null;
        BigInteger bigInteger3 = null;
        if (this.typeproc == 1) {
            int n = this.init_random.nextInt();
            int n2 = this.init_random.nextInt();
            switch (this.size) {
                case 512: {
                    this.procedure_A(n, n2, bigIntegerArray, 512);
                    break;
                }
                case 1024: {
                    this.procedure_B(n, n2, bigIntegerArray);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Ooops! key size 512 or 1024 bit.");
                }
            }
            bigInteger2 = bigIntegerArray[0];
            bigInteger = bigIntegerArray[1];
            bigInteger3 = this.procedure_C(bigInteger2, bigInteger);
            return new GOST3410Parameters(bigInteger2, bigInteger, bigInteger3, new GOST3410ValidationParameters(n, n2));
        }
        long l = this.init_random.nextLong();
        long l2 = this.init_random.nextLong();
        switch (this.size) {
            case 512: {
                this.procedure_Aa(l, l2, bigIntegerArray, 512);
                break;
            }
            case 1024: {
                this.procedure_Bb(l, l2, bigIntegerArray);
                break;
            }
            default: {
                throw new IllegalStateException("Ooops! key size 512 or 1024 bit.");
            }
        }
        bigInteger2 = bigIntegerArray[0];
        bigInteger = bigIntegerArray[1];
        bigInteger3 = this.procedure_C(bigInteger2, bigInteger);
        return new GOST3410Parameters(bigInteger2, bigInteger, bigInteger3, new GOST3410ValidationParameters(l, l2));
    }
}

