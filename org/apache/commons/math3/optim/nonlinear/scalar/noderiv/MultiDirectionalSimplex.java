/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MultiDirectionalSimplex
extends AbstractSimplex {
    private static final double DEFAULT_KHI = 2.0;
    private static final double DEFAULT_GAMMA = 0.5;
    private final double khi;
    private final double gamma;

    public MultiDirectionalSimplex(int n) {
        this(n, 1.0);
    }

    public MultiDirectionalSimplex(int n, double sideLength) {
        this(n, sideLength, 2.0, 0.5);
    }

    public MultiDirectionalSimplex(int n, double khi, double gamma) {
        this(n, 1.0, khi, gamma);
    }

    public MultiDirectionalSimplex(int n, double sideLength, double khi, double gamma) {
        super(n, sideLength);
        this.khi = khi;
        this.gamma = gamma;
    }

    public MultiDirectionalSimplex(double[] steps) {
        this(steps, 2.0, 0.5);
    }

    public MultiDirectionalSimplex(double[] steps, double khi, double gamma) {
        super(steps);
        this.khi = khi;
        this.gamma = gamma;
    }

    public MultiDirectionalSimplex(double[][] referenceSimplex) {
        this(referenceSimplex, 2.0, 0.5);
    }

    public MultiDirectionalSimplex(double[][] referenceSimplex, double khi, double gamma) {
        super(referenceSimplex);
        this.khi = khi;
        this.gamma = gamma;
    }

    @Override
    public void iterate(MultivariateFunction evaluationFunction, Comparator<PointValuePair> comparator) {
        PointValuePair[] original = this.getPoints();
        PointValuePair best = original[0];
        PointValuePair reflected = this.evaluateNewSimplex(evaluationFunction, original, 1.0, comparator);
        if (comparator.compare(reflected, best) < 0) {
            PointValuePair[] reflectedSimplex = this.getPoints();
            PointValuePair expanded = this.evaluateNewSimplex(evaluationFunction, original, this.khi, comparator);
            if (comparator.compare(reflected, expanded) <= 0) {
                this.setPoints(reflectedSimplex);
            }
            return;
        }
        this.evaluateNewSimplex(evaluationFunction, original, this.gamma, comparator);
    }

    private PointValuePair evaluateNewSimplex(MultivariateFunction evaluationFunction, PointValuePair[] original, double coeff, Comparator<PointValuePair> comparator) {
        double[] xSmallest = original[0].getPointRef();
        this.setPoint(0, original[0]);
        int dim = this.getDimension();
        for (int i = 1; i < this.getSize(); ++i) {
            double[] xOriginal = original[i].getPointRef();
            double[] xTransformed = new double[dim];
            for (int j = 0; j < dim; ++j) {
                xTransformed[j] = xSmallest[j] + coeff * (xSmallest[j] - xOriginal[j]);
            }
            this.setPoint(i, new PointValuePair(xTransformed, Double.NaN, false));
        }
        this.evaluate(evaluationFunction, comparator);
        return this.getPoint(0);
    }
}

