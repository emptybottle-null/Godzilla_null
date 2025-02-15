/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.events;

public interface EventHandler {
    public void init(double var1, double[] var3, double var4);

    public double g(double var1, double[] var3);

    public Action eventOccurred(double var1, double[] var3, boolean var4);

    public void resetState(double var1, double[] var3);

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Action {
        STOP,
        RESET_STATE,
        RESET_DERIVATIVES,
        CONTINUE;

    }
}

