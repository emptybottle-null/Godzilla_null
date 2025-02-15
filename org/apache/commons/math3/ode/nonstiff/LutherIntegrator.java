/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.nonstiff.LutherStepInterpolator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator;
import org.apache.commons.math3.util.FastMath;

public class LutherIntegrator
extends RungeKuttaIntegrator {
    private static final double Q = FastMath.sqrt(21.0);
    private static final double[] STATIC_C = new double[]{1.0, 0.5, 0.6666666666666666, (7.0 - Q) / 14.0, (7.0 + Q) / 14.0, 1.0};
    private static final double[][] STATIC_A = new double[][]{{1.0}, {0.375, 0.125}, {0.2962962962962963, 0.07407407407407407, 0.2962962962962963}, {(-21.0 + 9.0 * Q) / 392.0, (-56.0 + 8.0 * Q) / 392.0, (336.0 - 48.0 * Q) / 392.0, (-63.0 + 3.0 * Q) / 392.0}, {(-1155.0 - 255.0 * Q) / 1960.0, (-280.0 - 40.0 * Q) / 1960.0, (0.0 - 320.0 * Q) / 1960.0, (63.0 + 363.0 * Q) / 1960.0, (2352.0 + 392.0 * Q) / 1960.0}, {(330.0 + 105.0 * Q) / 180.0, (120.0 + 0.0 * Q) / 180.0, (-200.0 + 280.0 * Q) / 180.0, (126.0 - 189.0 * Q) / 180.0, (-686.0 - 126.0 * Q) / 180.0, (490.0 - 70.0 * Q) / 180.0}};
    private static final double[] STATIC_B = new double[]{0.05, 0.0, 0.35555555555555557, 0.0, 0.2722222222222222, 0.2722222222222222, 0.05};

    public LutherIntegrator(double step) {
        super("Luther", STATIC_C, STATIC_A, STATIC_B, new LutherStepInterpolator(), step);
    }
}

