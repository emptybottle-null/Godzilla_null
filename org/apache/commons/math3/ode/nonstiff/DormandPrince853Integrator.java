/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator;
import org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class DormandPrince853Integrator
extends EmbeddedRungeKuttaIntegrator {
    private static final String METHOD_NAME = "Dormand-Prince 8 (5, 3)";
    private static final double[] STATIC_C = new double[]{(12.0 - 2.0 * FastMath.sqrt(6.0)) / 135.0, (6.0 - FastMath.sqrt(6.0)) / 45.0, (6.0 - FastMath.sqrt(6.0)) / 30.0, (6.0 + FastMath.sqrt(6.0)) / 30.0, 0.3333333333333333, 0.25, 0.3076923076923077, 0.6512820512820513, 0.6, 0.8571428571428571, 1.0, 1.0};
    private static final double[][] STATIC_A = new double[][]{{(12.0 - 2.0 * FastMath.sqrt(6.0)) / 135.0}, {(6.0 - FastMath.sqrt(6.0)) / 180.0, (6.0 - FastMath.sqrt(6.0)) / 60.0}, {(6.0 - FastMath.sqrt(6.0)) / 120.0, 0.0, (6.0 - FastMath.sqrt(6.0)) / 40.0}, {(462.0 + 107.0 * FastMath.sqrt(6.0)) / 3000.0, 0.0, (-402.0 - 197.0 * FastMath.sqrt(6.0)) / 1000.0, (168.0 + 73.0 * FastMath.sqrt(6.0)) / 375.0}, {0.037037037037037035, 0.0, 0.0, (16.0 + FastMath.sqrt(6.0)) / 108.0, (16.0 - FastMath.sqrt(6.0)) / 108.0}, {0.037109375, 0.0, 0.0, (118.0 + 23.0 * FastMath.sqrt(6.0)) / 1024.0, (118.0 - 23.0 * FastMath.sqrt(6.0)) / 1024.0, -0.017578125}, {0.03709200011850479, 0.0, 0.0, (51544.0 + 4784.0 * FastMath.sqrt(6.0)) / 371293.0, (51544.0 - 4784.0 * FastMath.sqrt(6.0)) / 371293.0, -0.015319437748624402, 0.008273789163814023}, {0.6241109587160757, 0.0, 0.0, (-1.324889724104E12 - 3.18801444819E11 * FastMath.sqrt(6.0)) / 6.265569375E11, (-1.324889724104E12 + 3.18801444819E11 * FastMath.sqrt(6.0)) / 6.265569375E11, 27.59209969944671, 20.154067550477894, -43.48988418106996}, {0.47766253643826434, 0.0, 0.0, (-4521408.0 - 1137963.0 * FastMath.sqrt(6.0)) / 2937500.0, (-4521408.0 + 1137963.0 * FastMath.sqrt(6.0)) / 2937500.0, 21.230051448181193, 15.279233632882423, -33.28821096898486, -0.020331201708508627}, {-0.9371424300859873, 0.0, 0.0, (354216.0 + 94326.0 * FastMath.sqrt(6.0)) / 112847.0, (354216.0 - 94326.0 * FastMath.sqrt(6.0)) / 112847.0, -8.149787010746927, -18.52006565999696, 22.739487099350505, 2.4936055526796523, -3.0467644718982196}, {2.273310147516538, 0.0, 0.0, (-3457480.0 - 960905.0 * FastMath.sqrt(6.0)) / 551636.0, (-3457480.0 + 960905.0 * FastMath.sqrt(6.0)) / 551636.0, -17.9589318631188, 27.94888452941996, -2.8589982771350235, -8.87285693353063, 12.360567175794303, 0.6433927460157636}, {0.054293734116568765, 0.0, 0.0, 0.0, 0.0, 4.450312892752409, 1.8915178993145003, -5.801203960010585, 0.3111643669578199, -0.1521609496625161, 0.20136540080403034, 0.04471061572777259}};
    private static final double[] STATIC_B = new double[]{0.054293734116568765, 0.0, 0.0, 0.0, 0.0, 4.450312892752409, 1.8915178993145003, -5.801203960010585, 0.3111643669578199, -0.1521609496625161, 0.20136540080403034, 0.04471061572777259, 0.0};
    private static final double E1_01 = 0.01312004499419488;
    private static final double E1_06 = -1.2251564463762044;
    private static final double E1_07 = -0.4957589496572502;
    private static final double E1_08 = 1.6643771824549864;
    private static final double E1_09 = -0.35032884874997366;
    private static final double E1_10 = 0.3341791187130175;
    private static final double E1_11 = 0.08192320648511571;
    private static final double E1_12 = -0.022355307863886294;
    private static final double E2_01 = -0.18980075407240762;
    private static final double E2_06 = 4.450312892752409;
    private static final double E2_07 = 1.8915178993145003;
    private static final double E2_08 = -5.801203960010585;
    private static final double E2_09 = -0.42268232132379197;
    private static final double E2_10 = -0.1521609496625161;
    private static final double E2_11 = 0.20136540080403034;
    private static final double E2_12 = 0.022651792198360825;

    public DormandPrince853Integrator(double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) {
        super(METHOD_NAME, true, STATIC_C, STATIC_A, STATIC_B, (RungeKuttaStepInterpolator)new DormandPrince853StepInterpolator(), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    public DormandPrince853Integrator(double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(METHOD_NAME, true, STATIC_C, STATIC_A, STATIC_B, (RungeKuttaStepInterpolator)new DormandPrince853StepInterpolator(), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
    }

    public int getOrder() {
        return 8;
    }

    protected double estimateError(double[][] yDotK, double[] y0, double[] y1, double h) {
        double error1 = 0.0;
        double error2 = 0.0;
        for (int j = 0; j < this.mainSetDimension; ++j) {
            double errSum1 = 0.01312004499419488 * yDotK[0][j] + -1.2251564463762044 * yDotK[5][j] + -0.4957589496572502 * yDotK[6][j] + 1.6643771824549864 * yDotK[7][j] + -0.35032884874997366 * yDotK[8][j] + 0.3341791187130175 * yDotK[9][j] + 0.08192320648511571 * yDotK[10][j] + -0.022355307863886294 * yDotK[11][j];
            double errSum2 = -0.18980075407240762 * yDotK[0][j] + 4.450312892752409 * yDotK[5][j] + 1.8915178993145003 * yDotK[6][j] + -5.801203960010585 * yDotK[7][j] + -0.42268232132379197 * yDotK[8][j] + -0.1521609496625161 * yDotK[9][j] + 0.20136540080403034 * yDotK[10][j] + 0.022651792198360825 * yDotK[11][j];
            double yScale = FastMath.max(FastMath.abs(y0[j]), FastMath.abs(y1[j]));
            double tol = this.vecAbsoluteTolerance == null ? this.scalAbsoluteTolerance + this.scalRelativeTolerance * yScale : this.vecAbsoluteTolerance[j] + this.vecRelativeTolerance[j] * yScale;
            double ratio1 = errSum1 / tol;
            error1 += ratio1 * ratio1;
            double ratio2 = errSum2 / tol;
            error2 += ratio2 * ratio2;
        }
        double den = error1 + 0.01 * error2;
        if (den <= 0.0) {
            den = 1.0;
        }
        return FastMath.abs(h) * error1 / FastMath.sqrt((double)this.mainSetDimension * den);
    }
}

