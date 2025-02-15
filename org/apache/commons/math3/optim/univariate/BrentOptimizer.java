/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.univariate.UnivariateOptimizer;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BrentOptimizer
extends UnivariateOptimizer {
    private static final double GOLDEN_SECTION = 0.5 * (3.0 - FastMath.sqrt(5.0));
    private static final double MIN_RELATIVE_TOLERANCE = 2.0 * FastMath.ulp(1.0);
    private final double relativeThreshold;
    private final double absoluteThreshold;

    public BrentOptimizer(double rel, double abs, ConvergenceChecker<UnivariatePointValuePair> checker) {
        super(checker);
        if (rel < MIN_RELATIVE_TOLERANCE) {
            throw new NumberIsTooSmallException(rel, (Number)MIN_RELATIVE_TOLERANCE, true);
        }
        if (abs <= 0.0) {
            throw new NotStrictlyPositiveException(abs);
        }
        this.relativeThreshold = rel;
        this.absoluteThreshold = abs;
    }

    public BrentOptimizer(double rel, double abs) {
        this(rel, abs, null);
    }

    @Override
    protected UnivariatePointValuePair doOptimize() {
        UnivariatePointValuePair current;
        double x;
        double b;
        double a;
        boolean isMinim = this.getGoalType() == GoalType.MINIMIZE;
        double lo = this.getMin();
        double mid = this.getStartValue();
        double hi = this.getMax();
        ConvergenceChecker<UnivariatePointValuePair> checker = this.getConvergenceChecker();
        if (lo < hi) {
            a = lo;
            b = hi;
        } else {
            a = hi;
            b = lo;
        }
        double v = x = mid;
        double w = x;
        double d = 0.0;
        double e = 0.0;
        double fx = this.computeObjectiveValue(x);
        if (!isMinim) {
            fx = -fx;
        }
        double fv = fx;
        double fw = fx;
        UnivariatePointValuePair previous = null;
        UnivariatePointValuePair best = current = new UnivariatePointValuePair(x, isMinim ? fx : -fx);
        while (true) {
            boolean stop;
            double m = 0.5 * (a + b);
            double tol1 = this.relativeThreshold * FastMath.abs(x) + this.absoluteThreshold;
            double tol2 = 2.0 * tol1;
            boolean bl = stop = FastMath.abs(x - m) <= tol2 - 0.5 * (b - a);
            if (!stop) {
                double p = 0.0;
                double q = 0.0;
                double r = 0.0;
                double u = 0.0;
                if (FastMath.abs(e) > tol1) {
                    r = (x - w) * (fx - fv);
                    q = (x - v) * (fx - fw);
                    p = (x - v) * q - (x - w) * r;
                    if ((q = 2.0 * (q - r)) > 0.0) {
                        p = -p;
                    } else {
                        q = -q;
                    }
                    r = e;
                    e = d;
                    if (p > q * (a - x) && p < q * (b - x) && FastMath.abs(p) < FastMath.abs(0.5 * q * r)) {
                        d = p / q;
                        u = x + d;
                        if (u - a < tol2 || b - u < tol2) {
                            d = x <= m ? tol1 : -tol1;
                        }
                    } else {
                        e = x < m ? b - x : a - x;
                        d = GOLDEN_SECTION * e;
                    }
                } else {
                    e = x < m ? b - x : a - x;
                    d = GOLDEN_SECTION * e;
                }
                u = FastMath.abs(d) < tol1 ? (d >= 0.0 ? x + tol1 : x - tol1) : x + d;
                double fu = this.computeObjectiveValue(u);
                if (!isMinim) {
                    fu = -fu;
                }
                previous = current;
                current = new UnivariatePointValuePair(u, isMinim ? fu : -fu);
                best = this.best(best, this.best(previous, current, isMinim), isMinim);
                if (checker != null && checker.converged(this.getIterations(), previous, current)) {
                    return best;
                }
                if (fu <= fx) {
                    if (u < x) {
                        b = x;
                    } else {
                        a = x;
                    }
                    v = w;
                    fv = fw;
                    w = x;
                    fw = fx;
                    x = u;
                    fx = fu;
                } else {
                    if (u < x) {
                        a = u;
                    } else {
                        b = u;
                    }
                    if (fu <= fw || Precision.equals(w, x)) {
                        v = w;
                        fv = fw;
                        w = u;
                        fw = fu;
                    } else if (fu <= fv || Precision.equals(v, x) || Precision.equals(v, w)) {
                        v = u;
                        fv = fu;
                    }
                }
            } else {
                return this.best(best, this.best(previous, current, isMinim), isMinim);
            }
            this.incrementIterationCount();
        }
    }

    private UnivariatePointValuePair best(UnivariatePointValuePair a, UnivariatePointValuePair b, boolean isMinim) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (isMinim) {
            return a.getValue() <= b.getValue() ? a : b;
        }
        return a.getValue() >= b.getValue() ? a : b;
    }
}

