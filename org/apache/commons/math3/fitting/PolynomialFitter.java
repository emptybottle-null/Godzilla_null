/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.CurveFitter;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class PolynomialFitter
extends CurveFitter<PolynomialFunction.Parametric> {
    public PolynomialFitter(MultivariateVectorOptimizer optimizer) {
        super(optimizer);
    }

    @Override
    public double[] fit(int maxEval, double[] guess) {
        return this.fit(maxEval, new PolynomialFunction.Parametric(), guess);
    }

    public double[] fit(double[] guess) {
        return this.fit(new PolynomialFunction.Parametric(), guess);
    }
}

