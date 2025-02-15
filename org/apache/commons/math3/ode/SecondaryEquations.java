/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

public interface SecondaryEquations {
    public int getDimension();

    public void computeDerivatives(double var1, double[] var3, double[] var4, double[] var5, double[] var6) throws MaxCountExceededException, DimensionMismatchException;
}

