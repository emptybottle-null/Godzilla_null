/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.differentiation.DSCompiler;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DerivativeStructure
implements RealFieldElement<DerivativeStructure>,
Serializable {
    private static final long serialVersionUID = 20120730L;
    private transient DSCompiler compiler;
    private final double[] data;

    private DerivativeStructure(DSCompiler compiler) {
        this.compiler = compiler;
        this.data = new double[compiler.getSize()];
    }

    public DerivativeStructure(int parameters, int order) throws NumberIsTooLargeException {
        this(DSCompiler.getCompiler(parameters, order));
    }

    public DerivativeStructure(int parameters, int order, double value) throws NumberIsTooLargeException {
        this(parameters, order);
        this.data[0] = value;
    }

    public DerivativeStructure(int parameters, int order, int index, double value) throws NumberIsTooLargeException {
        this(parameters, order, value);
        if (index >= parameters) {
            throw new NumberIsTooLargeException(index, (Number)parameters, false);
        }
        if (order > 0) {
            this.data[DSCompiler.getCompiler((int)index, (int)order).getSize()] = 1.0;
        }
    }

    public DerivativeStructure(double a1, DerivativeStructure ds1, double a2, DerivativeStructure ds2) throws DimensionMismatchException {
        this(ds1.compiler);
        this.compiler.checkCompatibility(ds2.compiler);
        this.compiler.linearCombination(a1, ds1.data, 0, a2, ds2.data, 0, this.data, 0);
    }

    public DerivativeStructure(double a1, DerivativeStructure ds1, double a2, DerivativeStructure ds2, double a3, DerivativeStructure ds3) throws DimensionMismatchException {
        this(ds1.compiler);
        this.compiler.checkCompatibility(ds2.compiler);
        this.compiler.checkCompatibility(ds3.compiler);
        this.compiler.linearCombination(a1, ds1.data, 0, a2, ds2.data, 0, a3, ds3.data, 0, this.data, 0);
    }

    public DerivativeStructure(double a1, DerivativeStructure ds1, double a2, DerivativeStructure ds2, double a3, DerivativeStructure ds3, double a4, DerivativeStructure ds4) throws DimensionMismatchException {
        this(ds1.compiler);
        this.compiler.checkCompatibility(ds2.compiler);
        this.compiler.checkCompatibility(ds3.compiler);
        this.compiler.checkCompatibility(ds4.compiler);
        this.compiler.linearCombination(a1, ds1.data, 0, a2, ds2.data, 0, a3, ds3.data, 0, a4, ds4.data, 0, this.data, 0);
    }

    public DerivativeStructure(int parameters, int order, double ... derivatives) throws DimensionMismatchException, NumberIsTooLargeException {
        this(parameters, order);
        if (derivatives.length != this.data.length) {
            throw new DimensionMismatchException(derivatives.length, this.data.length);
        }
        System.arraycopy(derivatives, 0, this.data, 0, this.data.length);
    }

    private DerivativeStructure(DerivativeStructure ds) {
        this.compiler = ds.compiler;
        this.data = (double[])ds.data.clone();
    }

    public int getFreeParameters() {
        return this.compiler.getFreeParameters();
    }

    public int getOrder() {
        return this.compiler.getOrder();
    }

    public DerivativeStructure createConstant(double c) {
        return new DerivativeStructure(this.getFreeParameters(), this.getOrder(), c);
    }

    @Override
    public double getReal() {
        return this.data[0];
    }

    public double getValue() {
        return this.data[0];
    }

    public double getPartialDerivative(int ... orders) throws DimensionMismatchException, NumberIsTooLargeException {
        return this.data[this.compiler.getPartialDerivativeIndex(orders)];
    }

    public double[] getAllDerivatives() {
        return (double[])this.data.clone();
    }

    @Override
    public DerivativeStructure add(double a) {
        DerivativeStructure ds = new DerivativeStructure(this);
        ds.data[0] = ds.data[0] + a;
        return ds;
    }

    @Override
    public DerivativeStructure add(DerivativeStructure a) throws DimensionMismatchException {
        this.compiler.checkCompatibility(a.compiler);
        DerivativeStructure ds = new DerivativeStructure(this);
        this.compiler.add(this.data, 0, a.data, 0, ds.data, 0);
        return ds;
    }

    @Override
    public DerivativeStructure subtract(double a) {
        return this.add(-a);
    }

    @Override
    public DerivativeStructure subtract(DerivativeStructure a) throws DimensionMismatchException {
        this.compiler.checkCompatibility(a.compiler);
        DerivativeStructure ds = new DerivativeStructure(this);
        this.compiler.subtract(this.data, 0, a.data, 0, ds.data, 0);
        return ds;
    }

    @Override
    public DerivativeStructure multiply(int n) {
        return this.multiply((double)n);
    }

    @Override
    public DerivativeStructure multiply(double a) {
        DerivativeStructure ds = new DerivativeStructure(this);
        int i = 0;
        while (i < ds.data.length) {
            int n = i++;
            ds.data[n] = ds.data[n] * a;
        }
        return ds;
    }

    @Override
    public DerivativeStructure multiply(DerivativeStructure a) throws DimensionMismatchException {
        this.compiler.checkCompatibility(a.compiler);
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.multiply(this.data, 0, a.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure divide(double a) {
        DerivativeStructure ds = new DerivativeStructure(this);
        int i = 0;
        while (i < ds.data.length) {
            int n = i++;
            ds.data[n] = ds.data[n] / a;
        }
        return ds;
    }

    @Override
    public DerivativeStructure divide(DerivativeStructure a) throws DimensionMismatchException {
        this.compiler.checkCompatibility(a.compiler);
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.divide(this.data, 0, a.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure remainder(double a) {
        DerivativeStructure ds = new DerivativeStructure(this);
        ds.data[0] = FastMath.IEEEremainder(ds.data[0], a);
        return ds;
    }

    @Override
    public DerivativeStructure remainder(DerivativeStructure a) throws DimensionMismatchException {
        this.compiler.checkCompatibility(a.compiler);
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.remainder(this.data, 0, a.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure negate() {
        DerivativeStructure ds = new DerivativeStructure(this.compiler);
        for (int i = 0; i < ds.data.length; ++i) {
            ds.data[i] = -this.data[i];
        }
        return ds;
    }

    @Override
    public DerivativeStructure abs() {
        if (Double.doubleToLongBits(this.data[0]) < 0L) {
            return this.negate();
        }
        return this;
    }

    @Override
    public DerivativeStructure ceil() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.ceil(this.data[0]));
    }

    @Override
    public DerivativeStructure floor() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.floor(this.data[0]));
    }

    @Override
    public DerivativeStructure rint() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.rint(this.data[0]));
    }

    @Override
    public long round() {
        return FastMath.round(this.data[0]);
    }

    @Override
    public DerivativeStructure signum() {
        return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getOrder(), FastMath.signum(this.data[0]));
    }

    @Override
    public DerivativeStructure copySign(DerivativeStructure sign) {
        long m = Double.doubleToLongBits(this.data[0]);
        long s = Double.doubleToLongBits(sign.data[0]);
        if (m >= 0L && s >= 0L || m < 0L && s < 0L) {
            return this;
        }
        return this.negate();
    }

    @Override
    public DerivativeStructure copySign(double sign) {
        long m = Double.doubleToLongBits(this.data[0]);
        long s = Double.doubleToLongBits(sign);
        if (m >= 0L && s >= 0L || m < 0L && s < 0L) {
            return this;
        }
        return this.negate();
    }

    public int getExponent() {
        return FastMath.getExponent(this.data[0]);
    }

    @Override
    public DerivativeStructure scalb(int n) {
        DerivativeStructure ds = new DerivativeStructure(this.compiler);
        for (int i = 0; i < ds.data.length; ++i) {
            ds.data[i] = FastMath.scalb(this.data[i], n);
        }
        return ds;
    }

    @Override
    public DerivativeStructure hypot(DerivativeStructure y) throws DimensionMismatchException {
        int expY;
        this.compiler.checkCompatibility(y.compiler);
        if (Double.isInfinite(this.data[0]) || Double.isInfinite(y.data[0])) {
            return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getFreeParameters(), Double.POSITIVE_INFINITY);
        }
        if (Double.isNaN(this.data[0]) || Double.isNaN(y.data[0])) {
            return new DerivativeStructure(this.compiler.getFreeParameters(), this.compiler.getFreeParameters(), Double.NaN);
        }
        int expX = this.getExponent();
        if (expX > (expY = y.getExponent()) + 27) {
            return this.abs();
        }
        if (expY > expX + 27) {
            return y.abs();
        }
        int middleExp = (expX + expY) / 2;
        DerivativeStructure scaledX = this.scalb(-middleExp);
        DerivativeStructure scaledY = y.scalb(-middleExp);
        DerivativeStructure scaledH = scaledX.multiply(scaledX).add(scaledY.multiply(scaledY)).sqrt();
        return scaledH.scalb(middleExp);
    }

    public static DerivativeStructure hypot(DerivativeStructure x, DerivativeStructure y) throws DimensionMismatchException {
        return x.hypot(y);
    }

    public DerivativeStructure compose(double ... f) throws DimensionMismatchException {
        if (f.length != this.getOrder() + 1) {
            throw new DimensionMismatchException(f.length, this.getOrder() + 1);
        }
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.compose(this.data, 0, f, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure reciprocal() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, -1, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure sqrt() {
        return this.rootN(2);
    }

    @Override
    public DerivativeStructure cbrt() {
        return this.rootN(3);
    }

    @Override
    public DerivativeStructure rootN(int n) {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.rootN(this.data, 0, n, result.data, 0);
        return result;
    }

    @Override
    public Field<DerivativeStructure> getField() {
        return new Field<DerivativeStructure>(){

            @Override
            public DerivativeStructure getZero() {
                return new DerivativeStructure(DerivativeStructure.this.compiler.getFreeParameters(), DerivativeStructure.this.compiler.getOrder(), 0.0);
            }

            @Override
            public DerivativeStructure getOne() {
                return new DerivativeStructure(DerivativeStructure.this.compiler.getFreeParameters(), DerivativeStructure.this.compiler.getOrder(), 1.0);
            }

            @Override
            public Class<? extends FieldElement<DerivativeStructure>> getRuntimeClass() {
                return DerivativeStructure.class;
            }
        };
    }

    public static DerivativeStructure pow(double a, DerivativeStructure x) {
        DerivativeStructure result = new DerivativeStructure(x.compiler);
        x.compiler.pow(a, x.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure pow(double p) {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, p, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure pow(int n) {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, n, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure pow(DerivativeStructure e) throws DimensionMismatchException {
        this.compiler.checkCompatibility(e.compiler);
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.pow(this.data, 0, e.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure exp() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.exp(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure expm1() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.expm1(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure log() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.log(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure log1p() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.log1p(this.data, 0, result.data, 0);
        return result;
    }

    public DerivativeStructure log10() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.log10(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure cos() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.cos(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure sin() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.sin(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure tan() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.tan(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure acos() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.acos(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure asin() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.asin(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure atan() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.atan(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure atan2(DerivativeStructure x) throws DimensionMismatchException {
        this.compiler.checkCompatibility(x.compiler);
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.atan2(this.data, 0, x.data, 0, result.data, 0);
        return result;
    }

    public static DerivativeStructure atan2(DerivativeStructure y, DerivativeStructure x) throws DimensionMismatchException {
        return y.atan2(x);
    }

    @Override
    public DerivativeStructure cosh() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.cosh(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure sinh() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.sinh(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure tanh() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.tanh(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure acosh() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.acosh(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure asinh() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.asinh(this.data, 0, result.data, 0);
        return result;
    }

    @Override
    public DerivativeStructure atanh() {
        DerivativeStructure result = new DerivativeStructure(this.compiler);
        this.compiler.atanh(this.data, 0, result.data, 0);
        return result;
    }

    public DerivativeStructure toDegrees() {
        DerivativeStructure ds = new DerivativeStructure(this.compiler);
        for (int i = 0; i < ds.data.length; ++i) {
            ds.data[i] = FastMath.toDegrees(this.data[i]);
        }
        return ds;
    }

    public DerivativeStructure toRadians() {
        DerivativeStructure ds = new DerivativeStructure(this.compiler);
        for (int i = 0; i < ds.data.length; ++i) {
            ds.data[i] = FastMath.toRadians(this.data[i]);
        }
        return ds;
    }

    public double taylor(double ... delta) throws MathArithmeticException {
        return this.compiler.taylor(this.data, 0, delta);
    }

    public DerivativeStructure linearCombination(DerivativeStructure[] a, DerivativeStructure[] b) throws DimensionMismatchException {
        double[] aDouble = new double[a.length];
        for (int i = 0; i < a.length; ++i) {
            aDouble[i] = a[i].getValue();
        }
        double[] bDouble = new double[b.length];
        for (int i = 0; i < b.length; ++i) {
            bDouble[i] = b[i].getValue();
        }
        double accurateValue = MathArrays.linearCombination(aDouble, bDouble);
        DerivativeStructure simpleValue = a[0].getField().getZero();
        for (int i = 0; i < a.length; ++i) {
            simpleValue = simpleValue.add(a[i].multiply(b[i]));
        }
        double[] all = simpleValue.getAllDerivatives();
        all[0] = accurateValue;
        return new DerivativeStructure(simpleValue.getFreeParameters(), simpleValue.getOrder(), all);
    }

    public DerivativeStructure linearCombination(double[] a, DerivativeStructure[] b) throws DimensionMismatchException {
        double[] bDouble = new double[b.length];
        for (int i = 0; i < b.length; ++i) {
            bDouble[i] = b[i].getValue();
        }
        double accurateValue = MathArrays.linearCombination(a, bDouble);
        DerivativeStructure simpleValue = b[0].getField().getZero();
        for (int i = 0; i < a.length; ++i) {
            simpleValue = simpleValue.add(b[i].multiply(a[i]));
        }
        double[] all = simpleValue.getAllDerivatives();
        all[0] = accurateValue;
        return new DerivativeStructure(simpleValue.getFreeParameters(), simpleValue.getOrder(), all);
    }

    @Override
    public DerivativeStructure linearCombination(DerivativeStructure a1, DerivativeStructure b1, DerivativeStructure a2, DerivativeStructure b2) throws DimensionMismatchException {
        double accurateValue = MathArrays.linearCombination(a1.getValue(), b1.getValue(), a2.getValue(), b2.getValue());
        DerivativeStructure simpleValue = a1.multiply(b1).add(a2.multiply(b2));
        double[] all = simpleValue.getAllDerivatives();
        all[0] = accurateValue;
        return new DerivativeStructure(this.getFreeParameters(), this.getOrder(), all);
    }

    @Override
    public DerivativeStructure linearCombination(double a1, DerivativeStructure b1, double a2, DerivativeStructure b2) throws DimensionMismatchException {
        double accurateValue = MathArrays.linearCombination(a1, b1.getValue(), a2, b2.getValue());
        DerivativeStructure simpleValue = b1.multiply(a1).add(b2.multiply(a2));
        double[] all = simpleValue.getAllDerivatives();
        all[0] = accurateValue;
        return new DerivativeStructure(this.getFreeParameters(), this.getOrder(), all);
    }

    @Override
    public DerivativeStructure linearCombination(DerivativeStructure a1, DerivativeStructure b1, DerivativeStructure a2, DerivativeStructure b2, DerivativeStructure a3, DerivativeStructure b3) throws DimensionMismatchException {
        double accurateValue = MathArrays.linearCombination(a1.getValue(), b1.getValue(), a2.getValue(), b2.getValue(), a3.getValue(), b3.getValue());
        DerivativeStructure simpleValue = a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3));
        double[] all = simpleValue.getAllDerivatives();
        all[0] = accurateValue;
        return new DerivativeStructure(this.getFreeParameters(), this.getOrder(), all);
    }

    @Override
    public DerivativeStructure linearCombination(double a1, DerivativeStructure b1, double a2, DerivativeStructure b2, double a3, DerivativeStructure b3) throws DimensionMismatchException {
        double accurateValue = MathArrays.linearCombination(a1, b1.getValue(), a2, b2.getValue(), a3, b3.getValue());
        DerivativeStructure simpleValue = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3));
        double[] all = simpleValue.getAllDerivatives();
        all[0] = accurateValue;
        return new DerivativeStructure(this.getFreeParameters(), this.getOrder(), all);
    }

    @Override
    public DerivativeStructure linearCombination(DerivativeStructure a1, DerivativeStructure b1, DerivativeStructure a2, DerivativeStructure b2, DerivativeStructure a3, DerivativeStructure b3, DerivativeStructure a4, DerivativeStructure b4) throws DimensionMismatchException {
        double accurateValue = MathArrays.linearCombination(a1.getValue(), b1.getValue(), a2.getValue(), b2.getValue(), a3.getValue(), b3.getValue(), a4.getValue(), b4.getValue());
        DerivativeStructure simpleValue = a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3)).add(a4.multiply(b4));
        double[] all = simpleValue.getAllDerivatives();
        all[0] = accurateValue;
        return new DerivativeStructure(this.getFreeParameters(), this.getOrder(), all);
    }

    @Override
    public DerivativeStructure linearCombination(double a1, DerivativeStructure b1, double a2, DerivativeStructure b2, double a3, DerivativeStructure b3, double a4, DerivativeStructure b4) throws DimensionMismatchException {
        double accurateValue = MathArrays.linearCombination(a1, b1.getValue(), a2, b2.getValue(), a3, b3.getValue(), a4, b4.getValue());
        DerivativeStructure simpleValue = b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3)).add(b4.multiply(a4));
        double[] all = simpleValue.getAllDerivatives();
        all[0] = accurateValue;
        return new DerivativeStructure(this.getFreeParameters(), this.getOrder(), all);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof DerivativeStructure) {
            DerivativeStructure rhs = (DerivativeStructure)other;
            return this.getFreeParameters() == rhs.getFreeParameters() && this.getOrder() == rhs.getOrder() && MathArrays.equals(this.data, rhs.data);
        }
        return false;
    }

    public int hashCode() {
        return 227 + 229 * this.getFreeParameters() + 233 * this.getOrder() + 239 * MathUtils.hash(this.data);
    }

    private Object writeReplace() {
        return new DataTransferObject(this.compiler.getFreeParameters(), this.compiler.getOrder(), this.data);
    }

    private static class DataTransferObject
    implements Serializable {
        private static final long serialVersionUID = 20120730L;
        private final int variables;
        private final int order;
        private final double[] data;

        DataTransferObject(int variables, int order, double[] data) {
            this.variables = variables;
            this.order = order;
            this.data = data;
        }

        private Object readResolve() {
            return new DerivativeStructure(this.variables, this.order, this.data);
        }
    }
}

