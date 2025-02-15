/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.linear.RealMatrix;

public interface StatisticalMultivariateSummary {
    public int getDimension();

    public double[] getMean();

    public RealMatrix getCovariance();

    public double[] getStandardDeviation();

    public double[] getMax();

    public double[] getMin();

    public long getN();

    public double[] getGeometricMean();

    public double[] getSum();

    public double[] getSumSq();

    public double[] getSumLog();
}

