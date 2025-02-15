/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator;
import org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class DormandPrince54Integrator
extends EmbeddedRungeKuttaIntegrator {
    private static final String METHOD_NAME = "Dormand-Prince 5(4)";
    private static final double[] STATIC_C = new double[]{0.2, 0.3, 0.8, 0.8888888888888888, 1.0, 1.0};
    private static final double[][] STATIC_A = new double[][]{{0.2}, {0.075, 0.225}, {0.9777777777777777, -3.7333333333333334, 3.5555555555555554}, {2.9525986892242035, -11.595793324188385, 9.822892851699436, -0.2908093278463649}, {2.8462752525252526, -10.757575757575758, 8.906422717743473, 0.2784090909090909, -0.2735313036020583}, {0.09114583333333333, 0.0, 0.44923629829290207, 0.6510416666666666, -0.322376179245283, 0.13095238095238096}};
    private static final double[] STATIC_B = new double[]{0.09114583333333333, 0.0, 0.44923629829290207, 0.6510416666666666, -0.322376179245283, 0.13095238095238096, 0.0};
    private static final double E1 = 0.0012326388888888888;
    private static final double E3 = -0.0042527702905061394;
    private static final double E4 = 0.03697916666666667;
    private static final double E5 = -0.05086379716981132;
    private static final double E6 = 0.0419047619047619;
    private static final double E7 = -0.025;

    public DormandPrince54Integrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(METHOD_NAME, true, STATIC_C, STATIC_A, STATIC_B, (RungeKuttaStepInterpolator)new DormandPrince54StepInterpolator(), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    public DormandPrince54Integrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(METHOD_NAME, true, STATIC_C, STATIC_A, STATIC_B, (RungeKuttaStepInterpolator)new DormandPrince54StepInterpolator(), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
    }

    public int getOrder() {
        return 5;
    }

    protected double estimateError(double[][] yDotK, double[] y0, double[] y1, double h) {
        double error = 0.0;
        for (int j = 0; j < this.mainSetDimension; ++j) {
            double errSum = 0.0012326388888888888 * yDotK[0][j] + -0.0042527702905061394 * yDotK[2][j] + 0.03697916666666667 * yDotK[3][j] + -0.05086379716981132 * yDotK[4][j] + 0.0419047619047619 * yDotK[5][j] + -0.025 * yDotK[6][j];
            double yScale = FastMath.max(FastMath.abs(y0[j]), FastMath.abs(y1[j]));
            double tol = this.vecAbsoluteTolerance == null ? this.scalAbsoluteTolerance + this.scalRelativeTolerance * yScale : this.vecAbsoluteTolerance[j] + this.vecRelativeTolerance[j] * yScale;
            double ratio = h * errSum / tol;
            error += ratio * ratio;
        }
        return FastMath.sqrt(error / (double)this.mainSetDimension);
    }
}

