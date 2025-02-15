/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.special;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.ContinuedFraction;
import org.apache.commons.math3.util.FastMath;

public class Gamma {
    public static final double GAMMA = 0.5772156649015329;
    public static final double LANCZOS_G = 4.7421875;
    private static final double DEFAULT_EPSILON = 1.0E-14;
    private static final double[] LANCZOS = new double[]{0.9999999999999971, 57.15623566586292, -59.59796035547549, 14.136097974741746, -0.4919138160976202, 3.399464998481189E-5, 4.652362892704858E-5, -9.837447530487956E-5, 1.580887032249125E-4, -2.1026444172410488E-4, 2.1743961811521265E-4, -1.643181065367639E-4, 8.441822398385275E-5, -2.6190838401581408E-5, 3.6899182659531625E-6};
    private static final double HALF_LOG_2_PI = 0.5 * FastMath.log(Math.PI * 2);
    private static final double SQRT_TWO_PI = 2.5066282746310007;
    private static final double C_LIMIT = 49.0;
    private static final double S_LIMIT = 1.0E-5;
    private static final double INV_GAMMA1P_M1_A0 = 6.116095104481416E-9;
    private static final double INV_GAMMA1P_M1_A1 = 6.247308301164655E-9;
    private static final double INV_GAMMA1P_M1_B1 = 0.203610414066807;
    private static final double INV_GAMMA1P_M1_B2 = 0.026620534842894922;
    private static final double INV_GAMMA1P_M1_B3 = 4.939449793824468E-4;
    private static final double INV_GAMMA1P_M1_B4 = -8.514194324403149E-6;
    private static final double INV_GAMMA1P_M1_B5 = -6.4304548177935305E-6;
    private static final double INV_GAMMA1P_M1_B6 = 9.926418406727737E-7;
    private static final double INV_GAMMA1P_M1_B7 = -6.077618957228252E-8;
    private static final double INV_GAMMA1P_M1_B8 = 1.9575583661463974E-10;
    private static final double INV_GAMMA1P_M1_P0 = 6.116095104481416E-9;
    private static final double INV_GAMMA1P_M1_P1 = 6.8716741130671986E-9;
    private static final double INV_GAMMA1P_M1_P2 = 6.820161668496171E-10;
    private static final double INV_GAMMA1P_M1_P3 = 4.686843322948848E-11;
    private static final double INV_GAMMA1P_M1_P4 = 1.5728330277104463E-12;
    private static final double INV_GAMMA1P_M1_P5 = -1.2494415722763663E-13;
    private static final double INV_GAMMA1P_M1_P6 = 4.343529937408594E-15;
    private static final double INV_GAMMA1P_M1_Q1 = 0.3056961078365221;
    private static final double INV_GAMMA1P_M1_Q2 = 0.054642130860422966;
    private static final double INV_GAMMA1P_M1_Q3 = 0.004956830093825887;
    private static final double INV_GAMMA1P_M1_Q4 = 2.6923694661863613E-4;
    private static final double INV_GAMMA1P_M1_C = -0.42278433509846713;
    private static final double INV_GAMMA1P_M1_C0 = 0.5772156649015329;
    private static final double INV_GAMMA1P_M1_C1 = -0.6558780715202539;
    private static final double INV_GAMMA1P_M1_C2 = -0.04200263503409524;
    private static final double INV_GAMMA1P_M1_C3 = 0.16653861138229148;
    private static final double INV_GAMMA1P_M1_C4 = -0.04219773455554433;
    private static final double INV_GAMMA1P_M1_C5 = -0.009621971527876973;
    private static final double INV_GAMMA1P_M1_C6 = 0.0072189432466631;
    private static final double INV_GAMMA1P_M1_C7 = -0.0011651675918590652;
    private static final double INV_GAMMA1P_M1_C8 = -2.1524167411495098E-4;
    private static final double INV_GAMMA1P_M1_C9 = 1.280502823881162E-4;
    private static final double INV_GAMMA1P_M1_C10 = -2.013485478078824E-5;
    private static final double INV_GAMMA1P_M1_C11 = -1.2504934821426706E-6;
    private static final double INV_GAMMA1P_M1_C12 = 1.133027231981696E-6;
    private static final double INV_GAMMA1P_M1_C13 = -2.056338416977607E-7;

    private Gamma() {
    }

    public static double logGamma(double x) {
        double ret;
        if (Double.isNaN(x) || x <= 0.0) {
            ret = Double.NaN;
        } else {
            if (x < 0.5) {
                return Gamma.logGamma1p(x) - FastMath.log(x);
            }
            if (x <= 2.5) {
                return Gamma.logGamma1p(x - 0.5 - 0.5);
            }
            if (x <= 8.0) {
                int n = (int)FastMath.floor(x - 1.5);
                double prod = 1.0;
                for (int i = 1; i <= n; ++i) {
                    prod *= x - (double)i;
                }
                return Gamma.logGamma1p(x - (double)(n + 1)) + FastMath.log(prod);
            }
            double sum = Gamma.lanczos(x);
            double tmp = x + 4.7421875 + 0.5;
            ret = (x + 0.5) * FastMath.log(tmp) - tmp + HALF_LOG_2_PI + FastMath.log(sum / x);
        }
        return ret;
    }

    public static double regularizedGammaP(double a, double x) {
        return Gamma.regularizedGammaP(a, x, 1.0E-14, Integer.MAX_VALUE);
    }

    public static double regularizedGammaP(double a, double x, double epsilon, int maxIterations) {
        double ret;
        if (Double.isNaN(a) || Double.isNaN(x) || a <= 0.0 || x < 0.0) {
            ret = Double.NaN;
        } else if (x == 0.0) {
            ret = 0.0;
        } else if (x >= a + 1.0) {
            ret = 1.0 - Gamma.regularizedGammaQ(a, x, epsilon, maxIterations);
        } else {
            double sum;
            double an;
            double n = 0.0;
            for (sum = an = 1.0 / a; FastMath.abs(an / sum) > epsilon && n < (double)maxIterations && sum < Double.POSITIVE_INFINITY; sum += (an *= x / (a + (n += 1.0)))) {
            }
            if (n >= (double)maxIterations) {
                throw new MaxCountExceededException(maxIterations);
            }
            ret = Double.isInfinite(sum) ? 1.0 : FastMath.exp(-x + a * FastMath.log(x) - Gamma.logGamma(a)) * sum;
        }
        return ret;
    }

    public static double regularizedGammaQ(double a, double x) {
        return Gamma.regularizedGammaQ(a, x, 1.0E-14, Integer.MAX_VALUE);
    }

    public static double regularizedGammaQ(final double a, double x, double epsilon, int maxIterations) {
        double ret;
        if (Double.isNaN(a) || Double.isNaN(x) || a <= 0.0 || x < 0.0) {
            ret = Double.NaN;
        } else if (x == 0.0) {
            ret = 1.0;
        } else if (x < a + 1.0) {
            ret = 1.0 - Gamma.regularizedGammaP(a, x, epsilon, maxIterations);
        } else {
            ContinuedFraction cf = new ContinuedFraction(){

                protected double getA(int n, double x) {
                    return 2.0 * (double)n + 1.0 - a + x;
                }

                protected double getB(int n, double x) {
                    return (double)n * (a - (double)n);
                }
            };
            ret = 1.0 / cf.evaluate(x, epsilon, maxIterations);
            ret = FastMath.exp(-x + a * FastMath.log(x) - Gamma.logGamma(a)) * ret;
        }
        return ret;
    }

    public static double digamma(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return x;
        }
        if (x > 0.0 && x <= 1.0E-5) {
            return -0.5772156649015329 - 1.0 / x;
        }
        if (x >= 49.0) {
            double inv = 1.0 / (x * x);
            return FastMath.log(x) - 0.5 / x - inv * (0.08333333333333333 + inv * (0.008333333333333333 - inv / 252.0));
        }
        return Gamma.digamma(x + 1.0) - 1.0 / x;
    }

    public static double trigamma(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return x;
        }
        if (x > 0.0 && x <= 1.0E-5) {
            return 1.0 / (x * x);
        }
        if (x >= 49.0) {
            double inv = 1.0 / (x * x);
            return 1.0 / x + inv / 2.0 + inv / x * (0.16666666666666666 - inv * (0.03333333333333333 + inv / 42.0));
        }
        return Gamma.trigamma(x + 1.0) + 1.0 / (x * x);
    }

    public static double lanczos(double x) {
        double sum = 0.0;
        for (int i = LANCZOS.length - 1; i > 0; --i) {
            sum += LANCZOS[i] / (x + (double)i);
        }
        return sum + LANCZOS[0];
    }

    public static double invGamma1pm1(double x) {
        double ret;
        double t;
        if (x < -0.5) {
            throw new NumberIsTooSmallException(x, (Number)-0.5, true);
        }
        if (x > 1.5) {
            throw new NumberIsTooLargeException(x, (Number)1.5, true);
        }
        double d = t = x <= 0.5 ? x : x - 0.5 - 0.5;
        if (t < 0.0) {
            double a = 6.116095104481416E-9 + t * 6.247308301164655E-9;
            double b = 1.9575583661463974E-10;
            b = -6.077618957228252E-8 + t * b;
            b = 9.926418406727737E-7 + t * b;
            b = -6.4304548177935305E-6 + t * b;
            b = -8.514194324403149E-6 + t * b;
            b = 4.939449793824468E-4 + t * b;
            b = 0.026620534842894922 + t * b;
            b = 0.203610414066807 + t * b;
            b = 1.0 + t * b;
            double c = -2.056338416977607E-7 + t * (a / b);
            c = 1.133027231981696E-6 + t * c;
            c = -1.2504934821426706E-6 + t * c;
            c = -2.013485478078824E-5 + t * c;
            c = 1.280502823881162E-4 + t * c;
            c = -2.1524167411495098E-4 + t * c;
            c = -0.0011651675918590652 + t * c;
            c = 0.0072189432466631 + t * c;
            c = -0.009621971527876973 + t * c;
            c = -0.04219773455554433 + t * c;
            c = 0.16653861138229148 + t * c;
            c = -0.04200263503409524 + t * c;
            c = -0.6558780715202539 + t * c;
            c = -0.42278433509846713 + t * c;
            ret = x > 0.5 ? t * c / x : x * (c + 0.5 + 0.5);
        } else {
            double p = 4.343529937408594E-15;
            p = -1.2494415722763663E-13 + t * p;
            p = 1.5728330277104463E-12 + t * p;
            p = 4.686843322948848E-11 + t * p;
            p = 6.820161668496171E-10 + t * p;
            p = 6.8716741130671986E-9 + t * p;
            p = 6.116095104481416E-9 + t * p;
            double q = 2.6923694661863613E-4;
            q = 0.004956830093825887 + t * q;
            q = 0.054642130860422966 + t * q;
            q = 0.3056961078365221 + t * q;
            q = 1.0 + t * q;
            double c = -2.056338416977607E-7 + p / q * t;
            c = 1.133027231981696E-6 + t * c;
            c = -1.2504934821426706E-6 + t * c;
            c = -2.013485478078824E-5 + t * c;
            c = 1.280502823881162E-4 + t * c;
            c = -2.1524167411495098E-4 + t * c;
            c = -0.0011651675918590652 + t * c;
            c = 0.0072189432466631 + t * c;
            c = -0.009621971527876973 + t * c;
            c = -0.04219773455554433 + t * c;
            c = 0.16653861138229148 + t * c;
            c = -0.04200263503409524 + t * c;
            c = -0.6558780715202539 + t * c;
            c = 0.5772156649015329 + t * c;
            ret = x > 0.5 ? t / x * (c - 0.5 - 0.5) : x * c;
        }
        return ret;
    }

    public static double logGamma1p(double x) throws NumberIsTooSmallException, NumberIsTooLargeException {
        if (x < -0.5) {
            throw new NumberIsTooSmallException(x, (Number)-0.5, true);
        }
        if (x > 1.5) {
            throw new NumberIsTooLargeException(x, (Number)1.5, true);
        }
        return -FastMath.log1p(Gamma.invGamma1pm1(x));
    }

    public static double gamma(double x) {
        double ret;
        if (x == FastMath.rint(x) && x <= 0.0) {
            return Double.NaN;
        }
        double absX = FastMath.abs(x);
        if (absX <= 20.0) {
            if (x >= 1.0) {
                double prod = 1.0;
                double t = x;
                while (t > 2.5) {
                    prod *= (t -= 1.0);
                }
                ret = prod / (1.0 + Gamma.invGamma1pm1(t - 1.0));
            } else {
                double prod = x;
                double t = x;
                while (t < -0.5) {
                    prod *= (t += 1.0);
                }
                ret = 1.0 / (prod * (1.0 + Gamma.invGamma1pm1(t)));
            }
        } else {
            double y = absX + 4.7421875 + 0.5;
            double gammaAbs = 2.5066282746310007 / absX * FastMath.pow(y, absX + 0.5) * FastMath.exp(-y) * Gamma.lanczos(absX);
            ret = x > 0.0 ? gammaAbs : -Math.PI / (x * FastMath.sin(Math.PI * x) * gammaAbs);
        }
        return ret;
    }
}

