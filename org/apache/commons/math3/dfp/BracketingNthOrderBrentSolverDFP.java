/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.dfp;

import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver;
import org.apache.commons.math3.dfp.Dfp;
import org.apache.commons.math3.dfp.UnivariateDfpFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class BracketingNthOrderBrentSolverDFP
extends FieldBracketingNthOrderBrentSolver<Dfp> {
    public BracketingNthOrderBrentSolverDFP(Dfp relativeAccuracy, Dfp absoluteAccuracy, Dfp functionValueAccuracy, int maximalOrder) throws NumberIsTooSmallException {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy, maximalOrder);
    }

    @Override
    public Dfp getAbsoluteAccuracy() {
        return (Dfp)super.getAbsoluteAccuracy();
    }

    @Override
    public Dfp getRelativeAccuracy() {
        return (Dfp)super.getRelativeAccuracy();
    }

    @Override
    public Dfp getFunctionValueAccuracy() {
        return (Dfp)super.getFunctionValueAccuracy();
    }

    public Dfp solve(int maxEval, UnivariateDfpFunction f, Dfp min, Dfp max, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        return this.solve(maxEval, f, min, max, min.add(max).divide(2), allowedSolution);
    }

    public Dfp solve(int maxEval, final UnivariateDfpFunction f, Dfp min, Dfp max, Dfp startValue, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        MathUtils.checkNotNull(f);
        RealFieldUnivariateFunction<Dfp> fieldF = new RealFieldUnivariateFunction<Dfp>(){

            @Override
            public Dfp value(Dfp x) {
                return f.value(x);
            }
        };
        return this.solve(maxEval, fieldF, min, max, startValue, allowedSolution);
    }
}

