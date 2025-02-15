/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.solvers.AbstractUnivariateDifferentiableSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class NewtonRaphsonSolver
extends AbstractUnivariateDifferentiableSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6;

    public NewtonRaphsonSolver() {
        this(1.0E-6);
    }

    public NewtonRaphsonSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    public double solve(int maxEval, UnivariateDifferentiableFunction f, double min, double max) throws TooManyEvaluationsException {
        return super.solve(maxEval, f, UnivariateSolverUtils.midpoint(min, max));
    }

    protected double doSolve() throws TooManyEvaluationsException {
        double startValue = this.getStartValue();
        double absoluteAccuracy = this.getAbsoluteAccuracy();
        double x0 = startValue;
        while (true) {
            DerivativeStructure y0 = this.computeObjectiveValueAndDerivative(x0);
            int[] nArray = new int[]{1};
            double x1 = x0 - y0.getValue() / y0.getPartialDerivative(nArray);
            if (FastMath.abs(x1 - x0) <= absoluteAccuracy) {
                return x1;
            }
            x0 = x1;
        }
    }
}

