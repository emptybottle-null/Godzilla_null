/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

public interface FixedStepHandler {
    public void init(double var1, double[] var3, double var4);

    public void handleStep(double var1, double[] var3, double[] var4, boolean var5);
}

