/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;

public class PascalDistribution
extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 6751309484392813623L;
    private final int numberOfSuccesses;
    private final double probabilityOfSuccess;
    private final double logProbabilityOfSuccess;
    private final double log1mProbabilityOfSuccess;

    public PascalDistribution(int r, double p) throws NotStrictlyPositiveException, OutOfRangeException {
        this(new Well19937c(), r, p);
    }

    public PascalDistribution(RandomGenerator rng, int r, double p) throws NotStrictlyPositiveException, OutOfRangeException {
        super(rng);
        if (r <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.NUMBER_OF_SUCCESSES, r);
        }
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0, 1);
        }
        this.numberOfSuccesses = r;
        this.probabilityOfSuccess = p;
        this.logProbabilityOfSuccess = FastMath.log(p);
        this.log1mProbabilityOfSuccess = FastMath.log1p(-p);
    }

    public int getNumberOfSuccesses() {
        return this.numberOfSuccesses;
    }

    public double getProbabilityOfSuccess() {
        return this.probabilityOfSuccess;
    }

    public double probability(int x) {
        double ret = x < 0 ? 0.0 : CombinatoricsUtils.binomialCoefficientDouble(x + this.numberOfSuccesses - 1, this.numberOfSuccesses - 1) * FastMath.pow(this.probabilityOfSuccess, this.numberOfSuccesses) * FastMath.pow(1.0 - this.probabilityOfSuccess, x);
        return ret;
    }

    public double logProbability(int x) {
        double ret = x < 0 ? Double.NEGATIVE_INFINITY : CombinatoricsUtils.binomialCoefficientLog(x + this.numberOfSuccesses - 1, this.numberOfSuccesses - 1) + this.logProbabilityOfSuccess * (double)this.numberOfSuccesses + this.log1mProbabilityOfSuccess * (double)x;
        return ret;
    }

    public double cumulativeProbability(int x) {
        double ret = x < 0 ? 0.0 : Beta.regularizedBeta(this.probabilityOfSuccess, this.numberOfSuccesses, (double)x + 1.0);
        return ret;
    }

    public double getNumericalMean() {
        double p = this.getProbabilityOfSuccess();
        double r = this.getNumberOfSuccesses();
        return r * (1.0 - p) / p;
    }

    public double getNumericalVariance() {
        double p = this.getProbabilityOfSuccess();
        double r = this.getNumberOfSuccesses();
        return r * (1.0 - p) / (p * p);
    }

    public int getSupportLowerBound() {
        return 0;
    }

    public int getSupportUpperBound() {
        return Integer.MAX_VALUE;
    }

    public boolean isSupportConnected() {
        return true;
    }
}

