/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface BracketedRealFieldUnivariateSolver<T extends RealFieldElement<T>> {
    public int getMaxEvaluations();

    public int getEvaluations();

    public T getAbsoluteAccuracy();

    public T getRelativeAccuracy();

    public T getFunctionValueAccuracy();

    public T solve(int var1, RealFieldUnivariateFunction<T> var2, T var3, T var4, AllowedSolution var5);

    public T solve(int var1, RealFieldUnivariateFunction<T> var2, T var3, T var4, T var5, AllowedSolution var6);
}

