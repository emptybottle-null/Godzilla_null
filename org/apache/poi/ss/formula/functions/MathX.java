/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.poi.ss.util.NumberToTextConverter;

final class MathX {
    private MathX() {
    }

    public static double round(double n, int p) {
        return MathX.round(n, p, RoundingMode.HALF_UP);
    }

    public static double roundUp(double n, int p) {
        return MathX.round(n, p, RoundingMode.UP);
    }

    public static double roundDown(double n, int p) {
        return MathX.round(n, p, RoundingMode.DOWN);
    }

    private static double round(double n, int p, RoundingMode rounding) {
        if (Double.isNaN(n) || Double.isInfinite(n)) {
            return Double.NaN;
        }
        String excelNumber = NumberToTextConverter.toText(n);
        return new BigDecimal(excelNumber).setScale(p, rounding).doubleValue();
    }

    public static short sign(double d) {
        return (short)(d == 0.0 ? 0 : (d < 0.0 ? -1 : 1));
    }

    public static double average(double[] values) {
        double ave = 0.0;
        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }
        ave = sum / (double)values.length;
        return ave;
    }

    public static double sum(double[] values) {
        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }

    public static double sumsq(double[] values) {
        double sumsq = 0.0;
        for (double value : values) {
            sumsq += value * value;
        }
        return sumsq;
    }

    public static double product(double[] values) {
        double product = 0.0;
        if (values != null && values.length > 0) {
            product = 1.0;
            for (double value : values) {
                product *= value;
            }
        }
        return product;
    }

    public static double min(double[] values) {
        double min = Double.POSITIVE_INFINITY;
        for (double value : values) {
            min = Math.min(min, value);
        }
        return min;
    }

    public static double max(double[] values) {
        double max = Double.NEGATIVE_INFINITY;
        for (double value : values) {
            max = Math.max(max, value);
        }
        return max;
    }

    public static double floor(double n, double s) {
        if (s == 0.0 && n != 0.0) {
            return Double.NaN;
        }
        return n == 0.0 || s == 0.0 ? 0.0 : Math.floor(n / s) * s;
    }

    public static double ceiling(double n, double s) {
        if (n > 0.0 && s < 0.0) {
            return Double.NaN;
        }
        return n == 0.0 || s == 0.0 ? 0.0 : Math.ceil(n / s) * s;
    }

    public static double factorial(int n) {
        double d = 1.0;
        if (n >= 0) {
            if (n <= 170) {
                for (int i = 1; i <= n; ++i) {
                    d *= (double)i;
                }
            } else {
                d = Double.POSITIVE_INFINITY;
            }
        } else {
            d = Double.NaN;
        }
        return d;
    }

    public static double mod(double n, double d) {
        if (d == 0.0) {
            return Double.NaN;
        }
        if (MathX.sign(n) == MathX.sign(d)) {
            return n % d;
        }
        return (n % d + d) % d;
    }

    public static double acosh(double d) {
        return Math.log(Math.sqrt(Math.pow(d, 2.0) - 1.0) + d);
    }

    public static double asinh(double d) {
        return Math.log(Math.sqrt(d * d + 1.0) + d);
    }

    public static double atanh(double d) {
        return Math.log((1.0 + d) / (1.0 - d)) / 2.0;
    }

    public static double cosh(double d) {
        double ePowX = Math.pow(Math.E, d);
        double ePowNegX = Math.pow(Math.E, -d);
        return (ePowX + ePowNegX) / 2.0;
    }

    public static double sinh(double d) {
        double ePowX = Math.pow(Math.E, d);
        double ePowNegX = Math.pow(Math.E, -d);
        return (ePowX - ePowNegX) / 2.0;
    }

    public static double tanh(double d) {
        double ePowX = Math.pow(Math.E, d);
        double ePowNegX = Math.pow(Math.E, -d);
        return (ePowX - ePowNegX) / (ePowX + ePowNegX);
    }

    public static double nChooseK(int n, int k) {
        double d = 1.0;
        if (n < 0 || k < 0 || n < k) {
            d = Double.NaN;
        } else {
            int maxnk;
            int minnk = Math.min(n - k, k);
            for (int i = maxnk = Math.max(n - k, k); i < n; ++i) {
                d *= (double)(i + 1);
            }
            d /= MathX.factorial(minnk);
        }
        return d;
    }
}

