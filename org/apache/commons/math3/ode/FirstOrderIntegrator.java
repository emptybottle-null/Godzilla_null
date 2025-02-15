/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.ODEIntegrator;

public interface FirstOrderIntegrator
extends ODEIntegrator {
    public double integrate(FirstOrderDifferentialEquations var1, double var2, double[] var4, double var5, double[] var7) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException;
}

