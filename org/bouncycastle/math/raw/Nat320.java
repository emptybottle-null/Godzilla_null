/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

public abstract class Nat320 {
    public static void copy64(long[] lArray, long[] lArray2) {
        lArray2[0] = lArray[0];
        lArray2[1] = lArray[1];
        lArray2[2] = lArray[2];
        lArray2[3] = lArray[3];
        lArray2[4] = lArray[4];
    }

    public static long[] create64() {
        return new long[5];
    }

    public static long[] createExt64() {
        return new long[10];
    }

    public static boolean eq64(long[] lArray, long[] lArray2) {
        for (int i = 4; i >= 0; --i) {
            if (lArray[i] == lArray2[i]) continue;
            return false;
        }
        return true;
    }

    public static long[] fromBigInteger64(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 320) {
            throw new IllegalArgumentException();
        }
        long[] lArray = Nat320.create64();
        int n = 0;
        while (bigInteger.signum() != 0) {
            lArray[n++] = bigInteger.longValue();
            bigInteger = bigInteger.shiftRight(64);
        }
        return lArray;
    }

    public static boolean isOne64(long[] lArray) {
        if (lArray[0] != 1L) {
            return false;
        }
        for (int i = 1; i < 5; ++i) {
            if (lArray[i] == 0L) continue;
            return false;
        }
        return true;
    }

    public static boolean isZero64(long[] lArray) {
        for (int i = 0; i < 5; ++i) {
            if (lArray[i] == 0L) continue;
            return false;
        }
        return true;
    }

    public static BigInteger toBigInteger64(long[] lArray) {
        byte[] byArray = new byte[40];
        for (int i = 0; i < 5; ++i) {
            long l = lArray[i];
            if (l == 0L) continue;
            Pack.longToBigEndian(l, byArray, 4 - i << 3);
        }
        return new BigInteger(1, byArray);
    }
}

