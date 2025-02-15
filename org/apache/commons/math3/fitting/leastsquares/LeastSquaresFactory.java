/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.leastsquares.AbstractEvaluation;
import org.apache.commons.math3.fitting.leastsquares.DenseWeightedEvaluation;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresAdapter;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.fitting.leastsquares.MultivariateJacobianFunction;
import org.apache.commons.math3.fitting.leastsquares.ParameterValidator;
import org.apache.commons.math3.fitting.leastsquares.ValueAndJacobianFunction;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.AbstractOptimizationProblem;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LeastSquaresFactory {
    private LeastSquaresFactory() {
    }

    public static LeastSquaresProblem create(MultivariateJacobianFunction model, RealVector observed, RealVector start, RealMatrix weight, ConvergenceChecker<LeastSquaresProblem.Evaluation> checker, int maxEvaluations, int maxIterations, boolean lazyEvaluation, ParameterValidator paramValidator) {
        LocalLeastSquaresProblem p = new LocalLeastSquaresProblem(model, observed, start, checker, maxEvaluations, maxIterations, lazyEvaluation, paramValidator);
        if (weight != null) {
            return LeastSquaresFactory.weightMatrix(p, weight);
        }
        return p;
    }

    public static LeastSquaresProblem create(MultivariateJacobianFunction model, RealVector observed, RealVector start, ConvergenceChecker<LeastSquaresProblem.Evaluation> checker, int maxEvaluations, int maxIterations) {
        return LeastSquaresFactory.create(model, observed, start, null, checker, maxEvaluations, maxIterations, false, null);
    }

    public static LeastSquaresProblem create(MultivariateJacobianFunction model, RealVector observed, RealVector start, RealMatrix weight, ConvergenceChecker<LeastSquaresProblem.Evaluation> checker, int maxEvaluations, int maxIterations) {
        return LeastSquaresFactory.weightMatrix(LeastSquaresFactory.create(model, observed, start, checker, maxEvaluations, maxIterations), weight);
    }

    public static LeastSquaresProblem create(MultivariateVectorFunction model, MultivariateMatrixFunction jacobian, double[] observed, double[] start, RealMatrix weight, ConvergenceChecker<LeastSquaresProblem.Evaluation> checker, int maxEvaluations, int maxIterations) {
        return LeastSquaresFactory.create(LeastSquaresFactory.model(model, jacobian), new ArrayRealVector(observed, false), new ArrayRealVector(start, false), weight, checker, maxEvaluations, maxIterations);
    }

    public static LeastSquaresProblem weightMatrix(LeastSquaresProblem problem, RealMatrix weights) {
        final RealMatrix weightSquareRoot = LeastSquaresFactory.squareRoot(weights);
        return new LeastSquaresAdapter(problem){

            public LeastSquaresProblem.Evaluation evaluate(RealVector point) {
                return new DenseWeightedEvaluation(super.evaluate(point), weightSquareRoot);
            }
        };
    }

    public static LeastSquaresProblem weightDiagonal(LeastSquaresProblem problem, RealVector weights) {
        return LeastSquaresFactory.weightMatrix(problem, new DiagonalMatrix(weights.toArray()));
    }

    public static LeastSquaresProblem countEvaluations(LeastSquaresProblem problem, final Incrementor counter) {
        return new LeastSquaresAdapter(problem){

            public LeastSquaresProblem.Evaluation evaluate(RealVector point) {
                counter.incrementCount();
                return super.evaluate(point);
            }
        };
    }

    public static ConvergenceChecker<LeastSquaresProblem.Evaluation> evaluationChecker(final ConvergenceChecker<PointVectorValuePair> checker) {
        return new ConvergenceChecker<LeastSquaresProblem.Evaluation>(){

            @Override
            public boolean converged(int iteration, LeastSquaresProblem.Evaluation previous, LeastSquaresProblem.Evaluation current) {
                return checker.converged(iteration, new PointVectorValuePair(previous.getPoint().toArray(), previous.getResiduals().toArray(), false), new PointVectorValuePair(current.getPoint().toArray(), current.getResiduals().toArray(), false));
            }
        };
    }

    private static RealMatrix squareRoot(RealMatrix m) {
        if (m instanceof DiagonalMatrix) {
            int dim = m.getRowDimension();
            DiagonalMatrix sqrtM = new DiagonalMatrix(dim);
            for (int i = 0; i < dim; ++i) {
                sqrtM.setEntry(i, i, FastMath.sqrt(m.getEntry(i, i)));
            }
            return sqrtM;
        }
        EigenDecomposition dec = new EigenDecomposition(m);
        return dec.getSquareRoot();
    }

    public static MultivariateJacobianFunction model(MultivariateVectorFunction value, MultivariateMatrixFunction jacobian) {
        return new LocalValueAndJacobianFunction(value, jacobian);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class LocalLeastSquaresProblem
    extends AbstractOptimizationProblem<LeastSquaresProblem.Evaluation>
    implements LeastSquaresProblem {
        private final RealVector target;
        private final MultivariateJacobianFunction model;
        private final RealVector start;
        private final boolean lazyEvaluation;
        private final ParameterValidator paramValidator;

        LocalLeastSquaresProblem(MultivariateJacobianFunction model, RealVector target, RealVector start, ConvergenceChecker<LeastSquaresProblem.Evaluation> checker, int maxEvaluations, int maxIterations, boolean lazyEvaluation, ParameterValidator paramValidator) {
            super(maxEvaluations, maxIterations, checker);
            this.target = target;
            this.model = model;
            this.start = start;
            this.lazyEvaluation = lazyEvaluation;
            this.paramValidator = paramValidator;
            if (lazyEvaluation && !(model instanceof ValueAndJacobianFunction)) {
                throw new MathIllegalStateException(LocalizedFormats.INVALID_IMPLEMENTATION, model.getClass().getName());
            }
        }

        @Override
        public int getObservationSize() {
            return this.target.getDimension();
        }

        @Override
        public int getParameterSize() {
            return this.start.getDimension();
        }

        @Override
        public RealVector getStart() {
            return this.start == null ? null : this.start.copy();
        }

        @Override
        public LeastSquaresProblem.Evaluation evaluate(RealVector point) {
            RealVector p;
            RealVector realVector = p = this.paramValidator == null ? point.copy() : this.paramValidator.validate(point.copy());
            if (this.lazyEvaluation) {
                return new LazyUnweightedEvaluation((ValueAndJacobianFunction)this.model, this.target, p);
            }
            Pair<RealVector, RealMatrix> value = this.model.value(p);
            return new UnweightedEvaluation(value.getFirst(), value.getSecond(), this.target, p);
        }

        private static class LazyUnweightedEvaluation
        extends AbstractEvaluation {
            private final RealVector point;
            private final ValueAndJacobianFunction model;
            private final RealVector target;

            private LazyUnweightedEvaluation(ValueAndJacobianFunction model, RealVector target, RealVector point) {
                super(target.getDimension());
                this.model = model;
                this.point = point;
                this.target = target;
            }

            public RealMatrix getJacobian() {
                return this.model.computeJacobian(this.point.toArray());
            }

            public RealVector getPoint() {
                return this.point;
            }

            public RealVector getResiduals() {
                return this.target.subtract(this.model.computeValue(this.point.toArray()));
            }
        }

        private static class UnweightedEvaluation
        extends AbstractEvaluation {
            private final RealVector point;
            private final RealMatrix jacobian;
            private final RealVector residuals;

            private UnweightedEvaluation(RealVector values, RealMatrix jacobian, RealVector target, RealVector point) {
                super(target.getDimension());
                this.jacobian = jacobian;
                this.point = point;
                this.residuals = target.subtract(values);
            }

            public RealMatrix getJacobian() {
                return this.jacobian;
            }

            public RealVector getPoint() {
                return this.point;
            }

            public RealVector getResiduals() {
                return this.residuals;
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class LocalValueAndJacobianFunction
    implements ValueAndJacobianFunction {
        private final MultivariateVectorFunction value;
        private final MultivariateMatrixFunction jacobian;

        LocalValueAndJacobianFunction(MultivariateVectorFunction value, MultivariateMatrixFunction jacobian) {
            this.value = value;
            this.jacobian = jacobian;
        }

        @Override
        public Pair<RealVector, RealMatrix> value(RealVector point) {
            double[] p = point.toArray();
            return new Pair<RealVector, RealMatrix>(this.computeValue(p), this.computeJacobian(p));
        }

        @Override
        public RealVector computeValue(double[] params) {
            return new ArrayRealVector(this.value.value(params), false);
        }

        @Override
        public RealMatrix computeJacobian(double[] params) {
            return new Array2DRowRealMatrix(this.jacobian.value(params), false);
        }
    }
}

