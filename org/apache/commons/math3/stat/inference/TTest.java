/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.util.FastMath;

public class TTest {
    public double pairedT(double[] sample1, double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException {
        this.checkSampleData(sample1);
        this.checkSampleData(sample2);
        double meanDifference = StatUtils.meanDifference(sample1, sample2);
        return this.t(meanDifference, 0.0, StatUtils.varianceDifference(sample1, sample2, meanDifference), sample1.length);
    }

    public double pairedTTest(double[] sample1, double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException {
        double meanDifference = StatUtils.meanDifference(sample1, sample2);
        return this.tTest(meanDifference, 0.0, StatUtils.varianceDifference(sample1, sample2, meanDifference), sample1.length);
    }

    public boolean pairedTTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        this.checkSignificanceLevel(alpha);
        return this.pairedTTest(sample1, sample2) < alpha;
    }

    public double t(double mu, double[] observed) throws NullArgumentException, NumberIsTooSmallException {
        this.checkSampleData(observed);
        return this.t(StatUtils.mean(observed), mu, StatUtils.variance(observed), observed.length);
    }

    public double t(double mu, StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException {
        this.checkSampleData(sampleStats);
        return this.t(sampleStats.getMean(), mu, sampleStats.getVariance(), sampleStats.getN());
    }

    public double homoscedasticT(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
        this.checkSampleData(sample1);
        this.checkSampleData(sample2);
        return this.homoscedasticT(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
    }

    public double t(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
        this.checkSampleData(sample1);
        this.checkSampleData(sample2);
        return this.t(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
    }

    public double t(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
        this.checkSampleData(sampleStats1);
        this.checkSampleData(sampleStats2);
        return this.t(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
    }

    public double homoscedasticT(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
        this.checkSampleData(sampleStats1);
        this.checkSampleData(sampleStats2);
        return this.homoscedasticT(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
    }

    public double tTest(double mu, double[] sample) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        this.checkSampleData(sample);
        return this.tTest(StatUtils.mean(sample), mu, StatUtils.variance(sample), sample.length);
    }

    public boolean tTest(double mu, double[] sample, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        this.checkSignificanceLevel(alpha);
        return this.tTest(mu, sample) < alpha;
    }

    public double tTest(double mu, StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        this.checkSampleData(sampleStats);
        return this.tTest(sampleStats.getMean(), mu, sampleStats.getVariance(), sampleStats.getN());
    }

    public boolean tTest(double mu, StatisticalSummary sampleStats, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        this.checkSignificanceLevel(alpha);
        return this.tTest(mu, sampleStats) < alpha;
    }

    public double tTest(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        this.checkSampleData(sample1);
        this.checkSampleData(sample2);
        return this.tTest(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
    }

    public double homoscedasticTTest(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        this.checkSampleData(sample1);
        this.checkSampleData(sample2);
        return this.homoscedasticTTest(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
    }

    public boolean tTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        this.checkSignificanceLevel(alpha);
        return this.tTest(sample1, sample2) < alpha;
    }

    public boolean homoscedasticTTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        this.checkSignificanceLevel(alpha);
        return this.homoscedasticTTest(sample1, sample2) < alpha;
    }

    public double tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        this.checkSampleData(sampleStats1);
        this.checkSampleData(sampleStats2);
        return this.tTest(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
    }

    public double homoscedasticTTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        this.checkSampleData(sampleStats1);
        this.checkSampleData(sampleStats2);
        return this.homoscedasticTTest(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
    }

    public boolean tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        this.checkSignificanceLevel(alpha);
        return this.tTest(sampleStats1, sampleStats2) < alpha;
    }

    protected double df(double v1, double v2, double n1, double n2) {
        return (v1 / n1 + v2 / n2) * (v1 / n1 + v2 / n2) / (v1 * v1 / (n1 * n1 * (n1 - 1.0)) + v2 * v2 / (n2 * n2 * (n2 - 1.0)));
    }

    protected double t(double m, double mu, double v, double n) {
        return (m - mu) / FastMath.sqrt(v / n);
    }

    protected double t(double m1, double m2, double v1, double v2, double n1, double n2) {
        return (m1 - m2) / FastMath.sqrt(v1 / n1 + v2 / n2);
    }

    protected double homoscedasticT(double m1, double m2, double v1, double v2, double n1, double n2) {
        double pooledVariance = ((n1 - 1.0) * v1 + (n2 - 1.0) * v2) / (n1 + n2 - 2.0);
        return (m1 - m2) / FastMath.sqrt(pooledVariance * (1.0 / n1 + 1.0 / n2));
    }

    protected double tTest(double m, double mu, double v, double n) throws MaxCountExceededException, MathIllegalArgumentException {
        double t = FastMath.abs(this.t(m, mu, v, n));
        TDistribution distribution = new TDistribution(null, n - 1.0);
        return 2.0 * distribution.cumulativeProbability(-t);
    }

    protected double tTest(double m1, double m2, double v1, double v2, double n1, double n2) throws MaxCountExceededException, NotStrictlyPositiveException {
        double t = FastMath.abs(this.t(m1, m2, v1, v2, n1, n2));
        double degreesOfFreedom = this.df(v1, v2, n1, n2);
        TDistribution distribution = new TDistribution(null, degreesOfFreedom);
        return 2.0 * distribution.cumulativeProbability(-t);
    }

    protected double homoscedasticTTest(double m1, double m2, double v1, double v2, double n1, double n2) throws MaxCountExceededException, NotStrictlyPositiveException {
        double t = FastMath.abs(this.homoscedasticT(m1, m2, v1, v2, n1, n2));
        double degreesOfFreedom = n1 + n2 - 2.0;
        TDistribution distribution = new TDistribution(null, degreesOfFreedom);
        return 2.0 * distribution.cumulativeProbability(-t);
    }

    private void checkSignificanceLevel(double alpha) throws OutOfRangeException {
        if (alpha <= 0.0 || alpha > 0.5) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.SIGNIFICANCE_LEVEL, (Number)alpha, 0.0, 0.5);
        }
    }

    private void checkSampleData(double[] data) throws NullArgumentException, NumberIsTooSmallException {
        if (data == null) {
            throw new NullArgumentException();
        }
        if (data.length < 2) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.INSUFFICIENT_DATA_FOR_T_STATISTIC, (Number)data.length, 2, true);
        }
    }

    private void checkSampleData(StatisticalSummary stat) throws NullArgumentException, NumberIsTooSmallException {
        if (stat == null) {
            throw new NullArgumentException();
        }
        if (stat.getN() < 2L) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.INSUFFICIENT_DATA_FOR_T_STATISTIC, (Number)stat.getN(), 2, true);
        }
    }
}

