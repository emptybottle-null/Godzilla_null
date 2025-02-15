/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.distribution.AbstractIntegerDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.SaddlePointExpansion;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;

public class PoissonDistribution
extends AbstractIntegerDistribution {
    public static final int DEFAULT_MAX_ITERATIONS = 10000000;
    public static final double DEFAULT_EPSILON = 1.0E-12;
    private static final long serialVersionUID = -3349935121172596109L;
    private final NormalDistribution normal;
    private final ExponentialDistribution exponential;
    private final double mean;
    private final int maxIterations;
    private final double epsilon;

    public PoissonDistribution(double p) throws NotStrictlyPositiveException {
        this(p, 1.0E-12, 10000000);
    }

    public PoissonDistribution(double p, double epsilon, int maxIterations) throws NotStrictlyPositiveException {
        this(new Well19937c(), p, epsilon, maxIterations);
    }

    public PoissonDistribution(RandomGenerator rng, double p, double epsilon, int maxIterations) throws NotStrictlyPositiveException {
        super(rng);
        if (p <= 0.0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.MEAN, p);
        }
        this.mean = p;
        this.epsilon = epsilon;
        this.maxIterations = maxIterations;
        this.normal = new NormalDistribution(rng, p, FastMath.sqrt(p), 1.0E-9);
        this.exponential = new ExponentialDistribution(rng, 1.0, 1.0E-9);
    }

    public PoissonDistribution(double p, double epsilon) throws NotStrictlyPositiveException {
        this(p, epsilon, 10000000);
    }

    public PoissonDistribution(double p, int maxIterations) {
        this(p, 1.0E-12, maxIterations);
    }

    public double getMean() {
        return this.mean;
    }

    public double probability(int x) {
        double logProbability = this.logProbability(x);
        return logProbability == Double.NEGATIVE_INFINITY ? 0.0 : FastMath.exp(logProbability);
    }

    public double logProbability(int x) {
        double ret = x < 0 || x == Integer.MAX_VALUE ? Double.NEGATIVE_INFINITY : (x == 0 ? -this.mean : -SaddlePointExpansion.getStirlingError(x) - SaddlePointExpansion.getDeviancePart(x, this.mean) - 0.5 * FastMath.log(Math.PI * 2) - 0.5 * FastMath.log(x));
        return ret;
    }

    public double cumulativeProbability(int x) {
        if (x < 0) {
            return 0.0;
        }
        if (x == Integer.MAX_VALUE) {
            return 1.0;
        }
        return Gamma.regularizedGammaQ((double)x + 1.0, this.mean, this.epsilon, this.maxIterations);
    }

    public double normalApproximateProbability(int x) {
        return this.normal.cumulativeProbability((double)x + 0.5);
    }

    public double getNumericalMean() {
        return this.getMean();
    }

    public double getNumericalVariance() {
        return this.getMean();
    }

    public int getSupportLowerBound() {
        return 0;
    }

    public int getSupportUpperBound() {
        return Integer.MAX_VALUE;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public int sample() {
        return (int)FastMath.min(this.nextPoisson(this.mean), Integer.MAX_VALUE);
    }

    private long nextPoisson(double meanPoisson) {
        double y;
        long y2;
        block9: {
            double pivot = 40.0;
            if (meanPoisson < 40.0) {
                double p = FastMath.exp(-meanPoisson);
                long n = 0L;
                double r = 1.0;
                double rnd = 1.0;
                while ((double)n < 1000.0 * meanPoisson) {
                    rnd = this.random.nextDouble();
                    if ((r *= rnd) >= p) {
                        ++n;
                        continue;
                    }
                    return n;
                }
                return n;
            }
            double lambda = FastMath.floor(meanPoisson);
            double lambdaFractional = meanPoisson - lambda;
            double logLambda = FastMath.log(lambda);
            double logLambdaFactorial = CombinatoricsUtils.factorialLog((int)lambda);
            y2 = lambdaFractional < Double.MIN_VALUE ? 0L : this.nextPoisson(lambdaFractional);
            double delta = FastMath.sqrt(lambda * FastMath.log(32.0 * lambda / Math.PI + 1.0));
            double halfDelta = delta / 2.0;
            double twolpd = 2.0 * lambda + delta;
            double a1 = FastMath.sqrt(Math.PI * twolpd) * FastMath.exp(1.0 / (8.0 * lambda));
            double a2 = twolpd / delta * FastMath.exp(-delta * (1.0 + delta) / twolpd);
            double aSum = a1 + a2 + 1.0;
            double p1 = a1 / aSum;
            double p2 = a2 / aSum;
            double c1 = 1.0 / (8.0 * lambda);
            double x = 0.0;
            y = 0.0;
            double v = 0.0;
            boolean a = false;
            double t = 0.0;
            double qr = 0.0;
            double qa = 0.0;
            while (true) {
                double u;
                if ((u = this.random.nextDouble()) <= p1) {
                    double n = this.random.nextGaussian();
                    x = n * FastMath.sqrt(lambda + halfDelta) - 0.5;
                    if (x > delta || x < -lambda) continue;
                    y = x < 0.0 ? FastMath.floor(x) : FastMath.ceil(x);
                    double e = this.exponential.sample();
                    v = -e - n * n / 2.0 + c1;
                } else {
                    if (u > p1 + p2) {
                        y = lambda;
                        break block9;
                    }
                    x = delta + twolpd / delta * this.exponential.sample();
                    y = FastMath.ceil(x);
                    v = -this.exponential.sample() - delta * (x + 1.0) / twolpd;
                }
                a = x < 0.0;
                t = y * (y + 1.0) / (2.0 * lambda);
                if (v < -t && !a) {
                    y = lambda + y;
                    break block9;
                }
                qr = t * ((2.0 * y + 1.0) / (6.0 * lambda) - 1.0);
                qa = qr - t * t / (3.0 * (lambda + (double)a * (y + 1.0)));
                if (v < qa) {
                    y = lambda + y;
                    break block9;
                }
                if (!(v > qr) && v < y * logLambda - CombinatoricsUtils.factorialLog((int)(y + lambda)) + logLambdaFactorial) break;
            }
            y = lambda + y;
        }
        return y2 + (long)y;
    }
}

