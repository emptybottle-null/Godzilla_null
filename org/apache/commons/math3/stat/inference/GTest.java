/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class GTest {
    public double g(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        if (expected.length < 2) {
            throw new DimensionMismatchException(expected.length, 2);
        }
        if (expected.length != observed.length) {
            throw new DimensionMismatchException(expected.length, observed.length);
        }
        MathArrays.checkPositive(expected);
        MathArrays.checkNonNegative(observed);
        double sumExpected = 0.0;
        double sumObserved = 0.0;
        for (int i = 0; i < observed.length; ++i) {
            sumExpected += expected[i];
            sumObserved += (double)observed[i];
        }
        double ratio = 1.0;
        boolean rescale = false;
        if (FastMath.abs(sumExpected - sumObserved) > 1.0E-5) {
            ratio = sumObserved / sumExpected;
            rescale = true;
        }
        double sum = 0.0;
        for (int i = 0; i < observed.length; ++i) {
            double dev = rescale ? FastMath.log((double)observed[i] / (ratio * expected[i])) : FastMath.log((double)observed[i] / expected[i]);
            sum += (double)observed[i] * dev;
        }
        return 2.0 * sum;
    }

    public double gTest(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        ChiSquaredDistribution distribution = new ChiSquaredDistribution(null, (double)expected.length - 1.0);
        return 1.0 - distribution.cumulativeProbability(this.g(expected, observed));
    }

    public double gTestIntrinsic(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        ChiSquaredDistribution distribution = new ChiSquaredDistribution(null, (double)expected.length - 2.0);
        return 1.0 - distribution.cumulativeProbability(this.g(expected, observed));
    }

    public boolean gTest(double[] expected, long[] observed, double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        if (alpha <= 0.0 || alpha > 0.5) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, (Number)alpha, 0, 0.5);
        }
        return this.gTest(expected, observed) < alpha;
    }

    private double entropy(long[][] k) {
        int j;
        int i;
        double h = 0.0;
        double sum_k = 0.0;
        for (i = 0; i < k.length; ++i) {
            for (j = 0; j < k[i].length; ++j) {
                sum_k += (double)k[i][j];
            }
        }
        for (i = 0; i < k.length; ++i) {
            for (j = 0; j < k[i].length; ++j) {
                if (k[i][j] == 0L) continue;
                double p_ij = (double)k[i][j] / sum_k;
                h += p_ij * FastMath.log(p_ij);
            }
        }
        return -h;
    }

    private double entropy(long[] k) {
        int i;
        double h = 0.0;
        double sum_k = 0.0;
        for (i = 0; i < k.length; ++i) {
            sum_k += (double)k[i];
        }
        for (i = 0; i < k.length; ++i) {
            if (k[i] == 0L) continue;
            double p_i = (double)k[i] / sum_k;
            h += p_i * FastMath.log(p_i);
        }
        return -h;
    }

    public double gDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        if (observed1.length < 2) {
            throw new DimensionMismatchException(observed1.length, 2);
        }
        if (observed1.length != observed2.length) {
            throw new DimensionMismatchException(observed1.length, observed2.length);
        }
        MathArrays.checkNonNegative(observed1);
        MathArrays.checkNonNegative(observed2);
        long countSum1 = 0L;
        long countSum2 = 0L;
        long[] collSums = new long[observed1.length];
        long[][] k = new long[2][observed1.length];
        for (int i = 0; i < observed1.length; ++i) {
            if (observed1[i] == 0L && observed2[i] == 0L) {
                throw new ZeroException((Localizable)LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, i);
            }
            countSum1 += observed1[i];
            countSum2 += observed2[i];
            collSums[i] = observed1[i] + observed2[i];
            k[0][i] = observed1[i];
            k[1][i] = observed2[i];
        }
        if (countSum1 == 0L || countSum2 == 0L) {
            throw new ZeroException();
        }
        long[] rowSums = new long[]{countSum1, countSum2};
        double sum = (double)countSum1 + (double)countSum2;
        return 2.0 * sum * (this.entropy(rowSums) + this.entropy(collSums) - this.entropy(k));
    }

    public double rootLogLikelihoodRatio(long k11, long k12, long k21, long k22) {
        double llr = this.gDataSetsComparison(new long[]{k11, k12}, new long[]{k21, k22});
        double sqrt = FastMath.sqrt(llr);
        if ((double)k11 / (double)(k11 + k12) < (double)k21 / (double)(k21 + k22)) {
            sqrt = -sqrt;
        }
        return sqrt;
    }

    public double gTestDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        ChiSquaredDistribution distribution = new ChiSquaredDistribution(null, (double)observed1.length - 1.0);
        return 1.0 - distribution.cumulativeProbability(this.gDataSetsComparison(observed1, observed2));
    }

    public boolean gTestDataSetsComparison(long[] observed1, long[] observed2, double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        if (alpha <= 0.0 || alpha > 0.5) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, (Number)alpha, 0, 0.5);
        }
        return this.gTestDataSetsComparison(observed1, observed2) < alpha;
    }
}

