/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class ZipfDistribution
extends AbstractIntegerDistribution {
    private static final long serialVersionUID = -140627372283420404L;
    private final int numberOfElements;
    private final double exponent;
    private double numericalMean = Double.NaN;
    private boolean numericalMeanIsCalculated = false;
    private double numericalVariance = Double.NaN;
    private boolean numericalVarianceIsCalculated = false;
    private transient ZipfRejectionInversionSampler sampler;

    public ZipfDistribution(int numberOfElements, double exponent) {
        this(new Well19937c(), numberOfElements, exponent);
    }

    public ZipfDistribution(RandomGenerator rng, int numberOfElements, double exponent) throws NotStrictlyPositiveException {
        super(rng);
        if (numberOfElements <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.DIMENSION, numberOfElements);
        }
        if (exponent <= 0.0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.EXPONENT, exponent);
        }
        this.numberOfElements = numberOfElements;
        this.exponent = exponent;
    }

    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    public double getExponent() {
        return this.exponent;
    }

    public double probability(int x) {
        if (x <= 0 || x > this.numberOfElements) {
            return 0.0;
        }
        return 1.0 / FastMath.pow((double)x, this.exponent) / this.generalizedHarmonic(this.numberOfElements, this.exponent);
    }

    public double logProbability(int x) {
        if (x <= 0 || x > this.numberOfElements) {
            return Double.NEGATIVE_INFINITY;
        }
        return -FastMath.log(x) * this.exponent - FastMath.log(this.generalizedHarmonic(this.numberOfElements, this.exponent));
    }

    public double cumulativeProbability(int x) {
        if (x <= 0) {
            return 0.0;
        }
        if (x >= this.numberOfElements) {
            return 1.0;
        }
        return this.generalizedHarmonic(x, this.exponent) / this.generalizedHarmonic(this.numberOfElements, this.exponent);
    }

    public double getNumericalMean() {
        if (!this.numericalMeanIsCalculated) {
            this.numericalMean = this.calculateNumericalMean();
            this.numericalMeanIsCalculated = true;
        }
        return this.numericalMean;
    }

    protected double calculateNumericalMean() {
        int N = this.getNumberOfElements();
        double s = this.getExponent();
        double Hs1 = this.generalizedHarmonic(N, s - 1.0);
        double Hs = this.generalizedHarmonic(N, s);
        return Hs1 / Hs;
    }

    public double getNumericalVariance() {
        if (!this.numericalVarianceIsCalculated) {
            this.numericalVariance = this.calculateNumericalVariance();
            this.numericalVarianceIsCalculated = true;
        }
        return this.numericalVariance;
    }

    protected double calculateNumericalVariance() {
        int N = this.getNumberOfElements();
        double s = this.getExponent();
        double Hs2 = this.generalizedHarmonic(N, s - 2.0);
        double Hs1 = this.generalizedHarmonic(N, s - 1.0);
        double Hs = this.generalizedHarmonic(N, s);
        return Hs2 / Hs - Hs1 * Hs1 / (Hs * Hs);
    }

    private double generalizedHarmonic(int n, double m) {
        double value = 0.0;
        for (int k = n; k > 0; --k) {
            value += 1.0 / FastMath.pow((double)k, m);
        }
        return value;
    }

    public int getSupportLowerBound() {
        return 1;
    }

    public int getSupportUpperBound() {
        return this.getNumberOfElements();
    }

    public boolean isSupportConnected() {
        return true;
    }

    public int sample() {
        if (this.sampler == null) {
            this.sampler = new ZipfRejectionInversionSampler(this.numberOfElements, this.exponent);
        }
        return this.sampler.sample(this.random);
    }

    static final class ZipfRejectionInversionSampler {
        private final double exponent;
        private final int numberOfElements;
        private final double hIntegralX1;
        private final double hIntegralNumberOfElements;
        private final double s;

        ZipfRejectionInversionSampler(int numberOfElements, double exponent) {
            this.exponent = exponent;
            this.numberOfElements = numberOfElements;
            this.hIntegralX1 = this.hIntegral(1.5) - 1.0;
            this.hIntegralNumberOfElements = this.hIntegral((double)numberOfElements + 0.5);
            this.s = 2.0 - this.hIntegralInverse(this.hIntegral(2.5) - this.h(2.0));
        }

        int sample(RandomGenerator random) {
            double u;
            double x;
            int k;
            do {
                if ((k = (int)((x = this.hIntegralInverse(u = this.hIntegralNumberOfElements + random.nextDouble() * (this.hIntegralX1 - this.hIntegralNumberOfElements))) + 0.5)) < 1) {
                    k = 1;
                    continue;
                }
                if (k <= this.numberOfElements) continue;
                k = this.numberOfElements;
            } while (!((double)k - x <= this.s) && !(u >= this.hIntegral((double)k + 0.5) - this.h(k)));
            return k;
        }

        private double hIntegral(double x) {
            double logX = FastMath.log(x);
            return ZipfRejectionInversionSampler.helper2((1.0 - this.exponent) * logX) * logX;
        }

        private double h(double x) {
            return FastMath.exp(-this.exponent * FastMath.log(x));
        }

        private double hIntegralInverse(double x) {
            double t = x * (1.0 - this.exponent);
            if (t < -1.0) {
                t = -1.0;
            }
            return FastMath.exp(ZipfRejectionInversionSampler.helper1(t) * x);
        }

        static double helper1(double x) {
            if (FastMath.abs(x) > 1.0E-8) {
                return FastMath.log1p(x) / x;
            }
            return 1.0 - x * (0.5 - x * (0.3333333333333333 - x * 0.25));
        }

        static double helper2(double x) {
            if (FastMath.abs(x) > 1.0E-8) {
                return FastMath.expm1(x) / x;
            }
            return 1.0 + x * 0.5 * (1.0 + x * 0.3333333333333333 * (1.0 + x * 0.25));
        }
    }
}

