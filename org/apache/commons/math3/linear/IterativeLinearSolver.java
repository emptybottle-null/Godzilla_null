/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.NonSquareOperatorException;
import org.apache.commons.math3.linear.RealLinearOperator;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.IterationManager;
import org.apache.commons.math3.util.MathUtils;

public abstract class IterativeLinearSolver {
    private final IterationManager manager;

    public IterativeLinearSolver(int maxIterations) {
        this.manager = new IterationManager(maxIterations);
    }

    public IterativeLinearSolver(IterationManager manager) throws NullArgumentException {
        MathUtils.checkNotNull(manager);
        this.manager = manager;
    }

    protected static void checkParameters(RealLinearOperator a, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException {
        MathUtils.checkNotNull(a);
        MathUtils.checkNotNull(b);
        MathUtils.checkNotNull(x0);
        if (a.getRowDimension() != a.getColumnDimension()) {
            throw new NonSquareOperatorException(a.getRowDimension(), a.getColumnDimension());
        }
        if (b.getDimension() != a.getRowDimension()) {
            throw new DimensionMismatchException(b.getDimension(), a.getRowDimension());
        }
        if (x0.getDimension() != a.getColumnDimension()) {
            throw new DimensionMismatchException(x0.getDimension(), a.getColumnDimension());
        }
    }

    public IterationManager getIterationManager() {
        return this.manager;
    }

    public RealVector solve(RealLinearOperator a, RealVector b) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(a);
        ArrayRealVector x = new ArrayRealVector(a.getColumnDimension());
        ((RealVector)x).set(0.0);
        return this.solveInPlace(a, b, x);
    }

    public RealVector solve(RealLinearOperator a, RealVector b, RealVector x0) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException {
        MathUtils.checkNotNull(x0);
        return this.solveInPlace(a, b, x0.copy());
    }

    public abstract RealVector solveInPlace(RealLinearOperator var1, RealVector var2, RealVector var3) throws NullArgumentException, NonSquareOperatorException, DimensionMismatchException, MaxCountExceededException;
}

