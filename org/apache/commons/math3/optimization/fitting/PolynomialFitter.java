/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.fitting;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.optimization.fitting.CurveFitter;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class PolynomialFitter
extends CurveFitter<PolynomialFunction.Parametric> {
    @Deprecated
    private final int degree;

    @Deprecated
    public PolynomialFitter(int degree, DifferentiableMultivariateVectorOptimizer optimizer) {
        super(optimizer);
        this.degree = degree;
    }

    public PolynomialFitter(DifferentiableMultivariateVectorOptimizer optimizer) {
        super(optimizer);
        this.degree = -1;
    }

    @Deprecated
    public double[] fit() {
        return this.fit(new PolynomialFunction.Parametric(), new double[this.degree + 1]);
    }

    @Override
    public double[] fit(int maxEval, double[] guess) {
        return this.fit(maxEval, new PolynomialFunction.Parametric(), guess);
    }

    public double[] fit(double[] guess) {
        return this.fit(new PolynomialFunction.Parametric(), guess);
    }
}

