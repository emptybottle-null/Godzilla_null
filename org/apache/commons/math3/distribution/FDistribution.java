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
import org.apache.commons.math3.util.FastMath;

public class FDistribution
extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9;
    private static final long serialVersionUID = -8516354193418641566L;
    private final double numeratorDegreesOfFreedom;
    private final double denominatorDegreesOfFreedom;
    private final double solverAbsoluteAccuracy;
    private double numericalVariance = Double.NaN;
    private boolean numericalVarianceIsCalculated = false;

    public FDistribution(double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom) throws NotStrictlyPositiveException {
        this(numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, 1.0E-9);
    }

    public FDistribution(double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, inverseCumAccuracy);
    }

    public FDistribution(RandomGenerator rng, double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom) throws NotStrictlyPositiveException {
        this(rng, numeratorDegreesOfFreedom, denominatorDegreesOfFreedom, 1.0E-9);
    }

    public FDistribution(RandomGenerator rng, double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        if (numeratorDegreesOfFreedom <= 0.0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.DEGREES_OF_FREEDOM, numeratorDegreesOfFreedom);
        }
        if (denominatorDegreesOfFreedom <= 0.0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.DEGREES_OF_FREEDOM, denominatorDegreesOfFreedom);
        }
        this.numeratorDegreesOfFreedom = numeratorDegreesOfFreedom;
        this.denominatorDegreesOfFreedom = denominatorDegreesOfFreedom;
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    public double density(double x) {
        return FastMath.exp(this.logDensity(x));
    }

    public double logDensity(double x) {
        double nhalf = this.numeratorDegreesOfFreedom / 2.0;
        double mhalf = this.denominatorDegreesOfFreedom / 2.0;
        double logx = FastMath.log(x);
        double logn = FastMath.log(this.numeratorDegreesOfFreedom);
        double logm = FastMath.log(this.denominatorDegreesOfFreedom);
        double lognxm = FastMath.log(this.numeratorDegreesOfFreedom * x + this.denominatorDegreesOfFreedom);
        return nhalf * logn + nhalf * logx - logx + mhalf * logm - nhalf * lognxm - mhalf * lognxm - Beta.logBeta(nhalf, mhalf);
    }

    public double cumulativeProbability(double x) {
        double ret;
        if (x <= 0.0) {
            ret = 0.0;
        } else {
            double n = this.numeratorDegreesOfFreedom;
            double m = this.denominatorDegreesOfFreedom;
            ret = Beta.regularizedBeta(n * x / (m + n * x), 0.5 * n, 0.5 * m);
        }
        return ret;
    }

    public double getNumeratorDegreesOfFreedom() {
        return this.numeratorDegreesOfFreedom;
    }

    public double getDenominatorDegreesOfFreedom() {
        return this.denominatorDegreesOfFreedom;
    }

    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        double denominatorDF = this.getDenominatorDegreesOfFreedom();
        if (denominatorDF > 2.0) {
            return denominatorDF / (denominatorDF - 2.0);
        }
        return Double.NaN;
    }

    public double getNumericalVariance() {
        if (!this.numericalVarianceIsCalculated) {
            this.numericalVariance = this.calculateNumericalVariance();
            this.numericalVarianceIsCalculated = true;
        }
        return this.numericalVariance;
    }

    protected double calculateNumericalVariance() {
        double denominatorDF = this.getDenominatorDegreesOfFreedom();
        if (denominatorDF > 4.0) {
            double numeratorDF = this.getNumeratorDegreesOfFreedom();
            double denomDFMinusTwo = denominatorDF - 2.0;
            return 2.0 * (denominatorDF * denominatorDF) * (numeratorDF + denominatorDF - 2.0) / (numeratorDF * (denomDFMinusTwo * denomDFMinusTwo) * (denominatorDF - 4.0));
        }
        return Double.NaN;
    }

    public double getSupportLowerBound() {
        return 0.0;
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

