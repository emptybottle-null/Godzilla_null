/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public interface MainStateJacobianProvider
extends FirstOrderDifferentialEquations {
    public void computeMainStateJacobian(double var1, double[] var3, double[] var4, double[][] var5) throws MaxCountExceededException, DimensionMismatchException;
}

