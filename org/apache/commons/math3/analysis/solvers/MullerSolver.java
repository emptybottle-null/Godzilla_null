/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.solvers.AbstractUnivariateSolver;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class MullerSolver
extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6;

    public MullerSolver() {
        this(1.0E-6);
    }

    public MullerSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    public MullerSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    protected double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double min = this.getMin();
        double max = this.getMax();
        double initial = this.getStartValue();
        double functionValueAccuracy = this.getFunctionValueAccuracy();
        this.verifySequence(min, initial, max);
        double fMin = this.computeObjectiveValue(min);
        if (FastMath.abs(fMin) < functionValueAccuracy) {
            return min;
        }
        double fMax = this.computeObjectiveValue(max);
        if (FastMath.abs(fMax) < functionValueAccuracy) {
            return max;
        }
        double fInitial = this.computeObjectiveValue(initial);
        if (FastMath.abs(fInitial) < functionValueAccuracy) {
            return initial;
        }
        this.verifyBracketing(min, max);
        if (this.isBracketing(min, initial)) {
            return this.solve(min, initial, fMin, fInitial);
        }
        return this.solve(initial, max, fInitial, fMax);
    }

    private double solve(double min, double max, double fMin, double fMax) throws TooManyEvaluationsException {
        double relativeAccuracy = this.getRelativeAccuracy();
        double absoluteAccuracy = this.getAbsoluteAccuracy();
        double functionValueAccuracy = this.getFunctionValueAccuracy();
        double x0 = min;
        double y0 = fMin;
        double x2 = max;
        double y2 = fMax;
        double x1 = 0.5 * (x0 + x2);
        double y1 = this.computeObjectiveValue(x1);
        double oldx = Double.POSITIVE_INFINITY;
        while (true) {
            boolean bisect;
            double d01 = (y1 - y0) / (x1 - x0);
            double d12 = (y2 - y1) / (x2 - x1);
            double d012 = (d12 - d01) / (x2 - x0);
            double c1 = d01 + (x1 - x0) * d012;
            double delta = c1 * c1 - 4.0 * y1 * d012;
            double xplus = x1 + -2.0 * y1 / (c1 + FastMath.sqrt(delta));
            double xminus = x1 + -2.0 * y1 / (c1 - FastMath.sqrt(delta));
            double x = this.isSequence(x0, xplus, x2) ? xplus : xminus;
            double y = this.computeObjectiveValue(x);
            double tolerance = FastMath.max(relativeAccuracy * FastMath.abs(x), absoluteAccuracy);
            if (FastMath.abs(x - oldx) <= tolerance || FastMath.abs(y) <= functionValueAccuracy) {
                return x;
            }
            boolean bl = bisect = x < x1 && x1 - x0 > 0.95 * (x2 - x0) || x > x1 && x2 - x1 > 0.95 * (x2 - x0) || x == x1;
            if (!bisect) {
                x0 = x < x1 ? x0 : x1;
                y0 = x < x1 ? y0 : y1;
                x2 = x > x1 ? x2 : x1;
                y2 = x > x1 ? y2 : y1;
                x1 = x;
                y1 = y;
                oldx = x;
                continue;
            }
            double xm = 0.5 * (x0 + x2);
            double ym = this.computeObjectiveValue(xm);
            if (FastMath.signum(y0) + FastMath.signum(ym) == 0.0) {
                x2 = xm;
                y2 = ym;
            } else {
                x0 = xm;
                y0 = ym;
            }
            x1 = 0.5 * (x0 + x2);
            y1 = this.computeObjectiveValue(x1);
            oldx = Double.POSITIVE_INFINITY;
        }
    }
}

