/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

public interface StatisticalSummary {
    public double getMean();

    public double getVariance();

    public double getStandardDeviation();

    public double getMax();

    public double getMin();

    public long getN();

    public double getSum();
}

