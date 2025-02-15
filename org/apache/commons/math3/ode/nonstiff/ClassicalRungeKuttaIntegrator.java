/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaStepInterpolator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator;

public class ClassicalRungeKuttaIntegrator
extends RungeKuttaIntegrator {
    private static final double[] STATIC_C = new double[]{0.5, 0.5, 1.0};
    private static final double[][] STATIC_A = new double[][]{{0.5}, {0.0, 0.5}, {0.0, 0.0, 1.0}};
    private static final double[] STATIC_B = new double[]{0.16666666666666666, 0.3333333333333333, 0.3333333333333333, 0.16666666666666666};

    public ClassicalRungeKuttaIntegrator(double step) {
        super("classical Runge-Kutta", STATIC_C, STATIC_A, STATIC_B, new ClassicalRungeKuttaStepInterpolator(), step);
    }
}

