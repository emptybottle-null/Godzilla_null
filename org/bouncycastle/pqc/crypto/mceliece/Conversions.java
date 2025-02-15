/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import java.math.BigInteger;
import org.bouncycastle.pqc.math.linearalgebra.BigIntUtils;
import org.bouncycastle.pqc.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;

final class Conversions {
    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private static final BigInteger ONE = BigInteger.valueOf(1L);

    private Conversions() {
    }

    public static GF2Vector encode(int n, int n2, byte[] byArray) {
        if (n < n2) {
            throw new IllegalArgumentException("n < t");
        }
        BigInteger bigInteger = new BigInteger(1, byArray);
        BigInteger bigInteger2 = IntegerFunctions.binomial(n, n2);
        if (bigInteger.compareTo(bigInteger2) >= 0) {
            throw new IllegalArgumentException("Encoded number too large.");
        }
        GF2Vector gF2Vector = new GF2Vector(n);
        int n3 = n;
        int n4 = n2;
        for (int i = 0; i < n; ++i) {
            bigInteger2 = bigInteger2.multiply(BigInteger.valueOf(n3 - n4)).divide(BigInteger.valueOf(n3));
            --n3;
            if (bigInteger2.compareTo(bigInteger) > 0) continue;
            gF2Vector.setBit(i);
            bigInteger = bigInteger.subtract(bigInteger2);
            bigInteger2 = n3 == --n4 ? ONE : bigInteger2.multiply(BigInteger.valueOf(n4 + 1)).divide(BigInteger.valueOf(n3 - n4));
        }
        return gF2Vector;
    }

    public static byte[] decode(int n, int n2, GF2Vector gF2Vector) {
        if (gF2Vector.getLength() != n || gF2Vector.getHammingWeight() != n2) {
            throw new IllegalArgumentException("vector has wrong length or hamming weight");
        }
        int[] nArray = gF2Vector.getVecArray();
        BigInteger bigInteger = IntegerFunctions.binomial(n, n2);
        BigInteger bigInteger2 = ZERO;
        int n3 = n;
        int n4 = n2;
        for (int i = 0; i < n; ++i) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(n3 - n4)).divide(BigInteger.valueOf(n3));
            --n3;
            int n5 = i >> 5;
            int n6 = nArray[n5] & 1 << (i & 0x1F);
            if (n6 == 0) continue;
            bigInteger2 = bigInteger2.add(bigInteger);
            bigInteger = n3 == --n4 ? ONE : bigInteger.multiply(BigInteger.valueOf(n4 + 1)).divide(BigInteger.valueOf(n3 - n4));
        }
        return BigIntUtils.toMinimalByteArray(bigInteger2);
    }

    public static byte[] signConversion(int n, int n2, byte[] byArray) {
        int n3;
        int n4;
        byte[] byArray2;
        if (n < n2) {
            throw new IllegalArgumentException("n < t");
        }
        BigInteger bigInteger = IntegerFunctions.binomial(n, n2);
        int n5 = bigInteger.bitLength() - 1;
        int n6 = n5 >> 3;
        int n7 = n5 & 7;
        if (n7 == 0) {
            --n6;
            n7 = 8;
        }
        int n8 = n >> 3;
        int n9 = n & 7;
        if (n9 == 0) {
            --n8;
            n9 = 8;
        }
        if (byArray.length < (byArray2 = new byte[n8 + 1]).length) {
            System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
            for (n4 = byArray.length; n4 < byArray2.length; ++n4) {
                byArray2[n4] = 0;
            }
        } else {
            System.arraycopy(byArray, 0, byArray2, 0, n8);
            n4 = (1 << n9) - 1;
            byArray2[n8] = (byte)(n4 & byArray[n8]);
        }
        BigInteger bigInteger2 = ZERO;
        int n10 = n;
        int n11 = n2;
        for (int i = 0; i < n; ++i) {
            bigInteger = bigInteger.multiply(new BigInteger(Integer.toString(n10 - n11))).divide(new BigInteger(Integer.toString(n10)));
            --n10;
            int n12 = i >>> 3;
            n3 = i & 7;
            byte by = (byte)((n3 = 1 << n3) & byArray2[n12]);
            if (by == 0) continue;
            bigInteger2 = bigInteger2.add(bigInteger);
            bigInteger = n10 == --n11 ? ONE : bigInteger.multiply(new BigInteger(Integer.toString(n11 + 1))).divide(new BigInteger(Integer.toString(n10 - n11)));
        }
        byte[] byArray3 = new byte[n6 + 1];
        byte[] byArray4 = bigInteger2.toByteArray();
        if (byArray4.length < byArray3.length) {
            System.arraycopy(byArray4, 0, byArray3, 0, byArray4.length);
            for (n3 = byArray4.length; n3 < byArray3.length; ++n3) {
                byArray3[n3] = 0;
            }
        } else {
            System.arraycopy(byArray4, 0, byArray3, 0, n6);
            byArray3[n6] = (byte)((1 << n7) - 1 & byArray4[n6]);
        }
        return byArray3;
    }
}

