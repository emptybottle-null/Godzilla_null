/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AggregateSummaryStatistics
implements StatisticalSummary,
Serializable {
    private static final long serialVersionUID = -8207112444016386906L;
    private final SummaryStatistics statisticsPrototype;
    private final SummaryStatistics statistics;

    public AggregateSummaryStatistics() {
        this(new SummaryStatistics());
    }

    public AggregateSummaryStatistics(SummaryStatistics prototypeStatistics) throws NullArgumentException {
        this(prototypeStatistics, prototypeStatistics == null ? null : new SummaryStatistics(prototypeStatistics));
    }

    public AggregateSummaryStatistics(SummaryStatistics prototypeStatistics, SummaryStatistics initialStatistics) {
        this.statisticsPrototype = prototypeStatistics == null ? new SummaryStatistics() : prototypeStatistics;
        this.statistics = initialStatistics == null ? new SummaryStatistics() : initialStatistics;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getMax() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getMax();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getMean() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getMean();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getMin() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getMin();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getN() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getN();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getStandardDeviation() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getStandardDeviation();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getSum() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getSum();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public double getVariance() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getVariance();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public double getSumOfLogs() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getSumOfLogs();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public double getGeometricMean() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getGeometricMean();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public double getSumsq() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getSumsq();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public double getSecondMoment() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return this.statistics.getSecondMoment();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public StatisticalSummary getSummary() {
        SummaryStatistics summaryStatistics = this.statistics;
        synchronized (summaryStatistics) {
            return new StatisticalSummaryValues(this.getMean(), this.getVariance(), this.getN(), this.getMax(), this.getMin(), this.getSum());
        }
    }

    public SummaryStatistics createContributingStatistics() {
        AggregatingSummaryStatistics contributingStatistics = new AggregatingSummaryStatistics(this.statistics);
        SummaryStatistics.copy(this.statisticsPrototype, contributingStatistics);
        return contributingStatistics;
    }

    public static StatisticalSummaryValues aggregate(Collection<? extends StatisticalSummary> statistics) {
        if (statistics == null) {
            return null;
        }
        Iterator<? extends StatisticalSummary> iterator = statistics.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        StatisticalSummary current = iterator.next();
        long n = current.getN();
        double min = current.getMin();
        double sum = current.getSum();
        double max = current.getMax();
        double var = current.getVariance();
        double m2 = var * ((double)n - 1.0);
        double mean = current.getMean();
        while (iterator.hasNext()) {
            current = iterator.next();
            if (current.getMin() < min || Double.isNaN(min)) {
                min = current.getMin();
            }
            if (current.getMax() > max || Double.isNaN(max)) {
                max = current.getMax();
            }
            double oldN = n;
            double curN = current.getN();
            n = (long)((double)n + curN);
            double meanDiff = current.getMean() - mean;
            mean = (sum += current.getSum()) / (double)n;
            double curM2 = current.getVariance() * (curN - 1.0);
            m2 = m2 + curM2 + meanDiff * meanDiff * oldN * curN / (double)n;
        }
        double variance = n == 0L ? Double.NaN : (n == 1L ? 0.0 : m2 / (double)(n - 1L));
        return new StatisticalSummaryValues(mean, variance, n, max, min, sum);
    }

    private static class AggregatingSummaryStatistics
    extends SummaryStatistics {
        private static final long serialVersionUID = 1L;
        private final SummaryStatistics aggregateStatistics;

        AggregatingSummaryStatistics(SummaryStatistics aggregateStatistics) {
            this.aggregateStatistics = aggregateStatistics;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void addValue(double value) {
            super.addValue(value);
            SummaryStatistics summaryStatistics = this.aggregateStatistics;
            synchronized (summaryStatistics) {
                this.aggregateStatistics.addValue(value);
            }
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof AggregatingSummaryStatistics)) {
                return false;
            }
            AggregatingSummaryStatistics stat = (AggregatingSummaryStatistics)object;
            return super.equals(stat) && this.aggregateStatistics.equals(stat.aggregateStatistics);
        }

        public int hashCode() {
            return 123 + super.hashCode() + this.aggregateStatistics.hashCode();
        }
    }
}

