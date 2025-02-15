/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.PolynomialSolver;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractPolynomialSolver
extends BaseAbstractUnivariateSolver<PolynomialFunction>
implements PolynomialSolver {
    private PolynomialFunction polynomialFunction;

    protected AbstractPolynomialSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    protected AbstractPolynomialSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    protected AbstractPolynomialSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
    }

    @Override
    protected void setup(int maxEval, PolynomialFunction f, double min, double max, double startValue) {
        super.setup(maxEval, f, min, max, startValue);
        this.polynomialFunction = f;
    }

    protected double[] getCoefficients() {
        return this.polynomialFunction.getCoefficients();
    }
}

