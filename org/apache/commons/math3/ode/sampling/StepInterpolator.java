/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

import java.io.Externalizable;
import org.apache.commons.math3.exception.MaxCountExceededException;

public interface StepInterpolator
extends Externalizable {
    public double getPreviousTime();

    public double getCurrentTime();

    public double getInterpolatedTime();

    public void setInterpolatedTime(double var1);

    public double[] getInterpolatedState() throws MaxCountExceededException;

    public double[] getInterpolatedDerivatives() throws MaxCountExceededException;

    public double[] getInterpolatedSecondaryState(int var1) throws MaxCountExceededException;

    public double[] getInterpolatedSecondaryDerivatives(int var1) throws MaxCountExceededException;

    public boolean isForward();

    public StepInterpolator copy() throws MaxCountExceededException;
}

