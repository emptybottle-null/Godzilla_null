/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SchurTransformer;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.linear.TriDiagonalTransformer;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class EigenDecomposition {
    private static final double EPSILON = 1.0E-12;
    private byte maxIter = (byte)30;
    private double[] main;
    private double[] secondary;
    private TriDiagonalTransformer transformer;
    private double[] realEigenvalues;
    private double[] imagEigenvalues;
    private ArrayRealVector[] eigenvectors;
    private RealMatrix cachedV;
    private RealMatrix cachedD;
    private RealMatrix cachedVt;
    private final boolean isSymmetric;

    public EigenDecomposition(RealMatrix matrix) throws MathArithmeticException {
        double symTol = (double)(10 * matrix.getRowDimension() * matrix.getColumnDimension()) * Precision.EPSILON;
        this.isSymmetric = MatrixUtils.isSymmetric(matrix, symTol);
        if (this.isSymmetric) {
            this.transformToTridiagonal(matrix);
            this.findEigenVectors(this.transformer.getQ().getData());
        } else {
            SchurTransformer t = this.transformToSchur(matrix);
            this.findEigenVectorsFromSchur(t);
        }
    }

    @Deprecated
    public EigenDecomposition(RealMatrix matrix, double splitTolerance) throws MathArithmeticException {
        this(matrix);
    }

    public EigenDecomposition(double[] main, double[] secondary) {
        this.isSymmetric = true;
        this.main = (double[])main.clone();
        this.secondary = (double[])secondary.clone();
        this.transformer = null;
        int size = main.length;
        double[][] z = new double[size][size];
        for (int i = 0; i < size; ++i) {
            z[i][i] = 1.0;
        }
        this.findEigenVectors(z);
    }

    @Deprecated
    public EigenDecomposition(double[] main, double[] secondary, double splitTolerance) {
        this(main, secondary);
    }

    public RealMatrix getV() {
        if (this.cachedV == null) {
            int m = this.eigenvectors.length;
            this.cachedV = MatrixUtils.createRealMatrix(m, m);
            for (int k = 0; k < m; ++k) {
                this.cachedV.setColumnVector(k, this.eigenvectors[k]);
            }
        }
        return this.cachedV;
    }

    public RealMatrix getD() {
        if (this.cachedD == null) {
            this.cachedD = MatrixUtils.createRealDiagonalMatrix(this.realEigenvalues);
            for (int i = 0; i < this.imagEigenvalues.length; ++i) {
                if (Precision.compareTo(this.imagEigenvalues[i], 0.0, 1.0E-12) > 0) {
                    this.cachedD.setEntry(i, i + 1, this.imagEigenvalues[i]);
                    continue;
                }
                if (Precision.compareTo(this.imagEigenvalues[i], 0.0, 1.0E-12) >= 0) continue;
                this.cachedD.setEntry(i, i - 1, this.imagEigenvalues[i]);
            }
        }
        return this.cachedD;
    }

    public RealMatrix getVT() {
        if (this.cachedVt == null) {
            int m = this.eigenvectors.length;
            this.cachedVt = MatrixUtils.createRealMatrix(m, m);
            for (int k = 0; k < m; ++k) {
                this.cachedVt.setRowVector(k, this.eigenvectors[k]);
            }
        }
        return this.cachedVt;
    }

    public boolean hasComplexEigenvalues() {
        for (int i = 0; i < this.imagEigenvalues.length; ++i) {
            if (Precision.equals(this.imagEigenvalues[i], 0.0, 1.0E-12)) continue;
            return true;
        }
        return false;
    }

    public double[] getRealEigenvalues() {
        return (double[])this.realEigenvalues.clone();
    }

    public double getRealEigenvalue(int i) {
        return this.realEigenvalues[i];
    }

    public double[] getImagEigenvalues() {
        return (double[])this.imagEigenvalues.clone();
    }

    public double getImagEigenvalue(int i) {
        return this.imagEigenvalues[i];
    }

    public RealVector getEigenvector(int i) {
        return this.eigenvectors[i].copy();
    }

    public double getDeterminant() {
        double determinant = 1.0;
        for (double lambda : this.realEigenvalues) {
            determinant *= lambda;
        }
        return determinant;
    }

    public RealMatrix getSquareRoot() {
        if (!this.isSymmetric) {
            throw new MathUnsupportedOperationException();
        }
        double[] sqrtEigenValues = new double[this.realEigenvalues.length];
        for (int i = 0; i < this.realEigenvalues.length; ++i) {
            double eigen = this.realEigenvalues[i];
            if (eigen <= 0.0) {
                throw new MathUnsupportedOperationException();
            }
            sqrtEigenValues[i] = FastMath.sqrt(eigen);
        }
        RealMatrix sqrtEigen = MatrixUtils.createRealDiagonalMatrix(sqrtEigenValues);
        RealMatrix v = this.getV();
        RealMatrix vT = this.getVT();
        return v.multiply(sqrtEigen).multiply(vT);
    }

    public DecompositionSolver getSolver() {
        if (this.hasComplexEigenvalues()) {
            throw new MathUnsupportedOperationException();
        }
        return new Solver(this.realEigenvalues, this.imagEigenvalues, this.eigenvectors);
    }

    private void transformToTridiagonal(RealMatrix matrix) {
        this.transformer = new TriDiagonalTransformer(matrix);
        this.main = this.transformer.getMainDiagonalRef();
        this.secondary = this.transformer.getSecondaryDiagonalRef();
    }

    private void findEigenVectors(double[][] householderMatrix) {
        int i;
        double[][] z = (double[][])householderMatrix.clone();
        int n = this.main.length;
        this.realEigenvalues = new double[n];
        this.imagEigenvalues = new double[n];
        double[] e = new double[n];
        for (int i2 = 0; i2 < n - 1; ++i2) {
            this.realEigenvalues[i2] = this.main[i2];
            e[i2] = this.secondary[i2];
        }
        this.realEigenvalues[n - 1] = this.main[n - 1];
        e[n - 1] = 0.0;
        double maxAbsoluteValue = 0.0;
        for (i = 0; i < n; ++i) {
            if (FastMath.abs(this.realEigenvalues[i]) > maxAbsoluteValue) {
                maxAbsoluteValue = FastMath.abs(this.realEigenvalues[i]);
            }
            if (!(FastMath.abs(e[i]) > maxAbsoluteValue)) continue;
            maxAbsoluteValue = FastMath.abs(e[i]);
        }
        if (maxAbsoluteValue != 0.0) {
            for (i = 0; i < n; ++i) {
                if (FastMath.abs(this.realEigenvalues[i]) <= Precision.EPSILON * maxAbsoluteValue) {
                    this.realEigenvalues[i] = 0.0;
                }
                if (!(FastMath.abs(e[i]) <= Precision.EPSILON * maxAbsoluteValue)) continue;
                e[i] = 0.0;
            }
        }
        for (int j = 0; j < n; ++j) {
            int m;
            int its = 0;
            do {
                int i3;
                for (m = j; m < n - 1; ++m) {
                    double delta = FastMath.abs(this.realEigenvalues[m]) + FastMath.abs(this.realEigenvalues[m + 1]);
                    if (FastMath.abs(e[m]) + delta == delta) break;
                }
                if (m == j) continue;
                if (its == this.maxIter) {
                    throw new MaxCountExceededException((Localizable)LocalizedFormats.CONVERGENCE_FAILED, this.maxIter, new Object[0]);
                }
                ++its;
                double q = (this.realEigenvalues[j + 1] - this.realEigenvalues[j]) / (2.0 * e[j]);
                double t = FastMath.sqrt(1.0 + q * q);
                q = q < 0.0 ? this.realEigenvalues[m] - this.realEigenvalues[j] + e[j] / (q - t) : this.realEigenvalues[m] - this.realEigenvalues[j] + e[j] / (q + t);
                double u = 0.0;
                double s = 1.0;
                double c = 1.0;
                for (i3 = m - 1; i3 >= j; --i3) {
                    double p = s * e[i3];
                    double h = c * e[i3];
                    if (FastMath.abs(p) >= FastMath.abs(q)) {
                        c = q / p;
                        t = FastMath.sqrt(c * c + 1.0);
                        e[i3 + 1] = p * t;
                        s = 1.0 / t;
                        c *= s;
                    } else {
                        s = p / q;
                        t = FastMath.sqrt(s * s + 1.0);
                        e[i3 + 1] = q * t;
                        c = 1.0 / t;
                        s *= c;
                    }
                    if (e[i3 + 1] == 0.0) {
                        int n2 = i3 + 1;
                        this.realEigenvalues[n2] = this.realEigenvalues[n2] - u;
                        e[m] = 0.0;
                        break;
                    }
                    q = this.realEigenvalues[i3 + 1] - u;
                    t = (this.realEigenvalues[i3] - q) * s + 2.0 * c * h;
                    u = s * t;
                    this.realEigenvalues[i3 + 1] = q + u;
                    q = c * t - h;
                    for (int ia = 0; ia < n; ++ia) {
                        p = z[ia][i3 + 1];
                        z[ia][i3 + 1] = s * z[ia][i3] + c * p;
                        z[ia][i3] = c * z[ia][i3] - s * p;
                    }
                }
                if (t == 0.0 && i3 >= j) continue;
                int n3 = j;
                this.realEigenvalues[n3] = this.realEigenvalues[n3] - u;
                e[j] = q;
                e[m] = 0.0;
            } while (m != j);
        }
        for (i = 0; i < n; ++i) {
            int j;
            int k = i;
            double p = this.realEigenvalues[i];
            for (j = i + 1; j < n; ++j) {
                if (!(this.realEigenvalues[j] > p)) continue;
                k = j;
                p = this.realEigenvalues[j];
            }
            if (k == i) continue;
            this.realEigenvalues[k] = this.realEigenvalues[i];
            this.realEigenvalues[i] = p;
            for (j = 0; j < n; ++j) {
                p = z[j][i];
                z[j][i] = z[j][k];
                z[j][k] = p;
            }
        }
        maxAbsoluteValue = 0.0;
        for (i = 0; i < n; ++i) {
            if (!(FastMath.abs(this.realEigenvalues[i]) > maxAbsoluteValue)) continue;
            maxAbsoluteValue = FastMath.abs(this.realEigenvalues[i]);
        }
        if (maxAbsoluteValue != 0.0) {
            for (i = 0; i < n; ++i) {
                if (!(FastMath.abs(this.realEigenvalues[i]) < Precision.EPSILON * maxAbsoluteValue)) continue;
                this.realEigenvalues[i] = 0.0;
            }
        }
        this.eigenvectors = new ArrayRealVector[n];
        double[] tmp = new double[n];
        for (int i4 = 0; i4 < n; ++i4) {
            for (int j = 0; j < n; ++j) {
                tmp[j] = z[j][i4];
            }
            this.eigenvectors[i4] = new ArrayRealVector(tmp);
        }
    }

    private SchurTransformer transformToSchur(RealMatrix matrix) {
        SchurTransformer schurTransform = new SchurTransformer(matrix);
        double[][] matT = schurTransform.getT().getData();
        this.realEigenvalues = new double[matT.length];
        this.imagEigenvalues = new double[matT.length];
        for (int i = 0; i < this.realEigenvalues.length; ++i) {
            if (i == this.realEigenvalues.length - 1 || Precision.equals(matT[i + 1][i], 0.0, 1.0E-12)) {
                this.realEigenvalues[i] = matT[i][i];
                continue;
            }
            double x = matT[i + 1][i + 1];
            double p = 0.5 * (matT[i][i] - x);
            double z = FastMath.sqrt(FastMath.abs(p * p + matT[i + 1][i] * matT[i][i + 1]));
            this.realEigenvalues[i] = x + p;
            this.imagEigenvalues[i] = z;
            this.realEigenvalues[i + 1] = x + p;
            this.imagEigenvalues[i + 1] = -z;
            ++i;
        }
        return schurTransform;
    }

    private Complex cdiv(double xr, double xi, double yr, double yi) {
        return new Complex(xr, xi).divide(new Complex(yr, yi));
    }

    private void findEigenVectorsFromSchur(SchurTransformer schur) throws MathArithmeticException {
        double[][] matrixT = schur.getT().getData();
        double[][] matrixP = schur.getP().getData();
        int n = matrixT.length;
        double norm = 0.0;
        for (int i = 0; i < n; ++i) {
            for (int j = FastMath.max(i - 1, 0); j < n; ++j) {
                norm += FastMath.abs(matrixT[i][j]);
            }
        }
        if (Precision.equals(norm, 0.0, 1.0E-12)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        double r = 0.0;
        double s = 0.0;
        double z = 0.0;
        for (int idx = n - 1; idx >= 0; --idx) {
            double t;
            int i;
            int l;
            double p = this.realEigenvalues[idx];
            double q = this.imagEigenvalues[idx];
            if (Precision.equals(q, 0.0)) {
                l = idx;
                matrixT[idx][idx] = 1.0;
                for (i = idx - 1; i >= 0; --i) {
                    double w = matrixT[i][i] - p;
                    r = 0.0;
                    for (int j = l; j <= idx; ++j) {
                        r += matrixT[i][j] * matrixT[j][idx];
                    }
                    if (Precision.compareTo(this.imagEigenvalues[i], 0.0, 1.0E-12) < 0) {
                        z = w;
                        s = r;
                        continue;
                    }
                    l = i;
                    if (Precision.equals(this.imagEigenvalues[i], 0.0)) {
                        matrixT[i][idx] = w != 0.0 ? -r / w : -r / (Precision.EPSILON * norm);
                    } else {
                        double x = matrixT[i][i + 1];
                        double y = matrixT[i + 1][i];
                        q = (this.realEigenvalues[i] - p) * (this.realEigenvalues[i] - p) + this.imagEigenvalues[i] * this.imagEigenvalues[i];
                        matrixT[i][idx] = t = (x * s - z * r) / q;
                        matrixT[i + 1][idx] = FastMath.abs(x) > FastMath.abs(z) ? (-r - w * t) / x : (-s - y * t) / z;
                    }
                    double t2 = FastMath.abs(matrixT[i][idx]);
                    if (!(Precision.EPSILON * t2 * t2 > 1.0)) continue;
                    for (int j = i; j <= idx; ++j) {
                        double[] dArray = matrixT[j];
                        int n2 = idx;
                        dArray[n2] = dArray[n2] / t2;
                    }
                }
                continue;
            }
            if (!(q < 0.0)) continue;
            l = idx - 1;
            if (FastMath.abs(matrixT[idx][idx - 1]) > FastMath.abs(matrixT[idx - 1][idx])) {
                matrixT[idx - 1][idx - 1] = q / matrixT[idx][idx - 1];
                matrixT[idx - 1][idx] = -(matrixT[idx][idx] - p) / matrixT[idx][idx - 1];
            } else {
                Complex result = this.cdiv(0.0, -matrixT[idx - 1][idx], matrixT[idx - 1][idx - 1] - p, q);
                matrixT[idx - 1][idx - 1] = result.getReal();
                matrixT[idx - 1][idx] = result.getImaginary();
            }
            matrixT[idx][idx - 1] = 0.0;
            matrixT[idx][idx] = 1.0;
            for (i = idx - 2; i >= 0; --i) {
                double ra = 0.0;
                double sa = 0.0;
                for (int j = l; j <= idx; ++j) {
                    ra += matrixT[i][j] * matrixT[j][idx - 1];
                    sa += matrixT[i][j] * matrixT[j][idx];
                }
                double w = matrixT[i][i] - p;
                if (Precision.compareTo(this.imagEigenvalues[i], 0.0, 1.0E-12) < 0) {
                    z = w;
                    r = ra;
                    s = sa;
                    continue;
                }
                l = i;
                if (Precision.equals(this.imagEigenvalues[i], 0.0)) {
                    Complex c = this.cdiv(-ra, -sa, w, q);
                    matrixT[i][idx - 1] = c.getReal();
                    matrixT[i][idx] = c.getImaginary();
                } else {
                    double x = matrixT[i][i + 1];
                    double y = matrixT[i + 1][i];
                    double vr = (this.realEigenvalues[i] - p) * (this.realEigenvalues[i] - p) + this.imagEigenvalues[i] * this.imagEigenvalues[i] - q * q;
                    double vi = (this.realEigenvalues[i] - p) * 2.0 * q;
                    if (Precision.equals(vr, 0.0) && Precision.equals(vi, 0.0)) {
                        vr = Precision.EPSILON * norm * (FastMath.abs(w) + FastMath.abs(q) + FastMath.abs(x) + FastMath.abs(y) + FastMath.abs(z));
                    }
                    Complex c = this.cdiv(x * r - z * ra + q * sa, x * s - z * sa - q * ra, vr, vi);
                    matrixT[i][idx - 1] = c.getReal();
                    matrixT[i][idx] = c.getImaginary();
                    if (FastMath.abs(x) > FastMath.abs(z) + FastMath.abs(q)) {
                        matrixT[i + 1][idx - 1] = (-ra - w * matrixT[i][idx - 1] + q * matrixT[i][idx]) / x;
                        matrixT[i + 1][idx] = (-sa - w * matrixT[i][idx] - q * matrixT[i][idx - 1]) / x;
                    } else {
                        Complex c2 = this.cdiv(-r - y * matrixT[i][idx - 1], -s - y * matrixT[i][idx], z, q);
                        matrixT[i + 1][idx - 1] = c2.getReal();
                        matrixT[i + 1][idx] = c2.getImaginary();
                    }
                }
                t = FastMath.max(FastMath.abs(matrixT[i][idx - 1]), FastMath.abs(matrixT[i][idx]));
                if (!(Precision.EPSILON * t * t > 1.0)) continue;
                for (int j = i; j <= idx; ++j) {
                    double[] dArray = matrixT[j];
                    int n3 = idx - 1;
                    dArray[n3] = dArray[n3] / t;
                    double[] dArray2 = matrixT[j];
                    int n4 = idx;
                    dArray2[n4] = dArray2[n4] / t;
                }
            }
        }
        for (int j = n - 1; j >= 0; --j) {
            for (int i = 0; i <= n - 1; ++i) {
                z = 0.0;
                for (int k = 0; k <= FastMath.min(j, n - 1); ++k) {
                    z += matrixP[i][k] * matrixT[k][j];
                }
                matrixP[i][j] = z;
            }
        }
        this.eigenvectors = new ArrayRealVector[n];
        double[] tmp = new double[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                tmp[j] = matrixP[j][i];
            }
            this.eigenvectors[i] = new ArrayRealVector(tmp);
        }
    }

    private static class Solver
    implements DecompositionSolver {
        private double[] realEigenvalues;
        private double[] imagEigenvalues;
        private final ArrayRealVector[] eigenvectors;

        private Solver(double[] realEigenvalues, double[] imagEigenvalues, ArrayRealVector[] eigenvectors) {
            this.realEigenvalues = realEigenvalues;
            this.imagEigenvalues = imagEigenvalues;
            this.eigenvectors = eigenvectors;
        }

        public RealVector solve(RealVector b) {
            if (!this.isNonSingular()) {
                throw new SingularMatrixException();
            }
            int m = this.realEigenvalues.length;
            if (b.getDimension() != m) {
                throw new DimensionMismatchException(b.getDimension(), m);
            }
            double[] bp = new double[m];
            for (int i = 0; i < m; ++i) {
                ArrayRealVector v = this.eigenvectors[i];
                double[] vData = v.getDataRef();
                double s = v.dotProduct(b) / this.realEigenvalues[i];
                for (int j = 0; j < m; ++j) {
                    int n = j;
                    bp[n] = bp[n] + s * vData[j];
                }
            }
            return new ArrayRealVector(bp, false);
        }

        public RealMatrix solve(RealMatrix b) {
            if (!this.isNonSingular()) {
                throw new SingularMatrixException();
            }
            int m = this.realEigenvalues.length;
            if (b.getRowDimension() != m) {
                throw new DimensionMismatchException(b.getRowDimension(), m);
            }
            int nColB = b.getColumnDimension();
            double[][] bp = new double[m][nColB];
            double[] tmpCol = new double[m];
            for (int k = 0; k < nColB; ++k) {
                int i;
                for (i = 0; i < m; ++i) {
                    tmpCol[i] = b.getEntry(i, k);
                    bp[i][k] = 0.0;
                }
                for (i = 0; i < m; ++i) {
                    int j;
                    ArrayRealVector v = this.eigenvectors[i];
                    double[] vData = v.getDataRef();
                    double s = 0.0;
                    for (j = 0; j < m; ++j) {
                        s += v.getEntry(j) * tmpCol[j];
                    }
                    s /= this.realEigenvalues[i];
                    for (j = 0; j < m; ++j) {
                        double[] dArray = bp[j];
                        int n = k;
                        dArray[n] = dArray[n] + s * vData[j];
                    }
                }
            }
            return new Array2DRowRealMatrix(bp, false);
        }

        public boolean isNonSingular() {
            int i;
            double largestEigenvalueNorm = 0.0;
            for (i = 0; i < this.realEigenvalues.length; ++i) {
                largestEigenvalueNorm = FastMath.max(largestEigenvalueNorm, this.eigenvalueNorm(i));
            }
            if (largestEigenvalueNorm == 0.0) {
                return false;
            }
            for (i = 0; i < this.realEigenvalues.length; ++i) {
                if (!Precision.equals(this.eigenvalueNorm(i) / largestEigenvalueNorm, 0.0, 1.0E-12)) continue;
                return false;
            }
            return true;
        }

        private double eigenvalueNorm(int i) {
            double re = this.realEigenvalues[i];
            double im = this.imagEigenvalues[i];
            return FastMath.sqrt(re * re + im * im);
        }

        public RealMatrix getInverse() {
            if (!this.isNonSingular()) {
                throw new SingularMatrixException();
            }
            int m = this.realEigenvalues.length;
            double[][] invData = new double[m][m];
            for (int i = 0; i < m; ++i) {
                double[] invI = invData[i];
                for (int j = 0; j < m; ++j) {
                    double invIJ = 0.0;
                    for (int k = 0; k < m; ++k) {
                        double[] vK = this.eigenvectors[k].getDataRef();
                        invIJ += vK[i] * vK[j] / this.realEigenvalues[k];
                    }
                    invI[j] = invIJ;
                }
            }
            return MatrixUtils.createRealMatrix(invData);
        }
    }
}

