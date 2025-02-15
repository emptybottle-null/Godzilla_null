/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.inference;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OneWayAnova {
    public double anovaFValue(Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException {
        AnovaStats a = this.anovaStats(categoryData);
        return a.F;
    }

    public double anovaPValue(Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
        AnovaStats a = this.anovaStats(categoryData);
        FDistribution fdist = new FDistribution(null, (double)a.dfbg, (double)a.dfwg);
        return 1.0 - fdist.cumulativeProbability(a.F);
    }

    public double anovaPValue(Collection<SummaryStatistics> categoryData, boolean allowOneElementData) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
        AnovaStats a = this.anovaStats(categoryData, allowOneElementData);
        FDistribution fdist = new FDistribution(null, (double)a.dfbg, (double)a.dfwg);
        return 1.0 - fdist.cumulativeProbability(a.F);
    }

    private AnovaStats anovaStats(Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException {
        MathUtils.checkNotNull(categoryData);
        ArrayList<SummaryStatistics> categoryDataSummaryStatistics = new ArrayList<SummaryStatistics>(categoryData.size());
        for (double[] data : categoryData) {
            SummaryStatistics dataSummaryStatistics = new SummaryStatistics();
            categoryDataSummaryStatistics.add(dataSummaryStatistics);
            for (double val : data) {
                dataSummaryStatistics.addValue(val);
            }
        }
        return this.anovaStats(categoryDataSummaryStatistics, false);
    }

    public boolean anovaTest(Collection<double[]> categoryData, double alpha) throws NullArgumentException, DimensionMismatchException, OutOfRangeException, ConvergenceException, MaxCountExceededException {
        if (alpha <= 0.0 || alpha > 0.5) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, (Number)alpha, 0, 0.5);
        }
        return this.anovaPValue(categoryData) < alpha;
    }

    private AnovaStats anovaStats(Collection<SummaryStatistics> categoryData, boolean allowOneElementData) throws NullArgumentException, DimensionMismatchException {
        MathUtils.checkNotNull(categoryData);
        if (!allowOneElementData) {
            if (categoryData.size() < 2) {
                throw new DimensionMismatchException((Localizable)LocalizedFormats.TWO_OR_MORE_CATEGORIES_REQUIRED, categoryData.size(), 2);
            }
            for (SummaryStatistics array : categoryData) {
                if (array.getN() > 1L) continue;
                throw new DimensionMismatchException((Localizable)LocalizedFormats.TWO_OR_MORE_VALUES_IN_CATEGORY_REQUIRED, (int)array.getN(), 2);
            }
        }
        int dfwg = 0;
        double sswg = 0.0;
        double totsum = 0.0;
        double totsumsq = 0.0;
        int totnum = 0;
        for (SummaryStatistics data : categoryData) {
            double sum = data.getSum();
            double sumsq = data.getSumsq();
            int num = (int)data.getN();
            totnum += num;
            totsum += sum;
            totsumsq += sumsq;
            dfwg += num - 1;
            double ss = sumsq - sum * sum / (double)num;
            sswg += ss;
        }
        double sst = totsumsq - totsum * totsum / (double)totnum;
        double ssbg = sst - sswg;
        int dfbg = categoryData.size() - 1;
        double msbg = ssbg / (double)dfbg;
        double mswg = sswg / (double)dfwg;
        double F = msbg / mswg;
        return new AnovaStats(dfbg, dfwg, F);
    }

    private static class AnovaStats {
        private final int dfbg;
        private final int dfwg;
        private final double F;

        private AnovaStats(int dfbg, int dfwg, double F) {
            this.dfbg = dfbg;
            this.dfwg = dfwg;
            this.F = F;
        }
    }
}

