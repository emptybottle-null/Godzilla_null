/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction;
import org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.fitting.PolynomialFitter;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

@Deprecated
public class SmoothingPolynomialBicubicSplineInterpolator
extends BicubicSplineInterpolator {
    private final PolynomialFitter xFitter;
    private final int xDegree;
    private final PolynomialFitter yFitter;
    private final int yDegree;

    public SmoothingPolynomialBicubicSplineInterpolator() {
        this(3);
    }

    public SmoothingPolynomialBicubicSplineInterpolator(int degree) throws NotPositiveException {
        this(degree, degree);
    }

    public SmoothingPolynomialBicubicSplineInterpolator(int xDegree, int yDegree) throws NotPositiveException {
        if (xDegree < 0) {
            throw new NotPositiveException(xDegree);
        }
        if (yDegree < 0) {
            throw new NotPositiveException(yDegree);
        }
        this.xDegree = xDegree;
        this.yDegree = yDegree;
        double safeFactor = 100.0;
        SimpleVectorValueChecker checker = new SimpleVectorValueChecker(100.0 * Precision.EPSILON, 100.0 * Precision.SAFE_MIN);
        this.xFitter = new PolynomialFitter(new GaussNewtonOptimizer(false, checker));
        this.yFitter = new PolynomialFitter(new GaussNewtonOptimizer(false, checker));
    }

    public BicubicSplineInterpolatingFunction interpolate(double[] xval, double[] yval, double[][] fval) throws NoDataException, NullArgumentException, DimensionMismatchException, NonMonotonicSequenceException {
        int i;
        if (xval.length == 0 || yval.length == 0 || fval.length == 0) {
            throw new NoDataException();
        }
        if (xval.length != fval.length) {
            throw new DimensionMismatchException(xval.length, fval.length);
        }
        int xLen = xval.length;
        int yLen = yval.length;
        for (int i2 = 0; i2 < xLen; ++i2) {
            if (fval[i2].length == yLen) continue;
            throw new DimensionMismatchException(fval[i2].length, yLen);
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        PolynomialFunction[] yPolyX = new PolynomialFunction[yLen];
        for (int j = 0; j < yLen; ++j) {
            this.xFitter.clearObservations();
            for (int i3 = 0; i3 < xLen; ++i3) {
                this.xFitter.addObservedPoint(1.0, xval[i3], fval[i3][j]);
            }
            yPolyX[j] = new PolynomialFunction(this.xFitter.fit(new double[this.xDegree + 1]));
        }
        double[][] fval_1 = new double[xLen][yLen];
        for (int j = 0; j < yLen; ++j) {
            PolynomialFunction f = yPolyX[j];
            for (i = 0; i < xLen; ++i) {
                fval_1[i][j] = f.value(xval[i]);
            }
        }
        PolynomialFunction[] xPolyY = new PolynomialFunction[xLen];
        for (int i4 = 0; i4 < xLen; ++i4) {
            this.yFitter.clearObservations();
            for (int j = 0; j < yLen; ++j) {
                this.yFitter.addObservedPoint(1.0, yval[j], fval_1[i4][j]);
            }
            xPolyY[i4] = new PolynomialFunction(this.yFitter.fit(new double[this.yDegree + 1]));
        }
        double[][] fval_2 = new double[xLen][yLen];
        for (i = 0; i < xLen; ++i) {
            PolynomialFunction f = xPolyY[i];
            for (int j = 0; j < yLen; ++j) {
                fval_2[i][j] = f.value(yval[j]);
            }
        }
        return super.interpolate(xval, yval, fval_2);
    }
}

