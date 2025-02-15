/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optimization.fitting;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.analysis.DifferentiableMultivariateVectorFunction;
import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.optimization.MultivariateDifferentiableVectorOptimizer;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.fitting.WeightedObservedPoint;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class CurveFitter<T extends ParametricUnivariateFunction> {
    @Deprecated
    private final DifferentiableMultivariateVectorOptimizer oldOptimizer;
    private final MultivariateDifferentiableVectorOptimizer optimizer;
    private final List<WeightedObservedPoint> observations;

    @Deprecated
    public CurveFitter(DifferentiableMultivariateVectorOptimizer optimizer) {
        this.oldOptimizer = optimizer;
        this.optimizer = null;
        this.observations = new ArrayList<WeightedObservedPoint>();
    }

    public CurveFitter(MultivariateDifferentiableVectorOptimizer optimizer) {
        this.oldOptimizer = null;
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
        PointVectorValuePair optimum = this.optimizer == null ? this.oldOptimizer.optimize(maxEval, new OldTheoreticalValuesFunction((ParametricUnivariateFunction)f), target, weights, initialGuess) : this.optimizer.optimize(maxEval, new TheoreticalValuesFunction((ParametricUnivariateFunction)f), target, weights, initialGuess);
        return optimum.getPointRef();
    }

    private class TheoreticalValuesFunction
    implements MultivariateDifferentiableVectorFunction {
        private final ParametricUnivariateFunction f;

        TheoreticalValuesFunction(ParametricUnivariateFunction f) {
            this.f = f;
        }

        public double[] value(double[] point) {
            double[] values = new double[CurveFitter.this.observations.size()];
            int i = 0;
            for (WeightedObservedPoint observed : CurveFitter.this.observations) {
                values[i++] = this.f.value(observed.getX(), point);
            }
            return values;
        }

        public DerivativeStructure[] value(DerivativeStructure[] point) {
            double[] parameters = new double[point.length];
            for (int k = 0; k < point.length; ++k) {
                parameters[k] = point[k].getValue();
            }
            DerivativeStructure[] values = new DerivativeStructure[CurveFitter.this.observations.size()];
            int i = 0;
            for (WeightedObservedPoint observed : CurveFitter.this.observations) {
                DerivativeStructure vi = new DerivativeStructure(point.length, 1, this.f.value(observed.getX(), parameters));
                for (int k = 0; k < point.length; ++k) {
                    vi = vi.add(new DerivativeStructure(point.length, 1, k, 0.0));
                }
                values[i++] = vi;
            }
            return values;
        }
    }

    @Deprecated
    private class OldTheoreticalValuesFunction
    implements DifferentiableMultivariateVectorFunction {
        private final ParametricUnivariateFunction f;

        OldTheoreticalValuesFunction(ParametricUnivariateFunction f) {
            this.f = f;
        }

        public MultivariateMatrixFunction jacobian() {
            return new MultivariateMatrixFunction(){

                public double[][] value(double[] point) {
                    double[][] jacobian = new double[CurveFitter.this.observations.size()][];
                    int i = 0;
                    for (WeightedObservedPoint observed : CurveFitter.this.observations) {
                        jacobian[i++] = OldTheoreticalValuesFunction.this.f.gradient(observed.getX(), point);
                    }
                    return jacobian;
                }
            };
        }

        public double[] value(double[] point) {
            double[] values = new double[CurveFitter.this.observations.size()];
            int i = 0;
            for (WeightedObservedPoint observed : CurveFitter.this.observations) {
                values[i++] = this.f.value(observed.getX(), point);
            }
            return values;
        }
    }
}

