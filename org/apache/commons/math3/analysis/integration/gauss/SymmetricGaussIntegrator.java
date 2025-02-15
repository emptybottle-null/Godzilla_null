/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SymmetricGaussIntegrator
extends GaussIntegrator {
    public SymmetricGaussIntegrator(double[] points, double[] weights) throws NonMonotonicSequenceException, DimensionMismatchException {
        super(points, weights);
    }

    public SymmetricGaussIntegrator(Pair<double[], double[]> pointsAndWeights) throws NonMonotonicSequenceException {
        this(pointsAndWeights.getFirst(), pointsAndWeights.getSecond());
    }

    @Override
    public double integrate(UnivariateFunction f) {
        int ruleLength = this.getNumberOfPoints();
        if (ruleLength == 1) {
            return this.getWeight(0) * f.value(0.0);
        }
        int iMax = ruleLength / 2;
        double s = 0.0;
        double c = 0.0;
        for (int i = 0; i < iMax; ++i) {
            double p = this.getPoint(i);
            double w = this.getWeight(i);
            double f1 = f.value(p);
            double f2 = f.value(-p);
            double y = w * (f1 + f2) - c;
            double t = s + y;
            c = t - s - y;
            s = t;
        }
        if (ruleLength % 2 != 0) {
            double t;
            double w = this.getWeight(iMax);
            double y = w * f.value(0.0) - c;
            s = t = s + y;
        }
        return s;
    }
}

