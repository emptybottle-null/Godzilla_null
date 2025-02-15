/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.RealMatrix;

@Deprecated
public class LeastSquaresConverter
implements MultivariateFunction {
    private final MultivariateVectorFunction function;
    private final double[] observations;
    private final double[] weights;
    private final RealMatrix scale;

    public LeastSquaresConverter(MultivariateVectorFunction function, double[] observations) {
        this.function = function;
        this.observations = (double[])observations.clone();
        this.weights = null;
        this.scale = null;
    }

    public LeastSquaresConverter(MultivariateVectorFunction function, double[] observations, double[] weights) {
        if (observations.length != weights.length) {
            throw new DimensionMismatchException(observations.length, weights.length);
        }
        this.function = function;
        this.observations = (double[])observations.clone();
        this.weights = (double[])weights.clone();
        this.scale = null;
    }

    public LeastSquaresConverter(MultivariateVectorFunction function, double[] observations, RealMatrix scale) {
        if (observations.length != scale.getColumnDimension()) {
            throw new DimensionMismatchException(observations.length, scale.getColumnDimension());
        }
        this.function = function;
        this.observations = (double[])observations.clone();
        this.weights = null;
        this.scale = scale.copy();
    }

    public double value(double[] point) {
        double[] residuals = this.function.value(point);
        if (residuals.length != this.observations.length) {
            throw new DimensionMismatchException(residuals.length, this.observations.length);
        }
        for (int i = 0; i < residuals.length; ++i) {
            int n = i;
            residuals[n] = residuals[n] - this.observations[i];
        }
        double sumSquares = 0.0;
        if (this.weights != null) {
            for (int i = 0; i < residuals.length; ++i) {
                double ri = residuals[i];
                sumSquares += this.weights[i] * ri * ri;
            }
        } else if (this.scale != null) {
            for (double yi : this.scale.operate(residuals)) {
                sumSquares += yi * yi;
            }
        } else {
            for (double ri : residuals) {
                sumSquares += ri * ri;
            }
        }
        return sumSquares;
    }
}

