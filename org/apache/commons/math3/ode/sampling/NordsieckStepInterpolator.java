/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class NordsieckStepInterpolator
extends AbstractStepInterpolator {
    private static final long serialVersionUID = -7179861704951334960L;
    protected double[] stateVariation;
    private double scalingH;
    private double referenceTime;
    private double[] scaled;
    private Array2DRowRealMatrix nordsieck;

    public NordsieckStepInterpolator() {
    }

    public NordsieckStepInterpolator(NordsieckStepInterpolator interpolator) {
        super(interpolator);
        this.scalingH = interpolator.scalingH;
        this.referenceTime = interpolator.referenceTime;
        if (interpolator.scaled != null) {
            this.scaled = (double[])interpolator.scaled.clone();
        }
        if (interpolator.nordsieck != null) {
            this.nordsieck = new Array2DRowRealMatrix(interpolator.nordsieck.getDataRef(), true);
        }
        if (interpolator.stateVariation != null) {
            this.stateVariation = (double[])interpolator.stateVariation.clone();
        }
    }

    protected StepInterpolator doCopy() {
        return new NordsieckStepInterpolator(this);
    }

    public void reinitialize(double[] y, boolean forward, EquationsMapper primaryMapper, EquationsMapper[] secondaryMappers) {
        super.reinitialize(y, forward, primaryMapper, secondaryMappers);
        this.stateVariation = new double[y.length];
    }

    public void reinitialize(double time, double stepSize, double[] scaledDerivative, Array2DRowRealMatrix nordsieckVector) {
        this.referenceTime = time;
        this.scalingH = stepSize;
        this.scaled = scaledDerivative;
        this.nordsieck = nordsieckVector;
        this.setInterpolatedTime(this.getInterpolatedTime());
    }

    public void rescale(double stepSize) {
        double ratio = stepSize / this.scalingH;
        int i = 0;
        while (i < this.scaled.length) {
            int n = i++;
            this.scaled[n] = this.scaled[n] * ratio;
        }
        double[][] nData = this.nordsieck.getDataRef();
        double power = ratio;
        for (int i2 = 0; i2 < nData.length; ++i2) {
            power *= ratio;
            double[] nDataI = nData[i2];
            int j = 0;
            while (j < nDataI.length) {
                int n = j++;
                nDataI[n] = nDataI[n] * power;
            }
        }
        this.scalingH = stepSize;
    }

    public double[] getInterpolatedStateVariation() throws MaxCountExceededException {
        this.getInterpolatedState();
        return this.stateVariation;
    }

    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        double x = this.interpolatedTime - this.referenceTime;
        double normalizedAbscissa = x / this.scalingH;
        Arrays.fill(this.stateVariation, 0.0);
        Arrays.fill(this.interpolatedDerivatives, 0.0);
        double[][] nData = this.nordsieck.getDataRef();
        for (int i = nData.length - 1; i >= 0; --i) {
            int order = i + 2;
            double[] nDataI = nData[i];
            double power = FastMath.pow(normalizedAbscissa, order);
            int j = 0;
            while (j < nDataI.length) {
                double d = nDataI[j] * power;
                int n = j;
                this.stateVariation[n] = this.stateVariation[n] + d;
                int n2 = j++;
                this.interpolatedDerivatives[n2] = this.interpolatedDerivatives[n2] + (double)order * d;
            }
        }
        for (int j = 0; j < this.currentState.length; ++j) {
            int n = j;
            this.stateVariation[n] = this.stateVariation[n] + this.scaled[j] * normalizedAbscissa;
            this.interpolatedState[j] = this.currentState[j] + this.stateVariation[j];
            this.interpolatedDerivatives[j] = (this.interpolatedDerivatives[j] + this.scaled[j] * normalizedAbscissa) / x;
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        int n;
        this.writeBaseExternal(out);
        out.writeDouble(this.scalingH);
        out.writeDouble(this.referenceTime);
        int n2 = n = this.currentState == null ? -1 : this.currentState.length;
        if (this.scaled == null) {
            out.writeBoolean(false);
        } else {
            out.writeBoolean(true);
            for (int j = 0; j < n; ++j) {
                out.writeDouble(this.scaled[j]);
            }
        }
        if (this.nordsieck == null) {
            out.writeBoolean(false);
        } else {
            out.writeBoolean(true);
            out.writeObject(this.nordsieck);
        }
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        boolean hasNordsieck;
        double t = this.readBaseExternal(in);
        this.scalingH = in.readDouble();
        this.referenceTime = in.readDouble();
        int n = this.currentState == null ? -1 : this.currentState.length;
        boolean hasScaled = in.readBoolean();
        if (hasScaled) {
            this.scaled = new double[n];
            for (int j = 0; j < n; ++j) {
                this.scaled[j] = in.readDouble();
            }
        } else {
            this.scaled = null;
        }
        this.nordsieck = (hasNordsieck = in.readBoolean()) ? (Array2DRowRealMatrix)in.readObject() : null;
        if (hasScaled && hasNordsieck) {
            this.stateVariation = new double[n];
            this.setInterpolatedTime(t);
        } else {
            this.stateVariation = null;
        }
    }
}

