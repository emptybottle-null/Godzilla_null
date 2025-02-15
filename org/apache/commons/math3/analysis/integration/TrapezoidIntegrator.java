/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class TrapezoidIntegrator
extends BaseAbstractUnivariateIntegrator {
    public static final int TRAPEZOID_MAX_ITERATIONS_COUNT = 64;
    private double s;

    public TrapezoidIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        if (maximalIterationCount > 64) {
            throw new NumberIsTooLargeException(maximalIterationCount, (Number)64, false);
        }
    }

    public TrapezoidIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(minimalIterationCount, maximalIterationCount);
        if (maximalIterationCount > 64) {
            throw new NumberIsTooLargeException(maximalIterationCount, (Number)64, false);
        }
    }

    public TrapezoidIntegrator() {
        super(3, 64);
    }

    double stage(BaseAbstractUnivariateIntegrator baseIntegrator, int n) throws TooManyEvaluationsException {
        if (n == 0) {
            double max = baseIntegrator.getMax();
            double min = baseIntegrator.getMin();
            this.s = 0.5 * (max - min) * (baseIntegrator.computeObjectiveValue(min) + baseIntegrator.computeObjectiveValue(max));
            return this.s;
        }
        long np = 1L << n - 1;
        double sum = 0.0;
        double max = baseIntegrator.getMax();
        double min = baseIntegrator.getMin();
        double spacing = (max - min) / (double)np;
        double x = min + 0.5 * spacing;
        for (long i = 0L; i < np; ++i) {
            sum += baseIntegrator.computeObjectiveValue(x);
            x += spacing;
        }
        this.s = 0.5 * (this.s + sum * spacing);
        return this.s;
    }

    protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        double oldt = this.stage(this, 0);
        this.incrementCount();
        while (true) {
            double rLimit;
            double delta;
            int i = this.getIterations();
            double t = this.stage(this, i);
            if (i >= this.getMinimalIterationCount() && ((delta = FastMath.abs(t - oldt)) <= (rLimit = this.getRelativeAccuracy() * (FastMath.abs(oldt) + FastMath.abs(t)) * 0.5) || delta <= this.getAbsoluteAccuracy())) {
                return t;
            }
            oldt = t;
            this.incrementCount();
        }
    }
}

