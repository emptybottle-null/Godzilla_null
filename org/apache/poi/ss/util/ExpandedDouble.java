/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.util;

import java.math.BigInteger;
import org.apache.poi.ss.util.NormalisedDecimal;

final class ExpandedDouble {
    private static final BigInteger BI_FRAC_MASK = BigInteger.valueOf(0xFFFFFFFFFFFFFL);
    private static final BigInteger BI_IMPLIED_FRAC_MSB = BigInteger.valueOf(0x10000000000000L);
    private final BigInteger _significand;
    private final int _binaryExponent;

    private static BigInteger getFrac(long rawBits) {
        return BigInteger.valueOf(rawBits).and(BI_FRAC_MASK).or(BI_IMPLIED_FRAC_MSB).shiftLeft(11);
    }

    public static ExpandedDouble fromRawBitsAndExponent(long rawBits, int exp) {
        return new ExpandedDouble(ExpandedDouble.getFrac(rawBits), exp);
    }

    public ExpandedDouble(long rawBits) {
        int biasedExp = Math.toIntExact(rawBits >> 52);
        if (biasedExp == 0) {
            BigInteger frac = BigInteger.valueOf(rawBits).and(BI_FRAC_MASK);
            int expAdj = 64 - frac.bitLength();
            this._significand = frac.shiftLeft(expAdj);
            this._binaryExponent = (biasedExp & 0x7FF) - 1023 - expAdj;
        } else {
            this._significand = ExpandedDouble.getFrac(rawBits);
            this._binaryExponent = (biasedExp & 0x7FF) - 1023;
        }
    }

    ExpandedDouble(BigInteger frac, int binaryExp) {
        if (frac.bitLength() != 64) {
            throw new IllegalArgumentException("bad bit length");
        }
        this._significand = frac;
        this._binaryExponent = binaryExp;
    }

    public NormalisedDecimal normaliseBaseTen() {
        return NormalisedDecimal.create(this._significand, this._binaryExponent);
    }

    public int getBinaryExponent() {
        return this._binaryExponent;
    }

    public BigInteger getSignificand() {
        return this._significand;
    }
}

