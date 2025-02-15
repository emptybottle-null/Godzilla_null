/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

class EulerStepInterpolator
extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120L;

    public EulerStepInterpolator() {
    }

    EulerStepInterpolator(EulerStepInterpolator interpolator) {
        super(interpolator);
    }

    protected StepInterpolator doCopy() {
        return new EulerStepInterpolator(this);
    }

    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        if (this.previousState != null && theta <= 0.5) {
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                this.interpolatedState[i] = this.previousState[i] + theta * this.h * this.yDotK[0][i];
            }
            System.arraycopy(this.yDotK[0], 0, this.interpolatedDerivatives, 0, this.interpolatedDerivatives.length);
        } else {
            for (int i = 0; i < this.interpolatedState.length; ++i) {
                this.interpolatedState[i] = this.currentState[i] - oneMinusThetaH * this.yDotK[0][i];
            }
            System.arraycopy(this.yDotK[0], 0, this.interpolatedDerivatives, 0, this.interpolatedDerivatives.length);
        }
    }
}

