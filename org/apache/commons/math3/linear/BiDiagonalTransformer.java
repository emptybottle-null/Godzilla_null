/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;

class BiDiagonalTransformer {
    private final double[][] householderVectors;
    private final double[] main;
    private final double[] secondary;
    private RealMatrix cachedU;
    private RealMatrix cachedB;
    private RealMatrix cachedV;

    BiDiagonalTransformer(RealMatrix matrix) {
        int m = matrix.getRowDimension();
        int n = matrix.getColumnDimension();
        int p = FastMath.min(m, n);
        this.householderVectors = matrix.getData();
        this.main = new double[p];
        this.secondary = new double[p - 1];
        this.cachedU = null;
        this.cachedB = null;
        this.cachedV = null;
        if (m >= n) {
            this.transformToUpperBiDiagonal();
        } else {
            this.transformToLowerBiDiagonal();
        }
    }

    public RealMatrix getU() {
        if (this.cachedU == null) {
            int k;
            int m = this.householderVectors.length;
            int n = this.householderVectors[0].length;
            int p = this.main.length;
            int diagOffset = m >= n ? 0 : 1;
            double[] diagonal = m >= n ? this.main : this.secondary;
            double[][] ua = new double[m][m];
            for (k = m - 1; k >= p; --k) {
                ua[k][k] = 1.0;
            }
            for (k = p - 1; k >= diagOffset; --k) {
                double[] hK = this.householderVectors[k];
                ua[k][k] = 1.0;
                if (hK[k - diagOffset] == 0.0) continue;
                for (int j = k; j < m; ++j) {
                    int i;
                    double alpha = 0.0;
                    for (i = k; i < m; ++i) {
                        alpha -= ua[i][j] * this.householderVectors[i][k - diagOffset];
                    }
                    alpha /= diagonal[k - diagOffset] * hK[k - diagOffset];
                    for (i = k; i < m; ++i) {
                        double[] dArray = ua[i];
                        int n2 = j;
                        dArray[n2] = dArray[n2] + -alpha * this.householderVectors[i][k - diagOffset];
                    }
                }
            }
            if (diagOffset > 0) {
                ua[0][0] = 1.0;
            }
            this.cachedU = MatrixUtils.createRealMatrix(ua);
        }
        return this.cachedU;
    }

    public RealMatrix getB() {
        if (this.cachedB == null) {
            int m = this.householderVectors.length;
            int n = this.householderVectors[0].length;
            double[][] ba = new double[m][n];
            for (int i = 0; i < this.main.length; ++i) {
                ba[i][i] = this.main[i];
                if (m < n) {
                    if (i <= 0) continue;
                    ba[i][i - 1] = this.secondary[i - 1];
                    continue;
                }
                if (i >= this.main.length - 1) continue;
                ba[i][i + 1] = this.secondary[i];
            }
            this.cachedB = MatrixUtils.createRealMatrix(ba);
        }
        return this.cachedB;
    }

    public RealMatrix getV() {
        if (this.cachedV == null) {
            int k;
            int m = this.householderVectors.length;
            int n = this.householderVectors[0].length;
            int p = this.main.length;
            int diagOffset = m >= n ? 1 : 0;
            double[] diagonal = m >= n ? this.secondary : this.main;
            double[][] va = new double[n][n];
            for (k = n - 1; k >= p; --k) {
                va[k][k] = 1.0;
            }
            for (k = p - 1; k >= diagOffset; --k) {
                double[] hK = this.householderVectors[k - diagOffset];
                va[k][k] = 1.0;
                if (hK[k] == 0.0) continue;
                for (int j = k; j < n; ++j) {
                    int i;
                    double beta = 0.0;
                    for (i = k; i < n; ++i) {
                        beta -= va[i][j] * hK[i];
                    }
                    beta /= diagonal[k - diagOffset] * hK[k];
                    for (i = k; i < n; ++i) {
                        double[] dArray = va[i];
                        int n2 = j;
                        dArray[n2] = dArray[n2] + -beta * hK[i];
                    }
                }
            }
            if (diagOffset > 0) {
                va[0][0] = 1.0;
            }
            this.cachedV = MatrixUtils.createRealMatrix(va);
        }
        return this.cachedV;
    }

    double[][] getHouseholderVectorsRef() {
        return this.householderVectors;
    }

    double[] getMainDiagonalRef() {
        return this.main;
    }

    double[] getSecondaryDiagonalRef() {
        return this.secondary;
    }

    boolean isUpperBiDiagonal() {
        return this.householderVectors.length >= this.householderVectors[0].length;
    }

    private void transformToUpperBiDiagonal() {
        int m = this.householderVectors.length;
        int n = this.householderVectors[0].length;
        for (int k = 0; k < n; ++k) {
            double b;
            int j;
            double a;
            double xNormSqr = 0.0;
            for (int i = k; i < m; ++i) {
                double c = this.householderVectors[i][k];
                xNormSqr += c * c;
            }
            double[] hK = this.householderVectors[k];
            this.main[k] = a = hK[k] > 0.0 ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            if (a != 0.0) {
                int n2 = k;
                hK[n2] = hK[n2] - a;
                for (j = k + 1; j < n; ++j) {
                    double[] hI;
                    int i;
                    double alpha = 0.0;
                    for (i = k; i < m; ++i) {
                        hI = this.householderVectors[i];
                        alpha -= hI[j] * hI[k];
                    }
                    alpha /= a * this.householderVectors[k][k];
                    for (i = k; i < m; ++i) {
                        hI = this.householderVectors[i];
                        int n3 = j;
                        hI[n3] = hI[n3] - alpha * hI[k];
                    }
                }
            }
            if (k >= n - 1) continue;
            xNormSqr = 0.0;
            for (j = k + 1; j < n; ++j) {
                double c = hK[j];
                xNormSqr += c * c;
            }
            this.secondary[k] = b = hK[k + 1] > 0.0 ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            if (b == 0.0) continue;
            int n4 = k + 1;
            hK[n4] = hK[n4] - b;
            for (int i = k + 1; i < m; ++i) {
                int j2;
                double[] hI = this.householderVectors[i];
                double beta = 0.0;
                for (j2 = k + 1; j2 < n; ++j2) {
                    beta -= hI[j2] * hK[j2];
                }
                beta /= b * hK[k + 1];
                for (j2 = k + 1; j2 < n; ++j2) {
                    int n5 = j2;
                    hI[n5] = hI[n5] - beta * hK[j2];
                }
            }
        }
    }

    private void transformToLowerBiDiagonal() {
        int m = this.householderVectors.length;
        int n = this.householderVectors[0].length;
        for (int k = 0; k < m; ++k) {
            double b;
            double a;
            double[] hK = this.householderVectors[k];
            double xNormSqr = 0.0;
            for (int j = k; j < n; ++j) {
                double c = hK[j];
                xNormSqr += c * c;
            }
            this.main[k] = a = hK[k] > 0.0 ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            if (a != 0.0) {
                int n2 = k;
                hK[n2] = hK[n2] - a;
                for (int i = k + 1; i < m; ++i) {
                    int j;
                    double[] hI = this.householderVectors[i];
                    double alpha = 0.0;
                    for (j = k; j < n; ++j) {
                        alpha -= hI[j] * hK[j];
                    }
                    alpha /= a * this.householderVectors[k][k];
                    for (j = k; j < n; ++j) {
                        int n3 = j;
                        hI[n3] = hI[n3] - alpha * hK[j];
                    }
                }
            }
            if (k >= m - 1) continue;
            double[] hKp1 = this.householderVectors[k + 1];
            xNormSqr = 0.0;
            for (int i = k + 1; i < m; ++i) {
                double c = this.householderVectors[i][k];
                xNormSqr += c * c;
            }
            this.secondary[k] = b = hKp1[k] > 0.0 ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            if (b == 0.0) continue;
            int n4 = k;
            hKp1[n4] = hKp1[n4] - b;
            for (int j = k + 1; j < n; ++j) {
                double[] hI;
                int i;
                double beta = 0.0;
                for (i = k + 1; i < m; ++i) {
                    hI = this.householderVectors[i];
                    beta -= hI[j] * hI[k];
                }
                beta /= b * hKp1[k];
                for (i = k + 1; i < m; ++i) {
                    hI = this.householderVectors[i];
                    int n5 = j;
                    hI[n5] = hI[n5] - beta * hI[k];
                }
            }
        }
    }
}

