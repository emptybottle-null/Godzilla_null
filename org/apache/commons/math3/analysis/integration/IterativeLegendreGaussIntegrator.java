/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator;
import org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator;
import org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class IterativeLegendreGaussIntegrator
extends BaseAbstractUnivariateIntegrator {
    private static final GaussIntegratorFactory FACTORY = new GaussIntegratorFactory();
    private final int numberOfPoints;

    public IterativeLegendreGaussIntegrator(int n, double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        if (n <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.NUMBER_OF_POINTS, n);
        }
        this.numberOfPoints = n;
    }

    public IterativeLegendreGaussIntegrator(int n, double relativeAccuracy, double absoluteAccuracy) throws NotStrictlyPositiveException {
        this(n, relativeAccuracy, absoluteAccuracy, 3, Integer.MAX_VALUE);
    }

    public IterativeLegendreGaussIntegrator(int n, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException {
        this(n, 1.0E-6, 1.0E-15, minimalIterationCount, maximalIterationCount);
    }

    protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        double oldt = this.stage(1);
        int n = 2;
        while (true) {
            double t = this.stage(n);
            double delta = FastMath.abs(t - oldt);
            double limit = FastMath.max(this.getAbsoluteAccuracy(), this.getRelativeAccuracy() * (FastMath.abs(oldt) + FastMath.abs(t)) * 0.5);
            if (this.getIterations() + 1 >= this.getMinimalIterationCount() && delta <= limit) {
                return t;
            }
            double ratio = FastMath.min(4.0, FastMath.pow(delta / limit, 0.5 / (double)this.numberOfPoints));
            n = FastMath.max((int)(ratio * (double)n), n + 1);
            oldt = t;
            this.incrementCount();
        }
    }

    private double stage(int n) throws TooManyEvaluationsException {
        UnivariateFunction f = new UnivariateFunction(){

            public double value(double x) throws MathIllegalArgumentException, TooManyEvaluationsException {
                return IterativeLegendreGaussIntegrator.this.computeObjectiveValue(x);
            }
        };
        double min = this.getMin();
        double max = this.getMax();
        double step = (max - min) / (double)n;
        double sum = 0.0;
        for (int i = 0; i < n; ++i) {
            double a = min + (double)i * step;
            double b = a + step;
            GaussIntegrator g = FACTORY.legendreHighPrecision(this.numberOfPoints, a, b);
            sum += g.integrate(f);
        }
        return sum;
    }
}

