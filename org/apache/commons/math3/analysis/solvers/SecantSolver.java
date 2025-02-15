/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.solvers.AbstractUnivariateSolver;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class SecantSolver
extends AbstractUnivariateSolver {
    protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6;

    public SecantSolver() {
        super(1.0E-6);
    }

    public SecantSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    public SecantSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    protected final double doSolve() throws TooManyEvaluationsException, NoBracketingException {
        double x0 = this.getMin();
        double x1 = this.getMax();
        double f0 = this.computeObjectiveValue(x0);
        double f1 = this.computeObjectiveValue(x1);
        if (f0 == 0.0) {
            return x0;
        }
        if (f1 == 0.0) {
            return x1;
        }
        this.verifyBracketing(x0, x1);
        double ftol = this.getFunctionValueAccuracy();
        double atol = this.getAbsoluteAccuracy();
        double rtol = this.getRelativeAccuracy();
        do {
            double x;
            double fx;
            if ((fx = this.computeObjectiveValue(x = x1 - f1 * (x1 - x0) / (f1 - f0))) == 0.0) {
                return x;
            }
            x0 = x1;
            f0 = f1;
            x1 = x;
            f1 = fx;
            if (!(FastMath.abs(f1) <= ftol)) continue;
            return x1;
        } while (!(FastMath.abs(x1 - x0) < FastMath.max(rtol * FastMath.abs(x1), atol)));
        return x1;
    }
}

