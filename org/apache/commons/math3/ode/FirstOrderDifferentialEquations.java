/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;

public interface FirstOrderDifferentialEquations {
    public int getDimension();

    public void computeDerivatives(double var1, double[] var3, double[] var4) throws MaxCountExceededException, DimensionMismatchException;
}

