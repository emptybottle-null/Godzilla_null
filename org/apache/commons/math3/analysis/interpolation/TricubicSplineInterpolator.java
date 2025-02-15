/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolatingFunction;
import org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator;
import org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolatingFunction;
import org.apache.commons.math3.analysis.interpolation.TrivariateGridInterpolator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
public class TricubicSplineInterpolator
implements TrivariateGridInterpolator {
    public TricubicSplineInterpolatingFunction interpolate(double[] xval, double[] yval, double[] zval, double[][][] fval) throws NoDataException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        int i;
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
        double[][][] fvalXY = new double[zLen][xLen][yLen];
        double[][][] fvalZX = new double[yLen][zLen][xLen];
        for (int i2 = 0; i2 < xLen; ++i2) {
            if (fval[i2].length != yLen) {
                throw new DimensionMismatchException(fval[i2].length, yLen);
            }
            for (int j = 0; j < yLen; ++j) {
                if (fval[i2][j].length != zLen) {
                    throw new DimensionMismatchException(fval[i2][j].length, zLen);
                }
                for (int k = 0; k < zLen; ++k) {
                    double v;
                    fvalXY[k][i2][j] = v = fval[i2][j][k];
                    fvalZX[j][k][i2] = v;
                }
            }
        }
        BicubicSplineInterpolator bsi = new BicubicSplineInterpolator(true);
        BicubicSplineInterpolatingFunction[] xSplineYZ = new BicubicSplineInterpolatingFunction[xLen];
        for (int i3 = 0; i3 < xLen; ++i3) {
            xSplineYZ[i3] = bsi.interpolate(yval, zval, fval[i3]);
        }
        BicubicSplineInterpolatingFunction[] ySplineZX = new BicubicSplineInterpolatingFunction[yLen];
        for (int j = 0; j < yLen; ++j) {
            ySplineZX[j] = bsi.interpolate(zval, xval, fvalZX[j]);
        }
        BicubicSplineInterpolatingFunction[] zSplineXY = new BicubicSplineInterpolatingFunction[zLen];
        for (int k = 0; k < zLen; ++k) {
            zSplineXY[k] = bsi.interpolate(xval, yval, fvalXY[k]);
        }
        double[][][] dFdX = new double[xLen][yLen][zLen];
        double[][][] dFdY = new double[xLen][yLen][zLen];
        double[][][] d2FdXdY = new double[xLen][yLen][zLen];
        for (int k = 0; k < zLen; ++k) {
            BicubicSplineInterpolatingFunction f = zSplineXY[k];
            for (i = 0; i < xLen; ++i) {
                double x = xval[i];
                for (int j = 0; j < yLen; ++j) {
                    double y = yval[j];
                    dFdX[i][j][k] = f.partialDerivativeX(x, y);
                    dFdY[i][j][k] = f.partialDerivativeY(x, y);
                    d2FdXdY[i][j][k] = f.partialDerivativeXY(x, y);
                }
            }
        }
        double[][][] dFdZ = new double[xLen][yLen][zLen];
        double[][][] d2FdYdZ = new double[xLen][yLen][zLen];
        for (i = 0; i < xLen; ++i) {
            BicubicSplineInterpolatingFunction f = xSplineYZ[i];
            for (int j = 0; j < yLen; ++j) {
                double y = yval[j];
                for (int k = 0; k < zLen; ++k) {
                    double z = zval[k];
                    dFdZ[i][j][k] = f.partialDerivativeY(y, z);
                    d2FdYdZ[i][j][k] = f.partialDerivativeXY(y, z);
                }
            }
        }
        double[][][] d2FdZdX = new double[xLen][yLen][zLen];
        for (int j = 0; j < yLen; ++j) {
            BicubicSplineInterpolatingFunction f = ySplineZX[j];
            for (int k = 0; k < zLen; ++k) {
                double z = zval[k];
                for (int i4 = 0; i4 < xLen; ++i4) {
                    double x = xval[i4];
                    d2FdZdX[i4][j][k] = f.partialDerivativeXY(z, x);
                }
            }
        }
        double[][][] d3FdXdYdZ = new double[xLen][yLen][zLen];
        for (int i5 = 0; i5 < xLen; ++i5) {
            int nI = this.nextIndex(i5, xLen);
            int pI = this.previousIndex(i5);
            for (int j = 0; j < yLen; ++j) {
                int nJ = this.nextIndex(j, yLen);
                int pJ = this.previousIndex(j);
                for (int k = 0; k < zLen; ++k) {
                    int nK = this.nextIndex(k, zLen);
                    int pK = this.previousIndex(k);
                    d3FdXdYdZ[i5][j][k] = (fval[nI][nJ][nK] - fval[nI][pJ][nK] - fval[pI][nJ][nK] + fval[pI][pJ][nK] - fval[nI][nJ][pK] + fval[nI][pJ][pK] + fval[pI][nJ][pK] - fval[pI][pJ][pK]) / ((xval[nI] - xval[pI]) * (yval[nJ] - yval[pJ]) * (zval[nK] - zval[pK]));
                }
            }
        }
        return new TricubicSplineInterpolatingFunction(xval, yval, zval, fval, dFdX, dFdY, dFdZ, d2FdXdY, d2FdZdX, d2FdYdZ, d3FdXdYdZ);
    }

    private int nextIndex(int i, int max) {
        int index = i + 1;
        return index < max ? index : index - 1;
    }

    private int previousIndex(int i) {
        int index = i - 1;
        return index >= 0 ? index : 0;
    }
}

