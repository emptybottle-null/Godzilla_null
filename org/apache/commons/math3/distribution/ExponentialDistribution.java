/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.ResizableDoubleArray;

public class ExponentialDistribution
extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
    private static final long serialVersionUID = 2401296428283614780L;
    private static final double[] EXPONENTIAL_SA_QI;
    private final double mean;
    private final double logMean;
    private final double solverAbsoluteAccuracy;

    public ExponentialDistribution(double mean) {
        this(mean, 1.0E-9);
    }

    public ExponentialDistribution(double mean, double inverseCumAccuracy) {
        this(new Well19937c(), mean, inverseCumAccuracy);
    }

    public ExponentialDistribution(RandomGenerator rng, double mean) throws NotStrictlyPositiveException {
        this(rng, mean, 1.0E-9);
    }

    public ExponentialDistribution(RandomGenerator rng, double mean, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        if (mean <= 0.0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.MEAN, mean);
        }
        this.mean = mean;
        this.logMean = FastMath.log(mean);
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    public double getMean() {
        return this.mean;
    }

    public double density(double x) {
        double logDensity = this.logDensity(x);
        return logDensity == Double.NEGATIVE_INFINITY ? 0.0 : FastMath.exp(logDensity);
    }

    public double logDensity(double x) {
        if (x < 0.0) {
            return Double.NEGATIVE_INFINITY;
        }
        return -x / this.mean - this.logMean;
    }

    public double cumulativeProbability(double x) {
        double ret = x <= 0.0 ? 0.0 : 1.0 - FastMath.exp(-x / this.mean);
        return ret;
    }

    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0.0, 1.0);
        }
        double ret = p == 1.0 ? Double.POSITIVE_INFINITY : -this.mean * FastMath.log(1.0 - p);
        return ret;
    }

    public double sample() {
        double u2;
        double u;
        double a = 0.0;
        for (u = this.random.nextDouble(); u < 0.5; u *= 2.0) {
            a += EXPONENTIAL_SA_QI[0];
        }
        if ((u += u - 1.0) <= EXPONENTIAL_SA_QI[0]) {
            return this.mean * (a + u);
        }
        int i = 0;
        double umin = u2 = this.random.nextDouble();
        do {
            ++i;
            u2 = this.random.nextDouble();
            if (!(u2 < umin)) continue;
            umin = u2;
        } while (u > EXPONENTIAL_SA_QI[i]);
        return this.mean * (a + umin * EXPONENTIAL_SA_QI[0]);
    }

    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        return this.getMean();
    }

    public double getNumericalVariance() {
        double m = this.getMean();
        return m * m;
    }

    public double getSupportLowerBound() {
        return 0.0;
    }

    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public boolean isSupportConnected() {
        return true;
    }

    static {
        double LN2 = FastMath.log(2.0);
        double qi = 0.0;
        int i = 1;
        ResizableDoubleArray ra = new ResizableDoubleArray(20);
        while (qi < 1.0) {
            ra.addElement(qi += FastMath.pow(LN2, i) / (double)CombinatoricsUtils.factorial(i));
            ++i;
        }
        EXPONENTIAL_SA_QI = ra.getElements();
    }
}

