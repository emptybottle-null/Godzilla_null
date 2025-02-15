/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.v8dtoa;

public final class DoubleConversion {
    private static final long kSignMask = Long.MIN_VALUE;
    private static final long kExponentMask = 0x7FF0000000000000L;
    private static final long kSignificandMask = 0xFFFFFFFFFFFFFL;
    private static final long kHiddenBit = 0x10000000000000L;
    private static final int kPhysicalSignificandSize = 52;
    private static final int kSignificandSize = 53;
    private static final int kExponentBias = 1075;
    private static final int kDenormalExponent = -1074;

    private DoubleConversion() {
    }

    private static int exponent(long d64) {
        if (DoubleConversion.isDenormal(d64)) {
            return -1074;
        }
        int biased_e = (int)((d64 & 0x7FF0000000000000L) >> 52);
        return biased_e - 1075;
    }

    private static long significand(long d64) {
        long significand = d64 & 0xFFFFFFFFFFFFFL;
        if (!DoubleConversion.isDenormal(d64)) {
            return significand + 0x10000000000000L;
        }
        return significand;
    }

    private static boolean isDenormal(long d64) {
        return (d64 & 0x7FF0000000000000L) == 0L;
    }

    private static int sign(long d64) {
        return (d64 & Long.MIN_VALUE) == 0L ? 1 : -1;
    }

    public static int doubleToInt32(double x) {
        int i = (int)x;
        if ((double)i == x) {
            return i;
        }
        long d64 = Double.doubleToLongBits(x);
        int exponent = DoubleConversion.exponent(d64);
        if (exponent <= -53 || exponent > 31) {
            return 0;
        }
        long s = DoubleConversion.significand(d64);
        return DoubleConversion.sign(d64) * (int)(exponent < 0 ? s >> -exponent : s << exponent);
    }
}

