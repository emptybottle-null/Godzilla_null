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

public class LogisticDistribution
extends AbstractRealDistribution {
    private static final long serialVersionUID = 20141003L;
    private final double mu;
    private final double s;

    public LogisticDistribution(double mu, double s) {
        this(new Well19937c(), mu, s);
    }

    public LogisticDistribution(RandomGenerator rng, double mu, double s) {
        super(rng);
        if (s <= 0.0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.NOT_POSITIVE_SCALE, s);
        }
        this.mu = mu;
        this.s = s;
    }

    public double getLocation() {
        return this.mu;
    }

    public double getScale() {
        return this.s;
    }

    public double density(double x) {
        double z = (x - this.mu) / this.s;
        double v = FastMath.exp(-z);
        return 1.0 / this.s * v / ((1.0 + v) * (1.0 + v));
    }

    public double cumulativeProbability(double x) {
        double z = 1.0 / this.s * (x - this.mu);
        return 1.0 / (1.0 + FastMath.exp(-z));
    }

    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0.0, 1.0);
        }
        if (p == 0.0) {
            return 0.0;
        }
        if (p == 1.0) {
            return Double.POSITIVE_INFINITY;
        }
        return this.s * Math.log(p / (1.0 - p)) + this.mu;
    }

    public double getNumericalMean() {
        return this.mu;
    }

    public double getNumericalVariance() {
        return 3.289868133696453 * (1.0 / (this.s * this.s));
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

