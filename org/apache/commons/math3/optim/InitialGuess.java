/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim;

import org.apache.commons.math3.optim.OptimizationData;

public class InitialGuess
implements OptimizationData {
    private final double[] init;

    public InitialGuess(double[] startPoint) {
        this.init = (double[])startPoint.clone();
    }

    public double[] getInitialGuess() {
        return (double[])this.init.clone();
    }
}

