/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator;
import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class SimpsonIntegrator
extends BaseAbstractUnivariateIntegrator {
    public static final int SIMPSON_MAX_ITERATIONS_COUNT = 64;

    public SimpsonIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        if (maximalIterationCount > 64) {
            throw new NumberIsTooLargeException(maximalIterationCount, (Number)64, false);
        }
    }

    public SimpsonIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(minimalIterationCount, maximalIterationCount);
        if (maximalIterationCount > 64) {
            throw new NumberIsTooLargeException(maximalIterationCount, (Number)64, false);
        }
    }

    public SimpsonIntegrator() {
        super(3, 64);
    }

    protected double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
        TrapezoidIntegrator qtrap = new TrapezoidIntegrator();
        if (this.getMinimalIterationCount() == 1) {
            return (4.0 * qtrap.stage(this, 1) - qtrap.stage(this, 0)) / 3.0;
        }
        double olds = 0.0;
        double oldt = qtrap.stage(this, 0);
        while (true) {
            double rLimit;
            double delta;
            double t = qtrap.stage(this, this.getIterations());
            this.incrementCount();
            double s = (4.0 * t - oldt) / 3.0;
            if (this.getIterations() >= this.getMinimalIterationCount() && ((delta = FastMath.abs(s - olds)) <= (rLimit = this.getRelativeAccuracy() * (FastMath.abs(olds) + FastMath.abs(s)) * 0.5) || delta <= this.getAbsoluteAccuracy())) {
                return s;
            }
            olds = s;
            oldt = t;
        }
    }
}

