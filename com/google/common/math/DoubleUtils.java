/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.math.BigInteger;

@GwtIncompatible
final class DoubleUtils {
    static final long SIGNIFICAND_MASK = 0xFFFFFFFFFFFFFL;
    static final long EXPONENT_MASK = 0x7FF0000000000000L;
    static final long SIGN_MASK = Long.MIN_VALUE;
    static final int SIGNIFICAND_BITS = 52;
    static final int EXPONENT_BIAS = 1023;
    static final long IMPLICIT_BIT = 0x10000000000000L;
    @VisibleForTesting
    static final long ONE_BITS = 0x3FF0000000000000L;

    private DoubleUtils() {
    }

    static double nextDown(double d) {
        return -Math.nextUp(-d);
    }

    static long getSignificand(double d) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d), "not a normal value");
        int exponent = Math.getExponent(d);
        long bits = Double.doubleToRawLongBits(d);
        return exponent == -1023 ? bits << 1 : (bits &= 0xFFFFFFFFFFFFFL) | 0x10000000000000L;
    }

    static boolean isFinite(double d) {
        return Math.getExponent(d) <= 1023;
    }

    static boolean isNormal(double d) {
        return Math.getExponent(d) >= -1022;
    }

    static double scaleNormalize(double x) {
        long significand = Double.doubleToRawLongBits(x) & 0xFFFFFFFFFFFFFL;
        return Double.longBitsToDouble(significand | 0x3FF0000000000000L);
    }

    static double bigToDouble(BigInteger x) {
        BigInteger absX = x.abs();
        int exponent = absX.bitLength() - 1;
        if (exponent < 63) {
            return x.longValue();
        }
        if (exponent > 1023) {
            return (double)x.signum() * Double.POSITIVE_INFINITY;
        }
        int shift = exponent - 52 - 1;
        long twiceSignifFloor = absX.shiftRight(shift).longValue();
        long signifFloor = twiceSignifFloor >> 1;
        boolean increment = (twiceSignifFloor & 1L) != 0L && (((signifFloor &= 0xFFFFFFFFFFFFFL) & 1L) != 0L || absX.getLowestSetBit() < shift);
        long signifRounded = increment ? signifFloor + 1L : signifFloor;
        long bits = (long)(exponent + 1023) << 52;
        bits += signifRounded;
        return Double.longBitsToDouble(bits |= (long)x.signum() & Long.MIN_VALUE);
    }

    static double ensureNonNegative(double value) {
        Preconditions.checkArgument(!Double.isNaN(value));
        if (value > 0.0) {
            return value;
        }
        return 0.0;
    }
}

