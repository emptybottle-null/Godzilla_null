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
import org.apache.commons.math3.util.FastMath;

public class LaplaceDistribution
extends AbstractRealDistribution {
    private static final long serialVersionUID = 20141003L;
    private final double mu;
    private final double beta;

    public LaplaceDistribution(double mu, double beta) {
        this(new Well19937c(), mu, beta);
    }

    public LaplaceDistribution(RandomGenerator rng, double mu, double beta) {
        super(rng);
        if (beta <= 0.0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.NOT_POSITIVE_SCALE, beta);
        }
        this.mu = mu;
        this.beta = beta;
    }

    public double getLocation() {
        return this.mu;
    }

    public double getScale() {
        return this.beta;
    }

    public double density(double x) {
        return FastMath.exp(-FastMath.abs(x - this.mu) / this.beta) / (2.0 * this.beta);
    }

    public double cumulativeProbability(double x) {
        if (x <= this.mu) {
            return FastMath.exp((x - this.mu) / this.beta) / 2.0;
        }
        return 1.0 - FastMath.exp((this.mu - x) / this.beta) / 2.0;
    }

    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0.0, 1.0);
        }
        if (p == 0.0) {
            return Double.NEGATIVE_INFINITY;
        }
        if (p == 1.0) {
            return Double.POSITIVE_INFINITY;
        }
        double x = p > 0.5 ? -Math.log(2.0 - 2.0 * p) : Math.log(2.0 * p);
        return this.mu + this.beta * x;
    }

    public double getNumericalMean() {
        return this.mu;
    }

    public double getNumericalVariance() {
        return 2.0 * this.beta * this.beta;
    }

    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
    }

    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    public boolean isSupportLowerBoundInclusive() {
        return false;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public boolean isSupportConnected() {
        return true;
    }
}

