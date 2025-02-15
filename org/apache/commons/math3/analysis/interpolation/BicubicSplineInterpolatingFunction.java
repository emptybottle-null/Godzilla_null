/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import java.util.Arrays;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
public class BicubicSplineInterpolatingFunction
implements BivariateFunction {
    private static final int NUM_COEFF = 16;
    private static final double[][] AINV = new double[][]{{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {-3.0, 3.0, 0.0, 0.0, -2.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {2.0, -2.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, -3.0, 3.0, 0.0, 0.0, -2.0, -1.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, -2.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0}, {-3.0, 0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 0.0, -2.0, 0.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, -3.0, 0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 0.0, -2.0, 0.0, -1.0, 0.0}, {9.0, -9.0, -9.0, 9.0, 6.0, 3.0, -6.0, -3.0, 6.0, -6.0, 3.0, -3.0, 4.0, 2.0, 2.0, 1.0}, {-6.0, 6.0, 6.0, -6.0, -3.0, -3.0, 3.0, 3.0, -4.0, 4.0, -2.0, 2.0, -2.0, -2.0, -1.0, -1.0}, {2.0, 0.0, -2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0}, {0.0, 0.0, 0.0, 0.0, 2.0, 0.0, -2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0}, {-6.0, 6.0, 6.0, -6.0, -4.0, -2.0, 4.0, 2.0, -3.0, 3.0, -3.0, 3.0, -2.0, -1.0, -2.0, -1.0}, {4.0, -4.0, -4.0, 4.0, 2.0, 2.0, -2.0, -2.0, 2.0, -2.0, 2.0, -2.0, 1.0, 1.0, 1.0, 1.0}};
    private final double[] xval;
    private final double[] yval;
    private final BicubicSplineFunction[][] splines;
    private final BivariateFunction[][][] partialDerivatives;

    public BicubicSplineInterpolatingFunction(double[] x, double[] y, double[][] f, double[][] dFdX, double[][] dFdY, double[][] d2FdXdY) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        this(x, y, f, dFdX, dFdY, d2FdXdY, false);
    }

    public BicubicSplineInterpolatingFunction(double[] x, double[] y, double[][] f, double[][] dFdX, double[][] dFdY, double[][] d2FdXdY, boolean initializeDerivatives) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        int i;
        int xLen = x.length;
        int yLen = y.length;
        if (xLen == 0 || yLen == 0 || f.length == 0 || f[0].length == 0) {
            throw new NoDataException();
        }
        if (xLen != f.length) {
            throw new DimensionMismatchException(xLen, f.length);
        }
        if (xLen != dFdX.length) {
            throw new DimensionMismatchException(xLen, dFdX.length);
        }
        if (xLen != dFdY.length) {
            throw new DimensionMismatchException(xLen, dFdY.length);
        }
        if (xLen != d2FdXdY.length) {
            throw new DimensionMismatchException(xLen, d2FdXdY.length);
        }
        MathArrays.checkOrder(x);
        MathArrays.checkOrder(y);
        this.xval = (double[])x.clone();
        this.yval = (double[])y.clone();
        int lastI = xLen - 1;
        int lastJ = yLen - 1;
        this.splines = new BicubicSplineFunction[lastI][lastJ];
        for (i = 0; i < lastI; ++i) {
            if (f[i].length != yLen) {
                throw new DimensionMismatchException(f[i].length, yLen);
            }
            if (dFdX[i].length != yLen) {
                throw new DimensionMismatchException(dFdX[i].length, yLen);
            }
            if (dFdY[i].length != yLen) {
                throw new DimensionMismatchException(dFdY[i].length, yLen);
            }
            if (d2FdXdY[i].length != yLen) {
                throw new DimensionMismatchException(d2FdXdY[i].length, yLen);
            }
            int ip1 = i + 1;
            for (int j = 0; j < lastJ; ++j) {
                int jp1 = j + 1;
                double[] beta = new double[]{f[i][j], f[ip1][j], f[i][jp1], f[ip1][jp1], dFdX[i][j], dFdX[ip1][j], dFdX[i][jp1], dFdX[ip1][jp1], dFdY[i][j], dFdY[ip1][j], dFdY[i][jp1], dFdY[ip1][jp1], d2FdXdY[i][j], d2FdXdY[ip1][j], d2FdXdY[i][jp1], d2FdXdY[ip1][jp1]};
                this.splines[i][j] = new BicubicSplineFunction(this.computeSplineCoefficients(beta), initializeDerivatives);
            }
        }
        if (initializeDerivatives) {
            this.partialDerivatives = new BivariateFunction[5][lastI][lastJ];
            for (i = 0; i < lastI; ++i) {
                for (int j = 0; j < lastJ; ++j) {
                    BicubicSplineFunction bcs = this.splines[i][j];
                    this.partialDerivatives[0][i][j] = bcs.partialDerivativeX();
                    this.partialDerivatives[1][i][j] = bcs.partialDerivativeY();
                    this.partialDerivatives[2][i][j] = bcs.partialDerivativeXX();
                    this.partialDerivatives[3][i][j] = bcs.partialDerivativeYY();
                    this.partialDerivatives[4][i][j] = bcs.partialDerivativeXY();
                }
            }
        } else {
            this.partialDerivatives = null;
        }
    }

    public double value(double x, double y) throws OutOfRangeException {
        int i = this.searchIndex(x, this.xval);
        int j = this.searchIndex(y, this.yval);
        double xN = (x - this.xval[i]) / (this.xval[i + 1] - this.xval[i]);
        double yN = (y - this.yval[j]) / (this.yval[j + 1] - this.yval[j]);
        return this.splines[i][j].value(xN, yN);
    }

    public boolean isValidPoint(double x, double y) {
        return !(x < this.xval[0] || x > this.xval[this.xval.length - 1] || y < this.yval[0]) && !(y > this.yval[this.yval.length - 1]);
    }

    public double partialDerivativeX(double x, double y) throws OutOfRangeException {
        return this.partialDerivative(0, x, y);
    }

    public double partialDerivativeY(double x, double y) throws OutOfRangeException {
        return this.partialDerivative(1, x, y);
    }

    public double partialDerivativeXX(double x, double y) throws OutOfRangeException {
        return this.partialDerivative(2, x, y);
    }

    public double partialDerivativeYY(double x, double y) throws OutOfRangeException {
        return this.partialDerivative(3, x, y);
    }

    public double partialDerivativeXY(double x, double y) throws OutOfRangeException {
        return this.partialDerivative(4, x, y);
    }

    private double partialDerivative(int which, double x, double y) throws OutOfRangeException {
        int i = this.searchIndex(x, this.xval);
        int j = this.searchIndex(y, this.yval);
        double xN = (x - this.xval[i]) / (this.xval[i + 1] - this.xval[i]);
        double yN = (y - this.yval[j]) / (this.yval[j + 1] - this.yval[j]);
        return this.partialDerivatives[which][i][j].value(xN, yN);
    }

    private int searchIndex(double c, double[] val) {
        int r = Arrays.binarySearch(val, c);
        if (r == -1 || r == -val.length - 1) {
            throw new OutOfRangeException(c, (Number)val[0], val[val.length - 1]);
        }
        if (r < 0) {
            return -r - 2;
        }
        int last = val.length - 1;
        if (r == last) {
            return last - 1;
        }
        return r;
    }

    private double[] computeSplineCoefficients(double[] beta) {
        double[] a = new double[16];
        for (int i = 0; i < 16; ++i) {
            double result = 0.0;
            double[] row = AINV[i];
            for (int j = 0; j < 16; ++j) {
                result += row[j] * beta[j];
            }
            a[i] = result;
        }
        return a;
    }
}

