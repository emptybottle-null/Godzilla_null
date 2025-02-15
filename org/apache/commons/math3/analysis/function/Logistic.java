/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;

public class Logistic
implements UnivariateDifferentiableFunction,
DifferentiableUnivariateFunction {
    private final double a;
    private final double k;
    private final double b;
    private final double oneOverN;
    private final double q;
    private final double m;

    public Logistic(double k, double m, double b, double q, double a, double n) throws NotStrictlyPositiveException {
        if (n <= 0.0) {
            throw new NotStrictlyPositiveException(n);
        }
        this.k = k;
        this.m = m;
        this.b = b;
        this.q = q;
        this.a = a;
        this.oneOverN = 1.0 / n;
    }

    public double value(double x) {
        return Logistic.value(this.m - x, this.k, this.b, this.q, this.a, this.oneOverN);
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    private static double value(double mMinusX, double k, double b, double q, double a, double oneOverN) {
        return a + (k - a) / FastMath.pow(1.0 + q * FastMath.exp(b * mMinusX), oneOverN);
    }

    public DerivativeStructure value(DerivativeStructure t) {
        return t.negate().add(this.m).multiply(this.b).exp().multiply(this.q).add(1.0).pow(this.oneOverN).reciprocal().multiply(this.k - this.a).add(this.a);
    }

    public static class Parametric
    implements ParametricUnivariateFunction {
        public double value(double x, double ... param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            this.validateParameters(param);
            return Logistic.value(param[1] - x, param[0], param[2], param[3], param[4], 1.0 / param[5]);
        }

        public double[] gradient(double x, double ... param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            this.validateParameters(param);
            double b = param[2];
            double q = param[3];
            double mMinusX = param[1] - x;
            double oneOverN = 1.0 / param[5];
            double exp = FastMath.exp(b * mMinusX);
            double qExp = q * exp;
            double qExp1 = qExp + 1.0;
            double factor1 = (param[0] - param[4]) * oneOverN / FastMath.pow(qExp1, oneOverN);
            double factor2 = -factor1 / qExp1;
            double gk = Logistic.value(mMinusX, 1.0, b, q, 0.0, oneOverN);
            double gm = factor2 * b * qExp;
            double gb = factor2 * mMinusX * qExp;
            double gq = factor2 * exp;
            double ga = Logistic.value(mMinusX, 0.0, b, q, 1.0, oneOverN);
            double gn = factor1 * FastMath.log(qExp1) * oneOverN;
            return new double[]{gk, gm, gb, gq, ga, gn};
        }

        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            if (param == null) {
                throw new NullArgumentException();
            }
            if (param.length != 6) {
                throw new DimensionMismatchException(param.length, 6);
            }
            if (param[5] <= 0.0) {
                throw new NotStrictlyPositiveException(param[5]);
            }
        }
    }
}

