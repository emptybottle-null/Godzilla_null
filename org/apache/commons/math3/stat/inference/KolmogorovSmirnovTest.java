/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.inference;

import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.distribution.EnumeratedRealDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.fraction.FractionConversionException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class KolmogorovSmirnovTest {
    protected static final int MAXIMUM_PARTIAL_SUM_COUNT = 100000;
    protected static final double KS_SUM_CAUCHY_CRITERION = 1.0E-20;
    protected static final double PG_SUM_RELATIVE_ERROR = 1.0E-10;
    @Deprecated
    protected static final int SMALL_SAMPLE_PRODUCT = 200;
    protected static final int LARGE_SAMPLE_PRODUCT = 10000;
    @Deprecated
    protected static final int MONTE_CARLO_ITERATIONS = 1000000;
    private final RandomGenerator rng;

    public KolmogorovSmirnovTest() {
        this.rng = new Well19937c();
    }

    @Deprecated
    public KolmogorovSmirnovTest(RandomGenerator rng) {
        this.rng = rng;
    }

    public double kolmogorovSmirnovTest(RealDistribution distribution, double[] data, boolean exact) {
        return 1.0 - this.cdf(this.kolmogorovSmirnovStatistic(distribution, data), data.length, exact);
    }

    public double kolmogorovSmirnovStatistic(RealDistribution distribution, double[] data) {
        this.checkArray(data);
        int n = data.length;
        double nd = n;
        double[] dataCopy = new double[n];
        System.arraycopy(data, 0, dataCopy, 0, n);
        Arrays.sort(dataCopy);
        double d = 0.0;
        for (int i = 1; i <= n; ++i) {
            double yi = distribution.cumulativeProbability(dataCopy[i - 1]);
            double currD = FastMath.max(yi - (double)(i - 1) / nd, (double)i / nd - yi);
            if (!(currD > d)) continue;
            d = currD;
        }
        return d;
    }

    public double kolmogorovSmirnovTest(double[] x, double[] y, boolean strict) {
        long lengthProduct = (long)x.length * (long)y.length;
        double[] xa = null;
        double[] ya = null;
        if (lengthProduct < 10000L && KolmogorovSmirnovTest.hasTies(x, y)) {
            xa = MathArrays.copyOf(x);
            ya = MathArrays.copyOf(y);
            KolmogorovSmirnovTest.fixTies(xa, ya);
        } else {
            xa = x;
            ya = y;
        }
        if (lengthProduct < 10000L) {
            return this.exactP(this.kolmogorovSmirnovStatistic(xa, ya), x.length, y.length, strict);
        }
        return this.approximateP(this.kolmogorovSmirnovStatistic(x, y), x.length, y.length);
    }

    public double kolmogorovSmirnovTest(double[] x, double[] y) {
        return this.kolmogorovSmirnovTest(x, y, true);
    }

    public double kolmogorovSmirnovStatistic(double[] x, double[] y) {
        return (double)this.integralKolmogorovSmirnovStatistic(x, y) / (double)((long)x.length * (long)y.length);
    }

    private long integralKolmogorovSmirnovStatistic(double[] x, double[] y) {
        this.checkArray(x);
        this.checkArray(y);
        double[] sx = MathArrays.copyOf(x);
        double[] sy = MathArrays.copyOf(y);
        Arrays.sort(sx);
        Arrays.sort(sy);
        int n = sx.length;
        int m = sy.length;
        int rankX = 0;
        int rankY = 0;
        long curD = 0L;
        long supD = 0L;
        do {
            double z;
            double d = z = Double.compare(sx[rankX], sy[rankY]) <= 0 ? sx[rankX] : sy[rankY];
            while (rankX < n && Double.compare(sx[rankX], z) == 0) {
                ++rankX;
                curD += (long)m;
            }
            while (rankY < m && Double.compare(sy[rankY], z) == 0) {
                ++rankY;
                curD -= (long)n;
            }
            if (curD > supD) {
                supD = curD;
                continue;
            }
            if (-curD <= supD) continue;
            supD = -curD;
        } while (rankX < n && rankY < m);
        return supD;
    }

    public double kolmogorovSmirnovTest(RealDistribution distribution, double[] data) {
        return this.kolmogorovSmirnovTest(distribution, data, false);
    }

    public boolean kolmogorovSmirnovTest(RealDistribution distribution, double[] data, double alpha) {
        if (alpha <= 0.0 || alpha > 0.5) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, (Number)alpha, 0, 0.5);
        }
        return this.kolmogorovSmirnovTest(distribution, data) < alpha;
    }

    public double bootstrap(double[] x, double[] y, int iterations, boolean strict) {
        int xLength = x.length;
        int yLength = y.length;
        double[] combined = new double[xLength + yLength];
        System.arraycopy(x, 0, combined, 0, xLength);
        System.arraycopy(y, 0, combined, xLength, yLength);
        EnumeratedRealDistribution dist = new EnumeratedRealDistribution(this.rng, combined);
        long d = this.integralKolmogorovSmirnovStatistic(x, y);
        int greaterCount = 0;
        int equalCount = 0;
        for (int i = 0; i < iterations; ++i) {
            double[] curY;
            double[] curX = dist.sample(xLength);
            long curD = this.integralKolmogorovSmirnovStatistic(curX, curY = dist.sample(yLength));
            if (curD > d) {
                ++greaterCount;
                continue;
            }
            if (curD != d) continue;
            ++equalCount;
        }
        return strict ? (double)greaterCount / (double)iterations : (double)(greaterCount + equalCount) / (double)iterations;
    }

    public double bootstrap(double[] x, double[] y, int iterations) {
        return this.bootstrap(x, y, iterations, true);
    }

    public double cdf(double d, int n) throws MathArithmeticException {
        return this.cdf(d, n, false);
    }

    public double cdfExact(double d, int n) throws MathArithmeticException {
        return this.cdf(d, n, true);
    }

    public double cdf(double d, int n, boolean exact) throws MathArithmeticException {
        double ninv = 1.0 / (double)n;
        double ninvhalf = 0.5 * ninv;
        if (d <= ninvhalf) {
            return 0.0;
        }
        if (ninvhalf < d && d <= ninv) {
            double res = 1.0;
            double f = 2.0 * d - ninv;
            for (int i = 1; i <= n; ++i) {
                res *= (double)i * f;
            }
            return res;
        }
        if (1.0 - ninv <= d && d < 1.0) {
            return 1.0 - 2.0 * Math.pow(1.0 - d, n);
        }
        if (1.0 <= d) {
            return 1.0;
        }
        if (exact) {
            return this.exactK(d, n);
        }
        if (n <= 140) {
            return this.roundedK(d, n);
        }
        return this.pelzGood(d, n);
    }

    private double exactK(double d, int n) throws MathArithmeticException {
        int k = (int)Math.ceil((double)n * d);
        FieldMatrix<BigFraction> H = this.createExactH(d, n);
        FieldMatrix<BigFraction> Hpower = H.power(n);
        BigFraction pFrac = Hpower.getEntry(k - 1, k - 1);
        for (int i = 1; i <= n; ++i) {
            pFrac = pFrac.multiply(i).divide(n);
        }
        return pFrac.bigDecimalValue(20, 4).doubleValue();
    }

    private double roundedK(double d, int n) {
        int k = (int)Math.ceil((double)n * d);
        RealMatrix H = this.createRoundedH(d, n);
        RealMatrix Hpower = H.power(n);
        double pFrac = Hpower.getEntry(k - 1, k - 1);
        for (int i = 1; i <= n; ++i) {
            pFrac *= (double)i / (double)n;
        }
        return pFrac;
    }

    public double pelzGood(double d, int n) {
        int k;
        double sqrtN = FastMath.sqrt(n);
        double z = d * sqrtN;
        double z2 = d * d * (double)n;
        double z4 = z2 * z2;
        double z6 = z4 * z2;
        double z8 = z4 * z4;
        double ret = 0.0;
        double sum = 0.0;
        double increment = 0.0;
        double kTerm = 0.0;
        double z2Term = Math.PI * Math.PI / (8.0 * z2);
        for (k = 1; k < 100000 && !((increment = FastMath.exp(-z2Term * (kTerm = (double)(2 * k - 1)) * kTerm)) <= 1.0E-10 * (sum += increment)); ++k) {
        }
        if (k == 100000) {
            throw new TooManyIterationsException(100000);
        }
        ret = sum * FastMath.sqrt(Math.PI * 2) / z;
        double twoZ2 = 2.0 * z2;
        sum = 0.0;
        kTerm = 0.0;
        double kTerm2 = 0.0;
        for (k = 0; k < 100000; ++k) {
            kTerm = (double)k + 0.5;
            kTerm2 = kTerm * kTerm;
            increment = (Math.PI * Math.PI * kTerm2 - z2) * FastMath.exp(-9.869604401089358 * kTerm2 / twoZ2);
            if (FastMath.abs(increment) < 1.0E-10 * FastMath.abs(sum += increment)) break;
        }
        if (k == 100000) {
            throw new TooManyIterationsException(100000);
        }
        double sqrtHalfPi = FastMath.sqrt(1.5707963267948966);
        ret += sum * sqrtHalfPi / (3.0 * z4 * sqrtN);
        double z4Term = 2.0 * z4;
        double z6Term = 6.0 * z6;
        z2Term = 5.0 * z2;
        double pi4 = 97.40909103400243;
        sum = 0.0;
        kTerm = 0.0;
        kTerm2 = 0.0;
        for (k = 0; k < 100000; ++k) {
            kTerm = (double)k + 0.5;
            kTerm2 = kTerm * kTerm;
            increment = (z6Term + z4Term + Math.PI * Math.PI * (z4Term - z2Term) * kTerm2 + 97.40909103400243 * (1.0 - twoZ2) * kTerm2 * kTerm2) * FastMath.exp(-9.869604401089358 * kTerm2 / twoZ2);
            if (FastMath.abs(increment) < 1.0E-10 * FastMath.abs(sum += increment)) break;
        }
        if (k == 100000) {
            throw new TooManyIterationsException(100000);
        }
        double sum2 = 0.0;
        kTerm2 = 0.0;
        for (k = 1; k < 100000; ++k) {
            kTerm2 = k * k;
            increment = Math.PI * Math.PI * kTerm2 * FastMath.exp(-9.869604401089358 * kTerm2 / twoZ2);
            if (FastMath.abs(increment) < 1.0E-10 * FastMath.abs(sum2 += increment)) break;
        }
        if (k == 100000) {
            throw new TooManyIterationsException(100000);
        }
        ret += sqrtHalfPi / (double)n * (sum / (36.0 * z2 * z2 * z2 * z) - sum2 / (18.0 * z2 * z));
        double pi6 = 961.3891935753043;
        sum = 0.0;
        double kTerm4 = 0.0;
        double kTerm6 = 0.0;
        for (k = 0; k < 100000; ++k) {
            kTerm = (double)k + 0.5;
            kTerm2 = kTerm * kTerm;
            kTerm4 = kTerm2 * kTerm2;
            kTerm6 = kTerm4 * kTerm2;
            increment = (961.3891935753043 * kTerm6 * (5.0 - 30.0 * z2) + 97.40909103400243 * kTerm4 * (-60.0 * z2 + 212.0 * z4) + Math.PI * Math.PI * kTerm2 * (135.0 * z4 - 96.0 * z6) - 30.0 * z6 - 90.0 * z8) * FastMath.exp(-9.869604401089358 * kTerm2 / twoZ2);
            if (FastMath.abs(increment) < 1.0E-10 * FastMath.abs(sum += increment)) break;
        }
        if (k == 100000) {
            throw new TooManyIterationsException(100000);
        }
        sum2 = 0.0;
        for (k = 1; k < 100000; ++k) {
            kTerm2 = k * k;
            kTerm4 = kTerm2 * kTerm2;
            increment = (-97.40909103400243 * kTerm4 + 29.608813203268074 * kTerm2 * z2) * FastMath.exp(-9.869604401089358 * kTerm2 / twoZ2);
            if (FastMath.abs(increment) < 1.0E-10 * FastMath.abs(sum2 += increment)) break;
        }
        if (k == 100000) {
            throw new TooManyIterationsException(100000);
        }
        return ret + sqrtHalfPi / (sqrtN * (double)n) * (sum / (3240.0 * z6 * z4) + sum2 / (108.0 * z6));
    }

    private FieldMatrix<BigFraction> createExactH(double d, int n) throws NumberIsTooLargeException, FractionConversionException {
        int i;
        int k = (int)Math.ceil((double)n * d);
        int m = 2 * k - 1;
        double hDouble = (double)k - (double)n * d;
        if (hDouble >= 1.0) {
            throw new NumberIsTooLargeException(hDouble, (Number)1.0, false);
        }
        BigFraction h = null;
        try {
            h = new BigFraction(hDouble, 1.0E-20, 10000);
        } catch (FractionConversionException e1) {
            try {
                h = new BigFraction(hDouble, 1.0E-10, 10000);
            } catch (FractionConversionException e2) {
                h = new BigFraction(hDouble, 1.0E-5, 10000);
            }
        }
        FieldElement[][] Hdata = new BigFraction[m][m];
        for (int i2 = 0; i2 < m; ++i2) {
            for (int j = 0; j < m; ++j) {
                Hdata[i2][j] = i2 - j + 1 < 0 ? BigFraction.ZERO : BigFraction.ONE;
            }
        }
        BigFraction[] hPowers = new BigFraction[m];
        hPowers[0] = h;
        for (i = 1; i < m; ++i) {
            hPowers[i] = h.multiply(hPowers[i - 1]);
        }
        for (i = 0; i < m; ++i) {
            Hdata[i][0] = Hdata[i][0].subtract(hPowers[i]);
            Hdata[m - 1][i] = ((BigFraction)Hdata[m - 1][i]).subtract(hPowers[m - i - 1]);
        }
        if (h.compareTo(BigFraction.ONE_HALF) == 1) {
            Hdata[m - 1][0] = ((BigFraction)Hdata[m - 1][0]).add(h.multiply(2).subtract(1).pow(m));
        }
        for (i = 0; i < m; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                if (i - j + 1 <= 0) continue;
                for (int g = 2; g <= i - j + 1; ++g) {
                    Hdata[i][j] = ((BigFraction)Hdata[i][j]).divide(g);
                }
            }
        }
        return new Array2DRowFieldMatrix((Field)BigFractionField.getInstance(), Hdata);
    }

    private RealMatrix createRoundedH(double d, int n) throws NumberIsTooLargeException {
        int i;
        int k = (int)Math.ceil((double)n * d);
        int m = 2 * k - 1;
        double h = (double)k - (double)n * d;
        if (h >= 1.0) {
            throw new NumberIsTooLargeException(h, (Number)1.0, false);
        }
        double[][] Hdata = new double[m][m];
        for (int i2 = 0; i2 < m; ++i2) {
            for (int j = 0; j < m; ++j) {
                Hdata[i2][j] = i2 - j + 1 < 0 ? 0.0 : 1.0;
            }
        }
        double[] hPowers = new double[m];
        hPowers[0] = h;
        for (i = 1; i < m; ++i) {
            hPowers[i] = h * hPowers[i - 1];
        }
        for (i = 0; i < m; ++i) {
            Hdata[i][0] = Hdata[i][0] - hPowers[i];
            double[] dArray = Hdata[m - 1];
            int n2 = i;
            dArray[n2] = dArray[n2] - hPowers[m - i - 1];
        }
        if (Double.compare(h, 0.5) > 0) {
            double[] dArray = Hdata[m - 1];
            dArray[0] = dArray[0] + FastMath.pow(2.0 * h - 1.0, m);
        }
        for (i = 0; i < m; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                if (i - j + 1 <= 0) continue;
                for (int g = 2; g <= i - j + 1; ++g) {
                    double[] dArray = Hdata[i];
                    int n3 = j;
                    dArray[n3] = dArray[n3] / (double)g;
                }
            }
        }
        return MatrixUtils.createRealMatrix(Hdata);
    }

    private void checkArray(double[] array) {
        if (array == null) {
            throw new NullArgumentException(LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
        }
        if (array.length < 2) {
            throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, array.length, 2);
        }
    }

    public double ksSum(double t, double tolerance, int maxIterations) {
        long i;
        if (t == 0.0) {
            return 0.0;
        }
        double x = -2.0 * t * t;
        int sign = -1;
        double partialSum = 0.5;
        double delta = 1.0;
        for (i = 1L; delta > tolerance && i < (long)maxIterations; ++i) {
            delta = FastMath.exp(x * (double)i * (double)i);
            partialSum += (double)sign * delta;
            sign *= -1;
        }
        if (i == (long)maxIterations) {
            throw new TooManyIterationsException(maxIterations);
        }
        return partialSum * 2.0;
    }

    private static long calculateIntegralD(double d, int n, int m, boolean strict) {
        double tol = 1.0E-12;
        long nm = (long)n * (long)m;
        long upperBound = (long)FastMath.ceil((d - 1.0E-12) * (double)nm);
        long lowerBound = (long)FastMath.floor((d + 1.0E-12) * (double)nm);
        if (strict && lowerBound == upperBound) {
            return upperBound + 1L;
        }
        return upperBound;
    }

    public double exactP(double d, int n, int m, boolean strict) {
        return 1.0 - KolmogorovSmirnovTest.n(m, n, m, n, KolmogorovSmirnovTest.calculateIntegralD(d, m, n, strict), strict) / CombinatoricsUtils.binomialCoefficientDouble(n + m, m);
    }

    public double approximateP(double d, int n, int m) {
        double dm = m;
        double dn = n;
        return 1.0 - this.ksSum(d * FastMath.sqrt(dm * dn / (dm + dn)), 1.0E-20, 100000);
    }

    static void fillBooleanArrayRandomlyWithFixedNumberTrueValues(boolean[] b, int numberOfTrueValues, RandomGenerator rng) {
        Arrays.fill(b, true);
        for (int k = numberOfTrueValues; k < b.length; ++k) {
            b[b[r = rng.nextInt((int)(k + 1))] ? r : k] = false;
        }
    }

    public double monteCarloP(double d, int n, int m, boolean strict, int iterations) {
        return this.integralMonteCarloP(KolmogorovSmirnovTest.calculateIntegralD(d, n, m, strict), n, m, iterations);
    }

    private double integralMonteCarloP(long d, int n, int m, int iterations) {
        int nn = FastMath.max(n, m);
        int mm = FastMath.min(n, m);
        int sum = nn + mm;
        int tail = 0;
        boolean[] b = new boolean[sum];
        block0: for (int i = 0; i < iterations; ++i) {
            KolmogorovSmirnovTest.fillBooleanArrayRandomlyWithFixedNumberTrueValues(b, nn, this.rng);
            long curD = 0L;
            for (int j = 0; j < b.length; ++j) {
                if (b[j]) {
                    if ((curD += (long)mm) < d) continue;
                    ++tail;
                    continue block0;
                }
                if ((curD -= (long)nn) > -d) continue;
                ++tail;
                continue block0;
            }
        }
        return (double)tail / (double)iterations;
    }

    private static void fixTies(double[] x, double[] y) {
        double[] values = MathArrays.unique(MathArrays.concatenate(x, y));
        if (values.length == x.length + y.length) {
            return;
        }
        double minDelta = 1.0;
        double prev = values[0];
        double delta = 1.0;
        for (int i = 1; i < values.length; ++i) {
            delta = prev - values[i];
            if (delta < minDelta) {
                minDelta = delta;
            }
            prev = values[i];
        }
        UniformRealDistribution dist = new UniformRealDistribution(new JDKRandomGenerator(100), -(minDelta /= 2.0), minDelta);
        int ct = 0;
        boolean ties = true;
        do {
            KolmogorovSmirnovTest.jitter(x, dist);
            KolmogorovSmirnovTest.jitter(y, dist);
        } while ((ties = KolmogorovSmirnovTest.hasTies(x, y)) && ++ct < 1000);
        if (ties) {
            throw new MathInternalError();
        }
    }

    private static boolean hasTies(double[] x, double[] y) {
        int i;
        HashSet<Double> values = new HashSet<Double>();
        for (i = 0; i < x.length; ++i) {
            if (values.add(x[i])) continue;
            return true;
        }
        for (i = 0; i < y.length; ++i) {
            if (values.add(y[i])) continue;
            return true;
        }
        return false;
    }

    private static void jitter(double[] data, RealDistribution dist) {
        int i = 0;
        while (i < data.length) {
            int n = i++;
            data[n] = data[n] + dist.sample();
        }
    }

    private static int c(int i, int j, int m, int n, long cmn, boolean strict) {
        if (strict) {
            return FastMath.abs((long)i * (long)n - (long)j * (long)m) <= cmn ? 1 : 0;
        }
        return FastMath.abs((long)i * (long)n - (long)j * (long)m) < cmn ? 1 : 0;
    }

    private static double n(int i, int j, int m, int n, long cnm, boolean strict) {
        int k;
        double[] lag = new double[n];
        double last = 0.0;
        for (k = 0; k < n; ++k) {
            lag[k] = KolmogorovSmirnovTest.c(0, k + 1, m, n, cnm, strict);
        }
        for (k = 1; k <= i; ++k) {
            last = KolmogorovSmirnovTest.c(k, 0, m, n, cnm, strict);
            for (int l = 1; l <= j; ++l) {
                lag[l - 1] = (double)KolmogorovSmirnovTest.c(k, l, m, n, cnm, strict) * (last + lag[l - 1]);
                last = lag[l - 1];
            }
        }
        return last;
    }
}

