/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.special;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.ContinuedFraction;
import org.apache.commons.math3.util.FastMath;

public class Beta {
    private static final double DEFAULT_EPSILON = 1.0E-14;
    private static final double HALF_LOG_TWO_PI = 0.9189385332046727;
    private static final double[] DELTA = new double[]{0.08333333333333333, -2.777777777777778E-5, 7.936507936507937E-8, -5.952380952380953E-10, 8.417508417508329E-12, -1.917526917518546E-13, 6.410256405103255E-15, -2.955065141253382E-16, 1.7964371635940225E-17, -1.3922896466162779E-18, 1.338028550140209E-19, -1.542460098679661E-20, 1.9770199298095743E-21, -2.3406566479399704E-22, 1.713480149663986E-23};

    private Beta() {
    }

    public static double regularizedBeta(double x, double a, double b) {
        return Beta.regularizedBeta(x, a, b, 1.0E-14, Integer.MAX_VALUE);
    }

    public static double regularizedBeta(double x, double a, double b, double epsilon) {
        return Beta.regularizedBeta(x, a, b, epsilon, Integer.MAX_VALUE);
    }

    public static double regularizedBeta(double x, double a, double b, int maxIterations) {
        return Beta.regularizedBeta(x, a, b, 1.0E-14, maxIterations);
    }

    public static double regularizedBeta(double x, final double a, final double b, double epsilon, int maxIterations) {
        double ret;
        if (Double.isNaN(x) || Double.isNaN(a) || Double.isNaN(b) || x < 0.0 || x > 1.0 || a <= 0.0 || b <= 0.0) {
            ret = Double.NaN;
        } else if (x > (a + 1.0) / (2.0 + b + a) && 1.0 - x <= (b + 1.0) / (2.0 + b + a)) {
            ret = 1.0 - Beta.regularizedBeta(1.0 - x, b, a, epsilon, maxIterations);
        } else {
            ContinuedFraction fraction = new ContinuedFraction(){

                protected double getB(int n, double x) {
                    double ret;
                    if (n % 2 == 0) {
                        double m = (double)n / 2.0;
                        ret = m * (b - m) * x / ((a + 2.0 * m - 1.0) * (a + 2.0 * m));
                    } else {
                        double m = ((double)n - 1.0) / 2.0;
                        ret = -((a + m) * (a + b + m) * x) / ((a + 2.0 * m) * (a + 2.0 * m + 1.0));
                    }
                    return ret;
                }

                protected double getA(int n, double x) {
                    return 1.0;
                }
            };
            ret = FastMath.exp(a * FastMath.log(x) + b * FastMath.log1p(-x) - FastMath.log(a) - Beta.logBeta(a, b)) * 1.0 / fraction.evaluate(x, epsilon, maxIterations);
        }
        return ret;
    }

    @Deprecated
    public static double logBeta(double a, double b, double epsilon, int maxIterations) {
        return Beta.logBeta(a, b);
    }

    private static double logGammaSum(double a, double b) throws OutOfRangeException {
        if (a < 1.0 || a > 2.0) {
            throw new OutOfRangeException(a, (Number)1.0, 2.0);
        }
        if (b < 1.0 || b > 2.0) {
            throw new OutOfRangeException(b, (Number)1.0, 2.0);
        }
        double x = a - 1.0 + (b - 1.0);
        if (x <= 0.5) {
            return Gamma.logGamma1p(1.0 + x);
        }
        if (x <= 1.5) {
            return Gamma.logGamma1p(x) + FastMath.log1p(x);
        }
        return Gamma.logGamma1p(x - 1.0) + FastMath.log(x * (1.0 + x));
    }

    private static double logGammaMinusLogGammaSum(double a, double b) throws NumberIsTooSmallException {
        double w;
        double d;
        if (a < 0.0) {
            throw new NumberIsTooSmallException(a, (Number)0.0, true);
        }
        if (b < 10.0) {
            throw new NumberIsTooSmallException(b, (Number)10.0, true);
        }
        if (a <= b) {
            d = b + (a - 0.5);
            w = Beta.deltaMinusDeltaSum(a, b);
        } else {
            d = a + (b - 0.5);
            w = Beta.deltaMinusDeltaSum(b, a);
        }
        double u = d * FastMath.log1p(a / b);
        double v = a * (FastMath.log(b) - 1.0);
        return u <= v ? w - u - v : w - v - u;
    }

    private static double deltaMinusDeltaSum(double a, double b) throws OutOfRangeException, NumberIsTooSmallException {
        if (a < 0.0 || a > b) {
            throw new OutOfRangeException(a, (Number)0, b);
        }
        if (b < 10.0) {
            throw new NumberIsTooSmallException(b, (Number)10, true);
        }
        double h = a / b;
        double p = h / (1.0 + h);
        double q = 1.0 / (1.0 + h);
        double q2 = q * q;
        double[] s = new double[DELTA.length];
        s[0] = 1.0;
        for (int i = 1; i < s.length; ++i) {
            s[i] = 1.0 + (q + q2 * s[i - 1]);
        }
        double sqrtT = 10.0 / b;
        double t = sqrtT * sqrtT;
        double w = DELTA[DELTA.length - 1] * s[s.length - 1];
        for (int i = DELTA.length - 2; i >= 0; --i) {
            w = t * w + DELTA[i] * s[i];
        }
        return w * p / b;
    }

    private static double sumDeltaMinusDeltaSum(double p, double q) {
        if (p < 10.0) {
            throw new NumberIsTooSmallException(p, (Number)10.0, true);
        }
        if (q < 10.0) {
            throw new NumberIsTooSmallException(q, (Number)10.0, true);
        }
        double a = FastMath.min(p, q);
        double b = FastMath.max(p, q);
        double sqrtT = 10.0 / a;
        double t = sqrtT * sqrtT;
        double z = DELTA[DELTA.length - 1];
        for (int i = DELTA.length - 2; i >= 0; --i) {
            z = t * z + DELTA[i];
        }
        return z / a + Beta.deltaMinusDeltaSum(a, b);
    }

    public static double logBeta(double p, double q) {
        if (Double.isNaN(p) || Double.isNaN(q) || p <= 0.0 || q <= 0.0) {
            return Double.NaN;
        }
        double a = FastMath.min(p, q);
        double b = FastMath.max(p, q);
        if (a >= 10.0) {
            double v;
            double w = Beta.sumDeltaMinusDeltaSum(a, b);
            double h = a / b;
            double c = h / (1.0 + h);
            double u = -(a - 0.5) * FastMath.log(c);
            if (u <= (v = b * FastMath.log1p(h))) {
                return -0.5 * FastMath.log(b) + 0.9189385332046727 + w - u - v;
            }
            return -0.5 * FastMath.log(b) + 0.9189385332046727 + w - v - u;
        }
        if (a > 2.0) {
            if (b > 1000.0) {
                int n = (int)FastMath.floor(a - 1.0);
                double prod = 1.0;
                double ared = a;
                for (int i = 0; i < n; ++i) {
                    prod *= (ared -= 1.0) / (1.0 + ared / b);
                }
                return FastMath.log(prod) - (double)n * FastMath.log(b) + (Gamma.logGamma(ared) + Beta.logGammaMinusLogGammaSum(ared, b));
            }
            double prod1 = 1.0;
            double ared = a;
            while (ared > 2.0) {
                double h = (ared -= 1.0) / b;
                prod1 *= h / (1.0 + h);
            }
            if (b < 10.0) {
                double prod2 = 1.0;
                double bred = b;
                while (bred > 2.0) {
                    prod2 *= (bred -= 1.0) / (ared + bred);
                }
                return FastMath.log(prod1) + FastMath.log(prod2) + (Gamma.logGamma(ared) + (Gamma.logGamma(bred) - Beta.logGammaSum(ared, bred)));
            }
            return FastMath.log(prod1) + Gamma.logGamma(ared) + Beta.logGammaMinusLogGammaSum(ared, b);
        }
        if (a >= 1.0) {
            if (b > 2.0) {
                if (b < 10.0) {
                    double prod = 1.0;
                    double bred = b;
                    while (bred > 2.0) {
                        prod *= (bred -= 1.0) / (a + bred);
                    }
                    return FastMath.log(prod) + (Gamma.logGamma(a) + (Gamma.logGamma(bred) - Beta.logGammaSum(a, bred)));
                }
                return Gamma.logGamma(a) + Beta.logGammaMinusLogGammaSum(a, b);
            }
            return Gamma.logGamma(a) + Gamma.logGamma(b) - Beta.logGammaSum(a, b);
        }
        if (b >= 10.0) {
            return Gamma.logGamma(a) + Beta.logGammaMinusLogGammaSum(a, b);
        }
        return FastMath.log(Gamma.gamma(a) * Gamma.gamma(b) / Gamma.gamma(a + b));
    }
}

