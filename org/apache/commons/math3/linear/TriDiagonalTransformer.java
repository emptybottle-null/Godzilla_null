/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import java.util.Arrays;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;

class TriDiagonalTransformer {
    private final double[][] householderVectors;
    private final double[] main;
    private final double[] secondary;
    private RealMatrix cachedQ;
    private RealMatrix cachedQt;
    private RealMatrix cachedT;

    TriDiagonalTransformer(RealMatrix matrix) {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        int m = matrix.getRowDimension();
        this.householderVectors = matrix.getData();
        this.main = new double[m];
        this.secondary = new double[m - 1];
        this.cachedQ = null;
        this.cachedQt = null;
        this.cachedT = null;
        this.transform();
    }

    public RealMatrix getQ() {
        if (this.cachedQ == null) {
            this.cachedQ = this.getQT().transpose();
        }
        return this.cachedQ;
    }

    public RealMatrix getQT() {
        if (this.cachedQt == null) {
            int m = this.householderVectors.length;
            double[][] qta = new double[m][m];
            for (int k = m - 1; k >= 1; --k) {
                double[] hK = this.householderVectors[k - 1];
                qta[k][k] = 1.0;
                if (hK[k] == 0.0) continue;
                double inv = 1.0 / (this.secondary[k - 1] * hK[k]);
                double beta = 1.0 / this.secondary[k - 1];
                qta[k][k] = 1.0 + beta * hK[k];
                for (int i = k + 1; i < m; ++i) {
                    qta[k][i] = beta * hK[i];
                }
                for (int j = k + 1; j < m; ++j) {
                    int i;
                    beta = 0.0;
                    for (i = k + 1; i < m; ++i) {
                        beta += qta[j][i] * hK[i];
                    }
                    qta[j][k] = (beta *= inv) * hK[k];
                    for (i = k + 1; i < m; ++i) {
                        double[] dArray = qta[j];
                        int n = i;
                        dArray[n] = dArray[n] + beta * hK[i];
                    }
                }
            }
            qta[0][0] = 1.0;
            this.cachedQt = MatrixUtils.createRealMatrix(qta);
        }
        return this.cachedQt;
    }

    public RealMatrix getT() {
        if (this.cachedT == null) {
            int m = this.main.length;
            double[][] ta = new double[m][m];
            for (int i = 0; i < m; ++i) {
                ta[i][i] = this.main[i];
                if (i > 0) {
                    ta[i][i - 1] = this.secondary[i - 1];
                }
                if (i >= this.main.length - 1) continue;
                ta[i][i + 1] = this.secondary[i];
            }
            this.cachedT = MatrixUtils.createRealMatrix(ta);
        }
        return this.cachedT;
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

    private void transform() {
        int m = this.householderVectors.length;
        double[] z = new double[m];
        for (int k = 0; k < m - 1; ++k) {
            int i;
            double a;
            double[] hK = this.householderVectors[k];
            this.main[k] = hK[k];
            double xNormSqr = 0.0;
            for (int j = k + 1; j < m; ++j) {
                double c = hK[j];
                xNormSqr += c * c;
            }
            this.secondary[k] = a = hK[k + 1] > 0.0 ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            if (a == 0.0) continue;
            int n = k + 1;
            hK[n] = hK[n] - a;
            double beta = -1.0 / (a * hK[k + 1]);
            Arrays.fill(z, k + 1, m, 0.0);
            for (int i2 = k + 1; i2 < m; ++i2) {
                double[] hI = this.householderVectors[i2];
                double hKI = hK[i2];
                double zI = hI[i2] * hKI;
                int j = i2 + 1;
                while (j < m) {
                    double hIJ = hI[j];
                    zI += hIJ * hK[j];
                    int n2 = j++;
                    z[n2] = z[n2] + hIJ * hKI;
                }
                z[i2] = beta * (z[i2] + zI);
            }
            double gamma = 0.0;
            for (i = k + 1; i < m; ++i) {
                gamma += z[i] * hK[i];
            }
            gamma *= beta / 2.0;
            for (i = k + 1; i < m; ++i) {
                int n3 = i;
                z[n3] = z[n3] - gamma * hK[i];
            }
            for (i = k + 1; i < m; ++i) {
                double[] hI = this.householderVectors[i];
                for (int j = i; j < m; ++j) {
                    int n4 = j;
                    hI[n4] = hI[n4] - (hK[i] * z[j] + z[i] * hK[j]);
                }
            }
        }
        this.main[m - 1] = this.householderVectors[m - 1][m - 1];
    }
}

