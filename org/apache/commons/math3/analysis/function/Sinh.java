/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.function.Cosh;
import org.apache.commons.math3.util.FastMath;

public class Sinh
implements UnivariateDifferentiableFunction,
DifferentiableUnivariateFunction {
    public double value(double x) {
        return FastMath.sinh(x);
    }

    @Deprecated
    public DifferentiableUnivariateFunction derivative() {
        return new Cosh();
    }

    public DerivativeStructure value(DerivativeStructure t) {
        return t.sinh();
    }
}

