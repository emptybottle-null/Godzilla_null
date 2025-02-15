/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;

public interface UnivariateFunctionDifferentiator {
    public UnivariateDifferentiableFunction differentiate(UnivariateFunction var1);
}

