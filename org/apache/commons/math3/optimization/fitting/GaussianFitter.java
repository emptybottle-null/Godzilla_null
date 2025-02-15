/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.fitting;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.optimization.fitting.CurveFitter;
import org.apache.commons.math3.optimization.fitting.WeightedObservedPoint;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class GaussianFitter
extends CurveFitter<Gaussian.Parametric> {
    public GaussianFitter(DifferentiableMultivariateVectorOptimizer optimizer) {
        super(optimizer);
    }

    public double[] fit(double[] initialGuess) {
        Gaussian.Parametric f = new Gaussian.Parametric(){

            public double value(double x, double ... p) {
                double v = Double.POSITIVE_INFINITY;
                try {
                    v = super.value(x, p);
                } catch (NotStrictlyPositiveException notStrictlyPositiveException) {
                    // empty catch block
                }
                return v;
            }

            public double[] gradient(double x, double ... p) {
                double[] v = new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
                try {
                    v = super.gradient(x, p);
                } catch (NotStrictlyPositiveException notStrictlyPositiveException) {
                    // empty catch block
                }
                return v;
            }
        };
        return this.fit(f, initialGuess);
    }

    public double[] fit() {
        double[] guess = new ParameterGuesser(this.getObservations()).guess();
        return this.fit(guess);
    }

    public static class ParameterGuesser {
        private final double norm;
        private final double mean;
        private final double sigma;

        public ParameterGuesser(WeightedObservedPoint[] observations) {
            if (observations == null) {
                throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
            }
            if (observations.length < 3) {
                throw new NumberIsTooSmallException(observations.length, (Number)3, true);
            }
            WeightedObservedPoint[] sorted = this.sortObservations(observations);
            double[] params = this.basicGuess(sorted);
            this.norm = params[0];
            this.mean = params[1];
            this.sigma = params[2];
        }

        public double[] guess() {
            return new double[]{this.norm, this.mean, this.sigma};
        }

        private WeightedObservedPoint[] sortObservations(WeightedObservedPoint[] unsorted) {
            WeightedObservedPoint[] observations = (WeightedObservedPoint[])unsorted.clone();
            Comparator<WeightedObservedPoint> cmp = new Comparator<WeightedObservedPoint>(){

                @Override
                public int compare(WeightedObservedPoint p1, WeightedObservedPoint p2) {
                    if (p1 == null && p2 == null) {
                        return 0;
                    }
                    if (p1 == null) {
                        return -1;
                    }
                    if (p2 == null) {
                        return 1;
                    }
                    int cmpX = Double.compare(p1.getX(), p2.getX());
                    if (cmpX < 0) {
                        return -1;
                    }
                    if (cmpX > 0) {
                        return 1;
                    }
                    int cmpY = Double.compare(p1.getY(), p2.getY());
                    if (cmpY < 0) {
                        return -1;
                    }
                    if (cmpY > 0) {
                        return 1;
                    }
                    int cmpW = Double.compare(p1.getWeight(), p2.getWeight());
                    if (cmpW < 0) {
                        return -1;
                    }
                    if (cmpW > 0) {
                        return 1;
                    }
                    return 0;
                }
            };
            Arrays.sort(observations, cmp);
            return observations;
        }

        private double[] basicGuess(WeightedObservedPoint[] points) {
            double fwhmApprox;
            int maxYIdx = this.findMaxY(points);
            double n = points[maxYIdx].getY();
            double m = points[maxYIdx].getX();
            try {
                double halfY = n + (m - n) / 2.0;
                double fwhmX1 = this.interpolateXAtY(points, maxYIdx, -1, halfY);
                double fwhmX2 = this.interpolateXAtY(points, maxYIdx, 1, halfY);
                fwhmApprox = fwhmX2 - fwhmX1;
            } catch (OutOfRangeException e) {
                fwhmApprox = points[points.length - 1].getX() - points[0].getX();
            }
            double s = fwhmApprox / (2.0 * FastMath.sqrt(2.0 * FastMath.log(2.0)));
            return new double[]{n, m, s};
        }

        private int findMaxY(WeightedObservedPoint[] points) {
            int maxYIdx = 0;
            for (int i = 1; i < points.length; ++i) {
                if (!(points[i].getY() > points[maxYIdx].getY())) continue;
                maxYIdx = i;
            }
            return maxYIdx;
        }

        private double interpolateXAtY(WeightedObservedPoint[] points, int startIdx, int idxStep, double y) throws OutOfRangeException {
            if (idxStep == 0) {
                throw new ZeroException();
            }
            WeightedObservedPoint[] twoPoints = this.getInterpolationPointsForY(points, startIdx, idxStep, y);
            WeightedObservedPoint p1 = twoPoints[0];
            WeightedObservedPoint p2 = twoPoints[1];
            if (p1.getY() == y) {
                return p1.getX();
            }
            if (p2.getY() == y) {
                return p2.getX();
            }
            return p1.getX() + (y - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY());
        }

        private WeightedObservedPoint[] getInterpolationPointsForY(WeightedObservedPoint[] points, int startIdx, int idxStep, double y) throws OutOfRangeException {
            if (idxStep == 0) {
                throw new ZeroException();
            }
            int i = startIdx;
            while (idxStep < 0 ? i + idxStep >= 0 : i + idxStep < points.length) {
                WeightedObservedPoint p1 = points[i];
                WeightedObservedPoint p2 = points[i + idxStep];
                if (this.isBetween(y, p1.getY(), p2.getY())) {
                    if (idxStep < 0) {
                        return new WeightedObservedPoint[]{p2, p1};
                    }
                    return new WeightedObservedPoint[]{p1, p2};
                }
                i += idxStep;
            }
            throw new OutOfRangeException(y, (Number)Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        private boolean isBetween(double value, double boundary1, double boundary2) {
            return value >= boundary1 && value <= boundary2 || value >= boundary2 && value <= boundary1;
        }
    }
}

