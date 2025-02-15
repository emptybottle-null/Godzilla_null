/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.analysis.function.HarmonicOscillator;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.AbstractCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class HarmonicCurveFitter
extends AbstractCurveFitter {
    private static final HarmonicOscillator.Parametric FUNCTION = new HarmonicOscillator.Parametric();
    private final double[] initialGuess;
    private final int maxIter;

    private HarmonicCurveFitter(double[] initialGuess, int maxIter) {
        this.initialGuess = initialGuess;
        this.maxIter = maxIter;
    }

    public static HarmonicCurveFitter create() {
        return new HarmonicCurveFitter(null, Integer.MAX_VALUE);
    }

    public HarmonicCurveFitter withStartPoint(double[] newStart) {
        return new HarmonicCurveFitter((double[])newStart.clone(), this.maxIter);
    }

    public HarmonicCurveFitter withMaxIterations(int newMaxIter) {
        return new HarmonicCurveFitter(this.initialGuess, newMaxIter);
    }

    @Override
    protected LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> observations) {
        int len = observations.size();
        double[] target = new double[len];
        double[] weights = new double[len];
        int i = 0;
        for (WeightedObservedPoint obs : observations) {
            target[i] = obs.getY();
            weights[i] = obs.getWeight();
            ++i;
        }
        AbstractCurveFitter.TheoreticalValuesFunction model = new AbstractCurveFitter.TheoreticalValuesFunction(FUNCTION, observations);
        double[] startPoint = this.initialGuess != null ? this.initialGuess : new ParameterGuesser(observations).guess();
        return new LeastSquaresBuilder().maxEvaluations(Integer.MAX_VALUE).maxIterations(this.maxIter).start(startPoint).target(target).weight(new DiagonalMatrix(weights)).model(model.getModelFunction(), model.getModelFunctionJacobian()).build();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class ParameterGuesser {
        private final double a;
        private final double omega;
        private final double phi;

        public ParameterGuesser(Collection<WeightedObservedPoint> observations) {
            if (observations.size() < 4) {
                throw new NumberIsTooSmallException((Localizable)LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, (Number)observations.size(), 4, true);
            }
            WeightedObservedPoint[] sorted = this.sortObservations(observations).toArray(new WeightedObservedPoint[0]);
            double[] aOmega = this.guessAOmega(sorted);
            this.a = aOmega[0];
            this.omega = aOmega[1];
            this.phi = this.guessPhi(sorted);
        }

        public double[] guess() {
            return new double[]{this.a, this.omega, this.phi};
        }

        private List<WeightedObservedPoint> sortObservations(Collection<WeightedObservedPoint> unsorted) {
            ArrayList<WeightedObservedPoint> observations = new ArrayList<WeightedObservedPoint>(unsorted);
            WeightedObservedPoint curr = (WeightedObservedPoint)observations.get(0);
            int len = observations.size();
            for (int j = 1; j < len; ++j) {
                WeightedObservedPoint prec = curr;
                curr = (WeightedObservedPoint)observations.get(j);
                if (!(curr.getX() < prec.getX())) continue;
                int i = j - 1;
                WeightedObservedPoint mI = (WeightedObservedPoint)observations.get(i);
                while (i >= 0 && curr.getX() < mI.getX()) {
                    observations.set(i + 1, mI);
                    if (i-- == 0) continue;
                    mI = (WeightedObservedPoint)observations.get(i);
                }
                observations.set(i + 1, curr);
                curr = (WeightedObservedPoint)observations.get(j);
            }
            return observations;
        }

        private double[] guessAOmega(WeightedObservedPoint[] observations) {
            double[] aOmega = new double[2];
            double sx2 = 0.0;
            double sy2 = 0.0;
            double sxy = 0.0;
            double sxz = 0.0;
            double syz = 0.0;
            double currentX = observations[0].getX();
            double currentY = observations[0].getY();
            double f2Integral = 0.0;
            double fPrime2Integral = 0.0;
            double startX = currentX;
            for (int i = 1; i < observations.length; ++i) {
                double previousX = currentX;
                double previousY = currentY;
                currentX = observations[i].getX();
                currentY = observations[i].getY();
                double dx = currentX - previousX;
                double dy = currentY - previousY;
                double f2StepIntegral = dx * (previousY * previousY + previousY * currentY + currentY * currentY) / 3.0;
                double fPrime2StepIntegral = dy * dy / dx;
                double x = currentX - startX;
                sx2 += x * x;
                sy2 += (f2Integral += f2StepIntegral) * f2Integral;
                sxy += x * f2Integral;
                sxz += x * (fPrime2Integral += fPrime2StepIntegral);
                syz += f2Integral * fPrime2Integral;
            }
            double c1 = sy2 * sxz - sxy * syz;
            double c2 = sxy * sxz - sx2 * syz;
            double c3 = sx2 * sy2 - sxy * sxy;
            if (c1 / c2 < 0.0 || c2 / c3 < 0.0) {
                int last = observations.length - 1;
                double xRange = observations[last].getX() - observations[0].getX();
                if (xRange == 0.0) {
                    throw new ZeroException();
                }
                aOmega[1] = Math.PI * 2 / xRange;
                double yMin = Double.POSITIVE_INFINITY;
                double yMax = Double.NEGATIVE_INFINITY;
                for (int i = 1; i < observations.length; ++i) {
                    double y = observations[i].getY();
                    if (y < yMin) {
                        yMin = y;
                    }
                    if (!(y > yMax)) continue;
                    yMax = y;
                }
                aOmega[0] = 0.5 * (yMax - yMin);
            } else {
                if (c2 == 0.0) {
                    throw new MathIllegalStateException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
                }
                aOmega[0] = FastMath.sqrt(c1 / c2);
                aOmega[1] = FastMath.sqrt(c2 / c3);
            }
            return aOmega;
        }

        private double guessPhi(WeightedObservedPoint[] observations) {
            double fcMean = 0.0;
            double fsMean = 0.0;
            double currentX = observations[0].getX();
            double currentY = observations[0].getY();
            for (int i = 1; i < observations.length; ++i) {
                double previousX = currentX;
                double previousY = currentY;
                currentX = observations[i].getX();
                currentY = observations[i].getY();
                double currentYPrime = (currentY - previousY) / (currentX - previousX);
                double omegaX = this.omega * currentX;
                double cosine = FastMath.cos(omegaX);
                double sine = FastMath.sin(omegaX);
                fcMean += this.omega * currentY * cosine - currentYPrime * sine;
                fsMean += this.omega * currentY * sine + currentYPrime * cosine;
            }
            return FastMath.atan2(-fsMean, fcMean);
        }
    }
}

