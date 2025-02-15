/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class AbstractSimplex
implements OptimizationData {
    private PointValuePair[] simplex;
    private double[][] startConfiguration;
    private final int dimension;

    protected AbstractSimplex(int n) {
        this(n, 1.0);
    }

    protected AbstractSimplex(int n, double sideLength) {
        this(AbstractSimplex.createHypercubeSteps(n, sideLength));
    }

    protected AbstractSimplex(double[] steps) {
        if (steps == null) {
            throw new NullArgumentException();
        }
        if (steps.length == 0) {
            throw new ZeroException();
        }
        this.dimension = steps.length;
        this.startConfiguration = new double[this.dimension][this.dimension];
        for (int i = 0; i < this.dimension; ++i) {
            double[] vertexI = this.startConfiguration[i];
            for (int j = 0; j < i + 1; ++j) {
                if (steps[j] == 0.0) {
                    throw new ZeroException((Localizable)LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX, new Object[0]);
                }
                System.arraycopy(steps, 0, vertexI, 0, j + 1);
            }
        }
    }

    protected AbstractSimplex(double[][] referenceSimplex) {
        if (referenceSimplex.length <= 0) {
            throw new NotStrictlyPositiveException((Localizable)LocalizedFormats.SIMPLEX_NEED_ONE_POINT, referenceSimplex.length);
        }
        this.dimension = referenceSimplex.length - 1;
        this.startConfiguration = new double[this.dimension][this.dimension];
        double[] ref0 = referenceSimplex[0];
        for (int i = 0; i < referenceSimplex.length; ++i) {
            double[] refI = referenceSimplex[i];
            if (refI.length != this.dimension) {
                throw new DimensionMismatchException(refI.length, this.dimension);
            }
            for (int j = 0; j < i; ++j) {
                double[] refJ = referenceSimplex[j];
                boolean allEquals = true;
                for (int k = 0; k < this.dimension; ++k) {
                    if (refI[k] == refJ[k]) continue;
                    allEquals = false;
                    break;
                }
                if (!allEquals) continue;
                throw new MathIllegalArgumentException(LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX, i, j);
            }
            if (i <= 0) continue;
            double[] confI = this.startConfiguration[i - 1];
            for (int k = 0; k < this.dimension; ++k) {
                confI[k] = refI[k] - ref0[k];
            }
        }
    }

    public int getDimension() {
        return this.dimension;
    }

    public int getSize() {
        return this.simplex.length;
    }

    public abstract void iterate(MultivariateFunction var1, Comparator<PointValuePair> var2);

    public void build(double[] startPoint) {
        if (this.dimension != startPoint.length) {
            throw new DimensionMismatchException(this.dimension, startPoint.length);
        }
        this.simplex = new PointValuePair[this.dimension + 1];
        this.simplex[0] = new PointValuePair(startPoint, Double.NaN);
        for (int i = 0; i < this.dimension; ++i) {
            double[] confI = this.startConfiguration[i];
            double[] vertexI = new double[this.dimension];
            for (int k = 0; k < this.dimension; ++k) {
                vertexI[k] = startPoint[k] + confI[k];
            }
            this.simplex[i + 1] = new PointValuePair(vertexI, Double.NaN);
        }
    }

    public void evaluate(MultivariateFunction evaluationFunction, Comparator<PointValuePair> comparator) {
        for (int i = 0; i < this.simplex.length; ++i) {
            PointValuePair vertex = this.simplex[i];
            double[] point = vertex.getPointRef();
            if (!Double.isNaN((Double)vertex.getValue())) continue;
            this.simplex[i] = new PointValuePair(point, evaluationFunction.value(point), false);
        }
        Arrays.sort(this.simplex, comparator);
    }

    protected void replaceWorstPoint(PointValuePair pointValuePair, Comparator<PointValuePair> comparator) {
        for (int i = 0; i < this.dimension; ++i) {
            if (comparator.compare(this.simplex[i], pointValuePair) <= 0) continue;
            PointValuePair tmp = this.simplex[i];
            this.simplex[i] = pointValuePair;
            pointValuePair = tmp;
        }
        this.simplex[this.dimension] = pointValuePair;
    }

    public PointValuePair[] getPoints() {
        PointValuePair[] copy = new PointValuePair[this.simplex.length];
        System.arraycopy(this.simplex, 0, copy, 0, this.simplex.length);
        return copy;
    }

    public PointValuePair getPoint(int index) {
        if (index < 0 || index >= this.simplex.length) {
            throw new OutOfRangeException(index, (Number)0, this.simplex.length - 1);
        }
        return this.simplex[index];
    }

    protected void setPoint(int index, PointValuePair point) {
        if (index < 0 || index >= this.simplex.length) {
            throw new OutOfRangeException(index, (Number)0, this.simplex.length - 1);
        }
        this.simplex[index] = point;
    }

    protected void setPoints(PointValuePair[] points) {
        if (points.length != this.simplex.length) {
            throw new DimensionMismatchException(points.length, this.simplex.length);
        }
        this.simplex = points;
    }

    private static double[] createHypercubeSteps(int n, double sideLength) {
        double[] steps = new double[n];
        for (int i = 0; i < n; ++i) {
            steps[i] = sideLength;
        }
        return steps;
    }
}

