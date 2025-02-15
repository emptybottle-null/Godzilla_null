/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

final class IEEEDouble {
    private static final long EXPONENT_MASK = 0x7FF0000000000000L;
    private static final int EXPONENT_SHIFT = 52;
    public static final long FRAC_MASK = 0xFFFFFFFFFFFFFL;
    public static final int EXPONENT_BIAS = 1023;
    public static final long FRAC_ASSUMED_HIGH_BIT = 0x10000000000000L;
    public static final int BIASED_EXPONENT_SPECIAL_VALUE = 2047;

    IEEEDouble() {
    }

    public static int getBiasedExponent(long rawBits) {
        return Math.toIntExact((rawBits & 0x7FF0000000000000L) >> 52);
    }
}

