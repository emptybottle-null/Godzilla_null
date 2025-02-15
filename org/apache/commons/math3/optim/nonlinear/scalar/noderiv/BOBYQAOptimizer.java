/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.util.FastMath;

public class BOBYQAOptimizer
extends MultivariateOptimizer {
    public static final int MINIMUM_PROBLEM_DIMENSION = 2;
    public static final double DEFAULT_INITIAL_RADIUS = 10.0;
    public static final double DEFAULT_STOPPING_RADIUS = 1.0E-8;
    private static final double ZERO = 0.0;
    private static final double ONE = 1.0;
    private static final double TWO = 2.0;
    private static final double TEN = 10.0;
    private static final double SIXTEEN = 16.0;
    private static final double TWO_HUNDRED_FIFTY = 250.0;
    private static final double MINUS_ONE = -1.0;
    private static final double HALF = 0.5;
    private static final double ONE_OVER_FOUR = 0.25;
    private static final double ONE_OVER_EIGHT = 0.125;
    private static final double ONE_OVER_TEN = 0.1;
    private static final double ONE_OVER_A_THOUSAND = 0.001;
    private final int numberOfInterpolationPoints;
    private double initialTrustRegionRadius;
    private final double stoppingTrustRegionRadius;
    private boolean isMinimize;
    private ArrayRealVector currentBest;
    private double[] boundDifference;
    private int trustRegionCenterInterpolationPointIndex;
    private Array2DRowRealMatrix bMatrix;
    private Array2DRowRealMatrix zMatrix;
    private Array2DRowRealMatrix interpolationPoints;
    private ArrayRealVector originShift;
    private ArrayRealVector fAtInterpolationPoints;
    private ArrayRealVector trustRegionCenterOffset;
    private ArrayRealVector gradientAtTrustRegionCenter;
    private ArrayRealVector lowerDifference;
    private ArrayRealVector upperDifference;
    private ArrayRealVector modelSecondDerivativesParameters;
    private ArrayRealVector newPoint;
    private ArrayRealVector alternativeNewPoint;
    private ArrayRealVector trialStepPoint;
    private ArrayRealVector lagrangeValuesAtNewPoint;
    private ArrayRealVector modelSecondDerivativesValues;

    public BOBYQAOptimizer(int numberOfInterpolationPoints) {
        this(numberOfInterpolationPoints, 10.0, 1.0E-8);
    }

    public BOBYQAOptimizer(int numberOfInterpolationPoints, double initialTrustRegionRadius, double stoppingTrustRegionRadius) {
        super((ConvergenceChecker<PointValuePair>)null);
        this.numberOfInterpolationPoints = numberOfInterpolationPoints;
        this.initialTrustRegionRadius = initialTrustRegionRadius;
        this.stoppingTrustRegionRadius = stoppingTrustRegionRadius;
    }

    protected PointValuePair doOptimize() {
        double[] lowerBound = this.getLowerBound();
        double[] upperBound = this.getUpperBound();
        this.setup(lowerBound, upperBound);
        this.isMinimize = this.getGoalType() == GoalType.MINIMIZE;
        this.currentBest = new ArrayRealVector(this.getStartPoint());
        double value = this.bobyqa(lowerBound, upperBound);
        return new PointValuePair(this.currentBest.getDataRef(), this.isMinimize ? value : -value);
    }

    private double bobyqa(double[] lowerBound, double[] upperBound) {
        BOBYQAOptimizer.printMethod();
        int n = this.currentBest.getDimension();
        for (int j = 0; j < n; ++j) {
            double deltaOne;
            double boundDiff = this.boundDifference[j];
            this.lowerDifference.setEntry(j, lowerBound[j] - this.currentBest.getEntry(j));
            this.upperDifference.setEntry(j, upperBound[j] - this.currentBest.getEntry(j));
            if (this.lowerDifference.getEntry(j) >= -this.initialTrustRegionRadius) {
                if (this.lowerDifference.getEntry(j) >= 0.0) {
                    this.currentBest.setEntry(j, lowerBound[j]);
                    this.lowerDifference.setEntry(j, 0.0);
                    this.upperDifference.setEntry(j, boundDiff);
                    continue;
                }
                this.currentBest.setEntry(j, lowerBound[j] + this.initialTrustRegionRadius);
                this.lowerDifference.setEntry(j, -this.initialTrustRegionRadius);
                deltaOne = upperBound[j] - this.currentBest.getEntry(j);
                this.upperDifference.setEntry(j, FastMath.max(deltaOne, this.initialTrustRegionRadius));
                continue;
            }
            if (!(this.upperDifference.getEntry(j) <= this.initialTrustRegionRadius)) continue;
            if (this.upperDifference.getEntry(j) <= 0.0) {
                this.currentBest.setEntry(j, upperBound[j]);
                this.lowerDifference.setEntry(j, -boundDiff);
                this.upperDifference.setEntry(j, 0.0);
                continue;
            }
            this.currentBest.setEntry(j, upperBound[j] - this.initialTrustRegionRadius);
            deltaOne = lowerBound[j] - this.currentBest.getEntry(j);
            double deltaTwo = -this.initialTrustRegionRadius;
            this.lowerDifference.setEntry(j, FastMath.min(deltaOne, deltaTwo));
            this.upperDifference.setEntry(j, this.initialTrustRegionRadius);
        }
        return this.bobyqb(lowerBound, upperBound);
    }

    /*
     * Unable to fully structure code
     */
    private double bobyqb(double[] lowerBound, double[] upperBound) {
        BOBYQAOptimizer.printMethod();
        n = this.currentBest.getDimension();
        npt = this.numberOfInterpolationPoints;
        np = n + 1;
        nptm = npt - np;
        nh = n * np / 2;
        work1 = new ArrayRealVector(n);
        work2 = new ArrayRealVector(npt);
        work3 = new ArrayRealVector(npt);
        cauchy = NaN;
        alpha = NaN;
        dsq = NaN;
        crvmin = NaN;
        this.trustRegionCenterInterpolationPointIndex = 0;
        this.prelim(lowerBound, upperBound);
        xoptsq = 0.0;
        for (i = 0; i < n; ++i) {
            this.trustRegionCenterOffset.setEntry(i, this.interpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex, i));
            deltaOne = this.trustRegionCenterOffset.getEntry(i);
            xoptsq += deltaOne * deltaOne;
        }
        fsave = this.fAtInterpolationPoints.getEntry(0);
        kbase = false;
        ntrits = 0;
        itest = 0;
        knew = 0;
        nfsav = this.getEvaluations();
        delta = rho = this.initialTrustRegionRadius;
        diffa = 0.0;
        diffb = 0.0;
        diffc = 0.0;
        f = 0.0;
        beta = 0.0;
        adelt = 0.0;
        denom = 0.0;
        ratio = 0.0;
        dnorm = 0.0;
        scaden = 0.0;
        biglsq = 0.0;
        distsq = 0.0;
        state = 20;
        block12: while (true) {
            switch (state) {
                case 20: {
                    BOBYQAOptimizer.printState(20);
                    if (this.trustRegionCenterInterpolationPointIndex != 0) {
                        ih = 0;
                        for (j = 0; j < n; ++j) {
                            for (i = 0; i <= j; ++i) {
                                if (i < j) {
                                    this.gradientAtTrustRegionCenter.setEntry(j, this.gradientAtTrustRegionCenter.getEntry(j) + this.modelSecondDerivativesValues.getEntry(ih) * this.trustRegionCenterOffset.getEntry(i));
                                }
                                this.gradientAtTrustRegionCenter.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i) + this.modelSecondDerivativesValues.getEntry(ih) * this.trustRegionCenterOffset.getEntry(j));
                                ++ih;
                            }
                        }
                        if (this.getEvaluations() > npt) {
                            for (k = 0; k < npt; ++k) {
                                temp = 0.0;
                                for (j = 0; j < n; ++j) {
                                    temp += this.interpolationPoints.getEntry(k, j) * this.trustRegionCenterOffset.getEntry(j);
                                }
                                temp *= this.modelSecondDerivativesParameters.getEntry(k);
                                for (i = 0; i < n; ++i) {
                                    this.gradientAtTrustRegionCenter.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i) + temp * this.interpolationPoints.getEntry(k, i));
                                }
                            }
                        }
                    }
                }
                case 60: {
                    BOBYQAOptimizer.printState(60);
                    gnew = new ArrayRealVector(n);
                    xbdi = new ArrayRealVector(n);
                    s = new ArrayRealVector(n);
                    hs = new ArrayRealVector(n);
                    hred = new ArrayRealVector(n);
                    dsqCrvmin = this.trsbox(delta, gnew, xbdi, s, hs, hred);
                    dsq = dsqCrvmin[0];
                    crvmin = dsqCrvmin[1];
                    deltaOne = delta;
                    deltaTwo = FastMath.sqrt(dsq);
                    dnorm = FastMath.min(deltaOne, deltaTwo);
                    if (dnorm < 0.5 * rho) {
                        ntrits = -1;
                        deltaOne = 10.0 * rho;
                        distsq = deltaOne * deltaOne;
                        if (this.getEvaluations() <= nfsav + 2) {
                            state = 650;
                            continue block12;
                        }
                        deltaOne = FastMath.max(diffa, diffb);
                        errbig = FastMath.max(deltaOne, diffc);
                        frhosq = rho * 0.125 * rho;
                        if (crvmin > 0.0 && errbig > frhosq * crvmin) {
                            state = 650;
                            continue block12;
                        }
                        bdtol = errbig / rho;
                        for (j = 0; j < n; ++j) {
                            bdtest = bdtol;
                            if (this.newPoint.getEntry(j) == this.lowerDifference.getEntry(j)) {
                                bdtest = work1.getEntry(j);
                            }
                            if (this.newPoint.getEntry(j) == this.upperDifference.getEntry(j)) {
                                bdtest = -work1.getEntry(j);
                            }
                            if (!(bdtest < bdtol)) continue;
                            curv = this.modelSecondDerivativesValues.getEntry((j + j * j) / 2);
                            for (k = 0; k < npt; ++k) {
                                d1 = this.interpolationPoints.getEntry(k, j);
                                curv += this.modelSecondDerivativesParameters.getEntry(k) * (d1 * d1);
                            }
                            if (!((bdtest += 0.5 * curv * rho) < bdtol)) continue;
                            state = 650;
                            break;
                        }
                        state = 680;
                        continue block12;
                    }
                    ++ntrits;
                }
                case 90: {
                    BOBYQAOptimizer.printState(90);
                    if (dsq <= xoptsq * 0.001) {
                        fracsq = xoptsq * 0.25;
                        sumpq = 0.0;
                        for (k = 0; k < npt; ++k) {
                            sumpq += this.modelSecondDerivativesParameters.getEntry(k);
                            sum = -0.5 * xoptsq;
                            for (i = 0; i < n; ++i) {
                                sum += this.interpolationPoints.getEntry(k, i) * this.trustRegionCenterOffset.getEntry(i);
                            }
                            work2.setEntry(k, sum);
                            temp = fracsq - 0.5 * sum;
                            for (i = 0; i < n; ++i) {
                                work1.setEntry(i, this.bMatrix.getEntry(k, i));
                                this.lagrangeValuesAtNewPoint.setEntry(i, sum * this.interpolationPoints.getEntry(k, i) + temp * this.trustRegionCenterOffset.getEntry(i));
                                ip = npt + i;
                                for (j = 0; j <= i; ++j) {
                                    this.bMatrix.setEntry(ip, j, this.bMatrix.getEntry(ip, j) + work1.getEntry(i) * this.lagrangeValuesAtNewPoint.getEntry(j) + this.lagrangeValuesAtNewPoint.getEntry(i) * work1.getEntry(j));
                                }
                            }
                        }
                        for (m = 0; m < nptm; ++m) {
                            sumz = 0.0;
                            sumw = 0.0;
                            for (k = 0; k < npt; ++k) {
                                sumz += this.zMatrix.getEntry(k, m);
                                this.lagrangeValuesAtNewPoint.setEntry(k, work2.getEntry(k) * this.zMatrix.getEntry(k, m));
                                sumw += this.lagrangeValuesAtNewPoint.getEntry(k);
                            }
                            for (j = 0; j < n; ++j) {
                                sum = (fracsq * sumz - 0.5 * sumw) * this.trustRegionCenterOffset.getEntry(j);
                                for (k = 0; k < npt; ++k) {
                                    sum += this.lagrangeValuesAtNewPoint.getEntry(k) * this.interpolationPoints.getEntry(k, j);
                                }
                                work1.setEntry(j, sum);
                                for (k = 0; k < npt; ++k) {
                                    this.bMatrix.setEntry(k, j, this.bMatrix.getEntry(k, j) + sum * this.zMatrix.getEntry(k, m));
                                }
                            }
                            for (i = 0; i < n; ++i) {
                                ip = i + npt;
                                temp = work1.getEntry(i);
                                for (j = 0; j <= i; ++j) {
                                    this.bMatrix.setEntry(ip, j, this.bMatrix.getEntry(ip, j) + temp * work1.getEntry(j));
                                }
                            }
                        }
                        ih = 0;
                        for (j = 0; j < n; ++j) {
                            work1.setEntry(j, -0.5 * sumpq * this.trustRegionCenterOffset.getEntry(j));
                            for (k = 0; k < npt; ++k) {
                                work1.setEntry(j, work1.getEntry(j) + this.modelSecondDerivativesParameters.getEntry(k) * this.interpolationPoints.getEntry(k, j));
                                this.interpolationPoints.setEntry(k, j, this.interpolationPoints.getEntry(k, j) - this.trustRegionCenterOffset.getEntry(j));
                            }
                            for (i = 0; i <= j; ++i) {
                                this.modelSecondDerivativesValues.setEntry(ih, this.modelSecondDerivativesValues.getEntry(ih) + work1.getEntry(i) * this.trustRegionCenterOffset.getEntry(j) + this.trustRegionCenterOffset.getEntry(i) * work1.getEntry(j));
                                this.bMatrix.setEntry(npt + i, j, this.bMatrix.getEntry(npt + j, i));
                                ++ih;
                            }
                        }
                        for (i = 0; i < n; ++i) {
                            this.originShift.setEntry(i, this.originShift.getEntry(i) + this.trustRegionCenterOffset.getEntry(i));
                            this.newPoint.setEntry(i, this.newPoint.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                            this.lowerDifference.setEntry(i, this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                            this.upperDifference.setEntry(i, this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                            this.trustRegionCenterOffset.setEntry(i, 0.0);
                        }
                        xoptsq = 0.0;
                    }
                    if (ntrits == 0) {
                        state = 210;
                        continue block12;
                    }
                    state = 230;
                    continue block12;
                }
                case 210: {
                    BOBYQAOptimizer.printState(210);
                    alphaCauchy = this.altmov(knew, adelt);
                    alpha = alphaCauchy[0];
                    cauchy = alphaCauchy[1];
                    for (i = 0; i < n; ++i) {
                        this.trialStepPoint.setEntry(i, this.newPoint.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                    }
                }
                case 230: {
                    BOBYQAOptimizer.printState(230);
                    for (k = 0; k < npt; ++k) {
                        suma = 0.0;
                        sumb = 0.0;
                        sum = 0.0;
                        for (j = 0; j < n; ++j) {
                            suma += this.interpolationPoints.getEntry(k, j) * this.trialStepPoint.getEntry(j);
                            sumb += this.interpolationPoints.getEntry(k, j) * this.trustRegionCenterOffset.getEntry(j);
                            sum += this.bMatrix.getEntry(k, j) * this.trialStepPoint.getEntry(j);
                        }
                        work3.setEntry(k, suma * (0.5 * suma + sumb));
                        this.lagrangeValuesAtNewPoint.setEntry(k, sum);
                        work2.setEntry(k, suma);
                    }
                    beta = 0.0;
                    for (m = 0; m < nptm; ++m) {
                        sum = 0.0;
                        for (k = 0; k < npt; ++k) {
                            sum += this.zMatrix.getEntry(k, m) * work3.getEntry(k);
                        }
                        beta -= sum * sum;
                        for (k = 0; k < npt; ++k) {
                            this.lagrangeValuesAtNewPoint.setEntry(k, this.lagrangeValuesAtNewPoint.getEntry(k) + sum * this.zMatrix.getEntry(k, m));
                        }
                    }
                    dsq = 0.0;
                    bsum = 0.0;
                    dx = 0.0;
                    for (j = 0; j < n; ++j) {
                        d1 = this.trialStepPoint.getEntry(j);
                        dsq += d1 * d1;
                        sum = 0.0;
                        for (k = 0; k < npt; ++k) {
                            sum += work3.getEntry(k) * this.bMatrix.getEntry(k, j);
                        }
                        bsum += sum * this.trialStepPoint.getEntry(j);
                        jp = npt + j;
                        for (i = 0; i < n; ++i) {
                            sum += this.bMatrix.getEntry(jp, i) * this.trialStepPoint.getEntry(i);
                        }
                        this.lagrangeValuesAtNewPoint.setEntry(jp, sum);
                        bsum += sum * this.trialStepPoint.getEntry(j);
                        dx += this.trialStepPoint.getEntry(j) * this.trustRegionCenterOffset.getEntry(j);
                    }
                    beta = dx * dx + dsq * (xoptsq + dx + dx + 0.5 * dsq) + beta - bsum;
                    this.lagrangeValuesAtNewPoint.setEntry(this.trustRegionCenterInterpolationPointIndex, this.lagrangeValuesAtNewPoint.getEntry(this.trustRegionCenterInterpolationPointIndex) + 1.0);
                    if (ntrits != 0) ** GOTO lbl253
                    d1 = this.lagrangeValuesAtNewPoint.getEntry(knew);
                    denom = d1 * d1 + alpha * beta;
                    if (denom < cauchy && cauchy > 0.0) {
                        for (i = 0; i < n; ++i) {
                            this.newPoint.setEntry(i, this.alternativeNewPoint.getEntry(i));
                            this.trialStepPoint.setEntry(i, this.newPoint.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                        }
                        cauchy = 0.0;
                        state = 230;
                        continue block12;
                    }
                    ** GOTO lbl280
lbl253:
                    // 1 sources

                    delsq = delta * delta;
                    scaden = 0.0;
                    biglsq = 0.0;
                    knew = 0;
                    for (k = 0; k < npt; ++k) {
                        if (k == this.trustRegionCenterInterpolationPointIndex) continue;
                        hdiag = 0.0;
                        for (m = 0; m < nptm; ++m) {
                            d1 = this.zMatrix.getEntry(k, m);
                            hdiag += d1 * d1;
                        }
                        d2 = this.lagrangeValuesAtNewPoint.getEntry(k);
                        den = beta * hdiag + d2 * d2;
                        distsq = 0.0;
                        for (j = 0; j < n; ++j) {
                            d3 = this.interpolationPoints.getEntry(k, j) - this.trustRegionCenterOffset.getEntry(j);
                            distsq += d3 * d3;
                        }
                        d4 = distsq / delsq;
                        temp = FastMath.max(1.0, d4 * d4);
                        if (temp * den > scaden) {
                            scaden = temp * den;
                            knew = k;
                            denom = den;
                        }
                        d5 = this.lagrangeValuesAtNewPoint.getEntry(k);
                        biglsq = FastMath.max(biglsq, temp * (d5 * d5));
                    }
                }
lbl280:
                // 3 sources

                case 360: {
                    BOBYQAOptimizer.printState(360);
                    for (i = 0; i < n; ++i) {
                        d3 = lowerBound[i];
                        d4 = this.originShift.getEntry(i) + this.newPoint.getEntry(i);
                        d1 = FastMath.max(d3, d4);
                        d2 = upperBound[i];
                        this.currentBest.setEntry(i, FastMath.min(d1, d2));
                        if (this.newPoint.getEntry(i) == this.lowerDifference.getEntry(i)) {
                            this.currentBest.setEntry(i, lowerBound[i]);
                        }
                        if (this.newPoint.getEntry(i) != this.upperDifference.getEntry(i)) continue;
                        this.currentBest.setEntry(i, upperBound[i]);
                    }
                    f = this.computeObjectiveValue(this.currentBest.toArray());
                    if (!this.isMinimize) {
                        f = -f;
                    }
                    if (ntrits == -1) {
                        fsave = f;
                        state = 720;
                        continue block12;
                    }
                    fopt = this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex);
                    vquad = 0.0;
                    ih = 0;
                    for (j = 0; j < n; ++j) {
                        vquad += this.trialStepPoint.getEntry(j) * this.gradientAtTrustRegionCenter.getEntry(j);
                        for (i = 0; i <= j; ++i) {
                            temp = this.trialStepPoint.getEntry(i) * this.trialStepPoint.getEntry(j);
                            if (i == j) {
                                temp *= 0.5;
                            }
                            vquad += this.modelSecondDerivativesValues.getEntry(ih) * temp;
                            ++ih;
                        }
                    }
                    for (k = 0; k < npt; ++k) {
                        d1 = work2.getEntry(k);
                        d2 = d1 * d1;
                        vquad += 0.5 * this.modelSecondDerivativesParameters.getEntry(k) * d2;
                    }
                    diff = f - fopt - vquad;
                    diffc = diffb;
                    diffb = diffa;
                    diffa = FastMath.abs(diff);
                    if (dnorm > rho) {
                        nfsav = this.getEvaluations();
                    }
                    if (ntrits > 0) {
                        if (vquad >= 0.0) {
                            throw new MathIllegalStateException(LocalizedFormats.TRUST_REGION_STEP_FAILED, new Object[]{vquad});
                        }
                        ratio = (f - fopt) / vquad;
                        hDelta = 0.5 * delta;
                        delta = ratio <= 0.1 ? FastMath.min(hDelta, dnorm) : (ratio <= 0.7 ? FastMath.max(hDelta, dnorm) : FastMath.max(hDelta, 2.0 * dnorm));
                        if (delta <= rho * 1.5) {
                            delta = rho;
                        }
                        if (f < fopt) {
                            ksav = knew;
                            densav = denom;
                            delsq = delta * delta;
                            scaden = 0.0;
                            biglsq = 0.0;
                            knew = 0;
                            for (k = 0; k < npt; ++k) {
                                hdiag = 0.0;
                                for (m = 0; m < nptm; ++m) {
                                    d1 = this.zMatrix.getEntry(k, m);
                                    hdiag += d1 * d1;
                                }
                                d1 = this.lagrangeValuesAtNewPoint.getEntry(k);
                                den = beta * hdiag + d1 * d1;
                                distsq = 0.0;
                                for (j = 0; j < n; ++j) {
                                    d2 = this.interpolationPoints.getEntry(k, j) - this.newPoint.getEntry(j);
                                    distsq += d2 * d2;
                                }
                                d3 = distsq / delsq;
                                temp = FastMath.max(1.0, d3 * d3);
                                if (temp * den > scaden) {
                                    scaden = temp * den;
                                    knew = k;
                                    denom = den;
                                }
                                d4 = this.lagrangeValuesAtNewPoint.getEntry(k);
                                d5 = temp * (d4 * d4);
                                biglsq = FastMath.max(biglsq, d5);
                            }
                            if (scaden <= 0.5 * biglsq) {
                                knew = ksav;
                                denom = densav;
                            }
                        }
                    }
                    this.update(beta, denom, knew);
                    ih = 0;
                    pqold = this.modelSecondDerivativesParameters.getEntry(knew);
                    this.modelSecondDerivativesParameters.setEntry(knew, 0.0);
                    for (i = 0; i < n; ++i) {
                        temp = pqold * this.interpolationPoints.getEntry(knew, i);
                        for (j = 0; j <= i; ++j) {
                            this.modelSecondDerivativesValues.setEntry(ih, this.modelSecondDerivativesValues.getEntry(ih) + temp * this.interpolationPoints.getEntry(knew, j));
                            ++ih;
                        }
                    }
                    for (m = 0; m < nptm; ++m) {
                        temp = diff * this.zMatrix.getEntry(knew, m);
                        for (k = 0; k < npt; ++k) {
                            this.modelSecondDerivativesParameters.setEntry(k, this.modelSecondDerivativesParameters.getEntry(k) + temp * this.zMatrix.getEntry(k, m));
                        }
                    }
                    this.fAtInterpolationPoints.setEntry(knew, f);
                    for (i = 0; i < n; ++i) {
                        this.interpolationPoints.setEntry(knew, i, this.newPoint.getEntry(i));
                        work1.setEntry(i, this.bMatrix.getEntry(knew, i));
                    }
                    for (k = 0; k < npt; ++k) {
                        suma = 0.0;
                        for (m = 0; m < nptm; ++m) {
                            suma += this.zMatrix.getEntry(knew, m) * this.zMatrix.getEntry(k, m);
                        }
                        sumb = 0.0;
                        for (j = 0; j < n; ++j) {
                            sumb += this.interpolationPoints.getEntry(k, j) * this.trustRegionCenterOffset.getEntry(j);
                        }
                        temp = suma * sumb;
                        for (i = 0; i < n; ++i) {
                            work1.setEntry(i, work1.getEntry(i) + temp * this.interpolationPoints.getEntry(k, i));
                        }
                    }
                    for (i = 0; i < n; ++i) {
                        this.gradientAtTrustRegionCenter.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i) + diff * work1.getEntry(i));
                    }
                    if (f < fopt) {
                        this.trustRegionCenterInterpolationPointIndex = knew;
                        xoptsq = 0.0;
                        ih = 0;
                        for (j = 0; j < n; ++j) {
                            this.trustRegionCenterOffset.setEntry(j, this.newPoint.getEntry(j));
                            d1 = this.trustRegionCenterOffset.getEntry(j);
                            xoptsq += d1 * d1;
                            for (i = 0; i <= j; ++i) {
                                if (i < j) {
                                    this.gradientAtTrustRegionCenter.setEntry(j, this.gradientAtTrustRegionCenter.getEntry(j) + this.modelSecondDerivativesValues.getEntry(ih) * this.trialStepPoint.getEntry(i));
                                }
                                this.gradientAtTrustRegionCenter.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i) + this.modelSecondDerivativesValues.getEntry(ih) * this.trialStepPoint.getEntry(j));
                                ++ih;
                            }
                        }
                        for (k = 0; k < npt; ++k) {
                            temp = 0.0;
                            for (j = 0; j < n; ++j) {
                                temp += this.interpolationPoints.getEntry(k, j) * this.trialStepPoint.getEntry(j);
                            }
                            temp *= this.modelSecondDerivativesParameters.getEntry(k);
                            for (i = 0; i < n; ++i) {
                                this.gradientAtTrustRegionCenter.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i) + temp * this.interpolationPoints.getEntry(k, i));
                            }
                        }
                    }
                    if (ntrits > 0) {
                        for (k = 0; k < npt; ++k) {
                            this.lagrangeValuesAtNewPoint.setEntry(k, this.fAtInterpolationPoints.getEntry(k) - this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex));
                            work3.setEntry(k, 0.0);
                        }
                        for (j = 0; j < nptm; ++j) {
                            sum = 0.0;
                            for (k = 0; k < npt; ++k) {
                                sum += this.zMatrix.getEntry(k, j) * this.lagrangeValuesAtNewPoint.getEntry(k);
                            }
                            for (k = 0; k < npt; ++k) {
                                work3.setEntry(k, work3.getEntry(k) + sum * this.zMatrix.getEntry(k, j));
                            }
                        }
                        for (k = 0; k < npt; ++k) {
                            sum = 0.0;
                            for (j = 0; j < n; ++j) {
                                sum += this.interpolationPoints.getEntry(k, j) * this.trustRegionCenterOffset.getEntry(j);
                            }
                            work2.setEntry(k, work3.getEntry(k));
                            work3.setEntry(k, sum * work3.getEntry(k));
                        }
                        gqsq = 0.0;
                        gisq = 0.0;
                        for (i = 0; i < n; ++i) {
                            sum = 0.0;
                            for (k = 0; k < npt; ++k) {
                                sum += this.bMatrix.getEntry(k, i) * this.lagrangeValuesAtNewPoint.getEntry(k) + this.interpolationPoints.getEntry(k, i) * work3.getEntry(k);
                            }
                            if (this.trustRegionCenterOffset.getEntry(i) == this.lowerDifference.getEntry(i)) {
                                d1 = FastMath.min(0.0, this.gradientAtTrustRegionCenter.getEntry(i));
                                gqsq += d1 * d1;
                                d2 = FastMath.min(0.0, sum);
                                gisq += d2 * d2;
                            } else if (this.trustRegionCenterOffset.getEntry(i) == this.upperDifference.getEntry(i)) {
                                d1 = FastMath.max(0.0, this.gradientAtTrustRegionCenter.getEntry(i));
                                gqsq += d1 * d1;
                                d2 = FastMath.max(0.0, sum);
                                gisq += d2 * d2;
                            } else {
                                d1 = this.gradientAtTrustRegionCenter.getEntry(i);
                                gqsq += d1 * d1;
                                gisq += sum * sum;
                            }
                            this.lagrangeValuesAtNewPoint.setEntry(npt + i, sum);
                        }
                        ++itest;
                        if (gqsq < 10.0 * gisq) {
                            itest = 0;
                        }
                        if (itest >= 3) {
                            max = FastMath.max(npt, nh);
                            for (i = 0; i < max; ++i) {
                                if (i < n) {
                                    this.gradientAtTrustRegionCenter.setEntry(i, this.lagrangeValuesAtNewPoint.getEntry(npt + i));
                                }
                                if (i < npt) {
                                    this.modelSecondDerivativesParameters.setEntry(i, work2.getEntry(i));
                                }
                                if (i < nh) {
                                    this.modelSecondDerivativesValues.setEntry(i, 0.0);
                                }
                                itest = 0;
                            }
                        }
                    }
                    if (ntrits == 0) {
                        state = 60;
                        continue block12;
                    }
                    if (f <= fopt + 0.1 * vquad) {
                        state = 60;
                        continue block12;
                    }
                    d1 = 2.0 * delta;
                    d2 = 10.0 * rho;
                    distsq = FastMath.max(d1 * d1, d2 * d2);
                }
                case 650: {
                    BOBYQAOptimizer.printState(650);
                    knew = -1;
                    for (k = 0; k < npt; ++k) {
                        sum = 0.0;
                        for (j = 0; j < n; ++j) {
                            d1 = this.interpolationPoints.getEntry(k, j) - this.trustRegionCenterOffset.getEntry(j);
                            sum += d1 * d1;
                        }
                        if (!(sum > distsq)) continue;
                        knew = k;
                        distsq = sum;
                    }
                    if (knew >= 0) {
                        dist = FastMath.sqrt(distsq);
                        if (ntrits == -1 && (delta = FastMath.min(0.1 * delta, 0.5 * dist)) <= rho * 1.5) {
                            delta = rho;
                        }
                        ntrits = 0;
                        d1 = FastMath.min(0.1 * dist, delta);
                        adelt = FastMath.max(d1, rho);
                        dsq = adelt * adelt;
                        state = 90;
                        continue block12;
                    }
                    if (ntrits == -1) {
                        state = 680;
                        continue block12;
                    }
                    if (ratio > 0.0) {
                        state = 60;
                        continue block12;
                    }
                    if (FastMath.max(delta, dnorm) > rho) {
                        state = 60;
                        continue block12;
                    }
                }
                case 680: {
                    BOBYQAOptimizer.printState(680);
                    if (rho > this.stoppingTrustRegionRadius) {
                        delta = 0.5 * rho;
                        ratio = rho / this.stoppingTrustRegionRadius;
                        rho = ratio <= 16.0 ? this.stoppingTrustRegionRadius : (ratio <= 250.0 ? FastMath.sqrt(ratio) * this.stoppingTrustRegionRadius : (rho *= 0.1));
                        delta = FastMath.max(delta, rho);
                        ntrits = 0;
                        nfsav = this.getEvaluations();
                        state = 60;
                        continue block12;
                    }
                    if (ntrits == -1) {
                        state = 360;
                        continue block12;
                    }
                }
                case 720: {
                    BOBYQAOptimizer.printState(720);
                    if (this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex) <= fsave) {
                        for (i = 0; i < n; ++i) {
                            d3 = lowerBound[i];
                            d4 = this.originShift.getEntry(i) + this.trustRegionCenterOffset.getEntry(i);
                            d1 = FastMath.max(d3, d4);
                            d2 = upperBound[i];
                            this.currentBest.setEntry(i, FastMath.min(d1, d2));
                            if (this.trustRegionCenterOffset.getEntry(i) == this.lowerDifference.getEntry(i)) {
                                this.currentBest.setEntry(i, lowerBound[i]);
                            }
                            if (this.trustRegionCenterOffset.getEntry(i) != this.upperDifference.getEntry(i)) continue;
                            this.currentBest.setEntry(i, upperBound[i]);
                        }
                        f = this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex);
                    }
                    return f;
                }
            }
            break;
        }
        throw new MathIllegalStateException(LocalizedFormats.SIMPLE_MESSAGE, new Object[]{"bobyqb"});
    }

    private double[] altmov(int knew, double adelt) {
        int k;
        BOBYQAOptimizer.printMethod();
        int n = this.currentBest.getDimension();
        int npt = this.numberOfInterpolationPoints;
        ArrayRealVector glag = new ArrayRealVector(n);
        ArrayRealVector hcol = new ArrayRealVector(npt);
        ArrayRealVector work1 = new ArrayRealVector(n);
        ArrayRealVector work2 = new ArrayRealVector(n);
        for (int k2 = 0; k2 < npt; ++k2) {
            hcol.setEntry(k2, 0.0);
        }
        int max = npt - n - 1;
        for (int j = 0; j < max; ++j) {
            double tmp = this.zMatrix.getEntry(knew, j);
            for (k = 0; k < npt; ++k) {
                hcol.setEntry(k, hcol.getEntry(k) + tmp * this.zMatrix.getEntry(k, j));
            }
        }
        double alpha = hcol.getEntry(knew);
        double ha = 0.5 * alpha;
        for (int i = 0; i < n; ++i) {
            glag.setEntry(i, this.bMatrix.getEntry(knew, i));
        }
        for (k = 0; k < npt; ++k) {
            double tmp = 0.0;
            for (int j = 0; j < n; ++j) {
                tmp += this.interpolationPoints.getEntry(k, j) * this.trustRegionCenterOffset.getEntry(j);
            }
            tmp *= hcol.getEntry(k);
            for (int i = 0; i < n; ++i) {
                glag.setEntry(i, glag.getEntry(i) + tmp * this.interpolationPoints.getEntry(k, i));
            }
        }
        double presav = 0.0;
        double step = Double.NaN;
        int ksav = 0;
        int ibdsav = 0;
        double stpsav = 0.0;
        for (int k3 = 0; k3 < npt; ++k3) {
            double tmp;
            if (k3 == this.trustRegionCenterInterpolationPointIndex) continue;
            double dderiv = 0.0;
            double distsq = 0.0;
            for (int i = 0; i < n; ++i) {
                double tmp2 = this.interpolationPoints.getEntry(k3, i) - this.trustRegionCenterOffset.getEntry(i);
                dderiv += glag.getEntry(i) * tmp2;
                distsq += tmp2 * tmp2;
            }
            double subd = adelt / FastMath.sqrt(distsq);
            double slbd = -subd;
            int ilbd = 0;
            int iubd = 0;
            double sumin = FastMath.min(1.0, subd);
            for (int i = 0; i < n; ++i) {
                double tmp3 = this.interpolationPoints.getEntry(k3, i) - this.trustRegionCenterOffset.getEntry(i);
                if (tmp3 > 0.0) {
                    if (slbd * tmp3 < this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) {
                        slbd = (this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) / tmp3;
                        ilbd = -i - 1;
                    }
                    if (!(subd * tmp3 > this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i))) continue;
                    subd = FastMath.max(sumin, (this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) / tmp3);
                    iubd = i + 1;
                    continue;
                }
                if (!(tmp3 < 0.0)) continue;
                if (slbd * tmp3 > this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) {
                    slbd = (this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) / tmp3;
                    ilbd = i + 1;
                }
                if (!(subd * tmp3 < this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i))) continue;
                subd = FastMath.max(sumin, (this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) / tmp3);
                iubd = -i - 1;
            }
            step = slbd;
            int isbd = ilbd;
            double vlag = Double.NaN;
            if (k3 == knew) {
                double d5;
                double d4;
                double d2;
                double d3;
                double diff = dderiv - 1.0;
                vlag = slbd * (dderiv - slbd * diff);
                double d1 = subd * (dderiv - subd * diff);
                if (FastMath.abs(d1) > FastMath.abs(vlag)) {
                    step = subd;
                    vlag = d1;
                    isbd = iubd;
                }
                if ((d3 = (d2 = 0.5 * dderiv) - diff * slbd) * (d4 = d2 - diff * subd) < 0.0 && FastMath.abs(d5 = d2 * d2 / diff) > FastMath.abs(vlag)) {
                    step = d2 / diff;
                    vlag = d5;
                    isbd = 0;
                }
            } else {
                vlag = slbd * (1.0 - slbd);
                tmp = subd * (1.0 - subd);
                if (FastMath.abs(tmp) > FastMath.abs(vlag)) {
                    step = subd;
                    vlag = tmp;
                    isbd = iubd;
                }
                if (subd > 0.5 && FastMath.abs(vlag) < 0.25) {
                    step = 0.5;
                    vlag = 0.25;
                    isbd = 0;
                }
                vlag *= dderiv;
            }
            tmp = step * (1.0 - step) * distsq;
            double predsq = vlag * vlag * (vlag * vlag + ha * tmp * tmp);
            if (!(predsq > presav)) continue;
            presav = predsq;
            ksav = k3;
            stpsav = step;
            ibdsav = isbd;
        }
        for (int i = 0; i < n; ++i) {
            double tmp = this.trustRegionCenterOffset.getEntry(i) + stpsav * (this.interpolationPoints.getEntry(ksav, i) - this.trustRegionCenterOffset.getEntry(i));
            this.newPoint.setEntry(i, FastMath.max(this.lowerDifference.getEntry(i), FastMath.min(this.upperDifference.getEntry(i), tmp)));
        }
        if (ibdsav < 0) {
            this.newPoint.setEntry(-ibdsav - 1, this.lowerDifference.getEntry(-ibdsav - 1));
        }
        if (ibdsav > 0) {
            this.newPoint.setEntry(ibdsav - 1, this.upperDifference.getEntry(ibdsav - 1));
        }
        double bigstp = adelt + adelt;
        boolean iflag = false;
        double cauchy = Double.NaN;
        double csave = 0.0;
        while (true) {
            double wfixsq = 0.0;
            double ggfree = 0.0;
            for (int i = 0; i < n; ++i) {
                double glagValue = glag.getEntry(i);
                work1.setEntry(i, 0.0);
                if (!(FastMath.min(this.trustRegionCenterOffset.getEntry(i) - this.lowerDifference.getEntry(i), glagValue) > 0.0) && !(FastMath.max(this.trustRegionCenterOffset.getEntry(i) - this.upperDifference.getEntry(i), glagValue) < 0.0)) continue;
                work1.setEntry(i, bigstp);
                ggfree += glagValue * glagValue;
            }
            if (ggfree == 0.0) {
                return new double[]{alpha, 0.0};
            }
            double tmp1 = adelt * adelt - wfixsq;
            if (tmp1 > 0.0) {
                step = FastMath.sqrt(tmp1 / ggfree);
                ggfree = 0.0;
                for (int i = 0; i < n; ++i) {
                    double d1;
                    if (work1.getEntry(i) != bigstp) continue;
                    double tmp2 = this.trustRegionCenterOffset.getEntry(i) - step * glag.getEntry(i);
                    if (tmp2 <= this.lowerDifference.getEntry(i)) {
                        work1.setEntry(i, this.lowerDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                        d1 = work1.getEntry(i);
                        wfixsq += d1 * d1;
                        continue;
                    }
                    if (tmp2 >= this.upperDifference.getEntry(i)) {
                        work1.setEntry(i, this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                        d1 = work1.getEntry(i);
                        wfixsq += d1 * d1;
                        continue;
                    }
                    d1 = glag.getEntry(i);
                    ggfree += d1 * d1;
                }
            }
            double gw = 0.0;
            for (int i = 0; i < n; ++i) {
                double glagValue = glag.getEntry(i);
                if (work1.getEntry(i) == bigstp) {
                    work1.setEntry(i, -step * glagValue);
                    double min = FastMath.min(this.upperDifference.getEntry(i), this.trustRegionCenterOffset.getEntry(i) + work1.getEntry(i));
                    this.alternativeNewPoint.setEntry(i, FastMath.max(this.lowerDifference.getEntry(i), min));
                } else if (work1.getEntry(i) == 0.0) {
                    this.alternativeNewPoint.setEntry(i, this.trustRegionCenterOffset.getEntry(i));
                } else if (glagValue > 0.0) {
                    this.alternativeNewPoint.setEntry(i, this.lowerDifference.getEntry(i));
                } else {
                    this.alternativeNewPoint.setEntry(i, this.upperDifference.getEntry(i));
                }
                gw += glagValue * work1.getEntry(i);
            }
            double curv = 0.0;
            for (int k4 = 0; k4 < npt; ++k4) {
                double tmp = 0.0;
                for (int j = 0; j < n; ++j) {
                    tmp += this.interpolationPoints.getEntry(k4, j) * work1.getEntry(j);
                }
                curv += hcol.getEntry(k4) * tmp * tmp;
            }
            if (iflag) {
                curv = -curv;
            }
            if (curv > -gw && curv < -gw * (1.0 + FastMath.sqrt(2.0))) {
                double scale = -gw / curv;
                for (int i = 0; i < n; ++i) {
                    double tmp = this.trustRegionCenterOffset.getEntry(i) + scale * work1.getEntry(i);
                    this.alternativeNewPoint.setEntry(i, FastMath.max(this.lowerDifference.getEntry(i), FastMath.min(this.upperDifference.getEntry(i), tmp)));
                }
                double d1 = 0.5 * gw * scale;
                cauchy = d1 * d1;
            } else {
                double d1 = gw + 0.5 * curv;
                cauchy = d1 * d1;
            }
            if (iflag) break;
            for (int i = 0; i < n; ++i) {
                glag.setEntry(i, -glag.getEntry(i));
                work2.setEntry(i, this.alternativeNewPoint.getEntry(i));
            }
            csave = cauchy;
            iflag = true;
        }
        if (csave > cauchy) {
            for (int i = 0; i < n; ++i) {
                this.alternativeNewPoint.setEntry(i, work2.getEntry(i));
            }
            cauchy = csave;
        }
        return new double[]{alpha, cauchy};
    }

    private void prelim(double[] lowerBound, double[] upperBound) {
        BOBYQAOptimizer.printMethod();
        int n = this.currentBest.getDimension();
        int npt = this.numberOfInterpolationPoints;
        int ndim = this.bMatrix.getRowDimension();
        double rhosq = this.initialTrustRegionRadius * this.initialTrustRegionRadius;
        double recip = 1.0 / rhosq;
        int np = n + 1;
        for (int j = 0; j < n; ++j) {
            this.originShift.setEntry(j, this.currentBest.getEntry(j));
            for (int k = 0; k < npt; ++k) {
                this.interpolationPoints.setEntry(k, j, 0.0);
            }
            for (int i = 0; i < ndim; ++i) {
                this.bMatrix.setEntry(i, j, 0.0);
            }
        }
        int max = n * np / 2;
        for (int i = 0; i < max; ++i) {
            this.modelSecondDerivativesValues.setEntry(i, 0.0);
        }
        for (int k = 0; k < npt; ++k) {
            this.modelSecondDerivativesParameters.setEntry(k, 0.0);
            int max2 = npt - np;
            for (int j = 0; j < max2; ++j) {
                this.zMatrix.setEntry(k, j, 0.0);
            }
        }
        int ipt = 0;
        int jpt = 0;
        double fbeg = Double.NaN;
        do {
            double tmp;
            int nfm = this.getEvaluations();
            int nfx = nfm - n;
            int nfmm = nfm - 1;
            int nfxm = nfx - 1;
            double stepa = 0.0;
            double stepb = 0.0;
            if (nfm <= 2 * n) {
                if (nfm >= 1 && nfm <= n) {
                    stepa = this.initialTrustRegionRadius;
                    if (this.upperDifference.getEntry(nfmm) == 0.0) {
                        stepa = -stepa;
                    }
                    this.interpolationPoints.setEntry(nfm, nfmm, stepa);
                } else if (nfm > n) {
                    stepa = this.interpolationPoints.getEntry(nfx, nfxm);
                    stepb = -this.initialTrustRegionRadius;
                    if (this.lowerDifference.getEntry(nfxm) == 0.0) {
                        stepb = FastMath.min(2.0 * this.initialTrustRegionRadius, this.upperDifference.getEntry(nfxm));
                    }
                    if (this.upperDifference.getEntry(nfxm) == 0.0) {
                        stepb = FastMath.max(-2.0 * this.initialTrustRegionRadius, this.lowerDifference.getEntry(nfxm));
                    }
                    this.interpolationPoints.setEntry(nfm, nfxm, stepb);
                }
            } else {
                int tmp1 = (nfm - np) / n;
                jpt = nfm - tmp1 * n - n;
                ipt = jpt + tmp1;
                if (ipt > n) {
                    int tmp2 = jpt;
                    jpt = ipt - n;
                    ipt = tmp2;
                }
                int iptMinus1 = ipt - 1;
                int jptMinus1 = jpt - 1;
                this.interpolationPoints.setEntry(nfm, iptMinus1, this.interpolationPoints.getEntry(ipt, iptMinus1));
                this.interpolationPoints.setEntry(nfm, jptMinus1, this.interpolationPoints.getEntry(jpt, jptMinus1));
            }
            for (int j = 0; j < n; ++j) {
                this.currentBest.setEntry(j, FastMath.min(FastMath.max(lowerBound[j], this.originShift.getEntry(j) + this.interpolationPoints.getEntry(nfm, j)), upperBound[j]));
                if (this.interpolationPoints.getEntry(nfm, j) == this.lowerDifference.getEntry(j)) {
                    this.currentBest.setEntry(j, lowerBound[j]);
                }
                if (this.interpolationPoints.getEntry(nfm, j) != this.upperDifference.getEntry(j)) continue;
                this.currentBest.setEntry(j, upperBound[j]);
            }
            double objectiveValue = this.computeObjectiveValue(this.currentBest.toArray());
            double f = this.isMinimize ? objectiveValue : -objectiveValue;
            int numEval = this.getEvaluations();
            this.fAtInterpolationPoints.setEntry(nfm, f);
            if (numEval == 1) {
                fbeg = f;
                this.trustRegionCenterInterpolationPointIndex = 0;
            } else if (f < this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex)) {
                this.trustRegionCenterInterpolationPointIndex = nfm;
            }
            if (numEval <= 2 * n + 1) {
                if (numEval >= 2 && numEval <= n + 1) {
                    this.gradientAtTrustRegionCenter.setEntry(nfmm, (f - fbeg) / stepa);
                    if (npt >= numEval + n) continue;
                    double oneOverStepA = 1.0 / stepa;
                    this.bMatrix.setEntry(0, nfmm, -oneOverStepA);
                    this.bMatrix.setEntry(nfm, nfmm, oneOverStepA);
                    this.bMatrix.setEntry(npt + nfmm, nfmm, -0.5 * rhosq);
                    continue;
                }
                if (numEval < n + 2) continue;
                int ih = nfx * (nfx + 1) / 2 - 1;
                tmp = (f - fbeg) / stepb;
                double diff = stepb - stepa;
                this.modelSecondDerivativesValues.setEntry(ih, 2.0 * (tmp - this.gradientAtTrustRegionCenter.getEntry(nfxm)) / diff);
                this.gradientAtTrustRegionCenter.setEntry(nfxm, (this.gradientAtTrustRegionCenter.getEntry(nfxm) * stepb - tmp * stepa) / diff);
                if (stepa * stepb < 0.0 && f < this.fAtInterpolationPoints.getEntry(nfm - n)) {
                    this.fAtInterpolationPoints.setEntry(nfm, this.fAtInterpolationPoints.getEntry(nfm - n));
                    this.fAtInterpolationPoints.setEntry(nfm - n, f);
                    if (this.trustRegionCenterInterpolationPointIndex == nfm) {
                        this.trustRegionCenterInterpolationPointIndex = nfm - n;
                    }
                    this.interpolationPoints.setEntry(nfm - n, nfxm, stepb);
                    this.interpolationPoints.setEntry(nfm, nfxm, stepa);
                }
                this.bMatrix.setEntry(0, nfxm, -(stepa + stepb) / (stepa * stepb));
                this.bMatrix.setEntry(nfm, nfxm, -0.5 / this.interpolationPoints.getEntry(nfm - n, nfxm));
                this.bMatrix.setEntry(nfm - n, nfxm, -this.bMatrix.getEntry(0, nfxm) - this.bMatrix.getEntry(nfm, nfxm));
                this.zMatrix.setEntry(0, nfxm, FastMath.sqrt(2.0) / (stepa * stepb));
                this.zMatrix.setEntry(nfm, nfxm, FastMath.sqrt(0.5) / rhosq);
                this.zMatrix.setEntry(nfm - n, nfxm, -this.zMatrix.getEntry(0, nfxm) - this.zMatrix.getEntry(nfm, nfxm));
                continue;
            }
            this.zMatrix.setEntry(0, nfxm, recip);
            this.zMatrix.setEntry(nfm, nfxm, recip);
            this.zMatrix.setEntry(ipt, nfxm, -recip);
            this.zMatrix.setEntry(jpt, nfxm, -recip);
            int ih = ipt * (ipt - 1) / 2 + jpt - 1;
            tmp = this.interpolationPoints.getEntry(nfm, ipt - 1) * this.interpolationPoints.getEntry(nfm, jpt - 1);
            this.modelSecondDerivativesValues.setEntry(ih, (fbeg - this.fAtInterpolationPoints.getEntry(ipt) - this.fAtInterpolationPoints.getEntry(jpt) + f) / tmp);
        } while (this.getEvaluations() < npt);
    }

    private double[] trsbox(double delta, ArrayRealVector gnew, ArrayRealVector xbdi, ArrayRealVector s, ArrayRealVector hs, ArrayRealVector hred) {
        BOBYQAOptimizer.printMethod();
        int n = this.currentBest.getDimension();
        int npt = this.numberOfInterpolationPoints;
        double dsq = Double.NaN;
        double crvmin = Double.NaN;
        double beta = 0.0;
        int iact = -1;
        int nact = 0;
        double angt = 0.0;
        double temp = 0.0;
        double xsav = 0.0;
        double xsum = 0.0;
        double angbd = 0.0;
        double dredg = 0.0;
        double sredg = 0.0;
        double resid = 0.0;
        double delsq = 0.0;
        double ggsav = 0.0;
        double tempa = 0.0;
        double tempb = 0.0;
        double redmax = 0.0;
        double dredsq = 0.0;
        double redsav = 0.0;
        double gredsq = 0.0;
        double rednew = 0.0;
        int itcsav = 0;
        double rdprev = 0.0;
        double rdnext = 0.0;
        double stplen = 0.0;
        double stepsq = 0.0;
        int itermax = 0;
        int iterc = 0;
        nact = 0;
        for (int i = 0; i < n; ++i) {
            xbdi.setEntry(i, 0.0);
            if (this.trustRegionCenterOffset.getEntry(i) <= this.lowerDifference.getEntry(i)) {
                if (this.gradientAtTrustRegionCenter.getEntry(i) >= 0.0) {
                    xbdi.setEntry(i, -1.0);
                }
            } else if (this.trustRegionCenterOffset.getEntry(i) >= this.upperDifference.getEntry(i) && this.gradientAtTrustRegionCenter.getEntry(i) <= 0.0) {
                xbdi.setEntry(i, 1.0);
            }
            if (xbdi.getEntry(i) != 0.0) {
                ++nact;
            }
            this.trialStepPoint.setEntry(i, 0.0);
            gnew.setEntry(i, this.gradientAtTrustRegionCenter.getEntry(i));
        }
        delsq = delta * delta;
        double qred = 0.0;
        crvmin = -1.0;
        int state = 20;
        block12: while (true) {
            switch (state) {
                case 20: {
                    BOBYQAOptimizer.printState(20);
                    beta = 0.0;
                }
                case 30: {
                    BOBYQAOptimizer.printState(30);
                    stepsq = 0.0;
                    for (int i = 0; i < n; ++i) {
                        if (xbdi.getEntry(i) != 0.0) {
                            s.setEntry(i, 0.0);
                        } else if (beta == 0.0) {
                            s.setEntry(i, -gnew.getEntry(i));
                        } else {
                            s.setEntry(i, beta * s.getEntry(i) - gnew.getEntry(i));
                        }
                        double d1 = s.getEntry(i);
                        stepsq += d1 * d1;
                    }
                    if (stepsq == 0.0) {
                        state = 190;
                        continue block12;
                    }
                    if (beta == 0.0) {
                        gredsq = stepsq;
                        itermax = iterc + n - nact;
                    }
                    if (gredsq * delsq <= qred * 1.0E-4 * qred) {
                        state = 190;
                        continue block12;
                    }
                    state = 210;
                    continue block12;
                }
                case 50: {
                    int i;
                    BOBYQAOptimizer.printState(50);
                    resid = delsq;
                    double ds = 0.0;
                    double shs = 0.0;
                    for (i = 0; i < n; ++i) {
                        if (xbdi.getEntry(i) != 0.0) continue;
                        double d1 = this.trialStepPoint.getEntry(i);
                        resid -= d1 * d1;
                        ds += s.getEntry(i) * this.trialStepPoint.getEntry(i);
                        shs += s.getEntry(i) * hs.getEntry(i);
                    }
                    if (resid <= 0.0) {
                        state = 90;
                        continue block12;
                    }
                    temp = FastMath.sqrt(stepsq * resid + ds * ds);
                    double blen = ds < 0.0 ? (temp - ds) / stepsq : resid / (temp + ds);
                    stplen = blen;
                    if (shs > 0.0) {
                        stplen = FastMath.min(blen, gredsq / shs);
                    }
                    iact = -1;
                    for (i = 0; i < n; ++i) {
                        if (s.getEntry(i) == 0.0) continue;
                        xsum = this.trustRegionCenterOffset.getEntry(i) + this.trialStepPoint.getEntry(i);
                        temp = s.getEntry(i) > 0.0 ? (this.upperDifference.getEntry(i) - xsum) / s.getEntry(i) : (this.lowerDifference.getEntry(i) - xsum) / s.getEntry(i);
                        if (!(temp < stplen)) continue;
                        stplen = temp;
                        iact = i;
                    }
                    double sdec = 0.0;
                    if (stplen > 0.0) {
                        ++iterc;
                        temp = shs / stepsq;
                        if (iact == -1 && temp > 0.0 && (crvmin = FastMath.min(crvmin, temp)) == -1.0) {
                            crvmin = temp;
                        }
                        ggsav = gredsq;
                        gredsq = 0.0;
                        for (i = 0; i < n; ++i) {
                            gnew.setEntry(i, gnew.getEntry(i) + stplen * hs.getEntry(i));
                            if (xbdi.getEntry(i) == 0.0) {
                                double d1 = gnew.getEntry(i);
                                gredsq += d1 * d1;
                            }
                            this.trialStepPoint.setEntry(i, this.trialStepPoint.getEntry(i) + stplen * s.getEntry(i));
                        }
                        double d1 = stplen * (ggsav - 0.5 * stplen * shs);
                        sdec = FastMath.max(d1, 0.0);
                        qred += sdec;
                    }
                    if (iact >= 0) {
                        double d1;
                        ++nact;
                        xbdi.setEntry(iact, 1.0);
                        if (s.getEntry(iact) < 0.0) {
                            xbdi.setEntry(iact, -1.0);
                        }
                        if ((delsq -= (d1 = this.trialStepPoint.getEntry(iact)) * d1) <= 0.0) {
                            state = 190;
                            continue block12;
                        }
                        state = 20;
                        continue block12;
                    }
                    if (stplen < blen) {
                        if (iterc == itermax) {
                            state = 190;
                            continue block12;
                        }
                        if (sdec <= qred * 0.01) {
                            state = 190;
                            continue block12;
                        }
                        beta = gredsq / ggsav;
                        state = 30;
                        continue block12;
                    }
                }
                case 90: {
                    BOBYQAOptimizer.printState(90);
                    crvmin = 0.0;
                }
                case 100: {
                    BOBYQAOptimizer.printState(100);
                    if (nact >= n - 1) {
                        state = 190;
                        continue block12;
                    }
                    dredsq = 0.0;
                    dredg = 0.0;
                    gredsq = 0.0;
                    for (int i = 0; i < n; ++i) {
                        if (xbdi.getEntry(i) == 0.0) {
                            double d1 = this.trialStepPoint.getEntry(i);
                            dredsq += d1 * d1;
                            dredg += this.trialStepPoint.getEntry(i) * gnew.getEntry(i);
                            d1 = gnew.getEntry(i);
                            gredsq += d1 * d1;
                            s.setEntry(i, this.trialStepPoint.getEntry(i));
                            continue;
                        }
                        s.setEntry(i, 0.0);
                    }
                    itcsav = iterc;
                    state = 210;
                    continue block12;
                }
                case 120: {
                    int i;
                    BOBYQAOptimizer.printState(120);
                    ++iterc;
                    temp = gredsq * dredsq - dredg * dredg;
                    if (temp <= qred * 1.0E-4 * qred) {
                        state = 190;
                        continue block12;
                    }
                    temp = FastMath.sqrt(temp);
                    for (i = 0; i < n; ++i) {
                        if (xbdi.getEntry(i) == 0.0) {
                            s.setEntry(i, (dredg * this.trialStepPoint.getEntry(i) - dredsq * gnew.getEntry(i)) / temp);
                            continue;
                        }
                        s.setEntry(i, 0.0);
                    }
                    sredg = -temp;
                    angbd = 1.0;
                    iact = -1;
                    for (i = 0; i < n; ++i) {
                        if (xbdi.getEntry(i) != 0.0) continue;
                        tempa = this.trustRegionCenterOffset.getEntry(i) + this.trialStepPoint.getEntry(i) - this.lowerDifference.getEntry(i);
                        tempb = this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i) - this.trialStepPoint.getEntry(i);
                        if (tempa <= 0.0) {
                            ++nact;
                            xbdi.setEntry(i, -1.0);
                            state = 100;
                            break;
                        }
                        if (tempb <= 0.0) {
                            ++nact;
                            xbdi.setEntry(i, 1.0);
                            state = 100;
                            break;
                        }
                        double d1 = this.trialStepPoint.getEntry(i);
                        double d2 = s.getEntry(i);
                        double ssq = d1 * d1 + d2 * d2;
                        temp = ssq - (d1 = this.trustRegionCenterOffset.getEntry(i) - this.lowerDifference.getEntry(i)) * d1;
                        if (temp > 0.0 && angbd * (temp = FastMath.sqrt(temp) - s.getEntry(i)) > tempa) {
                            angbd = tempa / temp;
                            iact = i;
                            xsav = -1.0;
                        }
                        if (!((temp = ssq - (d1 = this.upperDifference.getEntry(i) - this.trustRegionCenterOffset.getEntry(i)) * d1) > 0.0) || !(angbd * (temp = FastMath.sqrt(temp) + s.getEntry(i)) > tempb)) continue;
                        angbd = tempb / temp;
                        iact = i;
                        xsav = 1.0;
                    }
                    state = 210;
                    continue block12;
                }
                case 150: {
                    double sth;
                    int i;
                    BOBYQAOptimizer.printState(150);
                    double shs = 0.0;
                    double dhs = 0.0;
                    double dhd = 0.0;
                    for (i = 0; i < n; ++i) {
                        if (xbdi.getEntry(i) != 0.0) continue;
                        shs += s.getEntry(i) * hs.getEntry(i);
                        dhs += this.trialStepPoint.getEntry(i) * hs.getEntry(i);
                        dhd += this.trialStepPoint.getEntry(i) * hred.getEntry(i);
                    }
                    redmax = 0.0;
                    int isav = -1;
                    redsav = 0.0;
                    int iu = (int)(angbd * 17.0 + 3.1);
                    for (i = 0; i < iu; ++i) {
                        angt = angbd * (double)i / (double)iu;
                        sth = (angt + angt) / (1.0 + angt * angt);
                        temp = shs + angt * (angt * dhd - dhs - dhs);
                        rednew = sth * (angt * dredg - sredg - 0.5 * sth * temp);
                        if (rednew > redmax) {
                            redmax = rednew;
                            isav = i;
                            rdprev = redsav;
                        } else if (i == isav + 1) {
                            rdnext = rednew;
                        }
                        redsav = rednew;
                    }
                    if (isav < 0) {
                        state = 190;
                        continue block12;
                    }
                    if (isav < iu) {
                        temp = (rdnext - rdprev) / (redmax + redmax - rdprev - rdnext);
                        angt = angbd * ((double)isav + 0.5 * temp) / (double)iu;
                    }
                    double cth = (1.0 - angt * angt) / (1.0 + angt * angt);
                    sth = (angt + angt) / (1.0 + angt * angt);
                    temp = shs + angt * (angt * dhd - dhs - dhs);
                    double sdec = sth * (angt * dredg - sredg - 0.5 * sth * temp);
                    if (sdec <= 0.0) {
                        state = 190;
                        continue block12;
                    }
                    dredg = 0.0;
                    gredsq = 0.0;
                    for (i = 0; i < n; ++i) {
                        gnew.setEntry(i, gnew.getEntry(i) + (cth - 1.0) * hred.getEntry(i) + sth * hs.getEntry(i));
                        if (xbdi.getEntry(i) == 0.0) {
                            this.trialStepPoint.setEntry(i, cth * this.trialStepPoint.getEntry(i) + sth * s.getEntry(i));
                            dredg += this.trialStepPoint.getEntry(i) * gnew.getEntry(i);
                            double d1 = gnew.getEntry(i);
                            gredsq += d1 * d1;
                        }
                        hred.setEntry(i, cth * hred.getEntry(i) + sth * hs.getEntry(i));
                    }
                    qred += sdec;
                    if (iact >= 0 && isav == iu) {
                        ++nact;
                        xbdi.setEntry(iact, xsav);
                        state = 100;
                        continue block12;
                    }
                    if (sdec > qred * 0.01) {
                        state = 120;
                        continue block12;
                    }
                }
                case 190: {
                    BOBYQAOptimizer.printState(190);
                    dsq = 0.0;
                    for (int i = 0; i < n; ++i) {
                        double min = FastMath.min(this.trustRegionCenterOffset.getEntry(i) + this.trialStepPoint.getEntry(i), this.upperDifference.getEntry(i));
                        this.newPoint.setEntry(i, FastMath.max(min, this.lowerDifference.getEntry(i)));
                        if (xbdi.getEntry(i) == -1.0) {
                            this.newPoint.setEntry(i, this.lowerDifference.getEntry(i));
                        }
                        if (xbdi.getEntry(i) == 1.0) {
                            this.newPoint.setEntry(i, this.upperDifference.getEntry(i));
                        }
                        this.trialStepPoint.setEntry(i, this.newPoint.getEntry(i) - this.trustRegionCenterOffset.getEntry(i));
                        double d1 = this.trialStepPoint.getEntry(i);
                        dsq += d1 * d1;
                    }
                    return new double[]{dsq, crvmin};
                }
                case 210: {
                    int i;
                    BOBYQAOptimizer.printState(210);
                    int ih = 0;
                    for (int j = 0; j < n; ++j) {
                        hs.setEntry(j, 0.0);
                        for (i = 0; i <= j; ++i) {
                            if (i < j) {
                                hs.setEntry(j, hs.getEntry(j) + this.modelSecondDerivativesValues.getEntry(ih) * s.getEntry(i));
                            }
                            hs.setEntry(i, hs.getEntry(i) + this.modelSecondDerivativesValues.getEntry(ih) * s.getEntry(j));
                            ++ih;
                        }
                    }
                    RealVector tmp = this.interpolationPoints.operate(s).ebeMultiply(this.modelSecondDerivativesParameters);
                    for (int k = 0; k < npt; ++k) {
                        if (this.modelSecondDerivativesParameters.getEntry(k) == 0.0) continue;
                        for (int i2 = 0; i2 < n; ++i2) {
                            hs.setEntry(i2, hs.getEntry(i2) + tmp.getEntry(k) * this.interpolationPoints.getEntry(k, i2));
                        }
                    }
                    if (crvmin != 0.0) {
                        state = 50;
                        continue block12;
                    }
                    if (iterc > itcsav) {
                        state = 150;
                        continue block12;
                    }
                    for (i = 0; i < n; ++i) {
                        hred.setEntry(i, hs.getEntry(i));
                    }
                    state = 120;
                    continue block12;
                }
            }
            break;
        }
        throw new MathIllegalStateException(LocalizedFormats.SIMPLE_MESSAGE, "trsbox");
    }

    private void update(double beta, double denom, int knew) {
        BOBYQAOptimizer.printMethod();
        int n = this.currentBest.getDimension();
        int npt = this.numberOfInterpolationPoints;
        int nptm = npt - n - 1;
        ArrayRealVector work = new ArrayRealVector(npt + n);
        double ztest = 0.0;
        for (int k = 0; k < npt; ++k) {
            for (int j = 0; j < nptm; ++j) {
                ztest = FastMath.max(ztest, FastMath.abs(this.zMatrix.getEntry(k, j)));
            }
        }
        ztest *= 1.0E-20;
        for (int j = 1; j < nptm; ++j) {
            double d1 = this.zMatrix.getEntry(knew, j);
            if (FastMath.abs(d1) > ztest) {
                double d2 = this.zMatrix.getEntry(knew, 0);
                double d3 = this.zMatrix.getEntry(knew, j);
                double d4 = FastMath.sqrt(d2 * d2 + d3 * d3);
                double d5 = this.zMatrix.getEntry(knew, 0) / d4;
                double d6 = this.zMatrix.getEntry(knew, j) / d4;
                for (int i = 0; i < npt; ++i) {
                    double d7 = d5 * this.zMatrix.getEntry(i, 0) + d6 * this.zMatrix.getEntry(i, j);
                    this.zMatrix.setEntry(i, j, d5 * this.zMatrix.getEntry(i, j) - d6 * this.zMatrix.getEntry(i, 0));
                    this.zMatrix.setEntry(i, 0, d7);
                }
            }
            this.zMatrix.setEntry(knew, j, 0.0);
        }
        for (int i = 0; i < npt; ++i) {
            work.setEntry(i, this.zMatrix.getEntry(knew, 0) * this.zMatrix.getEntry(i, 0));
        }
        double alpha = work.getEntry(knew);
        double tau = this.lagrangeValuesAtNewPoint.getEntry(knew);
        this.lagrangeValuesAtNewPoint.setEntry(knew, this.lagrangeValuesAtNewPoint.getEntry(knew) - 1.0);
        double sqrtDenom = FastMath.sqrt(denom);
        double d1 = tau / sqrtDenom;
        double d2 = this.zMatrix.getEntry(knew, 0) / sqrtDenom;
        for (int i = 0; i < npt; ++i) {
            this.zMatrix.setEntry(i, 0, d1 * this.zMatrix.getEntry(i, 0) - d2 * this.lagrangeValuesAtNewPoint.getEntry(i));
        }
        for (int j = 0; j < n; ++j) {
            int jp = npt + j;
            work.setEntry(jp, this.bMatrix.getEntry(knew, j));
            double d3 = (alpha * this.lagrangeValuesAtNewPoint.getEntry(jp) - tau * work.getEntry(jp)) / denom;
            double d4 = (-beta * work.getEntry(jp) - tau * this.lagrangeValuesAtNewPoint.getEntry(jp)) / denom;
            for (int i = 0; i <= jp; ++i) {
                this.bMatrix.setEntry(i, j, this.bMatrix.getEntry(i, j) + d3 * this.lagrangeValuesAtNewPoint.getEntry(i) + d4 * work.getEntry(i));
                if (i < npt) continue;
                this.bMatrix.setEntry(jp, i - npt, this.bMatrix.getEntry(i, j));
            }
        }
    }

    private void setup(double[] lowerBound, double[] upperBound) {
        BOBYQAOptimizer.printMethod();
        double[] init = this.getStartPoint();
        int dimension = init.length;
        if (dimension < 2) {
            throw new NumberIsTooSmallException(dimension, (Number)2, true);
        }
        int[] nPointsInterval = new int[]{dimension + 2, (dimension + 2) * (dimension + 1) / 2};
        if (this.numberOfInterpolationPoints < nPointsInterval[0] || this.numberOfInterpolationPoints > nPointsInterval[1]) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.NUMBER_OF_INTERPOLATION_POINTS, (Number)this.numberOfInterpolationPoints, nPointsInterval[0], nPointsInterval[1]);
        }
        this.boundDifference = new double[dimension];
        double requiredMinDiff = 2.0 * this.initialTrustRegionRadius;
        double minDiff = Double.POSITIVE_INFINITY;
        for (int i = 0; i < dimension; ++i) {
            this.boundDifference[i] = upperBound[i] - lowerBound[i];
            minDiff = FastMath.min(minDiff, this.boundDifference[i]);
        }
        if (minDiff < requiredMinDiff) {
            this.initialTrustRegionRadius = minDiff / 3.0;
        }
        this.bMatrix = new Array2DRowRealMatrix(dimension + this.numberOfInterpolationPoints, dimension);
        this.zMatrix = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, this.numberOfInterpolationPoints - dimension - 1);
        this.interpolationPoints = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, dimension);
        this.originShift = new ArrayRealVector(dimension);
        this.fAtInterpolationPoints = new ArrayRealVector(this.numberOfInterpolationPoints);
        this.trustRegionCenterOffset = new ArrayRealVector(dimension);
        this.gradientAtTrustRegionCenter = new ArrayRealVector(dimension);
        this.lowerDifference = new ArrayRealVector(dimension);
        this.upperDifference = new ArrayRealVector(dimension);
        this.modelSecondDerivativesParameters = new ArrayRealVector(this.numberOfInterpolationPoints);
        this.newPoint = new ArrayRealVector(dimension);
        this.alternativeNewPoint = new ArrayRealVector(dimension);
        this.trialStepPoint = new ArrayRealVector(dimension);
        this.lagrangeValuesAtNewPoint = new ArrayRealVector(dimension + this.numberOfInterpolationPoints);
        this.modelSecondDerivativesValues = new ArrayRealVector(dimension * (dimension + 1) / 2);
    }

    private static String caller(int n) {
        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();
        StackTraceElement e = elements[n];
        return e.getMethodName() + " (at line " + e.getLineNumber() + ")";
    }

    private static void printState(int s) {
    }

    private static void printMethod() {
    }

    private static class PathIsExploredException
    extends RuntimeException {
        private static final long serialVersionUID = 745350979634801853L;
        private static final String PATH_IS_EXPLORED = "If this exception is thrown, just remove it from the code";

        PathIsExploredException() {
            super("If this exception is thrown, just remove it from the code " + BOBYQAOptimizer.caller(3));
        }
    }
}

