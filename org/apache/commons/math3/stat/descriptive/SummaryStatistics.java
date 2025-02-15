/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class SummaryStatistics
implements StatisticalSummary,
Serializable {
    private static final long serialVersionUID = -2021321786743555871L;
    private long n = 0L;
    private SecondMoment secondMoment = new SecondMoment();
    private Sum sum = new Sum();
    private SumOfSquares sumsq = new SumOfSquares();
    private Min min = new Min();
    private Max max = new Max();
    private SumOfLogs sumLog = new SumOfLogs();
    private GeometricMean geoMean = new GeometricMean(this.sumLog);
    private Mean mean = new Mean(this.secondMoment);
    private Variance variance = new Variance(this.secondMoment);
    private StorelessUnivariateStatistic sumImpl = this.sum;
    private StorelessUnivariateStatistic sumsqImpl = this.sumsq;
    private StorelessUnivariateStatistic minImpl = this.min;
    private StorelessUnivariateStatistic maxImpl = this.max;
    private StorelessUnivariateStatistic sumLogImpl = this.sumLog;
    private StorelessUnivariateStatistic geoMeanImpl = this.geoMean;
    private StorelessUnivariateStatistic meanImpl = this.mean;
    private StorelessUnivariateStatistic varianceImpl = this.variance;

    public SummaryStatistics() {
    }

    public SummaryStatistics(SummaryStatistics original) throws NullArgumentException {
        SummaryStatistics.copy(original, this);
    }

    public StatisticalSummary getSummary() {
        return new StatisticalSummaryValues(this.getMean(), this.getVariance(), this.getN(), this.getMax(), this.getMin(), this.getSum());
    }

    public void addValue(double value) {
        this.sumImpl.increment(value);
        this.sumsqImpl.increment(value);
        this.minImpl.increment(value);
        this.maxImpl.increment(value);
        this.sumLogImpl.increment(value);
        this.secondMoment.increment(value);
        if (this.meanImpl != this.mean) {
            this.meanImpl.increment(value);
        }
        if (this.varianceImpl != this.variance) {
            this.varianceImpl.increment(value);
        }
        if (this.geoMeanImpl != this.geoMean) {
            this.geoMeanImpl.increment(value);
        }
        ++this.n;
    }

    public long getN() {
        return this.n;
    }

    public double getSum() {
        return this.sumImpl.getResult();
    }

    public double getSumsq() {
        return this.sumsqImpl.getResult();
    }

    public double getMean() {
        return this.meanImpl.getResult();
    }

    public double getStandardDeviation() {
        double stdDev = Double.NaN;
        if (this.getN() > 0L) {
            stdDev = this.getN() > 1L ? FastMath.sqrt(this.getVariance()) : 0.0;
        }
        return stdDev;
    }

    public double getQuadraticMean() {
        long size = this.getN();
        return size > 0L ? FastMath.sqrt(this.getSumsq() / (double)size) : Double.NaN;
    }

    public double getVariance() {
        return this.varianceImpl.getResult();
    }

    public double getPopulationVariance() {
        Variance populationVariance = new Variance(this.secondMoment);
        populationVariance.setBiasCorrected(false);
        return populationVariance.getResult();
    }

    public double getMax() {
        return this.maxImpl.getResult();
    }

    public double getMin() {
        return this.minImpl.getResult();
    }

    public double getGeometricMean() {
        return this.geoMeanImpl.getResult();
    }

    public double getSumOfLogs() {
        return this.sumLogImpl.getResult();
    }

    public double getSecondMoment() {
        return this.secondMoment.getResult();
    }

    public String toString() {
        StringBuilder outBuffer = new StringBuilder();
        String endl = "\n";
        outBuffer.append("SummaryStatistics:").append(endl);
        outBuffer.append("n: ").append(this.getN()).append(endl);
        outBuffer.append("min: ").append(this.getMin()).append(endl);
        outBuffer.append("max: ").append(this.getMax()).append(endl);
        outBuffer.append("sum: ").append(this.getSum()).append(endl);
        outBuffer.append("mean: ").append(this.getMean()).append(endl);
        outBuffer.append("geometric mean: ").append(this.getGeometricMean()).append(endl);
        outBuffer.append("variance: ").append(this.getVariance()).append(endl);
        outBuffer.append("population variance: ").append(this.getPopulationVariance()).append(endl);
        outBuffer.append("second moment: ").append(this.getSecondMoment()).append(endl);
        outBuffer.append("sum of squares: ").append(this.getSumsq()).append(endl);
        outBuffer.append("standard deviation: ").append(this.getStandardDeviation()).append(endl);
        outBuffer.append("sum of logs: ").append(this.getSumOfLogs()).append(endl);
        return outBuffer.toString();
    }

    public void clear() {
        this.n = 0L;
        this.minImpl.clear();
        this.maxImpl.clear();
        this.sumImpl.clear();
        this.sumLogImpl.clear();
        this.sumsqImpl.clear();
        this.geoMeanImpl.clear();
        this.secondMoment.clear();
        if (this.meanImpl != this.mean) {
            this.meanImpl.clear();
        }
        if (this.varianceImpl != this.variance) {
            this.varianceImpl.clear();
        }
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SummaryStatistics)) {
            return false;
        }
        SummaryStatistics stat = (SummaryStatistics)object;
        return Precision.equalsIncludingNaN(stat.getGeometricMean(), this.getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), this.getMax()) && Precision.equalsIncludingNaN(stat.getMean(), this.getMean()) && Precision.equalsIncludingNaN(stat.getMin(), this.getMin()) && Precision.equalsIncludingNaN(stat.getN(), this.getN()) && Precision.equalsIncludingNaN(stat.getSum(), this.getSum()) && Precision.equalsIncludingNaN(stat.getSumsq(), this.getSumsq()) && Precision.equalsIncludingNaN(stat.getVariance(), this.getVariance());
    }

    public int hashCode() {
        int result = 31 + MathUtils.hash(this.getGeometricMean());
        result = result * 31 + MathUtils.hash(this.getGeometricMean());
        result = result * 31 + MathUtils.hash(this.getMax());
        result = result * 31 + MathUtils.hash(this.getMean());
        result = result * 31 + MathUtils.hash(this.getMin());
        result = result * 31 + MathUtils.hash(this.getN());
        result = result * 31 + MathUtils.hash(this.getSum());
        result = result * 31 + MathUtils.hash(this.getSumsq());
        result = result * 31 + MathUtils.hash(this.getVariance());
        return result;
    }

    public StorelessUnivariateStatistic getSumImpl() {
        return this.sumImpl;
    }

    public void setSumImpl(StorelessUnivariateStatistic sumImpl) throws MathIllegalStateException {
        this.checkEmpty();
        this.sumImpl = sumImpl;
    }

    public StorelessUnivariateStatistic getSumsqImpl() {
        return this.sumsqImpl;
    }

    public void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl) throws MathIllegalStateException {
        this.checkEmpty();
        this.sumsqImpl = sumsqImpl;
    }

    public StorelessUnivariateStatistic getMinImpl() {
        return this.minImpl;
    }

    public void setMinImpl(StorelessUnivariateStatistic minImpl) throws MathIllegalStateException {
        this.checkEmpty();
        this.minImpl = minImpl;
    }

    public StorelessUnivariateStatistic getMaxImpl() {
        return this.maxImpl;
    }

    public void setMaxImpl(StorelessUnivariateStatistic maxImpl) throws MathIllegalStateException {
        this.checkEmpty();
        this.maxImpl = maxImpl;
    }

    public StorelessUnivariateStatistic getSumLogImpl() {
        return this.sumLogImpl;
    }

    public void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) throws MathIllegalStateException {
        this.checkEmpty();
        this.sumLogImpl = sumLogImpl;
        this.geoMean.setSumLogImpl(sumLogImpl);
    }

    public StorelessUnivariateStatistic getGeoMeanImpl() {
        return this.geoMeanImpl;
    }

    public void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl) throws MathIllegalStateException {
        this.checkEmpty();
        this.geoMeanImpl = geoMeanImpl;
    }

    public StorelessUnivariateStatistic getMeanImpl() {
        return this.meanImpl;
    }

    public void setMeanImpl(StorelessUnivariateStatistic meanImpl) throws MathIllegalStateException {
        this.checkEmpty();
        this.meanImpl = meanImpl;
    }

    public StorelessUnivariateStatistic getVarianceImpl() {
        return this.varianceImpl;
    }

    public void setVarianceImpl(StorelessUnivariateStatistic varianceImpl) throws MathIllegalStateException {
        this.checkEmpty();
        this.varianceImpl = varianceImpl;
    }

    private void checkEmpty() throws MathIllegalStateException {
        if (this.n > 0L) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, this.n);
        }
    }

    public SummaryStatistics copy() {
        SummaryStatistics result = new SummaryStatistics();
        SummaryStatistics.copy(this, result);
        return result;
    }

    public static void copy(SummaryStatistics source, SummaryStatistics dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.maxImpl = source.maxImpl.copy();
        dest.minImpl = source.minImpl.copy();
        dest.sumImpl = source.sumImpl.copy();
        dest.sumLogImpl = source.sumLogImpl.copy();
        dest.sumsqImpl = source.sumsqImpl.copy();
        dest.secondMoment = source.secondMoment.copy();
        dest.n = source.n;
        dest.varianceImpl = source.getVarianceImpl() instanceof Variance ? new Variance(dest.secondMoment) : source.varianceImpl.copy();
        dest.meanImpl = source.meanImpl instanceof Mean ? new Mean(dest.secondMoment) : source.meanImpl.copy();
        dest.geoMeanImpl = source.getGeoMeanImpl() instanceof GeometricMean ? new GeometricMean((SumOfLogs)dest.sumLogImpl) : source.geoMeanImpl.copy();
        if (source.geoMean == source.geoMeanImpl) {
            dest.geoMean = (GeometricMean)dest.geoMeanImpl;
        } else {
            GeometricMean.copy(source.geoMean, dest.geoMean);
        }
        if (source.max == source.maxImpl) {
            dest.max = (Max)dest.maxImpl;
        } else {
            Max.copy(source.max, dest.max);
        }
        if (source.mean == source.meanImpl) {
            dest.mean = (Mean)dest.meanImpl;
        } else {
            Mean.copy(source.mean, dest.mean);
        }
        if (source.min == source.minImpl) {
            dest.min = (Min)dest.minImpl;
        } else {
            Min.copy(source.min, dest.min);
        }
        if (source.sum == source.sumImpl) {
            dest.sum = (Sum)dest.sumImpl;
        } else {
            Sum.copy(source.sum, dest.sum);
        }
        if (source.variance == source.varianceImpl) {
            dest.variance = (Variance)dest.varianceImpl;
        } else {
            Variance.copy(source.variance, dest.variance);
        }
        if (source.sumLog == source.sumLogImpl) {
            dest.sumLog = (SumOfLogs)dest.sumLogImpl;
        } else {
            SumOfLogs.copy(source.sumLog, dest.sumLog);
        }
        if (source.sumsq == source.sumsqImpl) {
            dest.sumsq = (SumOfSquares)dest.sumsqImpl;
        } else {
            SumOfSquares.copy(source.sumsq, dest.sumsq);
        }
    }
}

