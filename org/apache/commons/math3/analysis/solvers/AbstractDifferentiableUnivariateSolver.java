/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.DifferentiableUnivariateSolver;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public abstract class AbstractDifferentiableUnivariateSolver
extends BaseAbstractUnivariateSolver<DifferentiableUnivariateFunction>
implements DifferentiableUnivariateSolver {
    private UnivariateFunction functionDerivative;

    protected AbstractDifferentiableUnivariateSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    protected AbstractDifferentiableUnivariateSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
    }

    protected double computeDerivativeObjectiveValue(double point) throws TooManyEvaluationsException {
        this.incrementEvaluationCount();
        return this.functionDerivative.value(point);
    }

    @Override
    protected void setup(int maxEval, DifferentiableUnivariateFunction f, double min, double max, double startValue) {
        super.setup(maxEval, f, min, max, startValue);
        this.functionDerivative = f.derivative();
    }
}

