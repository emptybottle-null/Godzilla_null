/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

public abstract class AbstractStepInterpolator
implements StepInterpolator {
    protected double h;
    protected double[] currentState;
    protected double interpolatedTime;
    protected double[] interpolatedState;
    protected double[] interpolatedDerivatives;
    protected double[] interpolatedPrimaryState;
    protected double[] interpolatedPrimaryDerivatives;
    protected double[][] interpolatedSecondaryState;
    protected double[][] interpolatedSecondaryDerivatives;
    private double globalPreviousTime;
    private double globalCurrentTime;
    private double softPreviousTime;
    private double softCurrentTime;
    private boolean finalized;
    private boolean forward;
    private boolean dirtyState;
    private EquationsMapper primaryMapper;
    private EquationsMapper[] secondaryMappers;

    protected AbstractStepInterpolator() {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = null;
        this.finalized = false;
        this.forward = true;
        this.dirtyState = true;
        this.primaryMapper = null;
        this.secondaryMappers = null;
        this.allocateInterpolatedArrays(-1);
    }

    protected AbstractStepInterpolator(double[] y, boolean forward, EquationsMapper primaryMapper, EquationsMapper[] secondaryMappers) {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = y;
        this.finalized = false;
        this.forward = forward;
        this.dirtyState = true;
        this.primaryMapper = primaryMapper;
        this.secondaryMappers = secondaryMappers == null ? null : (EquationsMapper[])secondaryMappers.clone();
        this.allocateInterpolatedArrays(y.length);
    }

    protected AbstractStepInterpolator(AbstractStepInterpolator interpolator) {
        this.globalPreviousTime = interpolator.globalPreviousTime;
        this.globalCurrentTime = interpolator.globalCurrentTime;
        this.softPreviousTime = interpolator.softPreviousTime;
        this.softCurrentTime = interpolator.softCurrentTime;
        this.h = interpolator.h;
        this.interpolatedTime = interpolator.interpolatedTime;
        if (interpolator.currentState == null) {
            this.currentState = null;
            this.primaryMapper = null;
            this.secondaryMappers = null;
            this.allocateInterpolatedArrays(-1);
        } else {
            this.currentState = (double[])interpolator.currentState.clone();
            this.interpolatedState = (double[])interpolator.interpolatedState.clone();
            this.interpolatedDerivatives = (double[])interpolator.interpolatedDerivatives.clone();
            this.interpolatedPrimaryState = (double[])interpolator.interpolatedPrimaryState.clone();
            this.interpolatedPrimaryDerivatives = (double[])interpolator.interpolatedPrimaryDerivatives.clone();
            this.interpolatedSecondaryState = new double[interpolator.interpolatedSecondaryState.length][];
            this.interpolatedSecondaryDerivatives = new double[interpolator.interpolatedSecondaryDerivatives.length][];
            for (int i = 0; i < this.interpolatedSecondaryState.length; ++i) {
                this.interpolatedSecondaryState[i] = (double[])interpolator.interpolatedSecondaryState[i].clone();
                this.interpolatedSecondaryDerivatives[i] = (double[])interpolator.interpolatedSecondaryDerivatives[i].clone();
            }
        }
        this.finalized = interpolator.finalized;
        this.forward = interpolator.forward;
        this.dirtyState = interpolator.dirtyState;
        this.primaryMapper = interpolator.primaryMapper;
        this.secondaryMappers = interpolator.secondaryMappers == null ? null : (EquationsMapper[])interpolator.secondaryMappers.clone();
    }

    private void allocateInterpolatedArrays(int dimension) {
        if (dimension < 0) {
            this.interpolatedState = null;
            this.interpolatedDerivatives = null;
            this.interpolatedPrimaryState = null;
            this.interpolatedPrimaryDerivatives = null;
            this.interpolatedSecondaryState = null;
            this.interpolatedSecondaryDerivatives = null;
        } else {
            this.interpolatedState = new double[dimension];
            this.interpolatedDerivatives = new double[dimension];
            this.interpolatedPrimaryState = new double[this.primaryMapper.getDimension()];
            this.interpolatedPrimaryDerivatives = new double[this.primaryMapper.getDimension()];
            if (this.secondaryMappers == null) {
                this.interpolatedSecondaryState = null;
                this.interpolatedSecondaryDerivatives = null;
            } else {
                this.interpolatedSecondaryState = new double[this.secondaryMappers.length][];
                this.interpolatedSecondaryDerivatives = new double[this.secondaryMappers.length][];
                for (int i = 0; i < this.secondaryMappers.length; ++i) {
                    this.interpolatedSecondaryState[i] = new double[this.secondaryMappers[i].getDimension()];
                    this.interpolatedSecondaryDerivatives[i] = new double[this.secondaryMappers[i].getDimension()];
                }
            }
        }
    }

    protected void reinitialize(double[] y, boolean isForward, EquationsMapper primary, EquationsMapper[] secondary) {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = y;
        this.finalized = false;
        this.forward = isForward;
        this.dirtyState = true;
        this.primaryMapper = primary;
        this.secondaryMappers = (EquationsMapper[])secondary.clone();
        this.allocateInterpolatedArrays(y.length);
    }

    public StepInterpolator copy() throws MaxCountExceededException {
        this.finalizeStep();
        return this.doCopy();
    }

    protected abstract StepInterpolator doCopy();

    public void shift() {
        this.softPreviousTime = this.globalPreviousTime = this.globalCurrentTime;
        this.softCurrentTime = this.globalCurrentTime;
    }

    public void storeTime(double t) {
        this.softCurrentTime = this.globalCurrentTime = t;
        this.h = this.globalCurrentTime - this.globalPreviousTime;
        this.setInterpolatedTime(t);
        this.finalized = false;
    }

    public void setSoftPreviousTime(double softPreviousTime) {
        this.softPreviousTime = softPreviousTime;
    }

    public void setSoftCurrentTime(double softCurrentTime) {
        this.softCurrentTime = softCurrentTime;
    }

    public double getGlobalPreviousTime() {
        return this.globalPreviousTime;
    }

    public double getGlobalCurrentTime() {
        return this.globalCurrentTime;
    }

    public double getPreviousTime() {
        return this.softPreviousTime;
    }

    public double getCurrentTime() {
        return this.softCurrentTime;
    }

    public double getInterpolatedTime() {
        return this.interpolatedTime;
    }

    public void setInterpolatedTime(double time) {
        this.interpolatedTime = time;
        this.dirtyState = true;
    }

    public boolean isForward() {
        return this.forward;
    }

    protected abstract void computeInterpolatedStateAndDerivatives(double var1, double var3) throws MaxCountExceededException;

    private void evaluateCompleteInterpolatedState() throws MaxCountExceededException {
        if (this.dirtyState) {
            double oneMinusThetaH = this.globalCurrentTime - this.interpolatedTime;
            double theta = this.h == 0.0 ? 0.0 : (this.h - oneMinusThetaH) / this.h;
            this.computeInterpolatedStateAndDerivatives(theta, oneMinusThetaH);
            this.dirtyState = false;
        }
    }

    public double[] getInterpolatedState() throws MaxCountExceededException {
        this.evaluateCompleteInterpolatedState();
        this.primaryMapper.extractEquationData(this.interpolatedState, this.interpolatedPrimaryState);
        return this.interpolatedPrimaryState;
    }

    public double[] getInterpolatedDerivatives() throws MaxCountExceededException {
        this.evaluateCompleteInterpolatedState();
        this.primaryMapper.extractEquationData(this.interpolatedDerivatives, this.interpolatedPrimaryDerivatives);
        return this.interpolatedPrimaryDerivatives;
    }

    public double[] getInterpolatedSecondaryState(int index) throws MaxCountExceededException {
        this.evaluateCompleteInterpolatedState();
        this.secondaryMappers[index].extractEquationData(this.interpolatedState, this.interpolatedSecondaryState[index]);
        return this.interpolatedSecondaryState[index];
    }

    public double[] getInterpolatedSecondaryDerivatives(int index) throws MaxCountExceededException {
        this.evaluateCompleteInterpolatedState();
        this.secondaryMappers[index].extractEquationData(this.interpolatedDerivatives, this.interpolatedSecondaryDerivatives[index]);
        return this.interpolatedSecondaryDerivatives[index];
    }

    public final void finalizeStep() throws MaxCountExceededException {
        if (!this.finalized) {
            this.doFinalize();
            this.finalized = true;
        }
    }

    protected void doFinalize() throws MaxCountExceededException {
    }

    public abstract void writeExternal(ObjectOutput var1) throws IOException;

    public abstract void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException;

    protected void writeBaseExternal(ObjectOutput out) throws IOException {
        if (this.currentState == null) {
            out.writeInt(-1);
        } else {
            out.writeInt(this.currentState.length);
        }
        out.writeDouble(this.globalPreviousTime);
        out.writeDouble(this.globalCurrentTime);
        out.writeDouble(this.softPreviousTime);
        out.writeDouble(this.softCurrentTime);
        out.writeDouble(this.h);
        out.writeBoolean(this.forward);
        out.writeObject(this.primaryMapper);
        out.write(this.secondaryMappers.length);
        for (EquationsMapper mapper : this.secondaryMappers) {
            out.writeObject(mapper);
        }
        if (this.currentState != null) {
            for (int i = 0; i < this.currentState.length; ++i) {
                out.writeDouble(this.currentState[i]);
            }
        }
        out.writeDouble(this.interpolatedTime);
        try {
            this.finalizeStep();
        } catch (MaxCountExceededException mcee) {
            IOException ioe = new IOException(mcee.getLocalizedMessage());
            ioe.initCause(mcee);
            throw ioe;
        }
    }

    protected double readBaseExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int i;
        int dimension = in.readInt();
        this.globalPreviousTime = in.readDouble();
        this.globalCurrentTime = in.readDouble();
        this.softPreviousTime = in.readDouble();
        this.softCurrentTime = in.readDouble();
        this.h = in.readDouble();
        this.forward = in.readBoolean();
        this.primaryMapper = (EquationsMapper)in.readObject();
        this.secondaryMappers = new EquationsMapper[in.read()];
        for (i = 0; i < this.secondaryMappers.length; ++i) {
            this.secondaryMappers[i] = (EquationsMapper)in.readObject();
        }
        this.dirtyState = true;
        if (dimension < 0) {
            this.currentState = null;
        } else {
            this.currentState = new double[dimension];
            for (i = 0; i < this.currentState.length; ++i) {
                this.currentState[i] = in.readDouble();
            }
        }
        this.interpolatedTime = Double.NaN;
        this.allocateInterpolatedArrays(dimension);
        this.finalized = true;
        return in.readDouble();
    }
}

