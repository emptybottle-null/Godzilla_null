/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction;
import org.apache.commons.math3.analysis.interpolation.BivariateGridInterpolator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

public class BicubicInterpolator
implements BivariateGridInterpolator {
    public BicubicInterpolatingFunction interpolate(final double[] xval, final double[] yval, double[][] fval) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException, NumberIsTooSmallException {
        if (xval.length == 0 || yval.length == 0 || fval.length == 0) {
            throw new NoDataException();
        }
        if (xval.length != fval.length) {
            throw new DimensionMismatchException(xval.length, fval.length);
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        int xLen = xval.length;
        int yLen = yval.length;
        double[][] dFdX = new double[xLen][yLen];
        double[][] dFdY = new double[xLen][yLen];
        double[][] d2FdXdY = new double[xLen][yLen];
        for (int i = 1; i < xLen - 1; ++i) {
            int nI = i + 1;
            int pI = i - 1;
            double nX = xval[nI];
            double pX = xval[pI];
            double deltaX = nX - pX;
            for (int j = 1; j < yLen - 1; ++j) {
                int nJ = j + 1;
                int pJ = j - 1;
                double nY = yval[nJ];
                double pY = yval[pJ];
                double deltaY = nY - pY;
                dFdX[i][j] = (fval[nI][j] - fval[pI][j]) / deltaX;
                dFdY[i][j] = (fval[i][nJ] - fval[i][pJ]) / deltaY;
                double deltaXY = deltaX * deltaY;
                d2FdXdY[i][j] = (fval[nI][nJ] - fval[nI][pJ] - fval[pI][nJ] + fval[pI][pJ]) / deltaXY;
            }
        }
        return new BicubicInterpolatingFunction(xval, yval, fval, dFdX, dFdY, d2FdXdY){

            public boolean isValidPoint(double x, double y) {
                return !(x < xval[1] || x > xval[xval.length - 2] || y < yval[1]) && !(y > yval[yval.length - 2]);
            }
        };
    }
}

