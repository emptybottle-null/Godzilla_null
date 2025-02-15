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

public class CauchyDistribution
extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
    private static final long serialVersionUID = 8589540077390120676L;
    private final double median;
    private final double scale;
    private final double solverAbsoluteAccuracy;

    public CauchyDistribution() {
        this(0.0, 1.0);
    }

    public CauchyDistribution(double median, double scale) {
        this(median, scale, 1.0E-9);
    }

    public CauchyDistribution(double median, double scale, double inverseCumAccuracy) {
        this(new Well19937c(), median, scale, inverseCumAccuracy);
    }

    public CauchyDistribution(RandomGenerator rng, double median, double scale) {
        this(rng, median, scale, 1.0E-9);
    }

    public CauchyDistribution(RandomGenerator rng, double median, double scale, double inverseCumAccuracy) {
        super(rng);
        if (scale <= 0.0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.SCALE, scale);
        }
        this.scale = scale;
        this.median = median;
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    public double cumulativeProbability(double x) {
        return 0.5 + FastMath.atan((x - this.median) / this.scale) / Math.PI;
    }

    public double getMedian() {
        return this.median;
    }

    public double getScale() {
        return this.scale;
    }

    public double density(double x) {
        double dev = x - this.median;
        return 0.3183098861837907 * (this.scale / (dev * dev + this.scale * this.scale));
    }

    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, (Number)0, 1);
        }
        double ret = p == 0.0 ? Double.NEGATIVE_INFINITY : (p == 1.0 ? Double.POSITIVE_INFINITY : this.median + this.scale * FastMath.tan(Math.PI * (p - 0.5)));
        return ret;
    }

    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        return Double.NaN;
    }

    public double getNumericalVariance() {
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

