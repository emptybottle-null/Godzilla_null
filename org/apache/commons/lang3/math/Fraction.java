/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.math;

import java.math.BigInteger;
import org.apache.commons.lang3.Validate;

public final class Fraction
extends Number
implements Comparable<Fraction> {
    private static final long serialVersionUID = 65382027393090L;
    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
    private final int numerator;
    private final int denominator;
    private transient int hashCode;
    private transient String toString;
    private transient String toProperString;

    private Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction getFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (denominator < 0) {
            if (numerator == Integer.MIN_VALUE || denominator == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate");
            }
            numerator = -numerator;
            denominator = -denominator;
        }
        return new Fraction(numerator, denominator);
    }

    public static Fraction getFraction(int whole, int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (denominator < 0) {
            throw new ArithmeticException("The denominator must not be negative");
        }
        if (numerator < 0) {
            throw new ArithmeticException("The numerator must not be negative");
        }
        long numeratorValue = whole < 0 ? (long)whole * (long)denominator - (long)numerator : (long)whole * (long)denominator + (long)numerator;
        if (numeratorValue < Integer.MIN_VALUE || numeratorValue > Integer.MAX_VALUE) {
            throw new ArithmeticException("Numerator too large to represent as an Integer.");
        }
        return new Fraction((int)numeratorValue, denominator);
    }

    public static Fraction getReducedFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (numerator == 0) {
            return ZERO;
        }
        if (denominator == Integer.MIN_VALUE && (numerator & 1) == 0) {
            numerator /= 2;
            denominator /= 2;
        }
        if (denominator < 0) {
            if (numerator == Integer.MIN_VALUE || denominator == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate");
            }
            numerator = -numerator;
            denominator = -denominator;
        }
        int gcd = Fraction.greatestCommonDivisor(numerator, denominator);
        return new Fraction(numerator /= gcd, denominator /= gcd);
    }

    public static Fraction getFraction(double value) {
        double delta1;
        int sign = value < 0.0 ? -1 : 1;
        if ((value = Math.abs(value)) > 2.147483647E9 || Double.isNaN(value)) {
            throw new ArithmeticException("The value must not be greater than Integer.MAX_VALUE or NaN");
        }
        int wholeNumber = (int)value;
        value -= (double)wholeNumber;
        int numer0 = 0;
        int denom0 = 1;
        int numer1 = 1;
        int denom1 = 0;
        int numer2 = 0;
        int denom2 = 0;
        int a1 = (int)value;
        int a2 = 0;
        double x1 = 1.0;
        double x2 = 0.0;
        double y1 = value - (double)a1;
        double y2 = 0.0;
        double delta2 = Double.MAX_VALUE;
        int i = 1;
        do {
            delta1 = delta2;
            a2 = (int)(x1 / y1);
            x2 = y1;
            y2 = x1 - (double)a2 * y1;
            numer2 = a1 * numer1 + numer0;
            denom2 = a1 * denom1 + denom0;
            double fraction = (double)numer2 / (double)denom2;
            delta2 = Math.abs(value - fraction);
            a1 = a2;
            x1 = x2;
            y1 = y2;
            numer0 = numer1;
            denom0 = denom1;
            numer1 = numer2;
            denom1 = denom2;
        } while (delta1 > delta2 && denom2 <= 10000 && denom2 > 0 && ++i < 25);
        if (i == 25) {
            throw new ArithmeticException("Unable to convert double to fraction");
        }
        return Fraction.getReducedFraction((numer0 + wholeNumber * denom0) * sign, denom0);
    }

    public static Fraction getFraction(String str) {
        Validate.notNull(str, "str", new Object[0]);
        int pos = str.indexOf(46);
        if (pos >= 0) {
            return Fraction.getFraction(Double.parseDouble(str));
        }
        pos = str.indexOf(32);
        if (pos > 0) {
            int whole = Integer.parseInt(str.substring(0, pos));
            if ((pos = (str = str.substring(pos + 1)).indexOf(47)) < 0) {
                throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
            }
            int numer = Integer.parseInt(str.substring(0, pos));
            int denom = Integer.parseInt(str.substring(pos + 1));
            return Fraction.getFraction(whole, numer, denom);
        }
        pos = str.indexOf(47);
        if (pos < 0) {
            return Fraction.getFraction(Integer.parseInt(str), 1);
        }
        int numer = Integer.parseInt(str.substring(0, pos));
        int denom = Integer.parseInt(str.substring(pos + 1));
        return Fraction.getFraction(numer, denom);
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getProperNumerator() {
        return Math.abs(this.numerator % this.denominator);
    }

    public int getProperWhole() {
        return this.numerator / this.denominator;
    }

    @Override
    public int intValue() {
        return this.numerator / this.denominator;
    }

    @Override
    public long longValue() {
        return (long)this.numerator / (long)this.denominator;
    }

    @Override
    public float floatValue() {
        return (float)this.numerator / (float)this.denominator;
    }

    @Override
    public double doubleValue() {
        return (double)this.numerator / (double)this.denominator;
    }

    public Fraction reduce() {
        if (this.numerator == 0) {
            return this.equals(ZERO) ? this : ZERO;
        }
        int gcd = Fraction.greatestCommonDivisor(Math.abs(this.numerator), this.denominator);
        if (gcd == 1) {
            return this;
        }
        return Fraction.getFraction(this.numerator / gcd, this.denominator / gcd);
    }

    public Fraction invert() {
        if (this.numerator == 0) {
            throw new ArithmeticException("Unable to invert zero.");
        }
        if (this.numerator == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow: can't negate numerator");
        }
        if (this.numerator < 0) {
            return new Fraction(-this.denominator, -this.numerator);
        }
        return new Fraction(this.denominator, this.numerator);
    }

    public Fraction negate() {
        if (this.numerator == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow: too large to negate");
        }
        return new Fraction(-this.numerator, this.denominator);
    }

    public Fraction abs() {
        if (this.numerator >= 0) {
            return this;
        }
        return this.negate();
    }

    public Fraction pow(int power) {
        if (power == 1) {
            return this;
        }
        if (power == 0) {
            return ONE;
        }
        if (power < 0) {
            if (power == Integer.MIN_VALUE) {
                return this.invert().pow(2).pow(-(power / 2));
            }
            return this.invert().pow(-power);
        }
        Fraction f = this.multiplyBy(this);
        if (power % 2 == 0) {
            return f.pow(power / 2);
        }
        return f.pow(power / 2).multiplyBy(this);
    }

    private static int greatestCommonDivisor(int u, int v) {
        int t;
        int k;
        if (u == 0 || v == 0) {
            if (u == Integer.MIN_VALUE || v == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: gcd is 2^31");
            }
            return Math.abs(u) + Math.abs(v);
        }
        if (Math.abs(u) == 1 || Math.abs(v) == 1) {
            return 1;
        }
        if (u > 0) {
            u = -u;
        }
        if (v > 0) {
            v = -v;
        }
        for (k = 0; (u & 1) == 0 && (v & 1) == 0 && k < 31; ++k) {
            u /= 2;
            v /= 2;
        }
        if (k == 31) {
            throw new ArithmeticException("overflow: gcd is 2^31");
        }
        int n = t = (u & 1) == 1 ? v : -(u / 2);
        while (true) {
            if ((t & 1) == 0) {
                t /= 2;
                continue;
            }
            if (t > 0) {
                u = -t;
            } else {
                v = t;
            }
            if ((t = (v - u) / 2) == 0) break;
        }
        return -u * (1 << k);
    }

    private static int mulAndCheck(int x, int y) {
        long m = (long)x * (long)y;
        if (m < Integer.MIN_VALUE || m > Integer.MAX_VALUE) {
            throw new ArithmeticException("overflow: mul");
        }
        return (int)m;
    }

    private static int mulPosAndCheck(int x, int y) {
        long m = (long)x * (long)y;
        if (m > Integer.MAX_VALUE) {
            throw new ArithmeticException("overflow: mulPos");
        }
        return (int)m;
    }

    private static int addAndCheck(int x, int y) {
        long s = (long)x + (long)y;
        if (s < Integer.MIN_VALUE || s > Integer.MAX_VALUE) {
            throw new ArithmeticException("overflow: add");
        }
        return (int)s;
    }

    private static int subAndCheck(int x, int y) {
        long s = (long)x - (long)y;
        if (s < Integer.MIN_VALUE || s > Integer.MAX_VALUE) {
            throw new ArithmeticException("overflow: add");
        }
        return (int)s;
    }

    public Fraction add(Fraction fraction) {
        return this.addSub(fraction, true);
    }

    public Fraction subtract(Fraction fraction) {
        return this.addSub(fraction, false);
    }

    private Fraction addSub(Fraction fraction, boolean isAdd) {
        int tmodd1;
        int d2;
        Validate.notNull(fraction, "fraction", new Object[0]);
        if (this.numerator == 0) {
            return isAdd ? fraction : fraction.negate();
        }
        if (fraction.numerator == 0) {
            return this;
        }
        int d1 = Fraction.greatestCommonDivisor(this.denominator, fraction.denominator);
        if (d1 == 1) {
            int uvp = Fraction.mulAndCheck(this.numerator, fraction.denominator);
            int upv = Fraction.mulAndCheck(fraction.numerator, this.denominator);
            return new Fraction(isAdd ? Fraction.addAndCheck(uvp, upv) : Fraction.subAndCheck(uvp, upv), Fraction.mulPosAndCheck(this.denominator, fraction.denominator));
        }
        BigInteger uvp = BigInteger.valueOf(this.numerator).multiply(BigInteger.valueOf(fraction.denominator / d1));
        BigInteger upv = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(this.denominator / d1));
        BigInteger t = isAdd ? uvp.add(upv) : uvp.subtract(upv);
        BigInteger w = t.divide(BigInteger.valueOf(d2 = (tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue()) == 0 ? d1 : Fraction.greatestCommonDivisor(tmodd1, d1)));
        if (w.bitLength() > 31) {
            throw new ArithmeticException("overflow: numerator too large after multiply");
        }
        return new Fraction(w.intValue(), Fraction.mulPosAndCheck(this.denominator / d1, fraction.denominator / d2));
    }

    public Fraction multiplyBy(Fraction fraction) {
        Validate.notNull(fraction, "fraction", new Object[0]);
        if (this.numerator == 0 || fraction.numerator == 0) {
            return ZERO;
        }
        int d1 = Fraction.greatestCommonDivisor(this.numerator, fraction.denominator);
        int d2 = Fraction.greatestCommonDivisor(fraction.numerator, this.denominator);
        return Fraction.getReducedFraction(Fraction.mulAndCheck(this.numerator / d1, fraction.numerator / d2), Fraction.mulPosAndCheck(this.denominator / d2, fraction.denominator / d1));
    }

    public Fraction divideBy(Fraction fraction) {
        Validate.notNull(fraction, "fraction", new Object[0]);
        if (fraction.numerator == 0) {
            throw new ArithmeticException("The fraction to divide by must not be zero");
        }
        return this.multiplyBy(fraction.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction other = (Fraction)obj;
        return this.getNumerator() == other.getNumerator() && this.getDenominator() == other.getDenominator();
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = 37 * (629 + this.getNumerator()) + this.getDenominator();
        }
        return this.hashCode;
    }

    @Override
    public int compareTo(Fraction other) {
        if (this == other) {
            return 0;
        }
        if (this.numerator == other.numerator && this.denominator == other.denominator) {
            return 0;
        }
        long first = (long)this.numerator * (long)other.denominator;
        long second = (long)other.numerator * (long)this.denominator;
        return Long.compare(first, second);
    }

    public String toString() {
        if (this.toString == null) {
            this.toString = this.getNumerator() + "/" + this.getDenominator();
        }
        return this.toString;
    }

    public String toProperString() {
        if (this.toProperString == null) {
            int properNumerator;
            this.toProperString = this.numerator == 0 ? "0" : (this.numerator == this.denominator ? "1" : (this.numerator == -1 * this.denominator ? "-1" : ((this.numerator > 0 ? -this.numerator : this.numerator) < -this.denominator ? ((properNumerator = this.getProperNumerator()) == 0 ? Integer.toString(this.getProperWhole()) : this.getProperWhole() + " " + properNumerator + "/" + this.getDenominator()) : this.getNumerator() + "/" + this.getDenominator())));
        }
        return this.toProperString;
    }
}

