/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

public interface MultivariateDifferentiableVectorFunction
extends MultivariateVectorFunction {
    public DerivativeStructure[] value(DerivativeStructure[] var1) throws MathIllegalArgumentException;
}

