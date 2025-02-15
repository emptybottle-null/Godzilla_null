/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

public interface SecondOrderDifferentialEquations {
    public int getDimension();

    public void computeSecondDerivatives(double var1, double[] var3, double[] var4, double[] var5);
}

