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
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;

public class Logit
implements UnivariateDifferentiableFunction,
DifferentiableUnivariateFunction {
    private final double lo;
    private final double hi;

    public Logit() {
        this(0.0, 1.0);
    }

    public Logit(double lo, double hi) {
        this.lo = lo;
        this.hi = hi;
    }

    public double value(double x) throws OutOfRangeException {
        return Logit.value(x, this.lo, this.hi);
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    private static double value(double x, double lo, double hi) throws OutOfRangeException {
        if (x < lo || x > hi) {
            throw new OutOfRangeException(x, (Number)lo, hi);
        }
        return FastMath.log((x - lo) / (hi - x));
    }

    public DerivativeStructure value(DerivativeStructure t) throws OutOfRangeException {
        double x = t.getValue();
        if (x < this.lo || x > this.hi) {
            throw new OutOfRangeException(x, (Number)this.lo, this.hi);
        }
        double[] f = new double[t.getOrder() + 1];
        f[0] = FastMath.log((x - this.lo) / (this.hi - x));
        if (Double.isInfinite(f[0])) {
            if (f.length > 1) {
                f[1] = Double.POSITIVE_INFINITY;
            }
            for (int i = 2; i < f.length; ++i) {
                f[i] = f[i - 2];
            }
        } else {
            double invH;
            double invL;
            double xL = invL = 1.0 / (x - this.lo);
            double xH = invH = 1.0 / (this.hi - x);
            for (int i = 1; i < f.length; ++i) {
                f[i] = xL + xH;
                xL *= (double)(-i) * invL;
                xH *= (double)i * invH;
            }
        }
        return t.compose(f);
    }

    public static class Parametric
    implements ParametricUnivariateFunction {
        public double value(double x, double ... param) throws NullArgumentException, DimensionMismatchException {
            this.validateParameters(param);
            return Logit.value(x, param[0], param[1]);
        }

        public double[] gradient(double x, double ... param) throws NullArgumentException, DimensionMismatchException {
            this.validateParameters(param);
            double lo = param[0];
            double hi = param[1];
            return new double[]{1.0 / (lo - x), 1.0 / (hi - x)};
        }

        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException {
            if (param == null) {
                throw new NullArgumentException();
            }
            if (param.length != 2) {
                throw new DimensionMismatchException(param.length, 2);
            }
        }
    }
}

