/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SparseGradient
implements RealFieldElement<SparseGradient>,
Serializable {
    private static final long serialVersionUID = 20131025L;
    private double value;
    private final Map<Integer, Double> derivatives;

    private SparseGradient(double value, Map<Integer, Double> derivatives) {
        this.value = value;
        this.derivatives = new HashMap<Integer, Double>();
        if (derivatives != null) {
            this.derivatives.putAll(derivatives);
        }
    }

    private SparseGradient(double value, double scale, Map<Integer, Double> derivatives) {
        this.value = value;
        this.derivatives = new HashMap<Integer, Double>();
        if (derivatives != null) {
            for (Map.Entry<Integer, Double> entry : derivatives.entrySet()) {
                this.derivatives.put(entry.getKey(), scale * entry.getValue());
            }
        }
    }

    public static SparseGradient createConstant(double value) {
        return new SparseGradient(value, Collections.<Integer, Double>emptyMap());
    }

    public static SparseGradient createVariable(int idx, double value) {
        return new SparseGradient(value, Collections.singletonMap(idx, 1.0));
    }

    public int numVars() {
        return this.derivatives.size();
    }

    public double getDerivative(int index) {
        Double out = this.derivatives.get(index);
        return out == null ? 0.0 : out;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public double getReal() {
        return this.value;
    }

    @Override
    public SparseGradient add(SparseGradient a) {
        SparseGradient out = new SparseGradient(this.value + a.value, this.derivatives);
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            int id = entry.getKey();
            Double old = out.derivatives.get(id);
            if (old == null) {
                out.derivatives.put(id, entry.getValue());
                continue;
            }
            out.derivatives.put(id, old + entry.getValue());
        }
        return out;
    }

    public void addInPlace(SparseGradient a) {
        this.value += a.value;
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            int id = entry.getKey();
            Double old = this.derivatives.get(id);
            if (old == null) {
                this.derivatives.put(id, entry.getValue());
                continue;
            }
            this.derivatives.put(id, old + entry.getValue());
        }
    }

    @Override
    public SparseGradient add(double c) {
        SparseGradient out = new SparseGradient(this.value + c, this.derivatives);
        return out;
    }

    @Override
    public SparseGradient subtract(SparseGradient a) {
        SparseGradient out = new SparseGradient(this.value - a.value, this.derivatives);
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            int id = entry.getKey();
            Double old = out.derivatives.get(id);
            if (old == null) {
                out.derivatives.put(id, -entry.getValue().doubleValue());
                continue;
            }
            out.derivatives.put(id, old - entry.getValue());
        }
        return out;
    }

    @Override
    public SparseGradient subtract(double c) {
        return new SparseGradient(this.value - c, this.derivatives);
    }

    @Override
    public SparseGradient multiply(SparseGradient a) {
        SparseGradient out = new SparseGradient(this.value * a.value, Collections.<Integer, Double>emptyMap());
        for (Map.Entry<Integer, Double> entry : this.derivatives.entrySet()) {
            out.derivatives.put(entry.getKey(), a.value * entry.getValue());
        }
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            int id = entry.getKey();
            Double old = out.derivatives.get(id);
            if (old == null) {
                out.derivatives.put(id, this.value * entry.getValue());
                continue;
            }
            out.derivatives.put(id, old + this.value * entry.getValue());
        }
        return out;
    }

    public void multiplyInPlace(SparseGradient a) {
        for (Map.Entry<Integer, Double> entry : this.derivatives.entrySet()) {
            this.derivatives.put(entry.getKey(), a.value * entry.getValue());
        }
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            int id = entry.getKey();
            Double old = this.derivatives.get(id);
            if (old == null) {
                this.derivatives.put(id, this.value * entry.getValue());
                continue;
            }
            this.derivatives.put(id, old + this.value * entry.getValue());
        }
        this.value *= a.value;
    }

    @Override
    public SparseGradient multiply(double c) {
        return new SparseGradient(this.value * c, c, this.derivatives);
    }

    @Override
    public SparseGradient multiply(int n) {
        return new SparseGradient(this.value * (double)n, n, this.derivatives);
    }

    @Override
    public SparseGradient divide(SparseGradient a) {
        SparseGradient out = new SparseGradient(this.value / a.value, Collections.<Integer, Double>emptyMap());
        for (Map.Entry<Integer, Double> entry : this.derivatives.entrySet()) {
            out.derivatives.put(entry.getKey(), entry.getValue() / a.value);
        }
        for (Map.Entry<Integer, Double> entry : a.derivatives.entrySet()) {
            int id = entry.getKey();
            Double old = out.derivatives.get(id);
            if (old == null) {
                out.derivatives.put(id, -out.value / a.value * entry.getValue());
                continue;
            }
            out.derivatives.put(id, old - out.value / a.value * entry.getValue());
        }
        return out;
    }

    @Override
    public SparseGradient divide(double c) {
        return new SparseGradient(this.value / c, 1.0 / c, this.derivatives);
    }

    @Override
    public SparseGradient negate() {
        return new SparseGradient(-this.value, -1.0, this.derivatives);
    }

    @Override
    public Field<SparseGradient> getField() {
        return new Field<SparseGradient>(){

            @Override
            public SparseGradient getZero() {
                return SparseGradient.createConstant(0.0);
            }

            @Override
            public SparseGradient getOne() {
                return SparseGradient.createConstant(1.0);
            }

            @Override
            public Class<? extends FieldElement<SparseGradient>> getRuntimeClass() {
                return SparseGradient.class;
            }
        };
    }

    @Override
    public SparseGradient remainder(double a) {
        return new SparseGradient(FastMath.IEEEremainder(this.value, a), this.derivatives);
    }

    @Override
    public SparseGradient remainder(SparseGradient a) {
        double rem = FastMath.IEEEremainder(this.value, a.value);
        double k = FastMath.rint((this.value - rem) / a.value);
        return this.subtract(a.multiply(k));
    }

    @Override
    public SparseGradient abs() {
        if (Double.doubleToLongBits(this.value) < 0L) {
            return this.negate();
        }
        return this;
    }

    @Override
    public SparseGradient ceil() {
        return SparseGradient.createConstant(FastMath.ceil(this.value));
    }

    @Override
    public SparseGradient floor() {
        return SparseGradient.createConstant(FastMath.floor(this.value));
    }

    @Override
    public SparseGradient rint() {
        return SparseGradient.createConstant(FastMath.rint(this.value));
    }

    @Override
    public long round() {
        return FastMath.round(this.value);
    }

    @Override
    public SparseGradient signum() {
        return SparseGradient.createConstant(FastMath.signum(this.value));
    }

    @Override
    public SparseGradient copySign(SparseGradient sign) {
        long m = Double.doubleToLongBits(this.value);
        long s = Double.doubleToLongBits(sign.value);
        if (m >= 0L && s >= 0L || m < 0L && s < 0L) {
            return this;
        }
        return this.negate();
    }

    @Override
    public SparseGradient copySign(double sign) {
        long m = Double.doubleToLongBits(this.value);
        long s = Double.doubleToLongBits(sign);
        if (m >= 0L && s >= 0L || m < 0L && s < 0L) {
            return this;
        }
        return this.negate();
    }

    @Override
    public SparseGradient scalb(int n) {
        SparseGradient out = new SparseGradient(FastMath.scalb(this.value, n), Collections.<Integer, Double>emptyMap());
        for (Map.Entry<Integer, Double> entry : this.derivatives.entrySet()) {
            out.derivatives.put(entry.getKey(), FastMath.scalb(entry.getValue(), n));
        }
        return out;
    }

    @Override
    public SparseGradient hypot(SparseGradient y) {
        int expY;
        if (Double.isInfinite(this.value) || Double.isInfinite(y.value)) {
            return SparseGradient.createConstant(Double.POSITIVE_INFINITY);
        }
        if (Double.isNaN(this.value) || Double.isNaN(y.value)) {
            return SparseGradient.createConstant(Double.NaN);
        }
        int expX = FastMath.getExponent(this.value);
        if (expX > (expY = FastMath.getExponent(y.value)) + 27) {
            return this.abs();
        }
        if (expY > expX + 27) {
            return y.abs();
        }
        int middleExp = (expX + expY) / 2;
        SparseGradient scaledX = this.scalb(-middleExp);
        SparseGradient scaledY = y.scalb(-middleExp);
        SparseGradient scaledH = scaledX.multiply(scaledX).add(scaledY.multiply(scaledY)).sqrt();
        return scaledH.scalb(middleExp);
    }

    public static SparseGradient hypot(SparseGradient x, SparseGradient y) {
        return x.hypot(y);
    }

    @Override
    public SparseGradient reciprocal() {
        return new SparseGradient(1.0 / this.value, -1.0 / (this.value * this.value), this.derivatives);
    }

    @Override
    public SparseGradient sqrt() {
        double sqrt = FastMath.sqrt(this.value);
        return new SparseGradient(sqrt, 0.5 / sqrt, this.derivatives);
    }

    @Override
    public SparseGradient cbrt() {
        double cbrt = FastMath.cbrt(this.value);
        return new SparseGradient(cbrt, 1.0 / (3.0 * cbrt * cbrt), this.derivatives);
    }

    @Override
    public SparseGradient rootN(int n) {
        if (n == 2) {
            return this.sqrt();
        }
        if (n == 3) {
            return this.cbrt();
        }
        double root = FastMath.pow(this.value, 1.0 / (double)n);
        return new SparseGradient(root, 1.0 / ((double)n * FastMath.pow(root, n - 1)), this.derivatives);
    }

    @Override
    public SparseGradient pow(double p) {
        return new SparseGradient(FastMath.pow(this.value, p), p * FastMath.pow(this.value, p - 1.0), this.derivatives);
    }

    @Override
    public SparseGradient pow(int n) {
        if (n == 0) {
            return this.getField().getOne();
        }
        double valueNm1 = FastMath.pow(this.value, n - 1);
        return new SparseGradient(this.value * valueNm1, (double)n * valueNm1, this.derivatives);
    }

    @Override
    public SparseGradient pow(SparseGradient e) {
        return this.log().multiply(e).exp();
    }

    public static SparseGradient pow(double a, SparseGradient x) {
        if (a == 0.0) {
            if (x.value == 0.0) {
                return x.compose(1.0, Double.NEGATIVE_INFINITY);
            }
            if (x.value < 0.0) {
                return x.compose(Double.NaN, Double.NaN);
            }
            return x.getField().getZero();
        }
        double ax = FastMath.pow(a, x.value);
        return new SparseGradient(ax, ax * FastMath.log(a), x.derivatives);
    }

    @Override
    public SparseGradient exp() {
        double e = FastMath.exp(this.value);
        return new SparseGradient(e, e, this.derivatives);
    }

    @Override
    public SparseGradient expm1() {
        return new SparseGradient(FastMath.expm1(this.value), FastMath.exp(this.value), this.derivatives);
    }

    @Override
    public SparseGradient log() {
        return new SparseGradient(FastMath.log(this.value), 1.0 / this.value, this.derivatives);
    }

    public SparseGradient log10() {
        return new SparseGradient(FastMath.log10(this.value), 1.0 / (FastMath.log(10.0) * this.value), this.derivatives);
    }

    @Override
    public SparseGradient log1p() {
        return new SparseGradient(FastMath.log1p(this.value), 1.0 / (1.0 + this.value), this.derivatives);
    }

    @Override
    public SparseGradient cos() {
        return new SparseGradient(FastMath.cos(this.value), -FastMath.sin(this.value), this.derivatives);
    }

    @Override
    public SparseGradient sin() {
        return new SparseGradient(FastMath.sin(this.value), FastMath.cos(this.value), this.derivatives);
    }

    @Override
    public SparseGradient tan() {
        double t = FastMath.tan(this.value);
        return new SparseGradient(t, 1.0 + t * t, this.derivatives);
    }

    @Override
    public SparseGradient acos() {
        return new SparseGradient(FastMath.acos(this.value), -1.0 / FastMath.sqrt(1.0 - this.value * this.value), this.derivatives);
    }

    @Override
    public SparseGradient asin() {
        return new SparseGradient(FastMath.asin(this.value), 1.0 / FastMath.sqrt(1.0 - this.value * this.value), this.derivatives);
    }

    @Override
    public SparseGradient atan() {
        return new SparseGradient(FastMath.atan(this.value), 1.0 / (1.0 + this.value * this.value), this.derivatives);
    }

    @Override
    public SparseGradient atan2(SparseGradient x) {
        SparseGradient a;
        SparseGradient r = this.multiply(this).add(x.multiply(x)).sqrt();
        if (x.value >= 0.0) {
            a = this.divide(r.add(x)).atan().multiply(2);
        } else {
            SparseGradient tmp = this.divide(r.subtract(x)).atan().multiply(-2);
            a = tmp.add(tmp.value <= 0.0 ? -Math.PI : Math.PI);
        }
        a.value = FastMath.atan2(this.value, x.value);
        return a;
    }

    public static SparseGradient atan2(SparseGradient y, SparseGradient x) {
        return y.atan2(x);
    }

    @Override
    public SparseGradient cosh() {
        return new SparseGradient(FastMath.cosh(this.value), FastMath.sinh(this.value), this.derivatives);
    }

    @Override
    public SparseGradient sinh() {
        return new SparseGradient(FastMath.sinh(this.value), FastMath.cosh(this.value), this.derivatives);
    }

    @Override
    public SparseGradient tanh() {
        double t = FastMath.tanh(this.value);
        return new SparseGradient(t, 1.0 - t * t, this.derivatives);
    }

    @Override
    public SparseGradient acosh() {
        return new SparseGradient(FastMath.acosh(this.value), 1.0 / FastMath.sqrt(this.value * this.value - 1.0), this.derivatives);
    }

    @Override
    public SparseGradient asinh() {
        return new SparseGradient(FastMath.asinh(this.value), 1.0 / FastMath.sqrt(this.value * this.value + 1.0), this.derivatives);
    }

    @Override
    public SparseGradient atanh() {
        return new SparseGradient(FastMath.atanh(this.value), 1.0 / (1.0 - this.value * this.value), this.derivatives);
    }

    public SparseGradient toDegrees() {
        return new SparseGradient(FastMath.toDegrees(this.value), FastMath.toDegrees(1.0), this.derivatives);
    }

    public SparseGradient toRadians() {
        return new SparseGradient(FastMath.toRadians(this.value), FastMath.toRadians(1.0), this.derivatives);
    }

    public double taylor(double ... delta) {
        double y = this.value;
        for (int i = 0; i < delta.length; ++i) {
            y += delta[i] * this.getDerivative(i);
        }
        return y;
    }

    public SparseGradient compose(double f0, double f1) {
        return new SparseGradient(f0, f1, this.derivatives);
    }

    public SparseGradient linearCombination(SparseGradient[] a, SparseGradient[] b) throws DimensionMismatchException {
        SparseGradient out = a[0].getField().getZero();
        for (int i = 0; i < a.length; ++i) {
            out = out.add(a[i].multiply(b[i]));
        }
        double[] aDouble = new double[a.length];
        for (int i = 0; i < a.length; ++i) {
            aDouble[i] = a[i].getValue();
        }
        double[] bDouble = new double[b.length];
        for (int i = 0; i < b.length; ++i) {
            bDouble[i] = b[i].getValue();
        }
        out.value = MathArrays.linearCombination(aDouble, bDouble);
        return out;
    }

    public SparseGradient linearCombination(double[] a, SparseGradient[] b) {
        SparseGradient out = b[0].getField().getZero();
        for (int i = 0; i < a.length; ++i) {
            out = out.add(b[i].multiply(a[i]));
        }
        double[] bDouble = new double[b.length];
        for (int i = 0; i < b.length; ++i) {
            bDouble[i] = b[i].getValue();
        }
        out.value = MathArrays.linearCombination(a, bDouble);
        return out;
    }

    @Override
    public SparseGradient linearCombination(SparseGradient a1, SparseGradient b1, SparseGradient a2, SparseGradient b2) {
        SparseGradient out = a1.multiply(b1).add(a2.multiply(b2));
        out.value = MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value);
        return out;
    }

    @Override
    public SparseGradient linearCombination(double a1, SparseGradient b1, double a2, SparseGradient b2) {
        SparseGradient out = b1.multiply(a1).add(b2.multiply(a2));
        out.value = MathArrays.linearCombination(a1, b1.value, a2, b2.value);
        return out;
    }

    @Override
    public SparseGradient linearCombination(SparseGradient a1, SparseGradient b1, SparseGradient a2, SparseGradient b2, SparseGradient a3, SparseGradient b3) {
        SparseGradient out = a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3));
        out.value = MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value);
        return out;
    }

    @Override
    public SparseGradient linearCombination(double a1, SparseGradient b1, double a2, SparseGradient b2, double a3, SparseGradient b3) {
        SparseGradient out = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3));
        out.value = MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value);
        return out;
    }

    @Override
    public SparseGradient linearCombination(SparseGradient a1, SparseGradient b1, SparseGradient a2, SparseGradient b2, SparseGradient a3, SparseGradient b3, SparseGradient a4, SparseGradient b4) {
        SparseGradient out = a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3)).add(a4.multiply(b4));
        out.value = MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value, a4.value, b4.value);
        return out;
    }

    @Override
    public SparseGradient linearCombination(double a1, SparseGradient b1, double a2, SparseGradient b2, double a3, SparseGradient b3, double a4, SparseGradient b4) {
        SparseGradient out = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3)).add(b4.multiply(a4));
        out.value = MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value, a4, b4.value);
        return out;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof SparseGradient) {
            SparseGradient rhs = (SparseGradient)other;
            if (!Precision.equals(this.value, rhs.value, 1)) {
                return false;
            }
            if (this.derivatives.size() != rhs.derivatives.size()) {
                return false;
            }
            for (Map.Entry<Integer, Double> entry : this.derivatives.entrySet()) {
                if (!rhs.derivatives.containsKey(entry.getKey())) {
                    return false;
                }
                if (Precision.equals((double)entry.getValue(), (double)rhs.derivatives.get(entry.getKey()), 1)) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        return 743 + 809 * MathUtils.hash(this.value) + 167 * this.derivatives.hashCode();
    }
}

