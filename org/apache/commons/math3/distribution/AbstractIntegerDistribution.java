/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import java.io.Serializable;
import org.apache.commons.math3.distribution.IntegerDistribution;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractIntegerDistribution
implements IntegerDistribution,
Serializable {
    private static final long serialVersionUID = -1146319659338487221L;
    @Deprecated
    protected final RandomDataImpl randomData = new RandomDataImpl();
    protected final RandomGenerator random;

    @Deprecated
    protected AbstractIntegerDistribution() {
        this.random = null;
    }

    protected AbstractIntegerDistribution(RandomGenerator rng) {
        this.random = rng;
    }

    public double cumulativeProbability(int x0, int x1) throws NumberIsTooLargeException {
        if (x1 < x0) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, (Number)x0, x1, true);
        }
        return this.cumulativeProbability(x1) - this.cumulativeProbability(x0);
    }

    public int inverseCumulativeProbability(double p) throws OutOfRangeException {
        boolean chebyshevApplies;
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0, 1);
        }
        int lower = this.getSupportLowerBound();
        if (p == 0.0) {
            return lower;
        }
        if (lower == Integer.MIN_VALUE) {
            if (this.checkedCumulativeProbability(lower) >= p) {
                return lower;
            }
        } else {
            --lower;
        }
        int upper = this.getSupportUpperBound();
        if (p == 1.0) {
            return upper;
        }
        double mu = this.getNumericalMean();
        double sigma = FastMath.sqrt(this.getNumericalVariance());
        boolean bl = chebyshevApplies = !Double.isInfinite(mu) && !Double.isNaN(mu) && !Double.isInfinite(sigma) && !Double.isNaN(sigma) && sigma != 0.0;
        if (chebyshevApplies) {
            double k = FastMath.sqrt((1.0 - p) / p);
            double tmp = mu - k * sigma;
            if (tmp > (double)lower) {
                lower = (int)FastMath.ceil(tmp) - 1;
            }
            if ((tmp = mu + (k = 1.0 / k) * sigma) < (double)upper) {
                upper = (int)FastMath.ceil(tmp) - 1;
            }
        }
        return this.solveInverseCumulativeProbability(p, lower, upper);
    }

    protected int solveInverseCumulativeProbability(double p, int lower, int upper) {
        while (lower + 1 < upper) {
            double pm;
            int xm = (lower + upper) / 2;
            if (xm < lower || xm > upper) {
                xm = lower + (upper - lower) / 2;
            }
            if ((pm = this.checkedCumulativeProbability(xm)) >= p) {
                upper = xm;
                continue;
            }
            lower = xm;
        }
        return upper;
    }

    public void reseedRandomGenerator(long seed) {
        this.random.setSeed(seed);
        this.randomData.reSeed(seed);
    }

    public int sample() {
        return this.inverseCumulativeProbability(this.random.nextDouble());
    }

    public int[] sample(int sampleSize) {
        if (sampleSize <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.NUMBER_OF_SAMPLES, sampleSize);
        }
        int[] out = new int[sampleSize];
        for (int i = 0; i < sampleSize; ++i) {
            out[i] = this.sample();
        }
        return out;
    }

    private double checkedCumulativeProbability(int argument) throws MathInternalError {
        double result = Double.NaN;
        result = this.cumulativeProbability(argument);
        if (Double.isNaN(result)) {
            throw new MathInternalError(LocalizedFormats.DISCRETE_CUMULATIVE_PROBABILITY_RETURNED_NAN, argument);
        }
        return result;
    }

    public double logProbability(int x) {
        return FastMath.log(this.probability(x));
    }
}

