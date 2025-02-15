/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.linear.AnyMatrix;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrixChangingVisitor;
import org.apache.commons.math3.linear.RealMatrixPreservingVisitor;
import org.apache.commons.math3.linear.RealVector;

public interface RealMatrix
extends AnyMatrix {
    public RealMatrix createMatrix(int var1, int var2) throws NotStrictlyPositiveException;

    public RealMatrix copy();

    public RealMatrix add(RealMatrix var1) throws MatrixDimensionMismatchException;

    public RealMatrix subtract(RealMatrix var1) throws MatrixDimensionMismatchException;

    public RealMatrix scalarAdd(double var1);

    public RealMatrix scalarMultiply(double var1);

    public RealMatrix multiply(RealMatrix var1) throws DimensionMismatchException;

    public RealMatrix preMultiply(RealMatrix var1) throws DimensionMismatchException;

    public RealMatrix power(int var1) throws NotPositiveException, NonSquareMatrixException;

    public double[][] getData();

    public double getNorm();

    public double getFrobeniusNorm();

    public RealMatrix getSubMatrix(int var1, int var2, int var3, int var4) throws OutOfRangeException, NumberIsTooSmallException;

    public RealMatrix getSubMatrix(int[] var1, int[] var2) throws NullArgumentException, NoDataException, OutOfRangeException;

    public void copySubMatrix(int var1, int var2, int var3, int var4, double[][] var5) throws OutOfRangeException, NumberIsTooSmallException, MatrixDimensionMismatchException;

    public void copySubMatrix(int[] var1, int[] var2, double[][] var3) throws OutOfRangeException, NullArgumentException, NoDataException, MatrixDimensionMismatchException;

    public void setSubMatrix(double[][] var1, int var2, int var3) throws NoDataException, OutOfRangeException, DimensionMismatchException, NullArgumentException;

    public RealMatrix getRowMatrix(int var1) throws OutOfRangeException;

    public void setRowMatrix(int var1, RealMatrix var2) throws OutOfRangeException, MatrixDimensionMismatchException;

    public RealMatrix getColumnMatrix(int var1) throws OutOfRangeException;

    public void setColumnMatrix(int var1, RealMatrix var2) throws OutOfRangeException, MatrixDimensionMismatchException;

    public RealVector getRowVector(int var1) throws OutOfRangeException;

    public void setRowVector(int var1, RealVector var2) throws OutOfRangeException, MatrixDimensionMismatchException;

    public RealVector getColumnVector(int var1) throws OutOfRangeException;

    public void setColumnVector(int var1, RealVector var2) throws OutOfRangeException, MatrixDimensionMismatchException;

    public double[] getRow(int var1) throws OutOfRangeException;

    public void setRow(int var1, double[] var2) throws OutOfRangeException, MatrixDimensionMismatchException;

    public double[] getColumn(int var1) throws OutOfRangeException;

    public void setColumn(int var1, double[] var2) throws OutOfRangeException, MatrixDimensionMismatchException;

    public double getEntry(int var1, int var2) throws OutOfRangeException;

    public void setEntry(int var1, int var2, double var3) throws OutOfRangeException;

    public void addToEntry(int var1, int var2, double var3) throws OutOfRangeException;

    public void multiplyEntry(int var1, int var2, double var3) throws OutOfRangeException;

    public RealMatrix transpose();

    public double getTrace() throws NonSquareMatrixException;

    public double[] operate(double[] var1) throws DimensionMismatchException;

    public RealVector operate(RealVector var1) throws DimensionMismatchException;

    public double[] preMultiply(double[] var1) throws DimensionMismatchException;

    public RealVector preMultiply(RealVector var1) throws DimensionMismatchException;

    public double walkInRowOrder(RealMatrixChangingVisitor var1);

    public double walkInRowOrder(RealMatrixPreservingVisitor var1);

    public double walkInRowOrder(RealMatrixChangingVisitor var1, int var2, int var3, int var4, int var5) throws OutOfRangeException, NumberIsTooSmallException;

    public double walkInRowOrder(RealMatrixPreservingVisitor var1, int var2, int var3, int var4, int var5) throws OutOfRangeException, NumberIsTooSmallException;

    public double walkInColumnOrder(RealMatrixChangingVisitor var1);

    public double walkInColumnOrder(RealMatrixPreservingVisitor var1);

    public double walkInColumnOrder(RealMatrixChangingVisitor var1, int var2, int var3, int var4, int var5) throws OutOfRangeException, NumberIsTooSmallException;

    public double walkInColumnOrder(RealMatrixPreservingVisitor var1, int var2, int var3, int var4, int var5) throws OutOfRangeException, NumberIsTooSmallException;

    public double walkInOptimizedOrder(RealMatrixChangingVisitor var1);

    public double walkInOptimizedOrder(RealMatrixPreservingVisitor var1);

    public double walkInOptimizedOrder(RealMatrixChangingVisitor var1, int var2, int var3, int var4, int var5) throws OutOfRangeException, NumberIsTooSmallException;

    public double walkInOptimizedOrder(RealMatrixPreservingVisitor var1, int var2, int var3, int var4, int var5) throws OutOfRangeException, NumberIsTooSmallException;
}

