/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

class BicubicSplineFunction
implements BivariateFunction {
    private static final short N = 4;
    private final double[][] a = new double[4][4];
    private final BivariateFunction partialDerivativeX;
    private final BivariateFunction partialDerivativeY;
    private final BivariateFunction partialDerivativeXX;
    private final BivariateFunction partialDerivativeYY;
    private final BivariateFunction partialDerivativeXY;

    BicubicSplineFunction(double[] coeff) {
        this(coeff, false);
    }

    BicubicSplineFunction(double[] coeff, boolean initializeDerivatives) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.a[i][j] = coeff[i * 4 + j];
            }
        }
        if (initializeDerivatives) {
            final double[][] aX = new double[4][4];
            final double[][] aY = new double[4][4];
            final double[][] aXX = new double[4][4];
            final double[][] aYY = new double[4][4];
            final double[][] aXY = new double[4][4];
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    double c = this.a[i][j];
                    aX[i][j] = (double)i * c;
                    aY[i][j] = (double)j * c;
                    aXX[i][j] = (double)(i - 1) * aX[i][j];
                    aYY[i][j] = (double)(j - 1) * aY[i][j];
                    aXY[i][j] = (double)j * aX[i][j];
                }
            }
            this.partialDerivativeX = new BivariateFunction(){

                public double value(double x, double y) {
                    double x2 = x * x;
                    double[] pX = new double[]{0.0, 1.0, x, x2};
                    double y2 = y * y;
                    double y3 = y2 * y;
                    double[] pY = new double[]{1.0, y, y2, y3};
                    return BicubicSplineFunction.this.apply(pX, pY, aX);
                }
            };
            this.partialDerivativeY = new BivariateFunction(){

                public double value(double x, double y) {
                    double x2 = x * x;
                    double x3 = x2 * x;
                    double[] pX = new double[]{1.0, x, x2, x3};
                    double y2 = y * y;
                    double[] pY = new double[]{0.0, 1.0, y, y2};
                    return BicubicSplineFunction.this.apply(pX, pY, aY);
                }
            };
            this.partialDerivativeXX = new BivariateFunction(){

                public double value(double x, double y) {
                    double[] pX = new double[]{0.0, 0.0, 1.0, x};
                    double y2 = y * y;
                    double y3 = y2 * y;
                    double[] pY = new double[]{1.0, y, y2, y3};
                    return BicubicSplineFunction.this.apply(pX, pY, aXX);
                }
            };
            this.partialDerivativeYY = new BivariateFunction(){

                public double value(double x, double y) {
                    double x2 = x * x;
                    double x3 = x2 * x;
                    double[] pX = new double[]{1.0, x, x2, x3};
                    double[] pY = new double[]{0.0, 0.0, 1.0, y};
                    return BicubicSplineFunction.this.apply(pX, pY, aYY);
                }
            };
            this.partialDerivativeXY = new BivariateFunction(){

                public double value(double x, double y) {
                    double x2 = x * x;
                    double[] pX = new double[]{0.0, 1.0, x, x2};
                    double y2 = y * y;
                    double[] pY = new double[]{0.0, 1.0, y, y2};
                    return BicubicSplineFunction.this.apply(pX, pY, aXY);
                }
            };
        } else {
            this.partialDerivativeX = null;
            this.partialDerivativeY = null;
            this.partialDerivativeXX = null;
            this.partialDerivativeYY = null;
            this.partialDerivativeXY = null;
        }
    }

    public double value(double x, double y) {
        if (x < 0.0 || x > 1.0) {
            throw new OutOfRangeException(x, (Number)0, 1);
        }
        if (y < 0.0 || y > 1.0) {
            throw new OutOfRangeException(y, (Number)0, 1);
        }
        double x2 = x * x;
        double x3 = x2 * x;
        double[] pX = new double[]{1.0, x, x2, x3};
        double y2 = y * y;
        double y3 = y2 * y;
        double[] pY = new double[]{1.0, y, y2, y3};
        return this.apply(pX, pY, this.a);
    }

    private double apply(double[] pX, double[] pY, double[][] coeff) {
        double result = 0.0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                result += coeff[i][j] * pX[i] * pY[j];
            }
        }
        return result;
    }

    public BivariateFunction partialDerivativeX() {
        return this.partialDerivativeX;
    }

    public BivariateFunction partialDerivativeY() {
        return this.partialDerivativeY;
    }

    public BivariateFunction partialDerivativeXX() {
        return this.partialDerivativeXX;
    }

    public BivariateFunction partialDerivativeYY() {
        return this.partialDerivativeYY;
    }

    public BivariateFunction partialDerivativeXY() {
        return this.partialDerivativeXY;
    }
}

