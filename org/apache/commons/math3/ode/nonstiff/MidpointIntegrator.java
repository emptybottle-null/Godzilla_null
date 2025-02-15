/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.nonstiff.MidpointStepInterpolator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator;

public class MidpointIntegrator
extends RungeKuttaIntegrator {
    private static final double[] STATIC_C = new double[]{0.5};
    private static final double[][] STATIC_A = new double[][]{{0.5}};
    private static final double[] STATIC_B = new double[]{0.0, 1.0};

    public MidpointIntegrator(double step) {
        super("midpoint", STATIC_C, STATIC_A, STATIC_B, new MidpointStepInterpolator(), step);
    }
}

