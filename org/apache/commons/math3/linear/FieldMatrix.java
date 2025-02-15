/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.linear.AnyMatrix;
import org.apache.commons.math3.linear.FieldMatrixChangingVisitor;
import org.apache.commons.math3.linear.FieldMatrixPreservingVisitor;
import org.apache.commons.math3.linear.FieldVector;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.NonSquareMatrixException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FieldMatrix<T extends FieldElement<T>>
extends AnyMatrix {
    public Field<T> getField();

    public FieldMatrix<T> createMatrix(int var1, int var2) throws NotStrictlyPositiveException;

    public FieldMatrix<T> copy();

    public FieldMatrix<T> add(FieldMatrix<T> var1) throws MatrixDimensionMismatchException;

    public FieldMatrix<T> subtract(FieldMatrix<T> var1) throws MatrixDimensionMismatchException;

    public FieldMatrix<T> scalarAdd(T var1);

    public FieldMatrix<T> scalarMultiply(T var1);

    public FieldMatrix<T> multiply(FieldMatrix<T> var1) throws DimensionMismatchException;

    public FieldMatrix<T> preMultiply(FieldMatrix<T> var1) throws DimensionMismatchException;

    public FieldMatrix<T> power(int var1) throws NonSquareMatrixException, NotPositiveException;

    public T[][] getData();

    public FieldMatrix<T> getSubMatrix(int var1, int var2, int var3, int var4) throws NumberIsTooSmallException, OutOfRangeException;

    public FieldMatrix<T> getSubMatrix(int[] var1, int[] var2) throws NoDataException, NullArgumentException, OutOfRangeException;

    public void copySubMatrix(int var1, int var2, int var3, int var4, T[][] var5) throws MatrixDimensionMismatchException, NumberIsTooSmallException, OutOfRangeException;

    public void copySubMatrix(int[] var1, int[] var2, T[][] var3) throws MatrixDimensionMismatchException, NoDataException, NullArgumentException, OutOfRangeException;

    public void setSubMatrix(T[][] var1, int var2, int var3) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException;

    public FieldMatrix<T> getRowMatrix(int var1) throws OutOfRangeException;

    public void setRowMatrix(int var1, FieldMatrix<T> var2) throws MatrixDimensionMismatchException, OutOfRangeException;

    public FieldMatrix<T> getColumnMatrix(int var1) throws OutOfRangeException;

    public void setColumnMatrix(int var1, FieldMatrix<T> var2) throws MatrixDimensionMismatchException, OutOfRangeException;

    public FieldVector<T> getRowVector(int var1) throws OutOfRangeException;

    public void setRowVector(int var1, FieldVector<T> var2) throws MatrixDimensionMismatchException, OutOfRangeException;

    public FieldVector<T> getColumnVector(int var1) throws OutOfRangeException;

    public void setColumnVector(int var1, FieldVector<T> var2) throws MatrixDimensionMismatchException, OutOfRangeException;

    public T[] getRow(int var1) throws OutOfRangeException;

    public void setRow(int var1, T[] var2) throws MatrixDimensionMismatchException, OutOfRangeException;

    public T[] getColumn(int var1) throws OutOfRangeException;

    public void setColumn(int var1, T[] var2) throws MatrixDimensionMismatchException, OutOfRangeException;

    public T getEntry(int var1, int var2) throws OutOfRangeException;

    public void setEntry(int var1, int var2, T var3) throws OutOfRangeException;

    public void addToEntry(int var1, int var2, T var3) throws OutOfRangeException;

    public void multiplyEntry(int var1, int var2, T var3) throws OutOfRangeException;

    public FieldMatrix<T> transpose();

    public T getTrace() throws NonSquareMatrixException;

    public T[] operate(T[] var1) throws DimensionMismatchException;

    public FieldVector<T> operate(FieldVector<T> var1) throws DimensionMismatchException;

    public T[] preMultiply(T[] var1) throws DimensionMismatchException;

    public FieldVector<T> preMultiply(FieldVector<T> var1) throws DimensionMismatchException;

    public T walkInRowOrder(FieldMatrixChangingVisitor<T> var1);

    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> var1);

    public T walkInRowOrder(FieldMatrixChangingVisitor<T> var1, int var2, int var3, int var4, int var5) throws OutOfRangeException, NumberIsTooSmallException;

    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> var1, int var2, int var3, int var4, int var5) throws OutOfRangeException, NumberIsTooSmallException;

    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> var1);

    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> var1);

    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> var1, int var2, int var3, int var4, int var5) throws NumberIsTooSmallException, OutOfRangeException;

    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> var1, int var2, int var3, int var4, int var5) throws NumberIsTooSmallException, OutOfRangeException;

    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> var1);

    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> var1);

    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> var1, int var2, int var3, int var4, int var5) throws NumberIsTooSmallException, OutOfRangeException;

    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> var1, int var2, int var3, int var4, int var5) throws NumberIsTooSmallException, OutOfRangeException;
}

