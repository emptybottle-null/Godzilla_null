/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class BigIntegers {
    private static final int MAX_ITERATIONS = 1000;
    private static final BigInteger ZERO = BigInteger.valueOf(0L);

    public static byte[] asUnsignedByteArray(BigInteger bigInteger) {
        byte[] byArray = bigInteger.toByteArray();
        if (byArray[0] == 0) {
            byte[] byArray2 = new byte[byArray.length - 1];
            System.arraycopy(byArray, 1, byArray2, 0, byArray2.length);
            return byArray2;
        }
        return byArray;
    }

    public static byte[] asUnsignedByteArray(int n, BigInteger bigInteger) {
        byte[] byArray = bigInteger.toByteArray();
        if (byArray.length == n) {
            return byArray;
        }
        int n2 = byArray[0] == 0 ? 1 : 0;
        int n3 = byArray.length - n2;
        if (n3 > n) {
            throw new IllegalArgumentException("standard length exceeded for value");
        }
        byte[] byArray2 = new byte[n];
        System.arraycopy(byArray, n2, byArray2, byArray2.length - n3, n3);
        return byArray2;
    }

    public static BigInteger createRandomInRange(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        int n = bigInteger.compareTo(bigInteger2);
        if (n >= 0) {
            if (n > 0) {
                throw new IllegalArgumentException("'min' may not be greater than 'max'");
            }
            return bigInteger;
        }
        if (bigInteger.bitLength() > bigInteger2.bitLength() / 2) {
            return BigIntegers.createRandomInRange(ZERO, bigInteger2.subtract(bigInteger), secureRandom).add(bigInteger);
        }
        for (int i = 0; i < 1000; ++i) {
            BigInteger bigInteger3 = new BigInteger(bigInteger2.bitLength(), secureRandom);
            if (bigInteger3.compareTo(bigInteger) < 0 || bigInteger3.compareTo(bigInteger2) > 0) continue;
            return bigInteger3;
        }
        return new BigInteger(bigInteger2.subtract(bigInteger).bitLength() - 1, secureRandom).add(bigInteger);
    }

    public static BigInteger fromUnsignedByteArray(byte[] byArray) {
        return new BigInteger(1, byArray);
    }

    public static BigInteger fromUnsignedByteArray(byte[] byArray, int n, int n2) {
        byte[] byArray2 = byArray;
        if (n != 0 || n2 != byArray.length) {
            byArray2 = new byte[n2];
            System.arraycopy(byArray, n, byArray2, 0, n2);
        }
        return new BigInteger(1, byArray2);
    }
}

