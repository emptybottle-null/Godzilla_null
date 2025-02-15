/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;

public class LevyDistribution
extends AbstractRealDistribution {
    private static final long serialVersionUID = 20130314L;
    private final double mu;
    private final double c;
    private final double halfC;

    public LevyDistribution(double mu, double c) {
        this(new Well19937c(), mu, c);
    }

    public LevyDistribution(RandomGenerator rng, double mu, double c) {
        super(rng);
        this.mu = mu;
        this.c = c;
        this.halfC = 0.5 * c;
    }

    public double density(double x) {
        if (x < this.mu) {
            return Double.NaN;
        }
        double delta = x - this.mu;
        double f = this.halfC / delta;
        return FastMath.sqrt(f / Math.PI) * FastMath.exp(-f) / delta;
    }

    public double logDensity(double x) {
        if (x < this.mu) {
            return Double.NaN;
        }
        double delta = x - this.mu;
        double f = this.halfC / delta;
        return 0.5 * FastMath.log(f / Math.PI) - f - FastMath.log(delta);
    }

    public double cumulativeProbability(double x) {
        if (x < this.mu) {
            return Double.NaN;
        }
        return Erf.erfc(FastMath.sqrt(this.halfC / (x - this.mu)));
    }

    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0, 1);
        }
        double t = Erf.erfcInv(p);
        return this.mu + this.halfC / (t * t);
    }

    public double getScale() {
        return this.c;
    }

    public double getLocation() {
        return this.mu;
    }

    public double getNumericalMean() {
        return Double.POSITIVE_INFINITY;
    }

    public double getNumericalVariance() {
        return Double.POSITIVE_INFINITY;
    }

    public double getSupportLowerBound() {
        return this.mu;
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

