/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.nonstiff.GillStepInterpolator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator;
import org.apache.commons.math3.util.FastMath;

public class GillIntegrator
extends RungeKuttaIntegrator {
    private static final double[] STATIC_C = new double[]{0.5, 0.5, 1.0};
    private static final double[][] STATIC_A = new double[][]{{0.5}, {(FastMath.sqrt(2.0) - 1.0) / 2.0, (2.0 - FastMath.sqrt(2.0)) / 2.0}, {0.0, -FastMath.sqrt(2.0) / 2.0, (2.0 + FastMath.sqrt(2.0)) / 2.0}};
    private static final double[] STATIC_B = new double[]{0.16666666666666666, (2.0 - FastMath.sqrt(2.0)) / 6.0, (2.0 + FastMath.sqrt(2.0)) / 6.0, 0.16666666666666666};

    public GillIntegrator(double step) {
        super("Gill", STATIC_C, STATIC_A, STATIC_B, new GillStepInterpolator(), step);
    }
}

