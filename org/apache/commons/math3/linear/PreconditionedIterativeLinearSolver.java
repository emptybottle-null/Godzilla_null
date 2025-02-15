/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.IterativeLinearSolver;
import org.apache.commons.math3.linear.NonSquareOperatorException;
import org.apache.commons.math3.linear.RealLinearOperator;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.IterationManager;
import org.apache.commons.math3.util.MathUtils;

public abstract class PreconditionedIterativeLinearSolver
extends IterativeLinearSolver {
    public PreconditionedIterativeLinearSolver(int maxIterations) {
        super(maxIterations);
    }

    public PreconditionedIterativeLinearSolver(IterationManager manager) throws NullArgumentException {
        super(manager);
    }

    public RealVector solve(RealLinearOperator a, RealLinearOperator m, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(x0);
        return this.solveInPlace(a, m, b, x0.copy());
    }

    public RealVector solve(RealLinearOperator a, RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(a);
        ArrayRealVector x = new ArrayRealVector(a.getColumnDimension());
        ((RealVector)x).set(0.0);
        return this.solveInPlace(a, null, b, x);
    }

    public RealVector solve(RealLinearOperator a, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(x0);
        return this.solveInPlace(a, null, b, x0.copy());
    }

    protected static void checkParameters(RealLinearOperator a, RealLinearOperator m, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException {
        PreconditionedIterativeLinearSolver.checkParameters(a, b, x0);
        if (m != null) {
            if (m.getColumnDimension() != m.getRowDimension()) {
                throw new NonSquareOperatorException(m.getColumnDimension(), m.getRowDimension());
            }
            if (m.getRowDimension() != a.getRowDimension()) {
                throw new DimensionMismatchException(m.getRowDimension(), a.getRowDimension());
            }
        }
    }

    public RealVector solve(RealLinearOperator a, RealLinearOperator m, RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(a);
        ArrayRealVector x = new ArrayRealVector(a.getColumnDimension());
        return this.solveInPlace(a, m, b, x);
    }

    public abstract RealVector solveInPlace(RealLinearOperator var1, RealLinearOperator var2, RealVector var3, RealVector var4) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException;

    public RealVector solveInPlace(RealLinearOperator a, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        return this.solveInPlace(a, null, b, x0);
    }
}

