/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.util.Arrays;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class MathUtils {
    public static final double TWO_PI = Math.PI * 2;
    public static final double PI_SQUARED = Math.PI * Math.PI;

    private MathUtils() {
    }

    public static int hash(double value) {
        return new Double(value).hashCode();
    }

    public static boolean equals(double x, double y) {
        return new Double(x).equals(new Double(y));
    }

    public static int hash(double[] value) {
        return Arrays.hashCode(value);
    }

    public static double normalizeAngle(double a, double center) {
        return a - Math.PI * 2 * FastMath.floor((a + Math.PI - center) / (Math.PI * 2));
    }

    public static <T extends RealFieldElement<T>> T max(T e1, T e2) {
        return e1.subtract(e2).getReal() >= 0.0 ? e1 : e2;
    }

    public static <T extends RealFieldElement<T>> T min(T e1, T e2) {
        return e1.subtract(e2).getReal() >= 0.0 ? e2 : e1;
    }

    public static double reduce(double a, double period, double offset) {
        double p = FastMath.abs(period);
        return a - p * FastMath.floor((a - offset) / p) - offset;
    }

    public static byte copySign(byte magnitude, byte sign) throws MathArithmeticException {
        if (magnitude >= 0 && sign >= 0 || magnitude < 0 && sign < 0) {
            return magnitude;
        }
        if (sign >= 0 && magnitude == -128) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
        }
        return -magnitude;
    }

    public static short copySign(short magnitude, short sign) throws MathArithmeticException {
        if (magnitude >= 0 && sign >= 0 || magnitude < 0 && sign < 0) {
            return magnitude;
        }
        if (sign >= 0 && magnitude == Short.MIN_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
        }
        return -magnitude;
    }

    public static int copySign(int magnitude, int sign) throws MathArithmeticException {
        if (magnitude >= 0 && sign >= 0 || magnitude < 0 && sign < 0) {
            return magnitude;
        }
        if (sign >= 0 && magnitude == Integer.MIN_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
        }
        return -magnitude;
    }

    public static long copySign(long magnitude, long sign) throws MathArithmeticException {
        if (magnitude >= 0L && sign >= 0L || magnitude < 0L && sign < 0L) {
            return magnitude;
        }
        if (sign >= 0L && magnitude == Long.MIN_VALUE) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
        }
        return -magnitude;
    }

    public static void checkFinite(double x) throws NotFiniteNumberException {
        if (Double.isInfinite(x) || Double.isNaN(x)) {
            throw new NotFiniteNumberException(x, new Object[0]);
        }
    }

    public static void checkFinite(double[] val) throws NotFiniteNumberException {
        for (int i = 0; i < val.length; ++i) {
            double x = val[i];
            if (!Double.isInfinite(x) && !Double.isNaN(x)) continue;
            throw new NotFiniteNumberException((Localizable)LocalizedFormats.ARRAY_ELEMENT, x, i);
        }
    }

    public static void checkNotNull(Object o, Localizable pattern, Object ... args) throws NullArgumentException {
        if (o == null) {
            throw new NullArgumentException(pattern, args);
        }
    }

    public static void checkNotNull(Object o) throws NullArgumentException {
        if (o == null) {
            throw new NullArgumentException();
        }
    }
}

