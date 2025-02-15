/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Decimal64Field;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Decimal64
extends Number
implements RealFieldElement<Decimal64>,
Comparable<Decimal64> {
    public static final Decimal64 ZERO = new Decimal64(0.0);
    public static final Decimal64 ONE = new Decimal64(1.0);
    public static final Decimal64 NEGATIVE_INFINITY = new Decimal64(Double.NEGATIVE_INFINITY);
    public static final Decimal64 POSITIVE_INFINITY = new Decimal64(Double.POSITIVE_INFINITY);
    public static final Decimal64 NAN = new Decimal64(Double.NaN);
    private static final long serialVersionUID = 20120227L;
    private final double value;

    public Decimal64(double x) {
        this.value = x;
    }

    @Override
    public Field<Decimal64> getField() {
        return Decimal64Field.getInstance();
    }

    @Override
    public Decimal64 add(Decimal64 a) {
        return new Decimal64(this.value + a.value);
    }

    @Override
    public Decimal64 subtract(Decimal64 a) {
        return new Decimal64(this.value - a.value);
    }

    @Override
    public Decimal64 negate() {
        return new Decimal64(-this.value);
    }

    @Override
    public Decimal64 multiply(Decimal64 a) {
        return new Decimal64(this.value * a.value);
    }

    @Override
    public Decimal64 multiply(int n) {
        return new Decimal64((double)n * this.value);
    }

    @Override
    public Decimal64 divide(Decimal64 a) {
        return new Decimal64(this.value / a.value);
    }

    @Override
    public Decimal64 reciprocal() {
        return new Decimal64(1.0 / this.value);
    }

    @Override
    public byte byteValue() {
        return (byte)this.value;
    }

    @Override
    public short shortValue() {
        return (short)this.value;
    }

    @Override
    public int intValue() {
        return (int)this.value;
    }

    @Override
    public long longValue() {
        return (long)this.value;
    }

    @Override
    public float floatValue() {
        return (float)this.value;
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    @Override
    public int compareTo(Decimal64 o) {
        return Double.compare(this.value, o.value);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Decimal64) {
            Decimal64 that = (Decimal64)obj;
            return Double.doubleToLongBits(this.value) == Double.doubleToLongBits(that.value);
        }
        return false;
    }

    public int hashCode() {
        long v = Double.doubleToLongBits(this.value);
        return (int)(v ^ v >>> 32);
    }

    public String toString() {
        return Double.toString(this.value);
    }

    public boolean isInfinite() {
        return Double.isInfinite(this.value);
    }

    public boolean isNaN() {
        return Double.isNaN(this.value);
    }

    @Override
    public double getReal() {
        return this.value;
    }

    @Override
    public Decimal64 add(double a) {
        return new Decimal64(this.value + a);
    }

    @Override
    public Decimal64 subtract(double a) {
        return new Decimal64(this.value - a);
    }

    @Override
    public Decimal64 multiply(double a) {
        return new Decimal64(this.value * a);
    }

    @Override
    public Decimal64 divide(double a) {
        return new Decimal64(this.value / a);
    }

    @Override
    public Decimal64 remainder(double a) {
        return new Decimal64(FastMath.IEEEremainder(this.value, a));
    }

    @Override
    public Decimal64 remainder(Decimal64 a) {
        return new Decimal64(FastMath.IEEEremainder(this.value, a.value));
    }

    @Override
    public Decimal64 abs() {
        return new Decimal64(FastMath.abs(this.value));
    }

    @Override
    public Decimal64 ceil() {
        return new Decimal64(FastMath.ceil(this.value));
    }

    @Override
    public Decimal64 floor() {
        return new Decimal64(FastMath.floor(this.value));
    }

    @Override
    public Decimal64 rint() {
        return new Decimal64(FastMath.rint(this.value));
    }

    @Override
    public long round() {
        return FastMath.round(this.value);
    }

    @Override
    public Decimal64 signum() {
        return new Decimal64(FastMath.signum(this.value));
    }

    @Override
    public Decimal64 copySign(Decimal64 sign) {
        return new Decimal64(FastMath.copySign(this.value, sign.value));
    }

    @Override
    public Decimal64 copySign(double sign) {
        return new Decimal64(FastMath.copySign(this.value, sign));
    }

    @Override
    public Decimal64 scalb(int n) {
        return new Decimal64(FastMath.scalb(this.value, n));
    }

    @Override
    public Decimal64 hypot(Decimal64 y) {
        return new Decimal64(FastMath.hypot(this.value, y.value));
    }

    @Override
    public Decimal64 sqrt() {
        return new Decimal64(FastMath.sqrt(this.value));
    }

    @Override
    public Decimal64 cbrt() {
        return new Decimal64(FastMath.cbrt(this.value));
    }

    @Override
    public Decimal64 rootN(int n) {
        if (this.value < 0.0) {
            return new Decimal64(-FastMath.pow(-this.value, 1.0 / (double)n));
        }
        return new Decimal64(FastMath.pow(this.value, 1.0 / (double)n));
    }

    @Override
    public Decimal64 pow(double p) {
        return new Decimal64(FastMath.pow(this.value, p));
    }

    @Override
    public Decimal64 pow(int n) {
        return new Decimal64(FastMath.pow(this.value, n));
    }

    @Override
    public Decimal64 pow(Decimal64 e) {
        return new Decimal64(FastMath.pow(this.value, e.value));
    }

    @Override
    public Decimal64 exp() {
        return new Decimal64(FastMath.exp(this.value));
    }

    @Override
    public Decimal64 expm1() {
        return new Decimal64(FastMath.expm1(this.value));
    }

    @Override
    public Decimal64 log() {
        return new Decimal64(FastMath.log(this.value));
    }

    @Override
    public Decimal64 log1p() {
        return new Decimal64(FastMath.log1p(this.value));
    }

    public Decimal64 log10() {
        return new Decimal64(FastMath.log10(this.value));
    }

    @Override
    public Decimal64 cos() {
        return new Decimal64(FastMath.cos(this.value));
    }

    @Override
    public Decimal64 sin() {
        return new Decimal64(FastMath.sin(this.value));
    }

    @Override
    public Decimal64 tan() {
        return new Decimal64(FastMath.tan(this.value));
    }

    @Override
    public Decimal64 acos() {
        return new Decimal64(FastMath.acos(this.value));
    }

    @Override
    public Decimal64 asin() {
        return new Decimal64(FastMath.asin(this.value));
    }

    @Override
    public Decimal64 atan() {
        return new Decimal64(FastMath.atan(this.value));
    }

    @Override
    public Decimal64 atan2(Decimal64 x) {
        return new Decimal64(FastMath.atan2(this.value, x.value));
    }

    @Override
    public Decimal64 cosh() {
        return new Decimal64(FastMath.cosh(this.value));
    }

    @Override
    public Decimal64 sinh() {
        return new Decimal64(FastMath.sinh(this.value));
    }

    @Override
    public Decimal64 tanh() {
        return new Decimal64(FastMath.tanh(this.value));
    }

    @Override
    public Decimal64 acosh() {
        return new Decimal64(FastMath.acosh(this.value));
    }

    @Override
    public Decimal64 asinh() {
        return new Decimal64(FastMath.asinh(this.value));
    }

    @Override
    public Decimal64 atanh() {
        return new Decimal64(FastMath.atanh(this.value));
    }

    public Decimal64 linearCombination(Decimal64[] a, Decimal64[] b) throws DimensionMismatchException {
        if (a.length != b.length) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        double[] aDouble = new double[a.length];
        double[] bDouble = new double[b.length];
        for (int i = 0; i < a.length; ++i) {
            aDouble[i] = a[i].value;
            bDouble[i] = b[i].value;
        }
        return new Decimal64(MathArrays.linearCombination(aDouble, bDouble));
    }

    public Decimal64 linearCombination(double[] a, Decimal64[] b) throws DimensionMismatchException {
        if (a.length != b.length) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        double[] bDouble = new double[b.length];
        for (int i = 0; i < a.length; ++i) {
            bDouble[i] = b[i].value;
        }
        return new Decimal64(MathArrays.linearCombination(a, bDouble));
    }

    @Override
    public Decimal64 linearCombination(Decimal64 a1, Decimal64 b1, Decimal64 a2, Decimal64 b2) {
        return new Decimal64(MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value));
    }

    @Override
    public Decimal64 linearCombination(double a1, Decimal64 b1, double a2, Decimal64 b2) {
        return new Decimal64(MathArrays.linearCombination(a1, b1.value, a2, b2.value));
    }

    @Override
    public Decimal64 linearCombination(Decimal64 a1, Decimal64 b1, Decimal64 a2, Decimal64 b2, Decimal64 a3, Decimal64 b3) {
        return new Decimal64(MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value));
    }

    @Override
    public Decimal64 linearCombination(double a1, Decimal64 b1, double a2, Decimal64 b2, double a3, Decimal64 b3) {
        return new Decimal64(MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value));
    }

    @Override
    public Decimal64 linearCombination(Decimal64 a1, Decimal64 b1, Decimal64 a2, Decimal64 b2, Decimal64 a3, Decimal64 b3, Decimal64 a4, Decimal64 b4) {
        return new Decimal64(MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value, a4.value, b4.value));
    }

    @Override
    public Decimal64 linearCombination(double a1, Decimal64 b1, double a2, Decimal64 b2, double a3, Decimal64 b3, double a4, Decimal64 b4) {
        return new Decimal64(MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value, a4, b4.value));
    }
}

