/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis;

public interface ParametricUnivariateFunction {
    public double value(double var1, double ... var3);

    public double[] gradient(double var1, double ... var3);
}

