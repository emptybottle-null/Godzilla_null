/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.interpolation;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class LoessInterpolator
implements UnivariateInterpolator,
Serializable {
    public static final double DEFAULT_BANDWIDTH = 0.3;
    public static final int DEFAULT_ROBUSTNESS_ITERS = 2;
    public static final double DEFAULT_ACCURACY = 1.0E-12;
    private static final long serialVersionUID = 5204927143605193821L;
    private final double bandwidth;
    private final int robustnessIters;
    private final double accuracy;

    public LoessInterpolator() {
        this.bandwidth = 0.3;
        this.robustnessIters = 2;
        this.accuracy = 1.0E-12;
    }

    public LoessInterpolator(double bandwidth, int robustnessIters) {
        this(bandwidth, robustnessIters, 1.0E-12);
    }

    public LoessInterpolator(double bandwidth, int robustnessIters, double accuracy) throws OutOfRangeException, NotPositiveException {
        if (bandwidth < 0.0 || bandwidth > 1.0) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.BANDWIDTH, (Number)bandwidth, 0, 1);
        }
        this.bandwidth = bandwidth;
        if (robustnessIters < 0) {
            throw new NotPositiveException((Localizable)LocalizedFormats.ROBUSTNESS_ITERATIONS, robustnessIters);
        }
        this.robustnessIters = robustnessIters;
        this.accuracy = accuracy;
    }

    public final PolynomialSplineFunction interpolate(double[] xval, double[] yval) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        return new SplineInterpolator().interpolate(xval, this.smooth(xval, yval));
    }

    public final double[] smooth(double[] xval, double[] yval, double[] weights) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        if (xval.length != yval.length) {
            throw new DimensionMismatchException(xval.length, yval.length);
        }
        int n = xval.length;
        if (n == 0) {
            throw new NoDataException();
        }
        LoessInterpolator.checkAllFiniteReal(xval);
        LoessInterpolator.checkAllFiniteReal(yval);
        LoessInterpolator.checkAllFiniteReal(weights);
        MathArrays.checkOrder(xval);
        if (n == 1) {
            return new double[]{yval[0]};
        }
        if (n == 2) {
            return new double[]{yval[0], yval[1]};
        }
        int bandwidthInPoints = (int)(this.bandwidth * (double)n);
        if (bandwidthInPoints < 2) {
            throw new NumberIsTooSmallException((Localizable)LocalizedFormats.BANDWIDTH, (Number)bandwidthInPoints, 2, true);
        }
        double[] res = new double[n];
        double[] residuals = new double[n];
        double[] sortedResiduals = new double[n];
        double[] robustnessWeights = new double[n];
        Arrays.fill(robustnessWeights, 1.0);
        for (int iter = 0; iter <= this.robustnessIters; ++iter) {
            int[] bandwidthInterval = new int[]{0, bandwidthInPoints - 1};
            for (int i = 0; i < n; ++i) {
                int iright;
                int ileft;
                double x = xval[i];
                if (i > 0) {
                    LoessInterpolator.updateBandwidthInterval(xval, weights, i, bandwidthInterval);
                }
                int edge = xval[i] - xval[ileft = bandwidthInterval[0]] > xval[iright = bandwidthInterval[1]] - xval[i] ? ileft : iright;
                double sumWeights = 0.0;
                double sumX = 0.0;
                double sumXSquared = 0.0;
                double sumY = 0.0;
                double sumXY = 0.0;
                double denom = FastMath.abs(1.0 / (xval[edge] - x));
                for (int k = ileft; k <= iright; ++k) {
                    double xk = xval[k];
                    double yk = yval[k];
                    double dist = k < i ? x - xk : xk - x;
                    double w = LoessInterpolator.tricube(dist * denom) * robustnessWeights[k] * weights[k];
                    double xkw = xk * w;
                    sumWeights += w;
                    sumX += xkw;
                    sumXSquared += xk * xkw;
                    sumY += yk * w;
                    sumXY += yk * xkw;
                }
                double meanX = sumX / sumWeights;
                double meanY = sumY / sumWeights;
                double meanXY = sumXY / sumWeights;
                double meanXSquared = sumXSquared / sumWeights;
                double beta = FastMath.sqrt(FastMath.abs(meanXSquared - meanX * meanX)) < this.accuracy ? 0.0 : (meanXY - meanX * meanY) / (meanXSquared - meanX * meanX);
                double alpha = meanY - beta * meanX;
                res[i] = beta * x + alpha;
                residuals[i] = FastMath.abs(yval[i] - res[i]);
            }
            if (iter == this.robustnessIters) break;
            System.arraycopy(residuals, 0, sortedResiduals, 0, n);
            Arrays.sort(sortedResiduals);
            double medianResidual = sortedResiduals[n / 2];
            if (FastMath.abs(medianResidual) < this.accuracy) break;
            for (int i = 0; i < n; ++i) {
                double arg = residuals[i] / (6.0 * medianResidual);
                if (arg >= 1.0) {
                    robustnessWeights[i] = 0.0;
                    continue;
                }
                double w = 1.0 - arg * arg;
                robustnessWeights[i] = w * w;
            }
        }
        return res;
    }

    public final double[] smooth(double[] xval, double[] yval) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        if (xval.length != yval.length) {
            throw new DimensionMismatchException(xval.length, yval.length);
        }
        double[] unitWeights = new double[xval.length];
        Arrays.fill(unitWeights, 1.0);
        return this.smooth(xval, yval, unitWeights);
    }

    private static void updateBandwidthInterval(double[] xval, double[] weights, int i, int[] bandwidthInterval) {
        int left = bandwidthInterval[0];
        int right = bandwidthInterval[1];
        int nextRight = LoessInterpolator.nextNonzero(weights, right);
        if (nextRight < xval.length && xval[nextRight] - xval[i] < xval[i] - xval[left]) {
            int nextLeft;
            bandwidthInterval[0] = nextLeft = LoessInterpolator.nextNonzero(weights, bandwidthInterval[0]);
            bandwidthInterval[1] = nextRight;
        }
    }

    private static int nextNonzero(double[] weights, int i) {
        int j;
        for (j = i + 1; j < weights.length && weights[j] == 0.0; ++j) {
        }
        return j;
    }

    private static double tricube(double x) {
        double absX = FastMath.abs(x);
        if (absX >= 1.0) {
            return 0.0;
        }
        double tmp = 1.0 - absX * absX * absX;
        return tmp * tmp * tmp;
    }

    private static void checkAllFiniteReal(double[] values) {
        for (int i = 0; i < values.length; ++i) {
            MathUtils.checkFinite(values[i]);
        }
    }
}

