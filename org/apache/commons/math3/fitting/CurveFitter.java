/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.optim.InitialGuess;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.optim.nonlinear.vector.ModelFunction;
import org.apache.commons.math3.optim.nonlinear.vector.ModelFunctionJacobian;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;
import org.apache.commons.math3.optim.nonlinear.vector.Target;
import org.apache.commons.math3.optim.nonlinear.vector.Weight;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class CurveFitter<T extends ParametricUnivariateFunction> {
    private final MultivariateVectorOptimizer optimizer;
    private final List<WeightedObservedPoint> observations;

    public CurveFitter(MultivariateVectorOptimizer optimizer) {
        this.optimizer = optimizer;
        this.observations = new ArrayList<WeightedObservedPoint>();
    }

    public void addObservedPoint(double x, double y) {
        this.addObservedPoint(1.0, x, y);
    }

    public void addObservedPoint(double weight, double x, double y) {
        this.observations.add(new WeightedObservedPoint(weight, x, y));
    }

    public void addObservedPoint(WeightedObservedPoint observed) {
        this.observations.add(observed);
    }

    public WeightedObservedPoint[] getObservations() {
        return this.observations.toArray(new WeightedObservedPoint[this.observations.size()]);
    }

    public void clearObservations() {
        this.observations.clear();
    }

    public double[] fit(T f, double[] initialGuess) {
        return this.fit(Integer.MAX_VALUE, f, initialGuess);
    }

    public double[] fit(int maxEval, T f, double[] initialGuess) {
        double[] target = new double[this.observations.size()];
        double[] weights = new double[this.observations.size()];
        int i = 0;
        for (WeightedObservedPoint point : this.observations) {
            target[i] = point.getY();
            weights[i] = point.getWeight();
            ++i;
        }
        TheoreticalValuesFunction model = new TheoreticalValuesFunction((ParametricUnivariateFunction)f);
        PointVectorValuePair optimum = this.optimizer.optimize(new MaxEval(maxEval), model.getModelFunction(), model.getModelFunctionJacobian(), new Target(target), new Weight(weights), new InitialGuess(initialGuess));
        return optimum.getPointRef();
    }

    private class TheoreticalValuesFunction {
        private final ParametricUnivariateFunction f;

        TheoreticalValuesFunction(ParametricUnivariateFunction f) {
            this.f = f;
        }

        public ModelFunction getModelFunction() {
            return new ModelFunction(new MultivariateVectorFunction(){

                public double[] value(double[] point) {
                    double[] values = new double[CurveFitter.this.observations.size()];
                    int i = 0;
                    for (WeightedObservedPoint observed : CurveFitter.this.observations) {
                        values[i++] = TheoreticalValuesFunction.this.f.value(observed.getX(), point);
                    }
                    return values;
                }
            });
        }

        public ModelFunctionJacobian getModelFunctionJacobian() {
            return new ModelFunctionJacobian(new MultivariateMatrixFunction(){

                public double[][] value(double[] point) {
                    double[][] jacobian = new double[CurveFitter.this.observations.size()][];
                    int i = 0;
                    for (WeightedObservedPoint observed : CurveFitter.this.observations) {
                        jacobian[i++] = TheoreticalValuesFunction.this.f.gradient(observed.getX(), point);
                    }
                    return jacobian;
                }
            });
        }
    }
}

