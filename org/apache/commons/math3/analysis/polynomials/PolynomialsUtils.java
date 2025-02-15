/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.polynomials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PolynomialsUtils {
    private static final List<BigFraction> CHEBYSHEV_COEFFICIENTS = new ArrayList<BigFraction>();
    private static final List<BigFraction> HERMITE_COEFFICIENTS;
    private static final List<BigFraction> LAGUERRE_COEFFICIENTS;
    private static final List<BigFraction> LEGENDRE_COEFFICIENTS;
    private static final Map<JacobiKey, List<BigFraction>> JACOBI_COEFFICIENTS;

    private PolynomialsUtils() {
    }

    public static PolynomialFunction createChebyshevPolynomial(int degree) {
        return PolynomialsUtils.buildPolynomial(degree, CHEBYSHEV_COEFFICIENTS, new RecurrenceCoefficientsGenerator(){
            private final BigFraction[] coeffs = new BigFraction[]{BigFraction.ZERO, BigFraction.TWO, BigFraction.ONE};

            public BigFraction[] generate(int k) {
                return this.coeffs;
            }
        });
    }

    public static PolynomialFunction createHermitePolynomial(int degree) {
        return PolynomialsUtils.buildPolynomial(degree, HERMITE_COEFFICIENTS, new RecurrenceCoefficientsGenerator(){

            public BigFraction[] generate(int k) {
                return new BigFraction[]{BigFraction.ZERO, BigFraction.TWO, new BigFraction(2 * k)};
            }
        });
    }

    public static PolynomialFunction createLaguerrePolynomial(int degree) {
        return PolynomialsUtils.buildPolynomial(degree, LAGUERRE_COEFFICIENTS, new RecurrenceCoefficientsGenerator(){

            public BigFraction[] generate(int k) {
                int kP1 = k + 1;
                return new BigFraction[]{new BigFraction(2 * k + 1, kP1), new BigFraction(-1, kP1), new BigFraction(k, kP1)};
            }
        });
    }

    public static PolynomialFunction createLegendrePolynomial(int degree) {
        return PolynomialsUtils.buildPolynomial(degree, LEGENDRE_COEFFICIENTS, new RecurrenceCoefficientsGenerator(){

            public BigFraction[] generate(int k) {
                int kP1 = k + 1;
                return new BigFraction[]{BigFraction.ZERO, new BigFraction(k + kP1, kP1), new BigFraction(k, kP1)};
            }
        });
    }

    public static PolynomialFunction createJacobiPolynomial(int degree, final int v, final int w) {
        JacobiKey key = new JacobiKey(v, w);
        if (!JACOBI_COEFFICIENTS.containsKey(key)) {
            ArrayList<BigFraction> list = new ArrayList<BigFraction>();
            JACOBI_COEFFICIENTS.put(key, list);
            list.add(BigFraction.ONE);
            list.add(new BigFraction(v - w, 2));
            list.add(new BigFraction(2 + v + w, 2));
        }
        return PolynomialsUtils.buildPolynomial(degree, JACOBI_COEFFICIENTS.get(key), new RecurrenceCoefficientsGenerator(){

            public BigFraction[] generate(int k) {
                int kvw = ++k + v + w;
                int twoKvw = kvw + k;
                int twoKvwM1 = twoKvw - 1;
                int twoKvwM2 = twoKvw - 2;
                int den = 2 * k * kvw * twoKvwM2;
                return new BigFraction[]{new BigFraction(twoKvwM1 * (v * v - w * w), den), new BigFraction(twoKvwM1 * twoKvw * twoKvwM2, den), new BigFraction(2 * (k + v - 1) * (k + w - 1) * twoKvw, den)};
            }
        });
    }

    public static double[] shift(double[] coefficients, double shift) {
        int i;
        int dp1 = coefficients.length;
        double[] newCoefficients = new double[dp1];
        int[][] coeff = new int[dp1][dp1];
        for (i = 0; i < dp1; ++i) {
            for (int j = 0; j <= i; ++j) {
                coeff[i][j] = (int)CombinatoricsUtils.binomialCoefficient(i, j);
            }
        }
        for (i = 0; i < dp1; ++i) {
            newCoefficients[0] = newCoefficients[0] + coefficients[i] * FastMath.pow(shift, i);
        }
        int d = dp1 - 1;
        for (int i2 = 0; i2 < d; ++i2) {
            for (int j = i2; j < d; ++j) {
                int n = i2 + 1;
                newCoefficients[n] = newCoefficients[n] + (double)coeff[j + 1][j - i2] * coefficients[j + 1] * FastMath.pow(shift, j - i2);
            }
        }
        return newCoefficients;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static PolynomialFunction buildPolynomial(int degree, List<BigFraction> coefficients, RecurrenceCoefficientsGenerator generator) {
        List<BigFraction> list = coefficients;
        synchronized (list) {
            int maxDegree = (int)FastMath.floor(FastMath.sqrt(2 * coefficients.size())) - 1;
            if (degree > maxDegree) {
                PolynomialsUtils.computeUpToDegree(degree, maxDegree, generator, coefficients);
            }
        }
        int start = degree * (degree + 1) / 2;
        double[] a = new double[degree + 1];
        for (int i = 0; i <= degree; ++i) {
            a[i] = coefficients.get(start + i).doubleValue();
        }
        return new PolynomialFunction(a);
    }

    private static void computeUpToDegree(int degree, int maxDegree, RecurrenceCoefficientsGenerator generator, List<BigFraction> coefficients) {
        int startK = (maxDegree - 1) * maxDegree / 2;
        for (int k = maxDegree; k < degree; ++k) {
            int startKm1 = startK;
            BigFraction[] ai = generator.generate(k);
            BigFraction ck = coefficients.get(startK += k);
            BigFraction ckm1 = coefficients.get(startKm1);
            coefficients.add(ck.multiply(ai[0]).subtract(ckm1.multiply(ai[2])));
            for (int i = 1; i < k; ++i) {
                BigFraction ckPrev = ck;
                ck = coefficients.get(startK + i);
                ckm1 = coefficients.get(startKm1 + i);
                coefficients.add(ck.multiply(ai[0]).add(ckPrev.multiply(ai[1])).subtract(ckm1.multiply(ai[2])));
            }
            BigFraction ckPrev = ck;
            ck = coefficients.get(startK + k);
            coefficients.add(ck.multiply(ai[0]).add(ckPrev.multiply(ai[1])));
            coefficients.add(ck.multiply(ai[1]));
        }
    }

    static {
        CHEBYSHEV_COEFFICIENTS.add(BigFraction.ONE);
        CHEBYSHEV_COEFFICIENTS.add(BigFraction.ZERO);
        CHEBYSHEV_COEFFICIENTS.add(BigFraction.ONE);
        HERMITE_COEFFICIENTS = new ArrayList<BigFraction>();
        HERMITE_COEFFICIENTS.add(BigFraction.ONE);
        HERMITE_COEFFICIENTS.add(BigFraction.ZERO);
        HERMITE_COEFFICIENTS.add(BigFraction.TWO);
        LAGUERRE_COEFFICIENTS = new ArrayList<BigFraction>();
        LAGUERRE_COEFFICIENTS.add(BigFraction.ONE);
        LAGUERRE_COEFFICIENTS.add(BigFraction.ONE);
        LAGUERRE_COEFFICIENTS.add(BigFraction.MINUS_ONE);
        LEGENDRE_COEFFICIENTS = new ArrayList<BigFraction>();
        LEGENDRE_COEFFICIENTS.add(BigFraction.ONE);
        LEGENDRE_COEFFICIENTS.add(BigFraction.ZERO);
        LEGENDRE_COEFFICIENTS.add(BigFraction.ONE);
        JACOBI_COEFFICIENTS = new HashMap<JacobiKey, List<BigFraction>>();
    }

    private static interface RecurrenceCoefficientsGenerator {
        public BigFraction[] generate(int var1);
    }

    private static class JacobiKey {
        private final int v;
        private final int w;

        JacobiKey(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int hashCode() {
            return this.v << 16 ^ this.w;
        }

        public boolean equals(Object key) {
            if (key == null || !(key instanceof JacobiKey)) {
                return false;
            }
            JacobiKey otherK = (JacobiKey)key;
            return this.v == otherK.v && this.w == otherK.w;
        }
    }
}

