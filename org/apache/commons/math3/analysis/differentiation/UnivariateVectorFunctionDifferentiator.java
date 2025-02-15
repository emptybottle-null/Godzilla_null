/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.UnivariateVectorFunction;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableVectorFunction;

public interface UnivariateVectorFunctionDifferentiator {
    public UnivariateDifferentiableVectorFunction differentiate(UnivariateVectorFunction var1);
}

