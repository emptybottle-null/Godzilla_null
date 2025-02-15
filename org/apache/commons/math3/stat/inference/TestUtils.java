/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.inference;

import java.util.Collection;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.apache.commons.math3.stat.inference.GTest;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;
import org.apache.commons.math3.stat.inference.OneWayAnova;
import org.apache.commons.math3.stat.inference.TTest;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TestUtils {
    private static final TTest T_TEST = new TTest();
    private static final ChiSquareTest CHI_SQUARE_TEST = new ChiSquareTest();
    private static final OneWayAnova ONE_WAY_ANANOVA = new OneWayAnova();
    private static final GTest G_TEST = new GTest();
    private static final KolmogorovSmirnovTest KS_TEST = new KolmogorovSmirnovTest();

    private TestUtils() {
    }

    public static double homoscedasticT(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.homoscedasticT(sample1, sample2);
    }

    public static double homoscedasticT(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.homoscedasticT(sampleStats1, sampleStats2);
    }

    public static boolean homoscedasticTTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.homoscedasticTTest(sample1, sample2, alpha);
    }

    public static double homoscedasticTTest(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.homoscedasticTTest(sample1, sample2);
    }

    public static double homoscedasticTTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.homoscedasticTTest(sampleStats1, sampleStats2);
    }

    public static double pairedT(double[] sample1, double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException {
        return T_TEST.pairedT(sample1, sample2);
    }

    public static boolean pairedTTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.pairedTTest(sample1, sample2, alpha);
    }

    public static double pairedTTest(double[] sample1, double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.pairedTTest(sample1, sample2);
    }

    public static double t(double mu, double[] observed) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.t(mu, observed);
    }

    public static double t(double mu, StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.t(mu, sampleStats);
    }

    public static double t(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.t(sample1, sample2);
    }

    public static double t(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
        return T_TEST.t(sampleStats1, sampleStats2);
    }

    public static boolean tTest(double mu, double[] sample, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.tTest(mu, sample, alpha);
    }

    public static double tTest(double mu, double[] sample) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.tTest(mu, sample);
    }

    public static boolean tTest(double mu, StatisticalSummary sampleStats, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.tTest(mu, sampleStats, alpha);
    }

    public static double tTest(double mu, StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.tTest(mu, sampleStats);
    }

    public static boolean tTest(double[] sample1, double[] sample2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.tTest(sample1, sample2, alpha);
    }

    public static double tTest(double[] sample1, double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.tTest(sample1, sample2);
    }

    public static boolean tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2, double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        return T_TEST.tTest(sampleStats1, sampleStats2, alpha);
    }

    public static double tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        return T_TEST.tTest(sampleStats1, sampleStats2);
    }

    public static double chiSquare(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        return CHI_SQUARE_TEST.chiSquare(expected, observed);
    }

    public static double chiSquare(long[][] counts) throws NullArgumentException, NotPositiveException, DimensionMismatchException {
        return CHI_SQUARE_TEST.chiSquare(counts);
    }

    public static boolean chiSquareTest(double[] expected, long[] observed, double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTest(expected, observed, alpha);
    }

    public static double chiSquareTest(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTest(expected, observed);
    }

    public static boolean chiSquareTest(long[][] counts, double alpha) throws NullArgumentException, DimensionMismatchException, NotPositiveException, OutOfRangeException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTest(counts, alpha);
    }

    public static double chiSquareTest(long[][] counts) throws NullArgumentException, DimensionMismatchException, NotPositiveException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTest(counts);
    }

    public static double chiSquareDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        return CHI_SQUARE_TEST.chiSquareDataSetsComparison(observed1, observed2);
    }

    public static double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTestDataSetsComparison(observed1, observed2);
    }

    public static boolean chiSquareTestDataSetsComparison(long[] observed1, long[] observed2, double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        return CHI_SQUARE_TEST.chiSquareTestDataSetsComparison(observed1, observed2, alpha);
    }

    public static double oneWayAnovaFValue(Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException {
        return ONE_WAY_ANANOVA.anovaFValue(categoryData);
    }

    public static double oneWayAnovaPValue(Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
        return ONE_WAY_ANANOVA.anovaPValue(categoryData);
    }

    public static boolean oneWayAnovaTest(Collection<double[]> categoryData, double alpha) throws NullArgumentException, DimensionMismatchException, OutOfRangeException, ConvergenceException, MaxCountExceededException {
        return ONE_WAY_ANANOVA.anovaTest(categoryData, alpha);
    }

    public static double g(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        return G_TEST.g(expected, observed);
    }

    public static double gTest(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return G_TEST.gTest(expected, observed);
    }

    public static double gTestIntrinsic(double[] expected, long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return G_TEST.gTestIntrinsic(expected, observed);
    }

    public static boolean gTest(double[] expected, long[] observed, double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        return G_TEST.gTest(expected, observed, alpha);
    }

    public static double gDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        return G_TEST.gDataSetsComparison(observed1, observed2);
    }

    public static double rootLogLikelihoodRatio(long k11, long k12, long k21, long k22) throws DimensionMismatchException, NotPositiveException, ZeroException {
        return G_TEST.rootLogLikelihoodRatio(k11, k12, k21, k22);
    }

    public static double gTestDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        return G_TEST.gTestDataSetsComparison(observed1, observed2);
    }

    public static boolean gTestDataSetsComparison(long[] observed1, long[] observed2, double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        return G_TEST.gTestDataSetsComparison(observed1, observed2, alpha);
    }

    public static double kolmogorovSmirnovStatistic(RealDistribution dist, double[] data) throws InsufficientDataException, NullArgumentException {
        return KS_TEST.kolmogorovSmirnovStatistic(dist, data);
    }

    public static double kolmogorovSmirnovTest(RealDistribution dist, double[] data) throws InsufficientDataException, NullArgumentException {
        return KS_TEST.kolmogorovSmirnovTest(dist, data);
    }

    public static double kolmogorovSmirnovTest(RealDistribution dist, double[] data, boolean strict) throws InsufficientDataException, NullArgumentException {
        return KS_TEST.kolmogorovSmirnovTest(dist, data, strict);
    }

    public static boolean kolmogorovSmirnovTest(RealDistribution dist, double[] data, double alpha) throws InsufficientDataException, NullArgumentException {
        return KS_TEST.kolmogorovSmirnovTest(dist, data, alpha);
    }

    public static double kolmogorovSmirnovStatistic(double[] x, double[] y) throws InsufficientDataException, NullArgumentException {
        return KS_TEST.kolmogorovSmirnovStatistic(x, y);
    }

    public static double kolmogorovSmirnovTest(double[] x, double[] y) throws InsufficientDataException, NullArgumentException {
        return KS_TEST.kolmogorovSmirnovTest(x, y);
    }

    public static double kolmogorovSmirnovTest(double[] x, double[] y, boolean strict) throws InsufficientDataException, NullArgumentException {
        return KS_TEST.kolmogorovSmirnovTest(x, y, strict);
    }

    public static double exactP(double d, int m, int n, boolean strict) {
        return KS_TEST.exactP(d, n, m, strict);
    }

    public static double approximateP(double d, int n, int m) {
        return KS_TEST.approximateP(d, n, m);
    }

    public static double monteCarloP(double d, int n, int m, boolean strict, int iterations) {
        return KS_TEST.monteCarloP(d, n, m, strict, iterations);
    }
}

