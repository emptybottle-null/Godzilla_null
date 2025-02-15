/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface BaseUnivariateSolver<FUNC extends UnivariateFunction> {
    public int getMaxEvaluations();

    public int getEvaluations();

    public double getAbsoluteAccuracy();

    public double getRelativeAccuracy();

    public double getFunctionValueAccuracy();

    public double solve(int var1, FUNC var2, double var3, double var5) throws MathIllegalArgumentException, TooManyEvaluationsException;

    public double solve(int var1, FUNC var2, double var3, double var5, double var7) throws MathIllegalArgumentException, TooManyEvaluationsException;

    public double solve(int var1, FUNC var2, double var3);
}

