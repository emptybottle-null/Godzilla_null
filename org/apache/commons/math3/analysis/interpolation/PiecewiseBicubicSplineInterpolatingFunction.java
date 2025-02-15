/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import java.util.Arrays;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.analysis.interpolation.AkimaSplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

public class PiecewiseBicubicSplineInterpolatingFunction
implements BivariateFunction {
    private static final int MIN_NUM_POINTS = 5;
    private final double[] xval;
    private final double[] yval;
    private final double[][] fval;

    public PiecewiseBicubicSplineInterpolatingFunction(double[] x, double[] y, double[][] f) throws DimensionMismatchException, NullArgumentException, NoDataException, NonMonotonicSequenceException {
        if (x == null || y == null || f == null || f[0] == null) {
            throw new NullArgumentException();
        }
        int xLen = x.length;
        int yLen = y.length;
        if (xLen == 0 || yLen == 0 || f.length == 0 || f[0].length == 0) {
            throw new NoDataException();
        }
        if (xLen < 5 || yLen < 5 || f.length < 5 || f[0].length < 5) {
            throw new InsufficientDataException();
        }
        if (xLen != f.length) {
            throw new DimensionMismatchException(xLen, f.length);
        }
        if (yLen != f[0].length) {
            throw new DimensionMismatchException(yLen, f[0].length);
        }
        MathArrays.checkOrder(x);
        MathArrays.checkOrder(y);
        this.xval = (double[])x.clone();
        this.yval = (double[])y.clone();
        this.fval = (double[][])f.clone();
    }

    public double value(double x, double y) throws OutOfRangeException {
        AkimaSplineInterpolator interpolator = new AkimaSplineInterpolator();
        int offset = 2;
        int count = 5;
        int i = this.searchIndex(x, this.xval, 2, 5);
        int j = this.searchIndex(y, this.yval, 2, 5);
        double[] xArray = new double[5];
        double[] yArray = new double[5];
        double[] zArray = new double[5];
        double[] interpArray = new double[5];
        for (int index = 0; index < 5; ++index) {
            xArray[index] = this.xval[i + index];
            yArray[index] = this.yval[j + index];
        }
        for (int zIndex = 0; zIndex < 5; ++zIndex) {
            for (int index = 0; index < 5; ++index) {
                zArray[index] = this.fval[i + index][j + zIndex];
            }
            PolynomialSplineFunction spline = interpolator.interpolate(xArray, zArray);
            interpArray[zIndex] = spline.value(x);
        }
        PolynomialSplineFunction spline = interpolator.interpolate(yArray, interpArray);
        double returnValue = spline.value(y);
        return returnValue;
    }

    public boolean isValidPoint(double x, double y) {
        return !(x < this.xval[0] || x > this.xval[this.xval.length - 1] || y < this.yval[0]) && !(y > this.yval[this.yval.length - 1]);
    }

    private int searchIndex(double c, double[] val, int offset, int count) {
        int r = Arrays.binarySearch(val, c);
        if (r == -1 || r == -val.length - 1) {
            throw new OutOfRangeException(c, (Number)val[0], val[val.length - 1]);
        }
        r = r < 0 ? -r - offset - 1 : (r -= offset);
        if (r < 0) {
            r = 0;
        }
        if (r + count >= val.length) {
            r = val.length - count;
        }
        return r;
    }
}

