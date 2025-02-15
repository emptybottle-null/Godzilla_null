/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MathArrays {
    private MathArrays() {
    }

    public static double[] scale(double val, double[] arr) {
        double[] newArr = new double[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            newArr[i] = arr[i] * val;
        }
        return newArr;
    }

    public static void scaleInPlace(double val, double[] arr) {
        int i = 0;
        while (i < arr.length) {
            int n = i++;
            arr[n] = arr[n] * val;
        }
    }

    public static double[] ebeAdd(double[] a, double[] b) throws DimensionMismatchException {
        MathArrays.checkEqualLength(a, b);
        double[] result = (double[])a.clone();
        for (int i = 0; i < a.length; ++i) {
            int n = i;
            result[n] = result[n] + b[i];
        }
        return result;
    }

    public static double[] ebeSubtract(double[] a, double[] b) throws DimensionMismatchException {
        MathArrays.checkEqualLength(a, b);
        double[] result = (double[])a.clone();
        for (int i = 0; i < a.length; ++i) {
            int n = i;
            result[n] = result[n] - b[i];
        }
        return result;
    }

    public static double[] ebeMultiply(double[] a, double[] b) throws DimensionMismatchException {
        MathArrays.checkEqualLength(a, b);
        double[] result = (double[])a.clone();
        for (int i = 0; i < a.length; ++i) {
            int n = i;
            result[n] = result[n] * b[i];
        }
        return result;
    }

    public static double[] ebeDivide(double[] a, double[] b) throws DimensionMismatchException {
        MathArrays.checkEqualLength(a, b);
        double[] result = (double[])a.clone();
        for (int i = 0; i < a.length; ++i) {
            int n = i;
            result[n] = result[n] / b[i];
        }
        return result;
    }

    public static double distance1(double[] p1, double[] p2) throws DimensionMismatchException {
        MathArrays.checkEqualLength(p1, p2);
        double sum = 0.0;
        for (int i = 0; i < p1.length; ++i) {
            sum += FastMath.abs(p1[i] - p2[i]);
        }
        return sum;
    }

    public static int distance1(int[] p1, int[] p2) throws DimensionMismatchException {
        MathArrays.checkEqualLength(p1, p2);
        int sum = 0;
        for (int i = 0; i < p1.length; ++i) {
            sum += FastMath.abs(p1[i] - p2[i]);
        }
        return sum;
    }

    public static double distance(double[] p1, double[] p2) throws DimensionMismatchException {
        MathArrays.checkEqualLength(p1, p2);
        double sum = 0.0;
        for (int i = 0; i < p1.length; ++i) {
            double dp = p1[i] - p2[i];
            sum += dp * dp;
        }
        return FastMath.sqrt(sum);
    }

    public static double cosAngle(double[] v1, double[] v2) {
        return MathArrays.linearCombination(v1, v2) / (MathArrays.safeNorm(v1) * MathArrays.safeNorm(v2));
    }

    public static double distance(int[] p1, int[] p2) throws DimensionMismatchException {
        MathArrays.checkEqualLength(p1, p2);
        double sum = 0.0;
        for (int i = 0; i < p1.length; ++i) {
            double dp = p1[i] - p2[i];
            sum += dp * dp;
        }
        return FastMath.sqrt(sum);
    }

    public static double distanceInf(double[] p1, double[] p2) throws DimensionMismatchException {
        MathArrays.checkEqualLength(p1, p2);
        double max = 0.0;
        for (int i = 0; i < p1.length; ++i) {
            max = FastMath.max(max, FastMath.abs(p1[i] - p2[i]));
        }
        return max;
    }

    public static int distanceInf(int[] p1, int[] p2) throws DimensionMismatchException {
        MathArrays.checkEqualLength(p1, p2);
        int max = 0;
        for (int i = 0; i < p1.length; ++i) {
            max = FastMath.max(max, FastMath.abs(p1[i] - p2[i]));
        }
        return max;
    }

    public static <T extends Comparable<? super T>> boolean isMonotonic(T[] val, OrderDirection dir, boolean strict) {
        T previous = val[0];
        int max = val.length;
        for (int i = 1; i < max; ++i) {
            switch (dir) {
                case INCREASING: {
                    int comp = previous.compareTo(val[i]);
                    if (!(strict ? comp >= 0 : comp > 0)) break;
                    return false;
                }
                case DECREASING: {
                    int comp = val[i].compareTo(previous);
                    if (!(strict ? comp >= 0 : comp > 0)) break;
                    return false;
                }
                default: {
                    throw new MathInternalError();
                }
            }
            previous = val[i];
        }
        return true;
    }

    public static boolean isMonotonic(double[] val, OrderDirection dir, boolean strict) {
        return MathArrays.checkOrder(val, dir, strict, false);
    }

    public static boolean checkEqualLength(double[] a, double[] b, boolean abort) {
        if (a.length == b.length) {
            return true;
        }
        if (abort) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        return false;
    }

    public static void checkEqualLength(double[] a, double[] b) {
        MathArrays.checkEqualLength(a, b, true);
    }

    public static boolean checkEqualLength(int[] a, int[] b, boolean abort) {
        if (a.length == b.length) {
            return true;
        }
        if (abort) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        return false;
    }

    public static void checkEqualLength(int[] a, int[] b) {
        MathArrays.checkEqualLength(a, b, true);
    }

    public static boolean checkOrder(double[] val, OrderDirection dir, boolean strict, boolean abort) throws NonMonotonicSequenceException {
        int index;
        double previous = val[0];
        int max = val.length;
        block4: for (index = 1; index < max; ++index) {
            switch (dir) {
                case INCREASING: {
                    if (!(strict ? val[index] <= previous : val[index] < previous)) break;
                    break block4;
                }
                case DECREASING: {
                    if (!(strict ? val[index] >= previous : val[index] > previous)) break;
                    break block4;
                }
                default: {
                    throw new MathInternalError();
                }
            }
            previous = val[index];
        }
        if (index == max) {
            return true;
        }
        if (abort) {
            throw new NonMonotonicSequenceException(val[index], (Number)previous, index, dir, strict);
        }
        return false;
    }

    public static void checkOrder(double[] val, OrderDirection dir, boolean strict) throws NonMonotonicSequenceException {
        MathArrays.checkOrder(val, dir, strict, true);
    }

    public static void checkOrder(double[] val) throws NonMonotonicSequenceException {
        MathArrays.checkOrder(val, OrderDirection.INCREASING, true);
    }

    public static void checkRectangular(long[][] in) throws NullArgumentException, DimensionMismatchException {
        MathUtils.checkNotNull(in);
        for (int i = 1; i < in.length; ++i) {
            if (in[i].length == in[0].length) continue;
            throw new DimensionMismatchException((Localizable)LocalizedFormats.DIFFERENT_ROWS_LENGTHS, in[i].length, in[0].length);
        }
    }

    public static void checkPositive(double[] in) throws NotStrictlyPositiveException {
        for (int i = 0; i < in.length; ++i) {
            if (!(in[i] <= 0.0)) continue;
            throw new NotStrictlyPositiveException(in[i]);
        }
    }

    public static void checkNotNaN(double[] in) throws NotANumberException {
        for (int i = 0; i < in.length; ++i) {
            if (!Double.isNaN(in[i])) continue;
            throw new NotANumberException();
        }
    }

    public static void checkNonNegative(long[] in) throws NotPositiveException {
        for (int i = 0; i < in.length; ++i) {
            if (in[i] >= 0L) continue;
            throw new NotPositiveException(in[i]);
        }
    }

    public static void checkNonNegative(long[][] in) throws NotPositiveException {
        for (int i = 0; i < in.length; ++i) {
            for (int j = 0; j < in[i].length; ++j) {
                if (in[i][j] >= 0L) continue;
                throw new NotPositiveException(in[i][j]);
            }
        }
    }

    public static double safeNorm(double[] v) {
        double rdwarf = 3.834E-20;
        double rgiant = 1.304E19;
        double s1 = 0.0;
        double s2 = 0.0;
        double s3 = 0.0;
        double x1max = 0.0;
        double x3max = 0.0;
        double floatn = v.length;
        double agiant = rgiant / floatn;
        for (int i = 0; i < v.length; ++i) {
            double xabs = FastMath.abs(v[i]);
            if (xabs < rdwarf || xabs > agiant) {
                double r;
                if (xabs > rdwarf) {
                    if (xabs > x1max) {
                        r = x1max / xabs;
                        s1 = 1.0 + s1 * r * r;
                        x1max = xabs;
                        continue;
                    }
                    r = xabs / x1max;
                    s1 += r * r;
                    continue;
                }
                if (xabs > x3max) {
                    r = x3max / xabs;
                    s3 = 1.0 + s3 * r * r;
                    x3max = xabs;
                    continue;
                }
                if (xabs == 0.0) continue;
                r = xabs / x3max;
                s3 += r * r;
                continue;
            }
            s2 += xabs * xabs;
        }
        double norm = s1 != 0.0 ? x1max * Math.sqrt(s1 + s2 / x1max / x1max) : (s2 == 0.0 ? x3max * Math.sqrt(s3) : (s2 >= x3max ? Math.sqrt(s2 * (1.0 + x3max / s2 * (x3max * s3))) : Math.sqrt(x3max * (s2 / x3max + x3max * s3))));
        return norm;
    }

    public static void sortInPlace(double[] x, double[] ... yList) throws DimensionMismatchException, NullArgumentException {
        MathArrays.sortInPlace(x, OrderDirection.INCREASING, yList);
    }

    public static void sortInPlace(double[] x, OrderDirection dir, double[] ... yList) throws NullArgumentException, DimensionMismatchException {
        if (x == null) {
            throw new NullArgumentException();
        }
        int yListLen = yList.length;
        int len = x.length;
        for (int j = 0; j < yListLen; ++j) {
            double[] y = yList[j];
            if (y == null) {
                throw new NullArgumentException();
            }
            if (y.length == len) continue;
            throw new DimensionMismatchException(y.length, len);
        }
        ArrayList<PairDoubleInteger> list = new ArrayList<PairDoubleInteger>(len);
        for (int i = 0; i < len; ++i) {
            list.add(new PairDoubleInteger(x[i], i));
        }
        Comparator<PairDoubleInteger> comp = dir == OrderDirection.INCREASING ? new Comparator<PairDoubleInteger>(){

            @Override
            public int compare(PairDoubleInteger o1, PairDoubleInteger o2) {
                return Double.compare(o1.getKey(), o2.getKey());
            }
        } : new Comparator<PairDoubleInteger>(){

            @Override
            public int compare(PairDoubleInteger o1, PairDoubleInteger o2) {
                return Double.compare(o2.getKey(), o1.getKey());
            }
        };
        Collections.sort(list, comp);
        int[] indices = new int[len];
        for (int i = 0; i < len; ++i) {
            PairDoubleInteger e = (PairDoubleInteger)list.get(i);
            x[i] = e.getKey();
            indices[i] = e.getValue();
        }
        for (int j = 0; j < yListLen; ++j) {
            double[] yInPlace = yList[j];
            double[] yOrig = (double[])yInPlace.clone();
            for (int i = 0; i < len; ++i) {
                yInPlace[i] = yOrig[indices[i]];
            }
        }
    }

    public static int[] copyOf(int[] source) {
        return MathArrays.copyOf(source, source.length);
    }

    public static double[] copyOf(double[] source) {
        return MathArrays.copyOf(source, source.length);
    }

    public static int[] copyOf(int[] source, int len) {
        int[] output = new int[len];
        System.arraycopy(source, 0, output, 0, FastMath.min(len, source.length));
        return output;
    }

    public static double[] copyOf(double[] source, int len) {
        double[] output = new double[len];
        System.arraycopy(source, 0, output, 0, FastMath.min(len, source.length));
        return output;
    }

    public static double[] copyOfRange(double[] source, int from, int to) {
        int len = to - from;
        double[] output = new double[len];
        System.arraycopy(source, from, output, 0, FastMath.min(len, source.length - from));
        return output;
    }

    public static double linearCombination(double[] a, double[] b) throws DimensionMismatchException {
        MathArrays.checkEqualLength(a, b);
        int len = a.length;
        if (len == 1) {
            return a[0] * b[0];
        }
        double[] prodHigh = new double[len];
        double prodLowSum = 0.0;
        for (int i = 0; i < len; ++i) {
            double ai = a[i];
            double aHigh = Double.longBitsToDouble(Double.doubleToRawLongBits(ai) & 0xFFFFFFFFF8000000L);
            double aLow = ai - aHigh;
            double bi = b[i];
            double bHigh = Double.longBitsToDouble(Double.doubleToRawLongBits(bi) & 0xFFFFFFFFF8000000L);
            double bLow = bi - bHigh;
            prodHigh[i] = ai * bi;
            double prodLow = aLow * bLow - (prodHigh[i] - aHigh * bHigh - aLow * bHigh - aHigh * bLow);
            prodLowSum += prodLow;
        }
        double prodHighCur = prodHigh[0];
        double prodHighNext = prodHigh[1];
        double sHighPrev = prodHighCur + prodHighNext;
        double sPrime = sHighPrev - prodHighNext;
        double sLowSum = prodHighNext - (sHighPrev - sPrime) + (prodHighCur - sPrime);
        int lenMinusOne = len - 1;
        for (int i = 1; i < lenMinusOne; ++i) {
            prodHighNext = prodHigh[i + 1];
            double sHighCur = sHighPrev + prodHighNext;
            sPrime = sHighCur - prodHighNext;
            sLowSum += prodHighNext - (sHighCur - sPrime) + (sHighPrev - sPrime);
            sHighPrev = sHighCur;
        }
        double result = sHighPrev + (prodLowSum + sLowSum);
        if (Double.isNaN(result)) {
            result = 0.0;
            for (int i = 0; i < len; ++i) {
                result += a[i] * b[i];
            }
        }
        return result;
    }

    public static double linearCombination(double a1, double b1, double a2, double b2) {
        double s12Prime;
        double s12Low;
        double b2High;
        double b2Low;
        double a2High;
        double a2Low;
        double prod2Low;
        double b1High;
        double b1Low;
        double prod1High = a1 * b1;
        double prod2High = a2 * b2;
        double s12High = prod1High + prod2High;
        double a1High = Double.longBitsToDouble(Double.doubleToRawLongBits(a1) & 0xFFFFFFFFF8000000L);
        double a1Low = a1 - a1High;
        double prod1Low = a1Low * (b1Low = b1 - (b1High = Double.longBitsToDouble(Double.doubleToRawLongBits(b1) & 0xFFFFFFFFF8000000L))) - (prod1High - a1High * b1High - a1Low * b1High - a1High * b1Low);
        double result = s12High + (prod1Low + (prod2Low = (a2Low = a2 - (a2High = Double.longBitsToDouble(Double.doubleToRawLongBits(a2) & 0xFFFFFFFFF8000000L))) * (b2Low = b2 - (b2High = Double.longBitsToDouble(Double.doubleToRawLongBits(b2) & 0xFFFFFFFFF8000000L))) - (prod2High - a2High * b2High - a2Low * b2High - a2High * b2Low)) + (s12Low = prod2High - (s12High - (s12Prime = s12High - prod2High)) + (prod1High - s12Prime)));
        if (Double.isNaN(result)) {
            result = a1 * b1 + a2 * b2;
        }
        return result;
    }

    public static double linearCombination(double a1, double b1, double a2, double b2, double a3, double b3) {
        double s123Prime;
        double s123Low;
        double s12Prime;
        double s12Low;
        double b3High;
        double b3Low;
        double a3High;
        double a3Low;
        double prod3Low;
        double b2High;
        double b2Low;
        double a2High;
        double a2Low;
        double prod2Low;
        double b1High;
        double b1Low;
        double prod1High = a1 * b1;
        double prod2High = a2 * b2;
        double s12High = prod1High + prod2High;
        double prod3High = a3 * b3;
        double s123High = s12High + prod3High;
        double a1High = Double.longBitsToDouble(Double.doubleToRawLongBits(a1) & 0xFFFFFFFFF8000000L);
        double a1Low = a1 - a1High;
        double prod1Low = a1Low * (b1Low = b1 - (b1High = Double.longBitsToDouble(Double.doubleToRawLongBits(b1) & 0xFFFFFFFFF8000000L))) - (prod1High - a1High * b1High - a1Low * b1High - a1High * b1Low);
        double result = s123High + (prod1Low + (prod2Low = (a2Low = a2 - (a2High = Double.longBitsToDouble(Double.doubleToRawLongBits(a2) & 0xFFFFFFFFF8000000L))) * (b2Low = b2 - (b2High = Double.longBitsToDouble(Double.doubleToRawLongBits(b2) & 0xFFFFFFFFF8000000L))) - (prod2High - a2High * b2High - a2Low * b2High - a2High * b2Low)) + (prod3Low = (a3Low = a3 - (a3High = Double.longBitsToDouble(Double.doubleToRawLongBits(a3) & 0xFFFFFFFFF8000000L))) * (b3Low = b3 - (b3High = Double.longBitsToDouble(Double.doubleToRawLongBits(b3) & 0xFFFFFFFFF8000000L))) - (prod3High - a3High * b3High - a3Low * b3High - a3High * b3Low)) + (s12Low = prod2High - (s12High - (s12Prime = s12High - prod2High)) + (prod1High - s12Prime)) + (s123Low = prod3High - (s123High - (s123Prime = s123High - prod3High)) + (s12High - s123Prime)));
        if (Double.isNaN(result)) {
            result = a1 * b1 + a2 * b2 + a3 * b3;
        }
        return result;
    }

    public static double linearCombination(double a1, double b1, double a2, double b2, double a3, double b3, double a4, double b4) {
        double s1234Prime;
        double s1234Low;
        double s123Prime;
        double s123Low;
        double s12Prime;
        double s12Low;
        double b4High;
        double b4Low;
        double a4High;
        double a4Low;
        double prod4Low;
        double b3High;
        double b3Low;
        double a3High;
        double a3Low;
        double prod3Low;
        double b2High;
        double b2Low;
        double a2High;
        double a2Low;
        double prod2Low;
        double b1High;
        double b1Low;
        double prod1High = a1 * b1;
        double prod2High = a2 * b2;
        double s12High = prod1High + prod2High;
        double prod3High = a3 * b3;
        double s123High = s12High + prod3High;
        double prod4High = a4 * b4;
        double s1234High = s123High + prod4High;
        double a1High = Double.longBitsToDouble(Double.doubleToRawLongBits(a1) & 0xFFFFFFFFF8000000L);
        double a1Low = a1 - a1High;
        double prod1Low = a1Low * (b1Low = b1 - (b1High = Double.longBitsToDouble(Double.doubleToRawLongBits(b1) & 0xFFFFFFFFF8000000L))) - (prod1High - a1High * b1High - a1Low * b1High - a1High * b1Low);
        double result = s1234High + (prod1Low + (prod2Low = (a2Low = a2 - (a2High = Double.longBitsToDouble(Double.doubleToRawLongBits(a2) & 0xFFFFFFFFF8000000L))) * (b2Low = b2 - (b2High = Double.longBitsToDouble(Double.doubleToRawLongBits(b2) & 0xFFFFFFFFF8000000L))) - (prod2High - a2High * b2High - a2Low * b2High - a2High * b2Low)) + (prod3Low = (a3Low = a3 - (a3High = Double.longBitsToDouble(Double.doubleToRawLongBits(a3) & 0xFFFFFFFFF8000000L))) * (b3Low = b3 - (b3High = Double.longBitsToDouble(Double.doubleToRawLongBits(b3) & 0xFFFFFFFFF8000000L))) - (prod3High - a3High * b3High - a3Low * b3High - a3High * b3Low)) + (prod4Low = (a4Low = a4 - (a4High = Double.longBitsToDouble(Double.doubleToRawLongBits(a4) & 0xFFFFFFFFF8000000L))) * (b4Low = b4 - (b4High = Double.longBitsToDouble(Double.doubleToRawLongBits(b4) & 0xFFFFFFFFF8000000L))) - (prod4High - a4High * b4High - a4Low * b4High - a4High * b4Low)) + (s12Low = prod2High - (s12High - (s12Prime = s12High - prod2High)) + (prod1High - s12Prime)) + (s123Low = prod3High - (s123High - (s123Prime = s123High - prod3High)) + (s12High - s123Prime)) + (s1234Low = prod4High - (s1234High - (s1234Prime = s1234High - prod4High)) + (s123High - s1234Prime)));
        if (Double.isNaN(result)) {
            result = a1 * b1 + a2 * b2 + a3 * b3 + a4 * b4;
        }
        return result;
    }

    public static boolean equals(float[] x, float[] y) {
        if (x == null || y == null) {
            return !(x == null ^ y == null);
        }
        if (x.length != y.length) {
            return false;
        }
        for (int i = 0; i < x.length; ++i) {
            if (Precision.equals(x[i], y[i])) continue;
            return false;
        }
        return true;
    }

    public static boolean equalsIncludingNaN(float[] x, float[] y) {
        if (x == null || y == null) {
            return !(x == null ^ y == null);
        }
        if (x.length != y.length) {
            return false;
        }
        for (int i = 0; i < x.length; ++i) {
            if (Precision.equalsIncludingNaN(x[i], y[i])) continue;
            return false;
        }
        return true;
    }

    public static boolean equals(double[] x, double[] y) {
        if (x == null || y == null) {
            return !(x == null ^ y == null);
        }
        if (x.length != y.length) {
            return false;
        }
        for (int i = 0; i < x.length; ++i) {
            if (Precision.equals(x[i], y[i])) continue;
            return false;
        }
        return true;
    }

    public static boolean equalsIncludingNaN(double[] x, double[] y) {
        if (x == null || y == null) {
            return !(x == null ^ y == null);
        }
        if (x.length != y.length) {
            return false;
        }
        for (int i = 0; i < x.length; ++i) {
            if (Precision.equalsIncludingNaN(x[i], y[i])) continue;
            return false;
        }
        return true;
    }

    public static double[] normalizeArray(double[] values, double normalizedSum) throws MathIllegalArgumentException, MathArithmeticException {
        int i;
        if (Double.isInfinite(normalizedSum)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_INFINITE, new Object[0]);
        }
        if (Double.isNaN(normalizedSum)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_NAN, new Object[0]);
        }
        double sum = 0.0;
        int len = values.length;
        double[] out = new double[len];
        for (i = 0; i < len; ++i) {
            if (Double.isInfinite(values[i])) {
                throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, values[i], i);
            }
            if (Double.isNaN(values[i])) continue;
            sum += values[i];
        }
        if (sum == 0.0) {
            throw new MathArithmeticException(LocalizedFormats.ARRAY_SUMS_TO_ZERO, new Object[0]);
        }
        for (i = 0; i < len; ++i) {
            out[i] = Double.isNaN(values[i]) ? Double.NaN : values[i] * normalizedSum / sum;
        }
        return out;
    }

    public static <T> T[] buildArray(Field<T> field, int length) {
        Object[] array = (Object[])Array.newInstance(field.getRuntimeClass(), length);
        Arrays.fill(array, field.getZero());
        return array;
    }

    public static <T> T[][] buildArray(Field<T> field, int rows, int columns) {
        Object[][] array;
        if (columns < 0) {
            T[] dummyRow = MathArrays.buildArray(field, 0);
            array = (Object[][])Array.newInstance(dummyRow.getClass(), rows);
        } else {
            array = (Object[][])Array.newInstance(field.getRuntimeClass(), rows, columns);
            for (int i = 0; i < rows; ++i) {
                Arrays.fill(array[i], field.getZero());
            }
        }
        return array;
    }

    public static double[] convolve(double[] x, double[] h) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(x);
        MathUtils.checkNotNull(h);
        int xLen = x.length;
        int hLen = h.length;
        if (xLen == 0 || hLen == 0) {
            throw new NoDataException();
        }
        int totalLength = xLen + hLen - 1;
        double[] y = new double[totalLength];
        for (int n = 0; n < totalLength; ++n) {
            double yn = 0.0;
            int k = FastMath.max(0, n + 1 - xLen);
            int j = n - k;
            while (k < hLen && j >= 0) {
                yn += x[j--] * h[k++];
            }
            y[n] = yn;
        }
        return y;
    }

    public static void shuffle(int[] list, int start, Position pos) {
        MathArrays.shuffle(list, start, pos, new Well19937c());
    }

    public static void shuffle(int[] list, int start, Position pos, RandomGenerator rng) {
        switch (pos) {
            case TAIL: {
                for (int i = list.length - 1; i >= start; --i) {
                    int target = i == start ? start : new UniformIntegerDistribution(rng, start, i).sample();
                    int temp = list[target];
                    list[target] = list[i];
                    list[i] = temp;
                }
                break;
            }
            case HEAD: {
                for (int i = 0; i <= start; ++i) {
                    int target = i == start ? start : new UniformIntegerDistribution(rng, i, start).sample();
                    int temp = list[target];
                    list[target] = list[i];
                    list[i] = temp;
                }
                break;
            }
            default: {
                throw new MathInternalError();
            }
        }
    }

    public static void shuffle(int[] list, RandomGenerator rng) {
        MathArrays.shuffle(list, 0, Position.TAIL, rng);
    }

    public static void shuffle(int[] list) {
        MathArrays.shuffle(list, new Well19937c());
    }

    public static int[] natural(int n) {
        return MathArrays.sequence(n, 0, 1);
    }

    public static int[] sequence(int size, int start, int stride) {
        int[] a = new int[size];
        for (int i = 0; i < size; ++i) {
            a[i] = start + i * stride;
        }
        return a;
    }

    public static boolean verifyValues(double[] values, int begin, int length) throws MathIllegalArgumentException {
        return MathArrays.verifyValues(values, begin, length, false);
    }

    public static boolean verifyValues(double[] values, int begin, int length, boolean allowEmpty) throws MathIllegalArgumentException {
        if (values == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        }
        if (begin < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.START_POSITION, begin);
        }
        if (length < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.LENGTH, length);
        }
        if (begin + length > values.length) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END, (Number)(begin + length), values.length, true);
        }
        return length != 0 || allowEmpty;
    }

    public static boolean verifyValues(double[] values, double[] weights, int begin, int length) throws MathIllegalArgumentException {
        return MathArrays.verifyValues(values, weights, begin, length, false);
    }

    public static boolean verifyValues(double[] values, double[] weights, int begin, int length, boolean allowEmpty) throws MathIllegalArgumentException {
        if (weights == null || values == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        }
        MathArrays.checkEqualLength(weights, values);
        boolean containsPositiveWeight = false;
        for (int i = begin; i < begin + length; ++i) {
            double weight = weights[i];
            if (Double.isNaN(weight)) {
                throw new MathIllegalArgumentException(LocalizedFormats.NAN_ELEMENT_AT_INDEX, i);
            }
            if (Double.isInfinite(weight)) {
                throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, weight, i);
            }
            if (weight < 0.0) {
                throw new MathIllegalArgumentException(LocalizedFormats.NEGATIVE_ELEMENT_AT_INDEX, i, weight);
            }
            if (containsPositiveWeight || !(weight > 0.0)) continue;
            containsPositiveWeight = true;
        }
        if (!containsPositiveWeight) {
            throw new MathIllegalArgumentException(LocalizedFormats.WEIGHT_AT_LEAST_ONE_NON_ZERO, new Object[0]);
        }
        return MathArrays.verifyValues(values, begin, length, allowEmpty);
    }

    public static double[] concatenate(double[] ... x) {
        int combinedLength = 0;
        for (double[] a : x) {
            combinedLength += a.length;
        }
        int offset = 0;
        int curLength = 0;
        double[] combined = new double[combinedLength];
        for (int i = 0; i < x.length; ++i) {
            curLength = x[i].length;
            System.arraycopy(x[i], 0, combined, offset, curLength);
            offset += curLength;
        }
        return combined;
    }

    public static double[] unique(double[] data) {
        TreeSet<Double> values = new TreeSet<Double>();
        for (int i = 0; i < data.length; ++i) {
            values.add(data[i]);
        }
        int count = values.size();
        double[] out = new double[count];
        Iterator iterator = values.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            out[count - ++i] = (Double)iterator.next();
        }
        return out;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Position {
        HEAD,
        TAIL;

    }

    private static class PairDoubleInteger {
        private final double key;
        private final int value;

        PairDoubleInteger(double key, int value) {
            this.key = key;
            this.value = value;
        }

        public double getKey() {
            return this.key;
        }

        public int getValue() {
            return this.value;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum OrderDirection {
        INCREASING,
        DECREASING;

    }

    public static interface Function {
        public double evaluate(double[] var1);

        public double evaluate(double[] var1, int var2, int var3);
    }
}

