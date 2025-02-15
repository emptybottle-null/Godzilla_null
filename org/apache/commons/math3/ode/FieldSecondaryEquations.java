/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FieldSecondaryEquations<T extends RealFieldElement<T>> {
    public int getDimension();

    public void init(T var1, T[] var2, T[] var3, T var4);

    public T[] computeDerivatives(T var1, T[] var2, T[] var3, T[] var4) throws MaxCountExceededException, DimensionMismatchException;
}

