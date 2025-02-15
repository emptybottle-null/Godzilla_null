/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.special;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class BesselJ
implements UnivariateFunction {
    private static final double PI2 = 0.6366197723675814;
    private static final double TOWPI1 = 6.28125;
    private static final double TWOPI2 = 0.001935307179586477;
    private static final double TWOPI = Math.PI * 2;
    private static final double ENTEN = 1.0E308;
    private static final double ENSIG = 1.0E16;
    private static final double RTNSIG = 1.0E-4;
    private static final double ENMTEN = 8.9E-308;
    private static final double X_MIN = 0.0;
    private static final double X_MAX = 10000.0;
    private static final double[] FACT = new double[]{1.0, 1.0, 2.0, 6.0, 24.0, 120.0, 720.0, 5040.0, 40320.0, 362880.0, 3628800.0, 3.99168E7, 4.790016E8, 6.2270208E9, 8.71782912E10, 1.307674368E12, 2.0922789888E13, 3.55687428096E14, 6.402373705728E15, 1.21645100408832E17, 2.43290200817664E18, 5.109094217170944E19, 1.1240007277776077E21, 2.585201673888498E22, 6.204484017332394E23};
    private final double order;

    public BesselJ(double order) {
        this.order = order;
    }

    public double value(double x) throws MathIllegalArgumentException, ConvergenceException {
        return BesselJ.value(this.order, x);
    }

    public static double value(double order, double x) throws MathIllegalArgumentException, ConvergenceException {
        int n = (int)order;
        double alpha = order - (double)n;
        int nb = n + 1;
        BesselJResult res = BesselJ.rjBesl(x, alpha, nb);
        if (res.nVals >= nb) {
            return res.vals[n];
        }
        if (res.nVals < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.BESSEL_FUNCTION_BAD_ARGUMENT, order, x);
        }
        if (FastMath.abs(res.vals[res.nVals - 1]) < 1.0E-100) {
            return res.vals[n];
        }
        throw new ConvergenceException(LocalizedFormats.BESSEL_FUNCTION_FAILED_CONVERGENCE, order, x);
    }

    public static BesselJResult rjBesl(double x, double alpha, int nb) {
        double[] b = new double[nb];
        int ncalc = 0;
        double alpem = 0.0;
        double alp2em = 0.0;
        int magx = (int)x;
        if (nb > 0 && x >= 0.0 && x <= 10000.0 && alpha >= 0.0 && alpha < 1.0) {
            ncalc = nb;
            for (int i = 0; i < nb; ++i) {
                b[i] = 0.0;
            }
            if (x < 1.0E-4) {
                double tempa = 1.0;
                alpem = 1.0 + alpha;
                double halfx = 0.0;
                if (x > 8.9E-308) {
                    halfx = 0.5 * x;
                }
                if (alpha != 0.0) {
                    tempa = FastMath.pow(halfx, alpha) / (alpha * Gamma.gamma(alpha));
                }
                double tempb = 0.0;
                if (x + 1.0 > 1.0) {
                    tempb = -halfx * halfx;
                }
                b[0] = tempa + tempa * tempb / alpem;
                if (x != 0.0 && b[0] == 0.0) {
                    ncalc = 0;
                }
                if (nb != 1) {
                    if (x <= 0.0) {
                        for (int n = 1; n < nb; ++n) {
                            b[n] = 0.0;
                        }
                    } else {
                        double tempc = halfx;
                        double tover = tempb != 0.0 ? 8.9E-308 / tempb : 1.78E-307 / x;
                        for (int n = 1; n < nb; ++n) {
                            tempa /= alpem;
                            if ((tempa *= tempc) <= tover * (alpem += 1.0)) {
                                tempa = 0.0;
                            }
                            b[n] = tempa + tempa * tempb / alpem;
                            if (b[n] != 0.0 || ncalc <= n) continue;
                            ncalc = n;
                        }
                    }
                }
            } else if (x > 25.0 && nb <= magx + 1) {
                double xc = FastMath.sqrt(0.6366197723675814 / x);
                double mul = 0.125 / x;
                double xin = mul * mul;
                int m = 0;
                m = x >= 130.0 ? 4 : (x >= 35.0 ? 8 : 11);
                double xm = 4.0 * (double)m;
                double t = (int)(x / (Math.PI * 2) + 0.5);
                double z = x - t * 6.28125 - t * 0.001935307179586477 - (alpha + 0.5) / 0.6366197723675814;
                double vsin = FastMath.sin(z);
                double vcos = FastMath.cos(z);
                double gnu = 2.0 * alpha;
                for (int i = 1; i <= 2; ++i) {
                    double s = (xm - 1.0 - gnu) * (xm - 1.0 + gnu) * xin * 0.5;
                    t = (gnu - (xm - 3.0)) * (gnu + (xm - 3.0));
                    double capp = s * t / FACT[2 * m];
                    double t1 = (gnu - (xm + 1.0)) * (gnu + (xm + 1.0));
                    double capq = s * t1 / FACT[2 * m + 1];
                    double xk = xm;
                    int k = 2 * m;
                    t1 = t;
                    for (int j = 2; j <= m; ++j) {
                        s = ((xk -= 4.0) - 1.0 - gnu) * (xk - 1.0 + gnu);
                        t = (gnu - (xk - 3.0)) * (gnu + (xk - 3.0));
                        capp = (capp + 1.0 / FACT[k - 2]) * s * t * xin;
                        capq = (capq + 1.0 / FACT[k - 1]) * s * t1 * xin;
                        k -= 2;
                        t1 = t;
                    }
                    capq = (capq + 1.0) * (gnu * gnu - 1.0) * (0.125 / x);
                    b[i - 1] = xc * ((capp += 1.0) * vcos - capq * vsin);
                    if (nb == 1) {
                        return new BesselJResult(MathArrays.copyOf(b, b.length), ncalc);
                    }
                    t = vsin;
                    vsin = -vcos;
                    vcos = t;
                    gnu += 2.0;
                }
                if (nb > 2) {
                    gnu = 2.0 * alpha + 2.0;
                    for (int j = 2; j < nb; ++j) {
                        b[j] = gnu * b[j - 1] / x - b[j - 2];
                        gnu += 2.0;
                    }
                }
            } else {
                int l;
                double tempb;
                double pold;
                int nbmx = nb - magx;
                int n = magx + 1;
                int nstart = 0;
                int nend = 0;
                double en = 2.0 * ((double)n + alpha);
                double plast = 1.0;
                double p = en / x;
                double test = 2.0E16;
                boolean readyToInitialize = false;
                if (nbmx >= 3) {
                    double tover = 1.0E292;
                    nstart = magx + 2;
                    nend = nb - 1;
                    en = 2.0 * ((double)(nstart - 1) + alpha);
                    int k = nstart;
                    while (k <= nend) {
                        n = k++;
                        plast = p;
                        pold = plast;
                        if (!((p = (en += 2.0) * plast / x - pold) > tover)) continue;
                        tover = 1.0E308;
                        double psave = p /= tover;
                        double psavel = plast /= tover;
                        nstart = n + 1;
                        do {
                            ++n;
                        } while ((p = (en += 2.0) * (plast = p) / x - (pold = plast)) <= 1.0);
                        tempb = en / x;
                        test = pold * plast * (0.5 - 0.5 / (tempb * tempb));
                        test /= 1.0E16;
                        p = plast * tover;
                        en -= 2.0;
                        nend = FastMath.min(nb, --n);
                        for (int l2 = nstart; l2 <= nend; ++l2) {
                            psavel = psave;
                            pold = psavel;
                            if (!((psave = en * psavel / x - pold) * psavel > test)) continue;
                            ncalc = l2 - 1;
                            readyToInitialize = true;
                            break;
                        }
                        ncalc = nend;
                        readyToInitialize = true;
                        break;
                    }
                    if (!readyToInitialize) {
                        n = nend;
                        en = 2.0 * ((double)n + alpha);
                        test = FastMath.max(test, FastMath.sqrt(plast * 1.0E16) * FastMath.sqrt(2.0 * p));
                    }
                }
                if (!readyToInitialize) {
                    do {
                        ++n;
                    } while ((p = (en += 2.0) * (plast = p) / x - (pold = plast)) < test);
                }
                en += 2.0;
                tempb = 0.0;
                double tempa = 1.0 / p;
                int m = 2 * ++n - 4 * (n / 2);
                double sum = 0.0;
                double em = n / 2;
                alpem = em - 1.0 + alpha;
                alp2em = 2.0 * em + alpha;
                if (m != 0) {
                    sum = tempa * alpem * alp2em / em;
                }
                nend = n - nb;
                boolean readyToNormalize = false;
                boolean calculatedB0 = false;
                for (l = 1; l <= nend; ++l) {
                    --n;
                    double tempc = tempb;
                    tempb = tempa;
                    tempa = (en -= 2.0) * tempb / x - tempc;
                    if ((m = 2 - m) == 0) continue;
                    alp2em = 2.0 * (em -= 1.0) + alpha;
                    if (n == 1) break;
                    alpem = em - 1.0 + alpha;
                    if (alpem == 0.0) {
                        alpem = 1.0;
                    }
                    sum = (sum + tempa * alp2em) * alpem / em;
                }
                b[n - 1] = tempa;
                if (nend >= 0) {
                    if (nb <= 1) {
                        alp2em = alpha;
                        if (alpha + 1.0 == 1.0) {
                            alp2em = 1.0;
                        }
                        sum += b[0] * alp2em;
                        readyToNormalize = true;
                    } else {
                        b[--n - 1] = (en -= 2.0) * tempa / x - tempb;
                        if (n == 1) {
                            calculatedB0 = true;
                        } else if ((m = 2 - m) != 0) {
                            alp2em = 2.0 * (em -= 1.0) + alpha;
                            alpem = em - 1.0 + alpha;
                            if (alpem == 0.0) {
                                alpem = 1.0;
                            }
                            sum = (sum + b[n - 1] * alp2em) * alpem / em;
                        }
                    }
                }
                if (!readyToNormalize && !calculatedB0 && (nend = n - 2) != 0) {
                    for (l = 1; l <= nend; ++l) {
                        b[--n - 1] = (en -= 2.0) * b[n] / x - b[n + 1];
                        if ((m = 2 - m) == 0) continue;
                        alp2em = 2.0 * (em -= 1.0) + alpha;
                        alpem = em - 1.0 + alpha;
                        if (alpem == 0.0) {
                            alpem = 1.0;
                        }
                        sum = (sum + b[n - 1] * alp2em) * alpem / em;
                    }
                }
                if (!readyToNormalize) {
                    if (!calculatedB0) {
                        b[0] = 2.0 * (alpha + 1.0) * b[1] / x - b[2];
                    }
                    if ((alp2em = 2.0 * (em -= 1.0) + alpha) == 0.0) {
                        alp2em = 1.0;
                    }
                    sum += b[0] * alp2em;
                }
                if (FastMath.abs(alpha) > 1.0E-16) {
                    sum *= Gamma.gamma(alpha) * FastMath.pow(x * 0.5, -alpha);
                }
                tempa = 8.9E-308;
                if (sum > 1.0) {
                    tempa *= sum;
                }
                n = 0;
                while (n < nb) {
                    if (FastMath.abs(b[n]) < tempa) {
                        b[n] = 0.0;
                    }
                    int n2 = n++;
                    b[n2] = b[n2] / sum;
                }
            }
        } else {
            if (b.length > 0) {
                b[0] = 0.0;
            }
            ncalc = FastMath.min(nb, 0) - 1;
        }
        return new BesselJResult(MathArrays.copyOf(b, b.length), ncalc);
    }

    public static class BesselJResult {
        private final double[] vals;
        private final int nVals;

        public BesselJResult(double[] b, int n) {
            this.vals = MathArrays.copyOf(b, b.length);
            this.nVals = n;
        }

        public double[] getVals() {
            return MathArrays.copyOf(this.vals, this.vals.length);
        }

        public int getnVals() {
            return this.nVals;
        }
    }
}

