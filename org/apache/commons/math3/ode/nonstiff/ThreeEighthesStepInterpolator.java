/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

class ThreeEighthesStepInterpolator
extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120L;

    public ThreeEighthesStepInterpolator() {
    }

    ThreeEighthesStepInterpolator(ThreeEighthesStepInterpolator interpolator) {
        super(interpolator);
    }

    protected StepInterpolator doCopy() {
        return new ThreeEighthesStepInterpolator(this);
    }

    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        double coeffDot3 = 0.75 * theta;
        double coeffDot1 = coeffDot3 * (4.0 * theta - 5.0) + 1.0;
        double coeffDot2 = coeffDot3 * (5.0 - 6.0 * theta);
        double coeffDot4 = coeffDot3 * (2.0 * theta - 1.0);
        if (this.previousState != null && theta <= 0.5) {
            double s = theta * this.h / 8.0;
            double fourTheta2 = 4.0 * theta * theta;
            double coeff1 = s * (8.0 - 15.0 * theta + 2.0 * fourTheta2);
            double coeff2 = 3.0 * s * (5.0 * theta - fourTheta2);
            double coeff3 = 3.0 * s * theta;
            double coeff4 = s * (-3.0 * theta + fourTheta2);
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                double yDot1 = this.yDotK[0][i];
                double yDot2 = this.yDotK[1][i];
                double yDot3 = this.yDotK[2][i];
                double yDot4 = this.yDotK[3][i];
                this.interpolatedState[i] = this.previousState[i] + coeff1 * yDot1 + coeff2 * yDot2 + coeff3 * yDot3 + coeff4 * yDot4;
                this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2 + coeffDot3 * yDot3 + coeffDot4 * yDot4;
            }
        } else {
            double s = oneMinusThetaH / 8.0;
            double fourTheta2 = 4.0 * theta * theta;
            double coeff1 = s * (1.0 - 7.0 * theta + 2.0 * fourTheta2);
            double coeff2 = 3.0 * s * (1.0 + theta - fourTheta2);
            double coeff3 = 3.0 * s * (1.0 + theta);
            double coeff4 = s * (1.0 + theta + fourTheta2);
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                double yDot1 = this.yDotK[0][i];
                double yDot2 = this.yDotK[1][i];
                double yDot3 = this.yDotK[2][i];
                double yDot4 = this.yDotK[3][i];
                this.interpolatedState[i] = this.currentState[i] - coeff1 * yDot1 - coeff2 * yDot2 - coeff3 * yDot3 - coeff4 * yDot4;
                this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2 + coeffDot3 * yDot3 + coeffDot4 * yDot4;
            }
        }
    }
}

