/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

class DormandPrince853StepInterpolator
extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120L;
    private static final double B_01 = 0.054293734116568765;
    private static final double B_06 = 4.450312892752409;
    private static final double B_07 = 1.8915178993145003;
    private static final double B_08 = -5.801203960010585;
    private static final double B_09 = 0.3111643669578199;
    private static final double B_10 = -0.1521609496625161;
    private static final double B_11 = 0.20136540080403034;
    private static final double B_12 = 0.04471061572777259;
    private static final double C14 = 0.1;
    private static final double K14_01 = 0.0018737681664791894;
    private static final double K14_06 = -4.450312892752409;
    private static final double K14_07 = -1.6380176890978755;
    private static final double K14_08 = 5.554964922539782;
    private static final double K14_09 = -0.4353557902216363;
    private static final double K14_10 = 0.30545274794128174;
    private static final double K14_11 = -0.19316434850839564;
    private static final double K14_12 = -0.03714271806722689;
    private static final double K14_13 = -0.008298;
    private static final double C15 = 0.2;
    private static final double K15_01 = -0.022459085953066622;
    private static final double K15_06 = -4.422011983080043;
    private static final double K15_07 = -1.8379759110070617;
    private static final double K15_08 = 5.746280211439194;
    private static final double K15_09 = -0.3111643669578199;
    private static final double K15_10 = 0.1521609496625161;
    private static final double K15_11 = -0.2014737481327276;
    private static final double K15_12 = -0.04432804463693693;
    private static final double K15_13 = -3.4046500868740456E-4;
    private static final double K15_14 = 0.1413124436746325;
    private static final double C16 = 0.7777777777777778;
    private static final double K16_01 = -0.4831900357003607;
    private static final double K16_06 = -9.147934308113573;
    private static final double K16_07 = 5.791903296748099;
    private static final double K16_08 = 9.870193778407696;
    private static final double K16_09 = 0.04556282049746119;
    private static final double K16_10 = 0.1521609496625161;
    private static final double K16_11 = -0.20136540080403034;
    private static final double K16_12 = -0.04471061572777259;
    private static final double K16_13 = -0.0013990241651590145;
    private static final double K16_14 = 2.9475147891527724;
    private static final double K16_15 = -9.15095847217987;
    private static final double[][] D = new double[][]{{-8.428938276109013, 0.5667149535193777, -3.0689499459498917, 2.38466765651207, 2.1170345824450285, -0.871391583777973, 2.2404374302607883, 0.6315787787694688, -0.08899033645133331, 18.148505520854727, -9.194632392478356, -4.436036387594894}, {10.427508642579134, 242.28349177525817, 165.20045171727028, -374.5467547226902, -22.113666853125302, 7.733432668472264, -30.674084731089398, -9.332130526430229, 15.697238121770845, -31.139403219565178, -9.35292435884448, 35.81684148639408}, {19.985053242002433, -387.0373087493518, -189.17813819516758, 527.8081592054236, -11.573902539959631, 6.8812326946963, -1.0006050966910838, 0.7777137798053443, -2.778205752353508, -60.19669523126412, 84.32040550667716, 11.99229113618279}, {-25.69393346270375, -154.18974869023643, -231.5293791760455, 357.6391179106141, 93.4053241836243, -37.45832313645163, 104.0996495089623, 29.8402934266605, -43.53345659001114, 96.32455395918828, -39.17726167561544, -149.72683625798564}};
    private double[][] yDotKLast;
    private double[][] v;
    private boolean vectorsInitialized;

    public DormandPrince853StepInterpolator() {
        this.yDotKLast = null;
        this.v = null;
        this.vectorsInitialized = false;
    }

    DormandPrince853StepInterpolator(DormandPrince853StepInterpolator interpolator) {
        super(interpolator);
        if (interpolator.currentState == null) {
            this.yDotKLast = null;
            this.v = null;
            this.vectorsInitialized = false;
        } else {
            int k;
            int dimension = interpolator.currentState.length;
            this.yDotKLast = new double[3][];
            for (k = 0; k < this.yDotKLast.length; ++k) {
                this.yDotKLast[k] = new double[dimension];
                System.arraycopy(interpolator.yDotKLast[k], 0, this.yDotKLast[k], 0, dimension);
            }
            this.v = new double[7][];
            for (k = 0; k < this.v.length; ++k) {
                this.v[k] = new double[dimension];
                System.arraycopy(interpolator.v[k], 0, this.v[k], 0, dimension);
            }
            this.vectorsInitialized = interpolator.vectorsInitialized;
        }
    }

    protected StepInterpolator doCopy() {
        return new DormandPrince853StepInterpolator(this);
    }

    public void reinitialize(AbstractIntegrator integrator, double[] y, double[][] yDotK, boolean forward, EquationsMapper primaryMapper, EquationsMapper[] secondaryMappers) {
        int k;
        super.reinitialize(integrator, y, yDotK, forward, primaryMapper, secondaryMappers);
        int dimension = this.currentState.length;
        this.yDotKLast = new double[3][];
        for (k = 0; k < this.yDotKLast.length; ++k) {
            this.yDotKLast[k] = new double[dimension];
        }
        this.v = new double[7][];
        for (k = 0; k < this.v.length; ++k) {
            this.v[k] = new double[dimension];
        }
        this.vectorsInitialized = false;
    }

    public void storeTime(double t) {
        super.storeTime(t);
        this.vectorsInitialized = false;
    }

    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) throws MaxCountExceededException {
        if (!this.vectorsInitialized) {
            if (this.v == null) {
                this.v = new double[7][];
                for (int k = 0; k < 7; ++k) {
                    this.v[k] = new double[this.interpolatedState.length];
                }
            }
            this.finalizeStep();
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                double yDot1 = this.yDotK[0][i];
                double yDot6 = this.yDotK[5][i];
                double yDot7 = this.yDotK[6][i];
                double yDot8 = this.yDotK[7][i];
                double yDot9 = this.yDotK[8][i];
                double yDot10 = this.yDotK[9][i];
                double yDot11 = this.yDotK[10][i];
                double yDot12 = this.yDotK[11][i];
                double yDot13 = this.yDotK[12][i];
                double yDot14 = this.yDotKLast[0][i];
                double yDot15 = this.yDotKLast[1][i];
                double yDot16 = this.yDotKLast[2][i];
                this.v[0][i] = 0.054293734116568765 * yDot1 + 4.450312892752409 * yDot6 + 1.8915178993145003 * yDot7 + -5.801203960010585 * yDot8 + 0.3111643669578199 * yDot9 + -0.1521609496625161 * yDot10 + 0.20136540080403034 * yDot11 + 0.04471061572777259 * yDot12;
                this.v[1][i] = yDot1 - this.v[0][i];
                this.v[2][i] = this.v[0][i] - this.v[1][i] - this.yDotK[12][i];
                for (int k = 0; k < D.length; ++k) {
                    this.v[k + 3][i] = D[k][0] * yDot1 + D[k][1] * yDot6 + D[k][2] * yDot7 + D[k][3] * yDot8 + D[k][4] * yDot9 + D[k][5] * yDot10 + D[k][6] * yDot11 + D[k][7] * yDot12 + D[k][8] * yDot13 + D[k][9] * yDot14 + D[k][10] * yDot15 + D[k][11] * yDot16;
                }
            }
            this.vectorsInitialized = true;
        }
        double eta = 1.0 - theta;
        double twoTheta = 2.0 * theta;
        double theta2 = theta * theta;
        double dot1 = 1.0 - twoTheta;
        double dot2 = theta * (2.0 - 3.0 * theta);
        double dot3 = twoTheta * (1.0 + theta * (twoTheta - 3.0));
        double dot4 = theta2 * (3.0 + theta * (5.0 * theta - 8.0));
        double dot5 = theta2 * (3.0 + theta * (-12.0 + theta * (15.0 - 6.0 * theta)));
        double dot6 = theta2 * theta * (4.0 + theta * (-15.0 + theta * (18.0 - 7.0 * theta)));
        if (this.previousState != null && theta <= 0.5) {
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                this.interpolatedState[i] = this.previousState[i] + theta * this.h * (this.v[0][i] + eta * (this.v[1][i] + theta * (this.v[2][i] + eta * (this.v[3][i] + theta * (this.v[4][i] + eta * (this.v[5][i] + theta * this.v[6][i]))))));
                this.interpolatedDerivatives[i] = this.v[0][i] + dot1 * this.v[1][i] + dot2 * this.v[2][i] + dot3 * this.v[3][i] + dot4 * this.v[4][i] + dot5 * this.v[5][i] + dot6 * this.v[6][i];
            }
        } else {
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                this.interpolatedState[i] = this.currentState[i] - oneMinusThetaH * (this.v[0][i] - theta * (this.v[1][i] + theta * (this.v[2][i] + eta * (this.v[3][i] + theta * (this.v[4][i] + eta * (this.v[5][i] + theta * this.v[6][i]))))));
                this.interpolatedDerivatives[i] = this.v[0][i] + dot1 * this.v[1][i] + dot2 * this.v[2][i] + dot3 * this.v[3][i] + dot4 * this.v[4][i] + dot5 * this.v[5][i] + dot6 * this.v[6][i];
            }
        }
    }

    protected void doFinalize() throws MaxCountExceededException {
        double s;
        int j;
        if (this.currentState == null) {
            return;
        }
        double[] yTmp = new double[this.currentState.length];
        double pT = this.getGlobalPreviousTime();
        for (j = 0; j < this.currentState.length; ++j) {
            s = 0.0018737681664791894 * this.yDotK[0][j] + -4.450312892752409 * this.yDotK[5][j] + -1.6380176890978755 * this.yDotK[6][j] + 5.554964922539782 * this.yDotK[7][j] + -0.4353557902216363 * this.yDotK[8][j] + 0.30545274794128174 * this.yDotK[9][j] + -0.19316434850839564 * this.yDotK[10][j] + -0.03714271806722689 * this.yDotK[11][j] + -0.008298 * this.yDotK[12][j];
            yTmp[j] = this.currentState[j] + this.h * s;
        }
        this.integrator.computeDerivatives(pT + 0.1 * this.h, yTmp, this.yDotKLast[0]);
        for (j = 0; j < this.currentState.length; ++j) {
            s = -0.022459085953066622 * this.yDotK[0][j] + -4.422011983080043 * this.yDotK[5][j] + -1.8379759110070617 * this.yDotK[6][j] + 5.746280211439194 * this.yDotK[7][j] + -0.3111643669578199 * this.yDotK[8][j] + 0.1521609496625161 * this.yDotK[9][j] + -0.2014737481327276 * this.yDotK[10][j] + -0.04432804463693693 * this.yDotK[11][j] + -3.4046500868740456E-4 * this.yDotK[12][j] + 0.1413124436746325 * this.yDotKLast[0][j];
            yTmp[j] = this.currentState[j] + this.h * s;
        }
        this.integrator.computeDerivatives(pT + 0.2 * this.h, yTmp, this.yDotKLast[1]);
        for (j = 0; j < this.currentState.length; ++j) {
            s = -0.4831900357003607 * this.yDotK[0][j] + -9.147934308113573 * this.yDotK[5][j] + 5.791903296748099 * this.yDotK[6][j] + 9.870193778407696 * this.yDotK[7][j] + 0.04556282049746119 * this.yDotK[8][j] + 0.1521609496625161 * this.yDotK[9][j] + -0.20136540080403034 * this.yDotK[10][j] + -0.04471061572777259 * this.yDotK[11][j] + -0.0013990241651590145 * this.yDotK[12][j] + 2.9475147891527724 * this.yDotKLast[0][j] + -9.15095847217987 * this.yDotKLast[1][j];
            yTmp[j] = this.currentState[j] + this.h * s;
        }
        this.integrator.computeDerivatives(pT + 0.7777777777777778 * this.h, yTmp, this.yDotKLast[2]);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        try {
            this.finalizeStep();
        } catch (MaxCountExceededException mcee) {
            IOException ioe = new IOException(mcee.getLocalizedMessage());
            ioe.initCause(mcee);
            throw ioe;
        }
        int dimension = this.currentState == null ? -1 : this.currentState.length;
        out.writeInt(dimension);
        for (int i = 0; i < dimension; ++i) {
            out.writeDouble(this.yDotKLast[0][i]);
            out.writeDouble(this.yDotKLast[1][i]);
            out.writeDouble(this.yDotKLast[2][i]);
        }
        super.writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.yDotKLast = new double[3][];
        int dimension = in.readInt();
        this.yDotKLast[0] = dimension < 0 ? null : new double[dimension];
        this.yDotKLast[1] = dimension < 0 ? null : new double[dimension];
        this.yDotKLast[2] = dimension < 0 ? null : new double[dimension];
        for (int i = 0; i < dimension; ++i) {
            this.yDotKLast[0][i] = in.readDouble();
            this.yDotKLast[1][i] = in.readDouble();
            this.yDotKLast[2][i] = in.readDouble();
        }
        super.readExternal(in);
    }
}

