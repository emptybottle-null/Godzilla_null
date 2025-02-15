/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.exception.DimensionMismatchException;

public interface UnivariateDifferentiableFunction
extends UnivariateFunction {
    public DerivativeStructure value(DerivativeStructure var1) throws DimensionMismatchException;
}

