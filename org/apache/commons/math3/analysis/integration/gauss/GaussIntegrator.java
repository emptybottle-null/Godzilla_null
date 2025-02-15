/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class GaussIntegrator {
    private final double[] points;
    private final double[] weights;

    public GaussIntegrator(double[] points, double[] weights) throws NonMonotonicSequenceException, DimensionMismatchException {
        if (points.length != weights.length) {
            throw new DimensionMismatchException(points.length, weights.length);
        }
        MathArrays.checkOrder(points, MathArrays.OrderDirection.INCREASING, true, true);
        this.points = (double[])points.clone();
        this.weights = (double[])weights.clone();
    }

    public GaussIntegrator(Pair<double[], double[]> pointsAndWeights) throws NonMonotonicSequenceException {
        this(pointsAndWeights.getFirst(), pointsAndWeights.getSecond());
    }

    public double integrate(UnivariateFunction f) {
        double s = 0.0;
        double c = 0.0;
        for (int i = 0; i < this.points.length; ++i) {
            double x = this.points[i];
            double w = this.weights[i];
            double y = w * f.value(x) - c;
            double t = s + y;
            c = t - s - y;
            s = t;
        }
        return s;
    }

    public int getNumberOfPoints() {
        return this.points.length;
    }

    public double getPoint(int index) {
        return this.points[index];
    }

    public double getWeight(int index) {
        return this.weights[index];
    }
}

