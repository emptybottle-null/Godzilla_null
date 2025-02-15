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

public class MidPointIntegrator
extends BaseAbstractUnivariateIntegrator {
    public static final int MIDPOINT_MAX_ITERATIONS_COUNT = 64;

    public MidPointIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        if (maximalIterationCount > 64) {
            throw new NumberIsTooLargeException(maximalIterationCount, (Number)64, false);
        }
    }

    public MidPointIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(minimalIterationCount, maximalIterationCount);
        if (maximalIterationCount > 64) {
            throw new NumberIsTooLargeException(maximalIterationCount, (Number)64, false);
        }
    }

    public MidPointIntegrator() {
        super(3, 64);
    }

    private double stage(int n, double previousStageResult, double min, double diffMaxMin) throws TooManyEvaluationsException {
        long np = 1L << n - 1;
        double sum = 0.0;
        double spacing = diffMaxMin / (double)np;
        double x = min + 0.5 * spacing;
        for (long i = 0L; i < np; ++i) {
            sum += this.computeObjectiveValue(x);
            x += spacing;
        }
        return 0.5 * (previousStageResult + sum * spacing);
    }

    protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        double min = this.getMin();
        double diff = this.getMax() - min;
        double midPoint = min + 0.5 * diff;
        double oldt = diff * this.computeObjectiveValue(midPoint);
        while (true) {
            double rLimit;
            double delta;
            this.incrementCount();
            int i = this.getIterations();
            double t = this.stage(i, oldt, min, diff);
            if (i >= this.getMinimalIterationCount() && ((delta = FastMath.abs(t - oldt)) <= (rLimit = this.getRelativeAccuracy() * (FastMath.abs(oldt) + FastMath.abs(t)) * 0.5) || delta <= this.getAbsoluteAccuracy())) {
                return t;
            }
            oldt = t;
        }
    }
}

