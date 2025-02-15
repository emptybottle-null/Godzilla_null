/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class TDistribution
extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
    private static final long serialVersionUID = -5852615386664158222L;
    private final double degreesOfFreedom;
    private final double solverAbsoluteAccuracy;
    private final double factor;

    public TDistribution(double degreesOfFreedom) throws NotStrictlyPositiveException {
        this(degreesOfFreedom, 1.0E-9);
    }

    public TDistribution(double degreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), degreesOfFreedom, inverseCumAccuracy);
    }

    public TDistribution(RandomGenerator rng, double degreesOfFreedom) throws NotStrictlyPositiveException {
        this(rng, degreesOfFreedom, 1.0E-9);
    }

    public TDistribution(RandomGenerator rng, double degreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        if (degreesOfFreedom <= 0.0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.DEGREES_OF_FREEDOM, degreesOfFreedom);
        }
        this.degreesOfFreedom = degreesOfFreedom;
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
        double n = degreesOfFreedom;
        double nPlus1Over2 = (n + 1.0) / 2.0;
        this.factor = Gamma.logGamma(nPlus1Over2) - 0.5 * (FastMath.log(Math.PI) + FastMath.log(n)) - Gamma.logGamma(n / 2.0);
    }

    public double getDegreesOfFreedom() {
        return this.degreesOfFreedom;
    }

    public double density(double x) {
        return FastMath.exp(this.logDensity(x));
    }

    public double logDensity(double x) {
        double n = this.degreesOfFreedom;
        double nPlus1Over2 = (n + 1.0) / 2.0;
        return this.factor - nPlus1Over2 * FastMath.log(1.0 + x * x / n);
    }

    public double cumulativeProbability(double x) {
        double ret;
        if (x == 0.0) {
            ret = 0.5;
        } else {
            double t = Beta.regularizedBeta(this.degreesOfFreedom / (this.degreesOfFreedom + x * x), 0.5 * this.degreesOfFreedom, 0.5);
            ret = x < 0.0 ? 0.5 * t : 1.0 - 0.5 * t;
        }
        return ret;
    }

    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        double df = this.getDegreesOfFreedom();
        if (df > 1.0) {
            return 0.0;
        }
        return Double.NaN;
    }

    public double getNumericalVariance() {
        double df = this.getDegreesOfFreedom();
        if (df > 2.0) {
            return df / (df - 2.0);
        }
        if (df > 1.0 && df <= 2.0) {
            return Double.POSITIVE_INFINITY;
        }
        return Double.NaN;
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

