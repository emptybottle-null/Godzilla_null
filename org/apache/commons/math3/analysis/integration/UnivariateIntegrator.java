/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

public interface UnivariateIntegrator {
    public double getRelativeAccuracy();

    public double getAbsoluteAccuracy();

    public int getMinimalIterationCount();

    public int getMaximalIterationCount();

    public double integrate(int var1, UnivariateFunction var2, double var3, double var5) throws TooManyEvaluationsException, MaxCountExceededException, MathIllegalArgumentException, NullArgumentException;

    public int getEvaluations();

    public int getIterations();
}

