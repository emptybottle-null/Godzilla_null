/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.distribution.SaddlePointExpansion;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class HypergeometricDistribution
extends AbstractIntegerDistribution {
    private static final long serialVersionUID = -436928820673516179L;
    private final int numberOfSuccesses;
    private final int populationSize;
    private final int sampleSize;
    private double numericalVariance = Double.NaN;
    private boolean numericalVarianceIsCalculated = false;

    public HypergeometricDistribution(int populationSize, int numberOfSuccesses, int sampleSize) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        this(new Well19937c(), populationSize, numberOfSuccesses, sampleSize);
    }

    public HypergeometricDistribution(RandomGenerator rng, int populationSize, int numberOfSuccesses, int sampleSize) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        super(rng);
        if (populationSize <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.POPULATION_SIZE, populationSize);
        }
        if (numberOfSuccesses < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.NUMBER_OF_SUCCESSES, numberOfSuccesses);
        }
        if (sampleSize < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.NUMBER_OF_SAMPLES, sampleSize);
        }
        if (numberOfSuccesses > populationSize) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.NUMBER_OF_SUCCESS_LARGER_THAN_POPULATION_SIZE, (Number)numberOfSuccesses, populationSize, true);
        }
        if (sampleSize > populationSize) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.SAMPLE_SIZE_LARGER_THAN_POPULATION_SIZE, (Number)sampleSize, populationSize, true);
        }
        this.numberOfSuccesses = numberOfSuccesses;
        this.populationSize = populationSize;
        this.sampleSize = sampleSize;
    }

    public double cumulativeProbability(int x) {
        int[] domain = this.getDomain(this.populationSize, this.numberOfSuccesses, this.sampleSize);
        double ret = x < domain[0] ? 0.0 : (x >= domain[1] ? 1.0 : this.innerCumulativeProbability(domain[0], x, 1));
        return ret;
    }

    private int[] getDomain(int n, int m, int k) {
        return new int[]{this.getLowerDomain(n, m, k), this.getUpperDomain(m, k)};
    }

    private int getLowerDomain(int n, int m, int k) {
        return FastMath.max(0, m - (n - k));
    }

    public int getNumberOfSuccesses() {
        return this.numberOfSuccesses;
    }

    public int getPopulationSize() {
        return this.populationSize;
    }

    public int getSampleSize() {
        return this.sampleSize;
    }

    private int getUpperDomain(int m, int k) {
        return FastMath.min(k, m);
    }

    public double probability(int x) {
        double logProbability = this.logProbability(x);
        return logProbability == Double.NEGATIVE_INFINITY ? 0.0 : FastMath.exp(logProbability);
    }

    public double logProbability(int x) {
        double ret;
        int[] domain = this.getDomain(this.populationSize, this.numberOfSuccesses, this.sampleSize);
        if (x < domain[0] || x > domain[1]) {
            ret = Double.NEGATIVE_INFINITY;
        } else {
            double p = (double)this.sampleSize / (double)this.populationSize;
            double q = (double)(this.populationSize - this.sampleSize) / (double)this.populationSize;
            double p1 = SaddlePointExpansion.logBinomialProbability(x, this.numberOfSuccesses, p, q);
            double p2 = SaddlePointExpansion.logBinomialProbability(this.sampleSize - x, this.populationSize - this.numberOfSuccesses, p, q);
            double p3 = SaddlePointExpansion.logBinomialProbability(this.sampleSize, this.populationSize, p, q);
            ret = p1 + p2 - p3;
        }
        return ret;
    }

    public double upperCumulativeProbability(int x) {
        int[] domain = this.getDomain(this.populationSize, this.numberOfSuccesses, this.sampleSize);
        double ret = x <= domain[0] ? 1.0 : (x > domain[1] ? 0.0 : this.innerCumulativeProbability(domain[1], x, -1));
        return ret;
    }

    private double innerCumulativeProbability(int x0, int x1, int dx) {
        double ret = this.probability(x0);
        while (x0 != x1) {
            ret += this.probability(x0 += dx);
        }
        return ret;
    }

    public double getNumericalMean() {
        return (double)this.getSampleSize() * ((double)this.getNumberOfSuccesses() / (double)this.getPopulationSize());
    }

    public double getNumericalVariance() {
        if (!this.numericalVarianceIsCalculated) {
            this.numericalVariance = this.calculateNumericalVariance();
            this.numericalVarianceIsCalculated = true;
        }
        return this.numericalVariance;
    }

    protected double calculateNumericalVariance() {
        double N = this.getPopulationSize();
        double m = this.getNumberOfSuccesses();
        double n = this.getSampleSize();
        return n * m * (N - n) * (N - m) / (N * N * (N - 1.0));
    }

    public int getSupportLowerBound() {
        return FastMath.max(0, this.getSampleSize() + this.getNumberOfSuccesses() - this.getPopulationSize());
    }

    public int getSupportUpperBound() {
        return FastMath.min(this.getNumberOfSuccesses(), this.getSampleSize());
    }

    public boolean isSupportConnected() {
        return true;
    }
}

