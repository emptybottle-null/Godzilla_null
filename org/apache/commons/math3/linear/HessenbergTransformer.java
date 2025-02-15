/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

class HessenbergTransformer {
    private final double[][] householderVectors;
    private final double[] ort;
    private RealMatrix cachedP;
    private RealMatrix cachedPt;
    private RealMatrix cachedH;

    HessenbergTransformer(RealMatrix matrix) {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        int m = matrix.getRowDimension();
        this.householderVectors = matrix.getData();
        this.ort = new double[m];
        this.cachedP = null;
        this.cachedPt = null;
        this.cachedH = null;
        this.transform();
    }

    public RealMatrix getP() {
        if (this.cachedP == null) {
            int j;
            int n = this.householderVectors.length;
            int high = n - 1;
            double[][] pa = new double[n][n];
            for (int i = 0; i < n; ++i) {
                for (j = 0; j < n; ++j) {
                    pa[i][j] = i == j ? 1.0 : 0.0;
                }
            }
            for (int m = high - 1; m >= 1; --m) {
                if (this.householderVectors[m][m - 1] == 0.0) continue;
                for (int i = m + 1; i <= high; ++i) {
                    this.ort[i] = this.householderVectors[i][m - 1];
                }
                for (j = m; j <= high; ++j) {
                    int i;
                    double g = 0.0;
                    for (i = m; i <= high; ++i) {
                        g += this.ort[i] * pa[i][j];
                    }
                    g = g / this.ort[m] / this.householderVectors[m][m - 1];
                    for (i = m; i <= high; ++i) {
                        double[] dArray = pa[i];
                        int n2 = j;
                        dArray[n2] = dArray[n2] + g * this.ort[i];
                    }
                }
            }
            this.cachedP = MatrixUtils.createRealMatrix(pa);
        }
        return this.cachedP;
    }

    public RealMatrix getPT() {
        if (this.cachedPt == null) {
            this.cachedPt = this.getP().transpose();
        }
        return this.cachedPt;
    }

    public RealMatrix getH() {
        if (this.cachedH == null) {
            int m = this.householderVectors.length;
            double[][] h = new double[m][m];
            for (int i = 0; i < m; ++i) {
                if (i > 0) {
                    h[i][i - 1] = this.householderVectors[i][i - 1];
                }
                for (int j = i; j < m; ++j) {
                    h[i][j] = this.householderVectors[i][j];
                }
            }
            this.cachedH = MatrixUtils.createRealMatrix(h);
        }
        return this.cachedH;
    }

    double[][] getHouseholderVectorsRef() {
        return this.householderVectors;
    }

    private void transform() {
        int n = this.householderVectors.length;
        int high = n - 1;
        for (int m = 1; m <= high - 1; ++m) {
            double f;
            double scale = 0.0;
            for (int i = m; i <= high; ++i) {
                scale += FastMath.abs(this.householderVectors[i][m - 1]);
            }
            if (Precision.equals(scale, 0.0)) continue;
            double h = 0.0;
            for (int i = high; i >= m; --i) {
                this.ort[i] = this.householderVectors[i][m - 1] / scale;
                h += this.ort[i] * this.ort[i];
            }
            double g = this.ort[m] > 0.0 ? -FastMath.sqrt(h) : FastMath.sqrt(h);
            h -= this.ort[m] * g;
            int n2 = m;
            this.ort[n2] = this.ort[n2] - g;
            for (int j = m; j < n; ++j) {
                int i;
                f = 0.0;
                for (i = high; i >= m; --i) {
                    f += this.ort[i] * this.householderVectors[i][j];
                }
                f /= h;
                for (i = m; i <= high; ++i) {
                    double[] dArray = this.householderVectors[i];
                    int n3 = j;
                    dArray[n3] = dArray[n3] - f * this.ort[i];
                }
            }
            for (int i = 0; i <= high; ++i) {
                int j;
                f = 0.0;
                for (j = high; j >= m; --j) {
                    f += this.ort[j] * this.householderVectors[i][j];
                }
                f /= h;
                for (j = m; j <= high; ++j) {
                    double[] dArray = this.householderVectors[i];
                    int n4 = j;
                    dArray[n4] = dArray[n4] - f * this.ort[j];
                }
            }
            this.ort[m] = scale * this.ort[m];
            this.householderVectors[m][m - 1] = scale * g;
        }
    }
}

