/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.util.Locale;
import org.apache.poi.ss.util.ExpandedDouble;
import org.apache.poi.ss.util.IEEEDouble;
import org.apache.poi.ss.util.NormalisedDecimal;

public final class NumberComparer {
    public static int compare(double a, double b) {
        boolean bIsNegative;
        long rawBitsA = Double.doubleToLongBits(a);
        long rawBitsB = Double.doubleToLongBits(b);
        int biasedExponentA = IEEEDouble.getBiasedExponent(rawBitsA);
        int biasedExponentB = IEEEDouble.getBiasedExponent(rawBitsB);
        if (biasedExponentA == 2047) {
            throw new IllegalArgumentException("Special double values are not allowed: " + NumberComparer.toHex(a));
        }
        if (biasedExponentB == 2047) {
            throw new IllegalArgumentException("Special double values are not allowed: " + NumberComparer.toHex(a));
        }
        boolean aIsNegative = rawBitsA < 0L;
        boolean bl = bIsNegative = rawBitsB < 0L;
        if (aIsNegative != bIsNegative) {
            return aIsNegative ? -1 : 1;
        }
        int cmp = biasedExponentA - biasedExponentB;
        int absExpDiff = Math.abs(cmp);
        if (absExpDiff > 1) {
            return aIsNegative ? -cmp : cmp;
        }
        if (absExpDiff != 1 && rawBitsA == rawBitsB) {
            return 0;
        }
        if (biasedExponentA == 0) {
            if (biasedExponentB == 0) {
                return NumberComparer.compareSubnormalNumbers(rawBitsA & 0xFFFFFFFFFFFFFL, rawBitsB & 0xFFFFFFFFFFFFFL, aIsNegative);
            }
            return -NumberComparer.compareAcrossSubnormalThreshold(rawBitsB, rawBitsA, aIsNegative);
        }
        if (biasedExponentB == 0) {
            return NumberComparer.compareAcrossSubnormalThreshold(rawBitsA, rawBitsB, aIsNegative);
        }
        ExpandedDouble edA = ExpandedDouble.fromRawBitsAndExponent(rawBitsA, biasedExponentA - 1023);
        ExpandedDouble edB = ExpandedDouble.fromRawBitsAndExponent(rawBitsB, biasedExponentB - 1023);
        NormalisedDecimal ndA = edA.normaliseBaseTen().roundUnits();
        NormalisedDecimal ndB = edB.normaliseBaseTen().roundUnits();
        cmp = ndA.compareNormalised(ndB);
        if (aIsNegative) {
            return -cmp;
        }
        return cmp;
    }

    private static int compareSubnormalNumbers(long fracA, long fracB, boolean isNegative) {
        if (isNegative) {
            return Long.compare(fracB, fracA);
        }
        return Long.compare(fracA, fracB);
    }

    private static int compareAcrossSubnormalThreshold(long normalRawBitsA, long subnormalRawBitsB, boolean isNegative) {
        long fracB = subnormalRawBitsB & 0xFFFFFFFFFFFFFL;
        if (fracB == 0L) {
            return isNegative ? -1 : 1;
        }
        long fracA = normalRawBitsA & 0xFFFFFFFFFFFFFL;
        if (fracA <= 7L && fracB >= 0xFFFFFFFFFFFFAL) {
            if (fracA == 7L && fracB == 0xFFFFFFFFFFFFAL) {
                return 0;
            }
            return isNegative ? 1 : -1;
        }
        return isNegative ? -1 : 1;
    }

    private static String toHex(double a) {
        return "0x" + Long.toHexString(Double.doubleToLongBits(a)).toUpperCase(Locale.ROOT);
    }
}

