/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.HessenbergTransformer;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

class SchurTransformer {
    private static final int MAX_ITERATIONS = 100;
    private final double[][] matrixP;
    private final double[][] matrixT;
    private RealMatrix cachedP;
    private RealMatrix cachedT;
    private RealMatrix cachedPt;
    private final double epsilon = Precision.EPSILON;

    SchurTransformer(RealMatrix matrix) {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        HessenbergTransformer transformer = new HessenbergTransformer(matrix);
        this.matrixT = transformer.getH().getData();
        this.matrixP = transformer.getP().getData();
        this.cachedT = null;
        this.cachedP = null;
        this.cachedPt = null;
        this.transform();
    }

    public RealMatrix getP() {
        if (this.cachedP == null) {
            this.cachedP = MatrixUtils.createRealMatrix(this.matrixP);
        }
        return this.cachedP;
    }

    public RealMatrix getPT() {
        if (this.cachedPt == null) {
            this.cachedPt = this.getP().transpose();
        }
        return this.cachedPt;
    }

    public RealMatrix getT() {
        if (this.cachedT == null) {
            this.cachedT = MatrixUtils.createRealMatrix(this.matrixT);
        }
        return this.cachedT;
    }

    private void transform() {
        int n = this.matrixT.length;
        double norm = this.getNorm();
        ShiftInfo shift = new ShiftInfo();
        int iteration = 0;
        int iu = n - 1;
        while (iu >= 0) {
            int il = this.findSmallSubDiagonalElement(iu, norm);
            if (il == iu) {
                double[] dArray = this.matrixT[iu];
                int n2 = iu--;
                dArray[n2] = dArray[n2] + shift.exShift;
                iteration = 0;
                continue;
            }
            if (il == iu - 1) {
                double p = (this.matrixT[iu - 1][iu - 1] - this.matrixT[iu][iu]) / 2.0;
                double q = p * p + this.matrixT[iu][iu - 1] * this.matrixT[iu - 1][iu];
                double[] dArray = this.matrixT[iu];
                int n3 = iu;
                dArray[n3] = dArray[n3] + shift.exShift;
                double[] dArray2 = this.matrixT[iu - 1];
                int n4 = iu - 1;
                dArray2[n4] = dArray2[n4] + shift.exShift;
                if (q >= 0.0) {
                    int i;
                    double z = FastMath.sqrt(FastMath.abs(q));
                    z = p >= 0.0 ? p + z : p - z;
                    double x = this.matrixT[iu][iu - 1];
                    double s = FastMath.abs(x) + FastMath.abs(z);
                    p = x / s;
                    q = z / s;
                    double r = FastMath.sqrt(p * p + q * q);
                    p /= r;
                    q /= r;
                    for (int j = iu - 1; j < n; ++j) {
                        z = this.matrixT[iu - 1][j];
                        this.matrixT[iu - 1][j] = q * z + p * this.matrixT[iu][j];
                        this.matrixT[iu][j] = q * this.matrixT[iu][j] - p * z;
                    }
                    for (i = 0; i <= iu; ++i) {
                        z = this.matrixT[i][iu - 1];
                        this.matrixT[i][iu - 1] = q * z + p * this.matrixT[i][iu];
                        this.matrixT[i][iu] = q * this.matrixT[i][iu] - p * z;
                    }
                    for (i = 0; i <= n - 1; ++i) {
                        z = this.matrixP[i][iu - 1];
                        this.matrixP[i][iu - 1] = q * z + p * this.matrixP[i][iu];
                        this.matrixP[i][iu] = q * this.matrixP[i][iu] - p * z;
                    }
                }
                iu -= 2;
                iteration = 0;
                continue;
            }
            this.computeShift(il, iu, iteration, shift);
            if (++iteration > 100) {
                throw new MaxCountExceededException((Localizable)LocalizedFormats.CONVERGENCE_FAILED, 100, new Object[0]);
            }
            double[] hVec = new double[3];
            int im = this.initQRStep(il, iu, shift, hVec);
            this.performDoubleQRStep(il, im, iu, shift, hVec);
        }
    }

    private double getNorm() {
        double norm = 0.0;
        for (int i = 0; i < this.matrixT.length; ++i) {
            for (int j = FastMath.max(i - 1, 0); j < this.matrixT.length; ++j) {
                norm += FastMath.abs(this.matrixT[i][j]);
            }
        }
        return norm;
    }

    private int findSmallSubDiagonalElement(int startIdx, double norm) {
        int l;
        for (l = startIdx; l > 0; --l) {
            double s = FastMath.abs(this.matrixT[l - 1][l - 1]) + FastMath.abs(this.matrixT[l][l]);
            if (s == 0.0) {
                s = norm;
            }
            if (FastMath.abs(this.matrixT[l][l - 1]) < this.epsilon * s) break;
        }
        return l;
    }

    private void computeShift(int l, int idx, int iteration, ShiftInfo shift) {
        double s;
        shift.x = this.matrixT[idx][idx];
        shift.w = 0.0;
        shift.y = 0.0;
        if (l < idx) {
            shift.y = this.matrixT[idx - 1][idx - 1];
            shift.w = this.matrixT[idx][idx - 1] * this.matrixT[idx - 1][idx];
        }
        if (iteration == 10) {
            shift.exShift += shift.x;
            int i = 0;
            while (i <= idx) {
                double[] dArray = this.matrixT[i];
                int n = i++;
                dArray[n] = dArray[n] - shift.x;
            }
            s = FastMath.abs(this.matrixT[idx][idx - 1]) + FastMath.abs(this.matrixT[idx - 1][idx - 2]);
            shift.x = 0.75 * s;
            shift.y = 0.75 * s;
            shift.w = -0.4375 * s * s;
        }
        if (iteration == 30) {
            s = (shift.y - shift.x) / 2.0;
            if ((s = s * s + shift.w) > 0.0) {
                s = FastMath.sqrt(s);
                if (shift.y < shift.x) {
                    s = -s;
                }
                s = shift.x - shift.w / ((shift.y - shift.x) / 2.0 + s);
                int i = 0;
                while (i <= idx) {
                    double[] dArray = this.matrixT[i];
                    int n = i++;
                    dArray[n] = dArray[n] - s;
                }
                shift.exShift += s;
                shift.w = 0.964;
                shift.y = 0.964;
                shift.x = 0.964;
            }
        }
    }

    private int initQRStep(int il, int iu, ShiftInfo shift, double[] hVec) {
        int im;
        for (im = iu - 2; im >= il; --im) {
            double rhs;
            double lhs;
            double z = this.matrixT[im][im];
            double r = shift.x - z;
            double s = shift.y - z;
            hVec[0] = (r * s - shift.w) / this.matrixT[im + 1][im] + this.matrixT[im][im + 1];
            hVec[1] = this.matrixT[im + 1][im + 1] - z - r - s;
            hVec[2] = this.matrixT[im + 2][im + 1];
            if (im == il || (lhs = FastMath.abs(this.matrixT[im][im - 1]) * (FastMath.abs(hVec[1]) + FastMath.abs(hVec[2]))) < this.epsilon * (rhs = FastMath.abs(hVec[0]) * (FastMath.abs(this.matrixT[im - 1][im - 1]) + FastMath.abs(z) + FastMath.abs(this.matrixT[im + 1][im + 1])))) break;
        }
        return im;
    }

    private void performDoubleQRStep(int il, int im, int iu, ShiftInfo shift, double[] hVec) {
        int n = this.matrixT.length;
        double p = hVec[0];
        double q = hVec[1];
        double r = hVec[2];
        for (int k = im; k <= iu - 1; ++k) {
            boolean notlast;
            boolean bl = notlast = k != iu - 1;
            if (k != im) {
                p = this.matrixT[k][k - 1];
                q = this.matrixT[k + 1][k - 1];
                r = notlast ? this.matrixT[k + 2][k - 1] : 0.0;
                shift.x = FastMath.abs(p) + FastMath.abs(q) + FastMath.abs(r);
                if (Precision.equals(shift.x, 0.0, this.epsilon)) continue;
                p /= shift.x;
                q /= shift.x;
                r /= shift.x;
            }
            double s = FastMath.sqrt(p * p + q * q + r * r);
            if (p < 0.0) {
                s = -s;
            }
            if (s == 0.0) continue;
            if (k != im) {
                this.matrixT[k][k - 1] = -s * shift.x;
            } else if (il != im) {
                this.matrixT[k][k - 1] = -this.matrixT[k][k - 1];
            }
            shift.x = (p += s) / s;
            shift.y = q / s;
            double z = r / s;
            q /= p;
            r /= p;
            int j = k;
            while (j < n) {
                p = this.matrixT[k][j] + q * this.matrixT[k + 1][j];
                if (notlast) {
                    double[] dArray = this.matrixT[k + 2];
                    int n2 = j;
                    dArray[n2] = dArray[n2] - (p += r * this.matrixT[k + 2][j]) * z;
                }
                double[] dArray = this.matrixT[k];
                int n3 = j;
                dArray[n3] = dArray[n3] - p * shift.x;
                double[] dArray2 = this.matrixT[k + 1];
                int n4 = j++;
                dArray2[n4] = dArray2[n4] - p * shift.y;
            }
            for (int i = 0; i <= FastMath.min(iu, k + 3); ++i) {
                p = shift.x * this.matrixT[i][k] + shift.y * this.matrixT[i][k + 1];
                if (notlast) {
                    double[] dArray = this.matrixT[i];
                    int n5 = k + 2;
                    dArray[n5] = dArray[n5] - (p += z * this.matrixT[i][k + 2]) * r;
                }
                double[] dArray = this.matrixT[i];
                int n6 = k;
                dArray[n6] = dArray[n6] - p;
                double[] dArray3 = this.matrixT[i];
                int n7 = k + 1;
                dArray3[n7] = dArray3[n7] - p * q;
            }
            int high = this.matrixT.length - 1;
            for (int i = 0; i <= high; ++i) {
                p = shift.x * this.matrixP[i][k] + shift.y * this.matrixP[i][k + 1];
                if (notlast) {
                    double[] dArray = this.matrixP[i];
                    int n8 = k + 2;
                    dArray[n8] = dArray[n8] - (p += z * this.matrixP[i][k + 2]) * r;
                }
                double[] dArray = this.matrixP[i];
                int n9 = k;
                dArray[n9] = dArray[n9] - p;
                double[] dArray4 = this.matrixP[i];
                int n10 = k + 1;
                dArray4[n10] = dArray4[n10] - p * q;
            }
        }
        for (int i = im + 2; i <= iu; ++i) {
            this.matrixT[i][i - 2] = 0.0;
            if (i <= im + 2) continue;
            this.matrixT[i][i - 3] = 0.0;
        }
    }

    private static class ShiftInfo {
        double x;
        double y;
        double w;
        double exShift;

        private ShiftInfo() {
        }
    }
}

