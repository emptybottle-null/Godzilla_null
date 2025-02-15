/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.general;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class LevenbergMarquardtOptimizer
extends AbstractLeastSquaresOptimizer {
    private int solvedCols;
    private double[] diagR;
    private double[] jacNorm;
    private double[] beta;
    private int[] permutation;
    private int rank;
    private double lmPar;
    private double[] lmDir;
    private final double initialStepBoundFactor;
    private final double costRelativeTolerance;
    private final double parRelativeTolerance;
    private final double orthoTolerance;
    private final double qrRankingThreshold;
    private double[] weightedResidual;
    private double[][] weightedJacobian;

    public LevenbergMarquardtOptimizer() {
        this(100.0, 1.0E-10, 1.0E-10, 1.0E-10, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        this(100.0, checker, 1.0E-10, 1.0E-10, 1.0E-10, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double initialStepBoundFactor, ConvergenceChecker<PointVectorValuePair> checker, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double threshold) {
        super(checker);
        this.initialStepBoundFactor = initialStepBoundFactor;
        this.costRelativeTolerance = costRelativeTolerance;
        this.parRelativeTolerance = parRelativeTolerance;
        this.orthoTolerance = orthoTolerance;
        this.qrRankingThreshold = threshold;
    }

    public LevenbergMarquardtOptimizer(double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance) {
        this(100.0, costRelativeTolerance, parRelativeTolerance, orthoTolerance, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double initialStepBoundFactor, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double threshold) {
        super(null);
        this.initialStepBoundFactor = initialStepBoundFactor;
        this.costRelativeTolerance = costRelativeTolerance;
        this.parRelativeTolerance = parRelativeTolerance;
        this.orthoTolerance = orthoTolerance;
        this.qrRankingThreshold = threshold;
    }

    @Override
    protected PointVectorValuePair doOptimize() {
        int nR = this.getTarget().length;
        double[] currentPoint = this.getStartPoint();
        int nC = currentPoint.length;
        this.solvedCols = FastMath.min(nR, nC);
        this.diagR = new double[nC];
        this.jacNorm = new double[nC];
        this.beta = new double[nC];
        this.permutation = new int[nC];
        this.lmDir = new double[nC];
        double delta = 0.0;
        double xNorm = 0.0;
        double[] diag = new double[nC];
        double[] oldX = new double[nC];
        double[] oldRes = new double[nR];
        double[] oldObj = new double[nR];
        double[] qtf = new double[nR];
        double[] work1 = new double[nC];
        double[] work2 = new double[nC];
        double[] work3 = new double[nC];
        RealMatrix weightMatrixSqrt = this.getWeightSquareRoot();
        double[] currentObjective = this.computeObjectiveValue(currentPoint);
        double[] currentResiduals = this.computeResiduals(currentObjective);
        PointVectorValuePair current = new PointVectorValuePair(currentPoint, currentObjective);
        double currentCost = this.computeCost(currentResiduals);
        this.lmPar = 0.0;
        boolean firstIteration = true;
        int iter = 0;
        ConvergenceChecker<PointVectorValuePair> checker = this.getConvergenceChecker();
        block0: while (true) {
            int j;
            int k;
            ++iter;
            PointVectorValuePair previous = current;
            this.qrDecomposition(this.computeWeightedJacobian(currentPoint));
            this.weightedResidual = weightMatrixSqrt.operate(currentResiduals);
            for (int i = 0; i < nR; ++i) {
                qtf[i] = this.weightedResidual[i];
            }
            this.qTy(qtf);
            for (k = 0; k < this.solvedCols; ++k) {
                int pk = this.permutation[k];
                this.weightedJacobian[k][pk] = this.diagR[pk];
            }
            if (firstIteration) {
                xNorm = 0.0;
                for (k = 0; k < nC; ++k) {
                    double dk = this.jacNorm[k];
                    if (dk == 0.0) {
                        dk = 1.0;
                    }
                    double xk = dk * currentPoint[k];
                    xNorm += xk * xk;
                    diag[k] = dk;
                }
                delta = (xNorm = FastMath.sqrt(xNorm)) == 0.0 ? this.initialStepBoundFactor : this.initialStepBoundFactor * xNorm;
            }
            double maxCosine = 0.0;
            if (currentCost != 0.0) {
                for (j = 0; j < this.solvedCols; ++j) {
                    int pj = this.permutation[j];
                    double s = this.jacNorm[pj];
                    if (s == 0.0) continue;
                    double sum = 0.0;
                    for (int i = 0; i <= j; ++i) {
                        sum += this.weightedJacobian[i][pj] * qtf[i];
                    }
                    maxCosine = FastMath.max(maxCosine, FastMath.abs(sum) / (s * currentCost));
                }
            }
            if (maxCosine <= this.orthoTolerance) {
                this.setCost(currentCost);
                this.point = current.getPoint();
                return current;
            }
            for (j = 0; j < nC; ++j) {
                diag[j] = FastMath.max(diag[j], this.jacNorm[j]);
            }
            double ratio = 0.0;
            do {
                if (!(ratio < 1.0E-4)) continue block0;
                for (int j2 = 0; j2 < this.solvedCols; ++j2) {
                    int pj = this.permutation[j2];
                    oldX[pj] = currentPoint[pj];
                }
                double previousCost = currentCost;
                double[] tmpVec = this.weightedResidual;
                this.weightedResidual = oldRes;
                oldRes = tmpVec;
                tmpVec = currentObjective;
                currentObjective = oldObj;
                oldObj = tmpVec;
                this.determineLMParameter(qtf, delta, diag, work1, work2, work3);
                double lmNorm = 0.0;
                for (int j3 = 0; j3 < this.solvedCols; ++j3) {
                    int pj = this.permutation[j3];
                    this.lmDir[pj] = -this.lmDir[pj];
                    currentPoint[pj] = oldX[pj] + this.lmDir[pj];
                    double s = diag[pj] * this.lmDir[pj];
                    lmNorm += s * s;
                }
                lmNorm = FastMath.sqrt(lmNorm);
                if (firstIteration) {
                    delta = FastMath.min(delta, lmNorm);
                }
                currentObjective = this.computeObjectiveValue(currentPoint);
                currentResiduals = this.computeResiduals(currentObjective);
                current = new PointVectorValuePair(currentPoint, currentObjective);
                currentCost = this.computeCost(currentResiduals);
                double actRed = -1.0;
                if (0.1 * currentCost < previousCost) {
                    double r = currentCost / previousCost;
                    actRed = 1.0 - r * r;
                }
                for (int j4 = 0; j4 < this.solvedCols; ++j4) {
                    int pj = this.permutation[j4];
                    double dirJ = this.lmDir[pj];
                    work1[j4] = 0.0;
                    for (int i = 0; i <= j4; ++i) {
                        int n = i;
                        work1[n] = work1[n] + this.weightedJacobian[i][pj] * dirJ;
                    }
                }
                double coeff1 = 0.0;
                for (int j5 = 0; j5 < this.solvedCols; ++j5) {
                    coeff1 += work1[j5] * work1[j5];
                }
                double pc2 = previousCost * previousCost;
                double coeff2 = this.lmPar * lmNorm * lmNorm / pc2;
                double preRed = (coeff1 /= pc2) + 2.0 * coeff2;
                double dirDer = -(coeff1 + coeff2);
                double d = ratio = preRed == 0.0 ? 0.0 : actRed / preRed;
                if (ratio <= 0.25) {
                    double tmp;
                    double d2 = tmp = actRed < 0.0 ? 0.5 * dirDer / (dirDer + 0.5 * actRed) : 0.5;
                    if (0.1 * currentCost >= previousCost || tmp < 0.1) {
                        tmp = 0.1;
                    }
                    delta = tmp * FastMath.min(delta, 10.0 * lmNorm);
                    this.lmPar /= tmp;
                } else if (this.lmPar == 0.0 || ratio >= 0.75) {
                    delta = 2.0 * lmNorm;
                    this.lmPar *= 0.5;
                }
                if (ratio >= 1.0E-4) {
                    firstIteration = false;
                    xNorm = 0.0;
                    for (int k2 = 0; k2 < nC; ++k2) {
                        double xK = diag[k2] * currentPoint[k2];
                        xNorm += xK * xK;
                    }
                    xNorm = FastMath.sqrt(xNorm);
                    if (checker != null && checker.converged(iter, previous, current)) {
                        this.setCost(currentCost);
                        this.point = current.getPoint();
                        return current;
                    }
                } else {
                    currentCost = previousCost;
                    for (int j6 = 0; j6 < this.solvedCols; ++j6) {
                        int pj = this.permutation[j6];
                        currentPoint[pj] = oldX[pj];
                    }
                    tmpVec = this.weightedResidual;
                    this.weightedResidual = oldRes;
                    oldRes = tmpVec;
                    tmpVec = currentObjective;
                    currentObjective = oldObj;
                    oldObj = tmpVec;
                    current = new PointVectorValuePair(currentPoint, currentObjective);
                }
                if (FastMath.abs(actRed) <= this.costRelativeTolerance && preRed <= this.costRelativeTolerance && ratio <= 2.0 || delta <= this.parRelativeTolerance * xNorm) {
                    this.setCost(currentCost);
                    this.point = current.getPoint();
                    return current;
                }
                if (FastMath.abs(actRed) <= 2.2204E-16 && preRed <= 2.2204E-16 && ratio <= 2.0) {
                    throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, this.costRelativeTolerance);
                }
                if (!(delta <= 2.2204E-16 * xNorm)) continue;
                throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, this.parRelativeTolerance);
            } while (!(maxCosine <= 2.2204E-16));
            break;
        }
        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE, this.orthoTolerance);
    }

    private void determineLMParameter(double[] qy, double delta, double[] diag, double[] work1, double[] work2, double[] work3) {
        double sum;
        int pj;
        int j;
        int j2;
        int nC = this.weightedJacobian[0].length;
        for (j2 = 0; j2 < this.rank; ++j2) {
            this.lmDir[this.permutation[j2]] = qy[j2];
        }
        for (j2 = this.rank; j2 < nC; ++j2) {
            this.lmDir[this.permutation[j2]] = 0.0;
        }
        for (int k = this.rank - 1; k >= 0; --k) {
            int pk = this.permutation[k];
            double ypk = this.lmDir[pk] / this.diagR[pk];
            for (int i = 0; i < k; ++i) {
                int n = this.permutation[i];
                this.lmDir[n] = this.lmDir[n] - ypk * this.weightedJacobian[i][pk];
            }
            this.lmDir[pk] = ypk;
        }
        double dxNorm = 0.0;
        for (int j3 = 0; j3 < this.solvedCols; ++j3) {
            double s;
            int pj2 = this.permutation[j3];
            work1[pj2] = s = diag[pj2] * this.lmDir[pj2];
            dxNorm += s * s;
        }
        double fp = (dxNorm = FastMath.sqrt(dxNorm)) - delta;
        if (fp <= 0.1 * delta) {
            this.lmPar = 0.0;
            return;
        }
        double parl = 0.0;
        if (this.rank == this.solvedCols) {
            for (j = 0; j < this.solvedCols; ++j) {
                int n = pj = this.permutation[j];
                work1[n] = work1[n] * (diag[pj] / dxNorm);
            }
            double sum2 = 0.0;
            for (j = 0; j < this.solvedCols; ++j) {
                double s;
                pj = this.permutation[j];
                sum = 0.0;
                for (int i = 0; i < j; ++i) {
                    sum += this.weightedJacobian[i][pj] * work1[this.permutation[i]];
                }
                work1[pj] = s = (work1[pj] - sum) / this.diagR[pj];
                sum2 += s * s;
            }
            parl = fp / (delta * sum2);
        }
        double sum2 = 0.0;
        for (j = 0; j < this.solvedCols; ++j) {
            pj = this.permutation[j];
            sum = 0.0;
            for (int i = 0; i <= j; ++i) {
                sum += this.weightedJacobian[i][pj] * qy[i];
            }
            sum2 += (sum /= diag[pj]) * sum;
        }
        double gNorm = FastMath.sqrt(sum2);
        double paru = gNorm / delta;
        if (paru == 0.0) {
            paru = 2.2251E-308 / FastMath.min(delta, 0.1);
        }
        this.lmPar = FastMath.min(paru, FastMath.max(this.lmPar, parl));
        if (this.lmPar == 0.0) {
            this.lmPar = gNorm / dxNorm;
        }
        for (int countdown = 10; countdown >= 0; --countdown) {
            int pj3;
            int j4;
            int pj4;
            int j5;
            if (this.lmPar == 0.0) {
                this.lmPar = FastMath.max(2.2251E-308, 0.001 * paru);
            }
            double sPar = FastMath.sqrt(this.lmPar);
            for (j5 = 0; j5 < this.solvedCols; ++j5) {
                pj4 = this.permutation[j5];
                work1[pj4] = sPar * diag[pj4];
            }
            this.determineLMDirection(qy, work1, work2, work3);
            dxNorm = 0.0;
            for (j5 = 0; j5 < this.solvedCols; ++j5) {
                double s;
                pj4 = this.permutation[j5];
                work3[pj4] = s = diag[pj4] * this.lmDir[pj4];
                dxNorm += s * s;
            }
            dxNorm = FastMath.sqrt(dxNorm);
            double previousFP = fp;
            fp = dxNorm - delta;
            if (FastMath.abs(fp) <= 0.1 * delta || parl == 0.0 && fp <= previousFP && previousFP < 0.0) {
                return;
            }
            for (j4 = 0; j4 < this.solvedCols; ++j4) {
                pj3 = this.permutation[j4];
                work1[pj3] = work3[pj3] * diag[pj3] / dxNorm;
            }
            for (j4 = 0; j4 < this.solvedCols; ++j4) {
                int n = pj3 = this.permutation[j4];
                work1[n] = work1[n] / work2[j4];
                double tmp = work1[pj3];
                for (int i = j4 + 1; i < this.solvedCols; ++i) {
                    int n2 = this.permutation[i];
                    work1[n2] = work1[n2] - this.weightedJacobian[i][pj3] * tmp;
                }
            }
            sum2 = 0.0;
            for (j4 = 0; j4 < this.solvedCols; ++j4) {
                double s = work1[this.permutation[j4]];
                sum2 += s * s;
            }
            double correction = fp / (delta * sum2);
            if (fp > 0.0) {
                parl = FastMath.max(parl, this.lmPar);
            } else if (fp < 0.0) {
                paru = FastMath.min(paru, this.lmPar);
            }
            this.lmPar = FastMath.max(parl, this.lmPar + correction);
        }
    }

    private void determineLMDirection(double[] qy, double[] diag, double[] lmDiag, double[] work) {
        int j;
        int pj;
        int j2;
        for (j2 = 0; j2 < this.solvedCols; ++j2) {
            pj = this.permutation[j2];
            for (int i = j2 + 1; i < this.solvedCols; ++i) {
                this.weightedJacobian[i][pj] = this.weightedJacobian[j2][this.permutation[i]];
            }
            this.lmDir[j2] = this.diagR[pj];
            work[j2] = qy[j2];
        }
        for (j2 = 0; j2 < this.solvedCols; ++j2) {
            pj = this.permutation[j2];
            double dpj = diag[pj];
            if (dpj != 0.0) {
                Arrays.fill(lmDiag, j2 + 1, lmDiag.length, 0.0);
            }
            lmDiag[j2] = dpj;
            double qtbpj = 0.0;
            for (int k = j2; k < this.solvedCols; ++k) {
                double cos;
                double sin;
                int pk = this.permutation[k];
                if (lmDiag[k] == 0.0) continue;
                double rkk = this.weightedJacobian[k][pk];
                if (FastMath.abs(rkk) < FastMath.abs(lmDiag[k])) {
                    double cotan = rkk / lmDiag[k];
                    sin = 1.0 / FastMath.sqrt(1.0 + cotan * cotan);
                    cos = sin * cotan;
                } else {
                    double tan = lmDiag[k] / rkk;
                    cos = 1.0 / FastMath.sqrt(1.0 + tan * tan);
                    sin = cos * tan;
                }
                this.weightedJacobian[k][pk] = cos * rkk + sin * lmDiag[k];
                double temp = cos * work[k] + sin * qtbpj;
                qtbpj = -sin * work[k] + cos * qtbpj;
                work[k] = temp;
                for (int i = k + 1; i < this.solvedCols; ++i) {
                    double rik = this.weightedJacobian[i][pk];
                    double temp2 = cos * rik + sin * lmDiag[i];
                    lmDiag[i] = -sin * rik + cos * lmDiag[i];
                    this.weightedJacobian[i][pk] = temp2;
                }
            }
            lmDiag[j2] = this.weightedJacobian[j2][this.permutation[j2]];
            this.weightedJacobian[j2][this.permutation[j2]] = this.lmDir[j2];
        }
        int nSing = this.solvedCols;
        for (j = 0; j < this.solvedCols; ++j) {
            if (lmDiag[j] == 0.0 && nSing == this.solvedCols) {
                nSing = j;
            }
            if (nSing >= this.solvedCols) continue;
            work[j] = 0.0;
        }
        if (nSing > 0) {
            for (j = nSing - 1; j >= 0; --j) {
                int pj2 = this.permutation[j];
                double sum = 0.0;
                for (int i = j + 1; i < nSing; ++i) {
                    sum += this.weightedJacobian[i][pj2] * work[i];
                }
                work[j] = (work[j] - sum) / lmDiag[j];
            }
        }
        for (j = 0; j < this.lmDir.length; ++j) {
            this.lmDir[this.permutation[j]] = work[j];
        }
    }

    private void qrDecomposition(RealMatrix jacobian) throws ConvergenceException {
        int k;
        this.weightedJacobian = jacobian.scalarMultiply(-1.0).getData();
        int nR = this.weightedJacobian.length;
        int nC = this.weightedJacobian[0].length;
        for (k = 0; k < nC; ++k) {
            this.permutation[k] = k;
            double norm2 = 0.0;
            for (int i = 0; i < nR; ++i) {
                double akk = this.weightedJacobian[i][k];
                norm2 += akk * akk;
            }
            this.jacNorm[k] = FastMath.sqrt(norm2);
        }
        for (k = 0; k < nC; ++k) {
            double betak;
            int nextColumn = -1;
            double ak2 = Double.NEGATIVE_INFINITY;
            for (int i = k; i < nC; ++i) {
                double norm2 = 0.0;
                for (int j = k; j < nR; ++j) {
                    double aki = this.weightedJacobian[j][this.permutation[i]];
                    norm2 += aki * aki;
                }
                if (Double.isInfinite(norm2) || Double.isNaN(norm2)) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN, nR, nC);
                }
                if (!(norm2 > ak2)) continue;
                nextColumn = i;
                ak2 = norm2;
            }
            if (ak2 <= this.qrRankingThreshold) {
                this.rank = k;
                return;
            }
            int pk = this.permutation[nextColumn];
            this.permutation[nextColumn] = this.permutation[k];
            this.permutation[k] = pk;
            double akk = this.weightedJacobian[k][pk];
            double alpha = akk > 0.0 ? -FastMath.sqrt(ak2) : FastMath.sqrt(ak2);
            this.beta[pk] = betak = 1.0 / (ak2 - akk * alpha);
            this.diagR[pk] = alpha;
            double[] dArray = this.weightedJacobian[k];
            int n = pk;
            dArray[n] = dArray[n] - alpha;
            for (int dk = nC - 1 - k; dk > 0; --dk) {
                int j;
                double gamma = 0.0;
                for (j = k; j < nR; ++j) {
                    gamma += this.weightedJacobian[j][pk] * this.weightedJacobian[j][this.permutation[k + dk]];
                }
                gamma *= betak;
                for (j = k; j < nR; ++j) {
                    double[] dArray2 = this.weightedJacobian[j];
                    int n2 = this.permutation[k + dk];
                    dArray2[n2] = dArray2[n2] - gamma * this.weightedJacobian[j][pk];
                }
            }
        }
        this.rank = this.solvedCols;
    }

    private void qTy(double[] y) {
        int nR = this.weightedJacobian.length;
        int nC = this.weightedJacobian[0].length;
        for (int k = 0; k < nC; ++k) {
            int i;
            int pk = this.permutation[k];
            double gamma = 0.0;
            for (i = k; i < nR; ++i) {
                gamma += this.weightedJacobian[i][pk] * y[i];
            }
            gamma *= this.beta[pk];
            for (i = k; i < nR; ++i) {
                int n = i;
                y[n] = y[n] - gamma * this.weightedJacobian[i][pk];
            }
        }
    }
}

