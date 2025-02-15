/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;

public class SynchronizedMultivariateSummaryStatistics
extends MultivariateSummaryStatistics {
    private static final long serialVersionUID = 7099834153347155363L;

    public SynchronizedMultivariateSummaryStatistics(int k, boolean isCovarianceBiasCorrected) {
        super(k, isCovarianceBiasCorrected);
    }

    public synchronized void addValue(double[] value) throws DimensionMismatchException {
        super.addValue(value);
    }

    public synchronized int getDimension() {
        return super.getDimension();
    }

    public synchronized long getN() {
        return super.getN();
    }

    public synchronized double[] getSum() {
        return super.getSum();
    }

    public synchronized double[] getSumSq() {
        return super.getSumSq();
    }

    public synchronized double[] getSumLog() {
        return super.getSumLog();
    }

    public synchronized double[] getMean() {
        return super.getMean();
    }

    public synchronized double[] getStandardDeviation() {
        return super.getStandardDeviation();
    }

    public synchronized RealMatrix getCovariance() {
        return super.getCovariance();
    }

    public synchronized double[] getMax() {
        return super.getMax();
    }

    public synchronized double[] getMin() {
        return super.getMin();
    }

    public synchronized double[] getGeometricMean() {
        return super.getGeometricMean();
    }

    public synchronized String toString() {
        return super.toString();
    }

    public synchronized void clear() {
        super.clear();
    }

    public synchronized boolean equals(Object object) {
        return super.equals(object);
    }

    public synchronized int hashCode() {
        return super.hashCode();
    }

    public synchronized StorelessUnivariateStatistic[] getSumImpl() {
        return super.getSumImpl();
    }

    public synchronized void setSumImpl(StorelessUnivariateStatistic[] sumImpl) throws DimensionMismatchException, MathIllegalStateException {
        super.setSumImpl(sumImpl);
    }

    public synchronized StorelessUnivariateStatistic[] getSumsqImpl() {
        return super.getSumsqImpl();
    }

    public synchronized void setSumsqImpl(StorelessUnivariateStatistic[] sumsqImpl) throws DimensionMismatchException, MathIllegalStateException {
        super.setSumsqImpl(sumsqImpl);
    }

    public synchronized StorelessUnivariateStatistic[] getMinImpl() {
        return super.getMinImpl();
    }

    public synchronized void setMinImpl(StorelessUnivariateStatistic[] minImpl) throws DimensionMismatchException, MathIllegalStateException {
        super.setMinImpl(minImpl);
    }

    public synchronized StorelessUnivariateStatistic[] getMaxImpl() {
        return super.getMaxImpl();
    }

    public synchronized void setMaxImpl(StorelessUnivariateStatistic[] maxImpl) throws DimensionMismatchException, MathIllegalStateException {
        super.setMaxImpl(maxImpl);
    }

    public synchronized StorelessUnivariateStatistic[] getSumLogImpl() {
        return super.getSumLogImpl();
    }

    public synchronized void setSumLogImpl(StorelessUnivariateStatistic[] sumLogImpl) throws DimensionMismatchException, MathIllegalStateException {
        super.setSumLogImpl(sumLogImpl);
    }

    public synchronized StorelessUnivariateStatistic[] getGeoMeanImpl() {
        return super.getGeoMeanImpl();
    }

    public synchronized void setGeoMeanImpl(StorelessUnivariateStatistic[] geoMeanImpl) throws DimensionMismatchException, MathIllegalStateException {
        super.setGeoMeanImpl(geoMeanImpl);
    }

    public synchronized StorelessUnivariateStatistic[] getMeanImpl() {
        return super.getMeanImpl();
    }

    public synchronized void setMeanImpl(StorelessUnivariateStatistic[] meanImpl) throws DimensionMismatchException, MathIllegalStateException {
        super.setMeanImpl(meanImpl);
    }
}

