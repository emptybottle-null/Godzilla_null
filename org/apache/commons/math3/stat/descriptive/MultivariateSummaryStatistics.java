/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.StatisticalMultivariateSummary;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class MultivariateSummaryStatistics
implements StatisticalMultivariateSummary,
Serializable {
    private static final long serialVersionUID = 2271900808994826718L;
    private int k;
    private long n = 0L;
    private StorelessUnivariateStatistic[] sumImpl;
    private StorelessUnivariateStatistic[] sumSqImpl;
    private StorelessUnivariateStatistic[] minImpl;
    private StorelessUnivariateStatistic[] maxImpl;
    private StorelessUnivariateStatistic[] sumLogImpl;
    private StorelessUnivariateStatistic[] geoMeanImpl;
    private StorelessUnivariateStatistic[] meanImpl;
    private VectorialCovariance covarianceImpl;

    public MultivariateSummaryStatistics(int k, boolean isCovarianceBiasCorrected) {
        this.k = k;
        this.sumImpl = new StorelessUnivariateStatistic[k];
        this.sumSqImpl = new StorelessUnivariateStatistic[k];
        this.minImpl = new StorelessUnivariateStatistic[k];
        this.maxImpl = new StorelessUnivariateStatistic[k];
        this.sumLogImpl = new StorelessUnivariateStatistic[k];
        this.geoMeanImpl = new StorelessUnivariateStatistic[k];
        this.meanImpl = new StorelessUnivariateStatistic[k];
        for (int i = 0; i < k; ++i) {
            this.sumImpl[i] = new Sum();
            this.sumSqImpl[i] = new SumOfSquares();
            this.minImpl[i] = new Min();
            this.maxImpl[i] = new Max();
            this.sumLogImpl[i] = new SumOfLogs();
            this.geoMeanImpl[i] = new GeometricMean();
            this.meanImpl[i] = new Mean();
        }
        this.covarianceImpl = new VectorialCovariance(k, isCovarianceBiasCorrected);
    }

    public void addValue(double[] value) throws DimensionMismatchException {
        this.checkDimension(value.length);
        for (int i = 0; i < this.k; ++i) {
            double v = value[i];
            this.sumImpl[i].increment(v);
            this.sumSqImpl[i].increment(v);
            this.minImpl[i].increment(v);
            this.maxImpl[i].increment(v);
            this.sumLogImpl[i].increment(v);
            this.geoMeanImpl[i].increment(v);
            this.meanImpl[i].increment(v);
        }
        this.covarianceImpl.increment(value);
        ++this.n;
    }

    public int getDimension() {
        return this.k;
    }

    public long getN() {
        return this.n;
    }

    private double[] getResults(StorelessUnivariateStatistic[] stats) {
        double[] results = new double[stats.length];
        for (int i = 0; i < results.length; ++i) {
            results[i] = stats[i].getResult();
        }
        return results;
    }

    public double[] getSum() {
        return this.getResults(this.sumImpl);
    }

    public double[] getSumSq() {
        return this.getResults(this.sumSqImpl);
    }

    public double[] getSumLog() {
        return this.getResults(this.sumLogImpl);
    }

    public double[] getMean() {
        return this.getResults(this.meanImpl);
    }

    public double[] getStandardDeviation() {
        double[] stdDev = new double[this.k];
        if (this.getN() < 1L) {
            Arrays.fill(stdDev, Double.NaN);
        } else if (this.getN() < 2L) {
            Arrays.fill(stdDev, 0.0);
        } else {
            RealMatrix matrix = this.covarianceImpl.getResult();
            for (int i = 0; i < this.k; ++i) {
                stdDev[i] = FastMath.sqrt(matrix.getEntry(i, i));
            }
        }
        return stdDev;
    }

    public RealMatrix getCovariance() {
        return this.covarianceImpl.getResult();
    }

    public double[] getMax() {
        return this.getResults(this.maxImpl);
    }

    public double[] getMin() {
        return this.getResults(this.minImpl);
    }

    public double[] getGeometricMean() {
        return this.getResults(this.geoMeanImpl);
    }

    public String toString() {
        String separator = ", ";
        String suffix = System.getProperty("line.separator");
        StringBuilder outBuffer = new StringBuilder();
        outBuffer.append("MultivariateSummaryStatistics:" + suffix);
        outBuffer.append("n: " + this.getN() + suffix);
        this.append(outBuffer, this.getMin(), "min: ", ", ", suffix);
        this.append(outBuffer, this.getMax(), "max: ", ", ", suffix);
        this.append(outBuffer, this.getMean(), "mean: ", ", ", suffix);
        this.append(outBuffer, this.getGeometricMean(), "geometric mean: ", ", ", suffix);
        this.append(outBuffer, this.getSumSq(), "sum of squares: ", ", ", suffix);
        this.append(outBuffer, this.getSumLog(), "sum of logarithms: ", ", ", suffix);
        this.append(outBuffer, this.getStandardDeviation(), "standard deviation: ", ", ", suffix);
        outBuffer.append("covariance: " + this.getCovariance().toString() + suffix);
        return outBuffer.toString();
    }

    private void append(StringBuilder buffer, double[] data, String prefix, String separator, String suffix) {
        buffer.append(prefix);
        for (int i = 0; i < data.length; ++i) {
            if (i > 0) {
                buffer.append(separator);
            }
            buffer.append(data[i]);
        }
        buffer.append(suffix);
    }

    public void clear() {
        this.n = 0L;
        for (int i = 0; i < this.k; ++i) {
            this.minImpl[i].clear();
            this.maxImpl[i].clear();
            this.sumImpl[i].clear();
            this.sumLogImpl[i].clear();
            this.sumSqImpl[i].clear();
            this.geoMeanImpl[i].clear();
            this.meanImpl[i].clear();
        }
        this.covarianceImpl.clear();
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof MultivariateSummaryStatistics)) {
            return false;
        }
        MultivariateSummaryStatistics stat = (MultivariateSummaryStatistics)object;
        return MathArrays.equalsIncludingNaN(stat.getGeometricMean(), this.getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), this.getMax()) && MathArrays.equalsIncludingNaN(stat.getMean(), this.getMean()) && MathArrays.equalsIncludingNaN(stat.getMin(), this.getMin()) && Precision.equalsIncludingNaN(stat.getN(), this.getN()) && MathArrays.equalsIncludingNaN(stat.getSum(), this.getSum()) && MathArrays.equalsIncludingNaN(stat.getSumSq(), this.getSumSq()) && MathArrays.equalsIncludingNaN(stat.getSumLog(), this.getSumLog()) && stat.getCovariance().equals(this.getCovariance());
    }

    public int hashCode() {
        int result = 31 + MathUtils.hash(this.getGeometricMean());
        result = result * 31 + MathUtils.hash(this.getGeometricMean());
        result = result * 31 + MathUtils.hash(this.getMax());
        result = result * 31 + MathUtils.hash(this.getMean());
        result = result * 31 + MathUtils.hash(this.getMin());
        result = result * 31 + MathUtils.hash(this.getN());
        result = result * 31 + MathUtils.hash(this.getSum());
        result = result * 31 + MathUtils.hash(this.getSumSq());
        result = result * 31 + MathUtils.hash(this.getSumLog());
        result = result * 31 + this.getCovariance().hashCode();
        return result;
    }

    private void setImpl(StorelessUnivariateStatistic[] newImpl, StorelessUnivariateStatistic[] oldImpl) throws MathIllegalStateException, DimensionMismatchException {
        this.checkEmpty();
        this.checkDimension(newImpl.length);
        System.arraycopy(newImpl, 0, oldImpl, 0, newImpl.length);
    }

    public StorelessUnivariateStatistic[] getSumImpl() {
        return (StorelessUnivariateStatistic[])this.sumImpl.clone();
    }

    public void setSumImpl(StorelessUnivariateStatistic[] sumImpl) throws MathIllegalStateException, DimensionMismatchException {
        this.setImpl(sumImpl, this.sumImpl);
    }

    public StorelessUnivariateStatistic[] getSumsqImpl() {
        return (StorelessUnivariateStatistic[])this.sumSqImpl.clone();
    }

    public void setSumsqImpl(StorelessUnivariateStatistic[] sumsqImpl) throws MathIllegalStateException, DimensionMismatchException {
        this.setImpl(sumsqImpl, this.sumSqImpl);
    }

    public StorelessUnivariateStatistic[] getMinImpl() {
        return (StorelessUnivariateStatistic[])this.minImpl.clone();
    }

    public void setMinImpl(StorelessUnivariateStatistic[] minImpl) throws MathIllegalStateException, DimensionMismatchException {
        this.setImpl(minImpl, this.minImpl);
    }

    public StorelessUnivariateStatistic[] getMaxImpl() {
        return (StorelessUnivariateStatistic[])this.maxImpl.clone();
    }

    public void setMaxImpl(StorelessUnivariateStatistic[] maxImpl) throws MathIllegalStateException, DimensionMismatchException {
        this.setImpl(maxImpl, this.maxImpl);
    }

    public StorelessUnivariateStatistic[] getSumLogImpl() {
        return (StorelessUnivariateStatistic[])this.sumLogImpl.clone();
    }

    public void setSumLogImpl(StorelessUnivariateStatistic[] sumLogImpl) throws MathIllegalStateException, DimensionMismatchException {
        this.setImpl(sumLogImpl, this.sumLogImpl);
    }

    public StorelessUnivariateStatistic[] getGeoMeanImpl() {
        return (StorelessUnivariateStatistic[])this.geoMeanImpl.clone();
    }

    public void setGeoMeanImpl(StorelessUnivariateStatistic[] geoMeanImpl) throws MathIllegalStateException, DimensionMismatchException {
        this.setImpl(geoMeanImpl, this.geoMeanImpl);
    }

    public StorelessUnivariateStatistic[] getMeanImpl() {
        return (StorelessUnivariateStatistic[])this.meanImpl.clone();
    }

    public void setMeanImpl(StorelessUnivariateStatistic[] meanImpl) throws MathIllegalStateException, DimensionMismatchException {
        this.setImpl(meanImpl, this.meanImpl);
    }

    private void checkEmpty() throws MathIllegalStateException {
        if (this.n > 0L) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, this.n);
        }
    }

    private void checkDimension(int dimension) throws DimensionMismatchException {
        if (dimension != this.k) {
            throw new DimensionMismatchException(dimension, this.k);
        }
    }
}

