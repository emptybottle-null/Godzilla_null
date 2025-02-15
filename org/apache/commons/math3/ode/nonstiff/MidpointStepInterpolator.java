/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

class MidpointStepInterpolator
extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120L;

    public MidpointStepInterpolator() {
    }

    MidpointStepInterpolator(MidpointStepInterpolator interpolator) {
        super(interpolator);
    }

    protected StepInterpolator doCopy() {
        return new MidpointStepInterpolator(this);
    }

    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        double coeffDot2 = 2.0 * theta;
        double coeffDot1 = 1.0 - coeffDot2;
        if (this.previousState != null && theta <= 0.5) {
            double coeff1 = theta * oneMinusThetaH;
            double coeff2 = theta * theta * this.h;
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                double yDot1 = this.yDotK[0][i];
                double yDot2 = this.yDotK[1][i];
                this.interpolatedState[i] = this.previousState[i] + coeff1 * yDot1 + coeff2 * yDot2;
                this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2;
            }
        } else {
            double coeff1 = oneMinusThetaH * theta;
            double coeff2 = oneMinusThetaH * (1.0 + theta);
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                double yDot1 = this.yDotK[0][i];
                double yDot2 = this.yDotK[1][i];
                this.interpolatedState[i] = this.currentState[i] + coeff1 * yDot1 - coeff2 * yDot2;
                this.interpolatedDerivatives[i] = coeffDot1 * yDot1 + coeffDot2 * yDot2;
            }
        }
    }
}

