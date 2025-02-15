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
import org.apache.commons.math3.util.FastMath;

public class HarmonicOscillator
implements UnivariateDifferentiableFunction,
DifferentiableUnivariateFunction {
    private final double amplitude;
    private final double omega;
    private final double phase;

    public HarmonicOscillator(double amplitude, double omega, double phase) {
        this.amplitude = amplitude;
        this.omega = omega;
        this.phase = phase;
    }

    public double value(double x) {
        return HarmonicOscillator.value(this.omega * x + this.phase, this.amplitude);
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    private static double value(double xTimesOmegaPlusPhase, double amplitude) {
        return amplitude * FastMath.cos(xTimesOmegaPlusPhase);
    }

    public DerivativeStructure value(DerivativeStructure t) throws DimensionMismatchException {
        double x = t.getValue();
        double[] f = new double[t.getOrder() + 1];
        double alpha = this.omega * x + this.phase;
        f[0] = this.amplitude * FastMath.cos(alpha);
        if (f.length > 1) {
            f[1] = -this.amplitude * this.omega * FastMath.sin(alpha);
            double mo2 = -this.omega * this.omega;
            for (int i = 2; i < f.length; ++i) {
                f[i] = mo2 * f[i - 2];
            }
        }
        return t.compose(f);
    }

    public static class Parametric
    implements ParametricUnivariateFunction {
        public double value(double x, double ... param) throws NullArgumentException, DimensionMismatchException {
            this.validateParameters(param);
            return HarmonicOscillator.value(x * param[1] + param[2], param[0]);
        }

        public double[] gradient(double x, double ... param) throws NullArgumentException, DimensionMismatchException {
            this.validateParameters(param);
            double amplitude = param[0];
            double omega = param[1];
            double phase = param[2];
            double xTimesOmegaPlusPhase = omega * x + phase;
            double a = HarmonicOscillator.value(xTimesOmegaPlusPhase, 1.0);
            double p = -amplitude * FastMath.sin(xTimesOmegaPlusPhase);
            double w = p * x;
            return new double[]{a, w, p};
        }

        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException {
            if (param == null) {
                throw new NullArgumentException();
            }
            if (param.length != 3) {
                throw new DimensionMismatchException(param.length, 3);
            }
        }
    }
}

