/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.Parameterizable;
import org.apache.commons.math3.ode.UnknownParameterException;

public interface ParameterJacobianProvider
extends Parameterizable {
    public void computeParameterJacobian(double var1, double[] var3, double[] var4, String var5, double[] var6) throws DimensionMismatchException, MaxCountExceededException, UnknownParameterException;
}

