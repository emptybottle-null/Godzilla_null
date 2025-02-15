/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import java.io.Serializable;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractRealDistribution
implements RealDistribution,
Serializable {
    public static final double SOLVER_DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6;
    private static final long serialVersionUID = -38038050983108802L;
    @Deprecated
    protected RandomDataImpl randomData = new RandomDataImpl();
    protected final RandomGenerator random;
    private double solverAbsoluteAccuracy = 1.0E-6;

    @Deprecated
    protected AbstractRealDistribution() {
        this.random = null;
    }

    protected AbstractRealDistribution(RandomGenerator rng) {
        this.random = rng;
    }

    @Deprecated
    public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
        return this.probability(x0, x1);
    }

    public double probability(double x0, double x1) {
        if (x0 > x1) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, (Number)x0, x1, true);
        }
        return this.cumulativeProbability(x1) - this.cumulativeProbability(x0);
    }

    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        double dx;
        boolean chebyshevApplies;
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0, 1);
        }
        double lowerBound = this.getSupportLowerBound();
        if (p == 0.0) {
            return lowerBound;
        }
        double upperBound = this.getSupportUpperBound();
        if (p == 1.0) {
            return upperBound;
        }
        double mu = this.getNumericalMean();
        double sig = FastMath.sqrt(this.getNumericalVariance());
        boolean bl = chebyshevApplies = !Double.isInfinite(mu) && !Double.isNaN(mu) && !Double.isInfinite(sig) && !Double.isNaN(sig);
        if (lowerBound == Double.NEGATIVE_INFINITY) {
            if (chebyshevApplies) {
                lowerBound = mu - sig * FastMath.sqrt((1.0 - p) / p);
            } else {
                lowerBound = -1.0;
                while (this.cumulativeProbability(lowerBound) >= p) {
                    lowerBound *= 2.0;
                }
            }
        }
        if (upperBound == Double.POSITIVE_INFINITY) {
            if (chebyshevApplies) {
                upperBound = mu + sig * FastMath.sqrt(p / (1.0 - p));
            } else {
                upperBound = 1.0;
                while (this.cumulativeProbability(upperBound) < p) {
                    upperBound *= 2.0;
                }
            }
        }
        UnivariateFunction toSolve = new UnivariateFunction(){

            public double value(double x) {
                return AbstractRealDistribution.this.cumulativeProbability(x) - p;
            }
        };
        double x = UnivariateSolverUtils.solve(toSolve, lowerBound, upperBound, this.getSolverAbsoluteAccuracy());
        if (!this.isSupportConnected() && x - (dx = this.getSolverAbsoluteAccuracy()) >= this.getSupportLowerBound()) {
            double px = this.cumulativeProbability(x);
            if (this.cumulativeProbability(x - dx) == px) {
                upperBound = x;
                while (upperBound - lowerBound > dx) {
                    double midPoint = 0.5 * (lowerBound + upperBound);
                    if (this.cumulativeProbability(midPoint) < px) {
                        lowerBound = midPoint;
                        continue;
                    }
                    upperBound = midPoint;
                }
                return upperBound;
            }
        }
        return x;
    }

    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public void reseedRandomGenerator(long seed) {
        this.random.setSeed(seed);
        this.randomData.reSeed(seed);
    }

    public double sample() {
        return this.inverseCumulativeProbability(this.random.nextDouble());
    }

    public double[] sample(int sampleSize) {
        if (sampleSize <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.NUMBER_OF_SAMPLES, sampleSize);
        }
        double[] out = new double[sampleSize];
        for (int i = 0; i < sampleSize; ++i) {
            out[i] = this.sample();
        }
        return out;
    }

    public double probability(double x) {
        return 0.0;
    }

    public double logDensity(double x) {
        return FastMath.log(this.density(x));
    }
}

