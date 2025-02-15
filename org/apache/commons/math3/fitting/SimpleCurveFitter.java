/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting;

import java.util.Collection;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.fitting.AbstractCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.DiagonalMatrix;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SimpleCurveFitter
extends AbstractCurveFitter {
    private final ParametricUnivariateFunction function;
    private final double[] initialGuess;
    private final int maxIter;

    private SimpleCurveFitter(ParametricUnivariateFunction function, double[] initialGuess, int maxIter) {
        this.function = function;
        this.initialGuess = initialGuess;
        this.maxIter = maxIter;
    }

    public static SimpleCurveFitter create(ParametricUnivariateFunction f, double[] start) {
        return new SimpleCurveFitter(f, start, Integer.MAX_VALUE);
    }

    public SimpleCurveFitter withStartPoint(double[] newStart) {
        return new SimpleCurveFitter(this.function, (double[])newStart.clone(), this.maxIter);
    }

    public SimpleCurveFitter withMaxIterations(int newMaxIter) {
        return new SimpleCurveFitter(this.function, this.initialGuess, newMaxIter);
    }

    @Override
    protected LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> observations) {
        int len = observations.size();
        double[] target = new double[len];
        double[] weights = new double[len];
        int count = 0;
        for (WeightedObservedPoint obs : observations) {
            target[count] = obs.getY();
            weights[count] = obs.getWeight();
            ++count;
        }
        AbstractCurveFitter.TheoreticalValuesFunction model = new AbstractCurveFitter.TheoreticalValuesFunction(this.function, observations);
        return new LeastSquaresBuilder().maxEvaluations(Integer.MAX_VALUE).maxIterations(this.maxIter).start(this.initialGuess).target(target).weight(new DiagonalMatrix(weights)).model(model.getModelFunction(), model.getModelFunctionJacobian()).build();
    }
}

