/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.solvers.AbstractUnivariateSolver;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class MullerSolver2
extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6;

    public MullerSolver2() {
        this(1.0E-6);
    }

    public MullerSolver2(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    public MullerSolver2(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    protected double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double min = this.getMin();
        double max = this.getMax();
        this.verifyInterval(min, max);
        double relativeAccuracy = this.getRelativeAccuracy();
        double absoluteAccuracy = this.getAbsoluteAccuracy();
        double functionValueAccuracy = this.getFunctionValueAccuracy();
        double x0 = min;
        double y0 = this.computeObjectiveValue(x0);
        if (FastMath.abs(y0) < functionValueAccuracy) {
            return x0;
        }
        double x1 = max;
        double y1 = this.computeObjectiveValue(x1);
        if (FastMath.abs(y1) < functionValueAccuracy) {
            return x1;
        }
        if (y0 * y1 > 0.0) {
            throw new NoBracketingException(x0, x1, y0, y1);
        }
        double x2 = 0.5 * (x0 + x1);
        double y2 = this.computeObjectiveValue(x2);
        double oldx = Double.POSITIVE_INFINITY;
        while (true) {
            double x;
            double denominator;
            double c;
            double a;
            double q;
            double b;
            double delta;
            if ((delta = (b = (2.0 * (q = (x2 - x1) / (x1 - x0)) + 1.0) * y2 - (1.0 + q) * (1.0 + q) * y1 + q * q * y0) * b - 4.0 * (a = q * (y2 - (1.0 + q) * y1 + q * y0)) * (c = (1.0 + q) * y2)) >= 0.0) {
                double dplus = b + FastMath.sqrt(delta);
                double dminus = b - FastMath.sqrt(delta);
                denominator = FastMath.abs(dplus) > FastMath.abs(dminus) ? dplus : dminus;
            } else {
                denominator = FastMath.sqrt(b * b - delta);
            }
            if (denominator != 0.0) {
                for (x = x2 - 2.0 * c * (x2 - x1) / denominator; x == x1 || x == x2; x += absoluteAccuracy) {
                }
            } else {
                x = min + FastMath.random() * (max - min);
                oldx = Double.POSITIVE_INFINITY;
            }
            double y = this.computeObjectiveValue(x);
            double tolerance = FastMath.max(relativeAccuracy * FastMath.abs(x), absoluteAccuracy);
            if (FastMath.abs(x - oldx) <= tolerance || FastMath.abs(y) <= functionValueAccuracy) {
                return x;
            }
            x0 = x1;
            y0 = y1;
            x1 = x2;
            y1 = y2;
            x2 = x;
            y2 = y;
            oldx = x;
        }
    }
}

