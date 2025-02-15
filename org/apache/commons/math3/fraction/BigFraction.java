/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.fraction.FractionConversionException;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BigFraction
extends Number
implements FieldElement<BigFraction>,
Comparable<BigFraction>,
Serializable {
    public static final BigFraction TWO = new BigFraction(2);
    public static final BigFraction ONE = new BigFraction(1);
    public static final BigFraction ZERO = new BigFraction(0);
    public static final BigFraction MINUS_ONE = new BigFraction(-1);
    public static final BigFraction FOUR_FIFTHS = new BigFraction(4, 5);
    public static final BigFraction ONE_FIFTH = new BigFraction(1, 5);
    public static final BigFraction ONE_HALF = new BigFraction(1, 2);
    public static final BigFraction ONE_QUARTER = new BigFraction(1, 4);
    public static final BigFraction ONE_THIRD = new BigFraction(1, 3);
    public static final BigFraction THREE_FIFTHS = new BigFraction(3, 5);
    public static final BigFraction THREE_QUARTERS = new BigFraction(3, 4);
    public static final BigFraction TWO_FIFTHS = new BigFraction(2, 5);
    public static final BigFraction TWO_QUARTERS = new BigFraction(2, 4);
    public static final BigFraction TWO_THIRDS = new BigFraction(2, 3);
    private static final long serialVersionUID = -5630213147331578515L;
    private static final BigInteger ONE_HUNDRED = BigInteger.valueOf(100L);
    private final BigInteger numerator;
    private final BigInteger denominator;

    public BigFraction(BigInteger num) {
        this(num, BigInteger.ONE);
    }

    public BigFraction(BigInteger num, BigInteger den) {
        MathUtils.checkNotNull(num, LocalizedFormats.NUMERATOR, new Object[0]);
        MathUtils.checkNotNull(den, LocalizedFormats.DENOMINATOR, new Object[0]);
        if (den.signum() == 0) {
            throw new ZeroException((Localizable)LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        if (num.signum() == 0) {
            this.numerator = BigInteger.ZERO;
            this.denominator = BigInteger.ONE;
        } else {
            BigInteger gcd = num.gcd(den);
            if (BigInteger.ONE.compareTo(gcd) < 0) {
                num = num.divide(gcd);
                den = den.divide(gcd);
            }
            if (den.signum() == -1) {
                num = num.negate();
                den = den.negate();
            }
            this.numerator = num;
            this.denominator = den;
        }
    }

    public BigFraction(double value) throws MathIllegalArgumentException {
        if (Double.isNaN(value)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NAN_VALUE_CONVERSION, new Object[0]);
        }
        if (Double.isInfinite(value)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_VALUE_CONVERSION, new Object[0]);
        }
        long bits = Double.doubleToLongBits(value);
        long sign = bits & Long.MIN_VALUE;
        long exponent = bits & 0x7FF0000000000000L;
        long m = bits & 0xFFFFFFFFFFFFFL;
        if (exponent != 0L) {
            m |= 0x10000000000000L;
        }
        if (sign != 0L) {
            m = -m;
        }
        int k = (int)(exponent >> 52) - 1075;
        while ((m & 0x1FFFFFFFFFFFFEL) != 0L && (m & 1L) == 0L) {
            m >>= 1;
            ++k;
        }
        if (k < 0) {
            this.numerator = BigInteger.valueOf(m);
            this.denominator = BigInteger.ZERO.flipBit(-k);
        } else {
            this.numerator = BigInteger.valueOf(m).multiply(BigInteger.ZERO.flipBit(k));
            this.denominator = BigInteger.ONE;
        }
    }

    public BigFraction(double value, double epsilon, int maxIterations) throws FractionConversionException {
        this(value, epsilon, Integer.MAX_VALUE, maxIterations);
    }

    private BigFraction(double value, double epsilon, int maxDenominator, int maxIterations) throws FractionConversionException {
        long overflow = Integer.MAX_VALUE;
        double r0 = value;
        long a0 = (long)FastMath.floor(r0);
        if (FastMath.abs(a0) > overflow) {
            throw new FractionConversionException(value, a0, 1L);
        }
        if (FastMath.abs((double)a0 - value) < epsilon) {
            this.numerator = BigInteger.valueOf(a0);
            this.denominator = BigInteger.ONE;
            return;
        }
        long p0 = 1L;
        long q0 = 0L;
        long p1 = a0;
        long q1 = 1L;
        long p2 = 0L;
        long q2 = 1L;
        int n = 0;
        boolean stop = false;
        do {
            ++n;
            double r1 = 1.0 / (r0 - (double)a0);
            long a1 = (long)FastMath.floor(r1);
            p2 = a1 * p1 + p0;
            q2 = a1 * q1 + q0;
            if (p2 > overflow || q2 > overflow) {
                if (epsilon == 0.0 && FastMath.abs(q1) < (long)maxDenominator) break;
                throw new FractionConversionException(value, p2, q2);
            }
            double convergent = (double)p2 / (double)q2;
            if (n < maxIterations && FastMath.abs(convergent - value) > epsilon && q2 < (long)maxDenominator) {
                p0 = p1;
                p1 = p2;
                q0 = q1;
                q1 = q2;
                a0 = a1;
                r0 = r1;
                continue;
            }
            stop = true;
        } while (!stop);
        if (n >= maxIterations) {
            throw new FractionConversionException(value, maxIterations);
        }
        if (q2 < (long)maxDenominator) {
            this.numerator = BigInteger.valueOf(p2);
            this.denominator = BigInteger.valueOf(q2);
        } else {
            this.numerator = BigInteger.valueOf(p1);
            this.denominator = BigInteger.valueOf(q1);
        }
    }

    public BigFraction(double value, int maxDenominator) throws FractionConversionException {
        this(value, 0.0, maxDenominator, 100);
    }

    public BigFraction(int num) {
        this(BigInteger.valueOf(num), BigInteger.ONE);
    }

    public BigFraction(int num, int den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    public BigFraction(long num) {
        this(BigInteger.valueOf(num), BigInteger.ONE);
    }

    public BigFraction(long num, long den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    public static BigFraction getReducedFraction(int numerator, int denominator) {
        if (numerator == 0) {
            return ZERO;
        }
        return new BigFraction(numerator, denominator);
    }

    public BigFraction abs() {
        return this.numerator.signum() == 1 ? this : this.negate();
    }

    @Override
    public BigFraction add(BigInteger bg) throws NullArgumentException {
        MathUtils.checkNotNull(bg);
        if (this.numerator.signum() == 0) {
            return new BigFraction(bg);
        }
        if (bg.signum() == 0) {
            return this;
        }
        return new BigFraction(this.numerator.add(this.denominator.multiply(bg)), this.denominator);
    }

    @Override
    public BigFraction add(int i) {
        return this.add(BigInteger.valueOf(i));
    }

    @Override
    public BigFraction add(long l) {
        return this.add(BigInteger.valueOf(l));
    }

    @Override
    public BigFraction add(BigFraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (fraction.numerator.signum() == 0) {
            return this;
        }
        if (this.numerator.signum() == 0) {
            return fraction;
        }
        BigInteger num = null;
        BigInteger den = null;
        if (this.denominator.equals(fraction.denominator)) {
            num = this.numerator.add(fraction.numerator);
            den = this.denominator;
        } else {
            num = this.numerator.multiply(fraction.denominator).add(fraction.numerator.multiply(this.denominator));
            den = this.denominator.multiply(fraction.denominator);
        }
        if (num.signum() == 0) {
            return ZERO;
        }
        return new BigFraction(num, den);
    }

    public BigDecimal bigDecimalValue() {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator));
    }

    public BigDecimal bigDecimalValue(int roundingMode) {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator), roundingMode);
    }

    public BigDecimal bigDecimalValue(int scale, int roundingMode) {
        return new BigDecimal(this.numerator).divide(new BigDecimal(this.denominator), scale, roundingMode);
    }

    @Override
    public int compareTo(BigFraction object) {
        int rhsSigNum;
        int lhsSigNum = this.numerator.signum();
        if (lhsSigNum != (rhsSigNum = object.numerator.signum())) {
            return lhsSigNum > rhsSigNum ? 1 : -1;
        }
        if (lhsSigNum == 0) {
            return 0;
        }
        BigInteger nOd = this.numerator.multiply(object.denominator);
        BigInteger dOn = this.denominator.multiply(object.numerator);
        return nOd.compareTo(dOn);
    }

    @Override
    public BigFraction divide(BigInteger bg) {
        if (bg == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (bg.signum() == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        if (this.numerator.signum() == 0) {
            return ZERO;
        }
        return new BigFraction(this.numerator, this.denominator.multiply(bg));
    }

    @Override
    public BigFraction divide(int i) {
        return this.divide(BigInteger.valueOf(i));
    }

    @Override
    public BigFraction divide(long l) {
        return this.divide(BigInteger.valueOf(l));
    }

    @Override
    public BigFraction divide(BigFraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (fraction.numerator.signum() == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
        }
        if (this.numerator.signum() == 0) {
            return ZERO;
        }
        return this.multiply(fraction.reciprocal());
    }

    @Override
    public double doubleValue() {
        double result = this.numerator.doubleValue() / this.denominator.doubleValue();
        if (Double.isNaN(result)) {
            int shift = FastMath.max(this.numerator.bitLength(), this.denominator.bitLength()) - FastMath.getExponent(Double.MAX_VALUE);
            result = this.numerator.shiftRight(shift).doubleValue() / this.denominator.shiftRight(shift).doubleValue();
        }
        return result;
    }

    public boolean equals(Object other) {
        boolean ret = false;
        if (this == other) {
            ret = true;
        } else if (other instanceof BigFraction) {
            BigFraction rhs = ((BigFraction)other).reduce();
            BigFraction thisOne = this.reduce();
            ret = thisOne.numerator.equals(rhs.numerator) && thisOne.denominator.equals(rhs.denominator);
        }
        return ret;
    }

    @Override
    public float floatValue() {
        float result = this.numerator.floatValue() / this.denominator.floatValue();
        if (Double.isNaN(result)) {
            int shift = FastMath.max(this.numerator.bitLength(), this.denominator.bitLength()) - FastMath.getExponent(Float.MAX_VALUE);
            result = this.numerator.shiftRight(shift).floatValue() / this.denominator.shiftRight(shift).floatValue();
        }
        return result;
    }

    public BigInteger getDenominator() {
        return this.denominator;
    }

    public int getDenominatorAsInt() {
        return this.denominator.intValue();
    }

    public long getDenominatorAsLong() {
        return this.denominator.longValue();
    }

    public BigInteger getNumerator() {
        return this.numerator;
    }

    public int getNumeratorAsInt() {
        return this.numerator.intValue();
    }

    public long getNumeratorAsLong() {
        return this.numerator.longValue();
    }

    public int hashCode() {
        return 37 * (629 + this.numerator.hashCode()) + this.denominator.hashCode();
    }

    @Override
    public int intValue() {
        return this.numerator.divide(this.denominator).intValue();
    }

    @Override
    public long longValue() {
        return this.numerator.divide(this.denominator).longValue();
    }

    @Override
    public BigFraction multiply(BigInteger bg) {
        if (bg == null) {
            throw new NullArgumentException();
        }
        if (this.numerator.signum() == 0 || bg.signum() == 0) {
            return ZERO;
        }
        return new BigFraction(bg.multiply(this.numerator), this.denominator);
    }

    @Override
    public BigFraction multiply(int i) {
        if (i == 0 || this.numerator.signum() == 0) {
            return ZERO;
        }
        return this.multiply(BigInteger.valueOf(i));
    }

    @Override
    public BigFraction multiply(long l) {
        if (l == 0L || this.numerator.signum() == 0) {
            return ZERO;
        }
        return this.multiply(BigInteger.valueOf(l));
    }

    @Override
    public BigFraction multiply(BigFraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (this.numerator.signum() == 0 || fraction.numerator.signum() == 0) {
            return ZERO;
        }
        return new BigFraction(this.numerator.multiply(fraction.numerator), this.denominator.multiply(fraction.denominator));
    }

    @Override
    public BigFraction negate() {
        return new BigFraction(this.numerator.negate(), this.denominator);
    }

    public double percentageValue() {
        return this.multiply(ONE_HUNDRED).doubleValue();
    }

    public BigFraction pow(int exponent) {
        if (exponent == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (exponent < 0) {
            return new BigFraction(this.denominator.pow(-exponent), this.numerator.pow(-exponent));
        }
        return new BigFraction(this.numerator.pow(exponent), this.denominator.pow(exponent));
    }

    public BigFraction pow(long exponent) {
        if (exponent == 0L) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (exponent < 0L) {
            return new BigFraction(ArithmeticUtils.pow(this.denominator, -exponent), ArithmeticUtils.pow(this.numerator, -exponent));
        }
        return new BigFraction(ArithmeticUtils.pow(this.numerator, exponent), ArithmeticUtils.pow(this.denominator, exponent));
    }

    public BigFraction pow(BigInteger exponent) {
        if (exponent.signum() == 0) {
            return ONE;
        }
        if (this.numerator.signum() == 0) {
            return this;
        }
        if (exponent.signum() == -1) {
            BigInteger eNeg = exponent.negate();
            return new BigFraction(ArithmeticUtils.pow(this.denominator, eNeg), ArithmeticUtils.pow(this.numerator, eNeg));
        }
        return new BigFraction(ArithmeticUtils.pow(this.numerator, exponent), ArithmeticUtils.pow(this.denominator, exponent));
    }

    public double pow(double exponent) {
        return FastMath.pow(this.numerator.doubleValue(), exponent) / FastMath.pow(this.denominator.doubleValue(), exponent);
    }

    @Override
    public BigFraction reciprocal() {
        return new BigFraction(this.denominator, this.numerator);
    }

    public BigFraction reduce() {
        BigInteger gcd = this.numerator.gcd(this.denominator);
        if (BigInteger.ONE.compareTo(gcd) < 0) {
            return new BigFraction(this.numerator.divide(gcd), this.denominator.divide(gcd));
        }
        return this;
    }

    @Override
    public BigFraction subtract(BigInteger bg) {
        if (bg == null) {
            throw new NullArgumentException();
        }
        if (bg.signum() == 0) {
            return this;
        }
        if (this.numerator.signum() == 0) {
            return new BigFraction(bg.negate());
        }
        return new BigFraction(this.numerator.subtract(this.denominator.multiply(bg)), this.denominator);
    }

    @Override
    public BigFraction subtract(int i) {
        return this.subtract(BigInteger.valueOf(i));
    }

    @Override
    public BigFraction subtract(long l) {
        return this.subtract(BigInteger.valueOf(l));
    }

    @Override
    public BigFraction subtract(BigFraction fraction) {
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION, new Object[0]);
        }
        if (fraction.numerator.signum() == 0) {
            return this;
        }
        if (this.numerator.signum() == 0) {
            return fraction.negate();
        }
        BigInteger num = null;
        BigInteger den = null;
        if (this.denominator.equals(fraction.denominator)) {
            num = this.numerator.subtract(fraction.numerator);
            den = this.denominator;
        } else {
            num = this.numerator.multiply(fraction.denominator).subtract(fraction.numerator.multiply(this.denominator));
            den = this.denominator.multiply(fraction.denominator);
        }
        return new BigFraction(num, den);
    }

    public String toString() {
        String str = null;
        str = BigInteger.ONE.equals(this.denominator) ? this.numerator.toString() : (BigInteger.ZERO.equals(this.numerator) ? "0" : this.numerator + " / " + this.denominator);
        return str;
    }

    public BigFractionField getField() {
        return BigFractionField.getInstance();
    }
}

