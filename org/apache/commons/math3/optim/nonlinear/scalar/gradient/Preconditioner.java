/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.nonlinear.scalar.gradient;

public interface Preconditioner {
    public double[] precondition(double[] var1, double[] var2);
}

