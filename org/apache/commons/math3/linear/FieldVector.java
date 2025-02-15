/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.linear.FieldMatrix;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FieldVector<T extends FieldElement<T>> {
    public Field<T> getField();

    public FieldVector<T> copy();

    public FieldVector<T> add(FieldVector<T> var1) throws DimensionMismatchException;

    public FieldVector<T> subtract(FieldVector<T> var1) throws DimensionMismatchException;

    public FieldVector<T> mapAdd(T var1) throws NullArgumentException;

    public FieldVector<T> mapAddToSelf(T var1) throws NullArgumentException;

    public FieldVector<T> mapSubtract(T var1) throws NullArgumentException;

    public FieldVector<T> mapSubtractToSelf(T var1) throws NullArgumentException;

    public FieldVector<T> mapMultiply(T var1) throws NullArgumentException;

    public FieldVector<T> mapMultiplyToSelf(T var1) throws NullArgumentException;

    public FieldVector<T> mapDivide(T var1) throws NullArgumentException, MathArithmeticException;

    public FieldVector<T> mapDivideToSelf(T var1) throws NullArgumentException, MathArithmeticException;

    public FieldVector<T> mapInv() throws MathArithmeticException;

    public FieldVector<T> mapInvToSelf() throws MathArithmeticException;

    public FieldVector<T> ebeMultiply(FieldVector<T> var1) throws DimensionMismatchException;

    public FieldVector<T> ebeDivide(FieldVector<T> var1) throws DimensionMismatchException, MathArithmeticException;

    @Deprecated
    public T[] getData();

    public T dotProduct(FieldVector<T> var1) throws DimensionMismatchException;

    public FieldVector<T> projection(FieldVector<T> var1) throws DimensionMismatchException, MathArithmeticException;

    public FieldMatrix<T> outerProduct(FieldVector<T> var1);

    public T getEntry(int var1) throws OutOfRangeException;

    public void setEntry(int var1, T var2) throws OutOfRangeException;

    public int getDimension();

    public FieldVector<T> append(FieldVector<T> var1);

    public FieldVector<T> append(T var1);

    public FieldVector<T> getSubVector(int var1, int var2) throws OutOfRangeException, NotPositiveException;

    public void setSubVector(int var1, FieldVector<T> var2) throws OutOfRangeException;

    public void set(T var1);

    public T[] toArray();
}

