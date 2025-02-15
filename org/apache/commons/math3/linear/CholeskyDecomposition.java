/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonPositiveDefiniteMatrixException;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.NonSymmetricMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.FastMath;

public class CholeskyDecomposition {
    public static final double DEFAULT_RELATIVE_SYMMETRY_THRESHOLD = 1.0E-15;
    public static final double DEFAULT_ABSOLUTE_POSITIVITY_THRESHOLD = 1.0E-10;
    private double[][] lTData;
    private RealMatrix cachedL;
    private RealMatrix cachedLT;

    public CholeskyDecomposition(RealMatrix matrix) {
        this(matrix, 1.0E-15, 1.0E-10);
    }

    public CholeskyDecomposition(RealMatrix matrix, double relativeSymmetryThreshold, double absolutePositivityThreshold) {
        int i;
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        int order = matrix.getRowDimension();
        this.lTData = matrix.getData();
        this.cachedL = null;
        this.cachedLT = null;
        for (i = 0; i < order; ++i) {
            double[] lI = this.lTData[i];
            for (int j = i + 1; j < order; ++j) {
                double[] lJ = this.lTData[j];
                double lIJ = lI[j];
                double lJI = lJ[i];
                double maxDelta = relativeSymmetryThreshold * FastMath.max(FastMath.abs(lIJ), FastMath.abs(lJI));
                if (FastMath.abs(lIJ - lJI) > maxDelta) {
                    throw new NonSymmetricMatrixException(i, j, relativeSymmetryThreshold);
                }
                lJ[i] = 0.0;
            }
        }
        for (i = 0; i < order; ++i) {
            double[] ltI = this.lTData[i];
            if (ltI[i] <= absolutePositivityThreshold) {
                throw new NonPositiveDefiniteMatrixException(ltI[i], i, absolutePositivityThreshold);
            }
            ltI[i] = FastMath.sqrt(ltI[i]);
            double inverse = 1.0 / ltI[i];
            for (int q = order - 1; q > i; --q) {
                int n = q;
                ltI[n] = ltI[n] * inverse;
                double[] ltQ = this.lTData[q];
                for (int p = q; p < order; ++p) {
                    int n2 = p;
                    ltQ[n2] = ltQ[n2] - ltI[q] * ltI[p];
                }
            }
        }
    }

    public RealMatrix getL() {
        if (this.cachedL == null) {
            this.cachedL = this.getLT().transpose();
        }
        return this.cachedL;
    }

    public RealMatrix getLT() {
        if (this.cachedLT == null) {
            this.cachedLT = MatrixUtils.createRealMatrix(this.lTData);
        }
        return this.cachedLT;
    }

    public double getDeterminant() {
        double determinant = 1.0;
        for (int i = 0; i < this.lTData.length; ++i) {
            double lTii = this.lTData[i][i];
            determinant *= lTii * lTii;
        }
        return determinant;
    }

    public DecompositionSolver getSolver() {
        return new Solver(this.lTData);
    }

    private static class Solver
    implements DecompositionSolver {
        private final double[][] lTData;

        private Solver(double[][] lTData) {
            this.lTData = lTData;
        }

        public boolean isNonSingular() {
            return true;
        }

        public RealVector solve(RealVector b) {
            int j;
            int m = this.lTData.length;
            if (b.getDimension() != m) {
                throw new DimensionMismatchException(b.getDimension(), m);
            }
            double[] x = b.toArray();
            for (j = 0; j < m; ++j) {
                double[] lJ = this.lTData[j];
                int n = j;
                x[n] = x[n] / lJ[j];
                double xJ = x[j];
                for (int i = j + 1; i < m; ++i) {
                    int n2 = i;
                    x[n2] = x[n2] - xJ * lJ[i];
                }
            }
            for (j = m - 1; j >= 0; --j) {
                int n = j;
                x[n] = x[n] / this.lTData[j][j];
                double xJ = x[j];
                for (int i = 0; i < j; ++i) {
                    int n3 = i;
                    x[n3] = x[n3] - xJ * this.lTData[i][j];
                }
            }
            return new ArrayRealVector(x, false);
        }

        public RealMatrix solve(RealMatrix b) {
            int j;
            int m = this.lTData.length;
            if (b.getRowDimension() != m) {
                throw new DimensionMismatchException(b.getRowDimension(), m);
            }
            int nColB = b.getColumnDimension();
            double[][] x = b.getData();
            for (j = 0; j < m; ++j) {
                double[] lJ = this.lTData[j];
                double lJJ = lJ[j];
                double[] xJ = x[j];
                int k = 0;
                while (k < nColB) {
                    int n = k++;
                    xJ[n] = xJ[n] / lJJ;
                }
                for (int i = j + 1; i < m; ++i) {
                    double[] xI = x[i];
                    double lJI = lJ[i];
                    for (int k2 = 0; k2 < nColB; ++k2) {
                        int n = k2;
                        xI[n] = xI[n] - xJ[k2] * lJI;
                    }
                }
            }
            for (j = m - 1; j >= 0; --j) {
                double lJJ = this.lTData[j][j];
                double[] xJ = x[j];
                int k = 0;
                while (k < nColB) {
                    int n = k++;
                    xJ[n] = xJ[n] / lJJ;
                }
                for (int i = 0; i < j; ++i) {
                    double[] xI = x[i];
                    double lIJ = this.lTData[i][j];
                    for (int k3 = 0; k3 < nColB; ++k3) {
                        int n = k3;
                        xI[n] = xI[n] - xJ[k3] * lIJ;
                    }
                }
            }
            return new Array2DRowRealMatrix(x);
        }

        public RealMatrix getInverse() {
            return this.solve(MatrixUtils.createRealIdentityMatrix(this.lTData.length));
        }
    }
}

