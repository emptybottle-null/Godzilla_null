/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.MathUtils;

public class SynchronizedSummaryStatistics
extends SummaryStatistics {
    private static final long serialVersionUID = 1909861009042253704L;

    public SynchronizedSummaryStatistics() {
    }

    public SynchronizedSummaryStatistics(SynchronizedSummaryStatistics original) throws NullArgumentException {
        SynchronizedSummaryStatistics.copy(original, this);
    }

    public synchronized StatisticalSummary getSummary() {
        return super.getSummary();
    }

    public synchronized void addValue(double value) {
        super.addValue(value);
    }

    public synchronized long getN() {
        return super.getN();
    }

    public synchronized double getSum() {
        return super.getSum();
    }

    public synchronized double getSumsq() {
        return super.getSumsq();
    }

    public synchronized double getMean() {
        return super.getMean();
    }

    public synchronized double getStandardDeviation() {
        return super.getStandardDeviation();
    }

    public synchronized double getQuadraticMean() {
        return super.getQuadraticMean();
    }

    public synchronized double getVariance() {
        return super.getVariance();
    }

    public synchronized double getPopulationVariance() {
        return super.getPopulationVariance();
    }

    public synchronized double getMax() {
        return super.getMax();
    }

    public synchronized double getMin() {
        return super.getMin();
    }

    public synchronized double getGeometricMean() {
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

    public synchronized StorelessUnivariateStatistic getSumImpl() {
        return super.getSumImpl();
    }

    public synchronized void setSumImpl(StorelessUnivariateStatistic sumImpl) throws MathIllegalStateException {
        super.setSumImpl(sumImpl);
    }

    public synchronized StorelessUnivariateStatistic getSumsqImpl() {
        return super.getSumsqImpl();
    }

    public synchronized void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl) throws MathIllegalStateException {
        super.setSumsqImpl(sumsqImpl);
    }

    public synchronized StorelessUnivariateStatistic getMinImpl() {
        return super.getMinImpl();
    }

    public synchronized void setMinImpl(StorelessUnivariateStatistic minImpl) throws MathIllegalStateException {
        super.setMinImpl(minImpl);
    }

    public synchronized StorelessUnivariateStatistic getMaxImpl() {
        return super.getMaxImpl();
    }

    public synchronized void setMaxImpl(StorelessUnivariateStatistic maxImpl) throws MathIllegalStateException {
        super.setMaxImpl(maxImpl);
    }

    public synchronized StorelessUnivariateStatistic getSumLogImpl() {
        return super.getSumLogImpl();
    }

    public synchronized void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) throws MathIllegalStateException {
        super.setSumLogImpl(sumLogImpl);
    }

    public synchronized StorelessUnivariateStatistic getGeoMeanImpl() {
        return super.getGeoMeanImpl();
    }

    public synchronized void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl) throws MathIllegalStateException {
        super.setGeoMeanImpl(geoMeanImpl);
    }

    public synchronized StorelessUnivariateStatistic getMeanImpl() {
        return super.getMeanImpl();
    }

    public synchronized void setMeanImpl(StorelessUnivariateStatistic meanImpl) throws MathIllegalStateException {
        super.setMeanImpl(meanImpl);
    }

    public synchronized StorelessUnivariateStatistic getVarianceImpl() {
        return super.getVarianceImpl();
    }

    public synchronized void setVarianceImpl(StorelessUnivariateStatistic varianceImpl) throws MathIllegalStateException {
        super.setVarianceImpl(varianceImpl);
    }

    public synchronized SynchronizedSummaryStatistics copy() {
        SynchronizedSummaryStatistics result = new SynchronizedSummaryStatistics();
        SynchronizedSummaryStatistics.copy(this, result);
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void copy(SynchronizedSummaryStatistics source, SynchronizedSummaryStatistics dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        SynchronizedSummaryStatistics synchronizedSummaryStatistics = source;
        synchronized (synchronizedSummaryStatistics) {
            SynchronizedSummaryStatistics synchronizedSummaryStatistics2 = dest;
            synchronized (synchronizedSummaryStatistics2) {
                SummaryStatistics.copy(source, dest);
            }
        }
    }
}

