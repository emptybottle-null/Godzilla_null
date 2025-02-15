/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.math.BigDecimal;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class Precision {
    public static final double EPSILON;
    public static final double SAFE_MIN;
    private static final long EXPONENT_OFFSET = 1023L;
    private static final long SGN_MASK = Long.MIN_VALUE;
    private static final int SGN_MASK_FLOAT = Integer.MIN_VALUE;
    private static final double POSITIVE_ZERO = 0.0;
    private static final long POSITIVE_ZERO_DOUBLE_BITS;
    private static final long NEGATIVE_ZERO_DOUBLE_BITS;
    private static final int POSITIVE_ZERO_FLOAT_BITS;
    private static final int NEGATIVE_ZERO_FLOAT_BITS;

    private Precision() {
    }

    public static int compareTo(double x, double y, double eps) {
        if (Precision.equals(x, y, eps)) {
            return 0;
        }
        if (x < y) {
            return -1;
        }
        return 1;
    }

    public static int compareTo(double x, double y, int maxUlps) {
        if (Precision.equals(x, y, maxUlps)) {
            return 0;
        }
        if (x < y) {
            return -1;
        }
        return 1;
    }

    public static boolean equals(float x, float y) {
        return Precision.equals(x, y, 1);
    }

    public static boolean equalsIncludingNaN(float x, float y) {
        return x != x || y != y ? !(x != x ^ y != y) : Precision.equals(x, y, 1);
    }

    public static boolean equals(float x, float y, float eps) {
        return Precision.equals(x, y, 1) || FastMath.abs(y - x) <= eps;
    }

    public static boolean equalsIncludingNaN(float x, float y, float eps) {
        return Precision.equalsIncludingNaN(x, y) || FastMath.abs(y - x) <= eps;
    }

    public static boolean equals(float x, float y, int maxUlps) {
        boolean isEqual;
        int yInt;
        int xInt = Float.floatToRawIntBits(x);
        if (((xInt ^ (yInt = Float.floatToRawIntBits(y))) & Integer.MIN_VALUE) == 0) {
            isEqual = FastMath.abs(xInt - yInt) <= maxUlps;
        } else {
            int deltaMinus;
            int deltaPlus;
            if (xInt < yInt) {
                deltaPlus = yInt - POSITIVE_ZERO_FLOAT_BITS;
                deltaMinus = xInt - NEGATIVE_ZERO_FLOAT_BITS;
            } else {
                deltaPlus = xInt - POSITIVE_ZERO_FLOAT_BITS;
                deltaMinus = yInt - NEGATIVE_ZERO_FLOAT_BITS;
            }
            isEqual = deltaPlus > maxUlps ? false : deltaMinus <= maxUlps - deltaPlus;
        }
        return isEqual && !Float.isNaN(x) && !Float.isNaN(y);
    }

    public static boolean equalsIncludingNaN(float x, float y, int maxUlps) {
        return x != x || y != y ? !(x != x ^ y != y) : Precision.equals(x, y, maxUlps);
    }

    public static boolean equals(double x, double y) {
        return Precision.equals(x, y, 1);
    }

    public static boolean equalsIncludingNaN(double x, double y) {
        return x != x || y != y ? !(x != x ^ y != y) : Precision.equals(x, y, 1);
    }

    public static boolean equals(double x, double y, double eps) {
        return Precision.equals(x, y, 1) || FastMath.abs(y - x) <= eps;
    }

    public static boolean equalsWithRelativeTolerance(double x, double y, double eps) {
        if (Precision.equals(x, y, 1)) {
            return true;
        }
        double absoluteMax = FastMath.max(FastMath.abs(x), FastMath.abs(y));
        double relativeDifference = FastMath.abs((x - y) / absoluteMax);
        return relativeDifference <= eps;
    }

    public static boolean equalsIncludingNaN(double x, double y, double eps) {
        return Precision.equalsIncludingNaN(x, y) || FastMath.abs(y - x) <= eps;
    }

    public static boolean equals(double x, double y, int maxUlps) {
        boolean isEqual;
        long yInt;
        long xInt = Double.doubleToRawLongBits(x);
        if (((xInt ^ (yInt = Double.doubleToRawLongBits(y))) & Long.MIN_VALUE) == 0L) {
            isEqual = FastMath.abs(xInt - yInt) <= (long)maxUlps;
        } else {
            long deltaMinus;
            long deltaPlus;
            if (xInt < yInt) {
                deltaPlus = yInt - POSITIVE_ZERO_DOUBLE_BITS;
                deltaMinus = xInt - NEGATIVE_ZERO_DOUBLE_BITS;
            } else {
                deltaPlus = xInt - POSITIVE_ZERO_DOUBLE_BITS;
                deltaMinus = yInt - NEGATIVE_ZERO_DOUBLE_BITS;
            }
            isEqual = deltaPlus > (long)maxUlps ? false : deltaMinus <= (long)maxUlps - deltaPlus;
        }
        return isEqual && !Double.isNaN(x) && !Double.isNaN(y);
    }

    public static boolean equalsIncludingNaN(double x, double y, int maxUlps) {
        return x != x || y != y ? !(x != x ^ y != y) : Precision.equals(x, y, maxUlps);
    }

    public static double round(double x, int scale) {
        return Precision.round(x, scale, 4);
    }

    public static double round(double x, int scale, int roundingMethod) {
        try {
            double rounded = new BigDecimal(Double.toString(x)).setScale(scale, roundingMethod).doubleValue();
            return rounded == 0.0 ? 0.0 * x : rounded;
        } catch (NumberFormatException ex) {
            if (Double.isInfinite(x)) {
                return x;
            }
            return Double.NaN;
        }
    }

    public static float round(float x, int scale) {
        return Precision.round(x, scale, 4);
    }

    public static float round(float x, int scale, int roundingMethod) throws MathArithmeticException, MathIllegalArgumentException {
        float sign = FastMath.copySign(1.0f, x);
        float factor = (float)FastMath.pow(10.0, scale) * sign;
        return (float)Precision.roundUnscaled(x * factor, sign, roundingMethod) / factor;
    }

    private static double roundUnscaled(double unscaled, double sign, int roundingMethod) throws MathArithmeticException, MathIllegalArgumentException {
        switch (roundingMethod) {
            case 2: {
                if (sign == -1.0) {
                    unscaled = FastMath.floor(FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY));
                    break;
                }
                unscaled = FastMath.ceil(FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY));
                break;
            }
            case 1: {
                unscaled = FastMath.floor(FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY));
                break;
            }
            case 3: {
                if (sign == -1.0) {
                    unscaled = FastMath.ceil(FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY));
                    break;
                }
                unscaled = FastMath.floor(FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY));
                break;
            }
            case 5: {
                unscaled = FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY);
                double fraction = unscaled - FastMath.floor(unscaled);
                if (fraction > 0.5) {
                    unscaled = FastMath.ceil(unscaled);
                    break;
                }
                unscaled = FastMath.floor(unscaled);
                break;
            }
            case 6: {
                double fraction = unscaled - FastMath.floor(unscaled);
                if (fraction > 0.5) {
                    unscaled = FastMath.ceil(unscaled);
                    break;
                }
                if (fraction < 0.5) {
                    unscaled = FastMath.floor(unscaled);
                    break;
                }
                if (FastMath.floor(unscaled) / 2.0 == FastMath.floor(FastMath.floor(unscaled) / 2.0)) {
                    unscaled = FastMath.floor(unscaled);
                    break;
                }
                unscaled = FastMath.ceil(unscaled);
                break;
            }
            case 4: {
                unscaled = FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY);
                double fraction = unscaled - FastMath.floor(unscaled);
                if (fraction >= 0.5) {
                    unscaled = FastMath.ceil(unscaled);
                    break;
                }
                unscaled = FastMath.floor(unscaled);
                break;
            }
            case 7: {
                if (unscaled == FastMath.floor(unscaled)) break;
                throw new MathArithmeticException();
            }
            case 0: {
                if (unscaled == FastMath.floor(unscaled)) break;
                unscaled = FastMath.ceil(FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY));
                break;
            }
            default: {
                throw new MathIllegalArgumentException(LocalizedFormats.INVALID_ROUNDING_METHOD, roundingMethod, "ROUND_CEILING", 2, "ROUND_DOWN", 1, "ROUND_FLOOR", 3, "ROUND_HALF_DOWN", 5, "ROUND_HALF_EVEN", 6, "ROUND_HALF_UP", 4, "ROUND_UNNECESSARY", 7, "ROUND_UP", 0);
            }
        }
        return unscaled;
    }

    public static double representableDelta(double x, double originalDelta) {
        return x + originalDelta - x;
    }

    static {
        POSITIVE_ZERO_DOUBLE_BITS = Double.doubleToRawLongBits(0.0);
        NEGATIVE_ZERO_DOUBLE_BITS = Double.doubleToRawLongBits(-0.0);
        POSITIVE_ZERO_FLOAT_BITS = Float.floatToRawIntBits(0.0f);
        NEGATIVE_ZERO_FLOAT_BITS = Float.floatToRawIntBits(-0.0f);
        EPSILON = Double.longBitsToDouble(4368491638549381120L);
        SAFE_MIN = Double.longBitsToDouble(0x10000000000000L);
    }
}

