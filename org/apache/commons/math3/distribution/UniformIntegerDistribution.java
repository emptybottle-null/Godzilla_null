/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

public class UniformIntegerDistribution
extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 20120109L;
    private final int lower;
    private final int upper;

    public UniformIntegerDistribution(int lower, int upper) throws NumberIsTooLargeException {
        this(new Well19937c(), lower, upper);
    }

    public UniformIntegerDistribution(RandomGenerator rng, int lower, int upper) throws NumberIsTooLargeException {
        super(rng);
        if (lower > upper) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, (Number)lower, upper, true);
        }
        this.lower = lower;
        this.upper = upper;
    }

    public double probability(int x) {
        if (x < this.lower || x > this.upper) {
            return 0.0;
        }
        return 1.0 / (double)(this.upper - this.lower + 1);
    }

    public double cumulativeProbability(int x) {
        if (x < this.lower) {
            return 0.0;
        }
        if (x > this.upper) {
            return 1.0;
        }
        return ((double)(x - this.lower) + 1.0) / ((double)(this.upper - this.lower) + 1.0);
    }

    public double getNumericalMean() {
        return 0.5 * (double)(this.lower + this.upper);
    }

    public double getNumericalVariance() {
        double n = this.upper - this.lower + 1;
        return (n * n - 1.0) / 12.0;
    }

    public int getSupportLowerBound() {
        return this.lower;
    }

    public int getSupportUpperBound() {
        return this.upper;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public int sample() {
        int max = this.upper - this.lower + 1;
        if (max <= 0) {
            int r;
            while ((r = this.random.nextInt()) < this.lower || r > this.upper) {
            }
            return r;
        }
        return this.lower + this.random.nextInt(max);
    }
}

