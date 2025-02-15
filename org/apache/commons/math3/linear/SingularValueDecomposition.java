/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.DefaultRealMatrixPreservingVisitor;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class SingularValueDecomposition {
    private static final double EPS = 2.220446049250313E-16;
    private static final double TINY = 1.6033346880071782E-291;
    private final double[] singularValues;
    private final int m;
    private final int n;
    private final boolean transposed;
    private final RealMatrix cachedU;
    private RealMatrix cachedUt;
    private RealMatrix cachedS;
    private final RealMatrix cachedV;
    private RealMatrix cachedVt;
    private final double tol;

    public SingularValueDecomposition(RealMatrix matrix) {
        int i;
        double t;
        int k;
        int j;
        double[][] A2;
        if (matrix.getRowDimension() < matrix.getColumnDimension()) {
            this.transposed = true;
            A2 = matrix.transpose().getData();
            this.m = matrix.getColumnDimension();
            this.n = matrix.getRowDimension();
        } else {
            this.transposed = false;
            A2 = matrix.getData();
            this.m = matrix.getRowDimension();
            this.n = matrix.getColumnDimension();
        }
        this.singularValues = new double[this.n];
        double[][] U = new double[this.m][this.n];
        double[][] V = new double[this.n][this.n];
        double[] e = new double[this.n];
        double[] work = new double[this.m];
        int nct = FastMath.min(this.m - 1, this.n);
        int nrt = FastMath.max(0, this.n - 2);
        for (int k2 = 0; k2 < FastMath.max(nct, nrt); ++k2) {
            int i2;
            int i3;
            if (k2 < nct) {
                this.singularValues[k2] = 0.0;
                for (i3 = k2; i3 < this.m; ++i3) {
                    this.singularValues[k2] = FastMath.hypot(this.singularValues[k2], A2[i3][k2]);
                }
                if (this.singularValues[k2] != 0.0) {
                    if (A2[k2][k2] < 0.0) {
                        this.singularValues[k2] = -this.singularValues[k2];
                    }
                    for (i3 = k2; i3 < this.m; ++i3) {
                        double[] dArray = A2[i3];
                        int n = k2;
                        dArray[n] = dArray[n] / this.singularValues[k2];
                    }
                    double[] dArray = A2[k2];
                    int n = k2;
                    dArray[n] = dArray[n] + 1.0;
                }
                this.singularValues[k2] = -this.singularValues[k2];
            }
            for (j = k2 + 1; j < this.n; ++j) {
                if (k2 < nct && this.singularValues[k2] != 0.0) {
                    double t2 = 0.0;
                    for (i2 = k2; i2 < this.m; ++i2) {
                        t2 += A2[i2][k2] * A2[i2][j];
                    }
                    t2 = -t2 / A2[k2][k2];
                    for (i2 = k2; i2 < this.m; ++i2) {
                        double[] dArray = A2[i2];
                        int n = j;
                        dArray[n] = dArray[n] + t2 * A2[i2][k2];
                    }
                }
                e[j] = A2[k2][j];
            }
            if (k2 < nct) {
                for (i3 = k2; i3 < this.m; ++i3) {
                    U[i3][k2] = A2[i3][k2];
                }
            }
            if (k2 >= nrt) continue;
            e[k2] = 0.0;
            for (i3 = k2 + 1; i3 < this.n; ++i3) {
                e[k2] = FastMath.hypot(e[k2], e[i3]);
            }
            if (e[k2] != 0.0) {
                if (e[k2 + 1] < 0.0) {
                    e[k2] = -e[k2];
                }
                i3 = k2 + 1;
                while (i3 < this.n) {
                    int n = i3++;
                    e[n] = e[n] / e[k2];
                }
                int n = k2 + 1;
                e[n] = e[n] + 1.0;
            }
            e[k2] = -e[k2];
            if (k2 + 1 < this.m && e[k2] != 0.0) {
                for (i3 = k2 + 1; i3 < this.m; ++i3) {
                    work[i3] = 0.0;
                }
                for (j = k2 + 1; j < this.n; ++j) {
                    for (int i4 = k2 + 1; i4 < this.m; ++i4) {
                        int n = i4;
                        work[n] = work[n] + e[j] * A2[i4][j];
                    }
                }
                for (j = k2 + 1; j < this.n; ++j) {
                    double t3 = -e[j] / e[k2 + 1];
                    for (i2 = k2 + 1; i2 < this.m; ++i2) {
                        double[] dArray = A2[i2];
                        int n = j;
                        dArray[n] = dArray[n] + t3 * work[i2];
                    }
                }
            }
            for (i3 = k2 + 1; i3 < this.n; ++i3) {
                V[i3][k2] = e[i3];
            }
        }
        int p = this.n;
        if (nct < this.n) {
            this.singularValues[nct] = A2[nct][nct];
        }
        if (this.m < p) {
            this.singularValues[p - 1] = 0.0;
        }
        if (nrt + 1 < p) {
            e[nrt] = A2[nrt][p - 1];
        }
        e[p - 1] = 0.0;
        for (j = nct; j < this.n; ++j) {
            for (int i5 = 0; i5 < this.m; ++i5) {
                U[i5][j] = 0.0;
            }
            U[j][j] = 1.0;
        }
        for (k = nct - 1; k >= 0; --k) {
            int i6;
            if (this.singularValues[k] != 0.0) {
                for (int j2 = k + 1; j2 < this.n; ++j2) {
                    t = 0.0;
                    for (i = k; i < this.m; ++i) {
                        t += U[i][k] * U[i][j2];
                    }
                    t = -t / U[k][k];
                    for (i = k; i < this.m; ++i) {
                        double[] dArray = U[i];
                        int n = j2;
                        dArray[n] = dArray[n] + t * U[i][k];
                    }
                }
                for (i6 = k; i6 < this.m; ++i6) {
                    U[i6][k] = -U[i6][k];
                }
                U[k][k] = 1.0 + U[k][k];
                for (i6 = 0; i6 < k - 1; ++i6) {
                    U[i6][k] = 0.0;
                }
                continue;
            }
            for (i6 = 0; i6 < this.m; ++i6) {
                U[i6][k] = 0.0;
            }
            U[k][k] = 1.0;
        }
        for (k = this.n - 1; k >= 0; --k) {
            if (k < nrt && e[k] != 0.0) {
                for (int j3 = k + 1; j3 < this.n; ++j3) {
                    t = 0.0;
                    for (i = k + 1; i < this.n; ++i) {
                        t += V[i][k] * V[i][j3];
                    }
                    t = -t / V[k + 1][k];
                    for (i = k + 1; i < this.n; ++i) {
                        double[] dArray = V[i];
                        int n = j3;
                        dArray[n] = dArray[n] + t * V[i][k];
                    }
                }
            }
            for (int i7 = 0; i7 < this.n; ++i7) {
                V[i7][k] = 0.0;
            }
            V[k][k] = 1.0;
        }
        int pp = p - 1;
        block34: while (p > 0) {
            int kase;
            int k3;
            for (k3 = p - 2; k3 >= 0; --k3) {
                double threshold = 1.6033346880071782E-291 + 2.220446049250313E-16 * (FastMath.abs(this.singularValues[k3]) + FastMath.abs(this.singularValues[k3 + 1]));
                if (FastMath.abs(e[k3]) > threshold) continue;
                e[k3] = 0.0;
                break;
            }
            if (k3 == p - 2) {
                kase = 4;
            } else {
                int ks;
                for (ks = p - 1; ks >= k3 && ks != k3; --ks) {
                    double t4 = (ks != p ? FastMath.abs(e[ks]) : 0.0) + (ks != k3 + 1 ? FastMath.abs(e[ks - 1]) : 0.0);
                    if (!(FastMath.abs(this.singularValues[ks]) <= 1.6033346880071782E-291 + 2.220446049250313E-16 * t4)) continue;
                    this.singularValues[ks] = 0.0;
                    break;
                }
                if (ks == k3) {
                    kase = 3;
                } else if (ks == p - 1) {
                    kase = 1;
                } else {
                    kase = 2;
                    k3 = ks;
                }
            }
            ++k3;
            switch (kase) {
                case 1: {
                    int i8;
                    double sn;
                    double cs;
                    double t5;
                    double f = e[p - 2];
                    e[p - 2] = 0.0;
                    for (int j4 = p - 2; j4 >= k3; --j4) {
                        t5 = FastMath.hypot(this.singularValues[j4], f);
                        cs = this.singularValues[j4] / t5;
                        sn = f / t5;
                        this.singularValues[j4] = t5;
                        if (j4 != k3) {
                            f = -sn * e[j4 - 1];
                            e[j4 - 1] = cs * e[j4 - 1];
                        }
                        for (i8 = 0; i8 < this.n; ++i8) {
                            t5 = cs * V[i8][j4] + sn * V[i8][p - 1];
                            V[i8][p - 1] = -sn * V[i8][j4] + cs * V[i8][p - 1];
                            V[i8][j4] = t5;
                        }
                    }
                    continue block34;
                }
                case 2: {
                    int i8;
                    double sn;
                    double cs;
                    double t5;
                    double f = e[k3 - 1];
                    e[k3 - 1] = 0.0;
                    for (int j5 = k3; j5 < p; ++j5) {
                        t5 = FastMath.hypot(this.singularValues[j5], f);
                        cs = this.singularValues[j5] / t5;
                        sn = f / t5;
                        this.singularValues[j5] = t5;
                        f = -sn * e[j5];
                        e[j5] = cs * e[j5];
                        for (i8 = 0; i8 < this.m; ++i8) {
                            t5 = cs * U[i8][j5] + sn * U[i8][k3 - 1];
                            U[i8][k3 - 1] = -sn * U[i8][j5] + cs * U[i8][k3 - 1];
                            U[i8][j5] = t5;
                        }
                    }
                    continue block34;
                }
                case 3: {
                    double maxPm1Pm2 = FastMath.max(FastMath.abs(this.singularValues[p - 1]), FastMath.abs(this.singularValues[p - 2]));
                    double scale = FastMath.max(FastMath.max(FastMath.max(maxPm1Pm2, FastMath.abs(e[p - 2])), FastMath.abs(this.singularValues[k3])), FastMath.abs(e[k3]));
                    double sp = this.singularValues[p - 1] / scale;
                    double spm1 = this.singularValues[p - 2] / scale;
                    double epm1 = e[p - 2] / scale;
                    double sk = this.singularValues[k3] / scale;
                    double ek = e[k3] / scale;
                    double b = ((spm1 + sp) * (spm1 - sp) + epm1 * epm1) / 2.0;
                    double c = sp * epm1 * (sp * epm1);
                    double shift = 0.0;
                    if (b != 0.0 || c != 0.0) {
                        shift = FastMath.sqrt(b * b + c);
                        if (b < 0.0) {
                            shift = -shift;
                        }
                        shift = c / (b + shift);
                    }
                    double f = (sk + sp) * (sk - sp) + shift;
                    double g = sk * ek;
                    for (int j6 = k3; j6 < p - 1; ++j6) {
                        int i9;
                        double t6 = FastMath.hypot(f, g);
                        double cs = f / t6;
                        double sn = g / t6;
                        if (j6 != k3) {
                            e[j6 - 1] = t6;
                        }
                        f = cs * this.singularValues[j6] + sn * e[j6];
                        e[j6] = cs * e[j6] - sn * this.singularValues[j6];
                        g = sn * this.singularValues[j6 + 1];
                        this.singularValues[j6 + 1] = cs * this.singularValues[j6 + 1];
                        for (i9 = 0; i9 < this.n; ++i9) {
                            t6 = cs * V[i9][j6] + sn * V[i9][j6 + 1];
                            V[i9][j6 + 1] = -sn * V[i9][j6] + cs * V[i9][j6 + 1];
                            V[i9][j6] = t6;
                        }
                        t6 = FastMath.hypot(f, g);
                        cs = f / t6;
                        sn = g / t6;
                        this.singularValues[j6] = t6;
                        f = cs * e[j6] + sn * this.singularValues[j6 + 1];
                        this.singularValues[j6 + 1] = -sn * e[j6] + cs * this.singularValues[j6 + 1];
                        g = sn * e[j6 + 1];
                        e[j6 + 1] = cs * e[j6 + 1];
                        if (j6 >= this.m - 1) continue;
                        for (i9 = 0; i9 < this.m; ++i9) {
                            t6 = cs * U[i9][j6] + sn * U[i9][j6 + 1];
                            U[i9][j6 + 1] = -sn * U[i9][j6] + cs * U[i9][j6 + 1];
                            U[i9][j6] = t6;
                        }
                    }
                    e[p - 2] = f;
                    break;
                }
                default: {
                    if (this.singularValues[k3] <= 0.0) {
                        this.singularValues[k3] = this.singularValues[k3] < 0.0 ? -this.singularValues[k3] : 0.0;
                        for (int i10 = 0; i10 <= pp; ++i10) {
                            V[i10][k3] = -V[i10][k3];
                        }
                    }
                    while (k3 < pp && !(this.singularValues[k3] >= this.singularValues[k3 + 1])) {
                        int i11;
                        double t7 = this.singularValues[k3];
                        this.singularValues[k3] = this.singularValues[k3 + 1];
                        this.singularValues[k3 + 1] = t7;
                        if (k3 < this.n - 1) {
                            for (i11 = 0; i11 < this.n; ++i11) {
                                t7 = V[i11][k3 + 1];
                                V[i11][k3 + 1] = V[i11][k3];
                                V[i11][k3] = t7;
                            }
                        }
                        if (k3 < this.m - 1) {
                            for (i11 = 0; i11 < this.m; ++i11) {
                                t7 = U[i11][k3 + 1];
                                U[i11][k3 + 1] = U[i11][k3];
                                U[i11][k3] = t7;
                            }
                        }
                        ++k3;
                    }
                    --p;
                }
            }
        }
        this.tol = FastMath.max((double)this.m * this.singularValues[0] * 2.220446049250313E-16, FastMath.sqrt(Precision.SAFE_MIN));
        if (!this.transposed) {
            this.cachedU = MatrixUtils.createRealMatrix(U);
            this.cachedV = MatrixUtils.createRealMatrix(V);
        } else {
            this.cachedU = MatrixUtils.createRealMatrix(V);
            this.cachedV = MatrixUtils.createRealMatrix(U);
        }
    }

    public RealMatrix getU() {
        return this.cachedU;
    }

    public RealMatrix getUT() {
        if (this.cachedUt == null) {
            this.cachedUt = this.getU().transpose();
        }
        return this.cachedUt;
    }

    public RealMatrix getS() {
        if (this.cachedS == null) {
            this.cachedS = MatrixUtils.createRealDiagonalMatrix(this.singularValues);
        }
        return this.cachedS;
    }

    public double[] getSingularValues() {
        return (double[])this.singularValues.clone();
    }

    public RealMatrix getV() {
        return this.cachedV;
    }

    public RealMatrix getVT() {
        if (this.cachedVt == null) {
            this.cachedVt = this.getV().transpose();
        }
        return this.cachedVt;
    }

    public RealMatrix getCovariance(double minSingularValue) {
        int dimension;
        int p = this.singularValues.length;
        for (dimension = 0; dimension < p && this.singularValues[dimension] >= minSingularValue; ++dimension) {
        }
        if (dimension == 0) {
            throw new NumberIsTooLargeException((Localizable)LocalizedFormats.TOO_LARGE_CUTOFF_SINGULAR_VALUE, (Number)minSingularValue, this.singularValues[0], true);
        }
        final double[][] data = new double[dimension][p];
        this.getVT().walkInOptimizedOrder(new DefaultRealMatrixPreservingVisitor(){

            public void visit(int row, int column, double value) {
                data[row][column] = value / SingularValueDecomposition.this.singularValues[row];
            }
        }, 0, dimension - 1, 0, p - 1);
        Array2DRowRealMatrix jv = new Array2DRowRealMatrix(data, false);
        return jv.transpose().multiply(jv);
    }

    public double getNorm() {
        return this.singularValues[0];
    }

    public double getConditionNumber() {
        return this.singularValues[0] / this.singularValues[this.n - 1];
    }

    public double getInverseConditionNumber() {
        return this.singularValues[this.n - 1] / this.singularValues[0];
    }

    public int getRank() {
        int r = 0;
        for (int i = 0; i < this.singularValues.length; ++i) {
            if (!(this.singularValues[i] > this.tol)) continue;
            ++r;
        }
        return r;
    }

    public DecompositionSolver getSolver() {
        return new Solver(this.singularValues, this.getUT(), this.getV(), this.getRank() == this.m, this.tol);
    }

    private static class Solver
    implements DecompositionSolver {
        private final RealMatrix pseudoInverse;
        private boolean nonSingular;

        private Solver(double[] singularValues, RealMatrix uT, RealMatrix v, boolean nonSingular, double tol) {
            double[][] suT = uT.getData();
            for (int i = 0; i < singularValues.length; ++i) {
                double a = singularValues[i] > tol ? 1.0 / singularValues[i] : 0.0;
                double[] suTi = suT[i];
                int j = 0;
                while (j < suTi.length) {
                    int n = j++;
                    suTi[n] = suTi[n] * a;
                }
            }
            this.pseudoInverse = v.multiply(new Array2DRowRealMatrix(suT, false));
            this.nonSingular = nonSingular;
        }

        public RealVector solve(RealVector b) {
            return this.pseudoInverse.operate(b);
        }

        public RealMatrix solve(RealMatrix b) {
            return this.pseudoInverse.multiply(b);
        }

        public boolean isNonSingular() {
            return this.nonSingular;
        }

        public RealMatrix getInverse() {
            return this.pseudoInverse;
        }
    }
}

