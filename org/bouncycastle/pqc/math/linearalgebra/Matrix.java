/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.Vector;

public abstract class Matrix {
    protected int numRows;
    protected int numColumns;
    public static final char MATRIX_TYPE_ZERO = 'Z';
    public static final char MATRIX_TYPE_UNIT = 'I';
    public static final char MATRIX_TYPE_RANDOM_LT = 'L';
    public static final char MATRIX_TYPE_RANDOM_UT = 'U';
    public static final char MATRIX_TYPE_RANDOM_REGULAR = 'R';

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumColumns() {
        return this.numColumns;
    }

    public abstract byte[] getEncoded();

    public abstract Matrix computeInverse();

    public abstract boolean isZero();

    public abstract Matrix rightMultiply(Matrix var1);

    public abstract Matrix rightMultiply(Permutation var1);

    public abstract Vector leftMultiply(Vector var1);

    public abstract Vector rightMultiply(Vector var1);

    public abstract String toString();
}

