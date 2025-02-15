/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.interpolation.TricubicInterpolatingFunction;
import org.apache.commons.math3.analysis.interpolation.TrivariateGridInterpolator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

public class TricubicInterpolator
implements TrivariateGridInterpolator {
    public TricubicInterpolatingFunction interpolate(final double[] xval, final double[] yval, final double[] zval, double[][][] fval) throws NoDataException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        if (xval.length == 0 || yval.length == 0 || zval.length == 0 || fval.length == 0) {
            throw new NoDataException();
        }
        if (xval.length != fval.length) {
            throw new DimensionMismatchException(xval.length, fval.length);
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        MathArrays.checkOrder(zval);
        int xLen = xval.length;
        int yLen = yval.length;
        int zLen = zval.length;
        double[][][] dFdX = new double[xLen][yLen][zLen];
        double[][][] dFdY = new double[xLen][yLen][zLen];
        double[][][] dFdZ = new double[xLen][yLen][zLen];
        double[][][] d2FdXdY = new double[xLen][yLen][zLen];
        double[][][] d2FdXdZ = new double[xLen][yLen][zLen];
        double[][][] d2FdYdZ = new double[xLen][yLen][zLen];
        double[][][] d3FdXdYdZ = new double[xLen][yLen][zLen];
        for (int i = 1; i < xLen - 1; ++i) {
            if (yval.length != fval[i].length) {
                throw new DimensionMismatchException(yval.length, fval[i].length);
            }
            int nI = i + 1;
            int pI = i - 1;
            double nX = xval[nI];
            double pX = xval[pI];
            double deltaX = nX - pX;
            for (int j = 1; j < yLen - 1; ++j) {
                if (zval.length != fval[i][j].length) {
                    throw new DimensionMismatchException(zval.length, fval[i][j].length);
                }
                int nJ = j + 1;
                int pJ = j - 1;
                double nY = yval[nJ];
                double pY = yval[pJ];
                double deltaY = nY - pY;
                double deltaXY = deltaX * deltaY;
                for (int k = 1; k < zLen - 1; ++k) {
                    int nK = k + 1;
                    int pK = k - 1;
                    double nZ = zval[nK];
                    double pZ = zval[pK];
                    double deltaZ = nZ - pZ;
                    dFdX[i][j][k] = (fval[nI][j][k] - fval[pI][j][k]) / deltaX;
                    dFdY[i][j][k] = (fval[i][nJ][k] - fval[i][pJ][k]) / deltaY;
                    dFdZ[i][j][k] = (fval[i][j][nK] - fval[i][j][pK]) / deltaZ;
                    double deltaXZ = deltaX * deltaZ;
                    double deltaYZ = deltaY * deltaZ;
                    d2FdXdY[i][j][k] = (fval[nI][nJ][k] - fval[nI][pJ][k] - fval[pI][nJ][k] + fval[pI][pJ][k]) / deltaXY;
                    d2FdXdZ[i][j][k] = (fval[nI][j][nK] - fval[nI][j][pK] - fval[pI][j][nK] + fval[pI][j][pK]) / deltaXZ;
                    d2FdYdZ[i][j][k] = (fval[i][nJ][nK] - fval[i][nJ][pK] - fval[i][pJ][nK] + fval[i][pJ][pK]) / deltaYZ;
                    double deltaXYZ = deltaXY * deltaZ;
                    d3FdXdYdZ[i][j][k] = (fval[nI][nJ][nK] - fval[nI][pJ][nK] - fval[pI][nJ][nK] + fval[pI][pJ][nK] - fval[nI][nJ][pK] + fval[nI][pJ][pK] + fval[pI][nJ][pK] - fval[pI][pJ][pK]) / deltaXYZ;
                }
            }
        }
        return new TricubicInterpolatingFunction(xval, yval, zval, fval, dFdX, dFdY, dFdZ, d2FdXdY, d2FdXdZ, d2FdYdZ, d3FdXdYdZ){

            public boolean isValidPoint(double x, double y, double z) {
                return !(x < xval[1] || x > xval[xval.length - 2] || y < yval[1] || y > yval[yval.length - 2] || z < zval[1]) && !(z > zval[zval.length - 2]);
            }
        };
    }
}

