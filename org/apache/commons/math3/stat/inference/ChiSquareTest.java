/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class ChiSquareTest {
    public double chiSquare(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
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
        double sumSq = 0.0;
        for (int i = 0; i < observed.length; ++i) {
            double dev;
            if (rescale) {
                dev = (double)observed[i] - ratio * expected[i];
                sumSq += dev * dev / (ratio * expected[i]);
                continue;
            }
            dev = (double)observed[i] - expected[i];
            sumSq += dev * dev / expected[i];
        }
        return sumSq;
    }

    public double chiSquareTest(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        ChiSquaredDistribution distribution = new ChiSquaredDistribution(null, (double)expected.length - 1.0);
        return 1.0 - distribution.cumulativeProbability(this.chiSquare(expected, observed));
    }

    public boolean chiSquareTest(double[] expected, long[] observed, double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        if (alpha <= 0.0 || alpha > 0.5) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, (Number)alpha, 0, 0.5);
        }
        return this.chiSquareTest(expected, observed) < alpha;
    }

    public double chiSquare(long[][] counts) throws NullArgumentException, NotPositiveException, DimensionMismatchException {
        this.checkArray(counts);
        int nRows = counts.length;
        int nCols = counts[0].length;
        double[] rowSum = new double[nRows];
        double[] colSum = new double[nCols];
        double total = 0.0;
        for (int row = 0; row < nRows; ++row) {
            for (int col = 0; col < nCols; ++col) {
                int n = row;
                rowSum[n] = rowSum[n] + (double)counts[row][col];
                int n2 = col;
                colSum[n2] = colSum[n2] + (double)counts[row][col];
                total += (double)counts[row][col];
            }
        }
        double sumSq = 0.0;
        double expected = 0.0;
        for (int row = 0; row < nRows; ++row) {
            for (int col = 0; col < nCols; ++col) {
                expected = rowSum[row] * colSum[col] / total;
                sumSq += ((double)counts[row][col] - expected) * ((double)counts[row][col] - expected) / expected;
            }
        }
        return sumSq;
    }

    public double chiSquareTest(long[][] counts) throws NullArgumentException, DimensionMismatchException, NotPositiveException, MaxCountExceededException {
        this.checkArray(counts);
        double df = ((double)counts.length - 1.0) * ((double)counts[0].length - 1.0);
        ChiSquaredDistribution distribution = new ChiSquaredDistribution(df);
        return 1.0 - distribution.cumulativeProbability(this.chiSquare(counts));
    }

    public boolean chiSquareTest(long[][] counts, double alpha) throws NullArgumentException, DimensionMismatchException, NotPositiveException, OutOfRangeException, MaxCountExceededException {
        if (alpha <= 0.0 || alpha > 0.5) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, (Number)alpha, 0, 0.5);
        }
        return this.chiSquareTest(counts) < alpha;
    }

    public double chiSquareDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
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
        boolean unequalCounts = false;
        double weight = 0.0;
        for (int i = 0; i < observed1.length; ++i) {
            countSum1 += observed1[i];
            countSum2 += observed2[i];
        }
        if (countSum1 == 0L || countSum2 == 0L) {
            throw new ZeroException();
        }
        boolean bl = unequalCounts = countSum1 != countSum2;
        if (unequalCounts) {
            weight = FastMath.sqrt((double)countSum1 / (double)countSum2);
        }
        double sumSq = 0.0;
        double dev = 0.0;
        double obs1 = 0.0;
        double obs2 = 0.0;
        for (int i = 0; i < observed1.length; ++i) {
            if (observed1[i] == 0L && observed2[i] == 0L) {
                throw new ZeroException((Localizable)LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, i);
            }
            obs1 = observed1[i];
            obs2 = observed2[i];
            dev = unequalCounts ? obs1 / weight - obs2 * weight : obs1 - obs2;
            sumSq += dev * dev / (obs1 + obs2);
        }
        return sumSq;
    }

    public double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        ChiSquaredDistribution distribution = new ChiSquaredDistribution(null, (double)observed1.length - 1.0);
        return 1.0 - distribution.cumulativeProbability(this.chiSquareDataSetsComparison(observed1, observed2));
    }

    public boolean chiSquareTestDataSetsComparison(long[] observed1, long[] observed2, double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        if (alpha <= 0.0 || alpha > 0.5) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, (Number)alpha, 0, 0.5);
        }
        return this.chiSquareTestDataSetsComparison(observed1, observed2) < alpha;
    }

    private void checkArray(long[][] in) throws NullArgumentException, DimensionMismatchException, NotPositiveException {
        if (in.length < 2) {
            throw new DimensionMismatchException(in.length, 2);
        }
        if (in[0].length < 2) {
            throw new DimensionMismatchException(in[0].length, 2);
        }
        MathArrays.checkRectangular(in);
        MathArrays.checkNonNegative(in);
    }
}

