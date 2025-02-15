/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.direct;

import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.direct.AbstractSimplex;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class NelderMeadSimplex
extends AbstractSimplex {
    private static final double DEFAULT_RHO = 1.0;
    private static final double DEFAULT_KHI = 2.0;
    private static final double DEFAULT_GAMMA = 0.5;
    private static final double DEFAULT_SIGMA = 0.5;
    private final double rho;
    private final double khi;
    private final double gamma;
    private final double sigma;

    public NelderMeadSimplex(int n) {
        this(n, 1.0);
    }

    public NelderMeadSimplex(int n, double sideLength) {
        this(n, sideLength, 1.0, 2.0, 0.5, 0.5);
    }

    public NelderMeadSimplex(int n, double sideLength, double rho, double khi, double gamma, double sigma) {
        super(n, sideLength);
        this.rho = rho;
        this.khi = khi;
        this.gamma = gamma;
        this.sigma = sigma;
    }

    public NelderMeadSimplex(int n, double rho, double khi, double gamma, double sigma) {
        this(n, 1.0, rho, khi, gamma, sigma);
    }

    public NelderMeadSimplex(double[] steps) {
        this(steps, 1.0, 2.0, 0.5, 0.5);
    }

    public NelderMeadSimplex(double[] steps, double rho, double khi, double gamma, double sigma) {
        super(steps);
        this.rho = rho;
        this.khi = khi;
        this.gamma = gamma;
        this.sigma = sigma;
    }

    public NelderMeadSimplex(double[][] referenceSimplex) {
        this(referenceSimplex, 1.0, 2.0, 0.5, 0.5);
    }

    public NelderMeadSimplex(double[][] referenceSimplex, double rho, double khi, double gamma, double sigma) {
        super(referenceSimplex);
        this.rho = rho;
        this.khi = khi;
        this.gamma = gamma;
        this.sigma = sigma;
    }

    @Override
    public void iterate(MultivariateFunction evaluationFunction, Comparator<PointValuePair> comparator) {
        int j;
        int n = this.getDimension();
        PointValuePair best = this.getPoint(0);
        PointValuePair secondBest = this.getPoint(n - 1);
        PointValuePair worst = this.getPoint(n);
        double[] xWorst = worst.getPointRef();
        double[] centroid = new double[n];
        for (int i = 0; i < n; ++i) {
            double[] x = this.getPoint(i).getPointRef();
            for (j = 0; j < n; ++j) {
                int n2 = j;
                centroid[n2] = centroid[n2] + x[j];
            }
        }
        double scaling = 1.0 / (double)n;
        j = 0;
        while (j < n) {
            int n3 = j++;
            centroid[n3] = centroid[n3] * scaling;
        }
        double[] xR = new double[n];
        for (int j2 = 0; j2 < n; ++j2) {
            xR[j2] = centroid[j2] + this.rho * (centroid[j2] - xWorst[j2]);
        }
        PointValuePair reflected = new PointValuePair(xR, evaluationFunction.value(xR), false);
        if (comparator.compare(best, reflected) <= 0 && comparator.compare(reflected, secondBest) < 0) {
            this.replaceWorstPoint(reflected, comparator);
        } else if (comparator.compare(reflected, best) < 0) {
            double[] xE = new double[n];
            for (int j3 = 0; j3 < n; ++j3) {
                xE[j3] = centroid[j3] + this.khi * (xR[j3] - centroid[j3]);
            }
            PointValuePair expanded = new PointValuePair(xE, evaluationFunction.value(xE), false);
            if (comparator.compare(expanded, reflected) < 0) {
                this.replaceWorstPoint(expanded, comparator);
            } else {
                this.replaceWorstPoint(reflected, comparator);
            }
        } else {
            double[] xC;
            if (comparator.compare(reflected, worst) < 0) {
                xC = new double[n];
                for (int j4 = 0; j4 < n; ++j4) {
                    xC[j4] = centroid[j4] + this.gamma * (xR[j4] - centroid[j4]);
                }
                PointValuePair outContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
                if (comparator.compare(outContracted, reflected) <= 0) {
                    this.replaceWorstPoint(outContracted, comparator);
                    return;
                }
            } else {
                xC = new double[n];
                for (int j5 = 0; j5 < n; ++j5) {
                    xC[j5] = centroid[j5] - this.gamma * (centroid[j5] - xWorst[j5]);
                }
                PointValuePair inContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
                if (comparator.compare(inContracted, worst) < 0) {
                    this.replaceWorstPoint(inContracted, comparator);
                    return;
                }
            }
            double[] xSmallest = this.getPoint(0).getPointRef();
            for (int i = 1; i <= n; ++i) {
                double[] x = this.getPoint(i).getPoint();
                for (int j6 = 0; j6 < n; ++j6) {
                    x[j6] = xSmallest[j6] + this.sigma * (x[j6] - xSmallest[j6]);
                }
                this.setPoint(i, new PointValuePair(x, Double.NaN, false));
            }
            this.evaluate(evaluationFunction, comparator);
        }
    }
}

