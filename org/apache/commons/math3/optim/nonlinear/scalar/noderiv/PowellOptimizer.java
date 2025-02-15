/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PowellOptimizer
extends MultivariateOptimizer {
    private static final double MIN_RELATIVE_TOLERANCE = 2.0 * FastMath.ulp(1.0);
    private final double relativeThreshold;
    private final double absoluteThreshold;
    private final LineSearch line;

    public PowellOptimizer(double rel, double abs, ConvergenceChecker<PointValuePair> checker) {
        this(rel, abs, FastMath.sqrt(rel), FastMath.sqrt(abs), checker);
    }

    public PowellOptimizer(double rel, double abs, double lineRel, double lineAbs, ConvergenceChecker<PointValuePair> checker) {
        super(checker);
        if (rel < MIN_RELATIVE_TOLERANCE) {
            throw new NumberIsTooSmallException(rel, (Number)MIN_RELATIVE_TOLERANCE, true);
        }
        if (abs <= 0.0) {
            throw new NotStrictlyPositiveException(abs);
        }
        this.relativeThreshold = rel;
        this.absoluteThreshold = abs;
        this.line = new LineSearch(this, lineRel, lineAbs, 1.0);
    }

    public PowellOptimizer(double rel, double abs) {
        this(rel, abs, null);
    }

    public PowellOptimizer(double rel, double abs, double lineRel, double lineAbs) {
        this(rel, abs, lineRel, lineAbs, null);
    }

    @Override
    protected PointValuePair doOptimize() {
        this.checkParameters();
        GoalType goal = this.getGoalType();
        double[] guess = this.getStartPoint();
        int n = guess.length;
        double[][] direc = new double[n][n];
        for (int i = 0; i < n; ++i) {
            direc[i][i] = 1.0;
        }
        ConvergenceChecker<PointValuePair> checker = this.getConvergenceChecker();
        double[] x = guess;
        double fVal = this.computeObjectiveValue(x);
        double[] x1 = (double[])x.clone();
        while (true) {
            this.incrementIterationCount();
            double fX = fVal;
            double fX2 = 0.0;
            double delta = 0.0;
            int bigInd = 0;
            double alphaMin = 0.0;
            for (int i = 0; i < n; ++i) {
                double[] d = MathArrays.copyOf(direc[i]);
                fX2 = fVal;
                UnivariatePointValuePair optimum = this.line.search(x, d);
                fVal = optimum.getValue();
                alphaMin = optimum.getPoint();
                double[][] result = this.newPointAndDirection(x, d, alphaMin);
                x = result[0];
                if (!(fX2 - fVal > delta)) continue;
                delta = fX2 - fVal;
                bigInd = i;
            }
            boolean stop = 2.0 * (fX - fVal) <= this.relativeThreshold * (FastMath.abs(fX) + FastMath.abs(fVal)) + this.absoluteThreshold;
            PointValuePair previous = new PointValuePair(x1, fX);
            PointValuePair current = new PointValuePair(x, fVal);
            if (!stop && checker != null) {
                stop = checker.converged(this.getIterations(), previous, current);
            }
            if (stop) {
                if (goal == GoalType.MINIMIZE) {
                    return fVal < fX ? current : previous;
                }
                return fVal > fX ? current : previous;
            }
            double[] d = new double[n];
            double[] x2 = new double[n];
            for (int i = 0; i < n; ++i) {
                d[i] = x[i] - x1[i];
                x2[i] = 2.0 * x[i] - x1[i];
            }
            x1 = (double[])x.clone();
            fX2 = this.computeObjectiveValue(x2);
            if (!(fX > fX2)) continue;
            double t = 2.0 * (fX + fX2 - 2.0 * fVal);
            double temp = fX - fVal - delta;
            t *= temp * temp;
            temp = fX - fX2;
            if (!((t -= delta * temp * temp) < 0.0)) continue;
            UnivariatePointValuePair optimum = this.line.search(x, d);
            fVal = optimum.getValue();
            alphaMin = optimum.getPoint();
            double[][] result = this.newPointAndDirection(x, d, alphaMin);
            x = result[0];
            int lastInd = n - 1;
            direc[bigInd] = direc[lastInd];
            direc[lastInd] = result[1];
        }
    }

    private double[][] newPointAndDirection(double[] p, double[] d, double optimum) {
        int n = p.length;
        double[] nP = new double[n];
        double[] nD = new double[n];
        for (int i = 0; i < n; ++i) {
            nD[i] = d[i] * optimum;
            nP[i] = p[i] + nD[i];
        }
        double[][] result = new double[][]{nP, nD};
        return result;
    }

    private void checkParameters() {
        if (this.getLowerBound() != null || this.getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}

