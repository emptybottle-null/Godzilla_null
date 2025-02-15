/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting.leastsquares;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.fitting.leastsquares.OptimumImpl;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.Precision;

public class LevenbergMarquardtOptimizer
implements LeastSquaresOptimizer {
    private static final double TWO_EPS = 2.0 * Precision.EPSILON;
    private final double initialStepBoundFactor;
    private final double costRelativeTolerance;
    private final double parRelativeTolerance;
    private final double orthoTolerance;
    private final double qrRankingThreshold;

    public LevenbergMarquardtOptimizer() {
        this(100.0, 1.0E-10, 1.0E-10, 1.0E-10, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double initialStepBoundFactor, double costRelativeTolerance, double parRelativeTolerance, double orthoTolerance, double qrRankingThreshold) {
        this.initialStepBoundFactor = initialStepBoundFactor;
        this.costRelativeTolerance = costRelativeTolerance;
        this.parRelativeTolerance = parRelativeTolerance;
        this.orthoTolerance = orthoTolerance;
        this.qrRankingThreshold = qrRankingThreshold;
    }

    public LevenbergMarquardtOptimizer withInitialStepBoundFactor(double newInitialStepBoundFactor) {
        return new LevenbergMarquardtOptimizer(newInitialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withCostRelativeTolerance(double newCostRelativeTolerance) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, newCostRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withParameterRelativeTolerance(double newParRelativeTolerance) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, newParRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withOrthoTolerance(double newOrthoTolerance) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, newOrthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withRankingThreshold(double newQRRankingThreshold) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, newQRRankingThreshold);
    }

    public double getInitialStepBoundFactor() {
        return this.initialStepBoundFactor;
    }

    public double getCostRelativeTolerance() {
        return this.costRelativeTolerance;
    }

    public double getParameterRelativeTolerance() {
        return this.parRelativeTolerance;
    }

    public double getOrthoTolerance() {
        return this.orthoTolerance;
    }

    public double getRankingThreshold() {
        return this.qrRankingThreshold;
    }

    public LeastSquaresOptimizer.Optimum optimize(LeastSquaresProblem problem) {
        int nR = problem.getObservationSize();
        int nC = problem.getParameterSize();
        Incrementor iterationCounter = problem.getIterationCounter();
        Incrementor evaluationCounter = problem.getEvaluationCounter();
        ConvergenceChecker<LeastSquaresProblem.Evaluation> checker = problem.getConvergenceChecker();
        int solvedCols = FastMath.min(nR, nC);
        double[] lmDir = new double[nC];
        double lmPar = 0.0;
        double delta = 0.0;
        double xNorm = 0.0;
        double[] diag = new double[nC];
        double[] oldX = new double[nC];
        double[] oldRes = new double[nR];
        double[] qtf = new double[nR];
        double[] work1 = new double[nC];
        double[] work2 = new double[nC];
        double[] work3 = new double[nC];
        evaluationCounter.incrementCount();
        LeastSquaresProblem.Evaluation current = problem.evaluate(problem.getStart());
        double[] currentResiduals = current.getResiduals().toArray();
        double currentCost = current.getCost();
        double[] currentPoint = current.getPoint().toArray();
        boolean firstIteration = true;
        block0: while (true) {
            int j;
            int k;
            iterationCounter.incrementCount();
            LeastSquaresProblem.Evaluation previous = current;
            InternalData internalData = this.qrDecomposition(current.getJacobian(), solvedCols);
            double[][] weightedJacobian = internalData.weightedJacobian;
            int[] permutation = internalData.permutation;
            double[] diagR = internalData.diagR;
            double[] jacNorm = internalData.jacNorm;
            double[] weightedResidual = currentResiduals;
            for (int i = 0; i < nR; ++i) {
                qtf[i] = weightedResidual[i];
            }
            this.qTy(qtf, internalData);
            for (k = 0; k < solvedCols; ++k) {
                int pk = permutation[k];
                weightedJacobian[k][pk] = diagR[pk];
            }
            if (firstIteration) {
                xNorm = 0.0;
                for (k = 0; k < nC; ++k) {
                    double dk = jacNorm[k];
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
                for (j = 0; j < solvedCols; ++j) {
                    int pj = permutation[j];
                    double s = jacNorm[pj];
                    if (s == 0.0) continue;
                    double sum = 0.0;
                    for (int i = 0; i <= j; ++i) {
                        sum += weightedJacobian[i][pj] * qtf[i];
                    }
                    maxCosine = FastMath.max(maxCosine, FastMath.abs(sum) / (s * currentCost));
                }
            }
            if (maxCosine <= this.orthoTolerance) {
                return new OptimumImpl(current, evaluationCounter.getCount(), iterationCounter.getCount());
            }
            for (j = 0; j < nC; ++j) {
                diag[j] = FastMath.max(diag[j], jacNorm[j]);
            }
            double ratio = 0.0;
            do {
                if (!(ratio < 1.0E-4)) continue block0;
                for (int j2 = 0; j2 < solvedCols; ++j2) {
                    int pj = permutation[j2];
                    oldX[pj] = currentPoint[pj];
                }
                double previousCost = currentCost;
                double[] tmpVec = weightedResidual;
                weightedResidual = oldRes;
                oldRes = tmpVec;
                lmPar = this.determineLMParameter(qtf, delta, diag, internalData, solvedCols, work1, work2, work3, lmDir, lmPar);
                double lmNorm = 0.0;
                for (int j3 = 0; j3 < solvedCols; ++j3) {
                    int pj = permutation[j3];
                    lmDir[pj] = -lmDir[pj];
                    currentPoint[pj] = oldX[pj] + lmDir[pj];
                    double s = diag[pj] * lmDir[pj];
                    lmNorm += s * s;
                }
                lmNorm = FastMath.sqrt(lmNorm);
                if (firstIteration) {
                    delta = FastMath.min(delta, lmNorm);
                }
                evaluationCounter.incrementCount();
                current = problem.evaluate(new ArrayRealVector(currentPoint));
                currentResiduals = current.getResiduals().toArray();
                currentCost = current.getCost();
                currentPoint = current.getPoint().toArray();
                double actRed = -1.0;
                if (0.1 * currentCost < previousCost) {
                    double r = currentCost / previousCost;
                    actRed = 1.0 - r * r;
                }
                for (int j4 = 0; j4 < solvedCols; ++j4) {
                    int pj = permutation[j4];
                    double dirJ = lmDir[pj];
                    work1[j4] = 0.0;
                    for (int i = 0; i <= j4; ++i) {
                        int n = i;
                        work1[n] = work1[n] + weightedJacobian[i][pj] * dirJ;
                    }
                }
                double coeff1 = 0.0;
                for (int j5 = 0; j5 < solvedCols; ++j5) {
                    coeff1 += work1[j5] * work1[j5];
                }
                double pc2 = previousCost * previousCost;
                double coeff2 = lmPar * lmNorm * lmNorm / pc2;
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
                    lmPar /= tmp;
                } else if (lmPar == 0.0 || ratio >= 0.75) {
                    delta = 2.0 * lmNorm;
                    lmPar *= 0.5;
                }
                if (ratio >= 1.0E-4) {
                    firstIteration = false;
                    xNorm = 0.0;
                    for (int k2 = 0; k2 < nC; ++k2) {
                        double xK = diag[k2] * currentPoint[k2];
                        xNorm += xK * xK;
                    }
                    xNorm = FastMath.sqrt(xNorm);
                    if (checker != null && checker.converged(iterationCounter.getCount(), previous, current)) {
                        return new OptimumImpl(current, evaluationCounter.getCount(), iterationCounter.getCount());
                    }
                } else {
                    currentCost = previousCost;
                    for (int j6 = 0; j6 < solvedCols; ++j6) {
                        int pj = permutation[j6];
                        currentPoint[pj] = oldX[pj];
                    }
                    tmpVec = weightedResidual;
                    weightedResidual = oldRes;
                    oldRes = tmpVec;
                    current = previous;
                }
                if (FastMath.abs(actRed) <= this.costRelativeTolerance && preRed <= this.costRelativeTolerance && ratio <= 2.0 || delta <= this.parRelativeTolerance * xNorm) {
                    return new OptimumImpl(current, evaluationCounter.getCount(), iterationCounter.getCount());
                }
                if (FastMath.abs(actRed) <= TWO_EPS && preRed <= TWO_EPS && ratio <= 2.0) {
                    throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, this.costRelativeTolerance);
                }
                if (!(delta <= TWO_EPS * xNorm)) continue;
                throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, this.parRelativeTolerance);
            } while (!(maxCosine <= TWO_EPS));
            break;
        }
        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE, this.orthoTolerance);
    }

    private double determineLMParameter(double[] qy, double delta, double[] diag, InternalData internalData, int solvedCols, double[] work1, double[] work2, double[] work3, double[] lmDir, double lmPar) {
        double sum;
        int pj;
        int j;
        int j2;
        double[][] weightedJacobian = internalData.weightedJacobian;
        int[] permutation = internalData.permutation;
        int rank = internalData.rank;
        double[] diagR = internalData.diagR;
        int nC = weightedJacobian[0].length;
        for (j2 = 0; j2 < rank; ++j2) {
            lmDir[permutation[j2]] = qy[j2];
        }
        for (j2 = rank; j2 < nC; ++j2) {
            lmDir[permutation[j2]] = 0.0;
        }
        for (int k = rank - 1; k >= 0; --k) {
            int pk = permutation[k];
            double ypk = lmDir[pk] / diagR[pk];
            for (int i = 0; i < k; ++i) {
                int n = permutation[i];
                lmDir[n] = lmDir[n] - ypk * weightedJacobian[i][pk];
            }
            lmDir[pk] = ypk;
        }
        double dxNorm = 0.0;
        for (int j3 = 0; j3 < solvedCols; ++j3) {
            double s;
            int pj2 = permutation[j3];
            work1[pj2] = s = diag[pj2] * lmDir[pj2];
            dxNorm += s * s;
        }
        double fp = (dxNorm = FastMath.sqrt(dxNorm)) - delta;
        if (fp <= 0.1 * delta) {
            lmPar = 0.0;
            return lmPar;
        }
        double parl = 0.0;
        if (rank == solvedCols) {
            for (j = 0; j < solvedCols; ++j) {
                int n = pj = permutation[j];
                work1[n] = work1[n] * (diag[pj] / dxNorm);
            }
            double sum2 = 0.0;
            for (j = 0; j < solvedCols; ++j) {
                double s;
                pj = permutation[j];
                sum = 0.0;
                for (int i = 0; i < j; ++i) {
                    sum += weightedJacobian[i][pj] * work1[permutation[i]];
                }
                work1[pj] = s = (work1[pj] - sum) / diagR[pj];
                sum2 += s * s;
            }
            parl = fp / (delta * sum2);
        }
        double sum2 = 0.0;
        for (j = 0; j < solvedCols; ++j) {
            pj = permutation[j];
            sum = 0.0;
            for (int i = 0; i <= j; ++i) {
                sum += weightedJacobian[i][pj] * qy[i];
            }
            sum2 += (sum /= diag[pj]) * sum;
        }
        double gNorm = FastMath.sqrt(sum2);
        double paru = gNorm / delta;
        if (paru == 0.0) {
            paru = Precision.SAFE_MIN / FastMath.min(delta, 0.1);
        }
        if ((lmPar = FastMath.min(paru, FastMath.max(lmPar, parl))) == 0.0) {
            lmPar = gNorm / dxNorm;
        }
        for (int countdown = 10; countdown >= 0; --countdown) {
            int pj3;
            int j4;
            int pj4;
            int j5;
            if (lmPar == 0.0) {
                lmPar = FastMath.max(Precision.SAFE_MIN, 0.001 * paru);
            }
            double sPar = FastMath.sqrt(lmPar);
            for (j5 = 0; j5 < solvedCols; ++j5) {
                pj4 = permutation[j5];
                work1[pj4] = sPar * diag[pj4];
            }
            this.determineLMDirection(qy, work1, work2, internalData, solvedCols, work3, lmDir);
            dxNorm = 0.0;
            for (j5 = 0; j5 < solvedCols; ++j5) {
                double s;
                pj4 = permutation[j5];
                work3[pj4] = s = diag[pj4] * lmDir[pj4];
                dxNorm += s * s;
            }
            dxNorm = FastMath.sqrt(dxNorm);
            double previousFP = fp;
            fp = dxNorm - delta;
            if (FastMath.abs(fp) <= 0.1 * delta || parl == 0.0 && fp <= previousFP && previousFP < 0.0) {
                return lmPar;
            }
            for (j4 = 0; j4 < solvedCols; ++j4) {
                pj3 = permutation[j4];
                work1[pj3] = work3[pj3] * diag[pj3] / dxNorm;
            }
            for (j4 = 0; j4 < solvedCols; ++j4) {
                int n = pj3 = permutation[j4];
                work1[n] = work1[n] / work2[j4];
                double tmp = work1[pj3];
                for (int i = j4 + 1; i < solvedCols; ++i) {
                    int n2 = permutation[i];
                    work1[n2] = work1[n2] - weightedJacobian[i][pj3] * tmp;
                }
            }
            sum2 = 0.0;
            for (j4 = 0; j4 < solvedCols; ++j4) {
                double s = work1[permutation[j4]];
                sum2 += s * s;
            }
            double correction = fp / (delta * sum2);
            if (fp > 0.0) {
                parl = FastMath.max(parl, lmPar);
            } else if (fp < 0.0) {
                paru = FastMath.min(paru, lmPar);
            }
            lmPar = FastMath.max(parl, lmPar + correction);
        }
        return lmPar;
    }

    private void determineLMDirection(double[] qy, double[] diag, double[] lmDiag, InternalData internalData, int solvedCols, double[] work, double[] lmDir) {
        int j;
        int pj;
        int j2;
        int[] permutation = internalData.permutation;
        double[][] weightedJacobian = internalData.weightedJacobian;
        double[] diagR = internalData.diagR;
        for (j2 = 0; j2 < solvedCols; ++j2) {
            pj = permutation[j2];
            for (int i = j2 + 1; i < solvedCols; ++i) {
                weightedJacobian[i][pj] = weightedJacobian[j2][permutation[i]];
            }
            lmDir[j2] = diagR[pj];
            work[j2] = qy[j2];
        }
        for (j2 = 0; j2 < solvedCols; ++j2) {
            pj = permutation[j2];
            double dpj = diag[pj];
            if (dpj != 0.0) {
                Arrays.fill(lmDiag, j2 + 1, lmDiag.length, 0.0);
            }
            lmDiag[j2] = dpj;
            double qtbpj = 0.0;
            for (int k = j2; k < solvedCols; ++k) {
                double cos;
                double sin;
                int pk = permutation[k];
                if (lmDiag[k] == 0.0) continue;
                double rkk = weightedJacobian[k][pk];
                if (FastMath.abs(rkk) < FastMath.abs(lmDiag[k])) {
                    double cotan = rkk / lmDiag[k];
                    sin = 1.0 / FastMath.sqrt(1.0 + cotan * cotan);
                    cos = sin * cotan;
                } else {
                    double tan = lmDiag[k] / rkk;
                    cos = 1.0 / FastMath.sqrt(1.0 + tan * tan);
                    sin = cos * tan;
                }
                weightedJacobian[k][pk] = cos * rkk + sin * lmDiag[k];
                double temp = cos * work[k] + sin * qtbpj;
                qtbpj = -sin * work[k] + cos * qtbpj;
                work[k] = temp;
                for (int i = k + 1; i < solvedCols; ++i) {
                    double rik = weightedJacobian[i][pk];
                    double temp2 = cos * rik + sin * lmDiag[i];
                    lmDiag[i] = -sin * rik + cos * lmDiag[i];
                    weightedJacobian[i][pk] = temp2;
                }
            }
            lmDiag[j2] = weightedJacobian[j2][permutation[j2]];
            weightedJacobian[j2][permutation[j2]] = lmDir[j2];
        }
        int nSing = solvedCols;
        for (j = 0; j < solvedCols; ++j) {
            if (lmDiag[j] == 0.0 && nSing == solvedCols) {
                nSing = j;
            }
            if (nSing >= solvedCols) continue;
            work[j] = 0.0;
        }
        if (nSing > 0) {
            for (j = nSing - 1; j >= 0; --j) {
                int pj2 = permutation[j];
                double sum = 0.0;
                for (int i = j + 1; i < nSing; ++i) {
                    sum += weightedJacobian[i][pj2] * work[i];
                }
                work[j] = (work[j] - sum) / lmDiag[j];
            }
        }
        for (j = 0; j < lmDir.length; ++j) {
            lmDir[permutation[j]] = work[j];
        }
    }

    private InternalData qrDecomposition(RealMatrix jacobian, int solvedCols) throws ConvergenceException {
        int k;
        double[][] weightedJacobian = jacobian.scalarMultiply(-1.0).getData();
        int nR = weightedJacobian.length;
        int nC = weightedJacobian[0].length;
        int[] permutation = new int[nC];
        double[] diagR = new double[nC];
        double[] jacNorm = new double[nC];
        double[] beta = new double[nC];
        for (k = 0; k < nC; ++k) {
            permutation[k] = k;
            double norm2 = 0.0;
            for (int i = 0; i < nR; ++i) {
                double akk = weightedJacobian[i][k];
                norm2 += akk * akk;
            }
            jacNorm[k] = FastMath.sqrt(norm2);
        }
        for (k = 0; k < nC; ++k) {
            double betak;
            int nextColumn = -1;
            double ak2 = Double.NEGATIVE_INFINITY;
            for (int i = k; i < nC; ++i) {
                double norm2 = 0.0;
                for (int j = k; j < nR; ++j) {
                    double aki = weightedJacobian[j][permutation[i]];
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
                return new InternalData(weightedJacobian, permutation, k, diagR, jacNorm, beta);
            }
            int pk = permutation[nextColumn];
            permutation[nextColumn] = permutation[k];
            permutation[k] = pk;
            double akk = weightedJacobian[k][pk];
            double alpha = akk > 0.0 ? -FastMath.sqrt(ak2) : FastMath.sqrt(ak2);
            beta[pk] = betak = 1.0 / (ak2 - akk * alpha);
            diagR[pk] = alpha;
            double[] dArray = weightedJacobian[k];
            int n = pk;
            dArray[n] = dArray[n] - alpha;
            for (int dk = nC - 1 - k; dk > 0; --dk) {
                int j;
                double gamma = 0.0;
                for (j = k; j < nR; ++j) {
                    gamma += weightedJacobian[j][pk] * weightedJacobian[j][permutation[k + dk]];
                }
                gamma *= betak;
                for (j = k; j < nR; ++j) {
                    double[] dArray2 = weightedJacobian[j];
                    int n2 = permutation[k + dk];
                    dArray2[n2] = dArray2[n2] - gamma * weightedJacobian[j][pk];
                }
            }
        }
        return new InternalData(weightedJacobian, permutation, solvedCols, diagR, jacNorm, beta);
    }

    private void qTy(double[] y, InternalData internalData) {
        double[][] weightedJacobian = internalData.weightedJacobian;
        int[] permutation = internalData.permutation;
        double[] beta = internalData.beta;
        int nR = weightedJacobian.length;
        int nC = weightedJacobian[0].length;
        for (int k = 0; k < nC; ++k) {
            int i;
            int pk = permutation[k];
            double gamma = 0.0;
            for (i = k; i < nR; ++i) {
                gamma += weightedJacobian[i][pk] * y[i];
            }
            gamma *= beta[pk];
            for (i = k; i < nR; ++i) {
                int n = i;
                y[n] = y[n] - gamma * weightedJacobian[i][pk];
            }
        }
    }

    private static class InternalData {
        private final double[][] weightedJacobian;
        private final int[] permutation;
        private final int rank;
        private final double[] diagR;
        private final double[] jacNorm;
        private final double[] beta;

        InternalData(double[][] weightedJacobian, int[] permutation, int rank, double[] diagR, double[] jacNorm, double[] beta) {
            this.weightedJacobian = weightedJacobian;
            this.permutation = permutation;
            this.rank = rank;
            this.diagR = diagR;
            this.jacNorm = jacNorm;
            this.beta = beta;
        }
    }
}

