/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3;

import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface RealFieldElement<T>
extends FieldElement<T> {
    public double getReal();

    @Override
    public T add(double var1);

    @Override
    public T subtract(double var1);

    @Override
    public T multiply(double var1);

    @Override
    public T divide(double var1);

    public T remainder(double var1);

    public T remainder(T var1) throws DimensionMismatchException;

    public T abs();

    public T ceil();

    public T floor();

    public T rint();

    public long round();

    public T signum();

    public T copySign(T var1);

    public T copySign(double var1);

    public T scalb(int var1);

    public T hypot(T var1) throws DimensionMismatchException;

    @Override
    public T reciprocal();

    public T sqrt();

    public T cbrt();

    public T rootN(int var1);

    public T pow(double var1);

    public T pow(int var1);

    public T pow(T var1) throws DimensionMismatchException;

    public T exp();

    public T expm1();

    public T log();

    public T log1p();

    public T cos();

    public T sin();

    public T tan();

    public T acos();

    public T asin();

    public T atan();

    public T atan2(T var1) throws DimensionMismatchException;

    public T cosh();

    public T sinh();

    public T tanh();

    public T acosh();

    public T asinh();

    public T atanh();

    public T linearCombination(T[] var1, T[] var2) throws DimensionMismatchException;

    public T linearCombination(double[] var1, T[] var2) throws DimensionMismatchException;

    public T linearCombination(T var1, T var2, T var3, T var4);

    public T linearCombination(double var1, T var3, double var4, T var6);

    public T linearCombination(T var1, T var2, T var3, T var4, T var5, T var6);

    public T linearCombination(double var1, T var3, double var4, T var6, double var7, T var9);

    public T linearCombination(T var1, T var2, T var3, T var4, T var5, T var6, T var7, T var8);

    public T linearCombination(double var1, T var3, double var4, T var6, double var7, T var9, double var10, T var12);
}

