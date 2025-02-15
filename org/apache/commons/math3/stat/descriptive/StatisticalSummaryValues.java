/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class StatisticalSummaryValues
implements Serializable,
StatisticalSummary {
    private static final long serialVersionUID = -5108854841843722536L;
    private final double mean;
    private final double variance;
    private final long n;
    private final double max;
    private final double min;
    private final double sum;

    public StatisticalSummaryValues(double mean, double variance, long n, double max, double min, double sum) {
        this.mean = mean;
        this.variance = variance;
        this.n = n;
        this.max = max;
        this.min = min;
        this.sum = sum;
    }

    public double getMax() {
        return this.max;
    }

    public double getMean() {
        return this.mean;
    }

    public double getMin() {
        return this.min;
    }

    public long getN() {
        return this.n;
    }

    public double getSum() {
        return this.sum;
    }

    public double getStandardDeviation() {
        return FastMath.sqrt(this.variance);
    }

    public double getVariance() {
        return this.variance;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof StatisticalSummaryValues)) {
            return false;
        }
        StatisticalSummaryValues stat = (StatisticalSummaryValues)object;
        return Precision.equalsIncludingNaN(stat.getMax(), this.getMax()) && Precision.equalsIncludingNaN(stat.getMean(), this.getMean()) && Precision.equalsIncludingNaN(stat.getMin(), this.getMin()) && Precision.equalsIncludingNaN(stat.getN(), this.getN()) && Precision.equalsIncludingNaN(stat.getSum(), this.getSum()) && Precision.equalsIncludingNaN(stat.getVariance(), this.getVariance());
    }

    public int hashCode() {
        int result = 31 + MathUtils.hash(this.getMax());
        result = result * 31 + MathUtils.hash(this.getMean());
        result = result * 31 + MathUtils.hash(this.getMin());
        result = result * 31 + MathUtils.hash(this.getN());
        result = result * 31 + MathUtils.hash(this.getSum());
        result = result * 31 + MathUtils.hash(this.getVariance());
        return result;
    }

    public String toString() {
        StringBuffer outBuffer = new StringBuffer();
        String endl = "\n";
        outBuffer.append("StatisticalSummaryValues:").append(endl);
        outBuffer.append("n: ").append(this.getN()).append(endl);
        outBuffer.append("min: ").append(this.getMin()).append(endl);
        outBuffer.append("max: ").append(this.getMax()).append(endl);
        outBuffer.append("mean: ").append(this.getMean()).append(endl);
        outBuffer.append("std dev: ").append(this.getStandardDeviation()).append(endl);
        outBuffer.append("variance: ").append(this.getVariance()).append(endl);
        outBuffer.append("sum: ").append(this.getSum()).append(endl);
        return outBuffer.toString();
    }
}

