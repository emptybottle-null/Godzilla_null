/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

class ClassicalRungeKuttaStepInterpolator
extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120L;

    public ClassicalRungeKuttaStepInterpolator() {
    }

    ClassicalRungeKuttaStepInterpolator(ClassicalRungeKuttaStepInterpolator interpolator) {
        super(interpolator);
    }

    protected StepInterpolator doCopy() {
        return new ClassicalRungeKuttaStepInterpolator(this);
    }

    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        double oneMinusTheta = 1.0 - theta;
        double oneMinus2Theta = 1.0 - 2.0 * theta;
        double coeffDot1 = oneMinusTheta * oneMinus2Theta;
        double coeffDot23 = 2.0 * theta * oneMinusTheta;
        double coeffDot4 = -theta * oneMinus2Theta;
        if (this.previousState != null && theta <= 0.5) {
            double fourTheta2 = 4.0 * theta * theta;
            double s = theta * this.h / 6.0;
            double coeff1 = s * (6.0 - 9.0 * theta + fourTheta2);
            double coeff23 = s * (6.0 * theta - fourTheta2);
            double coeff4 = s * (-3.0 * theta + fourTheta2);
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                double yDot1 = this.yDotK[0][i];
                double yDot23 = this.yDotK[1][i] + this.yDotK[2][i];
                double yDot4 = this.yDotK[3][i];
                this.interpolatedState[i] = this.previousState[i] + coeff1 * yDot1 + coeff23 * yDot23 + coeff4 * yDot4;
                this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot23 * yDot23 + coeffDot4 * yDot4;
            }
        } else {
            double fourTheta = 4.0 * theta;
            double s = oneMinusThetaH / 6.0;
            double coeff1 = s * ((-fourTheta + 5.0) * theta - 1.0);
            double coeff23 = s * ((fourTheta - 2.0) * theta - 2.0);
            double coeff4 = s * ((-fourTheta - 1.0) * theta - 1.0);
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                double yDot1 = this.yDotK[0][i];
                double yDot23 = this.yDotK[1][i] + this.yDotK[2][i];
                double yDot4 = this.yDotK[3][i];
                this.interpolatedState[i] = this.currentState[i] + coeff1 * yDot1 + coeff23 * yDot23 + coeff4 * yDot4;
                this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot23 * yDot23 + coeffDot4 * yDot4;
            }
        }
    }
}

