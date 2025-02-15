/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.solvers.AbstractUnivariateSolver;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class BrentSolver
extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6;

    public BrentSolver() {
        this(1.0E-6);
    }

    public BrentSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    public BrentSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    public BrentSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
    }

    protected double doSolve() throws NoBracketingException, TooManyEvaluationsException, NumberIsTooLargeException {
        double min = this.getMin();
        double max = this.getMax();
        double initial = this.getStartValue();
        double functionValueAccuracy = this.getFunctionValueAccuracy();
        this.verifySequence(min, initial, max);
        double yInitial = this.computeObjectiveValue(initial);
        if (FastMath.abs(yInitial) <= functionValueAccuracy) {
            return initial;
        }
        double yMin = this.computeObjectiveValue(min);
        if (FastMath.abs(yMin) <= functionValueAccuracy) {
            return min;
        }
        if (yInitial * yMin < 0.0) {
            return this.brent(min, initial, yMin, yInitial);
        }
        double yMax = this.computeObjectiveValue(max);
        if (FastMath.abs(yMax) <= functionValueAccuracy) {
            return max;
        }
        if (yInitial * yMax < 0.0) {
            return this.brent(initial, max, yInitial, yMax);
        }
        throw new NoBracketingException(min, max, yMin, yMax);
    }

    private double brent(double lo, double hi, double fLo, double fHi) {
        double d;
        double a = lo;
        double fa = fLo;
        double b = hi;
        double fb = fHi;
        double c = a;
        double fc = fa;
        double e = d = b - a;
        double t = this.getAbsoluteAccuracy();
        double eps = this.getRelativeAccuracy();
        while (true) {
            if (FastMath.abs(fc) < FastMath.abs(fb)) {
                a = b;
                b = c;
                c = a;
                fa = fb;
                fb = fc;
                fc = fa;
            }
            double tol = 2.0 * eps * FastMath.abs(b) + t;
            double m = 0.5 * (c - b);
            if (FastMath.abs(m) <= tol || Precision.equals(fb, 0.0)) {
                return b;
            }
            if (FastMath.abs(e) < tol || FastMath.abs(fa) <= FastMath.abs(fb)) {
                e = d = m;
            } else {
                double q;
                double p;
                double s = fb / fa;
                if (a == c) {
                    p = 2.0 * m * s;
                    q = 1.0 - s;
                } else {
                    q = fa / fc;
                    double r = fb / fc;
                    p = s * (2.0 * m * q * (q - r) - (b - a) * (r - 1.0));
                    q = (q - 1.0) * (r - 1.0) * (s - 1.0);
                }
                if (p > 0.0) {
                    q = -q;
                } else {
                    p = -p;
                }
                s = e;
                e = d;
                if (p >= 1.5 * m * q - FastMath.abs(tol * q) || p >= FastMath.abs(0.5 * s * q)) {
                    e = d = m;
                } else {
                    d = p / q;
                }
            }
            a = b;
            fa = fb;
            b = FastMath.abs(d) > tol ? (b += d) : (m > 0.0 ? (b += tol) : (b -= tol));
            fb = this.computeObjectiveValue(b);
            if (!(fb > 0.0 && fc > 0.0) && (!(fb <= 0.0) || !(fc <= 0.0))) continue;
            c = a;
            fc = fa;
            e = d = b - a;
        }
    }
}

