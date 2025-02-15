/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.distribution.SaddlePointExpansion;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.util.FastMath;

public class BinomialDistribution
extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 6751309484392813623L;
    private final int numberOfTrials;
    private final double probabilityOfSuccess;

    public BinomialDistribution(int trials, double p) {
        this(new Well19937c(), trials, p);
    }

    public BinomialDistribution(RandomGenerator rng, int trials, double p) {
        super(rng);
        if (trials < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.NUMBER_OF_TRIALS, trials);
        }
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0, 1);
        }
        this.probabilityOfSuccess = p;
        this.numberOfTrials = trials;
    }

    public int getNumberOfTrials() {
        return this.numberOfTrials;
    }

    public double getProbabilityOfSuccess() {
        return this.probabilityOfSuccess;
    }

    public double probability(int x) {
        double logProbability = this.logProbability(x);
        return logProbability == Double.NEGATIVE_INFINITY ? 0.0 : FastMath.exp(logProbability);
    }

    public double logProbability(int x) {
        if (this.numberOfTrials == 0) {
            return x == 0 ? 0.0 : Double.NEGATIVE_INFINITY;
        }
        double ret = x < 0 || x > this.numberOfTrials ? Double.NEGATIVE_INFINITY : SaddlePointExpansion.logBinomialProbability(x, this.numberOfTrials, this.probabilityOfSuccess, 1.0 - this.probabilityOfSuccess);
        return ret;
    }

    public double cumulativeProbability(int x) {
        double ret = x < 0 ? 0.0 : (x >= this.numberOfTrials ? 1.0 : 1.0 - Beta.regularizedBeta(this.probabilityOfSuccess, (double)x + 1.0, this.numberOfTrials - x));
        return ret;
    }

    public double getNumericalMean() {
        return (double)this.numberOfTrials * this.probabilityOfSuccess;
    }

    public double getNumericalVariance() {
        double p = this.probabilityOfSuccess;
        return (double)this.numberOfTrials * p * (1.0 - p);
    }

    public int getSupportLowerBound() {
        return this.probabilityOfSuccess < 1.0 ? 0 : this.numberOfTrials;
    }

    public int getSupportUpperBound() {
        return this.probabilityOfSuccess > 0.0 ? this.numberOfTrials : 0;
    }

    public boolean isSupportConnected() {
        return true;
    }
}

