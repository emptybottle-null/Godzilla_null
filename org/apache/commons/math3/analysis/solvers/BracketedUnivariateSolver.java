/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface BracketedUnivariateSolver<FUNC extends UnivariateFunction>
extends BaseUnivariateSolver<FUNC> {
    public double solve(int var1, FUNC var2, double var3, double var5, AllowedSolution var7);

    public double solve(int var1, FUNC var2, double var3, double var5, double var7, AllowedSolution var9);
}

